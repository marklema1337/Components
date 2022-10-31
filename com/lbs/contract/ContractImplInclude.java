/*    */ package com.lbs.contract;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Enumeration;
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
/*    */ public class ContractImplInclude
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_TemplateName;
/* 21 */   private Hashtable<String, String> m_VarSubstitutions = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public String getTemplateName() {
/* 25 */     return this.m_TemplateName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTemplateName(String templateName) {
/* 30 */     this.m_TemplateName = templateName;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, String> getVarSubstitutions() {
/* 35 */     return this.m_VarSubstitutions;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVarSubstitutions(Hashtable<String, String> varSubstitutions) {
/* 40 */     this.m_VarSubstitutions = varSubstitutions;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 46 */     ContractImplInclude clone = (ContractImplInclude)super.clone();
/* 47 */     clone.m_VarSubstitutions = new Hashtable<>();
/* 48 */     Enumeration<String> keys = this.m_VarSubstitutions.keys();
/* 49 */     while (keys.hasMoreElements()) {
/*    */       
/* 51 */       String key = keys.nextElement();
/* 52 */       String value = this.m_VarSubstitutions.get(key);
/* 53 */       clone.m_VarSubstitutions.put(key, value);
/*    */     } 
/*    */     
/* 56 */     return clone;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractImplInclude.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */