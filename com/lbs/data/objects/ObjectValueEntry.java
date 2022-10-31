/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class ObjectValueEntry
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String PropertyName;
/*    */   public Object Value;
/*    */   
/*    */   public ObjectValueEntry(String propertyName, Object value) {
/* 14 */     this.PropertyName = propertyName;
/* 15 */     this.Value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 23 */     return super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectValueEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */