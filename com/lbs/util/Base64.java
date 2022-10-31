/*      */ package com.lbs.util;
/*      */ 
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FilterInputStream;
/*      */ import java.io.FilterOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.zip.GZIPInputStream;
/*      */ import java.util.zip.GZIPOutputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Base64
/*      */ {
/*      */   public static final int NO_OPTIONS = 0;
/*      */   public static final int ENCODE_OPTION = 1;
/*      */   public static final int DECODE_OPTION = 0;
/*      */   public static final int GZIP = 2;
/*      */   public static final int DONT_BREAK_LINES = 8;
/*      */   private static final int MAX_LINE_LENGTH = 76;
/*      */   private static final byte EQUALS_SIGN = 61;
/*      */   private static final byte NEW_LINE = 10;
/*      */   private static final String PREFERRED_ENCODING = "UTF-8";
/*      */   private static final byte[] ALPHABET;
/*      */   
/*      */   static {
/*      */     byte[] __bytes;
/*      */   }
/*      */   
/*   98 */   private static final byte[] _NATIVE_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final byte[] DECODABET;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final byte WHITE_SPACE_ENC = -5;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final byte EQUALS_SIGN_ENC = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*      */     try {
/*  118 */       __bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes("UTF-8");
/*      */     }
/*  120 */     catch (UnsupportedEncodingException use) {
/*      */       
/*  122 */       __bytes = _NATIVE_ALPHABET;
/*      */     } 
/*  124 */     ALPHABET = __bytes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  132 */     DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] encode3to4(byte[] b4, byte[] threeBytes, int numSigBytes) {
/*  198 */     encode3to4(threeBytes, 0, numSigBytes, b4, 0);
/*  199 */     return b4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset) {
/*  239 */     int inBuff = ((numSigBytes > 0) ? (source[srcOffset] << 24 >>> 8) : 0) | ((numSigBytes > 1) ? (source[srcOffset + 1] << 24 >>> 16) : 0) | ((numSigBytes > 2) ? (source[srcOffset + 2] << 24 >>> 24) : 0);
/*      */ 
/*      */ 
/*      */     
/*  243 */     switch (numSigBytes) {
/*      */       
/*      */       case 3:
/*  246 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/*  247 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/*  248 */         destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3F];
/*  249 */         destination[destOffset + 3] = ALPHABET[inBuff & 0x3F];
/*  250 */         return destination;
/*      */       
/*      */       case 2:
/*  253 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/*  254 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/*  255 */         destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3F];
/*  256 */         destination[destOffset + 3] = 61;
/*  257 */         return destination;
/*      */       
/*      */       case 1:
/*  260 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/*  261 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/*  262 */         destination[destOffset + 2] = 61;
/*  263 */         destination[destOffset + 3] = 61;
/*  264 */         return destination;
/*      */     } 
/*      */     
/*  267 */     return destination;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeObject(Serializable serializableObject) {
/*  286 */     return encodeObject(serializableObject, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeObject(Serializable serializableObject, int options) {
/*  317 */     ByteArrayOutputStream baos = null;
/*  318 */     java.io.OutputStream b64os = null;
/*  319 */     ObjectOutputStream oos = null;
/*  320 */     GZIPOutputStream gzos = null;
/*      */ 
/*      */     
/*  323 */     int gzip = options & 0x2;
/*  324 */     int dontBreakLines = options & 0x8;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  329 */       baos = new ByteArrayOutputStream();
/*  330 */       b64os = new OutputStream(baos, 0x1 | dontBreakLines);
/*      */ 
/*      */       
/*  333 */       if (gzip == 2) {
/*      */         
/*  335 */         gzos = new GZIPOutputStream(b64os);
/*  336 */         oos = new ObjectOutputStream(gzos);
/*      */       } else {
/*      */         
/*  339 */         oos = new ObjectOutputStream(b64os);
/*      */       } 
/*  341 */       oos.writeObject(serializableObject);
/*      */     }
/*  343 */     catch (IOException e) {
/*      */       
/*  345 */       e.printStackTrace();
/*  346 */       return null;
/*      */     } finally {
/*      */ 
/*      */       
/*  350 */       try { if (oos != null) oos.close();  } catch (Exception exception) {} 
/*  351 */       try { if (gzos != null) gzos.close();  } catch (Exception exception) {} 
/*  352 */       try { if (b64os != null) b64os.close();  } catch (Exception exception) {} 
/*  353 */       try { if (baos != null) baos.close();  } catch (Exception exception) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  359 */       return new String(baos.toByteArray(), "UTF-8");
/*      */     }
/*  361 */     catch (UnsupportedEncodingException uue) {
/*      */       
/*  363 */       return new String(baos.toByteArray());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeBytes(byte[] source) {
/*  379 */     return encodeBytes(source, 0, source.length, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeBytes(byte[] source, int options) {
/*  406 */     return encodeBytes(source, 0, source.length, options);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeBytes(byte[] source, int off, int len) {
/*  421 */     return encodeBytes(source, off, len, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeBytes(byte[] source, int off, int len, int options) {
/*  451 */     int dontBreakLines = options & 0x8;
/*  452 */     int gzip = options & 0x2;
/*      */ 
/*      */     
/*  455 */     if (gzip == 2) {
/*      */       
/*  457 */       ByteArrayOutputStream baos = null;
/*  458 */       GZIPOutputStream gzos = null;
/*  459 */       OutputStream b64os = null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  465 */         baos = new ByteArrayOutputStream();
/*  466 */         b64os = new OutputStream(baos, 0x1 | dontBreakLines);
/*  467 */         gzos = new GZIPOutputStream(b64os);
/*      */         
/*  469 */         gzos.write(source, off, len);
/*  470 */         gzos.close();
/*      */       }
/*  472 */       catch (IOException iOException) {
/*      */         
/*  474 */         iOException.printStackTrace();
/*  475 */         return null;
/*      */       } finally {
/*      */ 
/*      */         
/*  479 */         try { gzos.close(); } catch (Exception exception) {} 
/*  480 */         try { b64os.close(); } catch (Exception exception) {} 
/*  481 */         try { baos.close(); } catch (Exception exception) {}
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  487 */         return new String(baos.toByteArray(), "UTF-8");
/*      */       }
/*  489 */       catch (UnsupportedEncodingException uue) {
/*      */         
/*  491 */         return new String(baos.toByteArray());
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  499 */     boolean breakLines = (dontBreakLines == 0);
/*      */     
/*  501 */     int len43 = len * 4 / 3;
/*  502 */     byte[] outBuff = new byte[len43 + ((len % 3 > 0) ? 4 : 0) + (breakLines ? (len43 / 76) : 0)];
/*      */ 
/*      */     
/*  505 */     int d = 0;
/*  506 */     int e = 0;
/*  507 */     int len2 = len - 2;
/*  508 */     int lineLength = 0;
/*  509 */     for (; d < len2; d += 3, e += 4) {
/*      */       
/*  511 */       encode3to4(source, d + off, 3, outBuff, e);
/*      */       
/*  513 */       lineLength += 4;
/*  514 */       if (breakLines && lineLength == 76) {
/*      */         
/*  516 */         outBuff[e + 4] = 10;
/*  517 */         e++;
/*  518 */         lineLength = 0;
/*      */       } 
/*      */     } 
/*      */     
/*  522 */     if (d < len) {
/*      */       
/*  524 */       encode3to4(source, d + off, len - d, outBuff, e);
/*  525 */       e += 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  532 */       return new String(outBuff, 0, e, "UTF-8");
/*      */     }
/*  534 */     catch (UnsupportedEncodingException uue) {
/*      */       
/*  536 */       return new String(outBuff, 0, e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset) {
/*  575 */     if (source[srcOffset + 2] == 61) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  580 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12;
/*      */ 
/*      */       
/*  583 */       destination[destOffset] = (byte)(outBuff >>> 16);
/*  584 */       return 1;
/*      */     } 
/*      */ 
/*      */     
/*  588 */     if (source[srcOffset + 3] == 61) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  594 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12 | (DECODABET[source[srcOffset + 2]] & 0xFF) << 6;
/*      */ 
/*      */ 
/*      */       
/*  598 */       destination[destOffset] = (byte)(outBuff >>> 16);
/*  599 */       destination[destOffset + 1] = (byte)(outBuff >>> 8);
/*  600 */       return 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  612 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12 | (DECODABET[source[srcOffset + 2]] & 0xFF) << 6 | DECODABET[source[srcOffset + 3]] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  618 */       destination[destOffset] = (byte)(outBuff >> 16);
/*  619 */       destination[destOffset + 1] = (byte)(outBuff >> 8);
/*  620 */       destination[destOffset + 2] = (byte)outBuff;
/*      */       
/*  622 */       return 3;
/*  623 */     } catch (Exception e) {
/*  624 */       System.out.println("" + source[srcOffset] + ": " + DECODABET[source[srcOffset]]);
/*  625 */       System.out.println("" + source[srcOffset + 1] + ": " + DECODABET[source[srcOffset + 1]]);
/*  626 */       System.out.println("" + source[srcOffset + 2] + ": " + DECODABET[source[srcOffset + 2]]);
/*  627 */       System.out.println("" + source[srcOffset + 3] + ": " + DECODABET[source[srcOffset + 3]]);
/*  628 */       return -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] decode(byte[] source, int off, int len) {
/*  649 */     int len34 = len * 3 / 4;
/*  650 */     byte[] outBuff = new byte[len34];
/*  651 */     int outBuffPosn = 0;
/*      */     
/*  653 */     byte[] b4 = new byte[4];
/*  654 */     int b4Posn = 0;
/*  655 */     int i = 0;
/*  656 */     byte sbiCrop = 0;
/*  657 */     byte sbiDecode = 0;
/*  658 */     for (i = off; i < off + len; i++) {
/*      */       
/*  660 */       sbiCrop = (byte)(source[i] & Byte.MAX_VALUE);
/*  661 */       sbiDecode = DECODABET[sbiCrop];
/*      */       
/*  663 */       if (sbiDecode >= -5) {
/*      */         
/*  665 */         if (sbiDecode >= -1)
/*      */         {
/*  667 */           b4[b4Posn++] = sbiCrop;
/*  668 */           if (b4Posn > 3)
/*      */           {
/*  670 */             outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn);
/*  671 */             b4Posn = 0;
/*      */ 
/*      */             
/*  674 */             if (sbiCrop == 61) {
/*      */               break;
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  683 */         System.err.println("Bad Base64 input character at " + i + ": " + source[i] + "(decimal)");
/*  684 */         return null;
/*      */       } 
/*      */     } 
/*      */     
/*  688 */     byte[] out = new byte[outBuffPosn];
/*  689 */     System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
/*  690 */     return out;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] decode(String s) {
/*      */     try {
/*  709 */       bytes = s.getBytes("UTF-8");
/*      */     }
/*  711 */     catch (UnsupportedEncodingException uee) {
/*      */       
/*  713 */       bytes = s.getBytes();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  718 */     byte[] bytes = decode(bytes, 0, bytes.length);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  723 */     if (bytes != null && bytes.length >= 4) {
/*      */ 
/*      */       
/*  726 */       int head = bytes[0] & 0xFF | bytes[1] << 8 & 0xFF00;
/*  727 */       if (35615 == head) {
/*      */         
/*  729 */         ByteArrayInputStream bais = null;
/*  730 */         GZIPInputStream gzis = null;
/*  731 */         ByteArrayOutputStream baos = null;
/*  732 */         byte[] buffer = new byte[2048];
/*  733 */         int length = 0;
/*      */ 
/*      */         
/*      */         try {
/*  737 */           baos = new ByteArrayOutputStream();
/*  738 */           bais = new ByteArrayInputStream(bytes);
/*  739 */           gzis = new GZIPInputStream(bais);
/*      */           
/*  741 */           while ((length = gzis.read(buffer)) >= 0)
/*      */           {
/*  743 */             baos.write(buffer, 0, length);
/*      */           }
/*      */ 
/*      */           
/*  747 */           bytes = baos.toByteArray();
/*      */         
/*      */         }
/*  750 */         catch (IOException iOException) {
/*      */ 
/*      */         
/*      */         } finally {
/*      */ 
/*      */           
/*  756 */           try { baos.close(); } catch (Exception exception) {} 
/*  757 */           try { gzis.close(); } catch (Exception exception) {} 
/*  758 */           try { bais.close(); } catch (Exception exception) {}
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  764 */     return bytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object decodeToObject(String encodedObject) {
/*  781 */     byte[] objBytes = decode(encodedObject);
/*      */     
/*  783 */     ByteArrayInputStream bais = null;
/*  784 */     ObjectInputStream ois = null;
/*  785 */     Object obj = null;
/*      */ 
/*      */     
/*      */     try {
/*  789 */       bais = new ByteArrayInputStream(objBytes);
/*  790 */       ois = new ObjectInputStream(bais);
/*      */       
/*  792 */       obj = ois.readObject();
/*      */     }
/*  794 */     catch (IOException e) {
/*      */       
/*  796 */       e.printStackTrace();
/*  797 */       obj = null;
/*      */     }
/*  799 */     catch (ClassNotFoundException e) {
/*      */       
/*  801 */       e.printStackTrace();
/*  802 */       obj = null;
/*      */     } finally {
/*      */ 
/*      */       
/*  806 */       try { bais.close(); } catch (Exception exception) {} 
/*  807 */       try { ois.close(); } catch (Exception exception) {}
/*      */     } 
/*      */     
/*  810 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean encodeToFile(byte[] dataToEncode, String filename) {
/*  826 */     boolean success = false;
/*  827 */     OutputStream bos = null;
/*  828 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  831 */       fos = new FileOutputStream(filename);
/*  832 */       bos = new OutputStream(fos, 1);
/*  833 */       bos.write(dataToEncode);
/*  834 */       success = true;
/*      */     }
/*  836 */     catch (IOException e) {
/*      */ 
/*      */       
/*  839 */       success = false;
/*      */     } finally {
/*      */ 
/*      */       
/*  843 */       try { bos.close(); } catch (Exception exception) {}
/*      */ 
/*      */       
/*      */       try {
/*  847 */         if (fos != null) {
/*  848 */           fos.close();
/*      */         }
/*  850 */       } catch (IOException ex) {
/*      */         
/*  852 */         ex.printStackTrace();
/*      */       } 
/*      */     } 
/*      */     
/*  856 */     return success;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean decodeToFile(String dataToDecode, String filename) {
/*  871 */     boolean success = false;
/*  872 */     OutputStream bos = null;
/*  873 */     FileOutputStream fos = null;
/*      */     
/*      */     try {
/*  876 */       fos = new FileOutputStream(filename);
/*  877 */       bos = new OutputStream(fos, 0);
/*  878 */       bos.write(dataToDecode.getBytes("UTF-8"));
/*  879 */       success = true;
/*      */     }
/*  881 */     catch (IOException e) {
/*      */       
/*  883 */       success = false;
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  890 */         if (fos != null) {
/*  891 */           fos.close();
/*      */         }
/*  893 */       } catch (IOException ex) {
/*      */         
/*  895 */         ex.printStackTrace();
/*      */       }  
/*  897 */       try { bos.close(); } catch (Exception exception) {}
/*      */     } 
/*      */     
/*  900 */     return success;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] decodeFromFile(String filename) {
/*  917 */     byte[] decodedData = null;
/*  918 */     InputStream bis = null;
/*  919 */     FileInputStream fis = null;
/*  920 */     BufferedInputStream bufis = null;
/*      */ 
/*      */     
/*      */     try {
/*  924 */       File file = new File(filename);
/*  925 */       byte[] buffer = null;
/*  926 */       int length = 0;
/*  927 */       int numBytes = 0;
/*      */ 
/*      */       
/*  930 */       if (file.length() > 2147483647L) {
/*      */         
/*  932 */         System.err.println("File is too big for this convenience method (" + file.length() + " bytes).");
/*  933 */         return null;
/*      */       } 
/*  935 */       buffer = new byte[(int)file.length()];
/*  936 */       fis = new FileInputStream(file);
/*      */       
/*  938 */       bufis = new BufferedInputStream(fis);
/*  939 */       bis = new InputStream(bufis, 0);
/*      */ 
/*      */       
/*  942 */       while ((numBytes = bis.read(buffer, length, 4096)) >= 0) {
/*  943 */         length += numBytes;
/*      */       }
/*      */       
/*  946 */       decodedData = new byte[length];
/*  947 */       System.arraycopy(buffer, 0, decodedData, 0, length);
/*      */     
/*      */     }
/*  950 */     catch (IOException e) {
/*      */       
/*  952 */       System.err.println("Error decoding from file " + filename);
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  959 */         if (bufis != null) {
/*  960 */           bufis.close();
/*      */         }
/*  962 */       } catch (IOException ex) {
/*      */         
/*  964 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/*  968 */         if (fis != null) {
/*  969 */           fis.close();
/*      */         }
/*  971 */       } catch (IOException ex) {
/*      */         
/*  973 */         ex.printStackTrace();
/*      */       }  
/*  975 */       try { bis.close(); } catch (Exception exception) {}
/*      */     } 
/*      */     
/*  978 */     return decodedData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeFromFile(String filename) {
/*  994 */     String encodedData = null;
/*  995 */     InputStream bis = null;
/*  996 */     FileInputStream fis = null;
/*  997 */     BufferedInputStream bufis = null;
/*      */ 
/*      */     
/*      */     try {
/* 1001 */       File file = new File(filename);
/* 1002 */       byte[] buffer = new byte[(int)(file.length() * 1.4D)];
/* 1003 */       int length = 0;
/* 1004 */       int numBytes = 0;
/* 1005 */       fis = new FileInputStream(file);
/* 1006 */       bufis = new BufferedInputStream(fis);
/*      */       
/* 1008 */       bis = new InputStream(bufis, 1);
/*      */ 
/*      */       
/* 1011 */       while ((numBytes = bis.read(buffer, length, 4096)) >= 0) {
/* 1012 */         length += numBytes;
/*      */       }
/*      */       
/* 1015 */       encodedData = new String(buffer, 0, length, "UTF-8");
/*      */     
/*      */     }
/* 1018 */     catch (IOException e) {
/*      */       
/* 1020 */       System.err.println("Error encoding from file " + filename);
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/* 1027 */         if (bufis != null) {
/* 1028 */           bufis.close();
/*      */         }
/* 1030 */       } catch (IOException ex) {
/*      */         
/* 1032 */         ex.printStackTrace();
/*      */       } 
/*      */       
/*      */       try {
/* 1036 */         if (fis != null) {
/* 1037 */           fis.close();
/*      */         }
/* 1039 */       } catch (IOException ex) {
/*      */         
/* 1041 */         ex.printStackTrace();
/*      */       }  
/* 1043 */       try { bis.close(); } catch (Exception exception) {}
/*      */     } 
/*      */     
/* 1046 */     return encodedData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class InputStream
/*      */     extends FilterInputStream
/*      */   {
/*      */     private boolean encode;
/*      */ 
/*      */ 
/*      */     
/*      */     private int position;
/*      */ 
/*      */ 
/*      */     
/*      */     private byte[] buffer;
/*      */ 
/*      */ 
/*      */     
/*      */     private int bufferLength;
/*      */ 
/*      */ 
/*      */     
/*      */     private int numSigBytes;
/*      */ 
/*      */ 
/*      */     
/*      */     private int lineLength;
/*      */ 
/*      */     
/*      */     private boolean breakLines;
/*      */ 
/*      */ 
/*      */     
/*      */     public InputStream(java.io.InputStream in) {
/* 1083 */       this(in, 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public InputStream(java.io.InputStream in, int options) {
/* 1110 */       super(in);
/* 1111 */       this.breakLines = ((options & 0x8) != 8);
/* 1112 */       this.encode = ((options & 0x1) == 1);
/* 1113 */       this.bufferLength = this.encode ? 4 : 3;
/* 1114 */       this.buffer = new byte[this.bufferLength];
/* 1115 */       this.position = -1;
/* 1116 */       this.lineLength = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int read() throws IOException {
/* 1129 */       if (this.position < 0)
/*      */       {
/* 1131 */         if (this.encode) {
/*      */           
/* 1133 */           byte[] b3 = new byte[3];
/* 1134 */           int numBinaryBytes = 0;
/* 1135 */           for (int i = 0; i < 3; i++) {
/*      */ 
/*      */             
/*      */             try {
/* 1139 */               int b = this.in.read();
/*      */ 
/*      */               
/* 1142 */               if (b >= 0)
/*      */               {
/* 1144 */                 b3[i] = (byte)b;
/* 1145 */                 numBinaryBytes++;
/*      */               }
/*      */             
/*      */             }
/* 1149 */             catch (IOException e) {
/*      */ 
/*      */               
/* 1152 */               if (i == 0) {
/* 1153 */                 throw e;
/*      */               }
/*      */             } 
/*      */           } 
/*      */           
/* 1158 */           if (numBinaryBytes > 0)
/*      */           {
/* 1160 */             Base64.encode3to4(b3, 0, numBinaryBytes, this.buffer, 0);
/* 1161 */             this.position = 0;
/* 1162 */             this.numSigBytes = 4;
/*      */           }
/*      */           else
/*      */           {
/* 1166 */             return -1;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1173 */           byte[] b4 = new byte[4];
/* 1174 */           int i = 0;
/* 1175 */           for (i = 0; i < 4; i++) {
/*      */ 
/*      */             
/* 1178 */             int b = 0; do {
/* 1179 */               b = this.in.read();
/* 1180 */             } while (b >= 0 && Base64.DECODABET[b & 0x7F] <= -5);
/*      */             
/* 1182 */             if (b < 0) {
/*      */               break;
/*      */             }
/* 1185 */             b4[i] = (byte)b;
/*      */           } 
/*      */           
/* 1188 */           if (i == 4) {
/*      */             
/* 1190 */             this.numSigBytes = Base64.decode4to3(b4, 0, this.buffer, 0);
/* 1191 */             this.position = 0;
/*      */           } else {
/* 1193 */             if (i == 0) {
/* 1194 */               return -1;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1199 */             throw new IOException("Improperly padded Base64 input.");
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1206 */       if (this.position >= 0) {
/*      */ 
/*      */         
/* 1209 */         if (this.position >= this.numSigBytes) {
/* 1210 */           return -1;
/*      */         }
/* 1212 */         if (this.encode && this.breakLines && this.lineLength >= 76) {
/*      */           
/* 1214 */           this.lineLength = 0;
/* 1215 */           return 10;
/*      */         } 
/*      */ 
/*      */         
/* 1219 */         this.lineLength++;
/*      */ 
/*      */ 
/*      */         
/* 1223 */         int b = this.buffer[this.position++];
/*      */         
/* 1225 */         if (this.position >= this.bufferLength) {
/* 1226 */           this.position = -1;
/*      */         }
/* 1228 */         return b & 0xFF;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1237 */       throw new IOException("Error in Base64 code reading stream.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int read(byte[] dest, int off, int len) throws IOException {
/*      */       int i;
/* 1258 */       for (i = 0; i < len; i++) {
/*      */         
/* 1260 */         int b = read();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1265 */         if (b >= 0)
/* 1266 */         { dest[off + i] = (byte)b; }
/* 1267 */         else { if (i == 0)
/* 1268 */             return -1; 
/*      */           break; }
/*      */       
/*      */       } 
/* 1272 */       return i;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class OutputStream
/*      */     extends FilterOutputStream
/*      */   {
/*      */     private boolean encode;
/*      */ 
/*      */ 
/*      */     
/*      */     private int position;
/*      */ 
/*      */ 
/*      */     
/*      */     private byte[] buffer;
/*      */ 
/*      */ 
/*      */     
/*      */     private int bufferLength;
/*      */ 
/*      */ 
/*      */     
/*      */     private int lineLength;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean breakLines;
/*      */ 
/*      */     
/*      */     private byte[] b4;
/*      */ 
/*      */     
/*      */     private boolean suspendEncoding;
/*      */ 
/*      */ 
/*      */     
/*      */     public OutputStream(java.io.OutputStream out) {
/* 1313 */       this(out, 1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public OutputStream(java.io.OutputStream out, int options) {
/* 1339 */       super(out);
/* 1340 */       this.breakLines = ((options & 0x8) != 8);
/* 1341 */       this.encode = ((options & 0x1) == 1);
/* 1342 */       this.bufferLength = this.encode ? 3 : 4;
/* 1343 */       this.buffer = new byte[this.bufferLength];
/* 1344 */       this.position = 0;
/* 1345 */       this.lineLength = 0;
/* 1346 */       this.suspendEncoding = false;
/* 1347 */       this.b4 = new byte[4];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void write(int theByte) throws IOException {
/* 1366 */       if (this.suspendEncoding) {
/*      */         
/* 1368 */         this.out.write(theByte);
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 1373 */       if (this.encode) {
/*      */         
/* 1375 */         this.buffer[this.position++] = (byte)theByte;
/* 1376 */         if (this.position >= this.bufferLength)
/*      */         {
/* 1378 */           this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength));
/*      */           
/* 1380 */           this.lineLength += 4;
/* 1381 */           if (this.breakLines && this.lineLength >= 76) {
/*      */             
/* 1383 */             this.out.write(10);
/* 1384 */             this.lineLength = 0;
/*      */           } 
/*      */           
/* 1387 */           this.position = 0;
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1395 */       else if (Base64.DECODABET[theByte & 0x7F] > -5) {
/*      */         
/* 1397 */         this.buffer[this.position++] = (byte)theByte;
/* 1398 */         if (this.position >= this.bufferLength)
/*      */         {
/* 1400 */           int len = Base64.decode4to3(this.buffer, 0, this.b4, 0);
/* 1401 */           this.out.write(this.b4, 0, len);
/*      */           
/* 1403 */           this.position = 0;
/*      */         }
/*      */       
/* 1406 */       } else if (Base64.DECODABET[theByte & 0x7F] != -5) {
/*      */         
/* 1408 */         throw new IOException("Invalid character in Base64 data.");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void write(byte[] theBytes, int off, int len) throws IOException {
/* 1427 */       if (this.suspendEncoding) {
/*      */         
/* 1429 */         this.out.write(theBytes, off, len);
/*      */         
/*      */         return;
/*      */       } 
/* 1433 */       for (int i = 0; i < len; i++)
/*      */       {
/* 1435 */         write(theBytes[off + i]);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void flushBase64() throws IOException {
/* 1448 */       if (this.position > 0)
/*      */       {
/* 1450 */         if (this.encode) {
/*      */           
/* 1452 */           this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position));
/* 1453 */           this.position = 0;
/*      */         }
/*      */         else {
/*      */           
/* 1457 */           throw new IOException("Base64 input not properly padded.");
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() throws IOException {
/* 1472 */       flushBase64();
/*      */ 
/*      */ 
/*      */       
/* 1476 */       super.close();
/*      */       
/* 1478 */       this.buffer = null;
/* 1479 */       this.out = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void suspendEncoding() throws IOException {
/* 1493 */       flushBase64();
/* 1494 */       this.suspendEncoding = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void resumeEncoding() {
/* 1507 */       this.suspendEncoding = false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\Base64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */