/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
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
/*    */ public class QueryResultSet
/*    */   extends ArrayList<Object>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String[] m_ColumnNames;
/*    */   private boolean m_CanAddColumnHeader = false;
/*    */   
/*    */   public String[] getColumnNames() {
/* 25 */     return this.m_ColumnNames;
/*    */   }
/*    */   
/*    */   public void setColumnNames(String[] columnNames) {
/* 29 */     this.m_ColumnNames = columnNames;
/*    */   }
/*    */   
/*    */   public int getColumnCount() {
/* 33 */     return (this.m_ColumnNames != null) ? this.m_ColumnNames.length : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumn(String columnName) {
/* 38 */     if (this.m_ColumnNames == null) {
/* 39 */       return -1;
/*    */     }
/* 41 */     for (int i = 0; i < this.m_ColumnNames.length; i++) {
/*    */       
/* 43 */       if (StringUtil.equalsIgnoreCase(this.m_ColumnNames[i], columnName))
/* 44 */         return i; 
/*    */     } 
/* 46 */     return -1;
/*    */   }
/*    */   
/*    */   public QueryResultSetItem item(int index) {
/* 50 */     if (index >= 0 && index < size())
/* 51 */       return (QueryResultSetItem)get(index); 
/* 52 */     return null;
/*    */   }
/*    */   
/*    */   public void setCanAddColumnHeader(boolean addColumnHeader) {
/* 56 */     this.m_CanAddColumnHeader = addColumnHeader;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canAddColumnHeader() {
/* 61 */     return this.m_CanAddColumnHeader;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryResultSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */