/*    */ package com.lbs.util;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ import java.lang.reflect.Method;
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
/*    */ public class StackUtil
/*    */ {
/*    */   public static void dumpStack(boolean filter) {
/* 20 */     prtStackTrace(LbsConsole.getLogger("Data.Client.StackUtil"), filter);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void prtStackTrace(LbsConsole logger, boolean filter) {
/* 28 */     logger.debug(getStack(filter) + "\n");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void dumpStack() {
/* 34 */     dumpStack(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getStack(boolean filter) {
/* 42 */     StringBuilder buf = new StringBuilder();
/* 43 */     StackTraceElement[] trace = (new Exception()).getStackTrace();
/* 44 */     for (int i = 0; i < trace.length; i++) {
/*    */       
/* 46 */       String className = trace[i].getClassName();
/* 47 */       if (!className.startsWith("com.lbs.util.StackUtil"))
/*    */       {
/*    */         
/* 50 */         if (!filter || (filter && className.startsWith("com.lbs")))
/* 51 */           buf.append(trace[i] + "\n");  } 
/*    */     } 
/* 53 */     return buf.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void createHeapDump() {
/*    */     try {
/* 61 */       Class<?> clazz = Class.forName("com.ibm.jvm.Dump");
/* 62 */       Method method = clazz.getMethod("HeapDump", new Class[0]);
/* 63 */       method.invoke(null, null);
/*    */     }
/* 65 */     catch (ClassNotFoundException e) {
/*    */       
/* 67 */       System.out.println("HeapDump unavailable = class not found!");
/*    */     }
/* 69 */     catch (Exception e) {
/*    */       
/* 71 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getStack(Throwable e) {
/* 80 */     StackTraceElement[] stackTrace = e.getStackTrace();
/* 81 */     StringBuilder buf = new StringBuilder();
/* 82 */     for (int i = 0; i < stackTrace.length; i++) {
/*    */       
/* 84 */       buf.append(stackTrace[i]);
/* 85 */       buf.append("\n");
/*    */     } 
/* 87 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\StackUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */