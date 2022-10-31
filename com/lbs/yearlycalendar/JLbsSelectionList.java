/*    */ package com.lbs.yearlycalendar;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Calendar;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsSelectionList
/*    */ {
/* 21 */   protected ArrayList m_List = new ArrayList();
/*    */   
/*    */   public JLbsSelectionList() {}
/*    */   
/*    */   public JLbsSelectionList(JLbsMonthCalendarControl ACalendar) {
/* 26 */     this();
/* 27 */     if (ACalendar != null)
/* 28 */       for (int AnIndex = 0; AnIndex < ACalendar.getCellRects().size(); AnIndex++) {
/* 29 */         if (ACalendar.getCellRects().getCellRect(AnIndex).getSelected())
/* 30 */           add(ACalendar.getCellRects().getCellRect(AnIndex).getDate()); 
/*    */       }  
/*    */   }
/*    */   public JLbsSelectionList(JLbsYearCalendarControl AYearlyCalendar) {
/* 34 */     this();
/* 35 */     if (AYearlyCalendar != null)
/*    */     {
/* 37 */       for (int anIdx = 0; anIdx < (AYearlyCalendar.getCalendars()).length; anIdx++) {
/*    */         
/* 39 */         JLbsMonthCalendarControl ACalendar = AYearlyCalendar.getCalendars()[anIdx];
/* 40 */         for (int AnIndex = 0; AnIndex < ACalendar.getCellRects().size(); AnIndex++) {
/* 41 */           if (ACalendar.getCellRects().getCellRect(AnIndex).getSelected())
/* 42 */             add(ACalendar.getCellRects().getCellRect(AnIndex).getDate()); 
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public int size() {
/* 49 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public Calendar get(int index) {
/* 54 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(Calendar aDate) {
/* 59 */     this.m_List.add(aDate);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsSelectionList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */