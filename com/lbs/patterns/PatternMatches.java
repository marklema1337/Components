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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PatternMatches
/*    */ {
/* 21 */   protected ArrayList m_List = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/*    */   public int size() {
/* 26 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public PatternMatch get(int index) {
/* 31 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int find(String op) {
/* 37 */     for (int i = 0; i < this.m_List.size(); i++) {
/*    */       
/* 39 */       PatternMatch match = this.m_List.get(i);
/*    */       
/* 41 */       if (StringUtil.equals(match.m_Operation, op)) {
/* 42 */         return i;
/*    */       }
/*    */     } 
/* 45 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean exists(PatternMatch match) {
/* 50 */     return (find(match.m_Operation) != -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(PatternMatch match) {
/* 55 */     this.m_List.add(match);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return this.m_List.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternMatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */