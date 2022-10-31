/*    */ package com.lbs.controls.datedit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsTimeFormatter
/*    */ {
/*    */   protected static String formatInt(int value, int length) {
/* 14 */     StringBuilder buffer = new StringBuilder();
/* 15 */     buffer.append(value);
/* 16 */     for (; buffer.length() < length; buffer.insert(0, '0'));
/* 17 */     return buffer.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String formatTime(JLbsTimeDuration time, int timeFormat) {
/* 22 */     return formatTime(time, ':', timeFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String formatTime(JLbsTimeDuration time, char separator, int timeFormat) {
/* 27 */     if (time == null) return null; 
/* 28 */     StringBuilder buffer = new StringBuilder();
/*    */     
/* 30 */     switch (timeFormat) {
/*    */       
/*    */       case 22:
/* 33 */         buffer.append(formatInt(time.getHour(), 2));
/* 34 */         buffer.append(separator);
/* 35 */         buffer.append(formatInt(time.getMinute(), 2));
/*    */         break;
/*    */       case 0:
/*    */       case 21:
/*    */       case 23:
/* 40 */         buffer.append(formatInt(time.getHour(), 2));
/* 41 */         buffer.append(separator);
/* 42 */         buffer.append(formatInt(time.getMinute(), 2));
/* 43 */         buffer.append(separator);
/* 44 */         buffer.append(formatInt(time.getSecond(), 2));
/*    */         break;
/*    */       case 24:
/* 47 */         buffer.append(formatInt(time.getHour(), 2));
/* 48 */         buffer.append(separator);
/* 49 */         buffer.append(formatInt(time.getMinute(), 2));
/* 50 */         buffer.append(separator);
/* 51 */         buffer.append(formatInt(time.getSecond(), 2));
/* 52 */         buffer.append(separator);
/* 53 */         buffer.append(formatInt(time.getMilisecond(), 2));
/*    */         break;
/*    */       case 25:
/* 56 */         buffer.append(formatInt(time.getHour() % 12, 2));
/* 57 */         buffer.append(separator);
/* 58 */         buffer.append(formatInt(time.getMinute(), 2));
/*    */         break;
/*    */       case 26:
/* 61 */         buffer.append(formatInt(time.getHour() % 12, 2));
/* 62 */         buffer.append(separator);
/* 63 */         buffer.append(formatInt(time.getMinute(), 2));
/* 64 */         buffer.append(separator);
/* 65 */         buffer.append(formatInt(time.getSecond(), 2));
/*    */         break;
/*    */     } 
/* 68 */     return buffer.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String formatMiliseconds(long miliseconds) {
/* 73 */     return formatMiliseconds(miliseconds, ':');
/*    */   }
/*    */ 
/*    */   
/*    */   public static String formatMiliseconds(long miliseconds, char separator) {
/* 78 */     int ms = (int)(miliseconds % 1000L);
/* 79 */     int seconds = (int)(miliseconds / 1000L);
/* 80 */     int minutes = (int)(miliseconds / 60000L);
/* 81 */     int hours = (int)(miliseconds / 3600000L);
/* 82 */     StringBuilder buffer = new StringBuilder();
/* 83 */     buffer.append(formatInt(hours, 2));
/* 84 */     buffer.append(separator);
/* 85 */     buffer.append(formatInt(minutes % 60, 2));
/* 86 */     buffer.append(separator);
/* 87 */     buffer.append(formatInt(seconds % 60, 2));
/* 88 */     buffer.append(separator);
/* 89 */     buffer.append(formatInt(ms, 2));
/* 90 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsTimeFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */