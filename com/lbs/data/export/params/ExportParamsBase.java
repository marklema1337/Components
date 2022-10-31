/*     */ package com.lbs.data.export.params;
/*     */ 
/*     */ import com.lbs.data.query.QueryParams;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExportParamsBase
/*     */   extends ExchangeParamsBase
/*     */   implements IDataExportParams
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_QueryName;
/*     */   private QueryParams m_QueryParams;
/*  20 */   private int m_GrammarType = -1;
/*     */   
/*     */   private boolean m_ZipExportData;
/*     */   
/*     */   private int[] m_RefObjects;
/*     */   
/*     */   private int m_RefObjectsState;
/*     */   
/*     */   public ExportParamsBase() {}
/*     */   
/*     */   public ExportParamsBase(ExchangeParamsBase copy) {
/*  31 */     super(copy);
/*     */   }
/*     */ 
/*     */   
/*     */   public ExportParamsBase(ExportParamsBase copy) {
/*  36 */     super(copy);
/*  37 */     this.m_QueryName = copy.m_QueryName;
/*  38 */     this.m_QueryParams = copy.m_QueryParams;
/*  39 */     this.m_GrammarType = copy.m_GrammarType;
/*  40 */     this.m_ZipExportData = copy.m_ZipExportData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParams(QueryParams params) {
/*  45 */     this.m_QueryParams = params;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryParams getQueryParams() {
/*  50 */     return this.m_QueryParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryName(String queryName) {
/*  55 */     this.m_QueryName = queryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryName() {
/*  60 */     return this.m_QueryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGrammarType(int grammarType) {
/*  65 */     this.m_GrammarType = grammarType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGrammarType() {
/*  70 */     return this.m_GrammarType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean zipExportData() {
/*  75 */     return this.m_ZipExportData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setZipExportData(boolean zip) {
/*  80 */     this.m_ZipExportData = zip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSkipDefaultValues() {
/*  86 */     return isOptionSet(128);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkipDefaultValues(boolean skipDefaultValues) {
/*  91 */     setOption(128, skipDefaultValues);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IDataExchangeParams cloneParams() {
/*     */     try {
/*  98 */       Object clone = clone();
/*  99 */       if (clone instanceof ExportParamsBase) {
/* 100 */         return (ExportParamsBase)clone;
/*     */       }
/* 102 */     } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */ 
/*     */     
/* 105 */     return new ExportParamsBase(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/* 110 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getRefObjects() {
/* 115 */     return this.m_RefObjects;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRefObjects(int[] refObjects) {
/* 120 */     this.m_RefObjects = refObjects;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRefObjectsState() {
/* 126 */     return this.m_RefObjectsState;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRefObjectsState(int refObjectsState) {
/* 132 */     this.m_RefObjectsState = refObjectsState;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ExportParamsBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */