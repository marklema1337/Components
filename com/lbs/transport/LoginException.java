/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.resource.JLbsLocalizer;
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
/*     */ public class LoginException
/*     */   extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int SUCCESS = 0;
/*     */   public static final int NOCHANNEL = -1;
/*     */   public static final int INVALIDURI = -2;
/*     */   public static final int INVALIDRESP = -3;
/*     */   public static final int INVALIDUSERPASS = -4;
/*     */   public static final int INNEREXCEPTION = -5;
/*     */   public static final int REMOTEEXCEPTION = -6;
/*     */   public static final int LICENCEEXCEPTION = -7;
/*     */   public static final int LICENCELANG = -8;
/*     */   public static final int LICENCETIMEOUT = -9;
/*     */   public static final int LICENCEOS = -10;
/*     */   public static final int NOLOGINSUPPORT = -11;
/*     */   public static final int URL_REDIRECTION = -12;
/*     */   public static final int CAPACITYEXCEEDED = -13;
/*     */   public static final int MENU_FUNC = -14;
/*     */   public static final int NO_LICENCE = -15;
/*     */   public static final int NO_CAPACITY_FOR_USERTYPE = -16;
/*     */   public static final int SERVER_MAINTENANCE_MODE = -17;
/*     */   public static final int SAAS_PACKAGE_DOWNGRADED = -18;
/*     */   public static final int OKTA_CORRECT_ANSWER_REQ = -19;
/*     */   protected int m_iErrorCode;
/*     */   protected String m_ErrString;
/*     */   private boolean m_AccessTokenException = false;
/*     */   
/*     */   public LoginException(int iErrorCode, String s) {
/*  45 */     this.m_iErrorCode = iErrorCode;
/*  46 */     this.m_ErrString = s;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoginException(int iErrorCode, String s, boolean accessTokenException) {
/*  51 */     this.m_iErrorCode = iErrorCode;
/*  52 */     this.m_ErrString = s;
/*  53 */     setAccessTokenException(accessTokenException);
/*     */   }
/*     */ 
/*     */   
/*     */   public LoginException(Throwable cause, int errorCode) {
/*  58 */     super(cause);
/*  59 */     this.m_iErrorCode = errorCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoginException(Throwable cause, int errorCode, boolean accessTokenException) {
/*  64 */     super(cause);
/*  65 */     this.m_iErrorCode = errorCode;
/*  66 */     setAccessTokenException(accessTokenException);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  71 */     return getErrorString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getErrorCode() {
/*  76 */     return this.m_iErrorCode;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getErrorString() {
/*  81 */     switch (this.m_iErrorCode) {
/*     */       
/*     */       case -1:
/*  84 */         return JLbsLocalizer.getCultureResource(1019);
/*     */       case -2:
/*  86 */         return String.valueOf(JLbsLocalizer.getCultureResource(1020)) + this.m_ErrString;
/*     */       case -3:
/*  88 */         return String.valueOf(JLbsLocalizer.getCultureResource(1021)) + this.m_ErrString;
/*     */       case -4:
/*  90 */         return JLbsLocalizer.getCultureResource(1001);
/*     */       case -5:
/*  92 */         return String.valueOf(JLbsLocalizer.getCultureResource(1022)) + this.m_ErrString;
/*     */       case -6:
/*  94 */         return String.valueOf(JLbsLocalizer.getCultureResource(1023)) + this.m_ErrString;
/*     */       case -7:
/*  96 */         return JLbsLocalizer.getCultureResource(1025);
/*     */       case -8:
/*  98 */         return JLbsLocalizer.getCultureResource(1026);
/*     */       case -9:
/* 100 */         return JLbsLocalizer.getCultureResource(1027);
/*     */       case -10:
/* 102 */         return JLbsLocalizer.getCultureResource(1028);
/*     */       case -11:
/* 104 */         return JLbsLocalizer.getCultureResource(1030);
/*     */       case -13:
/* 106 */         return JLbsLocalizer.getCultureResource(1039);
/*     */       case -14:
/* 108 */         return JLbsLocalizer.getCultureResource(1064);
/*     */       case -15:
/* 110 */         return JLbsLocalizer.getCultureResource(1068);
/*     */       case -16:
/* 112 */         return JLbsLocalizer.getCultureResource(1043);
/*     */       case -17:
/* 114 */         return JLbsLocalizer.getCultureResource(1045);
/*     */       case -18:
/* 116 */         return JLbsLocalizer.getCultureResource(1046);
/*     */       case -19:
/* 118 */         return this.m_ErrString;
/*     */     } 
/* 120 */     return 
/* 121 */       String.valueOf(JLbsLocalizer.getCultureResource(1024)) + this.m_iErrorCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 126 */     return getErrorString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAccessTokenException() {
/* 131 */     return this.m_AccessTokenException;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAccessTokenException(boolean accessTokenException) {
/* 136 */     this.m_AccessTokenException = accessTokenException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\LoginException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */