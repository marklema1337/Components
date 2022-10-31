/*     */ package com.lbs.unity.main;
/*     */ 
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsFrame;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JApplet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnityClientReportDesigner
/*     */   extends UnityClient
/*     */ {
/*     */   private static UnityClientReportDesigner application;
/*     */   
/*     */   public UnityClientReportDesigner() {
/*  21 */     setAppletVariables("com.lbs.unity.main.UnityLoginFormReportDesigner", "UnityLogin.jar", "logo", "smart/login/exit", true, true, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initApplet() {
/*  28 */     application = this;
/*  29 */     super.initApplet();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  34 */     preProcess(args, "j-platform");
/*  35 */     application = new UnityClientReportDesigner();
/*  36 */     postProcess(application, args);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  42 */       Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
/*  43 */       frame.setLocation(rec.x, rec.y);
/*  44 */       frame.setSize(rec.width, rec.height);
/*     */       
/*  46 */       frame.setUndecorated(true);
/*  47 */       frame.setExtendedState(0);
/*  48 */       frame.setIconImage(JLbsControlHelper.getImageIcon(JLbsFrame.class, "LbsApplicationDesktop3.png").getImage());
/*     */     
/*     */     }
/*  51 */     catch (Exception e) {
/*     */       
/*  53 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/*  57 */     frame.show();
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsFrame getFrame() {
/*  62 */     return frame;
/*     */   }
/*     */ 
/*     */   
/*     */   public static UnityClientReportDesigner getApplication() {
/*  67 */     return application;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParameter(String param) {
/*     */     String value;
/*  73 */     JApplet applet = getBaseApplet();
/*     */     
/*  75 */     if (applet != null) {
/*     */       
/*  77 */       value = super.getParameter(param);
/*  78 */       if (value == null)
/*     */       {
/*  80 */         return applet.getParameter(param);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  85 */       value = System.getProperty(param);
/*     */     } 
/*  87 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReportName() {
/*  92 */     return getParameter("REPORT_NAME");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReportLanguage() {
/*  97 */     return getParameter("REPORT_LANG");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLRef() {
/* 102 */     String val = getParameter("CUSTOM_REPORT_LREF");
/* 103 */     return Integer.parseInt(val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canClose() {
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserInfo getOauthUser(String accessToken) {
/* 115 */     String lang = getParameter("LANGUAGE");
/* 116 */     UserInfo result = super.getOauthUser(accessToken);
/* 117 */     if (lang != null && result != null)
/*     */     {
/* 119 */       result.selectedLanguage = lang;
/*     */     }
/* 121 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\unity\main\UnityClientReportDesigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */