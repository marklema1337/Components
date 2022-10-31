package com.lbs.data.expression;

import com.lbs.data.database.DBCommandException;

public interface IQueryItemGenerator {
  String generate(IQueryItemGenerator paramIQueryItemGenerator, QueryItem paramQueryItem, QueryItemParams paramQueryItemParams) throws DBCommandException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\IQueryItemGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */