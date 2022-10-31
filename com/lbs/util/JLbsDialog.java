/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.ILbsComponentHelper;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.interfaces.ILbsFormRecordingEvents;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.KeyStroke;
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
/*     */ public class JLbsDialog
/*     */   extends JDialog
/*     */   implements WindowListener, ComponentListener, ILbsComponentBase, ILbsFormRecordingEvents, ILbsLogoutListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static WindowListener ms_GlobalWindowListener;
/*     */   private WeakReference m_SelfRef;
/*     */   private Rectangle m_SaveBounds;
/*     */   private HashMap m_FormProps;
/*     */   private int m_UID;
/*     */   private String m_ResID;
/*     */   private int m_UIDWithMode;
/*  59 */   private static JLbsFrame m_DummyFrame = null;
/*     */   
/*     */   private int m_PreviousWidth;
/*     */   
/*     */   private int m_PreviousHeight;
/*     */   
/*     */   private int m_Width;
/*     */   private int m_Height;
/*     */   private boolean m_Activated = false;
/*  68 */   private int m_SnapShot = 0;
/*     */   
/*     */   private ILbsLogoutListener m_LogoutListener;
/*     */ 
/*     */   
/*     */   public JLbsDialog() {
/*  74 */     super(createDummy());
/*  75 */     addWindowListener(this);
/*  76 */     addComponentListener(this);
/*  77 */     setDefaultCloseOperation(2);
/*  78 */     this.m_ResID = getClass().getName();
/*  79 */     if (JLbsConstants.DESKTOP_MODE) {
/*     */ 
/*     */ 
/*     */       
/*  83 */       setUndecorated(true);
/*  84 */       setBackground(new Color(0, 0, 0, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static JLbsFrame createDummy() {
/*  90 */     if (m_DummyFrame == null)
/*  91 */       m_DummyFrame = new JLbsFrame(); 
/*  92 */     return m_DummyFrame;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDialog(Window parent) {
/*  99 */     super((parent == null) ? createDummy() : parent);
/* 100 */     addWindowListener(this);
/* 101 */     addComponentListener(this);
/* 102 */     setDefaultCloseOperation(2);
/* 103 */     this.m_ResID = getClass().getName();
/* 104 */     if (JLbsConstants.DESKTOP_MODE) {
/*     */ 
/*     */ 
/*     */       
/* 108 */       setUndecorated(true);
/*     */       
/* 110 */       GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
/* 111 */         .getDefaultConfiguration();
/* 112 */       if (gc.isTranslucencyCapable()) {
/* 113 */         setBackground(new Color(0, 0, 0, 0));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLogoutListener(ILbsLogoutListener logoutListener) {
/* 119 */     this.m_LogoutListener = logoutListener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void show() {
/* 125 */     SwingUtilities.updateComponentTreeUI(this);
/* 126 */     super.show();
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableEventsEx(long eventToEnable) {
/* 131 */     enableEvents(eventToEnable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 139 */     super.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowOpened(WindowEvent arg0) {
/* 144 */     registerWindow();
/* 145 */     SwingUtilities.updateComponentTreeUI(this);
/* 146 */     if (ms_GlobalWindowListener != null) {
/* 147 */       ms_GlobalWindowListener.windowOpened(arg0);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean shouldRegisterWindow() {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void registerWindow() {
/* 157 */     if (this.m_SelfRef == null)
/*     */     {
/* 159 */       this.m_SelfRef = new WeakReference<>(this);
/*     */     }
/* 161 */     if (shouldRegisterWindow()) {
/* 162 */       JLbsOpenWindowListing.registerWindow(this.m_SelfRef);
/*     */     }
/*     */   }
/*     */   
/*     */   public void windowClosed(WindowEvent arg0) {
/* 167 */     if (JLbsConstants.CONTROLLER_GENERATION_ON)
/* 168 */       recordWindowClosed(arg0); 
/* 169 */     JLbsOpenWindowListing.unregisterWindow(this.m_SelfRef);
/* 170 */     removeWindowListener(this);
/* 171 */     if (ms_GlobalWindowListener != null) {
/* 172 */       ms_GlobalWindowListener.windowClosed(arg0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void windowClosing(WindowEvent arg0) {
/* 177 */     recordWindowClosing(arg0);
/* 178 */     if (ms_GlobalWindowListener != null) {
/* 179 */       ms_GlobalWindowListener.windowClosing(arg0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowDeactivated(WindowEvent arg0) {
/* 185 */     this.m_Activated = false;
/* 186 */     setHandlerContainer((Object)null);
/*     */     
/* 188 */     if (ms_GlobalWindowListener != null) {
/* 189 */       ms_GlobalWindowListener.windowDeactivated(arg0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowDeiconified(WindowEvent arg0) {
/* 196 */     if (ms_GlobalWindowListener != null) {
/* 197 */       ms_GlobalWindowListener.windowDeiconified(arg0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void windowIconified(WindowEvent arg0) {
/* 202 */     if (ms_GlobalWindowListener != null) {
/* 203 */       ms_GlobalWindowListener.windowIconified(arg0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void windowActivated(WindowEvent arg0) {
/* 208 */     this.m_Activated = true;
/* 209 */     setHandlerContainer(this);
/*     */     
/* 211 */     if (!(this instanceof com.lbs.recording.interfaces.ILbsMessageDlgRecordingEvents)) {
/* 212 */       recordWindowActivated(arg0);
/*     */     }
/* 214 */     if (ms_GlobalWindowListener != null) {
/* 215 */       ms_GlobalWindowListener.windowActivated(arg0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEscapeCloseShortcut() {
/* 221 */     addShortcut(KeyStroke.getKeyStroke(27, 0, true), "CloseAction", new AbstractAction()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e) {
/* 227 */             JLbsDialog.this.dispose();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMaximizeMinimizeShortcut() {
/* 234 */     addShortcut(KeyStroke.getKeyStroke(122, 2, true), "MinMaxAction", new AbstractAction()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e) {
/* 240 */             JLbsDialog.this.doAdjustSize();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doLayoutInternal() {
/* 247 */     doLayout();
/* 248 */     Container cont = getContentPane();
/* 249 */     if (cont != null) {
/*     */       
/* 251 */       cont.doLayout();
/* 252 */       SwingUtilities.updateComponentTreeUI(cont);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addShortcut(KeyStroke ks, String action, AbstractAction listener) {
/* 258 */     JRootPane rPane = getRootPane();
/* 259 */     rPane.getInputMap().put(ks, action);
/* 260 */     rPane.getActionMap().put(action, listener);
/* 261 */     rPane.registerKeyboardAction(listener, ks, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocationWithScreenCheck(Rectangle bounds) {
/* 266 */     if (bounds != null) {
/* 267 */       setLocation(Math.max(0, bounds.x + bounds.width - getWidth()), 
/* 268 */           Math.min(bounds.y + bounds.height, (Toolkit.getDefaultToolkit().getScreenSize()).height - getHeight() - 32));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLocationWithScreenCheck(Point p) {
/* 273 */     if (p != null) {
/*     */       
/* 275 */       Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 276 */       int right = p.x + getWidth();
/* 277 */       if (right > scrSize.width)
/* 278 */         right = scrSize.width; 
/* 279 */       setLocation(Math.max(0, right - getWidth()), Math.min(p.y, scrSize.height - getHeight() - 32));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void show(int x, int y) {
/* 285 */     setLocationWithScreenCheck(new Point(x, y));
/* 286 */     show();
/*     */   }
/*     */ 
/*     */   
/*     */   public void centerScreen() {
/* 291 */     Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 292 */     int x = (scrSize.width - getWidth()) / 2;
/* 293 */     int y = (scrSize.height - getHeight()) / 2;
/* 294 */     setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doAdjustSize() {
/* 299 */     if (this.m_SaveBounds == null) {
/* 300 */       maximize(1.0D);
/*     */     } else {
/* 302 */       restore();
/* 303 */     }  invalidate();
/* 304 */     validate();
/* 305 */     doLayoutInternal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doAdjustSize(Dimension d) {
/* 310 */     if (this.m_SaveBounds == null) {
/*     */       
/* 312 */       this.m_SaveBounds = getBounds();
/* 313 */       setSize(d);
/* 314 */       setLocation(0, 0);
/*     */     } else {
/*     */       
/* 317 */       restore();
/* 318 */     }  invalidate();
/* 319 */     validate();
/* 320 */     doLayoutInternal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void restore() {
/* 325 */     if (this.m_SaveBounds == null)
/*     */       return; 
/* 327 */     setBounds(this.m_SaveBounds);
/* 328 */     this.m_SaveBounds = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximize(double factor) {
/* 333 */     this.m_SaveBounds = getBounds();
/*     */     
/* 335 */     GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 336 */     Rectangle bounds = env.getMaximumWindowBounds();
/*     */     
/* 338 */     Dimension scrSize = new Dimension(bounds.width, bounds.height);
/* 339 */     scrSize.width = (int)(scrSize.width * factor);
/* 340 */     scrSize.height = (int)(scrSize.height * factor);
/* 341 */     setSize(scrSize);
/* 342 */     setLocation(0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isMaximized() {
/* 347 */     return (this.m_SaveBounds != null) ? Boolean.valueOf(true) : Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static WindowListener getGlobalWindowListener() {
/* 352 */     return ms_GlobalWindowListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setGlobalWindowListener(WindowListener globalWindowListener) {
/* 357 */     ms_GlobalWindowListener = globalWindowListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFormProp(String key, Object value) {
/* 362 */     if (this.m_FormProps == null) {
/* 363 */       this.m_FormProps = new HashMap<>();
/*     */     }
/* 365 */     this.m_FormProps.put(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getFormProp(String key) {
/* 370 */     if (this.m_FormProps == null) {
/* 371 */       return null;
/*     */     }
/* 373 */     return this.m_FormProps.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormName() {
/* 378 */     Object obj = getFormProp("File_Name");
/* 379 */     if (obj == null) {
/* 380 */       return "";
/*     */     }
/* 382 */     return (String)obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPureFormName() {
/* 387 */     Object obj = getFormProp("Form_Name");
/* 388 */     if (obj == null) {
/* 389 */       return "";
/*     */     }
/* 391 */     return (String)obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFormProp(String key) {
/* 396 */     if (this.m_FormProps == null) {
/*     */       return;
/*     */     }
/* 399 */     this.m_FormProps.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormProps(HashMap formProps) {
/* 404 */     this.m_FormProps = formProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap getFormProps() {
/* 409 */     return this.m_FormProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 414 */     return this.m_ResID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 419 */     return this.m_UID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifiers(int uID, String resID, int uIDWithMode) {
/* 424 */     this.m_UID = uID;
/* 425 */     this.m_ResID = resID;
/* 426 */     this.m_UIDWithMode = uIDWithMode;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setHandlerContainer(Object currentContainer) {
/* 431 */     ILbsComponentHelper helper = JLbsComponentHelper.getHandler();
/* 432 */     if (helper != null) {
/* 433 */       helper.setCurrentContainer(currentContainer);
/*     */     }
/*     */   }
/*     */   
/*     */   public void recordWindowActivated(WindowEvent arg0) {
/* 438 */     if (getUniqueIdentifier() != 0 || getUIDWithMode() != 0) {
/*     */       
/* 440 */       StringBuilder buffer = new StringBuilder();
/* 441 */       buffer.append("ACTIVATE_CONTAINER");
/* 442 */       buffer.append("|");
/*     */       
/* 444 */       String formName = getPureFormName();
/* 445 */       if (!JLbsStringUtil.isEmpty(formName)) {
/* 446 */         buffer.append(formName);
/*     */       } else {
/* 448 */         buffer.append(getUniqueIdentifier());
/*     */       } 
/* 450 */       buffer.append("|");
/* 451 */       buffer.append(hashCode());
/* 452 */       if (JLbsConstants.EMULATING_CONTROLLER_GENERATION_ON) {
/*     */         
/* 454 */         Object xuiPane = this.m_FormProps.get("Content");
/* 455 */         JLbsEventRecorderHelper.addXUIPane(xuiPane, true);
/*     */       } 
/* 457 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowClosed(WindowEvent arg0) {
/* 463 */     if (canRecordWindowEvent(arg0)) {
/*     */       
/* 465 */       StringBuilder buffer = new StringBuilder();
/* 466 */       buffer.append("CONTAINER_CLOSED");
/* 467 */       buffer.append("|");
/*     */       
/* 469 */       String formName = getPureFormName();
/* 470 */       if (!JLbsStringUtil.isEmpty(formName)) {
/* 471 */         buffer.append(formName);
/*     */       } else {
/* 473 */         buffer.append(getUniqueIdentifier());
/*     */       } 
/* 475 */       buffer.append("|");
/* 476 */       buffer.append(hashCode());
/*     */       
/* 478 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowClosing(WindowEvent arg0) {
/* 484 */     if (canRecordWindowEvent(arg0)) {
/*     */       
/* 486 */       StringBuilder buffer = new StringBuilder();
/* 487 */       buffer.append("CLOSE_CONTAINER");
/* 488 */       buffer.append("|");
/*     */       
/* 490 */       String formName = getPureFormName();
/* 491 */       if (!JLbsStringUtil.isEmpty(formName)) {
/* 492 */         buffer.append(formName);
/*     */       } else {
/* 494 */         buffer.append(getUniqueIdentifier());
/*     */       } 
/* 496 */       buffer.append("|");
/* 497 */       buffer.append(hashCode());
/*     */       
/* 499 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowResized() {
/* 505 */     if (getUniqueIdentifier() != 0 && this.m_Activated && (this.m_Width != this.m_PreviousWidth || this.m_Height != this.m_PreviousHeight)) {
/*     */       
/* 507 */       StringBuilder buffer = new StringBuilder();
/* 508 */       buffer.append("RESIZE_CONTAINER");
/* 509 */       buffer.append("|");
/* 510 */       buffer.append(getPureFormName());
/* 511 */       buffer.append("|");
/*     */       
/* 513 */       buffer.append(this.m_Width);
/* 514 */       buffer.append("|");
/*     */       
/* 516 */       buffer.append(this.m_Height);
/* 517 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canRecordWindowEvent(WindowEvent arg0) {
/* 523 */     if (getUniqueIdentifier() != 0) {
/* 524 */       return true;
/*     */     }
/* 526 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {}
/*     */ 
/*     */   
/*     */   public int getUIDWithMode() {
/* 547 */     return this.m_UIDWithMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPureTitle() {
/* 552 */     String title = getTitle();
/*     */     
/* 554 */     if (title == null) {
/* 555 */       return null;
/*     */     }
/* 557 */     int debugStart = title.indexOf(" [");
/*     */     
/* 559 */     if (debugStart == -1) {
/* 560 */       debugStart = title.indexOf(" <");
/*     */     }
/* 562 */     if (debugStart != -1) {
/* 563 */       title = title.substring(0, debugStart);
/*     */     }
/* 565 */     return title;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 570 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 583 */     this.m_PreviousWidth = this.m_Width;
/* 584 */     this.m_PreviousHeight = this.m_Height;
/* 585 */     this.m_Width = getWidth();
/* 586 */     this.m_Height = getHeight();
/* 587 */     recordWindowResized();
/* 588 */     this.m_SnapShot++;
/* 589 */     if (this.m_SnapShot == 2 && JLbsComponentHelper.canTakeScreenShot()) {
/*     */       
/* 591 */       final Window window = this;
/* 592 */       final JLbsSwingUtilities.SwingTimer timer = new JLbsSwingUtilities.SwingTimer();
/* 593 */       timer.schedule(1000, new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e)
/*     */             {
/* 597 */               timer.stop();
/* 598 */               JLbsComponentHelper.takeScreenShot(window);
/*     */             }
/*     */           });
/*     */       
/* 602 */       timer.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowClosingOnLogout() {
/* 614 */     if (this.m_LogoutListener != null)
/* 615 */       this.m_LogoutListener.windowClosingOnLogout(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */