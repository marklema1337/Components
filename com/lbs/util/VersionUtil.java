/*    */ package com.lbs.util;
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
/*    */ public class VersionUtil
/*    */ {
/*    */   private static String ms_ImplementationVendor;
/*    */   private static String ms_ImplementationTitle;
/*    */   private static String ms_ImplementationVersion;
/*    */   
/*    */   static {
/* 20 */     if (VersionUtil.class.getPackage() != null && VersionUtil.class.getPackage().getName() != null && 
/* 21 */       Package.getPackage(VersionUtil.class.getPackage().getName()) != null) {
/*    */       
/* 23 */       ms_ImplementationVendor = Package.getPackage(VersionUtil.class.getPackage().getName()).getImplementationVendor();
/* 24 */       ms_ImplementationTitle = Package.getPackage(VersionUtil.class.getPackage().getName()).getImplementationTitle();
/* 25 */       ms_ImplementationVersion = Package.getPackage(VersionUtil.class.getPackage().getName()).getImplementationVersion();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getImplementationVendor() {
/* 34 */     return ms_ImplementationVendor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getImplementationTitle() {
/* 42 */     return ms_ImplementationTitle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getImplementationVersion() {
/* 50 */     return ms_ImplementationVersion;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 55 */     System.out.println(getImplementationVersion());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\VersionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */