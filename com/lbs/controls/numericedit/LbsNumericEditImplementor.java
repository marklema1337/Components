/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsNumericEdit;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.ParseException;
/*     */ import javax.swing.JComponent;
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
/*     */ public class LbsNumericEditImplementor
/*     */ {
/*     */   private ILbsNumericEdit m_Component;
/*     */   private String m_Prefix;
/*     */   private String m_Postfix;
/*     */   private boolean m_bDispZero;
/*     */   private boolean m_bFmtAsUType;
/*     */   private JLbsRealNumberAdapter m_Adapter;
/*     */   private ILbsCultureInfo m_CultureInfo;
/*     */   private JLbsCurrenciesBase m_CurrencyBase;
/*     */   private boolean m_ForceDecimals;
/*     */   
/*     */   public LbsNumericEditImplementor(ILbsNumericEdit component) {
/*  35 */     this.m_Prefix = null;
/*  36 */     this.m_Postfix = null;
/*  37 */     this.m_bDispZero = true;
/*  38 */     this.m_bFmtAsUType = true;
/*  39 */     this.m_Adapter = null;
/*  40 */     this.m_CultureInfo = null;
/*  41 */     this.m_CurrencyBase = null;
/*  42 */     this.m_ForceDecimals = false;
/*     */     this.m_Component = component;
/*     */   }
/*     */   public void init(boolean bReal, Number nNumber) {
/*  46 */     setFilter(bReal ? 
/*  47 */         2 : 
/*  48 */         1, null, -1);
/*  49 */     if (nNumber != null)
/*  50 */       this.m_Component.setNumber(nNumber); 
/*  51 */     this.m_Component.setHorizontalAlignment(4);
/*  52 */     this.m_Component.addFocusListener(new FocusAdapter());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumber(Number nNumber) {
/*  57 */     if (this.m_Adapter != null) {
/*  58 */       this.m_Component.setText(this.m_Adapter.getNumberFormatter().formatNumber(nNumber));
/*     */     } else {
/*  60 */       this.m_Component.setText(nNumber.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getNumber() throws ParseException {
/*  65 */     if (this.m_Adapter != null)
/*  66 */       return this.m_Adapter.getNumberFormatter().parseNumber(this.m_Component.getText()); 
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsNumberListener getNumberListener() {
/*  72 */     if (this.m_Adapter != null)
/*  73 */       return this.m_Adapter.getNumberListener(); 
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberListener(ILbsNumberListener listener) {
/*  79 */     if (this.m_Adapter != null) {
/*  80 */       this.m_Adapter.setNumberListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearNumberListener() {
/*  85 */     if (this.m_Adapter != null) {
/*  86 */       this.m_Adapter.setNumberListener(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsRealNumberFormatter getNumberFormatter() {
/*  91 */     if (this.m_Adapter != null)
/*  92 */       return this.m_Adapter.getNumberFormatter(); 
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(JLbsRealNumberFormatter formatter) {
/*  98 */     if (this.m_Adapter != null) {
/*     */       
/* 100 */       this.m_Adapter.setNumberFormatter(formatter);
/* 101 */       setFormatSpecifierParams();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero) {
/* 107 */     if (this.m_Adapter != null) {
/*     */       
/* 109 */       this.m_bDispZero = dispZero;
/* 110 */       this.m_Adapter.setNumberFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero);
/* 111 */       setFormatSpecifierParams();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero, boolean forceDecimals) {
/* 117 */     if (this.m_Adapter != null) {
/*     */       
/* 119 */       this.m_bDispZero = dispZero;
/* 120 */       this.m_Adapter.setNumberFormatter(iFormatSpec, currencyIndex, precision, currBase, dispZero, forceDecimals);
/* 121 */       setFormatSpecifierParams();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec) {
/* 127 */     if (this.m_Adapter != null) {
/*     */       
/* 129 */       this.m_Adapter.setNumberFormatter(iFormatSpec);
/* 130 */       setFormatSpecifierParams();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int iFormatSpec, int iPrecision) {
/* 136 */     if (this.m_Adapter != null) {
/*     */       
/* 138 */       this.m_Adapter.setNumberFormatter(iFormatSpec, iPrecision);
/* 139 */       setFormatSpecifierParams();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 145 */     this.m_CultureInfo = cultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrencyBase(JLbsCurrenciesBase currencyBase) {
/* 150 */     this.m_CurrencyBase = currencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCurrenciesBase getCurrencyBase() {
/* 155 */     return this.m_CurrencyBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/* 160 */     return this.m_CultureInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getForceDecimals() {
/* 165 */     return this.m_ForceDecimals;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceDecimals(boolean bForce) {
/* 170 */     if (this.m_ForceDecimals != bForce) {
/*     */       
/* 172 */       this.m_ForceDecimals = bForce;
/* 173 */       this.m_Component.invalidate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilter(int iFilter, String szFormat, int iPrecision) {
/*     */     boolean bSignAllowed;
/*     */     ILbsNumberListener listener;
/*     */     JLbsRealNumberFormatter formatter;
/* 182 */     if (this.m_Adapter != null) {
/*     */       
/* 184 */       listener = this.m_Adapter.getNumberListener();
/* 185 */       bSignAllowed = this.m_Adapter.isSignAllowed();
/*     */     }
/*     */     else {
/*     */       
/* 189 */       listener = null;
/* 190 */       bSignAllowed = true;
/*     */     } 
/*     */     
/* 193 */     this.m_Component.removeKeyListener(this.m_Adapter);
/* 194 */     if (this.m_Component instanceof JComponent)
/* 195 */       ((JComponent)this.m_Component).removeInputMethodListener(this.m_Adapter); 
/* 196 */     switch (iFilter) {
/*     */ 
/*     */       
/*     */       case 1:
/* 200 */         this.m_Adapter = new JLbsRealNumberAdapter();
/* 201 */         formatter = (szFormat == null) ? 
/* 202 */           new JLbsRealNumberFormatter(0, this.m_bDispZero) : 
/* 203 */           new JLbsRealNumberFormatter(szFormat);
/* 204 */         this.m_Adapter.setNumberFormatter(formatter);
/* 205 */         this.m_Adapter.setPrecision(0);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 210 */         this.m_Adapter = new JLbsRealNumberAdapter();
/* 211 */         if (szFormat == null) {  } else {  }  formatter = 
/*     */ 
/*     */ 
/*     */           
/* 215 */           new JLbsRealNumberFormatter(szFormat);
/* 216 */         this.m_Adapter.setNumberFormatter(formatter);
/* 217 */         this.m_Adapter.setPrecision((iPrecision >= 0) ? 
/* 218 */             iPrecision : 
/* 219 */             4);
/*     */         break;
/*     */       
/*     */       default:
/* 223 */         this.m_Adapter = null;
/*     */         break;
/*     */     } 
/* 226 */     if (this.m_Adapter == null)
/*     */       return; 
/* 228 */     this.m_Component.addKeyListener(this.m_Adapter);
/* 229 */     if (this.m_Component instanceof JComponent) {
/* 230 */       ((JComponent)this.m_Component).addInputMethodListener(this.m_Adapter);
/*     */     }
/* 232 */     this.m_Adapter.setSignAllowed(bSignAllowed);
/* 233 */     this.m_Adapter.setNumberListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSignAllowed(boolean bSignAllowed) {
/* 238 */     if (this.m_Adapter != null) {
/* 239 */       this.m_Adapter.setSignAllowed(bSignAllowed);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSignAllowed() {
/* 244 */     return (this.m_Adapter != null && this.m_Adapter.isSignAllowed());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFormatAsYouType() {
/* 249 */     return this.m_bFmtAsUType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 254 */     return this.m_Prefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/* 259 */     return this.m_Postfix;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDisplayZero() {
/* 264 */     return this.m_bDispZero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayZero(boolean bDispZero) {
/* 269 */     this.m_bDispZero = bDispZero;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormatAsYouType(boolean bFormat) {
/* 274 */     if (this.m_bFmtAsUType != bFormat) {
/*     */       
/* 276 */       this.m_bFmtAsUType = bFormat;
/* 277 */       this.m_Component.setText(this.m_Component.getText());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefix(String szPrefix) {
/* 283 */     if (this.m_Prefix == null || this.m_Prefix.compareTo(szPrefix) != 0) {
/*     */       
/* 285 */       this.m_Prefix = szPrefix;
/* 286 */       if (!this.m_Component.hasFocus()) {
/* 287 */         this.m_Component.repaint();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 293 */     if (this.m_Postfix == null || this.m_Postfix.compareTo(szPostfix) != 0) {
/*     */       
/* 295 */       this.m_Postfix = szPostfix;
/* 296 */       if (!this.m_Component.hasFocus()) {
/* 297 */         this.m_Component.repaint();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getDisplayZero(boolean bDisplayZero) {
/* 303 */     if (this.m_bDispZero != bDisplayZero) {
/*     */       
/* 305 */       this.m_bDispZero = bDisplayZero;
/* 306 */       if (!this.m_Component.hasFocus()) {
/* 307 */         this.m_Component.repaint();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDoubleValue() {
/*     */     try {
/* 315 */       Number number = getNumber();
/* 316 */       if (number instanceof Double || number instanceof Float)
/* 317 */         return number.doubleValue(); 
/* 318 */       if (number instanceof Long) {
/* 319 */         return number.longValue();
/*     */       }
/* 321 */     } catch (ParseException parseException) {}
/*     */ 
/*     */     
/* 324 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongValue() {
/*     */     try {
/* 331 */       Number number = getNumber();
/* 332 */       if (number instanceof Double || number instanceof Float)
/* 333 */         return Math.round(number.doubleValue()); 
/* 334 */       if (number instanceof Long) {
/* 335 */         return number.longValue();
/*     */       }
/* 337 */     } catch (ParseException parseException) {}
/*     */ 
/*     */     
/* 340 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Number getBigDecimalValue() {
/*     */     try {
/* 347 */       return getNumber();
/*     */     }
/* 349 */     catch (ParseException parseException) {
/*     */ 
/*     */       
/* 352 */       return new BigDecimal(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setText(String t) {
/* 357 */     if (this.m_Adapter != null && this.m_Adapter.getNumberFormatter() != null && this.m_Adapter.getNumberFormatter().requiresAbsValue()) {
/* 358 */       setFormatSpecifierParams();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFormatSpecifierParams() {
/* 363 */     if (this.m_CultureInfo == null)
/* 364 */       this.m_CultureInfo = (ILbsCultureInfo)new JLbsDefaultCultureInfo(); 
/* 365 */     JLbsRealNumberFormatter formatter = getNumberFormatter();
/* 366 */     int iFormatSpec = (formatter != null) ? 
/* 367 */       formatter.getFormatSpecifier() : 
/* 368 */       0;
/* 369 */     String currSymbol = "";
/* 370 */     if (this.m_CurrencyBase == null)
/* 371 */       this.m_CurrencyBase = new JLbsCurrenciesBase(); 
/* 372 */     int currIdx = this.m_CultureInfo.getCurrencyIdx();
/* 373 */     currSymbol = this.m_CurrencyBase.getCurrencySymbol(currIdx);
/* 374 */     switch (iFormatSpec) {
/*     */       
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 380 */         this.m_Postfix = null;
/* 381 */         this.m_Prefix = null;
/*     */         break;
/*     */       case 6:
/* 384 */         this.m_Postfix = null;
/* 385 */         this.m_Prefix = this.m_CultureInfo.getPercentSign();
/*     */         break;
/*     */       case 7:
/* 388 */         this.m_Postfix = this.m_CultureInfo.getPercentSign();
/* 389 */         this.m_Prefix = null;
/*     */         break;
/*     */       case 8:
/* 392 */         this.m_Postfix = null;
/* 393 */         this.m_Prefix = String.valueOf(currSymbol) + " ";
/*     */         break;
/*     */       case 9:
/*     */       case 18:
/* 397 */         this.m_Postfix = " " + currSymbol;
/* 398 */         this.m_Prefix = null;
/*     */         break;
/*     */       case 10:
/*     */       case 19:
/* 402 */         this.m_Postfix = " " + getCreditDebitText(this.m_CultureInfo);
/* 403 */         this.m_Prefix = null;
/*     */         break;
/*     */       case 11:
/*     */       case 20:
/* 407 */         this.m_Postfix = " " + getCreditDebitText(this.m_CultureInfo);
/* 408 */         this.m_Prefix = String.valueOf(currSymbol) + " ";
/*     */         break;
/*     */       case 12:
/*     */       case 21:
/* 412 */         this.m_Postfix = " " + getCreditDebitText(this.m_CultureInfo) + " " + currSymbol;
/* 413 */         this.m_Prefix = null;
/*     */         break;
/*     */       case 13:
/*     */       case 22:
/* 417 */         this.m_Postfix = " " + currSymbol + " " + getCreditDebitText(this.m_CultureInfo);
/* 418 */         this.m_Prefix = null;
/*     */         break;
/*     */       case 14:
/* 421 */         this.m_Postfix = ")";
/* 422 */         this.m_Prefix = String.valueOf(currSymbol) + " (";
/*     */         break;
/*     */       case 15:
/* 425 */         this.m_Postfix = ") " + currSymbol;
/* 426 */         this.m_Prefix = "(";
/*     */         break;
/*     */       case 23:
/* 429 */         this.m_Postfix = ")";
/* 430 */         this.m_Prefix = " (";
/*     */         break;
/*     */       default:
/* 433 */         this.m_Postfix = null;
/* 434 */         this.m_Prefix = null;
/*     */         break;
/*     */     } 
/* 437 */     this.m_Component.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getCreditDebitText(ILbsCultureInfo culture) {
/* 442 */     if (culture == null)
/* 443 */       return ""; 
/* 444 */     String text = this.m_Component.getText();
/* 445 */     if (text != null && text.length() > 0 && text.charAt(0) == '-')
/* 446 */       return culture.getCreditText(); 
/* 447 */     return culture.getDebitText();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getFormattedText(boolean bForceDecimals) {
/* 452 */     String szNumber, szText = this.m_Component.getText();
/* 453 */     if (szText == null || szText.trim().length() == 0 || szText.compareTo("-") == 0)
/*     */     {
/* 455 */       if (bForceDecimals) {
/* 456 */         szText = "0";
/*     */       } else {
/* 458 */         return this.m_bDispZero ? 
/* 459 */           "0" : 
/* 460 */           "";
/*     */       } 
/*     */     }
/*     */     
/* 464 */     JLbsRealNumberFormatter formatter = this.m_Adapter.getNumberFormatter();
/*     */     
/*     */     try {
/* 467 */       Number nNumber = formatter.parseNumber(szText);
/* 468 */       szNumber = formatter.formatNumber(nNumber, this.m_ForceDecimals, false);
/*     */     }
/* 470 */     catch (ParseException e) {
/*     */       
/* 472 */       szNumber = "";
/*     */     } 
/* 474 */     return szNumber;
/*     */   }
/*     */   
/*     */   class FocusAdapter
/*     */     implements FocusListener
/*     */   {
/*     */     public void focusLost(FocusEvent feEvt) {
/* 481 */       JLbsRealNumberFormatter formatter = LbsNumericEditImplementor.this.getNumberFormatter();
/* 482 */       if (formatter != null && formatter.requiresAbsValue())
/* 483 */         LbsNumericEditImplementor.this.setFormatSpecifierParams(); 
/* 484 */       InternalUpdate(feEvt.getSource());
/*     */     }
/*     */ 
/*     */     
/*     */     public void focusGained(FocusEvent feEvt) {
/* 489 */       InternalUpdate(feEvt.getSource());
/*     */     }
/*     */ 
/*     */     
/*     */     private void InternalUpdate(Object obj) {
/* 494 */       LbsNumericEditImplementor.this.m_Component.repaint();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\LbsNumericEditImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */