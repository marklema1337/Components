/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import javax.swing.text.MutableAttributeSet;
/*     */ import javax.swing.text.SimpleAttributeSet;
/*     */ import javax.swing.text.StyleConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsCharProps
/*     */ {
/*     */   public static final int BOLD = 1;
/*     */   public static final int ITALIC = 2;
/*     */   public static final int UNDERLINE = 4;
/*     */   private char m_value;
/*     */   private boolean m_bold;
/*     */   private boolean m_italic;
/*     */   private boolean m_underline;
/*     */   private boolean m_left_align;
/*     */   private boolean m_center_align;
/*     */   private boolean m_right_align;
/*     */   private int m_fontSize;
/*     */   private String m_fontFamily;
/*     */   private Color m_color;
/*     */   private int m_alignment;
/*     */   private int m_style;
/*     */   private int m_offset;
/*     */   
/*     */   public JLbsCharProps(boolean bold, boolean italic, boolean underline, boolean left_align, boolean center_align, boolean right_align, String fontFamily, int fontSize) {
/*  41 */     this.m_bold = bold;
/*  42 */     this.m_italic = italic;
/*  43 */     this.m_underline = underline;
/*  44 */     this.m_left_align = left_align;
/*  45 */     this.m_center_align = center_align;
/*  46 */     this.m_right_align = right_align;
/*  47 */     this.m_fontFamily = fontFamily;
/*  48 */     this.m_fontSize = fontSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCharProps(char value, int style, int size, String fontFamily, Color color, int alignment, int offset) {
/*  53 */     this.m_value = value;
/*  54 */     this.m_style = style;
/*  55 */     this.m_fontSize = size;
/*  56 */     this.m_fontFamily = fontFamily;
/*  57 */     this.m_color = color;
/*  58 */     this.m_alignment = alignment;
/*  59 */     this.m_offset = offset;
/*  60 */     setAlignmentValues();
/*  61 */     setStyleValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public MutableAttributeSet getAttributeSet() {
/*  66 */     MutableAttributeSet mas = new SimpleAttributeSet();
/*  67 */     StyleConstants.setFontFamily(mas, this.m_fontFamily);
/*  68 */     StyleConstants.setFontSize(mas, this.m_fontSize);
/*  69 */     StyleConstants.setBold(mas, this.m_bold);
/*  70 */     StyleConstants.setItalic(mas, this.m_italic);
/*  71 */     StyleConstants.setUnderline(mas, this.m_underline);
/*  72 */     StyleConstants.setForeground(mas, this.m_color);
/*  73 */     StyleConstants.setAlignment(mas, this.m_alignment);
/*  74 */     return mas;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAlignmentValues() {
/*  79 */     this.m_left_align = false;
/*  80 */     this.m_center_align = false;
/*  81 */     this.m_right_align = false;
/*  82 */     if (this.m_alignment == 0) {
/*  83 */       this.m_left_align = true;
/*  84 */     } else if (this.m_alignment == 1) {
/*  85 */       this.m_center_align = true;
/*  86 */     } else if (this.m_alignment == 2) {
/*  87 */       this.m_right_align = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setStyleValues() {
/*  92 */     this.m_bold = false;
/*  93 */     this.m_italic = false;
/*  94 */     this.m_underline = false;
/*  95 */     int style = this.m_style & 0x1;
/*  96 */     if (style == 1)
/*  97 */       this.m_bold = true; 
/*  98 */     style = this.m_style & 0x2;
/*  99 */     if (style == 2)
/* 100 */       this.m_italic = true; 
/* 101 */     style = this.m_style & 0x4;
/* 102 */     if (style == 4) {
/* 103 */       this.m_underline = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public int getAlignment() {
/* 108 */     return this.m_alignment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignment(int alignment) {
/* 113 */     this.m_alignment = alignment;
/* 114 */     setAlignmentValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBold() {
/* 119 */     return this.m_bold;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBold(boolean bold) {
/* 124 */     this.m_bold = bold;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCenter_align() {
/* 129 */     return this.m_center_align;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCenter_align(boolean center_align) {
/* 134 */     this.m_center_align = center_align;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 139 */     return this.m_color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 144 */     this.m_color = color;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFontFamily() {
/* 149 */     return this.m_fontFamily;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFontFamily(String fontFamily) {
/* 154 */     this.m_fontFamily = fontFamily;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFontSize() {
/* 159 */     return this.m_fontSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFontSize(int fontSize) {
/* 164 */     this.m_fontSize = fontSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItalic() {
/* 169 */     return this.m_italic;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItalic(boolean italic) {
/* 174 */     this.m_italic = italic;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeft_align() {
/* 179 */     return this.m_left_align;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeft_align(boolean left_align) {
/* 184 */     this.m_left_align = left_align;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffset() {
/* 189 */     return this.m_offset;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffset(int offset) {
/* 194 */     this.m_offset = offset;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRight_align() {
/* 199 */     return this.m_right_align;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRight_align(boolean right_align) {
/* 204 */     this.m_right_align = right_align;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStyle() {
/* 209 */     return this.m_style;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStyle(int style) {
/* 214 */     this.m_style = style;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUnderline() {
/* 219 */     return this.m_underline;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnderline(boolean underline) {
/* 224 */     this.m_underline = underline;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getValue() {
/* 229 */     return this.m_value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(char value) {
/* 234 */     this.m_value = value;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsCharProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */