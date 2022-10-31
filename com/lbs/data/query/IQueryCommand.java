package com.lbs.data.query;

import com.lbs.data.database.DBCommandException;
import com.lbs.data.database.DBQueryParameters;
import com.lbs.data.expression.IQueryItemGenerator;
import java.util.ArrayList;

public interface IQueryCommand {
  DBQueryParameters getParameters();
  
  ArrayList getParamValues();
  
  boolean isPrepared();
  
  String getCommandText(IQueryItemGenerator paramIQueryItemGenerator, Object paramObject) throws DBCommandException;
  
  void prepare(IQueryItemGenerator paramIQueryItemGenerator, Object paramObject) throws DBCommandException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\IQueryCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */