package com.lbs.cache;

public interface ICacheListener<K, V> {
  V cacheMiss(K paramK, String paramString);
  
  boolean isValid(K paramK, String paramString, V paramV);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\ICacheListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */