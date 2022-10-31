/*    */ package com.lbs.mail;
/*    */ 
/*    */ import java.util.LinkedList;
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
/*    */ public class JLbsWorkQueue
/*    */ {
/* 16 */   private LinkedList m_Queue = new LinkedList();
/*    */   
/*    */   private boolean m_LogActions = false;
/*    */   
/*    */   protected boolean m_Finished;
/*    */   
/*    */   public synchronized void addWork(Object work) {
/* 23 */     this.m_Queue.addLast(work);
/* 24 */     if (this.m_LogActions)
/* 25 */       System.out.println("In thread:" + Thread.currentThread() + " adding work to queue! " + work); 
/* 26 */     notify();
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Object getWork() throws InterruptedException {
/* 31 */     while (this.m_Queue.isEmpty()) {
/*    */       
/* 33 */       if (this.m_LogActions)
/* 34 */         System.out.println("In thread:" + Thread.currentThread() + " waiting for new work item!"); 
/* 35 */       if (getFinished())
/* 36 */         return null; 
/* 37 */       wait();
/*    */     } 
/* 39 */     Object work = this.m_Queue.removeFirst();
/*    */     
/* 41 */     if (this.m_LogActions)
/* 42 */       System.out.println("In thread:" + Thread.currentThread() + " took work to process! " + work); 
/* 43 */     return work;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean getFinished() {
/* 51 */     synchronized (this) {
/*    */       
/* 53 */       return this.m_Finished;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFinished(boolean bFinished) {
/* 59 */     synchronized (this) {
/*    */       
/* 61 */       this.m_Finished = bFinished;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void setLogActions(boolean logActions) {
/* 69 */     this.m_LogActions = logActions;
/*    */   }
/*    */ 
/*    */   
/*    */   public LinkedList getQueue() {
/* 74 */     return this.m_Queue;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsWorkQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */