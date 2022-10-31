/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.TextField;
/*     */ import java.awt.event.InputMethodEvent;
/*     */ import java.awt.event.InputMethodListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JTextField;
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
/*     */ public class JLbsRealNumberAdapter
/*     */   implements KeyListener, InputMethodListener
/*     */ {
/*  32 */   private ILbsNumberListener m_Listener = null;
/*  33 */   private JLbsRealNumberFormatter m_Formatter = null;
/*     */   private boolean m_bSignAllowed = true;
/*  35 */   private int m_iPrecision = 0;
/*  36 */   private char m_DecimalSeparator = (new DecimalFormat()).getDecimalFormatSymbols().getDecimalSeparator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberAdapter() {
/*  44 */     setNumberFormatter(new JLbsBigDecimalFormatter());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberAdapter(ILbsNumberListener listener) {
/*  55 */     setNumberFormatter(new JLbsBigDecimalFormatter());
/*  56 */     setNumberListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberAdapter(boolean bSignAllowed) {
/*  66 */     setNumberFormatter(new JLbsBigDecimalFormatter());
/*  67 */     setSignAllowed(bSignAllowed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberAdapter(ILbsNumberListener listener, boolean bSignAllowed) {
/*  78 */     setNumberFormatter(new JLbsBigDecimalFormatter());
/*  79 */     setNumberListener(listener);
/*  80 */     setSignAllowed(bSignAllowed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSignAllowed() {
/*  90 */     return this.m_bSignAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignAllowed(boolean bSignAllowed) {
/* 100 */     this.m_bSignAllowed = bSignAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsNumberListener getNumberListener() {
/* 111 */     return this.m_Listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberListener(ILbsNumberListener listener) {
/* 121 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNumberListenerAttached() {
/* 131 */     return (this.m_Listener != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter getNumberFormatter() {
/* 141 */     return this.m_Formatter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(JLbsRealNumberFormatter formatter) {
/* 151 */     this.m_Formatter = formatter;
/* 152 */     setInternalFormatSpecifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec) {
/* 163 */     this.m_Formatter = new JLbsBigDecimalFormatter(iFormatSpec);
/* 164 */     setInternalFormatSpecifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int iPrecision) {
/* 169 */     this.m_Formatter = new JLbsBigDecimalFormatter(iFormatSpec, iPrecision);
/* 170 */     setInternalFormatSpecifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero) {
/* 177 */     this.m_Formatter = new JLbsBigDecimalFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero);
/* 178 */     setInternalFormatSpecifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero, boolean forceDecimals) {
/* 183 */     this.m_Formatter = new JLbsBigDecimalFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero, forceDecimals);
/* 184 */     setInternalFormatSpecifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPrecision() {
/* 193 */     return this.m_iPrecision;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrecision(int iPrecision) {
/* 203 */     if (this.m_iPrecision != iPrecision)
/*     */     {
/* 205 */       this.m_iPrecision = iPrecision;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireInvalidNumberEvent(JLbsNumericEditEvent event) {
/* 217 */     if (this.m_Listener == null)
/*     */       return; 
/* 219 */     this.m_Listener.gotInvalidNumber(event);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent keKey) {
/* 230 */     char cKey = keKey.getKeyChar();
/* 231 */     if (processCharacter(cKey, keKey.getSource())) {
/*     */       
/* 233 */       checkForDisplayZero(keKey.getSource());
/*     */       return;
/*     */     } 
/* 236 */     keKey.consume();
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkForDisplayZero(Object o) {
/* 241 */     if (o instanceof JLbsNumericEdit) {
/*     */       
/* 243 */       JLbsNumericEdit edit = (JLbsNumericEdit)o;
/*     */       
/* 245 */       if (!edit.isEnabled()) {
/*     */         return;
/*     */       }
/* 248 */       boolean bDispZero = edit.getDisplayZero();
/* 249 */       String text = edit.getText();
/* 250 */       if (bDispZero && JLbsStringUtil.isEmpty(text)) {
/* 251 */         edit.setText("0");
/* 252 */       } else if (!bDispZero && JLbsStringUtil.areEqual(text, "0")) {
/* 253 */         edit.setText("");
/*     */       } 
/*     */     } 
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
/*     */   public void inputMethodTextChanged(InputMethodEvent imeEvt) {
/* 267 */     AttributedCharacterIterator aciIterator = imeEvt.getText();
/*     */     
/* 269 */     for (char cChar = aciIterator.first(); cChar != Character.MAX_VALUE; cChar = aciIterator.next()) {
/*     */       
/* 271 */       if (!processCharacter(cChar, imeEvt.getSource()))
/*     */       {
/* 273 */         imeEvt.consume();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent keEvt) {}
/*     */   
/*     */   public void keyReleased(KeyEvent keEvt) {
/* 282 */     checkForDisplayZero((keEvt != null) ? keEvt.getSource() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void caretPositionChanged(InputMethodEvent imeEvt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDecimalPointEnabled(String szText) {
/* 300 */     return (this.m_iPrecision > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean checkForDecimalPrecision(Object obj) {
/* 306 */     String szText = getEditText(obj);
/* 307 */     if (szText != null && szText.length() > 0) {
/*     */       
/* 309 */       int iIndex = szText.indexOf(this.m_DecimalSeparator);
/* 310 */       if (iIndex >= 0) {
/*     */         
/* 312 */         int iDecCount = szText.length() - iIndex - 1;
/* 313 */         if (iDecCount >= this.m_iPrecision) {
/*     */           
/* 315 */           int iCaretPos = getEditCaretPosition(obj);
/* 316 */           int iSelLength = getEditSelectionLength(obj);
/* 317 */           return !(iCaretPos > iIndex && szText.length() != iSelLength);
/*     */         } 
/*     */       } 
/*     */     } 
/* 321 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean checkForDecimalPointInsert(Object obj, String szText) {
/* 326 */     int iCaretPos = getEditCaretPosition(obj);
/* 327 */     if (iCaretPos < 0)
/* 328 */       return true; 
/* 329 */     int iDecCount = szText.length() - iCaretPos - 1;
/* 330 */     return (iDecCount < this.m_iPrecision);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getEditText(Object obj) {
/* 335 */     if (obj instanceof JTextField)
/* 336 */       return ((JTextField)obj).getText(); 
/* 337 */     if (obj instanceof TextField)
/* 338 */       return ((TextField)obj).getText(); 
/* 339 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setEditText(Object obj, String szValue) {
/* 344 */     if (!isControlEnabled(obj)) {
/*     */       return;
/*     */     }
/* 347 */     if (obj instanceof JTextField) {
/* 348 */       ((JTextField)obj).setText(szValue);
/* 349 */     } else if (obj instanceof TextField) {
/* 350 */       ((TextField)obj).setText(szValue);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isControlEnabled(Object obj) {
/* 355 */     if (obj instanceof JTextField) {
/* 356 */       return ((JTextField)obj).isEnabled();
/*     */     }
/* 358 */     if (obj instanceof TextField) {
/* 359 */       return ((TextField)obj).isEnabled();
/*     */     }
/* 361 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getEditCaretPosition(Object obj) {
/* 366 */     if (obj instanceof JTextField)
/* 367 */       return ((JTextField)obj).getCaretPosition(); 
/* 368 */     if (obj instanceof TextField)
/* 369 */       return ((TextField)obj).getCaretPosition(); 
/* 370 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getEditSelectionLength(Object obj) {
/* 375 */     String selText = null;
/* 376 */     if (obj instanceof JTextField) {
/* 377 */       selText = ((JTextField)obj).getSelectedText();
/* 378 */     } else if (obj instanceof TextField) {
/* 379 */       selText = ((TextField)obj).getSelectedText();
/* 380 */     }  return (selText != null) ? selText.length() : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setEditCaretPosition(Object obj, int iPos) {
/*     */     try {
/* 387 */       if (obj instanceof JTextField) {
/* 388 */         ((JTextField)obj).setCaretPosition(iPos);
/* 389 */       } else if (obj instanceof TextField) {
/* 390 */         ((TextField)obj).setCaretPosition(iPos);
/*     */       } 
/* 392 */     } catch (Exception exception) {}
/*     */   }
/*     */   protected boolean processCharacter(char cChar, Object obj) {
/*     */     String szEditText;
/*     */     int iCount;
/*     */     StringBuilder szBuffer;
/*     */     String str1;
/* 399 */     if (Character.isDigit(cChar) && checkForDecimalPrecision(obj))
/* 400 */       return true; 
/* 401 */     switch (cChar) {
/*     */       
/*     */       case '\b':
/* 404 */         return true;
/*     */       case ',':
/* 406 */         if (this.m_DecimalSeparator != ',') {
/*     */           break;
/*     */         }
/*     */       case '.':
/* 410 */         szEditText = getEditText(obj);
/* 411 */         if (this.m_iPrecision > 0 && szEditText != null && 
/* 412 */           isDecimalPointEnabled(szEditText) && 
/* 413 */           checkForDecimalPointInsert(obj, szEditText)) {
/* 414 */           return true;
/*     */         }
/*     */         break;
/*     */       
/*     */       case '-':
/* 419 */         if (!this.m_bSignAllowed)
/*     */           break; 
/* 421 */         szEditText = getEditText(obj);
/* 422 */         if (szEditText == null)
/* 423 */           return true; 
/* 424 */         szBuffer = new StringBuilder(szEditText);
/* 425 */         if (szEditText.length() > 0 && szEditText.charAt(0) == '-') {
/* 426 */           szBuffer.deleteCharAt(0);
/*     */         } else {
/* 428 */           szBuffer.insert(0, '-');
/* 429 */         }  setEditText(obj, szBuffer.toString());
/* 430 */         if (obj instanceof JLbsNumericEdit) {
/* 431 */           ((JLbsNumericEdit)obj).setFormatSpecifierParams();
/*     */         }
/*     */         break;
/*     */       
/*     */       case ' ':
/* 436 */         iCount = 0;
/* 437 */         str1 = getEditText(obj);
/* 438 */         if (str1 != null && str1.length() > 0 && str1.charAt(0) != '0')
/* 439 */           while (checkForDecimalPrecision(obj) && iCount++ < 3)
/* 440 */             setEditText(obj, String.valueOf(getEditText(obj)) + "0");  
/* 441 */         setEditCaretPosition(obj, getEditText(obj).length());
/*     */         break;
/*     */     } 
/*     */     
/* 445 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setInternalFormatSpecifier() {
/* 450 */     this.m_iPrecision = (this.m_Formatter != null) ? this.m_Formatter.getPrecisionCount() : 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsRealNumberAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */