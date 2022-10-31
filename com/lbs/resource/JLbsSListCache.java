/*     */ package com.lbs.resource;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.Hashtable;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsSListCache
/*     */   implements IStringListManager
/*     */ {
/*     */   protected String m_SListRoot;
/*     */   protected String m_Language;
/*  26 */   protected Hashtable m_Cache = new Hashtable<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsSListCache(String language) {
/*  31 */     this.m_SListRoot = JLbsFileUtil.getClientRootDirectory("slist");
/*  32 */     this.m_Language = language;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsSListCache(String slistRoot, String language) {
/*  37 */     this.m_SListRoot = JLbsFileUtil.ensurePath(slistRoot);
/*  38 */     this.m_Language = language;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] getResourceData(String path, int resNo) {
/*     */     try {
/*  45 */       String jarPath = JLbsFileUtil.appendPath(path, String.valueOf(JLbsStringUtil.getSListJarName(resNo)) + ".jar");
/*  46 */       File file = new File(jarPath);
/*  47 */       if (file.exists() && file.canRead()) {
/*     */         
/*  49 */         ZipInputStream zipStream = new ZipInputStream(new FileInputStream(file));
/*  50 */         ZipEntry entry = zipStream.getNextEntry();
/*  51 */         String fileName = String.valueOf(resNo) + ".txt";
/*  52 */         while (entry != null) {
/*     */           
/*  54 */           if (JLbsStringUtil.areEqual(entry.getName(), fileName)) {
/*     */             
/*  56 */             if (!entry.isDirectory()) {
/*     */               
/*  58 */               ByteArrayOutputStream out = new ByteArrayOutputStream();
/*  59 */               byte[] buf = new byte[4096];
/*     */               int len;
/*  61 */               while ((len = zipStream.read(buf, 0, buf.length)) > 0)
/*  62 */                 out.write(buf, 0, len); 
/*  63 */               out.close();
/*  64 */               zipStream.closeEntry();
/*  65 */               zipStream.close();
/*  66 */               return out.toByteArray();
/*     */             } 
/*     */             break;
/*     */           } 
/*  70 */           entry = zipStream.getNextEntry();
/*     */         } 
/*  72 */         zipStream.close();
/*     */       } 
/*  74 */       String resFile = JLbsFileUtil.appendPath(path, String.valueOf(resNo) + ".txt");
/*  75 */       return JLbsFileUtil.readFile(resFile);
/*     */     }
/*  77 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  80 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getStringList(int resNumber) {
/*  86 */     Integer hashKey = Integer.valueOf(resNumber);
/*  87 */     Object obj = this.m_Cache.get(hashKey);
/*  88 */     if (obj instanceof JLbsStringList)
/*  89 */       return (JLbsStringList)obj; 
/*  90 */     String path = (this.m_Language != null) ? JLbsFileUtil.appendPath(this.m_SListRoot, this.m_Language) : this.m_SListRoot;
/*     */     
/*     */     try {
/*  93 */       byte[] rawData = getResourceData(path, resNumber);
/*  94 */       String fileContent = JLbsStringUtil.getString(rawData);
/*  95 */       if (fileContent != null)
/*     */       {
/*  97 */         JLbsStringList list = new JLbsStringList();
/*  98 */         BufferedReader reader = new BufferedReader(new StringReader(fileContent));
/*     */         
/* 100 */         String line = reader.readLine();
/* 101 */         while (line != null) {
/*     */           
/* 103 */           if (line.length() > 0)
/* 104 */             list.add(line); 
/* 105 */           line = reader.readLine();
/*     */         } 
/* 107 */         this.m_Cache.put(hashKey, list);
/* 108 */         return list;
/*     */       }
/*     */     
/* 111 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSListRoot() {
/* 121 */     return this.m_SListRoot;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSListRoot(String string) {
/* 126 */     this.m_SListRoot = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/* 131 */     return this.m_Language;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLanguage(String string) {
/* 136 */     this.m_Language = string;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\resource\JLbsSListCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */