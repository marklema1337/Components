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
/*    */ public class FluentdAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String servers;
/*    */   private String applicationName;
/*    */   
/*    */   public FluentdAppenderProps(String name, int domainId, String servers, String applicationName) {
/* 21 */     super(name, domainId);
/* 22 */     if (servers == null || servers.length() == 0) {
/* 23 */       throw new IllegalArgumentException("Server's address is required!");
/*    */     }
/* 25 */     this.servers = servers;
/* 26 */     this.applicationName = applicationName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getServers() {
/* 31 */     return this.servers;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getApplicationName() {
/* 36 */     return this.applicationName;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() {
/* 41 */     return LbsAppenderFactory.createAppender(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 46 */     return new FluentdAppenderProps(getName(), getDomainId(), this.servers, this.applicationName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\FluentdAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */