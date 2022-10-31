package com.lbs.platform.interfaces;

import java.util.Collection;

public interface ICachedList<T> {
  T get(int paramInt) throws IndexOutOfBoundsException;
  
  void add(T paramT);
  
  void addAll(Collection<? extends T> paramCollection);
  
  void clear();
  
  String getInstanceName();
  
  int size();
  
  void remove(int paramInt);
  
  void set(int paramInt, T paramT);
  
  Object[] toArray();
  
  boolean isEmpty();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ICachedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */