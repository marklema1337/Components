/*    */ package com.lbs.util;
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
/*    */ public class LbsHeapSizeUtil
/*    */ {
/*    */   private long m_Mem0;
/*    */   
/*    */   public LbsHeapSizeUtil(boolean startMeasure) {
/* 18 */     if (startMeasure) {
/* 19 */       startMeasure();
/*    */     }
/*    */   }
/*    */   
/*    */   public void startMeasure() {
/* 24 */     runGarbageCollect();
/* 25 */     this.m_Mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
/*    */   }
/*    */ 
/*    */   
/*    */   private void runGarbageCollect() {
/* 30 */     for (int i = 0; i < 16; i++)
/*    */     {
/* 32 */       System.gc();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public long stopMeasure() {
/* 38 */     runGarbageCollect();
/* 39 */     long mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
/* 40 */     return mem1 - this.m_Mem0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsHeapSizeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */