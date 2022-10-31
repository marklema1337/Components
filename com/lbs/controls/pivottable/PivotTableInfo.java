/*     */ package com.lbs.controls.pivottable;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.PivotExtraColumn;
/*     */ import com.lbs.controls.numericedit.JLbsBigDecimalFormatter;
/*     */ import com.lbs.controls.tablereport.TableReportFormatter;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import java.util.Vector;
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
/*     */ public class PivotTableInfo
/*     */ {
/*     */   private Object m_Context;
/*     */   private String m_QueryName;
/*     */   private String[] m_ColumnNames;
/*     */   private Vector<Integer> m_UsedColumnIndexes;
/*     */   private Vector<JLbsBigDecimalFormatter> m_BigDecimalFormatters;
/*     */   private ICachedHashTable<Integer, PivotCellValues> m_DataMap;
/*     */   private PivotViewPreferences m_ViewPreferences;
/*     */   private boolean m_SuperUser = true;
/*     */   private Object m_ReportWrapper;
/*     */   private ILbsCultureInfo m_CultureInfo;
/*     */   private ILocalizationServices m_LclService;
/*     */   private PivotExtraColumn[] m_ExtraColumns;
/*     */   private PivotExtraColumn[] m_SelectedExtraColumns;
/*     */   private ILbsComponentBase m_GridComponent;
/*     */   private String m_FormName;
/*     */   private static TableReportFormatter ms_Formatter;
/*     */   
/*     */   public String[] getColumnNames() {
/*  44 */     return this.m_ColumnNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnNames(String[] columnNames) {
/*  49 */     this.m_ColumnNames = columnNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<Integer> getUsedColumnIndexes() {
/*  54 */     return this.m_UsedColumnIndexes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUsedColumnIndexes(Vector<Integer> usedColumnIndexes) {
/*  59 */     this.m_UsedColumnIndexes = usedColumnIndexes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<JLbsBigDecimalFormatter> getBigDecimalFormatters() {
/*  64 */     return this.m_BigDecimalFormatters;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBigDecimalFormatters(Vector<JLbsBigDecimalFormatter> bigDecimalFormatters) {
/*  69 */     this.m_BigDecimalFormatters = bigDecimalFormatters;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICachedHashTable<Integer, PivotCellValues> getDataMap() {
/*  74 */     return this.m_DataMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDataMap(ICachedHashTable<Integer, PivotCellValues> dataMap) {
/*  79 */     this.m_DataMap = dataMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryName() {
/*  84 */     return this.m_QueryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryName(String queryName) {
/*  89 */     this.m_QueryName = queryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public PivotViewPreferences getViewPreferences() {
/*  94 */     return this.m_ViewPreferences;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setViewPreferences(PivotViewPreferences viewPreferences) {
/*  99 */     this.m_ViewPreferences = viewPreferences;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSuperUser() {
/* 104 */     return this.m_SuperUser;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuperUser(boolean superUser) {
/* 109 */     this.m_SuperUser = superUser;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getContext() {
/* 114 */     return this.m_Context;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContext(Object context) {
/* 119 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getReportWrapper() {
/* 124 */     return this.m_ReportWrapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportWrapper(Object reportWrapper) {
/* 129 */     this.m_ReportWrapper = reportWrapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 134 */     return this.m_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 139 */     this.m_CultureInfo = cultureInfo;
/* 140 */     ms_Formatter = new TableReportFormatter(cultureInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public static TableReportFormatter getFormatter() {
/* 145 */     return ms_Formatter;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILocalizationServices getLclService() {
/* 150 */     return this.m_LclService;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLclService(ILocalizationServices lclService) {
/* 155 */     this.m_LclService = lclService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PivotExtraColumn[] getExtraColumns() {
/* 163 */     return this.m_ExtraColumns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraColumns(PivotExtraColumn[] extraColumns) {
/* 171 */     this.m_ExtraColumns = extraColumns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PivotExtraColumn[] getSelectedExtraColumns() {
/* 179 */     return this.m_SelectedExtraColumns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedExtraColumns(PivotExtraColumn[] selectedExtraColumns) {
/* 187 */     this.m_SelectedExtraColumns = selectedExtraColumns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponentBase getGridComponent() {
/* 195 */     return this.m_GridComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGridComponent(ILbsComponentBase gridComponent) {
/* 203 */     this.m_GridComponent = gridComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormName() {
/* 208 */     return this.m_FormName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormName(String formName) {
/* 213 */     this.m_FormName = formName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\pivottable\PivotTableInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */