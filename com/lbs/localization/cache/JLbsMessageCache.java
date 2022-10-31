/*     */ package com.lbs.localization.cache;
/*     */ 
/*     */ import com.lbs.cache.ICache;
/*     */ import com.lbs.cache.ICacheConstants;
/*     */ import com.lbs.cache.ICacheListener;
/*     */ import com.lbs.cache.IVersionSource;
/*     */ import com.lbs.cache.JLbsCache;
/*     */ import com.lbs.cache.JLbsCacheScope;
/*     */ import com.lbs.cache.JLbsVersionBasedCache;
/*     */ import com.lbs.message.JLbsMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMessageCache
/*     */   implements ICacheConstants, ICache<String, JLbsMessage>
/*     */ {
/*     */   JLbsVersionBasedCache<String> m_InternalCache;
/*     */   
/*     */   public JLbsMessageCache(String langPrefix, IVersionSource versionSource) {
/*  27 */     this.m_InternalCache = new JLbsVersionBasedCache("_MSG_" + langPrefix, JLbsCacheScope.GLOBAL(), versionSource);
/*  28 */     this.m_InternalCache.setCacheListener(new ICacheListener<String, Object>()
/*     */         {
/*     */           public Object cacheMiss(String key, String group)
/*     */           {
/*  32 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean isValid(String key, String group, Object item) {
/*  39 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessage get(String key) {
/*  47 */     return (JLbsMessage)this.m_InternalCache.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMessage get(String key, String group) {
/*  53 */     return (JLbsMessage)this.m_InternalCache.get(key, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String key, JLbsMessage object) {
/*  59 */     this.m_InternalCache.put(key, object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String key, JLbsMessage object, String group) {
/*  65 */     this.m_InternalCache.put(key, object, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(String key) {
/*  71 */     this.m_InternalCache.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(String key, String group) {
/*  77 */     this.m_InternalCache.remove(key, group);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  83 */     this.m_InternalCache.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUniqueName() {
/*  89 */     return this.m_InternalCache.getUniqueName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  95 */     return this.m_InternalCache.getDescription();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCacheScope getScope() {
/* 101 */     return this.m_InternalCache.getScope();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCache.JLbsCachePolicy getCachePolicy() {
/* 107 */     return this.m_InternalCache.getCachePolicy();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\cache\JLbsMessageCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */