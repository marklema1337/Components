/*    */ package com.lbs.yearlycalendar;
/*    */ 
/*    */ import java.awt.Rectangle;
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
/*    */ public class JLbsCalendarCellRect
/*    */ {
/*    */   private Calendar m_Date;
/*    */   private Rectangle m_Rect;
/*    */   private boolean m_Selected = false;
/*    */   private boolean m_HasDate = false;
/* 20 */   private JLbsMonthCalendarControl m_Calendar = null;
/*    */ 
/*    */   
/*    */   public JLbsCalendarCellRect() {}
/*    */ 
/*    */   
/*    */   public JLbsCalendarCellRect(JLbsMonthCalendarControl aCalendar) {
/* 27 */     this();
/* 28 */     this.m_Calendar = aCalendar;
/*    */   }
/*    */   
/*    */   public JLbsCalendarCellRect(Rectangle aRect, JLbsMonthCalendarControl aCalendar) {
/* 32 */     this(aCalendar);
/* 33 */     this.m_Rect = aRect;
/*    */   }
/*    */   
/*    */   public JLbsCalendarCellRect(Calendar aDate, Rectangle aRect, JLbsMonthCalendarControl aCalendar) {
/* 37 */     this(aRect, aCalendar);
/* 38 */     this.m_Date = aDate;
/*    */   }
/*    */   
/*    */   public JLbsCalenderSpecialDate getSpecialDate() {
/* 42 */     if (this.m_Calendar != null) {
/*    */       
/* 44 */       int AnIndex = this.m_Calendar.getSpecialDates().getIndex(this.m_Date);
/* 45 */       if (AnIndex != -1)
/* 46 */         return this.m_Calendar.getSpecialDates().getSpecialDate(AnIndex); 
/*    */     } 
/* 48 */     return null;
/*    */   }
/*    */   
/*    */   public Calendar getDate() {
/* 52 */     return this.m_Date;
/*    */   }
/*    */   
/*    */   public void setDate(Calendar value) {
/* 56 */     if (this.m_Date != value) {
/*    */       
/* 58 */       this.m_Date = value;
/* 59 */       this.m_HasDate = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Rectangle getRect() {
/* 64 */     return this.m_Rect;
/*    */   }
/*    */   
/*    */   public void setRect(Rectangle value) {
/* 68 */     if (this.m_Rect != value)
/* 69 */       this.m_Rect = value; 
/*    */   }
/*    */   
/*    */   public boolean getSelected() {
/* 73 */     return this.m_Selected;
/*    */   }
/*    */   
/*    */   public void setSelected(boolean value) {
/* 77 */     this.m_Calendar.setLastDate(this.m_Date);
/* 78 */     if (this.m_Selected != value) {
/*    */       
/* 80 */       this.m_Selected = value;
/* 81 */       this.m_Calendar.repaint();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void repaintCellRect() {
/* 86 */     if (this.m_Calendar != null)
/* 87 */       this.m_Calendar.repaint(this.m_Rect.x - 4, this.m_Rect.y - 4, this.m_Rect.width + 8, this.m_Rect.height + 8); 
/*    */   }
/*    */   
/*    */   public boolean getHasDate() {
/* 91 */     return this.m_HasDate;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsMonthCalendarControl getCalendarControl() {
/* 96 */     return this.m_Calendar;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsCalendarCellRect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */