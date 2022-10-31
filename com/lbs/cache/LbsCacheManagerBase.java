/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import com.lbs.platform.interfaces.ICacheManager;
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import com.lbs.platform.interfaces.ICachedIdentifiableList;
/*     */ import com.lbs.platform.interfaces.ICachedList;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.config.CacheConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsCacheManagerBase
/*     */   implements ICacheManager
/*     */ {
/*  27 */   protected static String[] SESSION_FORBIDDEN_VARS = new String[] { ":", "\\*", "\\?", "\"", "<", ">", "\\|", "/", "\\\\" };
/*  28 */   protected static String[] SESSION_SUBST_VARS = new String[] { "_col_", "_ast_", "_que_", "_quo_", "_ltn_", "_gtn_", "_ver_", 
/*  29 */       "_sla_", "_bsl_" };
/*     */   
/*     */   protected String m_SessionCode;
/*     */   
/*     */   protected int m_ContextIndex;
/*     */   
/*     */   protected String m_InstanceId;
/*     */   protected static CacheManager ms_CacheManager;
/*     */   protected static IClientContext m_ClientContext;
/*     */   
/*     */   protected static String getSpecializedSessionCode(String orgSessionCode) {
/*  40 */     if (orgSessionCode == null) {
/*  41 */       return "NL_SES";
/*     */     }
/*  43 */     String speSessionCode = orgSessionCode;
/*     */     
/*  45 */     for (int i = 0; i < SESSION_FORBIDDEN_VARS.length; i++) {
/*  46 */       speSessionCode = speSessionCode.replaceAll(SESSION_FORBIDDEN_VARS[i], SESSION_SUBST_VARS[i]);
/*     */     }
/*  48 */     return speSessionCode;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Cache createCache(String name, String instanceName) {
/*  53 */     Cache cache = null;
/*  54 */     if (ms_CacheManager.cacheExists(instanceName)) {
/*  55 */       cache = ms_CacheManager.getCache(instanceName);
/*  56 */     } else if (ms_CacheManager.cacheExists(name)) {
/*     */       
/*  58 */       cache = ms_CacheManager.getCache(name);
/*  59 */       CacheConfiguration config = cache.getCacheConfiguration();
/*  60 */       CacheConfiguration clone = config.clone();
/*  61 */       clone.name(instanceName);
/*  62 */       cache = new Cache(clone);
/*  63 */       ms_CacheManager.addCache(cache);
/*     */     }
/*     */     else {
/*     */       
/*  67 */       synchronized (ms_CacheManager) {
/*     */         
/*  69 */         if (!ms_CacheManager.cacheExists(instanceName))
/*  70 */           ms_CacheManager.addCache(instanceName); 
/*  71 */         cache = ms_CacheManager.getCache(instanceName);
/*     */       } 
/*     */     } 
/*  74 */     System.out.println("Created cache : " + instanceName + " by thread " + Thread.currentThread().getName());
/*  75 */     return cache;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T, V> ICachedHashTable<T, V> getCachedHashTable(String name, Class<T> keyClass, Class<V> valueClass, boolean domainAware) {
/*  80 */     if (isEHCacheDisabled())
/*  81 */       return new LbsNonCachedHashtable<>(name); 
/*  82 */     return new LbsCachedHashTable<>(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, name, this, domainAware);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> ICachedList<T> getCachedList(String name, Class<T> valueClass, boolean domainAware) {
/*  87 */     if (isEHCacheDisabled())
/*  88 */       return (ICachedList<T>)new LbsNonCachedList(name); 
/*  89 */     return (ICachedList<T>)new LbsCachedList(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, name, this, null, domainAware);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> ICachedIdentifiableList<T> getCachedList(String name, Class<T> valueClass, ICacheItemIdentifierProvider<T> itemIdentifierProvider, boolean domainAware) {
/*  96 */     if (isEHCacheDisabled())
/*  97 */       return new LbsNonCachedList<>(name); 
/*  98 */     return new LbsCachedList<>(this.m_InstanceId, this.m_SessionCode, this.m_ContextIndex, name, this, itemIdentifierProvider, domainAware);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IClientContext getClientContext() {
/* 103 */     return m_ClientContext;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setClientContext(IClientContext context) {
/* 108 */     m_ClientContext = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getEHCacheManager() {
/* 113 */     return ms_CacheManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void releaseCachedHashTable(ICachedHashTable<?, ?> table) {
/* 118 */     if (isEHCacheDisabled()) {
/* 119 */       table.clear();
/*     */     
/*     */     }
/* 122 */     else if (table != null) {
/* 123 */       ms_CacheManager.removeCache(table.getInstanceName());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void releaseCachedList(ICachedList<?> list) {
/* 129 */     if (isEHCacheDisabled()) {
/* 130 */       list.clear();
/*     */     
/*     */     }
/* 133 */     else if (list != null) {
/* 134 */       ms_CacheManager.removeCache(list.getInstanceName());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEHCacheDisabled() {
/* 140 */     return JLbsConstants.DISABLE_EH_CACHE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsCacheManagerBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */