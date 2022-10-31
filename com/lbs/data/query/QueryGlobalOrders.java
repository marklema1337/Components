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
/*    */ public class QueryGlobalOrders
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 24 */   protected ArrayList<QueryGlobalOrder> m_List = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(QueryGlobalOrder order) {
/* 29 */     this.m_List.add(order);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryGlobalOrder get(int index) {
/* 34 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int find(String name) {
/* 40 */     for (int i = 0; i < this.m_List.size(); i++) {
/*    */       
/* 42 */       QueryGlobalOrder glbOrder = this.m_List.get(i);
/*    */       
/* 44 */       if (StringUtil.equals(glbOrder.getName(), name)) {
/* 45 */         return i;
/*    */       }
/*    */     } 
/* 48 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryGlobalOrder get(String name) {
/* 53 */     int idx = find(name);
/*    */     
/* 55 */     if (idx != -1) {
/* 56 */       return this.m_List.get(idx);
/*    */     }
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 63 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 68 */     this.m_List.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryGlobalOrders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */