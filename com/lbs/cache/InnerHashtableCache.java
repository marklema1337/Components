/*     */ package com.lbs.cache;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class InnerHashtableCache<T, V>
/*     */ {
/*     */   private Cache m_ObjectCache;
/*     */   
/*     */   public InnerHashtableCache(LbsCacheManagerBase cacheManager, String name, String instanceName) {
/* 177 */     initCache(cacheManager, name, instanceName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initCache(LbsCacheManagerBase cacheManager, String name, String instanceName) {
/* 182 */     this.m_ObjectCache = cacheManager.createCache(name, instanceName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int size() {
/* 187 */     if (this.m_ObjectCache != null) {
/*     */       
/* 189 */       this.m_ObjectCache.evictExpiredElements();
/* 190 */       return this.m_ObjectCache.getSize();
/*     */     } 
/* 192 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean containsKey(T key) {
/* 197 */     if (this.m_ObjectCache != null)
/* 198 */       return this.m_ObjectCache.isKeyInCache(key); 
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean containsValue(V value) {
/* 204 */     if (this.m_ObjectCache != null)
/* 205 */       return this.m_ObjectCache.isValueInCache(value); 
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected V get(T key) {
/* 212 */     if (this.m_ObjectCache != null) {
/*     */       
/* 214 */       Element e = this.m_ObjectCache.get(key);
/* 215 */       if (e != null) {
/* 216 */         return (V)e.getObjectValue();
/*     */       }
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected V put(T key, V object) {
/* 224 */     if (object == null)
/* 225 */       throw new RuntimeException("Trying to put null element into the cache with key " + key); 
/* 226 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 228 */       if (this.m_ObjectCache != null) {
/*     */         
/* 230 */         V val = get(key);
/* 231 */         this.m_ObjectCache.put(new Element(key, object));
/* 232 */         return val;
/*     */       } 
/*     */     } 
/* 235 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(T key) {
/* 240 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 242 */       if (this.m_ObjectCache != null) {
/* 243 */         this.m_ObjectCache.remove(key);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void clear() {
/* 249 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 251 */       if (this.m_ObjectCache != null) {
/* 252 */         this.m_ObjectCache.removeAll();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Enumeration<T> keys() {
/* 259 */     this.m_ObjectCache.evictExpiredElements();
/* 260 */     List<T> keys = this.m_ObjectCache.getKeysWithExpiryCheck();
/* 261 */     return Collections.enumeration(keys);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\InnerHashtableCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */