/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.thoughtworks.xstream.annotations.XStreamAlias;
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
/*    */ public class QueryOrderColumn
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @XStreamAlias("column")
/*    */   protected QueryColumn m_Column;
/*    */   @XStreamAlias("ascending")
/*    */   protected boolean m_Ascending;
/*    */   
/*    */   public QueryOrderColumn(QueryColumn column, boolean ascending) {
/* 27 */     this.m_Column = column;
/* 28 */     this.m_Ascending = ascending;
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryOrderColumn(QueryColumn column) {
/* 33 */     this(column, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAscending() {
/* 38 */     return this.m_Ascending;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAscending(boolean ascending) {
/* 43 */     this.m_Ascending = ascending;
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryColumn getColumn() {
/* 48 */     return this.m_Column;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryOrderColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */