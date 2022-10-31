/*    */ package com.lbs.util;
/*    */ 
/*    */ public class CalendarCultureUtil
/*    */ {
/*  5 */   public static final String[] PERSIANTURKISHMONTHNAMES = new String[] { "Geçersiz", "Ferverdin", "Ordibeheşt", "Hordâd", "Tir", "Mordâd", 
/*  6 */       "Şehrivar", "Mehr", "Âbân", "Âzar", "Daî", "Behman", "İsfand" };
/*    */   
/*  8 */   public static final String[] PERSIANTURKISHDAYNAMES = new String[] { "Geçersiz", "Yekşenbe", "Duşenbe", "Seşenbe", "Çehārşenbe", 
/*  9 */       "Pencşenbe", "Cum'a", "Şenbe" };
/*    */   
/* 11 */   public static final String[] SHORTPERSIANTURKISHDAYNAMES = new String[] { "GÇS", "Yek", "Do", "Se", "Çhr", "Pnç", "Com", "Şnb" };
/*    */   
/* 13 */   public static final String[] PERSIANENGLISHMONTHNAMES = new String[] { "Invalid", "Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", 
/* 14 */       "Shahrivar", "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand" };
/*    */   
/* 16 */   public static final String[] PERSIANENGLISHDAYNAMES = new String[] { "Invalid", "Yekshanbeh", "Doshanbeh", "Ceshanbeh", "Tschaharshanbeh", 
/* 17 */       "Pandjshanbeh", "Djom’e", "Shanbeh" };
/*    */   
/* 19 */   public static final String[] SHORTPERSIANENGLISHDAYNAMES = new String[] { "INV", "Yek", "Do", "Ce", "Tsch", "Pndj", "Djom", "Shnb" };
/*    */ 
/*    */   
/*    */   public static String getPersianTurkishMonthFullName(int iMonth) {
/* 23 */     if (iMonth > 0 && iMonth <= 12)
/* 24 */       return PERSIANTURKISHMONTHNAMES[iMonth]; 
/* 25 */     return PERSIANTURKISHMONTHNAMES[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPersianTurkishDayFullName(int iDay) {
/* 30 */     if (iDay > 0 && iDay <= 12)
/* 31 */       return PERSIANTURKISHDAYNAMES[iDay]; 
/* 32 */     return PERSIANTURKISHDAYNAMES[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPersianTurkishDayShortName(int iDay) {
/* 37 */     if (iDay > 0 && iDay <= 12)
/* 38 */       return SHORTPERSIANTURKISHDAYNAMES[iDay]; 
/* 39 */     return SHORTPERSIANTURKISHDAYNAMES[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPersianEnglishMonthFullName(int iMonth) {
/* 44 */     if (iMonth > 0 && iMonth <= 12)
/* 45 */       return PERSIANENGLISHMONTHNAMES[iMonth]; 
/* 46 */     return PERSIANENGLISHMONTHNAMES[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPersianEnglishDayFullName(int iDay) {
/* 51 */     if (iDay > 0 && iDay <= 12)
/* 52 */       return PERSIANENGLISHDAYNAMES[iDay]; 
/* 53 */     return PERSIANENGLISHDAYNAMES[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPersianEnglıshDayShortName(int iDay) {
/* 58 */     if (iDay > 0 && iDay <= 12)
/* 59 */       return SHORTPERSIANENGLISHDAYNAMES[iDay]; 
/* 60 */     return SHORTPERSIANENGLISHDAYNAMES[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\CalendarCultureUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */