/*    */ package com.lbs.crypto;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import javax.crypto.spec.SecretKeySpec;
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
/*    */ public class JLbsCryptoUtil
/*    */ {
/*    */   public static byte[] createMD5Digest(byte[] data) {
/* 26 */     if (data == null || data.length == 0) {
/* 27 */       return null;
/*    */     }
/*    */     try {
/* 30 */       MessageDigest md5 = MessageDigest.getInstance("MD5");
/* 31 */       byte[] md5Data = md5.digest(data);
/* 32 */       md5 = null;
/* 33 */       return md5Data;
/*    */     }
/* 35 */     catch (Exception exception) {
/*    */ 
/*    */       
/* 38 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String createHashString(byte[] data) {
/* 47 */     byte[] hashData = createMD5Digest(data);
/* 48 */     if (hashData == null) {
/* 49 */       return null;
/*    */     }
/* 51 */     StringBuilder hash = new StringBuilder(new String(JLbsBase64.encode(hashData)));
/* 52 */     int iLen = hash.length();
/* 53 */     while (iLen > 0 && hash.charAt(iLen - 1) == '=') {
/*    */       
/* 55 */       hash = hash.delete(iLen - 1, iLen);
/* 56 */       iLen--;
/*    */     } 
/*    */     
/* 59 */     for (int i = hash.length() - 1; i >= 0; i--) {
/* 60 */       if (hash.charAt(i) == '/')
/* 61 */         hash.setCharAt(i, '-'); 
/* 62 */     }  return hash.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 67 */     String str = "Deneme";
/*    */     
/*    */     try {
/* 70 */       byte[] content = encryptAES(str.getBytes(), "BUqZ9zrNTNm6bXns".getBytes(), new byte[] { 24, 51, 32, 53, 54, 55, 64, 95, 
/* 71 */             121, 53, 71, 60, 38, -102, 81, 11 });
/* 72 */       System.out.println(new String(content));
/*    */     }
/* 74 */     catch (Exception e) {
/*    */       
/* 76 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static byte[] encryptAES(byte[] contentToEncrypt, byte[] encryptionKey, byte[] iv) throws Exception {
/* 82 */     Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 83 */     SecretKeySpec k = new SecretKeySpec(encryptionKey, "AES");
/* 84 */     c.init(1, k, new IvParameterSpec(iv));
/* 85 */     return c.doFinal(contentToEncrypt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsCryptoUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */