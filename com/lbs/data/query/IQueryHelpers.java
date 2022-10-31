package com.lbs.data.query;

import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IQueryHelpers {
  QuerySchema getQuerySchema(String paramString1, String paramString2) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  int getRowCount(String paramString, QueryParams paramQueryParams) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  QueryGridSchema getQueryGridSchema(String paramString1, String paramString2) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryHelpers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */