package com.lbs.channel;

public interface IChannelProvider {
  boolean initializeProvider();
  
  boolean finalizeProvider();
  
  IChannel createChannel();
  
  void closeChannel(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\IChannelProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */