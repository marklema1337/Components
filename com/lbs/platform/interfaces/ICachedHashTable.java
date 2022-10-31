package com.lbs.platform.interfaces;

import java.util.Enumeration;

public interface ICachedHashTable<T, V> {
  V get(T paramT);
  
  V put(T paramT, V paramV);
  
  void remove(T paramT);
  
  void clear();
  
  Enumeration<T> keys();
  
  String getInstanceName();
  
  boolean containsKey(T paramT);
  
  boolean containsValue(V paramV);
  
  int size();
  
  boolean isEmpty();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ICachedHashTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */