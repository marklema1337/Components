/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsImageButton;
/*     */ import com.lbs.control.interfaces.ILbsMenuButton;
/*     */ import com.lbs.control.interfaces.ILbsMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsPanel;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.controls.ILbsMenuButtonListener;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.Color;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public abstract class LbsMenuButtonAdapterBase
/*     */   implements ILbsMenuButton, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsMenuButton delegate;
/*     */   
/*     */   protected LbsMenuButtonAdapterBase(ILbsMenuButton delegate) {
/*  34 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  41 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  46 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  51 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  56 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  61 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  66 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  71 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  76 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  81 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  86 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/*  91 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  96 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 101 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 106 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 111 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 116 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 121 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 126 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 131 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 136 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 141 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 146 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 151 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 156 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 161 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 166 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 171 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 176 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 181 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 186 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 191 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 196 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 201 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 206 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 211 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 216 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 221 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 226 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 231 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 236 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 241 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 246 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 251 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 256 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 261 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 266 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 271 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 276 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 281 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 286 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 291 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 296 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 301 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 306 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 311 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 316 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 321 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 326 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 331 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 336 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 341 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 346 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 351 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 356 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 361 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 366 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 371 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 376 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 381 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 386 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 391 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 396 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doClick() {
/* 405 */     this.delegate.doClick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doMainButtonClick() {
/* 410 */     this.delegate.doMainButtonClick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPerformAction(int actionID) {
/* 415 */     this.delegate.doPerformAction(actionID);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionId() {
/* 420 */     return this.delegate.getActionId();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionIndex() {
/* 425 */     return this.delegate.getActionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon() {
/* 430 */     return this.delegate.getIcon();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 435 */     return this.delegate.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMenuButtonListener getMenuButtonListener() {
/* 440 */     return this.delegate.getMenuButtonListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenu getPopupControl() {
/* 445 */     return this.delegate.getPopupControl();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/* 450 */     return this.delegate.hasItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemEnabled(ILbsMenuItem arg0) {
/* 455 */     return this.delegate.isItemEnabled(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemVisible(ILbsMenuItem arg0) {
/* 460 */     return this.delegate.isItemVisible(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 465 */     return this.delegate.isItemWTagEnabled(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPopupButton(ILbsImageButton button) {
/* 470 */     return this.delegate.isPopupButton(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientPropertyInternal(Object property, Object value) {
/* 475 */     this.delegate.putClientPropertyInternal(property, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reinitialize() {
/* 480 */     this.delegate.reinitialize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionId(int actionId) {
/* 485 */     this.delegate.setActionId(actionId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionIndex(int actionIndex) {
/* 490 */     this.delegate.setActionIndex(actionIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList actions) {
/* 495 */     this.delegate.setActions(actions);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList list, boolean clear) {
/* 500 */     this.delegate.setActions(list, clear);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHighlightIcon(Icon hlIcon) {
/* 505 */     this.delegate.setHighlightIcon(hlIcon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIcon(Icon main) {
/* 510 */     this.delegate.setIcon(main);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuButtonListener(ILbsMenuButtonListener listener) {
/* 515 */     this.delegate.setMenuButtonListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void collapse() {
/* 524 */     this.delegate.collapse();
/*     */   }
/*     */ 
/*     */   
/*     */   public void expand() {
/* 529 */     this.delegate.expand();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollapsed() {
/* 534 */     return this.delegate.isCollapsed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(String header, ILbsPanel.IImageSupplier imageSupplier) {
/* 539 */     this.delegate.setHeader(header, imageSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 548 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 553 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 558 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 567 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsMenuButtonAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */