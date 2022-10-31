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
/*    */ 
/*    */ public class QueryOrderParams
/*    */   extends GridQueryOrder
/*    */ {
/*    */   protected boolean m_BuildGlobalOrder = false;
/*    */   
/*    */   public QueryOrderParams() {
/* 19 */     this("");
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryOrderParams(String orderDef) {
/* 24 */     super(orderDef);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildGlobalOrderFromSubOrders(boolean buildGlobalOrder) {
/* 29 */     this.m_BuildGlobalOrder = buildGlobalOrder;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBuildGlobalOrderFromSubOrders() {
/* 34 */     return this.m_BuildGlobalOrder;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGlobalOrderName(String globalOrderName) {
/* 39 */     this.m_GlobalOrderName = globalOrderName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTableOrder(QuerySubOrderParams subOrder) {
/* 44 */     int idx = 0;
/* 45 */     if (this.m_Orders == null || this.m_Orders.length == 0) {
/* 46 */       this.m_Orders = new GridQuerySubOrder[1];
/*    */     } else {
/*    */       
/* 49 */       GridQuerySubOrder[] orders = new GridQuerySubOrder[this.m_Orders.length + 1];
/* 50 */       System.arraycopy(this.m_Orders, 0, orders, 0, this.m_Orders.length);
/* 51 */       idx = this.m_Orders.length;
/* 52 */       this.m_Orders = orders;
/*    */     } 
/* 54 */     this.m_Orders[idx] = subOrder;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTableOrder(String tableAlias, String orderName) {
/* 59 */     addTableOrder(new QuerySubOrderParams(tableAlias, orderName));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryOrderParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */