/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.factory.DBErrors;
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
/*    */ public class QueryResult
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final int STATUS_OK = 0;
/*    */   public static final int STATUS_ERROR = 1;
/*    */   private int m_Status;
/*    */   private int m_Key;
/*    */   private int m_AffectedRows;
/*    */   private String m_QueryName;
/*    */   private DBErrors m_Errors;
/*    */   
/*    */   public QueryResult() {
/* 31 */     this(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryResult(String queryName) {
/* 36 */     this.m_QueryName = queryName;
/*    */     
/* 38 */     this.m_Status = 0;
/* 39 */     this.m_Key = -1;
/* 40 */     this.m_AffectedRows = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAffectedRows() {
/* 45 */     return this.m_AffectedRows;
/*    */   }
/*    */ 
/*    */   
/*    */   public DBErrors getErrors() {
/* 50 */     return this.m_Errors;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getKey() {
/* 55 */     return this.m_Key;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStatus() {
/* 60 */     return this.m_Status;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAffectedRows(int i) {
/* 65 */     this.m_AffectedRows = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setErrors(DBErrors errors) {
/* 70 */     this.m_Errors = errors;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setKey(int i) {
/* 75 */     this.m_Key = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatus(int i) {
/* 80 */     this.m_Status = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getQueryName() {
/* 85 */     return this.m_QueryName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setQueryName(String string) {
/* 90 */     this.m_QueryName = string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */