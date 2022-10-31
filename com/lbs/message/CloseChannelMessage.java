/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.platform.interfaces.IMessage;
/*    */ import com.lbs.platform.interfaces.MessageType;
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
/*    */ public class CloseChannelMessage
/*    */   implements IMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_DestIp;
/*    */   private int m_DestPort;
/*    */   
/*    */   public CloseChannelMessage(String clientIp, int clientPort) {
/* 24 */     this.m_DestIp = clientIp;
/* 25 */     this.m_DestPort = clientPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 30 */     return "Channel closed!";
/*    */   }
/*    */ 
/*    */   
/*    */   public MessageType getMessageType() {
/* 35 */     return MessageType.CHANNEL_CLOSE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDestIp() {
/* 41 */     return this.m_DestIp;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDestPort() {
/* 46 */     return this.m_DestPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDestIp(String destIp) {
/* 51 */     this.m_DestIp = destIp;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDestPort(int destPort) {
/* 56 */     this.m_DestPort = destPort;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\CloseChannelMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */