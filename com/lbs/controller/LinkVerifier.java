/*     */ package com.lbs.controller;
/*     */ 
/*     */ import com.lbs.data.query.QueryBusinessObject;
/*     */ import com.lbs.data.query.QueryBusinessObjects;
/*     */ import com.lbs.data.query.QueryFactoryException;
/*     */ import com.lbs.data.query.QueryFilterTerm;
/*     */ import com.lbs.data.query.QueryParams;
/*     */ import com.lbs.invoke.SessionReestablishedException;
/*     */ import com.lbs.invoke.SessionTimeoutException;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.transport.IUserDetailInfo;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.LbsAuthCodeHelper;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ public abstract class LinkVerifier
/*     */ {
/*     */   public static final String QUERY_RESULT = "QUERY_RESULT";
/*     */   protected IApplicationContext m_Context;
/*  25 */   protected Hashtable<String, String> m_InputValues = new Hashtable<>();
/*     */   
/*  27 */   protected ArrayList<ConditionInLinkVerifier> m_EnabledConditions = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public LinkVerifier(IApplicationContext context) {
/*  32 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareQueryParams(QueryParams params, String columnName, String term, String parameterName, String variableName, String orderName, Object value, int operator) {
/*  38 */     if (!StringUtil.isEmpty(term)) {
/*     */       
/*  40 */       params.getEnabledTerms().enable(term);
/*  41 */       if (!StringUtil.isEmpty(parameterName))
/*     */       {
/*  43 */         params.getParameters().put(parameterName, value);
/*     */       }
/*  45 */       if (!StringUtil.isEmpty(variableName))
/*     */       {
/*  47 */         params.getVariables().put(variableName, value);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  52 */       QueryFilterTerm filterTerm = new QueryFilterTerm(columnName, value, operator);
/*  53 */       params.getFilterTerms().add(filterTerm);
/*     */     } 
/*  55 */     if (!StringUtil.isEmpty(orderName)) {
/*  56 */       params.setOrder(orderName, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract QueryBusinessObjects search(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
/*     */ 
/*     */   
/*     */   public abstract QueryBusinessObjects list(String paramString, Object paramObject, int paramInt1, int paramInt2) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException;
/*     */ 
/*     */   
/*     */   protected QueryBusinessObjects listImpl(QueryParams params, String queryName, int maxRowCount) throws QueryFactoryException, SessionTimeoutException, SessionReestablishedException {
/*  68 */     QueryBusinessObjects items = new QueryBusinessObjects();
/*  69 */     this.m_Context.getQueryFactory().select(queryName, params, items, maxRowCount);
/*  70 */     return items;
/*     */   }
/*     */ 
/*     */   
/*     */   protected OperationResult validateImpl(QueryParams params, String queryName, String propertyName) throws InvalidDataException {
/*  75 */     QueryBusinessObjects items = new QueryBusinessObjects();
/*     */     
/*     */     try {
/*  78 */       if (this.m_Context.getQueryFactory().select(queryName, params, items, 1) && items.size() > 0) {
/*     */         
/*  80 */         QueryBusinessObject item = items.item(0);
/*  81 */         if (item != null) {
/*     */           
/*  83 */           String auhtVal = null;
/*  84 */           int authModeId = params.getAuthModeID();
/*  85 */           auhtVal = (String)item.getProperties().getValue("AuthCode");
/*  86 */           if (JLbsStringUtil.isEmpty(auhtVal)) {
/*     */             
/*  88 */             auhtVal = (String)item.getProperties().getValue("Code");
/*  89 */             checkDBAuthForOrgUnit(propertyName, item, auhtVal, authModeId);
/*     */           } 
/*  91 */           if (auhtVal != null && params != null && params.getAuthModeID() != -1)
/*     */           {
/*  93 */             if (!LbsAuthCodeHelper.hasRight(getDBRights(), 64, params.getAuthModeID(), 
/*  94 */                 auhtVal))
/*  95 */               throw prepareNotFoundException(propertyName); 
/*     */           }
/*     */         } 
/*  98 */         OperationResult result = new OperationResult(true);
/*  99 */         if (item != null)
/* 100 */           result.putExtraData("QUERY_RESULT", (Serializable)item); 
/* 101 */         return result;
/*     */       } 
/* 103 */       throw prepareNotFoundException(propertyName);
/*     */     }
/* 105 */     catch (QueryFactoryException e) {
/*     */       
/* 107 */       throw new InvalidDataException(e);
/*     */     }
/* 109 */     catch (SessionTimeoutException e) {
/*     */       
/* 111 */       throw new InvalidDataException(e);
/*     */     }
/* 113 */     catch (SessionReestablishedException e) {
/*     */       
/* 115 */       throw new InvalidDataException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkDBAuthForOrgUnit(String propertyName, QueryBusinessObject item, String auhtVal, int authModeId) throws InvalidDataException {
/* 122 */     if (JLbsStringUtil.equals(item.getTableName(), "GOOrgUnits")) {
/*     */       
/* 124 */       if (authModeId == 1 || authModeId == 4 || authModeId == 5 || authModeId == 6 || authModeId == 0) {
/* 125 */         authModeId = -1;
/* 126 */       } else if (authModeId == 2) {
/* 127 */         authModeId = -4;
/* 128 */       } else if (authModeId == 3) {
/* 129 */         authModeId = -3;
/* 130 */       } else if (authModeId == 7) {
/* 131 */         authModeId = -5;
/*     */       } 
/* 133 */       if (auhtVal != null)
/*     */       {
/* 135 */         if (!LbsAuthCodeHelper.hasRight(getDBRights(), 64, authModeId, auhtVal)) {
/* 136 */           throw prepareNotFoundException(propertyName);
/*     */         }
/*     */       }
/*     */     }
/* 140 */     else if (JLbsStringUtil.equals(item.getTableName(), "GODepartments")) {
/*     */       
/* 142 */       if (authModeId == 0) {
/* 143 */         authModeId = -2;
/*     */       }
/* 145 */       if (auhtVal != null)
/*     */       {
/* 147 */         if (!LbsAuthCodeHelper.hasRight(getDBRights(), 64, authModeId, auhtVal)) {
/* 148 */           throw prepareNotFoundException(propertyName);
/*     */         
/*     */         }
/*     */       }
/*     */     }
/* 153 */     else if (JLbsStringUtil.equals(item.getTableName(), "GLPostingGroups")) {
/*     */       
/* 155 */       if (authModeId == 0) {
/* 156 */         authModeId = 1051;
/*     */       }
/* 158 */       if (auhtVal != null)
/*     */       {
/* 160 */         if (!LbsAuthCodeHelper.hasRight(getDBRights(), 64, authModeId, auhtVal)) {
/* 161 */           throw prepareNotFoundException(propertyName);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsStringList getDBRights() {
/* 169 */     IUserDetailInfo userDetailInfo = this.m_Context.getUserDetailInfo();
/* 170 */     if (userDetailInfo != null)
/* 171 */       return userDetailInfo.getDBRights(); 
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected InvalidDataException prepareNotFoundException(String propertyName) {
/* 177 */     return new InvalidDataException(80300, 709, 
/* 178 */         "Record with the given properties could not be found, or you do not have permission to use this record!!!");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\LinkVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */