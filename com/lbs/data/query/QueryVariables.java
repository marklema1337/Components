/*    */ package com.lbs.data.query;
/*    */ 
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
/*    */ public class QueryVariables
/*    */   extends ArrayList<QueryVariable>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryVariable item(int index) {
/* 20 */     return get(index);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryVariables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */