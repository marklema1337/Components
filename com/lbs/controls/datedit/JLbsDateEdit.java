/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.ghasemkiani.util.DateFields;
/*     */ import com.lbs.controls.ILbsGridComponent;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.recording.interfaces.ILbsDateEditRecordingEvents;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.text.DefaultFormatterFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsDateEdit
/*     */   extends JLbsMaskedEdit
/*     */   implements ILbsDateEditRecordingEvents, ILbsInternalDateEdit
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private LbsDateEditImplementor m_Implementor;
/*     */   private boolean isNonModalUse = true;
/*     */   private boolean isNonModalDetermined = false;
/*     */   
/*     */   public JLbsDateEdit() {
/*  54 */     this(LbsDateEditImplementor.getEmptyFormat());
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
/*     */   public JLbsDateEdit(int iDateFmtSpec) {
/*  66 */     this(iDateFmtSpec, iDateFmtSpec);
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
/*     */   
/*     */   public JLbsDateEdit(int iDateFmtSpec, int iDisplayFmtSpec) {
/*  80 */     this(LbsDateEditImplementor.getFormat(JLbsDateFormatter.FMT_EditFormatStrings, iDateFmtSpec), LbsDateEditImplementor.getFormat(JLbsDateFormatter.FMT_FormatStrings, iDisplayFmtSpec));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDateEdit(String szDateFormat) {
/*  90 */     this(szDateFormat, (String)null);
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
/*     */   
/*     */   public JLbsDateEdit(String szDateFormat, String szDisplayFormat) {
/* 104 */     this.m_Implementor = new LbsDateEditImplementor(this, szDateFormat, szDisplayFormat);
/* 105 */     this.m_Implementor.init(szDateFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFormatter(JFormattedTextField.AbstractFormatter formatter) {
/* 110 */     setFormatterFactory(new DefaultFormatterFactory(formatter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDateFormat() {
/* 119 */     return this.m_Implementor.getDateFormat();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(String szFormat) {
/* 136 */     return this.m_Implementor.setDateFormat(szFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDateFormat(int iDateFmtSpec) {
/* 141 */     return this.m_Implementor.setDateFormat(iDateFmtSpec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayFormat() {
/* 150 */     return this.m_Implementor.getDisplayFormat();
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
/*     */   public void setDisplayFormat(String szFormat) {
/* 163 */     this.m_Implementor.setDisplayFormat(szFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisplayFormat(int iFmtSpec) {
/* 173 */     this.m_Implementor.setDisplayFormat(iFmtSpec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getDateSeparator() {
/* 182 */     return this.m_Implementor.getDateSeparator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDateSeparator(char chSeparator) {
/* 193 */     return this.m_Implementor.setDateSeparator(chSeparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/* 202 */     return this.m_Implementor.getMaskPlaceHolder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/* 211 */     this.m_Implementor.setMaskPlaceHolder(chPlaceHolder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getDate() {
/* 221 */     return this.m_Implementor.getDate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void commitEdit() throws ParseException {
/* 227 */     String szText = getText();
/* 228 */     if (this.m_Implementor.allTokensEmpty(szText)) {
/* 229 */       setValue(null);
/*     */     } else {
/*     */       
/* 232 */       super.commitEdit();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Date parseDate(String szText, String szDateFormat) {
/* 238 */     return this.m_Implementor.parseDate(szText, szDateFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DateFields parsePerDate(String szText, String szDateFormat) {
/*     */     try {
/* 245 */       DateFormat format = this.m_Implementor.getDateFormatObject();
/* 246 */       format.setLenient(true);
/*     */       
/* 248 */       DateFields dateFields = parsePersianDate(szText, szDateFormat);
/*     */       
/* 250 */       int iYear = dateFields.getYear();
/* 251 */       if (iYear < 100 && szDateFormat != null) {
/*     */         
/* 253 */         String[] tokens = szDateFormat.split("/");
/* 254 */         for (int i = 0; i < tokens.length; i++) {
/*     */           
/* 256 */           if (tokens[i].compareToIgnoreCase("yy") == 0 || tokens[i].compareToIgnoreCase("yyy") == 0) {
/*     */             
/* 258 */             dateFields.setYear(2000);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 263 */       return dateFields;
/*     */     }
/* 265 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 268 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private DateFields parsePersianDate(String szText, String szDateFormat) {
/* 273 */     DateFields dateFields = new DateFields();
/* 274 */     stripDateNumbers(szText);
/*     */     
/* 276 */     String[] tokensFormat = szDateFormat.split(Character.toString(this.m_Implementor.getDateSeparator()));
/* 277 */     String[] tokens = szText.split(Character.toString(this.m_Implementor.getDateSeparator()));
/* 278 */     for (int i = 0; i < tokensFormat.length; i++) {
/*     */       
/* 280 */       if (tokensFormat[i].compareToIgnoreCase("dd") == 0)
/* 281 */         dateFields.setDay(Integer.parseInt(tokens[i])); 
/* 282 */       if (tokensFormat[i].compareToIgnoreCase("MM") == 0)
/* 283 */         dateFields.setMonth(Integer.parseInt(tokens[i]) - 1); 
/* 284 */       if (tokensFormat[i].compareToIgnoreCase("yyyy") == 0 || tokensFormat[i].compareToIgnoreCase("yy") == 0)
/* 285 */         dateFields.setYear(Integer.parseInt(tokens[i])); 
/*     */     } 
/* 287 */     return dateFields;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDateFromPopup(Date date) {
/* 292 */     this.m_Implementor.setDateFromPopup(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/* 302 */     return this.m_Implementor.getCalendar();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDate(Date date) {
/*     */     try {
/* 314 */       this.m_Implementor.setDate(date);
/* 315 */       recordSetDate(date);
/*     */     }
/* 317 */     catch (Exception e) {
/*     */       
/* 319 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalText(String text) {
/* 326 */     this.m_OriginalText = text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDate(Calendar date) {
/* 336 */     this.m_Implementor.setDate(date);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDateString(Date date) {
/* 341 */     return this.m_Implementor.getDateString(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 349 */     return this.m_Implementor.getCultureInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo culture) {
/* 357 */     this.m_Implementor.setCultureInfo(culture);
/*     */   }
/*     */ 
/*     */   
/*     */   public String stripDateNumbers(String text) {
/* 362 */     return this.m_Implementor.stripDateNumbers(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean superVerifyContent() {
/* 367 */     Date d = parseDate(getText(), getDateFormat());
/* 368 */     setDate(d);
/* 369 */     return super.verifyContent();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean verifyContent() {
/* 375 */     return this.m_Implementor.verifyContent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 382 */     if (isFocusOwner() || this.m_Implementor.getDisplayFormat() == null) {
/* 383 */       super.paintComponent(g);
/*     */     } else {
/*     */       
/* 386 */       g.setColor(getBackground());
/* 387 */       g.setFont(getFont());
/* 388 */       Rectangle drawRect = JLbsControlHelper.getClientRect((JComponent)this);
/* 389 */       g.fillRect(drawRect.x, drawRect.y, drawRect.width, drawRect.height);
/*     */       
/* 391 */       String szFullText = this.m_Implementor.getFormattedText();
/* 392 */       g.setColor(isEnabled() ? 
/* 393 */           getForeground() : 
/* 394 */           getDisabledTextColor());
/* 395 */       JLbsControlHelper.drawStringVCentered((JComponent)this, g, drawRect, szFullText, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String parseDate(Date date) {
/* 401 */     return this.m_Implementor.parseDate(date);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 407 */     String result = super.getText();
/* 408 */     return this.m_Implementor.getText(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetDate(Date date) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void doSetDate(Date date, int actionID) {
/* 418 */     final Date mDate = date;
/* 419 */     final int fActionID = actionID;
/* 420 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 424 */             JLbsDateEdit.this.setDate(mDate);
/* 425 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultVisibleChars() {
/* 451 */     return this.m_Implementor.getDefaultVisibleChars();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageDialogImpl(Object messageDialogImpl) {
/* 456 */     this.m_Implementor.setMessageDialogImpl(messageDialogImpl);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/* 462 */     return this.m_Implementor.isResetTime();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean resetTime) {
/* 468 */     this.m_Implementor.setResetTime(resetTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 473 */     return this.m_Implementor.getCalendarValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar value) {
/* 478 */     this.m_Implementor.setCalendarValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 483 */     return this.m_Implementor.isValueModified();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/* 488 */     this.m_Implementor.resetModified(newValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoCompanyValidation(boolean value) {
/* 493 */     this.m_Implementor.setNoCompanyValidation(value);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/* 498 */     if (!this.isNonModalDetermined) {
/*     */       
/* 500 */       Container parent = getParent();
/* 501 */       if (parent != null) {
/*     */         
/* 503 */         Container parentOfParent = parent.getParent();
/* 504 */         if (parentOfParent != null && parentOfParent instanceof ILbsGridComponent) {
/*     */           
/* 506 */           this.isNonModalUse = ((ILbsGridComponent)parentOfParent).getNonModal();
/* 507 */           this.isNonModalDetermined = true;
/*     */         } else {
/*     */           
/* 510 */           this.isNonModalDetermined = true;
/*     */         } 
/*     */       } else {
/* 513 */         this.isNonModalDetermined = true;
/*     */       } 
/* 515 */     }  processFocusEvent(e, this.isNonModalUse);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNonModalUse() {
/* 520 */     return this.isNonModalUse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNonModalUse(boolean isNonModalUse) {
/* 525 */     this.isNonModalUse = isNonModalUse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseDay() {
/* 530 */     Date date = this.m_Implementor.getDate();
/* 531 */     date.setDate(1 + date.getDate());
/*     */     
/*     */     try {
/* 534 */       this.m_Implementor.setDate(date);
/*     */     }
/* 536 */     catch (Exception e) {
/*     */       
/* 538 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decreaseDay() {
/* 544 */     Date date = this.m_Implementor.getDate();
/* 545 */     date.setDate(date.getDate() - 1);
/*     */     
/*     */     try {
/* 548 */       this.m_Implementor.setDate(date);
/*     */     }
/* 550 */     catch (Exception e) {
/*     */       
/* 552 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsDateEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */