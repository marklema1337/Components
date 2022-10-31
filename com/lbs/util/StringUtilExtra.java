/*     */ package com.lbs.util;
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
/*     */ public class StringUtilExtra
/*     */ {
/*     */   public static final int LEFT = -1;
/*     */   public static final int CENTER = 0;
/*     */   public static final int RIGHT = 1;
/*     */   
/*     */   public static String padLeft(String s, char ch, int len) {
/*  38 */     StringBuilder buf = new StringBuilder(Math.max(len, s.length()));
/*  39 */     for (int i = s.length(); i < len; i++)
/*  40 */       buf.append(ch); 
/*  41 */     buf.append(s);
/*  42 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padLeftFirm(String s, char ch) {
/*  47 */     int length = 3;
/*  48 */     if (JLbsConstants.checkAppCloud()) {
/*  49 */       length = 5;
/*     */     }
/*  51 */     StringBuilder buf = new StringBuilder(Math.max(length, s.length()));
/*  52 */     for (int i = s.length(); i < length; i++)
/*  53 */       buf.append(ch); 
/*  54 */     buf.append(s);
/*  55 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String padLeft(String s, int len) {
/*  63 */     return padLeft(s, ' ', len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String padRight(String s, char ch, int len) {
/*  71 */     StringBuilder buf = new StringBuilder(Math.max(len, s.length()));
/*  72 */     buf.append(s);
/*  73 */     for (int i = s.length(); i < len; i++)
/*  74 */       buf.append(ch); 
/*  75 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String padRight(String s, int len) {
/*  83 */     return padRight(s, ' ', len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String padCenter(String s, char ch, int len) {
/*  91 */     StringBuilder buf = new StringBuilder(Math.max(len, s.length())); int i;
/*  92 */     for (i = s.length(); i < (len - s.length()) / 2; i++)
/*  93 */       buf.append(ch); 
/*  94 */     buf.append(s);
/*  95 */     for (i = buf.length(); i < len; i++)
/*  96 */       buf.append(ch); 
/*  97 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String padCenter(String s, int len) {
/* 105 */     return padCenter(s, ' ', len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replicate(char ch, int len) {
/* 113 */     return padLeft("", ch, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String space(int len) {
/* 121 */     return replicate(' ', len);
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
/*     */   public static String justify(String s, int len, int option) {
/* 133 */     switch (option) {
/*     */       
/*     */       case -1:
/* 136 */         return padRight(s, ' ', len);
/*     */       
/*     */       case 0:
/* 139 */         return padCenter(s, ' ', len);
/*     */       
/*     */       case 1:
/* 142 */         return padLeft(s, ' ', len);
/*     */     } 
/*     */     
/* 145 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String dump(String text, boolean chars) {
/* 165 */     String ret = "";
/* 166 */     int n = 15;
/* 167 */     StringBuilder sb = new StringBuilder();
/* 168 */     for (int i = 0; i < text.length(); i++) {
/*     */       
/* 170 */       ret = String.valueOf(ret) + padRight(Integer.toHexString(text.charAt(i)), '0', 4);
/* 171 */       sb.append(text.charAt(i));
/* 172 */       if (i % n == n - 1) {
/*     */         
/* 174 */         if (chars)
/* 175 */           ret = String.valueOf(ret) + " | " + sb.toString(); 
/* 176 */         ret = String.valueOf(ret) + "\n";
/* 177 */         sb.setLength(0);
/*     */       } else {
/*     */         
/* 180 */         ret = String.valueOf(ret) + ' ';
/*     */       } 
/* 182 */     }  int k = n - sb.length();
/* 183 */     ret = String.valueOf(ret) + space(5 * k) + "| " + sb.toString();
/* 184 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stripString(String text) {
/* 189 */     return JLbsStringUtil.trimRight(JLbsStringUtil.trimLeft(text));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mergeParameters(String message, String paramStr) {
/* 194 */     String merged = message;
/* 195 */     if (paramStr != null && paramStr.length() > 0) {
/*     */       
/* 197 */       int x = paramStr.indexOf("~");
/* 198 */       while (x >= 0) {
/*     */         
/* 200 */         int y = paramStr.indexOf("~", x + 1);
/* 201 */         if (y < 0) {
/* 202 */           x = -1;
/*     */           continue;
/*     */         } 
/* 205 */         String marker = paramStr.substring(x, y);
/* 206 */         int z = paramStr.indexOf("~", y + 1);
/* 207 */         if (z < 0) {
/*     */           
/* 209 */           z = paramStr.length();
/* 210 */           x = -1;
/*     */         } else {
/*     */           
/* 213 */           x = z;
/* 214 */         }  String param = paramStr.substring(y + 1, z);
/* 215 */         merged = JLbsStringUtil.replaceFirst(merged, marker, param);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 220 */     return merged;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mergeParameters(String inStr, String paramStr, int param) {
/* 225 */     if (inStr == null)
/* 226 */       return ""; 
/* 227 */     return JLbsStringUtil.replaceAll(inStr, "~" + param, paramStr);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mergeParameters(String inStr, String[] paramStr, int[] param) {
/* 232 */     return JLbsStringUtil.mergeParameters(inStr, paramStr, param);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\StringUtilExtra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */