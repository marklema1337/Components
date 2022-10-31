/*    */ package com.lbs.data.factory;
/*    */ 
/*    */ import com.lbs.data.objects.SimpleBusinessObject;
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ import com.lbs.localization.LbsMessage;
/*    */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*    */ 
/*    */ 
/*    */ public class ObjectInitializationException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected ILbsValidationResult m_Result;
/*    */   protected SimpleBusinessObject m_Object;
/*    */   
/*    */   public ObjectInitializationException(ILbsValidationResult result, SimpleBusinessObject object) {
/* 17 */     super(-1003, 66, "Object could not be initialized!");
/* 18 */     this.m_Result = result;
/* 19 */     this.m_Object = object;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectInitializationException(Throwable cause, SimpleBusinessObject object) {
/* 24 */     super(cause, -1003, 67, new LbsMessage("Exception occurred during object initialization:~1", new String[] { cause
/* 25 */             .getLocalizedMessage() }));
/* 26 */     this.m_Object = object;
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsValidationResult getValidationResult() {
/* 31 */     return this.m_Result;
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleBusinessObject getObject() {
/* 36 */     return this.m_Object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ObjectInitializationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */