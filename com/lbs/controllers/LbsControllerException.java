/*    */ package com.lbs.controllers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsControllerException
/*    */   extends Exception
/*    */   implements ILbsControllerExceptionTypes
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 16 */   private int m_ErrorType = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsControllerException() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsControllerException(String message, Throwable cause) {
/* 26 */     super(message, cause);
/* 27 */     this.m_ErrorType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsControllerException(String message) {
/* 32 */     super(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsControllerException(Throwable cause) {
/* 37 */     super(cause);
/* 38 */     this.m_ErrorType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsControllerException(int errorType, String message) {
/* 43 */     super(message);
/* 44 */     this.m_ErrorType = errorType;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getErrorType() {
/* 49 */     return this.m_ErrorType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controllers\LbsControllerException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */