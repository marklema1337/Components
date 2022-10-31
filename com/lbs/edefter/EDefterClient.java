/*    */ package com.lbs.edefter;
/*    */ 
/*    */ import com.lbs.appobjects.UserBlockedException;
/*    */ import com.lbs.client.LbsAppClient;
/*    */ import com.lbs.client.LbsClientContext;
/*    */ import com.lbs.interfaces.IVariableHolder;
/*    */ import com.lbs.transport.LoginException;
/*    */ import com.lbs.transport.UserInfo;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EDefterClient
/*    */   extends LbsAppClient
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public EDefterClient() {
/* 29 */     setAppletVariables("com.lbs.edefter.EDefterLoginForm", "EDefterLogin.jar", "logo", "smart/e-defter/exit", true, true, true, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected UserInfo getSsoUser(String userName) {
/*    */     try {
/* 38 */       String forAdmin = getClass().getSimpleName().equals("AdminClient") ? "true" : "false";
/*    */ 
/*    */       
/* 41 */       UserInfo uInfo = (UserInfo)this.context.getPublicObject("getSsoUserLoginInfo", new String[] { forAdmin, userName }, true);
/* 42 */       if (uInfo != null)
/*    */       {
/* 44 */         uInfo.variableHolder = (IVariableHolder)this.context;
/* 45 */         uInfo.modeLogin = UserInfo.MODE_LOGIN.ADFS;
/* 46 */         return uInfo;
/*    */       }
/*    */     
/* 49 */     } catch (UserBlockedException e) {
/*    */       
/* 51 */       SwingUtilities.invokeLater((Runnable)new Object(this));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 61 */     catch (LoginException le) {
/*    */       
/* 63 */       SwingUtilities.invokeLater((Runnable)new Object(this, le));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 73 */     catch (Exception e) {
/*    */       
/* 75 */       e.printStackTrace();
/*    */     } 
/* 77 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 82 */     preProcess(args, "E-Defter");
/* 83 */     EDefterClient application = new EDefterClient();
/* 84 */     postProcess((LbsAppClient)application, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\edefter\EDefterClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */