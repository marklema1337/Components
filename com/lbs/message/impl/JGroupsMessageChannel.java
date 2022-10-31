/*     */ package com.lbs.message.impl;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.message.CloseChannelMessage;
/*     */ import com.lbs.platform.interfaces.IMessage;
/*     */ import com.lbs.platform.interfaces.IMessageChannel;
/*     */ import com.lbs.platform.interfaces.IMessageReceiver;
/*     */ import com.lbs.platform.interfaces.MessageType;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import org.jgroups.Address;
/*     */ import org.jgroups.JChannel;
/*     */ import org.jgroups.Message;
/*     */ import org.jgroups.Receiver;
/*     */ import org.jgroups.protocols.TCP;
/*     */ import org.jgroups.stack.IpAddress;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JGroupsMessageChannel
/*     */   implements IMessageChannel
/*     */ {
/*  29 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(JGroupsMessageChannel.class);
/*     */   
/*     */   private JChannel m_Channel;
/*     */   
/*     */   private String m_ChannelId;
/*     */   
/*     */   private boolean m_NeedCloseMessage = false;
/*     */ 
/*     */   
/*     */   public JGroupsMessageChannel(JChannel channel, String channelId) {
/*  39 */     this.m_Channel = channel;
/*  40 */     setChannelId(channelId);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void send(IMessage message) {
/*  45 */     if (!this.m_Channel.isOpen()) {
/*     */       
/*  47 */       ms_Logger.error("Trying to send message on a closed channel!! Ignoring message!!", new Throwable());
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/*  52 */       Message msg = prepareMessage(message);
/*  53 */       connectChannel();
/*  54 */       this.m_Channel.send(msg);
/*  55 */       this.m_NeedCloseMessage = true;
/*     */     }
/*  57 */     catch (Exception e) {
/*     */       
/*  59 */       ms_Logger.error(e, e);
/*     */     } 
/*  61 */     if (message.getMessageType() == MessageType.CHANNEL_CLOSE) {
/*     */       
/*  63 */       this.m_NeedCloseMessage = false;
/*  64 */       close(message.getDestIp(), message.getDestPort());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Message prepareMessage(IMessage message) throws UnknownHostException {
/*  70 */     Message msg = new Message();
/*  71 */     msg.setDest((Address)new IpAddress(InetAddress.getByName(message.getDestIp()), message.getDestPort()));
/*  72 */     msg.setObject(message);
/*  73 */     msg.setFlag((byte)2);
/*  74 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   private void connectChannel() throws Exception {
/*  79 */     if (!this.m_Channel.isConnected()) {
/*  80 */       this.m_Channel.connect(getChannelId());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setReceiver(IMessageReceiver receiver) {
/*     */     try {
/*  87 */       connectChannel();
/*     */     }
/*  89 */     catch (Exception e) {
/*     */       
/*  91 */       ms_Logger.error(e, e);
/*     */     } 
/*  93 */     this.m_Channel.setReceiver((Receiver)new JGroupsMessageReceiver(this.m_Channel, receiver));
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void close(String clientIp, int clientPort) {
/*  98 */     if (this.m_Channel.isOpen()) {
/*     */       
/* 100 */       if (this.m_NeedCloseMessage)
/*     */         
/*     */         try {
/* 103 */           this.m_Channel.send(prepareMessage((IMessage)new CloseChannelMessage(clientIp, clientPort)));
/*     */         }
/* 105 */         catch (Exception e) {
/*     */           
/* 107 */           ms_Logger.error(e, e);
/*     */         }  
/* 109 */       this.m_Channel.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void closeDirect() {
/* 115 */     if (this.m_Channel.isOpen()) {
/* 116 */       this.m_Channel.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPort() {
/*     */     try {
/* 123 */       connectChannel();
/*     */       
/* 125 */       if (this.m_Channel != null && this.m_Channel.getProtocolStack() != null && 
/* 126 */         this.m_Channel.getProtocolStack().getTopProtocol() instanceof TCP) {
/* 127 */         return ((TCP)this.m_Channel.getProtocolStack().getTopProtocol()).getBindPort();
/*     */       }
/* 129 */     } catch (Exception e) {
/*     */       
/* 131 */       ms_Logger.error(e, e);
/*     */     } 
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChannelId(String channelId) {
/* 141 */     this.m_ChannelId = channelId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChannelId() {
/* 149 */     return this.m_ChannelId;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\impl\JGroupsMessageChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */