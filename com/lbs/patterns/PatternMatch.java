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
/*    */ public class PatternMatch
/*    */ {
/*    */   protected String[] m_Parts;
/*    */   protected String m_Match;
/*    */   protected String m_Operation;
/*    */   
/*    */   public PatternMatch(String[] parts, String match) {
/* 19 */     this.m_Parts = parts;
/* 20 */     this.m_Match = match;
/* 21 */     this.m_Operation = parts[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "[match='" + this.m_Match + "', op='" + this.m_Operation + "']";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMatch() {
/* 31 */     return this.m_Match;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOperation() {
/* 36 */     return this.m_Operation;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getParts() {
/* 41 */     return this.m_Parts;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternMatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */