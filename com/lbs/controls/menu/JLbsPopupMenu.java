/*     */ package com.lbs.controls.menu;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsSubMenu;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.interfaces.ILbsPopupMenuRecordingEvents;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsPopupMenu
/*     */   extends JPopupMenu
/*     */   implements PopupMenuListener, ILbsInternalPopupMenu, ILbsPopupMenuRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static JLbsPopupMenu ms_ActivePopup;
/*     */   private LbsPopupMenuImplementor m_Implementor;
/*     */   private Object m_Parent;
/*     */   private boolean m_showAll;
/*  56 */   private ILbsComponent m_FocusOwner = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsPopupMenu() {
/*  63 */     addPopupMenuListener(this);
/*  64 */     this.m_Implementor = new LbsPopupMenuImplementor(this);
/*  65 */     this.m_Implementor.init();
/*  66 */     this.m_showAll = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenuItem createItem(ILbsPopupMenu rootMenu, String s, int id, int index) {
/*  71 */     return new JLbsPopupMenuItem(rootMenu, s, id, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsSubMenu createSubMenu(String s) {
/*  77 */     return new JlbsSubMenu(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object createSeparator() {
/*  82 */     return new JPopupMenu.Separator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object item) {
/*  87 */     if (item instanceof JComponent) {
/*  88 */       add((JComponent)item);
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
/*     */   public int add(String s, int id) {
/* 100 */     return this.m_Implementor.add(s, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int insert(int index, String s, int id) {
/* 105 */     return this.m_Implementor.insert(index, s, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSeparator() {
/* 110 */     this.m_Implementor.addSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/* 115 */     this.m_Implementor.addItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/* 120 */     return this.m_Implementor.getItemsSList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 125 */     this.m_Implementor.clearItems();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener actionListener) {
/* 134 */     this.m_Implementor.setActionListener(actionListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionListener getActionListener() {
/* 139 */     return this.m_Implementor.getActionListener();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemFilter(ILbsMenuFilter listener) {
/* 148 */     this.m_Implementor.setItemFilter(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
/* 163 */     removeAll();
/* 164 */     popupMenuWillBecomeVisible();
/*     */     
/* 166 */     Component invoker = getInvoker();
/* 167 */     recordPopupMenuWillBecomeVisible(arg0, invoker);
/*     */   }
/*     */ 
/*     */   
/*     */   public void popupMenuWillBecomeVisible() {
/* 172 */     popupMenuWillBecomeVisible(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void popupMenuWillBecomeVisible(boolean isWebMode) {
/* 178 */     this.m_Implementor.preparePopup();
/*     */     
/* 180 */     this.m_Implementor.m_ItemsAddedToParent = true;
/* 181 */     Component invoker = getInvoker();
/*     */     
/* 183 */     JLbsPopupEventQueue que = new JLbsPopupEventQueue(null, isWebMode);
/* 184 */     que.append2ExistingMenu(invoker, this);
/*     */     
/* 186 */     this.m_Implementor.m_ItemsAddedToParent = false;
/* 187 */     if (getParent() != null) {
/* 188 */       this.m_Parent = getParent();
/*     */     }
/*     */   }
/*     */   
/*     */   public void doPopupMenuWillBecomeVisible(PopupMenuEvent arg0, int actionID) {
/* 193 */     final PopupMenuEvent event = arg0;
/* 194 */     final int mActionID = actionID;
/* 195 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 199 */             JLbsPopupMenu.this.popupMenuWillBecomeVisible(event);
/* 200 */             JLbsPopupMenu.this.setVisible(true);
/* 201 */             if (JLbsPopupMenu.this.isVisible()) {
/* 202 */               JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */             } else {
/* 204 */               JLbsComponentHelper.statusChanged(1, mActionID, "Popup Menu can not be shown");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void doShow(Component invoker, int x, int y, int actionID) {
/* 211 */     final int fX = x;
/* 212 */     final int fY = y;
/* 213 */     final Component fInvoker = invoker;
/* 214 */     final int mActionID = actionID;
/* 215 */     if (fInvoker != null)
/* 216 */       fInvoker.requestFocusInWindow(); 
/* 217 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 221 */             JLbsPopupMenu.this.show(fInvoker, fX, fY);
/* 222 */             if (JLbsPopupMenu.this.isVisible()) {
/* 223 */               JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */             } else {
/* 225 */               JLbsComponentHelper.statusChanged(1, mActionID, "Popup Menu can not be shown");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void doSelectAll(Component invoker, int actionID) {
/* 232 */     final int mActionID = actionID;
/* 233 */     final Component mInvoker = invoker;
/*     */     
/* 235 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 239 */             JLbsComponentHelper.doSelectAll(mInvoker);
/* 240 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void doDeselectAll(Component invoker, int actionID) {
/* 247 */     final int mActionID = actionID;
/* 248 */     final Component mInvoker = invoker;
/*     */     
/* 250 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 254 */             JLbsComponentHelper.doDeselectAll(mInvoker);
/* 255 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void doInvertSelection(Component invoker, int actionID) {
/* 262 */     final int mActionID = actionID;
/* 263 */     final Component mInvoker = invoker;
/*     */     
/* 265 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 269 */             JLbsComponentHelper.doInvertSelection(mInvoker);
/* 270 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 277 */     return this.m_Implementor.hasItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 282 */     return this.m_Implementor.isItemWTagEnabled(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagVisible(int tag) {
/* 287 */     return this.m_Implementor.isItemWTagVisible(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMenuItemCount() {
/* 292 */     return this.m_Implementor.getMenuItemCount();
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList getItems() {
/* 297 */     if (this.m_Implementor != null)
/* 298 */       return this.m_Implementor.m_Items; 
/* 299 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getShowingItemList() {
/* 304 */     if (this.m_Implementor != null)
/* 305 */       return this.m_Implementor.getShowingItemsSList(); 
/* 306 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JMenuItem getMenuItem(int index) {
/* 311 */     return (index < getItems().size() && index >= 0) ? 
/* 312 */       getItems().get(index) : 
/* 313 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JMenuItem getMenuItemByTag(int tag) {
/* 318 */     for (int i = 0; i < getItems().size(); i++) {
/*     */       
/* 320 */       Object obj = getItems().get(i);
/* 321 */       if (obj instanceof JLbsPopupMenuItem) {
/*     */         
/* 323 */         JLbsPopupMenuItem item = (JLbsPopupMenuItem)obj;
/* 324 */         if (item.getId() == tag)
/* 325 */           return item; 
/*     */       } 
/*     */     } 
/* 328 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIcon(int index, Icon icon) {
/* 333 */     setIcon(index, icon, (KeyStroke)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIcon(int index, Icon icon, KeyStroke key) {
/* 338 */     JMenuItem item = getMenuItem(index);
/* 339 */     if (item != null) {
/*     */       
/* 341 */       item.setIcon(icon);
/* 342 */       if (key != null) {
/* 343 */         item.setAccelerator(key);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void selectItemByTag(int tag) {
/* 349 */     this.m_Implementor.selectItemByTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean b) {
/* 354 */     this.m_Implementor.setVisible(b);
/* 355 */     super.setVisible(b);
/* 356 */     setActivePopup(b ? 
/* 357 */         this : 
/* 358 */         null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsPopupMenu getActivePopup() {
/* 363 */     synchronized (JLbsPopupMenu.class) {
/*     */       
/* 365 */       return ms_ActivePopup;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setActivePopup(JLbsPopupMenu activePopup) {
/* 371 */     synchronized (JLbsPopupMenu.class) {
/*     */       
/* 373 */       ms_ActivePopup = activePopup;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void closeActivePopup() {
/*     */     final JLbsPopupMenu popup;
/* 380 */     synchronized (JLbsPopupMenu.class) {
/*     */       
/* 382 */       popup = ms_ActivePopup;
/*     */     } 
/* 384 */     if (popup != null) {
/* 385 */       JLbsSwingUtilities.invokeLater(null, new Runnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 389 */               popup.setVisible(false);
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   public String getResourceIdentifier() {
/* 396 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 401 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordPopupMenuWillBecomeVisible(PopupMenuEvent arg0, Component invoker) {
/* 407 */     if (invoker instanceof com.lbs.controls.JLbsTabPage || invoker instanceof javax.swing.JToolBar) {
/*     */       return;
/*     */     }
/* 410 */     if (invoker instanceof ILbsComponentBase) {
/*     */       
/* 412 */       int gridTag, ownerTag, row, column, invokerType = JLbsComponentHelper.getInvokerType((ILbsComponentBase)invoker);
/*     */       
/* 414 */       switch (invokerType) {
/*     */         
/*     */         case 2:
/* 417 */           gridTag = JLbsComponentHelper.getGridTag((ILbsComponentBase)invoker);
/* 418 */           performGridRecordForPopUpVisible(gridTag);
/*     */           return;
/*     */         
/*     */         case 1:
/* 422 */           ownerTag = JLbsComponentHelper.getInpEditorOwnerTag((ILbsComponentBase)invoker);
/* 423 */           row = JLbsComponentHelper.getGridSelectedRowForInpEditor((ILbsComponentBase)invoker);
/* 424 */           column = JLbsComponentHelper.getGridSelectedColumnForInpEditor((ILbsComponentBase)invoker);
/* 425 */           performDetailedGridRecordForPopUpVisible(ownerTag, row, column);
/*     */           return;
/*     */       } 
/*     */       
/* 429 */       performNormalRecordForPopUpVisible();
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 435 */       performNormalRecordForPopUpVisible();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void performNormalRecordForPopUpVisible() {
/* 441 */     performDetailedGridRecordForPopUpVisible(-1, -1, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void performGridRecordForPopUpVisible(int gridTag) {
/* 446 */     performDetailedGridRecordForPopUpVisible(gridTag, -1, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void performDetailedGridRecordForPopUpVisible(int gridTag, int row, int column) {
/* 451 */     StringBuilder buffer = new StringBuilder();
/* 452 */     buffer.append("POPUPMENU_WILL_BECOME_VISIBLE");
/* 453 */     buffer.append("|");
/* 454 */     buffer.append(gridTag);
/* 455 */     buffer.append("|");
/* 456 */     buffer.append(row);
/* 457 */     buffer.append("|");
/* 458 */     buffer.append(column);
/* 459 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, buffer.toString());
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
/*     */   
/*     */   public boolean isDynamicMenuExtensionEnabled() {
/* 481 */     return this.m_Implementor.isDynamicMenuExtensionEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDynamicMenuExtensionEnabled(boolean dynamicMenuExtensionEnabled) {
/* 486 */     this.m_Implementor.setDynamicMenuExtensionEnabled(dynamicMenuExtensionEnabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAppendItem(int systemMenuItem) {
/* 491 */     if (getSystemMenuListener() != null)
/* 492 */       return getSystemMenuListener().canAppendSystemMenuItem(systemMenuItem, this); 
/* 493 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsSystemMenuListener getSystemMenuListener() {
/* 498 */     return this.m_Implementor.getSystemMenuListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSystemMenuListener(ILbsSystemMenuListener systemMenuListener) {
/* 503 */     this.m_Implementor.setSystemMenuListener(systemMenuListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JMenuItem createActionComponent(Action a) {
/* 508 */     JMenuItem mi = super.createActionComponent(a);
/* 509 */     mi.setComponentOrientation(getComponentOrientation());
/* 510 */     return mi;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 515 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDirect(JMenuItem menuItem) {
/* 520 */     if (menuItem != null) {
/* 521 */       getItems().add(menuItem);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateUI() {
/* 526 */     super.updateUI();
/*     */     
/* 528 */     if (getItems() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 532 */     for (int i = 0; i < getItems().size(); i++) {
/*     */       
/* 534 */       Object obj = getItems().get(i);
/*     */       
/* 536 */       if (obj instanceof Component) {
/* 537 */         SwingUtilities.updateComponentTreeUI((Component)obj);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayList getMenuItems() {
/* 543 */     return this.m_Implementor.getMenuItems();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void popupMenuCanceled(PopupMenuEvent e) {}
/*     */ 
/*     */   
/*     */   public Object getPopupParent() {
/* 552 */     return this.m_Parent;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 557 */     return getParent();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getInvokerControl() {
/* 562 */     Component invoker = getInvoker();
/* 563 */     if (invoker instanceof JComponent)
/* 564 */       return JLbsComponentHelper.getComponent((JComponent)invoker); 
/* 565 */     if (invoker instanceof ILbsComponent)
/* 566 */       return (ILbsComponent)invoker; 
/* 567 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemEnabled(int index, boolean enabled) {
/* 572 */     JMenuItem item = getMenuItem(index);
/* 573 */     if (item != null) {
/* 574 */       item.setEnabled(enabled);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setItemVisible(int index, boolean visible) {
/* 579 */     JMenuItem item = getMenuItem(index);
/* 580 */     if (item != null) {
/* 581 */       item.setVisible(visible);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isShowAll() {
/* 586 */     return this.m_showAll;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowAll(boolean showAll) {
/* 591 */     this.m_showAll = showAll;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsCustomPopupMenuItem getCustomMenuItemListener() {
/* 598 */     return this.m_Implementor.getCustomMenuItemListener();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomMenuItemListener(ILbsCustomPopupMenuItem customMenuItemListener) {
/* 604 */     this.m_Implementor.setCustomMenuItemListener(customMenuItemListener);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFocusOwner(ILbsComponent owner) {
/* 610 */     this.m_FocusOwner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getFocusOwner() {
/* 616 */     return this.m_FocusOwner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void open() {
/* 622 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\JLbsPopupMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */