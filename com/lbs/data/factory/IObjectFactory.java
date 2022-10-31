package com.lbs.data.factory;

import com.lbs.data.application.ApplicationVariables;
import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IObjectFactory extends IFactorySearchConstants, IFactorySingleObjectNavigation, IFactoryMultiObjectNavigation, IFactoryMultiObjectSearch, IFactorySingleObjectSearch, IFactoryAtomicObjectOperations, IFactoryLockOperations, IFactoryEvents, IFactoryDynamicLoading {
  ApplicationVariables getApplicationVariables();
  
  FactoryParams prepareParams(FactoryParams paramFactoryParams);
  
  String[] getObjectExtensions(String paramString1, String paramString2) throws ObjectFactoryException, SessionTimeoutException;
  
  boolean hasReferences(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  String getSecondaryKeyData(String paramString, Integer paramInteger) throws Exception;
  
  String getTableName(BasicBusinessObject paramBasicBusinessObject) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  String getObjectClassName(String paramString) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean initializeObjectOnServer(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */