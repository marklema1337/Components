/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import com.lbs.platform.interfaces.ICachedIdentifiableList;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsCachedList<T>
/*     */   implements ICachedIdentifiableList<T>, ICacheConstants
/*     */ {
/*     */   private final String m_InstanceId;
/*     */   private final String m_SessionCode;
/*     */   private final int m_ContextIndex;
/*     */   private final String m_Name;
/*     */   private String m_InstanceName;
/*     */   private InnerListCache<T> m_Cache;
/*     */   
/*     */   LbsCachedList(String instanceId, String sessionCode, int contextIndex, String name, LbsCacheManagerBase cacheManager, ICacheItemIdentifierProvider<T> itemIdentifierProvider, boolean domainAware) {
/*  45 */     this.m_InstanceId = instanceId;
/*  46 */     this.m_SessionCode = sessionCode;
/*  47 */     this.m_ContextIndex = contextIndex;
/*  48 */     this.m_Name = name;
/*  49 */     setInstanceName(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, this.m_Name);
/*  50 */     if (domainAware) {
/*  51 */       this.m_Cache = new DomainAwareInnerCache<>(cacheManager, this.m_Name, getInstanceName(), itemIdentifierProvider);
/*     */     } else {
/*  53 */       this.m_Cache = new InnerListCache<>(cacheManager, this.m_Name, getInstanceName(), itemIdentifierProvider);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InnerListCache<T> getCache() {
/*  58 */     return this.m_Cache;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(T object) {
/*  64 */     return this.m_Cache.contains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(T object) {
/*  70 */     return this.m_Cache.indexOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  76 */     return this.m_Cache.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(T object) {
/*  82 */     this.m_Cache.remove(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int index, T object) {
/*  88 */     this.m_Cache.set(index, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  94 */     return this.m_Cache.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/* 100 */     this.m_Cache.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T get(int index) throws IndexOutOfBoundsException {
/* 115 */     return this.m_Cache.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(T object) {
/* 127 */     this.m_Cache.add(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(Collection<? extends T> o) {
/* 133 */     this.m_Cache.addAll(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 143 */     this.m_Cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 149 */     return this.m_Cache.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInstanceName() {
/* 159 */     return this.m_InstanceName;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstanceName(String instanceId, String sessionCode, int contextIndex, String name) {
/* 164 */     if (contextIndex > 0) {
/* 165 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 166 */         LbsCacheManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 167 */         "~L~_-_" + contextIndex + "_-_" + 
/* 168 */         name;
/*     */     } else {
/* 170 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 171 */         LbsCacheManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 172 */         "~L~_-_" + name;
/*     */     } 
/* 174 */     this.m_InstanceName = this.m_InstanceName.toLowerCase(Locale.US);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsCachedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */