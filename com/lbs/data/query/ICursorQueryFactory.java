package com.lbs.data.query;

import com.lbs.data.database.IDBCommandExecutor;
import com.lbs.invoke.SessionReestablishedException;
import com.lbs.invoke.SessionTimeoutException;

public interface ICursorQueryFactory extends IQueryFactory {
  boolean first(long paramLong, int paramInt1, String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt2) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean next(long paramLong, int paramInt1, String paramString, QueryBusinessObjects paramQueryBusinessObjects, int paramInt2) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean first(long paramLong, String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean next(long paramLong, String paramString, QueryBusinessObjects paramQueryBusinessObjects, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean nativeFirst(long paramLong, String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean nativeFirst(long paramLong, String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt, IDBCommandExecutor paramIDBCommandExecutor) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean nativeNext(long paramLong, QueryBusinessObjects paramQueryBusinessObjects, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  void closeCursors(long paramLong);
  
  void closeCursor(long paramLong, int paramInt, String paramString);
  
  boolean firstToScroll(long paramLong, String paramString, QueryParams paramQueryParams, QueryBusinessObjects paramQueryBusinessObjects, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
  
  boolean scroll(long paramLong, String paramString, QueryBusinessObjects paramQueryBusinessObjects, int paramInt1, int paramInt2) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\ICursorQueryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */