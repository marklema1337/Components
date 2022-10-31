/*    */ package com.lbs.data.factory;
/*    */ 
/*    */ import com.lbs.localization.LbsMessage;
/*    */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*    */ 
/*    */ 
/*    */ public class ObjectValidationException
/*    */   extends ObjectFactoryException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected ILbsValidationResult m_Result;
/*    */   
/*    */   public ObjectValidationException(ILbsValidationResult result) {
/* 14 */     super(-1003, 61, "Object could not be validated!");
/* 15 */     this.m_Result = result;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectValidationException(Throwable cause) {
/* 20 */     super(cause, -1003, 62, new LbsMessage("Exception occurred during object validation:~1", new String[] { cause
/* 21 */             .getLocalizedMessage() }));
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsValidationResult getValidationResult() {
/* 26 */     return this.m_Result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ObjectValidationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */