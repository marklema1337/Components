/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.lbs.controls.JLbsUpdateFocusAdapter;
/*     */ import com.lbs.globalization.ILbsCultureCalendarHandler;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsDateUtil;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import javax.swing.text.MaskFormatter;
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
/*     */ public class LbsDateEditImplementor
/*     */ {
/*     */   private ILbsInternalDateEdit m_Component;
/*  34 */   private char m_chDateSeparator = '/';
/*  35 */   private char m_chPlaceHolder = '_';
/*  36 */   private String m_szDateFormat = "DD/MM/yyyy";
/*  37 */   private String m_szDisplayFormat = null;
/*  38 */   private DateFormat m_DateFormat = null;
/*  39 */   private int m_DateFmtSpec = -1;
/*  40 */   private JLbsDateFormatter m_DispFormat = null;
/*  41 */   private ILbsCultureInfo m_CultureInfo = null;
/*     */   
/*     */   private Object m_MessageDialogImpl;
/*     */   
/*     */   private boolean m_ResetTime;
/*     */   private boolean m_Modified = false;
/*     */   private Calendar m_BoundedCalendar;
/*     */   private Calendar m_CalendarValue;
/*     */   private boolean m_NoCompanyValidate = false;
/*     */   
/*     */   public Calendar getCalendarValue() {
/*  52 */     return this.m_CalendarValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar calendarValue) {
/*  57 */     this.m_BoundedCalendar = calendarValue;
/*  58 */     this.m_CalendarValue = (calendarValue != null) ? 
/*  59 */       (Calendar)calendarValue.clone() : 
/*  60 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/*  65 */     return this.m_ResetTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean resetTime) {
/*  70 */     this.m_ResetTime = resetTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsDateEditImplementor(ILbsInternalDateEdit component, String szDateFormat, String szDisplayFormat) {
/*  75 */     this.m_Component = component;
/*  76 */     this.m_szDisplayFormat = szDisplayFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(String szFormat) {
/*  81 */     if (!this.m_Component.setDateFormat(szFormat))
/*  82 */       this.m_Component.setDateFormat("DD/MM/yyyy"); 
/*  83 */     this.m_Component.addFocusListener((FocusListener)new JLbsUpdateFocusAdapter());
/*  84 */     this.m_Component.resetModified(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFormat() {
/*  89 */     return this.m_szDateFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageDialogImpl(Object messageDialogImpl) {
/*  94 */     this.m_MessageDialogImpl = messageDialogImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(String szFormat) {
/*     */     try {
/* 101 */       this.m_szDateFormat = (this.m_chDateSeparator != '/') ? 
/* 102 */         szFormat.replace('/', this.m_chDateSeparator) : 
/* 103 */         szFormat;
/* 104 */       this.m_DateFormat = null;
/* 105 */       MaskFormatter formatter = new MaskFormatter(getMaskPattern(this.m_szDateFormat));
/* 106 */       formatter.setPlaceholderCharacter(this.m_chPlaceHolder);
/* 107 */       this.m_Component.setDateFormatter(formatter);
/* 108 */       return true;
/*     */     }
/* 110 */     catch (Exception ex) {
/*     */       
/* 112 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(int iDateFmtSpec) {
/* 118 */     String szDateFormat = JLbsDateFormatter.FMT_EditFormatStrings[(iDateFmtSpec >= 0 && iDateFmtSpec < JLbsDateFormatter.FMT_EditFormatStrings.length) ? 
/* 119 */         iDateFmtSpec : 
/* 120 */         0];
/* 121 */     return this.m_Component.setDateFormat(szDateFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayFormat() {
/* 126 */     return this.m_szDisplayFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayFormat(String szFormat) {
/* 131 */     this.m_szDisplayFormat = szFormat;
/* 132 */     this.m_DateFmtSpec = -1;
/* 133 */     this.m_DispFormat = null;
/* 134 */     if (!this.m_Component.hasFocus()) {
/* 135 */       this.m_Component.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDisplayFormat(int iFmtSpec) {
/* 140 */     this.m_DateFmtSpec = iFmtSpec;
/* 141 */     if (iFmtSpec >= 0)
/* 142 */       this.m_szDisplayFormat = JLbsDateFormatter.FMT_EditFormatStrings[(iFmtSpec >= 0 && iFmtSpec < JLbsDateFormatter.FMT_EditFormatStrings.length) ? 
/* 143 */           iFmtSpec : 
/* 144 */           0]; 
/* 145 */     this.m_DispFormat = null;
/* 146 */     if (!this.m_Component.hasFocus()) {
/* 147 */       this.m_Component.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public char getDateSeparator() {
/* 152 */     return this.m_chDateSeparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateSeparator(char chSeparator) {
/* 157 */     if (this.m_chDateSeparator != chSeparator) {
/*     */       
/* 159 */       char chSaveSeparator = this.m_chDateSeparator;
/* 160 */       String szSaveFormat = this.m_szDateFormat;
/* 161 */       this.m_chDateSeparator = chSeparator;
/* 162 */       if (this.m_Component.setDateFormat(this.m_szDateFormat))
/* 163 */         return true; 
/* 164 */       this.m_chDateSeparator = chSaveSeparator;
/* 165 */       this.m_Component.setDateFormat(szSaveFormat);
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/* 172 */     return this.m_chPlaceHolder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/* 177 */     if (this.m_chPlaceHolder != chPlaceHolder) {
/*     */       
/* 179 */       this.m_chPlaceHolder = chPlaceHolder;
/* 180 */       this.m_Component.setDateFormat(this.m_szDateFormat);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/* 186 */     return (this.m_CalendarValue != null) ? 
/* 187 */       this.m_CalendarValue.getTime() : 
/* 188 */       null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date parseDate(String szText, String szDateFormat) {
/*     */     try {
/* 195 */       if (allTokensEmpty(szText)) {
/* 196 */         return null;
/*     */       }
/* 198 */       Date date = null;
/* 199 */       if (this.m_CultureInfo != null && this.m_CultureInfo.getCalendarType() == 1) {
/*     */         
/* 201 */         JLbsCultureInfoBase cib = (JLbsCultureInfoBase)this.m_CultureInfo;
/* 202 */         date = cib.parseDate(szText, szDateFormat, this.m_Component);
/*     */       }
/*     */       else {
/*     */         
/* 206 */         DateFormat format = getDateFormatObject();
/* 207 */         format.setLenient(true);
/* 208 */         date = format.parse(this.m_Component.stripDateNumbers(szText));
/*     */       } 
/*     */       
/* 211 */       if (this.m_CultureInfo == null) {
/*     */         
/*     */         try {
/*     */           
/* 215 */           Class<?> cls = Class.forName("com.lbs.globalization.JLbsDefaultCultureInfo");
/* 216 */           this.m_CultureInfo = (ILbsCultureInfo)cls.newInstance();
/*     */         }
/* 218 */         catch (Throwable throwable) {}
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 223 */       Calendar calendar = this.m_CultureInfo.getCalendarInstance();
/* 224 */       calendar.setTime(date);
/* 225 */       int iYear = calendar.get(1);
/* 226 */       if (iYear < 100 && szDateFormat != null) {
/*     */         
/* 228 */         String[] tokens = szDateFormat.split("/");
/* 229 */         for (int i = 0; i < tokens.length; i++) {
/*     */           
/* 231 */           if (tokens[i].compareToIgnoreCase("yy") == 0 || tokens[i].compareToIgnoreCase("yyy") == 0) {
/*     */             
/* 233 */             calendar.add(1, 2000);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 238 */       return calendar.getTime();
/*     */     }
/* 240 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 243 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/*     */     try {
/* 250 */       Calendar result = this.m_CultureInfo.getCalendarInstance();
/* 251 */       Date date = this.m_Component.getDate();
/* 252 */       if (date != null) {
/* 253 */         result.setTime(date);
/*     */       }
/* 255 */       if (this.m_Component instanceof com.lbs.controls.emulator.LbsComponentEmulator && date == null) {
/* 256 */         return null;
/*     */       }
/* 258 */       return result;
/*     */     }
/* 260 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 263 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDateFromPopup(Date date) {
/* 268 */     if (date == null) {
/* 269 */       this.m_Component.setText("");
/*     */     } else {
/* 271 */       this.m_Component.setText(this.m_Component.getDateString(date));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDate(Date date) throws Exception {
/* 276 */     if (date == null) {
/*     */       
/* 278 */       this.m_CalendarValue = null;
/* 279 */       if (this.m_BoundedCalendar != null && !this.m_BoundedCalendar.equals(this.m_CalendarValue))
/* 280 */         this.m_Modified = true; 
/*     */       return;
/*     */     } 
/* 283 */     GregorianCalendar temp = new GregorianCalendar();
/* 284 */     temp.setTime(date);
/* 285 */     if (this.m_CalendarValue == null) {
/*     */       
/* 287 */       this.m_CalendarValue = new GregorianCalendar();
/* 288 */       this.m_CalendarValue.set(10, 0);
/* 289 */       this.m_CalendarValue.set(11, 0);
/* 290 */       this.m_CalendarValue.set(12, 0);
/* 291 */       this.m_CalendarValue.set(13, 0);
/* 292 */       this.m_CalendarValue.set(14, 0);
/*     */     } 
/* 294 */     this.m_CalendarValue.set(1, temp.get(1));
/* 295 */     this.m_CalendarValue.set(2, temp.get(2));
/* 296 */     this.m_CalendarValue.set(5, temp.get(5));
/* 297 */     if (this.m_ResetTime) {
/*     */       
/* 299 */       this.m_CalendarValue.set(10, 0);
/* 300 */       this.m_CalendarValue.set(11, 0);
/* 301 */       this.m_CalendarValue.set(12, 0);
/* 302 */       this.m_CalendarValue.set(13, 0);
/* 303 */       this.m_CalendarValue.set(14, 0);
/*     */     } 
/* 305 */     if (!this.m_CalendarValue.equals(this.m_BoundedCalendar))
/* 306 */       this.m_Modified = true; 
/* 307 */     setDateFromPopup(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar date) {
/* 312 */     if (date == null) {
/* 313 */       this.m_Component.setDate(null);
/*     */     } else {
/* 315 */       this.m_Component.setDate(date.getTime());
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getDateString(Date date) {
/*     */     Calendar cal;
/* 321 */     if (JLbsDateUtil.getServerTimeZone() != null && !JLbsDateUtil.getServerTimeZone().equals(TimeZone.getDefault())) {
/*     */       
/* 323 */       cal = new GregorianCalendar(JLbsDateUtil.getServerTimeZone());
/* 324 */       int hourDiff = JLbsDateUtil.getTimeZoneDifference(TimeZone.getDefault(), JLbsDateUtil.getServerTimeZone());
/* 325 */       cal.setTimeInMillis(date.getTime());
/* 326 */       cal.add(11, hourDiff);
/*     */     }
/*     */     else {
/*     */       
/* 330 */       cal = Calendar.getInstance();
/* 331 */       cal.setTime(date);
/*     */     } 
/*     */     
/* 334 */     if (JLbsDateUtil.isSameDay(cal, JLbsConstants.MIN_DATE) || JLbsDateUtil.dateCompare(cal, JLbsConstants.MIN_DATE) < 0)
/*     */     {
/* 336 */       return getMaskPattern(this.m_szDateFormat, this.m_chPlaceHolder);
/*     */     }
/* 338 */     String szText = null;
/* 339 */     if (this.m_CultureInfo instanceof ILbsCultureCalendarHandler)
/* 340 */       szText = ((ILbsCultureCalendarHandler)this.m_CultureInfo).formatDate(date, this.m_szDisplayFormat, this.m_Component); 
/* 341 */     if (szText != null)
/* 342 */       return szText; 
/* 343 */     DateFormat format = getDateFormatObject();
/* 344 */     return format.format(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 349 */     return this.m_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo culture) {
/* 354 */     this.m_CultureInfo = culture;
/* 355 */     this.m_Component.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public String stripDateNumbers(String text) {
/* 360 */     if (text == null || text.length() == 0)
/* 361 */       return text; 
/* 362 */     StringBuilder buffer = new StringBuilder(text);
/* 363 */     boolean bInNumber = false;
/* 364 */     int index = 0;
/* 365 */     int iBlockLen = 0;
/* 366 */     while (index < buffer.length()) {
/*     */       
/* 368 */       char ch = buffer.charAt(index);
/* 369 */       if (ch == this.m_chPlaceHolder) {
/*     */         
/* 371 */         buffer.setCharAt(index, '0');
/* 372 */         ch = '0';
/*     */       } 
/* 374 */       switch (ch) {
/*     */         
/*     */         case '0':
/* 377 */           if (!bInNumber) {
/*     */             
/* 379 */             ch = (index < buffer.length() - 1) ? 
/* 380 */               buffer.charAt(index + 1) : Character
/* 381 */               .MIN_VALUE;
/* 382 */             if (ch >= '0' && ch <= '9') {
/*     */               
/* 384 */               buffer.delete(index, index + 1);
/*     */               continue;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         default:
/* 390 */           bInNumber = (ch > '0' && ch <= '9');
/* 391 */           iBlockLen = bInNumber ? 
/* 392 */             iBlockLen++ : 
/* 393 */             0;
/*     */           break;
/*     */       } 
/* 396 */       index++;
/*     */     } 
/* 398 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String parseDate(Date date) {
/*     */     Calendar cal;
/* 404 */     if (JLbsDateUtil.getServerTimeZone() != null && !JLbsDateUtil.getServerTimeZone().equals(TimeZone.getDefault())) {
/*     */       
/* 406 */       cal = new GregorianCalendar(JLbsDateUtil.getServerTimeZone());
/* 407 */       int hourDiff = JLbsDateUtil.getTimeZoneDifference(TimeZone.getDefault(), JLbsDateUtil.getServerTimeZone());
/* 408 */       cal.setTimeInMillis(date.getTime());
/* 409 */       cal.add(11, hourDiff);
/* 410 */       date.setTime(cal.getTimeInMillis());
/*     */     }
/*     */     else {
/*     */       
/* 414 */       cal = Calendar.getInstance();
/* 415 */       cal.setTime(date);
/*     */     } 
/* 417 */     if (JLbsDateUtil.isSameDay(cal, JLbsConstants.MIN_DATE))
/*     */     {
/* 419 */       return getMaskPattern(this.m_szDateFormat, this.m_chPlaceHolder);
/*     */     }
/* 421 */     JLbsDateFormatter dispFormat = getDisplayFormatObject();
/* 422 */     return dispFormat.format(date);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsDateFormatter getDisplayFormatObject() {
/* 427 */     if (this.m_DispFormat == null)
/* 428 */       if (this.m_DateFmtSpec >= 0) {
/* 429 */         this.m_DispFormat = new JLbsDateFormatter(this.m_DateFmtSpec, this.m_CultureInfo);
/*     */       } else {
/* 431 */         this.m_DispFormat = new JLbsDateFormatter(this.m_szDisplayFormat, this.m_CultureInfo);
/* 432 */       }   return this.m_DispFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText(String result) {
/* 437 */     if (result != null && this.m_chPlaceHolder != '0' && result.indexOf(this.m_chPlaceHolder) >= 0) {
/*     */       
/* 439 */       StringBuilder buffer = new StringBuilder(result.length());
/* 440 */       boolean atTheBeginning = true;
/* 441 */       int digitCount = 0;
/* 442 */       for (int i = 0; i < result.length(); i++) {
/*     */         
/* 444 */         char ch = result.charAt(i);
/* 445 */         if (ch == this.m_chPlaceHolder && atTheBeginning) {
/* 446 */           ch = '0';
/* 447 */         } else if (Character.isDigit(ch)) {
/*     */           
/* 449 */           atTheBeginning = false;
/* 450 */           digitCount++;
/*     */         }
/* 452 */         else if (ch == this.m_chDateSeparator) {
/* 453 */           atTheBeginning = true;
/* 454 */         }  buffer.append(ch);
/*     */       } 
/* 456 */       if (digitCount > 0)
/* 457 */         return buffer.toString(); 
/*     */     } 
/* 459 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultVisibleChars() {
/* 464 */     String dateFormat = (this.m_szDateFormat == null) ? 
/* 465 */       "" : 
/* 466 */       this.m_szDateFormat;
/* 467 */     String displayFormat = (this.m_szDisplayFormat == null) ? 
/* 468 */       "" : 
/* 469 */       this.m_szDisplayFormat;
/* 470 */     int nChars = Math.max(dateFormat.length(), displayFormat.length());
/* 471 */     nChars = Math.max(nChars, 10);
/*     */     
/* 473 */     return nChars;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 478 */     if (this.m_Component.isEnableFocusEvent()) {
/*     */       
/*     */       try {
/*     */         
/* 482 */         String szText = stripDateNumbers(this.m_Component.getText());
/* 483 */         if (szText == null)
/* 484 */           return false; 
/* 485 */         String szSplitter = Character.toString(this.m_chDateSeparator);
/* 486 */         String[] valueTokens = szText.split(szSplitter);
/* 487 */         String[] formatTokens = this.m_szDateFormat.split(szSplitter);
/* 488 */         if (valueTokens == null || formatTokens == null || valueTokens.length != formatTokens.length)
/* 489 */           return false; 
/* 490 */         int iDay = 1;
/* 491 */         int iMonth = 1;
/* 492 */         int iYear = 1;
/* 493 */         for (int i = 0; i < valueTokens.length; i++) {
/*     */           
/* 495 */           String szFormat = formatTokens[i];
/* 496 */           if (szFormat == null || szFormat.length() == 0)
/* 497 */             return false; 
/* 498 */           int value = Integer.parseInt(valueTokens[i]);
/* 499 */           switch (szFormat.charAt(0)) {
/*     */             
/*     */             case 'D':
/*     */             case 'd':
/* 503 */               iDay = value;
/*     */               break;
/*     */             case 'M':
/* 506 */               iMonth = value;
/*     */               break;
/*     */             case 'Y':
/*     */             case 'y':
/* 510 */               iYear = value;
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/* 515 */         boolean retValue = (((iDay == 0 && iMonth == 0 && iYear == 0) || JLbsDateUtil.isValidDate(this.m_CultureInfo, iDay, 
/* 516 */             iMonth, iYear, this.m_MessageDialogImpl, this.m_NoCompanyValidate)) && this.m_Component.superVerifyContent());
/*     */         
/* 518 */         return retValue;
/*     */       }
/* 520 */       catch (Exception e) {
/*     */         
/* 522 */         return false;
/*     */       } 
/*     */     }
/*     */     
/* 526 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected DateFormat getDateFormatObject() {
/* 531 */     if (this.m_DateFormat == null)
/* 532 */       this.m_DateFormat = new SimpleDateFormat(this.m_szDateFormat); 
/* 533 */     return this.m_DateFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean allTokensEmpty(String text) {
/* 538 */     if (text != null)
/* 539 */       for (int i = 0; i < text.length(); i++) {
/*     */         
/* 541 */         char ch = text.charAt(i);
/* 542 */         if (ch != this.m_chDateSeparator && ch != this.m_chPlaceHolder)
/* 543 */           return false; 
/*     */       }  
/* 545 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getMaskPattern(String szDateString) {
/* 550 */     return getMaskPattern(szDateString, '#');
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getMaskPattern(String szDateString, char ch) {
/* 555 */     StringBuilder szBuffer = new StringBuilder(szDateString);
/* 556 */     int iLen = szBuffer.length();
/* 557 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 559 */       switch (szBuffer.charAt(i)) {
/*     */         
/*     */         case 'D':
/*     */         case 'M':
/*     */         case 'Y':
/*     */         case 'd':
/*     */         case 'm':
/*     */         case 'y':
/* 567 */           szBuffer.setCharAt(i, ch); break;
/*     */       } 
/*     */     } 
/* 570 */     return szBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getFormattedText() {
/* 575 */     String szText = this.m_Component.getText();
/* 576 */     String mask = getMaskPattern(this.m_szDateFormat, this.m_chPlaceHolder);
/* 577 */     if (szText.compareToIgnoreCase(mask) == 0) {
/* 578 */       return szText;
/*     */     }
/*     */     try {
/* 581 */       Date date = this.m_Component.getDate();
/* 582 */       szText = this.m_Component.parseDate(date);
/*     */     }
/* 584 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 587 */     return szText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getEmptyFormat() {
/* 597 */     return "dd/MM/yyyy";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFormat(String[] arr, int idx) {
/* 602 */     if (idx < 0 || idx > arr.length)
/* 603 */       idx = 0; 
/* 604 */     return arr[idx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 615 */     return this.m_Modified;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/* 620 */     this.m_Modified = false;
/* 621 */     setCalendarValue(newValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoCompanyValidation(boolean value) {
/* 626 */     this.m_NoCompanyValidate = value;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\LbsDateEditImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */