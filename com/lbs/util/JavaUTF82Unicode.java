/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
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
/*     */ public class JavaUTF82Unicode
/*     */ {
/*     */   protected void processDirectory(File file) {
/*  39 */     String[] files = file.list();
/*  40 */     if (files != null)
/*     */     {
/*  42 */       for (int i = 0; i < files.length; i++) {
/*     */         
/*  44 */         String fileName = String.valueOf(file.getPath()) + "/" + files[i];
/*  45 */         File dirFile = new File(fileName);
/*  46 */         if (dirFile.isDirectory()) {
/*  47 */           processDirectory(dirFile);
/*  48 */         } else if (dirFile.exists() && fileName.endsWith(".java")) {
/*  49 */           processFile(fileName);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void processFile(String filePath) {
/*  56 */     System.out.print("Processing the file : " + filePath);
/*  57 */     FileInputStream stream = null;
/*  58 */     ByteArrayOutputStream byteStream = null;
/*  59 */     OutputStreamWriter writer = null;
/*  60 */     FileOutputStream fos = null;
/*     */     
/*     */     try {
/*  63 */       stream = new FileInputStream(filePath);
/*  64 */       byteStream = new ByteArrayOutputStream();
/*  65 */       byte[] buffer = new byte[4096];
/*     */       int read;
/*  67 */       while ((read = stream.read(buffer, 0, buffer.length)) > 0)
/*  68 */         byteStream.write(buffer, 0, read); 
/*  69 */       byte[] totalBytes = byteStream.toByteArray();
/*  70 */       String sAscii = new String(totalBytes, "UTF8");
/*  71 */       String tmpFileName = String.valueOf(filePath) + ".uncf";
/*  72 */       char[] charArr = sAscii.toCharArray();
/*  73 */       fos = new FileOutputStream(tmpFileName);
/*  74 */       writer = new OutputStreamWriter(fos, "unicode");
/*  75 */       writer.write(charArr);
/*  76 */       File sourceFile = new File(filePath);
/*  77 */       File tempFile = new File(String.valueOf(filePath) + ".jau.bak");
/*  78 */       File destFile = new File(tmpFileName);
/*  79 */       tempFile.delete();
/*  80 */       sourceFile.renameTo(tempFile);
/*  81 */       destFile.renameTo(sourceFile);
/*  82 */       tempFile.delete();
/*  83 */       System.out.println(" ... Done ...");
/*     */     }
/*  85 */     catch (Exception e) {
/*     */       
/*  87 */       System.out.println("\n\tException: " + e);
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/*  94 */         if (stream != null) {
/*  95 */           stream.close();
/*     */         }
/*  97 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 102 */         if (byteStream != null)
/*     */         {
/* 104 */           byteStream.close();
/* 105 */           byteStream = null;
/*     */         }
/*     */       
/* 108 */       } catch (Exception e2) {
/*     */         
/* 110 */         e2.printStackTrace();
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 115 */         if (fos != null) {
/* 116 */           fos.close();
/*     */         }
/* 118 */       } catch (Exception e2) {
/*     */         
/* 120 */         e2.printStackTrace();
/*     */       } 
/*     */       
/*     */       try {
/* 124 */         if (writer != null) {
/* 125 */           writer.close();
/*     */         }
/* 127 */       } catch (Exception e2) {
/*     */         
/* 129 */         e2.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JavaUTF82Unicode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */