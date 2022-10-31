/*    */ package com.lbs.platform.client;
/*    */ 
/*    */ import com.lbs.remoteclient.IClientContext;
/*    */ import com.lbs.util.JLbsClientFS;
/*    */ import com.lbs.util.JLbsFileUtil;
/*    */ import java.io.IOException;
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
/*    */ public class LibraryHelper
/*    */ {
/*    */   public static final String NATIVE_LIB_DIR = "NativeLibraries";
/*    */   
/*    */   public static void loadNativeLibrary(IClientContext context, String libName) throws IOException {
/* 23 */     String libPath = loadLibrary(context, libName);
/* 24 */     System.load(libPath);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String loadLibrary(IClientContext context, String libName) throws IOException {
/* 29 */     String filePath = JLbsFileUtil.appendPath("NativeLibraries", libName);
/*    */     
/* 31 */     if (!JLbsClientFS.fileExists(filePath)) {
/*    */       
/* 33 */       byte[] libContents = context.getResource(libName, false);
/* 34 */       JLbsClientFS.saveFile(filePath, libContents, true, false, false);
/*    */     } 
/*    */     
/* 37 */     return JLbsClientFS.getFullPath(filePath);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\LibraryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */