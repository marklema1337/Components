package com.lbs.cache;

import java.util.Set;

public interface ICacheImpl<K, V> {
  void put(K paramK, V paramV);
  
  V get(K paramK);
  
  void remove(K paramK);
  
  void clear();
  
  void flush();
  
  int size();
  
  Set<K> keySet(int paramInt);
  
  String getCacheName();
  
  String getCacheIndexSuffix();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\ICacheImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */