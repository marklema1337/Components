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
/*     */ public class LbsHCHashTable<T, V>
/*     */   implements ICachedHashTable<T, V>, ICacheConstants
/*     */ {
/*     */   private final String m_InstanceId;
/*     */   private final String m_SessionCode;
/*     */   private final int m_ContextIndex;
/*     */   private final String m_Name;
/*     */   private String m_InstanceName;
/*     */   private HCInnerHashtableCache<T, V> m_Cache;
/*     */   
/*     */   LbsHCHashTable(String instanceId, String sessionCode, int contextIndex, String name, LbsHCManagerBase manager, boolean domainAware) {
/*  34 */     this.m_InstanceId = instanceId;
/*  35 */     this.m_SessionCode = sessionCode;
/*  36 */     this.m_ContextIndex = contextIndex;
/*  37 */     this.m_Name = name;
/*     */     
/*  39 */     setInstanceName(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, this.m_Name);
/*     */     
/*  41 */     if (domainAware) {
/*  42 */       this.m_Cache = new DomainAwareHCHashtableCache<>(manager, name, getInstanceName());
/*     */     } else {
/*  44 */       this.m_Cache = new HCInnerHashtableCache<>(manager, name, getInstanceName());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public V get(T key) {
/*  50 */     return this.m_Cache.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public V put(T key, V object) {
/*  56 */     return this.m_Cache.put(key, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(T key) {
/*  62 */     this.m_Cache.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  69 */     this.m_Cache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<T> keys() {
/*  76 */     return this.m_Cache.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInstanceName() {
/*  82 */     return this.m_InstanceName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(T key) {
/*  88 */     return this.m_Cache.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(V value) {
/*  94 */     return this.m_Cache.containsValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 100 */     return this.m_Cache.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 106 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstanceName(String instanceId, String sessionCode, int contextIndex, String name) {
/* 111 */     if (contextIndex > 0) {
/* 112 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 113 */         LbsHCManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 114 */         "~H~_-_" + contextIndex + "_-_" + 
/* 115 */         name;
/*     */     } else {
/* 117 */       this.m_InstanceName = String.valueOf(instanceId) + "_-_" + 
/* 118 */         LbsHCManagerBase.getSpecializedSessionCode(sessionCode) + "_-_" + 
/* 119 */         "~H~_-_" + name;
/*     */     } 
/* 121 */     this.m_InstanceName = this.m_InstanceName.toLowerCase(Locale.US);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsHCHashTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */