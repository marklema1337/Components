/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.laf.mac.DesktopInternalFrameUI;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.dnd.DragSource;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.beans.PropertyVetoException;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ import javax.swing.event.InternalFrameListener;
/*     */ import javax.swing.plaf.InternalFrameUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsInternalFrame
/*     */   extends JInternalFrame
/*     */   implements InternalFrameListener, ILbsLogoutListener, DropTargetListener, ILbsComponentBase, ComponentListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private HashMap m_FormProps;
/*     */   private WeakReference m_SelfRef;
/*     */   private int m_UID;
/*     */   private String m_ResID;
/*     */   private int m_UIDWithMode;
/*     */   private ILbsLogoutListener m_LogoutListener;
/*     */   private int m_SnapShot;
/*     */   private Image m_FormImage;
/*     */   
/*     */   public JLbsInternalFrame() {
/*  60 */     addInternalFrameListener(this);
/*  61 */     addComponentListener(this);
/*  62 */     setOpaque(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFormProp(String key, Object value) {
/*  68 */     if (this.m_FormProps == null) {
/*  69 */       this.m_FormProps = new HashMap<>();
/*     */     }
/*  71 */     this.m_FormProps.put(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getFormProp(String key) {
/*  76 */     if (this.m_FormProps == null) {
/*  77 */       return null;
/*     */     }
/*  79 */     return this.m_FormProps.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUIDWithMode() {
/*  84 */     return this.m_UIDWithMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogoutListener(ILbsLogoutListener logoutListener) {
/*  89 */     this.m_LogoutListener = logoutListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifiers(int uID, String resID, int uIDWithMode) {
/*  94 */     this.m_UID = uID;
/*  95 */     this.m_ResID = resID;
/*  96 */     this.m_UIDWithMode = uIDWithMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEscapeCloseShortcut() {
/* 101 */     addShortcut(KeyStroke.getKeyStroke(27, 0, true), "CloseAction", new AbstractAction()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e) {
/* 107 */             JLbsInternalFrame.this.dispose();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void addShortcut(KeyStroke ks, String action, AbstractAction listener) {
/* 114 */     JRootPane rPane = getRootPane();
/* 115 */     rPane.getInputMap().put(ks, action);
/* 116 */     rPane.getActionMap().put(action, listener);
/* 117 */     rPane.registerKeyboardAction(listener, ks, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private Image getWindowIcon(Dimension dimension) {
/* 122 */     BufferedImage img = new BufferedImage(getWidth(), getHeight(), 2);
/* 123 */     Graphics g = img.getGraphics();
/* 124 */     g.setColor(getForeground());
/* 125 */     g.setFont(getFont());
/* 126 */     paintAll(g);
/* 127 */     return img.getScaledInstance(dimension.width, dimension.height, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameOpened(InternalFrameEvent e) {
/* 133 */     if (JLbsConstants.DESKTOP_MODE) {
/*     */       
/* 135 */       if (this.m_SelfRef == null)
/*     */       {
/* 137 */         this.m_SelfRef = new WeakReference<>(this);
/*     */       }
/* 139 */       JLbsOpenWindowListing.registerWindow(this.m_SelfRef);
/* 140 */       JLbsSwingUtilities.invokeLater(null, new Runnable()
/*     */           {
/*     */ 
/*     */             
/*     */             public void run()
/*     */             {
/* 146 */               JLbsInternalFrame.this.m_FormImage = JLbsInternalFrame.this.getWindowIcon(new Dimension(260, 260));
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameClosing(InternalFrameEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameClosed(InternalFrameEvent e) {
/* 160 */     if (JLbsConstants.CONTROLLER_GENERATION_ON)
/* 161 */       recordFrameClosed(); 
/* 162 */     JLbsOpenWindowListing.unregisterWindow(this.m_SelfRef);
/* 163 */     removeInternalFrameListener(this);
/* 164 */     dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   private void recordFrameClosed() {
/* 169 */     if (!JLbsStringUtil.isEmpty(getPureFormName())) {
/*     */       
/* 171 */       StringBuilder buffer = new StringBuilder();
/* 172 */       buffer.append("CONTAINER_CLOSED");
/* 173 */       buffer.append("|");
/* 174 */       buffer.append(getPureFormName());
/* 175 */       buffer.append("|");
/* 176 */       buffer.append(hashCode());
/* 177 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameIconified(InternalFrameEvent e) {
/*     */     try {
/* 186 */       setIcon(false);
/* 187 */       setVisible(false);
/*     */     }
/* 189 */     catch (PropertyVetoException e1) {
/*     */       
/* 191 */       e1.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameDeiconified(InternalFrameEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordFrameActivated() {
/* 204 */     if (!JLbsStringUtil.isEmpty(getPureFormName())) {
/*     */       
/* 206 */       StringBuilder buffer = new StringBuilder();
/* 207 */       buffer.append("ACTIVATE_CONTAINER");
/* 208 */       buffer.append("|");
/* 209 */       buffer.append(getPureFormName());
/* 210 */       buffer.append("|");
/* 211 */       buffer.append(hashCode());
/* 212 */       if (JLbsConstants.EMULATING_CONTROLLER_GENERATION_ON) {
/*     */         
/* 214 */         Object xuiPane = this.m_FormProps.get("Content");
/* 215 */         JLbsEventRecorderHelper.addXUIPane(xuiPane, false);
/*     */       } 
/* 217 */       JLbsEventRecorderHelper.addRecordItem(this, buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameActivated(InternalFrameEvent e) {
/* 224 */     JLbsSwingUtilities.invokeLater(null, new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/* 229 */             JLbsInternalFrame.this.recordFrameActivated();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalFrameDeactivated(InternalFrameEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 244 */     setUI((InternalFrameUI)new DesktopInternalFrameUI(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowClosingOnLogout() {
/* 250 */     if (this.m_LogoutListener != null) {
/* 251 */       this.m_LogoutListener.windowClosingOnLogout();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void dragEnter(DropTargetDragEvent dtde) {
/* 257 */     setCursor(DragSource.DefaultMoveNoDrop);
/* 258 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragOver(DropTargetDragEvent dtde) {
/* 264 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {
/* 270 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragExit(DropTargetEvent dte) {
/* 276 */     setCursor(Cursor.getDefaultCursor());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drop(DropTargetDropEvent dtde) {
/* 282 */     dtde.rejectDrop();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormProps(HashMap formProps) {
/* 287 */     this.m_FormProps = formProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap getFormProps() {
/* 292 */     return this.m_FormProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 297 */     return this.m_ResID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 302 */     return this.m_UID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 307 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormName() {
/* 312 */     Object obj = getFormProp("File_Name");
/* 313 */     if (obj == null) {
/* 314 */       return "";
/*     */     }
/* 316 */     return (String)obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPureFormName() {
/* 321 */     Object obj = getFormProp("Form_Name");
/* 322 */     if (obj == null) {
/* 323 */       return "";
/*     */     }
/* 325 */     return (String)obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 336 */     this.m_SnapShot++;
/* 337 */     if (this.m_SnapShot == 2 && JLbsComponentHelper.canTakeScreenShot()) {
/*     */       
/* 339 */       final Container window = this;
/* 340 */       final JLbsSwingUtilities.SwingTimer timer = new JLbsSwingUtilities.SwingTimer();
/* 341 */       timer.schedule(1000, new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e)
/*     */             {
/* 345 */               timer.stop();
/* 346 */               JLbsComponentHelper.takeScreenShot(window);
/*     */             }
/*     */           });
/*     */       
/* 350 */       timer.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 375 */     if (getParent() != null)
/* 376 */       getParent().requestFocus(); 
/* 377 */     super.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public Image getFormImage() {
/* 382 */     return this.m_FormImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormImage(Image formImage) {
/* 387 */     this.m_FormImage = formImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fireFrameActivated() {
/* 392 */     fireInternalFrameEvent(25554);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fireFrameDeactivated() {
/* 397 */     fireInternalFrameEvent(25555);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsInternalFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */