/*    */ package com.lbs.contract;
/*    */ 
/*    */ import com.lbs.interfaces.IParameter;
/*    */ import java.io.Serializable;
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
/*    */ public class ContractParameter
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private IParameter m_Parameter;
/*    */   private String m_Alias;
/*    */   private boolean m_Optional;
/*    */   
/*    */   public ContractParameter() {}
/*    */   
/*    */   public ContractParameter(IParameter parameter, String alias) {
/* 30 */     this(parameter, alias, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ContractParameter(IParameter parameter, String alias, boolean optional) {
/* 36 */     this.m_Parameter = parameter;
/* 37 */     this.m_Alias = alias;
/* 38 */     this.m_Optional = optional;
/*    */   }
/*    */ 
/*    */   
/*    */   public IParameter getParameter() {
/* 43 */     return this.m_Parameter;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParameter(IParameter parameter) {
/* 48 */     this.m_Parameter = parameter;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAlias() {
/* 53 */     return this.m_Alias;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAlias(String alias) {
/* 58 */     this.m_Alias = alias;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOptional(boolean optional) {
/* 63 */     this.m_Optional = optional;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOptional() {
/* 68 */     return this.m_Optional;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */