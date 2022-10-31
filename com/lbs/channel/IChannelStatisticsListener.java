package com.lbs.channel;

import java.util.EventListener;

public interface IChannelStatisticsListener extends EventListener {
  void sendData(ChannelStatisticsEvent paramChannelStatisticsEvent);
  
  void receiveData(ChannelStatisticsEvent paramChannelStatisticsEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\IChannelStatisticsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */