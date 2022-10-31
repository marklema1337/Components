/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.database.DBField;
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
/*    */ public class QueryParameters
/*    */   extends ArrayList<QueryParameter>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryParameter item(int index) {
/* 22 */     return get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryParameter find(String name) {
/* 28 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 30 */       QueryParameter qryParam = item(i);
/*    */       
/* 32 */       if (qryParam.getName().equals(name))
/* 33 */         return qryParam; 
/*    */     } 
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryParameter find(DBField field) {
/* 41 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 43 */       QueryParameter qryParam = item(i);
/*    */       
/* 45 */       if (qryParam.m_Field.getName().equals(field.getName()))
/* 46 */         return qryParam; 
/*    */     } 
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryParameter find(QueryColumn column) {
/* 54 */     return find(column.getField());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */