/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AggregateValue
/*    */ {
/*    */   private AggregateFunctionType type;
/*    */   private Number value;
/*    */   private static DecimalFormat decimalFormat;
/*    */   
/*    */   public enum AggregateFunctionType
/*    */   {
/* 18 */     SUM(
/*    */ 
/*    */       
/* 21 */       "SUM", 1), MIN("MIN", 0), MAX("MAX", 0), COUNT("COUNT", 0), AVG("AVG", 1);
/*    */     
/*    */     private String name;
/*    */     
/*    */     private int type;
/*    */     
/*    */     AggregateFunctionType(String name, int type) {
/* 28 */       this.name = name;
/* 29 */       this.type = type;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getName() {
/* 34 */       return this.name;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getType() {
/* 39 */       return this.type;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AggregateValue(AggregateFunctionType type, Number value) {
/* 49 */     this.type = type;
/* 50 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public AggregateFunctionType getType() {
/* 55 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public Number getValue() {
/* 60 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(Number value) {
/* 65 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     StringBuffer buffer = new StringBuffer(String.valueOf(this.type.toString()) + "= ");
/* 71 */     if (this.value != null)
/*    */     {
/* 73 */       buffer.append(decimalFormat.format(this.value.doubleValue()));
/*    */     }
/*    */     
/* 76 */     return buffer.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 81 */     if (o == null || !(o instanceof AggregateValue))
/*    */     {
/* 83 */       return false;
/*    */     }
/*    */     
/* 86 */     AggregateValue av = (AggregateValue)o;
/* 87 */     return (av.getType() == this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 92 */     return super.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setDecimalFormat(DecimalFormat decimalFormat) {
/* 97 */     AggregateValue.decimalFormat = decimalFormat;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\AggregateValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */