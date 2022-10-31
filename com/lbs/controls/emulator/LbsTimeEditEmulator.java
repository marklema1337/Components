/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.controls.datedit.ILbsInternalTimeEdit;
/*     */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*     */ import com.lbs.controls.datedit.LbsTimeEditImplementor;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.Calendar;
/*     */ import javax.swing.JFormattedTextField;
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
/*     */ public class LbsTimeEditEmulator
/*     */   extends LbsMaskedEditEmulator
/*     */   implements ILbsInternalTimeEdit
/*     */ {
/*     */   private LbsTimeEditImplementor m_Implementor;
/*     */   
/*     */   public LbsTimeEditEmulator() {
/*  27 */     this(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsTimeEditEmulator(int iTimeFormat) {
/*  37 */     this(iTimeFormat, true, null);
/*     */   }
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
/*     */   public LbsTimeEditEmulator(int iTimeFormat, boolean bShowSeconds, String szPostfix) {
/*  50 */     this(iTimeFormat, bShowSeconds, false, szPostfix);
/*     */   }
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
/*     */   public LbsTimeEditEmulator(int iTimeFormat, boolean bShowSeconds, boolean bShowMilis, String szPostfix) {
/*  63 */     this.m_Implementor = new LbsTimeEditImplementor(this, iTimeFormat, bShowSeconds, bShowMilis, szPostfix);
/*  64 */     this.m_Implementor.init(iTimeFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeFormatter(JFormattedTextField.AbstractFormatter formatter) {
/*  69 */     this.m_Formatter = formatter;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/*  74 */     return this.m_Implementor.getMaskPlaceHolder();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/*  79 */     return this.m_Implementor.getPostfix();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowSeconds() {
/*  84 */     return this.m_Implementor.getShowSeconds();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration getTime() {
/*  89 */     return this.m_Implementor.getTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getTimeSeparator() {
/*  94 */     return this.m_Implementor.getTimeSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/*  99 */     this.m_Implementor.setMaskPlaceHolder(chPlaceHolder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 104 */     this.m_Implementor.setPostfix(szPostfix);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowSeconds(boolean b) {
/* 109 */     this.m_Implementor.setShowSeconds(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTime(JLbsTimeDuration time) {
/* 114 */     this.m_Implementor.setTime(time);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTime(Calendar calendar) {
/* 119 */     this.m_Implementor.setTime(calendar);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTimeFormat(int timeFormat) {
/* 124 */     return this.m_Implementor.setTimeFormat(timeFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeFormatEx(int dispFormat, boolean duration) {
/* 129 */     this.m_Implementor.setTimeFormatEx(dispFormat, duration);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTimeSeparator(char chSeparator) {
/* 134 */     return this.m_Implementor.setTimeSeparator(chSeparator);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processKeyEvent(KeyEvent e) {
/* 140 */     this.m_Implementor.processKeyEvent(e);
/* 141 */     super.processKeyEvent(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar value) {
/* 146 */     this.m_Implementor.setCalendarValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 151 */     return this.m_Implementor.getCalendarValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 156 */     return this.m_Implementor.isValueModified();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsTimeEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */