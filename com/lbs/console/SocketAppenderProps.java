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
/*    */ public class SocketAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String remoteServerAddress;
/*    */   private int remoteServerPort;
/*    */   private boolean includeLineInfo;
/*    */   
/*    */   public SocketAppenderProps(String name, int domainId, String remoteServerAddress, int remoteServerPort, boolean includeLineInfo) {
/* 22 */     super(name, domainId);
/* 23 */     if (remoteServerAddress == null || remoteServerAddress.length() == 0)
/* 24 */       throw new IllegalArgumentException("Remote server's address is required!"); 
/* 25 */     if (remoteServerPort <= 0) {
/* 26 */       throw new IllegalArgumentException("Illegal port number (" + remoteServerPort + ")");
/*    */     }
/* 28 */     this.remoteServerAddress = remoteServerAddress;
/* 29 */     this.remoteServerPort = remoteServerPort;
/* 30 */     this.includeLineInfo = includeLineInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRemoteServerAddress() {
/* 35 */     return this.remoteServerAddress;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRemoteServerPort() {
/* 40 */     return this.remoteServerPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isIncludeLineInfo() {
/* 45 */     return this.includeLineInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() {
/* 50 */     return LbsAppenderFactory.createAppender(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 55 */     return new SocketAppenderProps(getName(), getDomainId(), this.remoteServerAddress, this.remoteServerPort, this.includeLineInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\SocketAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */