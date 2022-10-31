/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ public class DBTableMode
/*     */   implements Serializable, Cloneable, INamedEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_ID;
/*     */   private String m_Condition;
/*     */   private int m_AuthID;
/*     */   private String m_Module;
/*     */   private String m_SetValue;
/*  27 */   private ArrayList m_EnabledLinks = new ArrayList();
/*     */   private QueryItem m_Expression;
/*     */   private boolean m_IgnoreInBO;
/*     */   private String m_ExtraCondition;
/*     */   private String m_ExtraConditionJoinType;
/*     */   private boolean m_DoNotAuthorize = false;
/*  33 */   private String m_ExtraConditionForNonAuth = "";
/*     */ 
/*     */ 
/*     */   
/*     */   public DBTableMode() {}
/*     */ 
/*     */   
/*     */   public DBTableMode(DBTableMode mode) {
/*  41 */     this.m_ID = mode.getID();
/*  42 */     this.m_Condition = mode.getCondition();
/*  43 */     this.m_AuthID = mode.getAuthID();
/*  44 */     this.m_Module = mode.getModule();
/*  45 */     this.m_SetValue = mode.getSetValue();
/*  46 */     this.m_EnabledLinks = mode.getEnabledLinks();
/*  47 */     this.m_Expression = mode.getExpression();
/*  48 */     this.m_IgnoreInBO = mode.isIgnoreInBO();
/*  49 */     this.m_ExtraCondition = mode.getExtraCondition();
/*  50 */     this.m_ExtraConditionJoinType = mode.getExtraConditionJoinType();
/*  51 */     this.m_DoNotAuthorize = mode.isDoNotAuthorize();
/*  52 */     this.m_ExtraConditionForNonAuth = mode.getExtraConditionForNonAuth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getID() {
/*  57 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setID(int authMode) {
/*  62 */     this.m_ID = authMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  67 */     return "" + this.m_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getExpression() {
/*  72 */     return this.m_Expression;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpression(QueryItem expression) {
/*  77 */     this.m_Expression = expression;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAuthID() {
/*  82 */     return this.m_AuthID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthID(int authID) {
/*  87 */     this.m_AuthID = authID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCondition() {
/*  92 */     return this.m_Condition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCondition(String condition) {
/*  97 */     this.m_Condition = condition;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 102 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModule() {
/* 107 */     return this.m_Module;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModule(String module) {
/* 112 */     this.m_Module = module;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSetValue() {
/* 117 */     return this.m_SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValue(String setValue) {
/* 122 */     this.m_SetValue = setValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getEnabledLinks() {
/* 127 */     return this.m_EnabledLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabledLinks(ArrayList enabledLinks) {
/* 132 */     this.m_EnabledLinks = enabledLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEnabledLink(String linkAlias) {
/* 137 */     if (!StringUtil.isEmpty(linkAlias) && !this.m_EnabledLinks.contains(linkAlias)) {
/* 138 */       this.m_EnabledLinks.add(linkAlias);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeEnabledLink(String linkAlias) {
/* 143 */     this.m_EnabledLinks.remove(linkAlias);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAllLinks(NamedCollection links) {
/* 149 */     if (links != null) {
/* 150 */       for (int i = 0; i < links.size(); i++) {
/*     */         
/* 152 */         DBTableLink link = (DBTableLink)links.get(i);
/* 153 */         addEnabledLink(link.getAlias());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void setIgnoreInBO(boolean ignoreInBO) {
/* 159 */     this.m_IgnoreInBO = ignoreInBO;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIgnoreInBO() {
/* 164 */     return this.m_IgnoreInBO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraCondition(String extraCondition) {
/* 172 */     this.m_ExtraCondition = extraCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtraCondition() {
/* 180 */     return this.m_ExtraCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraConditionJoinType(String extraConditionJoinType) {
/* 188 */     this.m_ExtraConditionJoinType = extraConditionJoinType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtraConditionJoinType() {
/* 196 */     return this.m_ExtraConditionJoinType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDoNotAuthorize(boolean doNotAuthorize) {
/* 201 */     this.m_DoNotAuthorize = doNotAuthorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDoNotAuthorize() {
/* 206 */     return this.m_DoNotAuthorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraConditionForNonAuth(String extraConditionForNonAuth) {
/* 211 */     this.m_ExtraConditionForNonAuth = extraConditionForNonAuth;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getExtraConditionForNonAuth() {
/* 216 */     return this.m_ExtraConditionForNonAuth;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBTableMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */