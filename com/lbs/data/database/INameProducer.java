package com.lbs.data.database;

import com.lbs.data.factory.INamedVariables;
import com.lbs.data.factory.ISubstitutionListener;

public interface INameProducer {
  String getPhysicalName(String paramString, INamedVariables paramINamedVariables, int paramInt);
  
  String getPhysicalName(DBEntity paramDBEntity, INamedVariables paramINamedVariables, ISubstitutionListener paramISubstitutionListener, Object paramObject);
  
  String processString(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\INameProducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */