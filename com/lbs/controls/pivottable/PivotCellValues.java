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
/*    */ public class PivotCellValues
/*    */   implements Serializable
/*    */ {
/*    */   private PivotCellValue[] m_Array;
/*    */   
/*    */   public PivotCellValues(PivotCellValue[] m_Array) {
/* 19 */     this.m_Array = m_Array;
/*    */   }
/*    */ 
/*    */   
/*    */   public PivotCellValue[] getArray() {
/* 24 */     return this.m_Array;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setArray(PivotCellValue[] array) {
/* 29 */     this.m_Array = array;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotCellValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */