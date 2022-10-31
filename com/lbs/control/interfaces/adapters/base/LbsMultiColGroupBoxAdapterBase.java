/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsMultiColGroupBox;
/*     */ import com.lbs.control.interfaces.ILbsPanel;
/*     */ import com.lbs.controls.groupbox.ILbsButtonGroupListener;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import info.clearthought.layout.TableLayoutConstraints;
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
/*     */ public abstract class LbsMultiColGroupBoxAdapterBase
/*     */   implements ILbsMultiColGroupBox, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsMultiColGroupBox delegate;
/*     */   
/*     */   protected LbsMultiColGroupBoxAdapterBase(ILbsMultiColGroupBox delegate) {
/*  31 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  38 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  43 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  48 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  53 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  58 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  63 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  68 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  73 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  78 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  83 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/*  88 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  93 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/*  98 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 103 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 108 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 113 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 118 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 123 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 128 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 133 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 138 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 143 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 148 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 153 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 158 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 163 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 168 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 173 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 178 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 183 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 188 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 193 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 198 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 203 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 208 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 213 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 218 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 223 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 228 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 233 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 238 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 243 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 248 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 253 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 258 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 263 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 268 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 273 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 278 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 283 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 288 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 293 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 298 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 303 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 308 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 313 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 318 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 323 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 328 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 333 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 338 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 343 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 348 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 353 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 358 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 363 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 368 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 373 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 378 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 383 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 388 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 393 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 402 */     return this.delegate.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String title) {
/* 407 */     this.delegate.setText(title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value) {
/* 416 */     this.delegate.addItem(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, TableLayoutConstraints constr) {
/* 421 */     this.delegate.addItem(value, constr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag) {
/* 426 */     this.delegate.addItem(value, iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, TableLayoutConstraints constr) {
/* 431 */     this.delegate.addItem(value, iTag, constr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList arg0) {
/* 436 */     this.delegate.addItems(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 441 */     this.delegate.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsButtonGroupListener getButtonGroupListener() {
/* 446 */     return this.delegate.getButtonGroupListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/* 451 */     return this.delegate.getColumnCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getControlByTag(int tag) {
/* 456 */     return this.delegate.getControlByTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 461 */     return this.delegate.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemArray() {
/* 466 */     return this.delegate.getSelectedItemArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemIndexes() {
/* 471 */     return this.delegate.getSelectedItemIndexes();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemMask() {
/* 476 */     return this.delegate.getSelectedItemMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getVisibleItems() {
/* 481 */     return this.delegate.getVisibleItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonGroupListener(ILbsButtonGroupListener listener) {
/* 486 */     this.delegate.setButtonGroupListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnCount(int iColCount) {
/* 491 */     this.delegate.setColumnCount(iColCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setControlEnabled(int tag, boolean enabled) {
/* 496 */     return this.delegate.setControlEnabled(tag, enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 501 */     this.delegate.setItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLayout() {
/* 506 */     this.delegate.setLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedControl(ILbsComponent c, boolean flag) {
/* 511 */     this.delegate.setSelectedControl(c, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemArray(int[] iList) {
/* 516 */     this.delegate.setSelectedItemArray(iList);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMask(int mask) {
/* 521 */     this.delegate.setSelectedItemMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMaskWithSameTag(int mask) {
/* 526 */     this.delegate.setSelectedItemMaskWithSameTag(mask);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void collapse() {
/* 535 */     this.delegate.collapse();
/*     */   }
/*     */ 
/*     */   
/*     */   public void expand() {
/* 540 */     this.delegate.expand();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollapsed() {
/* 545 */     return this.delegate.isCollapsed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(String header, ILbsPanel.IImageSupplier imageSupplier) {
/* 550 */     this.delegate.setHeader(header, imageSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 559 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 564 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 569 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 578 */     this.delegate.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 583 */     this.delegate.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 592 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsMultiColGroupBoxAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */