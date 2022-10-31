package com.lbs.transport;

import com.lbs.invoke.SessionReestablishedException;

public interface IRemoteSessionTimeoutHandler {
  boolean remoteSessionTimeout(Exception paramException) throws SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\IRemoteSessionTimeoutHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */