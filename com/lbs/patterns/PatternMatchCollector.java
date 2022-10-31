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
/*    */ public class PatternMatchCollector
/*    */   implements IPatternMatchProcessor
/*    */ {
/* 17 */   protected PatternMatches m_Matches = new PatternMatches();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String process(PatternMatch match) throws PatternProcessorException {
/* 25 */     if (!this.m_Matches.exists(match)) {
/* 26 */       this.m_Matches.add(match);
/*    */     }
/* 28 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternMatchCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */