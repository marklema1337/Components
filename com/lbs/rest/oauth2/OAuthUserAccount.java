/*     */ package com.lbs.rest.oauth2;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class OAuthUserAccount
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String username;
/*     */   private String password;
/*     */   private String firmNr;
/*     */   private String lang;
/*     */   private String code;
/*     */   private String token;
/*     */   private String email;
/*     */   private String refreshToken;
/*     */   private long expireTime;
/*     */   private long expiration;
/*     */   private String fullname;
/*     */   private int userNr;
/*     */   private String azureUniqueName;
/*     */   private String azureObjectID;
/*     */   private boolean cloudIdmOnpremUser = false;
/*     */   
/*     */   public OAuthUserAccount() {}
/*     */   
/*     */   public OAuthUserAccount(String username, String password, String firmNr, String lang, String email, String code, String token, String refreshToken, long expireTime, String fullname, int userNr) {
/*  39 */     this.username = username;
/*  40 */     this.password = password;
/*  41 */     this.firmNr = firmNr;
/*  42 */     this.lang = lang;
/*  43 */     this.email = email;
/*  44 */     this.code = code;
/*  45 */     this.token = token;
/*  46 */     this.refreshToken = refreshToken;
/*  47 */     this.expireTime = expireTime;
/*  48 */     this.fullname = fullname;
/*  49 */     this.userNr = userNr;
/*  50 */     this.expiration = currentTimeSeconds() + expireTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUserNr() {
/*  55 */     return this.userNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserNr(int userNr) {
/*  60 */     this.userNr = userNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullname() {
/*  65 */     return this.fullname;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFullname(String fullname) {
/*  70 */     this.fullname = fullname;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  75 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/*  80 */     this.password = password;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFirmNr() {
/*  85 */     return this.firmNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFirmNr(String firmNr) {
/*  90 */     this.firmNr = firmNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLang() {
/*  95 */     return this.lang;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLang(String lang) {
/* 100 */     this.lang = lang;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getExpiration() {
/* 105 */     return this.expiration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpiration(long expiration) {
/* 110 */     this.expiration = expiration;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEmail() {
/* 115 */     return this.email;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmail(String email) {
/* 120 */     this.email = email;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 125 */     return this.username;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUsername(String username) {
/* 130 */     this.username = username;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 135 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 140 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToken() {
/* 145 */     return this.token;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToken(String token) {
/* 150 */     this.token = token;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRefreshToken() {
/* 155 */     return this.refreshToken;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRefreshToken(String refreshToken) {
/* 160 */     this.refreshToken = refreshToken;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getExpireTime() {
/* 165 */     return this.expireTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpireTime(long expireTime) {
/* 170 */     this.expireTime = expireTime;
/* 171 */     this.expiration = currentTimeSeconds() + expireTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRefreshable() {
/* 176 */     return (getRefreshToken() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExpired() {
/* 181 */     return (this.expiration < currentTimeSeconds());
/*     */   }
/*     */ 
/*     */   
/*     */   private long currentTimeSeconds() {
/* 186 */     return System.currentTimeMillis() / 1000L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAzureUniqueName() {
/* 191 */     return this.azureUniqueName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAzureUniqueName(String azureUniqueName) {
/* 196 */     this.azureUniqueName = azureUniqueName;
/*     */   }
/*     */   
/*     */   public String getAzureObjectID() {
/* 200 */     return this.azureObjectID;
/*     */   }
/*     */   
/*     */   public void setAzureObjectID(String azureObjectID) {
/* 204 */     this.azureObjectID = azureObjectID;
/*     */   }
/*     */   
/*     */   public boolean isCloudIdmOnpremUser() {
/* 208 */     return this.cloudIdmOnpremUser;
/*     */   }
/*     */   
/*     */   public void setCloudIdmOnpremUser(boolean cloudIdmOnpremUser) {
/* 212 */     this.cloudIdmOnpremUser = cloudIdmOnpremUser;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rest\oauth2\OAuthUserAccount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */