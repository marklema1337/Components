/*      */ package com.lbs.globalization;
/*      */ 
/*      */ import com.ghasemkiani.util.DateFields;
/*      */ import com.ghasemkiani.util.PersianCalendarHelper;
/*      */ import com.ghasemkiani.util.SimplePersianCalendar;
/*      */ import com.ibm.icu.text.DecimalFormat;
/*      */ import com.ibm.icu.text.DecimalFormatSymbols;
/*      */ import com.lbs.controls.datedit.JLbsDateEdit;
/*      */ import com.lbs.controls.datedit.JLbsDateFormatter;
/*      */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*      */ import com.lbs.controls.datedit.JLbsTimeFormatter;
/*      */ import com.lbs.controls.numericedit.JLbsBigDecimalFormatter;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsDateUtil;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Font;
/*      */ import java.io.Serializable;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Locale;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class JLbsCultureInfoBase
/*      */   extends JLbsCultureConstants
/*      */   implements ILbsCultureInfo, ILbsCultureResInfo, ILbsCultureCalendarHandler, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*   48 */   private int m_LanguafeID = -1;
/*   49 */   private String m_LanguagePrefix = null;
/*   50 */   private Locale m_Locale = null;
/*   51 */   protected int m_Calendar = 0;
/*      */   
/*      */   protected ILbsCultureInfo ms_PersianCulture;
/*      */   protected ILbsCultureInfo ms_GregorianCulture;
/*   55 */   protected int m_Fractions = 2;
/*      */   
/*   57 */   protected static final int[] MONTHDAYS = new int[] { 31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30 };
/*      */   
/*   59 */   public static String ms_FontName = JLbsConstants.APP_FONT_DEFAULT;
/*      */   
/*   61 */   public static int ms_FontStyle = JLbsConstants.APP_STYLE_DEFAULT;
/*      */   
/*   63 */   public static int ms_FontSize = JLbsConstants.APP_SIZE_DEFAULT;
/*      */   
/*   65 */   protected DecimalFormatSymbols symbols = (new DecimalFormat()).getDecimalFormatSymbols();
/*      */ 
/*      */   
/*      */   protected static final int m_defaultNumberFormat = 3;
/*      */ 
/*      */   
/*      */   protected static final int m_defaultCurrencyFormat = 5;
/*      */ 
/*      */   
/*      */   protected static final int m_defaultDateFormat = 5;
/*      */ 
/*      */   
/*      */   protected static final int m_defaultTimeFormat = 22;
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String getMonthFullName(int paramInt);
/*      */ 
/*      */   
/*      */   public abstract String getDayFullName(int paramInt);
/*      */ 
/*      */   
/*   87 */   protected JLbsStringList m_CurrencyDescriptions = null;
/*      */   protected abstract String[] getFormatStrings();
/*      */   
/*      */   public static TimeZone string2TimeZone(String timeZoneStr) {
/*   91 */     JLbsStringList alist = getTimeZones();
/*   92 */     int idx = alist.indexOf(timeZoneStr);
/*   93 */     if (idx == -1)
/*   94 */       return null; 
/*   95 */     String[] zoneIds = TimeZone.getAvailableIDs();
/*   96 */     return TimeZone.getTimeZone(zoneIds[idx]);
/*      */   }
/*      */   public abstract String[] getNumberNames();
/*      */   
/*      */   public static JLbsStringList getTimeZones() {
/*  101 */     String[] zoneIds = TimeZone.getAvailableIDs();
/*  102 */     for (int i = 0; i < zoneIds.length; i++) {
/*      */       
/*  104 */       TimeZone tz = TimeZone.getTimeZone(zoneIds[i]);
/*  105 */       String zoneDesc = zoneIds[i];
/*  106 */       if (zoneDesc.indexOf("GMT") == -1) {
/*      */         
/*  108 */         int rawOffset = tz.getRawOffset();
/*  109 */         int hour = Math.abs(rawOffset / 3600000);
/*  110 */         int min = Math.abs(rawOffset / 60000) % 60;
/*      */         
/*  112 */         if (rawOffset >= 0) {
/*  113 */           zoneDesc = String.valueOf(zoneDesc) + " GMT+";
/*      */         } else {
/*  115 */           zoneDesc = String.valueOf(zoneDesc) + " GMT-";
/*      */         } 
/*  117 */         if (min > 0) {
/*  118 */           zoneDesc = String.valueOf(zoneDesc) + hour + ":" + JLbsStringUtil.padLeft((new StringBuilder(String.valueOf(min))).toString(), '0', 2);
/*      */         } else {
/*  120 */           zoneDesc = String.valueOf(zoneDesc) + hour;
/*      */         } 
/*  122 */         zoneIds[i] = zoneDesc;
/*      */       } 
/*      */     } 
/*  125 */     JLbsStringList list = new JLbsStringList(zoneIds);
/*  126 */     return list;
/*      */   }
/*      */   
/*      */   public abstract String[] getGroupNames();
/*      */   
/*      */   public abstract String[] getBaseCombinations();
/*      */   
/*      */   public int getCalendarType() {
/*  134 */     return this.m_Calendar;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCalendarType(int calendar) {
/*  139 */     this.m_Calendar = calendar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDefaultNumberFormat() {
/*  148 */     return getDefaultFormatByIndex(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultNumberFormatIndex() {
/*  154 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultCurrencyFormatIndex() {
/*  160 */     return 5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPercentSign() {
/*  169 */     return getDefaultFormatByIndex(1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrencyIdx() {
/*  175 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsStringList getCurrencyDescriptions() {
/*  180 */     return this.m_CurrencyDescriptions;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCurrencyDescriptions(JLbsStringList list) {
/*  185 */     this.m_CurrencyDescriptions = list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDebitText() {
/*  194 */     return getDefaultFormatByIndex(3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCreditText() {
/*  203 */     return getDefaultFormatByIndex(4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDefaultDateFormat() {
/*  212 */     return getDefaultFormatByIndex(5);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultDateFormatIndex() {
/*  218 */     return 5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDefaultTimeFormat() {
/*  227 */     return getDefaultFormatByIndex(6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultTimeFormatIndex() {
/*  233 */     return 22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEra(int iEra) {
/*  242 */     return getDefaultFormatByIndex((iEra > 0) ? 
/*  243 */         7 : 
/*  244 */         8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAMPM(int iTimeSlice) {
/*  253 */     return getDefaultFormatByIndex((iTimeSlice > 0) ? 
/*  254 */         9 : 
/*  255 */         10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMonthShortName(int iMonth) {
/*  264 */     if (iMonth > 0 && iMonth < 13)
/*  265 */       return getMonthFullName(iMonth).substring(0, 3); 
/*  266 */     return "XxX";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMonthChar(int iMonth) {
/*  275 */     if (iMonth > 0 && iMonth < 13)
/*  276 */       return getMonthFullName(iMonth).substring(0, 1); 
/*  277 */     return "X";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDayShortName(int iDay) {
/*  286 */     if (iDay >= 0)
/*  287 */       return getDayFullName(iDay).substring(0, 3); 
/*  288 */     return "XxX";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDayChar(int iDay) {
/*  297 */     if (iDay >= 0)
/*  298 */       return getDayFullName(iDay).substring(0, 1); 
/*  299 */     return "X";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDatePatternChars(char ch) {
/*  308 */     switch (ch) {
/*      */       
/*      */       case 'G':
/*  311 */         return 0;
/*      */       case 'Y':
/*      */       case 'y':
/*  314 */         return 1;
/*      */       case 'M':
/*  316 */         return 2;
/*      */       case 'd':
/*  318 */         return 3;
/*      */       case 'h':
/*  320 */         return 15;
/*      */       case 'H':
/*  322 */         return 5;
/*      */       case 'm':
/*  324 */         return 6;
/*      */       case 's':
/*  326 */         return 7;
/*      */       case 'S':
/*  328 */         return 8;
/*      */       case 'E':
/*  330 */         return 9;
/*      */       case 'D':
/*  332 */         return 10;
/*      */       case 'F':
/*  334 */         return 11;
/*      */       case 'w':
/*  336 */         return 12;
/*      */       case 'W':
/*  338 */         return 13;
/*      */       case 'a':
/*  340 */         return 14;
/*      */       case 'k':
/*  342 */         return 4;
/*      */       case 'K':
/*  344 */         return 16;
/*      */       case 'z':
/*  346 */         return 17;
/*      */     } 
/*  348 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getLanguagePrefix(int language) {
/*  353 */     if (language < 0 || language >= LANGUAGEPREFIXES.length) {
/*  354 */       return LANGUAGEPREFIXES[2];
/*      */     }
/*  356 */     return LANGUAGEPREFIXES[language];
/*      */   }
/*      */ 
/*      */   
/*      */   public static Locale getLanguageLocale(int language) {
/*  361 */     if (language < 0 || language >= LANGUAGELOCALES.length) {
/*  362 */       return LANGUAGELOCALES[2];
/*      */     }
/*  364 */     return LANGUAGELOCALES[language];
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getLanguageTagOfPrefix(String langPrefix) {
/*  369 */     for (int idx = 1; idx < LANGUAGEPREFIXES.length; idx++) {
/*      */       
/*  371 */       if (LANGUAGEPREFIXES[idx].equalsIgnoreCase(langPrefix))
/*  372 */         return idx; 
/*      */     } 
/*  374 */     return 2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Locale getLanguageLocaleOfPrefix(String langPrefix) {
/*  379 */     int idx = getLanguageTagOfPrefix(langPrefix);
/*  380 */     if (idx != -1) {
/*  381 */       return LANGUAGELOCALES[idx];
/*      */     }
/*  383 */     return LANGUAGELOCALES[0];
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getLanguageNameOfPrefix(String langPrefix) {
/*  388 */     return LANGUAGENAMES[getLanguageTagOfPrefix(langPrefix)];
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getLanguagePrefixes() {
/*  393 */     return LANGUAGEPREFIXES;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getSupportedLanguagePrefixes() {
/*  398 */     String[] supportedLangs = new String[SUPPORTED_CULTURES.length];
/*      */     
/*  400 */     for (int i = 0; i < SUPPORTED_CULTURES.length; i++) {
/*      */       
/*  402 */       if (i < LANGUAGEPREFIXES.length) {
/*  403 */         supportedLangs[i] = LANGUAGEPREFIXES[SUPPORTED_CULTURES[i]];
/*      */       } else {
/*  405 */         supportedLangs[i] = "";
/*      */       } 
/*      */     } 
/*  408 */     return supportedLangs;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getLanguagePrefixesWithTag() {
/*  413 */     StringBuilder result = new StringBuilder();
/*  414 */     for (int idx = 1; idx < LANGUAGEPREFIXES.length; idx++) {
/*      */       
/*  416 */       if (idx != 1) {
/*      */ 
/*      */         
/*  419 */         result.append("|" + LANGUAGEPREFIXES[idx] + "~" + idx);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  424 */         result.append(String.valueOf(LANGUAGEPREFIXES[idx]) + "~" + idx);
/*      */       } 
/*      */     } 
/*  427 */     return result.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getLanguageNamesWithTag() {
/*  432 */     StringBuilder result = new StringBuilder();
/*  433 */     for (int idx = 0; idx < LANGUAGENAMES.length; idx++) {
/*      */       
/*  435 */       if (idx != 0) {
/*  436 */         result.append("|" + LANGUAGENAMES[idx] + "~" + idx);
/*      */       } else {
/*  438 */         result.append(String.valueOf(LANGUAGENAMES[idx]) + "~" + idx);
/*      */       } 
/*  440 */     }  return result.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getLanguageNames() {
/*  445 */     return LANGUAGENAMES;
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
/*      */   public String getYes() {
/*  468 */     return "Yes";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNo() {
/*  477 */     return "No";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOK() {
/*  486 */     return "OK";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCancel() {
/*  495 */     return "Cancel";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSave() {
/*  504 */     return "Save";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTimeZone() {
/*  510 */     return "Time Zone";
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getDefaultFormatByIndex(int index) {
/*  515 */     if (index >= 0) {
/*      */       
/*  517 */       String[] formats = getFormatStrings();
/*  518 */       if (formats != null && index < formats.length)
/*  519 */         return formats[index]; 
/*  520 */       if (index < DEFAULTFORMATS.length)
/*  521 */         return DEFAULTFORMATS[index]; 
/*      */     } 
/*  523 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getEquivalent(String[] list, int n) {
/*  528 */     String s = String.valueOf('[') + Integer.valueOf(n).toString() + ']';
/*      */     
/*  530 */     for (int i = 0; i < list.length; i++) {
/*      */       
/*  532 */       int x = list[i].indexOf(s);
/*  533 */       if (x == 0) {
/*  534 */         return list[i].substring(x + s.length() + 1);
/*      */       }
/*      */     } 
/*  537 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNumberName(int n) {
/*  542 */     return getEquivalent(getNumberNames(), n);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String customizeSpelled(String spellN) {
/*  547 */     spellN = spellN.replace("|", " ");
/*  548 */     return spellN;
/*      */   }
/*      */ 
/*      */   
/*      */   private String clipFraction(String fraction) {
/*  553 */     if (fraction.length() <= this.m_Fractions)
/*  554 */       return fraction; 
/*  555 */     return fraction.substring(0, this.m_Fractions);
/*      */   }
/*      */   
/*      */   protected String getFraction(BigDecimal n, boolean clip) {
/*      */     StringTokenizer token;
/*  560 */     JLbsBigDecimalFormatter formatter = new JLbsBigDecimalFormatter();
/*  561 */     String number = formatter.formatNumber(n, true);
/*      */     
/*  563 */     int dotIndex = number.lastIndexOf(".");
/*  564 */     int commaIndex = number.lastIndexOf(",");
/*      */     
/*  566 */     if (dotIndex > commaIndex) {
/*  567 */       token = new StringTokenizer(number, ".");
/*  568 */     } else if (dotIndex < commaIndex) {
/*  569 */       token = new StringTokenizer(number, ",");
/*      */     } else {
/*  571 */       token = new StringTokenizer(number, String.valueOf(this.symbols.getDecimalSeparator()));
/*  572 */     }  token.nextToken();
/*  573 */     String fraction = token.nextToken();
/*  574 */     while (fraction.length() > 2 && fraction.endsWith("0"))
/*  575 */       fraction = fraction.substring(0, fraction.length() - 1); 
/*  576 */     return clip ? clipFraction(fraction) : fraction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNumberSpelled(BigDecimal n, boolean fracPart) {
/*  583 */     String[] numbers = getNumberNames();
/*  584 */     String[] groups = getGroupNames();
/*  585 */     String[] basecombs = getBaseCombinations();
/*      */     
/*  587 */     BigInteger intN = n.toBigInteger().abs();
/*  588 */     if (fracPart) {
/*  589 */       intN = new BigInteger(getFraction(n, false));
/*      */     }
/*  591 */     String result = "";
/*  592 */     int group = 0;
/*      */     
/*  594 */     while (intN.compareTo(BigInteger.ONE) >= 0) {
/*      */       
/*  596 */       BigInteger div1000 = intN.divide(new BigInteger("1000"));
/*  597 */       BigInteger lowGrp = intN.subtract(div1000.multiply(new BigInteger("1000")));
/*      */       
/*  599 */       String lowS = "";
/*  600 */       if (lowGrp.compareTo(BigInteger.ZERO) != 0) {
/*      */         
/*  602 */         lowS = getEquivalent(groups, 10000 + group * 1000 + lowGrp.intValue());
/*  603 */         if (lowS.compareTo("") == 0) {
/*      */           
/*  605 */           String grpMask = getEquivalent(groups, 20000 + group * 1000 + lowGrp.mod(new BigInteger("10")).intValue());
/*  606 */           if (grpMask.compareTo("") == 0)
/*  607 */             grpMask = getEquivalent(groups, group); 
/*  608 */           if (grpMask.compareTo("") != 0) {
/*      */             
/*  610 */             String lowSpell = getEquivalent(numbers, lowGrp.intValue());
/*  611 */             if (lowSpell.compareTo("") == 0) {
/*      */               
/*  613 */               int hundr = lowGrp.intValue() / 100;
/*  614 */               int ten = lowGrp.intValue() % 100;
/*  615 */               int base = lowGrp.intValue() % 10;
/*      */               
/*  617 */               String hundrS = "";
/*  618 */               if (hundr != 0) {
/*      */                 
/*  620 */                 hundrS = getEquivalent(numbers, hundr * 100);
/*  621 */                 if (hundrS.compareTo("") == 0) {
/*  622 */                   hundrS = getEquivalent(basecombs, 1).replaceFirst("~", getEquivalent(numbers, hundr));
/*      */                 }
/*      */               } 
/*  625 */               String tenS = "";
/*  626 */               if (ten != 0) {
/*      */                 
/*  628 */                 tenS = getEquivalent(numbers, ten);
/*  629 */                 if (tenS.compareTo("") == 0) {
/*      */                   
/*  631 */                   String baseS = getEquivalent(numbers, base);
/*  632 */                   String s = getEquivalent(basecombs, 2).replaceFirst("~", getEquivalent(numbers, ten - base));
/*  633 */                   tenS = s.replaceFirst("#", baseS);
/*      */                 } 
/*      */               } 
/*      */               
/*  637 */               if (hundrS.compareTo("") != 0 && tenS.compareTo("") != 0) {
/*      */                 
/*  639 */                 String s = getEquivalent(basecombs, 3).replaceFirst("~", hundrS);
/*  640 */                 lowSpell = s.replaceFirst("#", tenS);
/*      */               } else {
/*      */                 
/*  643 */                 lowSpell = String.valueOf(hundrS) + tenS;
/*      */               } 
/*      */             } 
/*  646 */             lowS = grpMask.replaceFirst("~", lowSpell);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  651 */       result = lowS.concat(result);
/*  652 */       intN = div1000;
/*  653 */       group++;
/*      */     } 
/*  655 */     if (JLbsStringUtil.isEmpty(result)) {
/*  656 */       result = getEquivalent(numbers, 0);
/*      */     }
/*  658 */     return customizeSpelled(result);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCurrencyDescription(int currIndex) {
/*  663 */     return (this.m_CurrencyDescriptions == null) ? 
/*  664 */       null : 
/*  665 */       this.m_CurrencyDescriptions.getValueAtTag(currIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysInMonth(int month, int year) {
/*  671 */     switch (this.m_Calendar) {
/*      */       
/*      */       case 1:
/*  674 */         return getNumberOfDaysInPersianMonth(month, year);
/*      */     } 
/*  676 */     return JLbsDateUtil.getNumDays(month, year);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysInPersianMonth(int month, int year) {
/*  681 */     if (month == 11) {
/*      */       
/*  683 */       if (PersianCalendarHelper.isLeapYear(year))
/*  684 */         return 30; 
/*  685 */       return 29;
/*      */     } 
/*  687 */     if (month < 11)
/*      */     {
/*  689 */       return MONTHDAYS[month];
/*      */     }
/*      */     
/*  692 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLengthForMonetaryFigures() {
/*  698 */     return 26;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLengthForNonMonetaryFigures() {
/*  704 */     return 22;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLanguagePrefix() {
/*  710 */     if (this.m_LanguagePrefix == null) {
/*      */       
/*  712 */       String className = getClass().getName();
/*  713 */       className = className.substring(className.length() - 4);
/*      */       
/*  715 */       int langID = getLanguageTagOfPrefix(className);
/*  716 */       this.m_LanguagePrefix = LANGUAGEPREFIXES[langID];
/*      */     } 
/*      */ 
/*      */     
/*  720 */     return this.m_LanguagePrefix;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale getLocale() {
/*  726 */     if (this.m_Locale == null) {
/*      */       
/*  728 */       String langPrefix = getLanguagePrefix();
/*  729 */       this.m_Locale = getLanguageLocaleOfPrefix(langPrefix);
/*      */     } 
/*      */     
/*  732 */     return this.m_Locale;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsCultureInfo createInstance(int langID) {
/*  737 */     String langPrefix = (langID == 31) ? 
/*  738 */       "TRRL" : 
/*  739 */       LANGUAGEPREFIXES[langID];
/*  740 */     return createInstance(langPrefix);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ILbsCultureInfo createInstance(String langPrefix) {
/*      */     try {
/*  747 */       String className = "com.lbs.globalization.JLbsCultureInfo" + langPrefix;
/*  748 */       ILbsCultureInfo cultureInfo = (ILbsCultureInfo)Class.forName(className).newInstance();
/*  749 */       return cultureInfo;
/*      */     }
/*  751 */     catch (Exception e) {
/*      */       
/*  753 */       return new JLbsDefaultCultureInfo();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getCalendarInstance() {
/*  760 */     switch (this.m_Calendar) {
/*      */       
/*      */       case 0:
/*  763 */         return Calendar.getInstance();
/*      */       case 1:
/*  765 */         return (Calendar)new SimplePersianCalendar();
/*      */       
/*      */       case 2:
/*  768 */         return Calendar.getInstance();
/*      */       case 3:
/*  770 */         return Calendar.getInstance();
/*      */     } 
/*  772 */     return Calendar.getInstance();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ComponentOrientation getComponentOrientation() {
/*  778 */     return ComponentOrientation.LEFT_TO_RIGHT;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasLeftToRightOrientation() {
/*  784 */     return getComponentOrientation().isLeftToRight();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLanguageID() {
/*  792 */     if (this.m_LanguafeID == -1) {
/*  793 */       this.m_LanguafeID = getLanguageTagOfPrefix(getLanguagePrefix());
/*      */     }
/*  795 */     return this.m_LanguafeID;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsCultureInfo getCultureInfo(Locale locale) {
/*  800 */     Locale[] locales = LANGUAGELOCALES;
/*  801 */     for (int i = 0; i < locales.length; i++) {
/*      */       
/*  803 */       if (locales[i].equals(locale)) {
/*      */         
/*  805 */         String langPrefix = LANGUAGEPREFIXES[i];
/*  806 */         return createInstance(langPrefix);
/*      */       } 
/*      */     } 
/*      */     
/*  810 */     return null;
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
/*      */   public Date parseDate(String dateString, String dateFormat, Object requester) {
/*  823 */     if (this.m_Calendar == 1) {
/*      */       
/*  825 */       DateFields dateFields = null;
/*  826 */       if (requester instanceof JLbsDateEdit) {
/*  827 */         dateFields = ((JLbsDateEdit)requester).parsePerDate(dateString, dateFormat);
/*      */       }
/*  829 */       SimplePersianCalendar persianCalendar = new SimplePersianCalendar();
/*  830 */       persianCalendar.setDateFields(dateFields);
/*  831 */       GregorianCalendar gregCalendar = (GregorianCalendar)persianToGregorian((Calendar)persianCalendar);
/*      */       
/*  833 */       Date gregorianDate = gregCalendar.getTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  844 */       return gregorianDate;
/*      */     } 
/*  846 */     return null;
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
/*      */   public String formatDate(Date date, String dateFormat, Object requester) {
/*  875 */     String dateStr = null;
/*  876 */     if (requester instanceof JLbsDateEdit) {
/*  877 */       dateStr = ((JLbsDateEdit)requester).parseDate(date);
/*      */     }
/*  879 */     if (dateStr == null)
/*  880 */       dateStr = (new JLbsDateFormatter(dateFormat)).format(date); 
/*  881 */     return dateStr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object beforeDateFormat(Date date) {
/*  890 */     if (this.m_Calendar == 1) {
/*      */       
/*  892 */       GregorianCalendar gregCalendar = new GregorianCalendar();
/*  893 */       gregCalendar.setTime(date);
/*  894 */       gregCalendar.set(11, 12);
/*  895 */       SimplePersianCalendar persianCalendar = (SimplePersianCalendar)gregorianToPersian(gregCalendar);
/*  896 */       return persianCalendar.getDateFields();
/*      */     } 
/*  898 */     return date;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar beforeCalendarPanelSetCalendar(Calendar calendar) {
/*  907 */     if (this.m_Calendar == 1)
/*  908 */       return gregorianToPersian(calendar); 
/*  909 */     return calendar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar beforeCalendarPanelShow(Calendar calendar) {
/*  918 */     if (this.m_Calendar == 1) {
/*      */       
/*  920 */       calendar.set(11, 12);
/*  921 */       return gregorianToPersian(calendar);
/*      */     } 
/*  923 */     return calendar;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDayOfWeek(int year, int month, int day) {
/*  929 */     SimplePersianCalendar persianCalendar = new SimplePersianCalendar();
/*  930 */     persianCalendar.setDateFields(year, month, day);
/*  931 */     return toCalendarPanelWeekday(persianCalendar.get(7));
/*      */   }
/*      */ 
/*      */   
/*      */   protected int toCalendarPanelWeekday(int day) {
/*  936 */     switch (day) {
/*      */       
/*      */       case 7:
/*  939 */         return 0;
/*      */       case 1:
/*  941 */         return 1;
/*      */       case 2:
/*  943 */         return 2;
/*      */       case 3:
/*  945 */         return 3;
/*      */       case 4:
/*  947 */         return 4;
/*      */       case 5:
/*  949 */         return 5;
/*      */       case 6:
/*  951 */         return 6;
/*      */     } 
/*  953 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getWeekIndex(int day, int month, int year) {
/*  959 */     SimplePersianCalendar persianCalendar = new SimplePersianCalendar();
/*  960 */     persianCalendar.setDateFields(year, month, day + 1);
/*  961 */     return persianCalendar.get(3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Calendar gregorianToPersian(Calendar calendar) {
/*  966 */     if (calendar instanceof SimplePersianCalendar) {
/*      */       
/*  968 */       SimplePersianCalendar persian = (SimplePersianCalendar)calendar;
/*  969 */       return (Calendar)persian;
/*      */     } 
/*  971 */     if (calendar instanceof GregorianCalendar) {
/*      */       
/*  973 */       GregorianCalendar greg = (GregorianCalendar)calendar;
/*  974 */       SimplePersianCalendar persianCalendar = new SimplePersianCalendar();
/*  975 */       persianCalendar.setTime((Date)greg.getTime().clone());
/*  976 */       return (Calendar)persianCalendar;
/*      */     } 
/*  978 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Calendar persianToGregorian(Calendar calendar) {
/*  983 */     if (calendar instanceof SimplePersianCalendar) {
/*      */       
/*  985 */       SimplePersianCalendar persianCalendar = new SimplePersianCalendar();
/*  986 */       persianCalendar.setDateFields(((SimplePersianCalendar)calendar).getDateFields());
/*  987 */       GregorianCalendar gregCalendar = new GregorianCalendar();
/*  988 */       gregCalendar.setTime((Date)persianCalendar.getTime().clone());
/*  989 */       return gregCalendar;
/*      */     } 
/*  991 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDateField(int field, Calendar cal) {
/*  997 */     if (cal instanceof SimplePersianCalendar) {
/*      */       
/*  999 */       SimplePersianCalendar persian = (SimplePersianCalendar)cal;
/* 1000 */       switch (field) {
/*      */         
/*      */         case 1:
/* 1003 */           return persian.getDateFields().getYear();
/*      */         
/*      */         case 5:
/* 1006 */           return persian.getDateFields().getDay();
/*      */         
/*      */         case 2:
/* 1009 */           return persian.getDateFields().getMonth();
/*      */       } 
/* 1011 */       return 0;
/*      */     } 
/*      */ 
/*      */     
/* 1015 */     return (cal == null) ? 
/* 1016 */       -1 : 
/* 1017 */       cal.get(field);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getAsPersian(int year, int month, int date) {
/* 1023 */     SimplePersianCalendar result = new SimplePersianCalendar();
/* 1024 */     result.setDateFields(year, month, date);
/* 1025 */     return (Calendar)result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Calendar getCalendar(Calendar calendar, int dateType) {
/* 1030 */     switch (dateType) {
/*      */       
/*      */       case 0:
/* 1033 */         return calendar;
/*      */       case 1:
/* 1035 */         return gregorianToPersian(calendar);
/*      */     } 
/*      */     
/* 1038 */     return calendar;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String formatDateTime(Calendar calendar, int dateFormat, int timeFormat, int displayAttributes, int dateType) {
/* 1044 */     Calendar cal = getCalendar(calendar, dateType);
/* 1045 */     ILbsCultureInfo culture = getCultureInfo(dateType);
/* 1046 */     JLbsDateFormatter formatter = new JLbsDateFormatter(dateFormat, culture);
/* 1047 */     StringBuilder buffer = new StringBuilder();
/* 1048 */     boolean timeOnly = ((displayAttributes & 0x20) == 32);
/* 1049 */     boolean datetime = ((displayAttributes & 0x10) == 16);
/* 1050 */     if (!timeOnly || datetime)
/* 1051 */       buffer.append(formatter.format(cal.getTime())); 
/* 1052 */     if (timeOnly || datetime) {
/*      */       
/* 1054 */       if (buffer.length() > 0) {
/* 1055 */         buffer.append(' ');
/*      */       }
/* 1057 */       buffer.append(JLbsTimeFormatter.formatTime(new JLbsTimeDuration(cal), timeFormat));
/*      */     } 
/* 1059 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ILbsCultureInfo getCultureInfo(int dateType) {
/* 1069 */     switch (dateType) {
/*      */       
/*      */       case 0:
/* 1072 */         return getGregorianCultureInfo(this);
/*      */       case 1:
/* 1074 */         if (this.ms_PersianCulture != null) {
/* 1075 */           return this.ms_PersianCulture;
/*      */         }
/*      */         try {
/* 1078 */           Class<?> cls = Class.forName("com.lbs.globalization.JLbsCultureInfoFAIR");
/* 1079 */           this.ms_PersianCulture = (ILbsCultureInfo)cls.newInstance();
/*      */         }
/* 1081 */         catch (Exception exception) {}
/*      */ 
/*      */         
/* 1084 */         return this.ms_PersianCulture;
/*      */     } 
/* 1086 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   private ILbsCultureInfo getGregorianCultureInfo(JLbsCultureInfoBase cultureInfo) {
/* 1091 */     if (cultureInfo != null && cultureInfo.getCalendarType() == 0)
/* 1092 */       return cultureInfo; 
/* 1093 */     if (this.ms_GregorianCulture != null) {
/* 1094 */       return this.ms_GregorianCulture;
/*      */     }
/*      */     try {
/* 1097 */       Class<?> cls = Class.forName("com.lbs.globalization.JLbsDefaultCultureInfo");
/* 1098 */       this.ms_GregorianCulture = (ILbsCultureInfo)cls.newInstance();
/*      */     }
/* 1100 */     catch (Exception exception) {}
/*      */ 
/*      */     
/* 1103 */     return this.ms_GregorianCulture;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String getMonthFullName(int paramInt1, int paramInt2);
/*      */ 
/*      */   
/*      */   public abstract String getDayFullName(int paramInt1, int paramInt2);
/*      */ 
/*      */   
/*      */   public abstract String getDayShortName(int paramInt1, int paramInt2);
/*      */ 
/*      */   
/*      */   public Font getFont() {
/* 1118 */     return new Font(ms_FontName, ms_FontStyle, ms_FontSize);
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */