/*    */ package com.lbs.console;
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
/*    */ 
/*    */ 
/*    */ public class SilentException
/*    */   extends Exception
/*    */ {
/*    */   private boolean m_ContinueWF = false;
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public SilentException() {}
/*    */   
/*    */   public SilentException(boolean continueWorkflow) {
/* 25 */     this.m_ContinueWF = continueWorkflow;
/*    */   }
/*    */ 
/*    */   
/*    */   public SilentException(String message) {
/* 30 */     super(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public SilentException(Throwable cause) {
/* 35 */     super(cause);
/*    */   }
/*    */ 
/*    */   
/*    */   public SilentException(String message, Throwable cause) {
/* 40 */     super(message, cause);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isContinueWF() {
/* 45 */     return this.m_ContinueWF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContinueWF(boolean isContinueWF) {
/* 50 */     this.m_ContinueWF = isContinueWF;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\SilentException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */