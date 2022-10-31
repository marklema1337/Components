/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
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
/*     */ public class JLbsJarCache
/*     */ {
/*     */   private static ArrayList<JLbsJarStreamHolder> ms_HolderList;
/*     */   private static NameValueMap ms_List;
/*     */   
/*     */   static {
/*  29 */     clearCache();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean add(String jarName, JLbsJarStreamHolder jar) {
/*  34 */     if (jar != null && !hasJarFile(jarName)) {
/*     */       
/*  36 */       synchronized (ms_List) {
/*     */         
/*  38 */         ms_List.setValue(jarName, jar);
/*     */       } 
/*  40 */       synchronized (ms_HolderList) {
/*     */         
/*  42 */         ms_HolderList.add(0, jar);
/*     */       } 
/*  44 */       return true;
/*     */     } 
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] getEntry(String fileName) {
/*  51 */     fileName = fileName.replaceAll("\\\\", "/");
/*  52 */     synchronized (ms_HolderList) {
/*     */       
/*  54 */       for (int i = 0; i < ms_HolderList.size(); i++) {
/*     */         
/*  56 */         JLbsJarStreamHolder jar = ms_HolderList.get(i);
/*  57 */         byte[] entryData = jar.getEntryData(fileName);
/*  58 */         if (entryData != null)
/*     */         {
/*  60 */           return entryData;
/*     */         }
/*     */       } 
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsJarStreamHolder getJarHolder(String fileName) {
/*  69 */     return (JLbsJarStreamHolder)ms_List.getValue(fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasJarFile(String fileName) {
/*  74 */     synchronized (ms_List) {
/*     */       
/*  76 */       return (ms_List.getValue(fileName) != null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearCache() {
/*  82 */     ms_HolderList = new ArrayList<>();
/*  83 */     ms_List = new NameValueMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] getClassEntry(String className) {
/*  88 */     StringBuilder buffer = new StringBuilder(className);
/*     */     int index;
/*  90 */     while ((index = buffer.indexOf(".")) >= 0)
/*  91 */       buffer.setCharAt(index, '/'); 
/*  92 */     buffer.append(".class");
/*  93 */     return getEntry(buffer.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] getResourceEntry(String resName) {
/*  98 */     return getEntry(getResourcePath(resName));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getResourcePath(String resName) {
/* 103 */     return JLbsFileUtil.appendPath("resources", resName, '/');
/*     */   }
/*     */ 
/*     */   
/*     */   public static URL getResourceURL(String resPath) {
/* 108 */     synchronized (ms_HolderList) {
/*     */       
/* 110 */       for (JLbsJarStreamHolder holder : ms_HolderList) {
/*     */         
/* 112 */         if (holder.hasEntry(resPath)) {
/*     */           
/* 114 */           File jarFile = holder.getTmpFile();
/* 115 */           if (jarFile == null) {
/*     */             continue;
/*     */           }
/*     */           try {
/* 119 */             URI fileUri = jarFile.toURI();
/* 120 */             return new URL("jar:" + fileUri.toString() + "!/" + resPath);
/*     */           }
/* 122 */           catch (MalformedURLException e) {
/*     */             
/* 124 */             e.printStackTrace();
/* 125 */             return null;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\JLbsJarCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */