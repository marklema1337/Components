package com.lbs.data.query;

import com.lbs.data.expression.QueryItem;

public interface IQueryRestrictionCommand extends IQueryCommand {
  void addToWhere(QueryItem paramQueryItem, boolean paramBoolean);
  
  void addToTables(QueryItem paramQueryItem, boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryRestrictionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */