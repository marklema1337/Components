/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsImageButton;
/*     */ import com.lbs.control.interfaces.ILbsMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.controls.menu.ILbsMenuFilter;
/*     */ import com.lbs.controls.menu.JLbsPopupMenu;
/*     */ import com.lbs.controls.menu.JLbsPopupMenuItem;
/*     */ import com.lbs.recording.interfaces.ILbsMenuButtonRecordingEvents;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.net.URL;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMenuButton
/*     */   extends JLbsPanel
/*     */   implements MouseListener, Action, ILbsMenuFilter, ILbsMenuButtonRecordingEvents, ILbsInternalMenuButton
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private LbsMenuButtonImplementor m_Implementor;
/*     */   
/*     */   public JLbsMenuButton() {
/*  54 */     setLayout(new BorderLayout());
/*     */     
/*  56 */     this.m_Implementor = new LbsMenuButtonImplementor(this);
/*  57 */     this.m_Implementor.init();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChild(ILbsComponent child) {
/*  63 */     add((Component)child);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getChildAt(int index) {
/*  68 */     return getComponent(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChild(ILbsComponent child) {
/*  73 */     remove((Component)child);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsImageButton createMainButton() {
/*  78 */     JLbsImageButton mainButton = new JLbsImageButton();
/*  79 */     if (JLbsImageButton.CUSTOM_BORDERS) {
/*     */       
/*  81 */       mainButton.setBorder(JLbsImageButton.m_normal);
/*     */       
/*  83 */       mainButton.setBorderPainted(false);
/*     */     } 
/*  85 */     add(mainButton, "Center");
/*  86 */     return mainButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsImageButton createPopupButton() {
/*  91 */     JLbsImageButton popupButton = new JLbsImageButton();
/*     */ 
/*     */     
/*  94 */     if (JLbsImageButton.CUSTOM_BORDERS) {
/*     */       
/*  96 */       popupButton.setBorder(JLbsImageButton.m_pressed);
/*  97 */       popupButton.setContentAreaFilled(false);
/*  98 */       popupButton.setBorderPainted(false);
/*     */     } 
/* 100 */     popupButton.setIcon(getSmallDownArrow());
/* 101 */     popupButton.setFocusPainted(false);
/* 102 */     add(popupButton, "East");
/* 103 */     return popupButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenu createPopupMenu() {
/* 108 */     JLbsPopupMenu popup = new JLbsPopupMenu();
/* 109 */     add((Component)popup);
/* 110 */     return (ILbsPopupMenu)popup;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPopupButton(ILbsImageButton button) {
/* 115 */     return this.m_Implementor.isPopupButton(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMenuButton(ImageIcon main, JLbsStringList actions) {
/* 120 */     this();
/*     */     
/* 122 */     setIcon(main);
/*     */     
/* 124 */     setActions(actions);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 129 */     super.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponentAt(int x, int y) {
/* 134 */     if (JLbsConstants.DESIGN_TIME)
/* 135 */       return null; 
/* 136 */     return super.getComponentAt(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIcon(Icon main) {
/* 141 */     this.m_Implementor.setIcon(main);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHighlightIcon(Icon main) {
/* 146 */     this.m_Implementor.setHighlightIcon(main);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reinitialize() {
/* 151 */     this.m_Implementor.reinitialize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon() {
/* 156 */     return this.m_Implementor.getIcon();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList actions, boolean clear) {
/* 161 */     this.m_Implementor.setActions(actions, clear);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList actions) {
/* 166 */     this.m_Implementor.setActions(actions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ImageIcon getSmallDownArrow() {
/*     */     try {
/* 173 */       String fileName = "/com/lbs/resource/SmallDnArw.png";
/* 174 */       URL url = getClass().getResource(fileName);
/* 175 */       ImageIcon img = new ImageIcon(url);
/* 176 */       return img;
/*     */     }
/* 178 */     catch (Exception exception) {
/*     */ 
/*     */ 
/*     */       
/* 182 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setMenuButtonListener(ILbsMenuButtonListener listener) {
/* 187 */     this.m_Implementor.setMenuButtonListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMenuButtonListener getMenuButtonListener() {
/* 192 */     return this.m_Implementor.getMenuButtonListener();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent evt) {
/* 204 */     if (evt.getSource() instanceof JButton)
/*     */     {
/* 206 */       if (JLbsImageButton.CUSTOM_BORDERS) {
/* 207 */         ((JButton)evt.getSource()).setBorder(JLbsImageButton.m_pressed);
/*     */       } else {
/* 209 */         ((JButton)evt.getSource()).getModel().setPressed(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent evt) {
/* 218 */     if (evt.getSource() instanceof JButton)
/*     */     {
/* 220 */       if (JLbsImageButton.CUSTOM_BORDERS) {
/* 221 */         ((JButton)evt.getSource()).getModel().setPressed(false);
/*     */       } else {
/* 223 */         ((JButton)evt.getSource()).setBorder(JLbsImageButton.m_normal);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected JLbsImageButton getMainButton() {
/* 229 */     return (JLbsImageButton)this.m_Implementor.m_mainButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent evt) {
/* 234 */     if (JLbsImageButton.CUSTOM_BORDERS) {
/*     */       
/* 236 */       getMainButton().setBorderPainted(true);
/* 237 */       getPopupButton().setBorderPainted(true);
/* 238 */       if (JLbsConstants.DESKTOP_MODE && getMainButton().getHighlightIcon() != null) {
/* 239 */         getMainButton().setSuperIcon(getMainButton().getHighlightIcon());
/*     */       }
/*     */     } else {
/*     */       
/* 243 */       getMainButton().getModel().setSelected(true);
/* 244 */       getPopupButton().getModel().setSelected(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent evt) {
/* 250 */     if (JLbsImageButton.CUSTOM_BORDERS) {
/*     */       
/* 252 */       getMainButton().setBorderPainted(false);
/* 253 */       getPopupButton().setBorderPainted(false);
/* 254 */       if (JLbsConstants.DESKTOP_MODE) {
/* 255 */         getMainButton().setSuperIcon(getMainButton().getNormalIcon());
/*     */       }
/*     */     } else {
/*     */       
/* 259 */       getMainButton().getModel().setSelected(false);
/* 260 */       getPopupButton().getModel().setSelected(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPerformAction(int actionID) {
/* 266 */     this.m_Implementor.doPerformAction(actionID);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doMainButtonClick() {
/* 271 */     this.m_Implementor.doMainButtonClick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doClick() {
/* 276 */     this.m_Implementor.doClick();
/*     */     
/* 278 */     Point p = getLocation();
/* 279 */     p.y += getHeight();
/* 280 */     Container parent = getParent();
/*     */     
/* 282 */     if (getComponentOrientation().isLeftToRight()) {
/*     */       
/* 284 */       getPopup().show(parent, p.x, p.y);
/*     */     }
/*     */     else {
/*     */       
/* 288 */       int popupWidth = (getPopup().getPreferredSize()).width;
/* 289 */       getPopup().show(parent, p.x - popupWidth + getWidth(), p.y);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/* 298 */     Object source = evt.getSource();
/* 299 */     if (source instanceof JLbsPopupMenuItem)
/*     */     {
/* 301 */       recordMenuButtonSelected(evt);
/*     */     }
/* 303 */     this.m_Implementor.actionPerformed(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int selectAvailableAction() {
/* 312 */     return this.m_Implementor.selectAvailableAction();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActionIndex() {
/* 322 */     return this.m_Implementor.getActionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionIndex(int index) {
/* 327 */     this.m_Implementor.setActionIndex(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemVisible(ILbsMenuItem item) {
/* 335 */     return this.m_Implementor.isItemVisible(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToolTipText(String hint) {
/* 343 */     super.setToolTipText(hint);
/*     */     
/* 345 */     this.m_Implementor.setToolTipText(hint);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 350 */     return this.m_Implementor.getToolTipText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemEnabled(ILbsMenuItem item) {
/* 358 */     return this.m_Implementor.isItemEnabled(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addKeyListener(KeyListener listener) {
/* 366 */     super.addKeyListener(listener);
/* 367 */     this.m_Implementor.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addMouseListener(MouseListener listener) {
/* 375 */     super.addMouseListener(listener);
/* 376 */     this.m_Implementor.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientPropertyInternal(Object property, Object value) {
/* 381 */     this.m_Implementor.putClientPropertyInternal(property, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 386 */     return this.m_Implementor.hasItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 391 */     return this.m_Implementor.isItemWTagEnabled(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 396 */     return this.m_Implementor.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsPopupMenu getPopup() {
/* 401 */     return (JLbsPopupMenu)getPopupControl();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenu getPopupControl() {
/* 406 */     if (this.m_Implementor != null)
/* 407 */       return this.m_Implementor.getPopupControl(); 
/* 408 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 413 */     final int fActionID = actionID;
/* 414 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 418 */             JLbsMenuButton.this.requestFocus();
/* 419 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
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
/*     */   public void recordMenuButtonSelected(ActionEvent evt) {
/* 440 */     if (evt == null) {
/*     */       return;
/*     */     }
/* 443 */     Object o = getClientProperty("EVENT_TAG");
/* 444 */     if (o instanceof Integer) {
/*     */       
/* 446 */       int nValue = ((Integer)o).intValue();
/* 447 */       if (nValue == -1002) {
/*     */         return;
/*     */       }
/*     */     } 
/* 451 */     StringBuilder buffer = new StringBuilder();
/* 452 */     buffer.append("MENU_BUTTON_ITEM_CLICK");
/* 453 */     buffer.append("|");
/* 454 */     buffer.append(((JLbsPopupMenuItem)evt.getSource()).getId());
/* 455 */     buffer.append("|");
/* 456 */     buffer.append(evt.getModifiers());
/*     */     
/* 458 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, buffer.toString(), 
/* 459 */         String.valueOf(((JLbsPopupMenuItem)evt.getSource()).getId()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doActionPerformed(ActionEvent evt, int actionID) {
/* 465 */     final ActionEvent event = evt;
/* 466 */     final int mActionID = actionID;
/* 467 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 471 */             if (event.getSource() instanceof JButton || event.getSource() instanceof JLbsPopupMenuItem) {
/*     */               
/* 473 */               ((JLbsPopupMenuItem)event.getSource()).paintAsSelected();
/* 474 */               JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */             } else {
/*     */               
/* 477 */               JLbsComponentHelper.statusChanged(1, mActionID, "Menu button can not be found");
/* 478 */             }  JLbsMenuButton.this.actionPerformed(event);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {}
/*     */ 
/*     */   
/*     */   public int getActionId() {
/* 489 */     return this.m_Implementor.getActionId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionId(int actionId) {
/* 494 */     this.m_Implementor.setActionId(actionId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 499 */     super.setComponentOrientation(o);
/*     */     
/* 501 */     if (o.isLeftToRight()) {
/*     */       
/* 503 */       remove(getPopupButton());
/* 504 */       add(getPopupButton(), "East");
/*     */     }
/*     */     else {
/*     */       
/* 508 */       remove(getPopupButton());
/* 509 */       add(getPopupButton(), "West");
/*     */     } 
/*     */     
/* 512 */     doLayout();
/* 513 */     getPopup().setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsImageButton getPopupButton() {
/* 518 */     return (JLbsImageButton)this.m_Implementor.m_popupButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 523 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 528 */     super.updateUI();
/*     */     
/* 530 */     if (getPopup() != null) {
/* 531 */       getPopup().updateUI();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object getValue(String key) {
/* 536 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putValue(String key, Object value) {}
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 545 */     this.m_Implementor.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 551 */     Dimension d = new Dimension();
/* 552 */     double maxHeight = 0.0D;
/* 553 */     double totalWidth = 0.0D;
/* 554 */     Component[] components = getComponents();
/* 555 */     for (int i = 0; i < components.length; i++) {
/*     */       
/* 557 */       if (!(components[i] instanceof ILbsPopupMenu)) {
/*     */         
/* 559 */         Component comp = components[i];
/* 560 */         Dimension bd = comp.getPreferredSize();
/* 561 */         if (bd.height > maxHeight)
/* 562 */           maxHeight = bd.height; 
/* 563 */         totalWidth += bd.width;
/*     */       } 
/*     */     } 
/* 566 */     d.setSize(totalWidth, maxHeight);
/* 567 */     return d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsMenuButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */