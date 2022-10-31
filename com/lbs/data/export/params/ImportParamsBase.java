/*     */ package com.lbs.data.export.params;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
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
/*     */ public class ImportParamsBase
/*     */   extends ExchangeParamsBase
/*     */   implements IDataImportParams
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private IntegratorBOParameter m_PKResolveParams;
/*     */   private boolean enableUsePrimaryKey = true;
/*     */   protected byte[] m_FileContent;
/*  27 */   private Hashtable<String, Integer> m_ExcelColumnMap = new Hashtable<>();
/*     */ 
/*     */   
/*     */   public ImportParamsBase() {
/*  31 */     setOption(4, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImportParamsBase(ImportParamsBase copy) {
/*  36 */     super(copy);
/*  37 */     this.m_PKResolveParams = copy.m_PKResolveParams;
/*  38 */     this.m_FileContent = copy.m_FileContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public IntegratorBOParameter getPKResolveParams() {
/*  43 */     return this.m_PKResolveParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPKResolveParams(IntegratorBOParameter resolveParams) {
/*  48 */     this.m_PKResolveParams = resolveParams;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IDataExchangeParams cloneParams() {
/*     */     try {
/*  55 */       Object clone = clone();
/*  56 */       if (clone instanceof ImportParamsBase) {
/*  57 */         return (ImportParamsBase)clone;
/*     */       }
/*  59 */     } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */ 
/*     */     
/*  62 */     return new ImportParamsBase(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/*  67 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getFileContent() {
/*  72 */     return this.m_FileContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFileContent(byte[] fileContent) {
/*  77 */     this.m_FileContent = fileContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFileContent() {
/*  82 */     if (!StringUtil.isEmpty(this.m_FileName)) {
/*     */       
/*     */       try {
/*     */         
/*  86 */         setFileContent(JLbsFileUtil.readFile(this.m_FileName));
/*     */       }
/*  88 */       catch (IOException e) {
/*     */         
/*  90 */         LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUseGivenPrimaryKey() {
/*  97 */     return isOptionSet(32);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUseGivenPrimaryKey(boolean useGivenPrimaryKey) {
/* 102 */     setOption(32, useGivenPrimaryKey);
/*     */   }
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
/*     */   public void setSingleTransaction(boolean singleTransaction) {
/* 117 */     setOption(8, singleTransaction);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSingleTransaction() {
/* 122 */     return isOptionSet(8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUseEnvironmentVariables(boolean useEnvironmentVars) {
/* 127 */     setOption(16, useEnvironmentVars);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUseEnvironmentVariables() {
/* 132 */     return (isOptionSet(16) && !isUseGivenPrimaryKey());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUpdateOverwrite(boolean overwrite) {
/* 137 */     setOption(64, overwrite);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUpdateOverwrite() {
/* 142 */     return isOptionSet(64);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExcelColumnMap(Hashtable<String, Integer> excelColumnMap) {
/* 150 */     this.m_ExcelColumnMap = excelColumnMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable<String, Integer> getExcelColumnMap() {
/* 158 */     return this.m_ExcelColumnMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnableUsePrimaryKey() {
/* 167 */     return this.enableUsePrimaryKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnableUsePrimaryKey(boolean value) {
/* 176 */     this.enableUsePrimaryKey = value;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ImportParamsBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */