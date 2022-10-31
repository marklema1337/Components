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
/*    */ public class QueryNavigationOrder
/*    */   extends QueryOrder
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private boolean m_Descending;
/*    */   
/*    */   public boolean isDescending() {
/* 20 */     return this.m_Descending;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDescending(boolean descending) {
/* 25 */     this.m_Descending = descending;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryNavigationOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */