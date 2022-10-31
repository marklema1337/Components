/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.security.SignatureException;
/*     */ import java.util.Properties;
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
/*     */ public class JLbsClientCacheConfigurator
/*     */   implements ICacheConstants
/*     */ {
/*     */   public static final String CACHE_SUFFIX = "_EH";
/*     */   private final IClientContext m_Context;
/*     */   private final String m_Version;
/*     */   private final int m_Type;
/*     */   private volatile ICacheImplFactory m_Factory;
/*     */   private volatile ICacheImplFactory m_FactoryGeneral;
/*  43 */   private static Properties ms_ClientCacheProperties = new Properties(); static {
/*  44 */     ms_ClientCacheProperties.put("jcs.default.cacheattributes.MaxObjects", PROP_VALUE_MAX_OBJECTS);
/*  45 */     ms_ClientCacheProperties.put("jcs.auxiliary.DC.attributes.DiskPath", "");
/*  46 */     ms_ClientCacheProperties.put("jcs.auxiliary.DC.attributes.MaxPurgatorySize", JLbsConstants.PROP_VALUE_MAX_PURGATORY_SIZE);
/*     */   }
/*     */   private static JLbsClientCacheConfigurator ms_Instance;
/*     */   
/*     */   private JLbsClientCacheConfigurator(IClientContext context, String version, int type) {
/*  51 */     this.m_Context = context;
/*  52 */     this.m_Version = version;
/*  53 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsClientCacheConfigurator getInstance() {
/*  58 */     return ms_Instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsClientCacheConfigurator createInstance(IClientContext context, String version, int type) {
/*  63 */     ms_Instance = new JLbsClientCacheConfigurator(context, version, type);
/*  64 */     return ms_Instance;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean loadJars() {
/*  69 */     if (this.m_Context == null) {
/*  70 */       return false;
/*     */     }
/*     */     
/*     */     try {
/*  74 */       this.m_Context.loadJAR("ehcache-core.jar;slf4j-api.jar;log4j-slf4j-impl.jar;icu4jsimple.jar", false, false);
/*     */     }
/*  76 */     catch (SignatureException e) {
/*     */       
/*  78 */       e.printStackTrace();
/*  79 */       return false;
/*     */     }
/*  81 */     catch (FileNotFoundException e) {
/*     */       
/*  83 */       e.printStackTrace();
/*  84 */       return false;
/*     */     } 
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCacheStorageDir(String version) {
/*  92 */     return "Cache_" + version + "_EH";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void configure() {
/*  98 */     if (this.m_Context != null && loadJars() && this.m_Factory == null && this.m_FactoryGeneral == null) {
/*     */       
/*     */       try {
/*     */         
/* 102 */         String cacheName = getCacheStorageDir(this.m_Version);
/*     */         
/* 104 */         switch (this.m_Type) {
/*     */           
/*     */           case 1:
/* 107 */             cacheName = JLbsFileUtil.appendPath(cacheName, "Admin");
/*     */             break;
/*     */           
/*     */           case 2:
/* 111 */             cacheName = JLbsFileUtil.appendPath(cacheName, "Unity");
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 118 */         ms_ClientCacheProperties.setProperty("jcs.auxiliary.DC.attributes.DiskPath", JLbsClientFS.getFullPath(cacheName));
/* 119 */         Class<ICacheImplFactory> factoryClass = null;
/* 120 */         factoryClass = this.m_Context.loadClass("com.lbs.cache.EHCacheImplFactory");
/*     */         
/* 122 */         Constructor<ICacheImplFactory> constructor = factoryClass.getConstructor(new Class[] { Properties.class });
/*     */         
/* 124 */         ms_ClientCacheProperties.setProperty("factory.type", "0");
/* 125 */         this.m_Factory = constructor.newInstance(new Object[] { ms_ClientCacheProperties });
/*     */         
/* 127 */         ms_ClientCacheProperties.setProperty("factory.type", "1");
/* 128 */         this.m_FactoryGeneral = constructor.newInstance(new Object[] { ms_ClientCacheProperties });
/*     */       }
/* 130 */       catch (Throwable e) {
/*     */         
/* 132 */         LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ICacheImplFactory getFactory() {
/* 139 */     return this.m_Factory;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void shutdown(boolean shutdownCaches) {
/* 144 */     if (shutdownCaches) {
/*     */       
/* 146 */       JLbsCache[] caches = (JLbsCache[])JLbsCache.getKnownInstances();
/* 147 */       if (caches != null)
/*     */       {
/* 149 */         for (int i = 0; i < caches.length; i++) {
/*     */           
/* 151 */           JLbsCache<?, ?> cache = caches[i];
/* 152 */           cache.shutdown();
/*     */         } 
/*     */       }
/*     */     } 
/* 156 */     getInstance().getFactory().shutdown();
/*     */   }
/*     */ 
/*     */   
/*     */   public ICacheImplFactory getFactoryGeneral() {
/* 161 */     return this.m_FactoryGeneral;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsClientCacheConfigurator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */