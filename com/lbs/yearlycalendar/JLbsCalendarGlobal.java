/*    */ package com.lbs.yearlycalendar;
/*    */ 
/*    */ import com.lbs.util.JLbsDateUtil;
/*    */ import java.util.Calendar;
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsCalendarGlobal
/*    */ {
/*    */   public static Calendar incrementDays(int iOffset, Calendar aDate) {
/* 20 */     if (iOffset == 0 || JLbsDateUtil.isNullDate(aDate))
/* 21 */       return null; 
/* 22 */     int aDay = aDate.get(5);
/* 23 */     int aMonth = aDate.get(2);
/* 24 */     int aYear = aDate.get(1);
/*    */     
/* 26 */     int iNewDay = aDay + iOffset;
/* 27 */     if (iNewDay <= 0) {
/*    */       
/* 29 */       while (iNewDay <= 0) {
/*    */         
/* 31 */         if (aMonth < 0) {
/*    */           
/* 33 */           aYear--;
/* 34 */           aMonth = 11;
/*    */         } else {
/*    */           
/* 37 */           aMonth--;
/* 38 */         }  int iMonthDays = JLbsDateUtil.getNumDays(aMonth, aYear);
/* 39 */         iNewDay += iMonthDays;
/*    */       } 
/* 41 */       aDay = iNewDay;
/*    */     }
/*    */     else {
/*    */       
/* 45 */       int iCurrMonthDays = JLbsDateUtil.getNumDays(aMonth, aYear);
/* 46 */       while (iNewDay > iCurrMonthDays) {
/*    */         
/* 48 */         iNewDay -= iCurrMonthDays;
/* 49 */         if (aMonth >= 11) {
/*    */           
/* 51 */           aYear++;
/* 52 */           aMonth = 0;
/*    */         } else {
/*    */           
/* 55 */           aMonth++;
/* 56 */         }  iCurrMonthDays = JLbsDateUtil.getNumDays(aMonth, aYear);
/*    */       } 
/* 58 */       aDay = iNewDay;
/*    */     } 
/* 60 */     return new GregorianCalendar(aYear, aMonth, aDay);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean equalDates(Calendar date1, Calendar date2) {
/* 65 */     if (JLbsDateUtil.isNullDate(date1) || JLbsDateUtil.isNullDate(date2))
/* 66 */       return false; 
/* 67 */     return (getYeear(date1) == getYeear(date2) && getMonth(date1) == getMonth(date2) && getDay(date1) == getDay(date2));
/*    */   }
/*    */ 
/*    */   
/*    */   public static int dateCompare(Calendar date1, Calendar date2) {
/* 72 */     if (JLbsDateUtil.isNullDate(date1) || JLbsDateUtil.isNullDate(date2))
/* 73 */       return -2; 
/* 74 */     return JLbsDateUtil.dateCompare(date1, date2);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getDay(Calendar date) {
/* 79 */     if (JLbsDateUtil.isNullDate(date))
/* 80 */       return -1; 
/* 81 */     return date.get(5);
/*    */   }
/*    */   
/*    */   public static int getMonth(Calendar date) {
/* 85 */     if (JLbsDateUtil.isNullDate(date))
/* 86 */       return -1; 
/* 87 */     return date.get(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getYeear(Calendar date) {
/* 92 */     if (JLbsDateUtil.isNullDate(date))
/* 93 */       return -1; 
/* 94 */     return date.get(1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsCalendarGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */