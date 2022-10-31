package com.lbs.platform.interfaces;

import java.io.Serializable;

public interface IMessage extends Serializable {
  String getDestIp();
  
  void setDestIp(String paramString);
  
  int getDestPort();
  
  void setDestPort(int paramInt);
  
  String getMessage();
  
  MessageType getMessageType();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */