/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsNumericEdit;
/*     */ import com.lbs.control.interfaces.ILbsNumericEditWithCalculator;
/*     */ import com.lbs.controls.numericedit.ILbsNumberListener;
/*     */ import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
/*     */ import com.lbs.controls.numericedit.LbsNumericEditImplementor;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import java.text.ParseException;
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
/*     */ public class LbsNumericEditEmulator
/*     */   extends LbsMaskedEditEmulator
/*     */   implements ILbsNumericEdit, ILbsNumericEditWithCalculator
/*     */ {
/*     */   private LbsNumericEditImplementor m_Implementor;
/*     */   
/*     */   public LbsNumericEditEmulator() {
/*  28 */     this(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsNumericEditEmulator(boolean bReal) {
/*  33 */     this(bReal, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsNumericEditEmulator(boolean bReal, Number nNumber) {
/*  38 */     this.m_Implementor = new LbsNumericEditImplementor(this);
/*  39 */     this.m_Implementor.init(bReal, nNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/*  47 */     setNumber((Number)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*     */     try {
/*  55 */       return getNumber();
/*     */     }
/*  57 */     catch (ParseException e) {
/*     */       
/*  59 */       e.printStackTrace();
/*     */       
/*  61 */       return Integer.valueOf(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String s) {
/*     */     try {
/*  68 */       super.setText(s);
/*  69 */       this.m_Implementor.setText(s);
/*     */     }
/*  71 */     catch (Exception e) {
/*     */       
/*  73 */       super.setText(s);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearNumberListener() {
/*  79 */     this.m_Implementor.clearNumberListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getBigDecimalValue() {
/*  84 */     return this.m_Implementor.getBigDecimalValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCultureInfo getCultureInfo() {
/*  89 */     return this.m_Implementor.getCultureInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDisplayZero() {
/*  94 */     return this.m_Implementor.getDisplayZero();
/*     */   }
/*     */ 
/*     */   
/*     */   public void getDisplayZero(boolean displayZero) {
/*  99 */     this.m_Implementor.getDisplayZero(displayZero);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDoubleValue() {
/* 104 */     return this.m_Implementor.getDoubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getForceDecimals() {
/* 109 */     return this.m_Implementor.getForceDecimals();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFormatAsYouType() {
/* 114 */     return this.m_Implementor.getFormatAsYouType();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLongValue() {
/* 119 */     return this.m_Implementor.getLongValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getNumber() throws ParseException {
/* 124 */     return this.m_Implementor.getNumber();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsRealNumberFormatter getNumberFormatter() {
/* 129 */     return this.m_Implementor.getNumberFormatter();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsNumberListener getNumberListener() {
/* 134 */     return this.m_Implementor.getNumberListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/* 139 */     return this.m_Implementor.getPostfix();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 144 */     return this.m_Implementor.getPrefix();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSignAllowed() {
/* 149 */     return this.m_Implementor.isSignAllowed();
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
/*     */   public void setDisplayZero(boolean dispZero) {
/* 164 */     this.m_Implementor.setDisplayZero(dispZero);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilter(int filter, String szFormat, int precision) {
/* 169 */     this.m_Implementor.setFilter(filter, szFormat, precision);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceDecimals(boolean force) {
/* 174 */     this.m_Implementor.setForceDecimals(force);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormatAsYouType(boolean format) {
/* 179 */     this.m_Implementor.setFormatAsYouType(format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormatSpecifierParams() {
/* 184 */     this.m_Implementor.setFormatSpecifierParams();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumber(Number number) {
/* 189 */     this.m_Implementor.setNumber(number);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int formatSpec) {
/* 194 */     this.m_Implementor.setNumberFormatter(formatSpec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int formatSpec, int precision) {
/* 199 */     this.m_Implementor.setNumberFormatter(formatSpec, precision);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int formatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero) {
/* 204 */     this.m_Implementor.setNumberFormatter(formatSpec, currencyIndex, precision, currBase, dispZero);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(int formatSpec, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean dispZero, boolean forceDecimals) {
/* 209 */     this.m_Implementor.setNumberFormatter(formatSpec, currencyIndex, precision, currBase, dispZero, forceDecimals);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberFormatter(JLbsRealNumberFormatter formatter) {
/* 214 */     this.m_Implementor.setNumberFormatter(formatter);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumberListener(ILbsNumberListener listener) {
/* 219 */     this.m_Implementor.setNumberListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 224 */     this.m_Implementor.setPostfix(szPostfix);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefix(String szPrefix) {
/* 229 */     this.m_Implementor.setPrefix(szPrefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSignAllowed(boolean signAllowed) {
/* 234 */     this.m_Implementor.setSignAllowed(signAllowed);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsNumericEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */