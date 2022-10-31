/*     */ package com.lbs.als;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class JLbsRecurrenceRuleDef
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  18 */   private int m_TerminationMethod = 0;
/*  19 */   private int m_RecurrenceCount = 0;
/*  20 */   private Calendar m_EndTime = null;
/*  21 */   private int m_DelimiterIdx = 0;
/*  22 */   private int m_ConsYear = 0;
/*  23 */   private int m_ConsMonth = 0;
/*  24 */   private int m_ConsWeek = 0;
/*  25 */   private int m_ConsDay = 0;
/*  26 */   private int m_ConsWeekDay = 0;
/*  27 */   private Calendar m_ConsTime = null;
/*  28 */   private Calendar m_ConsEndingTime = null;
/*  29 */   private Calendar m_ConsBeginningTime = null;
/*     */   
/*  31 */   private int m_PatternIdx = 0;
/*     */   
/*     */   private String m_StringRep;
/*  34 */   private int m_State = 0;
/*     */   
/*     */   public static final int STATE_NEW = 0;
/*     */   
/*     */   public static final int STATE_UNMODIFIED = 1;
/*     */   
/*     */   public static final int STATE_MODIFIED = 2;
/*     */   
/*     */   public static final int STATE_DELETED = 3;
/*     */ 
/*     */   
/*     */   public int getTerminationMethod() {
/*  46 */     return this.m_TerminationMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTerminationMethod(int value) {
/*  54 */     this.m_TerminationMethod = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecurrenceCount() {
/*  62 */     return this.m_RecurrenceCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecurrenceCount(int value) {
/*  70 */     this.m_RecurrenceCount = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getEndTime() {
/*  78 */     return this.m_EndTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndTime(Calendar value) {
/*  86 */     this.m_EndTime = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelimiterIdx() {
/*  94 */     return this.m_DelimiterIdx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDelimiterIdx(int value) {
/* 102 */     this.m_DelimiterIdx = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConsYear() {
/* 107 */     return this.m_ConsYear;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsYear(int consYear) {
/* 112 */     this.m_ConsYear = consYear;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConsMonth() {
/* 117 */     return this.m_ConsMonth;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsMonth(int consMonth) {
/* 122 */     this.m_ConsMonth = consMonth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConsWeek() {
/* 127 */     return this.m_ConsWeek;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsWeek(int consWeek) {
/* 132 */     this.m_ConsWeek = consWeek;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConsDay() {
/* 137 */     return this.m_ConsDay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsDay(int consDay) {
/* 142 */     this.m_ConsDay = consDay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConsWeekDay() {
/* 150 */     return this.m_ConsWeekDay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsWeekDay(int consWeekDay) {
/* 155 */     this.m_ConsWeekDay = consWeekDay;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getConsTime() {
/* 160 */     return this.m_ConsTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsTime(Calendar consTime) {
/* 165 */     this.m_ConsTime = consTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPatternIdx() {
/* 173 */     return this.m_PatternIdx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPatternIdx(int value) {
/* 181 */     this.m_PatternIdx = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringRep() {
/* 189 */     return this.m_StringRep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStringRep(String value) {
/* 197 */     this.m_StringRep = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public int _getState() {
/* 202 */     return this.m_State;
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setState(int state) {
/* 207 */     this.m_State = state;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getConsEndingTime() {
/* 212 */     return this.m_ConsEndingTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsEndingTime(Calendar consEndingTime) {
/* 217 */     this.m_ConsEndingTime = consEndingTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getConsBeginningTime() {
/* 222 */     return this.m_ConsBeginningTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsBeginningTime(Calendar consBeginningTime) {
/* 227 */     this.m_ConsBeginningTime = consBeginningTime;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\als\JLbsRecurrenceRuleDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */