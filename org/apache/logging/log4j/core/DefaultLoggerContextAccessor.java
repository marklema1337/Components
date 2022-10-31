/*    */ package org.apache.logging.log4j.core;
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
/*    */ public class DefaultLoggerContextAccessor
/*    */   implements LoggerContextAccessor
/*    */ {
/* 28 */   public static DefaultLoggerContextAccessor INSTANCE = new DefaultLoggerContextAccessor();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoggerContext getLoggerContext() {
/* 37 */     return LoggerContext.getContext();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\DefaultLoggerContextAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */