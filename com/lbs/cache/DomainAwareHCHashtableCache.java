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
/*     */ class DomainAwareHCHashtableCache<T, V>
/*     */   extends HCInnerHashtableCache<T, V>
/*     */ {
/*     */   private Hashtable<Integer, HCInnerHashtableCache<T, V>> m_DomainCaches;
/*     */   private LbsHCManagerBase m_CacheManager;
/*     */   private String m_Name;
/*     */   private String m_InstanceName;
/*     */   
/*     */   public DomainAwareHCHashtableCache(LbsHCManagerBase cacheManager, String name, String instanceName) {
/* 231 */     super(cacheManager, name, instanceName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initCache(LbsHCManagerBase cacheManager, String name, String instanceName) {
/* 237 */     this.m_InstanceName = instanceName;
/* 238 */     this.m_Name = name;
/* 239 */     this.m_CacheManager = cacheManager;
/* 240 */     this.m_DomainCaches = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int size() {
/* 246 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 247 */     return cache.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 253 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 254 */     cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected V get(T key) {
/* 260 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 261 */     return cache.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean containsKey(T key) {
/* 267 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 268 */     return cache.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean containsValue(V value) {
/* 274 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 275 */     return cache.containsValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Enumeration<T> keys() {
/* 281 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 282 */     return cache.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected V put(T key, V object) {
/* 288 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 289 */     return cache.put(key, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void remove(T key) {
/* 295 */     HCInnerHashtableCache<T, V> cache = prepareCache();
/* 296 */     cache.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   private HCInnerHashtableCache<T, V> prepareCache() {
/* 301 */     int domainId = LbsDomainRegistryBase.getDomainId();
/* 302 */     HCInnerHashtableCache<T, V> cache = this.m_DomainCaches.get(Integer.valueOf(domainId));
/* 303 */     if (cache == null) {
/*     */       
/* 305 */       cache = new HCInnerHashtableCache<>(this.m_CacheManager, this.m_Name, String.valueOf(this.m_InstanceName) + "_" + domainId);
/* 306 */       this.m_DomainCaches.put(Integer.valueOf(domainId), cache);
/*     */     } 
/* 308 */     return cache;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\DomainAwareHCHashtableCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */