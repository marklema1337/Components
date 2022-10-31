package com.lbs.platform.interfaces;

public interface IMessageChannel {
  void send(IMessage paramIMessage);
  
  void setReceiver(IMessageReceiver paramIMessageReceiver);
  
  void closeDirect();
  
  void close(String paramString, int paramInt);
  
  int getPort();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IMessageChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */