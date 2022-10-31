/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsTabPage;
/*     */ import com.lbs.control.interfaces.ILbsTabbedPane;
/*     */ import com.lbs.controls.ILbsTabbedPaneListener;
/*     */ import com.lbs.controls.wizard.xui.ILbsXUIWizardPaneListener;
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
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ 
/*     */ public abstract class LbsTabbedPaneAdapterBase
/*     */   implements ILbsTabbedPane, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsTabbedPane delegate;
/*     */   
/*     */   protected LbsTabbedPaneAdapterBase(ILbsTabbedPane delegate) {
/*  32 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener listener) {
/*  39 */     this.delegate.addFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/*  44 */     this.delegate.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener listener) {
/*  49 */     this.delegate.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {
/*  54 */     this.delegate.applyComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  59 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  64 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  69 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  74 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  79 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  84 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/*  89 */     return this.delegate.getClientProperty(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  94 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/*  99 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 104 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 109 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 114 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 119 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 124 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 129 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 134 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 139 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 144 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 149 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 154 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 159 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 164 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 169 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 174 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 179 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 184 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 189 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 194 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 199 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 204 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 209 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 214 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 219 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 224 */     this.delegate.putClientProperty(key, value);
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
/*     */   public void addChangeListener(ChangeListener listener) {
/* 398 */     this.delegate.addChangeListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTab(String title, ILbsTabPage tabPage) {
/* 403 */     this.delegate.addTab(title, tabPage);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTabPage(String title, ILbsComponent tabPage) {
/* 408 */     this.delegate.addTabPage(title, tabPage);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getComponentAt(int index) {
/* 413 */     return this.delegate.getComponentAt(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedIndex() {
/* 418 */     return this.delegate.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsTabPage getSelectedPage() {
/* 423 */     return this.delegate.getSelectedPage();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedPageTag() {
/* 428 */     return this.delegate.getSelectedPageTag();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabCount() {
/* 433 */     return this.delegate.getTabCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabLayoutPolicy() {
/* 438 */     return this.delegate.getTabLayoutPolicy();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsTabbedPaneListener getTabbedPaneListener() {
/* 443 */     return this.delegate.getTabbedPaneListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitleAt(int tabIndex) {
/* 448 */     return this.delegate.getTitleAt(tabIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertTab(String title, Icon icon, Object component, String tip, int index) {
/* 453 */     this.delegate.insertTab(title, icon, component, tip, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabledAt(int index) {
/* 458 */     return this.delegate.isEnabledAt(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(ILbsComponent component) {
/* 463 */     this.delegate.remove(component);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(int tabIndex) {
/* 468 */     this.delegate.remove(tabIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 473 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(ChangeListener l) {
/* 478 */     this.delegate.removeChangeListener(l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTabAt(int index) {
/* 483 */     this.delegate.removeTabAt(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabledAt(int index, boolean enabled) {
/* 488 */     this.delegate.setEnabledAt(index, enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int tabIndex) {
/* 493 */     this.delegate.setSelectedIndex(tabIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTabLayoutPolicy(int tabLayoutPolicy) {
/* 498 */     this.delegate.setTabLayoutPolicy(tabLayoutPolicy);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTabbedPaneListener(ILbsTabbedPaneListener listener) {
/* 503 */     this.delegate.setTabbedPaneListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitleAt(int tabIndex, String title) {
/* 508 */     this.delegate.setTitleAt(tabIndex, title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 517 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 522 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 527 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canGoBack() throws Exception {
/* 536 */     return this.delegate.canGoBack();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGoFinish() throws Exception {
/* 541 */     return this.delegate.canGoFinish();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGoNext() throws Exception {
/* 546 */     return this.delegate.canGoNext();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWizardMode() {
/* 551 */     return this.delegate.isWizardMode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWizardMode(boolean wizardMode) {
/* 556 */     this.delegate.setWizardMode(wizardMode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWizardPaneListener(ILbsXUIWizardPaneListener listener) {
/* 561 */     this.delegate.setWizardPaneListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean wizardFinish(boolean cancelled) throws Exception {
/* 566 */     return this.delegate.wizardFinish(cancelled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 575 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsTabbedPaneAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */