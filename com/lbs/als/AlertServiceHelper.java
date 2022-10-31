/*     */ package com.lbs.als;
/*     */ 
/*     */ import com.ibm.icu.math.BigDecimal;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AlertServiceHelper
/*     */ {
/*     */   public static final int TERMINATIONMETHOD_INFINITE = 0;
/*     */   public static final int TERMINATIONMETHOD_USE_RECURRENCE_COUNT = 1;
/*     */   public static final int TERMINATIONMETHOD_USE_END_DATE = 2;
/*     */   
/*     */   public static String getStringTime(GregorianCalendar time) {
/*  26 */     String val = "";
/*     */     
/*  28 */     int hour = time.get(11);
/*  29 */     int min = time.get(12);
/*  30 */     int sec = time.get(13);
/*  31 */     if (hour < 10) {
/*  32 */       val = "0" + hour + ":";
/*     */     } else {
/*  34 */       val = String.valueOf(hour) + ":";
/*  35 */     }  if (min < 10) {
/*  36 */       val = String.valueOf(val) + "0" + min + ":";
/*     */     } else {
/*  38 */       val = String.valueOf(val) + min + ":";
/*  39 */     }  if (sec < 10) {
/*  40 */       val = String.valueOf(val) + "0" + sec;
/*     */     } else {
/*  42 */       val = String.valueOf(val) + sec;
/*  43 */     }  return val;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPossibleDayofMonth(int day, int month) {
/*  49 */     Calendar cal = Calendar.getInstance();
/*  50 */     cal.set(2, Math.max(0, month - 1));
/*  51 */     cal.getTime();
/*  52 */     int adjustedDay = cal.getActualMaximum(5);
/*  53 */     if (month == 2)
/*  54 */       adjustedDay = 28; 
/*  55 */     return Math.min(day, adjustedDay);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean controlForZeros(int recurrIndex, Number year, Number month, Number day) {
/*  60 */     switch (recurrIndex) {
/*     */       
/*     */       case 0:
/*     */       case 1:
/*  64 */         return year.equals(BigDecimal.ZERO);
/*     */       
/*     */       case 2:
/*  67 */         return (year.equals(BigDecimal.ZERO) && month.equals(BigDecimal.ZERO));
/*     */       
/*     */       case 3:
/*  70 */         return (year.equals(BigDecimal.ZERO) && month.equals(BigDecimal.ZERO) && day.equals(BigDecimal.ZERO));
/*     */       
/*     */       case 6:
/*  73 */         return month.equals(BigDecimal.ZERO);
/*     */       
/*     */       case 8:
/*  76 */         return (day.equals(BigDecimal.ZERO) && month.equals(BigDecimal.ZERO));
/*     */       
/*     */       case 10:
/*     */       case 11:
/*  80 */         return day.equals(BigDecimal.ZERO);
/*     */     } 
/*     */     
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean controlFirstExecutionTime(int recurrIndex, Number year, Number month, Number day) {
/*  88 */     boolean control = true;
/*     */     
/*  90 */     switch (recurrIndex) {
/*     */       
/*     */       case 0:
/*  93 */         return false;
/*     */       
/*     */       case 1:
/*  96 */         control &= between(year, Integer.valueOf(1), null);
/*  97 */         return control;
/*     */       
/*     */       case 2:
/* 100 */         control &= between(year, Integer.valueOf(1), null);
/* 101 */         control &= between(month, Integer.valueOf(1), Integer.valueOf(12));
/* 102 */         return control;
/*     */       
/*     */       case 3:
/* 105 */         control &= between(year, Integer.valueOf(1), null);
/* 106 */         control &= between(month, Integer.valueOf(1), Integer.valueOf(12));
/* 107 */         control &= between(day, Integer.valueOf(1), Integer.valueOf(31));
/* 108 */         return control;
/*     */       
/*     */       case 6:
/* 111 */         control &= between(month, Integer.valueOf(1), null);
/* 112 */         return control;
/*     */       
/*     */       case 8:
/* 115 */         control &= between(month, Integer.valueOf(1), null);
/* 116 */         control &= between(day, Integer.valueOf(1), Integer.valueOf(31));
/* 117 */         return control;
/*     */       
/*     */       case 10:
/* 120 */         control &= between(day, Integer.valueOf(1), null);
/* 121 */         return control;
/*     */       
/*     */       case 11:
/* 124 */         control &= between(day, Integer.valueOf(1), null);
/* 125 */         return control;
/*     */     } 
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean between(Number number, Number lower, Number upper) {
/* 132 */     if (number == null) {
/* 133 */       return false;
/*     */     }
/* 135 */     boolean control = true;
/*     */     
/* 137 */     int i = control & ((number.doubleValue() >= lower.doubleValue()) ? 1 : 0);
/*     */     
/* 139 */     if (upper != null) {
/* 140 */       i &= (number.doubleValue() <= upper.doubleValue()) ? 1 : 0;
/*     */     }
/* 142 */     return i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\als\AlertServiceHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */