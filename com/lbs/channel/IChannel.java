package com.lbs.channel;

import com.lbs.transport.ChannelSendException;
import java.io.FileNotFoundException;
import java.security.AccessControlException;
import java.util.Date;

public interface IChannel {
  boolean open(String paramString, Object paramObject);
  
  boolean close(boolean paramBoolean);
  
  boolean setUseCache(boolean paramBoolean);
  
  boolean setIfModifiedSince(Date paramDate);
  
  int sendData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) throws ChannelSendException;
  
  int getData() throws FileNotFoundException, AccessControlException;
  
  String getChannelCode();
  
  void setListener(IChannelListener paramIChannelListener);
  
  void setChannelReadListener(IChannelDataReader paramIChannelDataReader);
  
  void setSpringBoot(boolean paramBoolean);
  
  void clearPostParams();
  
  void setPostParam(String paramString1, String paramString2);
  
  String getReturnParam(String paramString);
  
  void setSessionCode(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\IChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */