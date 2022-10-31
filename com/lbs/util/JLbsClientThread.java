/*    */ package com.lbs.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsClientThread
/*    */   extends Thread
/*    */ {
/*    */   public JLbsClientThread(Runnable target) {
/* 13 */     super(target);
/* 14 */     initX();
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsClientThread(String name) {
/* 19 */     super(name);
/* 20 */     initX();
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsClientThread(ThreadGroup group, Runnable target) {
/* 25 */     super(group, target);
/* 26 */     initX();
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsClientThread(Runnable target, String name) {
/* 31 */     super(target, name);
/* 32 */     initX();
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsClientThread(ThreadGroup group, String name) {
/* 37 */     super(group, name);
/* 38 */     initX();
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsClientThread(ThreadGroup group, Runnable target, String name) {
/* 43 */     super(group, target, name);
/* 44 */     initX();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsClientThread(ThreadGroup group, Runnable target, String name, long stackSize) {
/* 53 */     super(group, target, name, stackSize);
/* 54 */     initX();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsClientThread() {
/* 60 */     initX();
/*    */   }
/*    */ 
/*    */   
/*    */   private void initX() {
/* 65 */     setContextClassLoader(Thread.currentThread().getContextClassLoader());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsClientThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */