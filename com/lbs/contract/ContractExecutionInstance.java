/*    */ package com.lbs.contract;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Hashtable;
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
/*    */ 
/*    */ public class ContractExecutionInstance
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ContractInstance m_ContractInstance;
/*    */   private ContractParameter[] m_Inputs;
/*    */   private Hashtable<String, String> m_ImplProps;
/*    */   
/*    */   public ContractExecutionInstance() {}
/*    */   
/*    */   public ContractExecutionInstance(ContractInstance contractInstance, Hashtable<String, String> implProps, ContractParameter... inputs) {
/* 31 */     this.m_ContractInstance = contractInstance;
/* 32 */     this.m_Inputs = inputs;
/* 33 */     this.m_ImplProps = implProps;
/*    */   }
/*    */ 
/*    */   
/*    */   public ContractInstance getContractInstance() {
/* 38 */     return this.m_ContractInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContractInstance(ContractInstance contractInstance) {
/* 43 */     this.m_ContractInstance = contractInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public ContractParameter[] getInputs() {
/* 48 */     return this.m_Inputs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInputs(ContractParameter[] inputs) {
/* 53 */     this.m_Inputs = inputs;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, String> getImplProps() {
/* 58 */     return this.m_ImplProps;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setImplProps(Hashtable<String, String> implProps) {
/* 63 */     this.m_ImplProps = implProps;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractExecutionInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */