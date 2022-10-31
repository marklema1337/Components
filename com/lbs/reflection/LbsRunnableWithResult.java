/*    */ package com.lbs.reflection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LbsRunnableWithResult<T>
/*    */   extends Thread
/*    */   implements RunnableWithResult
/*    */ {
/*    */   private T result;
/*    */   private Exception exception;
/*    */   
/*    */   public void run() {
/*    */     try {
/* 20 */       this.result = generateResult();
/* 21 */     } catch (Exception e) {
/* 22 */       this.exception = e;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract T generateResult() throws Exception;
/*    */   
/*    */   public T getResult() {
/* 29 */     return this.result;
/*    */   }
/*    */   
/*    */   public void setResult(T result) {
/* 33 */     this.result = result;
/*    */   }
/*    */   
/*    */   public Exception getException() {
/* 37 */     return this.exception;
/*    */   }
/*    */   
/*    */   public void setException(Exception exception) {
/* 41 */     this.exception = exception;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\reflection\LbsRunnableWithResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */