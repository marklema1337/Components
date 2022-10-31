/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.contract.execution.ParameterMapping;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.query.QueryBusinessObjects;
/*     */ import com.lbs.mi.defs.GlobalSearchDefinition;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
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
/*     */ 
/*     */ public class ServerContractService
/*     */   extends ContractServiceBase<IApplicationContext>
/*     */ {
/*     */   public ServerContractService(IApplicationContext context) {
/*  30 */     super(context);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractInstance getContractInstance(String contractId) throws ContractException {
/*  35 */     ContractSchema contract = ContractSchemaRegistry.getApplicationContainer().getContract(contractId);
/*  36 */     if (contract == null)
/*  37 */       throw new ContractException("Could not find contract with id '" + contractId + "'!"); 
/*  38 */     ContractImplementation implementation = ContractSchemaRegistry.getApplicationContainer().getCurrentImplementation(
/*  39 */         contractId);
/*  40 */     if (implementation == null)
/*  41 */       throw new ContractException("Could not find default implementation for contract with id '" + contractId + "'!"); 
/*  42 */     ContractInstance instance = new ContractInstance();
/*  43 */     instance.setContract(contract);
/*  44 */     instance.setImplementation(implementation);
/*  45 */     Hashtable<Identifier, ParameterMapping> parameterMappings = new Hashtable<>();
/*  46 */     List<ContractSchemaInOut> inputs = contract.getInputs();
/*  47 */     for (ContractSchemaInOut input : inputs) {
/*     */       
/*  49 */       Identifier parameterIdentifier = input.getId();
/*  50 */       ParameterMapping mapping = ContractSchemaRegistry.getParameterMapping(parameterIdentifier);
/*  51 */       if (mapping != null)
/*  52 */         parameterMappings.put(parameterIdentifier, mapping); 
/*     */     } 
/*  54 */     instance.setParameterMappings(parameterMappings);
/*  55 */     instance.setMandatoryMaps(contract.getMandatoryMaps());
/*  56 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractInstance getContractInstance(String guid, String contractId) throws ContractException {
/*  61 */     ContractSchema contract = ContractSchemaRegistry.getContainer(guid).getContract(contractId);
/*  62 */     if (contract == null)
/*  63 */       throw new ContractException("Could not find contract with id '" + guid + "." + contractId + "'!"); 
/*  64 */     ContractImplementation implementation = ContractSchemaRegistry.getContainer(guid).getContractImplementation(
/*     */         
/*  66 */         String.valueOf((guid == null) ? "" : (String.valueOf(guid) + ".")) + contractId, "applet");
/*  67 */     if (implementation == null)
/*  68 */       throw new ContractException("Could not find default implementation for contract with id '" + guid + "." + contractId + 
/*  69 */           "'!"); 
/*  70 */     ContractInstance instance = new ContractInstance();
/*  71 */     instance.setContract(contract);
/*  72 */     instance.setImplementation(implementation);
/*  73 */     Hashtable<Identifier, ParameterMapping> parameterMappings = new Hashtable<>();
/*  74 */     List<ContractSchemaInOut> inputs = contract.getInputs();
/*  75 */     for (ContractSchemaInOut input : inputs) {
/*     */       
/*  77 */       Identifier parameterIdentifier = input.getId();
/*  78 */       ParameterMapping mapping = ContractSchemaRegistry.getParameterMapping(parameterIdentifier);
/*  79 */       if (mapping != null)
/*  80 */         parameterMappings.put(parameterIdentifier, mapping); 
/*     */     } 
/*  82 */     instance.setParameterMappings(parameterMappings);
/*  83 */     instance.setMandatoryMaps(contract.getMandatoryMaps());
/*  84 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryBusinessObjects doContractGlobalSearch(String contractId, GlobalSearchDefinition searchDef, String searchText, Hashtable<String, String> implProps, boolean isSearchInDetails, ContractParameter... inputs) throws ContractException {
/*  90 */     ContractInstance instance = getContractInstance(contractId);
/*  91 */     String category = instance.getImplementation().getCategory();
/*  92 */     IContractGlobalSearchHandler handler = ContractSchemaRegistry.getSearchHandlers().get(category);
/*  93 */     if (handler == null)
/*  94 */       throw new ContractException("Could not find global search handler registered for contact category : " + category); 
/*  95 */     return handler.doGlobalSearch(this, this.m_Context, searchDef, searchText, instance, implProps, isSearchInDetails, inputs);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getContractInstanceIdsOfForm(String formName) throws ContractException {
/* 100 */     ArrayList<String> contractIds = null;
/* 101 */     ICachedHashTable<String, ContractImplementation> implementations = ContractSchemaRegistry.getContainer("")
/* 102 */       .getContractImplementations();
/* 103 */     Enumeration<String> keys = implementations.keys();
/* 104 */     while (keys.hasMoreElements()) {
/*     */       
/* 106 */       String key = keys.nextElement();
/* 107 */       ContractImplementation impl = (ContractImplementation)implementations.get(key);
/* 108 */       String contractFormName = impl.getProperties().get("FormName");
/* 109 */       if (!JLbsStringUtil.isEmpty(contractFormName) && contractFormName.equals(formName)) {
/*     */         
/* 111 */         if (contractIds == null)
/* 112 */           contractIds = new ArrayList<>(); 
/* 113 */         contractIds.add(impl.getIdentifier().getId());
/*     */       } 
/*     */     } 
/* 116 */     return contractIds;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ServerContractService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */