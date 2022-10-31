/*      */ package com.lbs.laf.common;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.ActionMap;
/*      */ import javax.swing.ButtonModel;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.InputMap;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.MenuElement;
/*      */ import javax.swing.MenuSelectionManager;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.event.MenuDragMouseEvent;
/*      */ import javax.swing.event.MenuDragMouseListener;
/*      */ import javax.swing.event.MenuKeyEvent;
/*      */ import javax.swing.event.MenuKeyListener;
/*      */ import javax.swing.event.MouseInputListener;
/*      */ import javax.swing.plaf.ActionMapUIResource;
/*      */ import javax.swing.plaf.ComponentInputMapUIResource;
/*      */ import javax.swing.plaf.MenuItemUI;
/*      */ import javax.swing.plaf.basic.BasicGraphicsUtils;
/*      */ import javax.swing.plaf.basic.BasicHTML;
/*      */ import javax.swing.text.StyleContext;
/*      */ import javax.swing.text.View;
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
/*      */ public abstract class SkinnedMenuItemUI
/*      */   extends MenuItemUI
/*      */ {
/*      */   protected static int ms_DefaultTextIconGap;
/*      */   protected static int ms_DefaultIconGap;
/*   65 */   protected JMenuItem m_MenuItem = null;
/*      */   protected Color m_SelectionBackground;
/*      */   protected Color m_SelectionForeground;
/*      */   protected Color m_DisabledForeground;
/*      */   protected Color m_AcceleratorForeground;
/*      */   protected Color m_AcceleratorSelectionForeground;
/*      */   private String m_AcceleratorDelimiter;
/*      */   protected Font m_AcceleratorFont;
/*      */   protected MouseInputListener m_MouseInputListener;
/*      */   protected MenuDragMouseListener m_MenuDragMouseListener;
/*      */   protected MenuKeyListener m_MenuKeyListener;
/*      */   private PropertyChangeListener m_PropertyChangeListener;
/*   77 */   protected Icon m_ArrowIcon = null;
/*   78 */   protected Icon m_CheckIcon = null;
/*      */   
/*      */   protected boolean m_OldBorderPainted;
/*      */   
/*      */   InputMap m_WindowInputMap;
/*      */   
/*      */   private static final boolean DEBUG = false;
/*      */   
/*      */   static final String MAX_TEXT_WIDTH = "maxTextWidth";
/*      */   static final String MAX_ACC_WIDTH = "maxAccWidth";
/*      */   static final String MAX_ICON_WIDTH = "maxIconWidth";
/*      */   
/*      */   public void installUI(JComponent c) {
/*   91 */     this.m_MenuItem = (JMenuItem)c;
/*   92 */     installDefaults();
/*   93 */     installComponents(this.m_MenuItem);
/*   94 */     installListeners();
/*   95 */     installKeyboardActions();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installComponents(JMenuItem menuItem) {
/*  100 */     BasicHTML.updateRenderer(menuItem, menuItem.getText());
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getPropertyPrefix() {
/*  105 */     return "MenuItem";
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installListeners() {
/*  110 */     if ((this.m_MouseInputListener = createMouseInputListener(this.m_MenuItem)) != null) {
/*      */       
/*  112 */       this.m_MenuItem.addMouseListener(this.m_MouseInputListener);
/*  113 */       this.m_MenuItem.addMouseMotionListener(this.m_MouseInputListener);
/*      */     } 
/*  115 */     if ((this.m_MenuDragMouseListener = createMenuDragMouseListener(this.m_MenuItem)) != null)
/*      */     {
/*  117 */       this.m_MenuItem.addMenuDragMouseListener(this.m_MenuDragMouseListener);
/*      */     }
/*  119 */     if ((this.m_MenuKeyListener = createMenuKeyListener(this.m_MenuItem)) != null)
/*      */     {
/*  121 */       this.m_MenuItem.addMenuKeyListener(this.m_MenuKeyListener);
/*      */     }
/*  123 */     if ((this.m_PropertyChangeListener = createPropertyChangeListener(this.m_MenuItem)) != null)
/*      */     {
/*  125 */       this.m_MenuItem.addPropertyChangeListener(this.m_PropertyChangeListener);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installKeyboardActions() {
/*  131 */     ActionMap actionMap = getActionMap();
/*  132 */     SwingUtilities.replaceUIActionMap(this.m_MenuItem, actionMap);
/*  133 */     updateAcceleratorBinding();
/*      */   }
/*      */ 
/*      */   
/*      */   public void uninstallUI(JComponent c) {
/*  138 */     this.m_MenuItem = (JMenuItem)c;
/*  139 */     uninstallDefaults();
/*  140 */     uninstallComponents(this.m_MenuItem);
/*  141 */     uninstallListeners();
/*  142 */     uninstallKeyboardActions();
/*      */     
/*  144 */     Container parent = this.m_MenuItem.getParent();
/*  145 */     if (parent != null && parent instanceof JComponent && (
/*  146 */       !(this.m_MenuItem instanceof JMenu) || !((JMenu)this.m_MenuItem).isTopLevelMenu())) {
/*      */       
/*  148 */       JComponent p = (JComponent)parent;
/*  149 */       p.putClientProperty("maxAccWidth", (Object)null);
/*  150 */       p.putClientProperty("maxTextWidth", (Object)null);
/*      */     } 
/*  152 */     this.m_MenuItem = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void uninstallDefaults() {
/*  157 */     LookAndFeel.uninstallBorder(this.m_MenuItem);
/*  158 */     this.m_MenuItem.setBorderPainted(this.m_OldBorderPainted);
/*  159 */     if (this.m_MenuItem.getMargin() instanceof javax.swing.plaf.UIResource)
/*  160 */       this.m_MenuItem.setMargin((Insets)null); 
/*  161 */     if (this.m_ArrowIcon instanceof javax.swing.plaf.UIResource)
/*  162 */       this.m_ArrowIcon = null; 
/*  163 */     if (this.m_CheckIcon instanceof javax.swing.plaf.UIResource) {
/*  164 */       this.m_CheckIcon = null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void uninstallComponents(JMenuItem menuItem) {
/*  172 */     BasicHTML.updateRenderer(menuItem, "");
/*      */   }
/*      */ 
/*      */   
/*      */   protected void uninstallListeners() {
/*  177 */     if (this.m_MouseInputListener != null) {
/*      */       
/*  179 */       this.m_MenuItem.removeMouseListener(this.m_MouseInputListener);
/*  180 */       this.m_MenuItem.removeMouseMotionListener(this.m_MouseInputListener);
/*      */     } 
/*  182 */     if (this.m_MenuDragMouseListener != null)
/*      */     {
/*  184 */       this.m_MenuItem.removeMenuDragMouseListener(this.m_MenuDragMouseListener);
/*      */     }
/*  186 */     if (this.m_MenuKeyListener != null)
/*      */     {
/*  188 */       this.m_MenuItem.removeMenuKeyListener(this.m_MenuKeyListener);
/*      */     }
/*  190 */     if (this.m_PropertyChangeListener != null)
/*      */     {
/*  192 */       this.m_MenuItem.removePropertyChangeListener(this.m_PropertyChangeListener);
/*      */     }
/*  194 */     this.m_MouseInputListener = null;
/*  195 */     this.m_MenuDragMouseListener = null;
/*  196 */     this.m_MenuKeyListener = null;
/*  197 */     this.m_PropertyChangeListener = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void uninstallKeyboardActions() {
/*  202 */     SwingUtilities.replaceUIActionMap(this.m_MenuItem, null);
/*  203 */     if (this.m_WindowInputMap != null) {
/*      */       
/*  205 */       SwingUtilities.replaceUIInputMap(this.m_MenuItem, 2, null);
/*  206 */       this.m_WindowInputMap = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected MouseInputListener createMouseInputListener(JComponent c) {
/*  212 */     return new MouseInputHandler();
/*      */   }
/*      */ 
/*      */   
/*      */   protected MenuDragMouseListener createMenuDragMouseListener(JComponent c) {
/*  217 */     return new MenuDragMouseHandler(null);
/*      */   }
/*      */ 
/*      */   
/*      */   protected MenuKeyListener createMenuKeyListener(JComponent c) {
/*  222 */     return new MenuKeyHandler(null);
/*      */   }
/*      */ 
/*      */   
/*      */   private PropertyChangeListener createPropertyChangeListener(JComponent c) {
/*  227 */     return new PropertyChangeHandler(null);
/*      */   }
/*      */ 
/*      */   
/*      */   ActionMap getActionMap() {
/*  232 */     String propertyPrefix = getPropertyPrefix();
/*  233 */     String uiKey = String.valueOf(propertyPrefix) + ".actionMap";
/*  234 */     ActionMap am = (ActionMap)UIManager.get(uiKey);
/*  235 */     if (am == null) {
/*      */       
/*  237 */       am = createActionMap();
/*  238 */       UIManager.getLookAndFeelDefaults().put(uiKey, am);
/*      */     } 
/*  240 */     return am;
/*      */   }
/*      */ 
/*      */   
/*      */   ActionMap createActionMap() {
/*  245 */     ActionMap map = new ActionMapUIResource();
/*  246 */     map.put("doClick", new ClickAction(null));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  251 */     return map;
/*      */   }
/*      */ 
/*      */   
/*      */   InputMap createInputMap(int condition) {
/*  256 */     if (condition == 2)
/*      */     {
/*  258 */       return new ComponentInputMapUIResource(this.m_MenuItem);
/*      */     }
/*  260 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   void updateAcceleratorBinding() {
/*  265 */     KeyStroke accelerator = this.m_MenuItem.getAccelerator();
/*  266 */     if (this.m_WindowInputMap != null)
/*      */     {
/*  268 */       this.m_WindowInputMap.clear();
/*      */     }
/*  270 */     if (accelerator != null) {
/*      */       
/*  272 */       if (this.m_WindowInputMap == null) {
/*      */         
/*  274 */         this.m_WindowInputMap = createInputMap(2);
/*  275 */         SwingUtilities.replaceUIInputMap(this.m_MenuItem, 2, this.m_WindowInputMap);
/*      */       } 
/*  277 */       if (this.m_WindowInputMap != null) {
/*  278 */         this.m_WindowInputMap.put(accelerator, "doClick");
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public Dimension getMinimumSize(JComponent c) {
/*  284 */     Dimension d = null;
/*  285 */     View v = (View)c.getClientProperty("html");
/*  286 */     if (v != null) {
/*      */       
/*  288 */       d = getPreferredSize(c);
/*  289 */       d.width = (int)(d.width - v.getPreferredSpan(0) - v.getMinimumSpan(0));
/*      */     } 
/*  291 */     return d;
/*      */   }
/*      */ 
/*      */   
/*      */   public Dimension getPreferredSize(JComponent c) {
/*  296 */     return getPreferredMenuItemSize(c, this.m_CheckIcon, this.m_ArrowIcon, ms_DefaultTextIconGap);
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
/*      */   protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
/*  310 */     ButtonModel model = menuItem.getModel();
/*  311 */     FontMetrics fm = g.getFontMetrics();
/*  312 */     int mnemIndex = menuItem.getDisplayedMnemonicIndex();
/*  313 */     if (!model.isEnabled()) {
/*      */ 
/*      */       
/*  316 */       if (UIManager.get("MenuItem.disabledForeground") instanceof Color)
/*      */       {
/*  318 */         g.setColor(UIManager.getColor("MenuItem.disabledForeground"));
/*  319 */         BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
/*      */       }
/*      */       else
/*      */       {
/*  323 */         g.setColor(menuItem.getBackground().brighter());
/*  324 */         BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
/*  325 */         g.setColor(menuItem.getBackground().darker());
/*  326 */         BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemIndex, textRect.x - 1, textRect.y + fm.getAscent() - 1);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  332 */       if (model.isArmed() || (menuItem instanceof JMenu && model.isSelected()))
/*      */       {
/*  334 */         g.setColor(this.m_SelectionForeground);
/*      */       }
/*  336 */       BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Dimension getMaximumSize(JComponent c) {
/*  342 */     Dimension d = null;
/*  343 */     View v = (View)c.getClientProperty("html");
/*  344 */     if (v != null) {
/*      */       
/*  346 */       d = getPreferredSize(c);
/*  347 */       d.width = (int)(d.width + v.getMaximumSpan(0) - v.getPreferredSpan(0));
/*      */     } 
/*  349 */     return d;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  354 */   static Rectangle zeroRect = new Rectangle(0, 0, 0, 0);
/*  355 */   static Rectangle iconRect = new Rectangle();
/*  356 */   static Rectangle textRect = new Rectangle();
/*  357 */   static Rectangle acceleratorRect = new Rectangle();
/*  358 */   static Rectangle checkIconRect = new Rectangle();
/*  359 */   static Rectangle arrowIconRect = new Rectangle();
/*  360 */   static Rectangle viewRect = new Rectangle(32767, 32767);
/*  361 */   static Rectangle r = new Rectangle();
/*      */ 
/*      */   
/*      */   private void resetRects() {
/*  365 */     iconRect.setBounds(zeroRect);
/*  366 */     textRect.setBounds(zeroRect);
/*  367 */     acceleratorRect.setBounds(zeroRect);
/*  368 */     checkIconRect.setBounds(zeroRect);
/*  369 */     arrowIconRect.setBounds(zeroRect);
/*  370 */     viewRect.setBounds(0, 0, 32767, 32767);
/*  371 */     r.setBounds(zeroRect);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update(Graphics g, JComponent c) {
/*  382 */     paint(g, c);
/*      */   }
/*      */ 
/*      */   
/*      */   public void paint(Graphics g, JComponent c) {
/*  387 */     paintMenuItem(g, c, this.m_CheckIcon, this.m_ArrowIcon, this.m_SelectionBackground, this.m_SelectionForeground, ms_DefaultTextIconGap);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useCheckAndArrow() {
/*  396 */     boolean b = true;
/*  397 */     if (this.m_MenuItem instanceof JMenu && ((JMenu)this.m_MenuItem).isTopLevelMenu())
/*      */     {
/*  399 */       b = false;
/*      */     }
/*  401 */     return b;
/*      */   }
/*      */   
/*      */   public MenuElement[] getPath() {
/*      */     MenuElement[] newPath;
/*  406 */     MenuSelectionManager m = MenuSelectionManager.defaultManager();
/*  407 */     MenuElement[] oldPath = m.getSelectedPath();
/*      */     
/*  409 */     int i = oldPath.length;
/*  410 */     if (i == 0)
/*  411 */       return new MenuElement[0]; 
/*  412 */     Component parent = this.m_MenuItem.getParent();
/*  413 */     if (oldPath[i - 1].getComponent() == parent) {
/*      */ 
/*      */       
/*  416 */       newPath = new MenuElement[i + 1];
/*  417 */       System.arraycopy(oldPath, 0, newPath, 0, i);
/*  418 */       newPath[i] = this.m_MenuItem;
/*      */     } else {
/*      */       int j;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  429 */       for (j = oldPath.length - 1; j >= 0; j--) {
/*      */         
/*  431 */         if (oldPath[j].getComponent() == parent)
/*      */           break; 
/*      */       } 
/*  434 */       newPath = new MenuElement[j + 2];
/*  435 */       System.arraycopy(oldPath, 0, newPath, 0, j + 1);
/*  436 */       newPath[j + 1] = this.m_MenuItem;
/*      */     } 
/*  438 */     return newPath;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class MouseInputHandler
/*      */     implements MouseInputListener
/*      */   {
/*      */     public void mouseClicked(MouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {
/*  453 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/*  454 */       Point p = e.getPoint();
/*  455 */       if (p.x >= 0 && p.x < SkinnedMenuItemUI.this.m_MenuItem.getWidth() && p.y >= 0 && p.y < SkinnedMenuItemUI.this.m_MenuItem.getHeight()) {
/*      */         
/*  457 */         SkinnedMenuItemUI.this.doClick(manager);
/*      */       }
/*      */       else {
/*      */         
/*  461 */         manager.processMouseEvent(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {
/*  467 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/*  468 */       int modifiers = e.getModifiers();
/*      */       
/*  470 */       if ((modifiers & 0x1C) != 0) {
/*      */         
/*  472 */         MenuSelectionManager.defaultManager().processMouseEvent(e);
/*      */       }
/*      */       else {
/*      */         
/*  476 */         manager.setSelectedPath(SkinnedMenuItemUI.this.getPath());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseExited(MouseEvent e) {
/*  482 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/*  483 */       int modifiers = e.getModifiers();
/*      */       
/*  485 */       if ((modifiers & 0x1C) != 0) {
/*      */         
/*  487 */         MenuSelectionManager.defaultManager().processMouseEvent(e);
/*      */       }
/*      */       else {
/*      */         
/*  491 */         MenuElement[] path = manager.getSelectedPath();
/*  492 */         if (path.length > 1) {
/*      */           
/*  494 */           MenuElement[] newPath = new MenuElement[path.length - 1];
/*      */           
/*  496 */           for (int i = 0, c = path.length - 1; i < c; i++)
/*  497 */             newPath[i] = path[i]; 
/*  498 */           manager.setSelectedPath(newPath);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseDragged(MouseEvent e) {
/*  505 */       MenuSelectionManager.defaultManager().processMouseEvent(e);
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseMoved(MouseEvent e) {}
/*      */   }
/*      */ 
/*      */   
/*      */   private class MenuDragMouseHandler
/*      */     implements MenuDragMouseListener
/*      */   {
/*      */     private MenuDragMouseHandler() {}
/*      */     
/*      */     public void menuDragMouseEntered(MenuDragMouseEvent e) {}
/*      */     
/*      */     public void menuDragMouseDragged(MenuDragMouseEvent e) {
/*  521 */       MenuSelectionManager manager = e.getMenuSelectionManager();
/*  522 */       MenuElement[] path = e.getPath();
/*  523 */       manager.setSelectedPath(path);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void menuDragMouseExited(MenuDragMouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void menuDragMouseReleased(MenuDragMouseEvent e) {
/*  532 */       MenuSelectionManager manager = e.getMenuSelectionManager();
/*      */       
/*  534 */       Point p = e.getPoint();
/*  535 */       if (p.x >= 0 && p.x < SkinnedMenuItemUI.this.m_MenuItem.getWidth() && p.y >= 0 && p.y < SkinnedMenuItemUI.this.m_MenuItem.getHeight()) {
/*      */         
/*  537 */         SkinnedMenuItemUI.this.doClick(manager);
/*      */       }
/*      */       else {
/*      */         
/*  541 */         manager.clearSelectedPath();
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
/*      */   private class MenuKeyHandler
/*      */     implements MenuKeyListener
/*      */   {
/*      */     private MenuKeyHandler() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void menuKeyTyped(MenuKeyEvent e) {
/*  563 */       int key = SkinnedMenuItemUI.this.m_MenuItem.getMnemonic();
/*  564 */       if (key == 0 || (e.getPath()).length != 2) {
/*      */         return;
/*      */       }
/*  567 */       if (lower((char)key) == lower(e.getKeyChar())) {
/*      */         
/*  569 */         MenuSelectionManager manager = e.getMenuSelectionManager();
/*  570 */         SkinnedMenuItemUI.this.doClick(manager);
/*  571 */         e.consume();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void menuKeyPressed(MenuKeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void menuKeyReleased(MenuKeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private char lower(char keyChar) {
/*  589 */       return Character.toLowerCase(keyChar);
/*      */     }
/*      */   }
/*      */   
/*      */   private class PropertyChangeHandler implements PropertyChangeListener {
/*      */     private PropertyChangeHandler() {}
/*      */     
/*      */     public void propertyChange(PropertyChangeEvent e) {
/*  597 */       String name = e.getPropertyName();
/*  598 */       if (name.equals("labelFor") || name.equals("displayedMnemonic") || name.equals("accelerator")) {
/*      */         
/*  600 */         SkinnedMenuItemUI.this.updateAcceleratorBinding();
/*      */       }
/*  602 */       else if (name.equals("text") || "font".equals(name) || "foreground".equals(name)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  607 */         JMenuItem lbl = (JMenuItem)e.getSource();
/*  608 */         String text = lbl.getText();
/*  609 */         BasicHTML.updateRenderer(lbl, text);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ClickAction extends AbstractAction {
/*      */     private ClickAction() {}
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/*  618 */       JMenuItem mi = (JMenuItem)e.getSource();
/*  619 */       MenuSelectionManager.defaultManager().clearSelectedPath();
/*  620 */       mi.doClick();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doClick(MenuSelectionManager msm) {
/*  643 */     if (msm == null)
/*      */     {
/*  645 */       msm = MenuSelectionManager.defaultManager();
/*      */     }
/*  647 */     msm.clearSelectedPath();
/*  648 */     this.m_MenuItem.doClick(0);
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
/*      */   public static class EmptyIcon
/*      */     implements Icon
/*      */   {
/*      */     int width;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int height;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EmptyIcon(int width, int height) {
/*  680 */       this.height = height;
/*  681 */       this.width = width;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void paintIcon(Component c, Graphics g, int x, int y) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public int getIconWidth() {
/*  691 */       return this.width;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getIconHeight() {
/*  696 */       return this.height;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installDefaults() {
/*  702 */     String prefix = getPropertyPrefix();
/*  703 */     this.m_AcceleratorFont = UIManager.getFont("MenuItem.acceleratorFont");
/*  704 */     this.m_MenuItem.setOpaque(true);
/*  705 */     if (this.m_MenuItem.getMargin() == null || this.m_MenuItem.getMargin() instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  707 */       this.m_MenuItem.setMargin(UIManager.getInsets(String.valueOf(prefix) + ".margin"));
/*      */     }
/*  709 */     ms_DefaultTextIconGap = 4;
/*  710 */     LookAndFeel.installBorder(this.m_MenuItem, String.valueOf(prefix) + ".border");
/*  711 */     this.m_OldBorderPainted = this.m_MenuItem.isBorderPainted();
/*  712 */     this.m_MenuItem.setBorderPainted(((Boolean)UIManager.get(String.valueOf(prefix) + ".borderPainted")).booleanValue());
/*  713 */     LookAndFeel.installColorsAndFont(this.m_MenuItem, String.valueOf(prefix) + ".background", String.valueOf(prefix) + ".foreground", String.valueOf(prefix) + ".font");
/*      */     
/*  715 */     if (this.m_SelectionBackground == null || this.m_SelectionBackground instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  717 */       this.m_SelectionBackground = UIManager.getColor(String.valueOf(prefix) + ".selectionBackground");
/*      */     }
/*  719 */     if (this.m_SelectionForeground == null || this.m_SelectionForeground instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  721 */       this.m_SelectionForeground = UIManager.getColor(String.valueOf(prefix) + ".selectionForeground");
/*      */     }
/*  723 */     if (this.m_DisabledForeground == null || this.m_DisabledForeground instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  725 */       this.m_DisabledForeground = UIManager.getColor(String.valueOf(prefix) + ".disabledForeground");
/*      */     }
/*  727 */     if (this.m_AcceleratorForeground == null || this.m_AcceleratorForeground instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  729 */       this.m_AcceleratorForeground = UIManager.getColor(String.valueOf(prefix) + ".acceleratorForeground");
/*      */     }
/*  731 */     if (this.m_AcceleratorSelectionForeground == null || this.m_AcceleratorSelectionForeground instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  733 */       this.m_AcceleratorSelectionForeground = UIManager.getColor(String.valueOf(prefix) + ".acceleratorSelectionForeground");
/*      */     }
/*      */     
/*  736 */     this.m_AcceleratorDelimiter = UIManager.getString("MenuItem.acceleratorDelimiter");
/*  737 */     if (this.m_AcceleratorDelimiter == null)
/*      */     {
/*  739 */       this.m_AcceleratorDelimiter = "+";
/*      */     }
/*      */     
/*  742 */     if (this.m_ArrowIcon == null || this.m_ArrowIcon instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  744 */       this.m_ArrowIcon = UIManager.getIcon(String.valueOf(prefix) + ".arrowIcon");
/*      */     }
/*  746 */     if (this.m_CheckIcon == null || this.m_CheckIcon instanceof javax.swing.plaf.UIResource)
/*      */     {
/*  748 */       this.m_CheckIcon = UIManager.getIcon(String.valueOf(prefix) + ".checkIcon");
/*      */     }
/*      */     
/*  751 */     ms_DefaultTextIconGap = 8;
/*  752 */     ms_DefaultIconGap = 4;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
/*  757 */     JMenuItem b = (JMenuItem)c;
/*  758 */     Icon icon = b.getIcon();
/*  759 */     String text = b.getText();
/*  760 */     KeyStroke accelerator = b.getAccelerator();
/*  761 */     String acceleratorText = "";
/*  762 */     if (accelerator != null) {
/*      */       
/*  764 */       int modifiers = accelerator.getModifiers();
/*  765 */       if (modifiers > 0) {
/*      */         
/*  767 */         acceleratorText = KeyEvent.getKeyModifiersText(modifiers);
/*      */         
/*  769 */         acceleratorText = String.valueOf(acceleratorText) + this.m_AcceleratorDelimiter;
/*      */       } 
/*  771 */       int keyCode = accelerator.getKeyCode();
/*  772 */       if (keyCode != 0) {
/*      */         
/*  774 */         acceleratorText = String.valueOf(acceleratorText) + KeyEvent.getKeyText(keyCode);
/*      */       }
/*      */       else {
/*      */         
/*  778 */         acceleratorText = String.valueOf(acceleratorText) + accelerator.getKeyChar();
/*      */       } 
/*      */     } 
/*  781 */     Font font = b.getFont();
/*  782 */     StyleContext context = new StyleContext();
/*  783 */     FontMetrics fm = context.getFontMetrics(font);
/*  784 */     FontMetrics fmAccel = context.getFontMetrics(this.m_AcceleratorFont);
/*  785 */     resetRects();
/*  786 */     layoutMenuItem(fm, text, fmAccel, acceleratorText, icon, checkIcon, arrowIcon, b.getVerticalAlignment(), b
/*  787 */         .getHorizontalAlignment(), b.getVerticalTextPosition(), b.getHorizontalTextPosition(), viewRect, iconRect, 
/*  788 */         textRect, acceleratorRect, checkIconRect, arrowIconRect, (text == null) ? 
/*  789 */         0 : 
/*  790 */         defaultTextIconGap, ms_DefaultIconGap);
/*  791 */     context = null;
/*      */     
/*  793 */     r.setBounds(textRect);
/*  794 */     r = SwingUtilities.computeUnion(iconRect.x, iconRect.y, iconRect.width, iconRect.height, r);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  799 */     Container parent = this.m_MenuItem.getParent();
/*      */     
/*  801 */     if (parent != null && parent instanceof JComponent && (
/*  802 */       !(this.m_MenuItem instanceof JMenu) || !((JMenu)this.m_MenuItem).isTopLevelMenu())) {
/*      */       
/*  804 */       JComponent p = (JComponent)parent;
/*      */       
/*  806 */       Integer maxTextWidth = (Integer)p.getClientProperty("maxTextWidth");
/*  807 */       Integer maxAccWidth = (Integer)p.getClientProperty("maxAccWidth");
/*  808 */       Integer maxIconWidth = (Integer)p.getClientProperty("maxIconWidth");
/*      */       
/*  810 */       int maxTextValue = (maxTextWidth != null) ? 
/*  811 */         maxTextWidth.intValue() : 
/*  812 */         0;
/*  813 */       int maxAccValue = (maxAccWidth != null) ? 
/*  814 */         maxAccWidth.intValue() : 
/*  815 */         0;
/*  816 */       int maxIconValue = (maxIconWidth != null) ? 
/*  817 */         maxIconWidth.intValue() : 
/*  818 */         0;
/*      */       
/*  820 */       if (r.width < maxTextValue) {
/*      */         
/*  822 */         r.width = maxTextValue;
/*      */       }
/*      */       else {
/*      */         
/*  826 */         p.putClientProperty("maxTextWidth", Integer.valueOf(r.width));
/*      */       } 
/*      */       
/*  829 */       if (acceleratorRect.width > maxAccValue) {
/*      */         
/*  831 */         maxAccValue = acceleratorRect.width;
/*  832 */         p.putClientProperty("maxAccWidth", Integer.valueOf(acceleratorRect.width));
/*      */       } 
/*      */       
/*  835 */       if (icon != null && icon.getIconWidth() > maxIconValue) {
/*      */         
/*  837 */         maxIconValue = icon.getIconWidth();
/*  838 */         p.putClientProperty("maxIconWidth", Integer.valueOf(maxIconValue));
/*      */       } 
/*      */       
/*  841 */       r.width += maxAccValue;
/*  842 */       r.width += defaultTextIconGap;
/*      */     } 
/*  844 */     if (useCheckAndArrow()) {
/*      */ 
/*      */       
/*  847 */       r.width += checkIconRect.width;
/*  848 */       r.width += defaultTextIconGap;
/*      */       
/*  850 */       r.width += defaultTextIconGap;
/*  851 */       r.width += arrowIconRect.width;
/*      */     } 
/*  853 */     r.width += 2 * defaultTextIconGap;
/*  854 */     Insets insets = b.getInsets();
/*  855 */     if (insets != null) {
/*      */       
/*  857 */       r.width += insets.left + insets.right;
/*  858 */       r.height += insets.top + insets.bottom;
/*      */     } 
/*      */ 
/*      */     
/*  862 */     if (r.width % 2 == 0)
/*      */     {
/*  864 */       r.width++;
/*      */     }
/*      */ 
/*      */     
/*  868 */     if (r.height % 2 == 0)
/*      */     {
/*  870 */       r.height++;
/*      */     }
/*  872 */     return r.getSize();
/*      */   }
/*      */ 
/*      */   
/*      */   private void paintMenuIcon(Icon icon, Component c, Graphics g, int x, int y, boolean isLeftToRight, int menuWidth) {
/*  877 */     if (isLeftToRight) {
/*  878 */       icon.paintIcon(c, g, x, y);
/*      */     } else {
/*      */       
/*  881 */       icon.paintIcon(c, g, x - 4, y);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
/*  887 */     JMenuItem b = (JMenuItem)c;
/*  888 */     ButtonModel model = b.getModel();
/*      */     
/*  890 */     JComponent p = (JComponent)b.getParent();
/*      */     
/*  892 */     boolean isLeftToRight = p.getComponentOrientation().isLeftToRight();
/*      */     
/*  894 */     Integer maxValueInt = (Integer)p.getClientProperty("maxIconWidth");
/*  895 */     int maxValue = (maxValueInt == null) ? 
/*  896 */       16 : 
/*  897 */       maxValueInt.intValue();
/*  898 */     int menuWidth = b.getWidth();
/*  899 */     int menuHeight = b.getHeight();
/*  900 */     Insets i = c.getInsets();
/*  901 */     resetRects();
/*  902 */     viewRect.setBounds(0, 0, menuWidth, menuHeight);
/*  903 */     viewRect.x += 0;
/*  904 */     viewRect.y += i.top;
/*  905 */     viewRect.width -= i.right + viewRect.x;
/*  906 */     viewRect.height -= i.bottom + viewRect.y;
/*  907 */     Font holdf = g.getFont();
/*  908 */     Font f = c.getFont();
/*  909 */     g.setFont(f);
/*  910 */     FontMetrics fm = g.getFontMetrics(f);
/*  911 */     FontMetrics fmAccel = g.getFontMetrics(this.m_AcceleratorFont);
/*      */     
/*  913 */     KeyStroke accelerator = b.getAccelerator();
/*  914 */     String acceleratorText = "";
/*  915 */     if (accelerator != null) {
/*      */       
/*  917 */       int modifiers = accelerator.getModifiers();
/*  918 */       if (modifiers > 0) {
/*      */         
/*  920 */         acceleratorText = KeyEvent.getKeyModifiersText(modifiers);
/*      */         
/*  922 */         acceleratorText = String.valueOf(acceleratorText) + this.m_AcceleratorDelimiter;
/*      */       } 
/*  924 */       int keyCode = accelerator.getKeyCode();
/*  925 */       if (keyCode != 0) {
/*      */         
/*  927 */         acceleratorText = String.valueOf(acceleratorText) + KeyEvent.getKeyText(keyCode);
/*      */       }
/*      */       else {
/*      */         
/*  931 */         acceleratorText = String.valueOf(acceleratorText) + accelerator.getKeyChar();
/*      */       } 
/*      */     } 
/*  934 */     int offset = 0;
/*      */     
/*  936 */     Icon ic = b.getIcon();
/*      */     
/*  938 */     Icon paintIcon = ic;
/*  939 */     if (useCheckAndArrow())
/*      */     {
/*  941 */       if (c instanceof javax.swing.JCheckBoxMenuItem || c instanceof javax.swing.JRadioButtonMenuItem) {
/*      */         
/*  943 */         ic = checkIcon;
/*  944 */         if (checkIcon.getIconWidth() < maxValue) {
/*      */           
/*  946 */           ic = new EmptyIcon(maxValue, checkIcon.getIconHeight());
/*  947 */           offset = (maxValue - checkIcon.getIconWidth()) / 2;
/*      */         } 
/*  949 */         paintIcon = null;
/*      */       }
/*  951 */       else if (c instanceof JMenuItem) {
/*      */         
/*  953 */         if (ic == null || ic.getIconWidth() < maxValue) {
/*      */           
/*  955 */           int height = (ic == null) ? 
/*  956 */             2 : 
/*  957 */             b.getIcon().getIconHeight();
/*  958 */           int width = (ic == null) ? 
/*  959 */             2 : 
/*  960 */             b.getIcon().getIconWidth();
/*  961 */           offset = (maxValue - width) / 2;
/*  962 */           ic = new EmptyIcon(maxValue, height);
/*      */         } 
/*      */       } 
/*      */     }
/*  966 */     String text = layoutMenuItem(fm, b.getText(), fmAccel, acceleratorText, ic, null, 
/*  967 */         arrowIcon, b.getVerticalAlignment(), b.getHorizontalAlignment(), b.getVerticalTextPosition(), b
/*  968 */         .getHorizontalTextPosition(), viewRect, iconRect, textRect, acceleratorRect, checkIconRect, arrowIconRect, 
/*  969 */         (b.getText() == null) ? 
/*  970 */         0 : 
/*  971 */         defaultTextIconGap, ms_DefaultIconGap);
/*      */     
/*  973 */     paintBackground(g, b, background);
/*  974 */     Color holdc = g.getColor();
/*      */     
/*  976 */     if (checkIcon != null) {
/*      */       
/*  978 */       if (model.isArmed() || (c instanceof JMenu && model.isSelected())) {
/*      */         
/*  980 */         g.setColor(foreground);
/*      */       }
/*      */       else {
/*      */         
/*  984 */         g.setColor(holdc);
/*      */       } 
/*  986 */       if (useCheckAndArrow()) {
/*  987 */         paintMenuIcon(checkIcon, c, g, iconRect.x + offset, iconRect.y, isLeftToRight, menuWidth);
/*      */       }
/*  989 */       g.setColor(holdc);
/*      */     } 
/*      */     
/*  992 */     if (paintIcon != null)
/*      */     {
/*      */       
/*  995 */       if (!model.isEnabled()) {
/*      */         
/*  997 */         Icon icon = b.getDisabledIcon();
/*  998 */         if (icon != null) {
/*  999 */           paintMenuIcon(icon, c, g, iconRect.x + offset, iconRect.y, isLeftToRight, menuWidth);
/*      */         }
/* 1001 */       } else if (model.isPressed() && model.isArmed()) {
/*      */         
/* 1003 */         Icon icon = b.getPressedIcon();
/* 1004 */         if (icon == null)
/*      */         {
/*      */           
/* 1007 */           icon = b.getIcon();
/*      */         }
/* 1009 */         if (icon != null)
/*      */         {
/* 1011 */           paintMenuIcon(icon, c, g, iconRect.x + offset, iconRect.y, isLeftToRight, menuWidth);
/*      */         }
/*      */       }
/* 1014 */       else if (model.isArmed() || model.isSelected()) {
/*      */         
/* 1016 */         Icon icon = b.getIcon();
/* 1017 */         if (icon != null)
/*      */         {
/* 1019 */           Icon disabled = b.getDisabledIcon();
/* 1020 */           if (disabled != null) {
/* 1021 */             paintMenuIcon(disabled, c, g, iconRect.x + offset + 1, iconRect.y + 1, isLeftToRight, menuWidth);
/*      */           }
/* 1023 */           paintMenuIcon(icon, c, g, iconRect.x + offset - 1, iconRect.y - 1, isLeftToRight, menuWidth);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1028 */         Icon icon = b.getIcon();
/* 1029 */         if (icon != null)
/*      */         {
/* 1031 */           paintMenuIcon(icon, c, g, iconRect.x + offset, iconRect.y, isLeftToRight, menuWidth);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1036 */     if (text != null) {
/*      */       
/* 1038 */       View v = (View)c.getClientProperty("html");
/* 1039 */       if (v != null) {
/*      */         
/* 1041 */         v.paint(g, textRect);
/*      */       }
/*      */       else {
/*      */         
/* 1045 */         paintText(g, b, textRect, text);
/*      */       } 
/*      */     } 
/*      */     
/* 1049 */     if (acceleratorText != null && !acceleratorText.equals("")) {
/*      */ 
/*      */       
/* 1052 */       int accOffset = 0;
/* 1053 */       Container parent = this.m_MenuItem.getParent();
/* 1054 */       if (parent != null && parent instanceof JComponent) {
/*      */         
/* 1056 */         Integer amaxValueInt = (Integer)p.getClientProperty("maxAccWidth");
/* 1057 */         int amaxValue = (amaxValueInt != null) ? 
/* 1058 */           amaxValueInt.intValue() : 
/* 1059 */           acceleratorRect.width;
/*      */         
/* 1061 */         accOffset = amaxValue - acceleratorRect.width;
/*      */       } 
/* 1063 */       g.setFont(this.m_AcceleratorFont);
/* 1064 */       if (!model.isEnabled()) {
/*      */ 
/*      */         
/* 1067 */         if (this.m_DisabledForeground != null)
/*      */         {
/* 1069 */           g.setColor(this.m_DisabledForeground);
/* 1070 */           BasicGraphicsUtils.drawString(g, acceleratorText, 0, acceleratorRect.x - accOffset, acceleratorRect.y + 
/* 1071 */               fmAccel.getAscent());
/*      */         }
/*      */         else
/*      */         {
/* 1075 */           g.setColor(b.getBackground().brighter());
/* 1076 */           BasicGraphicsUtils.drawString(g, acceleratorText, 0, acceleratorRect.x - accOffset, acceleratorRect.y + 
/* 1077 */               fmAccel.getAscent());
/* 1078 */           g.setColor(b.getBackground().darker());
/* 1079 */           BasicGraphicsUtils.drawString(g, acceleratorText, 0, acceleratorRect.x - accOffset - 1, acceleratorRect.y + 
/* 1080 */               fmAccel.getAscent() - 1);
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1086 */         if (model.isArmed() || (c instanceof JMenu && model.isSelected())) {
/*      */           
/* 1088 */           g.setColor(this.m_AcceleratorSelectionForeground);
/*      */         }
/*      */         else {
/*      */           
/* 1092 */           g.setColor(this.m_AcceleratorForeground);
/*      */         } 
/* 1094 */         BasicGraphicsUtils.drawString(g, acceleratorText, 0, acceleratorRect.x - accOffset, acceleratorRect.y + 
/* 1095 */             fmAccel.getAscent());
/*      */       } 
/*      */     } 
/*      */     
/* 1099 */     if (arrowIcon != null) {
/*      */       
/* 1101 */       if (model.isArmed() || (c instanceof JMenu && model.isSelected())) {
/* 1102 */         g.setColor(foreground);
/*      */       }
/* 1104 */       if (useCheckAndArrow())
/*      */       {
/*      */         
/* 1107 */         paintMenuIcon(arrowIcon, c, g, arrowIconRect.x, arrowIconRect.y, isLeftToRight, menuWidth);
/*      */       }
/*      */     } 
/* 1110 */     g.setColor(holdc);
/* 1111 */     g.setFont(holdf);
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
/*      */   protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
/* 1124 */     ButtonModel model = menuItem.getModel();
/*      */     
/* 1126 */     int menuWidth = menuItem.getWidth();
/* 1127 */     int menuHeight = menuItem.getHeight();
/* 1128 */     boolean mouseOver = !(!model.isArmed() && (!(menuItem instanceof JMenu) || !model.isSelected()));
/* 1129 */     int maxValue = menuWidth;
/*      */     
/* 1131 */     boolean isLeftToRight = menuItem.getParent().getComponentOrientation().isLeftToRight();
/*      */     
/* 1133 */     if (useCheckAndArrow()) {
/*      */       
/* 1135 */       JComponent p = (JComponent)menuItem.getParent();
/* 1136 */       Integer maxValueInt = (Integer)p.getClientProperty("maxIconWidth");
/* 1137 */       maxValue = (maxValueInt == null) ? 
/* 1138 */         16 : 
/* 1139 */         maxValueInt.intValue();
/* 1140 */       maxValue += ms_DefaultTextIconGap;
/* 1141 */       getSkin().draw(g, model.isEnabled(), mouseOver, false, false, menuWidth, maxValue, menuHeight, isLeftToRight);
/*      */     }
/*      */     else {
/*      */       
/* 1145 */       boolean isRollover = (menuItem.getClientProperty("rollover") == Boolean.TRUE);
/*      */ 
/*      */       
/* 1148 */       int index = 2;
/* 1149 */       if (!model.isEnabled()) {
/* 1150 */         index = 2;
/* 1151 */       } else if (model.isSelected()) {
/* 1152 */         index = 1;
/* 1153 */       } else if (isRollover) {
/* 1154 */         index = 0;
/* 1155 */       }  getTopSkin().draw(g, index, menuWidth, menuHeight);
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
/*      */   private String layoutMenuItem(FontMetrics fm, String text, FontMetrics fmAccel, String acceleratorText, Icon icon, Icon checkIcon, Icon arrowIcon, int verticalAlignment, int horizontalAlignment, int verticalTextPosition, int horizontalTextPosition, Rectangle viewRect, Rectangle iconRect, Rectangle textRect, Rectangle acceleratorRect, Rectangle checkIconRect, Rectangle arrowIconRect, int textIconGap, int menuItemGap) {
/* 1170 */     SwingUtilities.layoutCompoundLabel(this.m_MenuItem, fm, text, icon, verticalAlignment, horizontalAlignment, 
/* 1171 */         verticalTextPosition, horizontalTextPosition, viewRect, iconRect, textRect, textIconGap);
/*      */     
/* 1173 */     boolean isLeftToRight = true;
/*      */     
/* 1175 */     if (this.m_MenuItem != null) {
/*      */       
/* 1177 */       ComponentOrientation parentOrientation = this.m_MenuItem.getParent().getComponentOrientation();
/* 1178 */       if (!this.m_MenuItem.getComponentOrientation().equals(parentOrientation)) {
/* 1179 */         this.m_MenuItem.setComponentOrientation(parentOrientation);
/*      */       }
/* 1181 */       isLeftToRight = parentOrientation.isLeftToRight();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1188 */     if (acceleratorText == null || acceleratorText.equals("")) {
/*      */       
/* 1190 */       acceleratorRect.width = acceleratorRect.height = 0;
/* 1191 */       acceleratorText = "";
/*      */     }
/*      */     else {
/*      */       
/* 1195 */       acceleratorRect.width = SwingUtilities.computeStringWidth(fmAccel, acceleratorText);
/* 1196 */       acceleratorRect.height = fmAccel.getHeight();
/*      */     } 
/*      */ 
/*      */     
/* 1200 */     if (useCheckAndArrow()) {
/*      */       
/* 1202 */       if (checkIcon != null) {
/*      */         
/* 1204 */         checkIconRect.height = checkIcon.getIconHeight();
/* 1205 */         checkIconRect.width = checkIcon.getIconWidth();
/*      */       }
/*      */       else {
/*      */         
/* 1209 */         checkIconRect.width = checkIconRect.height = 0;
/*      */       } 
/*      */ 
/*      */       
/* 1213 */       if (arrowIcon != null)
/*      */       {
/* 1215 */         arrowIconRect.width = arrowIcon.getIconWidth();
/* 1216 */         arrowIconRect.height = arrowIcon.getIconHeight();
/*      */       }
/*      */       else
/*      */       {
/* 1220 */         arrowIconRect.width = arrowIconRect.height = 0;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1225 */       checkIconRect.width = checkIconRect.height = 0;
/* 1226 */       arrowIconRect.width = arrowIconRect.height = 0;
/*      */     } 
/* 1228 */     Rectangle labelRect = iconRect.union(textRect);
/*      */     
/* 1230 */     if (checkIcon != null) {
/*      */       
/* 1232 */       checkIconRect.x += menuItemGap;
/*      */     }
/*      */     else {
/*      */       
/* 1236 */       if (isLeftToRight) {
/* 1237 */         textRect.x += menuItemGap;
/*      */       } else {
/*      */         
/* 1240 */         textRect.x -= menuItemGap;
/*      */         
/* 1242 */         if (icon != null && 
/* 1243 */           !(icon instanceof EmptyIcon)) {
/* 1244 */           textRect.x = textRect.x - menuItemGap - icon.getIconWidth();
/*      */         }
/*      */       } 
/* 1247 */       iconRect.x += menuItemGap;
/*      */     } 
/*      */     
/* 1250 */     if (isLeftToRight) {
/* 1251 */       acceleratorRect.x = viewRect.x + viewRect.width - arrowIconRect.width - menuItemGap - acceleratorRect.width;
/*      */     } else {
/* 1253 */       acceleratorRect.x = viewRect.x + viewRect.width - 2 * viewRect.width / 3 - acceleratorRect.width + menuItemGap + arrowIconRect.width;
/*      */     } 
/*      */     
/* 1256 */     if (!isLeftToRight)
/*      */     {
/* 1258 */       if (icon != null && 
/* 1259 */         !(icon instanceof EmptyIcon)) {
/* 1260 */         iconRect.x = viewRect.x + viewRect.width - iconRect.width + menuItemGap;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 1265 */     acceleratorRect.y = labelRect.y + labelRect.height / 2 - acceleratorRect.height / 2;
/* 1266 */     if (useCheckAndArrow()) {
/*      */       
/* 1268 */       arrowIconRect.y = labelRect.y + labelRect.height / 2 - arrowIconRect.height / 2;
/* 1269 */       checkIconRect.y = labelRect.y + labelRect.height / 2 - checkIconRect.height / 2;
/*      */       
/* 1271 */       arrowIconRect.x = viewRect.x + viewRect.width - menuItemGap - arrowIconRect.width;
/*      */     } 
/*      */     
/* 1274 */     return text;
/*      */   }
/*      */   
/*      */   protected abstract SkinnedMenuItem getSkin();
/*      */   
/*      */   protected abstract SkinImage getTopSkin();
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedMenuItemUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */