package com.lbs.platform.interfaces;

public interface ICachedIdentifiableList<T> extends ICachedList<T> {
  boolean contains(T paramT);
  
  int indexOf(T paramT);
  
  void remove(T paramT);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ICachedIdentifiableList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */