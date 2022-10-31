/*    */ package com.lbs.client;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DesktopAppClient
/*    */   extends LbsAppClient
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public DesktopAppClient() {
/* 12 */     setAppletVariables("com.lbs.unity.main.UnityLoginForm", "UnityLogin.jar", "logo", (String)null, true, true, true, true);
/*    */   }
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
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 43 */     preProcess(args, "j-platform");
/* 44 */     frame.setExtendedState(6);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     frame.setUndecorated(true);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     DesktopAppClient application = new DesktopAppClient();
/*    */     
/* 61 */     postProcess(application, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\DesktopAppClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */