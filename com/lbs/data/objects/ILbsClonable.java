package com.lbs.data.objects;

public interface ILbsClonable extends Cloneable {
  Object clone() throws CloneNotSupportedException;
  
  void assignFrom(Object paramObject);
  
  Object getPropertyByName(String paramString);
  
  boolean setPropertyByName(String paramString, Object paramObject);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ILbsClonable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */