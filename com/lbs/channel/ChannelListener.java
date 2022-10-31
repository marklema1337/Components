/*    */ package com.lbs.channel;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChannelListener
/*    */   implements IChannelListener
/*    */ {
/*    */   public void channelBeforeDataSend(IChannel channel) {}
/*    */   
/*    */   public void channelClosed(IChannel channel) {}
/*    */   
/*    */   public boolean channelDataRead(IChannel channel, byte[] data) {
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ChannelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */