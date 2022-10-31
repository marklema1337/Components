/*     */ package com.lbs.transport;
/*     */ public class UserInfo implements Serializable { private static final long serialVersionUID = 1L; public String Name; public String Domain; public String OSUser; public String Password; public boolean testScope; public Object Parameters; public int UserNumber; public String LtpaToken; public long[] clientIpAddresses; public String clientMachineName; public transient long serverIpAddress; public boolean changeCompany; public boolean changeLanguage; public String selectedLanguage; public JLbsStringList globalResources; public String oauthToken; public String customToken; public String refreshToken; public String tenantId; public String timeZone; public boolean loginAsDifferentUser; public String diffUserName; public String diffUserPassword; public int LoginType; public String azureUniqueName; public String azureObjectID; public boolean loginAdminForService; public boolean loginSaasMenu; public int ldapReference;
/*     */   public boolean fromWebServiceLogin;
/*     */   private JLbsStringList oktaProperties;
/*     */   public static final int USRTYPE_USER = 1;
/*     */   public static final int USRTYPE_PLATFORMUSER = 2;
/*     */   public static final int USRTYPE_EMPLOYEE = 4;
/*     */   public static final int USRTYPE_RESTRICTED = 8;
/*     */   public static final int USRTYPE_EXCELLENT = 16;
/*     */   public static final int USRTYPE_PROJECT_PORTAL = 32;
/*     */   public static final int USRTYPE_HEALTH_PORTAL = 64;
/*     */   public static final int USRTYPE_CONTRACTOR_PORTAL = 128;
/*     */   public static final int USRTYPE_BI_USER = 256;
/*     */   public static final int USRTYPE_MOBILE_USER = 512;
/*     */   public int userTypes;
/*     */   public static final int[] AllUserTypes = new int[] { 1, 2, 4, 8, 16, 32, 64, 128 };
/*     */   public static final int SYSTEM_LOGIN = 0;
/*     */   public static final String SESS_VAR_GWTSESSION = "LBS_SESS_VAR_GWTSESSION";
/*     */   public String parentSessionId;
/*     */   public boolean loginWithToken;
/*     */   public MODE_LOGIN modeLogin;
/*     */   public transient IVariableHolder variableHolder;
/*     */   public transient Integer dSignatureFirmNr;
/*     */   
/*  25 */   public UserInfo() { this.testScope = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.clientMachineName = "";
/*  34 */     this.serverIpAddress = 0L;
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
/*  51 */     this.loginAsDifferentUser = false;
/*     */ 
/*     */     
/*  54 */     this.LoginType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.loginAdminForService = false;
/*     */     
/*  61 */     this.loginSaasMenu = false;
/*     */     
/*  63 */     this.ldapReference = 0;
/*     */     
/*  65 */     this.fromWebServiceLogin = false;
/*     */     
/*  67 */     this.oktaProperties = null;
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
/*     */     
/* 117 */     this.loginWithToken = false;
/* 118 */     this.modeLogin = MODE_LOGIN.DEFAULT;
/*     */ 
/*     */ 
/*     */     
/* 122 */     this.GwtSession = false;
/*     */     
/* 124 */     this.reLogin = false; }
/*     */   public boolean GwtSession; public boolean reLogin; public boolean isRestricted() { if (SetUtil.isOptionSet(this.userTypes, 1)) return false;  return SetUtil.isOptionSet(this.userTypes, 8); } public String getOAuthToken() { return this.oauthToken; } public void setOAuthToken(String oauthToken) { this.oauthToken = oauthToken; } public String getRefreshToken() { return this.refreshToken; } public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; } public enum MODE_LOGIN
/*     */   {
/* 127 */     DEFAULT, TOKEN, CUSTOM, LDAP, LDAP_FORCE_PASSWORD, DSIGNATURE, ADFS, OAUTHTOKEN, OKTA;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLoginAppForUserType() {
/* 132 */     if (this.userTypes == 0 || 
/* 133 */       SetUtil.isOptionSet(this.userTypes, 1) || SetUtil.isOptionSet(this.userTypes, 2))
/* 134 */       return true; 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     StringBuilder sb = new StringBuilder();
/* 143 */     sb.append("Name:").append(this.Name);
/* 144 */     sb.append(" Oauthtoken:").append(this.oauthToken);
/* 145 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFromWebServiceLogin() {
/* 150 */     return this.fromWebServiceLogin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromWebServiceLogin(boolean fromWebServiceLogin) {
/* 155 */     this.fromWebServiceLogin = fromWebServiceLogin;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomToken() {
/* 160 */     return this.customToken;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomToken(String customToken) {
/* 165 */     this.customToken = customToken;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getOktaProperties() {
/* 170 */     return this.oktaProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOktaProperties(JLbsStringList oktaProperties) {
/* 175 */     this.oktaProperties = oktaProperties;
/*     */   } }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\UserInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */