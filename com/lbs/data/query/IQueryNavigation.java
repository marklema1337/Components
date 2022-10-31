package com.lbs.data.query;

import com.lbs.data.objects.ObjectPropertyList;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IQueryNavigation {
  boolean first(String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt, boolean paramBoolean) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean last(String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt, boolean paramBoolean) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean next(String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt, Object paramObject, boolean paramBoolean) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean previous(String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt, Object paramObject, boolean paramBoolean) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean search(String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt1, Object paramObject, int paramInt2, boolean paramBoolean) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean select(String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  int searchPrimaryKey(String paramString, QueryParams paramQueryParams, ObjectPropertyList paramObjectPropertyList) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryNavigation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */