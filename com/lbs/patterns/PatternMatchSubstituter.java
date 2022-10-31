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
/*    */ 
/*    */ 
/*    */ public class PatternMatchSubstituter
/*    */   implements IPatternMatchProcessor
/*    */ {
/* 17 */   protected PatternSubstitutions m_Substitutions = new PatternSubstitutions();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String process(PatternMatch match) throws PatternProcessorException {
/* 25 */     String op = match.m_Operation;
/* 26 */     Object value = this.m_Substitutions.getValue(op);
/*    */     
/* 28 */     if (value != null) {
/* 29 */       return value.toString();
/*    */     }
/* 31 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PatternSubstitutions getSubstitutions() {
/* 39 */     return this.m_Substitutions;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSubstitutions(PatternSubstitutions substitutions) {
/* 47 */     this.m_Substitutions = substitutions;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternMatchSubstituter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */