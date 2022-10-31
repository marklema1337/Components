/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboTextEdit;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.control.interfaces.ILbsPanel;
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
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public abstract class LbsComboTextEditAdapterBase
/*     */   implements ILbsComboTextEdit, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsComboTextEdit delegate;
/*     */   
/*     */   protected LbsComboTextEditAdapterBase(ILbsComboTextEdit delegate) {
/*  32 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtraButton(int buttonType) {
/*  39 */     this.delegate.addExtraButton(buttonType);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doButtonAction(int index, int id) {
/*  44 */     this.delegate.doButtonAction(index, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/*  49 */     return this.delegate.getEditControl();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/*  54 */     return this.delegate.getEmptyBlockWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getMultilangButton() {
/*  59 */     return this.delegate.getMultilangButton();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/*  64 */     this.delegate.setActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/*  69 */     this.delegate.setEmptyBlockWidth(width);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraButtonListener(ActionListener extraButtonListener) {
/*  74 */     this.delegate.setExtraButtonListener(extraButtonListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFirstLookupButtonVisible(boolean visible) {
/*  79 */     this.delegate.setFirstLookupButtonVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIconToMultilangButton(ImageIcon icon) {
/*  84 */     this.delegate.setIconToMultilangButton(icon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMultilangButton(JButton button) {
/*  89 */     this.delegate.setMultilangButton(button);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  98 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/* 103 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/* 108 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/* 113 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/* 118 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/* 123 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/* 128 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/* 133 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/* 138 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 143 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 148 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 153 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 158 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 163 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 168 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 173 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 178 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 183 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 188 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 193 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 198 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 203 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 208 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 213 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 218 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 223 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 228 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 233 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 238 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 243 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 248 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 253 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 258 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 263 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 268 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 273 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 278 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 283 */     this.delegate.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 288 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 293 */     this.delegate.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 298 */     this.delegate.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 303 */     this.delegate.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 308 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 313 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 318 */     return this.delegate.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 323 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float alignmentX) {
/* 328 */     this.delegate.setAlignmentX(alignmentX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float alignmentY) {
/* 333 */     this.delegate.setAlignmentY(alignmentY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 338 */     this.delegate.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 343 */     this.delegate.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 348 */     this.delegate.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 353 */     this.delegate.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 358 */     this.delegate.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 363 */     this.delegate.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {
/* 368 */     this.delegate.setDropTarget(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 373 */     this.delegate.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 378 */     this.delegate.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 383 */     this.delegate.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 388 */     this.delegate.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 393 */     this.delegate.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 398 */     this.delegate.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 403 */     this.delegate.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 408 */     this.delegate.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {
/* 413 */     this.delegate.setOpaque(opaque);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 418 */     this.delegate.setPreferredSize(prefSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 423 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 428 */     this.delegate.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 433 */     this.delegate.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 438 */     this.delegate.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 443 */     this.delegate.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 448 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 453 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void collapse() {
/* 462 */     this.delegate.collapse();
/*     */   }
/*     */ 
/*     */   
/*     */   public void expand() {
/* 467 */     this.delegate.expand();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollapsed() {
/* 472 */     return this.delegate.isCollapsed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(String header, ILbsPanel.IImageSupplier imageSupplier) {
/* 477 */     this.delegate.setHeader(header, imageSupplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 486 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 491 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 496 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 505 */     this.delegate.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 510 */     this.delegate.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 519 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsComboTextEditAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */