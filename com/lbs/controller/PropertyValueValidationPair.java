/*    */ package com.lbs.controller;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyValueValidationPair
/*    */ {
/*    */   private Object m_Value;
/*    */   private OperationResult m_Result;
/*    */   
/*    */   public PropertyValueValidationPair(Object value, OperationResult result) {
/* 12 */     this.m_Value = value;
/* 13 */     this.m_Result = result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 18 */     return this.m_Value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(Object value) {
/* 23 */     this.m_Value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public OperationResult getResult() {
/* 28 */     return this.m_Result;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResult(OperationResult result) {
/* 33 */     this.m_Result = result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyValueValidationPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */