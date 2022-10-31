/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.controls.datedit.ILbsInternalDateEdit;
/*     */ import com.lbs.controls.datedit.JLbsDateFormatter;
/*     */ import com.lbs.controls.datedit.LbsDateEditImplementor;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
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
/*     */ public class LbsDateEditEmulator
/*     */   extends LbsMaskedEditEmulator
/*     */   implements ILbsInternalDateEdit
/*     */ {
/*     */   private LbsDateEditImplementor m_Implementor;
/*     */   
/*     */   public LbsDateEditEmulator() {
/*  28 */     this(LbsDateEditImplementor.getEmptyFormat());
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsDateEditEmulator(int iDateFmtSpec) {
/*  33 */     this(iDateFmtSpec, iDateFmtSpec);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsDateEditEmulator(int iDateFmtSpec, int iDisplayFmtSpec) {
/*  39 */     this(LbsDateEditImplementor.getFormat(JLbsDateFormatter.FMT_EditFormatStrings, iDateFmtSpec), LbsDateEditImplementor.getFormat(JLbsDateFormatter.FMT_FormatStrings, iDisplayFmtSpec));
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsDateEditEmulator(String szDateFormat) {
/*  44 */     this(szDateFormat, (String)null);
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
/*     */   public LbsDateEditEmulator(String szDateFormat, String szDisplayFormat) {
/*  57 */     this.m_Implementor = new LbsDateEditImplementor(this, szDateFormat, szDisplayFormat);
/*  58 */     this.m_Implementor.init(szDateFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean superVerifyContent() {
/*  63 */     return super.verifyContent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFormatter(JFormattedTextField.AbstractFormatter formatter) {
/*  68 */     this.m_Formatter = formatter;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/*  73 */     return this.m_Implementor.getCalendar();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/*  78 */     return this.m_Implementor.getCultureInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/*  83 */     return this.m_Implementor.getDate();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateFormat() {
/*  88 */     return this.m_Implementor.getDateFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getDateSeparator() {
/*  93 */     return this.m_Implementor.getDateSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateString(Date date) {
/*  98 */     return this.m_Implementor.getDateString(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultVisibleChars() {
/* 103 */     return this.m_Implementor.getDefaultVisibleChars();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayFormat() {
/* 108 */     return this.m_Implementor.getDisplayFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/* 113 */     return this.m_Implementor.getMaskPlaceHolder();
/*     */   }
/*     */ 
/*     */   
/*     */   public String parseDate(Date date) {
/* 118 */     return this.m_Implementor.parseDate(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date parseDate(String szText, String szDateFormat) {
/* 123 */     return this.m_Implementor.parseDate(szText, szDateFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo culture) {
/* 128 */     this.m_Implementor.setCultureInfo(culture);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar date) {
/* 133 */     this.m_Implementor.setDate(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Date date) {
/*     */     try {
/* 139 */       this.m_Implementor.setDate(date);
/*     */     }
/* 141 */     catch (Exception e) {
/*     */       
/* 143 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(String szFormat) {
/* 149 */     return this.m_Implementor.setDateFormat(szFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(int dateFmtSpec) {
/* 154 */     return this.m_Implementor.setDateFormat(dateFmtSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateSeparator(char chSeparator) {
/* 159 */     return this.m_Implementor.setDateSeparator(chSeparator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayFormat(String szFormat) {
/* 164 */     this.m_Implementor.setDisplayFormat(szFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayFormat(int fmtSpec) {
/* 169 */     this.m_Implementor.setDisplayFormat(fmtSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/* 174 */     this.m_Implementor.setMaskPlaceHolder(chPlaceHolder);
/*     */   }
/*     */ 
/*     */   
/*     */   public String stripDateNumbers(String text) {
/* 179 */     return this.m_Implementor.stripDateNumbers(text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 185 */     return this.m_Implementor.verifyContent();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 191 */     String result = super.getText();
/* 192 */     return this.m_Implementor.getText(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOriginalText(String text) {
/* 197 */     this.m_OriginalText = text;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageDialogImpl(Object messageDialogImpl) {
/* 202 */     this.m_Implementor.setMessageDialogImpl(messageDialogImpl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar value) {
/* 207 */     this.m_Implementor.setCalendarValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 212 */     return this.m_Implementor.getCalendarValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 217 */     return this.m_Implementor.isValueModified();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/* 222 */     this.m_Implementor.resetModified(newValue);
/*     */   }
/*     */   
/*     */   public void increaseDay() {}
/*     */   
/*     */   public void decreaseDay() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsDateEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */