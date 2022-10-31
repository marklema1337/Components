/*     */ package com.lbs.localization.cache;
/*     */ 
/*     */ import com.lbs.cache.ICache;
/*     */ import com.lbs.cache.ICacheConstants;
/*     */ import com.lbs.cache.ICacheListener;
/*     */ import com.lbs.cache.IVersionSource;
/*     */ import com.lbs.cache.JLbsCache;
/*     */ import com.lbs.cache.JLbsCacheScope;
/*     */ import com.lbs.cache.JLbsVersionBasedCache;
/*     */ import com.lbs.util.JLbsStringList;
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
/*     */ public class JLbsLocalizationCache
/*     */   implements ICacheConstants, ICache<Integer, JLbsStringList>
/*     */ {
/*     */   JLbsVersionBasedCache<Integer> m_InternalCache;
/*     */   
/*     */   public JLbsLocalizationCache(String langPrefix, IVersionSource versionSource) {
/*  29 */     this.m_InternalCache = new JLbsVersionBasedCache("_LCL_" + langPrefix, JLbsCacheScope.GLOBAL(), versionSource);
/*  30 */     this.m_InternalCache.setCacheListener(new ICacheListener<Integer, Object>()
/*     */         {
/*     */           public Object cacheMiss(Integer key, String group)
/*     */           {
/*  34 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public boolean isValid(Integer key, String group, Object item) {
/*  41 */             return false;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  48 */     this.m_InternalCache.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList get(Integer key) {
/*  53 */     return (JLbsStringList)this.m_InternalCache.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList get(Integer key, String group) {
/*  58 */     return (JLbsStringList)this.m_InternalCache.get(key, group);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  63 */     return this.m_InternalCache.getDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCacheScope getScope() {
/*  68 */     return this.m_InternalCache.getScope();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUniqueName() {
/*  73 */     return this.m_InternalCache.getUniqueName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void put(Integer key, JLbsStringList object) {
/*  78 */     this.m_InternalCache.put(key, object);
/*     */   }
/*     */ 
/*     */   
/*     */   public void put(Integer key, JLbsStringList object, String group) {
/*  83 */     this.m_InternalCache.put(key, object, group);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(Integer key) {
/*  88 */     this.m_InternalCache.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(Integer key, String group) {
/*  93 */     this.m_InternalCache.remove(key, group);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<Integer> getIterator() {
/* 102 */     return this.m_InternalCache.keySet(-1).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<Integer> getIterator(String group) {
/* 112 */     return this.m_InternalCache.keySet(group, -1).iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCache.JLbsCachePolicy getCachePolicy() {
/* 117 */     return this.m_InternalCache.getCachePolicy();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\cache\JLbsLocalizationCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */