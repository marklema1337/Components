/*    */ package com.lbs.appobjects;
/*    */ 
/*    */ import com.lbs.util.JLbsFileUtil;
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
/*    */ public class JLbsWebUtil
/*    */ {
/*    */   private static String ms_AppPath;
/* 19 */   public static final String RESOURCEPATHRELATIVE = "resources" + File.separator;
/*    */ 
/*    */   
/*    */   public static void setAppPath(String appPath) {
/* 23 */     ms_AppPath = appPath;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getAppPath() {
/* 28 */     return ms_AppPath;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getResourcePath() {
/* 33 */     return JLbsFileUtil.appendPath(getAppPath(), RESOURCEPATHRELATIVE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\JLbsWebUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */