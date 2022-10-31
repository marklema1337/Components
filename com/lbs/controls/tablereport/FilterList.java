/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterList
/*     */   extends JList
/*     */ {
/*     */   private TableReportHeaderInfo headerInfo;
/*     */   
/*     */   public void clearFilter() {
/*  25 */     DefaultListModel<FilterListCellValue> model = (DefaultListModel)getModel();
/*  26 */     for (int index = 1; index < model.getSize(); index++) {
/*  27 */       FilterListCellValue flcv = model.getElementAt(index);
/*  28 */       flcv.setIncluded(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void restoreFilter() {
/*  33 */     DefaultListModel<FilterListCellValue> model = (DefaultListModel)getModel();
/*  34 */     for (int i = 0; i < this.headerInfo.getFilteredValues().size(); i++) {
/*  35 */       Integer index = this.headerInfo.getFilteredValues().get(i);
/*  36 */       FilterListCellValue flcv = model.getElementAt(index.intValue());
/*  37 */       flcv.setIncluded(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(FilterListCellValue flcv) {
/*  42 */     DefaultListModel<FilterListCellValue> model = (DefaultListModel)getModel();
/*  43 */     model.addElement(flcv);
/*     */   }
/*     */   
/*     */   public void addAll(List<FilterListCellValue> list, boolean addSelectAll) {
/*  47 */     DefaultListModel<FilterListCellValue> model = new DefaultListModel();
/*     */     
/*  49 */     if (list == this.headerInfo.getDistinctValues()) {
/*  50 */       if (addSelectAll) {
/*  51 */         FilterListCellValue selectAll = new FilterListCellValue(TableReportConstants.STRINGS2[2], false);
/*  52 */         this.headerInfo.getDistinctValues().insertElementAt(selectAll, 0);
/*     */       } 
/*     */     } else {
/*     */       
/*  56 */       this.headerInfo.getDistinctValues().clear();
/*     */       
/*  58 */       if (addSelectAll) {
/*  59 */         FilterListCellValue selectAll = new FilterListCellValue(TableReportConstants.STRINGS2[2], false);
/*  60 */         this.headerInfo.getDistinctValues().insertElementAt(selectAll, 0);
/*     */       } 
/*     */       
/*  63 */       for (FilterListCellValue flcv : list) {
/*  64 */         this.headerInfo.getDistinctValues().add(flcv);
/*     */       }
/*     */     } 
/*     */     
/*  68 */     for (FilterListCellValue flcv : this.headerInfo.getDistinctValues()) {
/*  69 */       model.addElement(flcv);
/*     */     }
/*     */     
/*  72 */     setModel((ListModel)model);
/*     */   }
/*     */   
/*     */   public FilterList() {
/*  76 */     this((TableReportHeaderInfo)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FilterList(TableReportHeaderInfo headerInfo) {
/*  82 */     this.headerInfo = headerInfo;
/*     */     
/*  84 */     setVisibleRowCount(12);
/*  85 */     setCellRenderer(new CheckListCellRenderer());
/*     */     
/*  87 */     addMouseListener(new MouseAdapter() {
/*     */           public void mousePressed(MouseEvent e) {
/*  89 */             DefaultListModel<FilterListCellValue> model = (DefaultListModel)FilterList.this.getModel();
/*  90 */             int index = FilterList.this.locationToIndex(e.getPoint());
/*  91 */             FilterListCellValue flcv = model.get(index);
/*     */             
/*  93 */             CheckListCellRenderer renderer = (CheckListCellRenderer)FilterList.this.getCellRenderer();
/*     */             
/*  95 */             if (renderer.getCheck().getWidth() >= (e.getPoint()).x) {
/*  96 */               flcv.setIncluded(!flcv.isIncluded());
/*     */               
/*  98 */               if (index == 0 && model.size() > 1) {
/*  99 */                 List<FilterListCellValue> list = new ArrayList<>();
/* 100 */                 list.add(flcv);
/*     */                 
/* 102 */                 for (int i = 1; i < model.size(); i++) {
/* 103 */                   FilterListCellValue flcvIndividual = model.get(i);
/* 104 */                   flcvIndividual.setIncluded(flcv.isIncluded());
/* 105 */                   list.add(flcvIndividual);
/*     */                 } 
/*     */               } 
/*     */               
/* 109 */               FilterList.this.updateUI();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<FilterListCellValue> getExclusionList() {
/* 117 */     Vector<FilterListCellValue> list = new Vector<>();
/*     */     
/* 119 */     for (int index = 1; index < getModel().getSize(); index++) {
/* 120 */       FilterListCellValue flcv = (FilterListCellValue)getModel().getElementAt(index);
/* 121 */       if (!flcv.isIncluded()) {
/* 122 */         list.add(flcv);
/*     */       }
/*     */     } 
/*     */     
/* 126 */     return list;
/*     */   }
/*     */   
/*     */   public boolean hasSelectedValue() {
/* 130 */     boolean has = false;
/*     */     
/* 132 */     for (int index = 1; index < getModel().getSize(); index++) {
/* 133 */       FilterListCellValue flcv = (FilterListCellValue)getModel().getElementAt(index);
/* 134 */       has |= flcv.isIncluded();
/*     */     } 
/*     */     
/* 137 */     return has;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChanged() {
/* 145 */     ArrayList<FilterListCellValue> currentValues = new ArrayList<>();
/* 146 */     boolean changed = false;
/*     */     int index;
/* 148 */     for (index = 0; index < getModel().getSize(); index++) {
/* 149 */       currentValues.add((FilterListCellValue)getModel().getElementAt(index));
/*     */     }
/*     */     
/* 152 */     for (index = 1; index < this.headerInfo.getDistinctValues().size(); index++) {
/* 153 */       FilterListCellValue flcv = this.headerInfo.getDistinctValues().get(index);
/* 154 */       FilterListCellValue compare = currentValues.get(index);
/* 155 */       int i = changed | flcv.isIncluded() ^ compare.isIncluded();
/*     */       
/* 157 */       if (i != 0) {
/* 158 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   public void refreshHeaderInfo() {
/* 166 */     for (int index = 0; index < getModel().getSize(); index++) {
/* 167 */       FilterListCellValue flcv = (FilterListCellValue)getModel().getElementAt(index);
/* 168 */       ((FilterListCellValue)this.headerInfo.getDistinctValues().get(index)).setIncluded(flcv.isIncluded());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveCurrentState() {
/* 173 */     this.headerInfo.getDistinctValues().clear();
/*     */     
/* 175 */     for (int index = 0; index < getModel().getSize(); index++) {
/* 176 */       FilterListCellValue flcv = (FilterListCellValue)getModel().getElementAt(index);
/* 177 */       this.headerInfo.getDistinctValues().add((FilterListCellValue)flcv.clone());
/*     */     } 
/*     */   }
/*     */   
/*     */   public TableReportHeaderInfo getHeaderInfo() {
/* 182 */     return this.headerInfo;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\FilterList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */