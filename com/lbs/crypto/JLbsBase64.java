/*     */ package com.lbs.crypto;
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
/*     */ public class JLbsBase64
/*     */ {
/*  17 */   private static char[] ms_CharMap = new char[64];
/*     */   
/*  19 */   private static byte[] ms_ValueMap = new byte[128];
/*     */   
/*     */   static {
/*  22 */     int i = 0; char c;
/*  23 */     for (c = 'A'; c <= 'Z'; c = (char)(c + 1))
/*  24 */       ms_CharMap[i++] = c; 
/*  25 */     for (c = 'a'; c <= 'z'; c = (char)(c + 1))
/*  26 */       ms_CharMap[i++] = c; 
/*  27 */     for (c = '0'; c <= '9'; c = (char)(c + 1))
/*  28 */       ms_CharMap[i++] = c; 
/*  29 */     ms_CharMap[i++] = '+';
/*  30 */     ms_CharMap[i++] = '/';
/*     */     
/*  32 */     for (i = 0; i < ms_ValueMap.length; i++)
/*  33 */       ms_ValueMap[i] = -1; 
/*  34 */     for (i = 0; i < 64; i++) {
/*  35 */       ms_ValueMap[ms_CharMap[i]] = (byte)i;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String encodeString(String s) {
/*  46 */     char[] chars = (s == null) ? null : encode(s.getBytes());
/*  47 */     if (chars == null)
/*  48 */       return null; 
/*  49 */     return new String(chars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] encode(String s) {
/*  60 */     return encode((s == null) ? null : s.getBytes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] encode(byte[] in) {
/*  71 */     if (in == null || in.length == 0)
/*  72 */       return null; 
/*  73 */     int iLen = in.length;
/*  74 */     int oDataLen = (iLen * 4 + 2) / 3;
/*  75 */     int oLen = (iLen + 2) / 3 * 4;
/*  76 */     char[] out = new char[oLen];
/*  77 */     int ip = 0;
/*  78 */     int op = 0;
/*  79 */     while (ip < iLen) {
/*     */       
/*  81 */       int i0 = in[ip++] & 0xFF;
/*  82 */       int i1 = (ip < iLen) ? (in[ip++] & 0xFF) : 0;
/*  83 */       int i2 = (ip < iLen) ? (in[ip++] & 0xFF) : 0;
/*  84 */       int o0 = i0 >>> 2;
/*  85 */       int o1 = (i0 & 0x3) << 4 | i1 >>> 4;
/*  86 */       int o2 = (i1 & 0xF) << 2 | i2 >>> 6;
/*  87 */       int o3 = i2 & 0x3F;
/*  88 */       out[op++] = ms_CharMap[o0];
/*  89 */       out[op++] = ms_CharMap[o1];
/*  90 */       out[op] = (op < oDataLen) ? ms_CharMap[o2] : '=';
/*  91 */       op++;
/*  92 */       out[op] = (op < oDataLen) ? ms_CharMap[o3] : '=';
/*  93 */       op++;
/*     */     } 
/*  95 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String encodeAsString(byte[] a) {
/* 100 */     return new String(encode(a));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decodeString(String s) {
/* 111 */     return (s == null || s.length() == 0) ? null : new String(decode(s.toCharArray()));
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
/*     */   public static byte[] decode(String s) {
/* 123 */     return decode((s == null || s.length() == 0) ? null : s.toCharArray());
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
/*     */   public static byte[] decode(char[] in) throws IllegalArgumentException {
/* 135 */     if (in == null || in.length == 0)
/* 136 */       return null; 
/* 137 */     int iLen = in.length;
/* 138 */     if (iLen % 4 != 0)
/* 139 */       throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4."); 
/* 140 */     while (iLen > 0 && in[iLen - 1] == '=')
/* 141 */       iLen--; 
/* 142 */     int oLen = iLen * 3 / 4;
/* 143 */     byte[] out = new byte[oLen];
/* 144 */     int ip = 0;
/* 145 */     int op = 0;
/* 146 */     while (ip < iLen) {
/*     */       
/* 148 */       int i0 = in[ip++];
/* 149 */       int i1 = in[ip++];
/* 150 */       int i2 = (ip < iLen) ? in[ip++] : 65;
/* 151 */       int i3 = (ip < iLen) ? in[ip++] : 65;
/* 152 */       if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
/* 153 */         throw new IllegalArgumentException("Illegal character in Base64 encoded data."); 
/* 154 */       int b0 = ms_ValueMap[i0];
/* 155 */       int b1 = ms_ValueMap[i1];
/* 156 */       int b2 = ms_ValueMap[i2];
/* 157 */       int b3 = ms_ValueMap[i3];
/* 158 */       if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
/* 159 */         throw new IllegalArgumentException("Illegal character in Base64 encoded data."); 
/* 160 */       int o0 = b0 << 2 | b1 >>> 4;
/* 161 */       int o1 = (b1 & 0xF) << 4 | b2 >>> 2;
/* 162 */       int o2 = (b2 & 0x3) << 6 | b3;
/* 163 */       out[op++] = (byte)o0;
/* 164 */       if (op < oLen)
/* 165 */         out[op++] = (byte)o1; 
/* 166 */       if (op < oLen)
/* 167 */         out[op++] = (byte)o2; 
/*     */     } 
/* 169 */     return out;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsBase64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */