/*     */ package com.lbs.localization.cache;
/*     */ 
/*     */ import com.lbs.cache.ICache;
/*     */ import com.lbs.cache.ICacheConstants;
/*     */ import com.lbs.cache.ICacheListener;
/*     */ import com.lbs.cache.IVersionSource;
/*     */ import com.lbs.cache.JLbsCache;
/*     */ import com.lbs.cache.JLbsCacheScope;
/*     */ import com.lbs.cache.JLbsVersionBasedCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMessageConstantIdsCache
/*     */   implements ICacheConstants, ICache<String, String[]>
/*     */ {
/*     */   JLbsVersionBasedCache<String> m_InternalCache;
/*     */   
/*     */   public JLbsMessageConstantIdsCache(String langPrefix, IVersionSource versionSource) {
/*  26 */     this.m_InternalCache = new JLbsVersionBasedCache("_MSGID_" + langPrefix, JLbsCacheScope.GLOBAL(), versionSource);
/*  27 */     this.m_InternalCache.setCacheListener(new ICacheListener<String, Object>()
/*     */         {
/*     */           public Object cacheMiss(String key, String group)
/*     */           {
/*  31 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean isValid(String key, String group, Object item) {
/*  38 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] get(String key) {
/*  46 */     return (String[])this.m_InternalCache.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] get(String key, String group) {
/*  52 */     return (String[])this.m_InternalCache.get(key, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String key, String[] object) {
/*  58 */     this.m_InternalCache.put(key, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String key, String[] object, String group) {
/*  64 */     this.m_InternalCache.put(key, object, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(String key) {
/*  70 */     this.m_InternalCache.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(String key, String group) {
/*  76 */     this.m_InternalCache.remove(key, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  82 */     this.m_InternalCache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUniqueName() {
/*  88 */     return this.m_InternalCache.getUniqueName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  94 */     return this.m_InternalCache.getDescription();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCacheScope getScope() {
/* 100 */     return this.m_InternalCache.getScope();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCache.JLbsCachePolicy getCachePolicy() {
/* 106 */     return this.m_InternalCache.getCachePolicy();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\cache\JLbsMessageConstantIdsCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */