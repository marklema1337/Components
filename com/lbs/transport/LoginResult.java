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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoginResult
/*    */   implements Serializable
/*    */ {
/*    */   public String SessionCode;
/*    */   public Object RemoteCryptoData;
/*    */   public Object RemoteObject;
/*    */   
/*    */   public LoginResult() {}
/*    */   
/*    */   public LoginResult(String sessionCode, Object remCryptoData, Object remData) {
/* 27 */     this.SessionCode = sessionCode;
/* 28 */     this.RemoteCryptoData = remCryptoData;
/* 29 */     this.RemoteObject = remData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\LoginResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */