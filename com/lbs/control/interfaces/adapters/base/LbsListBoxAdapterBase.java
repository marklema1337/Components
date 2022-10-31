/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsListBox;
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
/*     */ import java.util.Vector;
/*     */ import javax.swing.ListModel;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public abstract class LbsListBoxAdapterBase
/*     */   implements ILbsListBox, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsListBox delegate;
/*     */   
/*     */   protected LbsListBoxAdapterBase(ILbsListBox delegate) {
/*  29 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  36 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  41 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  46 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  51 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  56 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  61 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  66 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  71 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  76 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  81 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/*  86 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  91 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/*  96 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 101 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 106 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 111 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 116 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 121 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 126 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 131 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 136 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 141 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 146 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 151 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 156 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 161 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 166 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 171 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 176 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 181 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 186 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 191 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 196 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 201 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 206 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 211 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 216 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 221 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 226 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 231 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 236 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 241 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 246 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 251 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 256 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 261 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 266 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 271 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 276 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 281 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 286 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 291 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 296 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 301 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 306 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 311 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 316 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 321 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 326 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 331 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 336 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 341 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 346 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 351 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 356 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 361 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 366 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 371 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 376 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 381 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 386 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 391 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addItem(String item) {
/* 400 */     return this.delegate.addItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public int addItem(String text, int imageIndex) {
/* 405 */     return this.delegate.addItem(text, imageIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSelectionInterval(int anchor, int lead) {
/* 410 */     this.delegate.addSelectionInterval(anchor, lead);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean clearItems() {
/* 415 */     return this.delegate.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearSelection() {
/* 420 */     this.delegate.clearSelection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ensureIndexIsVisible(int index) {
/* 425 */     this.delegate.ensureIndexIsVisible(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnchorSelectionIndex() {
/* 430 */     return this.delegate.getAnchorSelectionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDragEnabled() {
/* 435 */     return this.delegate.getDragEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFirstVisibleIndex() {
/* 440 */     return this.delegate.getFirstVisibleIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFixedCellHeight() {
/* 445 */     return this.delegate.getFixedCellHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFixedCellWidth() {
/* 450 */     return this.delegate.getFixedCellWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastVisibleIndex() {
/* 455 */     return this.delegate.getLastVisibleIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLayoutOrientation() {
/* 460 */     return this.delegate.getLayoutOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLeadSelectionIndex() {
/* 465 */     return this.delegate.getLeadSelectionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSelectionIndex() {
/* 470 */     return this.delegate.getMaxSelectionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinSelectionIndex() {
/* 475 */     return this.delegate.getMinSelectionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public ListModel getModel() {
/* 480 */     return this.delegate.getModel();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedIndex() {
/* 485 */     return this.delegate.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedIndices() {
/* 490 */     return this.delegate.getSelectedIndices();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedValue() {
/* 495 */     return this.delegate.getSelectedValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getSelectedValues() {
/* 500 */     return this.delegate.getSelectedValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionBackground() {
/* 505 */     return this.delegate.getSelectionBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionForeground() {
/* 510 */     return this.delegate.getSelectionForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionMode() {
/* 515 */     return this.delegate.getSelectionMode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowTooltips() {
/* 520 */     return this.delegate.getShowTooltips();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getValueIsAdjusting() {
/* 525 */     return this.delegate.getValueIsAdjusting();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVisibleRowCount() {
/* 530 */     return this.delegate.getVisibleRowCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point indexToLocation(int index) {
/* 535 */     return this.delegate.indexToLocation(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelectedIndex(int index) {
/* 540 */     return this.delegate.isSelectedIndex(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelectionEmpty() {
/* 545 */     return this.delegate.isSelectionEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public int locationToIndex(Point location) {
/* 550 */     return this.delegate.locationToIndex(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDragEnabled(boolean b) {
/* 555 */     this.delegate.setDragEnabled(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixedCellHeight(int height) {
/* 560 */     this.delegate.setFixedCellHeight(height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixedCellWidth(int width) {
/* 565 */     this.delegate.setFixedCellWidth(width);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLayoutOrientation(int orientation) {
/* 570 */     this.delegate.setLayoutOrientation(orientation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListData(Object[] listData) {
/* 575 */     this.delegate.setListData(listData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListData(Vector listData) {
/* 580 */     this.delegate.setListData(listData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModel(ListModel model) {
/* 585 */     this.delegate.setModel(model);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int index) {
/* 590 */     this.delegate.setSelectedIndex(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndices(int[] indices) {
/* 595 */     this.delegate.setSelectedIndices(indices);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedValue(Object anObject, boolean shouldScroll) {
/* 600 */     this.delegate.setSelectedValue(anObject, shouldScroll);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(int index) {
/* 605 */     this.delegate.setSelection(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionBackground(Color selectionBackground) {
/* 610 */     this.delegate.setSelectionBackground(selectionBackground);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionForeground(Color selectionForeground) {
/* 615 */     this.delegate.setSelectionForeground(selectionForeground);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionInterval(int anchor, int lead) {
/* 620 */     this.delegate.setSelectionInterval(anchor, lead);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionMode(int selectionMode) {
/* 625 */     this.delegate.setSelectionMode(selectionMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowTooltips(boolean value) {
/* 630 */     this.delegate.setShowTooltips(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueIsAdjusting(boolean b) {
/* 635 */     this.delegate.setValueIsAdjusting(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibleRowCount(int visibleRowCount) {
/* 640 */     this.delegate.setVisibleRowCount(visibleRowCount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 649 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 654 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 659 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 668 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsListBoxAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */