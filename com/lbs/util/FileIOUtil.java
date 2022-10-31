/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
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
/*     */ public class FileIOUtil
/*     */ {
/*     */   public static String[] getDirectoryListing(String path, String extension) {
/*  26 */     File directory = new File(path);
/*  27 */     FileNameFilterGeneric filter = new FileNameFilterGeneric(extension);
/*  28 */     return directory.list(filter);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getExtension(File file) {
/*  33 */     if (file == null)
/*  34 */       return null; 
/*  35 */     String name = file.getName();
/*  36 */     int iDotIndex = name.lastIndexOf(".");
/*  37 */     if (iDotIndex < 0)
/*  38 */       return null; 
/*  39 */     int iSlashIndex = name.lastIndexOf(File.separator);
/*  40 */     if (iSlashIndex > iDotIndex)
/*  41 */       return null; 
/*  42 */     return name.substring(iDotIndex + 1, name.length());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean saveFile(String path, byte[] data, boolean bOverwrite, boolean bAppend, boolean bScramble) throws IOException {
/*  48 */     return saveFile(path, data, 0, (data != null) ? 
/*  49 */         data.length : 
/*  50 */         0, bOverwrite, bAppend, bScramble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean saveFile(String path, byte[] data, int index, int length, boolean bOverwrite, boolean bAppend, boolean bScramble) throws IOException {
/*     */     try {
/*  58 */       File file = new File(path);
/*  59 */       if (file.exists() && !bOverwrite)
/*  60 */         return false; 
/*  61 */       FileOutputStream stream = new FileOutputStream(file, bAppend);
/*  62 */       stream.write(data, index, length);
/*  63 */       stream.close();
/*  64 */       return true;
/*     */     }
/*  66 */     catch (IOException e) {
/*     */       
/*  68 */       throw e;
/*     */     }
/*  70 */     catch (Exception e) {
/*     */       
/*  72 */       System.out.println("saveFile :" + e);
/*     */       
/*  74 */       return false;
/*     */     } 
/*     */   }
/*     */   public static byte[] loadFile(String path, boolean bScramble) throws IOException {
/*     */     int readLen;
/*  79 */     byte[] result = null;
/*  80 */     File file = new File(path);
/*  81 */     if (!file.exists())
/*  82 */       return null; 
/*  83 */     FileInputStream stream = new FileInputStream(file);
/*  84 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  85 */     byte[] buffer = new byte[4096];
/*     */ 
/*     */     
/*     */     do {
/*  89 */       readLen = stream.read(buffer, 0, buffer.length);
/*  90 */       if (readLen <= 0)
/*  91 */         continue;  outStream.write(buffer, 0, readLen);
/*     */     }
/*  93 */     while (readLen > 0);
/*  94 */     stream.close();
/*  95 */     result = outStream.toByteArray();
/*  96 */     outStream.close();
/*  97 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean acceptFileForWildCard(String fileName, int preWildCardLength, String searchStr, String extension) {
/* 102 */     FileNameFilterGeneric filter = new FileNameFilterGeneric(extension);
/* 103 */     return filter.acceptStartingWith(fileName, preWildCardLength, searchStr, extension);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\FileIOUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */