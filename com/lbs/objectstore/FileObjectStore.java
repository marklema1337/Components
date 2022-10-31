/*     */ package com.lbs.objectstore;
/*     */ 
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileObjectStore
/*     */ {
/*     */   public static final int MAGIC = 247397546;
/*     */   public static final long DELETED_ID = -9223372036854775808L;
/*     */   private static final int OFFSET_INDEXTAG = 4;
/*     */   private static final int OFFSET_DATA_START = 8;
/*     */   private static final int OFFSET_REC_HEADER = 12;
/*     */   public static final int READ_ONLY = 1;
/*     */   public static final int CREATE_NEW = 2;
/*     */   public static final int APPEND = 3;
/*     */   private RandomAccessFile m_File;
/*     */   private ArrayList m_List;
/*     */   private int m_LastObjectOffset;
/*     */   private boolean m_IndexChanged;
/*     */   private boolean m_Writable;
/*     */   
/*     */   public FileObjectStore(String file, int openMode) throws IOException {
/*  61 */     this(new File(file), openMode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileObjectStore(File file, int openMode) throws IOException {
/*  71 */     if (openMode < 1 && openMode > 3) {
/*  72 */       throw new IOException("Invalid open mode!");
/*     */     }
/*  74 */     this.m_File = new RandomAccessFile(file, (openMode == 1) ? 
/*  75 */         "r" : 
/*  76 */         "rw");
/*  77 */     this.m_List = new ArrayList();
/*     */     
/*  79 */     boolean skipLoad = false;
/*  80 */     if (openMode != 1) {
/*     */       
/*  82 */       this.m_Writable = true;
/*  83 */       if (openMode == 2) {
/*     */         
/*  85 */         this.m_File.setLength(0L);
/*  86 */         skipLoad = true;
/*     */       } 
/*  88 */       if (this.m_File.length() < 8L) {
/*     */         
/*  90 */         this.m_File.seek(0L);
/*  91 */         this.m_File.writeInt(247397546);
/*  92 */         this.m_File.writeInt(0);
/*  93 */         this.m_LastObjectOffset = 8;
/*  94 */         skipLoad = true;
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     if (!skipLoad) {
/*  99 */       loadIndexes();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(Serializable obj) throws IOException {
/* 108 */     long id = 0L;
/* 109 */     if (obj instanceof BusinessObject)
/* 110 */       id = ((BusinessObject)obj).getAutoIncrementValue(); 
/* 111 */     writeObject(obj, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(Serializable obj, long id) throws IOException {
/* 121 */     checkForValidFile(true);
/* 122 */     byte[] data = TransportUtil.serializeObjectPlain(obj);
/*     */     
/* 124 */     ObjectEntry entry = new ObjectEntry();
/* 125 */     entry.Size = data.length;
/* 126 */     entry.Offset = this.m_LastObjectOffset;
/* 127 */     entry.ID = id;
/* 128 */     this.m_File.seek(this.m_LastObjectOffset);
/*     */ 
/*     */     
/* 131 */     this.m_File.writeInt(data.length);
/* 132 */     this.m_File.writeLong(id);
/* 133 */     this.m_File.write(data, 0, data.length);
/* 134 */     this.m_LastObjectOffset += data.length + 12;
/*     */     
/* 136 */     this.m_File.writeInt(0);
/*     */     
/* 138 */     this.m_File.seek(4L);
/* 139 */     this.m_File.writeInt(0);
/* 140 */     this.m_List.add(entry);
/* 141 */     this.m_IndexChanged = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readObject(int index) throws IOException, ClassNotFoundException {
/* 152 */     checkForValidFile(false);
/* 153 */     if (index >= 0 && index < this.m_List.size()) {
/*     */       
/* 155 */       ObjectEntry entry = this.m_List.get(index);
/* 156 */       this.m_File.seek((entry.Offset + 12));
/* 157 */       byte[] buffer = new byte[entry.Size];
/* 158 */       this.m_File.readFully(buffer);
/* 159 */       return TransportUtil.deserializeObjectPlain(buffer);
/*     */     } 
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readObjectByID(long id) throws IOException, ClassNotFoundException {
/* 172 */     return readObject(indexOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(long id) {
/* 181 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/* 183 */       ObjectEntry entry = this.m_List.get(i);
/* 184 */       if (entry.ID == id)
/* 185 */         return i; 
/*     */     } 
/* 187 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean deleteObject(int index) throws IOException {
/* 192 */     checkForValidFile(false);
/* 193 */     if (index >= 0 && index < this.m_List.size()) {
/*     */       
/* 195 */       ObjectEntry entry = this.m_List.get(index);
/* 196 */       this.m_File.seek((entry.Offset + 4));
/* 197 */       this.m_File.writeLong(Long.MIN_VALUE);
/* 198 */       this.m_List.remove(index);
/* 199 */       this.m_IndexChanged = true;
/* 200 */       return true;
/*     */     } 
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean deleteObjectByID(long id) throws IOException {
/* 207 */     return deleteObject(indexOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 215 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 224 */     close(false);
/* 225 */     this.m_List.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close(boolean forceIndexWrite) throws IOException {
/* 236 */     boolean updateIndex = !(!forceIndexWrite && !this.m_IndexChanged);
/* 237 */     checkForValidFile(updateIndex);
/* 238 */     if (updateIndex)
/* 239 */       writeIndexes(); 
/* 240 */     this.m_File.close();
/* 241 */     this.m_File = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeIndexes() throws IOException {
/* 246 */     this.m_File.seek(this.m_LastObjectOffset);
/*     */     
/* 248 */     this.m_File.writeInt(0);
/*     */     
/* 250 */     this.m_File.writeInt(this.m_List.size());
/* 251 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/* 253 */       ObjectEntry entry = this.m_List.get(i);
/* 254 */       this.m_File.writeInt(entry.Size);
/* 255 */       this.m_File.writeInt(entry.Offset);
/* 256 */       this.m_File.writeLong(entry.ID);
/*     */     } 
/* 258 */     this.m_File.seek(4L);
/* 259 */     this.m_File.writeInt(this.m_LastObjectOffset);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadIndexes() throws IOException {
/* 264 */     this.m_List.clear();
/*     */     
/* 266 */     this.m_File.seek(0L);
/* 267 */     if (this.m_File.readInt() != 247397546) {
/* 268 */       throw new IOException("Not a valid object store!");
/*     */     }
/* 270 */     this.m_File.seek(4L);
/* 271 */     int offset = this.m_File.readInt();
/* 272 */     if (offset <= 0) {
/*     */ 
/*     */       
/* 275 */       this.m_File.seek(8L);
/*     */       
/*     */       try {
/* 278 */         this.m_LastObjectOffset = 8;
/* 279 */         int size = this.m_File.readInt();
/* 280 */         while (size > 0)
/*     */         {
/* 282 */           long id = this.m_File.readLong();
/* 283 */           if (id != Long.MIN_VALUE) {
/*     */             
/* 285 */             ObjectEntry entry = new ObjectEntry();
/* 286 */             entry.Size = size;
/* 287 */             entry.Offset = this.m_LastObjectOffset;
/* 288 */             entry.ID = id;
/* 289 */             this.m_List.add(entry);
/*     */           } 
/* 291 */           this.m_LastObjectOffset += size + 12;
/* 292 */           this.m_File.seek(this.m_LastObjectOffset);
/* 293 */           size = this.m_File.readInt();
/*     */         }
/*     */       
/* 296 */       } catch (EOFException eOFException) {}
/*     */ 
/*     */       
/* 299 */       this.m_IndexChanged = true;
/*     */     }
/*     */     else {
/*     */       
/* 303 */       this.m_LastObjectOffset = offset;
/* 304 */       this.m_File.seek((offset + 4));
/* 305 */       int size = this.m_File.readInt();
/* 306 */       for (int i = 0; i < size; i++) {
/*     */ 
/*     */         
/*     */         try {
/* 310 */           ObjectEntry entry = new ObjectEntry();
/* 311 */           entry.Size = this.m_File.readInt();
/* 312 */           entry.Offset = this.m_File.readInt();
/* 313 */           entry.ID = this.m_File.readLong();
/*     */           
/* 315 */           this.m_List.add(entry);
/*     */         }
/* 317 */         catch (EOFException e) {
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkForValidFile(boolean needWrite) throws IOException {
/* 327 */     if (this.m_File == null)
/* 328 */       throw new IOException("The file is closed!"); 
/* 329 */     if (needWrite && !this.m_Writable)
/* 330 */       throw new IOException("Not opened for writing"); 
/*     */   }
/*     */   
/*     */   static class ObjectEntry {
/*     */     public int Size;
/*     */     public int Offset;
/*     */     public long ID;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\objectstore\FileObjectStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */