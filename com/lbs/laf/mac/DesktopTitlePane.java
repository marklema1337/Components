/*      */ package com.lbs.laf.mac;
/*      */ 
/*      */ import com.lbs.globalization.ILbsCultureInfo;
/*      */ import com.lbs.resource.JLbsLocalizer;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dialog;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Frame;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Image;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.event.WindowListener;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JRootPane;
/*      */ import javax.swing.JSeparator;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.plaf.basic.BasicButtonUI;
/*      */ import javax.swing.plaf.metal.MetalLookAndFeel;
/*      */ import sun.awt.SunToolkit;
/*      */ import sun.swing.SwingUtilities2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class DesktopTitlePane
/*      */   extends JComponent
/*      */ {
/*   53 */   private static final Border handyEmptyBorder = new EmptyBorder(0, 0, 0, 0);
/*      */   
/*      */   private static final int IMAGE_HEIGHT = 16;
/*      */   private static final int IMAGE_WIDTH = 16;
/*      */   private static final int CHAR_BUFFER_SIZE = 100;
/*   58 */   private final Object charsBufferLock = new Object();
/*   59 */   private char[] charsBuffer = new char[100];
/*      */   
/*      */   private PropertyChangeListener propertyChangeListener;
/*      */   
/*      */   private JMenuBar menuBar;
/*      */   
/*      */   private Action closeAction;
/*      */   
/*      */   private Action iconifyAction;
/*      */   
/*      */   private Action restoreAction;
/*      */   
/*      */   private Action maximizeAction;
/*      */   
/*      */   private JButton toggleButton;
/*      */   
/*      */   private JButton iconifyButton;
/*      */   
/*      */   private JButton closeButton;
/*      */   
/*      */   private Icon maximizeIcon;
/*      */   
/*      */   private Icon minimizeIcon;
/*      */   
/*      */   private Image systemIcon;
/*      */   
/*      */   private WindowListener windowListener;
/*      */   
/*      */   private Window window;
/*      */   
/*      */   private JRootPane rootPane;
/*      */   
/*      */   private int buttonsWidth;
/*      */   
/*      */   private int state;
/*      */   
/*      */   private DesktopRootPaneUI rootPaneUI;
/*      */   
/*   97 */   private Color inactiveBackground = UIManager.getColor("inactiveCaption");
/*   98 */   private Color inactiveForeground = UIManager.getColor("inactiveCaptionText");
/*   99 */   private Color inactiveShadow = UIManager.getColor("inactiveCaptionBorder");
/*  100 */   private Color activeBumpsHighlight = MetalLookAndFeel.getPrimaryControlHighlight();
/*  101 */   private Color activeBumpsShadow = MetalLookAndFeel.getPrimaryControlDarkShadow();
/*  102 */   private Color activeBackground = null;
/*  103 */   private Color activeForeground = null;
/*  104 */   private Color activeShadow = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Icon m_icon;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DesktopTitlePane(JRootPane root, DesktopRootPaneUI ui) {
/*  121 */     this.rootPane = root;
/*  122 */     this.rootPaneUI = ui;
/*      */     
/*  124 */     this.state = -1;
/*      */     
/*  126 */     installSubcomponents();
/*  127 */     determineColors();
/*  128 */     installDefaults();
/*      */     
/*  130 */     setLayout(createLayout());
/*      */   }
/*      */ 
/*      */   
/*      */   private void uninstall() {
/*  135 */     uninstallListeners();
/*  136 */     this.window = null;
/*  137 */     removeAll();
/*      */   }
/*      */ 
/*      */   
/*      */   private void installListeners() {
/*  142 */     if (this.window != null) {
/*      */       
/*  144 */       this.windowListener = createWindowListener();
/*  145 */       this.window.addWindowListener(this.windowListener);
/*  146 */       this.propertyChangeListener = createWindowPropertyChangeListener();
/*  147 */       this.window.addPropertyChangeListener(this.propertyChangeListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void uninstallListeners() {
/*  153 */     if (this.window != null) {
/*      */       
/*  155 */       this.window.removeWindowListener(this.windowListener);
/*  156 */       this.window.removePropertyChangeListener(this.propertyChangeListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private WindowListener createWindowListener() {
/*  162 */     return new WindowHandler(null);
/*      */   }
/*      */ 
/*      */   
/*      */   private PropertyChangeListener createWindowPropertyChangeListener() {
/*  167 */     return new PropertyChangeHandler(null);
/*      */   }
/*      */ 
/*      */   
/*      */   public JRootPane getRootPane() {
/*  172 */     return this.rootPane;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getWindowDecorationStyle() {
/*  177 */     return getRootPane().getWindowDecorationStyle();
/*      */   }
/*      */ 
/*      */   
/*      */   public void addNotify() {
/*  182 */     super.addNotify();
/*      */     
/*  184 */     uninstallListeners();
/*      */     
/*  186 */     this.window = SwingUtilities.getWindowAncestor(this);
/*  187 */     if (this.window != null) {
/*      */       
/*  189 */       ILbsCultureInfo cul = JLbsLocalizer.getCultureInfo();
/*  190 */       if (cul != null) {
/*      */         
/*  192 */         ComponentOrientation orientation = cul.getComponentOrientation();
/*  193 */         if (orientation != null) {
/*  194 */           this.window.setComponentOrientation(orientation);
/*      */         }
/*      */       } 
/*  197 */       if (this.window instanceof Frame) {
/*      */         
/*  199 */         setState(((Frame)this.window).getExtendedState());
/*      */       }
/*      */       else {
/*      */         
/*  203 */         setState(0);
/*      */       } 
/*  205 */       setActive(this.window.isActive());
/*  206 */       installListeners();
/*  207 */       updateSystemIcon();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeNotify() {
/*  213 */     super.removeNotify();
/*      */     
/*  215 */     uninstallListeners();
/*  216 */     this.window = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void installSubcomponents() {
/*  221 */     int decorationStyle = getWindowDecorationStyle();
/*  222 */     if (decorationStyle == 1) {
/*      */       
/*  224 */       createActions();
/*  225 */       this.menuBar = createMenuBar();
/*  226 */       add(this.menuBar);
/*  227 */       createButtons();
/*  228 */       add(this.iconifyButton);
/*  229 */       add(this.toggleButton);
/*  230 */       add(this.closeButton);
/*      */     }
/*  232 */     else if (decorationStyle == 2 || decorationStyle == 3 || 
/*  233 */       decorationStyle == 4 || decorationStyle == 5 || 
/*  234 */       decorationStyle == 6 || decorationStyle == 7 || 
/*  235 */       decorationStyle == 8) {
/*      */       
/*  237 */       createActions();
/*  238 */       createButtons();
/*  239 */       add(this.closeButton);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void determineColors() {
/*  245 */     getWindowDecorationStyle();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  272 */     this.activeBackground = UIManager.getColor("activeCaption");
/*  273 */     this.activeForeground = UIManager.getColor("activeCaptionText");
/*  274 */     this.activeShadow = UIManager.getColor("activeCaptionBorder");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void installDefaults() {
/*  283 */     setFont(UIManager.getFont("InternalFrame.titleFont", getLocale()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void uninstallDefaults() {}
/*      */ 
/*      */   
/*      */   protected JMenuBar createMenuBar() {
/*  292 */     this.menuBar = new SystemMenuBar(null);
/*  293 */     this.menuBar.setFocusable(false);
/*  294 */     this.menuBar.setBorderPainted(true);
/*      */     
/*  296 */     return this.menuBar;
/*      */   }
/*      */ 
/*      */   
/*      */   private void close() {
/*  301 */     Window window = getWindow();
/*      */     
/*  303 */     if (window != null)
/*      */     {
/*  305 */       window.dispatchEvent(new WindowEvent(window, 201));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void iconify() {
/*  311 */     Frame frame = getFrame();
/*  312 */     if (frame != null)
/*      */     {
/*  314 */       frame.setExtendedState(this.state | 0x1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void maximize() {
/*  320 */     Frame frame = getFrame();
/*  321 */     if (frame != null) {
/*      */       
/*  323 */       if (JLbsConstants.WINDOW_MODE && this.state == 0)
/*  324 */         frame.setSize(new Dimension(1024, 768)); 
/*  325 */       frame.setExtendedState(this.state | 0x6);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void restore() {
/*  331 */     Frame frame = getFrame();
/*      */     
/*  333 */     if (frame == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  338 */     if ((this.state & 0x1) != 0) {
/*      */       
/*  340 */       frame.setExtendedState(this.state & 0xFFFFFFFE);
/*      */     }
/*      */     else {
/*      */       
/*  344 */       frame.setExtendedState(this.state & 0xFFFFFFF9);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void createActions() {
/*  350 */     this.closeAction = new CloseAction();
/*  351 */     if (getWindowDecorationStyle() == 1) {
/*      */       
/*  353 */       this.iconifyAction = new IconifyAction();
/*  354 */       this.restoreAction = new RestoreAction();
/*  355 */       this.maximizeAction = new MaximizeAction();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private JMenu createMenu() {
/*  361 */     JMenu menu = new JMenu("");
/*  362 */     if (getWindowDecorationStyle() == 1)
/*      */     {
/*  364 */       addMenuItems(menu);
/*      */     }
/*  366 */     return menu;
/*      */   }
/*      */ 
/*      */   
/*      */   private void addMenuItems(JMenu menu) {
/*  371 */     Locale locale = getRootPane().getLocale();
/*  372 */     JMenuItem mi = menu.add(this.restoreAction);
/*  373 */     int mnemonic = getInt("MetalTitlePane.restoreMnemonic", -1);
/*      */     
/*  375 */     if (mnemonic != -1)
/*      */     {
/*  377 */       mi.setMnemonic(mnemonic);
/*      */     }
/*      */     
/*  380 */     mi = menu.add(this.iconifyAction);
/*  381 */     mnemonic = getInt("MetalTitlePane.iconifyMnemonic", -1);
/*  382 */     if (mnemonic != -1)
/*      */     {
/*  384 */       mi.setMnemonic(mnemonic);
/*      */     }
/*      */     
/*  387 */     if (Toolkit.getDefaultToolkit().isFrameStateSupported(6)) {
/*      */       
/*  389 */       mi = menu.add(this.maximizeAction);
/*  390 */       mnemonic = getInt("MetalTitlePane.maximizeMnemonic", -1);
/*  391 */       if (mnemonic != -1)
/*      */       {
/*  393 */         mi.setMnemonic(mnemonic);
/*      */       }
/*      */     } 
/*      */     
/*  397 */     menu.add(new JSeparator());
/*      */     
/*  399 */     mi = menu.add(this.closeAction);
/*  400 */     mnemonic = getInt("MetalTitlePane.closeMnemonic", -1);
/*  401 */     if (mnemonic != -1)
/*      */     {
/*  403 */       mi.setMnemonic(mnemonic);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static int getInt(Object key, int defaultValue) {
/*  409 */     Object value = UIManager.get(key);
/*      */     
/*  411 */     if (value instanceof Integer)
/*      */     {
/*  413 */       return ((Integer)value).intValue();
/*      */     }
/*  415 */     if (value instanceof String) {
/*      */       
/*      */       try {
/*      */         
/*  419 */         return Integer.parseInt((String)value);
/*      */       }
/*  421 */       catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */ 
/*      */     
/*  425 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   
/*      */   private JButton createTitleButton() {
/*  430 */     JButton button = new JButton()
/*      */       {
/*      */         
/*      */         public void updateUI()
/*      */         {
/*  435 */           setUI(new BasicButtonUI());
/*      */         }
/*      */       };
/*      */     
/*  439 */     button.setFocusPainted(false);
/*  440 */     button.setFocusable(false);
/*  441 */     button.setOpaque(false);
/*  442 */     return button;
/*      */   }
/*      */ 
/*      */   
/*      */   private void createButtons() {
/*  447 */     this.closeButton = createTitleButton();
/*  448 */     this.closeButton.setAction(this.closeAction);
/*  449 */     this.closeButton.setText((String)null);
/*  450 */     this.closeButton.putClientProperty("paintActive", Boolean.TRUE);
/*  451 */     this.closeButton.setBorder(handyEmptyBorder);
/*  452 */     this.closeButton.putClientProperty("AccessibleName", "Close");
/*  453 */     this.closeButton.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
/*      */     
/*  455 */     if (getWindowDecorationStyle() == 1) {
/*      */       
/*  457 */       this.maximizeIcon = UIManager.getIcon("InternalFrame.maximizeIcon");
/*  458 */       this.minimizeIcon = UIManager.getIcon("InternalFrame.minimizeIcon");
/*      */       
/*  460 */       this.iconifyButton = createTitleButton();
/*  461 */       this.iconifyButton.setAction(this.iconifyAction);
/*  462 */       this.iconifyButton.setText((String)null);
/*  463 */       this.iconifyButton.putClientProperty("paintActive", Boolean.TRUE);
/*  464 */       this.iconifyButton.setBorder(handyEmptyBorder);
/*  465 */       this.iconifyButton.putClientProperty("AccessibleName", "Iconify");
/*  466 */       this.iconifyButton.setIcon(UIManager.getIcon("InternalFrame.iconifyIcon"));
/*      */       
/*  468 */       this.toggleButton = createTitleButton();
/*  469 */       this.toggleButton.setAction(this.restoreAction);
/*  470 */       this.toggleButton.putClientProperty("paintActive", Boolean.TRUE);
/*  471 */       this.toggleButton.setBorder(handyEmptyBorder);
/*  472 */       this.toggleButton.putClientProperty("AccessibleName", "Maximize");
/*  473 */       this.toggleButton.setIcon(this.maximizeIcon);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private LayoutManager createLayout() {
/*  479 */     return new TitlePaneLayout(null);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setActive(boolean isActive) {
/*  484 */     Boolean activeB = isActive ? 
/*  485 */       Boolean.TRUE : 
/*  486 */       Boolean.FALSE;
/*      */     
/*  488 */     this.closeButton.putClientProperty("paintActive", activeB);
/*  489 */     if (getWindowDecorationStyle() == 1) {
/*      */       
/*  491 */       this.iconifyButton.putClientProperty("paintActive", activeB);
/*  492 */       this.toggleButton.putClientProperty("paintActive", activeB);
/*      */     } 
/*      */ 
/*      */     
/*  496 */     getRootPane().repaint();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setState(int state) {
/*  501 */     setState(state, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setState(int state, boolean updateRegardless) {
/*  506 */     Window w = getWindow();
/*      */     
/*  508 */     if (w != null && getWindowDecorationStyle() == 1) {
/*      */       
/*  510 */       if (this.state == state && !updateRegardless) {
/*      */         return;
/*      */       }
/*      */       
/*  514 */       Frame frame = getFrame();
/*      */       
/*  516 */       if (frame != null) {
/*      */         
/*  518 */         JRootPane rootPane = getRootPane();
/*      */         
/*  520 */         if ((state & 0x6) != 0 && (
/*  521 */           rootPane.getBorder() == null || rootPane.getBorder() instanceof javax.swing.plaf.UIResource) && frame.isShowing()) {
/*      */           
/*  523 */           rootPane.setBorder((Border)null);
/*      */         }
/*  525 */         else if ((state & 0x6) == 0) {
/*      */ 
/*      */ 
/*      */           
/*  529 */           this.rootPaneUI.installBorder(rootPane);
/*      */         } 
/*  531 */         if (frame.isResizable())
/*      */         {
/*  533 */           if ((state & 0x6) != 0) {
/*      */             
/*  535 */             updateToggleButton(this.restoreAction, this.minimizeIcon);
/*  536 */             this.maximizeAction.setEnabled(false);
/*  537 */             this.restoreAction.setEnabled(true);
/*      */           }
/*      */           else {
/*      */             
/*  541 */             updateToggleButton(this.maximizeAction, this.maximizeIcon);
/*  542 */             this.maximizeAction.setEnabled(true);
/*  543 */             this.restoreAction.setEnabled(false);
/*      */           } 
/*  545 */           if (this.toggleButton.getParent() == null || this.iconifyButton.getParent() == null) {
/*      */             
/*  547 */             add(this.toggleButton);
/*  548 */             add(this.iconifyButton);
/*  549 */             revalidate();
/*  550 */             repaint();
/*      */           } 
/*  552 */           this.toggleButton.setText((String)null);
/*      */         }
/*      */         else
/*      */         {
/*  556 */           this.maximizeAction.setEnabled(false);
/*  557 */           this.restoreAction.setEnabled(false);
/*  558 */           if (this.toggleButton.getParent() != null)
/*      */           {
/*  560 */             remove(this.toggleButton);
/*  561 */             revalidate();
/*  562 */             repaint();
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  569 */         this.maximizeAction.setEnabled(false);
/*  570 */         this.restoreAction.setEnabled(false);
/*  571 */         this.iconifyAction.setEnabled(false);
/*  572 */         remove(this.toggleButton);
/*  573 */         remove(this.iconifyButton);
/*  574 */         revalidate();
/*  575 */         repaint();
/*      */       } 
/*  577 */       this.closeAction.setEnabled(true);
/*  578 */       this.state = state;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateToggleButton(Action action, Icon icon) {
/*  584 */     this.toggleButton.setAction(action);
/*  585 */     this.toggleButton.setIcon(icon);
/*  586 */     this.toggleButton.setText((String)null);
/*      */   }
/*      */ 
/*      */   
/*      */   private Frame getFrame() {
/*  591 */     Window window = getWindow();
/*      */     
/*  593 */     if (window instanceof Frame)
/*      */     {
/*  595 */       return (Frame)window;
/*      */     }
/*  597 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private Window getWindow() {
/*  602 */     return this.window;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getTitle() {
/*  607 */     Window w = getWindow();
/*      */     
/*  609 */     if (w instanceof Frame)
/*      */     {
/*  611 */       return ((Frame)w).getTitle();
/*      */     }
/*  613 */     if (w instanceof Dialog)
/*      */     {
/*  615 */       return ((Dialog)w).getTitle();
/*      */     }
/*  617 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void paintComponent(Graphics g) {
/*      */     Color background, foreground, darkShadow;
/*  624 */     if (getFrame() != null)
/*      */     {
/*  626 */       setState(getFrame().getExtendedState());
/*      */     }
/*  628 */     JRootPane rootPane = getRootPane();
/*  629 */     Window window = getWindow();
/*  630 */     boolean leftToRight = (window == null) ? 
/*  631 */       rootPane.getComponentOrientation().isLeftToRight() : 
/*  632 */       window.getComponentOrientation().isLeftToRight();
/*  633 */     boolean isSelected = (window == null) ? true : 
/*      */       
/*  635 */       window.isActive();
/*  636 */     int width = getWidth();
/*  637 */     int height = getHeight();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  645 */     if (isSelected) {
/*      */       
/*  647 */       background = this.activeBackground;
/*  648 */       foreground = this.activeForeground;
/*  649 */       darkShadow = this.activeShadow;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  654 */       background = this.inactiveBackground;
/*  655 */       foreground = this.inactiveForeground;
/*  656 */       darkShadow = this.inactiveShadow;
/*      */     } 
/*      */ 
/*      */     
/*  660 */     g.setColor(background);
/*  661 */     g.fillRect(0, 0, width, height);
/*      */     
/*  663 */     g.setColor(darkShadow);
/*  664 */     g.drawLine(0, height - 1, width, height - 1);
/*  665 */     g.drawLine(0, 0, 0, 0);
/*  666 */     g.drawLine(width - 1, 0, width - 1, 0);
/*      */     
/*  668 */     int xOffset = leftToRight ? 
/*  669 */       5 : (
/*  670 */       width - 5);
/*      */     
/*  672 */     if (getWindowDecorationStyle() == 1) {
/*      */       
/*  674 */       xOffset += leftToRight ? 
/*  675 */         21 : 
/*  676 */         -21;
/*      */     }
/*      */     else {
/*      */       
/*  680 */       this.m_icon = UIManager.getIcon("InternalFrame.icon");
/*      */       
/*  682 */       if (this.m_icon != null) {
/*      */         
/*  684 */         if (!leftToRight)
/*  685 */           xOffset -= this.m_icon.getIconWidth(); 
/*  686 */         int y = (height - this.m_icon.getIconHeight()) / 2;
/*  687 */         this.m_icon.paintIcon(this, g, xOffset, y);
/*      */         
/*  689 */         xOffset += leftToRight ? (
/*  690 */           this.m_icon.getIconWidth() + 5) : 
/*  691 */           -5;
/*      */       } 
/*      */     } 
/*      */     
/*  695 */     String theTitle = getTitle();
/*  696 */     if (theTitle != null) {
/*      */       
/*  698 */       FontMetrics fm = SwingUtilities2.getFontMetrics(rootPane, g);
/*      */       
/*  700 */       g.setColor(foreground);
/*      */       
/*  702 */       int yOffset = (height - fm.getHeight()) / 2 + fm.getAscent();
/*      */       
/*  704 */       Rectangle rect = new Rectangle(0, 0, 0, 0);
/*  705 */       if (this.iconifyButton != null && this.iconifyButton.getParent() != null)
/*      */       {
/*  707 */         rect = this.iconifyButton.getBounds();
/*      */       }
/*      */ 
/*      */       
/*  711 */       if (leftToRight) {
/*      */         
/*  713 */         if (rect.x == 0)
/*      */         {
/*  715 */           rect.x = window.getWidth() - (window.getInsets()).right - 2;
/*      */         }
/*  717 */         int titleW = rect.x - xOffset - this.buttonsWidth - 10 - 4;
/*  718 */         theTitle = SwingUtilities2.clipStringIfNecessary(rootPane, fm, theTitle, titleW);
/*      */       }
/*      */       else {
/*      */         
/*  722 */         int titleW = xOffset - rect.x - this.buttonsWidth - 10 - rect.width - 4;
/*  723 */         theTitle = clipForRight2Left(getTitle(), fm, titleW);
/*  724 */         xOffset -= SwingUtilities2.stringWidth(rootPane, fm, theTitle);
/*      */       } 
/*  726 */       int titleLength = SwingUtilities2.stringWidth(rootPane, fm, theTitle);
/*  727 */       SwingUtilities2.drawString(rootPane, g, theTitle, xOffset, yOffset);
/*  728 */       xOffset += leftToRight ? (
/*  729 */         titleLength + 5) : 
/*  730 */         -5;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  735 */     if (leftToRight) {
/*      */       
/*  737 */       int bumpLength = width - this.buttonsWidth - xOffset - 5;
/*  738 */       int bumpXOffset = xOffset;
/*      */     }
/*      */     else {
/*      */       
/*  742 */       int bumpLength = xOffset - this.buttonsWidth - 5;
/*  743 */       int bumpXOffset = this.buttonsWidth + 5;
/*      */     } 
/*  745 */     int bumpYOffset = 3;
/*  746 */     int bumpHeight = getHeight() - 2 * bumpYOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String clipForRight2Left(String text, FontMetrics fm, int availTextWidth) {
/*  753 */     String clipString = "...";
/*  754 */     int stringWidth = SwingUtilities2.stringWidth(getRootPane(), fm, text);
/*      */     
/*  756 */     availTextWidth -= SwingUtilities2.stringWidth(getRootPane(), fm, clipString);
/*  757 */     if (availTextWidth <= 0)
/*  758 */       return clipString; 
/*  759 */     if (stringWidth < availTextWidth)
/*  760 */       return text; 
/*  761 */     synchronized (this.charsBufferLock) {
/*      */       
/*  763 */       int stringLength = syncCharsBuffer(text);
/*  764 */       int width = 0;
/*  765 */       for (int nChars = 0; nChars < stringLength; nChars++) {
/*      */         
/*  767 */         width += fm.charWidth(this.charsBuffer[nChars]);
/*  768 */         if (width > availTextWidth) {
/*      */           
/*  770 */           text = text.substring(0, nChars);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  775 */     return String.valueOf(text) + clipString;
/*      */   }
/*      */ 
/*      */   
/*      */   private int syncCharsBuffer(String s) {
/*  780 */     int length = s.length();
/*  781 */     if (this.charsBuffer == null || this.charsBuffer.length < length) {
/*      */       
/*  783 */       this.charsBuffer = s.toCharArray();
/*      */     }
/*      */     else {
/*      */       
/*  787 */       s.getChars(0, length, this.charsBuffer, 0);
/*      */     } 
/*  789 */     return length;
/*      */   }
/*      */   
/*      */   private class CloseAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public CloseAction() {
/*  796 */       super(UIManager.getString("MetalTitlePane.closeTitle", DesktopTitlePane.this.getLocale()));
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/*  801 */       DesktopTitlePane.this.close();
/*      */     }
/*      */   }
/*      */   
/*      */   private class IconifyAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public IconifyAction() {
/*  809 */       super(UIManager.getString("MetalTitlePane.iconifyTitle", DesktopTitlePane.this.getLocale()));
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/*  814 */       DesktopTitlePane.this.iconify();
/*      */     }
/*      */   }
/*      */   
/*      */   private class RestoreAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public RestoreAction() {
/*  822 */       super(UIManager.getString("MetalTitlePane.restoreTitle", DesktopTitlePane.this.getLocale()));
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/*  827 */       DesktopTitlePane.this.restore();
/*      */     }
/*      */   }
/*      */   
/*      */   private class MaximizeAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public MaximizeAction() {
/*  835 */       super(UIManager.getString("MetalTitlePane.maximizeTitle", DesktopTitlePane.this.getLocale()));
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/*  840 */       DesktopTitlePane.this.maximize();
/*      */     }
/*      */   }
/*      */   
/*      */   private class SystemMenuBar extends JMenuBar {
/*      */     private SystemMenuBar() {}
/*      */     
/*      */     public void paint(Graphics g) {
/*  848 */       if (isOpaque()) {
/*      */         
/*  850 */         g.setColor(getBackground());
/*  851 */         g.fillRect(0, 0, getWidth(), getHeight());
/*      */       } 
/*      */       
/*  854 */       if (DesktopTitlePane.this.systemIcon != null) {
/*      */         
/*  856 */         g.drawImage(DesktopTitlePane.this.systemIcon, 0, 0, 16, 16, null);
/*      */       }
/*      */       else {
/*      */         
/*  860 */         Icon icon = UIManager.getIcon("InternalFrame.icon");
/*      */         
/*  862 */         if (icon != null)
/*      */         {
/*  864 */           icon.paintIcon(this, g, 0, 0);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public Dimension getMinimumSize() {
/*  871 */       return getPreferredSize();
/*      */     }
/*      */ 
/*      */     
/*      */     public Dimension getPreferredSize() {
/*  876 */       Dimension size = super.getPreferredSize();
/*      */       
/*  878 */       return new Dimension(Math.max(16, size.width), Math.max(size.height, 16));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class TitlePaneLayout
/*      */     implements LayoutManager
/*      */   {
/*      */     private TitlePaneLayout() {}
/*      */ 
/*      */     
/*      */     public void addLayoutComponent(String name, Component c) {}
/*      */     
/*      */     public void removeLayoutComponent(Component c) {}
/*      */     
/*      */     public Dimension preferredLayoutSize(Container c) {
/*  894 */       int height = computeHeight();
/*  895 */       return new Dimension(height, height);
/*      */     }
/*      */ 
/*      */     
/*      */     public Dimension minimumLayoutSize(Container c) {
/*  900 */       return preferredLayoutSize(c);
/*      */     }
/*      */ 
/*      */     
/*      */     private int computeHeight() {
/*  905 */       FontMetrics fm = DesktopTitlePane.this.rootPane.getFontMetrics(DesktopTitlePane.this.getFont());
/*  906 */       int fontHeight = fm.getHeight();
/*  907 */       fontHeight += 7;
/*  908 */       int iconHeight = 0;
/*  909 */       if (DesktopTitlePane.this.getWindowDecorationStyle() == 1)
/*      */       {
/*  911 */         iconHeight = 16;
/*      */       }
/*      */       
/*  914 */       int finalHeight = Math.max(fontHeight, iconHeight);
/*  915 */       return finalHeight;
/*      */     }
/*      */     
/*      */     public void layoutContainer(Container c) {
/*      */       int buttonHeight, buttonWidth;
/*  920 */       boolean leftToRight = (DesktopTitlePane.this.window == null) ? 
/*  921 */         DesktopTitlePane.this.getRootPane().getComponentOrientation().isLeftToRight() : 
/*  922 */         DesktopTitlePane.this.window.getComponentOrientation().isLeftToRight();
/*      */       
/*  924 */       int w = DesktopTitlePane.this.getWidth();
/*      */       
/*  926 */       int y = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  931 */       if (DesktopTitlePane.this.closeButton != null && DesktopTitlePane.this.closeButton.getIcon() != null) {
/*      */         
/*  933 */         buttonHeight = DesktopTitlePane.this.closeButton.getIcon().getIconHeight();
/*  934 */         buttonWidth = DesktopTitlePane.this.closeButton.getIcon().getIconWidth();
/*      */       }
/*      */       else {
/*      */         
/*  938 */         buttonHeight = 16;
/*  939 */         buttonWidth = 16;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  945 */       int x = leftToRight ? 
/*  946 */         w : 
/*  947 */         0;
/*      */       
/*  949 */       int spacing = 5;
/*  950 */       x = leftToRight ? 
/*  951 */         spacing : (
/*  952 */         w - buttonWidth - spacing);
/*  953 */       if (DesktopTitlePane.this.menuBar != null)
/*      */       {
/*  955 */         DesktopTitlePane.this.menuBar.setBounds(x, y, buttonWidth, buttonHeight);
/*      */       }
/*      */       
/*  958 */       x = leftToRight ? 
/*  959 */         w : 
/*  960 */         0;
/*  961 */       spacing = 4;
/*  962 */       x += leftToRight ? (
/*  963 */         -spacing - buttonWidth) : 
/*  964 */         spacing;
/*  965 */       if (DesktopTitlePane.this.closeButton != null)
/*      */       {
/*  967 */         DesktopTitlePane.this.closeButton.setBounds(x, y, buttonWidth, buttonHeight);
/*      */       }
/*      */       
/*  970 */       if (!leftToRight) {
/*  971 */         x += buttonWidth;
/*      */       }
/*  973 */       if (DesktopTitlePane.this.getWindowDecorationStyle() == 1) {
/*      */         
/*  975 */         if (Toolkit.getDefaultToolkit().isFrameStateSupported(6))
/*      */         {
/*  977 */           if (DesktopTitlePane.this.toggleButton.getParent() != null) {
/*      */             
/*  979 */             spacing = 2;
/*  980 */             x += leftToRight ? (
/*  981 */               -spacing - buttonWidth) : 
/*  982 */               spacing;
/*  983 */             DesktopTitlePane.this.toggleButton.setBounds(x, y, buttonWidth, buttonHeight);
/*  984 */             if (!leftToRight)
/*      */             {
/*  986 */               x += buttonWidth;
/*      */             }
/*      */           } 
/*      */         }
/*      */         
/*  991 */         if (DesktopTitlePane.this.iconifyButton != null && DesktopTitlePane.this.iconifyButton.getParent() != null) {
/*      */           
/*  993 */           spacing = 2;
/*  994 */           x += leftToRight ? (
/*  995 */             -spacing - buttonWidth) : 
/*  996 */             spacing;
/*  997 */           DesktopTitlePane.this.iconifyButton.setBounds(x, y, buttonWidth, buttonHeight);
/*  998 */           if (!leftToRight)
/*      */           {
/* 1000 */             x += buttonWidth;
/*      */           }
/*      */         } 
/*      */       } 
/* 1004 */       DesktopTitlePane.this.buttonsWidth = leftToRight ? (
/* 1005 */         w - x) : 
/* 1006 */         x;
/*      */     }
/*      */   }
/*      */   
/*      */   private class PropertyChangeHandler implements PropertyChangeListener {
/*      */     private PropertyChangeHandler() {}
/*      */     
/*      */     public void propertyChange(PropertyChangeEvent pce) {
/* 1014 */       String name = pce.getPropertyName();
/*      */ 
/*      */       
/* 1017 */       if ("resizable".equals(name) || "state".equals(name)) {
/*      */         
/* 1019 */         Frame frame = DesktopTitlePane.this.getFrame();
/*      */         
/* 1021 */         if (frame != null)
/*      */         {
/* 1023 */           DesktopTitlePane.this.setState(frame.getExtendedState(), true);
/*      */         }
/* 1025 */         if ("resizable".equals(name))
/*      */         {
/* 1027 */           DesktopTitlePane.this.getRootPane().repaint();
/*      */         }
/*      */       }
/* 1030 */       else if ("title".equals(name)) {
/*      */         
/* 1032 */         DesktopTitlePane.this.repaint();
/*      */       }
/* 1034 */       else if ("componentOrientation" == name) {
/*      */         
/* 1036 */         DesktopTitlePane.this.revalidate();
/* 1037 */         DesktopTitlePane.this.repaint();
/*      */       }
/* 1039 */       else if ("iconImage" == name) {
/*      */         
/* 1041 */         DesktopTitlePane.this.updateSystemIcon();
/* 1042 */         DesktopTitlePane.this.revalidate();
/* 1043 */         DesktopTitlePane.this.repaint();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateSystemIcon() {
/* 1050 */     Window window = getWindow();
/* 1051 */     if (window == null) {
/*      */       
/* 1053 */       this.systemIcon = null;
/*      */       return;
/*      */     } 
/* 1056 */     List<Image> icons = window.getIconImages();
/* 1057 */     assert icons != null;
/*      */     
/* 1059 */     if (icons.size() == 0) {
/*      */       
/* 1061 */       this.systemIcon = null;
/*      */     }
/* 1063 */     else if (icons.size() == 1) {
/*      */       
/* 1065 */       this.systemIcon = icons.get(0);
/*      */     }
/*      */     else {
/*      */       
/* 1069 */       this.systemIcon = SunToolkit.getScaledIconImage(icons, 16, 16);
/*      */     } 
/*      */   }
/*      */   
/*      */   private class WindowHandler extends WindowAdapter {
/*      */     private WindowHandler() {}
/*      */     
/*      */     public void windowActivated(WindowEvent ev) {
/* 1077 */       DesktopTitlePane.this.setActive(true);
/*      */     }
/*      */ 
/*      */     
/*      */     public void windowDeactivated(WindowEvent ev) {
/* 1082 */       DesktopTitlePane.this.setActive(false);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\DesktopTitlePane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */