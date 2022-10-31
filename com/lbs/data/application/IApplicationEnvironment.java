package com.lbs.data.application;

import com.lbs.data.factory.IObjectFactory;
import com.lbs.data.factory.NamedVariables;
import java.io.Serializable;

public interface IApplicationEnvironment extends Serializable {
  ApplicationVariables getDefinitions(IObjectFactory paramIObjectFactory);
  
  NamedVariables getValues();
  
  int getIntVariable(String paramString);
  
  String getStringVariable(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\application\IApplicationEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */