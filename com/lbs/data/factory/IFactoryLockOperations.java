package com.lbs.data.factory;

import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IFactoryLockOperations {
  boolean isLocked(FactoryParams paramFactoryParams, String paramString1, String paramString2) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  String acquireLock(FactoryParams paramFactoryParams, String paramString) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  void releaseLock(FactoryParams paramFactoryParams, String paramString1, String paramString2) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactoryLockOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */