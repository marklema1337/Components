/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsWrappedText
/*     */ {
/*     */   private ArrayList m_Lines;
/*     */   private Dimension m_Size;
/*     */   int[] m_LineWidths;
/*     */   
/*     */   public JLbsWrappedText(FontMetrics fm, String text, int width) {
/*  20 */     int stringWidth = fm.stringWidth(text);
/*  21 */     if (width - 5 < stringWidth) {
/*  22 */       this.m_Lines = breakIntoLines(fm, text, width - 5);
/*     */     } else {
/*     */       
/*  25 */       this.m_Lines = new ArrayList();
/*  26 */       this.m_Lines.add(text);
/*     */     } 
/*  28 */     this.m_Size = getLinesSize(fm);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsWrappedText(FontMetrics fm, String text) {
/*  33 */     if (text != null) {
/*     */       
/*  35 */       String[] tokens = text.split("\n");
/*  36 */       this.m_Lines = new ArrayList();
/*  37 */       for (int i = 0; i < tokens.length; i++)
/*  38 */         this.m_Lines.add(tokens[i]); 
/*     */     } 
/*  40 */     this.m_Size = getLinesSize(fm);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getLinesSize(FontMetrics fm) {
/*  45 */     Dimension result = new Dimension();
/*  46 */     if (fm != null && this.m_Lines != null) {
/*     */       
/*  48 */       int lineHeight = fm.getHeight();
/*  49 */       int count = this.m_Lines.size();
/*  50 */       this.m_LineWidths = new int[count];
/*  51 */       for (int i = 0; i < count; i++) {
/*     */         
/*  53 */         String s = this.m_Lines.get(i);
/*  54 */         int width = fm.stringWidth(s);
/*  55 */         this.m_LineWidths[i] = width;
/*  56 */         if (result.width < width)
/*  57 */           result.width = width; 
/*  58 */         result.height += lineHeight;
/*     */       } 
/*     */     } 
/*  61 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ArrayList breakIntoLines(FontMetrics fm, String s, int width) {
/*  66 */     if (s == null) return null; 
/*  67 */     int fromIndex = 0;
/*  68 */     int pos = 0;
/*     */ 
/*     */     
/*  71 */     ArrayList<String> lines = new ArrayList();
/*  72 */     String text = s;
/*  73 */     while (fromIndex != -1) {
/*     */       
/*  75 */       while (fromIndex < text.length() && text.charAt(fromIndex) == ' ') {
/*     */         
/*  77 */         fromIndex++;
/*  78 */         if (fromIndex >= text.length())
/*     */           break; 
/*     */       } 
/*  81 */       pos = fromIndex;
/*  82 */       int bestpos = -1;
/*  83 */       String largestString = null;
/*  84 */       while (pos >= fromIndex && pos < text.length()) {
/*     */         
/*  86 */         boolean bHardNewline = false;
/*  87 */         int newlinePos = text.indexOf('\n', pos);
/*  88 */         int spacePos = text.indexOf(' ', pos);
/*  89 */         if (newlinePos != -1 && (spacePos == -1 || (spacePos != -1 && newlinePos < spacePos))) {
/*     */           
/*  91 */           pos = newlinePos;
/*  92 */           bHardNewline = true;
/*     */         }
/*     */         else {
/*     */           
/*  96 */           pos = spacePos;
/*  97 */           bHardNewline = false;
/*     */         } 
/*  99 */         if (pos == -1) {
/* 100 */           s = text.substring(fromIndex);
/*     */         } else {
/* 102 */           s = text.substring(fromIndex, pos);
/* 103 */         }  if (fm.stringWidth(s) < width) {
/*     */           
/* 105 */           largestString = s;
/* 106 */           bestpos = pos;
/* 107 */           if (bHardNewline)
/* 108 */             bestpos++; 
/* 109 */           if (pos == -1 || bHardNewline) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 114 */           pos++; continue;
/*     */         }  break;
/* 116 */       }  if (largestString == null) {
/*     */         
/* 118 */         int totalWidth = 0;
/* 119 */         int oneCharWidth = 0;
/* 120 */         pos = fromIndex;
/* 121 */         while (pos < text.length()) {
/*     */           
/* 123 */           oneCharWidth = fm.charWidth(text.charAt(pos));
/* 124 */           if (totalWidth + oneCharWidth >= width && pos != fromIndex)
/*     */             break; 
/* 126 */           totalWidth += oneCharWidth;
/* 127 */           pos++;
/*     */         } 
/* 129 */         if (fromIndex == pos)
/*     */           break; 
/* 131 */         lines.add(text.substring(fromIndex, pos));
/* 132 */         fromIndex = pos;
/*     */         
/*     */         continue;
/*     */       } 
/* 136 */       lines.add(largestString);
/* 137 */       fromIndex = bestpos;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 142 */     return lines;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 147 */     return this.m_Size;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 152 */     StringBuilder buffer = new StringBuilder();
/* 153 */     if (this.m_Lines != null)
/* 154 */       for (int i = 0; i < this.m_Lines.size(); i++) {
/*     */         
/* 156 */         String s = this.m_Lines.get(i);
/* 157 */         buffer.append(s);
/* 158 */         buffer.append("\n");
/*     */       }  
/* 160 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLine(int index) {
/* 165 */     if (this.m_Lines != null && index >= 0 && index < this.m_Lines.size())
/* 166 */       return this.m_Lines.get(index); 
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLineCount() {
/* 172 */     return (this.m_Lines != null) ? this.m_Lines.size() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getLineWidths() {
/* 177 */     return this.m_LineWidths;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsWrappedText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */