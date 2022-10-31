/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class QueryUnion
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_QueryName;
/*    */   private boolean m_UnionAll;
/*    */   private boolean m_Enabled = true;
/* 13 */   private QuerySchema m_QuerySchema = null;
/*    */ 
/*    */   
/*    */   public QueryUnion(QueryUnion union) {
/* 17 */     this.m_Enabled = union.m_Enabled;
/* 18 */     this.m_QueryName = union.m_QueryName;
/* 19 */     this.m_QuerySchema = union.m_QuerySchema;
/* 20 */     this.m_UnionAll = union.m_UnionAll;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryUnion() {}
/*    */ 
/*    */   
/*    */   public String getQueryName() {
/* 29 */     return this.m_QueryName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setQueryName(String queryName) {
/* 34 */     this.m_QueryName = queryName;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUnionAll() {
/* 39 */     return this.m_UnionAll;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUnionAll(boolean unionAll) {
/* 44 */     this.m_UnionAll = unionAll;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEnabled() {
/* 49 */     return this.m_Enabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 54 */     this.m_Enabled = enabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setQuerySchema(QuerySchema querySchema) {
/* 59 */     this.m_QuerySchema = querySchema;
/*    */   }
/*    */ 
/*    */   
/*    */   public QuerySchema getQuerySchema() {
/* 64 */     return this.m_QuerySchema;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryUnion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */