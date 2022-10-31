/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsScrollPane;
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
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public abstract class LbsScrollPaneAdapterBase
/*     */   implements ILbsScrollPane, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsScrollPane delegate;
/*     */   
/*     */   protected LbsScrollPaneAdapterBase(ILbsScrollPane delegate) {
/*  27 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  34 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  39 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  44 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  49 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  54 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  59 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  64 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  69 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  74 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  79 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/*  84 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  89 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/*  94 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/*  99 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 104 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 109 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 114 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 119 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 124 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 129 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 134 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 139 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 144 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 149 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 154 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 159 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 164 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 169 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 174 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 179 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 184 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 189 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 194 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 199 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 204 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 209 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 214 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 219 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 224 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 229 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 234 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 239 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 244 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 249 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 254 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 259 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 264 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 269 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 274 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 279 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 284 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 289 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 294 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 299 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 304 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 309 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 314 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 319 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 324 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 329 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 334 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 339 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 344 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 349 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 354 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 359 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 364 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 369 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 374 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 379 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 384 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 389 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHorizontalScrollBarPolicy() {
/* 398 */     return this.delegate.getHorizontalScrollBarPolicy();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getInnerComponent() {
/* 403 */     return this.delegate.getInnerComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVerticalScrollBarPolicy() {
/* 408 */     return this.delegate.getVerticalScrollBarPolicy();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWheelScrollingEnabled() {
/* 413 */     return this.delegate.isWheelScrollingEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalScrollBarPolicy(int policy) {
/* 418 */     this.delegate.setHorizontalScrollBarPolicy(policy);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVerticalScrollBarPolicy(int policy) {
/* 423 */     this.delegate.setVerticalScrollBarPolicy(policy);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWheelScrollingEnabled(boolean handleWheel) {
/* 428 */     this.delegate.setWheelScrollingEnabled(handleWheel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 437 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 442 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 447 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 456 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsScrollPaneAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */