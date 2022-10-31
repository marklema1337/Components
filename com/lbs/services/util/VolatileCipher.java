/*     */ package com.lbs.services.util;
/*     */ 
/*     */ import com.lbs.util.Base64;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VolatileCipher
/*     */ {
/*     */   private static final String ENCRYPTION_INPUT_KEY = "LaO1vG19OcE0GmeA";
/*  13 */   private static byte[] key = new byte[] { -113, 61, 32, 4, -87, 72, 48, 11, 110, 92, 62, 80, 82, 54, 21, 19 };
/*  14 */   private static byte[] vector = new byte[] { -110, 64, -65, 111, 23, 3, 113, 119, -25, 121, -4, 112, 79, 32, 114, -100 };
/*     */ 
/*     */ 
/*     */   
/*     */   public static String EncryptString(String appKey) {
/*     */     try {
/*  20 */       String text = "LaO1vG19OcE0GmeA";
/*  21 */       VolatileCipher LCR = new VolatileCipher();
/*  22 */       String encString = VolatileCipherHelper.InitiateString(appKey, text);
/*  23 */       encString = Base64.encodeBytes(encString.getBytes(), 8);
/*  24 */       return LCR.Encrypt(encString);
/*     */     }
/*  26 */     catch (Exception e) {
/*  27 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String EncryptString(String appKey, String text) {
/*     */     try {
/*  35 */       if (text == null)
/*  36 */         text = "LaO1vG19OcE0GmeA"; 
/*  37 */       VolatileCipher LCR = new VolatileCipher();
/*  38 */       String encString = VolatileCipherHelper.InitiateString(appKey, text);
/*  39 */       encString = Base64.encodeBytes(encString.getBytes(), 8);
/*  40 */       return LCR.Encrypt(encString);
/*     */     }
/*  42 */     catch (Exception e) {
/*     */       
/*  44 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String DecryptString(String encryptedString) throws Exception {
/*  50 */     VolatileCipher LCR = new VolatileCipher();
/*  51 */     String DecryptedKeyBase64 = LCR.Decrypt(encryptedString);
/*  52 */     return new String(Base64.decode(DecryptedKeyBase64));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String CheckAndGetEncryptedString(String appKey, String encryptedString) throws Exception {
/*  57 */     VolatileCipher LCR = new VolatileCipher();
/*  58 */     String DecryptedKeyBase64 = LCR.Decrypt(encryptedString);
/*  59 */     String value = new String(Base64.decode(DecryptedKeyBase64));
/*  60 */     return VolatileCipherHelper.CheckWord(appKey, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String CheckEncryptedStringValid(String appKey, String encryptedString) throws Exception {
/*  65 */     VolatileCipher LCR = new VolatileCipher();
/*  66 */     String DecryptedKeyBase64 = LCR.Decrypt(encryptedString);
/*  67 */     String value = new String(Base64.decode(DecryptedKeyBase64));
/*  68 */     return VolatileCipherHelper.CheckWord(appKey, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Cipher GetEncryptor() throws Exception {
/*  74 */     Cipher rm = Cipher.getInstance("AES/CBC/PKCS5Padding");
/*  75 */     rm.init(1, new SecretKeySpec(key, "AES"), new IvParameterSpec(vector));
/*  76 */     return rm;
/*     */   }
/*     */ 
/*     */   
/*     */   public Cipher GetDecryptor() throws Exception {
/*  81 */     Cipher rm = Cipher.getInstance("AES/CBC/PKCS5Padding");
/*  82 */     rm.init(2, new SecretKeySpec(key, "AES"), new IvParameterSpec(vector));
/*  83 */     return rm;
/*     */   }
/*     */ 
/*     */   
/*     */   public String Encrypt(String unencrypted) throws Exception {
/*  88 */     return Base64.encodeBytes(Encrypt(unencrypted.getBytes()), 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public String Decrypt(String encrypted) throws Exception {
/*  93 */     return new String(Decrypt(Base64.decode(encrypted)));
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] Encrypt(byte[] buffer) throws Exception {
/*  98 */     Cipher cp = GetEncryptor();
/*  99 */     return cp.doFinal(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] Decrypt(byte[] buffer) throws Exception {
/* 104 */     Cipher cp = GetDecryptor();
/* 105 */     return cp.doFinal(buffer);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\service\\util\VolatileCipher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */