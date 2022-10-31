/*    */ package com.lbs.console;
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
/*    */ public abstract class LbsThrowableFilterBase
/*    */   implements ILbsThrowableFilter
/*    */ {
/*    */   protected abstract boolean isFilterStartElem(StackTraceElement paramStackTraceElement, boolean paramBoolean);
/*    */   
/*    */   protected abstract boolean isFilterEndElem(StackTraceElement paramStackTraceElement);
/*    */   
/*    */   protected boolean includeFilterStartElem() {
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public StackTraceElement[] filterStackTrace(Throwable t, StackTraceElement[] trace) {
/* 26 */     StackTraceElement[] newTrace = new StackTraceElement[trace.length];
/*    */     
/* 28 */     int startIdx = -1;
/* 29 */     int copyStartIdx = -1;
/* 30 */     int endIdx = -1;
/* 31 */     int cropCount = 0;
/* 32 */     int copyIdx = trace.length - 1;
/* 33 */     boolean lastElem = true;
/* 34 */     for (int i = trace.length - 1; i >= 0; i--) {
/*    */       
/* 36 */       StackTraceElement elem = trace[i];
/* 37 */       if (startIdx < 0) {
/*    */         
/* 39 */         if (isFilterStartElem(elem, lastElem))
/*    */         {
/* 41 */           startIdx = i;
/* 42 */           copyStartIdx = copyIdx;
/*    */         }
/*    */       
/* 45 */       } else if (isFilterEndElem(elem)) {
/*    */         
/* 47 */         endIdx = i;
/* 48 */         if (includeFilterStartElem()) {
/*    */           
/* 50 */           copyIdx = copyStartIdx - 1;
/* 51 */           cropCount += startIdx - endIdx - 1;
/*    */         }
/*    */         else {
/*    */           
/* 55 */           copyIdx = copyStartIdx;
/* 56 */           cropCount += startIdx - endIdx;
/*    */         } 
/* 58 */         startIdx = -1;
/*    */       } 
/* 60 */       newTrace[copyIdx] = elem;
/* 61 */       copyIdx--;
/* 62 */       lastElem = false;
/*    */     } 
/* 64 */     if (startIdx > 0) {
/*    */       
/* 66 */       endIdx = 0;
/* 67 */       boolean copyFirst = true;
/* 68 */       if (includeFilterStartElem()) {
/*    */         
/* 70 */         copyIdx = copyStartIdx - 1;
/* 71 */         cropCount += startIdx - endIdx - 1;
/*    */ 
/*    */       
/*    */       }
/* 75 */       else if (isFilterEndElem(trace[startIdx])) {
/* 76 */         copyFirst = false;
/*    */       } else {
/*    */         
/* 79 */         copyIdx = copyStartIdx;
/* 80 */         cropCount += startIdx - endIdx;
/*    */       } 
/*    */       
/* 83 */       if (copyFirst)
/* 84 */         newTrace[copyIdx] = trace[0]; 
/*    */     } 
/* 86 */     if (cropCount > 0) {
/*    */       
/* 88 */       StackTraceElement[] result = new StackTraceElement[trace.length - cropCount];
/* 89 */       System.arraycopy(newTrace, cropCount, result, 0, result.length);
/* 90 */       return result;
/*    */     } 
/* 92 */     return trace;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsThrowableFilterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */