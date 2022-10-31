/*    */ package com.lbs.remoteclient;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsTextReportOptions
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public boolean PrintHeaders = true;
/*    */   public boolean PadToMaxLenght = false;
/* 20 */   public int LineTerm = 0;
/* 21 */   public String PadCharacter = "0";
/* 22 */   public String Delimitter = ",";
/* 23 */   public String Encoding = "";
/* 24 */   public int DecimalSeperator = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsTextReportOptions() {}
/*    */ 
/*    */   
/*    */   public JLbsTextReportOptions(boolean printHeaders, boolean padToMaxLength, String padChar, String delimitter, int lineTerm, String encoding) {
/* 32 */     this();
/* 33 */     this.PrintHeaders = printHeaders;
/* 34 */     this.PadToMaxLenght = padToMaxLength;
/* 35 */     this.PadCharacter = padChar;
/* 36 */     this.Delimitter = delimitter;
/* 37 */     this.LineTerm = lineTerm;
/* 38 */     this.Encoding = encoding;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsTextReportOptions(boolean printHeaders, boolean padToMaxLength, String padChar, String delimitter, int lineTerm, String encoding, int decSep) {
/* 44 */     this();
/* 45 */     this.PrintHeaders = printHeaders;
/* 46 */     this.PadToMaxLenght = padToMaxLength;
/* 47 */     this.PadCharacter = padChar;
/* 48 */     this.Delimitter = delimitter;
/* 49 */     this.LineTerm = lineTerm;
/* 50 */     this.Encoding = encoding;
/* 51 */     this.DecimalSeperator = decSep;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsTextReportOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */