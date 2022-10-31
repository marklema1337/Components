/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsImageButton;
/*     */ import com.lbs.control.interfaces.ILbsMenuItem;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.controls.ILbsInternalMenuButton;
/*     */ import com.lbs.controls.ILbsMenuButtonListener;
/*     */ import com.lbs.controls.LbsMenuButtonImplementor;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.Icon;
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
/*     */ public class LbsMenuButtonEmulator
/*     */   extends LbsPanelEmulator
/*     */   implements ILbsInternalMenuButton
/*     */ {
/*     */   private LbsMenuButtonImplementor m_Implementor;
/*     */   
/*     */   public LbsMenuButtonEmulator() {
/*  31 */     this.m_Implementor = new LbsMenuButtonImplementor(this);
/*  32 */     this.m_Implementor.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPopupButton(ILbsImageButton button) {
/*  37 */     return this.m_Implementor.isPopupButton(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsImageButton createMainButton() {
/*  42 */     LbsImageButtonEmulator button = new LbsImageButtonEmulator();
/*  43 */     add(button, -1);
/*  44 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsImageButton createPopupButton() {
/*  49 */     LbsImageButtonEmulator button = new LbsImageButtonEmulator();
/*  50 */     add(button, -1);
/*  51 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenu createPopupMenu() {
/*  56 */     LbsPopupMenuEmulator popup = new LbsPopupMenuEmulator();
/*  57 */     add(popup, -1);
/*  58 */     return (ILbsPopupMenu)popup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doClick() {
/*  63 */     this.m_Implementor.doClick();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionId() {
/*  68 */     return this.m_Implementor.getActionId();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionIndex() {
/*  73 */     return this.m_Implementor.getActionIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/*  78 */     return this.m_Implementor.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMenuButtonListener getMenuButtonListener() {
/*  83 */     return this.m_Implementor.getMenuButtonListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsPopupMenu getPopupControl() {
/*  88 */     return this.m_Implementor.getPopupControl();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int tag) {
/*  93 */     return this.m_Implementor.hasItemTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemEnabled(ILbsMenuItem item) {
/*  98 */     return this.m_Implementor.isItemEnabled(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemVisible(ILbsMenuItem item) {
/* 103 */     return this.m_Implementor.isItemVisible(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemWTagEnabled(int tag) {
/* 108 */     return this.m_Implementor.isItemWTagEnabled(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientPropertyInternal(Object property, Object value) {
/* 113 */     this.m_Implementor.putClientPropertyInternal(property, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reinitialize() {
/* 118 */     this.m_Implementor.reinitialize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionId(int actionId) {
/* 123 */     this.m_Implementor.setActionId(actionId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionIndex(int actionIndex) {
/* 128 */     this.m_Implementor.setActionIndex(actionIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList list, boolean clear) {
/* 133 */     this.m_Implementor.setActions(list, clear);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActions(JLbsStringList actions) {
/* 138 */     this.m_Implementor.setActions(actions);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuButtonListener(ILbsMenuButtonListener listener) {
/* 143 */     this.m_Implementor.setMenuButtonListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String hint) {
/* 148 */     super.setToolTipText(hint);
/*     */     
/* 150 */     this.m_Implementor.setToolTipText(hint);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 155 */     return this.m_Implementor.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addKeyListener(KeyListener listener) {
/* 160 */     super.addKeyListener(listener);
/* 161 */     this.m_Implementor.addKeyListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addMouseListener(MouseListener listener) {
/* 166 */     super.addMouseListener(listener);
/* 167 */     this.m_Implementor.addMouseListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon() {
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(Icon main) {}
/*     */ 
/*     */   
/*     */   public void doPerformAction(int actionID) {
/* 181 */     this.m_Implementor.doPerformAction(actionID);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doMainButtonClick() {
/* 186 */     this.m_Implementor.doMainButtonClick();
/*     */   }
/*     */   
/*     */   public void setHighlightIcon(Icon hlIcon) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsMenuButtonEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */