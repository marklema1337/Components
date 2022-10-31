/*    */ package com.lbs.http;
/*    */ 
/*    */ import com.lbs.channel.IChannel;
/*    */ import com.lbs.channel.IChannelProvider;
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
/*    */ public class HttpChannelProvider
/*    */   implements IChannelProvider
/*    */ {
/*    */   private boolean m_CollectHeader = true;
/*    */   private boolean m_UseSSL = true;
/*    */   private IChannel m_Channel;
/*    */   private boolean m_SpringBoot = false;
/*    */   
/*    */   public HttpChannelProvider() {
/* 24 */     this.m_CollectHeader = true;
/* 25 */     this.m_UseSSL = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public HttpChannelProvider(boolean collectHeader) {
/* 30 */     this();
/* 31 */     this.m_CollectHeader = collectHeader;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initializeProvider() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean finalizeProvider() {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChannel createChannel() {
/* 55 */     this.m_Channel = new HttpChannel(this.m_CollectHeader, this.m_UseSSL);
/* 56 */     this.m_Channel.setSpringBoot(this.m_SpringBoot);
/* 57 */     return this.m_Channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void closeChannel(boolean bForce) {
/* 62 */     if (this.m_Channel != null) {
/* 63 */       this.m_Channel.close(bForce);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isSpringBoot() {
/* 68 */     return this.m_SpringBoot;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSpringBoot(boolean springBoot) {
/* 73 */     this.m_SpringBoot = springBoot;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\http\HttpChannelProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */