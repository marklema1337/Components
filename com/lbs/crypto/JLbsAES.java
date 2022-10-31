/*     */ package com.lbs.crypto;
/*     */ 
/*     */ import java.security.InvalidKeyException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JLbsAES
/*     */ {
/*     */   public static final int BLOCK_SIZE = 16;
/*     */   static final boolean IN = true;
/*     */   static final boolean OUT = false;
/*  18 */   static final int[] alog = new int[256];
/*  19 */   static final int[] log = new int[256];
/*  20 */   static final byte[] S = new byte[256];
/*  21 */   static final byte[] Si = new byte[256];
/*  22 */   static final int[] T1 = new int[256];
/*  23 */   static final int[] T2 = new int[256];
/*  24 */   static final int[] T3 = new int[256];
/*  25 */   static final int[] T4 = new int[256];
/*  26 */   static final int[] T5 = new int[256];
/*  27 */   static final int[] T6 = new int[256];
/*  28 */   static final int[] T7 = new int[256];
/*  29 */   static final int[] T8 = new int[256];
/*  30 */   static final int[] U1 = new int[256];
/*  31 */   static final int[] U2 = new int[256];
/*  32 */   static final int[] U3 = new int[256];
/*  33 */   static final int[] U4 = new int[256];
/*  34 */   static final byte[] rcon = new byte[30];
/*     */   
/*  36 */   static final int[][][] shifts = new int[][][] { { new int[2], { 1, 3 }, { 2, 2 }, { 3, 1
/*  37 */         } }, { new int[2], { 1, 5 }, { 2, 4 }, { 3, 3
/*  38 */         } }, { new int[2], { 1, 7 }, { 3, 5 }, { 4, 4 } } };
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  43 */     int ROOT = 283;
/*  44 */     int j = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     alog[0] = 1; int i;
/*  50 */     for (i = 1; i < 256; i++) {
/*     */       
/*  52 */       j = alog[i - 1] << 1 ^ alog[i - 1];
/*  53 */       if ((j & 0x100) != 0)
/*  54 */         j ^= ROOT; 
/*  55 */       alog[i] = j;
/*     */     } 
/*  57 */     for (i = 1; i < 255; i++)
/*  58 */       log[alog[i]] = i; 
/*  59 */     byte[][] A = { { 1, 1, 1, 1, 1
/*  60 */         }, { 0, 1, 1, 1, 1, 1
/*  61 */         }, { 0, 0, 1, 1, 1, 1, 1
/*  62 */         }, { 0, 0, 0, 1, 1, 1, 1, 1
/*  63 */         }, { 1, 1, 1, 1, 1
/*  64 */         }, { 1, 1, 1, 1, 1
/*  65 */         }, { 1, 1, 1, 1, 1
/*  66 */         }, { 1, 1, 1, 1, 1 } };
/*     */     
/*  68 */     byte[] B = { 0, 1, 1, 1, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     byte[][] box = new byte[256][8];
/*  74 */     box[1][7] = 1;
/*  75 */     for (i = 2; i < 256; i++) {
/*     */       
/*  77 */       j = alog[255 - log[i]];
/*  78 */       for (int k = 0; k < 8; k++) {
/*  79 */         box[i][k] = (byte)(j >>> 7 - k & 0x1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  84 */     byte[][] cox = new byte[256][8];
/*  85 */     for (i = 0; i < 256; i++) {
/*  86 */       for (int k = 0; k < 8; k++) {
/*     */         
/*  88 */         cox[i][k] = B[k];
/*  89 */         for (j = 0; j < 8; j++) {
/*  90 */           cox[i][k] = (byte)(cox[i][k] ^ A[k][j] * box[i][j]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     for (i = 0; i < 256; i++) {
/*     */       
/*  97 */       S[i] = (byte)(cox[i][0] << 7);
/*  98 */       for (int k = 1; k < 8; k++)
/*  99 */         S[i] = (byte)(S[i] ^ cox[i][k] << 7 - k); 
/* 100 */       Si[S[i] & 0xFF] = (byte)i;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 105 */     byte[][] G = { { 2, 1, 1, 3
/* 106 */         }, { 3, 2, 1, 1
/* 107 */         }, { 1, 3, 2, 1
/* 108 */         }, { 1, 1, 3, 2 } };
/*     */     
/* 110 */     byte[][] AA = new byte[4][8];
/* 111 */     for (i = 0; i < 4; i++) {
/*     */       
/* 113 */       for (j = 0; j < 4; j++)
/* 114 */         AA[i][j] = G[i][j]; 
/* 115 */       AA[i][i + 4] = 1;
/*     */     } 
/*     */     
/* 118 */     byte[][] iG = new byte[4][4];
/* 119 */     for (i = 0; i < 4; i++) {
/*     */       
/* 121 */       byte pivot = AA[i][i];
/* 122 */       if (pivot == 0) {
/*     */         
/* 124 */         int m = i + 1;
/* 125 */         while (AA[m][i] == 0 && m < 4)
/* 126 */           m++; 
/* 127 */         if (m == 4) {
/* 128 */           throw new RuntimeException("G matrix is not invertible");
/*     */         }
/*     */         
/* 131 */         for (j = 0; j < 8; j++) {
/*     */           
/* 133 */           byte tmp = AA[i][j];
/* 134 */           AA[i][j] = AA[m][j];
/* 135 */           AA[m][j] = tmp;
/*     */         } 
/* 137 */         pivot = AA[i][i];
/*     */       } 
/*     */       
/* 140 */       for (j = 0; j < 8; j++) {
/* 141 */         if (AA[i][j] != 0)
/* 142 */           AA[i][j] = 
/* 143 */             (byte)alog[(255 + 
/* 144 */               log[AA[i][j] & 0xFF] - 
/* 145 */               log[pivot & 0xFF]) % 
/* 146 */               255]; 
/* 147 */       }  for (int k = 0; k < 4; k++) {
/* 148 */         if (i != k) {
/*     */           
/* 150 */           for (j = i + 1; j < 8; j++)
/* 151 */             AA[k][j] = (byte)(AA[k][j] ^ mul(AA[i][j], AA[k][i])); 
/* 152 */           AA[k][i] = 0;
/*     */         } 
/*     */       } 
/* 155 */     }  for (i = 0; i < 4; i++) {
/* 156 */       for (j = 0; j < 4; j++)
/* 157 */         iG[i][j] = AA[i][j + 4]; 
/*     */     }  int t;
/* 159 */     for (t = 0; t < 256; t++) {
/*     */       
/* 161 */       int s = S[t];
/* 162 */       T1[t] = mul4(s, G[0]);
/* 163 */       T2[t] = mul4(s, G[1]);
/* 164 */       T3[t] = mul4(s, G[2]);
/* 165 */       T4[t] = mul4(s, G[3]);
/* 166 */       s = Si[t];
/* 167 */       T5[t] = mul4(s, iG[0]);
/* 168 */       T6[t] = mul4(s, iG[1]);
/* 169 */       T7[t] = mul4(s, iG[2]);
/* 170 */       T8[t] = mul4(s, iG[3]);
/* 171 */       U1[t] = mul4(t, iG[0]);
/* 172 */       U2[t] = mul4(t, iG[1]);
/* 173 */       U3[t] = mul4(t, iG[2]);
/* 174 */       U4[t] = mul4(t, iG[3]);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 179 */     rcon[0] = 1;
/* 180 */     int r = 1;
/* 181 */     for (t = 1; t < 30;) {
/* 182 */       rcon[t++] = (byte)(r = mul(2, r));
/*     */     }
/*     */   }
/*     */   
/*     */   static final int mul(int a, int b) {
/* 187 */     return (a != 0 && b != 0) ? 
/* 188 */       alog[(log[a & 0xFF] + log[b & 0xFF]) % 255] : 
/* 189 */       0;
/*     */   }
/*     */ 
/*     */   
/*     */   static final int mul4(int a, byte[] b) {
/* 194 */     if (a == 0)
/* 195 */       return 0; 
/* 196 */     a = log[a & 0xFF];
/* 197 */     int a0 = (b[0] != 0) ? (alog[(a + log[b[0] & 0xFF]) % 255] & 0xFF) : 0;
/* 198 */     int a1 = (b[1] != 0) ? (alog[(a + log[b[1] & 0xFF]) % 255] & 0xFF) : 0;
/* 199 */     int a2 = (b[2] != 0) ? (alog[(a + log[b[2] & 0xFF]) % 255] & 0xFF) : 0;
/* 200 */     int a3 = (b[3] != 0) ? (alog[(a + log[b[3] & 0xFF]) % 255] & 0xFF) : 0;
/* 201 */     return a0 << 24 | a1 << 16 | a2 << 8 | a3;
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
/*     */   public static Object makeKey(byte[] k) throws InvalidKeyException {
/* 214 */     return makeKey(k, 16);
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
/*     */   public static byte[] blockEncrypt(byte[] in, int inOffset, Object sessionKey) {
/* 230 */     int[][] Ke = (int[][])((Object[])sessionKey)[0];
/*     */     
/* 232 */     int ROUNDS = Ke.length - 1;
/* 233 */     int[] Ker = Ke[0];
/*     */     
/* 235 */     int t0 = ((
/* 236 */       in[inOffset++] & 0xFF) << 
/* 237 */       24 | (in[inOffset++] & 0xFF) << 
/* 238 */       16 | (in[inOffset++] & 0xFF) << 
/* 239 */       8 | in[inOffset++] & 0xFF) ^ 
/* 240 */       Ker[0];
/* 241 */     int t1 = ((
/* 242 */       in[inOffset++] & 0xFF) << 
/* 243 */       24 | (in[inOffset++] & 0xFF) << 
/* 244 */       16 | (in[inOffset++] & 0xFF) << 
/* 245 */       8 | in[inOffset++] & 0xFF) ^ 
/* 246 */       Ker[1];
/* 247 */     int t2 = ((
/* 248 */       in[inOffset++] & 0xFF) << 
/* 249 */       24 | (in[inOffset++] & 0xFF) << 
/* 250 */       16 | (in[inOffset++] & 0xFF) << 
/* 251 */       8 | in[inOffset++] & 0xFF) ^ 
/* 252 */       Ker[2];
/* 253 */     int t3 = ((
/* 254 */       in[inOffset++] & 0xFF) << 
/* 255 */       24 | (in[inOffset++] & 0xFF) << 
/* 256 */       16 | (in[inOffset++] & 0xFF) << 
/* 257 */       8 | in[inOffset++] & 0xFF) ^ 
/* 258 */       Ker[3];
/*     */     
/* 260 */     for (int r = 1; r < ROUNDS; r++) {
/*     */       
/* 262 */       Ker = Ke[r];
/* 263 */       int a0 = 
/* 264 */         T1[t0 >>> 24 & 
/* 265 */           0xFF] ^ 
/* 266 */         T2[t1 >>> 16 & 
/* 267 */           0xFF] ^ 
/* 268 */         T3[t2 >>> 8 & 
/* 269 */           0xFF] ^ 
/* 270 */         T4[t3 & 
/* 271 */           0xFF] ^ 
/* 272 */         Ker[0];
/* 273 */       int a1 = 
/* 274 */         T1[t1 >>> 24 & 
/* 275 */           0xFF] ^ 
/* 276 */         T2[t2 >>> 16 & 
/* 277 */           0xFF] ^ 
/* 278 */         T3[t3 >>> 8 & 
/* 279 */           0xFF] ^ 
/* 280 */         T4[t0 & 
/* 281 */           0xFF] ^ 
/* 282 */         Ker[1];
/* 283 */       int a2 = 
/* 284 */         T1[t2 >>> 24 & 
/* 285 */           0xFF] ^ 
/* 286 */         T2[t3 >>> 16 & 
/* 287 */           0xFF] ^ 
/* 288 */         T3[t0 >>> 8 & 
/* 289 */           0xFF] ^ 
/* 290 */         T4[t1 & 
/* 291 */           0xFF] ^ 
/* 292 */         Ker[2];
/* 293 */       int a3 = 
/* 294 */         T1[t3 >>> 24 & 
/* 295 */           0xFF] ^ 
/* 296 */         T2[t0 >>> 16 & 
/* 297 */           0xFF] ^ 
/* 298 */         T3[t1 >>> 8 & 
/* 299 */           0xFF] ^ 
/* 300 */         T4[t2 & 
/* 301 */           0xFF] ^ 
/* 302 */         Ker[3];
/* 303 */       t0 = a0;
/* 304 */       t1 = a1;
/* 305 */       t2 = a2;
/* 306 */       t3 = a3;
/*     */     } 
/*     */     
/* 309 */     byte[] result = new byte[16];
/* 310 */     Ker = Ke[ROUNDS];
/* 311 */     int tt = Ker[0];
/* 312 */     result[0] = (byte)(S[t0 >>> 24 & 0xFF] ^ tt >>> 24);
/* 313 */     result[1] = (byte)(S[t1 >>> 16 & 0xFF] ^ tt >>> 16);
/* 314 */     result[2] = (byte)(S[t2 >>> 8 & 0xFF] ^ tt >>> 8);
/* 315 */     result[3] = (byte)(S[t3 & 0xFF] ^ tt);
/* 316 */     tt = Ker[1];
/* 317 */     result[4] = (byte)(S[t1 >>> 24 & 0xFF] ^ tt >>> 24);
/* 318 */     result[5] = (byte)(S[t2 >>> 16 & 0xFF] ^ tt >>> 16);
/* 319 */     result[6] = (byte)(S[t3 >>> 8 & 0xFF] ^ tt >>> 8);
/* 320 */     result[7] = (byte)(S[t0 & 0xFF] ^ tt);
/* 321 */     tt = Ker[2];
/* 322 */     result[8] = (byte)(S[t2 >>> 24 & 0xFF] ^ tt >>> 24);
/* 323 */     result[9] = (byte)(S[t3 >>> 16 & 0xFF] ^ tt >>> 16);
/* 324 */     result[10] = (byte)(S[t0 >>> 8 & 0xFF] ^ tt >>> 8);
/* 325 */     result[11] = (byte)(S[t1 & 0xFF] ^ tt);
/* 326 */     tt = Ker[3];
/* 327 */     result[12] = (byte)(S[t3 >>> 24 & 0xFF] ^ tt >>> 24);
/* 328 */     result[13] = (byte)(S[t0 >>> 16 & 0xFF] ^ tt >>> 16);
/* 329 */     result[14] = (byte)(S[t1 >>> 8 & 0xFF] ^ tt >>> 8);
/* 330 */     result[15] = (byte)(S[t2 & 0xFF] ^ tt);
/* 331 */     return result;
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
/*     */   public static byte[] blockDecrypt(byte[] in, int inOffset, Object sessionKey) {
/* 347 */     int[][] Kd = (int[][])((Object[])sessionKey)[1];
/*     */     
/* 349 */     int ROUNDS = Kd.length - 1;
/* 350 */     int[] Kdr = Kd[0];
/*     */     
/* 352 */     int t0 = ((
/* 353 */       in[inOffset++] & 0xFF) << 
/* 354 */       24 | (in[inOffset++] & 0xFF) << 
/* 355 */       16 | (in[inOffset++] & 0xFF) << 
/* 356 */       8 | in[inOffset++] & 0xFF) ^ 
/* 357 */       Kdr[0];
/* 358 */     int t1 = ((
/* 359 */       in[inOffset++] & 0xFF) << 
/* 360 */       24 | (in[inOffset++] & 0xFF) << 
/* 361 */       16 | (in[inOffset++] & 0xFF) << 
/* 362 */       8 | in[inOffset++] & 0xFF) ^ 
/* 363 */       Kdr[1];
/* 364 */     int t2 = ((
/* 365 */       in[inOffset++] & 0xFF) << 
/* 366 */       24 | (in[inOffset++] & 0xFF) << 
/* 367 */       16 | (in[inOffset++] & 0xFF) << 
/* 368 */       8 | in[inOffset++] & 0xFF) ^ 
/* 369 */       Kdr[2];
/* 370 */     int t3 = ((
/* 371 */       in[inOffset++] & 0xFF) << 
/* 372 */       24 | (in[inOffset++] & 0xFF) << 
/* 373 */       16 | (in[inOffset++] & 0xFF) << 
/* 374 */       8 | in[inOffset++] & 0xFF) ^ 
/* 375 */       Kdr[3];
/*     */     
/* 377 */     for (int r = 1; r < ROUNDS; r++) {
/*     */       
/* 379 */       Kdr = Kd[r];
/* 380 */       int a0 = 
/* 381 */         T5[t0 >>> 24 & 
/* 382 */           0xFF] ^ 
/* 383 */         T6[t3 >>> 16 & 
/* 384 */           0xFF] ^ 
/* 385 */         T7[t2 >>> 8 & 
/* 386 */           0xFF] ^ 
/* 387 */         T8[t1 & 
/* 388 */           0xFF] ^ 
/* 389 */         Kdr[0];
/* 390 */       int a1 = 
/* 391 */         T5[t1 >>> 24 & 
/* 392 */           0xFF] ^ 
/* 393 */         T6[t0 >>> 16 & 
/* 394 */           0xFF] ^ 
/* 395 */         T7[t3 >>> 8 & 
/* 396 */           0xFF] ^ 
/* 397 */         T8[t2 & 
/* 398 */           0xFF] ^ 
/* 399 */         Kdr[1];
/* 400 */       int a2 = 
/* 401 */         T5[t2 >>> 24 & 
/* 402 */           0xFF] ^ 
/* 403 */         T6[t1 >>> 16 & 
/* 404 */           0xFF] ^ 
/* 405 */         T7[t0 >>> 8 & 
/* 406 */           0xFF] ^ 
/* 407 */         T8[t3 & 
/* 408 */           0xFF] ^ 
/* 409 */         Kdr[2];
/* 410 */       int a3 = 
/* 411 */         T5[t3 >>> 24 & 
/* 412 */           0xFF] ^ 
/* 413 */         T6[t2 >>> 16 & 
/* 414 */           0xFF] ^ 
/* 415 */         T7[t1 >>> 8 & 
/* 416 */           0xFF] ^ 
/* 417 */         T8[t0 & 
/* 418 */           0xFF] ^ 
/* 419 */         Kdr[3];
/* 420 */       t0 = a0;
/* 421 */       t1 = a1;
/* 422 */       t2 = a2;
/* 423 */       t3 = a3;
/*     */     } 
/*     */     
/* 426 */     byte[] result = new byte[16];
/* 427 */     Kdr = Kd[ROUNDS];
/* 428 */     int tt = Kdr[0];
/* 429 */     result[0] = (byte)(Si[t0 >>> 24 & 0xFF] ^ tt >>> 24);
/* 430 */     result[1] = (byte)(Si[t3 >>> 16 & 0xFF] ^ tt >>> 16);
/* 431 */     result[2] = (byte)(Si[t2 >>> 8 & 0xFF] ^ tt >>> 8);
/* 432 */     result[3] = (byte)(Si[t1 & 0xFF] ^ tt);
/* 433 */     tt = Kdr[1];
/* 434 */     result[4] = (byte)(Si[t1 >>> 24 & 0xFF] ^ tt >>> 24);
/* 435 */     result[5] = (byte)(Si[t0 >>> 16 & 0xFF] ^ tt >>> 16);
/* 436 */     result[6] = (byte)(Si[t3 >>> 8 & 0xFF] ^ tt >>> 8);
/* 437 */     result[7] = (byte)(Si[t2 & 0xFF] ^ tt);
/* 438 */     tt = Kdr[2];
/* 439 */     result[8] = (byte)(Si[t2 >>> 24 & 0xFF] ^ tt >>> 24);
/* 440 */     result[9] = (byte)(Si[t1 >>> 16 & 0xFF] ^ tt >>> 16);
/* 441 */     result[10] = (byte)(Si[t0 >>> 8 & 0xFF] ^ tt >>> 8);
/* 442 */     result[11] = (byte)(Si[t3 & 0xFF] ^ tt);
/* 443 */     tt = Kdr[3];
/* 444 */     result[12] = (byte)(Si[t3 >>> 24 & 0xFF] ^ tt >>> 24);
/* 445 */     result[13] = (byte)(Si[t2 >>> 16 & 0xFF] ^ tt >>> 16);
/* 446 */     result[14] = (byte)(Si[t1 >>> 8 & 0xFF] ^ tt >>> 8);
/* 447 */     result[15] = (byte)(Si[t0 & 0xFF] ^ tt);
/* 448 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean self_test() {
/* 453 */     return self_test(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int blockSize() {
/* 460 */     return 16;
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
/*     */   public static synchronized Object makeKey(byte[] k, int blockSize) throws InvalidKeyException {
/* 472 */     if (k == null)
/* 473 */       throw new InvalidKeyException("Empty key"); 
/* 474 */     if (k.length != 16 && k.length != 24 && k.length != 32)
/* 475 */       throw new InvalidKeyException("Incorrect key length"); 
/* 476 */     int ROUNDS = getRounds(k.length, blockSize);
/* 477 */     int BC = blockSize / 4;
/* 478 */     int[][] Ke = new int[ROUNDS + 1][BC];
/* 479 */     int[][] Kd = new int[ROUNDS + 1][BC];
/* 480 */     int ROUND_KEY_COUNT = (ROUNDS + 1) * BC;
/* 481 */     int KC = k.length / 4;
/* 482 */     int[] tk = new int[KC];
/*     */     
/*     */     int i, j;
/* 485 */     for (i = 0, j = 0; i < KC;) {
/* 486 */       tk[i++] = (
/* 487 */         k[j++] & 0xFF) << 
/* 488 */         24 | (k[j++] & 0xFF) << 
/* 489 */         16 | (k[j++] & 0xFF) << 
/* 490 */         8 | k[j++] & 0xFF;
/*     */     }
/* 492 */     int t = 0;
/* 493 */     for (j = 0; j < KC && t < ROUND_KEY_COUNT; j++, t++) {
/*     */       
/* 495 */       Ke[t / BC][t % BC] = tk[j];
/* 496 */       Kd[ROUNDS - t / BC][t % BC] = tk[j];
/*     */     } 
/* 498 */     int rconpointer = 0;
/* 499 */     while (t < ROUND_KEY_COUNT) {
/*     */ 
/*     */       
/* 502 */       int tt = tk[KC - 1];
/* 503 */       tk[0] = tk[0] ^ (S[tt >>> 16 & 0xFF] & 0xFF) << 
/* 504 */         24 ^ (S[tt >>> 8 & 0xFF] & 0xFF) << 
/* 505 */         16 ^ (S[tt & 0xFF] & 0xFF) << 
/* 506 */         8 ^ 
/* 507 */         S[tt >>> 24 & 0xFF] & 0xFF ^ (
/* 508 */         rcon[rconpointer++] & 0xFF) << 
/* 509 */         24;
/* 510 */       if (KC != 8) {
/* 511 */         for (i = 1, j = 0; i < KC;) {
/* 512 */           tk[i++] = tk[i++] ^ tk[j++];
/*     */         }
/*     */       } else {
/* 515 */         for (i = 1, j = 0; i < KC / 2;)
/* 516 */           tk[i++] = tk[i++] ^ tk[j++]; 
/* 517 */         tt = tk[KC / 2 - 1];
/* 518 */         tk[KC / 
/* 519 */             2] = tk[KC / 2] ^ S[tt & 0xFF] & 0xFF ^ (S[tt >>> 8 & 0xFF] & 0xFF) << 
/* 520 */           8 ^ (S[tt >>> 16 & 0xFF] & 0xFF) << 
/* 521 */           16 ^ (S[tt >>> 24 & 0xFF] & 0xFF) << 
/* 522 */           24;
/* 523 */         for (j = KC / 2, i = j + 1; i < KC;) {
/* 524 */           tk[i++] = tk[i++] ^ tk[j++];
/*     */         }
/*     */       } 
/* 527 */       for (j = 0; j < KC && t < ROUND_KEY_COUNT; j++, t++) {
/*     */         
/* 529 */         Ke[t / BC][t % BC] = tk[j];
/* 530 */         Kd[ROUNDS - t / BC][t % BC] = tk[j];
/*     */       } 
/*     */     } 
/* 533 */     for (int r = 1; r < ROUNDS; r++) {
/* 534 */       for (j = 0; j < BC; j++) {
/*     */         
/* 536 */         int tt = Kd[r][j];
/* 537 */         Kd[r][j] = 
/* 538 */           U1[tt >>> 24 & 
/* 539 */             0xFF] ^ 
/* 540 */           U2[tt >>> 16 & 
/* 541 */             0xFF] ^ 
/* 542 */           U3[tt >>> 8 & 
/* 543 */             0xFF] ^ 
/* 544 */           U4[tt & 
/* 545 */             0xFF];
/*     */       } 
/*     */     } 
/*     */     
/* 549 */     Object[] sessionKey = { Ke, Kd };
/* 550 */     return sessionKey;
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
/*     */   public static byte[] blockEncrypt(byte[] in, int inOffset, Object sessionKey, int blockSize) {
/* 567 */     if (blockSize == 16)
/* 568 */       return blockEncrypt(in, inOffset, sessionKey); 
/* 569 */     Object[] sKey = (Object[])sessionKey;
/* 570 */     int[][] Ke = (int[][])sKey[0];
/* 571 */     int BC = blockSize / 4;
/* 572 */     int ROUNDS = Ke.length - 1;
/* 573 */     int SC = (BC == 4) ? 0 : ((BC == 6) ? 1 : 2);
/* 574 */     int s1 = shifts[SC][1][0];
/* 575 */     int s2 = shifts[SC][2][0];
/* 576 */     int s3 = shifts[SC][3][0];
/* 577 */     int[] a = new int[BC];
/* 578 */     int[] t = new int[BC];
/*     */     
/* 580 */     byte[] result = new byte[blockSize];
/* 581 */     int j = 0; int i;
/* 582 */     for (i = 0; i < BC; i++)
/* 583 */       t[i] = ((
/* 584 */         in[inOffset++] & 0xFF) << 
/* 585 */         24 | (in[inOffset++] & 0xFF) << 
/* 586 */         16 | (in[inOffset++] & 0xFF) << 
/* 587 */         8 | in[inOffset++] & 0xFF) ^ 
/* 588 */         Ke[0][i]; 
/* 589 */     for (int r = 1; r < ROUNDS; r++) {
/*     */       
/* 591 */       for (i = 0; i < BC; i++)
/* 592 */         a[i] = 
/* 593 */           T1[t[i] >>> 24 & 
/* 594 */             0xFF] ^ 
/* 595 */           T2[t[(i + s1) % BC] >>> 16 & 
/* 596 */             0xFF] ^ 
/* 597 */           T3[t[(i + s2) % BC] >>> 8 & 
/* 598 */             0xFF] ^ 
/* 599 */           T4[t[(i + s3) % 
/* 600 */               BC] & 0xFF] ^ 
/* 601 */           Ke[r][i]; 
/* 602 */       System.arraycopy(a, 0, t, 0, BC);
/*     */     } 
/* 604 */     for (i = 0; i < BC; i++) {
/*     */       
/* 606 */       int tt = Ke[ROUNDS][i];
/* 607 */       result[j++] = (byte)(S[t[i] >>> 24 & 0xFF] ^ tt >>> 24);
/* 608 */       result[j++] = 
/* 609 */         (byte)(S[t[(i + s1) % BC] >>> 16 & 0xFF] ^ tt >>> 16);
/* 610 */       result[j++] = 
/* 611 */         (byte)(S[t[(i + s2) % BC] >>> 8 & 0xFF] ^ tt >>> 8);
/* 612 */       result[j++] = (byte)(S[t[(i + s3) % BC] & 0xFF] ^ tt);
/*     */     } 
/* 614 */     return result;
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
/*     */   public static byte[] blockDecrypt(byte[] in, int inOffset, Object sessionKey, int blockSize) {
/* 631 */     if (blockSize == 16)
/* 632 */       return blockDecrypt(in, inOffset, sessionKey); 
/* 633 */     Object[] sKey = (Object[])sessionKey;
/* 634 */     int[][] Kd = (int[][])sKey[1];
/* 635 */     int BC = blockSize / 4;
/* 636 */     int ROUNDS = Kd.length - 1;
/* 637 */     int SC = (BC == 4) ? 0 : ((BC == 6) ? 1 : 2);
/* 638 */     int s1 = shifts[SC][1][1];
/* 639 */     int s2 = shifts[SC][2][1];
/* 640 */     int s3 = shifts[SC][3][1];
/* 641 */     int[] a = new int[BC];
/* 642 */     int[] t = new int[BC];
/*     */     
/* 644 */     byte[] result = new byte[blockSize];
/* 645 */     int j = 0; int i;
/* 646 */     for (i = 0; i < BC; i++)
/* 647 */       t[i] = ((
/* 648 */         in[inOffset++] & 0xFF) << 
/* 649 */         24 | (in[inOffset++] & 0xFF) << 
/* 650 */         16 | (in[inOffset++] & 0xFF) << 
/* 651 */         8 | in[inOffset++] & 0xFF) ^ 
/* 652 */         Kd[0][i]; 
/* 653 */     for (int r = 1; r < ROUNDS; r++) {
/*     */       
/* 655 */       for (i = 0; i < BC; i++)
/* 656 */         a[i] = 
/* 657 */           T5[t[i] >>> 24 & 
/* 658 */             0xFF] ^ 
/* 659 */           T6[t[(i + s1) % BC] >>> 16 & 
/* 660 */             0xFF] ^ 
/* 661 */           T7[t[(i + s2) % BC] >>> 8 & 
/* 662 */             0xFF] ^ 
/* 663 */           T8[t[(i + s3) % 
/* 664 */               BC] & 0xFF] ^ 
/* 665 */           Kd[r][i]; 
/* 666 */       System.arraycopy(a, 0, t, 0, BC);
/*     */     } 
/* 668 */     for (i = 0; i < BC; i++) {
/*     */       
/* 670 */       int tt = Kd[ROUNDS][i];
/* 671 */       result[j++] = (byte)(Si[t[i] >>> 24 & 0xFF] ^ tt >>> 24);
/* 672 */       result[j++] = 
/* 673 */         (byte)(Si[t[(i + s1) % BC] >>> 16 & 0xFF] ^ tt >>> 16);
/* 674 */       result[j++] = 
/* 675 */         (byte)(Si[t[(i + s2) % BC] >>> 8 & 0xFF] ^ tt >>> 8);
/* 676 */       result[j++] = (byte)(Si[t[(i + s3) % BC] & 0xFF] ^ tt);
/*     */     } 
/* 678 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean self_test(int keysize) {
/* 683 */     boolean ok = false;
/*     */     
/*     */     try {
/* 686 */       byte[] kb = new byte[keysize];
/* 687 */       byte[] pt = new byte[16];
/*     */       int i;
/* 689 */       for (i = 0; i < keysize; i++)
/* 690 */         kb[i] = (byte)i; 
/* 691 */       for (i = 0; i < 16; i++)
/* 692 */         pt[i] = (byte)i; 
/* 693 */       Object key = makeKey(kb, 16);
/* 694 */       byte[] ct = blockEncrypt(pt, 0, key, 16);
/* 695 */       byte[] cpt = blockDecrypt(ct, 0, key, 16);
/* 696 */       ok = areEqual(pt, cpt);
/* 697 */       if (!ok) {
/* 698 */         throw new RuntimeException("Symmetric operation failed");
/*     */       }
/* 700 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 703 */     return ok;
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
/*     */   public static int getRounds(int keySize, int blockSize) {
/* 715 */     switch (keySize) {
/*     */       
/*     */       case 16:
/* 718 */         return (blockSize == 16) ? 10 : ((blockSize == 24) ? 12 : 14);
/*     */       case 24:
/* 720 */         return (blockSize != 32) ? 12 : 14;
/*     */     } 
/* 722 */     return 14;
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
/*     */   private static boolean areEqual(byte[] a, byte[] b) {
/* 734 */     int aLength = a.length;
/* 735 */     if (aLength != b.length)
/* 736 */       return false; 
/* 737 */     for (int i = 0; i < aLength; i++) {
/* 738 */       if (a[i] != b[i])
/* 739 */         return false; 
/* 740 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 746 */     self_test(16);
/* 747 */     self_test(24);
/* 748 */     self_test(32);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsAES.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */