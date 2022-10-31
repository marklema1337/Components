/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.lbs.controls.maskededit.JLbsLimitedTextDocument;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.ParseException;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsNumericEditDocument
/*     */   extends JLbsLimitedTextDocument
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLbsNumericEdit m_Edit;
/*     */   private char m_DecimalSeparator;
/*     */   private char m_GroupSeparator;
/*     */   
/*     */   public JLbsNumericEditDocument(JLbsNumericEdit edit) {
/*  32 */     this.m_Edit = edit;
/*  33 */     DecimalFormatSymbols symbols = (new DecimalFormat()).getDecimalFormatSymbols();
/*  34 */     this.m_DecimalSeparator = symbols.getDecimalSeparator();
/*  35 */     this.m_GroupSeparator = symbols.getGroupingSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
/*  40 */     String currentText = getText(0, getLength());
/*  41 */     if (this.m_MaxLength > 0 && currentText.length() >= this.m_MaxLength)
/*     */       return; 
/*  43 */     String beforeOffset = currentText.substring(0, offs);
/*  44 */     String afterOffset = currentText.substring(offs, currentText.length());
/*  45 */     String proposedResult = String.valueOf(beforeOffset) + str + afterOffset;
/*     */ 
/*     */     
/*     */     try {
/*  49 */       if (proposedResult.length() != 0) {
/*     */         
/*  51 */         String sInvalidComb = String.valueOf(Character.toString(this.m_DecimalSeparator)) + Character.toString(this.m_GroupSeparator);
/*  52 */         int index = proposedResult.indexOf(sInvalidComb);
/*  53 */         if (index >= 0) {
/*     */           
/*  55 */           StringBuilder sBuffer = new StringBuilder(proposedResult);
/*  56 */           sBuffer.delete(index + 1, index + 2);
/*  57 */           proposedResult = sBuffer.toString();
/*     */         } 
/*     */       } 
/*  60 */       int iCaretPos = currentText.length() - offs;
/*  61 */       int iPropStrLen = proposedResult.length();
/*  62 */       boolean bHasDot = (iPropStrLen > 0) ? (
/*  63 */         (proposedResult.charAt(iPropStrLen - 1) == this.m_DecimalSeparator)) : false;
/*     */       
/*  65 */       int sepPosition = currentText.indexOf(this.m_DecimalSeparator);
/*  66 */       boolean bAddZero = (sepPosition >= 0 && sepPosition < offs && str.equals("0"));
/*  67 */       if (proposedResult.length() == 1 && proposedResult.charAt(0) == this.m_DecimalSeparator)
/*  68 */         proposedResult = "0"; 
/*  69 */       String szNewText = getFormattedText(proposedResult);
/*  70 */       if (bHasDot)
/*  71 */         szNewText = String.valueOf(szNewText) + this.m_DecimalSeparator; 
/*  72 */       if (bAddZero)
/*  73 */         szNewText = proposedResult; 
/*  74 */       super.remove(0, currentText.length());
/*  75 */       super.insertString(0, szNewText, a);
/*  76 */       int iLen = szNewText.length();
/*  77 */       this.m_Edit.setCaretPosition(Math.max(0, Math.min(iLen, iLen - iCaretPos)));
/*     */     }
/*  79 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int offs, int len) throws BadLocationException {
/*  86 */     if (len <= 0)
/*     */       return; 
/*  88 */     String currentText = getText(0, getLength());
/*  89 */     if (len == 1 && currentText.charAt(offs) == this.m_GroupSeparator && offs < currentText.length() - 1)
/*  90 */       offs++; 
/*  91 */     String beforeOffset = currentText.substring(0, offs);
/*  92 */     String afterOffset = currentText.substring(len + offs, currentText.length());
/*  93 */     String proposedResult = String.valueOf(beforeOffset) + afterOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  99 */       if (proposedResult.length() != 0) {
/*     */         
/* 101 */         int sepPosition = currentText.indexOf(this.m_DecimalSeparator);
/* 102 */         boolean needZero = (sepPosition >= 0 && sepPosition < offs);
/* 103 */         String szNewText = getFormattedText(proposedResult);
/* 104 */         if (needZero) {
/*     */           
/* 106 */           szNewText = proposedResult;
/* 107 */           super.remove(offs, len);
/*     */         } else {
/*     */           
/* 110 */           replace(0, currentText.length(), szNewText, null);
/* 111 */         }  this.m_Edit.setCaretPosition(Math.max(0, Math.min(szNewText.length(), offs)));
/*     */       } else {
/*     */         
/* 114 */         super.remove(offs, len);
/*     */       } 
/* 116 */     } catch (ParseException e) {
/*     */       
/* 118 */       super.remove(offs, len);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getFormattedText(String szNumber) throws ParseException {
/* 124 */     if (this.m_Edit != null) {
/*     */       
/* 126 */       boolean bDispZero = this.m_Edit.getDisplayZero();
/* 127 */       if (bDispZero && JLbsStringUtil.isEmpty(szNumber)) {
/* 128 */         szNumber = "0";
/* 129 */       } else if (!bDispZero && JLbsStringUtil.areEqual(szNumber, "0")) {
/* 130 */         return "";
/* 131 */       }  if (this.m_Edit.getFormatAsYouType()) {
/*     */         
/* 133 */         Number number = this.m_Edit.getNumberFormatter().parseNumber(szNumber);
/* 134 */         JLbsRealNumberFormatter formatter = this.m_Edit.getNumberFormatter();
/* 135 */         String szNewText = "";
/* 136 */         if (formatter != null) {
/*     */           
/* 138 */           formatter.setEnableZeros(true);
/* 139 */           szNewText = formatter.formatNumber(number);
/*     */         }
/*     */         else {
/*     */           
/* 143 */           szNewText = number.toString();
/*     */         } 
/* 145 */         return szNewText;
/*     */       } 
/*     */     } 
/* 148 */     return szNumber;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsNumericEditDocument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */