package com.lbs.data.factory;

import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.data.objects.ObjectPropertyList;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IFactorySingleObjectSearch {
  boolean read(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams, int paramInt) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean readByUUID(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams, String paramString) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean search(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams, int paramInt) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  int searchPrimaryKeyByUUID(String paramString1, FactoryParams paramFactoryParams, String paramString2) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  int searchPrimaryKey(String paramString, ObjectPropertyList paramObjectPropertyList, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean searchByProps(BasicBusinessObject paramBasicBusinessObject, ObjectPropertyList paramObjectPropertyList, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean readBySecondaryKey(BasicBusinessObject paramBasicBusinessObject, FactoryParams paramFactoryParams) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IFactorySingleObjectSearch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */