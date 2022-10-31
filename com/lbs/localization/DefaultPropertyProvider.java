/*    */ package com.lbs.localization;
/*    */ 
/*    */ import com.lbs.invoke.RttiUtil;
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
/*    */ 
/*    */ public class DefaultPropertyProvider
/*    */   implements IPropertyProvider
/*    */ {
/*    */   public String getValue(Object source, String propertyName) {
/*    */     try {
/* 21 */       Object value = RttiUtil.getProperty(source, propertyName);
/* 22 */       return String.valueOf(value);
/*    */     }
/* 24 */     catch (Exception e) {
/*    */       
/* 26 */       return "unknown";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\DefaultPropertyProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */