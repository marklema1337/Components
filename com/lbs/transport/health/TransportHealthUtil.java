/*    */ package com.lbs.transport.health;
/*    */ 
/*    */ import com.lbs.util.LbsClassInstanceProvider;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransportHealthUtil
/*    */ {
/*    */   public static synchronized void setExceptionMonitorOn(boolean value) {
/* 11 */     (TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionMonitorOn = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public static synchronized void onTransportException(Throwable t) {
/* 16 */     if ((TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionMonitorOn) {
/* 17 */       (TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionsThrown.add(t);
/*    */     }
/*    */   }
/*    */   
/*    */   public static synchronized ArrayList<Throwable> getBlockerTransportExceptions() {
/* 22 */     if ((TransportHealthUtilFieldHolder.getInstance()).ms_BlockerExceptions.size() > 0) {
/* 23 */       return (TransportHealthUtilFieldHolder.getInstance()).ms_BlockerExceptions;
/*    */     }
/* 25 */     if ((TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionsThrown.size() > 0) {
/*    */       
/* 27 */       for (Throwable throwable : (TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionsThrown)
/*    */       {
/* 29 */         checkThrowable(throwable, throwable);
/*    */       }
/* 31 */       (TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionsThrown.clear();
/*    */     } 
/* 33 */     return (TransportHealthUtilFieldHolder.getInstance()).ms_BlockerExceptions;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void checkThrowable(Throwable throwableToAdd, Throwable throwableToCheck) {
/* 39 */     if (throwableToCheck == null) {
/*    */       return;
/*    */     }
/* 42 */     if (isTransportBlocker(throwableToCheck)) {
/*    */       
/* 44 */       (TransportHealthUtilFieldHolder.getInstance()).ms_BlockerExceptions.add(throwableToAdd);
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 50 */     if (throwableToCheck != throwableToCheck.getCause()) {
/* 51 */       checkThrowable(throwableToAdd, throwableToCheck.getCause());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isThrowableBlocking(Throwable throwableToCheck) {
/* 57 */     if (throwableToCheck == null) {
/* 58 */       return false;
/*    */     }
/* 60 */     if (isTransportBlocker(throwableToCheck))
/*    */     {
/*    */       
/* 63 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 67 */     if (throwableToCheck != throwableToCheck.getCause())
/* 68 */       return isThrowableBlocking(throwableToCheck.getCause()); 
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean isTransportBlocker(Throwable throwable) {
/* 74 */     return TransportExceptionAnalysers.isBlocker(throwable);
/*    */   }
/*    */ 
/*    */   
/*    */   public static synchronized void clearExceptions() {
/* 79 */     (TransportHealthUtilFieldHolder.getInstance()).ms_ExceptionsThrown.clear();
/*    */     
/* 81 */     (TransportHealthUtilFieldHolder.getInstance()).ms_BlockerExceptions = new ArrayList();
/*    */   }
/*    */   
/*    */   public static class TransportHealthUtilFieldHolder
/*    */   {
/*    */     private boolean ms_ExceptionMonitorOn = false;
/* 87 */     private ArrayList<Throwable> ms_ExceptionsThrown = new ArrayList<>();
/* 88 */     private ArrayList<Throwable> ms_BlockerExceptions = new ArrayList<>();
/*    */ 
/*    */     
/*    */     private static TransportHealthUtilFieldHolder getInstance() {
/* 92 */       return (TransportHealthUtilFieldHolder)LbsClassInstanceProvider.getInstanceByClass(TransportHealthUtilFieldHolder.class);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\health\TransportHealthUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */