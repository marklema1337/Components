/*     */ package com.lbs.yearlycalendar;
/*     */ 
/*     */ import com.lbs.util.JLbsDateUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
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
/*     */ public class JLbsCalendarCellRects
/*     */ {
/*  21 */   private JLbsMonthCalendarControl m_Calendar = null;
/*  22 */   private ArrayList m_List = null;
/*     */ 
/*     */   
/*     */   public JLbsCalendarCellRects() {
/*  26 */     this.m_List = new ArrayList();
/*     */   }
/*     */   
/*     */   public JLbsCalendarCellRects(JLbsMonthCalendarControl ACalendar) {
/*  30 */     this();
/*  31 */     this.m_Calendar = ACalendar;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCalendarCellRect add() {
/*  36 */     JLbsCalendarCellRect result = new JLbsCalendarCellRect();
/*  37 */     this.m_List.add(result);
/*  38 */     return result;
/*     */   }
/*     */   
/*     */   public JLbsCalendarCellRect add(Rectangle ARect) {
/*  42 */     if (indexOf(ARect) == -1) {
/*     */       
/*  44 */       JLbsCalendarCellRect result = new JLbsCalendarCellRect(ARect, this.m_Calendar);
/*  45 */       this.m_List.add(result);
/*  46 */       return result;
/*     */     } 
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public JLbsCalendarCellRect add(Calendar ADate, Rectangle ARect) {
/*  52 */     if (indexOf(ADate) == -1) {
/*     */       
/*  54 */       JLbsCalendarCellRect result = new JLbsCalendarCellRect(ADate, ARect, this.m_Calendar);
/*  55 */       this.m_List.add(result);
/*  56 */       return result;
/*     */     } 
/*  58 */     return null;
/*     */   }
/*     */   
/*     */   public int indexOf(Calendar ADate) {
/*  62 */     Calendar cDate = null;
/*  63 */     for (int anIndex = 0; anIndex < this.m_List.size(); anIndex++) {
/*     */       
/*  65 */       cDate = ((JLbsCalendarCellRect)this.m_List.get(anIndex)).getDate();
/*  66 */       if (cDate != null && JLbsDateUtil.dateCompare(cDate, ADate) == 0)
/*  67 */         return anIndex; 
/*     */     } 
/*  69 */     return -1;
/*     */   }
/*     */   
/*     */   public int indexOf(Rectangle ARect) {
/*  73 */     for (int anIndex = 0; anIndex < this.m_List.size(); anIndex++) {
/*  74 */       if (((JLbsCalendarCellRect)this.m_List.get(anIndex)).getRect() == ARect)
/*  75 */         return anIndex; 
/*  76 */     }  return -1;
/*     */   }
/*     */   
/*     */   public int indexOf(Point APoint) {
/*  80 */     for (int anIndex = 0; anIndex < this.m_List.size(); anIndex++) {
/*  81 */       if (((JLbsCalendarCellRect)this.m_List.get(anIndex)).getRect().contains(APoint))
/*  82 */         return anIndex; 
/*  83 */     }  return -1;
/*     */   }
/*     */   
/*     */   public int indexOf(int x, int y) {
/*  87 */     return indexOf(new Point(x, y));
/*     */   }
/*     */   
/*     */   public void remove(Calendar ADate) {
/*  91 */     int AnIndex = indexOf(ADate);
/*  92 */     if (AnIndex != -1)
/*  93 */       this.m_List.remove(AnIndex); 
/*     */   }
/*     */   
/*     */   public void clearSelection() {
/*  97 */     if (this.m_Calendar != null)
/*     */     {
/*  99 */       for (int anIndex = 0; anIndex < this.m_List.size(); anIndex++)
/* 100 */         ((JLbsCalendarCellRect)this.m_List.get(anIndex)).setSelected(false); 
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsCalendarCellRect getCellRect(int anIndex) {
/* 105 */     if (anIndex >= 0 && anIndex < this.m_List.size())
/* 106 */       return this.m_List.get(anIndex); 
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCalendarCellRect getCellRect(Calendar aDate) {
/* 113 */     int anIndex = indexOf(aDate);
/* 114 */     if (anIndex >= 0 && anIndex < this.m_List.size())
/* 115 */       return this.m_List.get(anIndex); 
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMonthCalendarControl getCalendar() {
/* 121 */     return this.m_Calendar;
/*     */   }
/*     */   
/*     */   public void setCalendar(JLbsMonthCalendarControl aCalendar) {
/* 125 */     this.m_Calendar = aCalendar;
/*     */   }
/*     */   
/*     */   public int size() {
/* 129 */     return this.m_List.size();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 133 */     this.m_List.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsCalendarCellRects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */