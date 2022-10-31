/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.application.ApplicationVariables;
/*     */ import com.lbs.data.factory.FactoryParams;
/*     */ import com.lbs.data.factory.IFactoryParamsListener;
/*     */ import com.lbs.data.factory.IObjectFactory;
/*     */ import com.lbs.data.factory.ObjectFactoryException;
/*     */ import com.lbs.data.factory.ObjectValidationException;
/*     */ import com.lbs.data.locks.JLbsClientLockManager;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BasicBusinessObjects;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.data.objects.BusinessObjectChanges;
/*     */ import com.lbs.data.objects.ObjectPropertyList;
/*     */ import com.lbs.data.objects.ObjectValidatorSource;
/*     */ import com.lbs.invoke.SessionReestablishedException;
/*     */ import com.lbs.invoke.SessionTimeoutException;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.platform.interfaces.ILbsContainer;
/*     */ import com.lbs.platform.interfaces.ILbsObjectValidator;
/*     */ import com.lbs.platform.interfaces.ILbsValidationEvent;
/*     */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*     */ import com.lbs.transport.IRemoteMethodInvoker;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.validation.LbsValidationEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsClientObjectFactory
/*     */   implements IObjectFactory
/*     */ {
/*  49 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger("Transport.ClientObjFact");
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private IRemoteMethodInvoker m_Invoker;
/*     */   
/*     */   private String m_FactoryName;
/*     */   private ClassLoader m_ClassLoader;
/*     */   private IClientContext m_Context;
/*  58 */   private IFactoryParamsListener m_ParamsListener = null;
/*     */   
/*     */   private static final String restrictedMethodNames = ",insert,_insert,update,_update,delete,persist1,persistX1,persistN,persistXN,";
/*     */   
/*     */   public JLbsClientObjectFactory(IClientContext context, IRemoteMethodInvoker invoker, String factoryName, ClassLoader clsLoader) {
/*  63 */     this.m_Invoker = invoker;
/*  64 */     this.m_FactoryName = factoryName;
/*  65 */     this.m_ClassLoader = clsLoader;
/*  66 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean first(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/*  75 */     return runRemoteMethodWithBO("first1", businessObject, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean last(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/*  84 */     return runRemoteMethodWithBO("last1", businessObject, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean next(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/*  93 */     return runRemoteMethodWithBO("next1", businessObject, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean previous(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 102 */     return runRemoteMethodWithBO("previous1", businessObject, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean first(BasicBusinessObjects items, String itemClassName, FactoryParams params, int maxCount, boolean reverse) throws ObjectFactoryException, SessionTimeoutException {
/* 111 */     return runRemoteMethodFirstLast("firstN", items, itemClassName, params, maxCount, reverse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean last(BasicBusinessObjects items, String itemClassName, FactoryParams params, int maxCount, boolean reverse) throws ObjectFactoryException, SessionTimeoutException {
/* 120 */     return runRemoteMethodFirstLast("lastN", items, itemClassName, params, maxCount, reverse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean search(BasicBusinessObjects items, String itemClassName, FactoryParams params, int maxCount, int comparison, boolean reverse) throws ObjectFactoryException, SessionTimeoutException {
/* 129 */     params = prepareParams(params);
/* 130 */     Object[] callParams = { items, itemClassName, params, Integer.valueOf(maxCount), Integer.valueOf(comparison), 
/* 131 */         new Boolean(reverse) };
/* 132 */     return runRemoteMethodWithObjectColl("searchN2", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean search(BasicBusinessObjects items, BasicBusinessObject refObject, FactoryParams params, int maxCount, int comparison, boolean reverse) throws ObjectFactoryException, SessionTimeoutException {
/* 141 */     params = prepareParams(params);
/* 142 */     Object[] callParams = { items, refObject, params, Integer.valueOf(maxCount), Integer.valueOf(comparison), 
/* 143 */         new Boolean(reverse) };
/* 144 */     return runRemoteMethodWithObjectColl("searchN", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean next(BasicBusinessObjects items, BasicBusinessObject refObject, FactoryParams params, int maxCount, boolean reverse) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 151 */     params = prepareParams(params);
/* 152 */     Object[] callParams = { items, refObject, params, Integer.valueOf(maxCount), new Boolean(reverse) };
/* 153 */     return runRemoteMethodWithObjectColl("nextN", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean previous(BasicBusinessObjects items, BasicBusinessObject refObject, FactoryParams params, int maxCount, boolean reverse) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 160 */     params = prepareParams(params);
/* 161 */     Object[] callParams = { items, refObject, params, Integer.valueOf(maxCount), new Boolean(reverse) };
/* 162 */     return runRemoteMethodWithObjectColl("previousN", items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean read(BasicBusinessObject businessObject, FactoryParams params, int key) throws ObjectFactoryException, SessionTimeoutException {
/* 171 */     params = prepareParams(params);
/* 172 */     Object[] callParams = { businessObject, params, Integer.valueOf(key) };
/* 173 */     return runRemoteMethodWithBO("read", businessObject, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readByUUID(BasicBusinessObject businessObject, FactoryParams params, String uuid) throws ObjectFactoryException, SessionTimeoutException {
/* 179 */     params = prepareParams(params);
/* 180 */     Object[] callParams = { businessObject, params, uuid };
/* 181 */     return runRemoteMethodWithBO("readByUUID", businessObject, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean search(BasicBusinessObject businessObject, FactoryParams params, int comparison) throws ObjectFactoryException, SessionTimeoutException {
/* 190 */     params = prepareParams(params);
/* 191 */     Object[] callParams = { businessObject, params, Integer.valueOf(comparison) };
/* 192 */     return runRemoteMethodWithBO("search1", businessObject, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readBySecondaryKey(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 198 */     params = prepareParams(params);
/* 199 */     Object[] callParams = { businessObject, params };
/* 200 */     return runRemoteMethodWithBO("readBySecondaryKey", businessObject, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean searchByProps(BasicBusinessObject businessObject, ObjectPropertyList propList, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 206 */     params = prepareParams(params);
/* 207 */     Object[] callParams = { businessObject, propList, params };
/* 208 */     return runRemoteMethodWithBO("searchByProps", businessObject, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int searchPrimaryKey(String itemClassName, ObjectPropertyList propList, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 214 */     params = prepareParams(params);
/* 215 */     Object[] callParams = { itemClassName, propList, params };
/* 216 */     return ((Integer)(runRemoteMethodEx("searchPrimaryKey", callParams, new boolean[] { true })).Result).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int searchPrimaryKeyByUUID(String itemClassName, FactoryParams params, String uuid) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 223 */     params = prepareParams(params);
/* 224 */     Object[] callParams = { itemClassName, params, uuid };
/* 225 */     return ((Integer)(runRemoteMethodEx("searchPrimaryKeyByUUID", callParams, new boolean[] { true })).Result).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean insert(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 234 */     validateBO(businessObject);
/* 235 */     boolean transferBack = isObjectTransferedBack(params);
/*     */     
/* 237 */     boolean result = false;
/* 238 */     if (transferBack) {
/* 239 */       result = runRemoteMethodWithBO("insert", businessObject, params, transferBack);
/*     */     } else {
/*     */       
/* 242 */       params = prepareParams(params);
/* 243 */       Object[] callParams = { businessObject, params };
/* 244 */       result = runRemoteMethodWithIncrementalBO("_insert", businessObject, callParams);
/*     */     } 
/* 246 */     if (result)
/*     */     {
/* 248 */       checkBusinessObjectState(businessObject);
/*     */     }
/* 250 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkBusinessObjectState(BasicBusinessObject businessObject) throws ObjectFactoryException {
/* 255 */     if (businessObject._getState() == 0 || businessObject.getUniqueIdentifier().getSimpleKey() <= 0) {
/* 256 */       throw new ObjectFactoryException(-1003, 65, 
/* 257 */           "Server-client communication error occurred. Business object save may not be succeeded.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean update(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 266 */     validateBO(businessObject);
/* 267 */     boolean transferBack = isObjectTransferedBack(params);
/*     */     
/* 269 */     if (transferBack) {
/* 270 */       return runRemoteMethodWithBO("update", businessObject, params, transferBack);
/*     */     }
/*     */     
/* 273 */     params = prepareParams(params);
/* 274 */     Object[] callParams = { businessObject, params };
/* 275 */     return runRemoteMethodWithIncrementalBO("_update", businessObject, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean delete(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 285 */     return runRemoteMethodWithBO("delete", businessObject, params, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasReferences(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 291 */     return runRemoteMethodWithBO("hasReferences", businessObject, params, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean persist(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 300 */     validateBO(businessObject);
/*     */     
/* 302 */     if (businessObject._getState() != 0 && businessObject.getUniqueIdentifier().getSimpleKey() <= 0) {
/* 303 */       return false;
/*     */     }
/* 305 */     boolean transferBack = isObjectTransferedBack(params);
/*     */     
/* 307 */     boolean result = false;
/* 308 */     if (transferBack) {
/* 309 */       result = runRemoteMethodWithBO("persist1", businessObject, params, transferBack);
/*     */     } else {
/*     */       
/* 312 */       params = prepareParams(params);
/* 313 */       Object[] callParams = { businessObject, params };
/* 314 */       result = runRemoteMethodWithIncrementalBO("persistX1", businessObject, callParams);
/*     */     } 
/* 316 */     if (result)
/*     */     {
/* 318 */       checkBusinessObjectState(businessObject);
/*     */     }
/* 320 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean persist(BasicBusinessObject[] businessObjects, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 329 */     if (businessObjects != null)
/*     */     {
/* 331 */       for (int i = 0; i < businessObjects.length; i++)
/*     */       {
/* 333 */         validateBO(businessObjects[i]);
/*     */       }
/*     */     }
/* 336 */     params = prepareParams(params);
/* 337 */     Object[] callParams = { businessObjects, params };
/* 338 */     boolean transferBack = isObjectTransferedBack(params);
/*     */     
/* 340 */     boolean result = false;
/* 341 */     if (transferBack) {
/* 342 */       result = runRemoteMethodWithBO("persistN", businessObjects, callParams, isObjectTransferedBack(params));
/*     */     } else {
/* 344 */       result = runRemoteMethodWithIncrementalBOs("persistXN", businessObjects, callParams);
/*     */     } 
/* 346 */     if (result)
/*     */     {
/* 348 */       for (int i = 0; i < businessObjects.length; i++)
/*     */       {
/* 350 */         checkBusinessObjectState(businessObjects[i]);
/*     */       }
/*     */     }
/* 353 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean delete(String className, int key, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 359 */     params = prepareParams(params);
/* 360 */     Object[] callParams = { className, Integer.valueOf(key), params };
/* 361 */     FactoryMethodCallResult result = runRemoteMethod("delete", callParams, null);
/* 362 */     return result.ReturnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String acquireLock(FactoryParams params, String lockTemplate) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 371 */     params = prepareParams(params);
/* 372 */     Object[] callParams = { params, lockTemplate };
/* 373 */     RemoteMethodResponse resp = runRemoteMethodEx("acquireLock", callParams, null);
/* 374 */     return (String)resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocked(FactoryParams params, String lockTemplate, String lockId) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 383 */     params = prepareParams(params);
/* 384 */     Object[] callParams = { params, lockTemplate, lockId };
/* 385 */     RemoteMethodResponse resp = runRemoteMethodEx("isLocked", callParams, null);
/* 386 */     return ((Boolean)resp.Result).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseLock(FactoryParams params, String lockTemplate, String lockId) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 395 */     params = prepareParams(params);
/* 396 */     Object[] callParams = { params, lockTemplate, lockId };
/* 397 */     runRemoteMethodEx("releaseLock", callParams, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getObjectExtensions(String boClassName, String customization) throws ObjectFactoryException, SessionTimeoutException {
/* 403 */     RemoteMethodResponse resp = runRemoteMethodEx("getObjectExtensions", new Object[] { boClassName, customization }, null);
/* 404 */     return (String[])resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTableName(BasicBusinessObject object) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 411 */     RemoteMethodResponse resp = runRemoteMethodEx("getTableName", new Object[] { object }, null);
/* 412 */     return (String)resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectClassName(String tableName) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/* 419 */     RemoteMethodResponse resp = runRemoteMethodEx("getObjectClassName", new Object[] { tableName }, null);
/* 420 */     return (String)resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSecondaryKeyData(String className, Integer key) throws ObjectFactoryException, SessionTimeoutException {
/* 426 */     RemoteMethodResponse resp = runRemoteMethodEx("getSecondaryKeyData", new Object[] { className, key }, null);
/* 427 */     return (String)resp.Result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithBO(String methodName, BasicBusinessObject obj, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 433 */     return runRemoteMethodWithBO(methodName, obj, params, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithBO(String methodName, BasicBusinessObject obj, FactoryParams params, boolean returnParam) throws ObjectFactoryException, SessionTimeoutException {
/* 439 */     params = prepareParams(params);
/* 440 */     Object[] callParams = { obj, params };
/* 441 */     return runRemoteMethodWithBO(methodName, obj, callParams, returnParam);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithBO(String methodName, BasicBusinessObject obj, Object[] callParams) throws ObjectFactoryException, SessionTimeoutException {
/* 447 */     return runRemoteMethodWithBO(methodName, obj, callParams, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithBO(String methodName, BasicBusinessObject obj, Object[] callParams, boolean returnParam) throws ObjectFactoryException, SessionTimeoutException {
/* 453 */     if (isUserRestrictedForMethod(methodName)) {
/* 454 */       return false;
/*     */     }
/* 456 */     Object info = JLbsClientLockManager.preProcessParams(callParams, 1, obj);
/*     */     
/* 458 */     boolean[] retParams = { returnParam };
/* 459 */     FactoryMethodCallResult result = null;
/*     */ 
/*     */     
/*     */     try {
/* 463 */       result = runRemoteMethod(methodName, callParams, retParams);
/*     */     }
/* 465 */     catch (ObjectFactoryException e) {
/*     */       
/* 467 */       JLbsClientLockManager.undoProcessParams(callParams, 1, obj, info);
/* 468 */       throw e;
/*     */     }
/* 470 */     catch (SessionTimeoutException e) {
/*     */       
/* 472 */       JLbsClientLockManager.undoProcessParams(callParams, 1, obj, info);
/* 473 */       throw e;
/*     */     } 
/*     */     
/* 476 */     if (returnParam && result.ReturnParams != null && result.ReturnParams.length > 0 && obj != null) {
/*     */       
/*     */       try {
/*     */         
/* 480 */         ObjectUtil.shallowCopy(result.ReturnParams[0], obj, obj.getClass());
/*     */       }
/* 482 */       catch (Exception e) {
/*     */         
/* 484 */         ms_Logger.error("ClientFactory clone exception", e);
/*     */       } 
/*     */     }
/* 487 */     return result.ReturnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isUserRestrictedForMethod(String methodName) {
/* 494 */     return (this.m_Context.getUserInfo().isRestricted() && ",insert,_insert,update,_update,delete,persist1,persistX1,persistN,persistXN,".indexOf(methodName) > -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithIncrementalBO(String methodName, BasicBusinessObject obj, Object[] callParams) throws ObjectFactoryException, SessionTimeoutException {
/* 500 */     if (isUserRestrictedForMethod(methodName)) {
/* 501 */       return false;
/*     */     }
/* 503 */     boolean[] retParams = new boolean[1];
/*     */     
/* 505 */     Object info = JLbsClientLockManager.preProcessParams(callParams, 1, obj);
/* 506 */     RemoteMethodResponse result = null;
/*     */ 
/*     */     
/*     */     try {
/* 510 */       result = runRemoteMethodEx(methodName, callParams, retParams);
/*     */     }
/* 512 */     catch (ObjectFactoryException e) {
/*     */       
/* 514 */       JLbsClientLockManager.undoProcessParams(callParams, 1, obj, info);
/* 515 */       throw e;
/*     */     }
/* 517 */     catch (SessionTimeoutException e) {
/*     */       
/* 519 */       JLbsClientLockManager.undoProcessParams(callParams, 1, obj, info);
/* 520 */       throw e;
/*     */     } 
/*     */     
/* 523 */     if (obj != null && result.Result != null && result.Result instanceof BusinessObjectChanges) {
/*     */       
/* 525 */       BusinessObjectChanges changes = (BusinessObjectChanges)result.Result;
/* 526 */       changes.apply(obj);
/*     */     } 
/*     */     
/* 529 */     JLbsClientLockManager.postProcessParams(callParams, 1, obj);
/*     */     
/* 531 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithIncrementalBOs(String methodName, BasicBusinessObject[] objs, Object[] callParams) throws ObjectFactoryException, SessionTimeoutException {
/* 537 */     boolean[] retParams = new boolean[1];
/*     */     
/* 539 */     Object info = JLbsClientLockManager.preProcessParams(callParams, 1, (Object[])objs);
/* 540 */     RemoteMethodResponse result = null;
/*     */     
/*     */     try {
/* 543 */       result = runRemoteMethodEx(methodName, callParams, retParams);
/*     */     }
/* 545 */     catch (ObjectFactoryException e) {
/*     */       
/* 547 */       JLbsClientLockManager.undoProcessParams(callParams, 1, objs, info);
/* 548 */       throw e;
/*     */     }
/* 550 */     catch (SessionTimeoutException e) {
/*     */       
/* 552 */       JLbsClientLockManager.undoProcessParams(callParams, 1, objs, info);
/* 553 */       throw e;
/*     */     } 
/*     */     
/* 556 */     if (objs != null && result.Result != null && result.Result instanceof BusinessObjectChanges[]) {
/*     */       
/* 558 */       BusinessObjectChanges[] changes = (BusinessObjectChanges[])result.Result;
/*     */       
/* 560 */       for (int i = 0; i < changes.length; i++) {
/* 561 */         changes[i].apply(objs[i]);
/*     */       }
/*     */     } 
/* 564 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodFirstLast(String methodName, BasicBusinessObjects items, String itemClassName, FactoryParams params, int maxCount, boolean reverse) throws ObjectFactoryException, SessionTimeoutException {
/* 571 */     params = prepareParams(params);
/* 572 */     Object[] callParams = { items, itemClassName, params, Integer.valueOf(maxCount), new Boolean(reverse) };
/* 573 */     return runRemoteMethodWithObjectColl(methodName, items, callParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithObjectColl(String methodName, BasicBusinessObjects items, Object[] callParams) throws ObjectFactoryException, SessionTimeoutException {
/* 579 */     if (items != null)
/* 580 */       items.clear(); 
/* 581 */     boolean[] retParams = { true };
/* 582 */     FactoryMethodCallResult result = runRemoteMethod(methodName, callParams, retParams);
/* 583 */     getBOCollectionFromResult(items, result.ReturnParams);
/*     */ 
/*     */     
/* 586 */     if (items != null)
/* 587 */       JLbsClientLockManager.postProcessParams(callParams, 2, items.toArray()); 
/* 588 */     return result.ReturnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected FactoryMethodCallResult runRemoteMethod(String methodName, Object[] callParams, boolean[] returnParams) throws ObjectFactoryException, SessionTimeoutException {
/* 594 */     FactoryMethodCallResult result = new FactoryMethodCallResult();
/*     */     
/*     */     try {
/* 597 */       RemoteMethodResponse resp = this.m_Invoker.invoke(this.m_FactoryName, methodName, callParams, returnParams, this.m_ClassLoader);
/* 598 */       if (resp != null && resp.Result instanceof Boolean)
/*     */       {
/* 600 */         result.ReturnValue = ((Boolean)resp.Result).booleanValue();
/* 601 */         result.ReturnParams = resp.ReturnParameters;
/*     */       }
/*     */     
/* 604 */     } catch (ObjectFactoryException e) {
/*     */       
/* 606 */       ms_Logger.error("ClientFactory exception", (Throwable)e);
/* 607 */       throw e;
/*     */     }
/* 609 */     catch (SessionTimeoutException e) {
/*     */       
/* 611 */       ms_Logger.error("ClientFactory session exception", (Throwable)e);
/* 612 */       throw e;
/*     */     }
/* 614 */     catch (IOException e) {
/*     */       
/* 616 */       ms_Logger.error("ClientFactory invoke exception", e);
/* 617 */       throw new ObjectFactoryException(e, -1003, 74, 
/* 618 */           "Connection excepiton occured. Please check your connections.");
/*     */     }
/* 620 */     catch (Exception e) {
/*     */       
/* 622 */       ms_Logger.error("ClientFactory invoke exception", e);
/* 623 */       throw new ObjectFactoryException(e);
/*     */     } 
/* 625 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RemoteMethodResponse runRemoteMethodEx(String methodName, Object[] callParams, boolean[] returnParams) throws ObjectFactoryException, SessionTimeoutException {
/*     */     try {
/* 633 */       RemoteMethodResponse resp = this.m_Invoker.invoke(this.m_FactoryName, methodName, callParams, returnParams, this.m_ClassLoader);
/*     */       
/* 635 */       return resp;
/*     */     }
/* 637 */     catch (ObjectFactoryException e) {
/*     */       
/* 639 */       ms_Logger.error("ClientFactory exception", (Throwable)e);
/*     */       
/* 641 */       throw e;
/*     */     }
/* 643 */     catch (SessionTimeoutException e) {
/*     */       
/* 645 */       ms_Logger.error("ClientFactory session exception", (Throwable)e);
/*     */       
/* 647 */       throw e;
/*     */     }
/* 649 */     catch (IOException e) {
/*     */       
/* 651 */       ms_Logger.error("ClientFactory invoke exception", e);
/* 652 */       throw new ObjectFactoryException(e, -1003, 74, 
/* 653 */           "Connection excepiton occured. Please check your connections.");
/*     */     }
/* 655 */     catch (Exception e) {
/*     */       
/* 657 */       ms_Logger.error("ClientFactory invoke exception", e);
/* 658 */       throw new ObjectFactoryException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getBOCollectionFromResult(BasicBusinessObjects items, Object[] retParams) {
/* 664 */     if (items != null && retParams != null && retParams.length > 0 && retParams[0] instanceof BasicBusinessObjects) {
/*     */       
/* 666 */       BasicBusinessObjects targetItems = (BasicBusinessObjects)retParams[0];
/* 667 */       items.clear();
/* 668 */       items.addAll((Collection)targetItems);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isObjectTransferedBack(FactoryParams params) {
/* 674 */     if (params == null) {
/* 675 */       return FactoryParams.DEFAULT_TRANSFER_BO_BACK;
/*     */     }
/* 677 */     return params.isObjectTransferedBack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void validateBO(BasicBusinessObject bo) throws ObjectValidationException {
/* 682 */     if (JLbsConstants.USE_BO_VALIDATOR && bo instanceof ILbsObjectValidator) {
/*     */       
/*     */       try {
/*     */         
/* 686 */         Object source = null;
/* 687 */         Object o = bo.getCustomParameters();
/* 688 */         if (o instanceof ObjectValidatorSource) {
/*     */           
/* 690 */           source = ((ObjectValidatorSource)o).getValidationSource();
/* 691 */           bo.setCustomParameters(((ObjectValidatorSource)o).getOrgParameters());
/*     */         } 
/* 693 */         LbsValidationEvent event = new LbsValidationEvent();
/* 694 */         event.setContext((IApplicationContext)this.m_Context);
/* 695 */         event.setData(bo);
/* 696 */         event.setSource(source);
/* 697 */         if (source instanceof ILbsContainer)
/* 698 */           event.setContainer((ILbsContainer)source); 
/* 699 */         ILbsValidationResult result = ((ILbsObjectValidator)bo).validate((ILbsValidationEvent)event);
/* 700 */         if (result == null || !result.canContinue())
/*     */         {
/* 702 */           throw new ObjectValidationException(result);
/*     */         }
/*     */       }
/* 705 */       catch (ObjectValidationException e) {
/*     */         
/* 707 */         throw e;
/*     */       }
/* 709 */       catch (Throwable t) {
/*     */         
/* 711 */         throw new ObjectValidationException(t);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean runRemoteMethodWithBO(String methodName, BasicBusinessObject[] objects, Object[] callParams, boolean returnParam) throws ObjectFactoryException, SessionTimeoutException {
/* 720 */     boolean[] retParams = { returnParam };
/* 721 */     FactoryMethodCallResult result = runRemoteMethod(methodName, callParams, retParams);
/* 722 */     if (returnParam && result.ReturnParams != null && result.ReturnParams.length > 0 && objects != null) {
/*     */       
/*     */       try {
/*     */         
/* 726 */         BasicBusinessObject[] retObjects = (BasicBusinessObject[])result.ReturnParams[0];
/* 727 */         if (retObjects != null)
/*     */         {
/* 729 */           for (int i = 0; i < retObjects.length; i++) {
/* 730 */             ObjectUtil.shallowCopy(retObjects[i], objects[i], objects[i].getClass());
/*     */           }
/*     */         }
/* 733 */       } catch (Exception e) {
/*     */         
/* 735 */         ms_Logger.error("ClientFactory clone exception", e);
/*     */       } 
/*     */     }
/*     */     
/* 739 */     return result.ReturnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApplicationVariables getApplicationVariables() {
/*     */     try {
/* 747 */       RemoteMethodResponse resp = runRemoteMethodEx("getApplicationVariables", new Object[0], new boolean[] { true });
/*     */       
/* 749 */       return (ApplicationVariables)resp.Result;
/*     */     }
/* 751 */     catch (ObjectFactoryException e) {
/*     */       
/* 753 */       ms_Logger.error("ClientFactory exception", (Throwable)e);
/*     */ 
/*     */       
/* 756 */       return null;
/*     */     }
/* 758 */     catch (SessionTimeoutException e) {
/*     */       
/* 760 */       ms_Logger.error("ClientFactory session exception", (Throwable)e);
/*     */ 
/*     */       
/* 763 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FactoryParams prepareParams(FactoryParams params) {
/* 772 */     if (this.m_ParamsListener != null) {
/* 773 */       return this.m_ParamsListener.prepareObjectFactoryParams(this, params);
/*     */     }
/* 775 */     return params;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFactoryParamsListener getParamsListener() {
/* 780 */     return this.m_ParamsListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParamsListener(IFactoryParamsListener listener) {
/* 785 */     this.m_ParamsListener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readProperty(BusinessObject obj, FactoryParams params, String property, int loadingState) throws ObjectFactoryException {
/* 791 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initializeObjectOnServer(BasicBusinessObject businessObject, FactoryParams params) throws ObjectFactoryException, SessionTimeoutException {
/* 798 */     params = prepareParams(params);
/* 799 */     Object[] callParams = { businessObject, params };
/* 800 */     return runRemoteMethodWithBO("initializeObjectOnServer", businessObject, callParams);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsClientObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */