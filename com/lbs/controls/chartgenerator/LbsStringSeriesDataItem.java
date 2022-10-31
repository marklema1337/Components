/*    */ package com.lbs.controls.chartgenerator;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LbsStringSeriesDataItem
/*    */   implements Cloneable, Comparable, Serializable {
/*    */   private String x;
/*    */   private Number y;
/*    */   
/*    */   public LbsStringSeriesDataItem(String x, Number y) {
/* 11 */     if (x == null) {
/* 12 */       throw new IllegalArgumentException("Null 'x' argument.");
/*    */     }
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsStringSeriesDataItem(String x, double y) {
/* 20 */     this(new String(x), new Double(y));
/*    */   }
/*    */   
/*    */   public String getX() {
/* 24 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public Number getY() {
/* 29 */     return this.y;
/*    */   }
/*    */   
/*    */   public void setY(Number y) {
/* 33 */     this.y = y;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 37 */     if (this == o) {
/* 38 */       return true;
/*    */     }
/* 40 */     if (!(o instanceof LbsStringSeriesDataItem)) {
/* 41 */       return false;
/*    */     }
/* 43 */     LbsStringSeriesDataItem stringSeriesDataItem = (LbsStringSeriesDataItem)o;
/* 44 */     if (this.x != null) {
/* 45 */       if (!this.x.equals(stringSeriesDataItem.x)) {
/* 46 */         return false;
/*    */       }
/*    */     }
/* 49 */     else if (stringSeriesDataItem.x != null) {
/* 50 */       return false;
/*    */     } 
/*    */     
/* 53 */     if (this.y != null) {
/* 54 */       if (!this.y.equals(stringSeriesDataItem.y)) {
/* 55 */         return false;
/*    */       }
/*    */     }
/* 58 */     else if (stringSeriesDataItem.y != null) {
/* 59 */       return false;
/*    */     } 
/*    */     
/* 62 */     return true;
/*    */   }
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
/*    */   public int compareTo(Object o1) {
/*    */     int result;
/* 76 */     if (o1 instanceof LbsStringSeriesDataItem) {
/* 77 */       LbsStringSeriesDataItem datapair = (LbsStringSeriesDataItem)o1;
/* 78 */       result = getX().compareTo(datapair.getX());
/*    */     } else {
/*    */       
/* 81 */       result = 1;
/*    */     } 
/*    */     
/* 84 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\chartgenerator\LbsStringSeriesDataItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */