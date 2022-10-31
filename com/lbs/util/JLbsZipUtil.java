/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsZipUtil
/*     */ {
/*     */   private static final int BUFFER_SIZE = 4096;
/*     */   
/*     */   public static void zipFolder(String sourceFilePath, String zipFilePath) {
/*     */     try {
/*  25 */       zip(sourceFilePath, zipFilePath);
/*     */     }
/*  27 */     catch (IOException e) {
/*     */       
/*  29 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void zip(String sourceDirPath, String zipFilePath) throws IOException {
/*  35 */     Path p = Files.createFile(Paths.get(zipFilePath, new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
/*  36 */     Exception exception1 = null, exception2 = null;
/*     */   }
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
/*     */   public static void unzip(String zipFilePath) {
/*     */     try {
/*  59 */       unzip(zipFilePath, zipFilePath.replace(".zip", ""));
/*     */     }
/*  61 */     catch (IOException e) {
/*     */       
/*  63 */       e.printStackTrace();
/*     */     } 
/*     */   }
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
/*     */   public static void unzip(String zipFilePath, String destDirectory) throws IOException {
/*  78 */     File destDir = new File(destDirectory);
/*  79 */     if (!destDir.exists())
/*     */     {
/*  81 */       destDir.mkdir();
/*     */     }
/*  83 */     ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
/*  84 */     ZipEntry entry = zipIn.getNextEntry();
/*     */     
/*  86 */     while (entry != null) {
/*     */       
/*  88 */       String filePath = String.valueOf(destDirectory) + File.separator + entry.getName();
/*  89 */       if (!entry.isDirectory()) {
/*     */ 
/*     */         
/*  92 */         String parentDir = filePath.substring(0, filePath.lastIndexOf(File.separator));
/*  93 */         if (!(new File(parentDir)).exists())
/*  94 */           (new File(parentDir)).mkdirs(); 
/*  95 */         extractFile(zipIn, filePath);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 100 */         File dir = new File(filePath);
/* 101 */         dir.mkdir();
/*     */       } 
/* 103 */       zipIn.closeEntry();
/* 104 */       entry = zipIn.getNextEntry();
/*     */     } 
/* 106 */     zipIn.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
/* 117 */     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
/* 118 */     byte[] bytesIn = new byte[4096];
/* 119 */     int read = 0;
/* 120 */     while ((read = zipIn.read(bytesIn)) != -1)
/*     */     {
/* 122 */       bos.write(bytesIn, 0, read);
/*     */     }
/* 124 */     bos.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] zipToBlob(String zipPath) {
/* 129 */     Path path = Paths.get(zipPath, new String[0]);
/*     */     
/*     */     try {
/* 132 */       byte[] blob = Files.readAllBytes(path);
/* 133 */       return blob;
/*     */     }
/* 135 */     catch (IOException e) {
/*     */       
/* 137 */       e.printStackTrace();
/*     */       
/* 139 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void blobToZip(String destPath, String zipName, byte[] blob) {
/*     */     try {
/* 146 */       Path path = Files.createFile(Paths.get(String.valueOf(destPath) + File.separator + zipName, new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
/* 147 */       Files.write(path, blob, new java.nio.file.OpenOption[0]);
/*     */     }
/* 149 */     catch (IOException e1) {
/*     */       
/* 151 */       e1.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsZipUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */