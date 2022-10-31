package com.lbs.platform.interfaces;

public interface ICacheManager {
  public static final String CACHE_CONFIG_FILE = "CacheConfig.ccf";
  
  public static final String CACHE_CONFIG_FILE_CLIENT = "ClientCacheConfig.ccf";
  
  public static final String PROP_DISK_PATH = "jcs.auxiliary.DC.attributes.DiskPath";
  
  public static final String INSTANCE_EXTENSION_DATA = ".data";
  
  public static final String INSTANCE_EXTENSION_KEY = ".key";
  
  public static final String INSTANCE_NAME_SEPERATOR = "_-_";
  
  public static final String INSTANCE_LIST_IDENTIFIER = "~L~";
  
  public static final String INSTANCE_HASH_IDENTIFIER = "~H~";
  
  public static final String INSTANCE_LIST_SEPERATOR = "~L~_-_";
  
  public static final String INSTANCE_HASH_SEPERATOR = "~H~_-_";
  
  public static final String NULL_SESSION = "NL_SES";
  
  <T, V> ICachedHashTable<T, V> getCachedHashTable(String paramString, Class<T> paramClass, Class<V> paramClass1, boolean paramBoolean);
  
  <T> ICachedList<T> getCachedList(String paramString, Class<T> paramClass, boolean paramBoolean);
  
  <T> ICachedIdentifiableList<T> getCachedList(String paramString, Class<T> paramClass, ICacheItemIdentifierProvider<T> paramICacheItemIdentifierProvider, boolean paramBoolean);
  
  void releaseCachedHashTable(ICachedHashTable<?, ?> paramICachedHashTable);
  
  void releaseCachedList(ICachedList<?> paramICachedList);
  
  Object getEHCacheManager();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\ICacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */