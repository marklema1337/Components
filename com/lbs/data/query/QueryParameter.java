/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.database.DBField;
/*    */ import com.lbs.data.expression.QueryItem;
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
/*    */ public class QueryParameter
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_Name;
/*    */   protected QueryColumn m_Column;
/*    */   protected DBField m_Field;
/* 24 */   protected Object m_DefaultValue = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getDefaultValue() {
/* 31 */     return this.m_DefaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColumn(QueryColumn column) {
/* 47 */     this.m_Column = column;
/*    */     
/* 49 */     if (this.m_Column != null) {
/* 50 */       this.m_Field = this.m_Column.m_Field;
/*    */     } else {
/* 52 */       this.m_Field = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDefaultValue(Object object) {
/* 60 */     this.m_DefaultValue = object;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String string) {
/* 68 */     this.m_Name = string;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DBField getField() {
/* 76 */     if (this.m_Column != null) {
/* 77 */       return this.m_Column.getField();
/*    */     }
/* 79 */     return this.m_Field;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setField(DBField field) {
/* 87 */     this.m_Field = field;
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryColumn getColumn() {
/* 92 */     return this.m_Column;
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryItem getQueryItem() {
/* 97 */     return new QueryItem(90, this.m_Name);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */