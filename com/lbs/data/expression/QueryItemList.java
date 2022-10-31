/*    */ package com.lbs.data.expression;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryItemList
/*    */   extends QueryItemListBase
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryItemList(QueryItemBase parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(QueryItem item) {
/* 24 */     add(item, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(QueryItem item, boolean copy) {
/* 29 */     if (item == null) {
/*    */       return;
/*    */     }
/* 32 */     if (copy) {
/* 33 */       item = (QueryItem)item.clone();
/*    */     }
/* 35 */     item.m_Parent = this;
/* 36 */     super.add(item);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(int index, QueryItem item) {
/* 41 */     item.m_Parent = this;
/* 42 */     super.set(index, item);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 50 */     QueryItemList cloneList = new QueryItemList(null);
/* 51 */     copy(cloneList, null);
/* 52 */     return cloneList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\QueryItemList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */