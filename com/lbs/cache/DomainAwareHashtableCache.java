/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.data.application.LbsDomainRegistryBase;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DomainAwareHashtableCache<T, V>
/*     */   extends InnerHashtableCache<T, V>
/*     */ {
/*     */   private Hashtable<Integer, InnerHashtableCache<T, V>> m_DomainCaches;
/*     */   private LbsCacheManagerBase m_CacheManager;
/*     */   private String m_Name;
/*     */   private String m_InstanceName;
/*     */   
/*     */   public DomainAwareHashtableCache(LbsCacheManagerBase cacheManager, String name, String instanceName) {
/* 274 */     super(cacheManager, name, instanceName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initCache(LbsCacheManagerBase cacheManager, String name, String instanceName) {
/* 280 */     this.m_InstanceName = instanceName;
/* 281 */     this.m_Name = name;
/* 282 */     this.m_CacheManager = cacheManager;
/* 283 */     this.m_DomainCaches = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int size() {
/* 289 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 290 */     return cache.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 296 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 297 */     cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected V get(T key) {
/* 303 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 304 */     return cache.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean containsKey(T key) {
/* 310 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 311 */     return cache.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean containsValue(V value) {
/* 317 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 318 */     return cache.containsValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Enumeration<T> keys() {
/* 324 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 325 */     return cache.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected V put(T key, V object) {
/* 331 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 332 */     return cache.put(key, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void remove(T key) {
/* 338 */     InnerHashtableCache<T, V> cache = prepareCache();
/* 339 */     cache.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   private InnerHashtableCache<T, V> prepareCache() {
/* 344 */     int domainId = LbsDomainRegistryBase.getDomainId();
/* 345 */     InnerHashtableCache<T, V> cache = this.m_DomainCaches.get(Integer.valueOf(domainId));
/* 346 */     if (cache == null) {
/*     */       
/* 348 */       cache = new InnerHashtableCache<>(this.m_CacheManager, this.m_Name, String.valueOf(this.m_InstanceName) + "_" + domainId);
/* 349 */       this.m_DomainCaches.put(Integer.valueOf(domainId), cache);
/*     */     } 
/* 351 */     return cache;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\DomainAwareHashtableCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */