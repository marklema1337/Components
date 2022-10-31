/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsNumericEditWithCalculator;
/*     */ import com.lbs.controls.maskededit.ITextLimitChangeListener;
/*     */ import com.lbs.controls.maskededit.JLbsCharMap;
/*     */ import com.lbs.controls.numericedit.ILbsNumberListener;
/*     */ import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
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
/*     */ import java.text.ParseException;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.text.BadLocationException;
/*     */ 
/*     */ 
/*     */ public abstract class LbsNumericEditWithCalculatorAdapterBase
/*     */   implements ILbsNumericEditWithCalculator, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsNumericEditWithCalculator delegate;
/*     */   
/*     */   protected LbsNumericEditWithCalculatorAdapterBase(ILbsNumericEditWithCalculator delegate) {
/*  37 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/*  44 */     this.delegate.addActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/*  49 */     this.delegate.removeActionListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  58 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  63 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  68 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  73 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  78 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  83 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  88 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  93 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  98 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 103 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 108 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 113 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 118 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 123 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 128 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 133 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 138 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 143 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 148 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 153 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 158 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 163 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 168 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 173 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 178 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 183 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 188 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 193 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 198 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 203 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 208 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 213 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 218 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 223 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 228 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 233 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 238 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 243 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 248 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 253 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 258 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 263 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 268 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 273 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 278 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 283 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 288 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 293 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 298 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 303 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 308 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 313 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 318 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 323 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 328 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 333 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 338 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 343 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 348 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 353 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 358 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 363 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 368 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 373 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 378 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 383 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 388 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 393 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 398 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 403 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 408 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 413 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableFocusEvent(boolean bEnable) {
/* 422 */     this.delegate.enableFocusEvent(bEnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCharFilter() {
/* 427 */     return this.delegate.getCharFilter();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCharMap getCharMap() {
/* 432 */     return this.delegate.getCharMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public JFormattedTextField.AbstractFormatter getFormatter() {
/* 437 */     return this.delegate.getFormatter();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMask() {
/* 442 */     return this.delegate.getMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getNormalBkColor() {
/* 447 */     return this.delegate.getNormalBkColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOriginalText() {
/* 452 */     return this.delegate.getOriginalText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/* 457 */     return this.delegate.getTextLimit();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 462 */     return this.delegate.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClipMode() {
/* 467 */     return this.delegate.isClipMode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableFocusEvent() {
/* 472 */     return this.delegate.isEnableFocusEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharFilter(String string) {
/* 477 */     this.delegate.setCharFilter(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharMap(JLbsCharMap charMap) {
/* 482 */     this.delegate.setCharMap(charMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClipMode(boolean isClipMode) {
/* 487 */     this.delegate.setClipMode(isClipMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMask(String mask) {
/* 492 */     this.delegate.setMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTextLimit(int limit) {
/* 497 */     return this.delegate.setTextLimit(limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextLimitChangeListener(ITextLimitChangeListener textLimitChangeListener) {
/* 502 */     this.delegate.setTextLimitChangeListener(textLimitChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/* 507 */     this.delegate.setValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 512 */     return this.delegate.verifyContent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNumberListener() {
/* 521 */     this.delegate.clearNumberListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getBigDecimalValue() {
/* 526 */     return this.delegate.getBigDecimalValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 531 */     return this.delegate.getCultureInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDisplayZero() {
/* 536 */     return this.delegate.getDisplayZero();
/*     */   }
/*     */ 
/*     */   
/*     */   public void getDisplayZero(boolean bDisplayZero) {
/* 541 */     this.delegate.getDisplayZero(bDisplayZero);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDoubleValue() {
/* 546 */     return this.delegate.getDoubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getForceDecimals() {
/* 551 */     return this.delegate.getForceDecimals();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFormatAsYouType() {
/* 556 */     return this.delegate.getFormatAsYouType();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLongValue() {
/* 561 */     return this.delegate.getLongValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getNumber() throws ParseException {
/* 566 */     return this.delegate.getNumber();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter getNumberFormatter() {
/* 571 */     return this.delegate.getNumberFormatter();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsNumberListener getNumberListener() {
/* 576 */     return this.delegate.getNumberListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/* 581 */     return this.delegate.getPostfix();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 586 */     return this.delegate.getPrefix();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSignAllowed() {
/* 591 */     return this.delegate.isSignAllowed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 596 */     this.delegate.setCultureInfo(cultureInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrencyBase(JLbsCurrenciesBase currBase) {
/* 601 */     this.delegate.setCurrencyBase(currBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayZero(boolean bDispZero) {
/* 606 */     this.delegate.setDisplayZero(bDispZero);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilter(int iFilter, String szFormat, int iPrecision) {
/* 611 */     this.delegate.setFilter(iFilter, szFormat, iPrecision);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceDecimals(boolean bForce) {
/* 616 */     this.delegate.setForceDecimals(bForce);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormatAsYouType(boolean bFormat) {
/* 621 */     this.delegate.setFormatAsYouType(bFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormatSpecifierParams() {
/* 626 */     this.delegate.setFormatSpecifierParams();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumber(Number number) {
/* 631 */     this.delegate.setNumber(number);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(JLbsRealNumberFormatter formatter) {
/* 636 */     this.delegate.setNumberFormatter(formatter);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec) {
/* 641 */     this.delegate.setNumberFormatter(iFormatSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int iPrecision) {
/* 646 */     this.delegate.setNumberFormatter(iFormatSpec, iPrecision);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero) {
/* 651 */     this.delegate.setNumberFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero, boolean forceDecimals) {
/* 658 */     this.delegate.setNumberFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero, forceDecimals);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberListener(ILbsNumberListener listener) {
/* 664 */     this.delegate.setNumberListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 669 */     this.delegate.setPostfix(szPostfix);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefix(String szPrefix) {
/* 674 */     this.delegate.setPrefix(szPrefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSignAllowed(boolean signAllowed) {
/* 679 */     this.delegate.setSignAllowed(signAllowed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCaretPosition() {
/* 688 */     return this.delegate.getCaretPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getDisabledTextColor() {
/* 693 */     return this.delegate.getDisabledTextColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/* 698 */     return this.delegate.getSelectedText();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectedTextColor() {
/* 703 */     return this.delegate.getSelectedTextColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionColor() {
/* 708 */     return this.delegate.getSelectionColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/* 713 */     return this.delegate.getSelectionEnd();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/* 718 */     return this.delegate.getSelectionStart();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 723 */     return this.delegate.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText(int offs, int len) throws BadLocationException {
/* 728 */     return this.delegate.getText(offs, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/* 733 */     return this.delegate.isEditable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceSelection(String content) {
/* 738 */     this.delegate.replaceSelection(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public void select(int selectionStart, int selectionEnd) {
/* 743 */     this.delegate.select(selectionStart, selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectAll() {
/* 748 */     this.delegate.selectAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int position) {
/* 753 */     this.delegate.setCaretPosition(position);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisabledTextColor(Color c) {
/* 758 */     this.delegate.setDisabledTextColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/* 763 */     this.delegate.setEditable(editable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedTextColor(Color c) {
/* 768 */     this.delegate.setSelectedTextColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionColor(Color c) {
/* 773 */     this.delegate.setSelectionColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionEnd(int selectionEnd) {
/* 778 */     this.delegate.setSelectionEnd(selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionStart(int selectionStart) {
/* 783 */     this.delegate.setSelectionStart(selectionStart);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 788 */     this.delegate.setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumns() {
/* 797 */     return this.delegate.getColumns();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/* 802 */     return this.delegate.getHorizontalAlignment();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumns(int columns) {
/* 807 */     this.delegate.setColumns(columns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignment(int alignment) {
/* 812 */     this.delegate.setHorizontalAlignment(alignment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 821 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 826 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 831 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 840 */     this.delegate.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 845 */     this.delegate.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 854 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsNumericEditWithCalculatorAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */