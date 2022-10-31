/*    */ package com.lbs.contract;
/*    */ 
/*    */ import com.lbs.contract.execution.ParameterMapping;
/*    */ import com.lbs.data.Identifier;
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
/*    */ public class ContractInstance
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ContractSchema m_Contract;
/*    */   private ContractImplementation m_Implementation;
/*    */   private Hashtable<Identifier, ParameterMapping> m_ParameterMappings;
/*    */   private Hashtable<String, MandatoryMap> m_MandatoryMaps;
/*    */   
/*    */   public ContractSchema getContract() {
/* 29 */     return this.m_Contract;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContract(ContractSchema contract) {
/* 34 */     this.m_Contract = contract;
/*    */   }
/*    */ 
/*    */   
/*    */   public ContractImplementation getImplementation() {
/* 39 */     return this.m_Implementation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setImplementation(ContractImplementation implementation) {
/* 44 */     this.m_Implementation = implementation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<Identifier, ParameterMapping> getParameterMappings() {
/* 49 */     return this.m_ParameterMappings;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParameterMappings(Hashtable<Identifier, ParameterMapping> parameterMappings) {
/* 54 */     this.m_ParameterMappings = parameterMappings;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMandatoryMaps(Hashtable<String, MandatoryMap> mandatoryMaps) {
/* 59 */     this.m_MandatoryMaps = mandatoryMaps;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, MandatoryMap> getMandatoryMaps() {
/* 64 */     return this.m_MandatoryMaps;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */