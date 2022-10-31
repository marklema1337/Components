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
/*    */ public class QueryOrders
/*    */   extends ArrayList<QueryOrder>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryOrder item(int index) {
/* 20 */     if (index < 0 || index >= size()) {
/* 21 */       return null;
/*    */     }
/* 23 */     return get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryOrder find(String name) {
/* 29 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 31 */       QueryOrder qryOrder = item(i);
/*    */       
/* 33 */       if (qryOrder.getName().equals(name)) {
/* 34 */         return qryOrder;
/*    */       }
/*    */     } 
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryOrders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */