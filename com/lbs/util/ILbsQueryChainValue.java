package com.lbs.util;

import java.util.ArrayList;

public interface ILbsQueryChainValue {
  void addValue(Object paramObject);
  
  void addValues(Object paramObject);
  
  void addExcludedValue(Object paramObject);
  
  void addExcludedValues(Object paramObject);
  
  ArrayList getValues();
  
  ArrayList getExcludedValues();
  
  int size();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ILbsQueryChainValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */