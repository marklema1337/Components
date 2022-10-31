/*     */ package com.lbs.start;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.SwingUtilities;
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
/*     */ public class JLbsStartup
/*     */   extends JApplet
/*     */   implements Runnable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final JLbsAnimationPanel panel;
/*     */   private Thread callingThread;
/*     */   private final JFrame m_Frame;
/*     */   private Dimension m_SetDimension;
/*     */   private static ILbsURLListener ms_URLListener;
/*     */   private static WindowListener ms_WindowListener;
/*     */   public static boolean SYSTEM_CLOSING = false;
/*     */   private Hashtable m_Parameters;
/*     */   private boolean m_Ready;
/*     */   
/*     */   public JLbsStartup() {
/*  54 */     this((JFrame)null);
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
/*     */   public JLbsStartup(JFrame frame) {
/* 301 */     this.m_Ready = false; setSize(640, 480); this.panel = new JLbsAnimationPanel("Loading LOGO Application", getImageIcon("jGuar.png")); this.panel.setForeground(Color.BLACK); this.panel.setLayout((LayoutManager)null); setContentPane(this.panel); this.m_Frame = frame; this.panel.start();
/*     */   } public void processToken(final String token, final int port) { Thread sendThread = new Thread() { public void run() { SocketToken.sendToken(token, port); } }
/*     */       ; sendThread.start(); } public boolean setTitle(String title) { if (this.m_Frame != null) { this.m_Frame.setTitle(title); this.m_Frame.invalidate(); this.m_Frame.repaint(); return true; }  Container p = getParent(); if (p != null) { p = p.getParent(); if (p instanceof Frame) { ((Frame)p).setTitle(title); return true; }  }  return false; } public String getTitle() { if (this.m_Frame != null) return this.m_Frame.getTitle();  Container p = getParent(); if (p != null) { p = p.getParent(); if (p instanceof Frame) return ((Frame)p).getTitle();  }  return null; } public JFrame getFrame() { return this.m_Frame; }
/*     */   public static void setWindowListener(WindowListener windowListener) { ms_WindowListener = windowListener; }
/* 305 */   private void setParameters(String[] args) { this.m_Parameters = new Hashtable<>();
/* 306 */     for (int i = 0; i < args.length; i++) {
/*     */       
/* 308 */       int idx = args[i].indexOf('=');
/* 309 */       if (idx > 0 && idx + 1 < args[i].length())
/*     */       {
/* 311 */         this.m_Parameters.put(args[i].substring(0, idx), args[i].substring(idx + 1)); } 
/*     */     }  }
/*     */   public void setBounds(int x, int y, int width, int height) { super.setBounds(x, y, width, height); }
/*     */   public void setSize(int width, int height) { super.setSize(width, height); if (this.m_Frame != null) {
/*     */       this.m_Frame.setSize(width, height); Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize(); int x = (scrSize.width - this.m_Frame.getWidth()) / 2; int y = (scrSize.height - this.m_Frame.getHeight()) / 2; this.m_Frame.setLocation(x, y); this.m_Frame.validate(); this.m_Frame.getContentPane().setBackground(Color.BLUE);
/*     */     }  validate();
/*     */     invalidate(); }
/*     */   public void init() { this.callingThread = Thread.currentThread();
/* 319 */     (new Thread(this)).start(); } public String getParameter(String name) { if (this.m_Parameters != null) {
/*     */       
/* 321 */       String result = (String)this.m_Parameters.get(name);
/* 322 */       if (result != null) {
/* 323 */         return result;
/*     */       }
/*     */     } 
/*     */     
/* 327 */     try { return super.getParameter(name); }
/*     */     
/* 329 */     catch (Exception exception)
/*     */     
/*     */     { 
/* 332 */       return null; }  }
/*     */   public void stop() { try { if (ms_WindowListener != null) ms_WindowListener.windowClosing(null);  } catch (Exception e) { e.printStackTrace(); }  super.stop(); }
/*     */   protected static void runMain(JFrame frame, JLbsStartup startup, String[] args) { startup.setParameters(args); String title = (startup.getParameter("INI_TITLE") == null || startup.getParameter("INI_TITLE").equals("null")) ? "j-Platform" : startup.getParameter("INI_TITLE"); String webAppName = (startup.getParameter("WEBAPPNAME") == null || startup.getParameter("WEBAPPNAME").equals("null")) ? "logo" : startup.getParameter("WEBAPPNAME"); if (startup instanceof JLbsWSStartup) { String rootURI = startup.getParameter("ROOTURI"); if (rootURI != null && rootURI.indexOf(webAppName) - 1 > 0) frame.setTitle(rootURI.substring(0, rootURI.indexOf(webAppName) - 1) + " - " + title);  } else { frame.setTitle(title); }  String isELogoDefter = (startup.getParameter("ELOGODEFTER") == null || startup.getParameter("ELOGODEFTER").equals("null")) ? null : startup.getParameter("ELOGODEFTER"); startup.init(); frame.setDefaultCloseOperation(0); frame.setContentPane(startup); boolean desktop = false; try { frame.setIconImage(getImageIcon("LbsApplicationDesktop.png").getImage()); desktop = Boolean.parseBoolean(startup.getParameter("DESKTOP")); if (desktop) { if (!"saaserp".equalsIgnoreCase(title)) frame.setIconImage(getImageIcon("LbsApplicationDesktop3.png").getImage());  Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(); frame.setLocation(rec.x, rec.y); frame.setSize(rec.width, rec.height); frame.setUndecorated(true); } else { String parameterX = startup.getParameter("X"); String parameterY = startup.getParameter("Y"); if (parameterX != null && parameterY != null) { int x = Integer.parseInt(parameterX); int y = Integer.parseInt(parameterY); frame.setSize(x, y); }  String iniWidth = startup.getParameter("INI_WIDTH"); String iniHeight = startup.getParameter("INI_HEIGHT"); if (iniWidth != null && iniHeight != null) { int x = Integer.parseInt(iniWidth); int y = Integer.parseInt(iniHeight); frame.setSize(x, y); } else { frame.setSize(800, 660); }  }  } catch (Exception e) { frame.setSize(800, 660); }  if ("saaserp".equalsIgnoreCase(title)) frame.setIconImage(getImageIcon("LbsApplicationDesktopSaasErp.png").getImage());  if ("j-hr".equalsIgnoreCase(title))
/*     */       frame.setIconImage(getImageIcon("jHR_favicon.png").getImage());  if (isELogoDefter != null)
/*     */       frame.setIconImage(getImageIcon("eLogoDefterfav.png").getImage());  if (!desktop) { Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize(); int x = (scrSize.width - frame.getWidth()) / 2; int y = (scrSize.height - frame.getHeight()) / 2; frame.setLocation(x, y); }  frame.show(); }
/* 337 */   public static void main(String[] args) { JFrame frame = new JFrame(); JLbsStartup startup = new JLbsStartup(frame); runMain(frame, startup, args); } protected ClassLoader createClassLoader(String[] params, String rootURI, ClassLoader parentLoader) { if (parentLoader instanceof JLbsContextLocator) {
/*     */       
/* 339 */       System.out.println("is a contextlocator");
/* 340 */       return parentLoader;
/*     */     } 
/*     */     
/* 343 */     return new JLbsContextLocator(params, rootURI, parentLoader); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doResize(int width, int height) {
/* 349 */     this.m_SetDimension = new Dimension(width, height);
/* 350 */     if (width != getWidth() || height != getHeight()) {
/*     */       
/* 352 */       final int finalWidth = width;
/* 353 */       final int finalHeight = height;
/* 354 */       SwingUtilities.invokeLater(new Runnable()
/*     */           {
/*     */             
/*     */             public void run()
/*     */             {
/*     */               try {
/* 360 */                 JLbsStartup.this.setSize(finalWidth, finalHeight);
/*     */               }
/* 362 */               catch (Exception exception) {}
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 372 */     SYSTEM_CLOSING = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void launchURL(String url, String statusDivId) {
/* 377 */     if (ms_URLListener != null) {
/* 378 */       ms_URLListener.launchURL(url, statusDivId);
/*     */     }
/*     */   }
/*     */   
/*     */   public void launchURL(String url, int opType, int lRef, String statusDivId) {
/* 383 */     if (ms_URLListener != null) {
/* 384 */       ms_URLListener.launchURL(url, opType, lRef, statusDivId);
/*     */     }
/*     */   }
/*     */   
/*     */   public void login(String userName, String password, String language) {
/* 389 */     if (ms_URLListener != null) {
/* 390 */       ms_URLListener.login(userName, password, language);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getSessionCode() {
/* 395 */     if (ms_URLListener != null)
/* 396 */       return ms_URLListener.getSessionCode(); 
/* 397 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUODFirm() {
/* 402 */     if (ms_URLListener != null)
/* 403 */       return ms_URLListener.getUODFirm(); 
/* 404 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUODUrl() {
/* 409 */     if (ms_URLListener != null)
/* 410 */       return ms_URLListener.getUODUrl(); 
/* 411 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleOneClickLogging() {
/* 416 */     if (ms_URLListener != null) {
/* 417 */       ms_URLListener.toggleOneClickLogging();
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeCompany(String companyNr, String statusDivId, String buttonId) {
/* 422 */     if (ms_URLListener != null) {
/* 423 */       ms_URLListener.changeCompany(companyNr, statusDivId, buttonId);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isReady() {
/* 428 */     return this.m_Ready;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static ImageIcon getImageIcon(String filename) {
/* 433 */     URL url = JLbsStartup.class.getResource(filename);
/* 434 */     if (url == null)
/* 435 */       return null; 
/* 436 */     return new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 443 */       String queryString = getParameter("URLTOKEN");
/*     */       
/* 445 */       if (queryString != null && queryString.trim().length() > 3) {
/*     */         
/* 447 */         boolean freshPort = getFreshPortParameter(queryString, false);
/* 448 */         if (!freshPort) {
/*     */           
/* 450 */           Object[] tokenInfo = getTokenInfo(queryString);
/* 451 */           if (tokenInfo == null || tokenInfo.length < 3) {
/*     */             
/* 453 */             System.out.println("Token not found");
/*     */             
/*     */             return;
/*     */           } 
/* 457 */           int userId = ((Integer)tokenInfo[0]).intValue();
/* 458 */           int firmNr = ((Integer)tokenInfo[1]).intValue();
/* 459 */           String token = (String)tokenInfo[2];
/* 460 */           int tokenPort = SocketToken.createPortNumber(userId, firmNr);
/* 461 */           if (SocketToken.isAlreadyOpenedByUs(tokenPort)) {
/*     */             
/* 463 */             SocketToken.sendToken(token, tokenPort);
/* 464 */             System.out.println("Token send to port:" + tokenPort);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 469 */           if (userId > 0 && firmNr > 0) {
/*     */             
/* 471 */             int tempTokenPort = SocketToken.loadPortFromTemp(SocketToken.getPortFileName(userId, firmNr));
/* 472 */             if (tempTokenPort > 0 && tempTokenPort != tokenPort && 
/* 473 */               SocketToken.isAlreadyOpenedByUs(tempTokenPort)) {
/*     */               
/* 475 */               SocketToken.sendToken(token, tempTokenPort);
/* 476 */               System.out.println("Token send to port:" + tempTokenPort);
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */           } else {
/* 482 */             System.out.println("Invalid token string");
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 489 */       ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
/* 490 */       ClassLoader clsLoader = createClassLoader(new String[] {
/* 491 */             getParameter("LIBJAR"), getParameter("MAINJAR"), getParameter("OTHERJAR")
/* 492 */           }, getParameter("ROOTURI"), contextLoader);
/* 493 */       JLbsContextLocator.clearInstances();
/* 494 */       this.callingThread.setContextClassLoader(clsLoader);
/*     */       
/* 496 */       Thread.currentThread().setContextClassLoader(clsLoader);
/*     */       
/* 498 */       this.panel.setMessage("Loading components library...");
/* 499 */       Class<?> c = clsLoader.loadClass("com.lbs.util.JLbsStringList");
/* 500 */       this.panel.setMessage("Loading application library...");
/* 501 */       c = clsLoader.loadClass(getParameter("MAINCLASS"));
/* 502 */       this.panel.setMessage("Libraries are successfully loaded, starting application.");
/*     */       
/* 504 */       if (c != null) {
/*     */         
/* 506 */         JApplet applet = (JApplet)c.newInstance();
/* 507 */         Method mtd = applet.getClass().getMethod("setParameters", new Class[] { String[].class });
/* 508 */         mtd.invoke(applet, new Object[] { getParameterArray() });
/*     */         
/* 510 */         Method mtd2 = applet.getClass().getMethod("setBaseApplet", new Class[] { JApplet.class });
/* 511 */         mtd2.invoke(applet, new Object[] { this });
/*     */ 
/*     */         
/*     */         try {
/* 515 */           Method mtd3 = applet.getClass().getMethod("setMainJar", new Class[] { String.class });
/* 516 */           mtd3.invoke(applet, new Object[] { getParameter("MAINJAR") });
/*     */         }
/* 518 */         catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 524 */           String docURI = getParameter("DOCUMENT_URI");
/* 525 */           if (docURI != null)
/*     */           {
/* 527 */             Method mtd3 = applet.getClass().getMethod("setDocumentURI", new Class[] { String.class });
/* 528 */             mtd3.invoke(applet, new Object[] { docURI });
/*     */           }
/*     */         
/* 531 */         } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 536 */         applet.init();
/* 537 */         if (this.m_Frame != null) {
/*     */           
/* 539 */           this.m_Frame.addWindowListener(ms_WindowListener);
/* 540 */           System.out.println("Window Listener:" + ms_WindowListener);
/*     */         } 
/*     */         
/* 543 */         setContentPane(applet);
/* 544 */         invalidate();
/* 545 */         validate();
/* 546 */         applet.invalidate();
/* 547 */         applet.validate();
/* 548 */         this.m_Ready = true;
/* 549 */         this.panel.stop();
/*     */         return;
/*     */       } 
/* 552 */       throw new Exception("The main class could not be retrieved!");
/*     */     }
/* 554 */     catch (Exception e) {
/*     */       
/* 556 */       e.printStackTrace();
/* 557 */       this.panel.stop();
/* 558 */       this.panel.setForeground(Color.RED);
/* 559 */       this.panel.setMessage("Unable to load the applet! : " + e.getMessage());
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object[] getTokenInfo(String urlWithToken) {
/* 565 */     if (urlWithToken != null) {
/*     */       
/* 567 */       String queryString = urlWithToken;
/* 568 */       String firmNr = getFirmParameter(queryString, "");
/* 569 */       String userId = getUserParameter(queryString, "");
/* 570 */       String token = getTokenParameter(queryString, "");
/* 571 */       Object[] info = { Integer.valueOf(userId), Integer.valueOf(firmNr), token };
/* 572 */       return info;
/*     */     } 
/* 574 */     return new Object[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getFreshPortParameter(String reqQuery, boolean defaultValue) {
/* 579 */     String param = null;
/* 580 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 581 */     if (parameters != null)
/* 582 */       param = parameters.get("freshport"); 
/* 583 */     if (param != null && param.length() > 0)
/* 584 */       return Boolean.parseBoolean(param); 
/* 585 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFirmParameter(String reqQuery, String defaultValue) {
/* 590 */     String firm = null;
/* 591 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 592 */     if (parameters != null)
/* 593 */       firm = parameters.get("firm"); 
/* 594 */     if (firm != null && firm.length() > 0) {
/* 595 */       return firm;
/*     */     }
/* 597 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getUserParameter(String reqQuery, String defaultValue) {
/* 602 */     String user = null;
/* 603 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 604 */     if (parameters != null)
/* 605 */       user = parameters.get("user"); 
/* 606 */     if (user != null && user.length() > 0) {
/* 607 */       return user;
/*     */     }
/* 609 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getLanguageParameter(String reqQuery, String defaultValue) {
/* 614 */     String lang = null;
/* 615 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 616 */     if (parameters != null)
/* 617 */       lang = parameters.get("lang"); 
/* 618 */     if (lang != null && lang.length() > 0)
/* 619 */       return lang; 
/* 620 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTokenParameter(String reqQuery, String defaultValue) {
/* 625 */     String token = null;
/* 626 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 627 */     if (parameters != null)
/* 628 */       token = parameters.get("token"); 
/* 629 */     if (token != null && token.length() > 0)
/* 630 */       return token; 
/* 631 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Hashtable<String, String> parseRequestParameters(String reqQuery) {
/* 636 */     Hashtable<String, String> map = new Hashtable<>();
/* 637 */     if (reqQuery != null) {
/*     */       
/* 639 */       String[] pars = split(reqQuery, "&", true);
/* 640 */       for (int i = 0; i < pars.length; i++) {
/*     */         
/* 642 */         int idx = pars[i].indexOf('=');
/* 643 */         if (idx > 0) {
/*     */           
/* 645 */           String name = pars[i].substring(0, idx);
/* 646 */           String value = null;
/* 647 */           if (idx + 1 < pars[i].length()) {
/* 648 */             value = pars[i].substring(idx + 1);
/*     */           } else {
/* 650 */             value = "";
/* 651 */           }  map.put(name, value);
/*     */         } else {
/*     */           
/* 654 */           map.put("lang", pars[i]);
/*     */         } 
/*     */       } 
/* 657 */     }  return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] split(String str, String delim, boolean trim) {
/* 668 */     StringTokenizer tok = new StringTokenizer(str, delim);
/*     */     
/* 670 */     int count = tok.countTokens();
/*     */     
/* 672 */     if (count == 0) {
/* 673 */       return null;
/*     */     }
/* 675 */     String[] res = new String[count];
/* 676 */     int i = 0;
/* 677 */     while (tok.hasMoreTokens()) {
/*     */       
/* 679 */       res[i] = tok.nextToken();
/* 680 */       if (trim)
/* 681 */         res[i] = res[i].trim(); 
/* 682 */       i++;
/*     */     } 
/*     */     
/* 685 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] getParameterArray() {
/* 690 */     boolean licencer = "true".equals(getParameter("LICENSEPACK"));
/* 691 */     (new String[45])[0] = "ROOTURI"; (new String[45])[1] = "SSO_KEY"; (new String[45])[2] = "LANGUAGE"; (new String[45])[3] = "LICENSEPACK"; (new String[45])[4] = "INI_LOGINIMAGE"; (new String[45])[5] = "INI_USE_DEFAULT_FIRM"; (new String[45])[6] = "INI_FIRM"; (new String[45])[7] = "INI_FIRMNAME"; (new String[45])[8] = "INI_PERIOD"; (new String[45])[9] = "INI_SUBCOMPANY"; (new String[45])[10] = "INI_LANG"; (new String[45])[11] = "INI_TITLE"; (new String[45])[12] = "INI_SHOWVERSION"; (new String[45])[13] = "INI_VERSION"; (new String[45])[14] = "SHOWTOOLBAR"; (new String[45])[15] = "SHOWLEFTTABPAGES"; (new String[45])[16] = "SHOWRIGHTTABPAGES"; (new String[45])[17] = "SHOWNEWSHTML"; (new String[45])[18] = "SHOWSTATUSBAR"; (new String[45])[19] = "SHOWCHANGEFIRMMENU"; (new String[45])[20] = "NEWSHTMLPATH"; (new String[45])[21] = "DISABLEDBUTTONS"; (new String[45])[22] = "SHOWCHANGEPASSWORDMENU"; (new String[45])[23] = "STARTUPFORM"; (new String[45])[24] = "STARTUPJAR"; (new String[45])[25] = "WEBAPPNAME"; (new String[45])[26] = "TERMINATEPAGE"; (new String[45])[27] = "USECACHE"; (new String[45])[28] = "USEOBJECTSCHEMACACHE"; (new String[45])[29] = "STARTCHRONOMETER"; (new String[45])[30] = "PRINTSYSTEMPROPERTIES"; (new String[45])[31] = "INI_FILENAME"; (new String[45])[32] = "INI_JARFILES"; (new String[45])[33] = "INI_MAINFORM"; (new String[45])[34] = "INI_WIDTH"; (new String[45])[35] = "INI_HEIGHT"; (new String[45])[36] = "EXT_USERNAME"; (new String[45])[37] = "EXT_PASSWORD"; (new String[45])[38] = "URLTOKEN"; (new String[45])[39] = "PSESID"; (new String[45])[40] = "RECORDSESSIONTO"; (new String[45])[41] = "ACCESS_TOKEN"; (new String[45])[42] = "TENANTID"; (new String[45])[43] = "FIRMNR"; (new String[45])[44] = "ELOGODEFTER"; (new String[44])[0] = "ROOTURI"; (new String[44])[1] = "SSO_KEY"; (new String[44])[2] = "LANGUAGE"; (new String[44])[3] = "INI_LOGINIMAGE"; (new String[44])[4] = "INI_USE_DEFAULT_FIRM"; (new String[44])[5] = "INI_FIRM"; (new String[44])[6] = "INI_FIRMNAME"; (new String[44])[7] = "INI_PERIOD"; (new String[44])[8] = "INI_SUBCOMPANY"; (new String[44])[9] = "INI_LANG"; (new String[44])[10] = "INI_TITLE"; (new String[44])[11] = "INI_VERSION"; (new String[44])[12] = "INI_SHOWVERSION"; (new String[44])[13] = "SHOWTOOLBAR"; (new String[44])[14] = "SHOWLEFTTABPAGES"; (new String[44])[15] = "SHOWRIGHTTABPAGES"; (new String[44])[16] = "SHOWNEWSHTML"; (new String[44])[17] = "SHOWSTATUSBAR"; (new String[44])[18] = "SHOWCHANGEFIRMMENU"; (new String[44])[19] = "NEWSHTMLPATH"; (new String[44])[20] = "DISABLEDBUTTONS"; (new String[44])[21] = "SHOWCHANGEPASSWORDMENU"; (new String[44])[22] = "STARTUPFORM"; (new String[44])[23] = "STARTUPJAR"; (new String[44])[24] = "WEBAPPNAME"; (new String[44])[25] = "TERMINATEPAGE"; (new String[44])[26] = "USECACHE"; (new String[44])[27] = "USEOBJECTSCHEMACACHE"; (new String[44])[28] = "STARTCHRONOMETER"; (new String[44])[29] = "PRINTSYSTEMPROPERTIES"; (new String[44])[30] = "INI_FILENAME"; (new String[44])[31] = "INI_JARFILES"; (new String[44])[32] = "INI_MAINFORM"; (new String[44])[33] = "INI_WIDTH"; (new String[44])[34] = "INI_HEIGHT"; (new String[44])[35] = "EXT_USERNAME"; (new String[44])[36] = "EXT_PASSWORD"; (new String[44])[37] = "URLTOKEN"; (new String[44])[38] = "PSESID"; (new String[44])[39] = "RECORDSESSIONTO"; (new String[44])[40] = "ACCESS_TOKEN"; (new String[44])[41] = "TENANTID"; (new String[44])[42] = "FIRMNR"; (new String[44])[43] = "ELOGODEFTER"; String[] paramNames = licencer ? new String[45] : new String[44];
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
/* 708 */     String launchParam = getParameter("USERNAME");
/* 709 */     if (launchParam != null && launchParam.length() > 0)
/*     */     {
/* 711 */       paramNames = new String[] { "ROOTURI", "SSO_KEY", "LANGUAGE", "USERNAME", "INI_LOGINIMAGE", "INI_USE_DEFAULT_FIRM", "INI_FIRM", "INI_FIRMNAME", "INI_PERIOD", "INI_SUBCOMPANY", "INI_LANG", "INI_TITLE", "INI_SHOWVERSION", "INI_VERSION", "SHOWTOOLBAR", "SHOWLEFTTABPAGES", "SHOWRIGHTTABPAGES", "SHOWNEWSHTML", "SHOWSTATUSBAR", "SHOWCHANGEFIRMMENU", "NEWSHTMLPATH", "DISABLEDBUTTONS", "SHOWCHANGEPASSWORDMENU", "STARTUPFORM", "STARTUPJAR", "WEBAPPNAME", "TERMINATEPAGE", "USECACHE", "USEOBJECTSCHEMACACHE", "STARTCHRONOMETER", "PRINTSYSTEMPROPERTIES", "INI_FILENAME", "INI_JARFILES", "INI_MAINFORM", "INI_WIDTH", "INI_HEIGHT", "EXT_USERNAME", "EXT_PASSWORD", "URLTOKEN", "PSESID", "RECORDSESSIONTO", "ACCESS_TOKEN", "TENANTID", "FIRMNR" };
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 719 */     String[] paramValues = new String[paramNames.length];
/* 720 */     for (int i = 0; i < paramNames.length; i++) {
/*     */       
/* 722 */       if (getParameter(paramNames[i]) == null) {
/*     */         
/* 724 */         System.out.println("No parameter for " + paramNames[i]);
/*     */       }
/*     */       else {
/*     */         
/* 728 */         System.out.println("Parameter for " + paramNames[i] + ": " + getParameter(paramNames[i]));
/* 729 */         StringBuilder buffer = new StringBuilder(paramNames[i]);
/* 730 */         buffer.append('=');
/* 731 */         buffer.append(getParameter(paramNames[i]));
/* 732 */         paramValues[i] = buffer.toString();
/*     */       } 
/* 734 */     }  return paramValues;
/*     */   }
/*     */   
/*     */   class JInitTask
/*     */     implements Runnable
/*     */   {
/*     */     Class c;
/*     */     
/*     */     public JInitTask(Class c) {
/* 743 */       this.c = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsURLListener getURLListener() {
/* 762 */     return ms_URLListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setURLListener(ILbsURLListener listener) {
/* 770 */     ms_URLListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSetDimension() {
/* 775 */     return this.m_SetDimension;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\JLbsStartup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */