package com.lbs.cache;

public interface ICacheImplFactory {
  <K, V> ICacheImpl<K, V> createCacheImpl(String paramString, Class<K> paramClass, Class<V> paramClass1);
  
  String getCacheIndexFileSuffix();
  
  String getDiskStorePath();
  
  void shutdown();
  
  <K, V> void closeCache(ICacheImpl<K, V> paramICacheImpl);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\ICacheImplFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */