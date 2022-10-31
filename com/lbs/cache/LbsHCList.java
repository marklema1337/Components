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
/*     */ public class LbsHCList<T>
/*     */   implements ICachedIdentifiableList<T>, ICacheConstants
/*     */ {
/*     */   private final String m_InstanceId;
/*     */   private final String m_SessionCode;
/*     */   private final int m_ContextIndex;
/*     */   private final String m_Name;
/*     */   private String m_InstanceName;
/*     */   private HCInnerListCache<T> m_Cache;
/*     */   
/*     */   LbsHCList(String instanceId, String sessionCode, int contextIndex, String name, LbsHCManagerBase cacheManager, ICacheItemIdentifierProvider<T> itemIdentifierProvider, boolean domainAware) {
/*  45 */     this.m_InstanceId = instanceId;
/*  46 */     this.m_SessionCode = sessionCode;
/*  47 */     this.m_ContextIndex = contextIndex;
/*  48 */     this.m_Name = name;
/*  49 */     setInstanceName(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, this.m_Name);
/*  50 */     if (domainAware) {
/*  51 */       this.m_Cache = new DomainAwareHCInnerCache<>(cacheManager, this.m_Name, getInstanceName(), itemIdentifierProvider);
/*     */     } else {
/*  53 */       this.m_Cache = new HCInnerListCache<>(cacheManager, this.m_Name, getInstanceName(), itemIdentifierProvider);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(T object) {
/*  59 */     return this.m_Cache.contains(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(T object) {
/*  65 */     return this.m_Cache.indexOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  71 */     return this.m_Cache.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(T object) {
/*  77 */     this.m_Cache.remove(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int index, T object) {
/*  83 */     this.m_Cache.set(index, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  89 */     return this.m_Cache.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/*  95 */     this.m_Cache.remove(index);
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
/* 110 */     return this.m_Cache.get(index);
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
/* 122 */     this.m_Cache.add(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(Collection<? extends T> o) {
/* 128 */     this.m_Cache.addAll(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 138 */     this.m_Cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 144 */     return this.m_Cache.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInstanceName() {
/* 154 */     return this.m_InstanceName;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstanceName(String instanceId, String sessionCode, int contextIndex, String name) {
/* 159 */     if (contextIndex > 0) {
/* 160 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 161 */         LbsHCManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 162 */         "~L~_-_" + contextIndex + "_-_" + 
/* 163 */         name;
/*     */     } else {
/* 165 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 166 */         LbsHCManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 167 */         "~L~_-_" + name;
/*     */     } 
/* 169 */     this.m_InstanceName = this.m_InstanceName.toLowerCase(Locale.US);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsHCList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */