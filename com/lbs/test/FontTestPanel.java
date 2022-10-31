/*     */ package com.lbs.test;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextLayout;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontTestPanel
/*     */   extends JPanel
/*     */ {
/*     */   private String m_Text;
/*     */   
/*     */   public FontTestPanel() {
/*  21 */     setBackground(new Color(240, 240, 255));
/*  22 */     setPreferredSize(new Dimension(100, 100));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBaseLine(Graphics g, Font font, Rectangle textRect, String text) {
/*  27 */     Graphics2D g2 = (Graphics2D)g;
/*  28 */     g2.setFont(font);
/*     */     
/*  30 */     FontRenderContext fontCtx = g2.getFontRenderContext();
/*  31 */     TextLayout tl = new TextLayout(text, font, fontCtx);
/*     */     
/*  33 */     int baseLine = (int)(textRect.y - tl.getDescent() + (textRect.height + tl.getAscent()) / 2.0F);
/*  34 */     return baseLine;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawBaseLineCenteredText(Graphics g, Font font, Rectangle textRect, String text, int horizontalAlignment) {
/*  39 */     Graphics2D g2 = (Graphics2D)g;
/*  40 */     g2.setFont(font);
/*     */     
/*  42 */     FontRenderContext fontCtx = g2.getFontRenderContext();
/*  43 */     TextLayout tl = new TextLayout(text, font, fontCtx);
/*     */ 
/*     */     
/*  46 */     int y = (int)(textRect.y - tl.getDescent() + (textRect.height + tl.getAscent()) / 2.0F);
/*     */     
/*  48 */     int x = textRect.x;
/*  49 */     switch (horizontalAlignment) {
/*     */       
/*     */       case 0:
/*  52 */         x = textRect.x + (int)((textRect.width - tl.getAdvance()) / 2.0F);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/*     */       case 11:
/*  60 */         x = textRect.x + (int)(textRect.width - tl.getAdvance());
/*     */         break;
/*     */     } 
/*     */     
/*  64 */     tl.draw(g2, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  69 */     super.paintComponent(g);
/*  70 */     if (this.m_Text != null && this.m_Text.length() > 0) {
/*     */       
/*  72 */       Graphics2D g2 = (Graphics2D)g;
/*  73 */       g2.setFont(getFont());
/*     */       
/*  75 */       g2.setColor(Color.red);
/*  76 */       g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
/*  77 */       g2.setColor(Color.red);
/*  78 */       g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       Dimension theSize = getSize();
/*  90 */       Rectangle textRect = new Rectangle(theSize.width / 2 - 100, theSize.height / 2 - 30, 200, 60);
/*  91 */       g2.setColor(Color.green);
/*  92 */       g2.drawRect(textRect.x, textRect.y, textRect.width, textRect.height);
/*     */       
/*  94 */       g2.setColor(Color.black);
/*  95 */       drawBaseLineCenteredText(g, getFont(), textRect, this.m_Text, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 101 */     return this.m_Text;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 106 */     this.m_Text = text;
/* 107 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 112 */     super.setFont(font);
/* 113 */     repaint();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\test\FontTestPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */