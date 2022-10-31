/*    */ package com.lbs.data.query;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryGroup
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 21 */   protected QueryColumns m_Columns = new QueryColumns();
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(QueryColumn column) {
/* 26 */     if (column.isAggregate()) {
/*    */       return;
/*    */     }
/* 29 */     if (this.m_Columns.contains(column)) {
/*    */       return;
/*    */     }
/* 32 */     this.m_Columns.add(column);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(QueryTable table) {
/* 37 */     QueryColumns columns = table.getColumns();
/*    */     
/* 39 */     for (int i = 0; i < columns.size(); i++) {
/*    */       
/* 41 */       QueryColumn column = columns.item(i);
/*    */       
/* 43 */       if (!this.m_Columns.contains(column)) {
/* 44 */         this.m_Columns.add(column);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryColumns getColumns() {
/* 52 */     return this.m_Columns;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */