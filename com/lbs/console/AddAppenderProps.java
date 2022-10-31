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
/*    */ public class AddAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private String m_FullLoggerName;
/*    */   private AppenderProps m_AppenderProps;
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public AddAppenderProps(String fullLoggerName, AppenderProps appenderProps) {
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
/* 40 */       if (LbsConsoleHelper.getAppenderByName(getName()) != null) {
/* 41 */         throw new Exception("An appender with the same name (" + getName() + ") is already exists!");
/*    */       }
/* 43 */       LbsConsole.getLogger(this.m_FullLoggerName).addAppender(createAppender());
/* 44 */       (new String[1])[0] = this.m_FullLoggerName; return new String[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 50 */     return new AddAppenderProps(this.m_FullLoggerName, (AppenderProps)this.m_AppenderProps.clone());
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() throws IOException {
/* 55 */     return this.m_AppenderProps.createAppender();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\AddAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */