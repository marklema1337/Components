/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.ibm.icu.text.DecimalFormat;
/*     */ import com.ibm.icu.text.DecimalFormatSymbols;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCurrenciesBase;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import java.math.BigDecimal;
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
/*     */ 
/*     */ 
/*     */ public class JLbsBigDecimalFormatter
/*     */   extends JLbsRealNumberFormatter
/*     */ {
/*  25 */   protected DecimalFormatSymbols symbols = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType) {
/*  35 */     super(iFormatType);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType, int iPrecision) {
/*  40 */     super(iFormatType, iPrecision);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType, int iPrecision, boolean showzero) {
/*  46 */     super(iFormatType, setIPrec(iPrecision, showzero));
/*  47 */     (JLbsBigDecimalFormatterFieldHolder.getInstance()).m_showFractionZero = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int setIPrec(int iprecision, boolean show) {
/*  53 */     (JLbsBigDecimalFormatterFieldHolder.getInstance()).m_showFractionZero = show;
/*  54 */     return iprecision;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(String szFormat) {
/*  59 */     super(szFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iPrecision, boolean bDisplayZero) {
/*  64 */     super(iPrecision, bDisplayZero);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iPrecision, boolean bGroup, boolean bDisplayZero) {
/*  69 */     super(iPrecision, bGroup, bDisplayZero);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero) {
/*  75 */     super(iFormatType, currencyIndex, precision, currBase, bDisplayZero);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType, int currencyIndex, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero, boolean forceDecimals) {
/*  81 */     super(iFormatType, currencyIndex, precision, currBase, bDisplayZero, forceDecimals);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType, int currencyIndex, String currencyValue, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero) {
/*  87 */     super(iFormatType, currencyIndex, currencyValue, precision, currBase, bDisplayZero);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsBigDecimalFormatter(int iFormatType, int currencyIndex, String currencyValue, int precision, JLbsCurrenciesBase currBase, boolean bDisplayZero, boolean forceDecimals) {
/*  93 */     super(iFormatType, currencyIndex, currencyValue, precision, currBase, bDisplayZero, forceDecimals);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Number parseNumber(String szText) throws ParseException {
/* 157 */     if (this.symbols == null) {
/* 158 */       this.symbols = isIndianCulture() ? (
/* 159 */         (getCultureInfo() == null) ? (
/* 160 */         new DecimalFormat(((ILbsCultureInfo)m_ThreadLocalCultureInfo.get()).getDefaultNumberFormat())).getDecimalFormatSymbols() : (
/* 161 */         new DecimalFormat(getCultureInfo().getDefaultNumberFormat())).getDecimalFormatSymbols()) : 
/* 162 */         createFormat(null).getDecimalFormatSymbols();
/*     */     }
/* 164 */     if (szText != null) {
/*     */       
/* 166 */       String groupSep = Character.toString(this.symbols.getGroupingSeparator());
/* 167 */       StringBuilder buffer = new StringBuilder(szText);
/*     */ 
/*     */       
/*     */       while (true) {
/* 171 */         int index = buffer.indexOf(groupSep);
/* 172 */         if (index < 0)
/*     */           break; 
/* 174 */         buffer.delete(index, index + 1);
/*     */       } 
/*     */       
/* 177 */       if (this.symbols.getDecimalSeparator() != '.') {
/*     */         
/* 179 */         int i = buffer.indexOf(Character.toString(this.symbols.getDecimalSeparator()));
/* 180 */         if (i >= 0)
/* 181 */           buffer.setCharAt(i, '.'); 
/*     */       } 
/* 183 */       szText = buffer.toString();
/*     */     } 
/*     */ 
/*     */     
/* 187 */     return new BigDecimal((szText == null || szText.length() == 0 || szText.compareTo("-") == 0) ? 
/* 188 */         "0" : 
/* 189 */         szText);
/*     */   }
/*     */   
/*     */   protected void setFormat(int iPrecision, boolean bGroup) {
/*     */     String baseFormat;
/* 194 */     StringBuilder szBuffer = new StringBuilder(iPrecision + 1);
/*     */     
/* 196 */     if (isIndianCulture()) {
/* 197 */       baseFormat = 
/*     */         
/* 199 */         String.valueOf(bGroup ? "#,##,##" : "##") + (this.m_DispZero ? 
/* 200 */         "0" : 
/* 201 */         "#");
/*     */     } else {
/* 203 */       baseFormat = 
/*     */         
/* 205 */         String.valueOf(bGroup ? "#,##" : "##") + (
/* 206 */         this.m_DispZero ? 
/* 207 */         "0" : 
/* 208 */         "#");
/*     */     } 
/* 210 */     if (iPrecision == 0) {
/* 211 */       this.m_Format = createFormat(baseFormat);
/*     */     } else {
/*     */       
/* 214 */       for (; iPrecision > 0; iPrecision--) {
/* 215 */         if ((JLbsBigDecimalFormatterFieldHolder.getInstance()).m_showFractionZero)
/* 216 */         { szBuffer.append('0'); }
/*     */         else
/* 218 */         { szBuffer.append('#'); } 
/* 219 */       }  this.m_Format = createFormat(String.valueOf(baseFormat) + "." + szBuffer);
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
/*     */   public void setDispZero(boolean bDispZero) {
/* 573 */     this.m_DispZero = bDispZero;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDispZero() {
/* 578 */     return this.m_DispZero;
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
/*     */   public static class JLbsBigDecimalFormatterFieldHolder
/*     */   {
/*     */     private boolean m_showFractionZero = false;
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
/*     */     private static JLbsBigDecimalFormatterFieldHolder getInstance() {
/* 607 */       return (JLbsBigDecimalFormatterFieldHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsBigDecimalFormatterFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsBigDecimalFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */