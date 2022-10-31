/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class RemoveAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private String m_FullLoggerName;
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public RemoveAppenderProps(String name, String fullLoggerName) {
/* 21 */     super(name, -1);
/* 22 */     if (fullLoggerName == null)
/* 23 */       throw new IllegalArgumentException("Logger name cannot be null!"); 
/* 24 */     this.m_FullLoggerName = fullLoggerName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] apply() throws Exception {
/* 29 */     synchronized (LbsAppenderFactory.class) {
/*    */       
/* 31 */       LbsConsole logger = LbsConsole.getLogger(this.m_FullLoggerName);
/* 32 */       ILbsConsole[] attachedLoggers = LbsConsoleHelper.getAttachedLoggers(getName());
/* 33 */       if (attachedLoggers.length == 1 && attachedLoggers[0].getName().equals(logger.getName())) {
/*    */         
/* 35 */         ILbsAppender appender = logger.removeAppender(getName());
/* 36 */         LbsAppenderFactory.destroyAppender(appender);
/*    */       } else {
/*    */         
/* 39 */         logger.removeAppender(getName());
/* 40 */       }  (new String[1])[0] = this.m_FullLoggerName; return new String[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 46 */     return new RemoveAppenderProps(getName(), this.m_FullLoggerName);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() throws IOException {
/* 51 */     throw new RuntimeException("Illegal call!");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\RemoveAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */