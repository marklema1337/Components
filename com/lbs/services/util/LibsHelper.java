/*    */ package com.lbs.services.util;
/*    */ 
/*    */ import com.lbs.remoteclient.IClientContext;
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
/*    */ public class LibsHelper
/*    */ {
/*    */   public static final String JAR_JAKARTA = "jakarta-regexp.jar";
/*    */   
/*    */   public static void loadJar(IClientContext context, String jarName) throws Exception {
/* 20 */     if (context != null) {
/*    */       
/* 22 */       context.loadJAR(jarName, false, true);
/*    */       
/*    */       return;
/*    */     } 
/* 26 */     throw new IllegalArgumentException();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void loadJakartaRegexp(IClientContext context) throws Exception {
/* 31 */     loadJar(context, "jakarta-regexp.jar");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\service\\util\LibsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */