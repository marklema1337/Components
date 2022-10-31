/*    */ package org.apache.logging.log4j.core.async;
/*    */ 
/*    */ import org.apache.logging.log4j.Level;
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
/*    */ 
/*    */ public class DefaultAsyncQueueFullPolicy
/*    */   implements AsyncQueueFullPolicy
/*    */ {
/*    */   public EventRoute getRoute(long backgroundThreadId, Level level) {
/* 33 */     Thread currentThread = Thread.currentThread();
/* 34 */     if (currentThread.getId() == backgroundThreadId || currentThread instanceof org.apache.logging.log4j.core.util.Log4jThread)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 40 */       return EventRoute.SYNCHRONOUS;
/*    */     }
/* 42 */     return EventRoute.ENQUEUE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\async\DefaultAsyncQueueFullPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */