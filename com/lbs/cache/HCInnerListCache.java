/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.hazelcast.core.HazelcastInstance;
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import java.util.Collection;
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
/*     */ class HCInnerListCache<T>
/*     */ {
/*     */   protected ICacheItemIdentifierProvider<T> m_ItemIdentifierProvider;
/*     */   private HazelcastInstance m_ObjectCache;
/*     */   private String m_CacheName;
/*     */   
/*     */   public HCInnerListCache(LbsHCManagerBase cacheManager, String name, String instanceName, ICacheItemIdentifierProvider<T> itemIdentifierProvider) {
/* 183 */     initCache(cacheManager, name, instanceName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initCache(LbsHCManagerBase cacheManager, String name, String instanceName) {
/* 188 */     this.m_ObjectCache = cacheManager.createCache(name, instanceName);
/* 189 */     this.m_CacheName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean contains(T object) {
/* 194 */     if (object != null && this.m_ObjectCache.getList(this.m_CacheName) != null)
/* 195 */       return this.m_ObjectCache.getList(this.m_CacheName).contains(object); 
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int indexOf(T object) {
/* 201 */     if (object != null)
/*     */     {
/* 203 */       return this.m_ObjectCache.getList(this.m_CacheName).indexOf(object);
/*     */     }
/* 205 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void set(int index, T object) {
/* 210 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 212 */       if (this.m_ObjectCache != null) {
/*     */         
/* 214 */         RangeCheck(index);
/* 215 */         this.m_ObjectCache.getList(this.m_CacheName).set(index, object);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(T object) {
/* 222 */     if (object == null)
/*     */       return; 
/* 224 */     int index = indexOf(object);
/* 225 */     remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object[] toArray() {
/* 230 */     Object[] arr = new Object[size()];
/* 231 */     for (int i = 0; i < arr.length; i++)
/*     */     {
/* 233 */       arr[i] = get(i);
/*     */     }
/* 235 */     return arr;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty() {
/* 240 */     return this.m_ObjectCache.getList(this.m_CacheName).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(int index) {
/* 245 */     RangeCheck(index);
/* 246 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 248 */       if (this.m_ObjectCache != null)
/*     */       {
/* 250 */         this.m_ObjectCache.getList(this.m_CacheName).remove(index);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(T object) {
/* 257 */     if (object == null)
/* 258 */       throw new RuntimeException("Trying to put null element into the cache."); 
/* 259 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 261 */       if (this.m_ObjectCache != null)
/*     */       {
/* 263 */         this.m_ObjectCache.getList(this.m_CacheName).add(object);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAll(Collection<? extends T> o) {
/* 270 */     if (o != null)
/*     */     {
/* 272 */       for (Iterator<? extends T> iterator = o.iterator(); iterator.hasNext();)
/*     */       {
/* 274 */         add(iterator.next());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected T get(int index) {
/*     */     try {
/* 284 */       RangeCheck(index);
/*     */     }
/* 286 */     catch (IndexOutOfBoundsException e) {
/*     */       
/* 288 */       throw e;
/*     */     } 
/*     */     
/* 291 */     if (this.m_ObjectCache != null)
/*     */     {
/* 293 */       return (T)this.m_ObjectCache.getList(this.m_CacheName).get(index);
/*     */     }
/*     */     
/* 296 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 301 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 303 */       if (this.m_ObjectCache != null) {
/* 304 */         this.m_ObjectCache.getList(this.m_CacheName).clear();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int size() {
/* 310 */     return this.m_ObjectCache.getList(this.m_CacheName).size();
/*     */   }
/*     */ 
/*     */   
/*     */   private void RangeCheck(int index) throws IndexOutOfBoundsException {
/* 315 */     if (index >= size() || index < 0)
/* 316 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\HCInnerListCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */