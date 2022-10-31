/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ICachedList;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ public class TableReportHeaderInfo
/*     */   implements Serializable
/*     */ {
/*     */   private Integer columnIndex;
/*     */   private String title;
/*  14 */   private TableReportConstants.SortDirection sortDirection = TableReportConstants.SortDirection.ASC;
/*     */   
/*  16 */   private Vector<FilterListCellValue> distinctValues = new Vector<>();
/*  17 */   private Vector<Integer> filteredValues = new Vector<>();
/*     */   
/*     */   public TableReportHeaderInfo(Integer columnIndex, TableReportConstants.SortDirection sortDirection) {
/*  20 */     this.columnIndex = columnIndex;
/*  21 */     this.sortDirection = sortDirection;
/*     */   }
/*     */   
/*     */   public void toggle() {
/*  25 */     if (this.sortDirection == TableReportConstants.SortDirection.ASC) {
/*  26 */       this.sortDirection = TableReportConstants.SortDirection.DESC;
/*     */     } else {
/*     */       
/*  29 */       this.sortDirection = TableReportConstants.SortDirection.ASC;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createDistinctValues(ICachedList<RowObject> dataList) {
/*  34 */     this.distinctValues.clear();
/*     */     
/*  36 */     for (int row = 0; row < dataList.size(); row++) {
/*  37 */       Object object = ((RowObject)dataList.get(row)).getRowData()[this.columnIndex.intValue()];
/*  38 */       if (object == null) {
/*  39 */         object = "";
/*     */       }
/*     */       
/*  42 */       FilterListCellValue flcv = new FilterListCellValue(object, true);
/*  43 */       if (!this.distinctValues.contains(flcv)) {
/*  44 */         this.distinctValues.add(flcv);
/*     */       }
/*     */     } 
/*     */     
/*  48 */     Collections.sort(this.distinctValues);
/*     */   }
/*     */   
/*     */   public void createFilter() {
/*  52 */     this.filteredValues.clear();
/*     */     
/*  54 */     for (int index = 1; index < this.distinctValues.size(); index++) {
/*  55 */       FilterListCellValue flcv = this.distinctValues.get(index);
/*  56 */       if (!flcv.isIncluded()) {
/*  57 */         this.filteredValues.add(Integer.valueOf(index));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasFilteredValues() {
/*  63 */     return (this.filteredValues.size() > 0);
/*     */   }
/*     */   
/*     */   public void clearFilter(boolean permanent) {
/*  67 */     for (int i = 0; i < this.filteredValues.size(); i++) {
/*  68 */       Integer index = this.filteredValues.get(i);
/*  69 */       FilterListCellValue flcv = this.distinctValues.get(index.intValue());
/*  70 */       flcv.setIncluded(true);
/*     */     } 
/*     */     
/*  73 */     if (permanent) {
/*  74 */       this.filteredValues.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public void restoreFilter() {
/*  79 */     for (int i = 0; i < this.filteredValues.size(); i++) {
/*  80 */       Integer index = this.filteredValues.get(i);
/*  81 */       FilterListCellValue flcv = this.distinctValues.get(index.intValue());
/*  82 */       flcv.setIncluded(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void applyExclusionList(Vector<FilterListCellValue> list) {
/*  87 */     for (int index = 0; index < list.size(); index++) {
/*  88 */       FilterListCellValue flcv = list.get(index);
/*     */       
/*  90 */       for (int listIndex = 1; listIndex < this.distinctValues.size(); listIndex++) {
/*  91 */         FilterListCellValue listValue = this.distinctValues.get(listIndex);
/*     */         
/*  93 */         if (listValue.equals(flcv)) {
/*  94 */           listValue.setIncluded(false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilterString() {
/* 102 */     StringBuilder buffer = new StringBuilder("");
/* 103 */     buffer.append(String.valueOf(this.title) + " NOT IN (");
/*     */     
/* 105 */     for (int i = 0; i < this.filteredValues.size(); i++) {
/* 106 */       Integer index = this.filteredValues.get(i);
/* 107 */       FilterListCellValue flcv = this.distinctValues.get(index.intValue());
/*     */       
/* 109 */       if (i > 0) {
/* 110 */         buffer.append(", ");
/*     */       }
/*     */       
/* 113 */       String text = TableReportInfo.getFormatter().format(flcv.getValue());
/* 114 */       buffer.append(text);
/*     */     } 
/*     */     
/* 117 */     buffer.append(")");
/*     */     
/* 119 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public boolean isFiltered(RowObject rowObject) {
/* 123 */     Object value = rowObject.getRowData()[this.columnIndex.intValue()];
/*     */     
/* 125 */     for (int index = 1; index < this.distinctValues.size(); index++) {
/* 126 */       FilterListCellValue flcv = this.distinctValues.get(index);
/*     */       
/* 128 */       if (flcv.getValue().equals(value)) {
/* 129 */         return !flcv.isIncluded();
/*     */       }
/*     */     } 
/*     */     
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 137 */     if (o == null || !(o instanceof TableReportHeaderInfo)) {
/* 138 */       return false;
/*     */     }
/*     */     
/* 141 */     TableReportHeaderInfo info = (TableReportHeaderInfo)o;
/* 142 */     return this.columnIndex.equals(info.getColumnIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 147 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   public Integer getColumnIndex() {
/* 151 */     return this.columnIndex;
/*     */   }
/*     */   
/*     */   public void setColumnIndex(Integer columnIndex) {
/* 155 */     this.columnIndex = columnIndex;
/*     */   }
/*     */   
/*     */   public TableReportConstants.SortDirection getSortDirection() {
/* 159 */     return this.sortDirection;
/*     */   }
/*     */   
/*     */   public void setSortDirection(TableReportConstants.SortDirection sortDirection) {
/* 163 */     this.sortDirection = sortDirection;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 167 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 171 */     this.title = title;
/*     */   }
/*     */   
/*     */   public Vector<FilterListCellValue> getDistinctValues() {
/* 175 */     return this.distinctValues;
/*     */   }
/*     */   
/*     */   public Vector<Integer> getFilteredValues() {
/* 179 */     return this.filteredValues;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportHeaderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */