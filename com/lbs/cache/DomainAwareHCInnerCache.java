/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.data.application.LbsDomainRegistryBase;
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import java.util.Collection;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DomainAwareHCInnerCache<T>
/*     */   extends HCInnerListCache<T>
/*     */ {
/*     */   private Hashtable<Integer, HCInnerListCache<T>> m_DomainCaches;
/*     */   private LbsHCManagerBase m_CacheManager;
/*     */   private String m_Name;
/*     */   private String m_InstanceName;
/*     */   
/*     */   public DomainAwareHCInnerCache(LbsHCManagerBase cacheManager, String name, String instanceName, ICacheItemIdentifierProvider<T> itemIdentifierProvider) {
/* 331 */     super(cacheManager, name, instanceName, itemIdentifierProvider);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initCache(LbsHCManagerBase cacheManager, String name, String instanceName) {
/* 337 */     this.m_InstanceName = instanceName;
/* 338 */     this.m_Name = name;
/* 339 */     this.m_CacheManager = cacheManager;
/* 340 */     this.m_DomainCaches = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean contains(T object) {
/* 346 */     HCInnerListCache<T> cache = prepareCache();
/* 347 */     return cache.contains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int indexOf(T object) {
/* 353 */     HCInnerListCache<T> cache = prepareCache();
/* 354 */     return cache.indexOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEmpty() {
/* 360 */     HCInnerListCache<T> cache = prepareCache();
/* 361 */     return cache.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void remove(T object) {
/* 367 */     HCInnerListCache<T> cache = prepareCache();
/* 368 */     cache.remove(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(int index, T object) {
/* 374 */     HCInnerListCache<T> cache = prepareCache();
/* 375 */     cache.set(index, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] toArray() {
/* 381 */     HCInnerListCache<T> cache = prepareCache();
/* 382 */     return cache.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void remove(int index) {
/* 388 */     HCInnerListCache<T> cache = prepareCache();
/* 389 */     cache.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void add(T object) {
/* 395 */     HCInnerListCache<T> cache = prepareCache();
/* 396 */     cache.add(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(Collection<? extends T> o) {
/* 402 */     if (o != null) {
/*     */       
/* 404 */       HCInnerListCache<T> cache = prepareCache();
/* 405 */       for (Iterator<? extends T> iterator = o.iterator(); iterator.hasNext();)
/*     */       {
/* 407 */         cache.add(iterator.next());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 415 */     HCInnerListCache<T> cache = prepareCache();
/* 416 */     cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected T get(int index) {
/* 422 */     HCInnerListCache<T> cache = prepareCache();
/* 423 */     return cache.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int size() {
/* 429 */     HCInnerListCache<T> cache = prepareCache();
/* 430 */     return cache.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private HCInnerListCache<T> prepareCache() {
/* 435 */     int domainId = LbsDomainRegistryBase.getDomainId();
/* 436 */     HCInnerListCache<T> cache = this.m_DomainCaches.get(Integer.valueOf(domainId));
/* 437 */     if (cache == null) {
/*     */       
/* 439 */       cache = new HCInnerListCache<>(this.m_CacheManager, this.m_Name, String.valueOf(this.m_InstanceName) + "_" + domainId, this.m_ItemIdentifierProvider);
/* 440 */       this.m_DomainCaches.put(Integer.valueOf(domainId), cache);
/*     */     } 
/* 442 */     return cache;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\DomainAwareHCInnerCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */