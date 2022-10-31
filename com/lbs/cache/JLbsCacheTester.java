/*      */ package com.lbs.cache;
/*      */ 
/*      */ import com.lbs.console.ILbsAppender;
/*      */ import com.lbs.console.LbsAppenderFactory;
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.console.LbsLevel;
/*      */ import com.lbs.util.FileUtil;
/*      */ import com.lbs.util.StackUtil;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.File;
/*      */ import java.util.Calendar;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsCacheTester
/*      */ {
/*      */   public static final String CACHE_STORAGE_PATH = "./com/lbs/cache/ObjectCache";
/*      */   
/*      */   static {
/*   33 */     LbsConsole rootLogger = LbsConsole.getRootLogger();
/*   34 */     ILbsAppender appender = LbsAppenderFactory.createSocketAppender("cacheTesterAppender", "localhost", 4445, false);
/*   35 */     rootLogger.addAppender(appender);
/*   36 */     rootLogger.setLevel2(LbsLevel.OFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeForTest(Vector testResults) {
/*      */     try {
/*   48 */       File cacheDir = new File("./com/lbs/cache/ObjectCache");
/*      */       
/*   50 */       if (!cacheDir.exists()) {
/*   51 */         cacheDir.mkdir();
/*      */       }
/*      */       else {
/*      */         
/*   55 */         File[] fileList = cacheDir.listFiles();
/*   56 */         for (int i = 0; i < fileList.length; i++) {
/*   57 */           fileList[i].delete();
/*      */         }
/*      */       } 
/*   60 */       String cacheConfigFilePath = "./com/lbs/cache/TesterConfig.ccf";
/*   61 */       String propsStr = FileUtil.getFileContents(cacheConfigFilePath, false);
/*   62 */       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(propsStr.getBytes());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*   70 */     catch (Exception e) {
/*      */       
/*   72 */       TestResult.add(testResults, String.valueOf(e.getMessage()) + "\n" + StackUtil.getStack(e), ResultType.ERROR);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanup() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*      */     try {
/*   84 */       test();
/*      */     }
/*   86 */     catch (InterruptedException e) {
/*      */       
/*   88 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Vector test() throws InterruptedException {
/*   99 */     Vector testResults = new Vector();
/*  100 */     JLbsCacheTester tester = new JLbsCacheTester();
/*  101 */     tester.initializeForTest(testResults);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  106 */     tester.threadedDownloaderTest(testResults);
/*  107 */     tester.cleanup();
/*  108 */     return testResults;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void threadedDownloaderTest(Vector testResults) {
/*  113 */     TestResult.add(testResults, "Starting threadedDownloaderTest..", ResultType.INFO);
/*  114 */     int NUMBER_OF_TEST_TO_PERFORM = 1000;
/*  115 */     int MAX_BYTE_DATA_LENGTH = 1000;
/*      */     
/*  117 */     TestResult.add(testResults, "Tester will perform 1000 insertions with random byte arrays having sizes between 0 and 1000 bytes.", 
/*      */         
/*  119 */         ResultType.INFO);
/*      */     
/*  121 */     IVersionSource defaultVersionSource = new IVersionSource()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */         
/*      */         public String getName() {
/*  127 */           return "Tester";
/*      */         }
/*      */ 
/*      */         
/*      */         public String getVersion() {
/*  132 */           return "1.1.1";
/*      */         }
/*      */       };
/*  135 */     JLbsVersionBasedCache<Integer> cache = new JLbsVersionBasedCache("ThreadedDownloader", JLbsCacheScope.GLOBAL(), defaultVersionSource);
/*  136 */     cache.setThreadedDownloader(new IThreadedDownloader<Object, Object>()
/*      */         {
/*  138 */           int i = 0;
/*  139 */           Random random = new Random();
/*      */ 
/*      */           
/*      */           public IThreadedDownloader.JLbsDownloadedItem feedMe() {
/*  143 */             if (this.i < 1000) {
/*      */               
/*  145 */               byte[] bytes = new byte[this.random.nextInt(1000)];
/*  146 */               this.random.nextBytes(bytes);
/*      */               
/*      */               try {
/*  149 */                 Thread.sleep(3L);
/*      */               }
/*  151 */               catch (InterruptedException e) {
/*      */                 
/*  153 */                 return null;
/*      */               } 
/*  155 */               return new IThreadedDownloader.JLbsDownloadedItem<>(Integer.valueOf(this.i++), bytes);
/*      */             } 
/*      */             
/*  158 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  162 */     cache.waitDownload();
/*      */     
/*  164 */     TestResult.add(testResults, "Starting read tests..", ResultType.INFO);
/*  165 */     int numErrors = 0;
/*  166 */     for (int i = 999; i >= 0; i--) {
/*      */       
/*  168 */       Object x = cache.get(Integer.valueOf(i));
/*  169 */       if (x == null)
/*      */       {
/*  171 */         numErrors++;
/*      */       }
/*      */     } 
/*      */     
/*  175 */     if (numErrors > 0) {
/*  176 */       TestResult.add(testResults, "threadedDownloaderTest Test finished with errors (" + numErrors + " cache misses)", 
/*  177 */           ResultType.ERROR);
/*      */     } else {
/*  179 */       TestResult.add(testResults, "threadedDownloaderTest Test finisihed successfully", ResultType.SUCCESS);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void sessionBasedCacheTest(Vector testResults) {
/*  185 */     TestResult.add(testResults, "Starting sessionBasedCacheTest..", ResultType.INFO);
/*  186 */     int numErrors = 0;
/*      */     
/*  188 */     JLbsSessionBasedCache.JLbsSessionInfoProvider session1 = new JLbsSessionBasedCache.JLbsSessionInfoProvider("SESSION1");
/*  189 */     JLbsSessionBasedCache.JLbsSessionInfoProvider session2 = new JLbsSessionBasedCache.JLbsSessionInfoProvider("SESSION2");
/*  190 */     Caches c1 = Caches.constructSessionBasedCaches(session1);
/*  191 */     Caches c2 = Caches.constructSessionBasedCaches(session2);
/*      */     
/*  193 */     numErrors += accessibilityTest(testResults, c1);
/*  194 */     numErrors += accessibilityTest(testResults, c2);
/*      */ 
/*      */     
/*  197 */     TestResult.add(testResults, "Testing session handlers..", ResultType.INFO);
/*  198 */     String session1Item1 = "session1Item1";
/*  199 */     String session1Item2 = "session1Item2";
/*  200 */     String session2Item1 = "session2Item1";
/*  201 */     String session2Item2 = "session2Item2";
/*      */     
/*  203 */     c1.ismaild_AdminAndUser.put("session1Item1", "session1Item1");
/*  204 */     c1.globalCache.put("session1Item2", "session1Item2");
/*  205 */     c2.ismaild_AdminAndUser.put("session2Item1", "session2Item1");
/*  206 */     c2.globalCache.put("session2Item2", "session2Item2");
/*  207 */     ((JLbsCache)c1.ismaild_AdminAndUser).save();
/*  208 */     ((JLbsCache)c1.globalCache).save();
/*      */     
/*  210 */     if (!"session1Item1".equals(c1.ismaild_AdminAndUser.get("session1Item1"))) {
/*      */       
/*  212 */       TestResult.add(testResults, "Could not retireve session1Item1 correctly", ResultType.ERROR);
/*  213 */       numErrors++;
/*      */     } 
/*  215 */     if (!"session1Item2".equals(c1.ismaild_AdminAndUser.get("session1Item2"))) {
/*      */       
/*  217 */       TestResult.add(testResults, "Could not retireve session1Item2 correctly", ResultType.ERROR);
/*  218 */       numErrors++;
/*      */     } 
/*  220 */     if (!"session2Item1".equals(c2.ismaild_AdminAndUser.get("session2Item1"))) {
/*      */       
/*  222 */       TestResult.add(testResults, "Could not retireve session2Item1 correctly", ResultType.ERROR);
/*  223 */       numErrors++;
/*      */     } 
/*  225 */     if (!"session2Item2".equals(c2.ismaild_AdminAndUser.get("session2Item2"))) {
/*      */       
/*  227 */       TestResult.add(testResults, "Could not retireve session2Item2 correctly", ResultType.ERROR);
/*  228 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  232 */     TestResult.add(testResults, "Destroying session1..", ResultType.INFO);
/*  233 */     session1.fireSessionDestroyed();
/*      */     
/*  235 */     if (c1.ismaild_AdminAndUser.get("session1Item1") != null) {
/*      */       
/*  237 */       TestResult.add(testResults, "Retrieved destroyed item!", ResultType.ERROR);
/*  238 */       numErrors++;
/*      */     } 
/*  240 */     if (c1.ismaild_AdminAndUser.get("session1Item2") != null) {
/*      */       
/*  242 */       TestResult.add(testResults, "Retrieved destroyed item!", ResultType.ERROR);
/*  243 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  247 */     session2.fireSessionCreated();
/*  248 */     if (c2.ismaild_AdminAndUser.get("session2Item1") != null) {
/*      */       
/*  250 */       TestResult.add(testResults, "Retrieved cleared item!", ResultType.ERROR);
/*  251 */       numErrors++;
/*      */     } 
/*  253 */     c2.ismaild_AdminAndUser.put("session2Item1", "session2Item1");
/*  254 */     if (!"session2Item1".equals(c2.ismaild_AdminAndUser.get("session2Item1"))) {
/*      */       
/*  256 */       TestResult.add(testResults, "Could not retireve session2Item1 correctly", ResultType.ERROR);
/*  257 */       numErrors++;
/*      */     } 
/*  259 */     if (c2.ismaild_AdminAndUser.get("session2Item2") != null) {
/*      */       
/*  261 */       TestResult.add(testResults, "Retrieved cleared item!", ResultType.ERROR);
/*  262 */       numErrors++;
/*      */     } 
/*      */     
/*  265 */     session2.fireSessionDestroyed();
/*      */ 
/*      */     
/*  268 */     if (numErrors > 0) {
/*  269 */       TestResult.add(testResults, "sessionBasedCacheTest finished with errors (" + numErrors + " errors)", ResultType.ERROR);
/*      */     } else {
/*  271 */       TestResult.add(testResults, "sessionBasedCacheTest finisihed successfully", ResultType.SUCCESS);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void versionBasedCacheTest(Vector testResults) {
/*  276 */     TestResult.add(testResults, "Starting versionBasedCacheTest..", ResultType.INFO);
/*  277 */     final String[] version = { "1.0.0" };
/*  278 */     String item1Key = "item1";
/*  279 */     String item1v1Value = "item1_V1.0";
/*  280 */     String item1v2Value = "item1_V2.0";
/*  281 */     String unrecovarableItem = "itemUnrecovarable";
/*  282 */     String item2Key = "item2";
/*  283 */     String item2Value = "item2_AllVersions";
/*      */ 
/*      */     
/*  286 */     IVersionSource defaultVersionSource = new IVersionSource()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */         
/*      */         public String getName() {
/*  292 */           return "Tester";
/*      */         }
/*      */ 
/*      */         
/*      */         public String getVersion() {
/*  297 */           return version[0];
/*      */         }
/*      */       };
/*  300 */     ICacheListener<Object, Object> cacheListener = new ICacheListener<Object, Object>()
/*      */       {
/*      */         public Object cacheMiss(Object key, String group)
/*      */         {
/*  304 */           if ("item1".equals(key))
/*  305 */             return "item1_V2.0"; 
/*  306 */           return null;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean isValid(Object key, String group, Object item) {
/*  312 */           if ("item1".equals(key) && "item1_V2.0".equals(item))
/*  313 */             return true; 
/*  314 */           if ("item2".equals(key) && "item2_AllVersions".equals(item))
/*  315 */             return true; 
/*  316 */           if ("itemUnrecovarable".equals(key))
/*  317 */             return false; 
/*  318 */           return false;
/*      */         }
/*      */       };
/*      */     
/*  322 */     Caches c = Caches.constructVersionBasedCaches(defaultVersionSource);
/*      */ 
/*      */     
/*  325 */     int numErrors = 0;
/*  326 */     numErrors += accessibilityTest(testResults, c);
/*      */ 
/*      */     
/*  329 */     TestResult.add(testResults, "Testing version change handlers..", ResultType.INFO);
/*  330 */     ((JLbsCache<Object, Object>)c.ismaild_AdminAndUser).setCacheListener(cacheListener);
/*  331 */     ((JLbsCache<Object, Object>)c.globalCache).setCacheListener(cacheListener);
/*  332 */     c.ismaild_AdminAndUser.put("item1", "item1_V1.0");
/*  333 */     c.globalCache.put("item2", "item2_AllVersions");
/*  334 */     c.ismaild_AdminAndUser.put("itemUnrecovarable", "itemUnrecovarable");
/*  335 */     if (!"item1_V1.0".equals(c.ismaild_AdminAndUser.get("item1"))) {
/*      */       
/*  337 */       TestResult.add(testResults, "Could not retireve item1 correctly", ResultType.ERROR);
/*  338 */       numErrors++;
/*      */     } 
/*  340 */     if (!"item2_AllVersions".equals(c.ismaild_AdminAndUser.get("item2"))) {
/*      */       
/*  342 */       TestResult.add(testResults, "Could not retireve item2 correctly", ResultType.ERROR);
/*  343 */       numErrors++;
/*      */     } 
/*  345 */     if (!"itemUnrecovarable".equals(c.ismaild_AdminAndUser.get("itemUnrecovarable"))) {
/*      */       
/*  347 */       TestResult.add(testResults, "Could not retireve itemUnrecovarable correctly", ResultType.ERROR);
/*  348 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  352 */     TestResult.add(testResults, "Changing version..", ResultType.INFO);
/*  353 */     version[0] = "2.0.0";
/*      */     
/*  355 */     if (!"item1_V2.0".equals(c.ismaild_AdminAndUser.get("item1"))) {
/*      */       
/*  357 */       TestResult.add(testResults, "Could not retireve item1 correctly", ResultType.ERROR);
/*  358 */       numErrors++;
/*      */     } 
/*  360 */     if (!"item1_V2.0".equals(c.ismaild_AdminAndUser.get("item1"))) {
/*      */       
/*  362 */       TestResult.add(testResults, "Could not retireve item1 correctly", ResultType.ERROR);
/*  363 */       numErrors++;
/*      */     } 
/*  365 */     if (!"item2_AllVersions".equals(c.ismaild_AdminAndUser.get("item2"))) {
/*      */       
/*  367 */       TestResult.add(testResults, "Could not retireve item2 correctly", ResultType.ERROR);
/*  368 */       numErrors++;
/*      */     } 
/*  370 */     if (!"item2_AllVersions".equals(c.ismaild_AdminAndUser.get("item2"))) {
/*      */       
/*  372 */       TestResult.add(testResults, "Could not retireve item2 correctly", ResultType.ERROR);
/*  373 */       numErrors++;
/*      */     } 
/*  375 */     if (c.ismaild_AdminAndUser.get("itemUnrecovarable") != null) {
/*      */       
/*  377 */       TestResult.add(testResults, "Could not retireve itemUnrecovarable correctly", ResultType.ERROR);
/*  378 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  382 */     if (numErrors > 0) {
/*  383 */       TestResult.add(testResults, "versionBasedCacheTest finished with errors (" + numErrors + " errors)", ResultType.ERROR);
/*      */     } else {
/*  385 */       TestResult.add(testResults, "versionBasedCacheTest finisihed successfully", ResultType.SUCCESS);
/*      */     } 
/*      */   }
/*      */   
/*      */   private int accessibilityTest(Vector testResults, Caches c) {
/*  390 */     TestResult.add(testResults, "Starting accessibilityTest..", ResultType.INFO);
/*      */     
/*  392 */     int numErrors = 0;
/*      */ 
/*      */     
/*  395 */     c.globalCache.put("globalItem1", "global1");
/*  396 */     Object globalItem1 = c.globalCache.get("globalItem1");
/*      */     
/*  398 */     if (!"global1".equals(globalItem1)) {
/*      */       
/*  400 */       TestResult.add(testResults, "Retrieved item has wrong value! (globalItem1=" + globalItem1 + ")", ResultType.ERROR);
/*  401 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  405 */     c.globalCache.remove("globalItem1");
/*  406 */     globalItem1 = c.globalCache.get("globalItem1");
/*      */ 
/*      */     
/*  409 */     if (globalItem1 != null) {
/*      */       
/*  411 */       TestResult.add(testResults, "Remove test failed! (globalItem1)", ResultType.ERROR);
/*  412 */       numErrors++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  417 */     String globalItem2 = "globalItem2";
/*  418 */     String overridenItemKey = "overridenItem";
/*  419 */     String overridenItemGlobalValue = "globalCache";
/*  420 */     String overridenItemAdminsValue = "adminsCache";
/*  421 */     String overridenItemUsersValue = "usersCache";
/*  422 */     String overridenItemOnuri_Admin_Value = "onuri_Admin";
/*  423 */     String overridenItemErdalb_Guest_Value = "erdalb_Guest";
/*  424 */     String overridenItemIsmaild_AdminAndUser_Value = "ismaild_AdminAndUser";
/*  425 */     String adminItem1 = "adminItem1";
/*  426 */     String usersItem1 = "usersItem1";
/*  427 */     String usersItem2 = "usersItem2";
/*  428 */     String onuri = "onuri";
/*      */ 
/*      */     
/*  431 */     c.globalCache.put(globalItem2, globalItem2);
/*  432 */     c.globalCache.put(overridenItemKey, overridenItemGlobalValue);
/*      */ 
/*      */     
/*  435 */     c.adminsCache.put(adminItem1, adminItem1);
/*  436 */     c.adminsCache.put(overridenItemKey, overridenItemAdminsValue);
/*  437 */     c.usersCache.put(usersItem1, usersItem1);
/*  438 */     c.usersCache.put(overridenItemKey, overridenItemUsersValue);
/*  439 */     c.usersAndAdminsCache.put(usersItem2, usersItem2);
/*      */ 
/*      */     
/*  442 */     c.onuri_Admin.put(overridenItemKey, overridenItemOnuri_Admin_Value);
/*  443 */     c.onuri_Admin.put(onuri, onuri);
/*  444 */     c.erdalb_Guest.put(overridenItemKey, overridenItemErdalb_Guest_Value);
/*  445 */     c.ismaild_AdminAndUser.put(overridenItemKey, overridenItemIsmaild_AdminAndUser_Value);
/*      */ 
/*      */     
/*  448 */     TestResult.add(testResults, "Testing accessibility from globalCache", ResultType.INFO);
/*      */ 
/*      */     
/*  451 */     if (!globalItem2.equals(c.globalCache.get(globalItem2))) {
/*      */       
/*  453 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  454 */       numErrors++;
/*      */     } 
/*  456 */     if (!overridenItemGlobalValue.equals(c.globalCache.get(overridenItemKey))) {
/*      */       
/*  458 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  459 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  463 */     if (c.globalCache.get(adminItem1) != null || c.globalCache.get(usersItem1) != null || 
/*  464 */       c.globalCache.get(usersItem2) != null || c.globalCache.get(onuri) != null) {
/*      */       
/*  466 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  467 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  471 */     TestResult.add(testResults, "Testing accessibility from admins cache", ResultType.INFO);
/*      */ 
/*      */     
/*  474 */     if (!adminItem1.equals(c.adminsCache.get(adminItem1))) {
/*      */       
/*  476 */       TestResult.add(testResults, "Could not retireve " + adminItem1 + " correctly", ResultType.ERROR);
/*  477 */       numErrors++;
/*      */     } 
/*  479 */     if (!overridenItemAdminsValue.equals(c.adminsCache.get(overridenItemKey))) {
/*      */       
/*  481 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  482 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  486 */     if (!globalItem2.equals(c.adminsCache.get(globalItem2))) {
/*      */       
/*  488 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  489 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  493 */     if (c.adminsCache.get(usersItem1) != null || c.adminsCache.get(usersItem2) != null || c.adminsCache.get(onuri) != null) {
/*      */       
/*  495 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  496 */       numErrors++;
/*      */     } 
/*      */     
/*  499 */     TestResult.add(testResults, "Testing accessibility from users cache", ResultType.INFO);
/*      */ 
/*      */     
/*  502 */     if (!usersItem1.equals(c.usersCache.get(usersItem1))) {
/*      */       
/*  504 */       TestResult.add(testResults, "Could not retireve " + usersItem1 + " correctly", ResultType.ERROR);
/*  505 */       numErrors++;
/*      */     } 
/*  507 */     if (!overridenItemUsersValue.equals(c.usersCache.get(overridenItemKey))) {
/*      */       
/*  509 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  510 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  514 */     if (!usersItem2.equals(c.usersCache.get(usersItem2))) {
/*      */       
/*  516 */       TestResult.add(testResults, "Could not retireve " + usersItem2 + " correctly", ResultType.ERROR);
/*  517 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  521 */     if (!globalItem2.equals(c.usersCache.get(globalItem2))) {
/*      */       
/*  523 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  524 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  528 */     if (c.usersCache.get(adminItem1) != null || c.usersCache.get(onuri) != null) {
/*      */       
/*  530 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  531 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  535 */     TestResult.add(testResults, "Testing accessibility from userAndAdmins cache", ResultType.INFO);
/*      */ 
/*      */     
/*  538 */     if (!usersItem2.equals(c.usersAndAdminsCache.get(usersItem2))) {
/*      */       
/*  540 */       TestResult.add(testResults, "Could not retireve " + usersItem2 + " correctly", ResultType.ERROR);
/*  541 */       numErrors++;
/*      */     } 
/*  543 */     if (!overridenItemUsersValue.equals(c.usersAndAdminsCache.get(overridenItemKey))) {
/*      */       
/*  545 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  546 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  550 */     if (!usersItem1.equals(c.usersAndAdminsCache.get(usersItem1))) {
/*      */       
/*  552 */       TestResult.add(testResults, "Could not retireve " + usersItem1 + " correctly", ResultType.ERROR);
/*  553 */       numErrors++;
/*      */     } 
/*      */     
/*  556 */     if (!adminItem1.equals(c.usersAndAdminsCache.get(adminItem1))) {
/*      */       
/*  558 */       TestResult.add(testResults, "Could not retireve " + adminItem1 + " correctly", ResultType.ERROR);
/*  559 */       numErrors++;
/*      */     } 
/*  561 */     if (!globalItem2.equals(c.usersAndAdminsCache.get(globalItem2))) {
/*      */       
/*  563 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  564 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  568 */     if (c.usersAndAdminsCache.get(onuri) != null) {
/*      */       
/*  570 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  571 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  575 */     TestResult.add(testResults, "Testing accessibility from onuri_Admin cache", ResultType.INFO);
/*      */ 
/*      */     
/*  578 */     if (!onuri.equals(c.onuri_Admin.get(onuri))) {
/*      */       
/*  580 */       TestResult.add(testResults, "Could not retireve " + onuri + " correctly", ResultType.ERROR);
/*  581 */       numErrors++;
/*      */     } 
/*  583 */     if (!overridenItemOnuri_Admin_Value.equals(c.onuri_Admin.get(overridenItemKey))) {
/*      */       
/*  585 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  586 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  590 */     if (!adminItem1.equals(c.onuri_Admin.get(adminItem1))) {
/*      */       
/*  592 */       TestResult.add(testResults, "Could not retireve " + adminItem1 + " correctly", ResultType.ERROR);
/*  593 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  597 */     if (!globalItem2.equals(c.onuri_Admin.get(globalItem2))) {
/*      */       
/*  599 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  600 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  604 */     if (c.onuri_Admin.get(usersItem1) != null || c.onuri_Admin.get(usersItem2) != null) {
/*      */       
/*  606 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  607 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  611 */     TestResult.add(testResults, "Testing accessibility from erdalb_Guest cache", ResultType.INFO);
/*      */ 
/*      */     
/*  614 */     if (!overridenItemErdalb_Guest_Value.equals(c.erdalb_Guest.get(overridenItemKey))) {
/*      */       
/*  616 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  617 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  621 */     if (!globalItem2.equals(c.erdalb_Guest.get(globalItem2))) {
/*      */       
/*  623 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  624 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  628 */     if (c.erdalb_Guest.get(adminItem1) != null || c.erdalb_Guest.get(usersItem1) != null || 
/*  629 */       c.erdalb_Guest.get(usersItem2) != null || c.erdalb_Guest.get(onuri) != null) {
/*      */       
/*  631 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  632 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  636 */     TestResult.add(testResults, "Testing accessibility from ismaild_AdminAndUser cache", ResultType.INFO);
/*      */ 
/*      */     
/*  639 */     if (!overridenItemIsmaild_AdminAndUser_Value.equals(c.ismaild_AdminAndUser.get(overridenItemKey))) {
/*      */       
/*  641 */       TestResult.add(testResults, "Could not retrieve correct " + overridenItemKey + " value.", ResultType.ERROR);
/*  642 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  646 */     if (!adminItem1.equals(c.ismaild_AdminAndUser.get(adminItem1))) {
/*      */       
/*  648 */       TestResult.add(testResults, "Could not retireve " + adminItem1 + " correctly", ResultType.ERROR);
/*  649 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  653 */     if (!usersItem1.equals(c.ismaild_AdminAndUser.get(usersItem1))) {
/*      */       
/*  655 */       TestResult.add(testResults, "Could not retireve " + usersItem1 + " correctly", ResultType.ERROR);
/*  656 */       numErrors++;
/*      */     } 
/*  658 */     if (!usersItem2.equals(c.ismaild_AdminAndUser.get(usersItem2))) {
/*      */       
/*  660 */       TestResult.add(testResults, "Could not retireve " + usersItem2 + " correctly", ResultType.ERROR);
/*  661 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  665 */     if (!globalItem2.equals(c.ismaild_AdminAndUser.get(globalItem2))) {
/*      */       
/*  667 */       TestResult.add(testResults, "Could not retireve " + globalItem2 + " correctly", ResultType.ERROR);
/*  668 */       numErrors++;
/*      */     } 
/*      */ 
/*      */     
/*  672 */     if (c.ismaild_AdminAndUser.get(onuri) != null) {
/*      */       
/*  674 */       TestResult.add(testResults, "Accessed to a prohibited value!!", ResultType.ERROR);
/*  675 */       numErrors++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  680 */     c.clear();
/*      */     
/*  682 */     if (numErrors > 0) {
/*  683 */       TestResult.add(testResults, "accessibilityTest finished with errors (" + numErrors + " errors)", ResultType.ERROR);
/*      */     } else {
/*  685 */       TestResult.add(testResults, "accessibilityTest finisihed successfully", ResultType.SUCCESS);
/*  686 */     }  return numErrors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void multiThreadedTest(Vector testResults) throws InterruptedException {
/*  693 */     int NUMBER_OF_THREADS = 10;
/*  694 */     int NUMBER_OF_TEST_TO_PERFORM = 100000;
/*  695 */     int MAX_BYTE_DATA_LENGTH = 1000;
/*  696 */     boolean USE_INTEGERS_INSTEAD_OF_BYTE_DATA = false;
/*  697 */     TestResult.add(testResults, "Starting multiThreadedTest..", ResultType.INFO);
/*      */     class ThreadEx extends Thread { int startKey; int endKey; int numErrors; double avgPutRate; double avgGetRate;
/*      */       int numInvalidEntries;
/*      */       
/*      */       public ThreadEx(Runnable runnable) {
/*  702 */         super(runnable);
/*      */       } }
/*      */     ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  712 */     final ThreadEx[] threads = new ThreadEx[10];
/*  713 */     TestResult.add(testResults, "Tester will perform 100000 insertions with random byte arrays having sizes between 0 and 1000 bytes.", 
/*      */         
/*  715 */         ResultType.INFO);
/*  716 */     TestResult.add(testResults, "Tests will be applied by 10 threads in parallel.", ResultType.INFO);
/*      */     
/*  718 */     int increment = 1818;
/*  719 */     int lastKey = 0; int i;
/*  720 */     for (i = 0; i < threads.length; i++) {
/*      */       
/*  722 */       final int k = i;
/*  723 */       threads[k] = new ThreadEx(new Runnable()
/*      */           {
/*      */             public void run()
/*      */             {
/*  727 */               double[] results = JLbsCacheTester.this.multiThreadedTestUnit((threads[k]).startKey, (threads[k]).endKey, 1000, 
/*  728 */                   false);
/*  729 */               (threads[k]).numErrors = (int)results[0];
/*  730 */               (threads[k]).avgPutRate = results[1];
/*  731 */               (threads[k]).avgGetRate = results[2];
/*  732 */               (threads[k]).numInvalidEntries = (int)results[3];
/*      */             }
/*      */           });
/*      */       
/*  736 */       ThreadEx t = threads[k];
/*  737 */       t.setName("CacheTesterThread" + i);
/*  738 */       t.startKey = lastKey;
/*  739 */       t.endKey = (i == threads.length - 1) ? 
/*  740 */         100000 : (
/*  741 */         lastKey + (i + 1) * increment);
/*  742 */       lastKey = t.endKey;
/*      */     } 
/*      */     
/*  745 */     for (i = 0; i < threads.length; i++)
/*      */     {
/*  747 */       TestResult.add(testResults, "CacheTesterThread" + i + " will insert keys from " + (threads[i]).startKey + " to " + 
/*  748 */           (threads[i]).endKey + " (total= " + ((threads[i]).endKey - (threads[i]).startKey) + " keys)", ResultType.INFO);
/*      */     }
/*      */     
/*  751 */     for (i = 0; i < threads.length; i++)
/*      */     {
/*  753 */       threads[i].start();
/*      */     }
/*  755 */     for (i = 0; i < threads.length; i++)
/*      */     {
/*  757 */       threads[i].join();
/*      */     }
/*      */     
/*  760 */     int totalErrors = 0;
/*  761 */     double totalPutTime = 0.0D;
/*  762 */     double totalGetTime = 0.0D;
/*  763 */     int totalInvalidEntries = 0;
/*  764 */     for (int j = 0; j < threads.length; j++) {
/*      */       
/*  766 */       totalErrors += (threads[j]).numErrors;
/*  767 */       totalInvalidEntries += (threads[j]).numInvalidEntries;
/*  768 */       totalPutTime += (threads[j]).avgPutRate * ((threads[j]).endKey - (threads[j]).startKey);
/*  769 */       totalGetTime += (threads[j]).avgGetRate * ((threads[j]).endKey - (threads[j]).startKey);
/*      */     } 
/*      */     
/*  772 */     if (totalErrors > 0) {
/*  773 */       TestResult.add(testResults, "multiThreadedTest Test finished with errors (" + totalErrors + " cache misses)", 
/*  774 */           ResultType.ERROR);
/*  775 */     } else if (totalInvalidEntries > 0) {
/*  776 */       TestResult.add(testResults, "multiThreadedTest Test finished with errors (" + totalInvalidEntries + 
/*  777 */           " erroreneous reads)", ResultType.ERROR);
/*      */     } else {
/*  779 */       TestResult.add(testResults, "multiThreadedTest Test finisihed successfully", ResultType.SUCCESS);
/*  780 */     }  TestResult.add(testResults, "Cost per insertion=" + (totalPutTime / 100000.0D) + "msecs", ResultType.INFO);
/*  781 */     TestResult.add(testResults, "Cost per retrieval=" + (totalGetTime / 100000.0D) + "msecs", ResultType.INFO);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double[] multiThreadedTestUnit(int startKey, int endKey, int MAX_BYTE_DATA_LENGTH, boolean USE_INTEGERS_INSTEAD_OF_BYTE_DATA) {
/*  794 */     Random random = new Random();
/*      */     
/*  796 */     double overheadDelta = 0.0D;
/*  797 */     if (!USE_INTEGERS_INSTEAD_OF_BYTE_DATA) {
/*      */       
/*  799 */       long seed = random.nextLong();
/*  800 */       random = new Random(seed);
/*      */       
/*  802 */       long overheadStart = System.currentTimeMillis();
/*  803 */       int NUMBER_OF_TEST_TO_PERFORM2 = endKey - startKey;
/*  804 */       for (int k = 0; k < NUMBER_OF_TEST_TO_PERFORM2; k++) {
/*      */         
/*  806 */         byte[] bytes = new byte[random.nextInt(MAX_BYTE_DATA_LENGTH)];
/*  807 */         random.nextBytes(bytes);
/*      */       } 
/*  809 */       overheadDelta = (System.currentTimeMillis() - overheadStart);
/*      */       
/*  811 */       random = new Random(seed);
/*      */     } 
/*      */     
/*  814 */     IVersionSource defaultVersionSource = new IVersionSource()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */         
/*      */         public String getName() {
/*  820 */           return "TestVersion";
/*      */         }
/*      */ 
/*      */         
/*      */         public String getVersion() {
/*  825 */           return "1.0.0";
/*      */         }
/*      */       };
/*  828 */     JLbsVersionBasedCache<Integer> globalCache = new JLbsVersionBasedCache(JLbsCacheScope.GLOBAL(), defaultVersionSource);
/*      */     
/*  830 */     long putCostStart = System.currentTimeMillis();
/*  831 */     for (int i = startKey; i < endKey; i++) {
/*      */       
/*  833 */       if (!USE_INTEGERS_INSTEAD_OF_BYTE_DATA) {
/*      */         
/*  835 */         byte[] bytes = new byte[random.nextInt(MAX_BYTE_DATA_LENGTH)];
/*  836 */         random.nextBytes(bytes);
/*  837 */         globalCache.put(Integer.valueOf(i), bytes);
/*      */       } else {
/*      */         
/*  840 */         globalCache.put(Integer.valueOf(i), Integer.valueOf(i));
/*      */       } 
/*  842 */     }  double putCostDelta = (System.currentTimeMillis() - putCostStart) - overheadDelta;
/*      */     
/*  844 */     long getCostStart = System.currentTimeMillis();
/*  845 */     int numErrors = 0;
/*  846 */     int numInvalidEntries = 0;
/*      */     
/*  848 */     for (int j = endKey - 1; j >= startKey; j--) {
/*      */       
/*  850 */       Object x = globalCache.get(Integer.valueOf(j));
/*  851 */       if (x == null) {
/*  852 */         numErrors++;
/*  853 */       } else if (USE_INTEGERS_INSTEAD_OF_BYTE_DATA && ((Integer)x).intValue() != j) {
/*  854 */         numInvalidEntries++;
/*      */       } 
/*      */     } 
/*  857 */     double getCostDelta = (System.currentTimeMillis() - getCostStart);
/*  858 */     return new double[] { numErrors, putCostDelta / (endKey - startKey), getCostDelta / (endKey - startKey), numInvalidEntries };
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void simpleOneThreadedTest(Vector testResults) {
/*  864 */     int NUMBER_OF_TEST_TO_PERFORM = 10000;
/*  865 */     int MAX_BYTE_DATA_LENGTH = 1000;
/*      */ 
/*      */     
/*  868 */     boolean TRACK_MEMORY_USAGE = false;
/*  869 */     boolean CHECK_STABILITY = true;
/*      */     
/*  871 */     TestResult.add(testResults, "Starting simpleOneThreadedTest..", ResultType.INFO);
/*  872 */     Random random = new Random();
/*  873 */     long seed = random.nextLong();
/*  874 */     random = new Random(seed);
/*      */     
/*  876 */     long overheadStart = System.currentTimeMillis();
/*  877 */     for (int i = 0; i < NUMBER_OF_TEST_TO_PERFORM; i++) {
/*      */       
/*  879 */       byte[] bytes = new byte[random.nextInt(MAX_BYTE_DATA_LENGTH)];
/*  880 */       random.nextBytes(bytes);
/*      */     } 
/*  882 */     double overheadDelta = (System.currentTimeMillis() - overheadStart);
/*      */     
/*  884 */     random = new Random(seed);
/*      */     
/*  886 */     TestResult.add(testResults, "Tester will perform " + NUMBER_OF_TEST_TO_PERFORM + 
/*  887 */         " insertions with random byte arrays having sizes between 0 and " + MAX_BYTE_DATA_LENGTH + " bytes.", 
/*  888 */         ResultType.INFO);
/*  889 */     IVersionSource defaultVersionSource = new IVersionSource()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */         
/*      */         public String getName() {
/*  895 */           return "TestVersion";
/*      */         }
/*      */ 
/*      */         
/*      */         public String getVersion() {
/*  900 */           return "1.0.0";
/*      */         }
/*      */       };
/*  903 */     String GROUP = "UN";
/*  904 */     JLbsVersionBasedCache<Integer> globalCache = new JLbsVersionBasedCache(JLbsCacheScope.GLOBAL(), defaultVersionSource);
/*  905 */     long putCostStart = System.currentTimeMillis();
/*  906 */     for (int j = 0; j < NUMBER_OF_TEST_TO_PERFORM; j++) {
/*      */       
/*  908 */       byte[] bytes = new byte[random.nextInt(MAX_BYTE_DATA_LENGTH)];
/*  909 */       random.nextBytes(bytes);
/*  910 */       globalCache.put(Integer.valueOf(j), bytes, GROUP);
/*      */       
/*  912 */       if (CHECK_STABILITY && (j + 1) % 10000 == 0 && j > 0) {
/*      */         
/*  914 */         TestResult.add(testResults, "Checking stability", ResultType.INFO);
/*      */         
/*  916 */         Set<Integer> keys = globalCache.keySet(GROUP, -1);
/*      */         
/*  918 */         if (keys.size() != j + 1)
/*      */         {
/*  920 */           TestResult.add(testResults, "keySet() stability test failed (" + keys.size() + " of " + (j + 1) + 
/*  921 */               " keys retrieved)", ResultType.ERROR);
/*      */         }
/*  923 */         int size = globalCache.size(GROUP);
/*  924 */         if (size != j + 1)
/*      */         {
/*  926 */           TestResult.add(testResults, "size() stability test failed (Retrieved size=" + size + ", correct value=" + (
/*  927 */               j + 1), ResultType.ERROR);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  932 */       if (TRACK_MEMORY_USAGE && j % 10000 == 0 && j > 0) {
/*      */         
/*  934 */         for (int m = 0; m < 10; m++)
/*      */         {
/*  936 */           System.gc();
/*      */         }
/*  938 */         TestResult.add(testResults, String.valueOf(j) + " data item inserted", ResultType.INFO);
/*  939 */         TestResult.add(testResults, "Used mem: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 
/*  940 */             1048576L) + "Mb", ResultType.INFO);
/*      */       } 
/*      */     } 
/*      */     
/*  944 */     double putCostDelta = (System.currentTimeMillis() - putCostStart) - overheadDelta;
/*      */     
/*  946 */     TestResult.add(testResults, "Insertions completed, (Cost per insertion=" + (putCostDelta / NUMBER_OF_TEST_TO_PERFORM) + 
/*  947 */         "msecs)", ResultType.INFO);
/*      */     
/*  949 */     TestResult.add(testResults, "Starting read tests..", ResultType.INFO);
/*  950 */     long getCostStart = System.currentTimeMillis();
/*  951 */     int numErrors = 0;
/*      */     
/*  953 */     for (int k = NUMBER_OF_TEST_TO_PERFORM - 1; k >= 0; k--) {
/*      */       
/*  955 */       Object x = globalCache.get(Integer.valueOf(k), GROUP);
/*  956 */       if (x == null)
/*      */       {
/*  958 */         numErrors++;
/*      */       }
/*      */     } 
/*  961 */     double getCostDelta = (System.currentTimeMillis() - getCostStart);
/*      */     
/*  963 */     if (numErrors > 0) {
/*  964 */       TestResult.add(testResults, "simpleOneThreadedTest Test finished with errors (" + numErrors + " cache misses)", 
/*  965 */           ResultType.ERROR);
/*      */     } else {
/*  967 */       TestResult.add(testResults, "simpleOneThreadedTest Test finisihed successfully", ResultType.SUCCESS);
/*  968 */     }  TestResult.add(testResults, "Cost per retrieval=" + (getCostDelta / NUMBER_OF_TEST_TO_PERFORM) + "msecs", ResultType.INFO);
/*      */   }
/*      */   static class Caches { ICache globalCache; ICache adminsCache; ICache usersCache;
/*      */     
/*      */     Caches() {
/*  973 */       this.globalCache = null;
/*  974 */       this.adminsCache = null;
/*  975 */       this.usersCache = null;
/*  976 */       this.usersAndAdminsCache = null;
/*  977 */       this.onuri_Admin = null;
/*  978 */       this.erdalb_Guest = null;
/*  979 */       this.ismaild_AdminAndUser = null;
/*      */     }
/*      */     ICache usersAndAdminsCache; ICache onuri_Admin; ICache erdalb_Guest; ICache ismaild_AdminAndUser;
/*      */     static Caches constructVersionBasedCaches(IVersionSource versionSource) {
/*  983 */       Caches caches = new Caches();
/*  984 */       caches.globalCache = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.global, versionSource);
/*  985 */       caches.adminsCache = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.adminGroupOnly, versionSource);
/*  986 */       caches.usersCache = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.userGroupOnly, versionSource);
/*  987 */       caches.usersAndAdminsCache = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.userAndAdminGroup, versionSource);
/*  988 */       caches.onuri_Admin = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.onuri_Admin, versionSource);
/*  989 */       caches.erdalb_Guest = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.erdalb_Guest, versionSource);
/*  990 */       caches.ismaild_AdminAndUser = new JLbsVersionBasedCache(JLbsCacheTester.Scopes.ismaild_AdminAndUser, versionSource);
/*  991 */       return caches;
/*      */     }
/*      */ 
/*      */     
/*      */     static Caches constructSessionBasedCaches(JLbsSessionBasedCache.JLbsSessionInfoProvider sessionInfoProvider) {
/*  996 */       Caches caches = new Caches();
/*  997 */       caches.globalCache = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.global, sessionInfoProvider);
/*  998 */       caches.adminsCache = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.adminGroupOnly, sessionInfoProvider);
/*  999 */       caches.usersCache = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.userGroupOnly, sessionInfoProvider);
/* 1000 */       caches.usersAndAdminsCache = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.userAndAdminGroup, sessionInfoProvider);
/* 1001 */       caches.onuri_Admin = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.onuri_Admin, sessionInfoProvider);
/* 1002 */       caches.erdalb_Guest = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.erdalb_Guest, sessionInfoProvider);
/* 1003 */       caches.ismaild_AdminAndUser = new JLbsSessionBasedCache<>(JLbsCacheTester.Scopes.ismaild_AdminAndUser, sessionInfoProvider);
/* 1004 */       return caches;
/*      */     }
/*      */ 
/*      */     
/*      */     void clear() {
/* 1009 */       this.globalCache.clear();
/* 1010 */       this.adminsCache.clear();
/* 1011 */       this.usersCache.clear();
/* 1012 */       this.usersAndAdminsCache.clear();
/* 1013 */       this.onuri_Admin.clear();
/* 1014 */       this.erdalb_Guest.clear();
/* 1015 */       this.ismaild_AdminAndUser.clear();
/*      */     }
/*      */ 
/*      */     
/*      */     void save() {
/* 1020 */       ((JLbsCache)this.globalCache).save();
/* 1021 */       ((JLbsCache)this.adminsCache).save();
/* 1022 */       ((JLbsCache)this.usersCache).save();
/* 1023 */       ((JLbsCache)this.usersAndAdminsCache).save();
/* 1024 */       ((JLbsCache)this.onuri_Admin).save();
/* 1025 */       ((JLbsCache)this.erdalb_Guest).save();
/* 1026 */       ((JLbsCache)this.ismaild_AdminAndUser).save();
/*      */     } }
/*      */ 
/*      */   
/*      */   static class Scopes
/*      */   {
/* 1032 */     static final JLbsCacheScope global = JLbsCacheScope.GLOBAL();
/* 1033 */     static final JLbsCacheScope adminGroupOnly = JLbsCacheScope.GROUP(new int[] { JLbsCacheTester.Groups.administrators.groupID });
/* 1034 */     static final JLbsCacheScope userGroupOnly = JLbsCacheScope.GROUP(new int[] { JLbsCacheTester.Groups.systemUsers.groupID });
/* 1035 */     static final JLbsCacheScope userAndAdminGroup = JLbsCacheScope.GROUP(new int[] { JLbsCacheTester.Groups.systemUsers.groupID, 
/* 1036 */           JLbsCacheTester.Groups.administrators.groupID });
/*      */     
/* 1038 */     static final JLbsCacheScope onuri_Admin = JLbsCacheTester.Users.scopeFromUser(JLbsCacheTester.Users.onuri);
/* 1039 */     static final JLbsCacheScope erdalb_Guest = JLbsCacheTester.Users.scopeFromUser(JLbsCacheTester.Users.erdalb);
/* 1040 */     static final JLbsCacheScope ismaild_AdminAndUser = JLbsCacheTester.Users.scopeFromUser(JLbsCacheTester.Users.ismaild);
/*      */   }
/*      */   
/*      */   static class Groups
/*      */   {
/* 1045 */     static JLbsCacheTester.Group administrators = new JLbsCacheTester.Group("administrators", 1);
/* 1046 */     static JLbsCacheTester.Group systemUsers = new JLbsCacheTester.Group("systemUsers", 2);
/* 1047 */     static JLbsCacheTester.Group guests = new JLbsCacheTester.Group("guests", 3);
/*      */   }
/*      */   
/*      */   static class Users
/*      */   {
/* 1052 */     static JLbsCacheTester.User onuri = new JLbsCacheTester.User("Onur Idrisoglu", 100, new JLbsCacheTester.Group[] { JLbsCacheTester.Groups.administrators });
/* 1053 */     static JLbsCacheTester.User erdalb = new JLbsCacheTester.User("Erdal BAS", 101, new JLbsCacheTester.Group[] { JLbsCacheTester.Groups.guests });
/* 1054 */     static JLbsCacheTester.User ismaild = new JLbsCacheTester.User("Ismail DURAN", 102, new JLbsCacheTester.Group[] { JLbsCacheTester.Groups.administrators, JLbsCacheTester.Groups.systemUsers });
/*      */ 
/*      */     
/*      */     static JLbsCacheScope scopeFromUser(JLbsCacheTester.User user) {
/* 1058 */       int[] groupIDs = new int[user.groups.length];
/* 1059 */       for (int i = 0; i < groupIDs.length; i++)
/* 1060 */         groupIDs[i] = (user.groups[i]).groupID; 
/* 1061 */       return JLbsCacheScope.USER(user.userID, groupIDs);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class User
/*      */   {
/*      */     int userID;
/*      */     String userName;
/*      */     JLbsCacheTester.Group[] groups;
/*      */     
/*      */     public User(String userName, int userID, JLbsCacheTester.Group[] groups) {
/* 1073 */       this.userName = userName;
/* 1074 */       this.userID = userID;
/* 1075 */       this.groups = groups;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1080 */       return String.valueOf(this.userID) + "(" + this.userName + ")";
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class Group
/*      */   {
/*      */     int groupID;
/*      */     String groupName;
/*      */     
/*      */     public Group(String groupName, int groupID) {
/* 1091 */       this.groupName = groupName;
/* 1092 */       this.groupID = groupID;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1097 */       return String.valueOf(this.groupID) + "(" + this.groupName + ")";
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ResultType
/*      */   {
/* 1103 */     public static final ResultType UNSPECIFIED = new ResultType(0);
/* 1104 */     public static final ResultType ERROR = new ResultType(1);
/* 1105 */     public static final ResultType SUCCESS = new ResultType(2);
/* 1106 */     public static final ResultType INFO = new ResultType(3);
/*      */     
/*      */     private int m_Type;
/*      */ 
/*      */     
/*      */     private ResultType(int type) {
/* 1112 */       this.m_Type = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 1120 */       return super.hashCode();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1127 */       if (obj instanceof ResultType)
/* 1128 */         return (this.m_Type == ((ResultType)obj).m_Type); 
/* 1129 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1134 */       switch (this.m_Type) {
/*      */         
/*      */         case 0:
/* 1137 */           return "UNSPECIFIED";
/*      */         case 1:
/* 1139 */           return "ERROR";
/*      */         case 2:
/* 1141 */           return "SUCCESS";
/*      */         case 3:
/* 1143 */           return "INFO";
/*      */       } 
/* 1145 */       return "UNKNOWN";
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class TestResult
/*      */   {
/*      */     public String message;
/*      */     
/*      */     public JLbsCacheTester.ResultType resultType;
/*      */     public final Calendar entryTime;
/*      */     
/*      */     public TestResult(String message, JLbsCacheTester.ResultType resultType) {
/* 1158 */       this.message = message;
/* 1159 */       this.resultType = resultType;
/* 1160 */       this.entryTime = Calendar.getInstance();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static void add(Vector<TestResult> testResults, String message, JLbsCacheTester.ResultType type) {
/* 1166 */       TestResult testResult = new TestResult(message, type);
/* 1167 */       testResults.add(testResult);
/* 1168 */       if (type.equals(JLbsCacheTester.ResultType.ERROR)) {
/*      */         
/* 1170 */         System.err.println(testResult);
/* 1171 */         System.err.flush();
/*      */       }
/*      */       else {
/*      */         
/* 1175 */         System.out.println(testResult);
/* 1176 */         System.out.flush();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public static TestResult[] extractResults(Vector testResults) {
/* 1182 */       return (TestResult[])testResults.toArray((Object[])new TestResult[testResults.size()]);
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1187 */       String message = String.valueOf(this.entryTime.get(1)) + "-";
/* 1188 */       message = String.valueOf(message) + (this.entryTime.get(2) + 1) + "-";
/* 1189 */       message = String.valueOf(message) + this.entryTime.get(5) + " ";
/* 1190 */       message = String.valueOf(message) + this.entryTime.get(11) + ":";
/* 1191 */       message = String.valueOf(message) + this.entryTime.get(12) + ":";
/* 1192 */       message = String.valueOf(message) + this.entryTime.get(13) + ",";
/* 1193 */       message = String.valueOf(message) + this.entryTime.get(14) + " ";
/* 1194 */       message = String.valueOf(message) + "[" + this.resultType + "] " + this.message;
/* 1195 */       return message;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsCacheTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */