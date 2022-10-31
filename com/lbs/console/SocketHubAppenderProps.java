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
/*    */ public class SocketHubAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int port;
/*    */   private boolean includeLineInfo;
/*    */   
/*    */   public SocketHubAppenderProps(String name, int domainId, int port, boolean includeLineInfo) {
/* 21 */     super(name, domainId);
/* 22 */     if (port <= 0)
/* 23 */       throw new IllegalArgumentException("Illegal port number (" + port + ")"); 
/* 24 */     this.port = port;
/* 25 */     this.includeLineInfo = includeLineInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPort() {
/* 30 */     return this.port;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isIncludeLineInfo() {
/* 35 */     return this.includeLineInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() {
/* 40 */     return LbsAppenderFactory.createAppender(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 45 */     return new SocketHubAppenderProps(getName(), getDomainId(), this.port, this.includeLineInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\SocketHubAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */