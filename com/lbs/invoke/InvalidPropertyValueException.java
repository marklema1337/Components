/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidPropertyValueException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private static final String EXCEPTION_DEF_MSG = "Invalid property value!";
/*    */   
/*    */   public InvalidPropertyValueException() {
/* 18 */     this("Invalid property value!");
/*    */   }
/*    */ 
/*    */   
/*    */   public InvalidPropertyValueException(String s) {
/* 23 */     super(-1003, 73, s);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\InvalidPropertyValueException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */