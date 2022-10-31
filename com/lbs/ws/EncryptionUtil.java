/*    */ package com.lbs.ws;
/*    */ 
/*    */ import com.lbs.services.util.VolatileCipher;
/*    */ import com.lbs.util.JLbsStringUtil;
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
/*    */ public class EncryptionUtil
/*    */ {
/*    */   private static final String JPLATFORM_KEY = "JPLATFORM";
/*    */   
/*    */   public static String encrypt(String guid, byte[] securityChecker) {
/* 22 */     return VolatileCipher.EncryptString("JPLATFORM", guid);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String decrypt(String data) throws Exception {
/* 27 */     if (data != null) {
/*    */       
/* 29 */       String[] dataParts = data.split(";");
/*    */       
/* 31 */       String decryptedString = VolatileCipher.CheckAndGetEncryptedString("JPLATFORM", dataParts[0]);
/* 32 */       if (!JLbsStringUtil.isEmpty(decryptedString)) {
/*    */         
/* 34 */         if (dataParts.length == 2)
/* 35 */           decryptedString = String.valueOf(decryptedString) + ";" + dataParts[1]; 
/* 36 */         return decryptedString;
/*    */       } 
/*    */     } 
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\ws\EncryptionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */