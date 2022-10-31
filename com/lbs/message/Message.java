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
/*    */ 
/*    */ public class Message
/*    */   implements IMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_DestIp;
/*    */   protected int m_DestPort;
/*    */   protected String m_Message;
/*    */   protected MessageType m_Type;
/*    */   
/*    */   public Message(String message, MessageType type) {
/* 27 */     this.m_Message = message;
/* 28 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Message() {}
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 37 */     return this.m_Message;
/*    */   }
/*    */ 
/*    */   
/*    */   public MessageType getMessageType() {
/* 42 */     return this.m_Type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(String message) {
/* 47 */     this.m_Message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(MessageType type) {
/* 52 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDestIp(String destIp) {
/* 60 */     this.m_DestIp = destIp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDestIp() {
/* 68 */     return this.m_DestIp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDestPort(int destPort) {
/* 76 */     this.m_DestPort = destPort;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDestPort() {
/* 84 */     return this.m_DestPort;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */