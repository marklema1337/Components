/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.hazelcast.core.HazelcastInstance;
/*     */ import java.util.Enumeration;
/*     */ import org.apache.commons.collections4.iterators.IteratorEnumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class HCInnerHashtableCache<T, V>
/*     */ {
/*     */   private HazelcastInstance m_ObjectCache;
/*     */   private final String m_CacheName;
/*     */   
/*     */   public HCInnerHashtableCache(LbsHCManagerBase cacheManager, String name, String instanceName) {
/* 135 */     this.m_CacheName = instanceName;
/* 136 */     initCache(cacheManager, name, instanceName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initCache(LbsHCManagerBase cacheManager, String name, String instanceName) {
/* 141 */     this.m_ObjectCache = cacheManager.createCache(name, instanceName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int size() {
/* 146 */     if (this.m_ObjectCache != null)
/*     */     {
/* 148 */       return this.m_ObjectCache.getMap(this.m_CacheName).size();
/*     */     }
/* 150 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean containsKey(T key) {
/* 155 */     if (this.m_ObjectCache != null)
/* 156 */       return this.m_ObjectCache.getMap(this.m_CacheName).containsKey(key); 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean containsValue(V value) {
/* 162 */     if (this.m_ObjectCache != null)
/* 163 */       return this.m_ObjectCache.getMap(this.m_CacheName).containsValue(value); 
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected V get(T key) {
/* 170 */     if (this.m_ObjectCache != null) {
/*     */       
/* 172 */       Object o = this.m_ObjectCache.getMap(this.m_CacheName).get(key);
/* 173 */       if (o != null) {
/* 174 */         return (V)o;
/*     */       }
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected V put(T key, V object) {
/* 182 */     if (object == null)
/* 183 */       throw new RuntimeException("Trying to put null element into the cache with key " + key); 
/* 184 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 186 */       if (this.m_ObjectCache != null) {
/*     */         
/* 188 */         V val = get(key);
/* 189 */         this.m_ObjectCache.getMap(this.m_CacheName).put(key, object);
/* 190 */         return val;
/*     */       } 
/*     */     } 
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(T key) {
/* 198 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 200 */       if (this.m_ObjectCache != null) {
/* 201 */         this.m_ObjectCache.getMap(this.m_CacheName).remove(key);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void clear() {
/* 207 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 209 */       if (this.m_ObjectCache != null) {
/* 210 */         this.m_ObjectCache.getMap(this.m_CacheName).clear();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Enumeration<T> keys() {
/* 217 */     this.m_ObjectCache.getMap(this.m_CacheName).evict("LRU");
/* 218 */     return (Enumeration<T>)new IteratorEnumeration(this.m_ObjectCache.getMap(this.m_CacheName).keySet().iterator());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\HCInnerHashtableCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */