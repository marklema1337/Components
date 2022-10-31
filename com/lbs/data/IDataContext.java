package com.lbs.data;

import com.lbs.data.factory.IObjectFactory;
import com.lbs.data.query.IQueryFactory;

public interface IDataContext {
  IObjectFactory getObjectFactory();
  
  IQueryFactory getQueryFactory();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\IDataContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */