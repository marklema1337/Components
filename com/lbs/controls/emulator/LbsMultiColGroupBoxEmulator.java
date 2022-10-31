/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.controls.groupbox.ILbsButtonGroupListener;
/*     */ import com.lbs.controls.groupbox.ILbsInternalMultiColGroupBox;
/*     */ import com.lbs.controls.groupbox.LbsMultiColGroupBoxImplementor;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import info.clearthought.layout.TableLayoutConstraints;
/*     */ import java.awt.event.FocusListener;
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
/*     */ public class LbsMultiColGroupBoxEmulator
/*     */   extends LbsGroupBoxEmulator
/*     */   implements ILbsInternalMultiColGroupBox
/*     */ {
/*     */   protected LbsMultiColGroupBoxImplementor m_Implementor;
/*     */   protected TableLayoutConstraints m_LayoutConstraints;
/*     */   
/*     */   public LbsMultiColGroupBoxEmulator() {
/*  29 */     this.m_Implementor = createImplementor();
/*  30 */     this.m_Implementor.init();
/*     */   }
/*     */ 
/*     */   
/*     */   protected LbsMultiColGroupBoxImplementor createImplementor() {
/*  35 */     return new LbsMultiColGroupBoxImplementor(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value) {
/*  40 */     this.m_Implementor.addItem(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int tag) {
/*  45 */     this.m_Implementor.addItem(value, tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/*  50 */     this.m_Implementor.addItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearItems() {
/*  55 */     this.m_Implementor.clearItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsButtonGroupListener getButtonGroupListener() {
/*  60 */     return this.m_Implementor.getButtonGroupListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  65 */     return this.m_Implementor.getColumnCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getControlByTag(int tag) {
/*  70 */     return this.m_Implementor.getControlByTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/*  75 */     return this.m_Implementor.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemArray() {
/*  80 */     return this.m_Implementor.getSelectedItemArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemIndexes() {
/*  85 */     return this.m_Implementor.getSelectedItemIndexes();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemMask() {
/*  90 */     return this.m_Implementor.getSelectedItemMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonGroupListener(ILbsButtonGroupListener listener) {
/*  95 */     this.m_Implementor.setButtonGroupListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnCount(int colCount) {
/* 100 */     this.m_Implementor.setColumnCount(colCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setControlEnabled(int tag, boolean enabled) {
/* 105 */     return this.m_Implementor.setControlEnabled(tag, enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 110 */     this.m_Implementor.setItems(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedControl(ILbsComponent c, boolean flag) {
/* 115 */     this.m_Implementor.setItemSelected(c, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemArray(int[] list) {
/* 120 */     this.m_Implementor.setSelectedItemArray(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMask(int mask) {
/* 125 */     this.m_Implementor.setSelectedItemMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 130 */     this.m_Implementor.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 135 */     this.m_Implementor.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getVisibleItems() {
/* 141 */     return this.m_Implementor.getVisibleItems();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedItemMaskWithSameTag(int mask) {
/* 147 */     this.m_Implementor.setSelectedItemMaskWithSameTag(mask);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, TableLayoutConstraints constr) {
/* 153 */     this.m_Implementor.addItem(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, TableLayoutConstraints constr) {
/* 159 */     this.m_Implementor.addItem(value, iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTableLayoutConstraints(TableLayoutConstraints constraints) {
/* 165 */     this.m_LayoutConstraints = constraints;
/*     */   }
/*     */   
/*     */   public void setLayout() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsMultiColGroupBoxEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */