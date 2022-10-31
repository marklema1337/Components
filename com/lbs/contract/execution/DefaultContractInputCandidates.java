/*    */ package com.lbs.contract.execution;
/*    */ 
/*    */ import com.lbs.contract.ContractParameter;
/*    */ import com.lbs.interfaces.IParameter;
/*    */ import com.lbs.util.JLbsListedHashtable;
/*    */ import java.util.ArrayList;
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
/*    */ public class DefaultContractInputCandidates
/*    */   implements ContractInputCandidates
/*    */ {
/* 21 */   private Hashtable<String, ArrayList<IParameter>> m_Candidates = new Hashtable<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultContractInputCandidates() {}
/*    */ 
/*    */   
/*    */   public DefaultContractInputCandidates(ContractParameter... inputs) {
/* 29 */     if (inputs != null) {
/*    */       byte b; int i; ContractParameter[] arrayOfContractParameter;
/* 31 */       for (i = (arrayOfContractParameter = inputs).length, b = 0; b < i; ) { ContractParameter input = arrayOfContractParameter[b];
/*    */         
/* 33 */         JLbsListedHashtable.put(this.m_Candidates, input.getAlias(), input.getParameter());
/*    */         b++; }
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public DefaultContractInputCandidates(String newAlias, ContractParameter... outputs) {
/* 40 */     if (outputs != null) {
/*    */       byte b; int i; ContractParameter[] arrayOfContractParameter;
/* 42 */       for (i = (arrayOfContractParameter = outputs).length, b = 0; b < i; ) { ContractParameter output = arrayOfContractParameter[b];
/*    */         
/* 44 */         JLbsListedHashtable.put(this.m_Candidates, newAlias, output.getParameter());
/*    */         b++; }
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<IParameter> getCandidatesForAlias(String alias) {
/* 52 */     return this.m_Candidates.get(alias);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\execution\DefaultContractInputCandidates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */