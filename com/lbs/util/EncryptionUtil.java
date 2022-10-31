/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Arrays;
/*    */ import java.util.Calendar;
/*    */ import java.util.TimeZone;
/*    */ import java.util.UUID;
/*    */ import javax.crypto.BadPaddingException;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.IllegalBlockSizeException;
/*    */ import javax.crypto.NoSuchPaddingException;
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
/*    */ public class EncryptionUtil
/*    */ {
/*    */   private static byte[] key;
/*    */   public static final int ENCRYPT_MODE = 1;
/*    */   public static final int DECRYPT_MODE = 2;
/*    */   
/*    */   static {
/*    */     try {
/* 41 */       key = "!!!!~~~~~aaaaaa~~~~~123456789!!!!".getBytes("Cp1254");
/*    */     
/*    */     }
/* 44 */     catch (Exception t) {
/*    */       
/* 46 */       key = "!!!!~~~~~aaaaaa~~~~~123456789!!!!".getBytes();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static String encrypt(String guid) {
/* 52 */     String randString = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
/* 53 */     DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
/* 54 */     dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
/* 55 */     Calendar cal = Calendar.getInstance();
/* 56 */     String time = dateFormat.format(cal.getTime());
/*    */     
/* 58 */     String GUID = UUID.randomUUID().toString().replace("-", "");
/*    */     
/* 60 */     String evalKey = String.valueOf(time) + "~" + GUID + "~" + randString;
/* 61 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static byte[] doAESOperation(String key, byte[] input, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
/* 67 */     Cipher c = Cipher.getInstance("AES");
/* 68 */     MessageDigest sha = MessageDigest.getInstance("SHA-1");
/* 69 */     byte[] key1 = key.getBytes("UTF-8");
/* 70 */     key1 = sha.digest(key1);
/* 71 */     key1 = Arrays.copyOf(key1, 16);
/* 72 */     SecretKeySpec k = new SecretKeySpec(key1, "AES");
/* 73 */     c.init(mode, k);
/* 74 */     byte[] data = c.doFinal(input);
/* 75 */     return data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\EncryptionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */