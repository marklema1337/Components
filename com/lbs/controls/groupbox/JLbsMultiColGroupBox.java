/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import info.clearthought.layout.TableLayout;
/*     */ import info.clearthought.layout.TableLayoutConstraints;
/*     */ import java.awt.Component;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMultiColGroupBox
/*     */   extends JLbsGroupBox
/*     */   implements FocusListener, ActionListener, ILbsInternalMultiColGroupBox
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected LbsMultiColGroupBoxImplementor m_Implementor;
/*     */   protected TableLayoutConstraints m_LayoutConstraints;
/*     */   
/*     */   public JLbsMultiColGroupBox() {
/*  40 */     this.m_Implementor = createImplementor();
/*  41 */     this.m_Implementor.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableLayout(int colCount) {
/*  46 */     double[] row = { -2.0D };
/*  47 */     double[] col = new double[colCount];
/*  48 */     for (int i = 0; i < colCount; i++) {
/*  49 */       col[i] = -2.0D;
/*     */     }
/*  51 */     setLayout((LayoutManager)new TableLayout(col, row));
/*     */   }
/*     */ 
/*     */   
/*     */   protected LbsMultiColGroupBoxImplementor createImplementor() {
/*  56 */     return new LbsMultiColGroupBoxImplementor(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChild(ILbsComponent child) {
/*  61 */     if (this.m_LayoutConstraints != null) {
/*  62 */       add((Component)child, this.m_LayoutConstraints);
/*     */     } else {
/*  64 */       add((Component)child);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getChildAt(int index) {
/*  69 */     return getComponent(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChild(ILbsComponent child) {
/*  74 */     remove((Component)child);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColumnCount(int iColCount) {
/*  84 */     if (this.m_Implementor.setColumnCount(iColCount)) {
/*     */       
/*  86 */       if (iColCount == 1)
/*  87 */         setLayout(new GridLayout(0, iColCount, 1, 1)); 
/*  88 */       validate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  94 */     return this.m_Implementor.getColumnCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value) {
/* 104 */     setLayout();
/* 105 */     this.m_Implementor.addItem(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag) {
/* 116 */     setLayout();
/* 117 */     this.m_Implementor.addItem(value, iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/* 126 */     int colCount = getColumnCount();
/* 127 */     if (list != null) {
/*     */       
/* 129 */       int listSize = list.size();
/* 130 */       if (colCount != 1 && colCount == listSize)
/*     */       {
/* 132 */         setTableLayout(listSize);
/*     */       }
/*     */     } 
/* 135 */     this.m_Implementor.addItems(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 143 */     this.m_Implementor.clearItems();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 153 */     this.m_Implementor.setItems(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectedItemMask() {
/* 163 */     return this.m_Implementor.getSelectedItemMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMask(int mask) {
/* 168 */     this.m_Implementor.setSelectedItemMask(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemMaskWithSameTag(int mask) {
/* 173 */     this.m_Implementor.setSelectedItemMaskWithSameTag(mask);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemSelected(Component c, boolean flag) {
/* 178 */     this.m_Implementor.setItemSelected(c, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedControl(ILbsComponent c, boolean flag) {
/* 183 */     this.m_Implementor.setItemSelected(c, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemArray() {
/* 192 */     return this.m_Implementor.getSelectedItemArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedItemIndexes() {
/* 197 */     return this.m_Implementor.getSelectedItemIndexes();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemArray(int[] iList) {
/* 202 */     this.m_Implementor.setSelectedItemArray(iList);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addLbsFocusListener(FocusListener listener) {
/* 207 */     this.m_Implementor.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeLbsFocusListener(FocusListener listener) {
/* 212 */     this.m_Implementor.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComponentTag(Component c) {
/* 217 */     return this.m_Implementor.getComponentTag(c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent event) {
/* 223 */     this.m_Implementor.focusGained(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent event) {
/* 228 */     this.m_Implementor.focusLost(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 233 */     this.m_Implementor.actionPerformed(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsButtonGroupListener getButtonGroupListener() {
/* 238 */     return this.m_Implementor.getButtonGroupListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonGroupListener(ILbsButtonGroupListener listener) {
/* 243 */     this.m_Implementor.setButtonGroupListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setControlEnabled(int tag, boolean enabled) {
/* 248 */     return this.m_Implementor.setControlEnabled(tag, enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getControlByTag(int tag) {
/* 253 */     return this.m_Implementor.getControlByTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getItems() {
/* 258 */     return this.m_Implementor.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getVisibleItems() {
/* 269 */     return this.m_Implementor.getVisibleItems();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, TableLayoutConstraints constr) {
/* 275 */     if (constr == null)
/* 276 */       setLayout(); 
/* 277 */     this.m_Implementor.addItem(value, constr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, TableLayoutConstraints constr) {
/* 284 */     if (constr == null)
/* 285 */       setLayout(); 
/* 286 */     this.m_Implementor.addItem(value, iTag, constr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTableLayoutConstraints(TableLayoutConstraints constraints) {
/* 292 */     this.m_LayoutConstraints = constraints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLayout() {
/* 299 */     int columnCount = getColumnCount();
/* 300 */     int itemsSize = getItems().size();
/* 301 */     if (columnCount != itemsSize)
/* 302 */       setLayout(new GridLayout(0, columnCount, 1, 1)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsMultiColGroupBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */