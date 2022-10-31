/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.contract.applet.AppletContractBase;
/*     */ import com.lbs.contract.applet.IAppletContractConstants;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import com.lbs.mi.defs.ModuleAction;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.ArrayList;
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
/*     */ public abstract class ContractListenerBase
/*     */   implements IContractExecutionListener, IAppletContractConstants
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  28 */   private static ContractListenerHelper ms_Helper = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractCanDoResult canExecuteContract(IApplicationContext context, IContractService contractService, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws LbsLocalizableException {
/*  33 */     checkForCachedQueryObject(context, contractService, contractId, implProps, inputs);
/*  34 */     prepareSourceFormInfo(context, implProps);
/*  35 */     if (contractId.startsWith("New"))
/*  36 */       return canCreate(context, contractId, inputs); 
/*  37 */     if (contractId.startsWith("Update"))
/*  38 */       return canUpdate(context, contractId, inputs); 
/*  39 */     if (contractId.startsWith("Delete"))
/*  40 */       return canDelete(context, contractId, inputs); 
/*  41 */     if (contractId.startsWith("View"))
/*  42 */       return canView(context, contractId, inputs); 
/*  43 */     if (contractId.startsWith("Duplicate"))
/*  44 */       return canDuplicate(context, contractId, inputs); 
/*  45 */     if (contractId.startsWith("Browse"))
/*  46 */       return canBrowse(context, contractId, inputs); 
/*  47 */     if (contractId.startsWith("Select"))
/*  48 */       return canSelect(context, contractId, inputs); 
/*  49 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void prepareSourceFormInfo(IApplicationContext context, Hashtable<String, String> implProps) {
/*     */     try {
/*  56 */       String formName = implProps.get("SourceForm");
/*  57 */       int mode = AppletContractBase.getFormMode(formName);
/*     */       
/*  59 */       if (!JLbsStringUtil.isEmpty(formName)) {
/*  60 */         context.setVariable("SOURCE_FORM", formName);
/*     */       }
/*  62 */       context.setVariable("SOURCE_FORM_MODE", Integer.valueOf(mode));
/*     */     }
/*  64 */     catch (Exception e) {
/*     */       
/*  66 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAddCanDoResultInputForDelete() {
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeExecute(IApplicationContext context, IContractService contractService, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs) throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onComplete(IApplicationContext context, IContractService contractService, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs, ContractParameter[] outputs) throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onException(IApplicationContext context, IContractService contractService, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs, Throwable e) throws Exception {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkForCachedQueryObject(IApplicationContext context, IContractService contractService, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) {
/*  95 */     if (ms_Helper != null) {
/*  96 */       ms_Helper.checkForContractInputs(context, contractService, contractId, implProps, inputs);
/*     */     }
/*     */   }
/*     */   
/*     */   protected ContractCanDoResult canCreate(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 101 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult canUpdate(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 106 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult canView(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 111 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult canDuplicate(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 116 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult canDelete(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 121 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult canBrowse(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 126 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult canSelect(IApplicationContext context, String contractId, ContractParameter... inputs) {
/* 131 */     return prepareOkResult();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult prepareOkResult() {
/* 136 */     ContractCanDoResult result = new ContractCanDoResult();
/* 137 */     result.setCanExecute(true);
/* 138 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult prepareExceptionResult(IApplicationContext context, Exception e, String extraMessage) {
/* 143 */     ContractCanDoResult result = new ContractCanDoResult();
/* 144 */     result.setCanExecute(false);
/* 145 */     ContractMessage message = new ContractMessage();
/* 146 */     message.setDefaultMessage("Exception occurred during contract execution listener : ~1, ~2");
/* 147 */     message.setMessageResource(new JLbsResourceId("UN", 80310, 1));
/* 148 */     message.setMessageSubstitutions(new String[] { extraMessage, e.toString() });
/* 149 */     message.setType(4);
/* 150 */     result.getMessages().add(message);
/* 151 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContractCanDoResult prepareMessageResult(IApplicationContext context, String messageConstantId, String defaultMessage) {
/* 156 */     ContractCanDoResult result = new ContractCanDoResult();
/* 157 */     result.setCanExecute(false);
/* 158 */     ContractMessage message = new ContractMessage(messageConstantId, defaultMessage);
/* 159 */     message.initialize(context.getLocalizationService());
/* 160 */     result.getMessages().add(message);
/* 161 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setHelper(ContractListenerHelper helper) {
/* 166 */     ms_Helper = helper;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ModuleAction> getDynamicContractProps(IApplicationContext context) {
/* 171 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractListenerBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */