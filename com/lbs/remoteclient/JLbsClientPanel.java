/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.cache.LbsCacheManagerBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.data.objects.CustomBusinessObject;
/*     */ import com.lbs.data.objects.ILbsCustBOValidatorProvider;
/*     */ import com.lbs.data.objects.ObjectValueManager;
/*     */ import com.lbs.http.cookie.JLbsCookieHive;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.validation.CustomBOValidationService;
/*     */ import java.applet.Applet;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsClientPanel
/*     */   extends JPanel
/*     */   implements IClientContextConsumer, IClientFocusInit
/*     */ {
/*     */   static {
/*  31 */     ObjectValueManager.touch();
/*     */   }
/*     */   
/*  34 */   protected IClientContext m_Context = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContext(IClientContext context) {
/*  41 */     this.m_Context = context;
/*  42 */     LbsCacheManagerBase.setClientContext(context);
/*  43 */     JLbsComponentHelper.setMainForm(this);
/*  44 */     CustomBusinessObject.setValidatorProvider((ILbsCustBOValidatorProvider)new CustomBOValidationService((IApplicationContext)this.m_Context));
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getContextVariable(Object key) {
/*  49 */     return this.m_Context.getVariable(key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(JComponent comp, int x, int y, int width, int height) {
/*  54 */     add(comp);
/*  55 */     comp.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFocus() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void addToComponent(JComponent parent, JComponent comp, int x, int y, int width, int height) {
/*  65 */     parent.add(comp);
/*  66 */     comp.setBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getComponentByTag(int tag) {
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getMainWorkPane() {
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doExit(JApplet baseApplet) {
/*  81 */     if (JLbsConstants.checkAppCloud()) {
/*     */       
/*  83 */       String[] cookies = JLbsCookieHive.getCookies(this.m_Context.getRootUri());
/*  84 */       String accessToken = null;
/*  85 */       if (cookies != null) {
/*     */         
/*  87 */         for (int i = 0; i < cookies.length; i++) {
/*     */           
/*  89 */           String[] parts = cookies[i].split("=");
/*  90 */           if (parts[0].equals("oauth_token")) {
/*     */             
/*  92 */             accessToken = parts[1];
/*     */             break;
/*     */           } 
/*     */         } 
/*  96 */         if (accessToken != null && accessToken.length() > 0 && JLbsConstants.checkAppCloud())
/*  97 */           this.m_Context.openURL(String.valueOf(JLbsConstants.JUGNU_PORTAL) + "/logout/" + accessToken, null); 
/*     */       } 
/*     */     } 
/* 100 */     unbindUnloadJavascriptEvent(baseApplet);
/* 101 */     this.m_Context.terminateSession(true);
/* 102 */     this.m_Context.logout();
/* 103 */     this.m_Context.terminateApplication();
/* 104 */     this.m_Context = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void unbindUnloadJavascriptEvent(JApplet baseApplet) {
/* 109 */     if (baseApplet != null)
/*     */       
/*     */       try {
/*     */         
/* 113 */         Class<?> c = Class.forName("netscape.javascript.JSObject");
/* 114 */         Method m = c.getMethod("getWindow", new Class[] { Applet.class });
/* 115 */         Object window = m.invoke(null, new Object[] { baseApplet });
/* 116 */         if (window != null)
/*     */         {
/* 118 */           m = c.getMethod("eval", new Class[] { String.class });
/* 119 */           m.invoke(window, new Object[] { " $(window).unbind('beforeunload');" });
/*     */         }
/*     */       
/* 122 */       } catch (Exception e) {
/*     */         
/* 124 */         if (JLbsConstants.DEBUG)
/* 125 */           e.printStackTrace(); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsClientPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */