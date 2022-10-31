/*      */ package com.lbs.client;
/*      */ 
/*      */ import com.lbs.appobjects.LbsApplicationConfig;
/*      */ import com.lbs.cache.ICacheConstants;
/*      */ import com.lbs.cache.IVersionSource;
/*      */ import com.lbs.cache.JLbsClientCacheConfigurator;
/*      */ import com.lbs.cache.JLbsSessionBasedCache;
/*      */ import com.lbs.cache.LbsClientCacheManager;
/*      */ import com.lbs.cache.LbsNonCachedHashtable;
/*      */ import com.lbs.channel.IChannelProvider;
/*      */ import com.lbs.channel.ICompressor;
/*      */ import com.lbs.console.ILbsConsole;
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.console.LbsConsoleSettings;
/*      */ import com.lbs.controls.JLbsComponentHelper;
/*      */ import com.lbs.controls.JLbsControlHelper;
/*      */ import com.lbs.controls.JLbsSwingUtilities;
/*      */ import com.lbs.crypto.AESCryptor;
/*      */ import com.lbs.crypto.JLbsCryptoUtil;
/*      */ import com.lbs.crypto.JLbsSymCryptoFactory;
/*      */ import com.lbs.customization.propertygrid.JLbsObjectSchemaCache;
/*      */ import com.lbs.data.InternalUse;
/*      */ import com.lbs.data.factory.IObjectFactory;
/*      */ import com.lbs.data.query.IQueryFactory;
/*      */ import com.lbs.globalization.ILbsCultureInfo;
/*      */ import com.lbs.globalization.JLbsCurrenciesBase;
/*      */ import com.lbs.globalization.JLbsInitializationData;
/*      */ import com.lbs.help.JLbsBrowserFactory;
/*      */ import com.lbs.http.HttpChannelProvider;
/*      */ import com.lbs.http.HttpLoginProvider;
/*      */ import com.lbs.http.cookie.JLbsCookieHive;
/*      */ import com.lbs.interfaces.IReportPreRunParams;
/*      */ import com.lbs.invoke.JLbsClassLoaderDelegate;
/*      */ import com.lbs.invoke.JLbsRemoteClassLoader;
/*      */ import com.lbs.invoke.RttiUtil;
/*      */ import com.lbs.invoke.SessionReestablishedException;
/*      */ import com.lbs.localization.ClientLocalizationServices;
/*      */ import com.lbs.localization.ClientReportingLocalizationServices;
/*      */ import com.lbs.localization.ILocalizationServices;
/*      */ import com.lbs.message.ILbsMessageListener;
/*      */ import com.lbs.message.JLbsMessageDialogResult;
/*      */ import com.lbs.performance.JLbsPerformanceTime;
/*      */ import com.lbs.platform.client.LbsAuthorizationManager;
/*      */ import com.lbs.platform.client.SessionInitializerRegistry;
/*      */ import com.lbs.platform.interfaces.IApplicationContext;
/*      */ import com.lbs.platform.interfaces.IAuthorizationManager;
/*      */ import com.lbs.platform.interfaces.ICacheManager;
/*      */ import com.lbs.platform.interfaces.ICachedHashTable;
/*      */ import com.lbs.platform.interfaces.IIterationManager;
/*      */ import com.lbs.platform.interfaces.IMessageService;
/*      */ import com.lbs.recording.JLbsDataPoolItem;
/*      */ import com.lbs.recording.JLbsRecordItem;
/*      */ import com.lbs.recording.interfaces.ILbsEventRecorder;
/*      */ import com.lbs.remoteclient.IClientContext;
/*      */ import com.lbs.remoteclient.IClientContextConsumer;
/*      */ import com.lbs.remoteclient.IClientUIInit;
/*      */ import com.lbs.remoteclient.IReportExecutor;
/*      */ import com.lbs.remoteclient.IResourceClientContext;
/*      */ import com.lbs.remoteclient.JLbsClientObjectFactory;
/*      */ import com.lbs.remoteclient.JLbsClientQueryFactory;
/*      */ import com.lbs.remoteclient.JLbsContextMessageUtil;
/*      */ import com.lbs.remoteclient.JLbsReportExecParams;
/*      */ import com.lbs.remoteclient.JLbsRunContextParameters;
/*      */ import com.lbs.remoteclient.URLRedirectionException;
/*      */ import com.lbs.reporting.JLbsReportingParams;
/*      */ import com.lbs.resource.JLbsLocalizer;
/*      */ import com.lbs.rmi.JLbsClientHttpInvoker;
/*      */ import com.lbs.start.ILbsClassLoaderDelegate;
/*      */ import com.lbs.start.ITokenProcessor;
/*      */ import com.lbs.start.JLbsContextLocator;
/*      */ import com.lbs.start.JLbsStartup;
/*      */ import com.lbs.transport.CachedTransportClient;
/*      */ import com.lbs.transport.FileUpToDateException;
/*      */ import com.lbs.transport.ILbsServerEventListener;
/*      */ import com.lbs.transport.IRemoteMethodInvoker;
/*      */ import com.lbs.transport.IRemoteSessionInvokeHandler;
/*      */ import com.lbs.transport.ISessionBase;
/*      */ import com.lbs.transport.ITransportCache;
/*      */ import com.lbs.transport.IUserDetailInfo;
/*      */ import com.lbs.transport.JLbsJarCache;
/*      */ import com.lbs.transport.JLbsJarStreamHolder;
/*      */ import com.lbs.transport.LbsServerEvent;
/*      */ import com.lbs.transport.LbsServerEventList;
/*      */ import com.lbs.transport.LoginException;
/*      */ import com.lbs.transport.MultiBufferTransportClient;
/*      */ import com.lbs.transport.NameValueMap;
/*      */ import com.lbs.transport.ObjectTransportClient;
/*      */ import com.lbs.transport.PublicInfoRequest;
/*      */ import com.lbs.transport.RemoteMethodInvoker;
/*      */ import com.lbs.transport.RemoteMethodResponse;
/*      */ import com.lbs.transport.StdCompressor;
/*      */ import com.lbs.transport.TransportClient;
/*      */ import com.lbs.transport.TransportClientRecorder;
/*      */ import com.lbs.transport.TransportUtil;
/*      */ import com.lbs.transport.UserInfo;
/*      */ import com.lbs.util.Base64;
/*      */ import com.lbs.util.JLbsClientFS;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsDateUtil;
/*      */ import com.lbs.util.JLbsFileUtil;
/*      */ import com.lbs.util.JLbsMessageBox;
/*      */ import com.lbs.util.JLbsOpenWindowListing;
/*      */ import com.lbs.util.JLbsPropertySetter;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import com.lbs.util.JLbsTransportMode;
/*      */ import com.lbs.util.JLbsURLUtil;
/*      */ import com.lbs.util.ObjectUtil;
/*      */ import com.lbs.util.StringUtil;
/*      */ import java.applet.AppletContext;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.InvalidObjectException;
/*      */ import java.io.Serializable;
/*      */ import java.io.StreamCorruptedException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.management.ManagementFactory;
/*      */ import java.lang.management.RuntimeMXBean;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.InetAddress;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.net.URLEncoder;
/*      */ import java.net.UnknownHostException;
/*      */ import java.security.SignatureException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Timer;
/*      */ import java.util.TimerTask;
/*      */ import java.util.UUID;
/*      */ import javax.swing.JApplet;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import net.sf.ehcache.CacheManager;
/*      */ import netscape.javascript.JSObject;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LbsClientContext
/*      */   implements IClientContext, IResourceClientContext, IRemoteSessionInvokeHandler, ILbsServerEventListener, ICacheConstants
/*      */ {
/*      */   private static final String L_PUB_INF = "~l_pub_inf";
/*      */   public static final int USERACTIONTIMEOUT = 300000;
/*      */   private static byte[] key;
/*      */   private static final int ms_BegEndCount = 3;
/*  188 */   private static int AZURE_OAUTH = 1;
/*  189 */   private static int IDP_OAUTH = 2;
/*  190 */   private static int DEFAULT_OAUTH = 0;
/*      */   
/*      */   protected IReportExecutor m_ReportExecutor;
/*      */   
/*      */   protected NameValueMap m_HashTable;
/*      */   
/*      */   protected ICachedHashTable<String, Serializable> m_ResourceCache;
/*      */   
/*      */   protected HttpChannelProvider m_ChannelProv;
/*      */   
/*      */   protected TransportClient m_BaseClient;
/*      */   protected ObjectTransportClient m_TransClient;
/*      */   protected ClassLoader m_ClassLoader;
/*      */   protected HttpLoginProvider m_LoginProv;
/*      */   protected IRemoteMethodInvoker m_RemoteInvoker;
/*      */   protected ILocalizationServices m_LocalizationService;
/*      */   protected ILocalizationServices m_ReportingLocalizationService;
/*      */   private ISessionBase m_Session;
/*      */   protected UserInfo m_UserInfo;
/*      */   protected String m_RootUri;
/*      */   protected LbsClient m_Applet;
/*      */   protected Timer m_Timer;
/*      */   protected int m_Timeout;
/*      */   protected Calendar m_LastUserAction;
/*      */   protected Hashtable m_ConfigParameters;
/*      */   protected ILbsServerEventListener m_ServerEventListener;
/*      */   private IVersionSource m_ApplicationVersion;
/*  217 */   private final JLbsSessionBasedCache.JLbsSessionInfoProvider m_SessionInfoProvider = new JLbsSessionBasedCache.JLbsSessionInfoProvider("");
/*      */   
/*      */   private TimeZone m_ServerTimeZone;
/*      */   
/*      */   private TimeZone m_DefaultTimeZone;
/*      */   private ICacheManager m_cacheManager;
/*  223 */   private IMessageService m_MessageService = null;
/*      */   
/*  225 */   private transient LbsConsole m_Logger = null;
/*      */   
/*  227 */   private HashMap<String, WeakReference<Class>> m_Classes = null;
/*      */   
/*  229 */   private HashMap<LbsServerEventList, LbsServerEvent> m_EventsToBeProcessed = new HashMap<>();
/*      */ 
/*      */   
/*      */   private List<Object> m_GlobalPopups;
/*      */ 
/*      */   
/*      */   static {
/*      */     try {
/*  237 */       key = "!!!!ééééééaaaaaaééééé123456789!!!!".getBytes("Cp1254");
/*      */     }
/*  239 */     catch (Exception e) {
/*      */       
/*  241 */       key = "!!!!ééééééaaaaaaééééé123456789!!!!".getBytes();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LbsClientContext(LbsClient applet, String rootURI, boolean exit) {
/*  248 */     this.m_ChannelProv = new HttpChannelProvider();
/*  249 */     this.m_Classes = new HashMap<>();
/*  250 */     this.m_LoginProv = new HttpLoginProvider((ICompressor)new StdCompressor());
/*  251 */     this.m_RootUri = rootURI;
/*  252 */     this.m_Applet = applet;
/*  253 */     this.m_HashTable = new NameValueMap();
/*      */     
/*  255 */     this.m_ResourceCache = (ICachedHashTable<String, Serializable>)new LbsNonCachedHashtable();
/*      */     
/*  257 */     if (applet != null && applet.getUsesCache()) {
/*      */       
/*  259 */       this.m_BaseClient = (TransportClient)new CachedTransportClient((IChannelProvider)this.m_ChannelProv, null, null, null);
/*  260 */       ((CachedTransportClient)this.m_BaseClient).setRootURI(rootURI);
/*      */     } else {
/*      */       
/*  263 */       this.m_BaseClient = new TransportClient((IChannelProvider)this.m_ChannelProv, null, null, null);
/*      */     } 
/*  265 */     this.m_ApplicationVersion = new IVersionSource()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public String getName() {
/*  272 */           return "AppVersion";
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public String getVersion() {
/*  278 */           String version = JLbsContextLocator.getUnityVersion();
/*  279 */           String edefterVersion = JLbsContextLocator.getUnityVersionEDefter();
/*  280 */           Properties productVersionProperties = JLbsContextLocator.getProductVersionProperties();
/*  281 */           boolean needsVersion = JLbsStringUtil.isEmpty(version);
/*  282 */           boolean needsEDefterVersion = JLbsStringUtil.isEmpty(edefterVersion);
/*  283 */           boolean needsProductVersion = (productVersionProperties == null);
/*  284 */           if (needsVersion || needsEDefterVersion || needsProductVersion) {
/*      */             
/*      */             try {
/*      */               
/*  288 */               Object[] versions = LbsClientContext.this.getPublicObjects(new String[] { "ApplicationVersion", "EDefterVersion", "ProductVersionProperties" }, new Object[] { null, 
/*      */                     
/*  290 */                     Boolean.valueOf(true), Boolean.valueOf(true) }, true);
/*  291 */               if (JLbsStringUtil.isEmpty(version)) {
/*      */                 
/*  293 */                 version = (String)versions[0];
/*  294 */                 JLbsContextLocator.setUnityVersion(version);
/*      */               } 
/*  296 */               if (JLbsStringUtil.isEmpty(edefterVersion)) {
/*      */                 
/*  298 */                 edefterVersion = (String)versions[1];
/*  299 */                 JLbsContextLocator.setUnityVersionEDefter(edefterVersion);
/*      */               } 
/*  301 */               if (productVersionProperties == null)
/*      */               {
/*  303 */                 productVersionProperties = (Properties)versions[2];
/*  304 */                 JLbsContextLocator.setProductVersionProperties(productVersionProperties);
/*      */               }
/*      */             
/*  307 */             } catch (Exception e) {
/*      */               
/*  309 */               LbsConsole.getLogger(getClass()).error(e, e);
/*      */             } 
/*      */           }
/*  312 */           return version;
/*      */         }
/*      */       };
/*  315 */     initializeVersion();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  320 */       if (applet != null && ("com.lbs.unity.main.UnityClient"
/*  321 */         .equals(applet.getClass().getName()) || "com.lbs.edefter.EDefterClient"
/*  322 */         .equals(applet.getClass().getName())) && 
/*  323 */         !JLbsMinimumRequirements.processMinSystemRequirements(this))
/*      */       {
/*  325 */         System.out.println("Minimum system requirements do not match. Exiting application!");
/*  326 */         System.exit(0);
/*      */       }
/*      */     
/*  329 */     } catch (Exception e) {
/*      */       
/*  331 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  334 */     JLbsJarCache.clearCache();
/*      */     
/*  336 */     JLbsStartup.setWindowListener(applet);
/*  337 */     ClassLoader parentLoader = Thread.currentThread().getContextClassLoader();
/*  338 */     if (parentLoader instanceof JLbsContextLocator) {
/*      */       
/*  340 */       ((JLbsContextLocator)parentLoader).addDelegate((ILbsClassLoaderDelegate)new JLbsClassLoaderDelegate(rootURI + "ClassDownload", this.m_BaseClient));
/*  341 */       this.m_ClassLoader = parentLoader;
/*      */     }
/*      */     else {
/*      */       
/*  345 */       this.m_ClassLoader = (ClassLoader)new JLbsRemoteClassLoader(parentLoader, rootURI + "ClassDownload", this.m_BaseClient);
/*  346 */       Thread.currentThread().setContextClassLoader(this.m_ClassLoader);
/*      */     } 
/*      */     
/*  349 */     JLbsControlHelper.setGlobalClsLoader(this.m_ClassLoader);
/*      */     
/*      */     try {
/*  352 */       if (this.m_Applet != null && this.m_Applet.getParameter("RECORDSESSIONTO") != null && !exit)
/*      */       {
/*  354 */         this.m_BaseClient.setRecorder(new TransportClientRecorder(this.m_Applet.getParameter("RECORDSESSIONTO")));
/*      */       }
/*      */     }
/*  357 */     catch (Exception e) {
/*      */       
/*  359 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  362 */     Object[] publicObjects = collectPublicObjects();
/*      */     
/*  364 */     if (publicObjects != null && publicObjects.length == 4) {
/*      */       
/*  366 */       Object config = publicObjects[0];
/*  367 */       applyServerSpecifiedConfig(config);
/*      */       
/*  369 */       initializeClientParameters(publicObjects[2], publicObjects[3]);
/*  370 */       initializeCacheConfiguration();
/*      */     } 
/*      */     
/*  373 */     prepareResourceCache();
/*  374 */     cleanTempFiles();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Object> getGlobalPopups() {
/*  379 */     return this.m_GlobalPopups;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGlobalPopups(List<Object> globalPopups) {
/*  384 */     this.m_GlobalPopups = globalPopups;
/*      */   }
/*      */ 
/*      */   
/*      */   private void prepareResourceCache() {
/*  389 */     synchronized (LbsClientContext.class) {
/*      */       
/*  391 */       ICacheManager cacheManager = getCacheManager();
/*  392 */       if (cacheManager != null) {
/*      */         
/*  394 */         ICachedHashTable<String, Serializable> cachRes = cacheManager.getCachedHashTable("ctx_cache" + hashCode(), String.class, Serializable.class, false);
/*      */         
/*  396 */         if (this.m_ResourceCache != null && this.m_ResourceCache.size() > 0) {
/*      */           
/*  398 */           Enumeration<String> keys = this.m_ResourceCache.keys();
/*  399 */           while (keys.hasMoreElements()) {
/*      */             
/*  401 */             String keyVal = keys.nextElement();
/*  402 */             cachRes.put(keyVal, this.m_ResourceCache.get(keyVal));
/*      */           } 
/*  404 */           this.m_ResourceCache.clear();
/*      */         } 
/*  406 */         this.m_ResourceCache = cachRes;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessageDialogResult showMessage(String messageId, String defaultMessage, Object[] messageFormatArguments, ILbsMessageListener listener) {
/*  415 */     return JLbsContextMessageUtil.showMessage((IApplicationContext)this, messageId, defaultMessage, listener, messageFormatArguments);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeClientParameters(Object setterCollection, Object runtimeVars) {
/*      */     try {
/*  425 */       Object o = setterCollection;
/*  426 */       if (o instanceof JLbsPropertySetter[]) {
/*      */         
/*  428 */         JLbsPropertySetter[] setterList = (JLbsPropertySetter[])o;
/*  429 */         traverseClientConfigCollection(setterList);
/*      */       } 
/*      */       
/*  432 */       Object objRuntimeVars = runtimeVars;
/*  433 */       if (objRuntimeVars instanceof Hashtable) {
/*      */         
/*  435 */         Hashtable varTable = (Hashtable)objRuntimeVars;
/*  436 */         Enumeration varKeys = varTable.keys();
/*  437 */         while (varKeys.hasMoreElements()) {
/*      */           
/*  439 */           Object key = varKeys.nextElement();
/*  440 */           Object value = varTable.get(key);
/*      */           
/*  442 */           if (value != null && value instanceof JLbsPropertySetter)
/*      */           {
/*  444 */             JLbsPropertySetter setter = (JLbsPropertySetter)value;
/*  445 */             setter.apply();
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  450 */     } catch (Exception e) {
/*      */       
/*  452 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void traverseClientConfigCollection(JLbsPropertySetter[] setterList) {
/*  459 */     if (setterList != null) {
/*      */       
/*      */       try {
/*      */         
/*  463 */         for (int i = 0; i < setterList.length; i++) {
/*      */           
/*  465 */           JLbsPropertySetter setter = setterList[i];
/*  466 */           String fieldName = setter.getFieldName();
/*  467 */           if (fieldName != null && fieldName.equals("ReportingURI")) {
/*      */             
/*  469 */             final JLbsPropertySetter fSetter = setter;
/*  470 */             (new Thread(new Runnable()
/*      */                 {
/*      */                   
/*      */                   public void run()
/*      */                   {
/*  475 */                     if (LbsClientContext.testReportingURIExistance(fSetter.getValue())) {
/*  476 */                       fSetter.apply();
/*      */                     } else {
/*  478 */                       LbsConsole.getLogger("LbsClientContext")
/*  479 */                         .error("ReportingURI can not be found! Please check server configuration.");
/*      */                     }  }
/*  481 */                 })).start();
/*      */           } else {
/*      */             
/*  484 */             setter.apply();
/*      */           }
/*      */         
/*      */         } 
/*  488 */       } catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean testReportingURIExistance(String reportingUri) {
/*  497 */     if (!JLbsStringUtil.isEmpty(reportingUri)) {
/*      */       
/*      */       try {
/*      */         
/*  501 */         URL url = null;
/*  502 */         if (reportingUri.endsWith("/")) {
/*  503 */           url = new URL(reportingUri + "Reporting");
/*      */         } else {
/*  505 */           url = new URL(reportingUri + "/" + "Reporting");
/*  506 */         }  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*  507 */         connection.getInputStream();
/*  508 */         return true;
/*      */       }
/*  510 */       catch (IOException ex) {
/*      */         
/*  512 */         LbsConsole.getLogger("LbsClientContext").error(ex);
/*      */       } 
/*      */     }
/*  515 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object[] collectPublicObjects() {
/*      */     try {
/*  525 */       return getPublicObjects(new String[] { "ClientConfigParameters", "ApplicationVersion", "ClientSetterCollection", "RuntimeSetClientVariables" }, new Object[] { null, null, null, null }, true);
/*      */     
/*      */     }
/*  528 */     catch (Exception e) {
/*      */       
/*  530 */       e.printStackTrace();
/*  531 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startLoadingLocalizationCache() {
/*  538 */     Thread cacheLoadThread = new Thread(new Runnable()
/*      */         {
/*      */           
/*      */           public void run()
/*      */           {
/*  543 */             ILocalizationServices lclService = LbsClientContext.this.getLocalizationService();
/*  544 */             if (lclService != null) {
/*      */ 
/*      */               
/*  547 */               Hashtable cacheContent = null;
/*      */ 
/*      */               
/*      */               try {
/*  551 */                 RemoteMethodResponse resp = LbsClientContext.this.callRemoteMethod("LSW", "getLocalizationCacheContent", null);
/*      */ 
/*      */                 
/*  554 */                 cacheContent = (Hashtable)resp.Result;
/*      */               }
/*  556 */               catch (Exception e) {
/*      */                 
/*  558 */                 e.printStackTrace();
/*  559 */                 cacheContent = null;
/*      */               } 
/*      */               
/*  562 */               if (cacheContent == null) {
/*      */                 return;
/*      */               }
/*  565 */               int[] unList = (int[])cacheContent.get("UN");
/*  566 */               if (unList != null && unList.length > 0) {
/*  567 */                 lclService.getListsByIDSet(unList, "UN");
/*      */               }
/*  569 */               int[] hrList = (int[])cacheContent.get("HR");
/*  570 */               if (hrList != null && hrList.length > 0) {
/*  571 */                 lclService.getListsByIDSet(hrList, "HR");
/*      */               }
/*  573 */               int[] unrpList = (int[])cacheContent.get("UNRP");
/*  574 */               if (unrpList != null && unrpList.length > 0) {
/*  575 */                 lclService.getListsByIDSet(unrpList, "UNRP");
/*      */               }
/*  577 */               int[] hrrpList = (int[])cacheContent.get("HRRP");
/*  578 */               if (hrrpList != null && hrrpList.length > 0) {
/*  579 */                 lclService.getListsByIDSet(hrrpList, "HRRP");
/*      */               }
/*  581 */               int[] helpList = (int[])cacheContent.get("HELP");
/*  582 */               if (helpList != null && helpList.length > 0) {
/*  583 */                 lclService.getListsByIDSet(helpList, "HELP");
/*      */               }
/*  585 */               int[] ssList = (int[])cacheContent.get("SS");
/*  586 */               if (ssList != null && ssList.length > 0) {
/*  587 */                 lclService.getListsByIDSet(ssList, "SS");
/*      */               }
/*      */             } 
/*      */           }
/*      */         }"Localization Cache Load");
/*  592 */     cacheLoadThread.setPriority(3);
/*  593 */     cacheLoadThread.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanTempFiles() {
/*      */     try {
/*  603 */       String tempDir = System.getProperty("java.io.tmpdir");
/*  604 */       if (tempDir != null) {
/*      */         
/*  606 */         File dir = new File(tempDir);
/*  607 */         if (dir.isDirectory()) {
/*      */           
/*  609 */           String[] list = dir.list(new FilenameFilter()
/*      */               {
/*      */                 
/*      */                 public boolean accept(File dir, String name)
/*      */                 {
/*  614 */                   if (name != null && name.endsWith(".ltmp"))
/*  615 */                     return true; 
/*  616 */                   return false;
/*      */                 }
/*      */               });
/*      */ 
/*      */           
/*      */           try {
/*  622 */             String fileName = null;
/*  623 */             for (int i = 0; i < list.length; i++)
/*      */             {
/*  625 */               fileName = JLbsFileUtil.appendPath(tempDir, list[i]);
/*  626 */               File file = new File(fileName);
/*  627 */               file.delete();
/*      */             }
/*      */           
/*  630 */           } catch (Exception e) {
/*      */             
/*  632 */             getLogger().error(e.getMessage(), e);
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  637 */     } catch (Exception e) {
/*      */       
/*  639 */       getLogger().error(e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public JApplet getApplet() {
/*  645 */     return this.m_Applet;
/*      */   }
/*      */ 
/*      */   
/*      */   public JApplet getBaseApplet() {
/*  650 */     if (this.m_Applet != null) {
/*  651 */       return this.m_Applet.getBaseApplet();
/*      */     }
/*  653 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyServerSpecifiedConfig(Object config) {
/*      */     try {
/*  663 */       if (config instanceof Hashtable) {
/*      */         
/*  665 */         this.m_ConfigParameters = (Hashtable)config;
/*      */ 
/*      */         
/*      */         try {
/*  669 */           JLbsTransportMode transportMode = (JLbsTransportMode)this.m_ConfigParameters.get(JLbsTransportMode.class.getName());
/*  670 */           if (transportMode != null) {
/*  671 */             transportMode.applyTransportMode();
/*      */           }
/*  673 */         } catch (Exception e) {
/*      */           
/*  675 */           getLogger().warn("Could not retrieve transport mode from server, using default transport mode.", e);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  681 */           LbsConsoleSettings consoleSettings = (LbsConsoleSettings)this.m_ConfigParameters.get(LbsConsoleSettings.class.getName());
/*  682 */           if (consoleSettings != null) {
/*  683 */             consoleSettings.applyClientSettings();
/*      */           }
/*  685 */         } catch (Exception e) {
/*      */           
/*  687 */           getLogger().warn("Could not retrieve LbsConsoleSettings from server, using default logLevel.", e);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  692 */         throw new Exception();
/*      */       } 
/*  694 */     } catch (Exception e) {
/*      */       
/*  696 */       getLogger().warn("Could not retrieve client configuration from server, leaving with defaults");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void ensureTransClientExist() {
/*  702 */     if (this.m_TransClient == null && isLoggedIn()) {
/*      */       
/*  704 */       this
/*      */         
/*  706 */         .m_TransClient = new ObjectTransportClient((IChannelProvider)this.m_ChannelProv, this.m_Session, JLbsSymCryptoFactory.createCryptor(this.m_Session.getLocalCryptoParam()), JLbsSymCryptoFactory.createCryptor(this.m_Session.getRemoteCryptoParam()));
/*  707 */       this.m_TransClient.setRecorder(this.m_BaseClient.getRecorder());
/*      */ 
/*      */       
/*  710 */       this
/*  711 */         .m_RemoteInvoker = (IRemoteMethodInvoker)(new JLbsClientHttpInvoker(this.m_RootUri, "Invoker", this.m_Session, this.m_ClassLoader)).setRecorder(this.m_BaseClient.getRecorder());
/*      */ 
/*      */ 
/*      */       
/*  715 */       if (this.m_Applet != null) {
/*  716 */         this.m_RemoteInvoker.setTimeoutHandler(this.m_Applet);
/*      */       }
/*  718 */       this.m_RemoteInvoker.setInvokeHandler(this);
/*  719 */       this.m_RemoteInvoker.setServerEventListener(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected IRemoteMethodInvoker ensureTransClientExist(String serverRootURI) {
/*  725 */     if (JLbsStringUtil.isEmpty(serverRootURI)) {
/*      */       
/*  727 */       ensureTransClientExist();
/*  728 */       return this.m_RemoteInvoker;
/*      */     } 
/*      */     
/*  731 */     if (isLoggedIn()) {
/*      */       
/*  733 */       IRemoteMethodInvoker remoteInvoker = null;
/*      */ 
/*      */       
/*  736 */       JLbsClientHttpInvoker jLbsClientHttpInvoker = new JLbsClientHttpInvoker(serverRootURI, "Invoker", this.m_Session, this.m_ClassLoader);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  746 */       jLbsClientHttpInvoker.setInvokeHandler(this);
/*  747 */       jLbsClientHttpInvoker.setServerEventListener(this);
/*  748 */       return (IRemoteMethodInvoker)jLbsClientHttpInvoker;
/*      */     } 
/*  750 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Object createInstance(String className) {
/*      */     try {
/*  758 */       className = className.replace('/', '.');
/*  759 */       Class cls = getClassFromCache(className);
/*      */       
/*  761 */       if (cls == null) {
/*      */         
/*  763 */         cls = loadClass(className);
/*  764 */         if (cls != null) {
/*  765 */           putClassToCache(className, cls);
/*      */         }
/*      */       } 
/*  768 */       if (cls != null)
/*      */       {
/*  770 */         Object target = RttiUtil.createInstance(cls);
/*  771 */         if (target instanceof IClientContextConsumer)
/*  772 */           ((IClientContextConsumer)target).setContext(this); 
/*  773 */         return target;
/*      */       }
/*      */     
/*  776 */     } catch (ClassNotFoundException e) {
/*      */       
/*  778 */       if (!className.equals("com.lbs.admin.AdminLicenceUI")) {
/*  779 */         getLogger().error("Context.createInstance (" + className + "): ", e);
/*      */       }
/*  781 */     } catch (Exception e) {
/*      */       
/*  783 */       getLogger().error("Context.createInstance (" + className + "): ", e);
/*      */     } 
/*  785 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void putClassToCache(String className, Class<?> cls) {
/*  791 */     if (className != null && cls != null && this.m_Classes.size() < 100) {
/*  792 */       this.m_Classes.put(className, new WeakReference<>(cls));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Class getClassFromCache(String className) {
/*  799 */     WeakReference<Class<?>> reference = this.m_Classes.get(className);
/*  800 */     return (reference != null) ? reference
/*  801 */       .get() : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createNamedInstance(String className, String name) throws Exception {
/*  808 */     if (className != null && name != null) {
/*      */       
/*  810 */       String varName = "@@" + className + "." + name;
/*  811 */       Object value = getVariable(varName);
/*  812 */       if (value != null)
/*  813 */         return value; 
/*  814 */       value = createInstance(className);
/*  815 */       if (value != null)
/*  816 */         setVariable(varName, value); 
/*  817 */       return value;
/*      */     } 
/*  819 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Class loadClass(String className) throws Exception {
/*  825 */     return this.m_ClassLoader.loadClass(className);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void showMessage(String title, String message) {
/*  831 */     (new JLbsMessageBox(title, message)).show();
/*      */   }
/*      */ 
/*      */   
/*      */   public void showMessage(String title, String message, int fontSize) {
/*  836 */     (new JLbsMessageBox(title, message, fontSize)).show();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void showError(Exception ex) {
/*  842 */     if (ex != null) {
/*  843 */       showMessage("Error", ex.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean relogin(String password) {
/*      */     try {
/*  855 */       synchronized (this)
/*      */       {
/*  857 */         if (this.m_UserInfo == null)
/*  858 */           throw new NullPointerException("User info is null"); 
/*  859 */         String ltpaToken = getCookie("LtpaCookie");
/*  860 */         if (!StringUtil.isEmpty(ltpaToken))
/*  861 */           setCookie("LtpaCookie", ""); 
/*  862 */         if (JLbsConstants.checkAppCloud()) {
/*      */           
/*  864 */           boolean isValidToken = false;
/*  865 */           if (this.m_UserInfo.getOAuthToken() != null)
/*      */           {
/*  867 */             isValidToken = ((Boolean)getPublicObject("validateAccessToken", new String[] { this.m_UserInfo.getOAuthToken() }, true)).booleanValue();
/*      */           }
/*      */           
/*  870 */           if (!isValidToken) {
/*      */             
/*  872 */             openURL(JLbsConstants.JUGNU_PORTAL + "/login", null);
/*  873 */             System.exit(0);
/*      */           } 
/*      */         } 
/*  876 */         this.m_UserInfo.LtpaToken = "";
/*  877 */         this.m_UserInfo.Password = password;
/*  878 */         this.m_UserInfo.reLogin = true;
/*  879 */         if (JLbsConstants.LDAP_AUTOLOGIN && this.m_UserInfo.modeLogin == UserInfo.MODE_LOGIN.LDAP_FORCE_PASSWORD) {
/*  880 */           this.m_UserInfo.modeLogin = UserInfo.MODE_LOGIN.LDAP;
/*      */         }
/*  882 */         ISessionBase session = this.m_LoginProv.login((IChannelProvider)this.m_ChannelProv, this.m_RootUri + "Login", this.m_UserInfo, this.m_ClassLoader);
/*      */         
/*  884 */         if (JLbsConstants.LDAP_AUTOLOGIN && this.m_UserInfo.modeLogin == UserInfo.MODE_LOGIN.LDAP)
/*  885 */           this.m_UserInfo.modeLogin = UserInfo.MODE_LOGIN.LDAP_FORCE_PASSWORD; 
/*  886 */         this.m_UserInfo.reLogin = false;
/*  887 */         if (session == null || this.m_Session == null) {
/*  888 */           return false;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  895 */         ObjectUtil.deepCopy(session, this.m_Session, this.m_Session.getClass());
/*  896 */         HttpLoginProvider.ms_SessionCode = this.m_Session.getSessionCode();
/*  897 */         if (!JLbsStringUtil.isEmpty(HttpLoginProvider.ms_SessionCode))
/*  898 */           JLbsCookieHive.addCookie("JSESSIONID=" + HttpLoginProvider.ms_SessionCode, this.m_RootUri, true); 
/*  899 */         this.m_TransClient.setLocalCryptor(JLbsSymCryptoFactory.createCryptor(session.getLocalCryptoParam()));
/*  900 */         this.m_TransClient.setRemoteCryptor(JLbsSymCryptoFactory.createCryptor(session.getRemoteCryptoParam()));
/*      */         
/*  902 */         this.m_SessionInfoProvider.fireSessionRecovered();
/*  903 */         checkWorkFlowInfo(getUserDetailInfo().getFirmNr());
/*  904 */         return true;
/*      */       }
/*      */     
/*  907 */     } catch (Exception e) {
/*      */       
/*  909 */       showMessage("Relogin Failed!", e.getMessage());
/*      */       
/*  911 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkWorkFlowInfo(int firmNr) {
/*      */     try {
/*  919 */       Hashtable<String, Object> workflowInfo = (Hashtable<String, Object>)getPublicObject("WFSettings", Integer.valueOf(firmNr), true);
/*      */       
/*  921 */       if (workflowInfo == null) {
/*      */         return;
/*      */       }
/*  924 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("WfSubscribeUrl"))) {
/*  925 */         JLbsConstants.WF_SUBSCRIBE_URL = (String)workflowInfo.get("WfSubscribeUrl");
/*      */       }
/*  927 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("WfLoginUrl"))) {
/*  928 */         JLbsConstants.WF_LOGIN_URL = (String)workflowInfo.get("WfLoginUrl");
/*      */       }
/*  930 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("ActiveDirectoryLoginUrl"))) {
/*  931 */         JLbsConstants.ACTIVE_DIRECTORY_LOGIN_URL = (String)workflowInfo.get("ActiveDirectoryLoginUrl");
/*      */       }
/*  933 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("WfStatusUrl"))) {
/*  934 */         JLbsConstants.WF_STATUS_URL = (String)workflowInfo.get("WfStatusUrl");
/*      */       }
/*  936 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("IdpProviderType"))) {
/*  937 */         JLbsConstants.IDP_PROVIDER_TYPE = Integer.valueOf((String)workflowInfo.get("IdpProviderType")).intValue();
/*      */       }
/*  939 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("WfStatusBaseUrl"))) {
/*  940 */         JLbsConstants.WF_STATUS_BASE_URL = (String)workflowInfo.get("WfStatusBaseUrl");
/*      */       }
/*  942 */       if (!JLbsStringUtil.isEmpty((String)workflowInfo.get("WfUsername"))) {
/*  943 */         JLbsConstants.WF_USERNAME = (String)workflowInfo.get("WfUsername");
/*      */       }
/*      */     }
/*  946 */     catch (Exception e) {
/*      */       
/*  948 */       LbsConsole.getLogger(getClass()).error(e, e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getIpAsString(InetAddress address) {
/*  955 */     byte[] ipAddress = address.getAddress();
/*  956 */     StringBuilder str = new StringBuilder();
/*  957 */     for (int i = 0; i < ipAddress.length; i++) {
/*      */       
/*  959 */       if (i > 0)
/*  960 */         str.append('.'); 
/*  961 */       str.append(ipAddress[i] & 0xFF);
/*      */     } 
/*  963 */     return str.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long ipToLong(String rawIpAddress) {
/*  974 */     int ipAddress = 0;
/*      */     
/*      */     try {
/*  977 */       String[] ipv4Array = JLbsStringUtil.tokenize(rawIpAddress, ".");
/*      */       int j;
/*  979 */       for (int i = ipv4Array.length - 1, counter = 0; counter < 4; i--, j *= 256)
/*      */       {
/*  981 */         ipAddress += j * Integer.parseInt(ipv4Array[i]);
/*  982 */         counter++;
/*      */       }
/*      */     
/*      */     }
/*  986 */     catch (Exception e) {
/*      */       
/*  988 */       LbsConsole.getLogger("LbsClient.LbsClientContext").error(null, e);
/*  989 */       ipAddress = 0;
/*      */     } 
/*  991 */     return ipAddress & 0xFFFFFFFFL;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long[] getAllIpAddresses() {
/* 1000 */     String[] rawIpAddresses = null;
/*      */ 
/*      */     
/*      */     try {
/* 1004 */       InetAddress localHost = InetAddress.getLocalHost();
/* 1005 */       InetAddress[] all_IPs = InetAddress.getAllByName(localHost.getHostName());
/*      */       
/* 1007 */       rawIpAddresses = new String[all_IPs.length];
/* 1008 */       for (int i = 0; i < all_IPs.length; i++)
/*      */       {
/* 1010 */         rawIpAddresses[i] = getIpAsString(all_IPs[i]);
/*      */       }
/*      */     }
/* 1013 */     catch (UnknownHostException e) {
/*      */       
/* 1015 */       getLogger().error("No Ip Addresses found.", e);
/*      */     } 
/*      */     
/* 1018 */     long[] ipAddresses = null;
/* 1019 */     if (rawIpAddresses != null) {
/*      */       
/* 1021 */       ipAddresses = new long[rawIpAddresses.length];
/*      */       
/* 1023 */       for (int i = 0; i < ipAddresses.length; i++)
/*      */       {
/* 1025 */         ipAddresses[i] = ipToLong(rawIpAddresses[i]);
/*      */       }
/*      */     } 
/* 1028 */     return ipAddresses;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getClientMachineName() {
/* 1033 */     Map<String, String> env = System.getenv();
/* 1034 */     if (env.containsKey("COMPUTERNAME"))
/* 1035 */       return env.get("COMPUTERNAME"); 
/* 1036 */     if (env.containsKey("HOSTNAME")) {
/* 1037 */       return env.get("HOSTNAME");
/*      */     }
/* 1039 */     String machineName = "";
/*      */     
/*      */     try {
/* 1042 */       machineName = InetAddress.getLocalHost().getHostName();
/* 1043 */       if (!StringUtil.isEmpty(machineName))
/*      */       {
/* 1045 */         return machineName;
/*      */       }
/*      */     }
/* 1048 */     catch (UnknownHostException unknownHostException) {}
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1053 */       RuntimeMXBean rmx = ManagementFactory.getRuntimeMXBean();
/* 1054 */       machineName = rmx.getName();
/* 1055 */       if (machineName.indexOf('@') > -1)
/* 1056 */         machineName = machineName.substring(machineName.indexOf('@') + 1); 
/* 1057 */       if (!StringUtil.isEmpty(machineName))
/*      */       {
/* 1059 */         return machineName;
/*      */       }
/*      */     }
/* 1062 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1067 */       Runtime r = Runtime.getRuntime();
/* 1068 */       Process p = r.exec("uname -n");
/* 1069 */       BufferedReader rdr = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 1070 */       machineName = rdr.readLine();
/* 1071 */       rdr.close();
/* 1072 */       if (!StringUtil.isEmpty(machineName))
/*      */       {
/* 1074 */         return machineName;
/*      */       }
/*      */     }
/* 1077 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/* 1081 */     return machineName;
/*      */   }
/*      */ 
/*      */   
/*      */   public void recoverUser(UserInfo userInfo, ISessionBase session) {
/* 1086 */     if (session == null || userInfo == null)
/*      */       return; 
/* 1088 */     synchronized (this) {
/*      */       
/* 1090 */       this.m_UserInfo = userInfo;
/* 1091 */       this.m_Session = session;
/* 1092 */       Object o = this.m_Session.getSessionData();
/* 1093 */       if (o instanceof Hashtable) {
/*      */         
/* 1095 */         Object o1 = ((Hashtable)o).get("XX3");
/* 1096 */         if (o1 instanceof Boolean)
/*      */         {
/* 1098 */           setVariable("CLI-DEV-LICENCE", o1);
/*      */         }
/* 1100 */         o1 = ((Hashtable)o).get("XX4");
/* 1101 */         if (o1 instanceof Integer)
/*      */         {
/* 1103 */           setVariable("CLI-WARN-DAYS", o1);
/*      */         }
/* 1105 */         o1 = ((Hashtable)o).get("XX5");
/* 1106 */         if (o1 instanceof Integer)
/*      */         {
/* 1108 */           setVariable("CLI-ALLOWED-DAYS", o1);
/*      */         }
/* 1110 */         o1 = ((Hashtable)o).get("XXL");
/* 1111 */         if (o1 instanceof Boolean)
/*      */         {
/* 1113 */           setVariable("isTrialSAAS", o1);
/*      */         }
/* 1115 */         o1 = ((Hashtable)o).get("XX6");
/* 1116 */         if (o1 instanceof Integer)
/*      */         {
/* 1118 */           setVariable("CLI-LICENCE-TYPE", o1);
/*      */         }
/*      */         
/* 1121 */         Hashtable sessionHash = (Hashtable)o;
/* 1122 */         JLbsInitializationData initData = (JLbsInitializationData)sessionHash.get("SessionInitData_");
/* 1123 */         initData.LoginData = sessionHash;
/* 1124 */         SessionInitializerRegistry.getInstance().initializeSession(userInfo.variableHolder, initData, 2);
/*      */       } 
/*      */     } 
/*      */     
/* 1128 */     if (this.m_Applet != null) {
/* 1129 */       this.m_Applet.userRecovered();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean login(UserInfo userInfo) {
/* 1135 */     logout();
/* 1136 */     userInfo.clientIpAddresses = getAllIpAddresses();
/* 1137 */     userInfo.clientMachineName = getClientMachineName();
/*      */     
/*      */     try {
/* 1140 */       synchronized (this) {
/*      */         
/* 1142 */         String psesid = (String)getVariable("PSESID");
/* 1143 */         if (psesid != null && !psesid.equals("")) {
/* 1144 */           userInfo.parentSessionId = psesid;
/*      */         }
/* 1146 */         this.m_Session = this.m_LoginProv.login((IChannelProvider)this.m_ChannelProv, this.m_RootUri + "Login", userInfo, this.m_ClassLoader);
/* 1147 */         HttpLoginProvider.ms_SessionCode = this.m_Session.getSessionCode();
/*      */         
/* 1149 */         this.m_UserInfo = userInfo;
/*      */ 
/*      */         
/* 1152 */         setServerTimeZone();
/*      */ 
/*      */         
/* 1155 */         Object o = this.m_Session.getSessionData();
/* 1156 */         if (o instanceof Hashtable) {
/*      */           
/* 1158 */           Object o1 = ((Hashtable)o).get("XX3");
/* 1159 */           if (o1 instanceof Boolean) {
/*      */             
/* 1161 */             setVariable("CLI-DEV-LICENCE", o1);
/* 1162 */             ((Hashtable)o).remove("XX3");
/*      */           } 
/* 1164 */           o1 = ((Hashtable)o).get("XX4");
/* 1165 */           if (o1 instanceof Integer) {
/*      */             
/* 1167 */             setVariable("CLI-WARN-DAYS", o1);
/* 1168 */             ((Hashtable)o).remove("XX4");
/*      */           } 
/* 1170 */           o1 = ((Hashtable)o).get("XX5");
/* 1171 */           if (o1 instanceof Integer) {
/*      */             
/* 1173 */             setVariable("CLI-ALLOWED-DAYS", o1);
/* 1174 */             ((Hashtable)o).remove("XX5");
/*      */           } 
/* 1176 */           o1 = ((Hashtable)o).get("XXL");
/* 1177 */           if (o1 instanceof Boolean) {
/*      */             
/* 1179 */             setVariable("isTrialSAAS", o1);
/* 1180 */             ((Hashtable)o).remove("XXL");
/*      */           } 
/* 1182 */           o1 = ((Hashtable)o).get("XX6");
/* 1183 */           if (o1 instanceof Integer) {
/*      */             
/* 1185 */             setVariable("CLI-LICENCE-TYPE", o1);
/* 1186 */             ((Hashtable)o).remove("XX6");
/*      */           } 
/*      */           
/* 1189 */           o1 = ((Hashtable)o).get("userInfo");
/* 1190 */           if (o1 != null && o1 instanceof UserInfo)
/*      */           {
/* 1192 */             this.m_UserInfo = (UserInfo)o1;
/*      */           }
/*      */           
/* 1195 */           o1 = ((Hashtable)o).get("dSignatureParams");
/* 1196 */           if (o1 != null && o1 instanceof ArrayList) {
/*      */             
/* 1198 */             String selectedLangParam = ((ArrayList<String>)o1).get(1);
/* 1199 */             if (!selectedLangParam.equals(this.m_UserInfo.selectedLanguage)) {
/*      */               
/* 1201 */               this.m_UserInfo.selectedLanguage = selectedLangParam;
/* 1202 */               this.m_UserInfo.changeLanguage = true;
/*      */             } 
/* 1204 */             this.m_UserInfo.dSignatureFirmNr = ((ArrayList<Integer>)o1).get(0);
/*      */           } 
/*      */           
/* 1207 */           o1 = ((Hashtable)o).get("userTypes");
/* 1208 */           if (o1 != null && o1 instanceof Integer)
/*      */           {
/* 1210 */             this.m_UserInfo.userTypes = ((Integer)o1).intValue();
/*      */           }
/*      */           
/* 1213 */           o1 = ((Hashtable)o).get("title_0");
/* 1214 */           Object o2 = ((Hashtable)o).get("title_1");
/* 1215 */           if (o1 != null || (o2 != null && this.m_Applet != null))
/*      */           {
/* 1217 */             this.m_Applet.modifyTitles((String)o1, (String)o2);
/*      */           }
/*      */           
/* 1220 */           o1 = ((Hashtable)o).get("access_token");
/* 1221 */           if (o1 != null && o1 instanceof String) {
/*      */             
/* 1223 */             this.m_UserInfo.oauthToken = (String)o1;
/* 1224 */             JLbsCookieHive.addCookie("oauth_token=" + (String)o1, this.m_RootUri, false);
/*      */           } 
/*      */           
/* 1227 */           o1 = ((Hashtable)o).get("refresh_token");
/* 1228 */           if (o1 != null && o1 instanceof String) {
/*      */             
/* 1230 */             this.m_UserInfo.refreshToken = (String)o1;
/* 1231 */             JLbsCookieHive.addCookie("refresh_token=" + (String)o1, this.m_RootUri, false);
/*      */           } 
/*      */           
/* 1234 */           o1 = ((Hashtable)o).get("ELOGODEFTER");
/* 1235 */           if (o1 != null && o1 instanceof Boolean) {
/* 1236 */             setVariable("ELOGODEFTER", o1);
/*      */           }
/* 1238 */           if (!JLbsStringUtil.isEmpty(HttpLoginProvider.ms_SessionCode))
/* 1239 */             JLbsCookieHive.addCookie("JSESSIONID=" + HttpLoginProvider.ms_SessionCode, this.m_RootUri, true); 
/*      */         } 
/*      */       } 
/* 1242 */       if (this.m_Applet != null)
/* 1243 */         this.m_Applet.userLoggedIn(); 
/* 1244 */       this.m_SessionInfoProvider.fireSessionCreated();
/* 1245 */       checkWorkFlowInfo(getUserDetailInfo().getFirmNr());
/* 1246 */       logoutFromBIService();
/* 1247 */       return true;
/*      */     }
/* 1249 */     catch (LoginException e) {
/*      */       
/* 1251 */       if (e.getCause() instanceof URLRedirectionException) {
/*      */         
/* 1253 */         URLRedirectionException exc = (URLRedirectionException)e.getCause();
/* 1254 */         this.m_RootUri = exc.getTargetRootURI();
/* 1255 */         userInfo.Parameters = exc.getTargetLoginParameters();
/* 1256 */         return login(userInfo);
/*      */       } 
/* 1258 */       if (e.isAccessTokenException()) {
/*      */         
/*      */         try {
/*      */ 
/*      */           
/* 1263 */           Hashtable<String, Object> oauthConfiguration = (Hashtable<String, Object>)getPublicObject("OauthConfiguration", null, true);
/*      */           
/* 1265 */           int oauthType = ((Integer)oauthConfiguration.get("OAuthType")).intValue();
/* 1266 */           String clientID = (String)oauthConfiguration.get("ClientID");
/* 1267 */           String tenantID = (String)oauthConfiguration.get("TenantID");
/* 1268 */           String stsAddress = (String)oauthConfiguration.get("StsAddress");
/* 1269 */           String rootUri = getRootUri();
/* 1270 */           String encodedURL = "";
/* 1271 */           String redirectUri = getRootUri() + "smart/run";
/* 1272 */           encodedURL = URLEncoder.encode(redirectUri, "UTF-8");
/* 1273 */           String oauthUri = null;
/* 1274 */           if (!JLbsConstants.checkAppCloud())
/*      */           {
/* 1276 */             if (oauthType != DEFAULT_OAUTH)
/*      */             {
/* 1278 */               if (oauthType == IDP_OAUTH && !JLbsStringUtil.isEmpty(clientID) && !JLbsStringUtil.isEmpty(stsAddress)) {
/*      */                 
/* 1280 */                 oauthUri = stsAddress + "/Oauth/Authorize?client_id=" + clientID + "&redirect_uri=" + encodedURL + "&response_type=token";
/*      */               
/*      */               }
/* 1283 */               else if (oauthType == AZURE_OAUTH && !JLbsStringUtil.isEmpty(tenantID) && 
/* 1284 */                 !JLbsStringUtil.isEmpty(clientID)) {
/*      */                 
/* 1286 */                 oauthUri = "https://login.microsoftonline.com/" + tenantID + "/oauth2/authorize?client_id=" + clientID + "&response_type=token&redirect_uri=" + encodedURL + "&resource=" + clientID;
/*      */               } 
/*      */               
/* 1289 */               JLbsURLUtil.displayURL(oauthUri);
/* 1290 */               System.exit(0);
/*      */             }
/*      */           
/*      */           }
/* 1294 */         } catch (UnsupportedEncodingException ex) {
/*      */           
/* 1296 */           LbsConsole.getLogger(getClass()).error(ex, ex);
/*      */         }
/* 1298 */         catch (IOException ex) {
/*      */           
/* 1300 */           LbsConsole.getLogger(getClass()).error(ex, ex);
/*      */         }
/* 1302 */         catch (Exception ex) {
/*      */           
/* 1304 */           LbsConsole.getLogger(getClass()).error(ex, ex);
/*      */         } 
/*      */       }
/* 1307 */       if (!this.m_Applet.isSilentLogin()) {
/*      */         
/* 1309 */         if (e.getErrorCode() == -19) {
/*      */           
/* 1311 */           String msg = e.getMessage();
/* 1312 */           String[] properties = msg.split("~");
/* 1313 */           JLbsStringList list = new JLbsStringList();
/* 1314 */           list.add(0, properties[3]);
/* 1315 */           list.add(1, properties[1]);
/* 1316 */           list.add(2, properties[2]);
/* 1317 */           userInfo.setOktaProperties(list);
/* 1318 */           showMessage("OKTA MFA", properties[3], 55);
/* 1319 */           return login(userInfo);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1324 */         SwingUtilities.invokeLater(new Runnable()
/*      */             {
/*      */ 
/*      */               
/*      */               public void run()
/*      */               {
/* 1330 */                 LbsClientContext.this.showMessage(LbsClientContext.this.getCultureResource("Login Failed!", 1018), e.getMessage());
/*      */               }
/*      */             });
/*      */       } 
/*      */       
/* 1335 */       this.m_Applet.userLoginFailed();
/*      */     }
/* 1337 */     catch (Exception e) {
/*      */       
/* 1339 */       if (!this.m_Applet.isSilentLogin())
/*      */       {
/*      */         
/* 1342 */         SwingUtilities.invokeLater(new Runnable()
/*      */             {
/*      */ 
/*      */               
/*      */               public void run()
/*      */               {
/* 1348 */                 LbsClientContext.this.showMessage(LbsClientContext.this.getCultureResource("Login Failed!", 1018), e.getMessage());
/*      */               }
/*      */             });
/*      */       }
/* 1352 */       this.m_Applet.userLoginFailed();
/*      */     } 
/*      */     
/* 1355 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setServerTimeZone() {
/* 1361 */     Object sessionData = this.m_Session.getSessionData();
/* 1362 */     if (sessionData instanceof Hashtable) {
/*      */       
/* 1364 */       Hashtable sessionHash = (Hashtable)sessionData;
/* 1365 */       JLbsInitializationData initData = (JLbsInitializationData)sessionHash.get("SessionInitData_");
/* 1366 */       setServerTimeZoneInternal(initData);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setServerTimeZoneInternal(JLbsInitializationData initData) {
/* 1372 */     if (initData.ServerTimeZone != null) {
/*      */       
/* 1374 */       this.m_DefaultTimeZone = TimeZone.getDefault();
/* 1375 */       JLbsDateUtil.setServerTimeZone(initData.ServerTimeZone);
/* 1376 */       this.m_ServerTimeZone = initData.ServerTimeZone;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCultureResource(String defaultString, int resID) {
/* 1382 */     String s = null;
/* 1383 */     s = JLbsLocalizer.getCultureResource(resID);
/* 1384 */     return (s == null || s.equals("")) ? defaultString : s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean changeCompany(UserInfo userInfo) {
/* 1392 */     userInfo.clientIpAddresses = getAllIpAddresses();
/* 1393 */     userInfo.clientMachineName = getClientMachineName();
/*      */     
/*      */     try {
/* 1396 */       synchronized (this) {
/*      */         
/* 1398 */         this.m_Session = this.m_LoginProv.login((IChannelProvider)this.m_ChannelProv, this.m_RootUri + "Login", userInfo, this.m_ClassLoader);
/* 1399 */         this.m_UserInfo = userInfo;
/*      */       } 
/* 1401 */       return true;
/*      */     }
/* 1403 */     catch (Exception e) {
/*      */       
/* 1405 */       showMessage("Login Failed!", e.getMessage());
/*      */ 
/*      */       
/* 1408 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isLoggedIn() {
/* 1414 */     synchronized (this) {
/*      */       
/* 1416 */       return (this.m_Session != null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean logout() {
/* 1423 */     logoutFromBIService();
/* 1424 */     return logoutEx(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean logoutEx(boolean closeForms) {
/* 1429 */     if (isLoggedIn()) {
/*      */       
/*      */       try {
/* 1432 */         if (closeForms)
/* 1433 */           JLbsOpenWindowListing.closeAll(); 
/* 1434 */         synchronized (this) {
/*      */           
/* 1436 */           getLogger().debug("Calling:" + this.m_RootUri + "Logout");
/* 1437 */           this.m_LoginProv.logout((IChannelProvider)this.m_ChannelProv, this.m_RootUri + "Logout", this.m_Session);
/* 1438 */           if (this.m_BaseClient.getRecorder() != null)
/*      */           {
/* 1440 */             this.m_BaseClient.getRecorder().dumpAndClear();
/*      */           }
/*      */           
/* 1443 */           String ltpaToken = getCookie("LtpaToken");
/* 1444 */           if (!StringUtil.isEmpty(ltpaToken)) {
/*      */ 
/*      */ 
/*      */             
/* 1448 */             getLogger().debug("Destroying ltpaToken: " + ltpaToken);
/* 1449 */             setCookie("LtpaToken", "");
/*      */           } 
/*      */           
/* 1452 */           this.m_Session = null;
/* 1453 */           this.m_TransClient = null;
/* 1454 */           this.m_RemoteInvoker = null;
/*      */         } 
/* 1456 */         clearCaches();
/* 1457 */         if (this.m_Applet != null)
/* 1458 */           this.m_Applet.userLoggedOut(); 
/* 1459 */         this.m_SessionInfoProvider.fireSessionDestroyed();
/* 1460 */         return true;
/*      */       }
/* 1462 */       catch (Exception exception) {}
/*      */     }
/*      */     
/* 1465 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ISessionBase getSession() {
/* 1474 */     return this.m_Session;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized UserInfo getUserInfo() {
/* 1484 */     return this.m_UserInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void realignContent() {
/* 1490 */     Container container = (this.m_Applet != null) ? this.m_Applet.getContentPane() : null;
/*      */     
/* 1492 */     if (container == null) {
/*      */       return;
/*      */     }
/* 1495 */     if (container.getComponentCount() == 1) {
/*      */       
/* 1497 */       Component comp = container.getComponent(0);
/* 1498 */       if (comp instanceof com.lbs.remoteclient.IClientFullScreen) {
/*      */         
/* 1500 */         Rectangle bounds = container.getBounds();
/*      */         
/* 1502 */         if (this.m_Applet != null && this.m_Applet.getBaseApplet() instanceof JLbsStartup) {
/*      */           
/* 1504 */           Dimension dimParent = ((JLbsStartup)this.m_Applet.getBaseApplet()).getSetDimension();
/* 1505 */           if (dimParent == null)
/* 1506 */             dimParent = this.m_Applet.getBaseApplet().getSize(); 
/* 1507 */           if (dimParent != null) {
/*      */             
/* 1509 */             bounds.width = dimParent.width;
/* 1510 */             bounds.height = dimParent.height;
/*      */           } 
/*      */         } 
/*      */         
/* 1514 */         comp.setBounds(bounds);
/* 1515 */         comp.setSize(bounds.width, bounds.height);
/* 1516 */         comp.setLocation(bounds.x, bounds.y);
/* 1517 */         forceLayout(comp);
/*      */       }
/*      */       else {
/*      */         
/* 1521 */         Dimension dim = comp.getSize();
/* 1522 */         Dimension dimParent = null;
/* 1523 */         if (this.m_Applet != null && this.m_Applet.getBaseApplet() instanceof JLbsStartup) {
/*      */           
/* 1525 */           dimParent = ((JLbsStartup)this.m_Applet.getBaseApplet()).getSetDimension();
/* 1526 */           if (dimParent == null) {
/* 1527 */             dimParent = this.m_Applet.getBaseApplet().getSize();
/*      */           }
/*      */         } else {
/* 1530 */           dimParent = container.getSize();
/* 1531 */         }  comp.setLocation((dimParent.width - dim.width) / 2, (dimParent.height - dim.height) / 2);
/*      */       } 
/* 1533 */       comp.repaint();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void forceLayout(Component c) {
/* 1540 */     if (c != null) {
/*      */       
/* 1542 */       c.doLayout();
/* 1543 */       if (c instanceof Container) {
/*      */         
/* 1545 */         Container cont = (Container)c;
/* 1546 */         for (int i = 0; i < cont.getComponentCount(); i++) {
/*      */           
/* 1548 */           Component child = cont.getComponent(i);
/* 1549 */           forceLayout(child);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void replaceContent(Component comp) {
/* 1561 */     Thread.currentThread().setContextClassLoader(this.m_ClassLoader);
/* 1562 */     if (comp == null || this.m_Applet == null)
/*      */       return; 
/* 1564 */     Container container = this.m_Applet.getContentPane();
/* 1565 */     container.removeAll();
/*      */ 
/*      */     
/* 1568 */     Dimension dim = comp.getPreferredSize();
/* 1569 */     if (dim == null || dim.width <= 0 || dim.height <= 0)
/* 1570 */       dim = comp.getSize(); 
/* 1571 */     Dimension dimParent = container.getSize();
/* 1572 */     container.add(comp);
/* 1573 */     if (comp instanceof com.lbs.remoteclient.IClientFullScreen) {
/*      */       
/* 1575 */       Rectangle bounds = container.getBounds();
/* 1576 */       comp.setBounds(bounds);
/* 1577 */       comp.setSize(bounds.width, bounds.height);
/* 1578 */       comp.setLocation(bounds.x, bounds.y);
/*      */     }
/*      */     else {
/*      */       
/* 1582 */       comp.setSize(dim);
/* 1583 */       comp.setLocation((dimParent.width - dim.width) / 2, (dimParent.height - dim.height) / 2);
/*      */     } 
/*      */     
/* 1586 */     if (comp instanceof IClientUIInit) {
/*      */       
/* 1588 */       IClientUIInit unit = (IClientUIInit)comp;
/* 1589 */       if (comp instanceof ITokenProcessor) {
/*      */         
/* 1591 */         ITokenProcessor tokenpro = (ITokenProcessor)comp;
/* 1592 */         if (tokenpro.isShowWithProduct()) {
/* 1593 */           unit.initUI();
/*      */         }
/*      */       } else {
/* 1596 */         unit.initUI();
/* 1597 */       }  this.m_Applet.setWindowClosingListener(unit);
/*      */     } 
/* 1599 */     if (comp instanceof JComponent)
/* 1600 */       ((JComponent)comp).updateUI(); 
/* 1601 */     SwingUtilities.invokeLater(new InvalidateComponents(container, comp));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteMethodResponse callRemoteMethod(String objName, String methodName, Object[] parameters) throws Exception {
/* 1611 */     return callRemoteMethod(objName, methodName, parameters, (boolean[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteMethodResponse callRemoteMethod(boolean newInstance, String objName, String methodName, Object[] parameters, boolean[] returnParams) throws Exception {
/* 1618 */     if (!isLoggedIn()) {
/* 1619 */       throw new Exception("Not logged in!");
/*      */     }
/* 1621 */     ensureTransClientExist();
/* 1622 */     return this.m_RemoteInvoker.invoke(objName, methodName, parameters, returnParams, this.m_ClassLoader, newInstance);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteMethodResponse callRemoteMethod(String objName, String methodName, Object[] parameters, boolean[] returnParams) throws Exception {
/* 1629 */     return callRemoteMethod(false, objName, methodName, parameters, returnParams);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteMethodResponse callRemoteMethod(String objName, String methodName, Object[] parameters, boolean[] returnParams, String serverRootURI) throws Exception {
/* 1636 */     if (!isLoggedIn()) {
/* 1637 */       throw new Exception("Not logged in!");
/*      */     }
/* 1639 */     IRemoteMethodInvoker remoteInvoker = ensureTransClientExist(serverRootURI);
/* 1640 */     return remoteInvoker.invoke(objName, methodName, parameters, returnParams, this.m_ClassLoader, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteMethodResponse callRemoteMethod(boolean newInstance, String objName, String methodName, Object[] parameters) throws Exception {
/* 1647 */     return callRemoteMethod(newInstance, objName, methodName, parameters, (boolean[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getResource(String resName, boolean bLangDep) throws FileNotFoundException {
/* 1653 */     if (resName == null || resName.length() == 0) {
/* 1654 */       return null;
/*      */     }
/*      */     try {
/* 1657 */       if (bLangDep)
/* 1658 */         resName = getLangDepResName(resName); 
/* 1659 */       boolean noCaching = resName.endsWith("!");
/* 1660 */       if (noCaching)
/* 1661 */         resName = resName.substring(0, resName.length() - 1); 
/* 1662 */       if (!noCaching) {
/*      */ 
/*      */         
/* 1665 */         synchronized (this.m_ResourceCache) {
/*      */           
/* 1667 */           cachedResource = (byte[])this.m_ResourceCache.get(resName);
/*      */         } 
/* 1669 */         if (cachedResource != null)
/* 1670 */           return cachedResource; 
/* 1671 */         byte[] cachedResource = JLbsJarCache.getResourceEntry(resName);
/* 1672 */         if (cachedResource != null)
/* 1673 */           return cachedResource; 
/*      */       } 
/* 1675 */       byte[] result = getServletData(new String[] { "@" + resName }, "ResDownload", null, false);
/*      */       
/* 1677 */       if (result != null && !noCaching)
/* 1678 */         synchronized (this.m_ResourceCache) {
/*      */           
/* 1680 */           if (TransportUtil.checkFormDataUseful(resName, result))
/* 1681 */             this.m_ResourceCache.put(resName, result); 
/*      */         }  
/* 1683 */       return result;
/*      */     }
/* 1685 */     catch (FileNotFoundException e) {
/*      */       
/* 1687 */       throw e;
/*      */     }
/* 1689 */     catch (Exception exception) {
/*      */ 
/*      */       
/* 1692 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectResource(String resName, boolean bLangDep) throws InvalidObjectException {
/*      */     try {
/* 1700 */       byte[] data = getResource(resName, bLangDep);
/* 1701 */       return (data != null) ? 
/* 1702 */         deserializeObject(data) : null;
/*      */     
/*      */     }
/* 1705 */     catch (Exception e) {
/*      */       
/* 1707 */       throw new InvalidObjectException("The stream dows not contain a serialized object");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Hashtable getCustomizationFormList() {
/* 1713 */     Hashtable<Object, Object> formList = new Hashtable<>();
/*      */     
/*      */     try {
/* 1716 */       RemoteMethodResponse response = callRemoteMethod("SCS", "getCustomizationFormList", new Object[] { null });
/*      */       
/* 1718 */       if (response != null && response.Result instanceof Hashtable) {
/* 1719 */         formList = (Hashtable<Object, Object>)response.Result;
/*      */       }
/* 1721 */     } catch (Exception e) {
/*      */       
/* 1723 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1726 */     return formList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[][] loadMultipleResource(String[] resNames, boolean bLangDep) {
/* 1732 */     if (resNames == null || resNames.length == 0) {
/* 1733 */       return (byte[][])null;
/*      */     }
/*      */     
/*      */     try {
/* 1737 */       StringBuilder requestList = new StringBuilder();
/* 1738 */       ITransportCache transCache = (ITransportCache)this.m_BaseClient;
/*      */       
/* 1740 */       ArrayList<ResourceEntryInfo> resourceEntries = new ArrayList();
/* 1741 */       int reqResCount = 0;
/* 1742 */       for (int i = 0; i < resNames.length; i++) {
/*      */         
/* 1744 */         String currentResName = resNames[i];
/* 1745 */         if (!JLbsStringUtil.isEmpty(currentResName)) {
/*      */ 
/*      */           
/* 1748 */           boolean bSkipCache = currentResName.endsWith("!");
/* 1749 */           boolean bCustomizedRes = transCache.isCustomizationResource(currentResName);
/* 1750 */           ResourceEntryInfo info = createResourceEntry(transCache, currentResName, bLangDep, bSkipCache, bCustomizedRes);
/* 1751 */           if (JLbsConstants.SKIP_CACHE)
/* 1752 */             info.setData(null); 
/* 1753 */           if (info.getData() == null) {
/*      */             
/* 1755 */             addToRequestList(transCache, info, requestList, bSkipCache);
/* 1756 */             info.setIndex(reqResCount++);
/*      */           } 
/*      */           
/* 1759 */           resourceEntries.add(info);
/*      */         } 
/*      */       } 
/* 1762 */       if (reqResCount > 0) {
/* 1763 */         updateResourceEntries(bLangDep, requestList, transCache, resourceEntries, reqResCount);
/*      */       }
/* 1765 */       int entryCount = resourceEntries.size();
/* 1766 */       if (entryCount > 0)
/*      */       {
/* 1768 */         byte[][] result = new byte[entryCount][];
/* 1769 */         for (int j = 0; j < entryCount; j++) {
/*      */           
/* 1771 */           ResourceEntryInfo info = resourceEntries.get(j);
/*      */           
/* 1773 */           byte[] data = info.getData();
/* 1774 */           result[j] = data;
/*      */         } 
/* 1776 */         return result;
/*      */       }
/*      */     
/*      */     }
/* 1780 */     catch (StreamCorruptedException e) {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 1785 */         if (this.m_RemoteInvoker != null && this.m_RemoteInvoker.getTimeoutHandler() != null) {
/* 1786 */           this.m_RemoteInvoker.getTimeoutHandler().remoteSessionTimeout(e);
/*      */         }
/* 1788 */       } catch (SessionReestablishedException e1) {
/*      */         
/* 1790 */         return loadMultipleResource(resNames, bLangDep);
/*      */       } 
/*      */       
/* 1793 */       getLogger().error(e);
/* 1794 */       StringBuilder resourceList = prepareResourceList(resNames);
/* 1795 */       String errMessage = "Error loading resources!";
/* 1796 */       if (resourceList != null)
/* 1797 */         errMessage = errMessage + " Problematic Resources :" + resourceList.toString() + "."; 
/* 1798 */       getLogger().error(errMessage);
/*      */     }
/* 1800 */     catch (Exception e) {
/*      */       
/* 1802 */       getLogger().error(e);
/* 1803 */       StringBuilder resourceList = prepareResourceList(resNames);
/* 1804 */       String errMessage = "Error loading resources!";
/* 1805 */       if (resourceList != null)
/* 1806 */         errMessage = errMessage + " Problematic Resources :" + resourceList.toString() + "."; 
/* 1807 */       getLogger().error(errMessage);
/*      */     } 
/*      */     
/* 1810 */     return (byte[][])null;
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
/*      */   
/*      */   private void updateResourceEntries(boolean bLangDep, StringBuilder requestList, ITransportCache transCache, ArrayList<ResourceEntryInfo> resourceEntries, int reqResCount) throws Exception {
/* 1824 */     requestList.setLength(requestList.length() - 1);
/* 1825 */     RemoteMethodResponse metRes = callRemoteMethod("MRD", "getResources", new Object[] { null, requestList.toString() }, (boolean[])null);
/* 1826 */     if (metRes != null && metRes.Result instanceof byte[][]) {
/*      */       
/* 1828 */       byte[][] metResult = (byte[][])metRes.Result;
/* 1829 */       if (metResult.length == reqResCount)
/*      */       
/* 1831 */       { for (int i = 0; i < resourceEntries.size(); i++) {
/*      */           
/* 1833 */           ResourceEntryInfo info = resourceEntries.get(i);
/* 1834 */           if (info.getData() == null) {
/*      */             
/* 1836 */             byte[] resData = metResult[info.getIndex()];
/* 1837 */             if (resData != null) {
/*      */               
/* 1839 */               String resName = info.getResourcePath();
/* 1840 */               if (resData.length == 0 && transCache != null) {
/* 1841 */                 info.setData(transCache.getPureFileData(bLangDep ? info
/* 1842 */                       .getResourcePath() : info
/* 1843 */                       .getFileNameHashPath()));
/*      */               } else {
/*      */                 
/* 1846 */                 if (JLbsTransportMode.COMPRESSION_ON && resData.length != 0) {
/*      */                   
/* 1848 */                   StdCompressor comp = new StdCompressor();
/* 1849 */                   resData = comp.uncompress(resData, 0, resData.length);
/*      */                 } 
/*      */                 
/* 1852 */                 info.setData(resData);
/* 1853 */                 if (info.getFileNameHashPath() != null && !info.isSkipCache() && !info.isCustomizedRes()) {
/*      */                   
/* 1855 */                   byte[] saveData = resData;
/* 1856 */                   transCache.saveFileData(bLangDep ? info
/* 1857 */                       .getLangDepHash() : info
/* 1858 */                       .getFileNameHash(), info.getFileDataHash(), saveData, resName);
/*      */                 } 
/*      */               } 
/*      */               
/* 1862 */               synchronized (this.m_ResourceCache) {
/*      */                 
/* 1864 */                 if (TransportUtil.checkFormDataUseful(resName, info.getData()))
/* 1865 */                   this.m_ResourceCache.put(resName, info.getData()); 
/*      */               } 
/* 1867 */               if (!bLangDep && resName.toUpperCase().endsWith(".JAR"))
/* 1868 */                 addToJarCache(resName, info.getData(), null, false); 
/*      */             } 
/*      */           } 
/*      */         }  }
/* 1872 */       else { throw new Exception("Remote method call result is not valid!"); }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ResourceEntryInfo createResourceEntry(ITransportCache transCache, String resourceName, boolean bLangDep, boolean bSkipCache, boolean bCustomizedRes) {
/* 1880 */     ResourceEntryInfo info = new ResourceEntryInfo();
/* 1881 */     info.setSkipCache(bSkipCache);
/* 1882 */     info.setCustomizedRes(bCustomizedRes);
/* 1883 */     info.setResourcePath(resourceName);
/* 1884 */     info.setFileNameHashPath("ResDownload?@" + info.getResourcePath());
/* 1885 */     if (bLangDep) {
/*      */       
/* 1887 */       info.setResourcePath(getLangDepResName(info.getResourcePath()));
/* 1888 */       info.setLangDepHash(JLbsCryptoUtil.createHashString(info.getResourcePath().getBytes()));
/*      */     } else {
/*      */       
/* 1891 */       info.setLangDepHash(null);
/*      */     } 
/* 1893 */     if (info.isSkipCache()) {
/* 1894 */       info.setResourcePath(info.getResourcePath().substring(0, info.getResourcePath().length() - 1));
/*      */     
/*      */     }
/* 1897 */     else if (this.m_ResourceCache.containsKey(info.getResourcePath())) {
/* 1898 */       info.setData((byte[])this.m_ResourceCache.get(info.getResourcePath()));
/* 1899 */     } else if (!info.isCustomizedRes()) {
/*      */       
/* 1901 */       synchronized (this.m_ResourceCache) {
/*      */ 
/*      */         
/*      */         try {
/* 1905 */           info.setFileDataHash(transCache.getFileHash(bLangDep ? info
/* 1906 */                 .getResourcePath() : info
/* 1907 */                 .getFileNameHashPath()));
/*      */         }
/* 1909 */         catch (FileUpToDateException e) {
/*      */           
/* 1911 */           info.setData(e.getData());
/* 1912 */           synchronized (this.m_ResourceCache) {
/*      */             
/* 1914 */             if (TransportUtil.checkFormDataUseful(info.getResourcePath(), info.getData()))
/* 1915 */               this.m_ResourceCache.put(info.getResourcePath(), info.getData()); 
/*      */           } 
/* 1917 */           if (!bLangDep && info.getResourceType() == 3) {
/* 1918 */             addToJarCache(info.getResourcePath(), info.getData(), null, false);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1925 */     return info;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addToRequestList(ITransportCache transCache, ResourceEntryInfo info, StringBuilder requestList, boolean bSkipCache) throws FileUpToDateException {
/* 1931 */     requestList.append(info.getResourcePath());
/* 1932 */     if (transCache != null && !bSkipCache) {
/*      */       
/* 1934 */       if (info.getFileNameHash() == null)
/*      */       {
/* 1936 */         info.setFileNameHash(transCache.getFileHash(info.getFileNameHashPath())); } 
/* 1937 */       if (!JLbsStringUtil.isEmpty(info.getFileDataHash())) {
/*      */         
/* 1939 */         requestList.append(';');
/* 1940 */         requestList.append(info.getFileDataHash());
/*      */       } 
/*      */     } 
/* 1943 */     requestList.append('|');
/*      */   }
/*      */ 
/*      */   
/*      */   private StringBuilder prepareResourceList(String[] resNames) {
/* 1948 */     if (resNames == null || resNames.length == 0) {
/* 1949 */       return null;
/*      */     }
/* 1951 */     StringBuilder sbResourceList = new StringBuilder();
/* 1952 */     for (int i = 0; i < resNames.length; i++) {
/*      */       
/* 1954 */       if (i > 0)
/* 1955 */         sbResourceList.append("; "); 
/* 1956 */       sbResourceList.append(resNames[i]);
/*      */     } 
/*      */     
/* 1959 */     return sbResourceList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getPublicObject(String objectID, Object params, boolean bAsObject) throws Exception {
/* 1968 */     Object result = getFromCache(objectID, params, bAsObject);
/* 1969 */     if (result != null)
/* 1970 */       return result; 
/* 1971 */     byte[] retData = getServletData(new String[] { objectID }, "PublicInfo", new Object[] { params }, true);
/* 1972 */     if (!bAsObject)
/* 1973 */       return retData; 
/* 1974 */     result = TransportUtil.deserializeObject(this.m_ClassLoader, retData);
/* 1975 */     if (result instanceof Exception) {
/* 1976 */       throw (Exception)result;
/*      */     }
/* 1978 */     return putToCache(objectID, params, bAsObject, result);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Object putToCache(String objectID, Object params, boolean bAsObject, Object result) {
/* 1984 */     if (params == null && bAsObject) {
/*      */       
/* 1986 */       Hashtable<Object, Object> cache = null;
/* 1987 */       Object obj = getVariable("~l_pub_inf");
/* 1988 */       if (obj instanceof Hashtable) {
/* 1989 */         cache = (Hashtable)obj;
/*      */       } else {
/* 1991 */         cache = new Hashtable<>();
/*      */       } 
/* 1993 */       if (result == null) {
/* 1994 */         cache.remove(objectID);
/*      */       } else {
/* 1996 */         cache.put(objectID, result);
/* 1997 */       }  setVariable("~l_pub_inf", cache);
/*      */     } 
/* 1999 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Object getFromCache(String objectID, Object params, boolean bAsObject) {
/* 2005 */     if (params == null && bAsObject) {
/*      */       
/* 2007 */       Object obj = getVariable("~l_pub_inf");
/* 2008 */       if (obj instanceof Hashtable) {
/*      */         
/* 2010 */         Hashtable cache = (Hashtable)obj;
/* 2011 */         return cache.get(objectID);
/*      */       } 
/*      */     } 
/* 2014 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] getPublicObjects(String[] objectIDs, Object[] params, boolean bAsObject) throws Exception {
/* 2020 */     byte[] retData = getServletData(objectIDs, "PublicInfo", params, true);
/* 2021 */     if (!bAsObject) {
/* 2022 */       return new Object[] { retData };
/*      */     }
/* 2024 */     Object list = TransportUtil.deserializeObject(this.m_ClassLoader, retData);
/*      */     
/* 2026 */     if (list instanceof Object[]) {
/*      */       
/* 2028 */       Object[] objs = (Object[])list;
/* 2029 */       if (params == null || params.length == 0) {
/*      */         
/* 2031 */         int cnt = Math.min(objectIDs.length, objs.length);
/* 2032 */         for (int i = 0; i < cnt; i++)
/*      */         {
/* 2034 */           putToCache(objectIDs[i], null, bAsObject, objs[i]);
/*      */         }
/*      */       } 
/* 2037 */       return objs;
/*      */     } 
/* 2039 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void terminateSession(boolean exit) {
/* 2048 */     logout();
/* 2049 */     UserInfo.MODE_LOGIN mode = this.m_UserInfo.modeLogin;
/* 2050 */     clearUserInfoAndHashTable();
/*      */     
/* 2052 */     if (this.m_Applet != null) {
/*      */       
/* 2054 */       setSessionTimeout(0);
/* 2055 */       if (!exit) {
/*      */         
/* 2057 */         this.m_Applet.initApplet(exit);
/* 2058 */         this.m_Applet.initStartupForm();
/*      */       } 
/*      */     } 
/* 2061 */     if (JLbsConstants.LDAP_AUTOLOGIN && (mode == UserInfo.MODE_LOGIN.LDAP_FORCE_PASSWORD || mode == UserInfo.MODE_LOGIN.LDAP)) {
/* 2062 */       terminateApplication();
/*      */     }
/*      */   }
/*      */   
/*      */   public void logoutFromBIService() {
/* 2067 */     if (isLoggedIn()) {
/*      */       
/*      */       try {
/*      */         
/* 2071 */         callRemoteMethod("BI", "logoutFromBIWhenJplatformSessionTerminated", new Object[] { null });
/*      */       }
/* 2073 */       catch (Exception e) {
/*      */         
/* 2075 */         getLogger().error("terminateSession", e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void terminateApplication() {
/* 2086 */     logoutFromBIService();
/* 2087 */     if (this.m_Applet == null)
/*      */       return; 
/* 2089 */     setSessionTimeout(0);
/* 2090 */     AppletContext appContext = null;
/*      */     
/*      */     try {
/* 2093 */       if (this.m_Applet.getBaseApplet() != null) {
/* 2094 */         appContext = this.m_Applet.getBaseApplet().getAppletContext();
/*      */       } else {
/* 2096 */         appContext = this.m_Applet.getAppletContext();
/*      */       } 
/* 2098 */     } catch (Exception e) {
/*      */       
/* 2100 */       appContext = null;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 2105 */       if (appContext != null)
/*      */       {
/* 2107 */         String urlAddress = (this.m_Applet.m_TerminateURI != null) ? this.m_Applet.m_TerminateURI : "http://www.lbs.com.tr";
/*      */ 
/*      */         
/* 2110 */         URL url = new URL(urlAddress);
/* 2111 */         appContext.showDocument(url);
/*      */       }
/*      */       else
/*      */       {
/* 2115 */         System.out.println("Applet Context is null, exiting application..");
/* 2116 */         System.exit(0);
/*      */       }
/*      */     
/* 2119 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTerminateURI(String langPrefix) {
/* 2128 */     if (this.m_Applet == null || this.m_Applet.m_TerminateURI == null) {
/*      */       return;
/*      */     }
/* 2131 */     int idx = this.m_Applet.m_TerminateURI.indexOf("?");
/*      */     
/* 2133 */     if (idx != -1)
/*      */     {
/* 2135 */       if (this.m_Applet.m_TerminateURI.length() > idx) {
/* 2136 */         this.m_Applet.m_TerminateURI = this.m_Applet.m_TerminateURI.substring(0, idx + 1) + langPrefix;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getVariable(Object key) {
/* 2146 */     synchronized (this.m_HashTable) {
/*      */       
/* 2148 */       return (key == null) ? null : this.m_HashTable
/*      */         
/* 2150 */         .getValue(key.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVariable(Object key, Object obj) {
/* 2160 */     if (key == null && obj == null)
/*      */       return; 
/* 2162 */     synchronized (this.m_HashTable) {
/*      */       
/* 2164 */       if (key != null) {
/* 2165 */         this.m_HashTable.setValue(key.toString(), obj);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getSerializedObject(String resName, boolean bLangDep) {
/*      */     try {
/* 2177 */       byte[] res = getResource(resName, bLangDep);
/* 2178 */       if (res != null) {
/* 2179 */         return TransportUtil.deserializeObjectPlain(this.m_ClassLoader, res);
/*      */       }
/* 2181 */     } catch (Exception e) {
/*      */ 
/*      */ 
/*      */       
/* 2185 */       getLogger().error("getSerializedObject", e);
/*      */     } 
/* 2187 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object deserializeObject(byte[] data) {
/*      */     try {
/* 2198 */       return TransportUtil.deserializeObject(this.m_ClassLoader, data);
/*      */     }
/* 2200 */     catch (Exception e) {
/*      */ 
/*      */ 
/*      */       
/* 2204 */       getLogger().error("deserializeObject", e);
/*      */       
/* 2206 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IObjectFactory getObjectFactory() {
/* 2215 */     ensureTransClientExist();
/* 2216 */     return (this.m_RemoteInvoker != null) ? (IObjectFactory)new JLbsClientObjectFactory(this, this.m_RemoteInvoker, "SF", this.m_ClassLoader) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ILocalizationServices getLocalizationService() {
/* 2224 */     if (this.m_LocalizationService == null) {
/* 2225 */       this.m_LocalizationService = (ILocalizationServices)new ClientLocalizationServices(this, this.m_ApplicationVersion);
/*      */     }
/* 2227 */     return this.m_LocalizationService;
/*      */   }
/*      */ 
/*      */   
/*      */   public void refreshLocalizationService() {
/* 2232 */     this.m_LocalizationService = (ILocalizationServices)new ClientLocalizationServices(this, this.m_ApplicationVersion);
/*      */   }
/*      */ 
/*      */   
/*      */   public void refreshReportingLocalizationService() {
/* 2237 */     this.m_ReportingLocalizationService = (ILocalizationServices)new ClientReportingLocalizationServices(this, this.m_ApplicationVersion);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ILocalizationServices getReportingLocalizationService() {
/* 2243 */     if (this.m_ReportingLocalizationService == null) {
/* 2244 */       this.m_ReportingLocalizationService = (ILocalizationServices)new ClientReportingLocalizationServices(this, this.m_ApplicationVersion);
/*      */     }
/* 2246 */     return this.m_ReportingLocalizationService;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getLangDepResName(String resName) {
/* 2251 */     Object varLang = getVariable("CLI-LANG");
/*      */     
/* 2253 */     String prefix = (varLang != null) ? varLang.toString() : null;
/*      */     
/* 2255 */     if (prefix != null)
/* 2256 */       resName = prefix + "/" + resName; 
/* 2257 */     return resName;
/*      */   }
/*      */ 
/*      */   
/*      */   protected byte[] getServletData(String[] resName, String uriSuffix, Object[] sendParam, boolean bPublicInfo) throws Exception {
/*      */     byte[] sendData;
/*      */     byte[] retData;
/* 2264 */     if (resName == null || resName.length == 0) {
/* 2265 */       return null;
/*      */     }
/* 2267 */     if (resName[0] == null || resName[0].length() == 0) {
/* 2268 */       return null;
/*      */     }
/* 2270 */     if (bPublicInfo) {
/*      */       
/* 2272 */       PublicInfoRequest request = new PublicInfoRequest();
/* 2273 */       request.ObjectIDs = resName;
/* 2274 */       request.Parameters = sendParam;
/* 2275 */       sendData = TransportUtil.serializeObject(request);
/*      */     } else {
/*      */       
/* 2278 */       sendData = resName[0].getBytes();
/*      */     } 
/* 2280 */     synchronized (this.m_BaseClient) {
/*      */       
/* 2282 */       String encodedResName = resName[0];
/*      */       
/*      */       try {
/* 2285 */         encodedResName = URLEncoder.encode(resName[0], "UTF-8");
/*      */       }
/* 2287 */       catch (UnsupportedEncodingException e) {
/*      */         
/* 2289 */         getLogger().error(e, e);
/*      */       } 
/* 2291 */       retData = this.m_BaseClient.postData(this.m_RootUri + uriSuffix + "?" + encodedResName, null, sendData, bPublicInfo);
/*      */     } 
/*      */     
/* 2294 */     if (retData != null && retData.length > 4) {
/*      */ 
/*      */       
/*      */       try {
/* 2298 */         StdCompressor comp = new StdCompressor();
/* 2299 */         byte[] unCompData = comp.uncompress(retData, 0, retData.length);
/* 2300 */         if (unCompData != null) {
/* 2301 */           retData = unCompData;
/*      */         }
/* 2303 */       } catch (Exception exception) {}
/*      */ 
/*      */       
/* 2306 */       return retData;
/*      */     } 
/*      */     
/* 2309 */     throw new FileNotFoundException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void clearUserInfoAndHashTable() {
/* 2319 */     this.m_HashTable = new NameValueMap();
/* 2320 */     this.m_UserInfo = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean changeTheme(String newThemeClass) {
/*      */     try {
/* 2331 */       UIManager.setLookAndFeel(newThemeClass);
/* 2332 */       UIManager.put("ClassLoader", this.m_ClassLoader);
/* 2333 */       if (this.m_Applet != null)
/* 2334 */         SwingUtilities.updateComponentTreeUI(this.m_Applet); 
/* 2335 */       ArrayList<Component> list = JLbsOpenWindowListing.getOpenDialogs();
/* 2336 */       for (int i = 0; i < list.size(); i++)
/* 2337 */         SwingUtilities.updateComponentTreeUI(list.get(i)); 
/* 2338 */       if (this.m_Applet != null) {
/* 2339 */         SwingUtilities.updateComponentTreeUI(this.m_Applet.getRootPane());
/*      */       }
/* 2341 */     } catch (Exception e) {
/*      */       
/* 2343 */       getLogger().error("change Theme exception", e);
/*      */     } 
/*      */     
/* 2346 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isJarInCache(String jarName) {
/* 2351 */     return (JLbsJarCache.hasJarFile(jarName) || JLbsJarCache.hasJarFile("Jars/" + jarName));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean loadJAR(String jarName, boolean signVerify, boolean silentLoad) throws SignatureException, FileNotFoundException {
/* 2361 */     Thread.currentThread().setContextClassLoader(this.m_ClassLoader);
/* 2362 */     if (jarName == null || jarName.length() == 0) {
/* 2363 */       return false;
/*      */     }
/* 2365 */     if (!isLoggedIn()) {
/*      */       
/* 2367 */       String[] jars = jarName.split(";");
/* 2368 */       if (jars == null || jars.length == 0)
/* 2369 */         return false; 
/* 2370 */       for (int i = 0; i < jars.length; i++) {
/*      */         
/* 2372 */         if (!loadSingleJars(jars[i], signVerify, silentLoad))
/* 2373 */           return false; 
/*      */       } 
/* 2375 */       return true;
/*      */     } 
/*      */     
/* 2378 */     return loadMultipleJars(jarName.split(";"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected byte[] readStream(InputStream inStream) {
/*      */     try {
/* 2385 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 2386 */       byte[] buffer = new byte[4096];
/*      */       int read;
/* 2388 */       while ((read = inStream.read(buffer, 0, buffer.length)) > 0)
/* 2389 */         out.write(buffer, 0, read); 
/* 2390 */       return out.toByteArray();
/*      */     }
/* 2392 */     catch (Exception exception) {
/*      */ 
/*      */       
/* 2395 */       return new byte[0];
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getFilePath(String url) {
/* 2400 */     int index = url.lastIndexOf("/");
/* 2401 */     if (index >= 0)
/* 2402 */       url = url.substring(index + 1); 
/* 2403 */     url = url + JLbsContextLocator.getUnityVersion();
/* 2404 */     if (JLbsClientFS.getRootPath() == null) {
/*      */       
/* 2406 */       String path = JLbsFileUtil.getClientRootDirectory();
/*      */       
/* 2408 */       path = (new File(".")).getAbsolutePath();
/* 2409 */       JLbsClientFS.setRootPath(path);
/*      */     } 
/* 2411 */     url = JLbsCryptoUtil.createHashString(url.getBytes());
/* 2412 */     return JLbsFileUtil.appendPath(JLbsClientFS.getCachePath(), url);
/*      */   }
/*      */ 
/*      */   
/*      */   private String getFileTimeStamp(String filePath) {
/* 2417 */     if (filePath != null) {
/*      */       
/* 2419 */       File f = new File(filePath);
/* 2420 */       if (f.exists()) {
/*      */         
/* 2422 */         long time = f.lastModified();
/* 2423 */         TimeZone zone = TimeZone.getTimeZone("GMT");
/* 2424 */         Calendar cal = Calendar.getInstance(zone, Locale.UK);
/* 2425 */         cal.setTimeInMillis(time);
/* 2426 */         SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.UK);
/* 2427 */         format.setTimeZone(zone);
/* 2428 */         return format.format(cal.getTime());
/*      */       } 
/*      */     } 
/* 2431 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveToCache(String filePath, byte[] data, long date) {
/*      */     try {
/* 2438 */       String fileDir = JLbsFileUtil.getFileDir(filePath);
/* 2439 */       File cacheDir = new File(fileDir);
/* 2440 */       if (!cacheDir.exists()) {
/* 2441 */         cacheDir.mkdir();
/*      */       }
/* 2443 */       JLbsClientFS.saveFile(filePath, data, true, false, true);
/* 2444 */       File file = new File(filePath);
/* 2445 */       if (date > 0L) {
/* 2446 */         file.setLastModified(date);
/*      */       }
/* 2448 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InputStream downloadURL(String urlPath) throws Exception {
/*      */     try {
/* 2457 */       String filePath = getFilePath(urlPath);
/* 2458 */       String stamp = getFileTimeStamp(filePath);
/* 2459 */       URL uRL = new URL(urlPath);
/* 2460 */       URLConnection conn = uRL.openConnection();
/* 2461 */       if (conn instanceof HttpURLConnection)
/*      */       {
/* 2463 */         HttpURLConnection http = (HttpURLConnection)conn;
/* 2464 */         http.setRequestMethod("GET");
/* 2465 */         http.setUseCaches(true);
/* 2466 */         http.setAllowUserInteraction(false);
/* 2467 */         HttpURLConnection.setFollowRedirects(true);
/* 2468 */         http.setDoOutput(false);
/* 2469 */         http.setDoInput(true);
/* 2470 */         if (stamp != null) {
/*      */           
/* 2472 */           http.setRequestProperty("Last-Modified", stamp);
/* 2473 */           http.setRequestProperty("If-Modified-Since", stamp);
/*      */         } 
/* 2475 */         http.connect();
/* 2476 */         InputStream inStream = http.getInputStream();
/* 2477 */         int respCode = http.getResponseCode();
/* 2478 */         byte[] data = null;
/* 2479 */         if (respCode >= 200 && respCode < 300) {
/*      */           
/* 2481 */           data = readStream(inStream);
/* 2482 */           long date = http.getHeaderFieldDate("Last-Modified", 0L);
/* 2483 */           saveToCache(filePath, data, date);
/*      */         }
/* 2485 */         else if (respCode >= 300 && respCode < 400) {
/* 2486 */           data = JLbsClientFS.loadFile(filePath, true);
/*      */         } else {
/* 2488 */           data = new byte[0];
/* 2489 */         }  return new ByteArrayInputStream(data);
/*      */       }
/*      */     
/* 2492 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 2495 */     URL url = new URL(urlPath);
/* 2496 */     return url.openStream();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] readContents(InputStream is) {
/*      */     try {
/* 2503 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 2504 */       byte[] buffer = new byte[4096];
/*      */       int read;
/* 2506 */       while ((read = is.read(buffer, 0, buffer.length)) > 0)
/* 2507 */         out.write(buffer, 0, read); 
/* 2508 */       return out.toByteArray();
/*      */     }
/* 2510 */     catch (Exception exception) {
/*      */ 
/*      */       
/* 2513 */       return new byte[0];
/*      */     } 
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
/*      */ 
/*      */   
/*      */   private boolean loadSingleJars(String jarName, boolean signVerify, boolean silentLoad) throws FileNotFoundException, SignatureException {
/* 2529 */     boolean bSilentLoad = (silentLoad || JLbsConstants.SKIP_CACHE || JLbsConstants.PLATFORM_SILENT);
/*      */     
/* 2531 */     if (isJarInCache(jarName)) {
/* 2532 */       return true;
/*      */     }
/*      */     try {
/* 2535 */       if (isAlreadyCached(jarName, signVerify, jarName))
/* 2536 */         return true; 
/* 2537 */       String latestJarName = (String)getPublicObject("LoadSingleJarFromResources", jarName, true);
/* 2538 */       String fullURI = this.m_RootUri + "resources/Jars/" + latestJarName;
/* 2539 */       if (isAlreadyCached(latestJarName, signVerify, jarName))
/* 2540 */         return true; 
/* 2541 */       InputStream is = downloadURL(fullURI);
/* 2542 */       byte[] jarData = readContents(is);
/* 2543 */       if (addToJarCache(jarName, jarData, null, signVerify)) {
/* 2544 */         return true;
/*      */       }
/* 2546 */     } catch (Exception e) {
/*      */       
/* 2548 */       if (!bSilentLoad) {
/* 2549 */         throw new FileNotFoundException("The jar file at " + jarName + " could not be retrieved!");
/*      */       }
/* 2551 */       return false;
/*      */     } 
/* 2553 */     throw new SignatureException("The signature of the jar file at " + jarName + " is not valid!");
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isAlreadyCached(String jarName, boolean signVerify, String keyName) {
/* 2558 */     String filePath = getFilePath(jarName);
/*      */     
/*      */     try {
/* 2561 */       byte[] data = JLbsClientFS.loadFile(filePath, true);
/* 2562 */       if (data != null)
/*      */       {
/* 2564 */         if (addToJarCache(keyName, data, null, signVerify)) {
/* 2565 */           return true;
/*      */         }
/*      */         
/* 2568 */         File file = new File(filePath);
/* 2569 */         if (file.exists()) {
/*      */           
/*      */           try {
/*      */             
/* 2573 */             file.delete();
/*      */           }
/* 2575 */           catch (Exception exception) {}
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 2583 */     catch (Exception exception) {}
/*      */ 
/*      */     
/* 2586 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean loadResourceJAR(String jarName, String pathPrefix, boolean silentLoad) throws FileNotFoundException {
/*      */     byte[] jarData;
/* 2592 */     if (jarName == null || jarName.length() == 0 || JLbsJarCache.hasJarFile(jarName))
/* 2593 */       return false; 
/* 2594 */     ensureTransClientExist();
/*      */     
/* 2596 */     String resName = getLangDepResName(jarName);
/* 2597 */     if (pathPrefix != null)
/* 2598 */       pathPrefix = getLangDepResName(pathPrefix); 
/* 2599 */     String fullURI = this.m_RootUri + "resources/" + resName;
/*      */ 
/*      */     
/*      */     try {
/* 2603 */       if (JLbsJarCache.getJarHolder(resName) != null)
/* 2604 */         return true; 
/* 2605 */       jarData = getServletData(new String[] { "@/" + resName }, "ResDownload", null, false);
/* 2606 */       if (jarData == null) {
/* 2607 */         throw new Exception();
/*      */       }
/* 2609 */     } catch (FileNotFoundException fnfe) {
/*      */       
/* 2611 */       if (!silentLoad) {
/* 2612 */         throw new FileNotFoundException("The jar file at " + fullURI + " could not be retrieved!");
/*      */       }
/*      */       
/* 2615 */       JLbsJarStreamHolder holder = new JLbsJarStreamHolder();
/* 2616 */       holder.setPathPrefix(JLbsJarCache.getResourcePath(pathPrefix));
/* 2617 */       JLbsJarCache.add(resName, holder);
/* 2618 */       return false;
/*      */     
/*      */     }
/* 2621 */     catch (Exception e) {
/*      */       
/* 2623 */       if (!silentLoad) {
/* 2624 */         throw new FileNotFoundException("The jar file at " + fullURI + " could not be retrieved!");
/*      */       }
/* 2626 */       return false;
/*      */     } 
/* 2628 */     return addToJarCache(resName, jarData, pathPrefix, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IQueryFactory getQueryFactory() {
/* 2637 */     ensureTransClientExist();
/* 2638 */     return (this.m_RemoteInvoker != null) ? (IQueryFactory)new JLbsClientQueryFactory(this.m_RemoteInvoker, "SQ", this.m_ClassLoader) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] loadLocalFile(String relativePath) throws IOException {
/* 2646 */     return JLbsClientFS.loadFile(relativePath, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean saveLocalFile(String relativePath, byte[] data, boolean overwrite, boolean append) throws IOException {
/* 2653 */     if (data != null)
/* 2654 */       return JLbsClientFS.saveFile(relativePath, data, overwrite, append, false); 
/* 2655 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean saveLocalFile(String relativePath, byte[] data, int index, int length, boolean overwrite, boolean append) throws IOException {
/* 2662 */     if (data != null && data.length >= index + length && length >= 0) {
/*      */       
/* 2664 */       byte[] copy = new byte[length];
/* 2665 */       for (int i = 0; i < length; i++)
/* 2666 */         copy[i] = data[index + i]; 
/* 2667 */       return saveLocalFile(relativePath, copy, overwrite, append);
/*      */     } 
/* 2669 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean deleteLocalFile(String relativePath) {
/* 2675 */     return JLbsClientFS.deleteFile(relativePath);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSessionTimeout(int timeout) {
/* 2681 */     synchronized (this) {
/*      */       
/* 2683 */       this.m_Timeout = timeout;
/* 2684 */       if (this.m_Timer != null) {
/*      */         
/* 2686 */         this.m_Timer.cancel();
/* 2687 */         this.m_Timer = null;
/*      */       } 
/* 2689 */       if (timeout <= 0)
/*      */         return; 
/* 2691 */       this.m_Timer = new Timer(true);
/* 2692 */       this.m_Timer.schedule(new TimerActionListener(this), timeout);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean loadMultipleJars(String[] jarNames) {
/* 2698 */     if (jarNames == null || jarNames.length == 0) {
/* 2699 */       return false;
/*      */     }
/*      */     try {
/* 2702 */       ArrayList<String> fullNameList = new ArrayList();
/*      */       
/* 2704 */       for (int i = 0; i < jarNames.length; i++) {
/*      */         
/* 2706 */         if (!isJarInCache(jarNames[i])) {
/* 2707 */           fullNameList.add("Jars/" + jarNames[i]);
/*      */         }
/*      */       } 
/* 2710 */       if (fullNameList.size() == 0) {
/* 2711 */         return true;
/*      */       }
/* 2713 */       loadMultipleResource(fullNameList.<String>toArray(new String[fullNameList.size()]), false);
/* 2714 */       return true;
/*      */     }
/* 2716 */     catch (Exception exception) {
/*      */ 
/*      */       
/* 2719 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void clearCaches() {
/* 2724 */     JLbsObjectSchemaCache.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean checkLastUserAction() {
/* 2729 */     if (this.m_LastUserAction != null) {
/*      */       
/* 2731 */       long timeDiff = (Calendar.getInstance().getTimeInMillis() - this.m_LastUserAction.getTimeInMillis()) / 100L;
/* 2732 */       return (timeDiff <= 300000L);
/*      */     } 
/* 2734 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void remoteSessionInvoked() {
/* 2740 */     synchronized (this) {
/*      */       
/* 2742 */       if (this.m_Timer != null) {
/*      */         
/* 2744 */         this.m_Timer.cancel();
/* 2745 */         this.m_Timer = new Timer(true);
/* 2746 */         this.m_Timer.schedule(new TimerActionListener(this), this.m_Timeout);
/*      */       } 
/* 2748 */       resetTimeoutTimer();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @InternalUse
/*      */   public boolean runReport(String repName, JLbsRunContextParameters ctxParams) throws FileNotFoundException, InvocationTargetException {
/* 2757 */     return runReport(repName, ctxParams, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @InternalUse
/*      */   public boolean runReport(String repName, Object ctxParamsObj, Object preRunParams) throws FileNotFoundException, InvocationTargetException {
/* 2765 */     if (!isLoggedIn() || this.m_ReportExecutor == null)
/* 2766 */       return false; 
/* 2767 */     JLbsRunContextParameters ctxParams = null;
/* 2768 */     if (ctxParamsObj instanceof JLbsRunContextParameters)
/* 2769 */       ctxParams = (JLbsRunContextParameters)ctxParamsObj; 
/* 2770 */     Thread.currentThread().setContextClassLoader(this.m_ClassLoader);
/*      */     
/* 2772 */     if (JLbsComponentHelper.getTestPlayerWrapper() != null) {
/* 2773 */       JLbsComponentHelper.getTestPlayerWrapper().setCurrentReportContextParameters(ctxParams);
/*      */     }
/*      */ 
/*      */     
/* 2777 */     String reportLanguage = (ctxParams != null && ctxParams.Language != null) ? ctxParams.Language : getLanguagePrefix();
/*      */     
/* 2779 */     boolean autoGenLay = (ctxParams != null) ? ctxParams.AutoGeneratedLayaout : false;
/*      */ 
/*      */ 
/*      */     
/* 2783 */     String customReportName = null;
/* 2784 */     IReportPreRunParams result = null;
/*      */     
/* 2786 */     if (ctxParams == null) {
/* 2787 */       ctxParams = new JLbsRunContextParameters();
/*      */     }
/* 2789 */     if (!autoGenLay) {
/*      */       
/* 2791 */       if (preRunParams == null) {
/*      */ 
/*      */         
/*      */         try {
/* 2795 */           int custFiltType = ctxParams.CustFilterType;
/* 2796 */           RemoteMethodResponse response = callRemoteMethod("RS", "getRunParameters", new Object[] { null, repName, reportLanguage, 
/* 2797 */                 Integer.valueOf(custFiltType) });
/* 2798 */           result = (IReportPreRunParams)response.Result;
/*      */         }
/* 2800 */         catch (Exception e) {
/*      */           
/* 2802 */           getLogger().error("runReport.getRunParameters", e);
/*      */         } 
/*      */       } else {
/*      */         
/* 2806 */         result = (IReportPreRunParams)preRunParams;
/* 2807 */       }  if (result == null)
/* 2808 */         throw new FileNotFoundException(repName); 
/* 2809 */       customReportName = result.getCustomReportName();
/*      */     } 
/*      */     
/* 2812 */     MultiBufferTransportClient newTransClient = createMultiBufferTransportClient();
/*      */     
/* 2814 */     ctxParams.ClientContext = (IApplicationContext)this;
/*      */     
/* 2816 */     JLbsReportExecParams execParams = new JLbsReportExecParams();
/* 2817 */     execParams.setClient((TransportClient)newTransClient);
/* 2818 */     execParams.setCtxParams(ctxParams);
/* 2819 */     if (customReportName != null) {
/* 2820 */       execParams.setRepName(customReportName);
/*      */     } else {
/* 2822 */       execParams.setRepName(repName);
/* 2823 */     }  execParams.setReportingURI(getReportingURI());
/* 2824 */     execParams.setReportParams(result);
/* 2825 */     execParams.setLanguage(reportLanguage);
/*      */     
/* 2827 */     updateReportCultureInfo();
/* 2828 */     updateReportCurrencyInfo();
/* 2829 */     return this.m_ReportExecutor.executeReport(null, execParams);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MultiBufferTransportClient createMultiBufferTransportClient() {
/* 2836 */     MultiBufferTransportClient newTransClient = new MultiBufferTransportClient((IChannelProvider)this.m_ChannelProv, this.m_Session, JLbsSymCryptoFactory.createCryptor(this.m_Session.getLocalCryptoParam()), JLbsSymCryptoFactory.createCryptor(this.m_Session.getRemoteCryptoParam()));
/* 2837 */     newTransClient.setRecorder(this.m_TransClient.getRecorder());
/* 2838 */     return newTransClient;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getReportingURI() {
/* 2846 */     String reportingURI = JLbsReportingParams.getReportingURI();
/* 2847 */     if (JLbsStringUtil.isEmpty(reportingURI)) {
/* 2848 */       return this.m_RootUri + "Reporting";
/*      */     }
/* 2850 */     return reportingURI + "Reporting";
/*      */   }
/*      */ 
/*      */   
/*      */   private String getBatchURI() {
/* 2855 */     String batchURI = JLbsConstants.getBatchURI();
/* 2856 */     if (JLbsStringUtil.isEmpty(batchURI)) {
/* 2857 */       return this.m_RootUri + "RunBatch";
/*      */     }
/* 2859 */     return batchURI + "RunBatch";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IReportExecutor getReportExecutor() {
/* 2865 */     return this.m_ReportExecutor;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReportExecutor(IReportExecutor executor) {
/* 2871 */     this.m_ReportExecutor = executor;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean openHelpURL(String url, String targetWindow) {
/* 2877 */     if (!JLbsStringUtil.isEmpty(url) && !url.endsWith(".htm"))
/* 2878 */       url = url + ".htm"; 
/* 2879 */     String language = getLanguagePrefix();
/* 2880 */     if (!JLbsStringUtil.isEmpty(language))
/* 2881 */       url = language + "/" + url; 
/* 2882 */     if (JLbsStringUtil.isEmpty(targetWindow))
/* 2883 */       targetWindow = "_help"; 
/* 2884 */     if (!JLbsConstants.USE_LOCAL_HELP_FILES) {
/*      */       
/* 2886 */       url = JLbsConstants.VERSION_STR + "/" + url;
/* 2887 */       url = JLbsFileUtil.appendPath(JLbsConstants.HELP_SERVER_URL, url, '/');
/* 2888 */       return openURLInternal(url, null, targetWindow);
/*      */     } 
/* 2890 */     return openURLInternal(url, "Help/", targetWindow);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean openURL(String url, String targetWindow) {
/* 2896 */     if (JLbsStringUtil.isEmpty(targetWindow))
/* 2897 */       targetWindow = "_reg"; 
/* 2898 */     return openURLInternal(url, null, targetWindow);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean addToJarCache(String jarName, byte[] jarData, String path, boolean verify) {
/*      */     try {
/* 2905 */       JLbsJarStreamHolder holder = new JLbsJarStreamHolder();
/* 2906 */       holder.load(jarData, verify);
/* 2907 */       if (path != null)
/* 2908 */         holder.setPathPrefix(JLbsJarCache.getResourcePath(path)); 
/* 2909 */       JLbsJarCache.add(jarName, holder);
/* 2910 */       return true;
/*      */     }
/* 2912 */     catch (Exception e) {
/*      */       
/* 2914 */       e.printStackTrace();
/*      */       
/* 2916 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected String getLanguagePrefix() {
/* 2921 */     Object langObj = getVariable("CLI-LANG");
/* 2922 */     return (langObj == null) ? null : langObj
/*      */       
/* 2924 */       .toString();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateReportCultureInfo() {
/* 2929 */     Object culture = getVariable("CLI-CULTUREINFO");
/* 2930 */     if (culture instanceof ILbsCultureInfo) {
/* 2931 */       JLbsReportingParams.setCulture((ILbsCultureInfo)culture);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateReportCurrencyInfo() {
/* 2938 */     Object cBase = getVariable("CLI-CURRENCIES");
/* 2939 */     if (cBase instanceof JLbsCurrenciesBase) {
/* 2940 */       JLbsReportingParams.setCurrencyBase((JLbsCurrenciesBase)cBase);
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean openURLInternal(String url, String prefix, String targetWindow) {
/* 2945 */     if (!JLbsStringUtil.isEmpty(url))
/*      */     {
/* 2947 */       if (JLbsConstants.USE_WEB_BROWSER) {
/*      */ 
/*      */         
/*      */         try {
/* 2951 */           if (!url.startsWith("http")) {
/*      */             
/* 2953 */             if (prefix != null && prefix.length() > 0)
/* 2954 */               url = JLbsFileUtil.appendPath(prefix, url, '/'); 
/* 2955 */             url = JLbsFileUtil.appendPath(this.m_RootUri, url, '/');
/*      */           } 
/* 2957 */           boolean ok = false;
/* 2958 */           if (this.m_Applet != null && this.m_Applet.getBaseApplet() != null) {
/*      */             
/*      */             try {
/*      */               
/* 2962 */               JSObject window = JSObject.getWindow(this.m_Applet.getBaseApplet());
/* 2963 */               JSObject document = (JSObject)window.getMember("document");
/* 2964 */               if (document != null)
/*      */               {
/* 2966 */                 document.eval("window.open(\"" + url + "\",\"_blank\",\"location=0\");");
/* 2967 */                 ok = true;
/*      */               }
/*      */             
/* 2970 */             } catch (Exception e) {
/*      */               
/* 2972 */               ok = false;
/*      */             } 
/*      */           }
/* 2975 */           if (!ok) {
/* 2976 */             JLbsURLUtil.displayURL(url);
/*      */           }
/* 2978 */         } catch (IOException e) {
/*      */           
/* 2980 */           getLogger().error(e.getMessage(), e);
/*      */         } 
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 2987 */           loadClass("com.lbs.help.JLbsWebBrowser");
/*      */         }
/* 2989 */         catch (Exception e) {
/*      */           
/* 2991 */           getLogger().error(e.getMessage(), e);
/*      */         } 
/* 2993 */         if (!url.startsWith("http")) {
/*      */           
/* 2995 */           if (prefix != null && prefix.length() > 0)
/* 2996 */             url = JLbsFileUtil.appendPath(prefix, url, '/'); 
/* 2997 */           url = JLbsFileUtil.appendPath(this.m_RootUri, url, '/');
/*      */         } 
/* 2999 */         getLogger().info(url + " @@ " + targetWindow);
/* 3000 */         JLbsBrowserFactory.openURL(url, targetWindow);
/* 3001 */         return true;
/*      */       } 
/*      */     }
/* 3004 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getServerTime() {
/* 3010 */     Calendar calendar = Calendar.getInstance();
/*      */ 
/*      */     
/*      */     try {
/* 3014 */       RemoteMethodResponse resp = callRemoteMethod("ST", "getServerTime", null);
/* 3015 */       JLbsPerformanceTime perfTime = (JLbsPerformanceTime)resp.Result;
/* 3016 */       Date date = new Date(perfTime.getTimeInMillis());
/* 3017 */       calendar.setTime(date);
/*      */     
/*      */     }
/* 3020 */     catch (Exception e) {
/*      */       
/* 3022 */       calendar = null;
/* 3023 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 3026 */     return calendar;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestBatchOperation(String batchName, Object[] parameters) throws Exception {
/* 3032 */     ensureTransClientExist();
/* 3033 */     if (this.m_TransClient != null && isLoggedIn()) {
/*      */       
/* 3035 */       RemoteMethodInvoker remoteInvoker = new RemoteMethodInvoker(this.m_TransClient, getBatchURI());
/*      */       
/* 3037 */       if (this.m_Applet != null)
/* 3038 */         remoteInvoker.setTimeoutHandler(this.m_Applet); 
/* 3039 */       remoteInvoker.setInvokeHandler(this);
/* 3040 */       remoteInvoker.setServerEventListener(this);
/* 3041 */       RemoteMethodResponse response = remoteInvoker.InvokeMethod(batchName, null, parameters);
/* 3042 */       if (response.ReturnParameters[0] instanceof Integer) {
/*      */         
/* 3044 */         int batchID = ((Integer)response.ReturnParameters[0]).intValue();
/* 3045 */         if (!JLbsConstants.EVENT_RECORDING && JLbsConstants.TEST_PLAYING == true)
/* 3046 */           JLbsConstants.m_CurrentBatchID = batchID; 
/* 3047 */         recordWaitForBatchOperation(batchName);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void recordWaitForBatchOperation(String batchName) {
/* 3055 */     JLbsDataPoolItem dataPoolItem = new JLbsDataPoolItem(-1, 1, -1, null, "", "");
/* 3056 */     JLbsRecordItem item = new JLbsRecordItem(dataPoolItem, "BATCH_OPERATION", batchName);
/* 3057 */     ILbsEventRecorder recorder = JLbsComponentHelper.getEventRecorder();
/* 3058 */     if (recorder != null) {
/* 3059 */       recorder.addRecordItem(item, null);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void resetTimeoutTimer() {
/* 3065 */     this.m_LastUserAction = Calendar.getInstance();
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
/*      */   public String getCookie(String name) {
/* 3077 */     if (this.m_Applet.getBaseApplet() == null)
/*      */     {
/*      */       
/* 3080 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3086 */       JSObject window = JSObject.getWindow(this.m_Applet.getBaseApplet());
/* 3087 */       JSObject document = (JSObject)window.getMember("document");
/*      */       
/* 3089 */       Object cookies = (document != null) ? document.getMember("cookie") : null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3095 */       if (cookies == null)
/* 3096 */         return null; 
/* 3097 */       String str = new String((String)cookies);
/* 3098 */       int index = 1;
/* 3099 */       while (index > 0) {
/*      */         
/* 3101 */         index = str.indexOf(';');
/* 3102 */         if (index < 0)
/* 3103 */           index = str.length(); 
/* 3104 */         String subStr = str.substring(0, index);
/* 3105 */         int subIndex = subStr.indexOf('=');
/* 3106 */         if (subIndex > 0) {
/*      */           
/* 3108 */           String key = subStr.substring(0, subIndex);
/* 3109 */           String value = subStr.substring(subIndex + 1);
/* 3110 */           if (name.equals(key.trim()))
/* 3111 */             return value.trim(); 
/*      */         } 
/* 3113 */         if (index < str.length()) {
/* 3114 */           str = str.substring(index + 1); continue;
/*      */         } 
/* 3116 */         str = "";
/*      */       } 
/* 3118 */       return null;
/*      */     }
/* 3120 */     catch (NoClassDefFoundError e) {
/*      */       
/* 3122 */       if (e.getMessage().indexOf("netscape") >= 0 && e.getMessage().indexOf("javascript") >= 0 && e
/* 3123 */         .getMessage().indexOf("JSObject") >= 0)
/*      */       {
/* 3125 */         getLogger().warn("LbsClientContext.getCookie(): Could not retrieve parent window, cookies not available");
/*      */       }
/* 3127 */       return null;
/*      */     }
/* 3129 */     catch (Throwable e) {
/*      */       
/* 3131 */       if (isJSException(e)) {
/*      */         
/* 3133 */         if (JLbsConstants.DEBUG)
/*      */         {
/* 3135 */           getLogger().warn("LbsClientContext.getCookie(): Could not retrieve parent window, cookies not available");
/*      */         }
/* 3137 */         return null;
/*      */       } 
/*      */       
/* 3140 */       getLogger().error(e.getMessage(), e);
/* 3141 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isJSException(Object ex) {
/* 3147 */     return (ex != null && ex.getClass().getName().endsWith("JSException"));
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
/*      */   public void setCookie(String name, String value) throws Exception {
/* 3159 */     if (this.m_Applet.getBaseApplet() == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3165 */     JSObject window = null;
/* 3166 */     JSObject document = null;
/*      */     
/*      */     try {
/* 3169 */       window = JSObject.getWindow(this.m_Applet.getBaseApplet());
/* 3170 */       document = (JSObject)window.getMember("document");
/* 3171 */       String excludedChars = " \t;,";
/* 3172 */       for (int i = 0; i < excludedChars.length(); i++) {
/*      */         
/* 3174 */         if (name.indexOf(excludedChars.charAt(i)) > 0)
/*      */         {
/* 3176 */           throw new Exception("Name is invalid, should not contain whitespace, comma or semi-colon");
/*      */         }
/* 3178 */         if (value.indexOf(excludedChars.charAt(i)) > 0)
/*      */         {
/* 3180 */           throw new Exception("Value is invalid, should not contain whitespace, comma or semi-colon");
/*      */         }
/*      */       } 
/* 3183 */       String domain = (String)getVariable("SSODOMAIN");
/* 3184 */       if (!StringUtil.isEmpty(domain))
/*      */       {
/* 3186 */         domain = "Domain=" + domain + ";";
/*      */       }
/* 3188 */       String cookieText = name + "=" + value + ";" + domain + "Path=/";
/* 3189 */       if (value == null || value.length() == 0) {
/*      */         
/* 3191 */         cookieText = name + "=" + value + ";" + domain + "Path=/";
/* 3192 */         cookieText = cookieText + ";Max=Age=0";
/* 3193 */         if (getLogger().isTraceEnabled()) {
/* 3194 */           getLogger().trace("Deleting cookie");
/*      */         }
/*      */       } 
/*      */       
/* 3198 */       if (getLogger().isTraceEnabled()) {
/* 3199 */         getLogger().trace("Setting document.cookie = \"" + cookieText + '"');
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3205 */       document.setMember("cookie", cookieText);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3211 */       Object cookies = document.getMember("cookie");
/* 3212 */       if (getLogger().isTraceEnabled()) {
/* 3213 */         getLogger().trace("Got document.cookie = \"" + cookies + '"');
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 3218 */     catch (Exception e) {
/*      */       
/* 3220 */       if (isJSException(e)) {
/*      */         
/* 3222 */         if (getLogger().isWarnEnabled()) {
/* 3223 */           getLogger().warn("LbsClientContext.setCookie(): Could not retrieve parent window, cookies not available");
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/* 3228 */       if (getLogger().isErrorEnabled()) {
/* 3229 */         getLogger().error(e.getMessage(), e);
/*      */       }
/*      */       return;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRootUri() {
/* 3241 */     return this.m_RootUri;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientUri() {
/* 3247 */     if (this.m_Applet != null) {
/*      */       
/* 3249 */       String uri = this.m_Applet.getDocumentURI();
/* 3250 */       if (uri != null)
/* 3251 */         return uri; 
/* 3252 */       return "(Unknown URL)";
/*      */     } 
/* 3254 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ICachedHashTable getResourceCache() {
/* 3260 */     return this.m_ResourceCache;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Hashtable getConfigParameters() {
/* 3266 */     return this.m_ConfigParameters;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public TransportClient getTransportClient() {
/* 3272 */     return this.m_BaseClient;
/*      */   }
/*      */ 
/*      */   
/*      */   public ILbsServerEventListener getServerEventListener() {
/* 3277 */     return this.m_ServerEventListener;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setServerEventListener(ILbsServerEventListener serverEventListener) {
/* 3282 */     this.m_ServerEventListener = serverEventListener;
/* 3283 */     for (Map.Entry<LbsServerEventList, LbsServerEvent> entry : this.m_EventsToBeProcessed.entrySet()) {
/*      */       
/* 3285 */       JLbsSwingUtilities.invokeLater(this.m_ServerEventListener, new Runnable()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/* 3290 */               LbsClientContext.this.m_ServerEventListener.onServerEvent((LbsServerEventList)entry.getKey(), (LbsServerEvent)entry.getValue());
/* 3291 */               LbsClientContext.this.m_EventsToBeProcessed.remove(entry.getKey());
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onServerEvent(final LbsServerEventList eventList, final LbsServerEvent event) {
/* 3300 */     if (this.m_ServerEventListener != null) {
/*      */       
/* 3302 */       JLbsSwingUtilities.invokeLater(this.m_ServerEventListener, new Runnable()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/* 3307 */               LbsClientContext.this.m_ServerEventListener.onServerEvent(eventList, event);
/*      */             }
/*      */           });
/*      */     }
/*      */     else {
/*      */       
/* 3313 */       this.m_EventsToBeProcessed.put(eventList, event);
/* 3314 */       Timer clearPendingEvents = new Timer();
/* 3315 */       clearPendingEvents.schedule(new TimerTask()
/*      */           {
/*      */ 
/*      */             
/*      */             public void run()
/*      */             {
/* 3321 */               LbsClientContext.this.m_EventsToBeProcessed.clear();
/*      */             }
/*      */           },  900000L);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean saveLocalFile(String relativePath, byte[] data, boolean overwrite, boolean append, String charSet) throws IOException {
/* 3331 */     if (data != null)
/* 3332 */       return JLbsClientFS.saveFile(relativePath, data, overwrite, append, false, charSet); 
/* 3333 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IAuthorizationManager getAuthorizationManager() {
/* 3339 */     return (IAuthorizationManager)new LbsAuthorizationManager();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IIterationManager getIterationManager() {
/* 3345 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized IUserDetailInfo getUserDetailInfo() {
/* 3351 */     if (this.m_UserInfo instanceof IUserDetailInfo)
/* 3352 */       return (IUserDetailInfo)this.m_UserInfo; 
/* 3353 */     if (this.m_UserInfo != null && this.m_UserInfo.Parameters instanceof IUserDetailInfo)
/* 3354 */       return (IUserDetailInfo)this.m_UserInfo.Parameters; 
/* 3355 */     Object userVar = getVariable("UserLoginInfo");
/* 3356 */     if (userVar instanceof IUserDetailInfo) {
/* 3357 */       return (IUserDetailInfo)userVar;
/*      */     }
/* 3359 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAllVariables() {
/* 3365 */     synchronized (this.m_HashTable) {
/*      */       
/* 3367 */       this.m_HashTable = new NameValueMap();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeVariable(Object key) {
/* 3374 */     synchronized (this.m_HashTable) {
/*      */       
/* 3376 */       if (key != null)
/*      */       {
/* 3378 */         this.m_HashTable.removeValue(key.toString());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void initializeVersion() {
/* 3385 */     if (this.m_ApplicationVersion != null) {
/*      */       
/* 3387 */       String version = this.m_ApplicationVersion.getVersion();
/* 3388 */       if (!JLbsStringUtil.isEmpty(version))
/* 3389 */         JLbsConstants.VERSION_STR = version; 
/*      */     } 
/* 3391 */     JLbsConstants.VERSION_MAJOR = JLbsContextLocator.getUnityVersionMajor();
/* 3392 */     JLbsConstants.VERSION_MINOR = JLbsContextLocator.getUnityVersionMinor();
/* 3393 */     JLbsConstants.VERSION_RELEASE = JLbsContextLocator.getUnityVersionRelease();
/* 3394 */     JLbsConstants.VERSION_BUGFIX = JLbsContextLocator.getUnityVersionBugFix();
/* 3395 */     JLbsConstants.VERSION_EDEFTER = JLbsContextLocator.getUnityVersionEDefter();
/* 3396 */     JLbsConstants.PRODUCT_VERSION_PROPERTIES = JLbsContextLocator.getProductVersionProperties();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeCacheConfiguration() {
/*      */     try {
/* 3405 */       if (this.m_ApplicationVersion != null) {
/*      */         
/* 3407 */         deleteOldVersionedCaches();
/*      */         
/* 3409 */         if (!JLbsStringUtil.isEmpty(this.m_ApplicationVersion.getVersion()))
/*      */         {
/* 3411 */           int type = 0;
/*      */           
/* 3413 */           if (this.m_Applet != null && !JLbsStringUtil.isEmpty(this.m_Applet.m_StartupForm))
/*      */           {
/* 3415 */             if (this.m_Applet.m_StartupForm.equalsIgnoreCase("com.lbs.admin.AdminLoginForm")) {
/* 3416 */               type = 1;
/* 3417 */             } else if (this.m_Applet.m_StartupForm.equalsIgnoreCase("com.lbs.unity.main.UnityLoginForm")) {
/* 3418 */               type = 2;
/*      */             } 
/*      */           }
/* 3421 */           JLbsClientCacheConfigurator configurator = JLbsClientCacheConfigurator.createInstance(this, this.m_ApplicationVersion
/* 3422 */               .getVersion(), type);
/* 3423 */           configurator.configure();
/*      */         }
/*      */       
/*      */       } 
/* 3427 */     } catch (Exception e) {
/*      */       
/* 3429 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void deleteOldVersionedCaches() {
/* 3439 */     String clientFSRootPath = JLbsClientFS.getRootPath();
/*      */     
/* 3441 */     if (!JLbsStringUtil.isEmpty(clientFSRootPath)) {
/*      */       
/*      */       try {
/*      */ 
/*      */         
/* 3446 */         String[] folderList = JLbsFileUtil.getFolderListUnderDirectory(clientFSRootPath, "Cache_", true);
/* 3447 */         for (int i = 0; i < folderList.length; i++) {
/*      */ 
/*      */           
/* 3450 */           String folderName = folderList[i].toLowerCase(Locale.UK);
/*      */           
/* 3452 */           String currentFolder = JLbsClientCacheConfigurator.getCacheStorageDir(this.m_ApplicationVersion.getVersion()).toLowerCase(Locale.UK);
/* 3453 */           if (!folderName.equals(currentFolder))
/*      */           {
/*      */ 
/*      */             
/* 3457 */             if (folderName.toLowerCase(Locale.UK)
/* 3458 */               .endsWith("_EH".toLowerCase(Locale.UK))) {
/* 3459 */               JLbsFileUtil.deleteDirectory(JLbsClientFS.getFullPath(folderList[i]));
/*      */             }
/*      */           }
/*      */         } 
/* 3463 */       } catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IMessageService getMessageService() {
/* 3473 */     if (this.m_MessageService == null) {
/* 3474 */       this.m_MessageService = (IMessageService)LbsApplicationConfig.getClientImplementation((IApplicationContext)this, "com.lbs.message.IMessageService", new Class[0], new Object[0], IMessageService.class);
/*      */     }
/* 3476 */     return this.m_MessageService;
/*      */   }
/*      */ 
/*      */   
/*      */   public void flushCacheContentToDisk(boolean logout) {
/* 3481 */     LbsCacheHandleListener cacheHandleListener = new LbsCacheHandleListener(this);
/* 3482 */     cacheHandleListener.flushCacheContentToDisk(logout);
/* 3483 */     if (getCacheManager() != null && getCacheManager().getEHCacheManager() instanceof CacheManager) {
/* 3484 */       ((CacheManager)getCacheManager().getEHCacheManager()).removalAll();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public ILbsConsole getLogger() {
/* 3490 */     if (this.m_Logger == null) {
/*      */       
/* 3492 */       String extension = "";
/* 3493 */       if (getUserInfo() != null && (getUserInfo()).Name != null)
/* 3494 */         extension = "." + (getUserInfo()).Name; 
/* 3495 */       this.m_Logger = LbsConsole.getLogger(getClass().getName() + extension);
/*      */     } 
/* 3497 */     return (ILbsConsole)this.m_Logger;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToCustomizationResourceList(String fileName) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCustomizationResourceList() {
/* 3508 */     if (this.m_BaseClient instanceof ITransportCache) {
/* 3509 */       ((ITransportCache)this.m_BaseClient).setCustomizationResourceList(getCustomizationFormList());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 3515 */     this.m_Timer.cancel();
/* 3516 */     this.m_Timer = null;
/* 3517 */     if (this.m_MessageService != null)
/* 3518 */       this.m_MessageService.prepareForClose(); 
/* 3519 */     this.m_MessageService = null;
/* 3520 */     super.finalize();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setServerTimeZone(TimeZone serverTimeZone) {
/* 3525 */     this.m_ServerTimeZone = serverTimeZone;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public TimeZone getServerTimeZone() {
/* 3531 */     return this.m_ServerTimeZone;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDefaultTimeZone(TimeZone defaultTimeZone) {
/* 3536 */     this.m_DefaultTimeZone = defaultTimeZone;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public TimeZone getDefaultTimeZone() {
/* 3542 */     return this.m_DefaultTimeZone;
/*      */   }
/*      */ 
/*      */   
/*      */   private static final class TimerActionListener
/*      */     extends TimerTask
/*      */   {
/*      */     private final WeakReference m_Parent;
/*      */     
/*      */     public TimerActionListener(LbsClientContext parent) {
/* 3552 */       this.m_Parent = new WeakReference<>(parent);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/* 3558 */       final LbsClientContext parent = this.m_Parent.get();
/* 3559 */       if (parent != null) {
/*      */         
/*      */         try {
/*      */           
/* 3563 */           if (parent.m_Applet != null)
/*      */           {
/* 3565 */             if (parent.isLoggedIn() && (parent.checkLastUserAction() || parent.m_Applet.doBeforeTimeout())) {
/*      */               
/*      */               try {
/* 3568 */                 parent.m_LastUserAction = null;
/* 3569 */                 SwingUtilities.invokeLater(new Runnable()
/*      */                     {
/*      */ 
/*      */                       
/*      */                       public void run()
/*      */                       {
/*      */                         try {
/* 3576 */                           parent.ensureTransClientExist();
/* 3577 */                           if (parent.m_RemoteInvoker != null)
/*      */                           {
/* 3579 */                             parent.m_RemoteInvoker.invoke("GO", "$", new Object[] { null }, null, null, false);
/*      */                           }
/* 3581 */                         } catch (Exception exx) {
/*      */                           
/* 3583 */                           parent.getLogger().error("GO$ InvokeLater", exx);
/*      */                         }
/*      */                       
/*      */                       }
/*      */                     });
/*      */               }
/* 3589 */               catch (Exception ex) {
/*      */                 
/* 3591 */                 parent.getLogger().error(ex);
/*      */               } 
/*      */             }
/*      */           }
/*      */         } finally {
/*      */ 
/*      */           
/*      */           try {
/*      */ 
/*      */             
/* 3601 */             synchronized (parent)
/*      */             {
/* 3603 */               parent.m_Timer = new Timer(true);
/* 3604 */               parent.m_Timer.schedule(this, parent.m_Timeout);
/*      */             }
/*      */           
/* 3607 */           } catch (Exception exception) {}
/*      */         } 
/*      */       }
/*      */     }
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
/*      */   public ICacheManager getCacheManager() {
/* 3622 */     if (this.m_cacheManager == null)
/*      */     {
/* 3624 */       this.m_cacheManager = (ICacheManager)LbsClientCacheManager.getInstance();
/*      */     }
/*      */     
/* 3627 */     return this.m_cacheManager;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean openNewWindow() {
/* 3633 */     if (JLbsConstants.USE_WEB_BROWSER) {
/*      */       
/* 3635 */       String str = "";
/*      */ 
/*      */       
/*      */       try {
/* 3639 */         str = JLbsFileUtil.appendPath(this.m_RootUri, "smart/run?lang=" + (getUserInfo()).selectedLanguage + "&pid=" + 
/* 3640 */             encryptSessionID(getSession().getSessionCode()), '/');
/*      */         
/* 3642 */         boolean ok = false;
/* 3643 */         if (this.m_Applet != null && this.m_Applet.getBaseApplet() != null) {
/*      */           
/*      */           try {
/*      */             
/* 3647 */             JSObject window = JSObject.getWindow(this.m_Applet.getBaseApplet());
/* 3648 */             JSObject document = (JSObject)window.getMember("document");
/* 3649 */             if (document != null)
/*      */             {
/* 3651 */               Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
/* 3652 */               int h = screen.height;
/* 3653 */               int w = screen.width;
/*      */               
/* 3655 */               String winprops = "\"width=\"" + w + "\", height=\"" + h + "\", left=\"" + ((screen.width - w) / 2) + "\", top=\"" + ((screen.height - h) / 2);
/*      */               
/* 3657 */               winprops = winprops + "\", location=0, menubar=0, toolbar=0, personalbar=0, status=0, scrollbars=0, resizable=1\"";
/* 3658 */               document.eval("window.open(\"" + str + "\",\"_blank\"," + winprops + ");");
/* 3659 */               ok = true;
/*      */             }
/*      */           
/* 3662 */           } catch (Exception e) {
/*      */             
/* 3664 */             ok = false;
/*      */           } 
/*      */         }
/* 3667 */         if (!ok) {
/* 3668 */           JLbsURLUtil.displayURL(str);
/*      */         }
/* 3670 */       } catch (IOException e) {
/*      */         
/* 3672 */         getLogger().error(e.getMessage(), e);
/* 3673 */         return false;
/*      */       } 
/* 3675 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3681 */       loadClass("com.lbs.help.JLbsWebBrowser");
/*      */     }
/* 3683 */     catch (Exception e) {
/*      */       
/* 3685 */       getLogger().error(e.getMessage(), e);
/*      */     } 
/* 3687 */     String url = JLbsFileUtil.appendPath(this.m_RootUri, "smart/login/dialog", '/');
/* 3688 */     getLogger().info(url + " @@ _reg");
/* 3689 */     JLbsBrowserFactory.openURL(url, "_reg");
/* 3690 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String encryptSessionID(String sessionID) {
/* 3696 */     String encrypted = "";
/* 3697 */     if (!JLbsStringUtil.isEmpty(sessionID)) {
/*      */       
/*      */       try {
/*      */         
/* 3701 */         String temp1SessionID = generateRandomString() + sessionID + generateDateString() + generateRandomString();
/* 3702 */         byte[] data = TransportUtil.serializeObject(temp1SessionID);
/* 3703 */         data = (new AESCryptor(key)).encrypt(data, 0, data.length);
/* 3704 */         encrypted = Base64.encodeBytes(data, 8);
/*      */       }
/* 3706 */       catch (Exception e) {
/*      */         
/* 3708 */         LbsConsole.getLogger(LbsClientContext.class).error(e, e);
/*      */       } 
/*      */     }
/* 3711 */     return encrypted;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String generateDateString() {
/* 3716 */     return Calendar.getInstance().getTimeInMillis() + "";
/*      */   }
/*      */ 
/*      */   
/*      */   private static String generateRandomString() {
/* 3721 */     return UUID.randomUUID().toString().substring(0, 3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object deserializeObjectPlain(byte[] data) {
/*      */     try {
/* 3729 */       return TransportUtil.deserializeObjectPlain(this.m_ClassLoader, data);
/*      */     }
/* 3731 */     catch (Exception e) {
/*      */ 
/*      */ 
/*      */       
/* 3735 */       getLogger().error("deserializeObject", e);
/*      */       
/* 3737 */       return null;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\LbsClientContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */