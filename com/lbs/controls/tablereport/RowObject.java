/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class RowObject
/*     */   implements Comparable<RowObject>, Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private Object[] rowData;
/*     */   private HashMap<Integer, CellDisplay> display;
/*  14 */   private static Vector<TableReportHeaderInfo> headers = new Vector<>();
/*  15 */   private static HashMap<Integer, TableReportHeaderInfo> headerInfos = new HashMap<>();
/*     */   
/*     */   public RowObject(Integer id, Object[] rowData) {
/*  18 */     this.id = id;
/*  19 */     this.rowData = rowData;
/*     */     
/*  21 */     this.display = new HashMap<>();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  25 */     StringBuilder buffer = new StringBuilder("[");
/*  26 */     for (int index = 0; index < this.rowData.length; index++) {
/*  27 */       Object value = this.rowData[index];
/*     */       
/*  29 */       if (index > 0) {
/*  30 */         buffer.append(", ");
/*     */       }
/*     */       
/*  33 */       buffer.append(TableReportInfo.getFormatter().format(value));
/*     */     } 
/*     */     
/*  36 */     buffer.append("]");
/*  37 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public Object[] getRowData() {
/*  41 */     return this.rowData;
/*     */   }
/*     */   
/*     */   public void setRowData(Object[] rowData) {
/*  45 */     this.rowData = rowData;
/*     */   }
/*     */   
/*     */   public static void setHeaders(Vector<TableReportHeaderInfo> headers) {
/*  49 */     RowObject.headers = headers;
/*     */   }
/*     */   
/*     */   public static void setHeaderInfos(HashMap<Integer, TableReportHeaderInfo> headerInfos) {
/*  53 */     RowObject.headerInfos = headerInfos;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  58 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  62 */     this.id = id;
/*     */   }
/*     */   
/*     */   private int compareDeep(RowObject o) {
/*  66 */     if (headerInfos == null) {
/*  67 */       return 0;
/*     */     }
/*     */     
/*  70 */     for (Iterator<Integer> iterator = headerInfos.keySet().iterator(); iterator.hasNext(); ) { int modelIndex = ((Integer)iterator.next()).intValue();
/*  71 */       TableReportHeaderInfo headerInfo = headerInfos.get(Integer.valueOf(modelIndex));
/*     */       
/*  73 */       if (headerInfo.getSortDirection() == TableReportConstants.SortDirection.UNDEFINED) {
/*     */         continue;
/*     */       }
/*     */       
/*  77 */       Integer columnIndex = headerInfo.getColumnIndex();
/*     */       
/*  79 */       for (TableReportHeaderInfo hinfo : headers) {
/*  80 */         if (hinfo.getColumnIndex().equals(columnIndex));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  85 */       Comparable<Comparable> c1 = (Comparable)this.rowData[headerInfo.getColumnIndex().intValue()];
/*  86 */       Comparable c2 = (Comparable)o.getRowData()[headerInfo.getColumnIndex().intValue()];
/*     */       
/*  88 */       int result = c1.compareTo(c2);
/*     */       
/*  90 */       if (result != 0) {
/*  91 */         if (headerInfo.getSortDirection() == TableReportConstants.SortDirection.DESC) {
/*  92 */           return -1 * result;
/*     */         }
/*     */         
/*  95 */         return result;
/*     */       }  }
/*     */ 
/*     */     
/*  99 */     return 0;
/*     */   }
/*     */   
/*     */   public int compareSimple(RowObject o) {
/* 103 */     if (o == null) {
/* 104 */       return -1;
/*     */     }
/*     */     
/* 107 */     if (this.rowData.length != (o.getRowData()).length) {
/* 108 */       return -1;
/*     */     }
/*     */     
/* 111 */     if (headers != null) {
/* 112 */       for (int index = 0; index < headers.size(); index++) {
/* 113 */         TableReportHeaderInfo headerInfo = headers.get(index);
/* 114 */         Comparable<Comparable> c1 = (Comparable)this.rowData[headerInfo.getColumnIndex().intValue()];
/* 115 */         Comparable c2 = (Comparable)o.getRowData()[headerInfo.getColumnIndex().intValue()];
/*     */         
/* 117 */         int result = c1.compareTo(c2);
/*     */         
/* 119 */         if (result != 0) {
/* 120 */           if (((TableReportHeaderInfo)headers.get(index)).getSortDirection() == TableReportConstants.SortDirection.ASC) {
/* 121 */             return result;
/*     */           }
/*     */           
/* 124 */           return -1 * result;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 130 */     return 0;
/*     */   }
/*     */   
/*     */   public int compareTo(RowObject o) {
/* 134 */     int result = compareSimple(o);
/*     */     
/* 136 */     if (result != 0) {
/* 137 */       return result;
/*     */     }
/*     */     
/* 140 */     return compareDeep(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 147 */     if (!(obj instanceof RowObject))
/* 148 */       return false; 
/* 149 */     return (compareTo((RowObject)obj) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 156 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, CellDisplay> getDisplay() {
/* 161 */     return this.display;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplay(HashMap<Integer, CellDisplay> display) {
/* 166 */     this.display = display;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\RowObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */