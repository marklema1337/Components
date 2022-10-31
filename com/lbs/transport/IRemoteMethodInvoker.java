package com.lbs.transport;

public interface IRemoteMethodInvoker {
  RemoteMethodResponse invoke(String paramString1, String paramString2, Object[] paramArrayOfObject, boolean[] paramArrayOfboolean, ClassLoader paramClassLoader, boolean paramBoolean) throws Exception;
  
  RemoteMethodResponse invoke(String paramString1, String paramString2, Object[] paramArrayOfObject, boolean[] paramArrayOfboolean, ClassLoader paramClassLoader) throws Exception;
  
  IRemoteSessionTimeoutHandler getTimeoutHandler();
  
  void setTimeoutHandler(IRemoteSessionTimeoutHandler paramIRemoteSessionTimeoutHandler);
  
  IRemoteSessionInvokeHandler getInvokeHandler();
  
  void setInvokeHandler(IRemoteSessionInvokeHandler paramIRemoteSessionInvokeHandler);
  
  ILbsServerEventListener getServerEventListener();
  
  void setServerEventListener(ILbsServerEventListener paramILbsServerEventListener);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\IRemoteMethodInvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */