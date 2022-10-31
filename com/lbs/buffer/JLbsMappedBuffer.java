/*     */ package com.lbs.buffer;
/*     */ 
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
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
/*     */ public class JLbsMappedBuffer
/*     */ {
/*     */   protected File m_File;
/*     */   protected RandomAccessFile m_RandFile;
/*     */   protected int m_Count;
/*     */   
/*     */   public JLbsMappedBuffer() {
/*     */     try {
/*  27 */       this.m_File = File.createTempFile("lbsbf", ".ltmp");
/*  28 */       this.m_File.deleteOnExit();
/*  29 */       this.m_RandFile = new RandomAccessFile(this.m_File, "rw");
/*  30 */       this.m_Count = 0;
/*     */     }
/*  32 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/*     */     try {
/*  41 */       this.m_RandFile.close();
/*     */       
/*  43 */       this.m_File.delete();
/*  44 */       this.m_File = null;
/*     */     }
/*  46 */     catch (Exception e) {
/*     */       
/*  48 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*  54 */     dispose();
/*  55 */     super.finalize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] data) throws Exception {
/*  60 */     checkPosition();
/*  61 */     this.m_RandFile.write(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] data, int index, int length) throws Exception {
/*  66 */     checkPosition();
/*  67 */     this.m_RandFile.write(data, index, length);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkPosition() throws IOException {
/*  72 */     this.m_RandFile.seek(this.m_RandFile.length());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  76 */     synchronized (this.m_RandFile) {
/*     */ 
/*     */       
/*     */       try {
/*  80 */         byte[] data = TransportUtil.serializeObjectPlain(object);
/*  81 */         int length = (data != null) ? data.length : 0;
/*  82 */         checkPosition();
/*  83 */         this.m_RandFile.writeInt(length);
/*  84 */         if (data != null)
/*  85 */           write(data); 
/*  86 */         this.m_Count++;
/*  87 */         return true;
/*     */       }
/*  89 */       catch (Exception e) {
/*     */         
/*  91 */         if (JLbsConstants.DEBUG)
/*  92 */           System.out.println("MappedBuffer.add: " + e); 
/*     */       } 
/*     */     } 
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList toArrayList() {
/* 100 */     synchronized (this) {
/*     */ 
/*     */       
/*     */       try {
/* 104 */         long fileLength = this.m_RandFile.length();
/* 105 */         this.m_RandFile.seek(0L);
/* 106 */         ArrayList<Object> result = new ArrayList();
/* 107 */         while (this.m_RandFile.getFilePointer() < fileLength) {
/*     */           
/* 109 */           int length = this.m_RandFile.readInt();
/* 110 */           if (length > 0) {
/*     */             
/* 112 */             byte[] data = new byte[length];
/* 113 */             this.m_RandFile.read(data);
/* 114 */             Object o = TransportUtil.deserializeObjectPlain(data);
/* 115 */             if (o != null)
/* 116 */               result.add(o);  continue;
/*     */           } 
/* 118 */           if (length == 0) {
/*     */             continue;
/*     */           }
/*     */           break;
/*     */         } 
/* 123 */         return result;
/*     */       }
/* 125 */       catch (Exception e) {
/*     */         
/* 127 */         if (JLbsConstants.DEBUG)
/* 128 */           System.out.println("MappedBuffer.toArrayList: " + e); 
/*     */       } 
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int getItemCount() {
/* 136 */     return this.m_Count;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\buffer\JLbsMappedBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */