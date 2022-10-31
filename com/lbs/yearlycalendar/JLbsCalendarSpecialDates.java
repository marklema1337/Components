/*     */ package com.lbs.yearlycalendar;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ public class JLbsCalendarSpecialDates
/*     */ {
/*     */   private ArrayList m_List;
/*  19 */   private JLbsMonthCalendarControl m_Calendar = null;
/*  20 */   private int m_Year = (new GregorianCalendar()).get(1);
/*     */ 
/*     */   
/*     */   public JLbsCalendarSpecialDates() {
/*  24 */     this.m_List = new ArrayList();
/*     */   }
/*     */   
/*     */   public JLbsCalendarSpecialDates(JLbsMonthCalendarControl aCalender) {
/*  28 */     this();
/*  29 */     this.m_Calendar = aCalender;
/*     */   }
/*     */   
/*     */   public int getYear() {
/*  33 */     return this.m_Year;
/*     */   }
/*     */   
/*     */   public int setYear(int aYear) {
/*  37 */     this.m_Year = aYear;
/*  38 */     return this.m_Year;
/*     */   }
/*     */   
/*     */   public JLbsCalenderSpecialDate addSpecialDate(JLbsCalenderSpecialDate aSpecialDate) {
/*  42 */     if (aSpecialDate != null)
/*  43 */       return addSpecialDate(aSpecialDate.getDate(), aSpecialDate.getDescription()); 
/*  44 */     return null;
/*     */   }
/*     */   
/*     */   public JLbsCalenderSpecialDate addSpecialDate(Calendar aDate, String aDescription) {
/*  48 */     if (aDate == null)
/*  49 */       return null; 
/*  50 */     if (aDate.get(1) == getYear() && getIndex(aDate) == -1) {
/*     */       
/*  52 */       JLbsCalenderSpecialDate result = new JLbsCalenderSpecialDate(aDate, aDescription);
/*  53 */       this.m_List.add(result);
/*  54 */       return result;
/*     */     } 
/*  56 */     return null;
/*     */   }
/*     */   
/*     */   public JLbsCalenderSpecialDate editSpecialDate(JLbsCalenderSpecialDate aSpecialDate) {
/*  60 */     if (aSpecialDate.getDate() == null)
/*  61 */       return null; 
/*  62 */     int anIndex = getIndex(aSpecialDate.getDate());
/*  63 */     if (anIndex != -1) {
/*     */       
/*  65 */       JLbsCalenderSpecialDate special = this.m_List.get(anIndex);
/*  66 */       special.setDescription(aSpecialDate.getDescription());
/*  67 */       special.setDate(aSpecialDate.getDate());
/*  68 */       return special;
/*     */     } 
/*  70 */     return null;
/*     */   }
/*     */   
/*     */   public JLbsCalenderSpecialDate editSpecialDate(Calendar aDate, String aDescription) {
/*  74 */     if (aDate == null)
/*  75 */       return null; 
/*  76 */     int anIndex = getIndex(aDate);
/*  77 */     if (anIndex != -1) {
/*     */       
/*  79 */       JLbsCalenderSpecialDate special = this.m_List.get(anIndex);
/*  80 */       special.setDescription(aDescription);
/*  81 */       special.setDate(aDate);
/*  82 */       return special;
/*     */     } 
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public JLbsCalenderSpecialDate editOrAddSpecialDate(Calendar aDate, String aDescription) {
/*  88 */     JLbsCalenderSpecialDate special = editSpecialDate(aDate, aDescription);
/*  89 */     if (special == null)
/*  90 */       special = addSpecialDate(aDate, aDescription); 
/*  91 */     return special;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex(Calendar aDate) {
/*  96 */     if (aDate == null)
/*  97 */       return -1; 
/*  98 */     Calendar tmpDate = null;
/*  99 */     for (int idx = 0; idx < this.m_List.size(); idx++) {
/*     */       
/* 101 */       tmpDate = ((JLbsCalenderSpecialDate)this.m_List.get(idx)).getDate();
/* 102 */       if (JLbsCalendarGlobal.equalDates(tmpDate, aDate))
/* 103 */         return idx; 
/*     */     } 
/* 105 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeSpecialDate(Calendar aDate) {
/* 110 */     boolean result = false;
/* 111 */     if (aDate != null)
/* 112 */       result = this.m_List.remove(aDate); 
/* 113 */     return result;
/*     */   }
/*     */   
/*     */   public JLbsCalenderSpecialDate getSpecialDate(int anIndex) {
/* 117 */     if (this.m_List.size() > anIndex)
/* 118 */       return this.m_List.get(anIndex); 
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCalenderSpecialDate getSpecialDate(Calendar aDate) {
/* 124 */     if (aDate == null)
/* 125 */       return null; 
/* 126 */     return getSpecialDate(getIndex(aDate));
/*     */   }
/*     */   
/*     */   public int size() {
/* 130 */     return this.m_List.size();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 134 */     this.m_List.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsCalendarSpecialDates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */