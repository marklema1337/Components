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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExtendAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private String m_FullLoggerName;
/*    */   private AppenderProps m_AppenderProps;
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public ExtendAppenderProps(String fullLoggerName, AppenderProps appenderProps) {
/* 27 */     super((appenderProps != null) ? appenderProps.getName() : null, (appenderProps != null) ? appenderProps.getDomainId() : -1);
/* 28 */     if (fullLoggerName == null)
/* 29 */       throw new IllegalArgumentException("Logger name cannot be null!"); 
/* 30 */     if (appenderProps == null)
/* 31 */       throw new IllegalArgumentException("Appender props cannot be empty!"); 
/* 32 */     this.m_FullLoggerName = fullLoggerName;
/* 33 */     this.m_AppenderProps = appenderProps;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] apply() throws Exception {
/* 38 */     synchronized (LbsAppenderFactory.class) {
/*    */       
/* 40 */       ILbsAppender appender = LbsConsoleHelper.getAppenderByName(getName());
/* 41 */       if (appender == null) {
/* 42 */         throw new Exception("An appender with the name " + getName() + " does not exist!");
/*    */       }
/* 44 */       LbsConsole.getLogger(this.m_FullLoggerName).addAppender(appender);
/* 45 */       (new String[1])[0] = this.m_FullLoggerName; return new String[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 51 */     return new ExtendAppenderProps(this.m_FullLoggerName, (AppenderProps)this.m_AppenderProps.clone());
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() throws IOException {
/* 56 */     return this.m_AppenderProps.createAppender();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\ExtendAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */