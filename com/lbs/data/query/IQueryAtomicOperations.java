package com.lbs.data.query;

import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface IQueryAtomicOperations {
  QueryResult executeServiceQuery(String paramString, QueryParams paramQueryParams) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  QueryResult[] executeServiceBatch(String[] paramArrayOfString, QueryParams[] paramArrayOfQueryParams) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryAtomicOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */