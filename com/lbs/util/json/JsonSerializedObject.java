/*    */ package com.lbs.util.json;
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
/*    */ public class JsonSerializedObject
/*    */ {
/*    */   private String className;
/*    */   private Object[] instanceArray;
/*    */   private Object instance;
/*    */   
/*    */   public JsonSerializedObject() {}
/*    */   
/*    */   public JsonSerializedObject(Object instance) {
/* 25 */     this.className = instance.getClass().getCanonicalName();
/* 26 */     this.instance = (instance instanceof Object[]) ? null : instance;
/* 27 */     this.instanceArray = (instance instanceof Object[]) ? (Object[])instance : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 32 */     return this.className;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClassName(String className) {
/* 37 */     this.className = className;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object[] getInstanceArray() {
/* 42 */     return this.instanceArray;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInstanceArray(Object[] instanceArray) {
/* 47 */     this.instanceArray = instanceArray;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getInstance() {
/* 52 */     return this.instance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInstance(Object instance) {
/* 57 */     this.instance = instance;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\json\JsonSerializedObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */