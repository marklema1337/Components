/*    */ package com.lbs.controls.pivottable;
/*    */ 
/*    */ public class PivotCellData
/*    */   implements Comparable<PivotCellData> {
/*    */   private PivotLocation location;
/*    */   private PivotCellValue[] value;
/*    */   
/*    */   public PivotCellData(PivotLocation location, PivotCellValue[] value) {
/*  9 */     this.location = location;
/* 10 */     this.value = value;
/*    */   }
/*    */   
/*    */   public PivotLocation getLocation() {
/* 14 */     return this.location;
/*    */   }
/*    */   
/*    */   public void setLocation(PivotLocation location) {
/* 18 */     this.location = location;
/*    */   }
/*    */   
/*    */   public PivotCellValue[] getValue() {
/* 22 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(PivotCellValue[] value) {
/* 26 */     this.value = value;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 30 */     if (o == null) return false; 
/* 31 */     if (!(o instanceof PivotCellData)) return false;
/*    */     
/* 33 */     PivotCellData pivotCellData = (PivotCellData)o;
/*    */     
/* 35 */     return (this.location.compareTo(pivotCellData.getLocation()) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 40 */     return super.hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     StringBuilder buffer = new StringBuilder("[");
/*    */     
/* 46 */     int index = 0;
/*    */     do {
/* 48 */       if (index >= this.value.length) {
/*    */         continue;
/*    */       }
/*    */       
/* 52 */       Object object = this.value[index].getValue();
/*    */       
/* 54 */       if (object != null) {
/* 55 */         buffer.append(object.toString());
/*    */       } else {
/* 57 */         buffer.append("null");
/*    */       } 
/*    */       
/* 60 */       if (index < this.value.length - 1) {
/* 61 */         buffer.append(", ");
/*    */       }
/*    */       
/* 64 */       index++;
/* 65 */     } while (index < this.value.length);
/*    */     
/* 67 */     buffer.append("]");
/*    */     
/* 69 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public int compareTo(PivotCellData o) {
/* 73 */     if (o == null) return -1;
/*    */     
/* 75 */     return this.location.compareTo(o.getLocation());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotCellData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */