/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsCheckBox;
/*     */ import com.lbs.control.interfaces.ILbsCheckBoxGroup;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
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
/*     */ public abstract class LbsCheckBoxGroupAdapterBase
/*     */   implements ILbsCheckBoxGroup, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsCheckBoxGroup delegate;
/*     */   
/*     */   protected LbsCheckBoxGroupAdapterBase(ILbsCheckBoxGroup delegate) {
/*  32 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, boolean bChecked) {
/*  39 */     this.delegate.addItem(value, iTag, bChecked);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCheckBox getCheckBoxControl(int index) {
/*  44 */     return this.delegate.getCheckBoxControl(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemDeselectedByTag(int iTag) {
/*  49 */     this.delegate.setItemDeselectedByTag(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLayout() {
/*  54 */     this.delegate.setLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPopupList(JLbsStringList list) {
/*  59 */     this.delegate.setPopupList(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  68 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  73 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  78 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  83 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  88 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  93 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  98 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/* 103 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/* 108 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 113 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 118 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 123 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 128 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 133 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 138 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 143 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 148 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 153 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 158 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 163 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 168 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 173 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 178 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 183 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 188 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 193 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 198 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 203 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 208 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 213 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 218 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 223 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 228 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 233 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 238 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 243 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 248 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 253 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 258 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 263 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 268 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 273 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 278 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 283 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 288 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 293 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 298 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 303 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 308 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 313 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 318 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 323 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 328 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 333 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 338 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 343 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 348 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 353 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 358 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 363 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 368 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 373 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 378 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 383 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 388 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 393 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 398 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 403 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 408 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 413 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 418 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 423 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 432 */     return this.delegate.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String title) {
/* 437 */     this.delegate.setText(title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value) {
/* 446 */     this.delegate.addItem(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, TableLayoutConstraints constr) {
/* 451 */     this.delegate.addItem(value, constr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag) {
/* 456 */     this.delegate.addItem(value, iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, TableLayoutConstraints constr) {
/* 461 */     this.delegate.addItem(value, iTag, constr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList arg0) {
/* 466 */     this.delegate.addItems(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 471 */     this.delegate.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsButtonGroupListener getButtonGroupListener() {
/* 476 */     return this.delegate.getButtonGroupListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/* 481 */     return this.delegate.getColumnCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getControlByTag(int tag) {
/* 486 */     return this.delegate.getControlByTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 491 */     return this.delegate.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemArray() {
/* 496 */     return this.delegate.getSelectedItemArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemIndexes() {
/* 501 */     return this.delegate.getSelectedItemIndexes();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemMask() {
/* 506 */     return this.delegate.getSelectedItemMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getVisibleItems() {
/* 511 */     return this.delegate.getVisibleItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonGroupListener(ILbsButtonGroupListener listener) {
/* 516 */     this.delegate.setButtonGroupListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnCount(int iColCount) {
/* 521 */     this.delegate.setColumnCount(iColCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setControlEnabled(int tag, boolean enabled) {
/* 526 */     return this.delegate.setControlEnabled(tag, enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 531 */     this.delegate.setItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedControl(ILbsComponent c, boolean flag) {
/* 536 */     this.delegate.setSelectedControl(c, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemArray(int[] iList) {
/* 541 */     this.delegate.setSelectedItemArray(iList);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMask(int mask) {
/* 546 */     this.delegate.setSelectedItemMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMaskWithSameTag(int mask) {
/* 551 */     this.delegate.setSelectedItemMaskWithSameTag(mask);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void collapse() {
/* 560 */     this.delegate.collapse();
/*     */   }
/*     */ 
/*     */   
/*     */   public void expand() {
/* 565 */     this.delegate.expand();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollapsed() {
/* 570 */     return this.delegate.isCollapsed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(String header, ILbsPanel.IImageSupplier imageSupplier) {
/* 575 */     this.delegate.setHeader(header, imageSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 584 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 589 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 594 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 603 */     this.delegate.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 608 */     this.delegate.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 617 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsCheckBoxGroupAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */