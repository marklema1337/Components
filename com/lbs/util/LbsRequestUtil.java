/*     */ package com.lbs.util;
/*     */ 
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
/*     */ 
/*     */ public class LbsRequestUtil
/*     */ {
/*     */   public static final String PARAM_LANG = "lang";
/*     */   public static final String PARAM_ACTIONID = "actionId";
/*     */   public static final String PARAM_KEY = "key";
/*     */   public static final String PARAM_MODE = "mode";
/*     */   public static final String PARAM_PID = "pid";
/*     */   public static final String MODE_EDITOR = "editor";
/*     */   public static final String MODE_BROWSER = "browser";
/*     */   public static final String PARAM_TOKEN = "token";
/*     */   public static final String PARAM_USER = "user";
/*     */   public static final String PARAM_FIRM = "firm";
/*     */   public static final String PARAM_LANGUAGE = "lang";
/*     */   public static final String PARAM_FRESH_PORT = "freshport";
/*     */   
/*     */   public static Hashtable<String, String> parseRequestParameters(String reqQuery) {
/*  33 */     Hashtable<String, String> map = new Hashtable<>();
/*  34 */     if (reqQuery != null) {
/*     */       
/*  36 */       String[] pars = JLbsStringUtil.split(reqQuery, "&", true);
/*  37 */       for (int i = 0; i < pars.length; i++) {
/*     */         
/*  39 */         int idx = pars[i].indexOf('=');
/*  40 */         if (idx > 0) {
/*     */           
/*  42 */           String name = pars[i].substring(0, idx);
/*  43 */           String value = null;
/*  44 */           if (idx + 1 < pars[i].length()) {
/*  45 */             value = pars[i].substring(idx + 1);
/*     */           } else {
/*  47 */             value = "";
/*  48 */           }  map.put(name, value);
/*     */         } else {
/*     */           
/*  51 */           map.put("lang", pars[i]);
/*     */         } 
/*     */       } 
/*  54 */     }  return map;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTokenParameter(String reqQuery, String defaultValue) {
/*  59 */     String token = null;
/*  60 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/*  61 */     if (parameters != null)
/*  62 */       token = parameters.get("token"); 
/*  63 */     if (!JLbsStringUtil.isEmpty(token))
/*  64 */       return token; 
/*  65 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getLangParameter(String reqQuery, String defaultValue) {
/*  70 */     String lang = null;
/*  71 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/*  72 */     if (parameters != null)
/*  73 */       lang = parameters.get("lang"); 
/*  74 */     if (!JLbsStringUtil.isEmpty(lang))
/*  75 */       return lang; 
/*  76 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getUserParameter(String reqQuery, String defaultValue) {
/*  81 */     String user = null;
/*  82 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/*  83 */     if (parameters != null)
/*  84 */       user = parameters.get("user"); 
/*  85 */     if (user != null && user.length() > 0) {
/*  86 */       return user;
/*     */     }
/*  88 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFirmParameter(String reqQuery, String defaultValue) {
/*  93 */     String firm = null;
/*  94 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/*  95 */     if (parameters != null)
/*  96 */       firm = parameters.get("firm"); 
/*  97 */     if (!JLbsStringUtil.isEmpty(firm)) {
/*  98 */       return firm;
/*     */     }
/* 100 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getLanguageParameter(String reqQuery, String defaultValue) {
/* 105 */     String lang = null;
/* 106 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 107 */     if (parameters != null)
/* 108 */       lang = parameters.get("lang"); 
/* 109 */     if (!JLbsStringUtil.isEmpty(lang))
/* 110 */       return lang; 
/* 111 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getPidParameter(String reqQuery, String defaultValue) {
/* 116 */     String pid = null;
/* 117 */     Hashtable<String, String> parameters = parseRequestParameters(reqQuery);
/* 118 */     if (parameters != null)
/* 119 */       pid = parameters.get("pid"); 
/* 120 */     if (!JLbsStringUtil.isEmpty(pid))
/* 121 */       return pid; 
/* 122 */     return defaultValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsRequestUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */