/*    */ package com.lbs.console;
/*    */ 
/*    */ import org.apache.logging.log4j.core.Appender;
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
/*    */ public class AppenderInUseException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private LbsConsole[] m_AttachedLoggers;
/*    */   private Appender m_Appender;
/*    */   
/*    */   public AppenderInUseException(LbsConsole[] attachedLoggers, Appender appender) {
/* 23 */     if (attachedLoggers == null)
/* 24 */       this.m_AttachedLoggers = new LbsConsole[0]; 
/* 25 */     this.m_AttachedLoggers = attachedLoggers;
/* 26 */     this.m_Appender = appender;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsConsole[] getAttachedLoggers() {
/* 34 */     return this.m_AttachedLoggers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Appender getAppender() {
/* 42 */     return this.m_Appender;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\AppenderInUseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */