/*    */ package com.lbs.data.query;
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
/*    */ public class QuerySubOrderParams
/*    */   extends GridQuerySubOrder
/*    */ {
/*    */   public QuerySubOrderParams() {
/* 16 */     this("");
/*    */   }
/*    */ 
/*    */   
/*    */   public QuerySubOrderParams(String order) {
/* 21 */     super(order);
/*    */   }
/*    */ 
/*    */   
/*    */   public QuerySubOrderParams(String tableAlias, String orderName) {
/* 26 */     this();
/* 27 */     this.m_TableAlias = tableAlias;
/* 28 */     this.m_OrderName = orderName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTableAlias(String tableAlias) {
/* 33 */     this.m_TableAlias = tableAlias;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOrderName(String orderName) {
/* 38 */     this.m_OrderName = orderName;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QuerySubOrderParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */