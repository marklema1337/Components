/*    */ package com.lbs.patterns;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
/*    */ import java.util.ArrayList;
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
/*    */ public class PatternSubstitutions
/*    */   extends ArrayList<PatternSubstitution>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public int find(String op) {
/* 22 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 24 */       PatternSubstitution subst = get(i);
/* 25 */       if (StringUtil.equals(subst.m_Operation, op)) {
/* 26 */         return i;
/*    */       }
/*    */     } 
/* 29 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue(String op) {
/* 34 */     int idx = find(op);
/*    */     
/* 36 */     if (idx != -1) {
/* 37 */       return (get(idx)).m_Value;
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternSubstitutions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */