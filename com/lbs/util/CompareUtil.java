/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*     */ import java.lang.reflect.Array;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CompareUtil
/*     */ {
/*     */   private static final double ms_DoubleEpsilon = 1.0E-9D;
/*  16 */   public static final BigDecimal epsilon = new BigDecimal(1.0E-9D);
/*     */ 
/*     */   
/*     */   public static boolean isZero(BigDecimal number) {
/*  20 */     return (number == null || number.abs().compareTo(epsilon) < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasValue(BigDecimal number) {
/*  25 */     return (number == null) ? false : (
/*     */       
/*  27 */       (number.abs().compareTo(epsilon) == 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean nearTo(BigDecimal number1, BigDecimal number2) {
/*  32 */     if (number1 == number2)
/*  33 */       return true; 
/*  34 */     if (number1 == null || number2 == null) {
/*  35 */       return false;
/*     */     }
/*  37 */     return (number1.subtract(number2).abs().compareTo(epsilon) == -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isArrayEquals(Object arr1, Object arr2) {
/*  42 */     if (arr1 == null && arr2 == null) {
/*  43 */       return true;
/*     */     }
/*  45 */     if (arr1 == null || arr2 == null) {
/*  46 */       return false;
/*     */     }
/*  48 */     if (!arr1.getClass().isArray() || !arr2.getClass().isArray()) {
/*  49 */       return arr1.equals(arr2);
/*     */     }
/*  51 */     if (arr1.getClass().isArray() && arr2.getClass().isArray()) {
/*     */       
/*  53 */       if (Array.getLength(arr1) != Array.getLength(arr2)) {
/*  54 */         return false;
/*     */       }
/*  56 */       for (int i = 0; i < Array.getLength(arr1); i++) {
/*     */         
/*  58 */         if (Array.get(arr1, i) != null && !Array.get(arr1, i).equals(Array.get(arr2, i))) {
/*  59 */           return false;
/*     */         }
/*     */       } 
/*     */     } else {
/*  63 */       return false;
/*  64 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNumberValueModified(Number oldVal, Number newVal) {
/*     */     try {
/*  71 */       double d_oldValue = oldVal.doubleValue();
/*  72 */       double d_newValue = newVal.doubleValue();
/*  73 */       return (Math.abs(d_oldValue - d_newValue) > 1.0E-9D);
/*     */     }
/*  75 */     catch (Exception e) {
/*     */       
/*  77 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areEqualNumbers(Object val1, Object val2) {
/*  83 */     if (val1 == null)
/*  84 */       return (val2 == null); 
/*  85 */     if (val2 == null)
/*  86 */       return false; 
/*  87 */     double n1 = 0.0D;
/*     */     
/*     */     try {
/*  90 */       n1 = ((Double)ValueConverter.convert(double.class, val1)).doubleValue();
/*     */     }
/*  92 */     catch (Exception e) {
/*     */       
/*  94 */       n1 = -9.75429056E8D;
/*     */     } 
/*     */     
/*  97 */     double n2 = 0.0D;
/*     */     
/*     */     try {
/* 100 */       n2 = ((Double)ValueConverter.convert(double.class, val2)).doubleValue();
/*     */     }
/* 102 */     catch (Exception e) {
/*     */       
/* 104 */       n2 = -5.73428032E8D;
/*     */     } 
/* 106 */     return (Math.abs(n1 - n2) <= 1.0E-9D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areEqual(Object value1, Object value2) {
/* 111 */     if (value1 == null)
/* 112 */       return (value2 == null); 
/* 113 */     if (value1 instanceof Number && value2 instanceof Number)
/* 114 */       return areEqualNumbers(value1, value2); 
/* 115 */     return value1.equals(value2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isValueEmpty(Object value, boolean date) {
/* 120 */     if (value == null)
/* 121 */       return true; 
/* 122 */     if (value instanceof String && ((String)value).compareTo("") == 0)
/* 123 */       return true; 
/* 124 */     if (value instanceof Number && Math.abs(((Number)value).doubleValue()) < 1.0E-9D)
/* 125 */       return true; 
/* 126 */     if (value instanceof Calendar && isCalendarEmpty((Calendar)value, date))
/* 127 */       return true; 
/* 128 */     if (value instanceof JLbsTimeDuration && value.equals(new JLbsTimeDuration())) {
/* 129 */       return true;
/*     */     }
/* 131 */     if (value instanceof byte[] && ((byte[])value).length == 0) {
/* 132 */       return true;
/*     */     }
/*     */     
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCalendarEmpty(Calendar c, boolean date) {
/* 140 */     if (date) {
/* 141 */       return !c.isSet(1);
/*     */     }
/* 143 */     return !c.isSet(10);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\CompareUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */