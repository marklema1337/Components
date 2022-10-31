/*    */ package com.lbs.data.query.select;
/*    */ 
/*    */ import com.lbs.data.query.QueryTable;
/*    */ import com.lbs.data.query.QueryTableLink;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelectQueryTableLink
/*    */   extends QueryTableLink
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public boolean hasAggregates() {
/* 21 */     return SelectQueryTable.hasAggregates((QueryTable)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasOnlyNativeAggregate() {
/* 27 */     return SelectQueryTable.hasOnlyNativeAggregate((QueryTable)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\select\SelectQueryTableLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */