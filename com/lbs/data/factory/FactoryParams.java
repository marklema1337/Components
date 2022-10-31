/*     */ package com.lbs.data.factory;
/*     */ 
/*     */ import com.lbs.contract.ContractParameter;
/*     */ import com.lbs.data.export.params.ISecondaryKeyResolveListener;
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.SetUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class FactoryParams
/*     */   implements Serializable, Cloneable, Externalizable, ICacheKey, IFactoryParams, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static boolean DEFAULT_TRANSFER_BO_BACK = false;
/*  32 */   private ISubstitutionVariables m_Variables = null;
/*  33 */   private NamedVariables m_Parameters = null;
/*  34 */   private ArrayList<Object> m_SegmentValues = null;
/*  35 */   private FactoryCascadeOptions m_CascadeRestrictions = null;
/*     */   
/*  37 */   private int m_Options = 128;
/*     */   
/*  39 */   private String m_IndexName = null;
/*  40 */   private String m_IndexSuffix = null;
/*  41 */   private String m_ActiveFilter = null;
/*  42 */   private int m_MaxCascadeDepth = -1;
/*     */   private String m_Customization;
/*     */   private String m_CustomObjectName;
/*  45 */   private ServerSideInitializationParams m_ServerSideInitParams = null;
/*     */   
/*     */   private ContractParameter[] m_ContractInputs;
/*     */   
/*  49 */   private transient ISecondaryKeyResolveListener m_Listener = null;
/*     */   
/*     */   private String m_CacheObjectName;
/*  52 */   private transient int m_KeyHashValue = DO_NOT_CACHE.intValue();
/*     */ 
/*     */   
/*     */   public FactoryParams() {
/*  56 */     this.m_Parameters = new NamedVariables();
/*  57 */     this.m_Variables = new NamedVariables();
/*  58 */     this.m_SegmentValues = new ArrayList();
/*  59 */     this.m_CascadeRestrictions = new FactoryCascadeOptions();
/*  60 */     setObjectTransferedBack(DEFAULT_TRANSFER_BO_BACK);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  65 */     setRequestLock(false);
/*     */     
/*  67 */     this.m_IndexName = null;
/*  68 */     this.m_IndexSuffix = null;
/*  69 */     this.m_ActiveFilter = null;
/*     */     
/*  71 */     this.m_Variables = new NamedVariables();
/*     */     
/*  73 */     if (this.m_Parameters != null) {
/*  74 */       this.m_Parameters.clear();
/*     */     }
/*  76 */     if (this.m_SegmentValues != null) {
/*  77 */       this.m_SegmentValues.clear();
/*     */     }
/*  79 */     setEscapeReadProcessor(false);
/*     */     
/*  81 */     setDomainLess(false);
/*  82 */     setSkipRecordLog(false);
/*  83 */     setDeepRecordLog(false);
/*  84 */     this.m_ContractInputs = null;
/*  85 */     setReadMultilangValues(false);
/*  86 */     setInitializeObjectOnServer(false);
/*     */     
/*  88 */     setForExchange(false);
/*     */     
/*  90 */     this.m_ServerSideInitParams = null;
/*  91 */     setAutoIncrementAlreadyGot(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadMultilangValues() {
/*  96 */     return SetUtil.isOptionSet(this.m_Options, 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReadMultilangValues(boolean readMultilangValues) {
/* 101 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 4096, readMultilangValues);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInitializeObjectOnServer() {
/* 106 */     return SetUtil.isOptionSet(this.m_Options, 8192);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitializeObjectOnServer(boolean InitializeObjectOnServer) {
/* 111 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 8192, InitializeObjectOnServer);
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerSideInitializationParams getServerSideInitParams() {
/* 116 */     return this.m_ServerSideInitParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServerSideInitParams(ServerSideInitializationParams onServerParams) {
/* 121 */     this.m_ServerSideInitParams = onServerParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReadModificationOnly(boolean readModificationOnly) {
/* 126 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 256, readModificationOnly);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadModificationOnly() {
/* 131 */     return SetUtil.isOptionSet(this.m_Options, 256);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEscapeReadProcessor(boolean escapeReadProcessor) {
/* 136 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 512, escapeReadProcessor);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEscapeReadProcessor() {
/* 141 */     return SetUtil.isOptionSet(this.m_Options, 512);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainLess(boolean domainLess) {
/* 146 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 1024, domainLess);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDomainLess() {
/* 151 */     return SetUtil.isOptionSet(this.m_Options, 1024);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkipRecordLog() {
/* 156 */     return SetUtil.isOptionSet(this.m_Options, 2048);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkipRecordLog(boolean skipRecordLog) {
/* 161 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 2048, skipRecordLog);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDeepRecordLog() {
/* 166 */     return SetUtil.isOptionSet(this.m_Options, 131072);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeepRecordLog(boolean skipRecordLog) {
/* 171 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 131072, skipRecordLog);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexName(String indexName) {
/* 176 */     this.m_IndexName = indexName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActiveFilter() {
/* 185 */     return this.m_ActiveFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIndexName() {
/* 194 */     return this.m_IndexName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedVariables getParameters() {
/* 203 */     return this.m_Parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISubstitutionVariables getVariables() {
/* 212 */     return this.m_Variables;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveFilter(String activeFilter) {
/* 221 */     this.m_ActiveFilter = activeFilter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRangeFilter(String activeFilter) {
/* 226 */     this.m_ActiveFilter = activeFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getSegmentValues() {
/* 235 */     return this.m_SegmentValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxCascadeDepth() {
/* 244 */     return this.m_MaxCascadeDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxCascadeDepth(int maxCascadeDepth) {
/* 253 */     this.m_MaxCascadeDepth = maxCascadeDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FactoryCascadeOptions getCascadeRestrictions() {
/* 262 */     return this.m_CascadeRestrictions;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAutoIncrementAlreadyGot() {
/* 267 */     return SetUtil.isOptionSet(this.m_Options, 16384);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoIncrementAlreadyGot(boolean autoIncrementAlreadyGot) {
/* 272 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 16384, autoIncrementAlreadyGot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 279 */       Object obj = super.clone();
/*     */       
/* 281 */       ObjectUtil.deepCopy(this, obj, FactoryParams.class);
/*     */       
/* 283 */       return obj;
/*     */     }
/* 285 */     catch (Exception e) {
/*     */ 
/*     */       
/* 288 */       throw new AssertionError();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getRequestLock() {
/* 298 */     return SetUtil.isOptionSet(this.m_Options, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequestLock(boolean lock) {
/* 307 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 1, lock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getReleaseLock() {
/* 316 */     return SetUtil.isOptionSet(this.m_Options, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReleaseLock(boolean releaseLock) {
/* 325 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 2, releaseLock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVariables(ISubstitutionVariables variables) {
/* 334 */     this.m_Variables = variables;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isObjectTransferedBack() {
/* 339 */     return SetUtil.isOptionSet(this.m_Options, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectTransferedBack(boolean b) {
/* 344 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 4, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomization() {
/* 349 */     return this.m_Customization;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomization(String guid) {
/* 354 */     this.m_Customization = guid;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomObjectName() {
/* 359 */     return this.m_CustomObjectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomObjectName(String string) {
/* 364 */     this.m_CustomObjectName = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustomObjectsOnly() {
/* 369 */     return SetUtil.isOptionSet(this.m_Options, 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomObjectsOnly(boolean customObjectsOnly) {
/* 374 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 8, customObjectsOnly);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getNoBlobFields() {
/* 379 */     return SetUtil.isOptionSet(this.m_Options, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoBlobFields(boolean noBlobFields) {
/* 384 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 16, noBlobFields);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIndexSuffix() {
/* 389 */     return this.m_IndexSuffix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexSuffix(String indexSuffix) {
/* 394 */     this.m_IndexSuffix = indexSuffix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDisableWorkflowTriggerService() {
/* 402 */     return SetUtil.isOptionSet(this.m_Options, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisableWorkflowTriggerService(boolean disableWorkflowTriggerService) {
/* 410 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 32, disableWorkflowTriggerService);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableSecondaryKeyResolve() {
/* 415 */     return SetUtil.isOptionSet(this.m_Options, 64);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnableSecondaryKeyResolve(boolean enableSecondaryKeyResolve) {
/* 420 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 64, enableSecondaryKeyResolve);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIgnoreErrorsOnSecondaryKeyResolve() {
/* 425 */     return SetUtil.isOptionSet(this.m_Options, 128);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIgnoreErrorsOnSecondaryKeyResolve(boolean ignoreErrorsOnSecondaryKeyResolve) {
/* 430 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 128, ignoreErrorsOnSecondaryKeyResolve);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSegmentValuesForUserOrder() {
/* 436 */     return SetUtil.isOptionSet(this.m_Options, 65536);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSegmentValuesForUserOrder(boolean segmentValuesForUserOrder) {
/* 441 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 65536, segmentValuesForUserOrder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListener(ISecondaryKeyResolveListener listener) {
/* 446 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ISecondaryKeyResolveListener getListener() {
/* 451 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContractInputs(ContractParameter[] contractInputs) {
/* 456 */     this.m_ContractInputs = contractInputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractParameter[] getContractInputs() {
/* 461 */     return this.m_ContractInputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForExchange(boolean forExchange) {
/* 466 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 32768, forExchange);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForExchange() {
/* 471 */     return SetUtil.isOptionSet(this.m_Options, 32768);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 476 */     out.writeObject(this.m_Variables);
/* 477 */     this.m_Parameters.writeExternal(out);
/* 478 */     ExternalizationUtil.writeExternal(this.m_SegmentValues, out);
/* 479 */     this.m_CascadeRestrictions.writeExternal(out);
/*     */     
/* 481 */     out.writeInt(this.m_MaxCascadeDepth);
/*     */     
/* 483 */     out.writeObject(this.m_IndexName);
/* 484 */     out.writeObject(this.m_IndexSuffix);
/* 485 */     out.writeObject(this.m_ActiveFilter);
/* 486 */     out.writeObject(this.m_Customization);
/* 487 */     out.writeObject(this.m_CustomObjectName);
/*     */     
/* 489 */     out.writeBoolean(getRequestLock());
/* 490 */     out.writeBoolean(getReleaseLock());
/* 491 */     out.writeBoolean(isObjectTransferedBack());
/* 492 */     out.writeBoolean(isCustomObjectsOnly());
/* 493 */     out.writeBoolean(getNoBlobFields());
/* 494 */     out.writeBoolean(isDisableWorkflowTriggerService());
/* 495 */     out.writeBoolean(isEnableSecondaryKeyResolve());
/* 496 */     out.writeBoolean(isIgnoreErrorsOnSecondaryKeyResolve());
/* 497 */     out.writeBoolean(isReadModificationOnly());
/* 498 */     out.writeBoolean(isEscapeReadProcessor());
/* 499 */     out.writeBoolean(isDomainLess());
/*     */     
/* 501 */     out.writeObject(this.m_ContractInputs);
/* 502 */     out.writeBoolean(isReadMultilangValues());
/* 503 */     out.writeBoolean(isInitializeObjectOnServer());
/* 504 */     out.writeBoolean(isForExchange());
/* 505 */     out.writeObject(this.m_ServerSideInitParams);
/* 506 */     out.writeBoolean(isAutoIncrementAlreadyGot());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 511 */     this.m_Variables = (ISubstitutionVariables)in.readObject();
/* 512 */     this.m_Parameters.readExternal(in);
/* 513 */     ExternalizationUtil.readExternal(this.m_SegmentValues, in);
/* 514 */     this.m_CascadeRestrictions.readExternal(in);
/*     */     
/* 516 */     this.m_MaxCascadeDepth = in.readInt();
/*     */     
/* 518 */     this.m_IndexName = (String)in.readObject();
/* 519 */     this.m_IndexSuffix = (String)in.readObject();
/* 520 */     this.m_ActiveFilter = (String)in.readObject();
/* 521 */     this.m_Customization = (String)in.readObject();
/* 522 */     this.m_CustomObjectName = (String)in.readObject();
/*     */ 
/*     */     
/* 525 */     setRequestLock(in.readBoolean());
/*     */ 
/*     */     
/* 528 */     setReleaseLock(in.readBoolean());
/*     */     
/* 530 */     setObjectTransferedBack(in.readBoolean());
/*     */ 
/*     */     
/* 533 */     setCustomObjectsOnly(in.readBoolean());
/*     */ 
/*     */     
/* 536 */     setNoBlobFields(in.readBoolean());
/*     */ 
/*     */     
/* 539 */     setDisableWorkflowTriggerService(in.readBoolean());
/*     */ 
/*     */     
/* 542 */     setEnableSecondaryKeyResolve(in.readBoolean());
/*     */ 
/*     */     
/* 545 */     setIgnoreErrorsOnSecondaryKeyResolve(in.readBoolean());
/*     */ 
/*     */     
/* 548 */     setReadModificationOnly(in.readBoolean());
/*     */ 
/*     */     
/* 551 */     setEscapeReadProcessor(in.readBoolean());
/*     */ 
/*     */     
/* 554 */     setDomainLess(in.readBoolean());
/*     */     
/* 556 */     this.m_ContractInputs = (ContractParameter[])in.readObject();
/* 557 */     this.m_KeyHashValue = 0;
/*     */     
/* 559 */     setReadMultilangValues(in.readBoolean());
/*     */     
/* 561 */     setInitializeObjectOnServer(in.readBoolean());
/*     */     
/* 563 */     setForExchange(in.readBoolean());
/* 564 */     this.m_ServerSideInitParams = (ServerSideInitializationParams)in.readObject();
/*     */     
/* 566 */     setAutoIncrementAlreadyGot(in.readBoolean());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer toKeyValue() {
/* 572 */     if (getRequestLock() || getReleaseLock())
/* 573 */       return DO_NOT_CACHE; 
/* 574 */     return Integer.valueOf(this.m_KeyHashValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCacheObjectName() {
/* 580 */     return this.m_CacheObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setCacheObjectName(String objName) {
/* 586 */     this.m_CacheObjectName = objName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCacheKeyValue_(int keyValue) {
/* 592 */     this.m_KeyHashValue = keyValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\FactoryParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */