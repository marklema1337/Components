/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryResults
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 24 */   private ArrayList m_List = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/*    */   public int size() {
/* 29 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryResult get(int index) {
/* 34 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryResult get(String qryName) {
/* 39 */     int idx = find(qryName);
/*    */     
/* 41 */     if (idx != -1) {
/* 42 */       return this.m_List.get(idx);
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(QueryResult qryResult) {
/* 49 */     this.m_List.add(qryResult);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int find(String qryName) {
/* 55 */     for (int i = 0; i < this.m_List.size(); i++) {
/*    */       
/* 57 */       QueryResult res = this.m_List.get(i);
/*    */       
/* 59 */       if (StringUtil.equals(res.getQueryName(), qryName)) {
/* 60 */         return i;
/*    */       }
/*    */     } 
/* 63 */     return -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */