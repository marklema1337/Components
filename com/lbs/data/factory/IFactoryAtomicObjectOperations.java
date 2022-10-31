package com.lbs.data.factory;

import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IFactoryAtomicObjectOperations {
  boolean insert(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean update(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean delete(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean delete(String paramString, int paramInt, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean persist(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean persist(BasicBusinessObject[] paramArrayOfBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactoryAtomicObjectOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */