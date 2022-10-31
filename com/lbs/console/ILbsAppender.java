package com.lbs.console;

public interface ILbsAppender {
  String getName();
  
  void close();
  
  AppenderProps constructProps();
  
  void setDomainId(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\ILbsAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */