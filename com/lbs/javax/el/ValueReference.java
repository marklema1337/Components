/*    */ package com.lbs.javax.el;
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
/*    */ 
/*    */ 
/*    */ public class ValueReference
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Object base;
/*    */   private Object property;
/*    */   
/*    */   public ValueReference(Object base, Object property) {
/* 34 */     this.base = base;
/* 35 */     this.property = property;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getBase() {
/* 40 */     return this.base;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getProperty() {
/* 45 */     return this.property;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\ValueReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */