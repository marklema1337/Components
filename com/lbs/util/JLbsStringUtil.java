/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.InetAddress;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Locale;
/*     */ import java.util.Random;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class JLbsStringUtil
/*     */ {
/*  28 */   public static Pattern patternWildCard = Pattern.compile("\\*");
/*  29 */   public static Pattern rfc2822 = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String buildString(String... parts) {
/*  36 */     StringBuilder builder = new StringBuilder();
/*  37 */     if (parts != null) {
/*     */       byte b; int i; String[] arrayOfString;
/*  39 */       for (i = (arrayOfString = parts).length, b = 0; b < i; ) { String part = arrayOfString[b];
/*     */         
/*  41 */         builder.append(part); b++; }
/*     */     
/*     */     } 
/*  44 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String escapeJava(String str) {
/*     */     try {
/*  51 */       if (str == null)
/*  52 */         return null; 
/*  53 */       StringWriter writer = new StringWriter(str.length() * 2);
/*  54 */       escapeJavaStyleString(writer, str, false, false);
/*  55 */       return writer.toString();
/*     */     }
/*  57 */     catch (Exception e) {
/*     */       
/*  59 */       e.printStackTrace();
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String hex(char ch) {
/*  66 */     return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote, boolean escapeForwardSlash) throws IOException {
/*  72 */     if (out == null || str == null) {
/*     */       return;
/*     */     }
/*  75 */     int sz = str.length();
/*  76 */     for (int i = 0; i < sz; i++) {
/*     */       
/*  78 */       char ch = str.charAt(i);
/*     */ 
/*     */       
/*  81 */       if (ch > '࿿') {
/*     */         
/*  83 */         out.write("\\u" + hex(ch));
/*     */       }
/*  85 */       else if (ch > 'ÿ') {
/*     */         
/*  87 */         out.write("\\u0" + hex(ch));
/*     */       }
/*  89 */       else if (ch > '') {
/*     */         
/*  91 */         out.write("\\u00" + hex(ch));
/*     */       }
/*  93 */       else if (ch < ' ') {
/*     */         
/*  95 */         switch (ch) {
/*     */           
/*     */           case '\b':
/*  98 */             out.write(92);
/*  99 */             out.write(98);
/*     */             break;
/*     */           case '\n':
/* 102 */             out.write(92);
/* 103 */             out.write(110);
/*     */             break;
/*     */           case '\t':
/* 106 */             out.write(92);
/* 107 */             out.write(116);
/*     */             break;
/*     */           case '\f':
/* 110 */             out.write(92);
/* 111 */             out.write(102);
/*     */             break;
/*     */           case '\r':
/* 114 */             out.write(92);
/* 115 */             out.write(114);
/*     */             break;
/*     */           default:
/* 118 */             if (ch > '\017') {
/*     */               
/* 120 */               out.write("\\u00" + hex(ch));
/*     */               
/*     */               break;
/*     */             } 
/* 124 */             out.write("\\u000" + hex(ch));
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } else {
/* 131 */         switch (ch) {
/*     */           
/*     */           case '\'':
/* 134 */             if (escapeSingleQuote)
/*     */             {
/* 136 */               out.write(92);
/*     */             }
/* 138 */             out.write(39);
/*     */             break;
/*     */           case '"':
/* 141 */             out.write(92);
/* 142 */             out.write(34);
/*     */             break;
/*     */           case '\\':
/* 145 */             out.write(92);
/* 146 */             out.write(92);
/*     */             break;
/*     */           case '/':
/* 149 */             if (escapeForwardSlash)
/*     */             {
/* 151 */               out.write(92);
/*     */             }
/* 153 */             out.write(47);
/*     */             break;
/*     */           default:
/* 156 */             out.write(ch);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] split(String str, String delim, boolean trim) {
/* 165 */     StringTokenizer tok = new StringTokenizer(str, delim);
/*     */     
/* 167 */     int count = tok.countTokens();
/*     */     
/* 169 */     if (count == 0) {
/* 170 */       return null;
/*     */     }
/* 172 */     String[] res = new String[count];
/* 173 */     int i = 0;
/* 174 */     while (tok.hasMoreTokens()) {
/*     */       
/* 176 */       res[i] = tok.nextToken();
/* 177 */       if (trim)
/* 178 */         res[i] = res[i].trim(); 
/* 179 */       i++;
/*     */     } 
/*     */     
/* 182 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] split(String str, String delim) {
/* 187 */     StringTokenizer tok = new StringTokenizer(str, delim);
/*     */     
/* 189 */     int count = tok.countTokens();
/*     */     
/* 191 */     if (count == 0) {
/* 192 */       return null;
/*     */     }
/* 194 */     String[] res = new String[count];
/* 195 */     int i = 0;
/* 196 */     while (tok.hasMoreTokens()) {
/*     */       
/* 198 */       res[i] = tok.nextToken();
/* 199 */       i++;
/*     */     } 
/*     */     
/* 202 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] splitRevision(String str) {
/* 207 */     if (!isEmpty(str))
/*     */     {
/* 209 */       return str.split("@_@");
/*     */     }
/* 211 */     return new String[] { str };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String checkRegExp(String s) {
/* 217 */     StringBuilder buff = new StringBuilder();
/*     */     
/* 219 */     for (int i = 0; i < s.length(); i++) {
/*     */       
/* 221 */       char c = s.charAt(i);
/* 222 */       switch (c) {
/*     */         
/*     */         case '*':
/* 225 */           buff.append(".*");
/*     */           break;
/*     */         
/*     */         case '?':
/* 229 */           buff.append(".");
/*     */           break;
/*     */         
/*     */         default:
/* 233 */           if (!Character.isLetterOrDigit(c)) {
/* 234 */             buff.append("\\");
/*     */           }
/*     */           
/* 237 */           buff.append(String.valueOf(c)); break;
/*     */       } 
/* 239 */     }  return buff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String remove(String str, char c) {
/* 244 */     String s = str;
/*     */     
/* 246 */     int idx = s.indexOf(c);
/* 247 */     while (idx != -1) {
/*     */       
/* 249 */       if (idx == 0) {
/* 250 */         s = s.substring(1);
/*     */       } else {
/* 252 */         s = s.substring(0, idx).concat(s.substring(idx + 1));
/*     */       } 
/* 254 */       idx = s.indexOf(c);
/*     */     } 
/*     */     
/* 257 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isLiteral(char character) {
/* 262 */     String str = (new StringBuilder(String.valueOf(character))).toString();
/* 263 */     return Pattern.matches("\\w", str);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String trimRight(String s) {
/* 268 */     if (s != null && s.length() > 0) {
/*     */       
/* 270 */       StringBuilder buffer = new StringBuilder(s);
/* 271 */       int iLen = buffer.length();
/*     */ 
/*     */       
/* 274 */       while (Character.isSpaceChar(buffer.charAt(iLen - 1)))
/*     */       
/* 276 */       { buffer.delete(iLen - 1, iLen);
/* 277 */         iLen--;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 282 */         if (iLen <= 0)
/* 283 */           break;  }  return buffer.toString();
/*     */     } 
/* 285 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String trimLeft(String s) {
/* 290 */     if (s != null && s.length() > 0) {
/*     */       
/* 292 */       StringBuilder buffer = new StringBuilder(s);
/* 293 */       int iLen = buffer.length();
/*     */ 
/*     */       
/* 296 */       while (Character.isSpaceChar(buffer.charAt(0)))
/*     */       
/* 298 */       { buffer.delete(0, 1);
/* 299 */         iLen--;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 304 */         if (iLen <= 0)
/* 305 */           break;  }  return buffer.toString();
/*     */     } 
/* 307 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areEqual(String s1, String s2) {
/* 312 */     if (s1 == null || s2 == null)
/* 313 */       return (s1 == s2); 
/* 314 */     return (s1.compareTo(s2) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areEqualNoCase(String s1, String s2) {
/* 319 */     if (s1 == null || s2 == null)
/* 320 */       return (s1 == s2); 
/* 321 */     return (s1.compareToIgnoreCase(s2) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equals(String a, String b) {
/* 326 */     boolean ae = isEmpty(a);
/* 327 */     boolean be = isEmpty(b);
/*     */     
/* 329 */     if (ae && be) {
/* 330 */       return true;
/*     */     }
/* 332 */     if (ae || be) {
/* 333 */       return false;
/*     */     }
/* 335 */     return a.equals(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String s) {
/* 340 */     return !(s != null && s.length() != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmptyString(String s) {
/* 345 */     if (isEmpty(s))
/* 346 */       return true; 
/* 347 */     String str = "";
/* 348 */     str = replaceAll(s, " ", "");
/* 349 */     if (str.equals("''") || str.equals("\"\""))
/* 350 */       return true; 
/* 351 */     return isEmpty(str);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stripQuotes(String value) {
/* 356 */     if (value != null && value.length() > 0)
/*     */     {
/* 358 */       if (value.startsWith("\"") && value.endsWith("\""))
/* 359 */         return value.substring(1, value.length() - 1); 
/*     */     }
/* 361 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stripSingleQuotes(String value) {
/* 366 */     if (value != null && value.length() > 0)
/*     */     {
/* 368 */       if (value.startsWith("'") && value.endsWith("'"))
/* 369 */         return value.substring(1, value.length() - 1); 
/*     */     }
/* 371 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String format(String source, Object o) {
/* 376 */     return format(source, new Object[] { o });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String format(String source, Object o1, Object o2) {
/* 381 */     return format(source, new Object[] { o1, o2 });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String format(String source, Object o1, Object o2, Object o3) {
/* 386 */     return format(source, new Object[] { o1, o2, o3 });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String format(String source, Object[] arr) {
/* 391 */     if (source != null && arr != null && arr.length > 0) {
/*     */       
/* 393 */       StringBuilder buffer = new StringBuilder();
/* 394 */       int index = source.indexOf("%s");
/* 395 */       int count = 0;
/* 396 */       while (index >= 0 && count < arr.length) {
/*     */         
/* 398 */         buffer.append(source.substring(0, index));
/* 399 */         buffer.append((arr[count] != null) ? 
/* 400 */             arr[count] : 
/* 401 */             "");
/* 402 */         source = source.substring(index + 2);
/* 403 */         index = source.indexOf("%s");
/* 404 */         count++;
/*     */       } 
/* 406 */       buffer.append(source);
/* 407 */       return buffer.toString();
/*     */     } 
/* 409 */     return source;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getString(byte[] data) {
/* 414 */     if (data != null) {
/*     */       
/*     */       try {
/*     */         
/* 418 */         if (data.length == 0)
/* 419 */           return ""; 
/* 420 */         ByteArrayInputStream stream = new ByteArrayInputStream(data);
/* 421 */         String encoding = "UTF-8";
/* 422 */         if (data.length > 1)
/*     */         {
/* 424 */           if (data[0] == -1 && data[1] == -2) {
/* 425 */             encoding = "unicode";
/* 426 */           } else if (data[0] == -2 && data[1] == -1) {
/* 427 */             encoding = "unicode";
/* 428 */           } else if (data[0] == -17 && data[1] == -69 && data.length > 2 && data[2] == -65) {
/* 429 */             stream.skip(3L);
/*     */           }  } 
/* 431 */         InputStreamReader reader = new InputStreamReader(stream, encoding);
/* 432 */         char[] characters = new char[data.length];
/* 433 */         int read = reader.read(characters, 0, data.length);
/* 434 */         String str = new String(characters, 0, read);
/* 435 */         reader.close();
/* 436 */         return str;
/*     */       }
/* 438 */       catch (Exception exception) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 443 */           return new String(data);
/*     */         }
/* 445 */         catch (Exception exception1) {}
/*     */       } 
/*     */     }
/*     */     
/* 449 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSListJarName(int id) {
/* 454 */     int group = id / 100 * 100;
/* 455 */     if (id < 0 && id % 100 != 0)
/* 456 */       group -= 100; 
/* 457 */     return Integer.toString(group);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] tokenize(String s, String delim) {
/* 462 */     if (s != null && delim != null && delim.length() > 0) {
/*     */       
/* 464 */       StringTokenizer tok = new StringTokenizer(s, delim);
/* 465 */       int cnt = tok.countTokens();
/* 466 */       if (cnt > 0) {
/*     */         
/* 468 */         String[] result = new String[cnt];
/* 469 */         for (int i = 0; i < cnt; i++)
/* 470 */           result[i] = tok.nextToken(); 
/* 471 */         return result;
/*     */       } 
/*     */     } 
/* 474 */     return new String[] { s };
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean simpleRegExpMatch(String exp, String s) {
/* 479 */     if (areEqual(exp, s))
/* 480 */       return true; 
/* 481 */     if (exp != null && s != null) {
/*     */       
/* 483 */       if (s.trim().equals(exp.trim())) {
/* 484 */         return true;
/*     */       }
/* 486 */       int len = s.length();
/* 487 */       StringTokenizer tokenizer = new StringTokenizer(exp, "?*.", true);
/* 488 */       String[] tokens = new String[tokenizer.countTokens()];
/* 489 */       for (int i = 0; i < tokens.length; i++)
/*     */       {
/* 491 */         tokens[i] = tokenizer.nextToken();
/*     */       }
/* 493 */       return checkTokens(s, len, tokens, 0, 0);
/*     */     } 
/* 495 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean checkTokens(String s, int len, String[] tokens, int idx, int tokenIdx) {
/* 500 */     for (int i = tokenIdx; i < tokens.length; i++) {
/*     */       
/* 502 */       String token = tokens[i];
/* 503 */       if (token.equals("?") || token.equals(".")) {
/*     */         
/* 505 */         idx++;
/* 506 */         if (idx > len)
/* 507 */           return false; 
/* 508 */         tokenIdx++;
/*     */       }
/* 510 */       else if (token.equals("*")) {
/*     */         
/* 512 */         tokenIdx++;
/*     */       }
/*     */       else {
/*     */         
/* 516 */         boolean match = true;
/*     */         
/*     */         do {
/* 519 */           int tIdx = s.indexOf(token, idx);
/* 520 */           if (tIdx < 0 || (idx == 0 && tIdx > 0 && tokenIdx == 0))
/* 521 */             return false; 
/* 522 */           idx = tIdx + token.length();
/* 523 */           match = checkTokens(s, len, tokens, idx, tokenIdx + 1);
/*     */         }
/* 525 */         while (!match && idx < len);
/* 526 */         return match;
/*     */       } 
/*     */     } 
/* 529 */     if (idx < len)
/*     */     {
/* 531 */       return tokens[tokens.length - 1].equals("*");
/*     */     }
/* 533 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String breakIntoLines(String s, int width) {
/* 538 */     ArrayList<String> lines = breakIntoLines(s, width, 8);
/*     */     
/* 540 */     StringBuilder buffer = new StringBuilder();
/* 541 */     String lineText = null;
/* 542 */     if (lines != null)
/* 543 */       for (int i = 0; i < lines.size(); i++) {
/*     */         
/* 545 */         lineText = lines.get(i);
/* 546 */         buffer.append(lineText);
/* 547 */         buffer.append("\\n");
/*     */       }  
/* 549 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<String> breakIntoLines(String s, int width, int oneCharWidth) {
/* 554 */     if (s == null)
/* 555 */       return null; 
/* 556 */     int fromIndex = 0;
/* 557 */     int pos = 0;
/*     */ 
/*     */     
/* 560 */     ArrayList<String> lines = new ArrayList<>();
/* 561 */     String text = s;
/* 562 */     while (fromIndex != -1) {
/*     */       
/* 564 */       while (fromIndex < text.length() && text.charAt(fromIndex) == ' ') {
/*     */         
/* 566 */         fromIndex++;
/* 567 */         if (fromIndex >= text.length())
/*     */           break; 
/*     */       } 
/* 570 */       pos = fromIndex;
/* 571 */       int bestpos = -1;
/* 572 */       String largestString = null;
/* 573 */       while (pos >= fromIndex && pos < text.length()) {
/*     */         
/* 575 */         boolean bHardNewline = false;
/* 576 */         int newlinePos = text.indexOf('\n', pos);
/* 577 */         int spacePos = text.indexOf(' ', pos);
/* 578 */         if (newlinePos != -1 && (spacePos == -1 || (spacePos != -1 && newlinePos < spacePos))) {
/*     */           
/* 580 */           pos = newlinePos;
/* 581 */           bHardNewline = true;
/*     */         }
/*     */         else {
/*     */           
/* 585 */           pos = spacePos;
/* 586 */           bHardNewline = false;
/*     */         } 
/* 588 */         if (pos == -1) {
/* 589 */           s = text.substring(fromIndex);
/*     */         } else {
/* 591 */           s = text.substring(fromIndex, pos);
/* 592 */         }  if (s.length() < width) {
/*     */           
/* 594 */           largestString = s;
/* 595 */           bestpos = pos;
/* 596 */           if (bHardNewline)
/* 597 */             bestpos++; 
/* 598 */           if (pos == -1 || bHardNewline) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 603 */           pos++; continue;
/*     */         }  break;
/* 605 */       }  if (largestString == null) {
/*     */         
/* 607 */         int totalWidth = 0;
/* 608 */         pos = fromIndex;
/* 609 */         while (pos < text.length()) {
/*     */           
/* 611 */           if (totalWidth + oneCharWidth >= width && pos != fromIndex)
/*     */             break; 
/* 613 */           totalWidth += oneCharWidth;
/* 614 */           pos++;
/*     */         } 
/* 616 */         if (fromIndex == pos)
/*     */           break; 
/* 618 */         lines.add(text.substring(fromIndex, pos));
/* 619 */         fromIndex = pos;
/*     */         
/*     */         continue;
/*     */       } 
/* 623 */       lines.add(largestString);
/* 624 */       fromIndex = bestpos;
/*     */     } 
/*     */     
/* 627 */     return lines;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 632 */     System.out.println(simpleRegExpMatch("500", "5008"));
/* 633 */     System.out.println(simpleRegExpMatch("5008", "500"));
/* 634 */     System.out.println(simpleRegExpMatch("500*", "500"));
/* 635 */     System.out.println(simpleRegExpMatch("500*", "5008"));
/* 636 */     System.out.println(simpleRegExpMatch("50*", "5001"));
/* 637 */     System.out.println(simpleRegExpMatch("20*1", "2100"));
/* 638 */     System.out.println(simpleRegExpMatch("21?0", "2100"));
/* 639 */     System.out.println(simpleRegExpMatch("21*0", "2100"));
/* 640 */     System.out.println(simpleRegExpMatch("*210", "2100"));
/* 641 */     System.out.println(simpleRegExpMatch("*210*", "2100"));
/* 642 */     System.out.println(matchesSearchString("Deneme", "Dnm"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replace(String src, char oldChar, char newChar) {
/* 647 */     if (src == null || src.length() == 0)
/* 648 */       return src; 
/* 649 */     return src.replace(oldChar, newChar);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mergeParameters(String inStr, String[] paramStr, int[] param) {
/* 654 */     if (inStr == null)
/* 655 */       return ""; 
/* 656 */     for (int i = 0; i < Math.min(paramStr.length, param.length); i++) {
/* 657 */       inStr = replaceAll(inStr, "~" + param[i], paramStr[i]);
/*     */     }
/* 659 */     return inStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replaceAll(String str, String match, String replacement) {
/* 664 */     if (!isEmpty(str)) {
/*     */       
/* 666 */       int idx = str.indexOf(match);
/* 667 */       int replaceCnt = 0;
/* 668 */       while (idx >= 0 && replaceCnt < 1000) {
/*     */         
/* 670 */         str = String.valueOf(str.substring(0, idx)) + replacement + str.substring(idx + match.length());
/* 671 */         idx = str.indexOf(match, idx + replacement.length());
/* 672 */         replaceCnt++;
/*     */       } 
/*     */     } 
/* 675 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replaceFirst(String str, String match, String replacement) {
/* 680 */     if (!isEmpty(str)) {
/*     */       
/* 682 */       int idx = str.indexOf(match);
/* 683 */       if (idx >= 0)
/* 684 */         str = String.valueOf(str.substring(0, idx)) + replacement + str.substring(idx + match.length()); 
/*     */     } 
/* 686 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String padLeft(String s, char ch, int len) {
/* 691 */     StringBuilder buf = new StringBuilder(Math.max(len, s.length()));
/* 692 */     for (int i = s.length(); i < len; i++)
/* 693 */       buf.append(ch); 
/* 694 */     buf.append(s);
/* 695 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean matchesSearchString(String value, String searchText) {
/* 700 */     return matchesSearchStringStrict(value, searchText, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean matchesSearchStringStrict(String value, String searchText, boolean isStrict) {
/* 705 */     if (searchText == null)
/* 706 */       return true; 
/* 707 */     if (isStrict)
/*     */     {
/* 709 */       return value.toLowerCase().contains(searchText.toLowerCase());
/*     */     }
/*     */     
/*     */     try {
/* 713 */       boolean ok = false;
/*     */       
/* 715 */       int s = searchText.length();
/* 716 */       int w = value.length();
/*     */       
/* 718 */       int k = -1;
/* 719 */       for (int i = 0; i < s; i++) {
/*     */         
/* 721 */         while (k < w - 1) {
/*     */           
/* 723 */           k++;
/* 724 */           if (Character.toLowerCase(value.charAt(k)) == Character.toLowerCase(searchText.charAt(i))) {
/*     */             
/* 726 */             ok = true;
/*     */             break;
/*     */           } 
/* 729 */           ok = false;
/*     */         } 
/* 731 */         if (k >= w - 1 && i < s - 1)
/* 732 */           return false; 
/*     */       } 
/* 734 */       return ok;
/*     */     }
/* 736 */     catch (Exception e) {
/*     */       
/* 738 */       e.printStackTrace();
/* 739 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean wildCardMatch(String text, String pattern) {
/* 746 */     if (text == null)
/* 747 */       return false; 
/* 748 */     if (text.isEmpty())
/* 749 */       return false; 
/* 750 */     String[] cards = patternWildCard.split(pattern); byte b; int i; String[] arrayOfString1;
/* 751 */     for (i = (arrayOfString1 = cards).length, b = 0; b < i; ) { String card = arrayOfString1[b];
/*     */       
/* 753 */       int idx = text.indexOf(card);
/* 754 */       if (idx == -1)
/* 755 */         return false; 
/* 756 */       text = text.substring(idx + card.length()); b++; }
/*     */     
/* 758 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean validateEmail(String email) {
/* 763 */     if (isEmpty(email))
/* 764 */       return false; 
/* 765 */     return rfc2822.matcher(email).matches();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String generateRandomString(int length) {
/* 770 */     return generateRandomString(length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String generateRandomString(int length, String chars) {
/* 775 */     Random rnd = new Random();
/* 776 */     char[] text = new char[length];
/* 777 */     for (int i = 0; i < length; i++)
/*     */     {
/* 779 */       text[i] = chars.charAt(rnd.nextInt(chars.length()));
/*     */     }
/* 781 */     return new String(text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIpAddress() {
/*     */     try {
/* 788 */       InetAddress thisIp = InetAddress.getLocalHost();
/* 789 */       return thisIp.getHostAddress().toString();
/*     */     }
/* 791 */     catch (Exception e) {
/*     */       
/* 793 */       e.printStackTrace();
/*     */       
/* 795 */       return "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getResource(ILocalizationServices service, String name, JLbsResourceId nameResource) {
/* 800 */     if (nameResource != null) {
/*     */       
/* 802 */       String val = nameResource.getLocalizedValue(service);
/* 803 */       if (!isEmpty(val))
/* 804 */         return val; 
/*     */     } 
/* 806 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String htmlEncode(String string) {
/* 811 */     if (string == null)
/* 812 */       return null; 
/* 813 */     StringBuilder htmtlEncoded = new StringBuilder();
/* 814 */     for (int i = 0; i < string.length(); i++) {
/*     */       
/* 816 */       Character character = Character.valueOf(string.charAt(i));
/* 817 */       if (character.charValue() < '') {
/* 818 */         htmtlEncoded.append(character);
/*     */       } else {
/* 820 */         htmtlEncoded.append(String.format("&#x%x;", new Object[] { Integer.valueOf(Character.codePointAt(string, i)) }));
/*     */       } 
/* 822 */     }  return htmtlEncoded.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replaceTurkishCharacters(String s) {
/* 827 */     s = s.replace('ş', 's');
/* 828 */     s = s.replace('Ş', 'S');
/* 829 */     s = s.replace('ı', 'i');
/* 830 */     s = s.replace('İ', 'I');
/* 831 */     s = s.replace('ç', 'c');
/* 832 */     s = s.replace('Ç', 'C');
/* 833 */     s = s.replace('ğ', 'g');
/* 834 */     s = s.replace('Ğ', 'G');
/* 835 */     s = s.replace('ö', 'o');
/* 836 */     s = s.replace('Ö', 'O');
/* 837 */     s = s.replace('ü', 'u');
/* 838 */     s = s.replace('Ü', 'U');
/* 839 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String buildUrl(String... urlParts) {
/* 844 */     if (urlParts == null || urlParts.length == 0) {
/* 845 */       return null;
/*     */     }
/* 847 */     StringBuilder sb = new StringBuilder();
/* 848 */     sb.append(urlParts[0]);
/* 849 */     for (int i = 1; i < urlParts.length; i++)
/*     */     {
/* 851 */       appendToUrl(sb, urlParts[i]);
/*     */     }
/* 853 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void appendToUrl(StringBuilder urlSoFar, String urlPart) {
/* 858 */     if (urlPart != null && urlPart.length() > 0) {
/*     */       
/* 860 */       if (urlSoFar.length() > 0 && urlSoFar.charAt(urlSoFar.length() - 1) != '/')
/* 861 */         urlSoFar.append('/'); 
/* 862 */       urlSoFar.append(urlPart);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String checkNewLineChars(String val) {
/* 868 */     if (!isEmpty(val)) {
/*     */       
/* 870 */       if (val.indexOf("\\\\n") > -1)
/* 871 */         val = val.replace("\\\\n", "\\n"); 
/* 872 */       if (val.indexOf("\\n") > -1)
/* 873 */         val = val.replace("\\n", "\n"); 
/* 874 */       if (val.indexOf("\\r") > -1)
/* 875 */         val = val.replace("\\r", "\r"); 
/*     */     } 
/* 877 */     return val;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsStringUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */