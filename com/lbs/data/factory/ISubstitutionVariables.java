package com.lbs.data.factory;

public interface ISubstitutionVariables extends INamedValues, Cloneable {
  void put(String paramString, Object paramObject);
  
  void clear();
  
  Object clone() throws CloneNotSupportedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ISubstitutionVariables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */