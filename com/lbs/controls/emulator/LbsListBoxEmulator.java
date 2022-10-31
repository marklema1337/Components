/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsListBox;
/*     */ import com.lbs.controls.JLbsListBoxItem;
/*     */ import java.awt.Color;
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Vector;
/*     */ import javax.swing.ListModel;
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
/*     */ public class LbsListBoxEmulator
/*     */   extends LbsComponentEmulator
/*     */   implements ILbsListBox
/*     */ {
/*  24 */   private ArrayList m_Items = new ArrayList();
/*  25 */   private ArrayList m_SelectedItems = new ArrayList();
/*     */   
/*     */   private static final int CELLHEIGHT = 22;
/*     */   private static final int CELLWIDTH = 50;
/*     */   private static final int VISIBLE_ROW_COUNT = 10;
/*     */   
/*     */   public int addItem(String item) {
/*  32 */     this.m_Items.add(new JLbsListBoxItem(item));
/*  33 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addItem(String text, int imageIndex) {
/*  38 */     this.m_Items.add(new JLbsListBoxItem(text));
/*  39 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSelectionInterval(int anchor, int lead) {}
/*     */ 
/*     */   
/*     */   public boolean clearItems() {
/*  48 */     this.m_Items.clear();
/*  49 */     this.m_SelectedItems.clear();
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearSelection() {
/*  55 */     this.m_SelectedItems.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ensureIndexIsVisible(int index) {}
/*     */ 
/*     */   
/*     */   public int getAnchorSelectionIndex() {
/*  64 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDragEnabled() {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFirstVisibleIndex() {
/*  74 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFixedCellHeight() {
/*  79 */     return 22;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFixedCellWidth() {
/*  84 */     return 50;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastVisibleIndex() {
/*  89 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLayoutOrientation() {
/*  94 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLeadSelectionIndex() {
/*  99 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSelectionIndex() {
/* 104 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinSelectionIndex() {
/* 109 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListModel getModel() {
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedIndex() {
/* 119 */     if (this.m_SelectedItems.size() > 0)
/* 120 */       return this.m_Items.indexOf(this.m_SelectedItems.get(0)); 
/* 121 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSelectedIndices() {
/* 126 */     int[] selectedIndices = new int[this.m_SelectedItems.size()];
/* 127 */     for (int i = 0; i < this.m_SelectedItems.size(); i++)
/* 128 */       selectedIndices[i] = this.m_Items.indexOf(this.m_SelectedItems.get(i)); 
/* 129 */     return selectedIndices;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedValue() {
/* 134 */     return this.m_SelectedItems.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getSelectedValues() {
/* 139 */     Object[] selectedValue = new Object[this.m_SelectedItems.size()];
/* 140 */     for (int i = 0; i < this.m_SelectedItems.size(); i++)
/* 141 */       selectedValue[i] = this.m_SelectedItems.get(i); 
/* 142 */     return selectedValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionBackground() {
/* 147 */     return Color.red;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSelectionForeground() {
/* 152 */     return Color.red;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionMode() {
/* 157 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowTooltips() {
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getValueIsAdjusting() {
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVisibleRowCount() {
/* 172 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public Point indexToLocation(int index) {
/* 177 */     return new Point();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelectedIndex(int index) {
/* 182 */     int[] selectedIdx = getSelectedIndices();
/* 183 */     for (int i = 0; i < selectedIdx.length; i++) {
/*     */       
/* 185 */       if (selectedIdx[i] == index)
/* 186 */         return true; 
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelectionEmpty() {
/* 193 */     return (this.m_SelectedItems.size() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int locationToIndex(Point location) {
/* 198 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDragEnabled(boolean b) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFixedCellHeight(int height) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFixedCellWidth(int width) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLayoutOrientation(int orientation) {}
/*     */ 
/*     */   
/*     */   public void setListData(Object[] listData) {
/* 219 */     clearItems();
/* 220 */     for (int i = 0; i < listData.length; i++) {
/* 221 */       this.m_Items.add(listData[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setListData(Vector listData) {
/* 226 */     clearItems();
/* 227 */     for (int i = 0; i < listData.size(); i++) {
/* 228 */       this.m_Items.add(listData.elementAt(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModel(ListModel model) {}
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int index) {
/* 237 */     if (index == -1) {
/*     */       
/* 239 */       this.m_SelectedItems.clear();
/*     */       
/*     */       return;
/*     */     } 
/* 243 */     if (this.m_Items.size() > index && this.m_SelectedItems.indexOf(this.m_Items.get(index)) == -1) {
/* 244 */       this.m_SelectedItems.add(this.m_Items.get(index));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSelectedIndices(int[] indices) {
/* 249 */     for (int i = 0; i < indices.length; i++) {
/* 250 */       setSelectedIndex(indices[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSelectedValue(Object anObject, boolean shouldScroll) {
/* 255 */     setSelectedIndex(-1);
/* 256 */     if (anObject != null)
/*     */     {
/* 258 */       if (this.m_Items.indexOf(anObject) != -1) {
/* 259 */         this.m_SelectedItems.add(anObject);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSelection(int index) {
/* 265 */     setSelectedIndex(index);
/*     */   }
/*     */   
/*     */   public void setSelectionBackground(Color selectionBackground) {}
/*     */   
/*     */   public void setSelectionForeground(Color selectionForeground) {}
/*     */   
/*     */   public void setSelectionInterval(int anchor, int lead) {}
/*     */   
/*     */   public void setSelectionMode(int selectionMode) {}
/*     */   
/*     */   public void setShowTooltips(boolean value) {}
/*     */   
/*     */   public void setValueIsAdjusting(boolean b) {}
/*     */   
/*     */   public void setVisibleRowCount(int visibleRowCount) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsListBoxEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */