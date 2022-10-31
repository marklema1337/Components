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
/*    */ 
/*    */ public class ExceptionUtil
/*    */ {
/*    */   public static String getMessage(Throwable e, boolean forceStackTrace, boolean filterLbs) {
/* 17 */     String msg = e.getMessage();
/*    */     
/* 19 */     if (e instanceof ArrayIndexOutOfBoundsException) {
/* 20 */       msg = "ArrayIndexOutOfBoundsException " + msg;
/*    */     }
/* 22 */     if (forceStackTrace || StringUtil.isEmpty(msg)) {
/*    */       
/* 24 */       msg = e.getClass().getName() + (forceStackTrace ? (": " + msg) : "");
/* 25 */       StringBuilder buffer = new StringBuilder();
/* 26 */       buffer.append(msg);
/* 27 */       StackTraceElement[] stack = e.getStackTrace();
/* 28 */       for (int i = 0; i < stack.length; i++) {
/*    */         
/* 30 */         if (!filterLbs || stack[i].getClassName().startsWith("com.lbs")) {
/*    */ 
/*    */           
/* 33 */           buffer.append("\n" + stack[i]);
/* 34 */           if (filterLbs)
/*    */             break; 
/*    */         } 
/*    */       } 
/* 38 */       msg = buffer.toString();
/*    */     } 
/* 40 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getMessage(Throwable e) {
/* 45 */     return getMessage(e, false, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getDetailedMessage(Throwable e) {
/* 50 */     String msg = e.getLocalizedMessage();
/*    */     
/* 52 */     if (e instanceof ArrayIndexOutOfBoundsException) {
/* 53 */       msg = "ArrayIndexOutOfBoundsException " + msg;
/*    */     }
/* 55 */     if (StringUtil.isEmpty(msg)) {
/*    */       
/* 57 */       msg = e.getClass().getName();
/* 58 */       StackTraceElement[] stackTrace = e.getStackTrace();
/* 59 */       if (stackTrace != null) {
/*    */         
/* 61 */         StringBuilder stackTraceBuff = new StringBuilder();
/* 62 */         for (int i = 0; i < stackTrace.length && i < 5; i++)
/*    */         {
/* 64 */           stackTraceBuff.append("\n\t" + stackTrace[i]);
/*    */         }
/* 66 */         if (stackTraceBuff.length() > 0) {
/* 67 */           msg = msg + "\n(inner exception stack trace part :" + stackTraceBuff.toString() + "\n)\n";
/*    */         }
/*    */       } 
/*    */     } 
/* 71 */     return msg;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ExceptionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */