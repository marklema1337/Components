/*    */ package com.lbs.controls.pivottable;
/*    */ 
/*    */ public class PivotLocation
/*    */   implements Comparable<PivotLocation>
/*    */ {
/*    */   public int row;
/*    */   public int column;
/*    */   
/*    */   public PivotLocation(int row, int column) {
/* 10 */     this.row = row;
/* 11 */     this.column = column;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 15 */     return "(" + this.row + ", " + this.column + ")";
/*    */   }
/*    */   
/*    */   public int compareTo(PivotLocation o) {
/* 19 */     if (o == null) return 1;
/*    */     
/* 21 */     if (o.row > this.row) return -1; 
/* 22 */     if (o.row < this.row) return 1;
/*    */     
/* 24 */     if (o.column > this.column) return -1; 
/* 25 */     if (o.column < this.column) return 1;
/*    */     
/* 27 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 34 */     if (!(obj instanceof PivotLocation))
/* 35 */       return false; 
/* 36 */     return (compareTo((PivotLocation)obj) == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 43 */     return super.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */