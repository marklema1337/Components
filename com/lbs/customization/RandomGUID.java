/*     */ package com.lbs.customization;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class RandomGUID
/*     */ {
/*  15 */   public String valueBeforeMD5 = "";
/*  16 */   public String GUID = "";
/*     */ 
/*     */ 
/*     */   
/*  20 */   private String m_UserName = "";
/*     */ 
/*     */ 
/*     */   
/*  24 */   private static SecureRandom ms_SecureRand = new SecureRandom(); static {
/*  25 */     long secureInitializer = ms_SecureRand.nextLong();
/*  26 */     ms_Random = new Random(secureInitializer);
/*     */     
/*     */     try {
/*  29 */       ms_ID = InetAddress.getLocalHost().toString();
/*     */     }
/*  31 */     catch (UnknownHostException unknownHostException) {}
/*     */   }
/*     */   
/*     */   private static Random ms_Random;
/*     */   private static String ms_ID;
/*     */   
/*     */   public RandomGUID() {
/*  38 */     getRandomGUID(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomGUID(boolean secure) {
/*  43 */     getRandomGUID(secure);
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomGUID(String userName) {
/*  48 */     this.m_UserName = userName;
/*  49 */     getRandomGUID(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomGUID(boolean secure, String userName) {
/*  54 */     this.m_UserName = userName;
/*  55 */     getRandomGUID(secure);
/*     */   }
/*     */ 
/*     */   
/*     */   private void getRandomGUID(boolean secure) {
/*  60 */     MessageDigest md5 = null;
/*  61 */     StringBuilder sbValueBeforeMD5 = new StringBuilder();
/*     */     
/*     */     try {
/*  64 */       md5 = MessageDigest.getInstance("MD5");
/*     */     }
/*  66 */     catch (NoSuchAlgorithmException e) {
/*     */       
/*  68 */       if (JLbsConstants.DEBUG) {
/*  69 */         System.out.println("Error: " + e);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/*  74 */       if (md5 != null)
/*     */       {
/*  76 */         long time = System.currentTimeMillis();
/*  77 */         long rand = 0L;
/*     */         
/*  79 */         if (secure) {
/*     */           
/*  81 */           rand = ms_SecureRand.nextLong();
/*     */         }
/*     */         else {
/*     */           
/*  85 */           rand = ms_Random.nextLong();
/*     */         } 
/*     */         
/*  88 */         sbValueBeforeMD5.append(ms_ID);
/*  89 */         sbValueBeforeMD5.append(":");
/*  90 */         sbValueBeforeMD5.append(Long.toString(time));
/*  91 */         sbValueBeforeMD5.append(":");
/*  92 */         sbValueBeforeMD5.append(Long.toString(rand));
/*     */         
/*  94 */         this.valueBeforeMD5 = sbValueBeforeMD5.toString();
/*  95 */         md5.update(this.valueBeforeMD5.getBytes());
/*     */         
/*  97 */         byte[] array = md5.digest();
/*  98 */         StringBuilder sb = new StringBuilder();
/*  99 */         for (int j = 0; j < array.length; j++) {
/*     */           
/* 101 */           int b = array[j] & 0xFF;
/* 102 */           if (b < 16)
/* 103 */             sb.append('0'); 
/* 104 */           sb.append(Integer.toHexString(b));
/*     */         } 
/*     */         
/* 107 */         this.GUID = sb.toString();
/*     */       }
/*     */     
/*     */     }
/* 111 */     catch (Exception e) {
/*     */       
/* 113 */       if (JLbsConstants.DEBUG) {
/* 114 */         System.out.println("Error:" + e);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 120 */     String raw = this.GUID.toUpperCase();
/* 121 */     StringBuilder sb = new StringBuilder();
/* 122 */     sb.append("{");
/* 123 */     if (!JLbsStringUtil.isEmpty(this.m_UserName)) {
/*     */       
/* 125 */       sb.append(this.m_UserName);
/* 126 */       sb.append("-");
/*     */     } 
/* 128 */     sb.append(raw.substring(0, 8));
/* 129 */     sb.append("-");
/* 130 */     sb.append(raw.substring(8, 12));
/* 131 */     sb.append("-");
/* 132 */     sb.append(raw.substring(12, 16));
/* 133 */     sb.append("-");
/* 134 */     sb.append(raw.substring(16, 20));
/* 135 */     sb.append("-");
/* 136 */     sb.append(raw.substring(20));
/* 137 */     sb.append("}");
/*     */     
/* 139 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\customization\RandomGUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */