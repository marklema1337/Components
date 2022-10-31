/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.crypto.JLbsCryptoUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.zip.Adler32;
/*     */ import java.util.zip.Checksum;
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
/*     */ public class DBIndexes
/*     */   extends DBEntityCollection
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ArrayList m_CustIndexes;
/*     */   
/*     */   public DBIndexes(DBTable table) {
/*  30 */     super(table);
/*  31 */     this.m_CustIndexes = new ArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex item(int index) {
/*  36 */     return (DBIndex)entity(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(DBIndex index) {
/*  41 */     boolean ok = add(index);
/*     */     
/*  43 */     if (index.getSegments().size() == 1) {
/*     */       
/*  45 */       DBTable table = (DBTable)getOwner();
/*  46 */       String segName = index.getSegments().item(0).getName();
/*  47 */       DBField field = table.getFields().get(segName);
/*     */       
/*  49 */       if (field.isPrimaryKey()) {
/*     */         
/*  51 */         index.setPrimary(true);
/*  52 */         index.setUnique(true);
/*  53 */         table.setKeyIndex(index);
/*     */       } 
/*     */     } 
/*  56 */     if (index.isSecondaryKey()) {
/*     */       
/*  58 */       DBTable table = (DBTable)getOwner();
/*  59 */       table.setSecondaryKeyIndex(index);
/*     */     } 
/*     */     
/*  62 */     return ok;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBIndex get(String indexName) {
/*  71 */     DBIndex index = (DBIndex)getEntity(indexName);
/*  72 */     if (index != null)
/*  73 */       return index; 
/*  74 */     return getBySuffix(indexName);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex getByPhysicalName(String name) {
/*  79 */     int idx = findByPhysicalName(name);
/*     */     
/*  81 */     if (idx == -1) {
/*  82 */       return null;
/*     */     }
/*  84 */     return item(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex getBySuffix(String name) {
/*  89 */     int idx = findBySuffix(name);
/*     */     
/*  91 */     if (idx == -1) {
/*  92 */       return null;
/*     */     }
/*  94 */     return item(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int findBySuffix(String suffix) {
/* 100 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 102 */       DBIndex index = item(i);
/* 103 */       if (StringUtil.equals(index.getSuffix(), suffix))
/* 104 */         return i; 
/*     */     } 
/* 106 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/* 111 */     super.remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getCustIndexes() {
/* 116 */     return this.m_CustIndexes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustIndexes(ArrayList custIndexes) {
/* 121 */     this.m_CustIndexes = custIndexes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void add2HashBuffer(DBIndex dbIndex, ByteArrayOutputStream os) {
/*     */     try {
/* 128 */       if (os == null) {
/*     */         return;
/*     */       }
/* 131 */       String indexStr = getIndexProp(dbIndex);
/* 132 */       if (indexStr != null) {
/* 133 */         os.write(indexStr.getBytes());
/*     */       }
/* 135 */     } catch (Exception e) {
/*     */       
/* 137 */       LbsConsole.getLogger(DBIndexes.class).error("add2HashBuffer", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getIndexProp(DBIndex index) {
/* 144 */     if (index == null)
/* 145 */       return ""; 
/* 146 */     StringBuilder buffer = new StringBuilder();
/* 147 */     buffer.append(index.getPhysicalName());
/* 148 */     buffer.append(",");
/* 149 */     buffer.append(index.isUnique() ? 1 : 0);
/*     */ 
/*     */     
/* 152 */     buffer.append("-");
/* 153 */     DBIndexSegments segments = index.getSegments();
/* 154 */     DBIndexSegment segment = null;
/* 155 */     for (int i = 0; i < segments.size(); i++) {
/*     */       
/* 157 */       segment = segments.item(i);
/* 158 */       buffer.append(segment.getPhysicalName());
/* 159 */       buffer.append(",");
/*     */     } 
/* 161 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getHashValue(ByteArrayOutputStream os) {
/* 166 */     long checksum = -1L;
/* 167 */     Checksum checksumEngine = new Adler32();
/*     */ 
/*     */     
/*     */     try {
/* 171 */       if (os == null)
/* 172 */         return -1L; 
/* 173 */       byte[] buff = JLbsCryptoUtil.createMD5Digest(os.toByteArray());
/* 174 */       if (buff == null)
/* 175 */         return -1L; 
/* 176 */       checksumEngine.update(buff, 0, buff.length);
/* 177 */       buff = null;
/* 178 */       checksum = checksumEngine.getValue();
/*     */     }
/* 180 */     catch (Exception e) {
/*     */       
/* 182 */       LbsConsole.getLogger(DBIndexes.class).error("getHashValue", e);
/* 183 */       return -1L;
/*     */     } 
/*     */     
/* 186 */     checksumEngine.reset();
/* 187 */     os = null;
/* 188 */     return checksum;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBIndexes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */