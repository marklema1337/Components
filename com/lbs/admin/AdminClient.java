/*     */ package com.lbs.admin;
/*     */ 
/*     */ import com.lbs.appobjects.UserBlockedException;
/*     */ import com.lbs.client.LbsAppClient;
/*     */ import com.lbs.client.LbsClientContext;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.interfaces.IVariableHolder;
/*     */ import com.lbs.transport.LoginException;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.StringUtil;
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
/*     */ public class AdminClient
/*     */   extends LbsAppClient
/*     */ {
/*     */   private static final long serialVersionUID = 3414623940200898168L;
/*     */   
/*     */   public AdminClient() {
/*  28 */     JLbsConstants.DESKTOP_MODE = false;
/*  29 */     JLbsConstants.HR = false;
/*  30 */     setAppletVariables("com.lbs.admin.AdminLoginForm", "AdminLogin.jar", "logo", "smart/admin/exit", true, false, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initApplet() {
/*  37 */     super.initApplet();
/*  38 */     if (StringUtil.equals(getParameter("LICENSEPACK"), "true")) {
/*     */       
/*     */       try {
/*     */         
/*  42 */         getContext().setVariable("CLI-LICENCER", getContext().createInstance("com.lbs.admin.AdminLicenceUI"));
/*  43 */         this.m_TerminatePage = "jLicensePack_Exit.jsp";
/*  44 */         prepareTerminateURI();
/*     */       }
/*  46 */       catch (Exception e) {
/*     */         
/*  48 */         LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserInfo getSsoUser(String userName) {
/*     */     try {
/*  58 */       String forAdmin = getClass().getSimpleName().equals("AdminClient") ? "true" : "false";
/*     */ 
/*     */       
/*  61 */       UserInfo uInfo = (UserInfo)this.context.getPublicObject("getSsoUserLoginInfo", new String[] { forAdmin, userName }, true);
/*  62 */       if (uInfo != null)
/*     */       {
/*  64 */         uInfo.variableHolder = (IVariableHolder)this.context;
/*  65 */         uInfo.modeLogin = UserInfo.MODE_LOGIN.ADFS;
/*  66 */         return uInfo;
/*     */       }
/*     */     
/*  69 */     } catch (UserBlockedException e) {
/*     */       
/*  71 */       SwingUtilities.invokeLater((Runnable)new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  81 */     catch (LoginException le) {
/*     */       
/*  83 */       SwingUtilities.invokeLater((Runnable)new Object(this, le));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  93 */     catch (Exception e) {
/*     */       
/*  95 */       e.printStackTrace();
/*     */     } 
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 102 */     preProcess(args, "j-platform Administration");
/* 103 */     AdminClient application = new AdminClient();
/* 104 */     postProcess((LbsAppClient)application, args);
/* 105 */     frame.show();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\admin\AdminClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */