/*    */ package com.lbs.controls.pivottable;
/*    */ 
/*    */ import com.lbs.controls.tablereport.ViewPreferences;
/*    */ import java.io.Serializable;
/*    */ import java.util.Vector;
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
/*    */ public class PivotViewPreferences
/*    */   extends ViewPreferences
/*    */   implements Serializable
/*    */ {
/*    */   private Vector<Integer> rowIndexes;
/*    */   private Vector<Integer> columnIndexes;
/*    */   private Vector<PivotAggregateColumn> aggregateColumnsVector;
/*    */   
/*    */   public Vector<Integer> getRowIndexes() {
/* 25 */     return this.rowIndexes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRowIndexes(Vector<Integer> rowIndexes) {
/* 30 */     this.rowIndexes = rowIndexes;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vector<Integer> getColumnIndexes() {
/* 35 */     return this.columnIndexes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColumnIndexes(Vector<Integer> columnIndexes) {
/* 40 */     this.columnIndexes = columnIndexes;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vector<PivotAggregateColumn> getAggregateColumnsVector() {
/* 45 */     return this.aggregateColumnsVector;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAggregateColumnsVector(Vector<PivotAggregateColumn> aggregateColumnsVector) {
/* 50 */     this.aggregateColumnsVector = aggregateColumnsVector;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotViewPreferences.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */