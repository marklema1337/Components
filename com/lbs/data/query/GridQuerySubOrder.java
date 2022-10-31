/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
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
/*    */ public class GridQuerySubOrder
/*    */ {
/*    */   protected String m_TableAlias;
/*    */   protected String m_OrderName;
/*    */   
/*    */   public GridQuerySubOrder(String order) {
/* 20 */     parse(order);
/*    */   }
/*    */ 
/*    */   
/*    */   public void parse(String order) {
/* 25 */     order = order.trim();
/*    */     
/* 27 */     int idx = order.indexOf('.');
/*    */     
/* 29 */     if (idx != -1) {
/*    */       
/* 31 */       this.m_TableAlias = order.substring(0, idx);
/* 32 */       order = order.substring(idx + 1);
/*    */     } 
/*    */     
/* 35 */     this.m_OrderName = order;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     if (StringUtil.isEmpty(this.m_TableAlias)) {
/* 41 */       return this.m_OrderName;
/*    */     }
/* 43 */     return this.m_TableAlias + "." + this.m_OrderName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOrderName() {
/* 48 */     return this.m_OrderName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTableAlias() {
/* 53 */     return this.m_TableAlias;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\GridQuerySubOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */