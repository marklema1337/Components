package com.lbs.data.factory;

import java.io.Serializable;

public interface INamedVariables extends Serializable {
  Object getObject(String paramString);
  
  boolean setObject(String paramString, Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\INamedVariables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */