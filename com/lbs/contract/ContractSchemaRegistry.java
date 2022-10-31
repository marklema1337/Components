/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.cache.LbsNonCachedCacheManager;
/*     */ import com.lbs.contract.applet.AppletContractImplVerifier;
/*     */ import com.lbs.contract.execution.ParameterMapping;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.platform.interfaces.ICacheManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public class ContractSchemaRegistry
/*     */ {
/*  24 */   static Hashtable<String, IContractImplVerifier> ms_Verifiers = new Hashtable<>();
/*  25 */   private static Hashtable<String, IContractGlobalSearchHandler> ms_SearchHandlers = new Hashtable<>();
/*     */   
/*  27 */   private static Hashtable<String, ContractSchemaContainer> ms_Containers = new Hashtable<>();
/*     */   
/*     */   private static ICacheManager ms_CacheManager;
/*     */   
/*     */   static {
/*  32 */     ms_Verifiers.put("applet", new AppletContractImplVerifier());
/*     */   }
/*     */ 
/*     */   
/*     */   public static ContractSchemaContainer getApplicationContainer() {
/*  37 */     return getContainer("");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clear() {
/*  42 */     ms_Containers.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ContractSchemaContainer getContainer(String guid) {
/*  47 */     if (guid == null)
/*  48 */       guid = ""; 
/*  49 */     ContractSchemaContainer container = ms_Containers.get(guid);
/*  50 */     if (container == null) {
/*     */       
/*  52 */       container = new ContractSchemaContainer(ms_CacheManager, guid);
/*  53 */       ms_Containers.put(guid, container);
/*     */     } 
/*  55 */     return container;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void prepareInstance(ICacheManager cacheManager) {
/*  60 */     ms_CacheManager = cacheManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void prepareNonCachedInstance() {
/*  65 */     ms_CacheManager = (ICacheManager)new LbsNonCachedCacheManager();
/*     */   }
/*     */   
/*     */   public static void registerGlobalSearchHandler(String category, IContractGlobalSearchHandler handler) {
/*  69 */     ms_SearchHandlers.put(category, handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable<String, IContractGlobalSearchHandler> getSearchHandlers() {
/*  74 */     return ms_SearchHandlers;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerContractImplementation(ContractImplementation implementation) {
/*  79 */     getContainer(implementation.getIdentifier().getGuid()).registerContractImplementation(implementation);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerContract(ContractSchema contract) {
/*  84 */     getContainer(contract.getIdentifier().getGuid()).registerContract(contract);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerParameterMapping(ParameterMapping mapping) {
/*  89 */     getContainer(mapping.getParameterIdentifier().getGuid()).registerParameterMapping(mapping);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ParameterMapping getParameterMapping(Identifier parameterIdentifier) {
/*  94 */     return getContainer(parameterIdentifier.getGuid()).getParameterMapping(parameterIdentifier.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public static ContractSchema getContract(Identifier identifier) {
/*  99 */     return getContainer(identifier.getGuid()).getContract(identifier.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public static ContractImplementation getContractCurrentImplementation(Identifier identifier) {
/* 104 */     return getContainer(identifier.getGuid()).getCurrentImplementation(identifier.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initialize() throws ContractException {
/* 109 */     Collection<ContractSchemaContainer> cons = ms_Containers.values();
/* 110 */     for (ContractSchemaContainer container : cons)
/*     */     {
/* 112 */       container.initialize();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<ContractImplementation> getAllReportContractImpls(String implCategory) {
/* 118 */     ArrayList<ContractImplementation> repImpls = new ArrayList<>();
/* 119 */     Collection<ContractSchemaContainer> cons = ms_Containers.values();
/* 120 */     for (ContractSchemaContainer container : cons) {
/*     */       
/* 122 */       ArrayList<ContractImplementation> list = container.getReportContractImplementations(implCategory);
/* 123 */       if (list != null)
/* 124 */         repImpls.addAll(list); 
/*     */     } 
/* 126 */     return repImpls;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractSchemaRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */