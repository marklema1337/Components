/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class LbsThrowableHandler
/*    */ {
/* 17 */   private static List<ILbsThrowableFilter> ms_Filters = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public static void registerFilter(ILbsThrowableFilter filter) {
/* 21 */     if (!ms_Filters.contains(filter)) {
/* 22 */       ms_Filters.add(filter);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void processThrowable(Throwable t) {
/* 27 */     if (t == null) {
/*    */       return;
/*    */     }
/* 30 */     StackTraceElement[] trace = t.getStackTrace();
/* 31 */     if (trace != null) {
/*    */       
/* 33 */       StackTraceElement[] subTrace = null;
/* 34 */       StackTraceElement[] newTrace = trace;
/*    */       
/* 36 */       for (int i = 0; i < ms_Filters.size(); i++) {
/*    */         
/* 38 */         ILbsThrowableFilter filter = ms_Filters.get(i);
/*    */         
/*    */         try {
/* 41 */           subTrace = filter.filterStackTrace(t, newTrace);
/* 42 */           if (subTrace != null) {
/* 43 */             newTrace = subTrace;
/*    */           }
/* 45 */         } catch (Exception exception) {}
/*    */       } 
/*    */ 
/*    */       
/* 49 */       t.setStackTrace(newTrace);
/*    */     } 
/*    */     
/* 52 */     if (t.getCause() != null)
/* 53 */       processThrowable(t.getCause()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsThrowableHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */