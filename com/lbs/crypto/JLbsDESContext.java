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
/*     */ public class JLbsDESContext
/*     */ {
/*  15 */   protected static byte[] ms_PC1 = new byte[] { 
/*  16 */       56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 
/*  17 */       18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 
/*  18 */       13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
/*  19 */   protected static byte[] ms_PC2 = new byte[] { 
/*  20 */       13, 16, 10, 23, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 
/*  21 */       15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 
/*  22 */       43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31 };
/*  23 */   protected static byte[] ms_CTotRot = new byte[] { 
/*  24 */       1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28 };
/*  25 */   protected static short[] ms_CBitMask = new short[] {
/*  26 */       128, 64, 32, 16, 8, 4, 2, 1
/*     */     };
/*  28 */   protected int[] m_TransformedKey = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_ForEncryption = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDESContext(JLbsDESKey key) throws Exception {
/*  40 */     internalInitialize(key, true);
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
/*     */   public JLbsDESContext(JLbsDESKey key, boolean bEncrypt) throws Exception {
/*  53 */     internalInitialize(key, bEncrypt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getTransformKey() {
/*  63 */     return this.m_TransformedKey;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void internalInitialize(JLbsDESKey key, boolean bEncrypt) throws Exception {
/*  68 */     if (key == null || !key.isInitialized()) {
/*  69 */       throw new Exception("JLbsDESContext: The given key is not valid for this operation");
/*     */     }
/*  71 */     this.m_TransformedKey = new int[32];
/*  72 */     this.m_ForEncryption = bEncrypt;
/*     */     
/*  74 */     byte[] PC1M = new byte[56];
/*  75 */     byte[] PC1R = new byte[56];
/*  76 */     byte[] keyData = key.getKey();
/*     */ 
/*     */     
/*  79 */     for (int j = 0; j < 56; j++) {
/*     */       
/*  81 */       byte L = ms_PC1[j];
/*  82 */       byte M = (byte)(L & 0x7);
/*  83 */       PC1M[j] = (byte)(((keyData[L >>> 3] & ms_CBitMask[M]) != 0) ? 1 : 0);
/*     */     } 
/*     */     
/*  86 */     for (int i = 0; i < 16; i++) {
/*     */ 
/*     */       
/*  89 */       for (int k = 0; k < 28; k++) {
/*     */         
/*  91 */         byte L = (byte)(k + ms_CTotRot[i]);
/*  92 */         if (L < 28) {
/*     */           
/*  94 */           PC1R[k] = PC1M[L];
/*  95 */           PC1R[k + 28] = PC1M[L + 28];
/*     */         }
/*     */         else {
/*     */           
/*  99 */           PC1R[k] = PC1M[L - 28];
/* 100 */           PC1R[k + 28] = PC1M[L];
/*     */         } 
/*     */       } 
/*     */       
/* 104 */       byte[] KS = new byte[8];
/* 105 */       for (int m = 0; m < 48; m++) {
/*     */         
/* 107 */         if (PC1R[ms_PC2[m]] == 1) {
/*     */           
/* 109 */           byte L = (byte)(m / 6);
/* 110 */           KS[L] = (byte)(KS[L] | ms_CBitMask[m % 6] >>> 2);
/*     */         } 
/*     */       } 
/*     */       
/* 114 */       if (bEncrypt) {
/*     */         
/* 116 */         this.m_TransformedKey[i * 2] = KS[0] << 24 | KS[2] << 16 | 
/* 117 */           KS[4] << 8 | KS[6];
/* 118 */         this.m_TransformedKey[i * 2 + 1] = KS[1] << 24 | KS[3] << 16 | 
/* 119 */           KS[5] << 8 | KS[7];
/*     */       }
/*     */       else {
/*     */         
/* 123 */         this.m_TransformedKey[31 - i * 2 + 1] = KS[0] << 24 | KS[2] << 16 | 
/* 124 */           KS[4] << 8 | KS[6];
/* 125 */         this.m_TransformedKey[31 - i * 2] = KS[1] << 24 | KS[3] << 16 | 
/* 126 */           KS[5] << 8 | KS[7];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsDESContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */