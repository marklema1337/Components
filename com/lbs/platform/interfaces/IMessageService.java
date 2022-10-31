package com.lbs.platform.interfaces;

public interface IMessageService {
  IMessageChannel getChannel(String paramString);
  
  IMessageChannel getChannel(String paramString1, String paramString2);
  
  void prepareForClose();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IMessageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */