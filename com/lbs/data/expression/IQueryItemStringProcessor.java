package com.lbs.data.expression;

import com.lbs.data.database.DBQueryParameter;

public interface IQueryItemStringProcessor {
  boolean shouldCheckForEmptyStringParameters(IQueryItemGenerator paramIQueryItemGenerator, QueryItem paramQueryItem, QueryItemParams paramQueryItemParams, DBQueryParameter paramDBQueryParameter);
  
  Object getEmptyStringParameterValue(IQueryItemGenerator paramIQueryItemGenerator, QueryItem paramQueryItem, QueryItemParams paramQueryItemParams, Object paramObject);
  
  Object processStringParameterValue(DBQueryParameter paramDBQueryParameter, Object paramObject);
  
  String getParameterSqlName(DBQueryParameter paramDBQueryParameter, Object paramObject, String paramString, boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\IQueryItemStringProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */