/*    */ package com.lbs.message.impl;
/*    */ 
/*    */ import com.lbs.platform.interfaces.IMessage;
/*    */ import com.lbs.platform.interfaces.IMessageReceiver;
/*    */ import com.lbs.platform.interfaces.MessageType;
/*    */ import org.jgroups.JChannel;
/*    */ import org.jgroups.Message;
/*    */ import org.jgroups.ReceiverAdapter;
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
/*    */ public class JGroupsMessageReceiver
/*    */   extends ReceiverAdapter
/*    */ {
/*    */   private IMessageReceiver m_Receiver;
/*    */   private JChannel m_Channel;
/*    */   
/*    */   public JGroupsMessageReceiver(JChannel channel, IMessageReceiver receiver) {
/* 28 */     this.m_Channel = channel;
/* 29 */     this.m_Receiver = receiver;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void receive(Message msg) {
/* 35 */     Object data = msg.getObject();
/* 36 */     if (data instanceof IMessage && this.m_Receiver != null)
/*    */     {
/* 38 */       if (((IMessage)data).getMessageType() == MessageType.CHANNEL_CLOSE && this.m_Channel.isOpen()) {
/* 39 */         this.m_Channel.close();
/*    */       } else {
/* 41 */         this.m_Receiver.receive((IMessage)data);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\impl\JGroupsMessageReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */