/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import java.io.Serializable;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.text.Collator;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.zip.CRC32;
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
/*     */ public class StringUtil
/*     */ {
/*     */   public static String merge(String[] parts, char delim) {
/*  30 */     if (parts == null || parts.length == 0)
/*  31 */       return null; 
/*  32 */     StringBuilder sb = new StringBuilder();
/*  33 */     for (int i = 0; i < parts.length; i++) {
/*     */       
/*  35 */       sb.append(parts[i]);
/*  36 */       if (i < parts.length - 1)
/*  37 */         sb.append(delim); 
/*     */     } 
/*  39 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] split(String str, char delim) {
/*  44 */     return split(str, String.valueOf(delim));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int indexOfIgnoreCase(String s, String part) {
/*  49 */     if (s == null)
/*  50 */       return -1; 
/*  51 */     String s2 = s.toLowerCase(Locale.UK);
/*  52 */     String part2 = part.toLowerCase(Locale.UK);
/*  53 */     return s2.indexOf(part2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int lastIndexOfIgnoreCase(String s, String part) {
/*  58 */     if (s == null)
/*  59 */       return -1; 
/*  60 */     String s2 = s.toLowerCase(Locale.UK);
/*  61 */     String part2 = part.toLowerCase(Locale.UK);
/*  62 */     return s2.lastIndexOf(part2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padLeft(String s, char ch, int len) {
/*  67 */     StringBuilder buf = new StringBuilder(Math.max(len, s.length()));
/*  68 */     for (int i = s.length(); i < len; i++)
/*  69 */       buf.append(ch); 
/*  70 */     buf.append(s);
/*  71 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padLeftFirm(String s, char ch) {
/*  76 */     int length = 3;
/*  77 */     if (JLbsConstants.checkAppCloud())
/*     */     {
/*  79 */       if (s.length() <= 3) {
/*  80 */         length = 3;
/*     */       } else {
/*  82 */         length = 5;
/*     */       }  } 
/*  84 */     StringBuilder buf = new StringBuilder(Math.max(length, s.length()));
/*  85 */     for (int i = s.length(); i < length; i++)
/*  86 */       buf.append(ch); 
/*  87 */     buf.append(s);
/*  88 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padLeftFirm(String s) {
/*  93 */     int length = 3;
/*  94 */     if (JLbsConstants.checkAppCloud())
/*     */     {
/*  96 */       if (s.length() <= 3) {
/*  97 */         length = 3;
/*     */       } else {
/*  99 */         length = 5;
/*     */       }  } 
/* 101 */     return padLeft(s, ' ', length);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padLeft(String s, int len) {
/* 106 */     return padLeft(s, ' ', len);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padRight(String s, char ch, int len) {
/* 111 */     StringBuilder buf = new StringBuilder(Math.max(len, s.length()));
/* 112 */     buf.append(s);
/* 113 */     for (int i = s.length(); i < len; i++)
/* 114 */       buf.append(ch); 
/* 115 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padRight(String s, int len) {
/* 120 */     return padRight(s, ' ', len);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] split(String str, String delim) {
/* 125 */     return split(str, delim, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] split(String str, String delim, boolean trim) {
/* 130 */     return JLbsStringUtil.split(str, delim, trim);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String s) {
/* 135 */     return (s == null || s.length() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isWhiteSpace(String s) {
/* 140 */     if (isEmpty(s)) {
/* 141 */       return true;
/*     */     }
/* 143 */     return s.trim().equals("");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getArrayIndex(String name, int[] index) {
/* 148 */     index[0] = -1;
/* 149 */     int idx = name.indexOf('[');
/* 150 */     if (idx != -1) {
/*     */       
/* 152 */       String idxStr = name.substring(idx + 1, name.length() - 1);
/* 153 */       index[0] = Integer.valueOf(idxStr).intValue();
/*     */       
/* 155 */       name = name.substring(0, idx);
/*     */     } 
/*     */     
/* 158 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equals(String a, String b) {
/* 163 */     if (a == null)
/* 164 */       a = ""; 
/* 165 */     if (b == null) {
/* 166 */       b = "";
/*     */     }
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
/* 179 */     return a.equals(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equalsIgnoreCase(String a, String b) {
/* 184 */     if (a == null)
/* 185 */       a = ""; 
/* 186 */     if (b == null) {
/* 187 */       b = "";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     return a.equalsIgnoreCase(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toCanonicalString(Calendar date) {
/* 203 */     return toCanonicalString(date, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toCanonicalString(Calendar date, boolean includeTime) {
/* 208 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 210 */     sb.append(date.get(1));
/* 211 */     sb.append("-");
/*     */     
/* 213 */     NumberFormat nf = NumberFormat.getInstance();
/* 214 */     nf.setMinimumIntegerDigits(2);
/*     */     
/* 216 */     double d = (date.get(2) + 1);
/* 217 */     sb.append(nf.format(d));
/* 218 */     sb.append("-");
/*     */     
/* 220 */     d = date.get(5);
/* 221 */     sb.append(nf.format(d));
/*     */     
/* 223 */     if (includeTime) {
/*     */       
/* 225 */       sb.append(" " + date.get(11));
/* 226 */       d = date.get(12);
/* 227 */       sb.append(":" + nf.format(d));
/* 228 */       d = date.get(13);
/* 229 */       sb.append(":" + nf.format(d));
/*     */     } 
/*     */     
/* 232 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toCanonicalString(long millis, boolean includeTime) {
/* 237 */     GregorianCalendar cal = new GregorianCalendar();
/* 238 */     cal.setTimeInMillis(millis);
/* 239 */     return toCanonicalString(cal, includeTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toDateString(Calendar cal) {
/* 244 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS");
/* 245 */     return sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toDateString(long timeInMillis) {
/* 250 */     GregorianCalendar cal = new GregorianCalendar();
/* 251 */     cal.setTimeInMillis(timeInMillis);
/*     */     
/* 253 */     return toDateString(cal);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String split(String str, int length) {
/* 258 */     StringBuilder sb = new StringBuilder();
/* 259 */     int i = 0;
/* 260 */     int strLen = str.length();
/* 261 */     while (i < strLen) {
/*     */       
/* 263 */       int off = Math.min(i + length, strLen);
/* 264 */       sb.append(str.substring(i, off));
/*     */       
/* 266 */       i += length;
/*     */     } 
/*     */     
/* 269 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList splitByLength(String str, int partLength) {
/* 274 */     ArrayList<String> list = new ArrayList();
/* 275 */     int i = 0;
/* 276 */     int strLen = str.length();
/* 277 */     while (i < strLen) {
/*     */       
/* 279 */       int off = Math.min(i + partLength, strLen);
/* 280 */       list.add(str.substring(i, off));
/* 281 */       i += partLength;
/*     */     } 
/*     */     
/* 284 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toSerializedString(Object obj) {
/* 289 */     if (obj instanceof Serializable) {
/* 290 */       return Base64.encodeObject((Serializable)obj, 0);
/*     */     }
/* 292 */     return "";
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
/*     */   public static Object getSerializedObject(String str) {
/* 327 */     return Base64.decodeToObject(str);
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
/*     */   public static String remove(String str, char c) {
/* 359 */     String s = str;
/*     */     
/* 361 */     int idx = s.indexOf(c);
/* 362 */     while (idx != -1) {
/*     */       
/* 364 */       if (idx == 0) {
/* 365 */         s = s.substring(1);
/*     */       } else {
/* 367 */         s = s.substring(0, idx).concat(s.substring(idx + 1));
/*     */       } 
/* 369 */       idx = s.indexOf(c);
/*     */     } 
/*     */     
/* 372 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean startsWith(String str, String subStr, boolean ignoreCase) {
/* 377 */     if (!ignoreCase) {
/* 378 */       return str.startsWith(subStr);
/*     */     }
/* 380 */     return str.regionMatches(ignoreCase, 0, subStr, 0, subStr.length());
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
/*     */   public static String replaceAll(String str, String match, String replacement) {
/* 395 */     return JLbsStringUtil.replaceAll(str, match, replacement);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar toCalendar(String strVal, boolean includeTime) {
/* 400 */     StringTokenizer tokens = new StringTokenizer(strVal, "-");
/* 401 */     if (tokens.countTokens() < 2 && strVal.length() > 5)
/* 402 */       return toCalendarFromLong(strVal, includeTime); 
/* 403 */     String tk = "";
/* 404 */     int year = 0;
/* 405 */     int month = 0;
/* 406 */     int day = 0;
/* 407 */     int i = 0;
/* 408 */     int hour = 0;
/* 409 */     int min = 0;
/* 410 */     int sec = 0;
/* 411 */     int millis = 0;
/*     */     
/* 413 */     while (tokens.hasMoreTokens()) {
/*     */       int pos;
/* 415 */       tk = tokens.nextToken();
/* 416 */       switch (i) {
/*     */         
/*     */         case 0:
/* 419 */           year = Integer.valueOf(tk).intValue();
/*     */           break;
/*     */         case 1:
/* 422 */           month = Integer.valueOf(tk).intValue() - 1;
/*     */           break;
/*     */         case 2:
/* 425 */           pos = tk.indexOf(' ');
/* 426 */           if (pos != -1) {
/*     */             
/* 428 */             String timeStr = tk.substring(pos + 1);
/* 429 */             tk = tk.substring(0, pos);
/*     */             
/* 431 */             if (includeTime && !isEmpty(timeStr)) {
/*     */               
/* 433 */               String[] parts = split(timeStr, ':');
/* 434 */               if (parts.length >= 2) {
/*     */                 
/* 436 */                 hour = Integer.parseInt(parts[0]);
/* 437 */                 min = Integer.parseInt(parts[1]);
/*     */               } 
/* 439 */               if (parts.length >= 3) {
/*     */                 
/* 441 */                 String[] milliSeconds = parts[2].split("\\.");
/* 442 */                 if (milliSeconds.length == 1) {
/* 443 */                   sec = Integer.parseInt(parts[2]);
/* 444 */                 } else if (milliSeconds.length > 1) {
/*     */                   
/* 446 */                   sec = Integer.parseInt(milliSeconds[0]);
/* 447 */                   millis = Integer.parseInt(milliSeconds[1]);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 452 */           day = Integer.valueOf(tk).intValue();
/*     */           break;
/*     */       } 
/* 455 */       i++;
/*     */     } 
/* 457 */     Calendar cal = new GregorianCalendar(year, month, day, hour, min, sec);
/* 458 */     cal.set(14, millis);
/* 459 */     return cal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Calendar toCalendarFromLong(String value, boolean includeTime) {
/*     */     try {
/* 466 */       if (!isEmpty(value))
/*     */       {
/* 468 */         Calendar cal = Calendar.getInstance();
/* 469 */         cal.setTimeInMillis(Long.parseLong(value));
/* 470 */         return cal;
/*     */       }
/*     */     
/* 473 */     } catch (Exception e) {
/*     */       
/* 475 */       LbsConsole.getLogger("com.lbs.util.StringUtil").error(e, e);
/* 476 */       return null;
/*     */     } 
/*     */     
/* 479 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String reverse(String str) {
/* 485 */     if (isEmpty(str)) {
/* 486 */       return str;
/*     */     }
/* 488 */     StringBuilder buffer = new StringBuilder();
/* 489 */     for (int i = str.length() - 1; i >= 0; i--)
/*     */     {
/* 491 */       buffer.append(str.charAt(i));
/*     */     }
/*     */     
/* 494 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 499 */     System.out.println(startsWith("Like any other", "lIKe", true));
/* 500 */     System.out.println(startsWith("Like any other", "lIKe", false));
/* 501 */     System.out.println(startsWith("LIKE any other", "LIKE", false));
/* 502 */     System.out.println(startsWith("LIKE any other", "LIKE", true));
/*     */     
/* 504 */     System.out.println(remove("ali@veli", '@'));
/* 505 */     System.out.println(remove("@veli", '@'));
/*     */     
/* 507 */     Calendar now = Calendar.getInstance();
/* 508 */     String x = toCanonicalString(now, true);
/*     */     
/* 510 */     Calendar now2 = toCalendar(x, true);
/* 511 */     System.out.println(now2.get(11) + " : " + now2.get(12) + " : " + now2.get(13));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String subStringBefore(String text, String untilFirst) {
/* 516 */     int i = text.indexOf(untilFirst);
/* 517 */     if (i == -1)
/* 518 */       return text; 
/* 519 */     return text.substring(0, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String subStringAfter(String text, String afterFirst) {
/* 524 */     return text.substring(text.indexOf(afterFirst) + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String subStringOf(String text, int to) {
/* 529 */     return (text == null) ? null : (
/*     */       
/* 531 */       (text.length() > to) ? text
/* 532 */       .substring(0, to) : text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNameFromQualified(String s) {
/* 538 */     if (s == null)
/* 539 */       return s; 
/* 540 */     int idx = s.lastIndexOf('.');
/* 541 */     if (idx > 0)
/* 542 */       return s.substring(idx + 1); 
/* 543 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equalEncodings(String str1, String str2) {
/* 548 */     int EOS = 65536;
/* 549 */     char INT_SPACE = ' ';
/*     */     
/* 551 */     int len1 = str1.length();
/* 552 */     int len2 = str2.length();
/*     */ 
/*     */     
/* 555 */     for (int i1 = 0, i2 = 0; i1 < len1 || i2 < len2; ) {
/*     */ 
/*     */ 
/*     */       
/* 559 */       int c1 = (i1 >= len1) ? EOS : str1.charAt(i1++);
/*     */ 
/*     */       
/* 562 */       int c2 = (i2 >= len2) ? EOS : str2.charAt(i2++);
/*     */ 
/*     */       
/* 565 */       if (c1 == c2) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 571 */       while (c1 <= INT_SPACE || c1 == 95 || c1 == 45)
/*     */       {
/*     */ 
/*     */         
/* 575 */         c1 = (i1 >= len1) ? EOS : str1.charAt(i1++);
/*     */       }
/* 577 */       while (c2 <= INT_SPACE || c2 == 95 || c2 == 45)
/*     */       {
/*     */ 
/*     */         
/* 581 */         c2 = (i2 >= len2) ? EOS : str2.charAt(i2++);
/*     */       }
/*     */       
/* 584 */       if (c1 != c2) {
/*     */ 
/*     */         
/* 587 */         if (c1 == EOS || c2 == EOS)
/*     */         {
/* 589 */           return false;
/*     */         }
/* 591 */         if (c1 < 127) {
/*     */           
/* 593 */           if (c1 <= 90 && c1 >= 65)
/*     */           {
/* 595 */             c1 += 32;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 600 */           c1 = Character.toLowerCase((char)c1);
/*     */         } 
/* 602 */         if (c2 < 127) {
/*     */           
/* 604 */           if (c2 <= 90 && c2 >= 65)
/*     */           {
/* 606 */             c2 += 32;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 611 */           c2 = Character.toLowerCase((char)c2);
/*     */         } 
/* 613 */         if (c1 != c2)
/*     */         {
/* 615 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 621 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int crc32(String string) {
/* 626 */     if (string == null) {
/* 627 */       return 0;
/*     */     }
/*     */     try {
/* 630 */       return crc32(string.getBytes("utf-8"));
/*     */     }
/* 632 */     catch (UnsupportedEncodingException e) {
/*     */       
/* 634 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int crc32(byte[] data) {
/* 640 */     if (data == null)
/* 641 */       return 0; 
/* 642 */     CRC32 crc = new CRC32();
/* 643 */     crc.update(data);
/* 644 */     return (int)crc.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isValidJavaIdentifier(String s) {
/* 650 */     if (s == null || s.length() == 0)
/*     */     {
/* 652 */       return false;
/*     */     }
/*     */     
/* 655 */     char[] c = s.toCharArray();
/* 656 */     if (!Character.isJavaIdentifierStart(c[0]))
/*     */     {
/* 658 */       return false;
/*     */     }
/*     */     
/* 661 */     for (int i = 1; i < c.length; i++) {
/*     */       
/* 663 */       if (!Character.isJavaIdentifierPart(c[i]))
/*     */       {
/* 665 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 669 */     return true;
/*     */   }
/*     */   
/* 672 */   static final Collator englishCollator = Collator.getInstance(Locale.ENGLISH);
/* 673 */   static final String[] keywords = new String[] { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isJavaKeyword(String keyword) {
/* 681 */     return (Arrays.binarySearch(keywords, keyword, englishCollator) >= 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\StringUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */