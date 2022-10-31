/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.database.DBField;
/*    */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*    */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryOrderColumns
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @XStreamAlias("list")
/*    */   @XStreamImplicit(itemFieldName = "QueryOrderColumn")
/* 29 */   private ArrayList<QueryOrderColumn> m_List = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public int size() {
/* 34 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryOrderColumn get(int index) {
/* 39 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(QueryOrderColumn column) {
/* 44 */     this.m_List.add(column);
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(int index) {
/* 49 */     this.m_List.remove(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryOrderColumn find(DBField field) {
/* 54 */     return find(field.getTableName(), field.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryOrderColumn find(String source, String name) {
/* 61 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 63 */       QueryOrderColumn col = this.m_List.get(i);
/* 64 */       DBField field = col.getColumn().getField();
/*    */       
/* 66 */       if (field != null)
/*    */       {
/*    */         
/* 69 */         if ((source == null || field.getTableName().equals(source)) && field.getName().equals(name))
/* 70 */           return col;  } 
/*    */     } 
/* 72 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryOrderColumns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */