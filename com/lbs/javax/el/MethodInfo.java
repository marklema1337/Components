/*    */ package com.lbs.javax.el;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodInfo
/*    */ {
/*    */   private final String name;
/*    */   private final Class<?> returnType;
/*    */   private final Class<?>[] paramTypes;
/*    */   
/*    */   public MethodInfo(String name, Class<?> returnType, Class[] paramTypes) {
/* 39 */     this.name = name;
/* 40 */     this.returnType = returnType;
/* 41 */     this.paramTypes = paramTypes;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 51 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class<?>[] getParamTypes() {
/* 61 */     return this.paramTypes;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class<?> getReturnType() {
/* 71 */     return this.returnType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\MethodInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */