/*     */ package com.lbs.data.export.params;
/*     */ 
/*     */ import com.lbs.util.SetUtil;
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
/*     */ public class DataExchangeTemplate
/*     */ {
/*     */   public static final int OPTION_BO_FIELDS = 1;
/*     */   public static final int OPTION_SETTINGS = 2;
/*     */   public static final int EXCEL_SETTINGS = 4;
/*     */   private Integer m_Ref;
/*     */   private String m_Code;
/*     */   private IDataExchangeParams m_Params;
/*  23 */   private int m_SavedOptions = 7;
/*     */   
/*     */   private String m_BOName;
/*     */   
/*     */   private byte m_ExchangeType;
/*     */   
/*     */   private boolean m_Default = false;
/*     */ 
/*     */   
/*     */   public DataExchangeTemplate() {}
/*     */ 
/*     */   
/*     */   public DataExchangeTemplate(String code, IDataExchangeParams params, int savedOptions) {
/*  36 */     this.m_Code = code;
/*  37 */     this.m_Params = params;
/*  38 */     this.m_SavedOptions = savedOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  43 */     return this.m_Code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  48 */     this.m_Code = code;
/*     */   }
/*     */   
/*     */   public Integer getRef() {
/*  52 */     return this.m_Ref;
/*     */   }
/*     */   
/*     */   public void setRef(Integer m_Ref) {
/*  56 */     this.m_Ref = m_Ref;
/*     */   }
/*     */ 
/*     */   
/*     */   public IDataExchangeParams getParams() {
/*  61 */     return this.m_Params;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParams(IDataExchangeParams params) {
/*  66 */     this.m_Params = params;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSavedOptions() {
/*  71 */     return this.m_SavedOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSavedOptions(int savedOptions) {
/*  76 */     this.m_SavedOptions = savedOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSavedOption(int option) {
/*  81 */     return SetUtil.isOptionSet(this.m_SavedOptions, option);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBOName(String bOName) {
/*  86 */     this.m_BOName = bOName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBOName() {
/*  91 */     return this.m_BOName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExchangeType(byte exchangeType) {
/*  96 */     this.m_ExchangeType = exchangeType;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getExchangeType() {
/* 101 */     return this.m_ExchangeType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefault(boolean default1) {
/* 106 */     this.m_Default = default1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDefault() {
/* 111 */     return this.m_Default;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDefault() {
/* 116 */     return this.m_Default;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\DataExchangeTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */