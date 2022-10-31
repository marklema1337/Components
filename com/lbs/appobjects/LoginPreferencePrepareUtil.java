/*     */ package com.lbs.appobjects;
/*     */ 
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import com.lbs.util.ConvertUtil;
/*     */ import com.lbs.util.ILbsLoginConstants;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ public class LoginPreferencePrepareUtil
/*     */   implements ILbsLoginConstants
/*     */ {
/*     */   private int m_Firm;
/*     */   private String m_Language;
/*     */   private Object m_SelectedCulture;
/*     */   private ArrayList<Object> m_LoginPreferences;
/*     */   
/*     */   public void collectLoginPreferences(String userName, LoginPreferenceCollector collector) throws Exception {
/*  36 */     ArrayList<Object> preferences = collector.getUserLoginPreferences(userName);
/*  37 */     this.m_LoginPreferences = preferences;
/*  38 */     Integer compactForms = null;
/*  39 */     Integer showMainPage = null;
/*  40 */     Integer showAdminConsole = null;
/*  41 */     Integer showMessages = null;
/*  42 */     if (preferences != null) {
/*     */       
/*  44 */       if (preferences.size() != 0) {
/*     */         
/*  46 */         boolean adminDefinedFirm = false;
/*  47 */         boolean adminDefinedLang = false;
/*  48 */         int loginFirmPref = ((Integer)preferences.get(0)).intValue();
/*  49 */         int loginFirm = ((Integer)preferences.get(1)).intValue();
/*  50 */         int loginLangPref = ((Integer)preferences.get(2)).intValue();
/*  51 */         String loginLang = (String)preferences.get(3);
/*  52 */         String lastUsedFirm = "0";
/*  53 */         String lastUsedLang = "";
/*  54 */         byte[] serializedLastLoginPrefs = (byte[])preferences.get(6);
/*  55 */         compactForms = (Integer)preferences.get(7);
/*  56 */         showMainPage = (Integer)preferences.get(8);
/*  57 */         showAdminConsole = (Integer)preferences.get(9);
/*  58 */         showMessages = (Integer)preferences.get(10);
/*  59 */         Object o = TransportUtil.deserializeObject(serializedLastLoginPrefs);
/*  60 */         if (o instanceof String) {
/*     */           
/*  62 */           String lastLoginPrefs = (String)o;
/*  63 */           String[] lastLoginPrefsArray = lastLoginPrefs.split(",");
/*  64 */           if (lastLoginPrefsArray.length == 2) {
/*     */             
/*  66 */             lastUsedFirm = lastLoginPrefsArray[0];
/*  67 */             lastUsedLang = lastLoginPrefsArray[1];
/*     */           } 
/*     */         } 
/*  70 */         switch (loginFirmPref) {
/*     */           
/*     */           case 1:
/*  73 */             if (!JLbsStringUtil.areEqualNoCase(lastUsedFirm, "0")) {
/*  74 */               this.m_Firm = ConvertUtil.strToInt(lastUsedFirm);
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/*  80 */             this.m_Firm = loginFirm;
/*     */             break;
/*     */           
/*     */           default:
/*  84 */             adminDefinedFirm = true;
/*     */             break;
/*     */         } 
/*     */         
/*  88 */         switch (loginLangPref) {
/*     */           
/*     */           case 1:
/*  91 */             if (!JLbsStringUtil.isEmpty(lastUsedLang)) {
/*  92 */               findSelectedCulture(lastUsedLang, collector);
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/*  98 */             findSelectedCulture(loginLang, collector);
/*     */             break;
/*     */           
/*     */           default:
/* 102 */             adminDefinedLang = true;
/*     */             break;
/*     */         } 
/*     */         
/* 106 */         if (adminDefinedFirm || adminDefinedLang)
/*     */         {
/* 108 */           collectFromAdminPrefs(userName, collector, adminDefinedFirm, adminDefinedLang);
/*     */         }
/*     */         
/* 111 */         JLbsConstants.SHOW_SEARCH_ROW = false;
/* 112 */         JLbsConstants.SHOW_REPORT_DIALOG = false;
/*     */         
/* 114 */         if (((Integer)preferences.get(4)).intValue() > 0) {
/* 115 */           JLbsConstants.SHOW_SEARCH_ROW = true;
/*     */         }
/* 117 */         if (((Integer)preferences.get(5)).intValue() > 0) {
/* 118 */           JLbsConstants.SHOW_REPORT_DIALOG = true;
/*     */         }
/*     */       } else {
/*     */         
/* 122 */         collectFromAdminPrefs(userName, collector, true, true);
/* 123 */         if (this.m_Firm == 0) {
/* 124 */           this.m_Firm = innerCollectDefaultCompanyProperty(collector);
/*     */         }
/*     */       } 
/*     */     } else {
/* 128 */       collectDefaultCompanyProperty(userName, collector);
/*     */     } 
/* 130 */     collector.loginPreferencesReady(userName, this.m_Firm, this.m_Language, this.m_SelectedCulture, compactForms);
/*     */   }
/*     */ 
/*     */   
/*     */   private void collectDefaultCompanyProperty(String userName, LoginPreferenceCollector collector) throws Exception {
/* 135 */     if (collector.canCollectDefaultCompany()) {
/*     */ 
/*     */       
/* 138 */       int lCompanyNr = -99;
/* 139 */       ArrayList<Object> adminPreferences = collector.getAdminLoginPreferences(userName);
/* 140 */       if (adminPreferences != null && adminPreferences.size() != 0) {
/*     */         
/* 142 */         lCompanyNr = ConvertUtil.strToInt(String.valueOf(adminPreferences.get(0)));
/* 143 */         if (lCompanyNr == 0) {
/* 144 */           lCompanyNr = innerCollectDefaultCompanyProperty(collector);
/*     */         }
/*     */       } else {
/* 147 */         lCompanyNr = innerCollectDefaultCompanyProperty(collector);
/* 148 */       }  this.m_Firm = lCompanyNr;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int innerCollectDefaultCompanyProperty(LoginPreferenceCollector collector) throws Exception {
/* 154 */     String version = collector.getVersion();
/* 155 */     int firm = -99;
/* 156 */     if (version != null) {
/*     */       
/* 158 */       if (version.startsWith("v")) {
/* 159 */         version = version.substring(1);
/*     */       }
/* 161 */       String[] versions = version.split("\\.");
/* 162 */       if (versions.length >= 3)
/*     */       {
/* 164 */         firm = collector.getFirmForVersion(versions[0], versions[1], versions[2]);
/*     */       }
/*     */     } 
/* 167 */     return firm;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void collectFromAdminPrefs(String userName, LoginPreferenceCollector collector, boolean adminDefinedFirm, boolean adminDefinedLang) throws Exception {
/* 173 */     ArrayList<Object> adminPreferences = collector.getAdminLoginPreferences(userName);
/* 174 */     if (adminPreferences != null && adminPreferences.size() != 0) {
/*     */       
/* 176 */       if (adminDefinedFirm) {
/* 177 */         this.m_Firm = ConvertUtil.strToInt(String.valueOf(adminPreferences.get(0)));
/*     */       }
/* 179 */       if (adminDefinedLang) {
/*     */         
/* 181 */         int adminLang = ((Integer)adminPreferences.get(1)).intValue();
/* 182 */         findSelectedLanguage(adminLang, collector);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void findSelectedCulture(String language, LoginPreferenceCollector collector) {
/* 189 */     Object[] supportedCultures = collector.getSupportedCultures();
/* 190 */     if (supportedCultures != null)
/*     */     {
/* 192 */       for (int i = 0; i < supportedCultures.length; i++) {
/*     */         
/* 194 */         if (isSameCulture(supportedCultures[i], language)) {
/*     */           
/* 196 */           setSelectedCulture(supportedCultures[i]);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSameCulture(Object culture, String language) {
/* 205 */     if (culture instanceof JLbsCultureInfoBase)
/* 206 */       return ((JLbsCultureInfoBase)culture).getLanguagePrefix().equalsIgnoreCase(language); 
/* 207 */     return String.valueOf(culture).equalsIgnoreCase(language);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSelectedCulture(Object culture) {
/* 212 */     this.m_SelectedCulture = culture;
/* 213 */     if (culture instanceof JLbsCultureInfoBase) {
/* 214 */       this.m_Language = ((JLbsCultureInfoBase)culture).getLanguagePrefix();
/* 215 */     } else if (culture instanceof String) {
/* 216 */       this.m_Language = (String)culture;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findSelectedLanguage(int adminLang, LoginPreferenceCollector collector) {
/* 221 */     Object[] supportedCultures = collector.getSupportedCultures();
/* 222 */     if (supportedCultures == null || supportedCultures.length <= 0) {
/*     */       return;
/*     */     }
/* 225 */     boolean foundLang = false;
/* 226 */     String langPrefix = JLbsCultureInfoBase.getLanguagePrefix(adminLang); int i;
/* 227 */     for (i = 0; i < supportedCultures.length; i++) {
/*     */       
/* 229 */       if (isSameCulture(supportedCultures[i], langPrefix)) {
/*     */         
/* 231 */         setSelectedCulture(supportedCultures[i]);
/* 232 */         foundLang = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 237 */     if (!foundLang)
/*     */     {
/* 239 */       for (i = 0; i < supportedCultures.length; i++) {
/*     */         
/* 241 */         langPrefix = Locale.getDefault().getLanguage();
/* 242 */         langPrefix = String.valueOf(langPrefix) + langPrefix;
/* 243 */         if (isSameCulture(supportedCultures[i], langPrefix)) {
/*     */           
/* 245 */           setSelectedCulture(supportedCultures[i]);
/* 246 */           foundLang = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 252 */     if (!foundLang) {
/*     */       
/* 254 */       langPrefix = "ENUS";
/* 255 */       for (i = 0; i < supportedCultures.length; i++) {
/*     */         
/* 257 */         if (isSameCulture(supportedCultures[i], langPrefix)) {
/*     */           
/* 259 */           setSelectedCulture(supportedCultures[i]);
/* 260 */           foundLang = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 266 */     if (!foundLang) {
/* 267 */       setSelectedCulture(supportedCultures[0]);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getFirm() {
/* 272 */     return this.m_Firm;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/* 277 */     return this.m_Language;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedCulture() {
/* 282 */     return this.m_SelectedCulture;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<Object> getLoginPreferences() {
/* 287 */     return this.m_LoginPreferences;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\LoginPreferencePrepareUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */