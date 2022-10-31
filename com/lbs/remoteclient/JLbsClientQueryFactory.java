/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.data.factory.INamedVariables;
/*     */ import com.lbs.data.objects.ObjectMode;
/*     */ import com.lbs.data.objects.ObjectPropertyList;
/*     */ import com.lbs.data.query.IQueryFactory;
/*     */ import com.lbs.data.query.IQueryFactoryParamsListener;
/*     */ import com.lbs.data.query.QueryBusinessObject;
/*     */ import com.lbs.data.query.QueryBusinessObjects;
/*     */ import com.lbs.data.query.QueryFactoryException;
/*     */ import com.lbs.data.query.QueryGridSchema;
/*     */ import com.lbs.data.query.QueryParams;
/*     */ import com.lbs.data.query.QueryProperties;
/*     */ import com.lbs.data.query.QueryResult;
/*     */ import com.lbs.data.query.QuerySchema;
/*     */ import com.lbs.invoke.SessionReestablishedException;
/*     */ import com.lbs.invoke.SessionTimeoutException;
/*     */ import com.lbs.transport.IRemoteMethodInvoker;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsClientQueryFactory
/*     */   implements IQueryFactory
/*     */ {
/*  37 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger("Transport.ClientQryFact");
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private static final int ITEMS_INDEX = 0;
/*     */   
/*     */   public static boolean USE_LFS_CACHE = true;
/*     */   
/*     */   private IRemoteMethodInvoker m_Invoker;
/*     */   
/*     */   private String m_FactoryName;
/*     */   private ClassLoader m_ClassLoader;
/*  49 */   private IQueryFactoryParamsListener m_ParamsListener = null;
/*     */ 
/*     */   
/*     */   public JLbsClientQueryFactory(IRemoteMethodInvoker invoker, String factoryName, ClassLoader clsLoader) {
/*  53 */     this.m_Invoker = invoker;
/*  54 */     this.m_FactoryName = factoryName;
/*  55 */     this.m_ClassLoader = clsLoader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithObjectColl(String methodName, QueryBusinessObjects items, Object[] callParams) throws QueryFactoryException, SessionTimeoutException {
/*     */     try {
/*  64 */       if (items != null) {
/*  65 */         items.clear();
/*     */       }
/*  67 */       boolean[] retParams = { false, false, true };
/*  68 */       RemoteMethodResponse result = runRemoteMethod(methodName, callParams, retParams);
/*  69 */       getBOCollectionFromResult(items, result.ReturnParameters);
/*     */       
/*  71 */       if (result.Result instanceof Boolean) {
/*  72 */         return ((Boolean)result.Result).booleanValue();
/*     */       }
/*  74 */     } catch (QueryFactoryException e) {
/*     */       
/*  76 */       ms_Logger.error("ClientQueryFactory exception", (Throwable)e);
/*     */       
/*  78 */       throw e;
/*     */     }
/*  80 */     catch (SessionTimeoutException e) {
/*     */       
/*  82 */       ms_Logger.error("ClientQueryFactory session exception", (Throwable)e);
/*     */       
/*  84 */       throw e;
/*     */     }
/*  86 */     catch (Exception e) {
/*     */       
/*  88 */       ms_Logger.error("ClientQueryFactory invoke exception", e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  96 */       throw new QueryFactoryException(e);
/*     */     } 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RemoteMethodResponse runRemoteMethod(String methodName, Object[] callParams, boolean[] returnParams) throws QueryFactoryException, SessionTimeoutException {
/*     */     try {
/* 106 */       RemoteMethodResponse resp = this.m_Invoker.invoke(this.m_FactoryName, methodName, callParams, returnParams, this.m_ClassLoader);
/*     */       
/* 108 */       return resp;
/*     */     }
/* 110 */     catch (QueryFactoryException e) {
/*     */       
/* 112 */       ms_Logger.error("ClientQueryFactory exception", (Throwable)e);
/*     */       
/* 114 */       throw e;
/*     */     }
/* 116 */     catch (SessionTimeoutException e) {
/*     */       
/* 118 */       ms_Logger.error("ClientQueryFactory session exception", (Throwable)e);
/*     */       
/* 120 */       throw e;
/*     */     }
/* 122 */     catch (IOException e) {
/*     */       
/* 124 */       ms_Logger.error("ClientQueryFactory invoke exception", e);
/* 125 */       throw new QueryFactoryException(e, -1003, 74, 
/* 126 */           "Connection excepiton occured. Please check your connections.");
/*     */     }
/* 128 */     catch (Exception e) {
/*     */       
/* 130 */       ms_Logger.error("ClientQueryFactory invoke exception", e);
/* 131 */       throw new QueryFactoryException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getBOCollectionFromResult(QueryBusinessObjects items, Object[] retParams) {
/* 137 */     if (items != null && retParams != null && retParams.length > 0 && 
/* 138 */       retParams[0] instanceof QueryBusinessObjects) {
/*     */       
/* 140 */       QueryBusinessObjects targetItems = (QueryBusinessObjects)retParams[0];
/* 141 */       items.clear();
/* 142 */       items.addAll((Collection)targetItems);
/* 143 */       items.setAuthModes(targetItems.getAuthModes());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean first(String queryName, QueryParams queryParams, QueryBusinessObjects items, int maxCount, boolean reverse) throws QueryFactoryException, SessionTimeoutException {
/* 153 */     queryParams = prepareParams(queryParams);
/* 154 */     Object[] callParams = { queryName, queryParams, items, Integer.valueOf(maxCount), new Boolean(reverse) };
/* 155 */     return runRemoteMethodWithObjectColl("first", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean last(String queryName, QueryParams queryParams, QueryBusinessObjects items, int maxCount, boolean reverse) throws QueryFactoryException, SessionTimeoutException {
/* 164 */     queryParams = prepareParams(queryParams);
/* 165 */     Object[] callParams = { queryName, queryParams, items, Integer.valueOf(maxCount), new Boolean(reverse) };
/* 166 */     return runRemoteMethodWithObjectColl("last", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean search(String queryName, QueryParams queryParams, QueryBusinessObjects items, int maxCount, Object refObject, int comparison, boolean reverse) throws QueryFactoryException, SessionTimeoutException {
/* 175 */     queryParams = prepareParams(queryParams);
/* 176 */     Object[] callParams = { queryName, queryParams, items, Integer.valueOf(maxCount), refObject, Integer.valueOf(comparison), 
/* 177 */         new Boolean(reverse) };
/*     */     
/* 179 */     return runRemoteMethodWithObjectColl("search", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean next(String queryName, QueryParams queryParams, QueryBusinessObjects items, int maxCount, Object refObject, boolean reverse) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 188 */     queryParams = prepareParams(queryParams);
/* 189 */     Object[] callParams = { queryName, queryParams, items, Integer.valueOf(maxCount), refObject, new Boolean(reverse) };
/*     */     
/* 191 */     return runRemoteMethodWithObjectColl("next", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean previous(String queryName, QueryParams queryParams, QueryBusinessObjects items, int maxCount, Object refObject, boolean reverse) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 200 */     queryParams = prepareParams(queryParams);
/* 201 */     Object[] callParams = { queryName, queryParams, items, Integer.valueOf(maxCount), refObject, new Boolean(reverse) };
/*     */     
/* 203 */     return runRemoteMethodWithObjectColl("previous", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QuerySchema getQuerySchema(String customization, String queryName) throws QueryFactoryException, SessionTimeoutException {
/* 212 */     String key = "getQuerySchema~" + customization + queryName;
/* 213 */     if (USE_LFS_CACHE && !JLbsConstants.SKIP_CACHE) {
/*     */       
/* 215 */       Object obj = JLbsComponentHelper.loadLFSObject(key);
/* 216 */       if (obj instanceof QueryGridSchema)
/*     */       {
/* 218 */         return (QuerySchema)obj;
/*     */       }
/*     */     } 
/*     */     
/* 222 */     Object[] callParams = { customization, queryName };
/* 223 */     QuerySchema qrySchema = null;
/*     */     
/* 225 */     RemoteMethodResponse resp = runRemoteMethod("getQuerySchema", callParams, null);
/* 226 */     qrySchema = (QuerySchema)resp.Result;
/*     */     
/* 228 */     if (USE_LFS_CACHE && qrySchema != null && !JLbsConstants.SKIP_CACHE)
/*     */     {
/* 230 */       JLbsComponentHelper.saveLFSObject(qrySchema, key);
/*     */     }
/*     */     
/* 233 */     return qrySchema;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryResult executeServiceQuery(String queryName, QueryParams queryParams) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 242 */     queryParams = prepareParams(queryParams);
/* 243 */     Object[] callParams = { queryName, queryParams };
/* 244 */     RemoteMethodResponse resp = runRemoteMethod("executeServiceQuery", callParams, null);
/*     */     
/* 246 */     return (QueryResult)resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryResult[] executeServiceBatch(String[] queryNames, QueryParams[] queryParams) throws QueryFactoryException, SessionTimeoutException {
/* 255 */     if (queryParams != null)
/*     */     {
/* 257 */       for (int i = 0; i < queryParams.length; i++) {
/* 258 */         queryParams[i] = prepareParams(queryParams[i]);
/*     */       }
/*     */     }
/* 261 */     Object[] callParams = { queryNames, queryParams };
/* 262 */     RemoteMethodResponse resp = runRemoteMethod("executeServiceBatch", callParams, null);
/*     */     
/* 264 */     return (QueryResult[])resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean select(String queryName, QueryParams queryParams, QueryBusinessObjects items, int maxCount) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 273 */     queryParams = prepareParams(queryParams);
/* 274 */     Object[] callParams = { queryName, queryParams, items, Integer.valueOf(maxCount) };
/*     */     
/* 276 */     return runRemoteMethodWithObjectColl("select", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int searchPrimaryKey(String tableName, QueryParams queryParams, ObjectPropertyList propList) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 282 */     queryParams = prepareParams(queryParams);
/* 283 */     Object[] callParams = { tableName, queryParams, propList };
/*     */     
/* 285 */     RemoteMethodResponse resp = runRemoteMethod("searchPrimaryKey", callParams, null);
/*     */     
/* 287 */     return ((Integer)resp.Result).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowCount(String queryName, QueryParams queryParams) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 296 */     queryParams = prepareParams(queryParams);
/* 297 */     Object[] callParams = { queryName, queryParams };
/*     */     
/* 299 */     RemoteMethodResponse resp = runRemoteMethod("getRowCount", callParams, null);
/* 300 */     Integer i = (Integer)resp.Result;
/*     */     
/* 302 */     return i.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryGridSchema getQueryGridSchema(String customization, String queryName) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 312 */     String key = "getQueryGridSchema~" + customization + queryName;
/* 313 */     if (USE_LFS_CACHE && !JLbsConstants.SKIP_CACHE) {
/*     */       
/* 315 */       Object obj = JLbsComponentHelper.loadLFSObject(key);
/* 316 */       if (obj instanceof QueryGridSchema)
/*     */       {
/* 318 */         return (QueryGridSchema)obj;
/*     */       }
/*     */     } 
/*     */     
/* 322 */     Object[] callParams = { customization, queryName };
/* 323 */     QueryGridSchema qrySchema = null;
/*     */     
/* 325 */     RemoteMethodResponse resp = runRemoteMethod("getQueryGridSchema", callParams, null);
/* 326 */     qrySchema = (QueryGridSchema)resp.Result;
/*     */     
/* 328 */     if (USE_LFS_CACHE && qrySchema != null && !JLbsConstants.SKIP_CACHE)
/*     */     {
/* 330 */       JLbsComponentHelper.saveLFSObject(qrySchema, key);
/*     */     }
/* 332 */     return qrySchema;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryParams prepareParams(QueryParams params) {
/* 340 */     if (this.m_ParamsListener != null) {
/* 341 */       this.m_ParamsListener.prepareQueryFactoryParams(this, params);
/*     */     }
/* 343 */     return params;
/*     */   }
/*     */ 
/*     */   
/*     */   public IQueryFactoryParamsListener getParamsListener() {
/* 348 */     return this.m_ParamsListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParamsListener(IQueryFactoryParamsListener listener) {
/* 353 */     this.m_ParamsListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public INamedVariables getVariables() {
/* 358 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVariables(INamedVariables variables) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectMode resolveQueryObjectMode(QueryBusinessObject item) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 368 */     if (item != null) {
/*     */       
/* 370 */       if (item.getMode() != null)
/* 371 */         return item.getMode(); 
/* 372 */       RemoteMethodResponse response = runRemoteMethod("resolveQueryObjectMode", new Object[] { item }, null);
/* 373 */       return (ObjectMode)response.Result;
/*     */     } 
/* 375 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryProperties getQueryProperties() {
/* 381 */     return m_QueryProperties;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsClientQueryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */