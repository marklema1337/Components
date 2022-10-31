/*    */ package com.lbs.crypto;
/*    */ 
/*    */ import com.lbs.channel.ICryptor;
/*    */ import com.lbs.transport.CryptorException;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import java.util.Base64;
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
/*    */ public class UUCryptor
/*    */   implements ICryptor
/*    */ {
/*    */   public boolean setParam(int iParamStyle, Object param) {
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getParam(int iParamStyle) throws CryptorException {
/* 33 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] encrypt(byte[] data, int index, int length) throws CryptorException {
/*    */     try {
/* 40 */       Base64.Encoder encoder = Base64.getEncoder();
/* 41 */       return encoder.encodeToString(data).getBytes();
/*    */     }
/* 43 */     catch (Exception e) {
/*    */       
/* 45 */       throw new CryptorException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] decrypt(byte[] data, int index, int length) throws CryptorException {
/*    */     try {
/* 53 */       Base64.Decoder decoder = Base64.getDecoder();
/* 54 */       return decoder.decode(JLbsStringUtil.getString(data));
/*    */     }
/* 56 */     catch (Exception e) {
/*    */       
/* 58 */       throw new CryptorException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\UUCryptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */