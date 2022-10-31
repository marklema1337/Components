/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.jar.JarInputStream;
/*     */ import java.util.zip.ZipEntry;
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
/*     */ public class JLbsJarStreamHolder
/*     */ {
/*     */   private Hashtable<String, byte[]> m_Entries;
/*     */   private String m_PathPrefix;
/*     */   private File m_TmpFile;
/*     */   
/*     */   public void load(byte[] data, boolean verify) throws IOException {
/*  33 */     clear();
/*  34 */     if (data != null && data.length > 0) {
/*     */       
/*  36 */       this.m_Entries = (Hashtable)new Hashtable<>(150);
/*  37 */       JarInputStream jar = new JarInputStream(new ByteArrayInputStream(data), verify);
/*  38 */       ZipEntry entry = jar.getNextEntry();
/*  39 */       while (entry != null) {
/*     */         
/*  41 */         String name = entry.getName();
/*  42 */         byte[] entryData = readEntry(jar);
/*  43 */         if (entryData != null)
/*  44 */           this.m_Entries.put(name, entryData); 
/*  45 */         entry = jar.getNextEntry();
/*     */       } 
/*  47 */       jar.close();
/*     */     } 
/*  49 */     this.m_TmpFile = File.createTempFile("jar_cache", ".jar");
/*  50 */     JLbsFileUtil.write2File(this.m_TmpFile.getAbsolutePath(), data);
/*  51 */     this.m_TmpFile.deleteOnExit();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  56 */     this.m_Entries = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEntry(String fileName) {
/*  61 */     if (this.m_PathPrefix != null && fileName.startsWith(this.m_PathPrefix))
/*  62 */       fileName = fileName.substring(this.m_PathPrefix.length()); 
/*  63 */     Object obj = (this.m_Entries != null) ? this.m_Entries.get(fileName) : null;
/*     */ 
/*     */     
/*  66 */     if (obj != null)
/*  67 */       return true; 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getEntryData(String fileName) {
/*  73 */     if (this.m_PathPrefix != null && fileName.startsWith(this.m_PathPrefix))
/*  74 */       fileName = fileName.substring(this.m_PathPrefix.length()); 
/*  75 */     Object obj = (this.m_Entries != null) ? this.m_Entries.get(fileName) : null;
/*     */ 
/*     */     
/*  78 */     if (obj != null)
/*  79 */       return (byte[])obj; 
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[] readEntry(JarInputStream jar) {
/*  85 */     if (jar != null) {
/*     */       
/*     */       try {
/*  88 */         ByteArrayOutputStream stream = new ByteArrayOutputStream();
/*  89 */         byte[] buff = new byte[4096];
/*     */         int read;
/*  91 */         while ((read = jar.read(buff, 0, buff.length)) > 0)
/*  92 */           stream.write(buff, 0, read); 
/*  93 */         return stream.toByteArray();
/*     */       }
/*  95 */       catch (Exception exception) {}
/*     */     }
/*     */     
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPathPrefix() {
/* 103 */     return this.m_PathPrefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPathPrefix(String string) {
/* 108 */     this.m_PathPrefix = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public File getTmpFile() {
/* 113 */     return this.m_TmpFile;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\JLbsJarStreamHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */