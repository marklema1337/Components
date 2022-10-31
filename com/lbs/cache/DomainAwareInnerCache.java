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
/*     */ class DomainAwareInnerCache<T>
/*     */   extends InnerListCache<T>
/*     */ {
/*     */   private Hashtable<Integer, InnerListCache<T>> m_DomainCaches;
/*     */   private LbsCacheManagerBase m_CacheManager;
/*     */   private String m_Name;
/*     */   private String m_InstanceName;
/*     */   
/*     */   public DomainAwareInnerCache(LbsCacheManagerBase cacheManager, String name, String instanceName, ICacheItemIdentifierProvider<T> itemIdentifierProvider) {
/* 189 */     super(cacheManager, name, instanceName, itemIdentifierProvider);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initCache(LbsCacheManagerBase cacheManager, String name, String instanceName) {
/* 195 */     this.m_InstanceName = instanceName;
/* 196 */     this.m_Name = name;
/* 197 */     this.m_CacheManager = cacheManager;
/* 198 */     this.m_DomainCaches = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean contains(T object) {
/* 204 */     InnerListCache<T> cache = prepareCache();
/* 205 */     return cache.contains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int indexOf(T object) {
/* 211 */     InnerListCache<T> cache = prepareCache();
/* 212 */     return cache.indexOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEmpty() {
/* 218 */     InnerListCache<T> cache = prepareCache();
/* 219 */     return cache.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void remove(T object) {
/* 225 */     InnerListCache<T> cache = prepareCache();
/* 226 */     cache.remove(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(int index, T object) {
/* 232 */     InnerListCache<T> cache = prepareCache();
/* 233 */     cache.set(index, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] toArray() {
/* 239 */     InnerListCache<T> cache = prepareCache();
/* 240 */     return cache.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void remove(int index) {
/* 246 */     InnerListCache<T> cache = prepareCache();
/* 247 */     cache.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void add(T object) {
/* 253 */     InnerListCache<T> cache = prepareCache();
/* 254 */     cache.add(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(Collection<? extends T> o) {
/* 260 */     if (o != null) {
/*     */       
/* 262 */       InnerListCache<T> cache = prepareCache();
/* 263 */       for (Iterator<? extends T> iterator = o.iterator(); iterator.hasNext();)
/*     */       {
/* 265 */         cache.add(iterator.next());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 273 */     InnerListCache<T> cache = prepareCache();
/* 274 */     cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected T get(int index) {
/* 280 */     InnerListCache<T> cache = prepareCache();
/* 281 */     return cache.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int size() {
/* 287 */     InnerListCache<T> cache = prepareCache();
/* 288 */     return cache.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private InnerListCache<T> prepareCache() {
/* 293 */     int domainId = LbsDomainRegistryBase.getDomainId();
/* 294 */     InnerListCache<T> cache = this.m_DomainCaches.get(Integer.valueOf(domainId));
/* 295 */     if (cache == null) {
/*     */       
/* 297 */       cache = new InnerListCache<>(this.m_CacheManager, this.m_Name, String.valueOf(this.m_InstanceName) + "_" + domainId, this.m_ItemIdentifierProvider);
/* 298 */       this.m_DomainCaches.put(Integer.valueOf(domainId), cache);
/*     */     } 
/* 300 */     return cache;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\DomainAwareInnerCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */