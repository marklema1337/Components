/*    */ package com.lbs.transport;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class LoginInfoTiny
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 18 */   public String userName = "SYSTEM";
/* 19 */   public String diffUserName = null;
/* 20 */   public int userNum = 0;
/* 21 */   public int companyID = 0;
/*    */   public long[] clientIpAdresses;
/* 23 */   public String clientMachineName = "";
/*    */ 
/*    */ 
/*    */   
/*    */   public LoginInfoTiny() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public LoginInfoTiny(LoginInfo info, int companyID) {
/* 32 */     UserInfo userInfo = info.UserInfo;
/* 33 */     this.userName = userInfo.Name;
/* 34 */     this.diffUserName = userInfo.loginAsDifferentUser ? 
/* 35 */       userInfo.diffUserName : 
/* 36 */       null;
/* 37 */     this.userNum = userInfo.UserNumber;
/* 38 */     this.clientIpAdresses = userInfo.clientIpAddresses;
/* 39 */     this.clientMachineName = userInfo.clientMachineName;
/* 40 */     this.companyID = companyID;
/*    */   }
/*    */ 
/*    */   
/*    */   public LoginInfoTiny(UserInfo userInfo, int companyID) {
/* 45 */     this.userName = userInfo.Name;
/* 46 */     this.diffUserName = userInfo.loginAsDifferentUser ? 
/* 47 */       userInfo.diffUserName : 
/* 48 */       null;
/* 49 */     this.userNum = userInfo.UserNumber;
/* 50 */     this.clientIpAdresses = userInfo.clientIpAddresses;
/* 51 */     this.clientMachineName = userInfo.clientMachineName;
/* 52 */     this.companyID = companyID;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\LoginInfoTiny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */