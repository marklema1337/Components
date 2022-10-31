/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.maskededit.ITextLimitChangeListener;
/*     */ import com.lbs.controls.maskededit.JLbsCharMap;
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
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.text.BadLocationException;
/*     */ 
/*     */ 
/*     */ public abstract class LbsMaskedEditAdapterBase
/*     */   implements ILbsMaskedEdit, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsMaskedEdit delegate;
/*     */   
/*     */   protected LbsMaskedEditAdapterBase(ILbsMaskedEdit delegate) {
/*  32 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/*  39 */     this.delegate.addActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/*  44 */     this.delegate.removeActionListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  53 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  58 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  63 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  68 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  73 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  78 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  83 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  88 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  93 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  98 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 103 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 108 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 113 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 118 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 123 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 128 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 133 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 138 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 143 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 148 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 153 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 158 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 163 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 168 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 173 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 178 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 183 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 188 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 193 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 198 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 203 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 208 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 213 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 218 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 223 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 228 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 233 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 238 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 243 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 248 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 253 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 258 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 263 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 268 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 273 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 278 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 283 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 288 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 293 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 298 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 303 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 308 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 313 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 318 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 323 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 328 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 333 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 338 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 343 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 348 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 353 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 358 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 363 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 368 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 373 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 378 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 383 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 388 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 393 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 398 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 403 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 408 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableFocusEvent(boolean bEnable) {
/* 417 */     this.delegate.enableFocusEvent(bEnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCharFilter() {
/* 422 */     return this.delegate.getCharFilter();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCharMap getCharMap() {
/* 427 */     return this.delegate.getCharMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public JFormattedTextField.AbstractFormatter getFormatter() {
/* 432 */     return this.delegate.getFormatter();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMask() {
/* 437 */     return this.delegate.getMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getNormalBkColor() {
/* 442 */     return this.delegate.getNormalBkColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOriginalText() {
/* 447 */     return this.delegate.getOriginalText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/* 452 */     return this.delegate.getTextLimit();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 457 */     return this.delegate.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClipMode() {
/* 462 */     return this.delegate.isClipMode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableFocusEvent() {
/* 467 */     return this.delegate.isEnableFocusEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharFilter(String string) {
/* 472 */     this.delegate.setCharFilter(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharMap(JLbsCharMap charMap) {
/* 477 */     this.delegate.setCharMap(charMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClipMode(boolean isClipMode) {
/* 482 */     this.delegate.setClipMode(isClipMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMask(String mask) {
/* 487 */     this.delegate.setMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTextLimit(int limit) {
/* 492 */     return this.delegate.setTextLimit(limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextLimitChangeListener(ITextLimitChangeListener textLimitChangeListener) {
/* 497 */     this.delegate.setTextLimitChangeListener(textLimitChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/* 502 */     this.delegate.setValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 507 */     return this.delegate.verifyContent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCaretPosition() {
/* 516 */     return this.delegate.getCaretPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getDisabledTextColor() {
/* 521 */     return this.delegate.getDisabledTextColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/* 526 */     return this.delegate.getSelectedText();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectedTextColor() {
/* 531 */     return this.delegate.getSelectedTextColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionColor() {
/* 536 */     return this.delegate.getSelectionColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/* 541 */     return this.delegate.getSelectionEnd();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/* 546 */     return this.delegate.getSelectionStart();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 551 */     return this.delegate.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText(int offs, int len) throws BadLocationException {
/* 556 */     return this.delegate.getText(offs, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/* 561 */     return this.delegate.isEditable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceSelection(String content) {
/* 566 */     this.delegate.replaceSelection(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public void select(int selectionStart, int selectionEnd) {
/* 571 */     this.delegate.select(selectionStart, selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectAll() {
/* 576 */     this.delegate.selectAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int position) {
/* 581 */     this.delegate.setCaretPosition(position);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisabledTextColor(Color c) {
/* 586 */     this.delegate.setDisabledTextColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/* 591 */     this.delegate.setEditable(editable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedTextColor(Color c) {
/* 596 */     this.delegate.setSelectedTextColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionColor(Color c) {
/* 601 */     this.delegate.setSelectionColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionEnd(int selectionEnd) {
/* 606 */     this.delegate.setSelectionEnd(selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionStart(int selectionStart) {
/* 611 */     this.delegate.setSelectionStart(selectionStart);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 616 */     this.delegate.setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumns() {
/* 625 */     return this.delegate.getColumns();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/* 630 */     return this.delegate.getHorizontalAlignment();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumns(int columns) {
/* 635 */     this.delegate.setColumns(columns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignment(int alignment) {
/* 640 */     this.delegate.setHorizontalAlignment(alignment);
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
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 668 */     this.delegate.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 673 */     this.delegate.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 682 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsMaskedEditAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */