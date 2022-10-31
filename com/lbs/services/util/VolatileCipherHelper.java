/*    */ package com.lbs.services.util;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class VolatileCipherHelper
/*    */ {
/*  8 */   private static Random rand = new Random();
/*    */ 
/*    */   
/*    */   private static String MakeWord(String appKey) throws Exception {
/* 12 */     byte[] staticPart = { 76, 48, 71, 48, 46, 67, 46, 82, 46, 77, 46, 80, 52, 36, 36, 46 };
/* 13 */     String ret = new String(staticPart, "UTF-8");
/* 14 */     ret = "L0G0." + appKey + ".P4$$.";
/* 15 */     ret = String.valueOf(ret) + Calendar.getInstance().get(1);
/* 16 */     ret = String.valueOf(ret) + ".-";
/*    */     
/* 18 */     ret = String.valueOf(ret) + (Calendar.getInstance().get(2) + 1);
/* 19 */     int i = 256;
/* 20 */     i = i / 16 + 5;
/* 21 */     i = (i + 75) / 8;
/* 22 */     i *= 12;
/* 23 */     ret = String.valueOf(ret) + ".-" + i;
/* 24 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String CheckWord(String appKey, String word) throws Exception {
/* 29 */     String privateKey = "";
/* 30 */     String controledKey = null;
/* 31 */     for (int i = -4; i <= 4; ) {
/*    */       
/* 33 */       controledKey = InitiateControlString(appKey, i);
/* 34 */       if (!word.startsWith(controledKey)) {
/*    */         i++; continue;
/* 36 */       }  String[] parts = word.split("\\|");
/* 37 */       privateKey = parts[parts.length - 1];
/* 38 */       return privateKey;
/*    */     } 
/* 40 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String MakeDate(int minuteDifference) {
/* 46 */     return Calendar.getInstance().get(1) + 
/*    */       
/* 48 */       Calendar.getInstance().get(2) + 1 + 
/* 49 */       Calendar.getInstance().get(5) + 
/*    */ 
/*    */       
/* 52 */       Calendar.getInstance().get(11) + 
/* 53 */       Calendar.getInstance().get(12) + 
/* 54 */       minuteDifference;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String RandomString() {
/* 60 */     int size = 16;
/* 61 */     String randomSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
/* 62 */     String retVal = "";
/* 63 */     for (int i = 0; i < size; i++) {
/*    */       
/* 65 */       int j = rand.nextInt(randomSet.length() - 1);
/* 66 */       if (j == 0) j++; 
/* 67 */       retVal = String.valueOf(retVal) + randomSet.charAt((j <= randomSet.length()) ? j : (j - 1));
/*    */     } 
/* 69 */     return retVal;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String InitiateString(String appKey, String text) throws Exception {
/* 74 */     return String.valueOf(MakeWord(appKey)) + "|" + MakeDate(0) + "|" + ((text == null) ? RandomString() : text);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String InitiateControlString(String appKey, int minuteDifference) throws Exception {
/* 79 */     return String.valueOf(MakeWord(appKey)) + "|" + MakeDate(minuteDifference);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\service\\util\VolatileCipherHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */