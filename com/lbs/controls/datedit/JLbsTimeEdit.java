/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.recording.interfaces.ILbsTimeEditRecordingEvents;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Calendar;
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
/*     */ public class JLbsTimeEdit
/*     */   extends JLbsMaskedEdit
/*     */   implements ILbsTimeEditRecordingEvents, ILbsInternalTimeEdit
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private LbsTimeEditImplementor m_Implementor;
/*     */   
/*     */   public JLbsTimeEdit() {
/*  40 */     this(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeEdit(int iTimeFormat) {
/*  50 */     this(iTimeFormat, true, (String)null);
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
/*     */   public JLbsTimeEdit(int iTimeFormat, boolean bShowSeconds, String szPostfix) {
/*  63 */     this(iTimeFormat, bShowSeconds, false, szPostfix);
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
/*     */   public JLbsTimeEdit(int iTimeFormat, boolean bShowSeconds, boolean bShowMilis, String szPostfix) {
/*  77 */     this.m_Implementor = new LbsTimeEditImplementor(this, iTimeFormat, bShowSeconds, bShowMilis, szPostfix);
/*  78 */     this.m_Implementor.init(iTimeFormat);
/*  79 */     addFocusListener(new FocusListener()
/*     */         {
/*     */           
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  84 */             JLbsTimeEdit.this.m_Implementor.focusGained(e);
/*     */           }
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {
/*  89 */             JLbsTimeEdit.this.m_Implementor.focusLost(e);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeFormatter(JFormattedTextField.AbstractFormatter formatter) {
/*  97 */     setFormatterFactory(new DefaultFormatterFactory(formatter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setTimeFormat(int iTimeFormat) {
/* 107 */     return this.m_Implementor.setTimeFormat(iTimeFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/* 116 */     return this.m_Implementor.getPostfix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 125 */     this.m_Implementor.setPostfix(szPostfix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getTimeSeparator() {
/* 134 */     return this.m_Implementor.getTimeSeparator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setTimeSeparator(char chSeparator) {
/* 145 */     return this.m_Implementor.setTimeSeparator(chSeparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/* 154 */     return this.m_Implementor.getMaskPlaceHolder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/* 163 */     this.m_Implementor.setMaskPlaceHolder(chPlaceHolder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration getTime() {
/* 173 */     return this.m_Implementor.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTime(Calendar time) {
/* 182 */     this.m_Implementor.setTime(time);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTime(JLbsTimeDuration duration) {
/* 191 */     this.m_Implementor.setTime(duration);
/* 192 */     recordSetTime(duration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paste() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processKeyEvent(KeyEvent e) {
/* 205 */     this.m_Implementor.processKeyEvent(e);
/* 206 */     super.processKeyEvent(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*     */     boolean bInheritedPaint;
/* 213 */     if (isFocusOwner() || this.m_Implementor.getPostfix() == null) {
/*     */       
/* 215 */       super.paintComponent(g);
/* 216 */       bInheritedPaint = true;
/*     */     }
/*     */     else {
/*     */       
/* 220 */       bInheritedPaint = false;
/* 221 */       g.setColor(getBackground());
/*     */     } 
/* 223 */     Rectangle drawRect = JLbsControlHelper.getClientRect((JComponent)this);
/* 224 */     if (!bInheritedPaint)
/* 225 */       g.fillRect(drawRect.x, drawRect.y, drawRect.width, drawRect.height); 
/* 226 */     String szText = getText();
/* 227 */     g.setColor(isEnabled() ? 
/* 228 */         getForeground() : 
/* 229 */         getDisabledTextColor());
/* 230 */     g.setFont(getFont());
/* 231 */     Rectangle textRect = JLbsControlHelper.drawStringVCentered((JComponent)this, g, drawRect, szText, 2, 
/* 232 */         bInheritedPaint);
/* 233 */     String szExtras = this.m_Implementor.getFormattedTextExtras();
/* 234 */     if (szExtras != null && szExtras.length() > 0) {
/*     */       
/* 236 */       drawRect.x = textRect.x + textRect.width + 4;
/* 237 */       JLbsControlHelper.drawStringVCentered((JComponent)this, g, drawRect, szExtras, 2, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowSeconds() {
/* 243 */     return this.m_Implementor.getShowSeconds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowSeconds(boolean b) {
/* 248 */     this.m_Implementor.setShowSeconds(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeFormatEx(int dispFormat, boolean duration) {
/* 253 */     this.m_Implementor.setTimeFormatEx(dispFormat, duration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetTime(JLbsTimeDuration duration) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordVerifyContent() {
/* 264 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "VERIFY");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 284 */     return this.m_Implementor.getCalendarValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar value) {
/* 289 */     this.m_Implementor.setCalendarValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 294 */     return this.m_Implementor.isValueModified();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsTimeEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */