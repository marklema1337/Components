/*    */ package com.lbs.contract.applet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContractExceptionEvent
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Throwable m_Exception;
/*    */   private boolean m_Consumed = false;
/*    */   
/*    */   public ContractExceptionEvent(Throwable exception) {
/* 15 */     this.m_Exception = exception;
/*    */   }
/*    */ 
/*    */   
/*    */   public Throwable getException() {
/* 20 */     return this.m_Exception;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setException(Throwable exception) {
/* 25 */     this.m_Exception = exception;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsumed() {
/* 30 */     return this.m_Consumed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConsumed(boolean consumed) {
/* 35 */     this.m_Consumed = consumed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void consume() {
/* 40 */     setConsumed(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\ContractExceptionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */