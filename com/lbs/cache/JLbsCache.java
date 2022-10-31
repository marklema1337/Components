/*     */ package com.lbs.cache;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StackUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
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
/*     */ public abstract class JLbsCache<K, V>
/*     */   implements ICache<K, V>, ICacheConstants
/*     */ {
/*     */   private String m_UniqueName;
/*     */   private String m_ScopeAccessName;
/*     */   private ICacheListener<K, V> m_CacheListener;
/*     */   private JLbsCacheScope m_Scope;
/*  32 */   private String m_Description = "";
/*     */   private boolean m_Destroyed = false;
/*  34 */   private HashMap<String, ICacheImpl<K, V>> m_Groups = new CacheHashMap<>();
/*     */   
/*     */   private Class<K> m_KeyClass;
/*     */   
/*     */   private Class<V> m_ValueClass;
/*     */   
/*     */   private IThreadedDownloader<K, V> m_ThreadedDownloader;
/*     */   
/*     */   private Thread m_DownloaderThread;
/*     */   
/*     */   private ICacheImplFactory m_Factory;
/*     */   private static WeakHashMap<String, JLbsCache<?, ?>> ms_KnownCacheInstances;
/*     */   public static boolean CACHE_ALLOCATION_STACK = false;
/*  47 */   private String m_StackInfo = null;
/*     */   
/*  49 */   private static final transient LbsConsole ms_Logger = LbsConsole.getLogger("LbsTransport.JLbsCache");
/*     */   
/*  51 */   protected static final HashMap<Class<?>, JLbsCachePolicy> ms_RegisteredImplementors = new HashMap<>();
/*     */ 
/*     */   
/*     */   static {
/*  55 */     ms_RegisteredImplementors.put(JLbsVersionBasedCache.class, new JLbsCachePolicy(true, JLbsVersionBasedCache.class));
/*  56 */     ms_RegisteredImplementors.put(JLbsSessionBasedCache.class, new JLbsCachePolicy(false, JLbsSessionBasedCache.class));
/*  57 */     ms_RegisteredImplementors.put(JLbsManuallyControlledCache.class, new JLbsCachePolicy(false, 
/*  58 */           JLbsManuallyControlledCache.class));
/*  59 */     ms_RegisteredImplementors.put(JLbsPersistentCache.class, new JLbsCachePolicy(true, JLbsPersistentCache.class));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JLbsCache(JLbsCacheScope scope) {
/*  69 */     this("", scope);
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
/*     */   JLbsCache(String nameExtension, JLbsCacheScope scope) {
/*  81 */     registrationCheck(getClass());
/*  82 */     if (scope == null) {
/*  83 */       scope = JLbsCacheScope.GLOBAL();
/*     */     }
/*  85 */     if (scope.equals(JLbsCacheScope.GLOBAL())) {
/*  86 */       this.m_Factory = JLbsClientCacheConfigurator.getInstance().getFactoryGeneral();
/*     */     } else {
/*  88 */       this.m_Factory = JLbsClientCacheConfigurator.getInstance().getFactory();
/*     */     } 
/*  90 */     createUniqueName(nameExtension, scope);
/*  91 */     this.m_Scope = scope;
/*  92 */     restoreGroups();
/*  93 */     if (CACHE_ALLOCATION_STACK)
/*  94 */       this.m_StackInfo = StackUtil.getStack(true); 
/*  95 */     addInstance(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createUniqueName(String nameExtension, JLbsCacheScope scope) {
/* 100 */     String name = getCachePolicy().getCacheTypeName();
/* 101 */     if (!StringUtil.isEmpty(nameExtension))
/*     */     {
/* 103 */       name = String.valueOf(name) + "_" + nameExtension + "_";
/*     */     }
/*     */ 
/*     */     
/* 107 */     synchronized (this) {
/*     */       
/* 109 */       this.m_ScopeAccessName = name;
/*     */       
/* 111 */       if (!StringUtil.isEmpty(scope.getUniqueID()))
/*     */       {
/* 113 */         name = String.valueOf(name) + scope.getUniqueID();
/*     */       }
/* 115 */       this.m_UniqueName = name.toLowerCase();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ICacheImpl<K, V> createCacheInstance(String cacheName) {
/* 121 */     return this.m_Factory.createCacheImpl(cacheName, this.m_KeyClass, this.m_ValueClass);
/*     */   }
/*     */ 
/*     */   
/*     */   private void registrationCheck(Class<?> class1) {
/* 126 */     if (ms_RegisteredImplementors.get(class1) == null) {
/* 127 */       throw new IllegalArgumentException(String.valueOf(class1.getName()) + 
/* 128 */           " is not known by JLbsCache. It should be statically registered on JLbsCache.");
/*     */     }
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
/*     */   private static synchronized void addInstance(JLbsCache<?, ?> cache) {
/* 141 */     if (ms_KnownCacheInstances == null) {
/*     */       
/* 143 */       ms_KnownCacheInstances = new WeakHashMap<>();
/* 144 */       clearNonPersistentCaches();
/*     */     } 
/*     */     
/* 147 */     if (ms_KnownCacheInstances.get(cache.getUniqueName()) == null) {
/* 148 */       ms_KnownCacheInstances.put(cache.getUniqueName(), cache);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 153 */       ms_KnownCacheInstances.put(String.valueOf(cache.getUniqueName()) + "@" + Integer.toHexString(cache.hashCode()), cache);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsCache<?, ?>[] getKnownInstances() {
/* 162 */     if (ms_KnownCacheInstances == null) {
/* 163 */       return (JLbsCache<?, ?>[])new JLbsCache[0];
/*     */     }
/* 165 */     synchronized (ms_KnownCacheInstances) {
/*     */       
/* 167 */       Collection<JLbsCache<?, ?>> values = ms_KnownCacheInstances.values();
/* 168 */       int i = 0;
/* 169 */       JLbsCache[] retval = new JLbsCache[values.size()];
/* 170 */       for (Iterator<JLbsCache<?, ?>> iterator = values.iterator(); iterator.hasNext(); i++)
/* 171 */         retval[i] = iterator.next(); 
/* 172 */       return (JLbsCache<?, ?>[])retval;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void clear() {
/* 178 */     restoreGroups();
/* 179 */     Collection<ICacheImpl<K, V>> values = this.m_Groups.values();
/* 180 */     for (Iterator<ICacheImpl<K, V>> iterator = values.iterator(); iterator.hasNext(); ) {
/*     */       
/* 182 */       ICacheImpl<K, V> jcs = iterator.next();
/* 183 */       if (jcs != null) {
/* 184 */         jcs.clear();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized V get(K key) {
/* 190 */     return get(key, "DefaultGroup");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ICacheImpl<K, V> getCacheInstance(String groupName) {
/* 197 */     synchronized (this) {
/*     */       
/* 199 */       if (this.m_Destroyed) {
/*     */         
/* 201 */         ms_Logger.debug("The cache named " + getUniqueName() + " is invalidated. Calls to this cache are no longer valid!");
/* 202 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/* 206 */     ICacheImpl<K, V> cache = this.m_Groups.get(groupName);
/* 207 */     if (cache == null)
/*     */     {
/* 209 */       synchronized (this) {
/*     */ 
/*     */ 
/*     */         
/* 213 */         if (this.m_Destroyed) {
/*     */           
/* 215 */           ms_Logger.debug("The cache named " + getUniqueName() + 
/* 216 */               " is invalidated. Calls to this cache are no longer valid!");
/* 217 */           return null;
/*     */         } 
/* 219 */         cache = this.m_Groups.get(groupName);
/* 220 */         if (cache == null) {
/*     */ 
/*     */           
/* 223 */           cache = createCacheInstance(String.valueOf(this.m_UniqueName) + groupName);
/* 224 */           this.m_Groups.put(groupName, cache);
/*     */         } 
/*     */       } 
/*     */     }
/* 228 */     return cache;
/*     */   }
/*     */ 
/*     */   
/*     */   private V internalGet(K key, String group) {
/* 233 */     ICacheImpl<K, V> cache = getCacheInstance(group);
/* 234 */     if (cache != null)
/* 235 */       return cache.get(key); 
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<K> getKeyClass() {
/* 241 */     return this.m_KeyClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<V> getValueClass() {
/* 246 */     return this.m_ValueClass;
/*     */   }
/*     */   
/*     */   public synchronized V get(K key, String group) {
/*     */     boolean valid;
/* 251 */     if (group == null) {
/* 252 */       group = "DefaultGroup";
/*     */     }
/* 254 */     V object = null;
/*     */     
/* 256 */     String[] searchPatterns = getScope().getScopeSearchPatterns();
/* 257 */     for (int i = 0; object == null && i < searchPatterns.length; i++) {
/*     */       
/* 259 */       if (i == 0) {
/* 260 */         object = internalGet(key, group);
/*     */       } else {
/*     */         
/* 263 */         JLbsCache<?, ?> cache = ms_KnownCacheInstances.get(String.valueOf(this.m_ScopeAccessName) + searchPatterns[i]);
/* 264 */         if (cache != null) {
/*     */           
/* 266 */           Class<?> keyClass = cache.getKeyClass();
/* 267 */           Class<?> valueClass = cache.getValueClass();
/* 268 */           if (this.m_KeyClass.isAssignableFrom(keyClass) && this.m_ValueClass.isAssignableFrom(valueClass)) {
/*     */ 
/*     */             
/* 271 */             JLbsCache<?, ?> jLbsCache = cache;
/* 272 */             object = (V)jLbsCache.internalGet(key, group);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 281 */       valid = onValidateCachedObject(key, group, object);
/*     */     }
/* 283 */     catch (Throwable t) {
/*     */       
/* 285 */       valid = false;
/*     */     } 
/*     */     
/* 288 */     if (object != null && !valid) {
/*     */       
/* 290 */       remove(key, group);
/* 291 */       object = null;
/*     */     } 
/*     */ 
/*     */     
/* 295 */     if (object == null)
/* 296 */       object = onCacheMiss(key, group); 
/* 297 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void put(K key, V object, String group) {
/* 302 */     if (group == null) {
/* 303 */       group = "DefaultGroup";
/*     */     }
/* 305 */     ICacheImpl<K, V> jcs = getCacheInstance(group);
/* 306 */     if (jcs != null) {
/* 307 */       jcs.put(key, object);
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void put(K key, V object) {
/* 312 */     put(key, object, "DefaultGroup");
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void remove(K key, String group) {
/* 317 */     if (group == null)
/* 318 */       group = "DefaultGroup"; 
/* 319 */     ICacheImpl<K, V> jcs = getCacheInstance(group);
/* 320 */     if (jcs != null) {
/* 321 */       jcs.remove(key);
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void remove(K key) {
/* 326 */     remove(key, "DefaultGroup");
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getUniqueName() {
/* 331 */     return this.m_UniqueName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 336 */     return String.valueOf(getUniqueName()) + ((this.m_StackInfo != null) ? (
/* 337 */       "\n Created at:\n" + this.m_StackInfo) : 
/* 338 */       "");
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
/*     */   protected V onCacheMiss(K key, String group) {
/* 351 */     V item = null;
/* 352 */     if (this.m_CacheListener != null)
/* 353 */       item = this.m_CacheListener.cacheMiss(key, group); 
/* 354 */     if (item != null)
/* 355 */       put(key, item, group); 
/* 356 */     return item;
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
/*     */   
/*     */   protected boolean onValidateCachedObject(K key, String group, V item) {
/* 372 */     if (item == null)
/* 373 */       return false; 
/* 374 */     if (this.m_CacheListener != null) {
/* 375 */       return this.m_CacheListener.isValid(key, group, item);
/*     */     }
/* 377 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ICacheListener<K, V> getCacheListener() {
/* 385 */     return this.m_CacheListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCacheListener(ICacheListener<K, V> cacheListener) {
/* 393 */     this.m_CacheListener = cacheListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 398 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 406 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCacheScope getScope() {
/* 411 */     return this.m_Scope;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int size() {
/* 416 */     return size("DefaultGroup");
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int size(String group) {
/* 421 */     if (group == null) {
/* 422 */       group = "DefaultGroup";
/*     */     }
/* 424 */     ICacheImpl<K, V> cache = getCacheInstance(group);
/* 425 */     return cache.size();
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
/*     */   public synchronized Set<K> keySet(int limit) {
/* 437 */     return keySet("DefaultGroup", limit);
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
/*     */   public synchronized Set<K> keySet(String group, int limit) {
/* 449 */     if (group == null) {
/* 450 */       group = "DefaultGroup";
/*     */     }
/*     */     
/*     */     try {
/* 454 */       if (limit < 0) {
/* 455 */         limit = Integer.MAX_VALUE;
/*     */       }
/* 457 */       ICacheImpl<K, V> cache = getCacheInstance(group);
/* 458 */       return cache.keySet(limit);
/*     */     }
/* 460 */     catch (Exception e) {
/*     */       
/* 462 */       ms_Logger.error(e.getMessage(), e);
/* 463 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void save() {
/* 475 */     if (!getCachePolicy().isPersistent()) {
/*     */       return;
/*     */     }
/* 478 */     restoreGroups();
/* 479 */     Collection<ICacheImpl<K, V>> values = this.m_Groups.values();
/* 480 */     for (Iterator<ICacheImpl<K, V>> iterator = values.iterator(); iterator.hasNext(); ) {
/*     */       
/* 482 */       ICacheImpl<K, V> cache = iterator.next();
/* 483 */       cache.flush();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void shutdown() {
/* 493 */     dispose(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void destroy() {
/* 501 */     dispose(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final synchronized void reinitialize() {
/* 510 */     destroy();
/* 511 */     this.m_Destroyed = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void clearNonPersistentCaches() {
/* 519 */     Collection<JLbsCachePolicy> values = ms_RegisteredImplementors.values();
/* 520 */     for (Iterator<JLbsCachePolicy> iterator = values.iterator(); iterator.hasNext(); ) {
/*     */       
/* 522 */       JLbsCachePolicy policy = iterator.next();
/* 523 */       if (policy != null && !policy.isPersistent())
/*     */       {
/* 525 */         deleteCache(policy.getCacheTypeName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void restoreGroups() {
/* 536 */     if (this.m_Factory == null)
/*     */       return; 
/* 538 */     String storagePath = this.m_Factory.getDiskStorePath();
/* 539 */     if (JLbsStringUtil.isEmpty(storagePath))
/*     */       return; 
/* 541 */     File cacheDir = new File(storagePath);
/*     */     
/* 543 */     if (cacheDir.exists() && cacheDir.isDirectory()) {
/*     */       
/* 545 */       File[] fileList = cacheDir.listFiles(new FilenameFilter()
/*     */           {
/*     */             public boolean accept(File dir, String name)
/*     */             {
/* 549 */               if (name.startsWith(JLbsCache.this.m_UniqueName) && name.endsWith(JLbsCache.this.m_Factory.getCacheIndexFileSuffix()))
/* 550 */                 return true; 
/* 551 */               return false;
/*     */             }
/*     */           });
/* 554 */       for (int i = 0; i < fileList.length; i++) {
/*     */         
/* 556 */         String name = fileList[i].getName();
/* 557 */         name = name.replaceFirst(this.m_UniqueName, "");
/* 558 */         int end = name.lastIndexOf(this.m_Factory.getCacheIndexFileSuffix());
/* 559 */         name = name.substring(0, end);
/* 560 */         if (this.m_Groups.get(name) == null) {
/*     */ 
/*     */           
/* 563 */           ICacheImpl<K, V> cache = createCacheInstance(String.valueOf(this.m_UniqueName) + name);
/* 564 */           this.m_Groups.put(name, cache);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void deleteCache(final String namePrefix) {
/* 577 */     String storagePath = JLbsClientCacheConfigurator.getInstance().getFactory().getDiskStorePath();
/* 578 */     File cacheDir = new File(storagePath);
/*     */     
/* 580 */     if (cacheDir.exists() && cacheDir.isDirectory()) {
/*     */       
/* 582 */       File[] fileList = cacheDir.listFiles(new FilenameFilter()
/*     */           {
/*     */             public boolean accept(File dir, String name)
/*     */             {
/* 586 */               if (name.startsWith(namePrefix))
/* 587 */                 return true; 
/* 588 */               return false;
/*     */             }
/*     */           });
/* 591 */       for (int i = 0; i < fileList.length; i++) {
/*     */         
/* 593 */         ms_Logger.debug("Deleting " + fileList[i].getAbsolutePath());
/* 594 */         fileList[i].delete();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dispose(boolean graceful) {
/* 606 */     synchronized (this) {
/*     */       
/* 608 */       restoreGroups();
/* 609 */       Collection<ICacheImpl<K, V>> values = this.m_Groups.values();
/* 610 */       for (Iterator<ICacheImpl<K, V>> iterator = values.iterator(); iterator.hasNext(); ) {
/*     */         
/* 612 */         ICacheImpl<K, V> cache = iterator.next();
/*     */         
/* 614 */         if (cache != null) {
/*     */           
/* 616 */           if (!graceful)
/*     */           {
/*     */             
/* 619 */             cache.clear();
/*     */           }
/*     */ 
/*     */           
/* 623 */           this.m_Factory.closeCache(cache);
/*     */         } 
/*     */       } 
/* 626 */       if (!graceful) {
/*     */         
/* 628 */         deleteCache(this.m_UniqueName);
/* 629 */         this.m_Destroyed = true;
/* 630 */         this.m_Groups.clear();
/*     */       } 
/*     */     } 
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
/*     */   public synchronized void setThreadedDownloader(IThreadedDownloader<K, V> threadedDownloader) {
/* 644 */     this.m_ThreadedDownloader = threadedDownloader;
/* 645 */     executeThreadedDownload();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized IThreadedDownloader<K, V> getThreadedDownloader() {
/* 654 */     return this.m_ThreadedDownloader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitDownload() {
/*     */     try {
/*     */       Thread downloaderThread;
/* 665 */       synchronized (this) {
/*     */         
/* 667 */         downloaderThread = this.m_DownloaderThread;
/*     */       } 
/* 669 */       downloaderThread.join();
/*     */     }
/* 671 */     catch (InterruptedException downloaderThread) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void executeThreadedDownload() {
/* 678 */     if (this.m_ThreadedDownloader != null) {
/*     */       
/* 680 */       final Thread oldThread = this.m_DownloaderThread;
/* 681 */       if (oldThread != null)
/*     */       {
/* 683 */         oldThread.interrupt();
/*     */       }
/* 685 */       this.m_DownloaderThread = new Thread(new Runnable()
/*     */           {
/* 687 */             double m_MaxTroughput = 0.0D;
/* 688 */             long m_LastCalculation = 0L;
/* 689 */             long TIME_BETWEEN_CALCULATIONS = 300000L;
/* 690 */             int SLOWNESS_FACTOR = 10;
/*     */ 
/*     */ 
/*     */             
/*     */             public void run() {
/* 695 */               if (oldThread != null) {
/*     */                 
/*     */                 try {
/*     */                   
/* 699 */                   oldThread.join();
/*     */                 }
/* 701 */                 catch (InterruptedException e) {
/*     */                   
/* 703 */                   JLbsCache.ms_Logger.debug(e.getMessage(), e);
/*     */                 } 
/*     */               }
/* 706 */               IThreadedDownloader.JLbsDownloadedItem<K, V> currentItem = null;
/*     */ 
/*     */               
/* 709 */               label33: while (!Thread.currentThread().isInterrupted()) {
/*     */ 
/*     */                 
/* 712 */                 if (System.currentTimeMillis() - this.m_LastCalculation > this.TIME_BETWEEN_CALCULATIONS) {
/*     */                   
/* 714 */                   long timerStart = System.currentTimeMillis();
/* 715 */                   int NUMBER_OF_TESTS = 20;
/* 716 */                   for (int i = 0; i < 20; i++) {
/*     */                     
/* 718 */                     currentItem = JLbsCache.this.m_ThreadedDownloader.feedMe();
/* 719 */                     if (currentItem == null)
/*     */                       break label33; 
/* 721 */                     JLbsCache.this.put(currentItem.getKey(), currentItem.getItem(), currentItem.getGroup());
/*     */                   } 
/* 723 */                   this.m_LastCalculation = System.currentTimeMillis();
/* 724 */                   double timerDelta = (this.m_LastCalculation - timerStart);
/* 725 */                   this.TIME_BETWEEN_CALCULATIONS = (long)(timerDelta * 1000.0D);
/* 726 */                   this.m_MaxTroughput = timerDelta / 20.0D;
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 731 */                   double x = this.m_MaxTroughput * this.SLOWNESS_FACTOR;
/* 732 */                   long millis = (long)Math.floor(x);
/* 733 */                   int nanos = (int)((x - millis) * 1000000.0D);
/* 734 */                   currentItem = JLbsCache.this.m_ThreadedDownloader.feedMe();
/* 735 */                   if (currentItem == null)
/*     */                     break; 
/* 737 */                   JLbsCache.this.put(currentItem.getKey(), currentItem.getItem(), currentItem.getGroup());
/*     */ 
/*     */                   
/*     */                   try {
/* 741 */                     Thread.sleep(millis, nanos);
/*     */                   }
/* 743 */                   catch (InterruptedException e) {
/*     */                     
/* 745 */                     JLbsCache.ms_Logger.debug(e.getMessage(), e);
/*     */                     
/*     */                     return;
/*     */                   } 
/*     */                 } 
/* 750 */                 if (currentItem == null)
/*     */                   break; 
/*     */               }  }
/*     */           });
/* 754 */       this.m_DownloaderThread.setPriority(1);
/* 755 */       this.m_DownloaderThread.setName("Downloader Thread (" + getUniqueName() + ")");
/* 756 */       this.m_DownloaderThread.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final JLbsCachePolicy getCachePolicy() {
/* 762 */     return ms_RegisteredImplementors.get(getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static class JLbsCachePolicy
/*     */   {
/*     */     private boolean m_Persistent;
/*     */     private String m_CacheTypeName;
/*     */     
/*     */     JLbsCachePolicy(boolean persistent, Class<?> clazz) {
/* 772 */       this.m_Persistent = persistent;
/* 773 */       this.m_CacheTypeName = constructCacheTypeNameFromClass(clazz);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isPersistent() {
/* 784 */       return this.m_Persistent;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getCacheTypeName() {
/* 792 */       return this.m_CacheTypeName;
/*     */     }
/*     */ 
/*     */     
/*     */     static String constructCacheTypeNameFromClass(Class<?> clazz) {
/* 797 */       return clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1).toLowerCase();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */