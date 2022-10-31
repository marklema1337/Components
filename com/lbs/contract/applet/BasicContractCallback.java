/*    */ package com.lbs.contract.applet;
/*    */ 
/*    */ import com.lbs.contract.ContractParameter;
/*    */ import com.lbs.remoteclient.IClientContext;
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
/*    */ public class BasicContractCallback
/*    */   implements IContractCallback
/*    */ {
/*    */   private boolean m_ContractComplete;
/*    */   private ContractParameter[] m_Outputs;
/*    */   private Throwable m_Exception;
/*    */   
/*    */   public synchronized void onComplete(IClientContext context, ContractParameter... outputs) {
/* 23 */     this.m_Outputs = outputs;
/* 24 */     this.m_ContractComplete = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void onException(IClientContext context, ContractExceptionEvent event) {
/* 29 */     this.m_Exception = event.getException();
/* 30 */     this.m_ContractComplete = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized boolean isContractComplete() {
/* 35 */     return this.m_ContractComplete;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable getException() {
/* 40 */     return this.m_Exception;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized ContractParameter[] getOutputs() {
/* 45 */     return this.m_Outputs;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\BasicContractCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */