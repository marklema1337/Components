/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.ibm.icu.text.DecimalFormat;
/*     */ import com.ibm.icu.text.DecimalFormatSymbols;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.globalization.JLbsCurrencyInfo;
/*     */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.ParseException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
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
/*     */ public class JLbsRealNumberFormatter
/*     */   implements ILbsNumberFormatter
/*     */ {
/*     */   public static final double ms_DoubleEpsilon = 1.0E-9D;
/*     */   public static final int FMT_DEFAULT = 1;
/*     */   public static final int FMT_1234__xxxx = 2;
/*     */   public static final int FMT_1_234__xxxx = 3;
/*     */   public static final int FMT_1234__xx = 4;
/*     */   public static final int FMT_1_234__xx = 5;
/*     */   public static final int FMT_PR_12__xx = 6;
/*     */   public static final int FMT_12__xx_PR = 7;
/*     */   public static final int FMT_$12_345__xx = 8;
/*     */   public static final int FMT_12_345__xx$ = 9;
/*     */   public static final int FMT_12_345_xx_CR = 10;
/*     */   public static final int FMT_$12_345__xx_CR = 11;
/*     */   public static final int FMT_12_345__xx_CR_$ = 12;
/*     */   public static final int FMT_12_345__xx_$_CR = 13;
/*     */   public static final int FMT_$p12_345__xxq = 14;
/*     */   public static final int FMT_p12_345__xxq$ = 15;
/*     */   public static final int FMT_12_345__XXXXX = 16;
/*     */   public static final int FMT_EXCELLENT = 17;
/*     */   public static final int FMT_N_12_345__xx$ = 18;
/*     */   public static final int FMT_N_12_345_xx_CR = 19;
/*     */   public static final int FMT_N_$12_345__xx_CR = 20;
/*     */   public static final int FMT_N_12_345__xx_CR_$ = 21;
/*     */   public static final int FMT_N_12_345__xx_$_CR = 22;
/*     */   public static final int FMT_p12_345__xxq = 23;
/*     */   public static final int FMT_1_23_456__xxxx = 24;
/*     */   public static final int DECIMAL_SEPARATOR_DEFAULT = 0;
/*     */   public static final int DECIMAL_SEPARATOR_DOT = 1;
/*     */   public static final int DECIMAL_SEPARATOR_COMMA = 2;
/*  73 */   protected DecimalFormat m_Format = null;
/*  74 */   protected int m_FormatSpec = 0;
/*  75 */   protected int m_currencyIndex = 0;
/*  76 */   protected JLbsCurrenciesBase m_currencyBase = null;
/*     */   protected boolean m_DispZero = true;
/*     */   protected boolean m_EnableZeros = false;
/*  79 */   protected String m_CurrencyValue = null;
/*     */   private boolean m_FromFocusLost = false;
/*     */   private boolean m_DecimalSepChanged = false;
/*     */   private char m_NewDecimalSeparator;
/*     */   private char m_NewGroupingSeparator;
/*  84 */   private Locale m_Locale = null;
/*  85 */   protected static ThreadLocal<ILbsCultureInfo> m_ThreadLocalCultureInfo = new ThreadLocal<>();
/*     */   
/*  87 */   private static DecimalFormatSymbols m_DecimalFormatEN = new DecimalFormatSymbols(new Locale("en"));
/*  88 */   protected static int ms_DecimalFormatsCapacity = 50;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_ForceDecimal = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter() {
/*  97 */     this.m_Format = createFormat("#,##0.####");
/*     */   }
/*     */ 
/*     */   
/*     */   protected static DecimalFormat createFormat(String pattern) {
/*     */     DecimalFormat format;
/* 103 */     if (pattern != null) {
/*     */       
/* 105 */       format = new DecimalFormat(pattern);
/*     */     }
/*     */     else {
/*     */       
/* 109 */       format = new DecimalFormat();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     Locale locale = JLbsLocalizer.getLocale();
/* 118 */     if (locale != null) {
/*     */       
/* 120 */       DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
/* 121 */       format.setDecimalFormatSymbols(symbols);
/*     */     } 
/*     */     
/* 124 */     return format;
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
/*     */   public JLbsRealNumberFormatter(int iPrecision, boolean bDisplayZero) {
/* 137 */     this.m_DispZero = bDisplayZero;
/* 138 */     setFormat(iPrecision, true);
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
/*     */   public JLbsRealNumberFormatter(int iPrecision, boolean bGroup, boolean bDisplayZero) {
/* 152 */     this.m_DispZero = bDisplayZero;
/* 153 */     setFormat(iPrecision, bGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter(String szFormat) {
/* 164 */     this.m_Format = createFormat(szFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter(int iFormatType) {
/* 175 */     this(iFormatType, 0, "", 4, (JLbsCurrenciesBase)null, true);
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
/*     */   public JLbsRealNumberFormatter(int iFormatType, int iPrecision) {
/* 187 */     this(iFormatType, 0, "", iPrecision, (JLbsCurrenciesBase)null, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter(int iFormatType, int currencyIndex, String currencyValue, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero) {
/* 197 */     this.m_FormatSpec = iFormatType;
/* 198 */     this.m_currencyIndex = currencyIndex;
/* 199 */     this.m_currencyBase = currBase;
/* 200 */     this.m_DispZero = bDisplayZero;
/* 201 */     this.m_CurrencyValue = currencyValue;
/*     */     
/* 203 */     setFormat(iFormatType, precision, currencyIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter(int iFormatType, int currencyIndex, String currencyValue, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero, boolean forceDecimals) {
/* 209 */     this.m_FormatSpec = iFormatType;
/* 210 */     this.m_currencyIndex = currencyIndex;
/* 211 */     this.m_currencyBase = currBase;
/* 212 */     this.m_DispZero = bDisplayZero;
/* 213 */     this.m_CurrencyValue = currencyValue;
/* 214 */     this.m_ForceDecimal = forceDecimals;
/* 215 */     setFormat(iFormatType, precision, currencyIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter(int iFormatType, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero) {
/* 221 */     this.m_FormatSpec = iFormatType;
/* 222 */     this.m_currencyIndex = currencyIndex;
/* 223 */     this.m_currencyBase = currBase;
/* 224 */     this.m_DispZero = bDisplayZero;
/*     */     
/* 226 */     setFormat(iFormatType, precision, currencyIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter(int iFormatType, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero, boolean m_ForceDecimals) {
/* 232 */     this.m_FormatSpec = iFormatType;
/* 233 */     this.m_currencyIndex = currencyIndex;
/* 234 */     this.m_currencyBase = currBase;
/* 235 */     this.m_DispZero = bDisplayZero;
/* 236 */     this.m_ForceDecimal = m_ForceDecimals;
/* 237 */     setFormat(iFormatType, precision, currencyIndex);
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
/*     */   public Number parseNumber(String szNumber) throws ParseException {
/* 250 */     Number nNumber = this.m_Format.parse((szNumber == null || szNumber.length() == 0 || szNumber.compareTo("-") == 0) ? 
/* 251 */         "0" : 
/* 252 */         szNumber);
/*     */     
/* 254 */     if (nNumber instanceof Double || nNumber instanceof Float)
/* 255 */       return nNumber; 
/* 256 */     if (nNumber instanceof Long)
/* 257 */       return nNumber; 
/* 258 */     if (nNumber instanceof BigDecimal) {
/* 259 */       return Double.valueOf(nNumber.doubleValue());
/*     */     }
/* 261 */     throw new ParseException("Number is not valid for the format '" + this.m_Format.toString() + "'!", 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatNumber(Number nNumber) {
/* 272 */     boolean forceDecimal = this.m_ForceDecimal;
/* 273 */     if (this.m_FromFocusLost)
/* 274 */       forceDecimal = !shouldTrimZeros(getFormatSpecifier()); 
/* 275 */     return formatNumber(nNumber, forceDecimal);
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
/*     */   public String formatNumber(Number nNumber, boolean bForceDecimals) {
/* 287 */     return formatNumber(nNumber, bForceDecimals, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String formatNumber(Number nNumber, boolean bForceDecimals, boolean bEditText) {
/* 292 */     String result = InternalNumberToString(nNumber, bForceDecimals);
/* 293 */     if (!bEditText && result != null && result.length() > 0 && result.startsWith("-") && requiresAbsValue()) {
/* 294 */       result = result.substring(1);
/*     */     }
/*     */     
/* 297 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatNumberFull(Number nNumber, ILbsCultureInfo culture, boolean bForceDecimals) {
/*     */     JLbsDefaultCultureInfo jLbsDefaultCultureInfo;
/*     */     String fmtString;
/* 309 */     if (culture == null)
/* 310 */       jLbsDefaultCultureInfo = new JLbsDefaultCultureInfo(); 
/* 311 */     this.m_Locale = jLbsDefaultCultureInfo.getLocale();
/* 312 */     if (this.m_FromFocusLost && !bForceDecimals)
/* 313 */       bForceDecimals = !shouldTrimZeros(getFormatSpecifier()); 
/* 314 */     String result = InternalNumberToString(nNumber, bForceDecimals);
/* 315 */     this.m_FromFocusLost = false;
/*     */     
/* 317 */     switch (this.m_FormatSpec) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 327 */         result = String.valueOf(jLbsDefaultCultureInfo.getPercentSign()) + result;
/*     */         break;
/*     */       case 7:
/* 330 */         result = String.valueOf(result) + jLbsDefaultCultureInfo.getPercentSign();
/*     */         break;
/*     */       case 8:
/* 333 */         result = String.valueOf(getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo)) + " " + result;
/*     */         break;
/*     */       case 9:
/*     */       case 18:
/* 337 */         result = String.valueOf(result) + " " + getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo);
/*     */         break;
/*     */       case 10:
/*     */       case 19:
/* 341 */         result = String.valueOf(result) + " " + getCreditDebitText(nNumber, (ILbsCultureInfo)jLbsDefaultCultureInfo);
/*     */         break;
/*     */       case 11:
/*     */       case 20:
/* 345 */         result = String.valueOf(getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo)) + " " + result + " " + getCreditDebitText(nNumber, (ILbsCultureInfo)jLbsDefaultCultureInfo);
/*     */         break;
/*     */       case 12:
/*     */       case 21:
/* 349 */         result = String.valueOf(result) + " " + getCreditDebitText(nNumber, (ILbsCultureInfo)jLbsDefaultCultureInfo) + " " + getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo);
/*     */         break;
/*     */       case 13:
/*     */       case 22:
/* 353 */         result = String.valueOf(result) + " " + getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo) + " " + getCreditDebitText(nNumber, (ILbsCultureInfo)jLbsDefaultCultureInfo);
/*     */         break;
/*     */       case 14:
/* 356 */         result = String.valueOf(getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo)) + " (" + result + ")";
/*     */         break;
/*     */       case 15:
/* 359 */         result = "(" + result + ") " + getCurrencySymbol((ILbsCultureInfo)jLbsDefaultCultureInfo);
/*     */         break;
/*     */       case 23:
/* 362 */         if (result.startsWith("-")) {
/*     */           
/* 364 */           String[] negativeVal = result.split("-");
/* 365 */           result = "(" + negativeVal[1] + ") ";
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 1:
/* 370 */         fmtString = jLbsDefaultCultureInfo.getDefaultNumberFormat();
/* 371 */         if (fmtString != null && fmtString.length() > 0) {
/* 372 */           return InternalNumberToString(fmtString, nNumber);
/*     */         }
/*     */         break;
/*     */       
/*     */       case 17:
/* 377 */         fmtString = ",##0.##";
/* 378 */         if (fmtString != null && fmtString.length() > 0) {
/* 379 */           return InternalNumberToString(fmtString, nNumber, ',', '.');
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 385 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isIndianCulture() {
/* 390 */     if ((JLbsRealNumberFormatterInstanceHolder.getInstance()).m_CultureInfo != null)
/* 391 */       return (JLbsRealNumberFormatterInstanceHolder.getInstance()).m_CultureInfo.getLanguagePrefix().equals("ENIN"); 
/* 392 */     if (m_ThreadLocalCultureInfo.get() != null) {
/* 393 */       return ((ILbsCultureInfo)m_ThreadLocalCultureInfo.get()).getLanguagePrefix().equals("ENIN");
/*     */     }
/* 395 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFormatSpecifier() {
/* 403 */     return this.m_FormatSpec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPrecisionCount() {
/* 411 */     switch (this.m_FormatSpec) {
/*     */       
/*     */       case 2:
/* 414 */         return 12;
/*     */       case 3:
/* 416 */         return 12;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 434 */     return (this.m_Format != null) ? 
/* 435 */       getPrecisionFromFormat(this.m_Format.toPattern()) : 
/* 436 */       0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getCurrencySymbol(int currIndex) {
/* 442 */     if (!JLbsStringUtil.isEmpty(this.m_CurrencyValue))
/* 443 */       return this.m_CurrencyValue; 
/* 444 */     if (this.m_currencyBase != null) {
/*     */       
/* 446 */       JLbsCurrencyInfo info = (currIndex > 0) ? 
/* 447 */         this.m_currencyBase.getCurrencyInfo(currIndex) : (
/* 448 */         (currIndex < 0) ? 
/* 449 */         this.m_currencyBase.getContextCurrency(-currIndex - 1) : 
/* 450 */         null);
/* 451 */       if (info != null)
/* 452 */         return info.m_shortCode; 
/*     */     } 
/* 454 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCurrencySymbol(ILbsCultureInfo culture) {
/* 459 */     if (!JLbsStringUtil.isEmpty(this.m_CurrencyValue))
/* 460 */       return this.m_CurrencyValue; 
/* 461 */     if (this.m_currencyIndex != 0 && this.m_currencyBase != null)
/* 462 */       return getCurrencySymbol(this.m_currencyIndex); 
/* 463 */     if (culture == null) {
/* 464 */       return "";
/*     */     }
/*     */     
/* 467 */     int currIdx = culture.getCurrencyIdx();
/* 468 */     return getCurrencySymbol(currIdx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(int currencyIndex) {
/* 474 */     this.m_currencyIndex = currencyIndex;
/* 475 */     setFormat(this.m_FormatSpec, 0, currencyIndex);
/*     */   }
/*     */   
/*     */   protected void setFormat(int iPrecision, boolean bGroup) {
/*     */     String baseFormat;
/* 480 */     StringBuilder szBuffer = new StringBuilder(iPrecision + 1);
/*     */ 
/*     */     
/* 483 */     if (isIndianCulture()) {
/* 484 */       baseFormat = 
/*     */         
/* 486 */         String.valueOf(bGroup ? "#,##,##" : "##") + (
/* 487 */         this.m_DispZero ? 
/* 488 */         "0" : 
/* 489 */         "#");
/*     */     } else {
/* 491 */       baseFormat = 
/*     */         
/* 493 */         String.valueOf(bGroup ? "#,##" : "##") + (
/* 494 */         this.m_DispZero ? 
/* 495 */         "0" : 
/* 496 */         "#");
/*     */     } 
/* 498 */     if (iPrecision == 0) {
/*     */       
/* 500 */       this.m_Format = createFormat(baseFormat);
/*     */     }
/*     */     else {
/*     */       
/* 504 */       for (; iPrecision > 0; iPrecision--)
/* 505 */         szBuffer.append('#'); 
/* 506 */       this.m_Format = createFormat(String.valueOf(baseFormat) + "." + szBuffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getCurrencyDecimals(int currIndex) {
/* 512 */     if (this.m_currencyBase != null) {
/*     */       
/* 514 */       JLbsCurrencyInfo info = (currIndex > 0) ? 
/* 515 */         this.m_currencyBase.getCurrencyInfo(currIndex) : (
/* 516 */         (currIndex < 0) ? 
/* 517 */         this.m_currencyBase.getContextCurrency(-currIndex - 1) : 
/* 518 */         null);
/* 519 */       if (info != null)
/* 520 */         return info.m_decimals; 
/*     */     } 
/* 522 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setFormat(int iFormatType, int iPrecision, int iCurrency) {
/* 527 */     int applyPrec = iPrecision;
/* 528 */     if (iCurrency != 0 && this.m_currencyBase != null)
/* 529 */       applyPrec = getCurrencyDecimals(iCurrency); 
/* 530 */     switch (iFormatType) {
/*     */       
/*     */       case 2:
/* 533 */         setFormat(12, false);
/*     */         return;
/*     */       case 3:
/* 536 */         setFormat(12, true);
/*     */         return;
/*     */       case 4:
/* 539 */         setFormat(applyPrec, false);
/*     */         return;
/*     */       case 5:
/* 542 */         setFormat(applyPrec, true);
/*     */         return;
/*     */       case 6:
/* 545 */         setFormat(applyPrec, false);
/*     */         return;
/*     */       case 7:
/* 548 */         setFormat(applyPrec, false);
/*     */         return;
/*     */       case 16:
/* 551 */         setFormat(applyPrec, true);
/*     */         return;
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/*     */       case 23:
/* 567 */         setFormat(applyPrec, true);
/*     */         return;
/*     */       case 24:
/* 570 */         setFormat(applyPrec, true);
/*     */         return;
/*     */     } 
/* 573 */     setFormat(applyPrec, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getPrecisionFromFormat(String format) {
/* 580 */     if (format != null) {
/*     */       
/* 582 */       int index = format.indexOf(".");
/* 583 */       if (index > 0)
/* 584 */         return format.length() - index - 1; 
/*     */     } 
/* 586 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getForcedFormat() {
/* 591 */     String format = (this.m_Format != null) ? 
/* 592 */       this.m_Format.toPattern() : 
/* 593 */       null;
/* 594 */     if (format != null) {
/*     */       
/* 596 */       int index = format.indexOf(".");
/* 597 */       if (index > 0) {
/*     */         
/* 599 */         StringBuilder buffer = new StringBuilder(format);
/* 600 */         for (int i = index + 1; i < format.length(); i++) {
/*     */           
/* 602 */           char ch = buffer.charAt(i);
/* 603 */           switch (ch) {
/*     */             
/*     */             case '#':
/* 606 */               buffer.setCharAt(i, '0');
/*     */               break;
/*     */             case '0':
/*     */               break;
/*     */             default:
/* 611 */               i = format.length();
/*     */               break;
/*     */           } 
/*     */         } 
/* 615 */         String newFormat = buffer.toString();
/* 616 */         if (newFormat.compareToIgnoreCase(format) == 0)
/* 617 */           return null; 
/* 618 */         return newFormat;
/*     */       } 
/* 620 */       return format;
/*     */     } 
/* 622 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getCreditDebitText(Number nNumber, ILbsCultureInfo culture) {
/* 627 */     if (culture == null) {
/* 628 */       return "";
/*     */     }
/* 630 */     if (Math.abs(nNumber.doubleValue()) < 1.0E-9D) {
/* 631 */       return "";
/*     */     }
/* 633 */     return (nNumber.doubleValue() < 0.0D) ? 
/* 634 */       culture.getCreditText() : 
/* 635 */       culture.getDebitText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String InternalNumberToString(Number nNumber, boolean bForceDecimals) {
/* 641 */     String strNumber = "";
/* 642 */     Number value = Integer.valueOf(0);
/*     */     
/* 644 */     if (nNumber instanceof BigDecimal && isMoreThan16Digits(nNumber)) {
/*     */       
/* 646 */       value = nNumber;
/* 647 */       if (requiresAbsValue()) {
/* 648 */         value = ((BigDecimal)value).abs();
/*     */       }
/*     */     } else {
/*     */       
/* 652 */       value = Double.valueOf(nNumber.doubleValue());
/* 653 */       if (requiresAbsValue()) {
/* 654 */         value = Double.valueOf(Math.abs(((Double)value).doubleValue()));
/*     */       }
/*     */     } 
/* 657 */     if (bForceDecimals) {
/*     */       
/* 659 */       String newFormat = getForcedFormat();
/* 660 */       if (newFormat != null)
/*     */       {
/* 662 */         DecimalFormat formatter = createFormat(newFormat);
/* 663 */         if (this.m_DecimalSepChanged)
/*     */         {
/* 665 */           changeSeperators(formatter);
/*     */         }
/* 667 */         strNumber = formatter.format(value);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 672 */       if (this.m_DecimalSepChanged)
/*     */       {
/* 674 */         changeSeperators(this.m_Format);
/*     */       }
/* 676 */       strNumber = this.m_Format.format(value);
/*     */     } 
/* 678 */     return strNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMoreThan16Digits(Number nNumber) {
/* 684 */     return (nNumber.toString().length() > 16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void changeSeperators(DecimalFormat formatter) {
/* 690 */     DecimalFormatSymbols newSymbols = new DecimalFormatSymbols(this.m_Locale);
/* 691 */     newSymbols.setDecimalSeparator(this.m_NewDecimalSeparator);
/* 692 */     newSymbols.setGroupingSeparator(this.m_NewGroupingSeparator);
/* 693 */     formatter.setDecimalFormatSymbols(newSymbols);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeperators(int seperatorChoice) {
/* 698 */     if (seperatorChoice == 1) {
/*     */       
/* 700 */       setDecimalSepChanged(true);
/* 701 */       setNewDecimalSeparator('.');
/* 702 */       setNewGroupingSeparator(',');
/*     */     }
/* 704 */     else if (seperatorChoice == 2) {
/*     */       
/* 706 */       setDecimalSepChanged(true);
/* 707 */       setNewDecimalSeparator(',');
/* 708 */       setNewGroupingSeparator('.');
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String InternalNumberToString(String szFormat, Number nNumber) {
/* 714 */     double value = nNumber.doubleValue();
/* 715 */     if (requiresAbsValue())
/* 716 */       value = Math.abs(value); 
/* 717 */     String strNumber = getDecimalFormat(szFormat).format(value);
/*     */     
/* 719 */     return strNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String InternalNumberToString(String szFormat, Number nNumber, char decimalSeparator, char groupingSeparator) {
/* 724 */     double value = nNumber.doubleValue();
/* 725 */     if (requiresAbsValue())
/* 726 */       value = Math.abs(value); 
/* 727 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
/* 728 */     formatSymbols.setDecimalSeparator(decimalSeparator);
/* 729 */     formatSymbols.setGroupingSeparator(groupingSeparator);
/* 730 */     DecimalFormat df = new DecimalFormat(szFormat, formatSymbols);
/* 731 */     String strNumber = df.format(value);
/* 732 */     return strNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String numberToString(Number nNumber) {
/* 737 */     DecimalFormat df = new DecimalFormat();
/* 738 */     df.setGroupingUsed(false);
/* 739 */     df.setMaximumFractionDigits(340);
/*     */ 
/*     */     
/* 742 */     df.setDecimalFormatSymbols(m_DecimalFormatEN);
/* 743 */     return df.format(nNumber);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresAbsValue() {
/* 748 */     return !(this.m_FormatSpec != 10 && this.m_FormatSpec != 11 && this.m_FormatSpec != 12 && 
/* 749 */       this.m_FormatSpec != 13 && this.m_FormatSpec != 9);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrenciesBase getCurrencyBase() {
/* 754 */     return this.m_currencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrencyBase(JLbsCurrenciesBase base) {
/* 759 */     this.m_currencyBase = base;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableZeros() {
/* 764 */     return this.m_EnableZeros;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnableZeros(boolean b) {
/* 769 */     this.m_EnableZeros = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean shouldTrimZeros(int formatSpec) {
/* 774 */     switch (formatSpec) {
/*     */       
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/*     */       case 23:
/* 794 */         return false;
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
/* 805 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String trimTrailingZeros(String number) {
/* 810 */     if (!shouldTrimZeros(this.m_FormatSpec)) {
/* 811 */       return number;
/*     */     }
/* 813 */     int len = number.length();
/* 814 */     int i = len - 1;
/*     */     
/* 816 */     if (number.indexOf('.') != -1) {
/*     */ 
/*     */       
/* 819 */       while (i >= 0) {
/*     */         
/* 821 */         char ch = number.charAt(i);
/* 822 */         if (ch != '0' && ch != '.') {
/*     */           break;
/*     */         }
/* 825 */         if (ch == '.') {
/*     */           break;
/*     */         }
/* 828 */         i--;
/*     */       } 
/*     */       
/* 831 */       if (i != len - 1 && i > 0) {
/* 832 */         return number.substring(0, i);
/*     */       }
/*     */     } 
/* 835 */     return number;
/*     */   }
/*     */ 
/*     */   
/*     */   public DecimalFormat getFormat() {
/* 840 */     return this.m_Format;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFromFocusLost() {
/* 845 */     return this.m_FromFocusLost;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromFocusLost(boolean fromFocusLost) {
/* 850 */     this.m_FromFocusLost = fromFocusLost;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDecimalSepChanged() {
/* 855 */     return this.m_DecimalSepChanged;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDecimalSepChanged(boolean decimalSepChanged) {
/* 860 */     this.m_DecimalSepChanged = decimalSepChanged;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getNewDecimalSeparator() {
/* 865 */     return this.m_NewDecimalSeparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNewDecimalSeparator(char newDecimalSeparator) {
/* 870 */     this.m_NewDecimalSeparator = newDecimalSeparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getNewGroupingSeparator() {
/* 875 */     return this.m_NewGroupingSeparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNewGroupingSeparator(char newGroupingSeparator) {
/* 880 */     this.m_NewGroupingSeparator = newGroupingSeparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 885 */     (JLbsRealNumberFormatterInstanceHolder.getInstance()).m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsCultureInfo getCultureInfo() {
/* 890 */     return (JLbsRealNumberFormatterInstanceHolder.getInstance()).m_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setThreadLocalCultureInfo(ILbsCultureInfo cultureInfo) {
/* 895 */     m_ThreadLocalCultureInfo.set(cultureInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DecimalFormat getDecimalFormat(String format) {
/*     */     DecimalFormat decimalFormat;
/* 902 */     Map<String, DecimalFormat> decimalFormats = (getFieldHolder()).ms_DecimalFormats;
/* 903 */     synchronized (decimalFormats) {
/*     */       
/* 905 */       decimalFormat = decimalFormats.get(format);
/* 906 */       if (decimalFormat != null)
/*     */       {
/* 908 */         return decimalFormat;
/*     */       }
/*     */       
/* 911 */       decimalFormat = createFormat(null);
/* 912 */       if (decimalFormats.size() >= ms_DecimalFormatsCapacity)
/*     */       {
/* 914 */         decimalFormats.clear();
/*     */       }
/* 916 */       decimalFormats.put(format, decimalFormat);
/*     */     } 
/* 918 */     return decimalFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDecimalSeperator() {
/* 923 */     if (this.m_Format != null && this.m_Format.getDecimalFormatSymbols() != null) {
/* 924 */       return this.m_Format.getDecimalFormatSymbols().getDecimalSeparatorString();
/*     */     }
/* 926 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGrouppingSeperator() {
/* 931 */     if (this.m_Format != null && this.m_Format.getDecimalFormatSymbols() != null) {
/* 932 */       return this.m_Format.getDecimalFormatSymbols().getGroupingSeparatorString();
/*     */     }
/* 934 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxFractionDigit() {
/* 939 */     if (this.m_Format != null)
/* 940 */       return this.m_Format.getMaximumFractionDigits(); 
/* 941 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsRealNumberFormatterInstanceHolder getFieldHolder() {
/* 946 */     return JLbsRealNumberFormatterInstanceHolder.getInstance();
/*     */   }
/*     */   
/*     */   public static class JLbsRealNumberFormatterInstanceHolder
/*     */   {
/* 951 */     private ILbsCultureInfo m_CultureInfo = null;
/* 952 */     protected Map<String, DecimalFormat> ms_DecimalFormats = new HashMap<>();
/*     */ 
/*     */     
/*     */     public static JLbsRealNumberFormatterInstanceHolder getInstance() {
/* 956 */       return (JLbsRealNumberFormatterInstanceHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsRealNumberFormatterInstanceHolder.class);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximumIntegerDigits() {
/* 962 */     if (this.m_Format != null)
/* 963 */       return this.m_Format.getMaximumIntegerDigits(); 
/* 964 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForceDecimal() {
/* 969 */     return this.m_ForceDecimal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceDecimal(boolean forceDecimal) {
/* 974 */     this.m_ForceDecimal = forceDecimal;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsRealNumberFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */