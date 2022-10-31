/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import com.lbs.util.CalendarCultureUtil;
/*    */ 
/*    */ public class JLbsCultureInfoENUS
/*    */   extends JLbsDefaultCultureInfo
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public String getMonthFullName(int iMonth, int calendarType) {
/* 11 */     switch (calendarType) {
/*    */       
/*    */       case 1:
/* 14 */         return CalendarCultureUtil.getPersianEnglishMonthFullName(iMonth);
/*    */       case 0:
/*    */       case 2:
/*    */       case 3:
/* 18 */         return getMonthFullName(iMonth);
/*    */     } 
/* 20 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoENUS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */