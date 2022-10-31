/*    */ package com.lbs.crypto;
/*    */ 
/*    */ import com.lbs.transport.CryptorException;
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
/*    */ public class DESedeCryptor
/*    */   extends SymetricCryptorBase
/*    */ {
/* 24 */   protected JLbsEnigma m_Cryptor = new JLbsEnigma();
/*    */ 
/*    */ 
/*    */   
/*    */   public DESedeCryptor() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public DESedeCryptor(Object privateKey) {
/* 33 */     this();
/* 34 */     setParam(0, privateKey);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] encrypt(byte[] data, int index, int length) throws CryptorException {
/*    */     try {
/* 44 */       return this.m_Cryptor.Encrypt(data, index, length);
/*    */     }
/* 46 */     catch (Exception e) {
/*    */       
/* 48 */       throw new CryptorException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] decrypt(byte[] data, int index, int length) throws CryptorException {
/*    */     try {
/* 59 */       return this.m_Cryptor.Decrypt(data, index, length);
/*    */     }
/* 61 */     catch (Exception e) {
/*    */       
/* 63 */       throw new CryptorException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setPassword(byte[] pass) {
/*    */     try {
/* 71 */       this.m_Cryptor.setPassword(pass);
/*    */     }
/* 73 */     catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setPassword(String pass) {
/*    */     try {
/* 82 */       this.m_Cryptor.setPassword(pass);
/*    */     }
/* 84 */     catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\DESedeCryptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */