/*    */ package com.lbs.controls.tablereport;
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
/*    */ public class PairValue
/*    */ {
/*    */   private String name;
/*    */   private Object value;
/*    */   
/*    */   public PairValue(String name, Object value) {
/* 19 */     this.name = name;
/* 20 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 31 */     Object object = null;
/* 32 */     if (o instanceof PairValue) {
/* 33 */       object = ((PairValue)o).getValue();
/*    */     }
/* 35 */     if (object == null) {
/* 36 */       return false;
/*    */     }
/* 38 */     return object.equals(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 43 */     return super.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 53 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 58 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(Object value) {
/* 63 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\PairValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */