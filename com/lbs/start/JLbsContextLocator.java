/*     */ package com.lbs.start;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.security.AllPermission;
/*     */ import java.security.CodeSource;
/*     */ import java.security.PermissionCollection;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.security.cert.Certificate;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import java.util.TimeZone;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarInputStream;
/*     */ import java.util.zip.CRC32;
/*     */ 
/*     */ public class JLbsContextLocator
/*     */   extends ClassLoader
/*     */ {
/*     */   public static final String VERSION_E_DEFTER = "Version.EDefter";
/*     */   public static final String VERSION_BUGFIX = "Version.Bugfix";
/*     */   public static final String VERSION_RELEASE = "Version.Release";
/*     */   public static final String VERSION_MINOR = "Version.Minor";
/*     */   public static final String VERSION_MAJOR = "Version.Major";
/*     */   protected static final PermissionCollection ALL_PERMISSIONS;
/*     */   private static ArrayList<JLbsContextLocator> ms_Instances;
/*  43 */   private static String ms_RootURI = "";
/*  44 */   private static String ms_UnityVersion = "";
/*     */   
/*     */   private static int ms_UnityVersionMajor;
/*     */   
/*     */   private static int ms_UnityVersionMinor;
/*     */   private static int ms_UnityVersionRelease;
/*     */   private static int ms_UnityVersionBugFix;
/*     */   private static String ms_UnityVersionEDefter;
/*     */   private static Properties ms_ProductVersionProperties;
/*     */   private static HashSet<String> ms_BlackList;
/*     */   private CopyOnWriteArrayList<ILbsClassLoaderDelegate> m_Delegates;
/*     */   private Hashtable<String, Object> m_Files;
/*     */   
/*     */   static {
/*  58 */     ms_Instances = new ArrayList<>();
/*  59 */     AllPermission allPerm = new AllPermission();
/*  60 */     ALL_PERMISSIONS = allPerm.newPermissionCollection();
/*  61 */     if (ALL_PERMISSIONS != null)
/*  62 */       ALL_PERMISSIONS.add(allPerm); 
/*  63 */     ms_BlackList = new HashSet<>();
/*  64 */     ms_BlackList.add("javax.servlet.ServletContext");
/*  65 */     ms_BlackList.add("org.slf4j.ext.EventData");
/*  66 */     ms_BlackList.add("com.jidesoft.docking.Product");
/*  67 */     ms_BlackList.add("com.jidesoft.action.Product");
/*  68 */     ms_BlackList.add("com.jidesoft.wizard.Product");
/*  69 */     ms_BlackList.add("com.jidesoft.shortcut.Product");
/*  70 */     ms_BlackList.add("com.jidesoft.editor.Product");
/*  71 */     ms_BlackList.add("com.jidesoft.rss.Product");
/*  72 */     ms_BlackList.add("com.jidesoft.treemap.Product");
/*  73 */     ms_BlackList.add("com.jidesoft.chart.Product");
/*  74 */     ms_BlackList.add("com.jidesoft.diff.Product");
/*  75 */     ms_BlackList.add("com.jidesoft.plaf.basic.BasicInitializer");
/*  76 */     ms_BlackList.add("com.jidesoft.plaf.metal.MetalInitializer");
/*  77 */     ms_BlackList.add("com.jidesoft.plaf.skinable.SkinableInitializer");
/*  78 */     ms_BlackList.add("com.jidesoft.plaf.desktop.DesktopInitializer");
/*  79 */     ms_BlackList.add("com.jidesoft.plaf.basic.BasicCustomizer");
/*  80 */     ms_BlackList.add("com.jidesoft.plaf.metal.MetalCustomizer");
/*  81 */     ms_BlackList.add("com.jidesoft.plaf.skinable.SkinableCustomizer");
/*  82 */     ms_BlackList.add("com.jidesoft.plaf.desktop.DesktopCustomizer");
/*  83 */     ms_BlackList.add("net.sf.ehcache.EnterpriseFeaturesManager");
/*  84 */     ms_BlackList.add("org.fusesource.jansi.WindowsAnsiOutputStream");
/*  85 */     ms_BlackList.add("com.fasterxml.jackson.databind.ObjectMapper");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsContextLocator(ClassLoader contextClassLoader) {
/*  93 */     super(contextClassLoader);
/*  94 */     add2Instances();
/*  95 */     this.m_Delegates = new CopyOnWriteArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   class LCL_URLStreamHandler
/*     */     extends URLStreamHandler
/*     */   {
/*     */     private String m_Name;
/*     */     
/*     */     public LCL_URLStreamHandler(String name) {
/* 105 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     protected URLConnection openConnection(URL u) {
/* 110 */       return new JLbsContextLocator.LCL_URLConnection(u, this.m_Name);
/*     */     }
/*     */   }
/*     */   
/*     */   class LCL_URLConnection
/*     */     extends URLConnection
/*     */   {
/*     */     private String m_Name;
/*     */     
/*     */     public LCL_URLConnection(URL u, String name) {
/* 120 */       super(u);
/* 121 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void connect() {}
/*     */ 
/*     */     
/*     */     public InputStream getInputStream() {
/* 130 */       return JLbsContextLocator.this.getResourceAsStream(this.m_Name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<URL> getResources(String name) throws IOException {
/* 137 */     if (this.m_Delegates == null || this.m_Delegates.size() == 0) {
/*     */       
/* 139 */       final URL url = null;
/* 140 */       if (this.m_Files != null && this.m_Files.containsKey(name)) {
/*     */         
/*     */         try {
/*     */           
/* 144 */           url = new URL(new URL(ms_RootURI), name, new LCL_URLStreamHandler(name));
/*     */         }
/* 146 */         catch (Exception exception) {}
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 151 */       if (url != null)
/*     */       {
/* 153 */         final URL fURL = url;
/* 154 */         return new Enumeration<URL>()
/*     */           {
/* 156 */             private int m_Counter = 0;
/*     */ 
/*     */ 
/*     */             
/*     */             public boolean hasMoreElements() {
/* 161 */               return (this.m_Counter < 1);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public URL nextElement() {
/* 167 */               this.m_Counter++;
/* 168 */               return fURL;
/*     */             }
/*     */           };
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 175 */       for (ILbsClassLoaderDelegate delegate : this.m_Delegates) {
/*     */         
/* 177 */         final URL url = delegate.findLocalResourceURL(name);
/* 178 */         if (url != null) {
/* 179 */           return new Enumeration<URL>()
/*     */             {
/*     */               
/* 182 */               private int m_Counter = 0;
/*     */ 
/*     */ 
/*     */               
/*     */               public boolean hasMoreElements() {
/* 187 */                 return (this.m_Counter < 1);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public URL nextElement() {
/* 193 */                 this.m_Counter++;
/* 194 */                 return url;
/*     */               }
/*     */             };
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     return super.getResources(name);
/*     */   }
/*     */ 
/*     */   
/*     */   private void add2Instances() {
/* 206 */     synchronized (JLbsContextLocator.class) {
/*     */       
/* 208 */       ms_Instances.add(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsContextLocator(String[] urls, ClassLoader parent) {
/* 214 */     super(parent);
/* 215 */     add2Instances();
/* 216 */     this.m_Delegates = new CopyOnWriteArrayList<>();
/* 217 */     this.m_Files = new Hashtable<>();
/* 218 */     if (urls != null)
/* 219 */       for (int i = 0; i < urls.length; i++) {
/* 220 */         loadURL(urls[i]);
/*     */       } 
/*     */   }
/*     */   
/*     */   public JLbsContextLocator(String[] urls, String rootURI, ClassLoader parent) {
/* 225 */     super(parent);
/* 226 */     if (rootURI != null && rootURI.length() > 0)
/* 227 */       ms_RootURI = rootURI; 
/* 228 */     ms_UnityVersion = readUnityVersion();
/* 229 */     add2Instances();
/* 230 */     this.m_Delegates = new CopyOnWriteArrayList<>();
/* 231 */     this.m_Files = new Hashtable<>();
/* 232 */     if (urls != null)
/* 233 */       for (int i = 0; i < urls.length; i++) {
/* 234 */         loadURL(urls[i], getCachePath());
/*     */       } 
/*     */   }
/*     */   
/*     */   public String getCachePath() {
/* 239 */     if (ms_UnityVersion != null && !ms_UnityVersion.equals("")) {
/*     */       
/* 241 */       int pos = ms_UnityVersion.indexOf(".", ms_UnityVersion.indexOf(".") + 1);
/* 242 */       return "Cache_" + ms_UnityVersion.substring(0, pos);
/*     */     } 
/* 244 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearInstances() {
/* 249 */     synchronized (JLbsContextLocator.class) {
/*     */       
/* 251 */       while (ms_Instances.size() > 1) {
/*     */         
/* 253 */         JLbsContextLocator ctxLocator = ms_Instances.get(0);
/* 254 */         ms_Instances.remove(0);
/* 255 */         if (ctxLocator != null && ctxLocator.m_Files != null) {
/*     */           
/* 257 */           ctxLocator.m_Files.clear();
/* 258 */           ctxLocator = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areEqualNoCase(String s1, String s2) {
/* 266 */     if (s1 == null || s2 == null)
/* 267 */       return (s1 == s2); 
/* 268 */     return (s1.compareToIgnoreCase(s2) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsClassLoaderDelegate getDelegate(String guid) {
/* 273 */     if (this.m_Delegates == null) {
/* 274 */       return null;
/*     */     }
/* 276 */     for (int i = 0; i < this.m_Delegates.size(); i++) {
/*     */       
/* 278 */       ILbsClassLoaderDelegate delegate = this.m_Delegates.get(i);
/*     */       
/* 280 */       if (areEqualNoCase(guid, delegate.getGUID())) {
/* 281 */         return delegate;
/*     */       }
/*     */     } 
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDelegate(ILbsClassLoaderDelegate delegate) {
/* 289 */     this.m_Delegates.add(delegate);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDelegate(ILbsClassLoaderDelegate delegate) {
/* 294 */     this.m_Delegates.remove(delegate);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadURL(String urlPath) {
/* 299 */     loadURL(urlPath, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadURL(String urlPath, String versionFolder) {
/* 304 */     if (urlPath == null) {
/*     */       return;
/*     */     }
/*     */     try {
/*     */       InputStream stream;
/* 309 */       if (versionFolder != null && versionFolder.length() > 0) {
/* 310 */         stream = downloadURL(urlPath, versionFolder);
/*     */       } else {
/* 312 */         stream = downloadURL(urlPath);
/* 313 */       }  JarInputStream jarStream = new JarInputStream(stream, true);
/* 314 */       JarEntry entry = jarStream.getNextJarEntry();
/* 315 */       while (entry != null) {
/*     */         
/* 317 */         if (!entry.isDirectory())
/* 318 */           this.m_Files.put(entry.getName(), readContents(jarStream)); 
/* 319 */         entry = jarStream.getNextJarEntry();
/*     */       } 
/* 321 */       stream.close();
/*     */     }
/* 323 */     catch (Exception e) {
/*     */       
/* 325 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String readUnityVersion() {
/* 331 */     String versionStr = "";
/*     */     
/*     */     try {
/* 334 */       Properties props = readProductVersion();
/*     */       
/* 336 */       int prop = getIntProp(props, "Version.Major");
/* 337 */       if (prop != Integer.MIN_VALUE) {
/* 338 */         ms_UnityVersionMajor = prop;
/*     */       }
/* 340 */       prop = getIntProp(props, "Version.Minor");
/* 341 */       if (prop != Integer.MIN_VALUE) {
/* 342 */         ms_UnityVersionMinor = prop;
/*     */       }
/* 344 */       prop = getIntProp(props, "Version.Release");
/* 345 */       if (prop != Integer.MIN_VALUE) {
/* 346 */         ms_UnityVersionRelease = prop;
/*     */       }
/* 348 */       prop = getIntProp(props, "Version.Bugfix");
/* 349 */       if (prop != Integer.MIN_VALUE) {
/* 350 */         ms_UnityVersionBugFix = prop;
/*     */       }
/* 352 */       String value = props.getProperty("Version.EDefter");
/* 353 */       if (value != null && value.length() > 0) {
/* 354 */         ms_UnityVersionEDefter = value;
/*     */       }
/* 356 */       versionStr = "v" + ms_UnityVersionMajor + "." + ms_UnityVersionMinor + "." + ms_UnityVersionRelease + "." + ms_UnityVersionBugFix;
/*     */     
/*     */     }
/* 359 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 362 */     return versionStr;
/*     */   }
/*     */ 
/*     */   
/*     */   private Properties readProductVersion() throws Exception, IOException {
/* 367 */     String verURL = "";
/* 368 */     if (ms_RootURI.lastIndexOf("/") == ms_RootURI.length() - 1) {
/* 369 */       verURL = ms_RootURI + "Config/ProductVersion.txt";
/*     */     } else {
/* 371 */       verURL = ms_RootURI + "/Config/ProductVersion.txt";
/* 372 */     }  InputStream stream = downloadURL(verURL, false);
/* 373 */     ms_ProductVersionProperties = new Properties();
/* 374 */     ms_ProductVersionProperties.load(stream);
/* 375 */     return ms_ProductVersionProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int getIntProp(Properties props, String propName) {
/* 380 */     String prop = props.getProperty(propName);
/* 381 */     if (prop != null && prop.length() > 0) {
/*     */       
/*     */       try {
/*     */         
/* 385 */         return Integer.parseInt(prop);
/*     */       }
/* 387 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     return Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean getBooleanProp(Properties props, String propName) {
/* 398 */     String prop = props.getProperty(propName);
/* 399 */     if (prop != null && prop.length() > 0) {
/*     */       
/*     */       try {
/*     */         
/* 403 */         return Boolean.parseBoolean(prop);
/*     */       }
/* 405 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 410 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream downloadURL(String urlPath, boolean saveToCache) throws Exception {
/*     */     try {
/* 417 */       String filePath = getCacheFileName(getFilePath(urlPath, getCachePath()));
/* 418 */       String stamp = getFileTimeStamp(filePath);
/* 419 */       URL uRL = new URL(urlPath);
/* 420 */       URLConnection conn = uRL.openConnection();
/* 421 */       if (conn instanceof HttpURLConnection)
/*     */       {
/* 423 */         HttpURLConnection http = (HttpURLConnection)conn;
/* 424 */         http.setRequestMethod("GET");
/* 425 */         http.setUseCaches(true);
/* 426 */         http.setAllowUserInteraction(false);
/* 427 */         HttpURLConnection.setFollowRedirects(true);
/* 428 */         http.setDoOutput(false);
/* 429 */         http.setDoInput(true);
/* 430 */         if (stamp != null) {
/*     */           
/* 432 */           http.setRequestProperty("Last-Modified", stamp);
/* 433 */           http.setRequestProperty("If-Modified-Since", stamp);
/*     */         } 
/* 435 */         http.connect();
/* 436 */         InputStream inStream = http.getInputStream();
/* 437 */         int respCode = http.getResponseCode();
/* 438 */         byte[] data = null;
/* 439 */         if (respCode >= 200 && respCode < 300) {
/*     */           
/* 441 */           data = readStream(inStream);
/* 442 */           if (saveToCache)
/*     */           {
/* 444 */             long date = http.getHeaderFieldDate("Last-Modified", 0L);
/* 445 */             saveToCache(filePath, data, date);
/*     */           }
/*     */         
/*     */         }
/* 449 */         else if (respCode >= 300 && respCode < 400) {
/* 450 */           data = readStream(new FileInputStream(filePath));
/*     */         } else {
/* 452 */           data = new byte[0];
/* 453 */         }  return new ByteArrayInputStream(data);
/*     */       }
/*     */     
/* 456 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 459 */     URL url = new URL(urlPath);
/* 460 */     return url.openStream();
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream downloadURL(String urlPath) throws Exception {
/* 465 */     return downloadURL(urlPath, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream downloadURL(String urlPath, String versionFolder) throws Exception {
/*     */     try {
/* 472 */       String filePath = getCacheFileName(getFilePath(urlPath, versionFolder));
/* 473 */       File cacheFile = new File(filePath);
/* 474 */       if (cacheFile.exists()) {
/*     */         
/* 476 */         byte[] data = readStream(new FileInputStream(filePath));
/* 477 */         return new ByteArrayInputStream(data);
/*     */       } 
/* 479 */       return downloadURL(urlPath, true);
/*     */     }
/* 481 */     catch (Exception e) {
/*     */       
/* 483 */       e.printStackTrace();
/*     */       
/* 485 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void saveToCache(String filePath, byte[] data, long date) {
/* 490 */     saveToCache(filePath, data, date, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getCacheFileName(String filePath) {
/* 495 */     if (filePath == null)
/* 496 */       return ""; 
/* 497 */     int slashIndex = Math.max(Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')), filePath
/* 498 */         .lastIndexOf(File.separatorChar)) + 1;
/* 499 */     if (slashIndex < filePath.length()) {
/*     */       
/* 501 */       String fileName = filePath.substring(slashIndex) + "_" + ms_UnityVersion.trim();
/* 502 */       fileName = "ls_" + getHasCode(fileName);
/* 503 */       String path = filePath.substring(0, slashIndex);
/* 504 */       filePath = path + fileName;
/*     */     } 
/* 506 */     return filePath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getHasCode(String fileName) {
/* 513 */     int hashCode = fileName.hashCode();
/* 514 */     if (hashCode == Integer.MIN_VALUE) {
/* 515 */       return Integer.MAX_VALUE;
/*     */     }
/* 517 */     return Math.abs(hashCode);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveToCache(String filePath, byte[] data, long date, boolean create) {
/* 522 */     FileOutputStream out = null;
/*     */     
/*     */     try {
/* 525 */       File file = new File(filePath);
/*     */       
/*     */       try {
/* 528 */         File parentFile = file.getParentFile();
/* 529 */         if (parentFile != null) {
/* 530 */           parentFile.mkdirs();
/*     */         }
/* 532 */       } catch (Exception exception) {}
/*     */ 
/*     */       
/* 535 */       out = new FileOutputStream(file);
/* 536 */       out.write(data, 0, data.length);
/* 537 */       if (date > 0L) {
/* 538 */         file.setLastModified(date);
/*     */       }
/* 540 */     } catch (Exception exception) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/* 545 */       if (out != null) {
/*     */         
/*     */         try {
/* 548 */           out.close();
/*     */         }
/* 550 */         catch (IOException e) {
/*     */           
/* 552 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected byte[] readStream(InputStream inStream) {
/*     */     try {
/* 561 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 562 */       byte[] buffer = new byte[4096];
/*     */       int read;
/* 564 */       while ((read = inStream.read(buffer, 0, buffer.length)) > 0)
/* 565 */         out.write(buffer, 0, read); 
/* 566 */       return out.toByteArray();
/*     */     }
/* 568 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 571 */       return new byte[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getFileTimeStamp(String filePath) {
/* 576 */     if (filePath != null) {
/*     */       
/* 578 */       File f = new File(filePath);
/* 579 */       if (f.exists()) {
/*     */         
/* 581 */         long time = f.lastModified();
/* 582 */         TimeZone zone = TimeZone.getTimeZone("GMT");
/* 583 */         Calendar cal = Calendar.getInstance(zone, Locale.UK);
/* 584 */         cal.setTimeInMillis(time);
/* 585 */         SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.UK);
/* 586 */         format.setTimeZone(zone);
/* 587 */         return format.format(cal.getTime());
/*     */       } 
/*     */     } 
/* 590 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getFilePath(String url) {
/* 595 */     int index = url.lastIndexOf("/");
/* 596 */     if (index >= 0)
/* 597 */       url = url.substring(index + 1); 
/* 598 */     File lfsDir = new File(getClientRootDirectory());
/* 599 */     return lfsDir + File.separator + url;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getFilePath(String url, String versionPath) {
/* 604 */     int index = url.lastIndexOf("/");
/* 605 */     if (index >= 0)
/* 606 */       url = url.substring(index + 1); 
/* 607 */     File lfsDir = new File(getClientRootDirectory());
/* 608 */     if (versionPath != null && versionPath.length() > 0) {
/* 609 */       return lfsDir + File.separator + versionPath + File.separator + url;
/*     */     }
/* 611 */     return lfsDir + File.separator + url;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRemoteDesktopSession() {
/* 616 */     String sessionName = System.getenv("SESSIONNAME");
/* 617 */     if (sessionName != null) {
/*     */       
/* 619 */       sessionName = sessionName.toLowerCase(Locale.ENGLISH);
/* 620 */       return (sessionName.startsWith("ica") || sessionName.startsWith("rdp"));
/*     */     } 
/* 622 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static File getTempDir() {
/* 627 */     String path = System.getProperty("java.io.tmpdir");
/* 628 */     return new File(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getClientRootDirectory() {
/* 633 */     return getClientRootDirectory((String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getClientRootDirectory(String rootURI) {
/* 638 */     String path = getTempDir().getAbsolutePath();
/* 639 */     if (path == null || path.length() == 0)
/* 640 */       path = (new File(".")).getAbsolutePath(); 
/* 641 */     path = path + File.separator + "LFS";
/* 642 */     String user = getSystemUserFolderName();
/*     */     
/* 644 */     if (rootURI != null && rootURI.length() > 0) {
/* 645 */       ms_RootURI = rootURI;
/*     */     }
/* 647 */     if (user != null && user.length() > 0) {
/*     */       
/* 649 */       if (ms_RootURI != null && ms_RootURI.length() > 0) {
/* 650 */         return path + File.separator + getURLHash(ms_RootURI) + File.separator + user;
/*     */       }
/* 652 */       return path + File.separator + user;
/*     */     } 
/* 654 */     File lfsDir = new File(path);
/* 655 */     if (!lfsDir.isDirectory() || (!lfsDir.exists() && !lfsDir.mkdirs()))
/*     */     {
/* 657 */       lfsDir = getTempDir();
/*     */     }
/*     */     
/* 660 */     return lfsDir.getPath();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getURLHash(String url) {
/* 665 */     if (url != null && url.length() > 0) {
/*     */       
/* 667 */       CRC32 crc = new CRC32();
/* 668 */       crc.update(url.getBytes());
/* 669 */       return (int)crc.getValue();
/*     */     } 
/* 671 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSystemUserFolderName() {
/* 676 */     String user = System.getProperty("user.name");
/* 677 */     if (user == null || user.length() <= 0) {
/* 678 */       user = "lUserNotFound";
/*     */     }
/* 680 */     return user;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized Class internalLoadClass(String name, boolean resolve) throws ClassNotFoundException {
/* 686 */     Class<?> c = null;
/*     */     
/*     */     try {
/* 689 */       c = super.loadClass(name, false);
/*     */     }
/* 691 */     catch (Throwable e) {
/*     */       
/* 693 */       c = findClass(name);
/*     */     } 
/* 695 */     if (c != null) {
/*     */       
/* 697 */       if (resolve)
/* 698 */         resolveClass(c); 
/* 699 */       return c;
/*     */     } 
/* 701 */     throw new ClassNotFoundException(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
/* 707 */     if (ms_BlackList.contains(name)) {
/* 708 */       return null;
/*     */     }
/* 710 */     if (this.m_Delegates.size() == 0) {
/* 711 */       return internalLoadClass(name, resolve);
/*     */     }
/* 713 */     Class<?> c = findLoadedClass(name);
/* 714 */     boolean bNeedLoad = false;
/*     */ 
/*     */     
/*     */     try {
/* 718 */       if (c == null)
/*     */       {
/* 720 */         if (name.startsWith("com.ghasemkiani"))
/*     */         {
/* 722 */           bNeedLoad = true;
/* 723 */           c = super.findClass(name);
/*     */         }
/*     */         else
/*     */         {
/* 727 */           bNeedLoad = false;
/* 728 */           c = findSystemClass(name);
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 733 */     catch (ClassNotFoundException e) {
/*     */       
/* 735 */       if (bNeedLoad) {
/* 736 */         c = super.loadClass(name, resolve);
/*     */       }
/* 738 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 741 */     if (c == null) {
/*     */       
/*     */       try {
/*     */         
/*     */         try
/*     */         {
/*     */           
/* 748 */           c = internalFindClass(name);
/*     */         }
/* 750 */         catch (Throwable e)
/*     */         {
/* 752 */           c = super.findClass(name);
/*     */         }
/*     */       
/* 755 */       } catch (Throwable e) {
/*     */         
/* 757 */         c = super.loadClass(name, resolve);
/*     */       } 
/*     */     }
/* 760 */     if (resolve)
/* 761 */       resolveClass(c); 
/* 762 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Class internalFindClass(String name) throws ClassNotFoundException {
/* 767 */     if (name != null) {
/*     */       
/* 769 */       String key = name.replace('.', '/') + ".class";
/* 770 */       if (name.startsWith("net.sf.ehcache.Enterprise"))
/* 771 */         throw new ClassNotFoundException(name); 
/* 772 */       byte[] data = (byte[])this.m_Files.get(key);
/* 773 */       if (data != null) {
/*     */         
/* 775 */         this.m_Files.remove(key);
/* 776 */         CodeSource codeSource = new CodeSource(null, (Certificate[])null);
/* 777 */         return defineClass(name, data, 0, data.length, new ProtectionDomain(codeSource, ALL_PERMISSIONS));
/*     */       } 
/*     */     } 
/*     */     
/* 781 */     for (int i = 0; i < this.m_Delegates.size(); i++) {
/*     */       
/* 783 */       ILbsClassLoaderDelegate delegate = this.m_Delegates.get(i);
/* 784 */       byte[] retData = delegate.findLocalClass(name);
/* 785 */       if (retData != null)
/* 786 */         return defineClass(name, retData, 0, retData.length, delegate.getProtectionDomain(name)); 
/*     */     } 
/* 788 */     throw new ClassNotFoundException(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Class findClass(String name) throws ClassNotFoundException {
/* 794 */     if (name != null) {
/*     */       
/* 796 */       String key = name.replace('.', '/') + ".class";
/* 797 */       byte[] data = (byte[])this.m_Files.get(key);
/* 798 */       if (data != null) {
/*     */         
/* 800 */         this.m_Files.remove(key);
/* 801 */         CodeSource codeSource = new CodeSource(null, (Certificate[])null);
/* 802 */         return defineClass(name, data, 0, data.length, new ProtectionDomain(codeSource, ALL_PERMISSIONS));
/*     */       } 
/*     */     } 
/* 805 */     for (int i = 0; i < this.m_Delegates.size(); i++) {
/*     */       
/* 807 */       ILbsClassLoaderDelegate delegate = this.m_Delegates.get(i);
/* 808 */       byte[] retData = delegate.findLocalClass(name);
/* 809 */       if (retData != null)
/* 810 */         return defineClass(name, retData, 0, retData.length, delegate.getProtectionDomain(name)); 
/*     */     } 
/* 812 */     return super.findClass(name);
/*     */   }
/*     */   
/*     */   class RCL_URLStreamHandler
/*     */     extends URLStreamHandler
/*     */   {
/*     */     private String m_Name;
/*     */     
/*     */     public RCL_URLStreamHandler(String name) {
/* 821 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     protected URLConnection openConnection(URL u) {
/* 826 */       return new JLbsContextLocator.RCL_URLConnection(u, this.m_Name);
/*     */     }
/*     */   }
/*     */   
/*     */   class RCL_URLConnection
/*     */     extends URLConnection
/*     */   {
/*     */     private String m_Name;
/*     */     
/*     */     public RCL_URLConnection(URL u, String name) {
/* 836 */       super(u);
/* 837 */       this.m_Name = name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void connect() {}
/*     */ 
/*     */     
/*     */     public InputStream getInputStream() {
/* 846 */       return JLbsContextLocator.this.getResourceAsStream(this.m_Name);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public URL getResource(String name) {
/* 852 */     if (name != null && this.m_Files != null && this.m_Files.containsKey(name)) {
/*     */       
/*     */       try {
/*     */         
/* 856 */         return new URL(new URL(ms_RootURI), name, new RCL_URLStreamHandler(name));
/*     */       }
/* 858 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 862 */     for (int i = 0; i < this.m_Delegates.size(); i++) {
/*     */       
/* 864 */       ILbsClassLoaderDelegate delegate = this.m_Delegates.get(i);
/* 865 */       URL url = delegate.findResourceURL(name);
/* 866 */       if (url != null)
/* 867 */         return url; 
/*     */     } 
/* 869 */     return super.getResource(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getResourceAsStream(String name) {
/* 874 */     if (name != null) {
/*     */       
/*     */       try {
/*     */         
/* 878 */         String key = name;
/* 879 */         byte[] data = (byte[])this.m_Files.get(key);
/* 880 */         if (data != null) {
/* 881 */           return new ByteArrayInputStream(data);
/*     */         }
/* 883 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 887 */     for (int i = 0; i < this.m_Delegates.size(); i++) {
/*     */       
/* 889 */       ILbsClassLoaderDelegate delegate = this.m_Delegates.get(i);
/* 890 */       InputStream stream = delegate.findLocalResource(name);
/* 891 */       if (stream != null)
/* 892 */         return stream; 
/*     */     } 
/* 894 */     return super.getResourceAsStream(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object readContents(JarInputStream jarStream) {
/*     */     try {
/* 901 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 902 */       byte[] buffer = new byte[4096];
/*     */       int read;
/* 904 */       while ((read = jarStream.read(buffer, 0, buffer.length)) > 0)
/* 905 */         out.write(buffer, 0, read); 
/* 906 */       return out.toByteArray();
/*     */     }
/* 908 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 911 */       return new byte[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getRootURI() {
/* 916 */     return ms_RootURI;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getUnityVersion() {
/* 921 */     return ms_UnityVersion;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setUnityVersion(String version) {
/* 926 */     ms_UnityVersion = version;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getUnityVersionMajor() {
/* 931 */     return ms_UnityVersionMajor;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getUnityVersionMinor() {
/* 936 */     return ms_UnityVersionMinor;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getUnityVersionRelease() {
/* 941 */     return ms_UnityVersionRelease;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getUnityVersionBugFix() {
/* 946 */     return ms_UnityVersionBugFix;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getUnityVersionEDefter() {
/* 951 */     return ms_UnityVersionEDefter;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setUnityVersionEDefter(String unityVersionEDefter) {
/* 956 */     ms_UnityVersionEDefter = unityVersionEDefter;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Properties getProductVersionProperties() {
/* 961 */     return ms_ProductVersionProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setProductVersionProperties(Properties productVersionProperties) {
/* 966 */     ms_ProductVersionProperties = productVersionProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 971 */     System.out.println(isRemoteDesktopSession());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\JLbsContextLocator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */