/*     */ package com.lbs.invoke;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
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
/*     */ public class JLbsClassLoader
/*     */   extends ClassLoader
/*     */ {
/*     */   ClassLoader m_Parent;
/*     */   
/*     */   public JLbsClassLoader() {
/*  25 */     super(Thread.currentThread().getContextClassLoader());
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[] loadClassData(File f) throws IOException {
/*  30 */     int size = 1024;
/*  31 */     int count = 0;
/*  32 */     byte[] b = null;
/*  33 */     b = new byte[size];
/*     */ 
/*     */ 
/*     */     
/*  37 */     FileInputStream fis = new FileInputStream(f);
/*     */     int c;
/*  39 */     while ((c = fis.read()) != -1) {
/*     */       
/*  41 */       if (count < size) {
/*  42 */         b[count++] = (byte)c;
/*     */         continue;
/*     */       } 
/*  45 */       byte[] arrayOfByte = new byte[size * 2];
/*  46 */       System.arraycopy(b, 0, arrayOfByte, 0, size);
/*  47 */       b = arrayOfByte;
/*  48 */       size *= 2;
/*  49 */       b[count++] = (byte)c;
/*     */     } 
/*     */     
/*  52 */     byte[] temp = new byte[count];
/*  53 */     System.arraycopy(b, 0, temp, 0, count);
/*  54 */     b = temp;
/*  55 */     fis.close();
/*     */     
/*  57 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[] loadClassData(String path) throws IOException {
/*  62 */     File f = new File(path);
/*  63 */     return loadClassData(f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassName(File file) {
/*  68 */     String className = file.getName();
/*  69 */     className = className.substring(0, className.length() - 6);
/*     */ 
/*     */     
/*  72 */     while (file.getParentFile() != null) {
/*     */       
/*  74 */       file = file.getParentFile();
/*  75 */       String segment = file.getName();
/*     */       
/*  77 */       className = String.valueOf(segment) + "." + className;
/*     */       
/*  79 */       if (segment == null) {
/*     */         break;
/*     */       }
/*  82 */       if (segment.equals("com") || segment.equals("org")) {
/*     */         break;
/*     */       }
/*     */     } 
/*  86 */     return className;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class loadClassFromFile(File file) throws IOException {
/*  92 */     String className = getClassName(file);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     byte[] b = loadClassData(file);
/*  99 */     return defineClass(className, b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class loadClass(String className, byte[] code) {
/* 109 */     Class<?> c = findLoadedClass(className);
/* 110 */     if (c == null) {
/*     */       
/* 112 */       c = defineClass(className, code, 0, code.length);
/* 113 */       resolveClass(c);
/* 114 */       return c;
/*     */     } 
/*     */ 
/*     */     
/* 118 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class loadClassFromFile(String className, String path) throws IOException {
/* 124 */     Class<?> c = findLoadedClass(className);
/* 125 */     if (c == null) {
/*     */       
/* 127 */       byte[] b = loadClassData(String.valueOf(path) + className + ".class");
/* 128 */       String cName = className.replace('/', '.');
/* 129 */       return defineClass(cName, b, 0, b.length);
/*     */     } 
/* 131 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class loadClassFromJar(String jarFile, String className) throws MalformedURLException, ClassNotFoundException {
/* 137 */     URL[] url = new URL[1];
/*     */     
/* 139 */     url[0] = (new File(jarFile)).toURL();
/* 140 */     URLClassLoader loader = new URLClassLoader(url);
/* 141 */     Class<?> drvClass = loader.loadClass(className);
/*     */     
/* 143 */     return drvClass;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\JLbsClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */