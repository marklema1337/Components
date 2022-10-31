package com.lbs.data.factory;

import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.data.objects.BasicBusinessObjects;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IFactoryMultiObjectNavigation {
  boolean first(BasicBusinessObjects paramBasicBusinessObjects, String paramString, FactoryParams paramFactoryParams, int paramInt, boolean paramBoolean) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean last(BasicBusinessObjects paramBasicBusinessObjects, String paramString, FactoryParams paramFactoryParams, int paramInt, boolean paramBoolean) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean next(BasicBusinessObjects paramBasicBusinessObjects, BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams, int paramInt, boolean paramBoolean) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean previous(BasicBusinessObjects paramBasicBusinessObjects, BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams, int paramInt, boolean paramBoolean) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactoryMultiObjectNavigation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */