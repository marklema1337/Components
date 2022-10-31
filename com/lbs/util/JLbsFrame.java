/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.interfaces.ILbsFormRecordingEvents;
/*     */ import java.awt.Dimension;
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
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JFrame;
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
/*     */ public class JLbsFrame
/*     */   extends JFrame
/*     */   implements WindowListener, ComponentListener, ILbsComponentBase, ILbsFormRecordingEvents, ILbsLogoutListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private WeakReference m_SelfRef;
/*     */   private boolean m_SkipRegisterForm;
/*     */   private HashMap m_FormProps;
/*     */   private int m_UID;
/*     */   private String m_ResID;
/*     */   private int m_UIDWithMode;
/*  55 */   public static ImageIcon ms_ApplicationIcon = null;
/*  56 */   public static ImageIcon ms_ApplicationIconDesktop = null;
/*     */   
/*     */   private int m_PreviousWidth;
/*     */   
/*     */   private int m_PreviousHeight;
/*     */   
/*     */   private int m_Width;
/*     */   
/*     */   private int m_Height;
/*     */   
/*     */   private boolean m_Activated = false;
/*     */   
/*     */   private int m_SnapShot;
/*     */   
/*     */   private ILbsLogoutListener m_LogoutListener;
/*     */   private static final String ms_IconName = "LbsApplication.png";
/*     */   private static final String ms_IconNameDesktop = "LbsApplicationDesktop.png";
/*     */   private static final String ms_IconNameDesktop3 = "LbsApplicationDesktop3.png";
/*     */   private static final String ms_IconNameHR = "jHR_favicon.png";
/*     */   private static final String ms_IconNameSaasErp = "LbsApplicationDesktopSaasErp.png";
/*     */   
/*     */   static {
/*  78 */     if ("0601".equalsIgnoreCase(JLbsConstants.getProductType())) {
/*     */       
/*  80 */       ms_ApplicationIconDesktop = getImageIcon("LbsApplicationDesktopSaasErp.png");
/*  81 */       ms_ApplicationIcon = getImageIcon("LbsApplicationDesktopSaasErp.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       ms_ApplicationIcon = getImageIcon("LbsApplication.png");
/* 101 */       if (JLbsConstants.DESKTOP_MODE) {
/* 102 */         ms_ApplicationIconDesktop = getImageIcon("LbsApplicationDesktop3.png");
/*     */       } else {
/* 104 */         ms_ApplicationIconDesktop = getImageIcon("LbsApplicationDesktop.png");
/* 105 */       }  if (JLbsConstants.HR) {
/* 106 */         ms_ApplicationIcon = ms_ApplicationIconDesktop = getImageIcon("jHR_favicon.png");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ImageIcon getImageIcon(String name) {
/* 112 */     return JLbsControlHelper.getImageIcon(JLbsFrame.class, name);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsFrame() {
/* 117 */     addWindowListener(this);
/* 118 */     addComponentListener(this);
/* 119 */     if (ms_ApplicationIconDesktop != null) {
/* 120 */       setIconImage(ms_ApplicationIconDesktop.getImage());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsFrame(String title) {
/* 127 */     super(title);
/* 128 */     addWindowListener(this);
/* 129 */     addComponentListener(this);
/* 130 */     if (ms_ApplicationIconDesktop != null) {
/* 131 */       setIconImage(ms_ApplicationIconDesktop.getImage());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLogoutListener(ILbsLogoutListener logoutListener) {
/* 136 */     this.m_LogoutListener = logoutListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableEventsEx(long eventToEnable) {
/* 141 */     enableEvents(eventToEnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowOpened(WindowEvent arg0) {
/* 146 */     this.m_SelfRef = new WeakReference<>(this);
/* 147 */     if (!this.m_SkipRegisterForm)
/* 148 */       JLbsOpenWindowListing.registerWindow(this.m_SelfRef); 
/* 149 */     SwingUtilities.updateComponentTreeUI(this);
/* 150 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/* 156 */             JLbsFrame.this.toFront();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowClosed(WindowEvent arg0) {
/* 163 */     recordWindowClosed(arg0);
/* 164 */     JLbsOpenWindowListing.unregisterWindow(this.m_SelfRef);
/* 165 */     removeWindowListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowClosing(WindowEvent arg0) {
/* 170 */     recordWindowClosing(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowDeactivated(WindowEvent arg0) {
/* 175 */     this.m_Activated = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowDeiconified(WindowEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowIconified(WindowEvent arg0) {}
/*     */ 
/*     */   
/*     */   public void windowActivated(WindowEvent arg0) {
/* 188 */     this.m_Activated = true;
/* 189 */     recordWindowActivated(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEscapeCloseShortcut() {
/* 194 */     KeyStroke ks = KeyStroke.getKeyStroke(27, 0, true);
/* 195 */     getRootPane().getInputMap().put(ks, "CloseAction");
/* 196 */     getRootPane().getActionMap().put("CloseAction", new AbstractAction()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e) {
/* 202 */             JLbsFrame.this.dispose();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void addShortcut(KeyStroke ks, String action, AbstractAction listener) {
/* 209 */     JRootPane rPane = getRootPane();
/* 210 */     rPane.getInputMap().put(ks, action);
/* 211 */     rPane.getActionMap().put(action, listener);
/* 212 */     rPane.registerKeyboardAction(listener, ks, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocationWithScreenCheck(Rectangle bounds) {
/* 217 */     if (bounds != null) {
/* 218 */       setLocation(Math.max(0, bounds.x + bounds.width - getWidth()), 
/* 219 */           Math.min(bounds.y + bounds.height, (Toolkit.getDefaultToolkit().getScreenSize()).height - getHeight() - 32));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLocationWithScreenCheck(Point p) {
/* 224 */     if (p != null) {
/*     */       
/* 226 */       Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 227 */       int right = p.x + getWidth();
/* 228 */       if (right > scrSize.width)
/* 229 */         right = scrSize.width; 
/* 230 */       setLocation(Math.max(0, right - getWidth()), Math.min(p.y, scrSize.height - getHeight() - 32));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void show(int x, int y) {
/* 236 */     setLocationWithScreenCheck(new Point(x, y));
/* 237 */     show();
/*     */   }
/*     */ 
/*     */   
/*     */   public void centerScreen() {
/* 242 */     Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 243 */     int x = (scrSize.width - getWidth()) / 2;
/* 244 */     int y = (scrSize.height - getHeight()) / 2;
/* 245 */     setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkipRegisterForm() {
/* 250 */     return this.m_SkipRegisterForm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkipRegisterForm(boolean b) {
/* 255 */     this.m_SkipRegisterForm = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFormProp(String key, Object value) {
/* 260 */     if (this.m_FormProps == null) {
/* 261 */       this.m_FormProps = new HashMap<>();
/*     */     }
/* 263 */     this.m_FormProps.put(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getFormProp(String key) {
/* 268 */     if (this.m_FormProps == null) {
/* 269 */       return null;
/*     */     }
/* 271 */     return this.m_FormProps.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormName() {
/* 276 */     Object obj = getFormProp("File_Name");
/* 277 */     if (obj == null) {
/* 278 */       return "";
/*     */     }
/* 280 */     return (String)obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPureFormName() {
/* 285 */     Object obj = getFormProp("Form_Name");
/* 286 */     if (obj == null) {
/* 287 */       return "";
/*     */     }
/* 289 */     return (String)obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFormProp(String key) {
/* 294 */     if (this.m_FormProps == null) {
/*     */       return;
/*     */     }
/* 297 */     this.m_FormProps.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormProps(HashMap formProps) {
/* 302 */     this.m_FormProps = formProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap getFormProps() {
/* 307 */     return this.m_FormProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 312 */     return this.m_ResID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 317 */     return this.m_UID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifiers(int uID, String resID, int uIDWithMode) {
/* 322 */     this.m_UID = uID;
/* 323 */     this.m_ResID = resID;
/* 324 */     this.m_UIDWithMode = uIDWithMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowActivated(WindowEvent arg0) {
/* 329 */     if (getUniqueIdentifier() != 0 || getUIDWithMode() != 0) {
/*     */       
/* 331 */       StringBuilder buffer = new StringBuilder();
/* 332 */       buffer.append("ACTIVATE_CONTAINER");
/* 333 */       buffer.append("|");
/* 334 */       buffer.append(getPureFormName());
/* 335 */       buffer.append("|");
/* 336 */       buffer.append(hashCode());
/* 337 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowClosed(WindowEvent arg0) {
/* 343 */     if (getUniqueIdentifier() != 0) {
/*     */       
/* 345 */       StringBuilder buffer = new StringBuilder();
/* 346 */       buffer.append("CONTAINER_CLOSED");
/* 347 */       buffer.append("|");
/* 348 */       buffer.append(getPureFormName());
/* 349 */       buffer.append("|");
/* 350 */       buffer.append(hashCode());
/* 351 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowClosing(WindowEvent arg0) {
/* 357 */     if (getUniqueIdentifier() != 0) {
/*     */       
/* 359 */       StringBuilder buffer = new StringBuilder();
/* 360 */       buffer.append("CLOSE_CONTAINER");
/* 361 */       buffer.append("|");
/* 362 */       buffer.append(getPureFormName());
/* 363 */       buffer.append("|");
/* 364 */       buffer.append(hashCode());
/* 365 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordWindowResized() {
/* 371 */     if (getUniqueIdentifier() != 0 && this.m_Activated && (this.m_Width != this.m_PreviousWidth || this.m_Height != this.m_PreviousHeight)) {
/*     */       
/* 373 */       StringBuilder buffer = new StringBuilder();
/* 374 */       buffer.append("RESIZE_CONTAINER");
/* 375 */       buffer.append("|");
/* 376 */       buffer.append(getPureFormName());
/* 377 */       buffer.append("|");
/*     */       
/* 379 */       buffer.append(this.m_Width);
/* 380 */       buffer.append("|");
/*     */       
/* 382 */       buffer.append(this.m_Height);
/* 383 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
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
/* 405 */     return this.m_UIDWithMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPureTitle() {
/* 410 */     String title = getTitle();
/*     */     
/* 412 */     int debugStart = title.indexOf(" [");
/*     */     
/* 414 */     if (debugStart == -1) {
/* 415 */       debugStart = title.indexOf(" <");
/*     */     }
/* 417 */     if (debugStart != -1) {
/* 418 */       title = title.substring(0, debugStart);
/*     */     }
/* 420 */     return title;
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
/* 433 */     this.m_PreviousWidth = this.m_Width;
/* 434 */     this.m_PreviousHeight = this.m_Height;
/* 435 */     this.m_Width = getWidth();
/* 436 */     this.m_Height = getHeight();
/* 437 */     recordWindowResized();
/* 438 */     this.m_SnapShot++;
/* 439 */     if (this.m_SnapShot == 2 && JLbsComponentHelper.canTakeScreenShot()) {
/*     */       
/* 441 */       final Window window = this;
/* 442 */       final JLbsSwingUtilities.SwingTimer timer = new JLbsSwingUtilities.SwingTimer();
/* 443 */       timer.schedule(1000, new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e)
/*     */             {
/* 447 */               timer.stop();
/* 448 */               JLbsComponentHelper.takeScreenShot(window);
/*     */             }
/*     */           });
/*     */       
/* 452 */       timer.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 463 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getXUIPane() {
/* 468 */     return getFormProp("Content");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowClosingOnLogout() {
/* 474 */     if (this.m_LogoutListener != null)
/* 475 */       this.m_LogoutListener.windowClosingOnLogout(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */