/*    */ package com.lbs.data.export.params;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class IntegratorBOParameter
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 20 */   private List m_ParameterList = null;
/*    */ 
/*    */   
/*    */   public IntegratorBOParameter() {
/* 24 */     this.m_ParameterList = new ArrayList();
/*    */   }
/*    */ 
/*    */   
/*    */   public List getParameterList() {
/* 29 */     return this.m_ParameterList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearParameterList() {
/* 34 */     this.m_ParameterList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParameterList(List parameterList) {
/* 39 */     if (parameterList == null) {
/* 40 */       this.m_ParameterList.clear();
/*    */     } else {
/* 42 */       this.m_ParameterList = parameterList;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addParameter(String parameter) {
/* 47 */     if (this.m_ParameterList.contains(parameter)) {
/*    */       return;
/*    */     }
/* 50 */     this.m_ParameterList.add(parameter);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeParameter(String parameter) {
/* 55 */     if (!this.m_ParameterList.contains(parameter)) {
/*    */       return;
/*    */     }
/* 58 */     this.m_ParameterList.remove(parameter);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IntegratorBOParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */