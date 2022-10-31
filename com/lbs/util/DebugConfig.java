/*     */ package com.lbs.util;
/*     */ 
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class DebugConfig
/*     */ {
/*     */   public static void load(String appPath, String fileName) {
/*     */     try {
/*  22 */       Document document = DOMUtil.loadXmlDocumentValidated(getFileName(appPath, fileName));
/*  23 */       if (document == null) {
/*     */         return;
/*     */       }
/*  26 */       internalLoad(appPath, document);
/*     */     }
/*  28 */     catch (Exception e) {
/*     */       
/*  30 */       System.out.println("Debug.xml not loaded.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void internalLoad(String appPath, Document document) throws DebugConfigException {
/*  36 */     Element root = document.getDocumentElement();
/*  37 */     NodeList nodeList = root.getElementsByTagName("debug-flags");
/*     */     
/*  39 */     if (nodeList != null && nodeList.getLength() > 0) {
/*  40 */       loadDebugFlags((Element)nodeList.item(0));
/*     */     }
/*  42 */     nodeList = root.getElementsByTagName("debug-level");
/*  43 */     if (nodeList != null && nodeList.getLength() > 0) {
/*  44 */       loadDebugLevel((Element)nodeList.item(0));
/*     */     }
/*  46 */     loadClientDebugInfo(root);
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
/*     */   private static void loadClientDebugInfo(Element root) {
/*     */     try {
/*  61 */       Element debugFlags = DOMUtil.getFirstElement(root, "client-debug-flags");
/*  62 */       NodeList nodeList = debugFlags.getElementsByTagName("debug-flag");
/*  63 */       for (int i = 0; i < nodeList.getLength(); i++) {
/*     */         
/*  65 */         Element element = (Element)nodeList.item(i);
/*     */         
/*  67 */         String debugType = element.getAttribute("debug-type");
/*  68 */         String debugValue = element.getAttribute("debug-value");
/*     */         
/*  70 */         if (StringUtil.isEmpty(debugType)) {
/*  71 */           throw new DebugConfigException("The required attribute 'debug-type' is not specified!");
/*     */         }
/*  73 */         boolean value = debugValue.equals("true");
/*     */         
/*  75 */         if (debugType.equals("MASTER")) {
/*  76 */           ClientDebugConfig.DEBUG = value;
/*  77 */         } else if (debugType.equals("DATA_CLIENT")) {
/*  78 */           ClientDebugConfig.DEBUG_DATA_CLIENT = value;
/*     */         } 
/*     */       } 
/*     */       
/*  82 */       Element debugLevel = DOMUtil.getFirstElement(root, "client-debug-level");
/*  83 */       String logLevel = debugLevel.getAttribute("log-level");
/*  84 */       if (!StringUtil.isEmpty(logLevel))
/*     */       {
/*  86 */         ClientDebugConfig.LOGLEVEL = ConvertUtil.strToInt(logLevel);
/*     */       
/*     */       }
/*     */     }
/*  90 */     catch (Throwable e) {
/*     */       
/*  92 */       System.err.println("DebugConfig. Could not retrieve client-debug-info (Debug.xml). Leaving DEBUG OFF");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void loadDebugFlags(Element element) throws DebugConfigException {
/*  98 */     NodeList nodeList = element.getElementsByTagName("debug-flag");
/*     */ 
/*     */ 
/*     */     
/* 102 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/* 104 */       element = (Element)nodeList.item(i);
/*     */       
/* 106 */       String debugType = element.getAttribute("debug-type");
/* 107 */       String debugValue = element.getAttribute("debug-value");
/*     */       
/* 109 */       if (StringUtil.isEmpty(debugType)) {
/* 110 */         throw new DebugConfigException("The required attribute 'debug-type' is not specified!");
/*     */       }
/* 112 */       boolean value = debugValue.equals("true");
/*     */       
/* 114 */       if (debugType.equals("MASTER")) {
/* 115 */         JLbsConstants.DEBUG = value;
/* 116 */       } else if (debugType.equals("DATA_CLIENT")) {
/* 117 */         JLbsConstants.DEBUG_DATA_CLIENT = value;
/* 118 */       } else if (debugType.equals("DATA_SERVER")) {
/* 119 */         JLbsConstants.DEBUG_DATA_SERVER = value;
/* 120 */       } else if (debugType.equals("DATA_SHOW_SQL")) {
/* 121 */         JLbsConstants.DEBUG_DATA_SHOW_SQL = value;
/* 122 */       } else if (debugType.equals("DATA_ON_ERROR_SHOW_SQL")) {
/* 123 */         JLbsConstants.DEBUG_DATA_ON_ERROR_SHOW_SQL = value;
/* 124 */       } else if (debugType.equals("DATA_LOGGING_FACTORIES")) {
/* 125 */         JLbsConstants.DEBUG_DATA_LOGGING_FACTORIES = value;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static void loadDebugLevel(Element element) throws DebugConfigException {
/* 131 */     String logLevel = element.getAttribute("log-level");
/* 132 */     if (!StringUtil.isEmpty(logLevel))
/*     */     {
/* 134 */       JLbsConstants.LOGLEVEL = ConvertUtil.strToInt(logLevel);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileName(String appPath, String path, String name) {
/* 140 */     String res = getFileName(path, name);
/*     */     
/* 142 */     res = getFileName(appPath, res);
/*     */     
/* 144 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileName(String path, String name) {
/* 149 */     if (StringUtil.isEmpty(path)) {
/* 150 */       return name;
/*     */     }
/* 152 */     return JLbsFileUtil.appendPath(path, name);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\DebugConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */