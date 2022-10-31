/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.util.JLbsStringUtil;
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
/*    */ public class QueryResultSetItem
/*    */   extends ArrayList<Object>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private QueryResultSet m_Owner;
/*    */   
/*    */   public QueryResultSetItem(QueryResultSet owner) {
/* 24 */     this.m_Owner = owner;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getColumnValue(int column) {
/* 29 */     if (column >= 0 && column < size())
/* 30 */       return get(column); 
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getColumnValue(String columnName) {
/* 36 */     if (this.m_Owner == null || JLbsStringUtil.isEmpty(columnName))
/* 37 */       return null; 
/* 38 */     int colIndex = this.m_Owner.getColumn(columnName);
/* 39 */     return getColumnValue(colIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryResultSet getOwner() {
/* 44 */     return this.m_Owner;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryResultSetItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */