/*    */ package com.lbs.data.factory;
/*    */ 
/*    */ import java.util.Locale;
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
/*    */ public class DBErrorsUtil
/*    */ {
/*    */   public static boolean isDBErrorSpecial(Exception e) {
/* 18 */     if (e instanceof ObjectFactoryException) {
/*    */       
/* 20 */       String message = e.getMessage();
/* 21 */       if (message != null) {
/*    */         
/* 23 */         message = message.toLowerCase(Locale.ENGLISH);
/* 24 */         if (message.indexOf("table definition has changed") > -1)
/* 25 */           return true; 
/*    */       } 
/*    */     } 
/* 28 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\DBErrorsUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */