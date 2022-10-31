package com.lbs.interfaces;

import java.util.ArrayList;

public interface ILbsXUIComparable<T> {
  boolean isSameDefinition(T paramT);
  
  void calculateDelta(T paramT, ArrayList<String> paramArrayList, String paramString);
  
  String getDeltaIdentifier(String paramString);
  
  T getThis();
  
  boolean isEmpty();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\interfaces\ILbsXUIComparable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */