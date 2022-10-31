/*    */ package com.lbs.performance;
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
/*    */ public class ThreadID
/*    */   extends ThreadLocal
/*    */ {
/* 17 */   private int nextID = 10001;
/*    */ 
/*    */ 
/*    */   
/*    */   private synchronized Integer getNewID() {
/* 22 */     Integer id = Integer.valueOf(this.nextID);
/* 23 */     this.nextID++;
/* 24 */     return id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Object initialValue() {
/* 31 */     return getNewID();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getThreadID() {
/* 38 */     Integer id = (Integer)get();
/* 39 */     return id.intValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\ThreadID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */