/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.recording.interfaces.ILbsReportTestListener;
/*     */ import com.lbs.report.params.IReportParameterConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsRunContextParameters
/*     */   implements IReportParameterConstants
/*     */ {
/*     */   public transient ILbsReportTestListener TestListener;
/*     */   public String SMTPListenerClassName;
/*     */   public Object InitContext;
/*     */   public Object RunContext;
/*     */   public Object Context0;
/*     */   public Object Context1;
/*     */   public Object Context2;
/*  25 */   public Object LabelPrintProperties = null;
/*     */   public JLbsReportOperationSettings OperationSettings;
/*     */   public JLbsTextReportOptions TextReportOptions;
/*     */   public String Language;
/*     */   public String SetValue;
/*     */   public boolean AutoGeneratedLayaout = false;
/*     */   public boolean TestMode = false;
/*  32 */   public JLbsReportTestParams TestParams = null;
/*     */   
/*     */   public IParameter ReportParameter;
/*  35 */   public int ExecutionType = 0;
/*     */   
/*     */   public transient IApplicationContext ClientContext;
/*     */   
/*     */   public boolean DirectPrint;
/*     */   
/*     */   public boolean MailToFromServer;
/*     */   
/*     */   public boolean PrintCustomized;
/*     */   
/*     */   public boolean RunModal;
/*     */   
/*     */   public boolean IsCopy;
/*     */   
/*     */   public boolean Silent;
/*     */   public boolean PrintDataCellsOnly;
/*     */   public int DeviceType;
/*     */   public String CustomReportName;
/*     */   public String ReportTitle;
/*     */   public String MessageTitle;
/*     */   public String Message;
/*     */   public int[] HiddenFilters;
/*  57 */   public int DlgOpType = 0;
/*     */   
/*  59 */   public int ReportType = -1;
/*     */ 
/*     */   
/*     */   public boolean HideFilterTab;
/*     */ 
/*     */   
/*     */   public String Operation;
/*     */ 
/*     */   
/*     */   public String Password;
/*     */ 
/*     */   
/*     */   public int CustFilterType;
/*     */ 
/*     */   
/*     */   public transient String[] FilteredFunctionSets;
/*     */ 
/*     */   
/*     */   public transient String[] FilteredFunctions;
/*     */ 
/*     */   
/*     */   public transient ILbsRunContextListener RunContextListener;
/*     */   
/*     */   public Object ReportContentListener;
/*     */   
/*  84 */   public int OutputOptions = 4;
/*  85 */   public int DebugOptions = 14;
/*  86 */   public double PageHeightInCM = 27.9D;
/*  87 */   public int PixelPerInch = 96;
/*     */   
/*     */   public boolean PrintFilters = false;
/*     */   
/*     */   public boolean m_SplitReportIntoParts = false;
/*     */   
/*     */   public int UserSettings;
/*     */   public boolean UsePrinterSettings;
/*     */   public String ContractInstanceId;
/*     */   
/*     */   public JLbsRunContextParameters() {}
/*     */   
/*     */   public JLbsRunContextParameters(Object initCtx) {
/* 100 */     this.InitContext = initCtx;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsRunContextParameters(Object initCtx, Object runCtx) {
/* 105 */     this.InitContext = initCtx;
/* 106 */     this.RunContext = runCtx;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContractInstanceId() {
/* 111 */     return this.ContractInstanceId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContractInstanceId(String contractInstanceId) {
/* 116 */     this.ContractInstanceId = contractInstanceId;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsRunContextParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */