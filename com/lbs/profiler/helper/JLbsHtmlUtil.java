/*    */ package com.lbs.profiler.helper;
/*    */ 
/*    */ import java.awt.Color;
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
/*    */ 
/*    */ 
/*    */ public class JLbsHtmlUtil
/*    */ {
/*    */   private StringBuilder m_Buffer;
/*    */   
/*    */   public JLbsHtmlUtil(StringBuilder buffer) {
/* 21 */     this.m_Buffer = buffer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void beginTable() {
/* 26 */     this.m_Buffer.append("<TABLE border=1 cellspacing=0 cellpadding=2>");
/* 27 */     this.m_Buffer.append("<TBODY>");
/*    */   }
/*    */ 
/*    */   
/*    */   public void endTable() {
/* 32 */     this.m_Buffer.append("</TBODY>");
/* 33 */     this.m_Buffer.append("</TABLE>");
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTableHeader(String[] cols) {
/* 38 */     this.m_Buffer.append("<TR>");
/* 39 */     for (int i = 0; i < cols.length; i++)
/* 40 */       this.m_Buffer.append("<TH>" + cols[i] + "</TH>"); 
/* 41 */     this.m_Buffer.append("</TR>");
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTableRow(String[] cols, boolean firstColumnIsBold) {
/* 46 */     this.m_Buffer.append("<TR>");
/* 47 */     for (int i = 0; i < cols.length; i++) {
/* 48 */       if (!firstColumnIsBold || i != 0)
/* 49 */       { this.m_Buffer.append("<TD>" + cols[i] + "</TD>"); }
/*    */       else
/* 51 */       { this.m_Buffer.append("<TH align=left>" + cols[i] + "</TH>"); } 
/* 52 */     }  this.m_Buffer.append("</TR>");
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTableRow(String[] cols) {
/* 57 */     addTableRow(cols, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getHref(String displayText, String href) {
/* 62 */     return "<a href=\"" + href + "\">" + displayText + "</a>";
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getColorStr(Color color) {
/* 67 */     String hexcolor = "";
/* 68 */     String colorPart = Integer.toHexString(color.getRed());
/* 69 */     if (colorPart.length() == 1)
/* 70 */       colorPart = "0" + colorPart; 
/* 71 */     hexcolor = String.valueOf(hexcolor) + colorPart;
/* 72 */     colorPart = Integer.toHexString(color.getGreen());
/* 73 */     if (colorPart.length() == 1)
/* 74 */       colorPart = "0" + colorPart; 
/* 75 */     hexcolor = String.valueOf(hexcolor) + colorPart;
/* 76 */     colorPart = Integer.toHexString(color.getBlue());
/* 77 */     if (colorPart.length() == 1)
/* 78 */       colorPart = "0" + colorPart; 
/* 79 */     hexcolor = String.valueOf(hexcolor) + colorPart;
/* 80 */     return hexcolor;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String changeTextFont(String text, Color color) {
/* 85 */     return "<font color=\"#" + getColorStr(color) + "\">" + text + "</font>";
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\JLbsHtmlUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */