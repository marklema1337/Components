package com.lbs.data.factory;

import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IFactorySingleObjectNavigation {
  boolean first(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean last(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean next(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean previous(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactorySingleObjectNavigation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */