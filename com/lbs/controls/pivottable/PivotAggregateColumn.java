/*    */ package com.lbs.controls.pivottable;
/*    */ 
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
/*    */ public class PivotAggregateColumn
/*    */   implements Serializable
/*    */ {
/*    */   private Integer columnIndex;
/*    */   private Integer aggregateFunctionIndex;
/*    */   
/*    */   public PivotAggregateColumn() {}
/*    */   
/*    */   public PivotAggregateColumn(Integer columnIndex, Integer aggregateFunctionIndex) {
/* 24 */     this.columnIndex = columnIndex;
/* 25 */     this.aggregateFunctionIndex = aggregateFunctionIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getColumnIndex() {
/* 30 */     return this.columnIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColumnIndex(Integer columnIndex) {
/* 35 */     this.columnIndex = columnIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getAggregateFunctionIndex() {
/* 40 */     return this.aggregateFunctionIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAggregateFunctionIndex(Integer aggregateFunctionIndex) {
/* 45 */     this.aggregateFunctionIndex = aggregateFunctionIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotAggregateColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */