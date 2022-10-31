/*     */ package com.lbs.laf.mac;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JRootPane;
/*     */ 
/*     */ public class DesktopRootPaneUI extends BasicRootPaneUI {
/*  17 */   private static final String[] borderKeys = new String[] { null, "RootPane.frameBorder", "RootPane.plainDialogBorder", 
/*  18 */       "RootPane.informationDialogBorder", "RootPane.errorDialogBorder", "RootPane.colorChooserDialogBorder", 
/*  19 */       "RootPane.fileChooserDialogBorder", "RootPane.questionDialogBorder", "RootPane.warningDialogBorder" };
/*     */   
/*     */   private static final int CORNER_DRAG_WIDTH = 16;
/*     */   
/*     */   private static final int BORDER_DRAG_THICKNESS = 5;
/*     */   
/*     */   private Window window;
/*     */   
/*     */   private JComponent titlePane;
/*     */   
/*     */   private MouseInputListener mouseInputListener;
/*     */   
/*     */   private LayoutManager layoutManager;
/*     */   
/*     */   private LayoutManager savedOldLayout;
/*     */   
/*     */   private JRootPane root;
/*     */   
/*  37 */   private Cursor lastCursor = Cursor.getPredefinedCursor(0);
/*     */ 
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  41 */     return new DesktopRootPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  46 */     super.installUI(c);
/*  47 */     this.root = (JRootPane)c;
/*  48 */     int style = this.root.getWindowDecorationStyle();
/*  49 */     if (style != 0)
/*     */     {
/*  51 */       installClientDecorations(this.root);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/*  57 */     super.uninstallUI(c);
/*  58 */     uninstallClientDecorations(this.root);
/*     */     
/*  60 */     this.layoutManager = null;
/*  61 */     this.mouseInputListener = null;
/*  62 */     this.root = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installBorder(JRootPane root) {
/*  67 */     int style = root.getWindowDecorationStyle();
/*     */     
/*  69 */     if (style == 0) {
/*     */       
/*  71 */       LookAndFeel.uninstallBorder(root);
/*     */     }
/*     */     else {
/*     */       
/*  75 */       LookAndFeel.installBorder(root, borderKeys[style]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void uninstallBorder(JRootPane root) {
/*  81 */     LookAndFeel.uninstallBorder(root);
/*     */   }
/*     */ 
/*     */   
/*     */   private void installWindowListeners(JRootPane root, Component parent) {
/*  86 */     if (parent instanceof Window) {
/*     */       
/*  88 */       this.window = (Window)parent;
/*     */     }
/*     */     else {
/*     */       
/*  92 */       this.window = SwingUtilities.getWindowAncestor(parent);
/*     */     } 
/*  94 */     if (this.window != null) {
/*     */       
/*  96 */       if (this.mouseInputListener == null)
/*     */       {
/*  98 */         this.mouseInputListener = createWindowMouseInputListener(root);
/*     */       }
/* 100 */       this.window.addMouseListener(this.mouseInputListener);
/* 101 */       this.window.addMouseMotionListener(this.mouseInputListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void uninstallWindowListeners(JRootPane root) {
/* 107 */     if (this.window != null) {
/*     */       
/* 109 */       this.window.removeMouseListener(this.mouseInputListener);
/* 110 */       this.window.removeMouseMotionListener(this.mouseInputListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void installLayout(JRootPane root) {
/* 116 */     if (this.layoutManager == null)
/*     */     {
/* 118 */       this.layoutManager = createLayoutManager();
/*     */     }
/* 120 */     this.savedOldLayout = root.getLayout();
/* 121 */     root.setLayout(this.layoutManager);
/*     */   }
/*     */ 
/*     */   
/*     */   private void uninstallLayout(JRootPane root) {
/* 126 */     if (this.savedOldLayout != null) {
/*     */       
/* 128 */       root.setLayout(this.savedOldLayout);
/* 129 */       this.savedOldLayout = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void installClientDecorations(JRootPane root) {
/* 135 */     installBorder(root);
/*     */     
/* 137 */     JComponent titlePane = createTitlePane(root);
/*     */     
/* 139 */     setTitlePane(root, titlePane);
/* 140 */     installWindowListeners(root, root.getParent());
/* 141 */     installLayout(root);
/* 142 */     if (root.getParent() instanceof javax.swing.JDialog)
/* 143 */       root.getParent().setBackground(new Color(0, 0, 0, 0)); 
/* 144 */     if (this.window != null) {
/*     */       
/* 146 */       root.revalidate();
/* 147 */       root.repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void uninstallClientDecorations(JRootPane root) {
/* 153 */     uninstallBorder(root);
/* 154 */     uninstallWindowListeners(root);
/* 155 */     setTitlePane(root, null);
/* 156 */     uninstallLayout(root);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     int style = root.getWindowDecorationStyle();
/* 163 */     if (style == 0) {
/*     */       
/* 165 */       root.repaint();
/* 166 */       root.revalidate();
/*     */     } 
/*     */     
/* 169 */     if (this.window != null)
/*     */     {
/* 171 */       this.window.setCursor(Cursor.getPredefinedCursor(0));
/*     */     }
/* 173 */     this.window = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private JComponent createTitlePane(JRootPane root) {
/* 178 */     return new DesktopTitlePane(root, this);
/*     */   }
/*     */ 
/*     */   
/*     */   private MouseInputListener createWindowMouseInputListener(JRootPane root) {
/* 183 */     return new MouseInputHandler(null);
/*     */   }
/*     */ 
/*     */   
/*     */   private LayoutManager createLayoutManager() {
/* 188 */     return new MetalRootLayout(null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTitlePane(JRootPane root, JComponent titlePane) {
/* 193 */     JLayeredPane layeredPane = root.getLayeredPane();
/* 194 */     JComponent oldTitlePane = getTitlePane();
/*     */     
/* 196 */     if (oldTitlePane != null) {
/*     */       
/* 198 */       oldTitlePane.setVisible(false);
/* 199 */       layeredPane.remove(oldTitlePane);
/*     */     } 
/* 201 */     if (titlePane != null) {
/*     */       
/* 203 */       layeredPane.add(titlePane, JLayeredPane.FRAME_CONTENT_LAYER);
/* 204 */       titlePane.setVisible(true);
/*     */     } 
/* 206 */     this.titlePane = titlePane;
/*     */   }
/*     */ 
/*     */   
/*     */   private JComponent getTitlePane() {
/* 211 */     return this.titlePane;
/*     */   }
/*     */ 
/*     */   
/*     */   private JRootPane getRootPane() {
/* 216 */     return this.root;
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/* 221 */     super.propertyChange(e);
/*     */     
/* 223 */     String propertyName = e.getPropertyName();
/* 224 */     if (propertyName == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 229 */     if (propertyName.equals("windowDecorationStyle")) {
/*     */       
/* 231 */       JRootPane root = (JRootPane)e.getSource();
/* 232 */       int style = root.getWindowDecorationStyle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       uninstallClientDecorations(root);
/* 239 */       if (style != 0)
/*     */       {
/* 241 */         installClientDecorations(root);
/*     */       }
/*     */     }
/* 244 */     else if (propertyName.equals("ancestor")) {
/*     */       
/* 246 */       uninstallWindowListeners(this.root);
/* 247 */       if (((JRootPane)e.getSource()).getWindowDecorationStyle() != 0)
/*     */       {
/* 249 */         installWindowListeners(this.root, this.root.getParent());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class MetalRootLayout
/*     */     implements LayoutManager2
/*     */   {
/*     */     private MetalRootLayout() {}
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/*     */       Dimension cpd;
/* 261 */       int cpWidth = 0;
/* 262 */       int cpHeight = 0;
/* 263 */       int mbWidth = 0;
/* 264 */       int mbHeight = 0;
/* 265 */       int tpWidth = 0;
/* 266 */       int tpHeight = 0;
/* 267 */       Insets i = parent.getInsets();
/* 268 */       JRootPane root = (JRootPane)parent;
/*     */       
/* 270 */       if (root.getContentPane() != null) {
/*     */         
/* 272 */         cpd = root.getContentPane().getPreferredSize();
/*     */       }
/*     */       else {
/*     */         
/* 276 */         cpd = root.getSize();
/*     */       } 
/* 278 */       if (cpd != null) {
/*     */         
/* 280 */         cpWidth = cpd.width;
/* 281 */         cpHeight = cpd.height;
/*     */       } 
/*     */       
/* 284 */       if (root.getMenuBar() != null) {
/*     */         
/* 286 */         Dimension mbd = root.getMenuBar().getPreferredSize();
/* 287 */         if (mbd != null) {
/*     */           
/* 289 */           mbWidth = mbd.width;
/* 290 */           mbHeight = mbd.height;
/*     */         } 
/*     */       } 
/*     */       
/* 294 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof DesktopRootPaneUI) {
/*     */         
/* 296 */         JComponent titlePane = ((DesktopRootPaneUI)root.getUI()).getTitlePane();
/* 297 */         if (titlePane != null) {
/*     */           
/* 299 */           Dimension tpd = titlePane.getPreferredSize();
/* 300 */           if (tpd != null) {
/*     */             
/* 302 */             tpWidth = tpd.width;
/* 303 */             tpHeight = tpd.height;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 308 */       return new Dimension(Math.max(Math.max(cpWidth, mbWidth), tpWidth) + i.left + i.right, cpHeight + mbHeight + tpWidth + 
/* 309 */           i.top + i.bottom);
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/*     */       Dimension cpd;
/* 315 */       int cpWidth = 0;
/* 316 */       int cpHeight = 0;
/* 317 */       int mbWidth = 0;
/* 318 */       int mbHeight = 0;
/* 319 */       int tpWidth = 0;
/* 320 */       int tpHeight = 0;
/* 321 */       Insets i = parent.getInsets();
/* 322 */       JRootPane root = (JRootPane)parent;
/*     */       
/* 324 */       if (root.getContentPane() != null) {
/*     */         
/* 326 */         cpd = root.getContentPane().getMinimumSize();
/*     */       }
/*     */       else {
/*     */         
/* 330 */         cpd = root.getSize();
/*     */       } 
/* 332 */       if (cpd != null) {
/*     */         
/* 334 */         cpWidth = cpd.width;
/* 335 */         cpHeight = cpd.height;
/*     */       } 
/*     */       
/* 338 */       if (root.getMenuBar() != null) {
/*     */         
/* 340 */         Dimension mbd = root.getMenuBar().getMinimumSize();
/* 341 */         if (mbd != null) {
/*     */           
/* 343 */           mbWidth = mbd.width;
/* 344 */           mbHeight = mbd.height;
/*     */         } 
/*     */       } 
/* 347 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof DesktopRootPaneUI) {
/*     */         
/* 349 */         JComponent titlePane = ((DesktopRootPaneUI)root.getUI()).getTitlePane();
/* 350 */         if (titlePane != null) {
/*     */           
/* 352 */           Dimension tpd = titlePane.getMinimumSize();
/* 353 */           if (tpd != null) {
/*     */             
/* 355 */             tpWidth = tpd.width;
/* 356 */             tpHeight = tpd.height;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 361 */       return new Dimension(Math.max(Math.max(cpWidth, mbWidth), tpWidth) + i.left + i.right, cpHeight + mbHeight + tpWidth + 
/* 362 */           i.top + i.bottom);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Dimension maximumLayoutSize(Container target) {
/* 368 */       int cpWidth = Integer.MAX_VALUE;
/* 369 */       int cpHeight = Integer.MAX_VALUE;
/* 370 */       int mbWidth = Integer.MAX_VALUE;
/* 371 */       int mbHeight = Integer.MAX_VALUE;
/* 372 */       int tpWidth = Integer.MAX_VALUE;
/* 373 */       int tpHeight = Integer.MAX_VALUE;
/* 374 */       Insets i = target.getInsets();
/* 375 */       JRootPane root = (JRootPane)target;
/*     */       
/* 377 */       if (root.getContentPane() != null) {
/*     */         
/* 379 */         Dimension cpd = root.getContentPane().getMaximumSize();
/* 380 */         if (cpd != null) {
/*     */           
/* 382 */           cpWidth = cpd.width;
/* 383 */           cpHeight = cpd.height;
/*     */         } 
/*     */       } 
/*     */       
/* 387 */       if (root.getMenuBar() != null) {
/*     */         
/* 389 */         Dimension mbd = root.getMenuBar().getMaximumSize();
/* 390 */         if (mbd != null) {
/*     */           
/* 392 */           mbWidth = mbd.width;
/* 393 */           mbHeight = mbd.height;
/*     */         } 
/*     */       } 
/*     */       
/* 397 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof DesktopRootPaneUI) {
/*     */         
/* 399 */         JComponent titlePane = ((DesktopRootPaneUI)root.getUI()).getTitlePane();
/* 400 */         if (titlePane != null) {
/*     */           
/* 402 */           Dimension tpd = titlePane.getMaximumSize();
/* 403 */           if (tpd != null) {
/*     */             
/* 405 */             tpWidth = tpd.width;
/* 406 */             tpHeight = tpd.height;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 411 */       int maxHeight = Math.max(Math.max(cpHeight, mbHeight), tpHeight);
/*     */ 
/*     */       
/* 414 */       if (maxHeight != Integer.MAX_VALUE)
/*     */       {
/* 416 */         maxHeight = cpHeight + mbHeight + tpHeight + i.top + i.bottom;
/*     */       }
/*     */       
/* 419 */       int maxWidth = Math.max(Math.max(cpWidth, mbWidth), tpWidth);
/*     */       
/* 421 */       if (maxWidth != Integer.MAX_VALUE)
/*     */       {
/* 423 */         maxWidth += i.left + i.right;
/*     */       }
/*     */       
/* 426 */       return new Dimension(maxWidth, maxHeight);
/*     */     }
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 431 */       JRootPane root = (JRootPane)parent;
/* 432 */       Rectangle b = root.getBounds();
/* 433 */       Insets i = root.getInsets();
/* 434 */       int nextY = 0;
/* 435 */       int w = b.width - i.right - i.left;
/* 436 */       int h = b.height - i.top - i.bottom;
/*     */       
/* 438 */       if (root.getLayeredPane() != null)
/*     */       {
/* 440 */         root.getLayeredPane().setBounds(i.left, i.top, w, h);
/*     */       }
/* 442 */       if (root.getGlassPane() != null)
/*     */       {
/* 444 */         root.getGlassPane().setBounds(i.left, i.top, w, h);
/*     */       }
/*     */ 
/*     */       
/* 448 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof DesktopRootPaneUI) {
/*     */         
/* 450 */         JComponent titlePane = ((DesktopRootPaneUI)root.getUI()).getTitlePane();
/* 451 */         if (titlePane != null) {
/*     */           
/* 453 */           Dimension tpd = titlePane.getPreferredSize();
/* 454 */           if (tpd != null) {
/*     */             
/* 456 */             int tpHeight = tpd.height;
/* 457 */             titlePane.setBounds(0, 0, w, tpHeight);
/* 458 */             nextY += tpHeight;
/*     */           } 
/*     */         } 
/*     */       } 
/* 462 */       if (root.getMenuBar() != null) {
/*     */         
/* 464 */         Dimension mbd = root.getMenuBar().getPreferredSize();
/* 465 */         root.getMenuBar().setBounds(0, nextY, w, mbd.height);
/* 466 */         nextY += mbd.height;
/*     */       } 
/* 468 */       if (root.getContentPane() != null) {
/*     */         
/* 470 */         Dimension cpd = root.getContentPane().getPreferredSize();
/* 471 */         root.getContentPane().setBounds(0, nextY, w, (h < nextY) ? 
/* 472 */             0 : (
/* 473 */             h - nextY));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(String name, Component comp) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeLayoutComponent(Component comp) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(Component comp, Object constraints) {}
/*     */ 
/*     */     
/*     */     public float getLayoutAlignmentX(Container target) {
/* 491 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getLayoutAlignmentY(Container target) {
/* 496 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     
/*     */     public void invalidateLayout(Container target) {}
/*     */   }
/*     */ 
/*     */   
/* 504 */   private static final int[] cursorMapping = new int[] { 6, 6, 
/* 505 */       8, 7, 7, 6, 
/* 506 */       7, 10, 11, 4, 
/* 507 */       5, 4, 4, 9, 
/* 508 */       5, 5 };
/*     */ 
/*     */   
/*     */   private class MouseInputHandler
/*     */     implements MouseInputListener
/*     */   {
/*     */     private boolean isMovingWindow;
/*     */     
/*     */     private int dragCursor;
/*     */     
/*     */     private int dragOffsetX;
/*     */     
/*     */     private int dragOffsetY;
/*     */     private int dragWidth;
/*     */     private int dragHeight;
/*     */     
/*     */     private MouseInputHandler() {}
/*     */     
/*     */     public void mousePressed(MouseEvent ev) {
/* 527 */       JRootPane rootPane = DesktopRootPaneUI.this.getRootPane();
/*     */       
/* 529 */       if (rootPane.getWindowDecorationStyle() == 0) {
/*     */         return;
/*     */       }
/*     */       
/* 533 */       Point dragWindowOffset = ev.getPoint();
/* 534 */       Window w = (Window)ev.getSource();
/* 535 */       if (w != null)
/*     */       {
/* 537 */         w.toFront();
/*     */       }
/* 539 */       Point convertedDragWindowOffset = SwingUtilities.convertPoint(w, dragWindowOffset, DesktopRootPaneUI.this.getTitlePane());
/*     */       
/* 541 */       Frame f = null;
/* 542 */       Dialog d = null;
/*     */       
/* 544 */       if (w instanceof Frame) {
/*     */         
/* 546 */         f = (Frame)w;
/*     */       }
/* 548 */       else if (w instanceof Dialog) {
/*     */         
/* 550 */         d = (Dialog)w;
/*     */       } 
/*     */       
/* 553 */       int frameState = (f != null) ? 
/* 554 */         f.getExtendedState() : 
/* 555 */         0;
/*     */       
/* 557 */       if (DesktopRootPaneUI.this.getTitlePane() != null && DesktopRootPaneUI.this.getTitlePane().contains(convertedDragWindowOffset)) {
/*     */         
/* 559 */         if (((f != null && (frameState & 0x6) == 0) || d != null) && 
/* 560 */           dragWindowOffset.y >= 5 && dragWindowOffset.x >= 5 && 
/* 561 */           dragWindowOffset.x < w.getWidth() - 5)
/*     */         {
/* 563 */           this.isMovingWindow = true;
/* 564 */           this.dragOffsetX = dragWindowOffset.x;
/* 565 */           this.dragOffsetY = dragWindowOffset.y;
/*     */         }
/*     */       
/* 568 */       } else if ((f != null && f.isResizable() && (frameState & 0x6) == 0) || (d != null && d.isResizable())) {
/*     */         
/* 570 */         this.dragOffsetX = dragWindowOffset.x;
/* 571 */         this.dragOffsetY = dragWindowOffset.y;
/* 572 */         this.dragWidth = w.getWidth();
/* 573 */         this.dragHeight = w.getHeight();
/* 574 */         this.dragCursor = getCursor(calculateCorner(w, dragWindowOffset.x, dragWindowOffset.y));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent ev) {
/* 580 */       if (this.dragCursor != 0 && DesktopRootPaneUI.this.window != null && !DesktopRootPaneUI.this.window.isValid()) {
/*     */ 
/*     */ 
/*     */         
/* 584 */         DesktopRootPaneUI.this.window.validate();
/* 585 */         DesktopRootPaneUI.this.getRootPane().repaint();
/*     */       } 
/* 587 */       this.isMovingWindow = false;
/* 588 */       this.dragCursor = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent ev) {
/* 593 */       JRootPane root = DesktopRootPaneUI.this.getRootPane();
/*     */       
/* 595 */       if (root.getWindowDecorationStyle() == 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 600 */       Window w = (Window)ev.getSource();
/*     */       
/* 602 */       Frame f = null;
/* 603 */       Dialog d = null;
/*     */       
/* 605 */       if (w instanceof Frame) {
/*     */         
/* 607 */         f = (Frame)w;
/*     */       }
/* 609 */       else if (w instanceof Dialog) {
/*     */         
/* 611 */         d = (Dialog)w;
/*     */       } 
/*     */ 
/*     */       
/* 615 */       int cursor = getCursor(calculateCorner(w, ev.getX(), ev.getY()));
/*     */       
/* 617 */       if (cursor != 0 && ((
/* 618 */         f != null && f.isResizable() && (f.getExtendedState() & 0x6) == 0) || (d != null && d
/* 619 */         .isResizable()))) {
/*     */         
/* 621 */         w.setCursor(Cursor.getPredefinedCursor(cursor));
/*     */       }
/*     */       else {
/*     */         
/* 625 */         w.setCursor(DesktopRootPaneUI.this.lastCursor);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void adjust(Rectangle bounds, Dimension min, int deltaX, int deltaY, int deltaWidth, int deltaHeight) {
/* 631 */       bounds.x += deltaX;
/* 632 */       bounds.y += deltaY;
/* 633 */       bounds.width += deltaWidth;
/* 634 */       bounds.height += deltaHeight;
/* 635 */       if (min != null) {
/*     */         
/* 637 */         if (bounds.width < min.width) {
/*     */           
/* 639 */           int correction = min.width - bounds.width;
/* 640 */           if (deltaX != 0)
/*     */           {
/* 642 */             bounds.x -= correction;
/*     */           }
/* 644 */           bounds.width = min.width;
/*     */         } 
/* 646 */         if (bounds.height < min.height) {
/*     */           
/* 648 */           int correction = min.height - bounds.height;
/* 649 */           if (deltaY != 0)
/*     */           {
/* 651 */             bounds.y -= correction;
/*     */           }
/* 653 */           bounds.height = min.height;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent ev) {
/* 660 */       Window w = (Window)ev.getSource();
/* 661 */       Point pt = ev.getPoint();
/*     */       
/* 663 */       if (this.isMovingWindow) {
/*     */         
/* 665 */         Point eventLocationOnScreen = ev.getLocationOnScreen();
/* 666 */         w.setLocation(eventLocationOnScreen.x - this.dragOffsetX, eventLocationOnScreen.y - this.dragOffsetY);
/*     */       }
/* 668 */       else if (this.dragCursor != 0) {
/*     */         
/* 670 */         Rectangle r = w.getBounds();
/* 671 */         Rectangle startBounds = new Rectangle(r);
/* 672 */         Dimension min = w.getMinimumSize();
/*     */         
/* 674 */         switch (this.dragCursor) {
/*     */           
/*     */           case 11:
/* 677 */             adjust(r, min, 0, 0, pt.x + this.dragWidth - this.dragOffsetX - r.width, 0);
/*     */             break;
/*     */           case 9:
/* 680 */             adjust(r, min, 0, 0, 0, pt.y + this.dragHeight - this.dragOffsetY - r.height);
/*     */             break;
/*     */           case 8:
/* 683 */             adjust(r, min, 0, pt.y - this.dragOffsetY, 0, -(pt.y - this.dragOffsetY));
/*     */             break;
/*     */           case 10:
/* 686 */             adjust(r, min, pt.x - this.dragOffsetX, 0, -(pt.x - this.dragOffsetX), 0);
/*     */             break;
/*     */           case 7:
/* 689 */             adjust(r, min, 0, pt.y - this.dragOffsetY, pt.x + this.dragWidth - this.dragOffsetX - r.width, -(pt.y - this.dragOffsetY));
/*     */             break;
/*     */           case 5:
/* 692 */             adjust(r, min, 0, 0, pt.x + this.dragWidth - this.dragOffsetX - r.width, pt.y + this.dragHeight - this.dragOffsetY - 
/* 693 */                 r.height);
/*     */             break;
/*     */           case 6:
/* 696 */             adjust(r, min, pt.x - this.dragOffsetX, pt.y - this.dragOffsetY, -(pt.x - this.dragOffsetX), -(pt.y - this.dragOffsetY));
/*     */             break;
/*     */           case 4:
/* 699 */             adjust(r, min, pt.x - this.dragOffsetX, 0, -(pt.x - this.dragOffsetX), pt.y + this.dragHeight - this.dragOffsetY - r.height);
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 704 */         if (!r.equals(startBounds)) {
/*     */           
/* 706 */           w.setBounds(r);
/*     */ 
/*     */           
/* 709 */           if (Toolkit.getDefaultToolkit().isDynamicLayoutActive()) {
/*     */             
/* 711 */             w.validate();
/* 712 */             DesktopRootPaneUI.this.getRootPane().repaint();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent ev) {
/* 720 */       Window w = (Window)ev.getSource();
/* 721 */       DesktopRootPaneUI.this.lastCursor = w.getCursor();
/* 722 */       mouseMoved(ev);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent ev) {
/* 727 */       Window w = (Window)ev.getSource();
/* 728 */       w.setCursor(DesktopRootPaneUI.this.lastCursor);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent ev) {
/* 733 */       Window w = (Window)ev.getSource();
/* 734 */       Frame f = null;
/*     */       
/* 736 */       if (w instanceof Frame) {
/*     */         
/* 738 */         f = (Frame)w;
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 745 */       Point convertedPoint = SwingUtilities.convertPoint(w, ev.getPoint(), DesktopRootPaneUI.this.getTitlePane());
/*     */       
/* 747 */       int state = f.getExtendedState();
/* 748 */       if (DesktopRootPaneUI.this.getTitlePane() != null && DesktopRootPaneUI.this.getTitlePane().contains(convertedPoint))
/*     */       {
/* 750 */         if (ev.getClickCount() % 2 == 0 && (ev.getModifiers() & 0x10) != 0)
/*     */         {
/* 752 */           if (f.isResizable()) {
/*     */             
/* 754 */             if ((state & 0x6) != 0) {
/*     */               
/* 756 */               f.setExtendedState(state & 0xFFFFFFF9);
/*     */             }
/*     */             else {
/*     */               
/* 760 */               f.setExtendedState(state | 0x6);
/*     */             } 
/*     */             return;
/*     */           } 
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private int calculateCorner(Window w, int x, int y) {
/* 770 */       Insets insets = w.getInsets();
/* 771 */       int xPosition = calculatePosition(x - insets.left, w.getWidth() - insets.left - insets.right);
/* 772 */       int yPosition = calculatePosition(y - insets.top, w.getHeight() - insets.top - insets.bottom);
/*     */       
/* 774 */       if (xPosition == -1 || yPosition == -1)
/*     */       {
/* 776 */         return -1;
/*     */       }
/* 778 */       return yPosition * 5 + xPosition;
/*     */     }
/*     */ 
/*     */     
/*     */     private int getCursor(int corner) {
/* 783 */       if (corner == -1)
/*     */       {
/* 785 */         return 0;
/*     */       }
/* 787 */       return DesktopRootPaneUI.cursorMapping[corner];
/*     */     }
/*     */ 
/*     */     
/*     */     private int calculatePosition(int spot, int width) {
/* 792 */       if (spot < 5)
/*     */       {
/* 794 */         return 0;
/*     */       }
/* 796 */       if (spot < 16)
/*     */       {
/* 798 */         return 1;
/*     */       }
/* 800 */       if (spot >= width - 5)
/*     */       {
/* 802 */         return 4;
/*     */       }
/* 804 */       if (spot >= width - 16)
/*     */       {
/* 806 */         return 3;
/*     */       }
/* 808 */       return 2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\DesktopRootPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */