/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
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
/*     */ public class InnerListCache<T>
/*     */ {
/*     */   protected ICacheItemIdentifierProvider<T> m_ItemIdentifierProvider;
/*     */   private Cache m_ObjectCache;
/*  28 */   private final List<Integer> m_CacheKeys = new ArrayList<>();
/*     */   
/*  30 */   private String cacheKeyPrefix = "";
/*     */   
/*     */   private String cacheName;
/*  33 */   private static final Map<String, Integer> references = new ConcurrentHashMap<>();
/*     */   
/*     */   private boolean referencing;
/*     */ 
/*     */   
/*     */   public InnerListCache(LbsCacheManagerBase cacheManager, String name, String instanceName, ICacheItemIdentifierProvider<T> itemIdentifierProvider) {
/*  39 */     initCache(cacheManager, name, instanceName);
/*  40 */     this.m_ItemIdentifierProvider = itemIdentifierProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initCache(LbsCacheManagerBase cacheManager, String name, String instanceName) {
/*  45 */     this.m_ObjectCache = cacheManager.createCache(name, instanceName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean contains(T object) {
/*  50 */     if (object != null && this.m_ItemIdentifierProvider != null)
/*  51 */       return this.m_CacheKeys.contains(Integer.valueOf(this.m_ItemIdentifierProvider.getItemUniqueIdentifier(object))); 
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int indexOf(T object) {
/*  57 */     if (object != null)
/*     */     {
/*  59 */       return this.m_CacheKeys.indexOf(Integer.valueOf(this.m_ItemIdentifierProvider.getItemUniqueIdentifier(object)));
/*     */     }
/*  61 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void set(int index, T object) {
/*  66 */     synchronized (this.m_ObjectCache) {
/*     */       
/*  68 */       if (this.m_ObjectCache != null) {
/*     */         
/*  70 */         RangeCheck(index);
/*  71 */         int cacheKey = ((Integer)this.m_CacheKeys.get(index)).intValue();
/*  72 */         if (this.m_ItemIdentifierProvider != null) {
/*     */           
/*  74 */           int newKey = this.m_ItemIdentifierProvider.getItemUniqueIdentifier(object);
/*  75 */           this.m_CacheKeys.set(index, Integer.valueOf(newKey));
/*  76 */           this.m_ObjectCache.remove(getFullCacheKey(Integer.valueOf(cacheKey)));
/*  77 */           cacheKey = newKey;
/*     */         } 
/*  79 */         this.m_ObjectCache.put(new Element(getFullCacheKey(Integer.valueOf(cacheKey)), object));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(T object) {
/*  86 */     if (object == null)
/*     */       return; 
/*  88 */     int index = indexOf(object);
/*  89 */     remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object[] toArray() {
/*  94 */     Object[] arr = new Object[size()];
/*  95 */     for (int i = 0; i < arr.length; i++)
/*     */     {
/*  97 */       arr[i] = get(i);
/*     */     }
/*  99 */     return arr;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEmpty() {
/* 104 */     return this.m_CacheKeys.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(int index) {
/* 109 */     RangeCheck(index);
/* 110 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 112 */       if (this.m_ObjectCache != null) {
/*     */         
/* 114 */         int cacheKey = ((Integer)this.m_CacheKeys.get(index)).intValue();
/* 115 */         this.m_CacheKeys.remove(index);
/* 116 */         this.m_ObjectCache.remove(getFullCacheKey(Integer.valueOf(cacheKey)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(T object) {
/* 123 */     if (object == null)
/* 124 */       throw new RuntimeException("Trying to put null element into the cache."); 
/* 125 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 127 */       if (this.m_ObjectCache != null) {
/*     */         
/* 129 */         int cacheKey = getCacheKey(object);
/* 130 */         this.m_CacheKeys.add(Integer.valueOf(cacheKey));
/* 131 */         this.m_ObjectCache.put(new Element(getFullCacheKey(Integer.valueOf(cacheKey)), object));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getCacheKey(T object) {
/* 138 */     if (this.m_ItemIdentifierProvider != null)
/* 139 */       return this.m_ItemIdentifierProvider.getItemUniqueIdentifier(object); 
/* 140 */     if (isEmpty())
/* 141 */       return 0; 
/* 142 */     return ((Integer)this.m_CacheKeys.get(this.m_CacheKeys.size() - 1)).intValue() + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAll(Collection<? extends T> o) {
/* 147 */     if (o != null)
/*     */     {
/* 149 */       for (Iterator<? extends T> iterator = o.iterator(); iterator.hasNext();)
/*     */       {
/* 151 */         add(iterator.next());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected T get(int index) {
/*     */     try {
/* 161 */       RangeCheck(index);
/*     */     }
/* 163 */     catch (IndexOutOfBoundsException e) {
/*     */       
/* 165 */       throw e;
/*     */     } 
/*     */     
/* 168 */     if (this.m_ObjectCache != null) {
/*     */       
/* 170 */       int cacheKey = ((Integer)this.m_CacheKeys.get(index)).intValue();
/* 171 */       Element e = this.m_ObjectCache.get(getFullCacheKey(Integer.valueOf(cacheKey)));
/* 172 */       if (e != null) {
/* 173 */         return (T)e.getObjectValue();
/*     */       }
/*     */     } 
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void clear() {
/* 181 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 183 */       if (this.m_ObjectCache != null)
/*     */       {
/* 185 */         for (Integer key : this.m_CacheKeys)
/*     */         {
/* 187 */           this.m_ObjectCache.remove(getFullCacheKey(key));
/*     */         }
/*     */       }
/* 190 */       this.m_CacheKeys.clear();
/* 191 */       if (this.referencing) {
/*     */         
/* 193 */         Integer value = references.get(this.cacheName);
/* 194 */         if (value == null) {
/*     */           return;
/*     */         }
/*     */         
/* 198 */         value = Integer.valueOf(value.intValue() - 1);
/* 199 */         if (value.intValue() > 0) {
/*     */           
/* 201 */           references.put(this.cacheName, value);
/*     */         }
/*     */         else {
/*     */           
/* 205 */           references.remove(this.cacheName);
/*     */         } 
/* 207 */         this.referencing = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int size() {
/* 214 */     return this.m_CacheKeys.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private void RangeCheck(int index) throws IndexOutOfBoundsException {
/* 219 */     if (index >= size() || index < 0) {
/* 220 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
/*     */     }
/*     */   }
/*     */   
/*     */   private String getFullCacheKey(Object cacheKey) {
/* 225 */     return String.valueOf(getCacheKeyPrefix()) + cacheKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCacheKeyPrefix() {
/* 230 */     return this.cacheKeyPrefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCacheKeyPrefix(String cacheKeyPrefix) {
/* 235 */     this.cacheKeyPrefix = cacheKeyPrefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCacheName(String cacheName) {
/* 240 */     this.cacheName = cacheName;
/* 241 */     synchronized (this.m_ObjectCache) {
/*     */       
/* 243 */       Integer value = references.get(cacheName);
/* 244 */       if (value == null)
/*     */       {
/* 246 */         value = Integer.valueOf(0);
/*     */       }
/* 248 */       value = Integer.valueOf(value.intValue() + 1);
/* 249 */       references.put(cacheName, value);
/* 250 */       this.referencing = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReleaseCache() {
/* 256 */     Integer value = references.get(this.cacheName);
/* 257 */     if (value == null || value.intValue() <= 0)
/*     */     {
/* 259 */       return true;
/*     */     }
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Cache getObjectCache() {
/* 266 */     return this.m_ObjectCache;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\InnerListCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */