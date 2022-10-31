/*    */ package com.lbs.patterns;
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
/*    */ public class PatternSubstitution
/*    */ {
/*    */   protected String m_Operation;
/*    */   protected Object m_Value;
/*    */   
/*    */   public PatternSubstitution(String op, String value) {
/* 18 */     this.m_Operation = op;
/* 19 */     this.m_Value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOperation() {
/* 24 */     return this.m_Operation;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 29 */     return this.m_Value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternSubstitution.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */