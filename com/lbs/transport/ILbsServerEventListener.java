package com.lbs.transport;

public interface ILbsServerEventListener {
  public static final int EXCHANGE_RATES = 1;
  
  public static final int CALCULATOR = 2;
  
  public static final int LICENSE_RENEWAL = 3;
  
  void onServerEvent(LbsServerEventList paramLbsServerEventList, LbsServerEvent paramLbsServerEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\ILbsServerEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */