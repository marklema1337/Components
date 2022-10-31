/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.MenuElement;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.event.MenuDragMouseEvent;
/*     */ import javax.swing.event.MenuDragMouseListener;
/*     */ import javax.swing.event.MenuEvent;
/*     */ import javax.swing.event.MenuKeyEvent;
/*     */ import javax.swing.event.MenuKeyListener;
/*     */ import javax.swing.event.MenuListener;
/*     */ import javax.swing.event.MouseInputListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SkinnedMenuUI
/*     */   extends SkinnedMenuItemUI
/*     */ {
/*     */   protected ChangeListener changeListener;
/*     */   protected PropertyChangeListener propertyChangeListener;
/*     */   protected MenuListener menuListener;
/*     */   private static MenuListener sharedMenuListener;
/*  50 */   private int lastMnemonic = 0;
/*     */   
/*     */   private static boolean crossMenuMnemonic = true;
/*     */   
/*     */   protected void installDefaults() {
/*  55 */     super.installDefaults();
/*  56 */     crossMenuMnemonic = UIManager.getBoolean("Menu.crossMenuMnemonic");
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getPropertyPrefix() {
/*  61 */     return "Menu";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/*  66 */     super.installListeners();
/*  67 */     if (this.changeListener == null)
/*  68 */       this.changeListener = createChangeListener(this.m_MenuItem); 
/*  69 */     if (this.changeListener != null)
/*  70 */       this.m_MenuItem.addChangeListener(this.changeListener); 
/*  71 */     if (this.propertyChangeListener == null)
/*  72 */       this.propertyChangeListener = createPropertyChangeListener(this.m_MenuItem); 
/*  73 */     if (this.propertyChangeListener != null)
/*  74 */       this.m_MenuItem.addPropertyChangeListener(this.propertyChangeListener); 
/*  75 */     if (this.menuListener == null)
/*  76 */       this.menuListener = createMenuListener(this.m_MenuItem); 
/*  77 */     if (this.menuListener != null) {
/*  78 */       ((JMenu)this.m_MenuItem).addMenuListener(this.menuListener);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void installKeyboardActions() {
/*  83 */     super.installKeyboardActions();
/*  84 */     updateMnemonicBinding();
/*     */   }
/*     */ 
/*     */   
/*     */   void updateMnemonicBinding() {
/*  89 */     int mnemonic = this.m_MenuItem.getModel().getMnemonic();
/*  90 */     int[] shortcutKeys = (int[])UIManager.get("Menu.shortcutKeys");
/*  91 */     if (mnemonic == this.lastMnemonic || shortcutKeys == null) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     if (this.lastMnemonic != 0 && this.m_WindowInputMap != null)
/*     */     {
/*  97 */       for (int i = 0; i < shortcutKeys.length; i++)
/*     */       {
/*  99 */         this.m_WindowInputMap.remove(
/* 100 */             KeyStroke.getKeyStroke(
/* 101 */               this.lastMnemonic, 
/* 102 */               shortcutKeys[i], 
/* 103 */               false));
/*     */       }
/*     */     }
/* 106 */     if (mnemonic != 0) {
/*     */       
/* 108 */       if (this.m_WindowInputMap == null) {
/*     */         
/* 110 */         this.m_WindowInputMap = 
/* 111 */           createInputMap(2);
/* 112 */         SwingUtilities.replaceUIInputMap(
/* 113 */             this.m_MenuItem, 
/* 114 */             2, 
/* 115 */             this.m_WindowInputMap);
/*     */       } 
/* 117 */       for (int i = 0; i < shortcutKeys.length; i++)
/*     */       {
/* 119 */         this.m_WindowInputMap.put(
/* 120 */             KeyStroke.getKeyStroke(mnemonic, shortcutKeys[i], false), 
/* 121 */             "selectMenu");
/*     */       }
/*     */     } 
/* 124 */     this.lastMnemonic = mnemonic;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallKeyboardActions() {
/* 129 */     super.uninstallKeyboardActions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ActionMap getActionMap() {
/* 138 */     return createActionMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ActionMap createActionMap() {
/* 146 */     ActionMap am = super.createActionMap();
/* 147 */     if (am != null)
/*     */     {
/* 149 */       am.put("selectMenu", new PostAction((JMenu)this.m_MenuItem, true));
/*     */     }
/* 151 */     return am;
/*     */   }
/*     */   
/*     */   protected MouseInputListener createMouseInputListener(JComponent c) {
/* 155 */     return new MouseInputHandler();
/*     */   }
/*     */   
/*     */   protected MenuListener createMenuListener(JComponent c) {
/* 159 */     if (sharedMenuListener == null)
/*     */     {
/* 161 */       sharedMenuListener = new MenuHandler(null);
/*     */     }
/* 163 */     return sharedMenuListener;
/*     */   }
/*     */   
/*     */   protected ChangeListener createChangeListener(JComponent c) {
/* 167 */     return null;
/*     */   }
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener(JComponent c) {
/* 171 */     return new PropertyChangeHandler(null);
/*     */   }
/*     */   
/*     */   protected void uninstallDefaults() {
/* 175 */     this.m_MenuItem.setArmed(false);
/* 176 */     this.m_MenuItem.setSelected(false);
/* 177 */     this.m_MenuItem.resetKeyboardActions();
/* 178 */     super.uninstallDefaults();
/*     */   }
/*     */   
/*     */   protected void uninstallListeners() {
/* 182 */     super.uninstallListeners();
/* 183 */     if (this.changeListener != null)
/* 184 */       this.m_MenuItem.removeChangeListener(this.changeListener); 
/* 185 */     if (this.propertyChangeListener != null)
/* 186 */       this.m_MenuItem.removePropertyChangeListener(this.propertyChangeListener); 
/* 187 */     if (this.menuListener != null)
/* 188 */       ((JMenu)this.m_MenuItem).removeMenuListener(this.menuListener); 
/* 189 */     this.changeListener = null;
/* 190 */     this.propertyChangeListener = null;
/* 191 */     this.menuListener = null;
/*     */   }
/*     */   
/*     */   protected MenuDragMouseListener createMenuDragMouseListener(JComponent c) {
/* 195 */     return new MenuDragMouseHandler(null);
/*     */   }
/*     */   
/*     */   protected MenuKeyListener createMenuKeyListener(JComponent c) {
/* 199 */     return new MenuKeyHandler(null);
/*     */   }
/*     */   
/*     */   public Dimension getMaximumSize(JComponent c) {
/* 203 */     if (((JMenu)this.m_MenuItem).isTopLevelMenu()) {
/*     */       
/* 205 */       Dimension d = c.getPreferredSize();
/* 206 */       return new Dimension(d.width, 32767);
/*     */     } 
/* 208 */     return null;
/*     */   }
/*     */   
/*     */   protected void setupPostTimer(JMenu menu) {
/* 212 */     Timer timer = new Timer(menu.getDelay(), new PostAction(menu, false));
/* 213 */     timer.setRepeats(false);
/* 214 */     timer.start();
/*     */   }
/*     */   
/*     */   private static void appendPath(MenuElement[] path, MenuElement elem) {
/* 218 */     MenuElement[] newPath = new MenuElement[path.length + 1];
/* 219 */     System.arraycopy(path, 0, newPath, 0, path.length);
/* 220 */     newPath[path.length] = elem;
/* 221 */     MenuSelectionManager.defaultManager().setSelectedPath(newPath);
/*     */   }
/*     */   
/*     */   private static class PostAction extends AbstractAction {
/*     */     JMenu menu;
/*     */     boolean force = false;
/*     */     
/*     */     PostAction(JMenu menu, boolean shouldForce) {
/* 229 */       this.menu = menu;
/* 230 */       this.force = shouldForce;
/*     */     }
/*     */     
/*     */     public void actionPerformed(ActionEvent e) {
/* 234 */       if (!SkinnedMenuUI.crossMenuMnemonic) {
/*     */         
/* 236 */         JPopupMenu pm = SkinnedMenuUI.getActivePopupMenu();
/* 237 */         if (pm != null && pm != this.menu.getParent()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 242 */       MenuSelectionManager defaultManager = 
/* 243 */         MenuSelectionManager.defaultManager();
/* 244 */       if (this.force) {
/*     */         
/* 246 */         Container cnt = this.menu.getParent();
/* 247 */         if (cnt != null && cnt instanceof javax.swing.JMenuBar)
/*     */         {
/*     */ 
/*     */           
/* 251 */           MenuElement[] me, subElements = this.menu.getPopupMenu().getSubElements();
/* 252 */           if (subElements.length > 0) {
/*     */             
/* 254 */             me = new MenuElement[4];
/* 255 */             me[0] = (MenuElement)cnt;
/* 256 */             me[1] = this.menu;
/* 257 */             me[2] = this.menu.getPopupMenu();
/* 258 */             me[3] = subElements[0];
/*     */           }
/*     */           else {
/*     */             
/* 262 */             me = new MenuElement[3];
/* 263 */             me[0] = (MenuElement)cnt;
/* 264 */             me[1] = this.menu;
/* 265 */             me[2] = this.menu.getPopupMenu();
/*     */           } 
/* 267 */           defaultManager.setSelectedPath(me);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 272 */         MenuElement[] path = defaultManager.getSelectedPath();
/* 273 */         if (path.length > 0 && path[path.length - 1] == this.menu)
/*     */         {
/* 275 */           SkinnedMenuUI.appendPath(path, this.menu.getPopupMenu());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEnabled() {
/* 281 */       return this.menu.getModel().isEnabled();
/*     */     } }
/*     */   
/*     */   private class PropertyChangeHandler implements PropertyChangeListener {
/*     */     private PropertyChangeHandler() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 288 */       String prop = e.getPropertyName();
/* 289 */       if (prop.equals("mnemonic"))
/*     */       {
/* 291 */         SkinnedMenuUI.this.updateMnemonicBinding();
/*     */       }
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
/*     */   
/*     */   protected class MouseInputHandler
/*     */     implements MouseInputListener
/*     */   {
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 322 */       JMenu menu = (JMenu)SkinnedMenuUI.this.m_MenuItem;
/* 323 */       if (!menu.isEnabled())
/*     */         return; 
/* 325 */       MenuSelectionManager manager = 
/* 326 */         MenuSelectionManager.defaultManager();
/* 327 */       if (menu.isTopLevelMenu())
/*     */       {
/* 329 */         if (menu.isSelected()) {
/*     */           
/* 331 */           manager.clearSelectedPath();
/*     */         }
/*     */         else {
/*     */           
/* 335 */           Container cnt = menu.getParent();
/* 336 */           if (cnt != null && cnt instanceof javax.swing.JMenuBar) {
/*     */             
/* 338 */             MenuElement[] me = new MenuElement[2];
/* 339 */             me[0] = (MenuElement)cnt;
/* 340 */             me[1] = menu;
/* 341 */             manager.setSelectedPath(me);
/*     */           } 
/*     */         } 
/*     */       }
/* 345 */       MenuElement[] selectedPath = manager.getSelectedPath();
/* 346 */       if (selectedPath.length > 0 && 
/* 347 */         selectedPath[selectedPath.length - 1] != menu.getPopupMenu())
/*     */       {
/* 349 */         if (menu.isTopLevelMenu() || menu.getDelay() == 0) {
/*     */           
/* 351 */           SkinnedMenuUI.appendPath(selectedPath, menu.getPopupMenu());
/*     */         }
/*     */         else {
/*     */           
/* 355 */           SkinnedMenuUI.this.setupPostTimer(menu);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 367 */       JMenu menu = (JMenu)SkinnedMenuUI.this.m_MenuItem;
/* 368 */       if (!menu.isEnabled())
/*     */         return; 
/* 370 */       MenuSelectionManager manager = 
/* 371 */         MenuSelectionManager.defaultManager();
/* 372 */       manager.processMouseEvent(e);
/* 373 */       if (!e.isConsumed()) {
/* 374 */         manager.clearSelectedPath();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {
/* 386 */       JMenu menu = (JMenu)SkinnedMenuUI.this.m_MenuItem;
/* 387 */       if (!menu.isEnabled())
/*     */         return; 
/* 389 */       menu.putClientProperty("rollover", Boolean.TRUE);
/* 390 */       menu.repaint();
/* 391 */       MenuSelectionManager manager = 
/* 392 */         MenuSelectionManager.defaultManager();
/* 393 */       MenuElement[] selectedPath = manager.getSelectedPath();
/* 394 */       if (!menu.isTopLevelMenu()) {
/*     */         
/* 396 */         if (selectedPath.length <= 0 || 
/* 397 */           selectedPath[selectedPath.length - 1] != 
/* 398 */           menu.getPopupMenu())
/*     */         {
/* 400 */           if (menu.getDelay() == 0)
/*     */           {
/* 402 */             SkinnedMenuUI.appendPath(SkinnedMenuUI.this.getPath(), menu.getPopupMenu());
/*     */           }
/*     */           else
/*     */           {
/* 406 */             manager.setSelectedPath(SkinnedMenuUI.this.getPath());
/* 407 */             SkinnedMenuUI.this.setupPostTimer(menu);
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       }
/* 413 */       else if (selectedPath.length > 0 && 
/* 414 */         selectedPath[0] == menu.getParent()) {
/*     */         
/* 416 */         MenuElement[] newPath = new MenuElement[3];
/*     */ 
/*     */         
/* 419 */         newPath[0] = (MenuElement)menu.getParent();
/* 420 */         newPath[1] = menu;
/* 421 */         newPath[2] = menu.getPopupMenu();
/* 422 */         manager.setSelectedPath(newPath);
/*     */       } 
/*     */       
/* 425 */       menu.repaint();
/*     */     }
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 429 */       JMenu menu = (JMenu)SkinnedMenuUI.this.m_MenuItem;
/* 430 */       menu.putClientProperty("rollover", Boolean.FALSE);
/* 431 */       menu.repaint();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 442 */       JMenu menu = (JMenu)SkinnedMenuUI.this.m_MenuItem;
/* 443 */       if (!menu.isEnabled())
/*     */         return; 
/* 445 */       MenuSelectionManager.defaultManager().processMouseEvent(e);
/*     */     }
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {}
/*     */   }
/*     */   
/*     */   private static class MenuHandler
/*     */     implements MenuListener
/*     */   {
/*     */     private MenuHandler() {}
/*     */     
/*     */     public void menuSelected(MenuEvent e) {}
/*     */     
/*     */     public void menuDeselected(MenuEvent e) {}
/*     */     
/*     */     public void menuCanceled(MenuEvent e) {
/* 461 */       JMenu m = (JMenu)e.getSource();
/* 462 */       MenuSelectionManager manager = 
/* 463 */         MenuSelectionManager.defaultManager();
/* 464 */       if (manager.isComponentPartOfCurrentMenu(m)) {
/* 465 */         MenuSelectionManager.defaultManager().clearSelectedPath();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public class ChangeHandler
/*     */     implements ChangeListener
/*     */   {
/*     */     public JMenu menu;
/*     */     public SkinnedMenuUI ui;
/*     */     public boolean isSelected = false;
/*     */     public Component wasFocused;
/*     */     
/*     */     public ChangeHandler(JMenu m, SkinnedMenuUI ui) {
/* 480 */       this.menu = m;
/* 481 */       this.ui = ui;
/*     */     }
/*     */     
/*     */     public void stateChanged(ChangeEvent e) {}
/*     */   }
/*     */   
/*     */   private class MenuDragMouseHandler
/*     */     implements MenuDragMouseListener {
/*     */     private MenuDragMouseHandler() {}
/*     */     
/*     */     public void menuDragMouseEntered(MenuDragMouseEvent e) {}
/*     */     
/*     */     public void menuDragMouseDragged(MenuDragMouseEvent e) {
/* 494 */       if (!SkinnedMenuUI.this.m_MenuItem.isEnabled())
/*     */         return; 
/* 496 */       MenuSelectionManager manager = e.getMenuSelectionManager();
/* 497 */       MenuElement[] path = e.getPath();
/* 498 */       Point p = e.getPoint();
/* 499 */       if (p.x >= 0 && 
/* 500 */         p.x < SkinnedMenuUI.this.m_MenuItem.getWidth() && 
/* 501 */         p.y >= 0 && 
/* 502 */         p.y < SkinnedMenuUI.this.m_MenuItem.getHeight()) {
/*     */         
/* 504 */         JMenu menu = (JMenu)SkinnedMenuUI.this.m_MenuItem;
/* 505 */         MenuElement[] selectedPath = manager.getSelectedPath();
/* 506 */         if (selectedPath.length <= 0 || 
/* 507 */           selectedPath[selectedPath.length - 1] != 
/* 508 */           menu.getPopupMenu())
/*     */         {
/* 510 */           if (menu.isTopLevelMenu() || 
/* 511 */             menu.getDelay() == 0 || 
/* 512 */             e.getID() == 506)
/*     */           {
/* 514 */             SkinnedMenuUI.appendPath(path, menu.getPopupMenu());
/*     */           }
/*     */           else
/*     */           {
/* 518 */             manager.setSelectedPath(path);
/* 519 */             SkinnedMenuUI.this.setupPostTimer(menu);
/*     */           }
/*     */         
/*     */         }
/*     */       }
/* 524 */       else if (e.getID() == 502) {
/*     */         
/* 526 */         Component comp = 
/* 527 */           manager.componentForPoint(
/* 528 */             e.getComponent(), 
/* 529 */             e.getPoint());
/* 530 */         if (comp == null) {
/* 531 */           manager.clearSelectedPath();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void menuDragMouseExited(MenuDragMouseEvent e) {}
/*     */     
/*     */     public void menuDragMouseReleased(MenuDragMouseEvent e) {}
/*     */   }
/*     */   
/*     */   static JPopupMenu getActivePopupMenu() {
/* 543 */     MenuElement[] path = 
/* 544 */       MenuSelectionManager.defaultManager().getSelectedPath();
/* 545 */     for (int i = path.length - 1; i >= 0; i--) {
/*     */       
/* 547 */       MenuElement elem = path[i];
/* 548 */       if (elem instanceof JPopupMenu)
/*     */       {
/* 550 */         return (JPopupMenu)elem;
/*     */       }
/*     */     } 
/* 553 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private class MenuKeyHandler
/*     */     implements MenuKeyListener
/*     */   {
/*     */     private int[] indexes;
/*     */     
/*     */     private char lastMnemonic;
/*     */     
/*     */     private int lastIndex;
/*     */     private int matches;
/*     */     
/*     */     private MenuKeyHandler() {}
/*     */     
/*     */     public void menuKeyTyped(MenuKeyEvent e) {
/* 570 */       if (!SkinnedMenuUI.crossMenuMnemonic) {
/*     */         
/* 572 */         JPopupMenu pm = SkinnedMenuUI.getActivePopupMenu();
/* 573 */         if (pm != null && pm != SkinnedMenuUI.this.m_MenuItem.getParent()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 578 */       int key = SkinnedMenuUI.this.m_MenuItem.getMnemonic();
/* 579 */       if (key == 0)
/*     */         return; 
/* 581 */       MenuElement[] path = e.getPath();
/* 582 */       if (lower((char)key) == lower(e.getKeyChar())) {
/*     */         
/* 584 */         JPopupMenu popupMenu = ((JMenu)SkinnedMenuUI.this.m_MenuItem).getPopupMenu();
/* 585 */         MenuElement[] sub = popupMenu.getSubElements();
/* 586 */         if (sub.length > 0) {
/*     */           
/* 588 */           MenuSelectionManager manager = e.getMenuSelectionManager();
/* 589 */           MenuElement[] newPath = new MenuElement[path.length + 2];
/* 590 */           System.arraycopy(path, 0, newPath, 0, path.length);
/* 591 */           newPath[path.length] = popupMenu;
/* 592 */           newPath[path.length + 1] = sub[0];
/* 593 */           manager.setSelectedPath(newPath);
/*     */         } 
/* 595 */         e.consume();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void menuKeyPressed(MenuKeyEvent e) {
/* 605 */       char keyChar = e.getKeyChar();
/* 606 */       if (!Character.isLetterOrDigit(keyChar))
/*     */         return; 
/* 608 */       MenuSelectionManager manager = e.getMenuSelectionManager();
/* 609 */       MenuElement[] path = e.getPath();
/* 610 */       MenuElement[] selectedPath = manager.getSelectedPath();
/* 611 */       for (int i = selectedPath.length - 1; i >= 0; i--) {
/*     */         
/* 613 */         if (selectedPath[i] == SkinnedMenuUI.this.m_MenuItem) {
/*     */           
/* 615 */           JPopupMenu popupMenu = ((JMenu)SkinnedMenuUI.this.m_MenuItem).getPopupMenu();
/* 616 */           MenuElement[] items = popupMenu.getSubElements();
/* 617 */           if (this.indexes == null || this.lastMnemonic != keyChar) {
/*     */             
/* 619 */             this.matches = 0;
/* 620 */             this.lastIndex = 0;
/* 621 */             this.indexes = new int[items.length];
/* 622 */             for (int j = 0; j < items.length; j++) {
/*     */               
/* 624 */               int key = ((JMenuItem)items[j]).getMnemonic();
/* 625 */               if (lower((char)key) == lower(keyChar))
/*     */               {
/* 627 */                 this.indexes[this.matches++] = j;
/*     */               }
/*     */             } 
/* 630 */             this.lastMnemonic = keyChar;
/*     */           } 
/* 632 */           if (this.matches != 0)
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 637 */             if (this.matches == 1) {
/*     */ 
/*     */               
/* 640 */               JMenuItem item = (JMenuItem)items[this.indexes[0]];
/* 641 */               if (!(item instanceof JMenu))
/*     */               {
/*     */                 
/* 644 */                 manager.clearSelectedPath();
/* 645 */                 item.doClick();
/*     */               
/*     */               }
/*     */ 
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 653 */               if (this.lastIndex == this.matches)
/*     */               {
/*     */ 
/*     */                 
/* 657 */                 this.lastIndex = 0;
/*     */               }
/* 659 */               MenuElement menuItem = items[this.indexes[this.lastIndex++]];
/* 660 */               MenuElement[] newPath = 
/* 661 */                 new MenuElement[path.length + 2];
/* 662 */               System.arraycopy(path, 0, newPath, 0, path.length);
/* 663 */               newPath[path.length] = popupMenu;
/* 664 */               newPath[path.length + 1] = menuItem;
/* 665 */               manager.setSelectedPath(newPath);
/*     */             }  } 
/* 667 */           e.consume();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void menuKeyReleased(MenuKeyEvent e) {}
/*     */     
/*     */     private char lower(char keyChar) {
/* 677 */       return Character.toLowerCase(keyChar);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedMenuUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */