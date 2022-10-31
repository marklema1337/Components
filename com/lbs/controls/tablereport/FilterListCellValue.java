/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FilterListCellValue implements Cloneable, Serializable, Comparable<FilterListCellValue> {
/*    */   private Object value;
/*    */   private boolean included = true;
/*    */   
/*    */   public FilterListCellValue(Object value) {
/* 10 */     this(value, true);
/*    */   }
/*    */   
/*    */   public FilterListCellValue(Object value, boolean included) {
/* 14 */     this.value = value;
/* 15 */     this.included = included;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 19 */     if (o == null || !(o instanceof FilterListCellValue)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     FilterListCellValue flcv = (FilterListCellValue)o;
/* 24 */     return flcv.getValue().equals(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 29 */     return super.hashCode();
/*    */   }
/*    */   
/*    */   public Object clone() {
/* 33 */     FilterListCellValue clone = new FilterListCellValue(this.value, this.included);
/* 34 */     return clone;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 38 */     return (this.value == null) ? "" : this.value.toString();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */   
/*    */   public boolean isIncluded() {
/* 46 */     return this.included;
/*    */   }
/*    */   
/*    */   public void setIncluded(boolean included) {
/* 50 */     this.included = included;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(FilterListCellValue o) {
/* 55 */     if (o == null) {
/* 56 */       return -1;
/*    */     }
/*    */     
/* 59 */     Comparable<Comparable> c1 = (Comparable)this.value;
/* 60 */     Comparable c2 = (Comparable)o.getValue();
/*    */     
/* 62 */     return c1.compareTo(c2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\FilterListCellValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */