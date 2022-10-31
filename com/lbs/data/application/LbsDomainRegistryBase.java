/*    */ package com.lbs.data.application;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
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
/*    */ public class LbsDomainRegistryBase
/*    */ {
/* 17 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(LbsDomainRegistryBase.class);
/*    */ 
/*    */   
/*    */   public static void setDomainId(int domainId) {
/* 21 */     ThreadLocalVariables.putThreadLocalValue("domain", Integer.valueOf(domainId));
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getDomainId() {
/* 26 */     Object value = ThreadLocalVariables.getThreadLocalValue("domain");
/* 27 */     if (value != null)
/* 28 */       return getIntValue(value); 
/* 29 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getIntValue(Object domainVal) {
/* 34 */     if (domainVal instanceof String) {
/*    */       
/*    */       try {
/*    */         
/* 38 */         return Integer.parseInt((String)domainVal);
/*    */       }
/* 40 */       catch (Exception e) {
/*    */         
/* 42 */         ms_Logger.error(e.getMessage(), e);
/*    */       } 
/*    */     }
/* 45 */     if (domainVal instanceof Number)
/*    */     {
/* 47 */       return ((Number)domainVal).intValue();
/*    */     }
/* 49 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\application\LbsDomainRegistryBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */