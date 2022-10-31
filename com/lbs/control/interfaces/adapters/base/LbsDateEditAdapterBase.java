/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsDateEdit;
/*     */ import com.lbs.controls.maskededit.ITextLimitChangeListener;
/*     */ import com.lbs.controls.maskededit.JLbsCharMap;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
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
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.text.BadLocationException;
/*     */ 
/*     */ 
/*     */ public abstract class LbsDateEditAdapterBase
/*     */   implements ILbsDateEdit, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsDateEdit delegate;
/*     */   
/*     */   protected LbsDateEditAdapterBase(ILbsDateEdit delegate) {
/*  35 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/*  42 */     this.delegate.addActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/*  47 */     this.delegate.removeActionListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  56 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  61 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  66 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  71 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  76 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  81 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  86 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  91 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  96 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 101 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 106 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 111 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 116 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 121 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 126 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 131 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 136 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 141 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 146 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 151 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 156 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 161 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 166 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 171 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 176 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 181 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 186 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 191 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 196 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 201 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 206 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 211 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 216 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 221 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 226 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 231 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 236 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 241 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 246 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 251 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 256 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 261 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 266 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 271 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 276 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 281 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 286 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 291 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 296 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 301 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 306 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 311 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 316 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 321 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 326 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 331 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 336 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 341 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 346 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 351 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 356 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 361 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 366 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 371 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 376 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 381 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 386 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 391 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 396 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 401 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 406 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 411 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decreaseDay() {
/* 420 */     this.delegate.decreaseDay();
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/* 425 */     return this.delegate.getCalendar();
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 430 */     return this.delegate.getCalendarValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 435 */     return this.delegate.getCultureInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/* 440 */     return this.delegate.getDate();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFormat() {
/* 445 */     return this.delegate.getDateFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDateSeparator() {
/* 450 */     return this.delegate.getDateSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateString(Date date) {
/* 455 */     return this.delegate.getDateString(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultVisibleChars() {
/* 460 */     return this.delegate.getDefaultVisibleChars();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayFormat() {
/* 465 */     return this.delegate.getDisplayFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/* 470 */     return this.delegate.getMaskPlaceHolder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 475 */     return this.delegate.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseDay() {
/* 480 */     this.delegate.increaseDay();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/* 485 */     return this.delegate.isResetTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 490 */     return this.delegate.isValueModified();
/*     */   }
/*     */ 
/*     */   
/*     */   public String parseDate(Date date) {
/* 495 */     return this.delegate.parseDate(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date parseDate(String szText, String szDateFormat) {
/* 500 */     return this.delegate.parseDate(szText, szDateFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/* 505 */     this.delegate.resetModified(newValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar value) {
/* 510 */     this.delegate.setCalendarValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo culture) {
/* 515 */     this.delegate.setCultureInfo(culture);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar date) {
/* 520 */     this.delegate.setDate(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Date date) {
/* 525 */     this.delegate.setDate(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(int iDateFmtSpec) {
/* 530 */     return this.delegate.setDateFormat(iDateFmtSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(String szFormat) {
/* 535 */     return this.delegate.setDateFormat(szFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateSeparator(char chSeparator) {
/* 540 */     return this.delegate.setDateSeparator(chSeparator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayFormat(int iFmtSpec) {
/* 545 */     this.delegate.setDisplayFormat(iFmtSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayFormat(String szFormat) {
/* 550 */     this.delegate.setDisplayFormat(szFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/* 555 */     this.delegate.setMaskPlaceHolder(chPlaceHolder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean a) {
/* 560 */     this.delegate.setResetTime(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public String stripDateNumbers(String text) {
/* 565 */     return this.delegate.stripDateNumbers(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableFocusEvent(boolean bEnable) {
/* 574 */     this.delegate.enableFocusEvent(bEnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCharFilter() {
/* 579 */     return this.delegate.getCharFilter();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCharMap getCharMap() {
/* 584 */     return this.delegate.getCharMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public JFormattedTextField.AbstractFormatter getFormatter() {
/* 589 */     return this.delegate.getFormatter();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMask() {
/* 594 */     return this.delegate.getMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getNormalBkColor() {
/* 599 */     return this.delegate.getNormalBkColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOriginalText() {
/* 604 */     return this.delegate.getOriginalText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/* 609 */     return this.delegate.getTextLimit();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClipMode() {
/* 614 */     return this.delegate.isClipMode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableFocusEvent() {
/* 619 */     return this.delegate.isEnableFocusEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharFilter(String string) {
/* 624 */     this.delegate.setCharFilter(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharMap(JLbsCharMap charMap) {
/* 629 */     this.delegate.setCharMap(charMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClipMode(boolean isClipMode) {
/* 634 */     this.delegate.setClipMode(isClipMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMask(String mask) {
/* 639 */     this.delegate.setMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTextLimit(int limit) {
/* 644 */     return this.delegate.setTextLimit(limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextLimitChangeListener(ITextLimitChangeListener textLimitChangeListener) {
/* 649 */     this.delegate.setTextLimitChangeListener(textLimitChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/* 654 */     this.delegate.setValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 659 */     return this.delegate.verifyContent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCaretPosition() {
/* 668 */     return this.delegate.getCaretPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getDisabledTextColor() {
/* 673 */     return this.delegate.getDisabledTextColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/* 678 */     return this.delegate.getSelectedText();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectedTextColor() {
/* 683 */     return this.delegate.getSelectedTextColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionColor() {
/* 688 */     return this.delegate.getSelectionColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/* 693 */     return this.delegate.getSelectionEnd();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/* 698 */     return this.delegate.getSelectionStart();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 703 */     return this.delegate.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText(int offs, int len) throws BadLocationException {
/* 708 */     return this.delegate.getText(offs, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/* 713 */     return this.delegate.isEditable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceSelection(String content) {
/* 718 */     this.delegate.replaceSelection(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public void select(int selectionStart, int selectionEnd) {
/* 723 */     this.delegate.select(selectionStart, selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectAll() {
/* 728 */     this.delegate.selectAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int position) {
/* 733 */     this.delegate.setCaretPosition(position);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisabledTextColor(Color c) {
/* 738 */     this.delegate.setDisabledTextColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/* 743 */     this.delegate.setEditable(editable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedTextColor(Color c) {
/* 748 */     this.delegate.setSelectedTextColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionColor(Color c) {
/* 753 */     this.delegate.setSelectionColor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionEnd(int selectionEnd) {
/* 758 */     this.delegate.setSelectionEnd(selectionEnd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectionStart(int selectionStart) {
/* 763 */     this.delegate.setSelectionStart(selectionStart);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 768 */     this.delegate.setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumns() {
/* 777 */     return this.delegate.getColumns();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/* 782 */     return this.delegate.getHorizontalAlignment();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumns(int columns) {
/* 787 */     this.delegate.setColumns(columns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignment(int alignment) {
/* 792 */     this.delegate.setHorizontalAlignment(alignment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 801 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 806 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 811 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 820 */     this.delegate.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 825 */     this.delegate.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 834 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsDateEditAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */