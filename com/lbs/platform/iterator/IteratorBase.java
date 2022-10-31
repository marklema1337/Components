/*     */ package com.lbs.platform.iterator;
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
/*     */ public abstract class IteratorBase<T>
/*     */   implements ILbsIterator<T>
/*     */ {
/*  15 */   protected int m_ChunkSize = 100;
/*     */   protected boolean m_Bidirectional = false;
/*     */   protected boolean m_HasRecordToGo = false;
/*     */   protected IIterationListener<T> m_Listener;
/*  19 */   protected IteratorParamBase<T> m_Params = null;
/*     */ 
/*     */   
/*     */   public int chunkSize() {
/*  23 */     return this.m_ChunkSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eof() {
/*  28 */     return !this.m_HasRecordToGo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void iterate(ILbsIterationProcessor<T> processor, boolean reverse) throws Exception {
/*  33 */     if (processor != null)
/*     */     {
/*  35 */       if (reverse) {
/*     */         
/*  37 */         T item = last();
/*  38 */         if (item != null) {
/*     */           
/*  40 */           processor.processItem(item);
/*  41 */           while (!eof() && item != null) {
/*     */             
/*  43 */             item = previous();
/*  44 */             if (item != null) {
/*  45 */               processor.processItem(item);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/*  51 */         T item = first();
/*  52 */         if (item != null) {
/*     */           
/*  54 */           processor.processItem(item);
/*  55 */           while (!eof() && item != null) {
/*     */             
/*  57 */             item = next();
/*  58 */             if (item != null) {
/*  59 */               processor.processItem(item);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected abstract T internalFirst() throws Exception;
/*     */   
/*     */   protected abstract T internalLast() throws Exception;
/*     */   
/*     */   protected abstract T internalNext() throws Exception;
/*     */   
/*     */   protected abstract T internalPrevious() throws Exception;
/*     */   
/*     */   protected abstract void internalInitialize(IteratorParamBase<T> paramIteratorParamBase) throws Exception;
/*     */   
/*     */   protected void fireEvent(int eventType, T obj) {
/*  78 */     if (this.m_Listener == null)
/*     */       return; 
/*  80 */     IterationEvent<T> e = new IterationEvent<>();
/*  81 */     e.Data = obj;
/*  82 */     e.ID = eventType;
/*  83 */     e.Iterator = this;
/*  84 */     e.Params = this.m_Params;
/*  85 */     switch (eventType) {
/*     */       
/*     */       case 0:
/*  88 */         this.m_Listener.onFirst(e);
/*     */         break;
/*     */       case 3:
/*  91 */         this.m_Listener.onNext(e);
/*     */         break;
/*     */       case 4:
/*  94 */         this.m_Listener.onPrevious(e);
/*     */         break;
/*     */       case 2:
/*  97 */         this.m_Listener.onLast(e);
/*     */         break;
/*     */       case 1:
/* 100 */         this.m_Listener.onInitialize(e);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public T first() throws Exception {
/* 108 */     T result = internalFirst();
/* 109 */     fireEvent(0, result);
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize(IteratorParamBase<T> params) throws Exception {
/* 115 */     this.m_Params = params;
/* 116 */     fireEvent(1, null);
/* 117 */     internalInitialize(params);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBidirectional() {
/* 122 */     return this.m_Bidirectional;
/*     */   }
/*     */ 
/*     */   
/*     */   public T last() throws Exception {
/* 127 */     T result = internalLast();
/* 128 */     fireEvent(2, result);
/* 129 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public T next() throws Exception {
/* 134 */     T result = internalNext();
/* 135 */     fireEvent(3, result);
/* 136 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public T previous() throws Exception {
/* 141 */     T result = internalPrevious();
/* 142 */     fireEvent(4, result);
/* 143 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIterationListener<T> getListener() {
/* 148 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListener(IIterationListener<T> listener) {
/* 153 */     this.m_Listener = listener;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\iterator\IteratorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */