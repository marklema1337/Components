/*     */ package com.lbs.client;
/*     */ 
/*     */ import com.lbs.appobjects.LbsApplicationConfig;
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.invoke.InvalidSessionException;
/*     */ import com.lbs.invoke.SessionReestablishedException;
/*     */ import com.lbs.reporting.render.rc.JLbsReportRunDlg;
/*     */ import com.lbs.start.JLbsContextLocator;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.Chronometer;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsConvertUtil;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsFrame;
/*     */ import com.lbs.util.JLbsOpenWindowListing;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Window;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
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
/*     */ public class LbsAppClient
/*     */   extends LbsClient
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected int m_Width;
/*     */   protected int m_Height;
/*     */   protected String m_WebAppName;
/*     */   protected String m_TerminatePage;
/*     */   protected boolean m_StartChronometer;
/*     */   protected boolean m_PrintSystemProperties;
/*  48 */   private LbsPasswordRequestDialog m_TimeoutDialog = null;
/*     */   
/*     */   private static JPanel contPanel;
/*     */   
/*     */   private static boolean terminating = false;
/*     */   private static final int SMSG_PRETIMEOUTTITLE = 1;
/*     */   private static final int SMSG_PRETIMEOUTMESSAGE = 2;
/*     */   
/*     */   public LbsAppClient() {
/*  57 */     this.m_UseCache = true;
/*  58 */     this.m_StartChronometer = false;
/*  59 */     this.m_PrintSystemProperties = false;
/*     */     
/*  61 */     JPopupMenu.setDefaultLightWeightPopupEnabled(false);
/*     */   }
/*     */   private static final int SMSG_POSTTIMEOUTTITLE = 3; private static final int SMSG_POSTTIMEOUTMESSAGE = 4; private static final int SMSG_POSTTIMEOUTDLGPASSWORD = 5;
/*     */   private static final int SMSG_POSTTIMEOUTDLGOK = 6;
/*     */   private static final int SMSG_POSTTIMEOUTDLGCANCEL = 7;
/*     */   
/*     */   public void setAppletVariables(String StartupForm, String StartupJAR, String WebAppName, String TerminatePage, boolean UseCache, boolean UseObjectSchemaCache, boolean StartChronometer, boolean PrintSystemProperties) {
/*  68 */     this.m_StartupForm = StartupForm;
/*  69 */     this.m_StartupJAR = StartupJAR;
/*  70 */     this.m_WebAppName = WebAppName;
/*  71 */     this.m_TerminatePage = TerminatePage;
/*  72 */     this.m_UseCache = UseCache;
/*  73 */     this.m_UseObjectSchemaCache = UseObjectSchemaCache;
/*  74 */     this.m_StartChronometer = StartChronometer;
/*  75 */     this.m_PrintSystemProperties = PrintSystemProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void printSystemProperties() {
/*  80 */     System.out.println("Java Runtime Environment version: " + System.getProperty("java.version"));
/*  81 */     System.out.println("Java Runtime Environment vendor: " + System.getProperty("java.vendor"));
/*  82 */     System.out.println("Java vendor URL: " + System.getProperty("java.vendor.url"));
/*  83 */     System.out.println("Java installation directory: " + System.getProperty("java.home"));
/*  84 */     System.out.println("Java Virtual Machine specification version: " + System.getProperty("java.vm.specification.version"));
/*  85 */     System.out.println("Java Virtual Machine specification vendor: " + System.getProperty("java.vm.specification.vendor"));
/*  86 */     System.out.println("Java Virtual Machine specification name: " + System.getProperty("java.vm.specification.name"));
/*  87 */     System.out.println("Java Virtual Machine implementation version: " + System.getProperty("java.vm.version"));
/*  88 */     System.out.println("Java Virtual Machine implementation vendor: " + System.getProperty("java.vm.vendor"));
/*  89 */     System.out.println("Java Virtual Machine implementation name: " + System.getProperty("java.vm.name"));
/*  90 */     System.out.println("Java Runtime Environment specification version: " + System.getProperty("java.specification.version"));
/*  91 */     System.out.println("Java Runtime Environment specification vendor: " + System.getProperty("java.specification.vendor"));
/*  92 */     System.out.println("Java Runtime Environment specification name: " + System.getProperty("java.specification.name"));
/*  93 */     System.out.println("Java class format version number: " + System.getProperty("java.class.version"));
/*  94 */     System.out.println("Java class path: " + System.getProperty("java.class.path"));
/*  95 */     System.out.println("Path of extension directory or directories: " + System.getProperty("java.ext.dirs"));
/*  96 */     System.out.println("Operating system name: " + System.getProperty("os.name"));
/*  97 */     System.out.println("Operating system architecture: " + System.getProperty("os.arch"));
/*  98 */     System.out.println("Operating system version: " + System.getProperty("os.version"));
/*  99 */     System.out.println("File separator: " + System.getProperty("file.separator"));
/* 100 */     System.out.println("Path separator: " + System.getProperty("path.separator"));
/* 101 */     System.out.println("Line separator: " + System.getProperty("line.separator"));
/* 102 */     System.out.println("User's account name: " + JLbsFileUtil.getSystemUserFolderName());
/* 103 */     System.out.println("User's home directory: " + System.getProperty("user.home"));
/* 104 */     System.out.println("User's current working directory: " + System.getProperty("user.dir"));
/*     */   }
/*     */ 
/*     */   
/*     */   private void setWebAppNameFromRootURI() {
/* 109 */     String s = this.m_RootURI;
/* 110 */     int len = s.length();
/* 111 */     if (s.charAt(len - 1) == '/') {
/* 112 */       s = s.substring(0, len - 1);
/*     */     }
/* 114 */     int idx = s.lastIndexOf("/");
/* 115 */     if (idx > 0) {
/* 116 */       s = s.substring(idx + 1);
/*     */     }
/* 118 */     if (s.compareToIgnoreCase("HRWebTier") == 0 || s.compareToIgnoreCase("logo") == 0) {
/* 119 */       this.m_WebAppName = s;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/* 125 */     applyAppletParameters();
/* 126 */     if (this.m_PrintSystemProperties) {
/*     */       
/*     */       try {
/*     */         
/* 130 */         printSystemProperties();
/*     */       }
/* 132 */       catch (Exception e) {
/*     */         
/* 134 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 138 */     if (this.m_StartChronometer) {
/*     */       
/* 140 */       Chronometer.reset();
/* 141 */       Chronometer.record("Started @ ");
/* 142 */       Chronometer.record("LbsAppClient.init() ");
/*     */     } 
/*     */     
/* 145 */     this.m_RootURI = getParameter("ROOTURI");
/* 146 */     if (this.m_RootURI == null) {
/*     */       
/* 148 */       String appName = getParameter("APPNAME");
/* 149 */       if (appName != null)
/* 150 */         this.m_WebAppName = appName; 
/* 151 */       this.m_RootURI = "http://localhost:8080/" + this.m_WebAppName + "/";
/*     */     } else {
/*     */       
/* 154 */       setWebAppNameFromRootURI();
/*     */     } 
/* 156 */     String titleParameter = getParameter("INI_TITLE");
/* 157 */     if (!JLbsStringUtil.isEmpty(titleParameter)) {
/* 158 */       JLbsConstants.HR = titleParameter.equalsIgnoreCase("J-HR");
/*     */     }
/* 160 */     super.init();
/*     */     
/* 162 */     String language = getParameter("LANGUAGE");
/* 163 */     if (language == null) {
/* 164 */       language = JLbsCultureInfoBase.getLanguagePrefix(2);
/*     */     }
/* 166 */     prepareTerminateURI();
/*     */ 
/*     */     
/* 169 */     if (getContext().getVariable("CLI-LANG") == null) {
/* 170 */       getContext().setVariable("CLI-LANG", language);
/*     */     }
/* 172 */     if (this.m_StartChronometer) {
/* 173 */       Chronometer.record("end of LbsAppClient.init() ");
/*     */     }
/*     */   }
/*     */   
/*     */   private void applyAppletParameters() {
/* 178 */     if (getParameter("STARTUPFORM") != null && !getParameter("STARTUPFORM").equals("null"))
/* 179 */       this.m_StartupForm = getParameter("STARTUPFORM"); 
/* 180 */     if (getParameter("STARTUPJAR") != null && !getParameter("STARTUPFORM").equals("null"))
/* 181 */       this.m_StartupJAR = getParameter("STARTUPJAR"); 
/* 182 */     if (getParameter("WEBAPPNAME") != null && !getParameter("STARTUPFORM").equals("null"))
/* 183 */       this.m_WebAppName = getParameter("WEBAPPNAME"); 
/* 184 */     if (getParameter("TERMINATEPAGE") != null && !getParameter("STARTUPFORM").equals("null"))
/* 185 */       this.m_TerminatePage = getParameter("TERMINATEPAGE"); 
/* 186 */     if (getParameter("USECACHE") != null && getParameter("USECACHE").equals("false"))
/* 187 */       this.m_UseCache = false; 
/* 188 */     if (getParameter("USEOBJECTSCHEMACACHE") != null && 
/* 189 */       getParameter("USEOBJECTSCHEMACACHE").equals("false"))
/* 190 */       this.m_UseObjectSchemaCache = false; 
/* 191 */     if (getParameter("STARTCHRONOMETER") != null && 
/* 192 */       getParameter("STARTCHRONOMETER").equals("false"))
/* 193 */       this.m_StartChronometer = false; 
/* 194 */     if (getParameter("PRINTSYSTEMPROPERTIES") != null && 
/* 195 */       getParameter("PRINTSYSTEMPROPERTIES").equals("false")) {
/* 196 */       this.m_PrintSystemProperties = false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void prepareTerminateURI() {
/* 201 */     this.m_TerminateURI = this.m_RootURI + this.m_TerminatePage;
/*     */     
/* 203 */     if (getContext().getVariable("CLI-LANG") != null) {
/* 204 */       this.m_TerminateURI += "?" + getContext().getVariable("CLI-LANG");
/*     */     } else {
/* 206 */       this.m_TerminateURI += "?" + JLbsCultureInfoBase.getLanguagePrefix(2);
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
/*     */   
/*     */   protected void initApplet() {
/* 220 */     if (this.m_StartChronometer) {
/* 221 */       Chronometer.record("LbsAppClient.initApplet() ");
/*     */     }
/* 223 */     if (this.m_Width == 0 || this.m_Height == 0) {
/*     */       
/* 225 */       this.m_Width = JLbsConvertUtil.str2IntDef(getParameter("width"), 0);
/* 226 */       if (this.m_Width == 0) {
/* 227 */         this.m_Width = 800;
/*     */       }
/* 229 */       this.m_Height = JLbsConvertUtil.str2IntDef(getParameter("height"), 0);
/* 230 */       if (this.m_Height == 0) {
/* 231 */         this.m_Height = 600;
/*     */       }
/* 233 */       setSize(this.m_Width, this.m_Height);
/*     */     } 
/*     */     
/* 236 */     super.initApplet();
/* 237 */     JLbsFileUtil.setRootDirectory(JLbsClientFS.getRootPath());
/* 238 */     JLbsStringList messages = new JLbsStringList();
/* 239 */     messages.add("Warning", 1);
/* 240 */     messages.add("In 30 seconds, your session will expire.\\nDo you want to continue your session?", 2);
/* 241 */     messages.add("Timeout!", 3);
/* 242 */     messages.add("Your session has expired. Please log in again.", 4);
/* 243 */     messages.add("Password:", 5);
/* 244 */     messages.add("OK", 6);
/* 245 */     messages.add("Quit", 7);
/*     */     
/* 247 */     getContext().setVariable("CLI-MESSAGES", messages);
/*     */     
/* 249 */     getContext().setVariable("ROOTURI", this.m_RootURI);
/*     */     
/* 251 */     terminating = false;
/*     */     
/* 253 */     if (this.m_StartChronometer) {
/* 254 */       Chronometer.record("end of LbsAppClient.initApplet() ");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTimeoutMessageString() {
/* 260 */     JLbsStringList messages = (JLbsStringList)getContext().getVariable("CLI-MESSAGES");
/* 261 */     if (messages != null) {
/* 262 */       return messages.getValueAtTag(2);
/*     */     }
/* 264 */     return "In 30 seconds, your session will expire.\\nDo you want to continue your session?";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTimeoutMessageTitle() {
/* 270 */     JLbsStringList messages = (JLbsStringList)getContext().getVariable("CLI-MESSAGES");
/* 271 */     if (messages != null) {
/* 272 */       return messages.getValueAtTag(1);
/*     */     }
/* 274 */     return "Warning";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String divineDataDirectory() {
/* 280 */     return JLbsContextLocator.getClientRootDirectory(this.m_RootURI);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remoteSessionTimeout(Exception ex) throws SessionReestablishedException {
/* 286 */     JLbsStringList messages = (JLbsStringList)getContext().getVariable("CLI-MESSAGES");
/*     */ 
/*     */     
/* 289 */     if (ex instanceof InvalidSessionException) {
/*     */       
/* 291 */       String str1 = messages.getValueAtTag(8);
/* 292 */       if (JLbsStringUtil.isEmpty(str1))
/* 293 */         str1 = "Licence Problem"; 
/* 294 */       int type = ((InvalidSessionException)ex).getType();
/* 295 */       String message = messages.getValueAtTag(9 + type);
/* 296 */       if (JLbsStringUtil.isEmpty(message)) {
/* 297 */         message = "Your session has become invalid due to invalid licence usage. " + ((type == 0) ? "You may have exceeded the user count limit of your licence package(s)." : "") + "Please consult your system administrator for proper licence usage!";
/*     */       }
/*     */       
/* 300 */       JOptionPane.showMessageDialog(null, message, str1, 0);
/* 301 */       getContext().terminateSession(false);
/* 302 */       getContext().logout();
/* 303 */       getContext().terminateApplication();
/* 304 */       return false;
/*     */     } 
/* 306 */     if (this.m_TimeoutDialog != null)
/*     */     {
/* 308 */       return false;
/*     */     }
/*     */     
/* 311 */     String title = "Warning";
/* 312 */     String msg = "Your session has expired. Please log in again.";
/* 313 */     String info = "";
/* 314 */     String passwordLabelText = "Password:";
/* 315 */     String okButtonCaption = "OK";
/* 316 */     String cancelButtonCaption = "Quit";
/* 317 */     if (messages != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       title = JLbsStringUtil.isEmpty(messages.getValueAtTag(3)) ? title : messages.getValueAtTag(3);
/*     */ 
/*     */       
/* 325 */       msg = JLbsStringUtil.isEmpty(messages.getValueAtTag(4)) ? msg : messages.getValueAtTag(4);
/*     */       
/* 327 */       info = getSessionUserInfo();
/*     */ 
/*     */ 
/*     */       
/* 331 */       passwordLabelText = JLbsStringUtil.isEmpty(messages.getValueAtTag(5)) ? passwordLabelText : messages.getValueAtTag(5);
/*     */ 
/*     */       
/* 334 */       okButtonCaption = JLbsStringUtil.isEmpty(messages.getValueAtTag(6)) ? okButtonCaption : messages.getValueAtTag(6);
/*     */ 
/*     */       
/* 337 */       cancelButtonCaption = JLbsStringUtil.isEmpty(messages.getValueAtTag(7)) ? cancelButtonCaption : messages.getValueAtTag(7);
/*     */     } 
/*     */     
/* 340 */     boolean passwordFieldEnabled = true;
/*     */     
/* 342 */     UserInfo ui = getContext().getUserInfo();
/* 343 */     if (ui != null && JLbsConstants.LDAP_AUTOLOGIN && ui.modeLogin == UserInfo.MODE_LOGIN.LDAP_FORCE_PASSWORD && 
/* 344 */       !StringUtil.isEmpty(ui.Domain) && !StringUtil.isEmpty(ui.Name) && !StringUtil.isEmpty(ui.OSUser) && ui.Name
/* 345 */       .equals(ui.OSUser))
/* 346 */       passwordFieldEnabled = false; 
/* 347 */     this.m_TimeoutDialog = new LbsPasswordRequestDialog(title, msg, info, passwordLabelText, okButtonCaption, cancelButtonCaption, passwordFieldEnabled);
/* 348 */     while (!terminating) {
/*     */       
/* 350 */       this.m_TimeoutDialog.showDialog();
/* 351 */       if (this.m_TimeoutDialog.isOkPressed()) {
/*     */         
/* 353 */         boolean ok = getContext().relogin(this.m_TimeoutDialog.getPassword());
/* 354 */         if (ok) {
/*     */           
/* 356 */           this.m_TimeoutDialog = null;
/*     */           
/* 358 */           Window[] openDialogs = JLbsReportRunDlg.getWindows();
/* 359 */           for (int i = 0; i < openDialogs.length; i++) {
/*     */             
/* 361 */             if (!(openDialogs[i] instanceof LbsPasswordRequestDialog) && openDialogs[i] instanceof JLbsReportRunDlg)
/*     */             {
/* 363 */               if (((JLbsReportRunDlg)openDialogs[i]).isInternalFrame()) {
/*     */                 
/* 365 */                 ((JLbsReportRunDlg)openDialogs[i]).getInternalFrame().dispose();
/* 366 */                 JLbsOpenWindowListing.removeWindow(((JLbsReportRunDlg)openDialogs[i]).getInternalFrame());
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 371 */           throw new SessionReestablishedException("Session recreated.");
/*     */         } 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 378 */       terminating = true;
/*     */     } 
/*     */ 
/*     */     
/* 382 */     getContext().terminateApplication();
/* 383 */     this.m_TimeoutDialog = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getSessionUserInfo() {
/* 396 */     StringBuilder sb = new StringBuilder();
/* 397 */     String iniTitle = LbsApplicationConfig.getAppletParameter("INI_TITLE");
/* 398 */     String userFullName = getContext().getUserDetailInfo().getFullName();
/* 399 */     String userName = getContext().getUserDetailInfo().getUserName();
/* 400 */     String companyTitle = getContext().getUserDetailInfo().getFirmTitle();
/* 401 */     String rootUri = getContext().getRootUri();
/*     */     
/* 403 */     if (!JLbsStringUtil.isEmpty(iniTitle)) {
/*     */       
/* 405 */       sb.append(iniTitle);
/* 406 */       if (this.m_StartupForm.contains("SAdmin")) {
/* 407 */         sb.append(" server administrator");
/* 408 */       } else if (this.m_StartupForm.contains("Admin")) {
/* 409 */         sb.append(" administrator");
/* 410 */       }  sb.append(" > ");
/*     */     } 
/* 412 */     if (!JLbsStringUtil.isEmpty(companyTitle)) {
/*     */       
/* 414 */       sb.append(companyTitle);
/* 415 */       sb.append(" > ");
/*     */     } 
/* 417 */     if (!JLbsStringUtil.isEmpty(userFullName)) {
/*     */       
/* 419 */       sb.append(userFullName);
/* 420 */       sb.append(" > ");
/*     */     }
/* 422 */     else if (!JLbsStringUtil.isEmpty(userName)) {
/*     */       
/* 424 */       sb.append(userName);
/* 425 */       sb.append(" > ");
/*     */     } 
/* 427 */     if (!JLbsStringUtil.isEmpty(rootUri)) {
/* 428 */       sb.append(rootUri);
/*     */     }
/* 430 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void preProcess(String[] args, String title) {
/* 435 */     System.setProperty("VAADIN_MODE", "false");
/*     */     
/* 437 */     for (int i = 0; i < args.length; i++) {
/* 438 */       System.out.println("Argument(" + i + "):" + args[i]);
/*     */     }
/* 440 */     frame = new JLbsFrame(title);
/* 441 */     frame.setSkipRegisterForm(true);
/* 442 */     frame.setDefaultCloseOperation(0);
/* 443 */     frame.setSize(800, 660);
/*     */     
/* 445 */     contPanel = new JPanel();
/* 446 */     contPanel.setLayout(new BorderLayout());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void adjustClassLoaders() {
/* 452 */     ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
/* 453 */     ClassLoader clsLoader = createClassLoader(new String[] { getParameter("LIBJAR"), this.m_StartupJAR }, this.m_RootURI, contextLoader);
/* 454 */     JLbsContextLocator.clearInstances();
/*     */ 
/*     */     
/* 457 */     Thread.currentThread().setContextClassLoader(clsLoader);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ClassLoader createClassLoader(String[] params, String rootURI, ClassLoader parentLoader) {
/* 462 */     if (parentLoader instanceof JLbsContextLocator) {
/*     */       
/* 464 */       System.out.println("is a contextlocator");
/* 465 */       return parentLoader;
/*     */     } 
/*     */     
/* 468 */     return (ClassLoader)new JLbsContextLocator(params, rootURI, parentLoader);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void postProcess(LbsAppClient application, String[] args) {
/* 473 */     frame.addWindowListener(application);
/* 474 */     application.setParameters(args);
/* 475 */     contPanel.setPreferredSize(new Dimension(800, 600));
/* 476 */     contPanel.add(application, "Center");
/* 477 */     application.init();
/* 478 */     frame.setContentPane(contPanel);
/* 479 */     frame.centerScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 484 */     preProcess(args, "Logo Application");
/* 485 */     LbsAppClient application = new LbsAppClient();
/* 486 */     postProcess(application, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWebAppName() {
/* 491 */     return this.m_WebAppName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\LbsAppClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */