/*     */ package com.lbs.data.expression;
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
/*     */ public class QueryItemParams
/*     */ {
/*     */   protected int m_RowCount;
/*     */   protected int m_StartRow;
/*     */   protected QueryItemListBase m_DelayedJoins;
/*     */   protected boolean m_QualifyColumns = true;
/*     */   protected boolean m_Distinct = false;
/*     */   protected boolean m_RownumSafe = true;
/*     */   protected boolean m_UseNLSSORT = true;
/*     */   protected boolean m_RowCountQuery = false;
/*     */   protected boolean m_DisableNoLock = false;
/*     */   protected Object m_ValueSource;
/*     */   
/*     */   public QueryItemParams(QueryItemParams src) {
/*  28 */     if (src != null) {
/*     */       
/*  30 */       this.m_RowCount = src.m_RowCount;
/*  31 */       this.m_StartRow = src.m_StartRow;
/*  32 */       this.m_QualifyColumns = src.m_QualifyColumns;
/*  33 */       this.m_Distinct = src.m_Distinct;
/*  34 */       this.m_RownumSafe = src.m_RownumSafe;
/*  35 */       this.m_UseNLSSORT = src.m_UseNLSSORT;
/*  36 */       this.m_RowCountQuery = src.m_RowCountQuery;
/*  37 */       this.m_DisableNoLock = src.m_DisableNoLock;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItemParams() {
/*  43 */     this.m_RowCount = 0;
/*  44 */     this.m_DelayedJoins = new QueryItemListBase(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItemListBase getDelayedJoins() {
/*  49 */     return this.m_DelayedJoins;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRowCount() {
/*  54 */     return this.m_RowCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRowCount(int i) {
/*  59 */     this.m_RowCount = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQualifyColumns() {
/*  64 */     return this.m_QualifyColumns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQualifyColumns(boolean b) {
/*  69 */     this.m_QualifyColumns = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDistinct() {
/*  74 */     return this.m_Distinct;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDistinct(boolean b) {
/*  79 */     this.m_Distinct = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRownumSafe() {
/*  84 */     return this.m_RownumSafe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRownumSafe(boolean isSafe) {
/*  89 */     this.m_RownumSafe = isSafe;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUseNLSSORT() {
/*  94 */     return this.m_UseNLSSORT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUseNLSSORT(boolean useNLSSORT) {
/*  99 */     this.m_UseNLSSORT = useNLSSORT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRowCountQuery() {
/* 104 */     return this.m_RowCountQuery;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRowCountQuery(boolean rowCountQuery) {
/* 109 */     this.m_RowCountQuery = rowCountQuery;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisableNoLock() {
/* 114 */     return this.m_DisableNoLock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisableNoLock(boolean disableNoLock) {
/* 119 */     this.m_DisableNoLock = disableNoLock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartRow(int startRow) {
/* 124 */     this.m_StartRow = startRow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStartRow() {
/* 129 */     return this.m_StartRow;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValueSource() {
/* 134 */     return this.m_ValueSource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueSource(Object valueSource) {
/* 139 */     this.m_ValueSource = valueSource;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\QueryItemParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */