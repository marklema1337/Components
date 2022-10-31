/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsNumericEdit;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.controls.maskededit.JLbsLimitedTextDocument;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.recording.interfaces.ILbsNumericEditRecordingEvents;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.text.ParseException;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.Document;
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
/*     */ public class JLbsNumericEdit
/*     */   extends JLbsMaskedEdit
/*     */   implements ILbsNumericEditRecordingEvents, ILbsNumericEdit
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private LbsNumericEditImplementor m_Implementor;
/*     */   
/*     */   public JLbsNumericEdit() {
/*  46 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsNumericEdit(boolean bReal) {
/*  57 */     this(bReal, (Number)null);
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
/*     */   public JLbsNumericEdit(boolean bReal, Number nNumber) {
/*  72 */     this.m_Implementor = new LbsNumericEditImplementor(this);
/*  73 */     this.m_Implementor.init(bReal, nNumber);
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
/*     */   public ILbsNumberListener getNumberListener() {
/*  85 */     return this.m_Implementor.getNumberListener();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberListener(ILbsNumberListener listener) {
/*  96 */     this.m_Implementor.setNumberListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNumberListener() {
/* 106 */     this.m_Implementor.clearNumberListener();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter getNumberFormatter() {
/* 116 */     return this.m_Implementor.getNumberFormatter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(JLbsRealNumberFormatter formatter) {
/* 126 */     this.m_Implementor.setNumberFormatter(formatter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero) {
/* 134 */     this.m_Implementor.setNumberFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec) {
/* 144 */     this.m_Implementor.setNumberFormatter(iFormatSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int iPrecision) {
/* 149 */     this.m_Implementor.setNumberFormatter(iFormatSpec, iPrecision);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 154 */     this.m_Implementor.setCultureInfo(cultureInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrencyBase(JLbsCurrenciesBase currBase) {
/* 159 */     this.m_Implementor.setCurrencyBase(currBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getForceDecimals() {
/* 167 */     return this.m_Implementor.getForceDecimals();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForceDecimals(boolean bForce) {
/* 175 */     this.m_Implementor.setForceDecimals(bForce);
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
/*     */   public void setFilter(int iFilter, String szFormat, int iPrecision) {
/* 187 */     this.m_Implementor.setFilter(iFilter, szFormat, iPrecision);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumber(Number nNumber) {
/* 198 */     this.m_Implementor.setNumber(nNumber);
/* 199 */     recordSetNumber(nNumber);
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
/*     */   public Number getNumber() throws ParseException {
/* 211 */     return this.m_Implementor.getNumber();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignAllowed(boolean bSignAllowed) {
/* 221 */     this.m_Implementor.setSignAllowed(bSignAllowed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSignAllowed() {
/* 231 */     return this.m_Implementor.isSignAllowed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFormatAsYouType() {
/* 241 */     return this.m_Implementor.getFormatAsYouType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 252 */     return this.m_Implementor.getPrefix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/* 263 */     return this.m_Implementor.getPostfix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisplayZero() {
/* 274 */     return this.m_Implementor.getDisplayZero();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayZero(boolean bDispZero) {
/* 279 */     this.m_Implementor.setDisplayZero(bDispZero);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormatAsYouType(boolean bFormat) {
/* 289 */     this.m_Implementor.setFormatAsYouType(bFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(String szPrefix) {
/* 300 */     this.m_Implementor.setPrefix(szPrefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 311 */     this.m_Implementor.setPostfix(szPostfix);
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
/*     */   public void getDisplayZero(boolean bDisplayZero) {
/* 323 */     this.m_Implementor.getDisplayZero(bDisplayZero);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDoubleValue() {
/* 333 */     return this.m_Implementor.getDoubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongValue() {
/* 343 */     return this.m_Implementor.getLongValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Number getBigDecimalValue() {
/* 353 */     return this.m_Implementor.getBigDecimalValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String t) {
/*     */     try {
/* 360 */       Document doc = getDocument();
/* 361 */       int iLen = doc.getLength();
/* 362 */       doc.remove(0, doc.getLength());
/* 363 */       if (t != null) {
/*     */         
/* 365 */         int iCaretPos = iLen - getCaretPosition();
/* 366 */         doc.insertString(0, t, null);
/* 367 */         iCaretPos = t.length() - iCaretPos;
/* 368 */         setCaretPosition(Math.max(0, Math.min(iCaretPos, doc.getLength())));
/*     */       } 
/* 370 */       this.m_Implementor.setText(t);
/*     */     }
/* 372 */     catch (IllegalArgumentException e) {
/*     */       
/* 374 */       super.setText(t);
/*     */     }
/* 376 */     catch (BadLocationException e) {
/*     */       
/* 378 */       super.setText(t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/* 387 */     Document doc = getDocument();
/* 388 */     if (doc instanceof JLbsLimitedTextDocument)
/* 389 */       return ((JLbsLimitedTextDocument)doc).getMaxLength(); 
/* 390 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setTextLimit(int iLimit) {
/* 398 */     Document doc = getDocument();
/* 399 */     if (doc instanceof JLbsNumericEditDocument) {
/*     */       
/*     */       try {
/* 402 */         ((JLbsNumericEditDocument)doc).setMaxLength(iLimit);
/* 403 */         return true;
/*     */       }
/* 405 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 408 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 414 */     if (isFocusOwner() || (
/* 415 */       this.m_Implementor.getPrefix() == null && this.m_Implementor.getPostfix() == null && !this.m_Implementor.getForceDecimals())) {
/*     */       
/* 417 */       super.paintComponent(g);
/*     */     }
/*     */     else {
/*     */       
/* 421 */       g.setColor(getBackground());
/* 422 */       Rectangle drawRect = JLbsControlHelper.getClientRect((JComponent)this);
/* 423 */       g.fillRect(drawRect.x, drawRect.y, drawRect.width, drawRect.height);
/* 424 */       String szFullText = this.m_Implementor.getFormattedText(this.m_Implementor.getForceDecimals());
/* 425 */       if (this.m_Implementor.getPrefix() != null)
/* 426 */         szFullText = String.valueOf(this.m_Implementor.getPrefix()) + szFullText; 
/* 427 */       if (this.m_Implementor.getPostfix() != null)
/* 428 */         szFullText = String.valueOf(szFullText) + this.m_Implementor.getPostfix(); 
/* 429 */       g.setColor(isEnabled() ? 
/* 430 */           getForeground() : 
/* 431 */           getDisabledTextColor());
/* 432 */       JLbsControlHelper.drawStringVCentered((JComponent)this, g, drawRect, szFullText, 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Document createDefaultModel() {
/* 438 */     return (Document)new JLbsNumericEditDocument(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormatSpecifierParams() {
/* 443 */     this.m_Implementor.setFormatSpecifierParams();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetNumber(Number nNumber) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordVerifyContent() {
/* 453 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "SET_NUMBER");
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetNumber(Number nNumber, int actionID) {
/* 458 */     final Number mNumber = nNumber;
/* 459 */     final int fActionID = actionID;
/* 460 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 464 */             JLbsNumericEdit.this.setNumber(mNumber);
/* 465 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
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
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 485 */     return this.m_Implementor.getCultureInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 490 */     super.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/* 499 */     if (e.getID() == 1005) {
/*     */       
/*     */       try {
/*     */         
/* 503 */         JLbsRealNumberFormatter formatter = getNumberFormatter();
/* 504 */         if (formatter != null)
/*     */         {
/* 506 */           Number number = formatter.parseNumber(getText());
/* 507 */           formatter.setFromFocusLost(true);
/* 508 */           setNumber(number);
/* 509 */           formatter.setFromFocusLost(false);
/*     */         }
/*     */       
/* 512 */       } catch (Exception ex) {
/*     */         
/* 514 */         ex.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 519 */     super.processFocusEvent(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero, boolean forceDecimals) {
/* 526 */     this.m_Implementor.setNumberFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero, forceDecimals);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsNumericEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */