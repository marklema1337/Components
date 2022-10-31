/*    */ package com.lbs.util;
/*    */ 
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ import com.lbs.localization.LbsMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConversionNotSupportedException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public ConversionNotSupportedException() {
/* 20 */     super(-1003, 55, "Type conversion is not supported!");
/*    */   }
/*    */ 
/*    */   
/*    */   public ConversionNotSupportedException(String message) {
/* 25 */     super(-1003, 55, new LbsMessage("Type conversion is not supported: ~1", new String[] { message }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ConversionNotSupportedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */