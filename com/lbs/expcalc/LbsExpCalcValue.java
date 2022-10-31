/*    */ package com.lbs.expcalc;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.util.GregorianCalendar;
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
/*    */ public class LbsExpCalcValue
/*    */ {
/* 17 */   private int expType = 0;
/*    */   
/*    */   private BigDecimal numericVal;
/*    */   private String stringVal;
/*    */   private GregorianCalendar calendar;
/*    */   
/*    */   public LbsExpCalcValue(int xType) {
/* 24 */     this.numericVal = new BigDecimal(0.0D);
/* 25 */     this.stringVal = new String("");
/* 26 */     this.expType = xType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpType() {
/* 34 */     return this.expType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setExpType(int expType) {
/* 43 */     this.expType = expType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BigDecimal getNumericVal() {
/* 52 */     return this.numericVal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getStringVal() {
/* 61 */     return this.stringVal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNumericVal(BigDecimal numericVal) {
/* 70 */     this.numericVal = numericVal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStringVal(String stringVal) {
/* 79 */     this.stringVal = stringVal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GregorianCalendar getCalendar() {
/* 87 */     return this.calendar;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCalendar(GregorianCalendar calendar) {
/* 96 */     this.calendar = calendar;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\expcalc\LbsExpCalcValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */