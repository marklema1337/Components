/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
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
/*     */ public class LittleIndianHelper
/*     */ {
/*     */   public static int readInt(DataInputStream stream) throws IOException {
/*  19 */     byte[] buffer = new byte[4];
/*  20 */     stream.readFully(buffer, 0, 4);
/*  21 */     return (buffer[3] & 0xFF) << 24 | (buffer[2] & 0xFF) << 16 | (buffer[1] & 0xFF) << 8 | buffer[0] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readLong(DataInputStream stream) throws IOException {
/*  29 */     byte[] buffer = new byte[8];
/*     */     
/*  31 */     stream.readFully(buffer, 0, 8);
/*  32 */     return (buffer[7] & 0xFF) << 56L | (buffer[6] & 0xFF) << 48L | (buffer[5] & 0xFF) << 40L | (buffer[4] & 0xFF) << 32L | (buffer[3] & 0xFF) << 24L | (buffer[2] & 0xFF) << 16L | (buffer[1] & 0xFF) << 8L | (buffer[0] & 0xFF);
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
/*     */   public static float readFloat(DataInputStream stream) throws IOException {
/*  44 */     return Float.intBitsToFloat(readInt(stream));
/*     */   }
/*     */ 
/*     */   
/*     */   public static double readDouble(DataInputStream stream) throws IOException {
/*  49 */     return Double.longBitsToDouble(readLong(stream));
/*     */   }
/*     */ 
/*     */   
/*     */   public static double readExtended(DataInputStream stream) throws IOException {
/*  54 */     byte[] buffer = new byte[10];
/*  55 */     stream.readFully(buffer, 0, 10);
/*     */ 
/*     */ 
/*     */     
/*  59 */     long t = (buffer[0] & 0xFF) | (buffer[1] & 0xFF) << 8L | (buffer[2] & 0xFF) << 16L | (buffer[3] & 0xFF) << 24L | (buffer[4] & 0xFF) << 32L | (buffer[5] & 0xFF) << 40L | (buffer[6] & 0xFF) << 48L | (buffer[7] & 0xFF) << 56L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     long exp = (buffer[8] & 0xFF) | (buffer[9] & 0xFF) << 8L;
/*  69 */     long sign = exp >> 15L << 63L;
/*     */     
/*  71 */     exp = (exp & 0x7FFFFFFFL) - 16383L + 1023L;
/*     */     
/*  73 */     if (exp <= 0L) {
/*  74 */       return 0.0D;
/*     */     }
/*  76 */     if (exp >= 1023L) {
/*  77 */       t = sign | 0x7FF0000000000000L;
/*     */     } else {
/*  79 */       t = sign | exp << 52L | t >> 11L & 0xFFFFFFFFFFFFFL;
/*     */     } 
/*  81 */     return Double.longBitsToDouble(t);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeShort(DataOutputStream stream, short s) throws IOException {
/*  86 */     byte[] buffer = new byte[2];
/*     */     
/*  88 */     buffer[0] = (byte)s;
/*  89 */     buffer[1] = (byte)(s >> 8);
/*     */     
/*  91 */     stream.write(buffer, 0, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeInt(DataOutputStream stream, int i) throws IOException {
/*  96 */     byte[] buffer = new byte[4];
/*     */     
/*  98 */     buffer[0] = (byte)i;
/*  99 */     buffer[1] = (byte)(i >> 8);
/* 100 */     buffer[2] = (byte)(i >> 16);
/* 101 */     buffer[3] = (byte)(i >> 24);
/*     */     
/* 103 */     stream.write(buffer, 0, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeLong(DataOutputStream stream, long l) throws IOException {
/* 108 */     byte[] buffer = new byte[8];
/*     */     
/* 110 */     buffer[0] = (byte)(int)l;
/* 111 */     buffer[1] = (byte)(int)(l >> 8L);
/* 112 */     buffer[2] = (byte)(int)(l >> 16L);
/* 113 */     buffer[3] = (byte)(int)(l >> 24L);
/*     */     
/* 115 */     buffer[4] = (byte)(int)(l >> 32L);
/* 116 */     buffer[5] = (byte)(int)(l >> 40L);
/* 117 */     buffer[6] = (byte)(int)(l >> 48L);
/* 118 */     buffer[7] = (byte)(int)(l >> 56L);
/*     */     
/* 120 */     stream.write(buffer, 0, 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeDouble(DataOutputStream stream, Double d) throws IOException {
/* 125 */     writeLong(stream, Double.doubleToLongBits(d.doubleValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeFloat(DataOutputStream stream, Float f) throws IOException {
/* 130 */     writeInt(stream, Float.floatToIntBits(f.floatValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeInteger(DataOutputStream stream, Integer i) throws IOException {
/* 135 */     writeInt(stream, i.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeExtended(DataOutputStream stream, Double e) throws IOException {
/* 140 */     throw new IOException("Writing Extended values is not supported");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LittleIndianHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */