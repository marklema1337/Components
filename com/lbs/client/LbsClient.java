/*      */ package com.lbs.client;
/*      */ 
/*      */ import com.lbs.console.ILbsAppender;
/*      */ import com.lbs.console.LbsAppenderFactory;
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.console.LbsLayout;
/*      */ import com.lbs.console.LbsLayoutFactory;
/*      */ import com.lbs.control.interfaces.ILbsOptionPane;
/*      */ import com.lbs.controls.JLbsSwingUtilities;
/*      */ import com.lbs.globalization.JLbsCultureInfoBase;
/*      */ import com.lbs.http.cookie.JLbsCookieHive;
/*      */ import com.lbs.interfaces.IVariableHolder;
/*      */ import com.lbs.invoke.SessionReestablishedException;
/*      */ import com.lbs.remoteclient.IClientContext;
/*      */ import com.lbs.remoteclient.IClientUIInit;
/*      */ import com.lbs.remoteclient.JLbsClientLoginPanel;
/*      */ import com.lbs.remoteclient.JLbsDesktopClientLoginPanel;
/*      */ import com.lbs.start.ILbsParamsTarget;
/*      */ import com.lbs.start.JLbsStartup;
/*      */ import com.lbs.transport.IRemoteSessionTimeoutHandler;
/*      */ import com.lbs.transport.UserInfo;
/*      */ import com.lbs.util.JLbsClientFS;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsFileUtil;
/*      */ import com.lbs.util.JLbsFrame;
/*      */ import com.lbs.util.JLbsNameValueMap;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import com.lbs.util.LbsClassInstanceProvider;
/*      */ import com.lbs.util.LbsRequestUtil;
/*      */ import com.lbs.util.StringUtil;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.event.WindowListener;
/*      */ import java.io.File;
/*      */ import java.net.URL;
/*      */ import java.util.Calendar;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Locale;
/*      */ import java.util.Timer;
/*      */ import java.util.TimerTask;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.activation.CommandMap;
/*      */ import javax.activation.MailcapCommandMap;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.JApplet;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.SwingUtilities;
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
/*      */ public class LbsClient
/*      */   extends JApplet
/*      */   implements IRemoteSessionTimeoutHandler, WindowListener, ILbsParamsTarget
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   protected String m_StartupForm;
/*      */   protected String m_StartupJAR;
/*      */   protected String m_TerminateURI;
/*      */   protected String m_MainJar;
/*      */   protected String m_RootURI;
/*      */   protected String m_DocumentURI;
/*      */   protected UserInfo m_UserInfo;
/*      */   protected boolean m_UseCache;
/*      */   protected boolean m_UseObjectSchemaCache;
/*      */   protected LbsClientContext context;
/*      */   private final boolean verifyJAR = false;
/*      */   private JLbsNameValueMap m_Parameters;
/*      */   private boolean m_SsoKeyExists = false;
/*   90 */   private String access_token = null; private static final int ms_ResID = -20000; private static final int ResID = -90002;
/*      */   protected static JLbsFrame frame;
/*      */   private JApplet m_AppletBase;
/*      */   private IClientUIInit windowClosingListener;
/*      */   
/*      */   public static ILbsCrashHandleListener getCrashListener() {
/*   96 */     return (LbsClientFieldHolder.getInstance()).ms_CrashListener;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setCrashListener(ILbsCrashHandleListener ms_CrashListener) {
/*  101 */     (LbsClientFieldHolder.getInstance()).ms_CrashListener = ms_CrashListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  108 */     initializeClientLogger();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void initializeClientLogger() {
/*  113 */     LbsLayout layout = LbsLayoutFactory.createLayout(LbsLayoutFactory.LogFormat.PLAIN(true));
/*  114 */     ILbsAppender appender = LbsAppenderFactory.createConsoleAppender("System.out", layout, false);
/*  115 */     LbsConsole.getRootLogger().addAppender(appender);
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
/*      */   public void init() {
/*  130 */     setMailCapCommandMap();
/*  131 */     initApplet();
/*  132 */     if (this.m_StartupForm != null) {
/*      */       
/*  134 */       Thread thread = new Thread(new LoginFormDownloadTask());
/*  135 */       thread.setContextClassLoader(Thread.currentThread().getContextClassLoader());
/*  136 */       thread.run();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroy() {
/*  143 */     System.out.println("Destroying applet..");
/*  144 */     if (this.context != null) {
/*  145 */       this.context.logout();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void validate() {
/*  151 */     super.validate();
/*  152 */     if (this.context != null) {
/*  153 */       this.context.realignContent();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getUsesCache() {
/*  158 */     return this.m_UseCache;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getUsesObjectSchemaCache() {
/*  163 */     return this.m_UseObjectSchemaCache;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void add(JComponent comp, int x, int y, int width, int height) {
/*  168 */     getContentPane().add(comp);
/*  169 */     comp.setBounds(x, y, width, height);
/*      */   }
/*      */ 
/*      */   
/*      */   private JLabel createLabel(String caption) {
/*  174 */     JLabel label = new JLabel();
/*  175 */     label.setText(caption);
/*  176 */     return label;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParameters(String[] params) {
/*  182 */     if (this.m_Parameters == null)
/*  183 */       this.m_Parameters = new JLbsNameValueMap(); 
/*  184 */     if (params == null || params.length == 0)
/*      */       return; 
/*  186 */     Pattern pattern = Pattern.compile("=");
/*  187 */     boolean erp = false;
/*  188 */     for (int i = 0; i < params.length; i++) {
/*      */       
/*  190 */       String param = params[i];
/*  191 */       if (param != null && param.length() != 0) {
/*      */         
/*  193 */         String[] tokens = pattern.split(param, 2);
/*  194 */         if (tokens.length == 2) {
/*      */           
/*  196 */           String value = JLbsStringUtil.stripQuotes(tokens[1]);
/*  197 */           this.m_Parameters.setValue(tokens[0], value);
/*  198 */           if ("ROOTURI".equalsIgnoreCase(tokens[0]))
/*      */           {
/*  200 */             if (value.contains(".logo.cloud"))
/*  201 */               erp = true; 
/*      */           }
/*  203 */           if ("INI_TITLE".equalsIgnoreCase(tokens[0]))
/*      */           {
/*  205 */             if ("j-platform india".equalsIgnoreCase(value)) {
/*  206 */               JLbsConstants.ms_ProductType = "0390";
/*  207 */             } else if ("j-eetah".equalsIgnoreCase(value)) {
/*  208 */               JLbsConstants.ms_ProductType = "0370";
/*  209 */             } else if ("saaserp".equalsIgnoreCase(value)) {
/*  210 */               JLbsConstants.ms_ProductType = "0601";
/*      */             
/*      */             }
/*  213 */             else if (erp) {
/*  214 */               JLbsConstants.ms_ProductType = "0601";
/*      */             } else {
/*  216 */               JLbsConstants.ms_ProductType = "0350";
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setMainJar(String mainjar) {
/*  225 */     if (mainjar != null)
/*  226 */       mainjar = JLbsFileUtil.getFileName(mainjar); 
/*  227 */     this.m_MainJar = mainjar;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDocumentURI(String uri) {
/*  232 */     if (uri != null) {
/*  233 */       this.m_DocumentURI = uri;
/*      */     }
/*      */   }
/*      */   
/*      */   public String getDocumentURI() {
/*  238 */     if (this.m_DocumentURI != null && this.m_DocumentURI.length() > 0) {
/*  239 */       return this.m_DocumentURI;
/*      */     }
/*      */     try {
/*  242 */       URL url = getDocumentBase();
/*  243 */       if (url != null) {
/*  244 */         return url.toExternalForm();
/*      */       }
/*  246 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  251 */       URL url = getBaseApplet().getDocumentBase();
/*  252 */       if (url != null) {
/*  253 */         return url.toExternalForm();
/*      */       }
/*  255 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  258 */     return null;
/*      */   }
/*      */   
/*      */   public LbsClient() {
/*  262 */     this.m_AppletBase = null;
/*      */     this.m_StartupForm = "com.lbs.reporting.test.ReportLoginForm";
/*      */     this.m_UseCache = true;
/*      */     this.m_UseObjectSchemaCache = true; } public void setBaseApplet(JApplet parent) {
/*  266 */     this.m_AppletBase = parent;
/*      */   }
/*      */ 
/*      */   
/*      */   public JApplet getBaseApplet() {
/*  271 */     return this.m_AppletBase;
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
/*      */   class LoginFormDownloadTask
/*      */     implements Runnable
/*      */   {
/*      */     public void run() {
/*      */       try {
/*  287 */         if (LbsClient.this.m_StartupJAR != null && !LbsClient.this.m_StartupJAR.equals(LbsClient.this.m_MainJar)) {
/*  288 */           LbsClient.this.context.loadJAR(LbsClient.this.m_StartupJAR, false, true);
/*      */         }
/*      */         
/*  291 */         if (LbsClient.this.m_StartupForm.equals("com.lbs.unity.main.UnityLoginForm")) {
/*      */           Object result;
/*      */           
/*  294 */           if ((result = LbsClient.this.context.getPublicObject("IsDesktop", null, true)) != null && (
/*  295 */             (Boolean)result).booleanValue())
/*      */           {
/*  297 */             LbsClient.this.m_StartupForm = "com.lbs.unity.main.DesktopLoginForm";
/*      */           }
/*      */         } 
/*      */         
/*  301 */         Object target = LbsClient.this.context.createInstance(LbsClient.this.m_StartupForm);
/*      */         
/*  303 */         SwingUtilities.invokeLater(new LbsClient.LoginFormInitTask(target, LbsClient.this.m_UserInfo));
/*      */       }
/*  305 */       catch (Exception e) {
/*      */         
/*  307 */         System.out.println("FormDownloadTask Exception: " + e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected IClientContext getContext() {
/*  314 */     return this.context;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String divineDataDirectory() {
/*  319 */     String path = JLbsFileUtil.getClientRootDirectory();
/*      */     
/*  321 */     path = (new File(".")).getAbsolutePath();
/*  322 */     System.out.println("Client File System Path: " + path);
/*  323 */     return path;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getParameter(String param) {
/*  329 */     String result = null;
/*      */     
/*      */     try {
/*  332 */       result = super.getParameter(param);
/*      */     }
/*  334 */     catch (Exception exception) {}
/*      */ 
/*      */     
/*  337 */     if (result == null && this.m_Parameters != null)
/*  338 */       result = (String)this.m_Parameters.getValue(param); 
/*  339 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initApplet() {
/*  344 */     initApplet(false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initApplet(boolean exit) {
/*  349 */     Toolkit.getDefaultToolkit().setDynamicLayout(false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  354 */     getContentPane().removeAll();
/*  355 */     JPanel contentPanel = new JPanel();
/*  356 */     contentPanel.setLayout((LayoutManager)null);
/*  357 */     setContentPane(contentPanel);
/*  358 */     add(createLabel("Initializing the client and retrieving the login form..."), 100, 100, 400, 40);
/*  359 */     if (this.m_RootURI == null)
/*  360 */       this.m_RootURI = getParameter("ROOTURI"); 
/*  361 */     String message = (this.m_RootURI == null) ? "No root URI is provided!" : ("Root URI : " + this.m_RootURI);
/*      */ 
/*      */     
/*  364 */     add(createLabel(message), 100, 140, 400, 40);
/*      */ 
/*      */     
/*  367 */     System.out.println("Looking up for SSO_KEY parameter");
/*  368 */     String ssoKeySerialized = getParameter("SSO_KEY");
/*  369 */     if (ssoKeySerialized != null && !ssoKeySerialized.isEmpty()) {
/*      */       
/*      */       try {
/*      */         
/*  373 */         System.out.println("Deserializing SSO_KEY parameter");
/*  374 */         this.m_UserInfo = (UserInfo)StringUtil.getSerializedObject(ssoKeySerialized.replaceAll("###n###", "\n"));
/*  375 */         this.m_UserInfo.variableHolder = (IVariableHolder)this.context;
/*  376 */         this.m_SsoKeyExists = true;
/*  377 */         System.out.println("We have SSO user named : " + this.m_UserInfo.Name);
/*      */       }
/*  379 */       catch (Exception e) {
/*      */         
/*  381 */         e.printStackTrace();
/*  382 */         this.m_UserInfo = null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  390 */     JLbsClientFS.setRootPath(divineDataDirectory());
/*      */ 
/*      */     
/*  393 */     if (exit) {
/*      */       
/*  395 */       initLAFTheme();
/*  396 */       createContext(exit);
/*  397 */       this.m_UserInfo = lookForLDAP();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  402 */       createContext(exit);
/*  403 */       initLAFTheme();
/*      */     } 
/*      */     
/*  406 */     setTimeout(900000);
/*      */     
/*  408 */     this.access_token = getParameter("ACCESS_TOKEN");
/*  409 */     if (!JLbsStringUtil.isEmpty(this.access_token) && !isValidAccessToken(this.access_token)) {
/*      */       
/*  411 */       String[] cookies = JLbsCookieHive.getCookies(this.m_RootURI);
/*  412 */       if (cookies != null) {
/*      */         
/*  414 */         getAccessTokenFromCookie(cookies);
/*      */       }
/*      */       else {
/*      */         
/*  418 */         cookies = JLbsCookieHive.getCookies(JLbsConstants.HR_SELF_SERVICE_URL);
/*  419 */         if (cookies != null)
/*      */         {
/*  421 */           getAccessTokenFromCookie(cookies);
/*      */         }
/*      */         else
/*      */         {
/*  425 */           cookies = JLbsCookieHive.getCookies(JLbsConstants.RECRUIT_PORTAL_URL);
/*  426 */           if (cookies != null)
/*      */           {
/*  428 */             getAccessTokenFromCookie(cookies);
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*  433 */     } else if (JLbsStringUtil.isEmpty(this.access_token)) {
/*      */       
/*  435 */       String[] cookies = JLbsCookieHive.getCookies(this.m_RootURI);
/*  436 */       if (cookies != null) {
/*      */         
/*  438 */         getAccessTokenFromCookie(cookies);
/*      */       }
/*      */       else {
/*      */         
/*  442 */         cookies = JLbsCookieHive.getCookies(JLbsConstants.HR_SELF_SERVICE_URL);
/*  443 */         if (cookies != null)
/*      */         {
/*  445 */           getAccessTokenFromCookie(cookies);
/*      */         }
/*      */         else
/*      */         {
/*  449 */           cookies = JLbsCookieHive.getCookies(JLbsConstants.RECRUIT_PORTAL_URL);
/*  450 */           if (cookies != null)
/*      */           {
/*  452 */             getAccessTokenFromCookie(cookies);
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*  457 */     } else if (isValidAccessToken(this.access_token)) {
/*      */       
/*  459 */       JLbsCookieHive.addCookie("oauth_token=" + this.access_token, this.m_RootURI, true);
/*      */     } 
/*      */ 
/*      */     
/*  463 */     contentPanel.addComponentListener(new AppletContentAdapter(this.context));
/*      */   }
/*      */ 
/*      */   
/*      */   private void getAccessTokenFromCookie(String[] cookies) {
/*  468 */     for (int i = 0; i < cookies.length; i++) {
/*      */       
/*  470 */       String[] parts = cookies[i].split("=");
/*  471 */       if (parts[0].equals("oauth_token")) {
/*      */         
/*  473 */         if (!JLbsStringUtil.isEmpty(this.access_token) && isValidAccessToken(this.access_token)) {
/*  474 */           this.access_token = parts[1];
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected UserInfo lookForLDAP() {
/*  482 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String[] getOSUserData() {
/*  487 */     String forAdmin = getClass().getSimpleName().equals("AdminClient") ? "true" : "false";
/*      */ 
/*      */     
/*  490 */     String osname = System.getProperty("os.name").toUpperCase(Locale.ENGLISH).replaceAll(" ", "");
/*  491 */     if (osname.indexOf("WINDOWS") > -1) {
/*      */       
/*      */       try {
/*      */         
/*  495 */         Class<?> clazz = Class.forName("com.sun.security.auth.module.NTSystem");
/*  496 */         Object nts = clazz.newInstance();
/*  497 */         return new String[] { forAdmin, (String)clazz.getDeclaredMethod("getName", new Class[0]).invoke(nts, new Object[0]), (String)clazz
/*  498 */             .getDeclaredMethod("getDomain", new Class[0]).invoke(nts, new Object[0]) };
/*      */       
/*      */       }
/*  501 */       catch (Exception e) {
/*      */         
/*  503 */         System.err.println(e);
/*  504 */         return null;
/*      */       } 
/*      */     }
/*  507 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void createContext(boolean exit) {
/*  512 */     this.context = new LbsClientContext(this, this.m_RootURI, exit);
/*      */ 
/*      */     
/*  515 */     String lang = getParameter("LANGUAGE");
/*  516 */     if (lang != null && !lang.equals("null")) {
/*      */       
/*  518 */       this.context.setVariable("LANGUAGE", lang);
/*  519 */       if (this.m_Parameters != null) {
/*  520 */         this.m_Parameters.removeValue("LANGUAGE");
/*      */       }
/*      */     } 
/*  523 */     String pid = getParameter("PSESID");
/*  524 */     if (pid != null && !pid.equals("null")) {
/*      */       
/*  526 */       this.context.setVariable("PSESID", pid);
/*  527 */       if (this.m_Parameters != null) {
/*  528 */         this.m_Parameters.removeValue("PSESID");
/*      */       }
/*      */     } 
/*  531 */     if (this.m_Parameters != null)
/*  532 */       this.context.setVariable("APPLET_PARAMETERS", this.m_Parameters.getTable()); 
/*  533 */     this.context.setVariable("CLI-LANG", 
/*  534 */         JLbsCultureInfoBase.getLanguagePrefix(2));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initLAFTheme() {
/*  540 */     if (JLbsConstants.DESKTOP_MODE) {
/*      */       
/*  542 */       JLbsConstants.DESKTOP_MODE = false;
/*  543 */       if (isDesktopLicence()) {
/*      */         
/*  545 */         JLbsConstants.APP_SIZE_DEFAULT = 11;
/*  546 */         JLbsConstants.APP_FONT_SIZE = 11;
/*      */       } 
/*      */     } 
/*  549 */     this.context.changeTheme("com.lbs.laf.mac.LookAndFeel");
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setTimeout(int timeout) {
/*  554 */     this.context.setSessionTimeout(timeout);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getTimeoutMessageString() {
/*  559 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getTimeoutMessageTitle() {
/*  564 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean doBeforeTimeout() {
/*  569 */     String text = getTimeoutMessageString();
/*  570 */     if (text == null)
/*  571 */       text = "Your session will timeout within one minute.\nPlease confirm this dialog to extend the current session!"; 
/*  572 */     String title = getTimeoutMessageTitle();
/*  573 */     if (title == null)
/*  574 */       title = "Please confirm"; 
/*  575 */     int iRes = ILbsOptionPane.showOptionDialog(null, text, title, 0, 3, 17000);
/*      */     
/*  577 */     return (iRes == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remoteSessionTimeout(Exception ex) throws SessionReestablishedException {
/*  588 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initStartupForm() {
/*  593 */     if (this.context == null) {
/*      */       return;
/*      */     }
/*      */     try {
/*  597 */       Object target = this.context.createInstance(this.m_StartupForm);
/*  598 */       this.context.replaceContent((Component)target);
/*  599 */       if (this.access_token != null) {
/*  600 */         this.context.setVariable("AdminAccessToken", this.access_token);
/*      */       }
/*  602 */     } catch (Exception e) {
/*      */       
/*  604 */       System.out.println("Startup form initialization exception: " + e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   class LoginFormInitTask
/*      */     implements Runnable
/*      */   {
/*      */     Object target;
/*      */     UserInfo userInfo;
/*      */     
/*      */     public LoginFormInitTask(Object target, UserInfo userInfo) {
/*  616 */       this.target = target;
/*  617 */       this.userInfo = userInfo;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/*  626 */         if (this.target instanceof JComponent) {
/*      */           
/*  628 */           if (!JLbsStringUtil.isEmpty(LbsClient.this.getParameter("URLTOKEN"))) {
/*      */             
/*  630 */             ((JComponent)this.target).setVisible(false);
/*  631 */             JPanel panel = new JPanel();
/*  632 */             JLabel label = new JLabel("Logging...Please wait...");
/*  633 */             panel.setLayout(new GridBagLayout());
/*  634 */             panel.add(label);
/*  635 */             panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*  636 */             LbsClient.this.context.replaceContent(panel);
/*      */           } else {
/*      */             
/*  639 */             LbsClient.this.context.replaceContent((JComponent)this.target);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  644 */           LbsClient.this.getContentPane().removeAll();
/*  645 */           JLabel lblStatus = LbsClient.this.createLabel("Unable to initialize the login form!");
/*  646 */           lblStatus.setForeground(Color.red);
/*  647 */           LbsClient.this.add(lblStatus, 100, 100, 400, 40);
/*      */         }
/*      */       
/*  650 */       } catch (Exception e) {
/*      */         
/*  652 */         System.out.println("FormInitTask Exception: " + e);
/*      */       } 
/*      */       
/*  655 */       LbsClient.this.invalidate();
/*  656 */       LbsClient.this.repaint();
/*  657 */       if (!JLbsStringUtil.isEmpty(LbsClient.this.access_token)) {
/*      */         
/*  659 */         UserInfo oauthUser = LbsClient.this.getOauthUser(LbsClient.this.access_token);
/*  660 */         this.userInfo = oauthUser;
/*      */       } 
/*  662 */       if (null == this.userInfo) {
/*      */         
/*  664 */         UserInfo ldap = LbsClient.this.lookForLDAP();
/*  665 */         if (ldap != null) {
/*  666 */           this.userInfo = ldap;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  671 */       else if (LbsClient.this.m_SsoKeyExists && !this.userInfo.Name.isEmpty()) {
/*      */         
/*  673 */         UserInfo ssoUser = LbsClient.this.getSsoUser(this.userInfo.Name);
/*      */         
/*  675 */         this.userInfo = ssoUser;
/*      */       } 
/*      */ 
/*      */       
/*  679 */       JLbsSwingUtilities.invokeLater(this, new Runnable()
/*      */           {
/*      */ 
/*      */             
/*      */             public void run()
/*      */             {
/*      */               try {
/*  686 */                 Thread.sleep(100L);
/*  687 */                 if (LbsClient.LoginFormInitTask.this.target instanceof JLbsClientLoginPanel) {
/*      */                   
/*  689 */                   System.out.println("RUN METHOD INVOKED");
/*      */                   
/*  691 */                   JLbsClientLoginPanel loginPnl = (JLbsClientLoginPanel)LbsClient.LoginFormInitTask.this.target;
/*  692 */                   if (LbsClient.this.getParameter("URLTOKEN") != null) {
/*      */                     
/*  694 */                     String queryString = LbsClient.this.getParameter("URLTOKEN");
/*  695 */                     LbsClient.LoginFormInitTask.this.userInfo = new UserInfo();
/*  696 */                     LbsClient.LoginFormInitTask.this.userInfo.modeLogin = UserInfo.MODE_LOGIN.TOKEN;
/*  697 */                     LbsClient.LoginFormInitTask.this.userInfo.Name = queryString;
/*  698 */                     LbsClient.LoginFormInitTask.this.userInfo.selectedLanguage = LbsRequestUtil.getLangParameter(queryString, "ENUS");
/*  699 */                     LbsClient.LoginFormInitTask.this.userInfo.variableHolder = (IVariableHolder)LbsClient.this.context;
/*  700 */                     loginPnl.login(LbsClient.LoginFormInitTask.this.userInfo);
/*      */                   }
/*      */                   else {
/*      */                     
/*  704 */                     if (LbsClient.LoginFormInitTask.this.userInfo == null) {
/*      */                       
/*      */                       try {
/*      */ 
/*      */                         
/*  709 */                         Hashtable<String, Object> oauthConfiguration = (Hashtable<String, Object>)LbsClient.this.context.getPublicObject("OauthConfiguration", null, true);
/*  710 */                         int oauthType = ((Integer)oauthConfiguration.get("OAuthType")).intValue();
/*  711 */                         if (LbsClient.this.access_token != null)
/*      */                         {
/*  713 */                           loginPnl.setAccess_token(LbsClient.this.access_token);
/*  714 */                           if (loginPnl.getClass().getSimpleName().equals("AdminLoginForm")) {
/*      */                             
/*  716 */                             LbsClient.LoginFormInitTask.this.userInfo = (UserInfo)LbsClient.this.context.getPublicObject("getAccessTokenAdminUserInfo", new String[] {
/*  717 */                                   LbsClient.access$300(this.this$1.this$0) }, true);
/*      */                           } else {
/*      */                             
/*  720 */                             LbsClient.LoginFormInitTask.this.userInfo = (UserInfo)LbsClient.this.context.getPublicObject("getAccessTokenUserInfo", new String[] {
/*  721 */                                   LbsClient.access$300(this.this$1.this$0) }, true);
/*  722 */                           }  if (LbsClient.LoginFormInitTask.this.userInfo == null && JLbsConstants.checkAppCloud() && oauthType == 2)
/*      */                           {
/*  724 */                             LbsClient.this.context.openURL(JLbsConstants.JUGNU_PORTAL + "/login", null);
/*  725 */                             System.exit(0);
/*      */                           
/*      */                           }
/*      */                         
/*      */                         }
/*  730 */                         else if (JLbsConstants.checkAppCloud() && oauthType == 2)
/*      */                         {
/*  732 */                           LbsClient.this.context.openURL(JLbsConstants.JUGNU_PORTAL + "/login", null);
/*  733 */                           System.exit(0);
/*      */                         }
/*      */                       
/*      */                       }
/*  737 */                       catch (Exception e) {
/*      */                         
/*  739 */                         LbsConsole.getLogger(getClass()).error(e, e);
/*      */                       } 
/*      */                     }
/*  742 */                     if (LbsClient.LoginFormInitTask.this.userInfo != null) {
/*      */                       
/*  744 */                       LbsClient.LoginFormInitTask.this.userInfo.variableHolder = (IVariableHolder)LbsClient.this.context;
/*  745 */                       if (!StringUtil.isEmpty(LbsClient.LoginFormInitTask.this.userInfo.Domain)) {
/*      */                         
/*  747 */                         loginPnl.setLdapUserInfo(LbsClient.LoginFormInitTask.this.userInfo);
/*  748 */                         if (JLbsConstants.LDAP_AUTOLOGIN)
/*      */                         {
/*  750 */                           loginPnl.loginViaLdapWith(LbsClient.LoginFormInitTask.this.userInfo);
/*  751 */                           LbsClient.LoginFormInitTask.this.userInfo.modeLogin = UserInfo.MODE_LOGIN.LDAP_FORCE_PASSWORD;
/*      */                         }
/*      */                       
/*      */                       } else {
/*      */                         
/*  756 */                         loginPnl.loginViaSSOWith(LbsClient.LoginFormInitTask.this.userInfo);
/*      */                       }
/*      */                     
/*  759 */                     } else if (loginPnl.getClass().getSimpleName().equals("AdminLoginForm")) {
/*      */                       
/*  761 */                       String[] osUserData = LbsClient.this.getOSUserData();
/*  762 */                       UserInfo userLdapInfo = (UserInfo)LbsClient.this.context.getPublicObject("getLdapUserLoginInfo", osUserData, true);
/*      */                       
/*  764 */                       if (userLdapInfo != null && !StringUtil.isEmpty(userLdapInfo.Domain))
/*      */                       {
/*  766 */                         userLdapInfo.variableHolder = (IVariableHolder)LbsClient.this.context;
/*  767 */                         loginPnl.setLdapUserInfo(userLdapInfo);
/*  768 */                         if (JLbsConstants.LDAP_AUTOLOGIN)
/*      */                         {
/*  770 */                           loginPnl.loginViaLdapWith(userLdapInfo);
/*  771 */                           userLdapInfo.modeLogin = UserInfo.MODE_LOGIN.LDAP_FORCE_PASSWORD;
/*      */                         }
/*      */                       
/*      */                       }
/*  775 */                       else if (LbsClient.LoginFormInitTask.this.userInfo != null)
/*      */                       {
/*  777 */                         loginPnl.loginViaSSOWith(LbsClient.LoginFormInitTask.this.userInfo);
/*      */                       }
/*      */                     
/*      */                     } else {
/*      */                       
/*  782 */                       loginPnl.setFocusToUser();
/*      */                     } 
/*      */                   } 
/*  785 */                 } else if (LbsClient.LoginFormInitTask.this.target instanceof JLbsDesktopClientLoginPanel) {
/*      */                   
/*  787 */                   JLbsDesktopClientLoginPanel loginPnl = (JLbsDesktopClientLoginPanel)LbsClient.LoginFormInitTask.this.target;
/*  788 */                   loginPnl.setFocusToUser();
/*      */                 }
/*      */               
/*  791 */               } catch (InterruptedException e) {
/*      */                 
/*  793 */                 e.printStackTrace();
/*      */               }
/*  795 */               catch (Exception e) {
/*      */                 
/*  797 */                 e.printStackTrace();
/*      */               } 
/*      */             }
/*      */           });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected UserInfo getSsoUser(String userName) {
/*  806 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected UserInfo getOauthUser(String accessToken) {
/*  811 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isValidAccessToken(String accessToken) {
/*  816 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowActivated(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowClosed(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canClose() {
/*  831 */     if ((this.context.showMessage("EXIT_PROGRAM", "Are you sure?", null, null)).button == 4)
/*      */     {
/*  833 */       return true;
/*      */     }
/*  835 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowClosing(WindowEvent e) {
/*  841 */     if (!this.context.isLoggedIn() || canClose()) {
/*      */ 
/*      */       
/*      */       try {
/*  845 */         if (this.windowClosingListener != null)
/*  846 */           this.windowClosingListener.onWindowClosing(e); 
/*  847 */         if (this.context.isLoggedIn())
/*  848 */           this.context.logout(); 
/*  849 */         System.exit(0);
/*      */       
/*      */       }
/*  852 */       catch (Exception exception) {}
/*      */ 
/*      */       
/*  855 */       if ((LbsClientFieldHolder.getInstance()).ms_CrashListener != null) {
/*      */         
/*      */         try {
/*      */           
/*  859 */           (LbsClientFieldHolder.getInstance()).ms_CrashListener.crashing();
/*      */         }
/*  861 */         catch (Exception exception) {}
/*      */       }
/*      */ 
/*      */       
/*  865 */       if (this.context != null) {
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */           
/*  871 */           this.context.flushCacheContentToDisk(true);
/*      */         }
/*  873 */         catch (Exception exception) {}
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowDeactivated(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowDeiconified(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowIconified(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowOpened(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void userLoggedIn() {
/*      */     try {
/*  904 */       String message = null;
/*  905 */       Object o = this.context.getVariable("CLI-WARN-DAYS");
/*  906 */       if (o instanceof Integer) {
/*      */         
/*  908 */         int warnDays = ((Integer)o).intValue();
/*  909 */         if (warnDays != Integer.MAX_VALUE && warnDays > 0) {
/*      */           
/*  911 */           String s = this.context.getLocalizationService().getItem(JLbsConstants.GLOBAL_RESOURCES, 18);
/*  912 */           if (StringUtil.isEmpty(s))
/*  913 */             s = "Your license will be expired in ~1 days"; 
/*  914 */           s = JLbsStringUtil.mergeParameters(s, new String[] { warnDays + "" }, new int[] { 1 });
/*  915 */           message = " (" + s + ")";
/*      */         } 
/*      */       } 
/*  918 */       o = this.context.getVariable("CLI-ALLOWED-DAYS");
/*  919 */       if (o instanceof Integer) {
/*      */         
/*  921 */         int allowedDays = ((Integer)o).intValue();
/*  922 */         if (allowedDays != Integer.MAX_VALUE && allowedDays > 0) {
/*      */           
/*  924 */           String s = this.context.getLocalizationService().getItem(JLbsConstants.GLOBAL_RESOURCES, 19);
/*  925 */           if (StringUtil.isEmpty(s))
/*  926 */             s = "Your license has expired. In ~1 days your product will become unusable."; 
/*  927 */           s = JLbsStringUtil.mergeParameters(s, new String[] { allowedDays + "" }, new int[] { 1 });
/*  928 */           message = " (" + s + ")";
/*      */         } 
/*      */       } 
/*  931 */       if (!JLbsStringUtil.isEmpty(message) && 
/*  932 */         !execJScript("titlebar(0, '" + message + "');")) {
/*      */         JLbsFrame jLbsFrame1;
/*  934 */         JApplet baseApplet = getBaseApplet();
/*  935 */         JFrame frame = null;
/*  936 */         if (baseApplet instanceof JLbsStartup)
/*  937 */           frame = ((JLbsStartup)baseApplet).getFrame(); 
/*  938 */         if (frame == null)
/*  939 */           jLbsFrame1 = LbsClient.frame; 
/*  940 */         if (jLbsFrame1 == null)
/*      */           return; 
/*  942 */         final JLbsFrame fframe = jLbsFrame1;
/*  943 */         final String msg = message;
/*  944 */         TimerTask task = new TimerTask()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/*  949 */               SwingUtilities.invokeLater(new Runnable()
/*      */                   {
/*      */ 
/*      */                     
/*      */                     public void run()
/*      */                     {
/*  955 */                       String title = fframe.getTitle();
/*  956 */                       if (title == null)
/*  957 */                         title = ""; 
/*  958 */                       int idx = title.indexOf(msg);
/*  959 */                       if (idx >= 0) {
/*  960 */                         title = title.substring(0, idx);
/*      */                       } else {
/*  962 */                         title = title + msg;
/*  963 */                       }  fframe.setTitle(title);
/*      */                     }
/*      */                   });
/*      */             }
/*      */           };
/*  968 */         Timer timer = new Timer(true);
/*  969 */         timer.schedule(task, Calendar.getInstance().getTime(), 1500L);
/*      */       }
/*      */     
/*  972 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean execJScript(String script) {
/*  979 */     boolean result = true;
/*      */     
/*      */     try {
/*  982 */       JApplet baseApplet = getBaseApplet();
/*  983 */       if (baseApplet == null)
/*  984 */         return false; 
/*  985 */       JSObject window = JSObject.getWindow(baseApplet);
/*  986 */       if (window != null) {
/*  987 */         window.eval(script);
/*      */       }
/*  989 */     } catch (Exception e) {
/*      */       
/*  991 */       e.printStackTrace();
/*  992 */       result = false;
/*      */     } 
/*  994 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void userLoginFailed() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void userLoggedOut() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void userRecovered() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSilentLogin() {
/* 1014 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWindowClosingListener(IClientUIInit unit) {
/* 1021 */     this.windowClosingListener = unit;
/*      */   }
/*      */ 
/*      */   
/*      */   public UserInfo getUserInfo() {
/* 1026 */     return this.m_UserInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void modifyTitles(String part1, String part2) {
/* 1031 */     if ((LbsClientFieldHolder.getInstance()).ms_TitlesModified) {
/*      */       return;
/*      */     }
/*      */     try {
/* 1035 */       JApplet baseApplet = getBaseApplet();
/* 1036 */       String title = null;
/* 1037 */       if (part1 != null && !part1.trim().equals("") && !part1.trim().equals("null"))
/* 1038 */         title = part1; 
/* 1039 */       if (part2 != null && !part2.trim().equals("") && !part2.trim().equals("null")) {
/*      */         
/* 1041 */         if (baseApplet instanceof JLbsStartup)
/*      */         {
/* 1043 */           title = ((JLbsStartup)baseApplet).getTitle();
/*      */         }
/* 1045 */         if (title != null) {
/*      */           
/* 1047 */           title = title + " (" + part2 + ")";
/*      */         } else {
/*      */           
/* 1050 */           title = "(" + part2 + ")";
/*      */         } 
/* 1052 */       }  if (title != null && !title.trim().equals("") && !title.trim().equals("null") && baseApplet instanceof JLbsStartup)
/*      */       {
/* 1054 */         boolean set = ((JLbsStartup)baseApplet).setTitle(title);
/* 1055 */         if (!set)
/*      */         {
/* 1057 */           execJScript("top.document.title += ' " + title + "';");
/*      */         }
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 1063 */       (LbsClientFieldHolder.getInstance()).ms_TitlesModified = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setMailCapCommandMap() {
/* 1070 */     MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
/* 1071 */     mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
/* 1072 */     mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
/* 1073 */     mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
/* 1074 */     mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
/* 1075 */     mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
/* 1076 */     CommandMap.setDefaultCommandMap((CommandMap)mc);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isDesktopLicence() {
/* 1082 */     boolean isDesktop = false;
/*      */     
/* 1084 */     if ("com.lbs.unity.main.UnityLoginForm".equals(this.m_StartupForm)) {
/*      */       try {
/*      */         Object result;
/*      */         
/* 1088 */         if ((result = this.context.getPublicObject("IsDesktop", null, true)) != null)
/*      */         {
/* 1090 */           if (result instanceof Boolean)
/*      */           {
/* 1092 */             isDesktop = ((Boolean)result).booleanValue();
/*      */           }
/*      */         }
/*      */       }
/* 1096 */       catch (Exception e) {
/*      */ 
/*      */         
/* 1099 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */     
/* 1103 */     return isDesktop;
/*      */   }
/*      */ 
/*      */   
/*      */   public static class LbsClientFieldHolder
/*      */   {
/*      */     private ILbsCrashHandleListener ms_CrashListener;
/*      */     private boolean ms_TitlesModified = false;
/*      */     
/*      */     private static LbsClientFieldHolder getInstance() {
/* 1113 */       return (LbsClientFieldHolder)LbsClassInstanceProvider.getInstanceByClass(LbsClientFieldHolder.class);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\LbsClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */