/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryTerms
/*    */   extends ArrayList<QueryTerm>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryTerm item(int index) {
/* 20 */     return get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryTerm find(String name) {
/* 26 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 28 */       QueryTerm term = get(i);
/*    */       
/* 30 */       if (term.getName().equals(name)) {
/* 31 */         return term;
/*    */       }
/*    */     } 
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTerms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */