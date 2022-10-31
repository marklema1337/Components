/*     */ package com.lbs.profiler.helper;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
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
/*     */ public class JLbsProfileUtil
/*     */ {
/*     */   public static Object deserializeObjectPlain(byte[] data) throws IOException, ClassNotFoundException {
/*  28 */     ByteArrayInputStream inStream = new ByteArrayInputStream(data);
/*  29 */     DataInputStream objStream = new DataInputStream(inStream);
/*  30 */     Object object = null;
/*     */     
/*     */     try {
/*  33 */       ObjectInputStream localStream = new ObjectInputStream(objStream);
/*  34 */       object = localStream.readObject();
/*     */     }
/*  36 */     catch (EOFException eOFException) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/*  41 */       objStream.close();
/*  42 */       inStream.close();
/*     */     } 
/*  44 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] serializeObjectPlain(Object obj) throws IOException {
/*  49 */     byte[] data = null;
/*  50 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  51 */     ObjectOutputStream objStream = new ObjectOutputStream(outStream);
/*     */     
/*     */     try {
/*  54 */       objStream.writeObject(obj);
/*  55 */       data = outStream.toByteArray();
/*     */     }
/*  57 */     catch (EOFException eOFException) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/*  62 */       objStream.close();
/*  63 */       outStream.close();
/*     */     } 
/*  65 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] readFile(String path) throws IOException {
/*  70 */     return readFile(new FileInputStream(path));
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] readFile(InputStream stream) throws IOException {
/*  75 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream(stream.available());
/*  76 */     byte[] buffer = new byte[4096];
/*     */     int read;
/*  78 */     while ((read = stream.read(buffer, 0, buffer.length)) > 0)
/*  79 */       outStream.write(buffer, 0, read); 
/*  80 */     stream.close();
/*  81 */     return outStream.toByteArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean ensurePathExists(File file) {
/*  86 */     if (file == null)
/*  87 */       return true; 
/*  88 */     if (file.exists() && file.isDirectory())
/*  89 */       return true; 
/*  90 */     if (!ensurePathExists(file.getParentFile()))
/*  91 */       return false; 
/*  92 */     return file.mkdir();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String appendPath(String path, String fileName) {
/*  97 */     return String.valueOf(ensurePath(path)) + ensureFileName(fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String appendPath(String path, String fileName, char separator) {
/* 102 */     return String.valueOf(ensurePath(path, separator)) + ensureFileName(fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String ensurePath(String path, char separator) {
/* 107 */     String result = (path != null) ? 
/* 108 */       path : 
/* 109 */       "";
/* 110 */     if (result.lastIndexOf(separator) != result.length() - 1)
/* 111 */       result = String.valueOf(result) + separator; 
/* 112 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String s) {
/* 117 */     return !(s != null && s.length() != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String ensurePath(String path) {
/* 122 */     return ensurePath(path, File.separatorChar);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String ensureFileName(String path) {
/* 127 */     if (!isEmpty(path) && path.startsWith(File.separator))
/* 128 */       return path.substring(1); 
/* 129 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean arrayContains(Object key, Object[] array) {
/* 137 */     for (int i = 0; i < array.length; i++) {
/*     */       
/* 139 */       if (array[i].equals(key))
/* 140 */         return true; 
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\JLbsProfileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */