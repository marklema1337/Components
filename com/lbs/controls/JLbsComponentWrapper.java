/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
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
/*     */ import javax.swing.InputVerifier;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsComponentWrapper
/*     */   implements ILbsComponent
/*     */ {
/*     */   private JComponent m_Component;
/*     */   
/*     */   public JLbsComponentWrapper(JComponent component) {
/*  38 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  43 */     this.m_Component.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  48 */     this.m_Component.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  53 */     this.m_Component.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  58 */     this.m_Component.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  63 */     return this.m_Component.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  68 */     return this.m_Component.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  73 */     return this.m_Component.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/*  78 */     return this.m_Component.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  83 */     return this.m_Component.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/*  88 */     return this.m_Component.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/*  93 */     return this.m_Component.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  98 */     return this.m_Component.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 103 */     return this.m_Component.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 108 */     return this.m_Component.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 113 */     return this.m_Component.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 118 */     return this.m_Component.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 123 */     return this.m_Component.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 128 */     return this.m_Component.getParent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 133 */     return this.m_Component.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 138 */     return this.m_Component.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 143 */     return this.m_Component.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 148 */     return this.m_Component.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 153 */     return this.m_Component.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 158 */     return this.m_Component.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 163 */     this.m_Component.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 168 */     this.m_Component.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 173 */     return this.m_Component.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 178 */     return this.m_Component.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 183 */     return this.m_Component.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 188 */     return this.m_Component.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 193 */     this.m_Component.putClientProperty(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 198 */     this.m_Component.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 203 */     this.m_Component.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 208 */     this.m_Component.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 213 */     return this.m_Component.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 218 */     this.m_Component.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {
/* 223 */     this.m_Component.setAutoscrolls(autoScrolls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 228 */     this.m_Component.setBackground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 233 */     this.m_Component.setBorder(border);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 238 */     this.m_Component.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 243 */     this.m_Component.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget arg0) {
/* 248 */     this.m_Component.setDropTarget(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 253 */     this.m_Component.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 258 */     this.m_Component.setFocusable(focusable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 263 */     this.m_Component.setFont(font);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {
/* 268 */     this.m_Component.setForeground(color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInputVerifier(InputVerifier arg0) {
/* 273 */     this.m_Component.setInputVerifier(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 278 */     this.m_Component.setLocation(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 283 */     this.m_Component.setLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 288 */     this.m_Component.setMaximumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 293 */     this.m_Component.setMinimumSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean arg0) {
/* 298 */     this.m_Component.setOpaque(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension arg0) {
/* 303 */     this.m_Component.setPreferredSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 308 */     this.m_Component.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 313 */     this.m_Component.setSize(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {
/* 318 */     this.m_Component.setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {
/* 323 */     this.m_Component.setTransferHandler(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 328 */     this.m_Component.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 333 */     this.m_Component.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 338 */     this.m_Component.validate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 343 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 348 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 353 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 358 */     this.m_Component.setBounds(bounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/* 363 */     return this.m_Component.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public InputVerifier getInputVerifier() {
/* 368 */     return this.m_Component.getInputVerifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 373 */     return this.m_Component.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 378 */     return this.m_Component.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener l) {
/* 383 */     this.m_Component.removeFocusListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener l) {
/* 388 */     this.m_Component.removeKeyListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener l) {
/* 393 */     this.m_Component.removeMouseListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 398 */     return this.m_Component.isFocusable();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComponentWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */