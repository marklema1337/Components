/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.localization.LuceneLocalizationServices;
/*    */ import com.lbs.resource.JLbsLocalizer;
/*    */ import com.lbs.util.JLbsFileUtil;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsMessageConstantsGenerator
/*    */ {
/*    */   private static final String PARAM_PATH = "-webtierPath=";
/*    */   private static final String PARAM_TARGET = "-targetFolder=";
/*    */   private static final String PARAM_MODULE = "-module=";
/*    */   
/*    */   public static void main(String[] args) {
/* 27 */     if (args.length < 2) {
/* 28 */       printUsage();
/*    */     }
/* 30 */     String path = null, targetFolder = null, module = null;
/* 31 */     for (int i = 0; i < args.length; i++) {
/*    */       
/* 33 */       if (args[i].startsWith("-webtierPath=")) {
/* 34 */         path = args[i].substring("-webtierPath=".length());
/*    */       }
/* 36 */       if (args[i].startsWith("-targetFolder=")) {
/* 37 */         targetFolder = args[i].substring("-targetFolder=".length());
/*    */       }
/* 39 */       if (args[i].startsWith("-module=")) {
/* 40 */         module = args[i].substring("-module=".length());
/*    */       }
/*    */     } 
/* 43 */     if (JLbsStringUtil.isEmpty(path) || JLbsStringUtil.isEmpty(targetFolder)) {
/* 44 */       printUsage();
/*    */     }
/* 46 */     File folder = new File(path);
/* 47 */     if (!folder.exists())
/* 48 */       error("Folder with the given path '" + path + "' does not exist!"); 
/* 49 */     if (!folder.isDirectory()) {
/* 50 */       error("The given path '" + path + "' is not a directory!");
/*    */     }
/* 52 */     LuceneLocalizationServices localService = new LuceneLocalizationServices("ENUS");
/* 53 */     localService.setLocalizationDBDirectory(JLbsFileUtil.appendPath(path, new String[] { "resources", "Database" }));
/* 54 */     JLbsLocalizer.setLocalizationService((ILocalizationServices)localService);
/*    */     
/* 56 */     if (!JLbsStringUtil.isEmpty(module)) {
/*    */       
/* 58 */       String packageName = "com.lbs.controllers.gen.message";
/* 59 */       String className = "I" + module + "MessageConstants";
/*    */       
/*    */       try {
/* 62 */         (new LbsMessageConstantsClassGenerator()).generateMessageConstantsClass((ILocalizationServices)localService, targetFolder, packageName, 
/* 63 */             className, module);
/*    */       }
/* 65 */       catch (Exception e) {
/*    */         
/* 67 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 70 */     localService.releaseEmbeddedConnection("ENUS", null);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void printUsage() {
/* 75 */     System.err.println("----------- Usage ---------------");
/* 76 */     System.err.println("LbsMessageConstantsGenerator -webtierPath=<Path of the LogoERP project WebContent folder> -targetFolder=<Target Folder to generate classes into> -module=<Module value for message interface generation (optional)>");
/*    */ 
/*    */     
/* 79 */     System.err.println("Both -webtierPath= and -targetFolder= should be specified!");
/* 80 */     System.exit(-1);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void error(String message) {
/* 85 */     System.err.println(message);
/* 86 */     System.exit(-1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\LbsMessageConstantsGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */