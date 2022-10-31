/*     */ package com.lbs.appobjects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.FileUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GOVersion
/*     */ {
/*     */   private static final String VERSION_E_DEFTER = "Version.EDefter";
/*     */   private static final String VERSION_TRIGGER = "Version.Trigger";
/*     */   private static final String VERSION_BUGFIX = "Version.Bugfix";
/*     */   private static final String VERSION_RELEASE = "Version.Release";
/*     */   private static final String VERSION_MINOR = "Version.Minor";
/*     */   private static final String VERSION_MAJOR = "Version.Major";
/*     */   private static final String SYSTEM_VERSION = "System.Version";
/*     */   private static final String APPLICATION_NAME2 = "Application.Name";
/*     */   public static final String MIN_OS = "Min.Os";
/*     */   public static final String MIN_OSVERSION = "Min.OsVersion";
/*     */   public static final String MIN_OSARCH = "Min.OsArchitecture";
/*     */   public static final String MIN_HEAP = "Min.HeapSize";
/*     */   public static final String MIN_JVMVERSION = "Min.JvmVersion";
/*     */   public static final String MIN_JVMARCH = "Min.JvmArchitecture";
/*     */   public static final String MIN_DISKSPACE = "Min.DiskSpace";
/*     */   public static final String MIN_RAM = "Min.Ram";
/*     */   public static final String MIN_WARN = "Min.Warn";
/*     */   public static final String MIN_REDIRECT = "Min.Redirect";
/*     */   public static final String MIN_URL = "Min.Url";
/*     */   public static final String LOGOSIGN_V1 = "LOGO DB Configuration Signature";
/*     */   public static final String LOGOSIGN_V2 = "LOGO DB Configuration Signature v2";
/*     */   public static final String LOGOSIGN = "LOGO DB Configuration Signature v2";
/*  41 */   public static String APPLICATION_NAME = "j-platform";
/*     */   
/*  43 */   public static int SYSTEMVERSION = 3094;
/*     */   
/*     */   public static final int SYSTEMNOTFOUND = -1;
/*     */   public static final int SYSTEMOLDVERSION = -2;
/*     */   
/*     */   public static void load(String appPath, String fileName) {
/*  49 */     fileName = JLbsFileUtil.appendPath(appPath, fileName);
/*     */     
/*  51 */     Properties props = new Properties();
/*     */ 
/*     */     
/*     */     try {
/*  55 */       String propsStr = FileUtil.getFileContents(fileName, false);
/*  56 */       ByteArrayInputStream in = new ByteArrayInputStream(propsStr.getBytes());
/*  57 */       props.load(in);
/*     */       
/*  59 */       JLbsConstants.PRODUCT_VERSION_PROPERTIES = props;
/*     */       
/*  61 */       String str = props.getProperty("Application.Name");
/*  62 */       if (!StringUtil.isEmpty(str)) {
/*  63 */         APPLICATION_NAME = str;
/*     */       }
/*  65 */       int prop = getIntProp(props, "System.Version");
/*  66 */       if (prop != Integer.MIN_VALUE) {
/*  67 */         SYSTEMVERSION = prop;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       prop = getIntProp(props, "Version.Major");
/*  78 */       if (prop != Integer.MIN_VALUE) {
/*  79 */         JLbsConstants.VERSION_MAJOR = prop;
/*     */       }
/*  81 */       prop = getIntProp(props, "Version.Minor");
/*  82 */       if (prop != Integer.MIN_VALUE) {
/*  83 */         JLbsConstants.VERSION_MINOR = prop;
/*     */       }
/*  85 */       prop = getIntProp(props, "Version.Release");
/*  86 */       if (prop != Integer.MIN_VALUE) {
/*  87 */         JLbsConstants.VERSION_RELEASE = prop;
/*     */       }
/*  89 */       prop = getIntProp(props, "Version.Bugfix");
/*  90 */       if (prop != Integer.MIN_VALUE) {
/*  91 */         JLbsConstants.VERSION_BUGFIX = prop;
/*     */       }
/*  93 */       prop = getIntProp(props, "Version.Trigger");
/*  94 */       if (prop != Integer.MIN_VALUE) {
/*  95 */         JLbsConstants.VERSION_TRIGGER = prop;
/*     */       }
/*  97 */       String value = props.getProperty("Version.EDefter");
/*  98 */       if (value != null && value.length() > 0) {
/*  99 */         JLbsConstants.VERSION_EDEFTER = value;
/*     */       }
/* 101 */       computeVersionString();
/*     */     
/*     */     }
/* 104 */     catch (FileNotFoundException fileNotFoundException) {
/*     */ 
/*     */     
/*     */     }
/* 108 */     catch (Exception e) {
/*     */       
/* 110 */       LbsConsole.getLogger("LbsApplication.Client.GOVersion").error(null, e);
/* 111 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getIntProp(Properties props, String propName) {
/* 117 */     String prop = props.getProperty(propName);
/* 118 */     if (!StringUtil.isEmpty(prop)) {
/*     */       
/*     */       try {
/*     */         
/* 122 */         return Integer.parseInt(prop);
/*     */       }
/* 124 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     return Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getBooleanProp(Properties props, String propName) {
/* 135 */     String prop = props.getProperty(propName);
/* 136 */     if (prop != null && prop.length() > 0) {
/*     */       
/*     */       try {
/*     */         
/* 140 */         return Boolean.parseBoolean(prop);
/*     */       }
/* 142 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void computeVersionString() {
/* 153 */     JLbsConstants.VERSION_STR = "v" + JLbsConstants.VERSION_MAJOR + "." + JLbsConstants.VERSION_MINOR + "." + 
/* 154 */       JLbsConstants.VERSION_RELEASE + "." + JLbsConstants.VERSION_BUGFIX;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 159 */     load("D:\\Projects\\ArslanA_Unity_int\\UnityVOB\\InitialC\\logoERP\\WebContent\\Config", "ProductVersion.txt");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\GOVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */