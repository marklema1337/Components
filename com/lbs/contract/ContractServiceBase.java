/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.data.objects.SimpleBusinessObject;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
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
/*     */ 
/*     */ public abstract class ContractServiceBase<T extends IApplicationContext>
/*     */   implements IContractService
/*     */ {
/*     */   protected T m_Context;
/*     */   
/*     */   public ContractServiceBase(T context) {
/*  24 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeExecuteContract(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs) throws ContractException {
/*     */     try {
/*  32 */       IContractExecutionListener listener = (IContractExecutionListener)this.m_Context.createInstance(listenerClassName);
/*  33 */       listener.beforeExecute((IApplicationContext)this.m_Context, this, contractId, implProps, inputs);
/*     */     }
/*  35 */     catch (Exception e) {
/*     */       
/*  37 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCompleteContract(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs, ContractParameter[] outputs) throws ContractException {
/*     */     try {
/*  46 */       IContractExecutionListener listener = (IContractExecutionListener)this.m_Context.createInstance(listenerClassName);
/*  47 */       listener.onComplete((IApplicationContext)this.m_Context, this, contractId, implProps, inputs, outputs);
/*     */     }
/*  49 */     catch (Exception e) {
/*     */       
/*  51 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContractException(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs, Throwable e) throws ContractException {
/*     */     try {
/*  60 */       IContractExecutionListener listener = (IContractExecutionListener)this.m_Context.createInstance(listenerClassName);
/*  61 */       listener.onException((IApplicationContext)this.m_Context, this, contractId, implProps, inputs, e);
/*     */     }
/*  63 */     catch (Exception t) {
/*     */       
/*  65 */       throw new ContractException(t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasDBRight(String listenerClassName, String contractId, SimpleBusinessObject businessObject, int dbRightId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/*     */     try {
/*  75 */       IUIContractCRUDListener listener = (IUIContractCRUDListener)this.m_Context.createInstance(listenerClassName);
/*  76 */       return listener.hasDBRight((IApplicationContext)this.m_Context, this, contractId, businessObject, dbRightId, implProps, inputs);
/*     */     }
/*  78 */     catch (Exception e) {
/*     */       
/*  80 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleBusinessObject createNewBusinessObject(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/*     */     try {
/*  89 */       IUIContractCRUDListener listener = (IUIContractCRUDListener)this.m_Context.createInstance(listenerClassName);
/*  90 */       return listener.createNewBusinessObject((IApplicationContext)this.m_Context, this, contractId, implProps, inputs);
/*     */     }
/*  92 */     catch (Exception e) {
/*     */       
/*  94 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object prepareBrowserData(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/*     */     try {
/* 103 */       IUIContractCRUDListener listener = (IUIContractCRUDListener)this.m_Context.createInstance(listenerClassName);
/* 104 */       return listener.prepareBrowserData((IApplicationContext)this.m_Context, this, contractId, implProps, inputs);
/*     */     }
/* 106 */     catch (Exception e) {
/*     */       
/* 108 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object prepareFormData(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/*     */     try {
/* 117 */       IUIContractListener listener = (IUIContractListener)this.m_Context.createInstance(listenerClassName);
/* 118 */       return listener.prepareFormData((IApplicationContext)this.m_Context, this, contractId, implProps, inputs);
/*     */     }
/* 120 */     catch (Exception e) {
/*     */       
/* 122 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleBusinessObject readBusinessObject(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/*     */     try {
/* 131 */       IUIContractCRUDListener listener = (IUIContractCRUDListener)this.m_Context.createInstance(listenerClassName);
/* 132 */       return listener.readBusinessObject((IApplicationContext)this.m_Context, this, contractId, implProps, inputs);
/*     */     }
/* 134 */     catch (Exception e) {
/*     */       
/* 136 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractCanDoResult canExecuteContract(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/*     */     try {
/* 145 */       IContractExecutionListener listener = (IContractExecutionListener)this.m_Context.createInstance(listenerClassName);
/* 146 */       return listener.canExecuteContract((IApplicationContext)this.m_Context, this, contractId, implProps, inputs);
/*     */     }
/* 148 */     catch (Exception e) {
/*     */       
/* 150 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractServiceBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */