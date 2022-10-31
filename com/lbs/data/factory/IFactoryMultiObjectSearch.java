package com.lbs.data.factory;

import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.data.objects.BasicBusinessObjects;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IFactoryMultiObjectSearch {
  boolean search(BasicBusinessObjects paramBasicBusinessObjects, String paramString, FactoryParams paramFactoryParams, int paramInt1, int paramInt2, boolean paramBoolean) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean search(BasicBusinessObjects paramBasicBusinessObjects, BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams, int paramInt1, int paramInt2, boolean paramBoolean) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactoryMultiObjectSearch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */