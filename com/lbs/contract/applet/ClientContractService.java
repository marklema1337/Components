/*     */ package com.lbs.contract.applet;
/*     */ 
/*     */ import com.lbs.cache.ICacheListener;
/*     */ import com.lbs.cache.IVersionSource;
/*     */ import com.lbs.cache.JLbsCacheScope;
/*     */ import com.lbs.cache.JLbsVersionBasedCache;
/*     */ import com.lbs.contract.ContractCanDoResult;
/*     */ import com.lbs.contract.ContractException;
/*     */ import com.lbs.contract.ContractInstance;
/*     */ import com.lbs.contract.ContractParameter;
/*     */ import com.lbs.contract.ContractServiceBase;
/*     */ import com.lbs.data.objects.SimpleBusinessObject;
/*     */ import com.lbs.data.query.QueryBusinessObjects;
/*     */ import com.lbs.mi.defs.GlobalSearchDefinition;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.transport.CachedTransportClient;
/*     */ import com.lbs.transport.RemoteMethodResponse;
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
/*     */ public class ClientContractService
/*     */   extends ContractServiceBase<IClientContext>
/*     */ {
/*     */   public static final String SERVICE = "CON";
/*     */   public static boolean EXEC_ON_CLIENT = true;
/*  38 */   private static JLbsVersionBasedCache<String> ms_Cache = null;
/*     */   
/*     */   private static boolean ms_CacheEnabled = false;
/*     */   
/*     */   private static void prepareCache() {
/*  43 */     synchronized (ClientContractService.class) {
/*     */       
/*  45 */       if (!ms_CacheEnabled || ms_Cache != null) {
/*     */         return;
/*     */       }
/*  48 */       ms_Cache = new JLbsVersionBasedCache("j", JLbsCacheScope.GLOBAL(), new IVersionSource()
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public String getVersion() {
/*  56 */               return CachedTransportClient.getClientVersion();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public String getName() {
/*  62 */               return "con";
/*     */             }
/*     */           });
/*     */       
/*  66 */       ms_Cache.setCacheListener(new ICacheListener<String, Object>()
/*     */           {
/*     */             public Object cacheMiss(String key, String group)
/*     */             {
/*  70 */               return null;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public boolean isValid(String key, String group, Object item) {
/*  76 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientContractService(IClientContext context) {
/*  85 */     super((IApplicationContext)context);
/*  86 */     prepareCache();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getKey(String guid, String contractId) {
/*  91 */     StringBuilder buffer = new StringBuilder();
/*  92 */     buffer.append((guid != null) ? 
/*  93 */         guid : 
/*  94 */         "");
/*  95 */     buffer.append("~");
/*  96 */     buffer.append(contractId);
/*  97 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ContractInstance getFromCache(String guid, String contractId) {
/* 103 */     if (!ms_CacheEnabled)
/* 104 */       return null; 
/* 105 */     return (ContractInstance)ms_Cache.get(getKey(guid, contractId));
/*     */   }
/*     */ 
/*     */   
/*     */   private void put2Cache(String guid, String contractId, ContractInstance instance) {
/* 110 */     if (!ms_CacheEnabled)
/*     */       return; 
/* 112 */     if (instance != null) {
/* 113 */       ms_Cache.put(getKey(guid, contractId), instance);
/*     */     } else {
/* 115 */       ms_Cache.remove(getKey(guid, contractId));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractInstance getContractInstance(String contractId) throws ContractException {
/*     */     try {
/* 122 */       ContractInstance instance = getFromCache(null, contractId);
/* 123 */       if (instance != null)
/* 124 */         return instance; 
/* 125 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "getContractInstance", new Object[] { contractId });
/* 126 */       instance = (ContractInstance)response.Result;
/* 127 */       put2Cache(null, contractId, instance);
/* 128 */       return instance;
/*     */     }
/* 130 */     catch (Exception e) {
/*     */       
/* 132 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractInstance getContractInstance(String guid, String contractId) throws ContractException {
/*     */     try {
/* 140 */       ContractInstance instance = getFromCache(guid, contractId);
/* 141 */       if (instance != null)
/* 142 */         return instance; 
/* 143 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "getContractInstance", new Object[] { guid, 
/* 144 */             contractId });
/* 145 */       instance = (ContractInstance)response.Result;
/* 146 */       put2Cache(guid, contractId, instance);
/* 147 */       return instance;
/*     */     }
/* 149 */     catch (Exception e) {
/*     */       
/* 151 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryBusinessObjects doContractGlobalSearch(String contractId, GlobalSearchDefinition searchDef, String searchText, Hashtable<String, String> implProps, boolean isSearchInDetails, ContractParameter... inputs) throws ContractException {
/*     */     try {
/* 160 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "doContractGlobalSearch", new Object[] {
/* 161 */             contractId, searchDef, searchText, implProps, Boolean.valueOf(isSearchInDetails), inputs });
/* 162 */       return (QueryBusinessObjects)response.Result;
/*     */     }
/* 164 */     catch (Exception e) {
/*     */       
/* 166 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeExecuteContract(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs) throws ContractException {
/* 175 */     if (EXEC_ON_CLIENT) {
/*     */       
/* 177 */       super.beforeExecuteContract(listenerClassName, contractId, implProps, inputs);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 183 */         RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "beforeExecuteContract", new Object[] {
/* 184 */               listenerClassName, contractId, implProps, inputs }, new boolean[] { false, false, true, true });
/* 185 */         Object[] returnParameters = response.ReturnParameters;
/* 186 */         if (returnParameters.length == 2) {
/*     */           
/* 188 */           if (returnParameters[0] instanceof Hashtable)
/*     */           {
/* 190 */             implProps.putAll((Hashtable)returnParameters[0]);
/*     */           }
/* 192 */           if (returnParameters[1] instanceof ContractParameter[])
/*     */           {
/* 194 */             ContractParameter[] returnedIns = (ContractParameter[])returnParameters[1];
/* 195 */             if (returnedIns.length == inputs.length)
/*     */             {
/* 197 */               for (int i = 0; i < returnedIns.length; i++)
/*     */               {
/* 199 */                 inputs[i] = returnedIns[i];
/*     */               }
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/* 205 */       } catch (Exception e) {
/*     */         
/* 207 */         throw new ContractException(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCompleteContract(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs, ContractParameter[] outputs) throws ContractException {
/* 217 */     if (EXEC_ON_CLIENT) {
/*     */       
/* 219 */       super.onCompleteContract(listenerClassName, contractId, implProps, inputs, outputs);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 225 */         RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "onCompleteContract", new Object[] {
/* 226 */               listenerClassName, contractId, implProps, inputs, outputs
/* 227 */             }, new boolean[] { false, false, true, true, true });
/* 228 */         Object[] returnParameters = response.ReturnParameters;
/* 229 */         if (returnParameters.length == 3) {
/*     */           
/* 231 */           if (returnParameters[0] instanceof Hashtable)
/*     */           {
/* 233 */             implProps.putAll((Hashtable)returnParameters[0]);
/*     */           }
/* 235 */           if (returnParameters[1] instanceof ContractParameter[]) {
/*     */             
/* 237 */             ContractParameter[] returnedIns = (ContractParameter[])returnParameters[1];
/* 238 */             if (returnedIns.length == inputs.length)
/*     */             {
/* 240 */               for (int i = 0; i < returnedIns.length; i++)
/*     */               {
/* 242 */                 inputs[i] = returnedIns[i];
/*     */               }
/*     */             }
/*     */           } 
/* 246 */           if (returnParameters[2] instanceof ContractParameter[])
/*     */           {
/* 248 */             ContractParameter[] returnedOuts = (ContractParameter[])returnParameters[2];
/* 249 */             if (returnedOuts.length == outputs.length)
/*     */             {
/* 251 */               for (int i = 0; i < returnedOuts.length; i++)
/*     */               {
/* 253 */                 outputs[i] = returnedOuts[i];
/*     */               }
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/* 259 */       } catch (Exception e) {
/*     */         
/* 261 */         throw new ContractException(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContractException(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter[] inputs, Throwable e) throws ContractException {
/* 271 */     if (EXEC_ON_CLIENT) {
/*     */       
/* 273 */       super.onContractException(listenerClassName, contractId, implProps, inputs, e);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 279 */         RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "onContractException", new Object[] {
/* 280 */               listenerClassName, contractId, implProps, inputs, e }, new boolean[] { false, false, true, true });
/* 281 */         Object[] returnParameters = response.ReturnParameters;
/* 282 */         if (returnParameters.length == 2) {
/*     */           
/* 284 */           if (returnParameters[0] instanceof Hashtable)
/*     */           {
/* 286 */             implProps.putAll((Hashtable)returnParameters[0]);
/*     */           }
/* 288 */           if (returnParameters[1] instanceof ContractParameter[])
/*     */           {
/* 290 */             ContractParameter[] returnedIns = (ContractParameter[])returnParameters[1];
/* 291 */             if (returnedIns.length == inputs.length)
/*     */             {
/* 293 */               for (int i = 0; i < returnedIns.length; i++)
/*     */               {
/* 295 */                 inputs[i] = returnedIns[i];
/*     */               }
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/* 301 */       } catch (Exception t) {
/*     */         
/* 303 */         throw new ContractException(t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasDBRight(String listenerClassName, String contractId, SimpleBusinessObject businessObject, int dbRightId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/* 312 */     if (EXEC_ON_CLIENT)
/*     */     {
/* 314 */       return super.hasDBRight(listenerClassName, contractId, businessObject, dbRightId, implProps, inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 320 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "hasDBRight", new Object[] { listenerClassName, 
/* 321 */             contractId, businessObject, Integer.valueOf(dbRightId), implProps, inputs });
/* 322 */       return ((Boolean)response.Result).booleanValue();
/*     */     }
/* 324 */     catch (Exception e) {
/*     */       
/* 326 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleBusinessObject createNewBusinessObject(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/* 335 */     if (EXEC_ON_CLIENT)
/*     */     {
/* 337 */       return super.createNewBusinessObject(listenerClassName, contractId, implProps, inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 343 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "createNewBusinessObject", new Object[] {
/* 344 */             listenerClassName, contractId, implProps, inputs });
/* 345 */       return (SimpleBusinessObject)response.Result;
/*     */     }
/* 347 */     catch (Exception e) {
/*     */       
/* 349 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object prepareFormData(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/* 358 */     if (EXEC_ON_CLIENT)
/*     */     {
/* 360 */       return super.prepareFormData(listenerClassName, contractId, implProps, inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 366 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "prepareFormData", new Object[] {
/* 367 */             listenerClassName, contractId, implProps, inputs });
/* 368 */       return response.Result;
/*     */     }
/* 370 */     catch (Exception e) {
/*     */       
/* 372 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object prepareBrowserData(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/* 381 */     if (EXEC_ON_CLIENT)
/*     */     {
/* 383 */       return super.prepareBrowserData(listenerClassName, contractId, implProps, inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 389 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "prepareBrowserData", new Object[] {
/* 390 */             listenerClassName, contractId, implProps, inputs });
/* 391 */       return response.Result;
/*     */     }
/* 393 */     catch (Exception e) {
/*     */       
/* 395 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleBusinessObject readBusinessObject(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/* 404 */     if (EXEC_ON_CLIENT)
/*     */     {
/* 406 */       return super.readBusinessObject(listenerClassName, contractId, implProps, inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 412 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "readBusinessObject", new Object[] {
/* 413 */             listenerClassName, contractId, implProps, inputs });
/* 414 */       return (SimpleBusinessObject)response.Result;
/*     */     }
/* 416 */     catch (Exception e) {
/*     */       
/* 418 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractCanDoResult canExecuteContract(String listenerClassName, String contractId, Hashtable<String, String> implProps, ContractParameter... inputs) throws ContractException {
/* 426 */     if (EXEC_ON_CLIENT)
/*     */     {
/* 428 */       return super.canExecuteContract(listenerClassName, contractId, implProps, inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 434 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "canExecuteContract", new Object[] {
/* 435 */             listenerClassName, contractId, inputs });
/* 436 */       return (ContractCanDoResult)response.Result;
/*     */     }
/* 438 */     catch (Exception e) {
/*     */       
/* 440 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCacheEnabled(boolean cacheEnabled) {
/* 447 */     ms_CacheEnabled = cacheEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getContractInstanceIdsOfForm(String formName) throws ContractException {
/*     */     try {
/* 455 */       RemoteMethodResponse response = ((IClientContext)this.m_Context).callRemoteMethod("CON", "getContractInstanceIdsOfForm", new Object[] { formName });
/* 456 */       return (ArrayList<String>)response.Result;
/*     */     }
/* 458 */     catch (Exception e) {
/*     */       
/* 460 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\ClientContractService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */