/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ICacheItemIdentifierProvider;
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import com.lbs.platform.interfaces.ICachedIdentifiableList;
/*     */ import com.lbs.platform.interfaces.ICachedList;
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
/*     */ public class LbsClientCacheManager
/*     */   extends LbsCacheManagerBase
/*     */ {
/*     */   private static boolean ms_Initialized = false;
/*     */   private static LbsClientCacheManager ms_AppCacheManager;
/*     */   
/*     */   public static LbsClientCacheManager getAppCacheManager() {
/*  24 */     synchronized (LbsClientCacheManager.class) {
/*     */       
/*  26 */       if (ms_AppCacheManager == null)
/*  27 */         ms_AppCacheManager = new LbsClientCacheManager(); 
/*     */     } 
/*  29 */     return ms_AppCacheManager;
/*     */   }
/*     */ 
/*     */   
/*     */   private LbsClientCacheManager() {
/*  34 */     if (!isInitialized())
/*     */     {
/*  36 */       initCacheManager();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static LbsClientCacheManager getInstance() {
/*  42 */     LbsClientCacheManager instance = null;
/*     */     
/*  44 */     synchronized (LbsClientCacheManager.class) {
/*     */       
/*  46 */       instance = new LbsClientCacheManager();
/*     */     } 
/*     */     
/*  49 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   private static synchronized boolean isInitialized() {
/*  54 */     return ms_Initialized;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void initCacheManager() {
/*  59 */     if (ms_CacheManager == null) {
/*     */       
/*  61 */       JLbsClientCacheConfigurator instance = JLbsClientCacheConfigurator.getInstance();
/*  62 */       if (instance == null)
/*     */         return; 
/*  64 */       ICacheImplFactory factory = instance.getFactoryGeneral();
/*  65 */       if (factory instanceof EHCacheImplFactory) {
/*     */         
/*  67 */         EHCacheImplFactory ehCacheImplFactory = (EHCacheImplFactory)factory;
/*  68 */         ms_CacheManager = ehCacheImplFactory.getManager();
/*     */       } else {
/*     */         return;
/*     */       } 
/*  72 */       ms_Initialized = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T, V> ICachedHashTable<T, V> getCachedHashTable(String name, Class<T> keyClass, Class<V> valueClass, boolean domainAware) {
/*  78 */     if (isEHCacheDisabled())
/*  79 */       return new LbsNonCachedHashtable<>(name); 
/*  80 */     initCacheManager();
/*  81 */     return new LbsCachedHashTable<>(null, null, 0, name, this, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> ICachedList<T> getCachedList(String name, Class<T> valueClass, boolean domainAware) {
/*  86 */     if (isEHCacheDisabled())
/*  87 */       return (ICachedList<T>)new LbsNonCachedList(name); 
/*  88 */     initCacheManager();
/*  89 */     return (ICachedList<T>)new LbsCachedList(null, null, 0, name, this, null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> ICachedIdentifiableList<T> getCachedList(String name, Class<T> valueClass, ICacheItemIdentifierProvider<T> itemIdentifierProvider, boolean domainAware) {
/*  96 */     if (isEHCacheDisabled())
/*  97 */       return new LbsNonCachedList<>(name); 
/*  98 */     initCacheManager();
/*  99 */     return new LbsCachedList<>(null, null, 0, name, this, itemIdentifierProvider, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseCachedHashTable(ICachedHashTable<?, ?> table) {
/* 105 */     if (isEHCacheDisabled()) {
/* 106 */       table.clear();
/*     */     } else {
/*     */       
/* 109 */       initCacheManager();
/* 110 */       super.releaseCachedHashTable(table);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseCachedList(ICachedList<?> list) {
/* 117 */     if (isEHCacheDisabled()) {
/* 118 */       list.clear();
/*     */     } else {
/*     */       
/* 121 */       initCacheManager();
/* 122 */       super.releaseCachedList(list);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\LbsClientCacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */