package com.lbs.channel;

public interface IChannelListener {
  void channelBeforeDataSend(IChannel paramIChannel);
  
  boolean channelDataRead(IChannel paramIChannel, byte[] paramArrayOfbyte);
  
  void channelClosed(IChannel paramIChannel);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\IChannelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */