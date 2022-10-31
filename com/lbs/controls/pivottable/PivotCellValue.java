/*     */ package com.lbs.controls.pivottable;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PivotCellValue
/*     */   implements Comparable<PivotCellValue>, Serializable, Cloneable
/*     */ {
/*     */   private Object value;
/*     */   private String text;
/*  20 */   private int horizontalAlignment = 0;
/*     */ 
/*     */   
/*     */   private boolean expanded = true;
/*     */   
/*     */   private boolean included = true;
/*     */ 
/*     */   
/*     */   public static PivotCellValue EMPTY() {
/*  29 */     PivotCellValue pcv = new PivotCellValue();
/*  30 */     pcv.setValue("?");
/*  31 */     pcv.setExpanded(true);
/*     */     
/*  33 */     return pcv;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  37 */     PivotCellValue clone = new PivotCellValue();
/*  38 */     clone.setValue(this.value);
/*     */     
/*  40 */     clone.setHorizontalAlignment(this.horizontalAlignment);
/*  41 */     clone.setExpanded(this.expanded);
/*  42 */     clone.setIncluded(this.included);
/*     */     
/*  44 */     return clone;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  48 */     if (this.text == null) {
/*  49 */       return 0;
/*     */     }
/*     */     
/*  52 */     return this.text.length();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  56 */     if (o == null || !(o instanceof PivotCellValue)) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     PivotCellValue pcv = (PivotCellValue)o;
/*     */     
/*  62 */     return pcv.getText().equals(this.text);
/*     */   }
/*     */   
/*     */   private void setText() {
/*  66 */     if (PivotTableInfo.getFormatter() != null) {
/*  67 */       this.text = PivotTableInfo.getFormatter().format(this.value);
/*     */     } else {
/*  69 */       this.text = this.value.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  74 */     return this.text;
/*     */   }
/*     */   
/*     */   public PivotCellValue(Object value, String text) {
/*  78 */     this.value = value;
/*  79 */     this.text = text;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  84 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/*  89 */     this.value = value;
/*  90 */     setText();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  95 */     return this.text;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/* 100 */     return this.horizontalAlignment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignment(int horizontalAlignment) {
/* 105 */     this.horizontalAlignment = horizontalAlignment;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(PivotCellValue o) {
/* 110 */     if (o == null || !(o.getValue() instanceof Comparable)) {
/* 111 */       return -1;
/*     */     }
/*     */     
/* 114 */     Comparable<Comparable> c1 = (Comparable)this.value;
/* 115 */     Comparable c2 = (Comparable)o.getValue();
/*     */     
/* 117 */     int result = -1;
/*     */     try {
/* 119 */       result = c1.compareTo(c2);
/* 120 */     } catch (Exception e) {
/* 121 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 124 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExpanded() {
/* 129 */     return this.expanded;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpanded(boolean expanded) {
/* 134 */     this.expanded = expanded;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIncluded() {
/* 139 */     return this.included;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncluded(boolean included) {
/* 144 */     this.included = included;
/*     */   }
/*     */   
/*     */   public PivotCellValue() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotCellValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */