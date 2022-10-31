/*    */ package com.lbs.platform.iterator;
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
/*    */ public class IteratorParamBase<T>
/*    */ {
/*    */   private boolean m_Bidirectional = false;
/* 15 */   private int m_ChunkSize = 100;
/*    */   
/*    */   private boolean m_ThrowExceptions = false;
/*    */   
/*    */   private IIterationListener<T> m_Listener;
/*    */ 
/*    */   
/*    */   public int getChunkSize() {
/* 23 */     return this.m_ChunkSize;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setChunkSize(int chunkSize) {
/* 30 */     this.m_ChunkSize = chunkSize;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IIterationListener<T> getListener() {
/* 37 */     return this.m_Listener;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setListener(IIterationListener<T> listener) {
/* 44 */     this.m_Listener = listener;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBidirectional() {
/* 51 */     return this.m_Bidirectional;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBidirectional(boolean bidirectional) {
/* 58 */     this.m_Bidirectional = bidirectional;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setThrowExceptions(boolean throwExceptions) {
/* 63 */     this.m_ThrowExceptions = throwExceptions;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isThrowExceptions() {
/* 68 */     return this.m_ThrowExceptions;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\iterator\IteratorParamBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */