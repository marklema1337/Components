/*     */ package com.lbs.platform.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
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
/*     */ public class FileSystemUtil
/*     */ {
/*     */   public static final String UNDEFINED = "UNDEFINED";
/*     */   public static final int INIT_PROBLEM = -1;
/*     */   public static final int OTHER = 0;
/*     */   public static final int WINDOWS = 1;
/*     */   public static final int UNIX = 2;
/*     */   public static final int POSIX_UNIX = 3;
/*     */   public static final int OS;
/*     */   public static final int X86 = 1;
/*     */   public static final int X64 = 2;
/*     */   public static final int OS_BIT;
/*     */   
/*     */   static {
/*  43 */     int os = 0;
/*  44 */     int os_bit = 1;
/*     */     
/*     */     try {
/*  47 */       String osName = System.getProperty("os.name");
/*  48 */       if (osName == null)
/*     */       {
/*  50 */         throw new IOException("os.name not found");
/*     */       }
/*  52 */       osName = osName.toLowerCase(Locale.UK);
/*     */       
/*  54 */       if (osName.indexOf("windows") != -1)
/*     */       {
/*  56 */         os = 1;
/*     */       }
/*  58 */       else if (osName.indexOf("linux") != -1 || osName.indexOf("sun os") != -1 || osName.indexOf("sunos") != -1 || 
/*  59 */         osName.indexOf("solaris") != -1 || osName.indexOf("mpe/ix") != -1 || osName.indexOf("totalbsd") != -1 || 
/*  60 */         osName.indexOf("irix") != -1 || osName.indexOf("digital unix") != -1 || osName.indexOf("unix") != -1 || 
/*  61 */         osName.indexOf("mac os x") != -1)
/*     */       {
/*  63 */         os = 2;
/*     */       }
/*  65 */       else if (osName.indexOf("hp-ux") != -1 || osName.indexOf("aix") != -1)
/*     */       {
/*  67 */         os = 3;
/*     */       }
/*     */       else
/*     */       {
/*  71 */         os = 0;
/*     */       }
/*     */     
/*     */     }
/*  75 */     catch (Exception ex) {
/*     */       
/*  77 */       os = -1;
/*     */     } 
/*     */     
/*     */     try {
/*  81 */       String osArch = System.getProperty("os.arch");
/*  82 */       if (osArch.indexOf("64") != -1) {
/*  83 */         os_bit = 2;
/*     */       }
/*  85 */     } catch (Exception e) {
/*     */       
/*  87 */       os_bit = 1;
/*     */     } 
/*  89 */     OS_BIT = os_bit;
/*  90 */     OS = os;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean is32Bit() {
/*  95 */     return (OS_BIT == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean is64Bit() {
/* 100 */     return (OS_BIT == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getOsName() {
/* 105 */     switch (OS) {
/*     */       
/*     */       case 1:
/* 108 */         return "windows";
/*     */       
/*     */       case 2:
/* 111 */         return "unix";
/*     */       
/*     */       case 3:
/* 114 */         return "posix-unix";
/*     */       
/*     */       case 0:
/* 117 */         return "other";
/*     */     } 
/*     */     
/* 120 */     return "unknown";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\FileSystemUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */