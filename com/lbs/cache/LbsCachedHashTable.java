/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import java.util.Enumeration;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsCachedHashTable<T, V>
/*     */   implements ICachedHashTable<T, V>, ICacheConstants
/*     */ {
/*     */   private final String m_InstanceId;
/*     */   private final String m_SessionCode;
/*     */   private final int m_ContextIndex;
/*     */   private final String m_Name;
/*     */   private String m_InstanceName;
/*     */   private InnerHashtableCache<T, V> m_Cache;
/*     */   
/*     */   LbsCachedHashTable(String instanceId, String sessionCode, int contextIndex, String name, LbsCacheManagerBase manager, boolean domainAware) {
/*  48 */     this.m_InstanceId = instanceId;
/*  49 */     this.m_SessionCode = sessionCode;
/*  50 */     this.m_ContextIndex = contextIndex;
/*  51 */     this.m_Name = name;
/*     */     
/*  53 */     setInstanceName(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, this.m_Name);
/*     */     
/*  55 */     if (domainAware) {
/*  56 */       this.m_Cache = new DomainAwareHashtableCache<>(manager, name, getInstanceName());
/*     */     } else {
/*  58 */       this.m_Cache = new InnerHashtableCache<>(manager, name, getInstanceName());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  64 */     return this.m_Cache.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  70 */     return (size() == 0);
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
/*     */   public V get(T key) {
/*  84 */     return this.m_Cache.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(T key) {
/*  90 */     return this.m_Cache.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(V value) {
/*  96 */     return this.m_Cache.containsValue(value);
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
/*     */   public V put(T key, V object) {
/* 109 */     return this.m_Cache.put(key, object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(T key) {
/* 120 */     this.m_Cache.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 130 */     this.m_Cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<T> keys() {
/* 140 */     return this.m_Cache.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInstanceName() {
/* 150 */     return this.m_InstanceName;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstanceName(String instanceId, String sessionCode, int contextIndex, String name) {
/* 155 */     if (contextIndex > 0) {
/* 156 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 157 */         LbsCacheManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 158 */         "~H~_-_" + contextIndex + "_-_" + 
/* 159 */         name;
/*     */     } else {
/* 161 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 162 */         LbsCacheManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 163 */         "~H~_-_" + name;
/*     */     } 
/* 165 */     this.m_InstanceName = this.m_InstanceName.toLowerCase(Locale.US);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsCachedHashTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */