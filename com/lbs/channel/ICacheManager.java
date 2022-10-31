package com.lbs.channel;

import java.sql.Time;

public interface ICacheManager {
  boolean finalizeCacheManager();
  
  byte[] requestURIData(IChannel paramIChannel, String paramString, Object paramObject, byte[] paramArrayOfbyte) throws CacheManagerException;
  
  void cacheURIData(IChannel paramIChannel, String paramString, Time paramTime, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws CacheManagerException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ICacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */