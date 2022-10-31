/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsConvertUtil
/*     */ {
/*     */   public static int str2IntDef(String s, int defVal) {
/*     */     try {
/*  19 */       return Integer.parseInt(s);
/*     */     }
/*  21 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  24 */       return defVal;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static double str2DoubleDef(String s, double defVal) {
/*     */     try {
/*  31 */       return Double.parseDouble(s);
/*     */     }
/*  33 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  36 */       return defVal;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static long str2LongDef(String s, long defVal) {
/*     */     try {
/*  43 */       return Long.parseLong(s);
/*     */     }
/*  45 */     catch (Exception exception) {
/*     */ 
/*     */       
/*  48 */       return defVal;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String paddedNumber(int i, int length) {
/*  53 */     StringBuilder buffer = new StringBuilder(Integer.toString(i));
/*  54 */     while (buffer.length() < length)
/*  55 */       buffer.insert(0, '0'); 
/*  56 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object convert2PrimiviteValue(String typeName, String value) {
/*  61 */     if (!JLbsStringUtil.isEmpty(typeName)) {
/*     */       
/*     */       try {
/*  64 */         if (typeName.compareTo("String") == 0)
/*  65 */           return JLbsStringUtil.areEqualNoCase("null", value) ? null : value; 
/*  66 */         if (typeName.compareTo("int") == 0 || typeName.compareTo("Integer") == 0)
/*  67 */           return Integer.valueOf(value); 
/*  68 */         if (typeName.compareTo("double") == 0 || typeName.compareTo("Double") == 0)
/*  69 */           return new Double(value); 
/*  70 */         if (typeName.compareTo("char") == 0 || typeName.compareTo("Character") == 0)
/*  71 */           return new Character(JLbsStringUtil.isEmpty(value) ? Character.MIN_VALUE : value.charAt(0)); 
/*  72 */         if (typeName.compareTo("byte") == 0 || typeName.compareTo("Byte") == 0)
/*  73 */           return Byte.valueOf(value); 
/*  74 */         if (typeName.compareTo("long") == 0 || typeName.compareTo("Long") == 0)
/*  75 */           return Long.valueOf(value); 
/*  76 */         if (typeName.compareTo("short") == 0 || typeName.compareTo("Short") == 0)
/*  77 */           return Short.valueOf(value); 
/*  78 */         if (typeName.compareTo("boolean") == 0 || typeName.compareTo("Boolean") == 0)
/*  79 */           return new Boolean(value); 
/*  80 */         if (typeName.compareTo("float") == 0 || typeName.compareTo("Float") == 0)
/*  81 */           return new Float(value); 
/*  82 */         if (typeName.compareTo("bigdecimal") == 0 || typeName.compareTo("BigDecimal") == 0) {
/*  83 */           return new BigDecimal(value);
/*     */         }
/*  85 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String byteArrayToHex(byte[] in) {
/*  93 */     byte ch = 0;
/*  94 */     int i = 0;
/*  95 */     if (in == null || in.length <= 0)
/*  96 */       return null; 
/*  97 */     String[] pseudo = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
/*  98 */     StringBuilder out = new StringBuilder(in.length * 2);
/*  99 */     while (i < in.length) {
/*     */       
/* 101 */       ch = (byte)(in[i] & 0xF0);
/* 102 */       ch = (byte)(ch >>> 4);
/* 103 */       ch = (byte)(ch & 0xF);
/* 104 */       out.append(pseudo[ch]);
/* 105 */       ch = (byte)(in[i] & 0xF);
/* 106 */       out.append(pseudo[ch]);
/* 107 */       i++;
/*     */     } 
/* 109 */     return new String(out);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] parseIntList(String s, String sep) {
/* 114 */     if (s != null) {
/*     */       
/* 116 */       StringTokenizer tok = new StringTokenizer(s, sep);
/* 117 */       int count = tok.countTokens();
/* 118 */       int[] result = new int[count];
/* 119 */       for (int i = 0; i < count; i++)
/* 120 */         result[i] = str2IntDef(tok.nextToken(), 0); 
/* 121 */       return result;
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFirmVariable() {
/* 128 */     if (JLbsConstants.checkAppCloud()) {
/* 129 */       return "00001";
/*     */     }
/* 131 */     return "001";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String parseToString(Object obj) {
/* 136 */     if (obj instanceof Number) {
/*     */       
/* 138 */       DecimalFormat format = new DecimalFormat("#.##");
/* 139 */       if (obj instanceof Integer || obj instanceof Long)
/* 140 */         return format.format(((Number)obj).longValue()); 
/* 141 */       return format.format(((Number)obj).doubleValue());
/*     */     } 
/* 143 */     return (obj != null) ? obj.toString() : "";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsConvertUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */