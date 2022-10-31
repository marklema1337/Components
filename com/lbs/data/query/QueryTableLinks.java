/*    */ package com.lbs.data.query;
/*    */ 
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
/*    */ public class QueryTableLinks
/*    */   extends ArrayList<QueryTableLink>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryTableLink item(int index) {
/* 19 */     return get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryTableLink find(String alias) {
/* 25 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 27 */       QueryTableLink tableLink = item(i);
/*    */       
/* 29 */       if (tableLink.getAlias().equals(alias)) {
/* 30 */         return tableLink;
/*    */       }
/*    */     } 
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTableLinks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */