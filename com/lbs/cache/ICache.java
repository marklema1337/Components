package com.lbs.cache;

public interface ICache<K, V> {
  V get(K paramK);
  
  V get(K paramK, String paramString);
  
  void put(K paramK, V paramV);
  
  void put(K paramK, V paramV, String paramString);
  
  void remove(K paramK);
  
  void remove(K paramK, String paramString);
  
  void clear();
  
  String getUniqueName();
  
  String getDescription();
  
  JLbsCacheScope getScope();
  
  JLbsCache.JLbsCachePolicy getCachePolicy();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\ICache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */