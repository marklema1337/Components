/*     */ package com.lbs.rmi;
/*     */ 
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsHttpInvoker
/*     */ {
/*  23 */   public static String ENCRYPTION_ALGORITHM = "AES";
/*  24 */   public static String ENCRYPTION_ALGORITHM_WITH_PADDING = "AES/ECB/ISO10126Padding";
/*  25 */   public static int ENCRYPTION_STRENGTH = 128;
/*     */   public static boolean CACHE_CIPHERS = false;
/*  27 */   public static int STREAM_BUFFER_SIZE = 4096;
/*     */ 
/*     */   
/*     */   public static final String HTTP_HEADER_CONTENT_MODE = "Content-Mode";
/*     */ 
/*     */   
/*     */   public static final String HTTP_HEADER_SESSION_ID = "Session-Id";
/*     */   
/*     */   private static KeyGenerator keyGenerator;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  40 */       keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
/*  41 */       keyGenerator.init(ENCRYPTION_STRENGTH);
/*     */     
/*     */     }
/*  44 */     catch (NoSuchAlgorithmException e) {
/*     */ 
/*     */       
/*  47 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getRandomKey() {
/*  58 */     SecretKey skey = keyGenerator.generateKey();
/*  59 */     byte[] raw = skey.getEncoded();
/*  60 */     return raw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Cipher getDecryptionCipher(Cipher cipher, byte[] key) {
/*  71 */     if (CACHE_CIPHERS && cipher != null) {
/*  72 */       return cipher;
/*     */     }
/*     */     
/*     */     try {
/*  76 */       SecretKeySpec keySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
/*  77 */       Cipher newCipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_WITH_PADDING);
/*  78 */       newCipher.init(2, keySpec);
/*     */       
/*  80 */       return newCipher;
/*     */     }
/*  82 */     catch (Exception e) {
/*     */       
/*  84 */       e.printStackTrace();
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Cipher getEncryptionCipher(Cipher cipher, byte[] key) {
/*  94 */     if (CACHE_CIPHERS && cipher != null) {
/*  95 */       return cipher;
/*     */     }
/*     */     
/*     */     try {
/*  99 */       SecretKeySpec keySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
/* 100 */       Cipher newCipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_WITH_PADDING);
/* 101 */       newCipher.init(1, keySpec);
/*     */       
/* 103 */       return newCipher;
/*     */     }
/* 105 */     catch (Exception e) {
/*     */       
/* 107 */       e.printStackTrace();
/* 108 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\JLbsHttpInvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */