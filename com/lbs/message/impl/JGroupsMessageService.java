/*     */ package com.lbs.message.impl;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.platform.interfaces.IMessageChannel;
/*     */ import com.lbs.platform.interfaces.IMessageService;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.net.InetAddress;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import org.jgroups.Channel;
/*     */ import org.jgroups.ChannelListener;
/*     */ import org.jgroups.JChannel;
/*     */ import org.jgroups.protocols.TCP;
/*     */ import org.jgroups.stack.Protocol;
/*     */ import org.jgroups.stack.ProtocolStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JGroupsMessageService
/*     */   implements IMessageService, ChannelListener
/*     */ {
/*  29 */   private static LbsConsole ms_Logger = LbsConsole.getLogger(JGroupsMessageService.class);
/*     */   
/*  31 */   private List<Channel> m_Channels = new Vector<>();
/*     */   
/*     */   private boolean m_Closing = false;
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*  37 */     prepareForClose();
/*  38 */     super.finalize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private JChannel getChannelImpl(String channelId, String ipAddress) {
/*     */     try {
/*  45 */       JChannel channel = new JChannel(false);
/*  46 */       ProtocolStack stack = new ProtocolStack();
/*  47 */       channel.setProtocolStack(stack);
/*     */       
/*  49 */       if (JLbsStringUtil.isEmpty(ipAddress)) {
/*  50 */         ipAddress = JLbsStringUtil.getIpAddress();
/*     */       }
/*  52 */       TCP tcp = new TCP();
/*  53 */       tcp.setValue("bind_addr", InetAddress.getByName(ipAddress));
/*  54 */       tcp.setLoopback(false);
/*  55 */       tcp.setBindPort(7800);
/*  56 */       tcp.setMaxBundleSize(64000);
/*  57 */       tcp.setMaxBundleTimeout(30L);
/*  58 */       tcp.setEnableBundling(true);
/*  59 */       tcp.setConnExpireTime(300L);
/*  60 */       tcp.setTimerMinThreads(4);
/*  61 */       tcp.setTimerMaxThreads(10);
/*  62 */       tcp.setTimerKeepAliveTime(3000L);
/*  63 */       tcp.setThreadPoolQueueEnabled(true);
/*  64 */       tcp.setThreadPoolMinThreads(1);
/*  65 */       tcp.setThreadPoolMaxThreads(10);
/*  66 */       tcp.setThreadPoolKeepAliveTime(5000L);
/*  67 */       tcp.setOOBThreadPoolMinThreads(1);
/*  68 */       tcp.setOOBThreadPoolMaxThreads(8);
/*  69 */       tcp.setOOBThreadPoolKeepAliveTime(5000L);
/*     */       
/*  71 */       stack.addProtocol((Protocol)tcp);
/*  72 */       stack.init();
/*     */       
/*  74 */       channel.addChannelListener(this);
/*  75 */       return channel;
/*     */     }
/*  77 */     catch (Exception e) {
/*     */       
/*  79 */       ms_Logger.error(e, e);
/*     */       
/*  81 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public IMessageChannel getChannel(String channelId) {
/*  86 */     return new JGroupsMessageChannel(getChannelImpl(channelId, ""), channelId);
/*     */   }
/*     */ 
/*     */   
/*     */   public IMessageChannel getChannel(String channelId, String ipAddress) {
/*  91 */     return new JGroupsMessageChannel(getChannelImpl(channelId, ipAddress), channelId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareForClose() {
/*  96 */     this.m_Closing = true;
/*     */     
/*     */     try {
/*  99 */       for (Channel channel : this.m_Channels)
/*     */       {
/* 101 */         channel.close();
/*     */       }
/* 103 */       this.m_Channels.clear();
/*     */     }
/*     */     finally {
/*     */       
/* 107 */       this.m_Closing = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelClosed(Channel channel) {
/* 113 */     if (!this.m_Closing)
/* 114 */       this.m_Channels.remove(channel); 
/*     */   }
/*     */   
/*     */   public void channelConnected(Channel arg0) {}
/*     */   
/*     */   public void channelDisconnected(Channel arg0) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\impl\JGroupsMessageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */