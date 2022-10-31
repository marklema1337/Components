package com.lbs.channel;

import com.lbs.transport.ISessionBase;
import com.lbs.transport.LoginException;
import com.lbs.transport.UserInfo;

public interface ILoginProvider {
  ISessionBase login(IChannelProvider paramIChannelProvider, String paramString, UserInfo paramUserInfo, ClassLoader paramClassLoader) throws LoginException;
  
  boolean logout(IChannelProvider paramIChannelProvider, String paramString, ISessionBase paramISessionBase) throws LoginException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ILoginProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */