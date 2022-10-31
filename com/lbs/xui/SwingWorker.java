/*     */ package com.lbs.xui;
/*     */ 
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SwingWorker
/*     */ {
/*     */   private Object value;
/*     */   private Thread thread;
/*     */   private ThreadVar threadVar;
/*     */   
/*     */   private static class ThreadVar
/*     */   {
/*     */     private Thread thread;
/*     */     
/*     */     ThreadVar(Thread t) {
/*  27 */       this.thread = t;
/*  28 */     } synchronized Thread get() { return this.thread; } synchronized void clear() {
/*  29 */       this.thread = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized Object getValue() {
/*  39 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void setValue(Object x) {
/*  46 */     this.value = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finished() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interrupt() {
/*  66 */     Thread t = this.threadVar.get();
/*  67 */     if (t != null) {
/*  68 */       t.interrupt();
/*     */     }
/*  70 */     this.threadVar.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/*     */     while (true) {
/*  82 */       Thread t = this.threadVar.get();
/*  83 */       if (t == null) {
/*  84 */         return getValue();
/*     */       }
/*     */       try {
/*  87 */         t.join();
/*     */       }
/*  89 */       catch (InterruptedException e) {
/*  90 */         Thread.currentThread().interrupt();
/*  91 */         return null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SwingWorker() {
/* 102 */     final Runnable doFinished = new Runnable() { public void run() {
/* 103 */           SwingWorker.this.finished();
/*     */         } }
/*     */       ;
/* 106 */     Runnable doConstruct = new Runnable() {
/*     */         public void run() {
/*     */           try {
/* 109 */             SwingWorker.this.setValue(SwingWorker.this.construct());
/*     */           } finally {
/*     */             
/* 112 */             SwingWorker.this.threadVar.clear();
/*     */           } 
/*     */           
/* 115 */           SwingUtilities.invokeLater(doFinished);
/*     */         }
/*     */       };
/*     */     
/* 119 */     Thread t = new Thread(doConstruct);
/* 120 */     this.threadVar = new ThreadVar(t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 127 */     Thread t = this.threadVar.get();
/* 128 */     if (t != null)
/* 129 */       t.start(); 
/*     */   }
/*     */   
/*     */   public abstract Object construct();
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\xui\SwingWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */