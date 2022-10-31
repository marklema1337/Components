/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.ghasemkiani.util.SimplePersianCalendar;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateUtil
/*     */ {
/*  18 */   private static char dateSeparator = '/';
/*  19 */   private static char timeSeparator = ':';
/*     */ 
/*     */   
/*     */   public static boolean IsLeapYear(Calendar c) {
/*  23 */     boolean isOK = false;
/*  24 */     isOK = !(c.get(1) % 4 != 0);
/*     */ 
/*     */     
/*  27 */     return isOK;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int MaxDays(Calendar c) {
/*  32 */     int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*  33 */     daysInMonths[1] = daysInMonths[1] + (IsLeapYear(c) ? 
/*  34 */       1 : 
/*  35 */       0);
/*  36 */     return daysInMonths[c.get(2)];
/*     */   }
/*     */ 
/*     */   
/*     */   public static int MaxDays(int m, int y) {
/*  41 */     Calendar dt = new GregorianCalendar();
/*  42 */     dt.set(y, m, 1, 0, 0, 0);
/*  43 */     dt.set(14, 0);
/*  44 */     return MaxDays(dt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar AfterXDays(Calendar dt, int value) {
/*  49 */     Calendar newDt = JLbsDateUtil.getCopyOf(dt);
/*  50 */     newDt.add(5, value);
/*  51 */     return newDt;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getToday() {
/*  56 */     Calendar now = Calendar.getInstance();
/*  57 */     Calendar dt = new GregorianCalendar();
/*  58 */     dt.set(now.get(1), now.get(2), now.get(5), 0, 0, 0);
/*  59 */     dt.set(14, 0);
/*  60 */     return dt;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getMinLogoDate() {
/*  65 */     Calendar dt = new GregorianCalendar();
/*  66 */     dt.set(1900, 0, 1, 0, 0, 0);
/*  67 */     dt.set(14, 0);
/*  68 */     return dt;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getMaxLogoDate() {
/*  73 */     Calendar dt = new GregorianCalendar();
/*  74 */     dt.set(2999, 11, 31, 0, 0, 0);
/*  75 */     dt.set(14, 0);
/*  76 */     return dt;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getMaxDate(Calendar dt1, Calendar dt2) {
/*  81 */     if (dt1 == null)
/*     */     {
/*  83 */       return dt2;
/*     */     }
/*  85 */     if (dt2 == null)
/*     */     {
/*  87 */       return dt1;
/*     */     }
/*     */     
/*  90 */     if (dt1.after(dt2)) {
/*  91 */       return dt1;
/*     */     }
/*  93 */     return dt2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getMinDate(Calendar dt1, Calendar dt2) {
/*  98 */     if (dt1.before(dt2)) {
/*  99 */       return dt1;
/*     */     }
/* 101 */     return dt2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar aMonthAfter(Calendar dt) {
/* 106 */     int mNr = dt.get(2), yNr = dt.get(1);
/*     */     
/* 108 */     if (mNr == 12) {
/*     */       
/* 110 */       mNr = 1;
/* 111 */       yNr++;
/*     */     }
/*     */     else {
/*     */       
/* 115 */       mNr++;
/*     */     } 
/* 117 */     Calendar newDt = new GregorianCalendar();
/* 118 */     newDt.set(yNr, mNr, 1, 0, 0, 0);
/* 119 */     newDt.set(14, 0);
/* 120 */     return newDt;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMonthsBetween(Calendar dt1, Calendar dt2) {
/* 125 */     int y1 = dt1.get(1), y2 = dt2.get(1), m1 = dt1.get(2), m2 = dt2.get(2);
/*     */     
/* 127 */     int yearDiff = y2 - y1;
/* 128 */     if (yearDiff != 0)
/*     */     {
/* 130 */       return 13 - m1 + (yearDiff - 1) * 12 + m2;
/*     */     }
/*     */ 
/*     */     
/* 134 */     return m2 - m1 + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] YMDiffValues(Calendar dt1, Calendar dt2) {
/* 140 */     int y1 = dt1.get(1), m1 = dt1.get(2) + 1, d1 = dt1.get(5);
/*     */     
/* 142 */     Calendar tDt = AfterXDays(dt2, 1);
/*     */     
/* 144 */     int y2 = tDt.get(1), m2 = tDt.get(2) + 1, d2 = tDt.get(5);
/*     */     
/* 146 */     int y = y2 - y1, m = 0, d = 0;
/* 147 */     if (dt1.after(tDt)) {
/*     */       
/* 149 */       if (y < 1) {
/*     */         
/* 151 */         if (m1 != m2) {
/* 152 */           m = m2 - m1 + 1;
/*     */         } else {
/* 154 */           m = 0;
/*     */         } 
/*     */       } else {
/* 157 */         m = m2 - m1 + 1;
/* 158 */       }  d = MaxDays(m1, y1) + d2 - d1;
/*     */     }
/*     */     else {
/*     */       
/* 162 */       if (y < 1) {
/*     */         
/* 164 */         if (m1 != m2) {
/* 165 */           m = m2 - m1;
/*     */         } else {
/* 167 */           m = 0;
/*     */         } 
/*     */       } else {
/* 170 */         m = m2 - m1;
/* 171 */       }  d = d2 - d1;
/*     */     } 
/*     */     
/* 174 */     if (m < 0 && y > 0) {
/*     */       
/* 176 */       y--;
/* 177 */       m += 12;
/*     */     } 
/*     */     
/* 180 */     int[] result = { y, m, d };
/* 181 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Calendar XUnitAfter(Calendar dt, char timeUnit, int value) {
/* 187 */     int y = dt.get(1), m = dt.get(2), d = dt.get(5);
/*     */     
/* 189 */     Calendar result = new GregorianCalendar();
/*     */     
/* 191 */     switch (timeUnit) {
/*     */       
/*     */       case 'y':
/* 194 */         y += value;
/* 195 */         d = Math.min(MaxDays(m, y), d);
/* 196 */         result.set(y, m, d, 0, 0, 0);
/* 197 */         result.set(14, 0);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSameDate(Calendar date1, Calendar date2) {
/* 210 */     if (date1 == null && date2 != null)
/* 211 */       return false; 
/* 212 */     if (date1 != null && date2 == null)
/* 213 */       return false; 
/* 214 */     if (date1 == null && date2 == null) {
/* 215 */       return true;
/*     */     }
/* 217 */     return (JLbsDateUtil.dateCompare(date1, date2) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String dateToStr(Calendar date) {
/* 222 */     String string = null;
/* 223 */     if (date != null) {
/*     */       String y, m, d;
/*     */       
/* 226 */       if (date instanceof SimplePersianCalendar) {
/*     */         
/* 228 */         SimplePersianCalendar persian = (SimplePersianCalendar)date;
/* 229 */         y = Integer.valueOf(persian.getDateFields().getYear()).toString();
/* 230 */         m = Integer.valueOf(persian.getDateFields().getMonth() + 1).toString();
/* 231 */         d = Integer.valueOf(persian.getDateFields().getDay()).toString();
/*     */       }
/*     */       else {
/*     */         
/* 235 */         y = Integer.valueOf(date.get(1)).toString();
/* 236 */         m = Integer.valueOf(date.get(2) + 1).toString();
/* 237 */         d = Integer.valueOf(date.get(5)).toString();
/*     */       } 
/* 239 */       string = String.valueOf(StringUtilExtra.padLeft(d, '0', 2)) + dateSeparator + StringUtilExtra.padLeft(m, '0', 2) + dateSeparator + 
/* 240 */         StringUtilExtra.padLeft(y, '0', 4);
/*     */     } 
/*     */     
/* 243 */     return string;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String timeToStr(Calendar date) {
/* 248 */     String string = null;
/* 249 */     if (date != null) {
/*     */       
/* 251 */       String h = Integer.valueOf(date.get(11)).toString();
/* 252 */       String m = Integer.valueOf(date.get(12)).toString();
/* 253 */       String s = Integer.valueOf(date.get(13)).toString();
/*     */       
/* 255 */       string = String.valueOf(StringUtilExtra.padLeft(h, '0', 2)) + timeSeparator + StringUtilExtra.padLeft(m, '0', 2) + timeSeparator + 
/* 256 */         StringUtilExtra.padLeft(s, '0', 2);
/*     */     } 
/*     */     
/* 259 */     return string;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String dateRangeToStr(Calendar begDate, Calendar endDate) {
/* 264 */     return String.valueOf(dateToStr(begDate)) + "-" + dateToStr(endDate);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\DateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */