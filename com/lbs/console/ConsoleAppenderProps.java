/*    */ package com.lbs.console;
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
/*    */ public class ConsoleAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private LbsLayoutFactory.LogFormat format;
/*    */   private boolean systemErr;
/*    */   
/*    */   public ConsoleAppenderProps(String name, int domainId, LbsLayoutFactory.LogFormat format, boolean systemErr) {
/* 26 */     super(name, domainId);
/* 27 */     if (format == null)
/* 28 */       throw new IllegalArgumentException("Log format cannot be null!"); 
/* 29 */     this.format = format;
/* 30 */     this.systemErr = systemErr;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsLayoutFactory.LogFormat getFormat() {
/* 35 */     return this.format;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSystemErr() {
/* 43 */     return this.systemErr;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 48 */     return new ConsoleAppenderProps(getName(), getDomainId(), (LbsLayoutFactory.LogFormat)this.format.clone(), this.systemErr);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() {
/* 53 */     return LbsAppenderFactory.createAppender(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\ConsoleAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */