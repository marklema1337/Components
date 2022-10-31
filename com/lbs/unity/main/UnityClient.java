/*     */ package com.lbs.unity.main;
/*     */ 
/*     */ import com.lbs.appobjects.LbsApplicationConfig;
/*     */ import com.lbs.appobjects.UserBlockedException;
/*     */ import com.lbs.client.LbsAppClient;
/*     */ import com.lbs.client.LbsClientContext;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.interfaces.IVariableHolder;
/*     */ import com.lbs.transport.LoginException;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsFrame;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.SwingUtilities;
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
/*     */ public class UnityClient
/*     */   extends LbsAppClient
/*     */ {
/*     */   public UnityClient() {
/*  34 */     setAppletVariables("com.lbs.unity.main.UnityLoginForm", "UnityLogin.jar", "logo", "smart/login/exit", true, true, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserInfo lookForLDAP() {
/*     */     try {
/*  43 */       String[] osUserData = getOSUserData();
/*  44 */       UserInfo userInfo = (UserInfo)this.context.getPublicObject("getLdapUserLoginInfo", osUserData, true);
/*  45 */       if (userInfo != null)
/*     */       {
/*  47 */         userInfo.variableHolder = (IVariableHolder)this.context;
/*  48 */         userInfo.modeLogin = UserInfo.MODE_LOGIN.LDAP;
/*  49 */         return userInfo;
/*     */       }
/*     */     
/*  52 */     } catch (UserBlockedException e) {
/*     */       
/*  54 */       SwingUtilities.invokeLater((Runnable)new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  64 */     catch (LoginException le) {
/*     */       
/*  66 */       SwingUtilities.invokeLater((Runnable)new Object(this, le));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  76 */     catch (Exception e) {
/*     */       
/*  78 */       e.printStackTrace();
/*     */     } 
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserInfo getSsoUser(String userName) {
/*     */     try {
/*  88 */       String forAdmin = getClass().getSimpleName().equals("AdminClient") ? "true" : "false";
/*     */ 
/*     */       
/*  91 */       UserInfo uInfo = (UserInfo)this.context.getPublicObject("getSsoUserLoginInfo", new String[] { forAdmin, userName }, true);
/*  92 */       if (uInfo != null)
/*     */       {
/*  94 */         uInfo.variableHolder = (IVariableHolder)this.context;
/*  95 */         uInfo.modeLogin = UserInfo.MODE_LOGIN.ADFS;
/*  96 */         return uInfo;
/*     */       }
/*     */     
/*  99 */     } catch (UserBlockedException e) {
/*     */       
/* 101 */       SwingUtilities.invokeLater((Runnable)new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 111 */     catch (LoginException le) {
/*     */       
/* 113 */       SwingUtilities.invokeLater((Runnable)new Object(this, le));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 123 */     catch (Exception e) {
/*     */       
/* 125 */       e.printStackTrace();
/*     */     } 
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserInfo getOauthUser(String accessToken) {
/*     */     try {
/* 135 */       UserInfo uInfo = (UserInfo)this.context.getPublicObject("getAccessTokenUserInfo", new String[] { accessToken }, true);
/* 136 */       if (uInfo != null)
/*     */       {
/* 138 */         uInfo.variableHolder = (IVariableHolder)this.context;
/* 139 */         uInfo.modeLogin = UserInfo.MODE_LOGIN.OAUTHTOKEN;
/* 140 */         return uInfo;
/*     */       }
/*     */     
/* 143 */     } catch (UserBlockedException e) {
/*     */       
/* 145 */       SwingUtilities.invokeLater((Runnable)new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 155 */     catch (LoginException le) {
/*     */       
/* 157 */       SwingUtilities.invokeLater((Runnable)new Object(this, le));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 167 */     catch (Exception e) {
/*     */       
/* 169 */       e.printStackTrace();
/*     */     } 
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidAccessToken(String accessToken) {
/*     */     try {
/* 179 */       return ((Boolean)this.context.getPublicObject("isValidAccessToken", new String[] { accessToken }, true)).booleanValue();
/*     */     }
/* 181 */     catch (Exception e) {
/*     */       
/* 183 */       e.printStackTrace();
/*     */       
/* 185 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 190 */     preProcess(args, "j-platform");
/* 191 */     UnityClient application = new UnityClient();
/* 192 */     postProcess((LbsAppClient)application, args);
/* 193 */     boolean desktop = false;
/*     */     
/*     */     try {
/*     */       Object result;
/* 197 */       if ((result = application.getContext().getPublicObject("IsDesktop", null, true)) != null)
/* 198 */         desktop = ((Boolean)result).booleanValue(); 
/* 199 */       if (desktop) {
/*     */         
/* 201 */         Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
/* 202 */         frame.setLocation(rec.x, rec.y);
/* 203 */         frame.setSize(rec.width, rec.height);
/*     */         
/* 205 */         frame.setUndecorated(true);
/* 206 */         frame.setExtendedState(0);
/*     */         
/* 208 */         frame.setIconImage(JLbsControlHelper.getImageIcon(JLbsFrame.class, "LbsApplicationDesktop3.png").getImage());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 213 */       if ("0601".equalsIgnoreCase(JLbsConstants.getProductType())) {
/* 214 */         frame.setIconImage(JLbsControlHelper.getImageIcon(JLbsFrame.class, "LbsApplicationDesktopSaasErp.png").getImage());
/*     */       }
/* 216 */       if (LbsApplicationConfig.getAppletParameter("INI_TITLE", "").equalsIgnoreCase("J-HR")) {
/* 217 */         frame.setIconImage(JLbsControlHelper.getImageIcon(JLbsFrame.class, "jHR_favicon.png").getImage());
/*     */       }
/* 219 */     } catch (Exception e) {
/*     */       
/* 221 */       e.printStackTrace();
/*     */     } 
/* 223 */     frame.show();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\unity\main\UnityClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */