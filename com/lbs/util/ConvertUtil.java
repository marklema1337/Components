/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.math.BigDecimal;
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
/*     */ public abstract class ConvertUtil
/*     */ {
/*     */   public static int strToInt(String s) {
/*     */     try {
/*  20 */       return Integer.parseInt(s);
/*     */     }
/*  22 */     catch (Exception e) {
/*     */       
/*  24 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String intToStr(int i) {
/*     */     try {
/*  32 */       return i;
/*     */     }
/*  34 */     catch (Exception e) {
/*     */       
/*  36 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String intToStr(int number, int len) {
/*  42 */     String str = intToStr(number);
/*  43 */     str = StringUtilExtra.padLeft(str, '0', len);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String intToStrGnrl(int number, int len) {
/*  53 */     String str = intToStr(number);
/*     */     
/*  55 */     while (str.length() < len) {
/*  56 */       str = "0" + str;
/*     */     }
/*  58 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String firmIntToStr(int number) {
/*  63 */     int length = 3;
/*  64 */     if (JLbsConstants.checkAppCloud())
/*     */     {
/*  66 */       length = 5;
/*     */     }
/*  68 */     String str = intToStr(number);
/*  69 */     str = StringUtilExtra.padLeft(str, '0', length);
/*     */     
/*  71 */     while (str.length() < length)
/*  72 */       str = "0" + str; 
/*  73 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String firmPadStr(int number) {
/*  78 */     int length = 3;
/*  79 */     if (JLbsConstants.checkAppCloud())
/*     */     {
/*  81 */       length = 5;
/*     */     }
/*  83 */     String str = intToStr(number);
/*     */     
/*  85 */     while (str.length() < length) {
/*  86 */       str = "0" + str;
/*     */     }
/*  88 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padStr(int number, int len) {
/*  93 */     String str = intToStr(number);
/*     */     
/*  95 */     while (str.length() < len) {
/*  96 */       str = "0" + str;
/*     */     }
/*  98 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal stripBigDecimal(BigDecimal value, int minDecimals) {
/* 103 */     String numbers = "+-1234567890";
/*     */     
/* 105 */     if (value != null) {
/*     */       
/* 107 */       String str = value.toString();
/* 108 */       if (str != null && str.length() != 0) {
/*     */         
/* 110 */         int cnt = 0;
/* 111 */         boolean sepFnd = false;
/* 112 */         int decCnt = 0;
/* 113 */         boolean trailingZeros = true;
/*     */         
/* 115 */         for (int i = str.length() - 1; i >= 0; i--) {
/*     */           
/* 117 */           String num = str.substring(i, i + 1);
/* 118 */           if ("+-1234567890".indexOf(num) == -1)
/* 119 */             sepFnd = true; 
/* 120 */           if (!sepFnd) {
/* 121 */             decCnt++;
/*     */           }
/* 123 */           if (!sepFnd && trailingZeros && num.compareTo("0") != 0) {
/* 124 */             trailingZeros = false;
/*     */           }
/* 126 */           if (trailingZeros) {
/* 127 */             cnt++;
/*     */           }
/*     */         } 
/* 130 */         if (sepFnd) {
/*     */           
/* 132 */           int newScale = decCnt - cnt;
/* 133 */           if (newScale > minDecimals && minDecimals != -1)
/* 134 */             newScale = minDecimals; 
/* 135 */           if (minDecimals != -1)
/* 136 */             newScale = minDecimals; 
/* 137 */           if (newScale > 0)
/* 138 */             return value.setScale(newScale, 4); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 142 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BigDecimal stripBigDecimal(BigDecimal value) {
/* 147 */     return stripBigDecimal(value, -1);
/*     */   }
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
/*     */   public static double round(double val, int places) {
/* 160 */     long factor = (long)Math.pow(10.0D, places);
/*     */ 
/*     */ 
/*     */     
/* 164 */     val *= factor;
/*     */ 
/*     */     
/* 167 */     long tmp = Math.round(val);
/*     */ 
/*     */ 
/*     */     
/* 171 */     return tmp / factor;
/*     */   }
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
/*     */   public static float round(float val, int places) {
/* 184 */     return (float)round(val, places);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ConvertUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */