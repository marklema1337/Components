/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.ghasemkiani.util.DateFields;
/*     */ import com.lbs.globalization.ILbsCultureCalendarHandler;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*     */ import com.lbs.interfaces.ILbsDisplayFormat;
/*     */ import com.lbs.util.JLbsDateUtil;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.TimeZone;
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
/*     */ public class JLbsDateFormatter
/*     */   implements ILbsDisplayFormat
/*     */ {
/*     */   private static boolean toggleAMPM = false;
/* 165 */   public static HashMap<String, DecimalFormat> ms_NumberFormat = new HashMap<>();
/* 166 */   public static int ms_NumberFormatCapacity = 100;
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
/* 181 */   private ILbsCultureInfo m_CultureInfo = null;
/* 182 */   private Calendar m_Calendar = new GregorianCalendar();
/* 183 */   private String m_Pattern = "dd/MM/yyyy";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDateFormatter() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDateFormatter(int iFormatSpec, ILbsCultureInfo cultureInfo) {
/* 193 */     this();
/* 194 */     this.m_CultureInfo = cultureInfo;
/* 195 */     if (iFormatSpec == 0 && cultureInfo != null) {
/* 196 */       this.m_Pattern = cultureInfo.getDefaultDateFormat();
/* 197 */     } else if (iFormatSpec == 21 && cultureInfo != null) {
/* 198 */       this.m_Pattern = cultureInfo.getDefaultTimeFormat();
/* 199 */     } else if (iFormatSpec >= 0 && iFormatSpec < FMT_FormatStrings.length) {
/* 200 */       this.m_Pattern = FMT_FormatStrings[iFormatSpec];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDateFormatter(String pattern, ILbsCultureInfo cultureInfo) {
/* 209 */     this();
/* 210 */     this.m_Pattern = pattern;
/* 211 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDateFormatter(String pattern) {
/* 219 */     this(pattern, (ILbsCultureInfo)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date parse(String format, String value) {
/* 224 */     return internalParse(format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format(Date date) {
/* 233 */     return format(date, new StringBuilder(), new FieldPosition(0)).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format(JLbsTimeDuration time) {
/* 242 */     return internalFormat(time, new StringBuilder(), new FieldPosition(0)).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toPattern() {
/* 250 */     return this.m_Pattern;
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
/*     */   public StringBuilder format(Date date, StringBuilder toAppendTo, FieldPosition pos) {
/* 268 */     return internalFormat(date, toAppendTo, pos);
/*     */   }
/*     */ 
/*     */   
/* 272 */   private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = new int[] { 0, 1, 2, 5, 
/* 273 */       11, 11, 12, 13, 14, 
/* 274 */       7, 6, 8, 3, 
/* 275 */       4, 9, 10, 10, 15 };
/*     */ 
/*     */   
/* 278 */   private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = new int[] { 0, 1, 
/* 279 */       2, 3, 4, 5, 
/* 280 */       6, 7, 8, 9, 
/* 281 */       10, 11, 12, 
/* 282 */       13, 14, 15, 16, 
/* 283 */       17 }; private static final int millisPerHour = 3600000;
/*     */   private static final int millisPerMinute = 60000;
/*     */   
/*     */   private String subFormat(char ch, int count, int beginOffset, FieldPosition pos) throws IllegalArgumentException {
/*     */     StringBuilder zoneString;
/* 288 */     int patternCharIndex = -1;
/* 289 */     int maxIntCount = Integer.MAX_VALUE;
/* 290 */     String current = "";
/*     */     
/* 292 */     if ((patternCharIndex = this.m_CultureInfo.getDatePatternChars(ch)) == -1) {
/* 293 */       throw new IllegalArgumentException("Illegal pattern character '" + ch + "'");
/*     */     }
/* 295 */     int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
/* 296 */     int value = getDateField(field);
/*     */     
/* 298 */     switch (patternCharIndex) {
/*     */       
/*     */       case 0:
/* 301 */         current = this.m_CultureInfo.getEra(value);
/*     */         break;
/*     */       case 1:
/* 304 */         if (count >= 4) {
/* 305 */           current = zeroPaddingNumber(value, 4, maxIntCount);
/*     */           break;
/*     */         } 
/* 308 */         current = zeroPaddingNumber(value, 2, 2);
/*     */         break;
/*     */       
/*     */       case 2:
/* 312 */         if (count >= 4) {
/* 313 */           current = this.m_CultureInfo.getMonthFullName(value + 1); break;
/* 314 */         }  if (count == 3) {
/* 315 */           current = this.m_CultureInfo.getMonthShortName(value + 1); break;
/*     */         } 
/* 317 */         current = zeroPaddingNumber((value + 1), count, maxIntCount);
/*     */         break;
/*     */       case 4:
/* 320 */         if (value == 0) {
/* 321 */           current = zeroPaddingNumber((this.m_Calendar.getMaximum(11) + 1), count, maxIntCount); break;
/*     */         } 
/* 323 */         current = zeroPaddingNumber((value % 12), count, maxIntCount);
/*     */         break;
/*     */       case 9:
/* 326 */         if (count >= 4) {
/* 327 */           current = this.m_CultureInfo.getDayFullName(value);
/*     */           break;
/*     */         } 
/* 330 */         current = this.m_CultureInfo.getDayShortName(value);
/*     */         break;
/*     */       case 14:
/* 333 */         if (toggleAMPM)
/* 334 */           if (value == 0) {
/* 335 */             value = 1;
/*     */           } else {
/* 337 */             value = 0;
/* 338 */           }   current = this.m_CultureInfo.getAMPM(value);
/*     */         break;
/*     */       case 15:
/* 341 */         if (value == 0) {
/* 342 */           current = zeroPaddingNumber((this.m_Calendar.getLeastMaximum(10) + 1), count, maxIntCount); break;
/*     */         } 
/* 344 */         current = zeroPaddingNumber(value, count, maxIntCount);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 17:
/* 351 */         zoneString = new StringBuilder();
/*     */         
/* 353 */         value = this.m_Calendar.get(15) + this.m_Calendar.get(16);
/*     */         
/* 355 */         if (value < 0) {
/*     */           
/* 357 */           zoneString.append("GMT-");
/* 358 */           value = -value;
/*     */         } else {
/*     */           
/* 361 */           zoneString.append("GMT+");
/* 362 */         }  zoneString.append(zeroPaddingNumber((value / 3600000), 2, 2));
/* 363 */         zoneString.append(':');
/* 364 */         zoneString.append(zeroPaddingNumber((value % 3600000 / 60000), 2, 2));
/* 365 */         current = zoneString.toString();
/*     */         break;
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
/*     */       default:
/* 379 */         current = zeroPaddingNumber(value, count, maxIntCount);
/*     */         break;
/*     */     } 
/*     */     
/* 383 */     if (pos.getField() == PATTERN_INDEX_TO_DATE_FORMAT_FIELD[patternCharIndex])
/*     */     {
/*     */       
/* 386 */       if (pos.getBeginIndex() == 0 && pos.getEndIndex() == 0) {
/*     */         
/* 388 */         pos.setBeginIndex(beginOffset);
/* 389 */         pos.setEndIndex(beginOffset + current.length());
/*     */       } 
/*     */     }
/*     */     
/* 393 */     return current;
/*     */   }
/*     */   
/*     */   private static final String GMT_PLUS = "GMT+";
/*     */   private static final String GMT_MINUS = "GMT-";
/*     */   private Object m_Argument;
/*     */   
/*     */   private String zeroPaddingNumber(long value, int minDigits, int maxDigits) {
/* 401 */     DecimalFormat m_NumberFormat = getFormat(minDigits, maxDigits);
/* 402 */     return m_NumberFormat.format(value);
/*     */   }
/*     */ 
/*     */   
/*     */   private DecimalFormat getFormat(int minDigits, int maxDigits) {
/* 407 */     String key = produceKey(minDigits, maxDigits);
/* 408 */     DecimalFormat format = ms_NumberFormat.get(key);
/* 409 */     if (format == null) {
/*     */ 
/*     */       
/* 412 */       format = new DecimalFormat();
/* 413 */       format.setGroupingUsed(false);
/* 414 */       format.setMinimumIntegerDigits(minDigits);
/* 415 */       format.setMaximumIntegerDigits(maxDigits);
/* 416 */       ms_NumberFormat.put(key, format);
/* 417 */       if (ms_NumberFormat.size() > ms_NumberFormatCapacity)
/*     */       {
/* 419 */         synchronized (ms_NumberFormat) {
/*     */           
/* 421 */           ms_NumberFormat = new HashMap<>();
/*     */         } 
/*     */       }
/*     */     } 
/* 425 */     return format;
/*     */   }
/*     */ 
/*     */   
/*     */   private String produceKey(int minDigits, int maxDigits) {
/* 430 */     return String.valueOf(minDigits) + "_" + maxDigits;
/*     */   }
/*     */   
/*     */   private StringBuilder internalFormat(Object date, StringBuilder toAppendTo, FieldPosition pos) {
/* 434 */     if (date == null) {
/* 435 */       return toAppendTo;
/*     */     }
/* 437 */     if (date instanceof Date)
/*     */     {
/* 439 */       if (JLbsDateUtil.getServerTimeZone() != null && !JLbsDateUtil.getServerTimeZone().equals(TimeZone.getDefault())) {
/*     */         
/* 441 */         Calendar c = Calendar.getInstance();
/* 442 */         c.setTimeZone(JLbsDateUtil.getServerTimeZone());
/* 443 */         c.setTimeInMillis(((Date)date).getTime());
/* 444 */         int hourDiff = JLbsDateUtil.getTimeZoneDifference(TimeZone.getDefault(), JLbsDateUtil.getServerTimeZone());
/* 445 */         c.add(10, hourDiff);
/* 446 */         date = c.getTime();
/*     */       } 
/*     */     }
/*     */     
/* 450 */     if (this.m_CultureInfo == null) {
/* 451 */       this.m_CultureInfo = (ILbsCultureInfo)new JLbsDefaultCultureInfo();
/*     */     }
/* 453 */     if (this.m_CultureInfo instanceof ILbsCultureCalendarHandler) {
/*     */       
/* 455 */       Date dateToFormat = null;
/* 456 */       if (date instanceof Date)
/* 457 */         dateToFormat = (Date)date; 
/* 458 */       if (dateToFormat != null) {
/* 459 */         date = ((ILbsCultureCalendarHandler)this.m_CultureInfo).beforeDateFormat(dateToFormat);
/*     */       }
/*     */     } 
/* 462 */     pos.setBeginIndex(0);
/* 463 */     pos.setEndIndex(0);
/*     */ 
/*     */     
/* 466 */     this.m_Argument = date;
/* 467 */     if (date instanceof Date) {
/* 468 */       this.m_Calendar.setTime((Date)date);
/*     */     }
/* 470 */     boolean inQuote = false;
/* 471 */     char prevCh = Character.MIN_VALUE;
/* 472 */     int count = 0;
/* 473 */     for (int i = 0; i < this.m_Pattern.length(); i++) {
/*     */       
/* 475 */       char ch = this.m_Pattern.charAt(i);
/*     */ 
/*     */       
/* 478 */       if (ch != prevCh && count > 0) {
/*     */         
/* 480 */         toAppendTo.append(subFormat(prevCh, count, toAppendTo.length(), pos));
/* 481 */         count = 0;
/*     */       } 
/* 483 */       if (ch == '\'') {
/*     */ 
/*     */ 
/*     */         
/* 487 */         if (i + 1 < this.m_Pattern.length() && this.m_Pattern.charAt(i + 1) == '\'')
/*     */         {
/* 489 */           toAppendTo.append('\'');
/* 490 */           i++;
/*     */         }
/*     */         else
/*     */         {
/* 494 */           inQuote = !inQuote;
/*     */         }
/*     */       
/* 497 */       } else if (!inQuote && ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
/*     */ 
/*     */ 
/*     */         
/* 501 */         prevCh = ch;
/* 502 */         count++;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 507 */         toAppendTo.append(ch);
/*     */       } 
/*     */     } 
/*     */     
/* 511 */     if (count > 0)
/*     */     {
/* 513 */       toAppendTo.append(subFormat(prevCh, count, toAppendTo.length(), pos));
/*     */     }
/* 515 */     return toAppendTo;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getDateField(int index) {
/* 520 */     if (this.m_Argument instanceof Date)
/*     */     {
/* 522 */       return this.m_Calendar.get(index);
/*     */     }
/* 524 */     if (this.m_Argument instanceof DateFields) {
/*     */       
/* 526 */       switch (index) {
/*     */         
/*     */         case 1:
/* 529 */           return ((DateFields)this.m_Argument).getYear();
/*     */         case 2:
/* 531 */           return ((DateFields)this.m_Argument).getMonth();
/*     */         case 5:
/* 533 */           return ((DateFields)this.m_Argument).getDay();
/*     */       } 
/*     */       
/* 536 */       return 0;
/*     */     } 
/*     */     
/* 539 */     if (this.m_Argument instanceof JLbsTimeDuration) {
/*     */       
/* 541 */       JLbsTimeDuration time = (JLbsTimeDuration)this.m_Argument;
/* 542 */       switch (index) {
/*     */         
/*     */         case 11:
/* 545 */           return time.getHour();
/*     */         case 12:
/* 547 */           return time.getMinute();
/*     */         case 13:
/* 549 */           return time.getSecond();
/*     */       } 
/*     */     } 
/* 552 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Date internalParse(String format, String value) {
/*     */     try {
/* 559 */       SimpleDateFormat dateFormat = new SimpleDateFormat(format);
/* 560 */       return dateFormat.parse(value);
/*     */     }
/* 562 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 565 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsDateFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */