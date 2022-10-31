/*    */ package com.lbs.crypto;
/*    */ 
/*    */ import com.lbs.channel.ICryptor;
/*    */ import com.lbs.util.JLbsTransportMode;
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
/*    */ public final class JLbsSymCryptoFactory
/*    */ {
/*    */   public static ICryptor createCryptor(Object param) {
/* 21 */     if (JLbsTransportMode.ENCRYPTION_ON) {
/* 22 */       return new AESCryptor(param);
/*    */     }
/* 24 */     return new UUCryptor();
/*    */   }
/*    */   
/*    */   public static ICryptor createAESCryptor(Object param) {
/* 28 */     return new AESCryptor(param);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsSymCryptoFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */