/*     */ package com.lbs.mi.defs;
/*     */ 
/*     */ import com.lbs.interfaces.IModuleAction;
/*     */ import com.lbs.util.JLbsStringListItemEx;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleAction
/*     */   implements Serializable, IModuleAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int ACTION_ID = -99;
/*  17 */   private int m_ActionType = 1;
/*     */   private int m_ActionId;
/*     */   private boolean m_Modal;
/*  20 */   private String m_Resource = "";
/*  21 */   private String m_DataClass = "";
/*     */   
/*     */   private String m_QueryOrder;
/*     */   private JLbsStringListItemEx[] m_ParamValues;
/*     */   private JLbsStringListItemEx[] m_QueryParamValues;
/*     */   private JLbsStringListItemEx[] m_QueryVariableValues;
/*     */   private String[] m_QueryTerms;
/*     */   private String[] m_QueryTableLinks;
/*     */   private int[] m_HiddenFilters;
/*     */   private ModuleJarDefinition[] m_ModuleJars;
/*     */   private String m_PropertyName;
/*     */   private String m_SetValue;
/*     */   private String m_URL;
/*     */   private String m_Name;
/*     */   private String m_ListenerName;
/*     */   private int m_Type;
/*     */   private int m_ModuleId;
/*  38 */   private Hashtable<String, ModuleContractInput> m_ContractInputs = new Hashtable<>();
/*     */ 
/*     */   
/*     */   private String m_CustGUID;
/*     */ 
/*     */   
/*     */   public ModuleAction() {}
/*     */ 
/*     */   
/*     */   public ModuleAction(ModuleAction action) {
/*  48 */     this.m_ActionId = action.m_ActionId;
/*  49 */     this.m_ActionType = action.m_ActionType;
/*  50 */     this.m_ContractInputs = action.m_ContractInputs;
/*  51 */     this.m_DataClass = action.m_DataClass;
/*  52 */     this.m_HiddenFilters = action.m_HiddenFilters;
/*  53 */     this.m_Modal = action.m_Modal;
/*  54 */     this.m_ModuleJars = action.m_ModuleJars;
/*  55 */     this.m_Name = action.m_Name;
/*  56 */     this.m_ParamValues = action.m_ParamValues;
/*  57 */     this.m_PropertyName = action.m_PropertyName;
/*  58 */     this.m_QueryOrder = action.m_QueryOrder;
/*  59 */     this.m_QueryParamValues = action.m_QueryParamValues;
/*  60 */     this.m_QueryTableLinks = action.m_QueryTableLinks;
/*  61 */     this.m_QueryTerms = action.m_QueryTerms;
/*  62 */     this.m_QueryVariableValues = action.m_QueryVariableValues;
/*  63 */     this.m_Resource = action.m_Resource;
/*  64 */     this.m_SetValue = action.m_SetValue;
/*  65 */     this.m_URL = action.m_URL;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSetValue() {
/*  70 */     return this.m_SetValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValue(String setValue) {
/*  75 */     this.m_SetValue = setValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  80 */     return this.m_PropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropertyName(String propertyName) {
/*  85 */     this.m_PropertyName = propertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionType() {
/*  90 */     return this.m_ActionType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDataClass() {
/*  95 */     return this.m_DataClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getHiddenFilters() {
/* 100 */     return this.m_HiddenFilters;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 105 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 110 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItemEx[] getParamValues() {
/* 115 */     return this.m_ParamValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryOrder() {
/* 120 */     return this.m_QueryOrder;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItemEx[] getQueryParamValues() {
/* 125 */     return this.m_QueryParamValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getQueryTableLinks() {
/* 130 */     return this.m_QueryTableLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getQueryTerms() {
/* 135 */     return this.m_QueryTerms;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResource() {
/* 140 */     return this.m_Resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionType(int i) {
/* 145 */     this.m_ActionType = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDataClass(String string) {
/* 150 */     this.m_DataClass = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHiddenFilters(int[] is) {
/* 155 */     this.m_HiddenFilters = is;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParamValues(JLbsStringListItemEx[] objects) {
/* 160 */     this.m_ParamValues = objects;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryOrder(String string) {
/* 165 */     this.m_QueryOrder = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParamValues(JLbsStringListItemEx[] objects) {
/* 170 */     this.m_QueryParamValues = objects;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryTableLinks(String[] strings) {
/* 175 */     this.m_QueryTableLinks = strings;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryTerms(String[] strings) {
/* 180 */     this.m_QueryTerms = strings;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResource(String string) {
/* 185 */     this.m_Resource = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionId() {
/* 190 */     return this.m_ActionId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionId(int i) {
/* 195 */     this.m_ActionId = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModuleId() {
/* 200 */     return this.m_ModuleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModuleId(int moduleId) {
/* 205 */     this.m_ModuleId = moduleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItemEx[] getQueryVariableValues() {
/* 210 */     return this.m_QueryVariableValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryVariableValues(JLbsStringListItemEx[] exs) {
/* 215 */     this.m_QueryVariableValues = exs;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isModal() {
/* 220 */     return this.m_Modal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModal(boolean b) {
/* 225 */     this.m_Modal = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleJarDefinition[] getModuleJars() {
/* 230 */     return this.m_ModuleJars;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModuleJars(ModuleJarDefinition[] moduleJars) {
/* 235 */     this.m_ModuleJars = moduleJars;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getURL() {
/* 240 */     if (JLbsStringUtil.isEmpty(this.m_URL))
/*     */     {
/* 242 */       return generateURL();
/*     */     }
/* 244 */     return this.m_URL;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getListenerName() {
/* 249 */     return this.m_ListenerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListenerName(String listenerName) {
/* 254 */     this.m_ListenerName = listenerName;
/*     */   }
/*     */ 
/*     */   
/*     */   private String generateURL() {
/* 259 */     switch (this.m_ActionType) {
/*     */       
/*     */       case 1:
/* 262 */         return "form_" + urlizeResource();
/*     */       case 2:
/* 264 */         return "report_" + this.m_Resource;
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String urlizeResource() {
/* 271 */     if (this.m_Resource != null) {
/*     */       
/* 273 */       String url = this.m_Resource;
/* 274 */       url = url.replace('%', '_');
/* 275 */       int idx = url.indexOf('.');
/* 276 */       if (idx > 0)
/* 277 */         url = url.substring(0, idx); 
/* 278 */       return url;
/*     */     } 
/* 280 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void setURL(String url) {
/* 285 */     this.m_URL = url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 293 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 301 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, ModuleContractInput> getContractInputs() {
/* 306 */     return this.m_ContractInputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustGUID() {
/* 311 */     return this.m_CustGUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustGUID(String custGUID) {
/* 316 */     this.m_CustGUID = custGUID;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\ModuleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */