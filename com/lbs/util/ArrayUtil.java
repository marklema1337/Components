/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ public abstract class ArrayUtil
/*     */ {
/*     */   public static byte[] subArray(byte[] source, int index, int length) {
/*  22 */     byte[] result = new byte[length];
/*  23 */     for (int i = 0; i < length; i++)
/*  24 */       result[i] = source[index + i]; 
/*  25 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] subArray(String[] source, int index, int length) {
/*  30 */     String[] result = new String[length];
/*  31 */     for (int i = 0; i < length; i++)
/*  32 */       result[i] = source[index + i]; 
/*  33 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean IsStringInArray(String[] list, String item) {
/*  38 */     int iSize = (list != null) ? 
/*  39 */       list.length : 
/*  40 */       0;
/*  41 */     for (int i = 0; i < iSize; i++) {
/*     */       
/*  43 */       String s = list[i];
/*  44 */       if (s == item || (s != null && s.compareTo(item) == 0))
/*  45 */         return true; 
/*     */     } 
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCommaSeperatedString(Object[] list) {
/*  52 */     return getSeperatedString(list, ',');
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSeperatedString(Object[] list, char separator) {
/*  57 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  59 */     if (list == null) {
/*  60 */       return sb.toString();
/*     */     }
/*  62 */     for (int i = 0; i < list.length; i++) {
/*     */       
/*  64 */       sb.append(String.valueOf(list[i]));
/*  65 */       if (i + 1 < list.length) {
/*  66 */         sb.append(separator);
/*     */       }
/*     */     } 
/*  69 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCommaSeperatedString(int[] list) {
/*  74 */     return getSeperatedString(list, ',');
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSeperatedString(int[] list, char separator) {
/*  79 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  81 */     if (list == null) {
/*  82 */       return sb.toString();
/*     */     }
/*  84 */     for (int i = 0; i < list.length; i++) {
/*     */       
/*  86 */       sb.append(String.valueOf(list[i]));
/*  87 */       if (i + 1 < list.length) {
/*  88 */         sb.append(separator);
/*     */       }
/*     */     } 
/*  91 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int hashCode(byte[] data) {
/*  96 */     if (data == null) {
/*  97 */       return 0;
/*     */     }
/*  99 */     int result = 1;
/* 100 */     for (int i = 0; i < data.length; i++) {
/*     */       
/* 102 */       byte element = data[i];
/* 103 */       result = 31 * result + element;
/*     */     } 
/* 105 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T[] merge(Object[] arr1, Object[] arr2) {
/* 110 */     if (arr1 == null)
/* 111 */       return (T[])arr2; 
/* 112 */     if (arr2 == null)
/* 113 */       return (T[])arr1; 
/* 114 */     int size = arr1.length + arr2.length;
/* 115 */     Object[] merged = Arrays.copyOf(arr1, size);
/* 116 */     for (int i = 0; i < arr2.length; i++)
/*     */     {
/* 118 */       merged[arr1.length + i] = arr2[i];
/*     */     }
/* 120 */     return (T[])merged;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] merge(int[] arr1, int[] arr2) {
/* 125 */     if (arr1 == null)
/* 126 */       return arr2; 
/* 127 */     if (arr2 == null)
/* 128 */       return arr1; 
/* 129 */     int size = arr1.length + arr2.length;
/* 130 */     int[] merged = Arrays.copyOf(arr1, size);
/* 131 */     for (int i = 0; i < arr2.length; i++)
/*     */     {
/* 133 */       merged[arr1.length + i] = arr2[i];
/*     */     }
/* 135 */     return merged;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ArrayUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */