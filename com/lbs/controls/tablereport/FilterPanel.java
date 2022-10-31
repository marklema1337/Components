/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import info.clearthought.layout.TableLayout;
/*    */ import info.clearthought.layout.TableLayoutConstraints;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.util.HashMap;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FilterPanel
/*    */   extends JPanel
/*    */ {
/* 27 */   private HashMap<Integer, TableReportHeaderInfo> map = new HashMap<>();
/*    */ 
/*    */   
/*    */   public boolean contains(TableReportHeaderInfo headerInfo) {
/* 31 */     return this.map.keySet().contains(headerInfo.getColumnIndex());
/*    */   }
/*    */   
/*    */   public void add(FilterEntryPanel filterEntryPanel) {
/* 35 */     this.map.put(filterEntryPanel.getHeaderInfo().getColumnIndex(), filterEntryPanel.getHeaderInfo());
/*    */     
/* 37 */     TableLayout layout = (TableLayout)getLayout();
/* 38 */     if (layout.getNumRow() <= getComponentCount()) {
/* 39 */       layout.insertRow(getComponentCount(), -1.0D);
/*    */     }
/*    */     
/* 42 */     TableLayoutConstraints c = new TableLayoutConstraints();
/* 43 */     c.col1 = 0;
/* 44 */     c.col2 = 0;
/* 45 */     c.hAlign = 0;
/* 46 */     c.vAlign = 1;
/* 47 */     c.row1 = getComponentCount();
/* 48 */     c.row2 = getComponentCount();
/* 49 */     add(filterEntryPanel, c);
/*    */     
/* 51 */     setPreferredSize(new Dimension(0, getComponentCount() * 22));
/*    */   }
/*    */   
/*    */   public void remove(TableReportHeaderInfo headerInfo) {
/* 55 */     if (!this.map.keySet().contains(headerInfo.getColumnIndex())) {
/*    */       return;
/*    */     }
/*    */     
/* 59 */     FilterEntryPanel filterEntryPanel = getFilterEntryPanel(headerInfo);
/* 60 */     TableLayout layout = (TableLayout)getLayout();
/*    */     
/* 62 */     if (filterEntryPanel != null) {
/* 63 */       TableLayoutConstraints c = layout.getConstraints(filterEntryPanel);
/* 64 */       this.map.remove(headerInfo.getColumnIndex());
/* 65 */       remove(filterEntryPanel);
/* 66 */       layout.deleteRow(c.row1);
/*    */     } 
/*    */     
/* 69 */     setPreferredSize(new Dimension(0, getComponentCount() * 22)); } private FilterEntryPanel getFilterEntryPanel(TableReportHeaderInfo headerInfo) {
/*    */     byte b;
/*    */     int i;
/*    */     Component[] arrayOfComponent;
/* 73 */     for (i = (arrayOfComponent = getComponents()).length, b = 0; b < i; ) { Component c = arrayOfComponent[b];
/* 74 */       if (c instanceof FilterEntryPanel) {
/*    */ 
/*    */ 
/*    */         
/* 78 */         FilterEntryPanel filterEntryPanel = (FilterEntryPanel)c;
/* 79 */         if (filterEntryPanel.getHeaderInfo().equals(headerInfo))
/* 80 */           return filterEntryPanel; 
/*    */       } 
/*    */       b++; }
/*    */     
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\FilterPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */