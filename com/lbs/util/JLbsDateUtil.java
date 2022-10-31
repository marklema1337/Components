/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class JLbsDateUtil
/*     */ {
/*  21 */   private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
/*  22 */   private static String DATE_FORMAT2 = "yyyyMMdd_HHmmss";
/*     */   
/*     */   private static TimeZone m_ServerTimeZone;
/*     */   
/*  26 */   private static String ms_ClientFlag = null;
/*     */ 
/*     */   
/*     */   public static void mergeDateTime(Calendar date, Calendar timeValue) {
/*  30 */     if (date == null)
/*     */       return; 
/*  32 */     if (timeValue == null) {
/*     */       
/*  34 */       date.set(11, 0);
/*  35 */       date.set(12, 0);
/*  36 */       date.set(13, 0);
/*  37 */       date.set(14, 0);
/*     */     }
/*     */     else {
/*     */       
/*  41 */       date.set(11, timeValue.get(11));
/*  42 */       date.set(12, timeValue.get(12));
/*  43 */       date.set(13, timeValue.get(13));
/*  44 */       date.set(14, timeValue.get(14));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetDateFormaters() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String dateToString(Calendar value) {
/*  57 */     if (value != null) {
/*     */       
/*  59 */       Date date = new Date(value.getTimeInMillis());
/*  60 */       return (new SimpleDateFormat(DATE_FORMAT)).format(date);
/*     */     } 
/*  62 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String dateToStringForFile(Calendar value) {
/*  67 */     if (value != null) {
/*     */       
/*  69 */       Date date = new Date(value.getTimeInMillis());
/*  70 */       return (new SimpleDateFormat(DATE_FORMAT2)).format(date);
/*     */     } 
/*  72 */     return "";
/*     */   }
/*     */   
/*  75 */   public static int[] daysInMonths = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*  76 */   public static String[] monthNames = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", 
/*  77 */       "October", "November", "December" };
/*  78 */   public static String[] dayNames = new String[] { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
/*     */ 
/*     */   
/*     */   public static boolean isLeap(int iYear) {
/*  82 */     return (iYear % 4 == 0 && (iYear % 100 != 0 || iYear % 400 == 0));
/*     */   }
/*     */ 
/*     */   
/*     */   private static int gDays(int d, int m, int y) {
/*  87 */     if (y < 0)
/*  88 */       y++; 
/*  89 */     if (m < 3) {
/*     */       
/*  91 */       y--;
/*  92 */       m += 10;
/*     */     } else {
/*     */       
/*  95 */       m -= 2;
/*  96 */     }  int c = y;
/*  97 */     if (c < 0)
/*  98 */       c -= 99; 
/*  99 */     c /= 100;
/*     */     
/* 101 */     return (979 * m >> 5) + (y >> 2) + (c >> 2) - c + d - 337 + 365 * y;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void truncateDate(Calendar calendar) {
/* 106 */     calendar.set(11, 0);
/* 107 */     calendar.set(12, 0);
/* 108 */     calendar.set(13, 0);
/* 109 */     calendar.set(14, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Date truncateDate(Date date) {
/* 114 */     Calendar calendar = Calendar.getInstance();
/* 115 */     calendar.setTime(date);
/* 116 */     calendar.set(11, 0);
/* 117 */     calendar.set(12, 0);
/* 118 */     calendar.set(13, 0);
/* 119 */     calendar.set(14, 0);
/*     */     
/* 121 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int gDays(Calendar date) {
/* 126 */     if (date == null)
/* 127 */       return 0; 
/* 128 */     return gDays(date.get(5), date.get(2) + 1, date.get(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int dayOfWeekMF(Calendar date) {
/* 133 */     int days = gDays(date);
/* 134 */     days %= 7;
/* 135 */     if (days < 0)
/* 136 */       days += 7; 
/* 137 */     return days + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfWeek(int y, int m, int d) {
/*     */     int a, b, c;
/* 143 */     m++;
/* 144 */     if (m < 3) {
/*     */       
/* 146 */       a = m + 10;
/* 147 */       b = (y - 1) % 100;
/* 148 */       c = (y - 1) / 100;
/*     */     }
/*     */     else {
/*     */       
/* 152 */       a = m - 2;
/* 153 */       b = y % 100;
/* 154 */       c = y / 100;
/*     */     } 
/* 156 */     return (700 + (26 * a - 2) / 10 + d + b + b / 4 + c / 4 - 2 * c) % 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getNumDays(int iMonth, int iYear) {
/* 161 */     if (iMonth == 1 && isLeap(iYear))
/* 162 */       return 29; 
/* 163 */     if (iMonth >= 0 && iMonth < 12)
/* 164 */       return daysInMonths[iMonth]; 
/* 165 */     return daysInMonths[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isValidDate(int day, int month, int year, Object messageDialogImpl) {
/* 170 */     return isValidDate(null, day, month, year, messageDialogImpl, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValidDate(ILbsCultureInfo cultureInfo, int day, int month, int year, Object messageDialogImpl, boolean noCompany) {
/* 176 */     if (month >= 1 && month <= 12 && year >= 0 && day > 0) {
/*     */       
/* 178 */       int dayCount = (cultureInfo != null) ? 
/* 179 */         cultureInfo.getNumberOfDaysInMonth(month - 1, year) : 
/* 180 */         getNumDays(month - 1, year);
/* 181 */       boolean result = (day <= dayCount);
/* 182 */       if (!result)
/* 183 */         return result; 
/* 184 */       return noCompany ? 
/* 185 */         JLbsComponentHelper.verifyCalendarValue(year, messageDialogImpl, cultureInfo, noCompany) : 
/* 186 */         JLbsComponentHelper.verifyCalendarValue(year, messageDialogImpl);
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSameDay(Calendar date1, Calendar date2) {
/* 193 */     return (date1.get(1) == date2.get(1) && date1.get(2) == date2.get(2) && 
/* 194 */       date1.get(5) == date2.get(5));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isNullDate(Calendar date) {
/* 199 */     return !(date != null && date.isSet(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int dateCompare(Calendar date1, Calendar date2) {
/* 204 */     int y1 = date1.get(1);
/* 205 */     int m1 = date1.get(2);
/* 206 */     int d1 = date1.get(5);
/* 207 */     int y2 = date2.get(1);
/* 208 */     int m2 = date2.get(2);
/* 209 */     int d2 = date2.get(5);
/*     */     
/* 211 */     if (y1 > y2)
/* 212 */       return 1; 
/* 213 */     if (y1 == y2 && m1 > m2)
/* 214 */       return 1; 
/* 215 */     if (y1 == y2 && m1 == m2 && d1 > d2)
/* 216 */       return 1; 
/* 217 */     if (y1 == y2 && m1 == m2 && d1 == d2) {
/* 218 */       return 0;
/*     */     }
/* 220 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDaysBetween(Calendar begDate, Calendar endDate) {
/* 225 */     return gDays(begDate) - gDays(endDate);
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
/*     */ 
/*     */   
/*     */   public static long getTimeInMillisBetween(Calendar date1, Calendar date2) {
/* 262 */     long milisecond1 = date1.getTimeInMillis();
/* 263 */     long milisecond2 = date2.getTimeInMillis();
/* 264 */     return Math.abs(milisecond1 - milisecond2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int maxDays(int month, int year) {
/* 269 */     return getNumDays(month, year);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int maxDaysX(int month, int year) {
/* 274 */     GregorianCalendar c = new GregorianCalendar(year, month, 1);
/*     */     
/* 276 */     return c.getActualMaximum(5);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfWeekMF(Calendar date) {
/* 281 */     int days = gDays(date);
/* 282 */     days %= 7;
/* 283 */     if (days < 0)
/* 284 */       days += 7; 
/* 285 */     return days + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getWeekIndex(int day, int month, int year) {
/* 290 */     Calendar cal = new GregorianCalendar(year, month, day);
/* 291 */     return cal.get(3);
/*     */   }
/*     */   
/*     */   public static Calendar getCopyOf(Calendar date) {
/*     */     GregorianCalendar result;
/* 296 */     int yr = date.get(1);
/* 297 */     int mn = date.get(2);
/* 298 */     int dy = date.get(5);
/*     */     
/* 300 */     checkSide();
/* 301 */     if ("0".equals(ms_ClientFlag) && m_ServerTimeZone != null) {
/* 302 */       result = new GregorianCalendar(m_ServerTimeZone);
/*     */     } else {
/* 304 */       result = new GregorianCalendar();
/* 305 */     }  result.set(yr, mn, dy, 0, 0, 0);
/* 306 */     result.set(14, 0);
/* 307 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTimeZoneDifference(TimeZone t1, TimeZone t2) {
/* 312 */     int hr = 0;
/* 313 */     if (t1 != null && t2 != null && !t1.getID().equals(t2.getID())) {
/*     */       
/* 315 */       Calendar time1 = new GregorianCalendar(t1);
/* 316 */       time1.set(11, 12);
/* 317 */       Calendar time2 = new GregorianCalendar(t2);
/* 318 */       time2.setTimeInMillis(time1.getTimeInMillis());
/* 319 */       int l1 = time1.get(11);
/* 320 */       int l2 = time2.get(11);
/* 321 */       hr = l2 - l1;
/*     */     } 
/* 323 */     return hr;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getCalendarByTimeZone(Calendar cal, TimeZone timeZone) {
/* 328 */     if (cal != null && timeZone != null)
/*     */     {
/* 330 */       if (!cal.getTimeZone().getID().equals(timeZone.getID())) {
/*     */         
/* 332 */         Calendar temp = new GregorianCalendar(timeZone);
/* 333 */         temp.setTimeInMillis(cal.getTimeInMillis());
/*     */         
/* 335 */         Calendar cal2 = Calendar.getInstance();
/* 336 */         cal2.set(1, temp.get(1));
/* 337 */         cal2.set(2, temp.get(2));
/* 338 */         cal2.set(5, temp.get(5));
/* 339 */         cal2.set(11, temp.get(11));
/* 340 */         cal2.set(12, temp.get(12));
/* 341 */         cal2.set(13, temp.get(13));
/* 342 */         cal2.set(14, temp.get(14));
/* 343 */         return cal2;
/*     */       } 
/*     */     }
/* 346 */     return cal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 351 */     Calendar cal = GregorianCalendar.getInstance();
/* 352 */     cal.setTimeInMillis(1229651518149L);
/* 353 */     System.out.println(dateToString(cal));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setServerTimeZone(TimeZone serverTimeZone) {
/* 359 */     m_ServerTimeZone = serverTimeZone;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TimeZone getServerTimeZone() {
/* 364 */     checkSide();
/* 365 */     return "0".equals(ms_ClientFlag) ? 
/* 366 */       m_ServerTimeZone : 
/* 367 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkSide() {
/* 372 */     if (ms_ClientFlag == null)
/*     */     {
/* 374 */       synchronized (JLbsDateUtil.class) {
/*     */         
/* 376 */         ms_ClientFlag = System.getProperty("lbs_is_client");
/* 377 */         if (ms_ClientFlag == null)
/* 378 */           ms_ClientFlag = "0"; 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsDateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */