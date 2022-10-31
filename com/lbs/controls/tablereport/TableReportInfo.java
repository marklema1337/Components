/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.platform.interfaces.ICachedList;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableReportInfo
/*     */ {
/*     */   private String m_Title;
/*     */   private Vector<String> m_HeaderNames;
/*     */   private Vector<Integer> m_FieldTypes;
/*     */   private Vector<String> m_FieldNames;
/*     */   private ICachedList<RowObject> m_DataList;
/*     */   private ILbsCultureInfo m_CultureInfo;
/*     */   private Vector<Integer> m_HorizontalAlignments;
/*     */   private Object m_Context;
/*     */   private boolean m_SuperUser;
/*     */   private TableReportPreferences m_ReportPreferences;
/*     */   private String m_QueryName;
/*     */   private ILocalizationServices m_LclService;
/*     */   private ILbsComponentBase m_GridComponent;
/*     */   private String m_FormName;
/*     */   private List<Class<?>> m_Classes;
/*     */   private boolean m_FromReport = false;
/*     */   private static TableReportFormatter ms_Formatter;
/*     */   
/*     */   public String getFormName() {
/*  34 */     return this.m_FormName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormName(String formName) {
/*  39 */     this.m_FormName = formName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<String> getHeaderNames() {
/*  44 */     return this.m_HeaderNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeaderNames(Vector<String> headerNames) {
/*  49 */     this.m_HeaderNames = headerNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  54 */     return this.m_Title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/*  59 */     this.m_Title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICachedList<RowObject> getDataList() {
/*  64 */     return this.m_DataList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDataList(ICachedList<RowObject> dataList) {
/*  69 */     this.m_DataList = dataList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/*  74 */     this.m_CultureInfo = cultureInfo;
/*     */     
/*  76 */     ms_Formatter = new TableReportFormatter(cultureInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/*  81 */     return this.m_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<Integer> getHorizontalAlignments() {
/*  86 */     return this.m_HorizontalAlignments;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalAlignments(Vector<Integer> horizontalAlignments) {
/*  91 */     this.m_HorizontalAlignments = horizontalAlignments;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TableReportFormatter getFormatter() {
/*  96 */     return ms_Formatter;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getContext() {
/* 101 */     return this.m_Context;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContext(Object context) {
/* 106 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSuperUser() {
/* 111 */     return this.m_SuperUser;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuperUser(boolean superUser) {
/* 116 */     this.m_SuperUser = superUser;
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportPreferences getReportPreferences() {
/* 121 */     return this.m_ReportPreferences;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportPreferences(TableReportPreferences reportPreferences) {
/* 126 */     this.m_ReportPreferences = reportPreferences;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryName() {
/* 131 */     return this.m_QueryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryName(String queryName) {
/* 136 */     this.m_QueryName = queryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<Integer> getFieldTypes() {
/* 141 */     return this.m_FieldTypes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFieldTypes(Vector<Integer> fieldTypes) {
/* 146 */     this.m_FieldTypes = fieldTypes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<String> getFieldNames() {
/* 151 */     return this.m_FieldNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFieldNames(Vector<String> fieldNames) {
/* 156 */     this.m_FieldNames = fieldNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILocalizationServices getLclService() {
/* 161 */     return this.m_LclService;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLclService(ILocalizationServices lclService) {
/* 166 */     this.m_LclService = lclService;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponentBase getGridComponent() {
/* 171 */     return this.m_GridComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGridComponent(ILbsComponentBase gridComponent) {
/* 176 */     this.m_GridComponent = gridComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Class<?>> getClasses() {
/* 181 */     return this.m_Classes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClasses(List<Class<?>> classes) {
/* 186 */     this.m_Classes = classes;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFromReport() {
/* 191 */     return this.m_FromReport;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromReport(boolean fromReport) {
/* 196 */     this.m_FromReport = fromReport;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */