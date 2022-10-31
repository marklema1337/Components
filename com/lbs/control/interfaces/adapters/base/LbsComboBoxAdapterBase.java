/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboBox;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.controls.ILbsComboFilterListener;
/*     */ import com.lbs.controls.JLbsComboBoxItem;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.Color;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public abstract class LbsComboBoxAdapterBase
/*     */   implements ILbsComboBox, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsComboBox delegate;
/*     */   
/*     */   protected LbsComboBoxAdapterBase(ILbsComboBox delegate) {
/*  34 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/*  41 */     this.delegate.addActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/*  46 */     this.delegate.removeActionListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject) {
/*  55 */     this.delegate.addItem(anObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag) {
/*  60 */     this.delegate.addItem(anObject, iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag, ImageIcon icon) {
/*  65 */     this.delegate.addItem(anObject, iTag, icon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemListener(ItemListener aListener) {
/*  70 */     this.delegate.addItemListener(aListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/*  75 */     this.delegate.addItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/*  80 */     this.delegate.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doFilterItems() {
/*  85 */     this.delegate.doFilterItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(Object value) {
/*  90 */     return this.delegate.findItemIndex(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(String value) {
/*  95 */     return this.delegate.findItemIndex(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getActionCommand() {
/* 100 */     return this.delegate.getActionCommand();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentCaretPosition() {
/* 105 */     return this.delegate.getCurrentCaretPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComboFilterListener getFilterListener() {
/* 110 */     return this.delegate.getFilterListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getItemAt(int index) {
/* 115 */     return this.delegate.getItemAt(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemCount() {
/* 120 */     return this.delegate.getItemCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsComboBoxItem getItemTagObject(int iTag) {
/* 125 */     return this.delegate.getItemTagObject(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemTagString(int tag) {
/* 130 */     return this.delegate.getItemTagString(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getItemTagValue(int iTag) {
/* 135 */     return this.delegate.getItemTagValue(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getItemlist() {
/* 140 */     return this.delegate.getItemlist();
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getItems() {
/* 145 */     return this.delegate.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/* 150 */     return this.delegate.getItemsSList();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximumRowCount() {
/* 155 */     return this.delegate.getMaximumRowCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedIndex() {
/* 160 */     return this.delegate.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedItem() {
/* 165 */     return this.delegate.getSelectedItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemTag() {
/* 170 */     return this.delegate.getSelectedItemTag();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedItemValue() {
/* 175 */     return this.delegate.getSelectedItemValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTagOfItem(Object item) {
/* 180 */     return this.delegate.getTagOfItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int iTag) {
/* 185 */     return this.delegate.hasItemTag(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/* 190 */     return this.delegate.isEditable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPopupVisible() {
/* 195 */     return this.delegate.isPopupVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllItems() {
/* 200 */     this.delegate.removeAllItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeItemAt(int anIndex) {
/* 205 */     this.delegate.removeItemAt(anIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeItemListener(ItemListener aListener) {
/* 210 */     this.delegate.removeItemListener(aListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionCommand(String command) {
/* 215 */     this.delegate.setActionCommand(command);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCaretPosition(int currentCaretPosition) {
/* 220 */     this.delegate.setCurrentCaretPosition(currentCaretPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/* 225 */     this.delegate.setEditable(editable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditableForAutoComplete(boolean aFlag) {
/* 230 */     this.delegate.setEditableForAutoComplete(aFlag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilterListener(ILbsComboFilterListener listener) {
/* 235 */     this.delegate.setFilterListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 240 */     this.delegate.setItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumRowCount(int count) {
/* 245 */     this.delegate.setMaximumRowCount(count);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPopupVisible(boolean v) {
/* 250 */     this.delegate.setPopupVisible(v);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int index) {
/* 255 */     this.delegate.setSelectedIndex(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItem(Object anObject) {
/* 260 */     this.delegate.setSelectedItem(anObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setSelectedItem(String itemText) {
/* 265 */     return this.delegate.setSelectedItem(itemText);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setSelectedItemTag(int iTag) {
/* 270 */     return this.delegate.setSelectedItemTag(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int setSelectedItemValue(Object value) {
/* 275 */     return this.delegate.setSelectedItemValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/* 284 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/* 289 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/* 294 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/* 299 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/* 304 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/* 309 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/* 314 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/* 319 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/* 324 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 329 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 334 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 339 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 344 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 349 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 354 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 359 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 364 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 369 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 374 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 379 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 384 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 389 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 394 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 399 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 404 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 409 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 414 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 419 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 424 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 429 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 434 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 439 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 444 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 449 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 454 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 459 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 464 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 469 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 474 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 479 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 484 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 489 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 494 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 499 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 504 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 509 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 514 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 519 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 524 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 529 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 534 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 539 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 544 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 549 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 554 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 559 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 564 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 569 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 574 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 579 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 584 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 589 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 594 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 599 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 604 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 609 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 614 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 619 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 624 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 629 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 634 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 639 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 648 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 653 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 658 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 667 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsComboBoxAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */