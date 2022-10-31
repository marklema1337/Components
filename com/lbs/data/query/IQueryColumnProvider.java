package com.lbs.data.query;

import com.lbs.data.database.DBField;

public interface IQueryColumnProvider {
  int size();
  
  QueryColumn item(int paramInt);
  
  QueryColumn find(String paramString1, String paramString2);
  
  QueryColumn findByAlias(String paramString);
  
  QueryColumn findByFieldAlias(String paramString);
  
  QueryColumn find(DBField paramDBField);
  
  QueryColumn findByAlias(String paramString, DBField paramDBField);
  
  boolean contains(QueryColumn paramQueryColumn);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryColumnProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */