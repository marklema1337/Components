/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.SwingConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControlPaint
/*     */   implements SwingConstants
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int Border3DStyle_Flat = 1;
/*     */   public static final int Border3DSide_All = 1;
/*     */   
/*     */   public static Dimension measureStringSize(JComponent owner, Graphics2D g, String s, Font font) {
/*  34 */     return measureStringSize(owner, g, s, font, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Dimension measureStringSize(JComponent owner, Graphics2D g, String s, Font font, boolean bFlipped) {
/*  39 */     return JLbsFlipTransform.measureStringSize(owner, g, s, font, bFlipped);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Shape getInnerClip(Shape currentClip, Rectangle newClip) {
/*  44 */     return JLbsFlipTransform.getInnerClip(currentClip, newClip);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawString(Graphics2D g, String s, Color color, Font font, Rectangle textRect, int horizontalAlign, int verticalAlign, boolean clip) {
/*  50 */     if (s == null || s.equals("")) {
/*     */       return;
/*     */     }
/*  53 */     AffineTransform oldTrans = g.getTransform();
/*     */     
/*  55 */     boolean flip = JLbsFlipTransform.isFlipTransform(oldTrans);
/*     */     
/*  57 */     g.setFont(font);
/*  58 */     Dimension size = measureStringSize(null, g, s, font, flip);
/*  59 */     Shape clipRect = g.getClip();
/*     */     
/*  61 */     int y = textRect.y;
/*  62 */     switch (verticalAlign) {
/*     */       
/*     */       case 1:
/*  65 */         y = textRect.y + size.height + 1;
/*     */         break;
/*     */       case 3:
/*  68 */         y = textRect.y + textRect.height - 1;
/*     */         break;
/*     */       
/*     */       default:
/*  72 */         y = textRect.y + (textRect.height + size.height) / 2 - 2;
/*     */         break;
/*     */     } 
/*     */     
/*  76 */     int x = textRect.x;
/*  77 */     if (flip)
/*     */     {
/*  79 */       switch (horizontalAlign) {
/*     */         
/*     */         case 2:
/*  82 */           horizontalAlign = 4;
/*     */           break;
/*     */         case 4:
/*  85 */           horizontalAlign = 2;
/*     */           break;
/*     */       } 
/*     */     }
/*  89 */     switch (horizontalAlign) {
/*     */       
/*     */       case 2:
/*  92 */         x = flip ? (
/*  93 */           x + textRect.width - size.width / 4) : 
/*  94 */           textRect.x;
/*     */         break;
/*     */       
/*     */       case 4:
/*  98 */         x += flip ? 
/*  99 */           size.width : (
/* 100 */           Math.max(0, textRect.width - size.width) - 1);
/*     */         break;
/*     */       
/*     */       default:
/* 104 */         x += flip ? (
/* 105 */           textRect.width - (textRect.width - size.width) / 2) : ((
/* 106 */           textRect.width - size.width) / 2 - 2);
/*     */         break;
/*     */     } 
/* 109 */     if (clip) {
/* 110 */       g.setClip(getInnerClip(clipRect, textRect));
/*     */     }
/* 112 */     g.setPaint(color);
/*     */     
/* 114 */     if (flip) {
/*     */       
/* 116 */       AffineTransform aft = AffineTransform.getScaleInstance(-1.0D, 1.0D);
/* 117 */       Font flipFont = font.deriveFont(aft);
/* 118 */       g.setFont(flipFont);
/*     */     } 
/*     */     
/* 121 */     g.drawString(s, x, y);
/* 122 */     g.setTransform(oldTrans);
/* 123 */     if (clip) {
/* 124 */       g.setClip(clipRect);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void drawReversibleLine(Graphics2D g, Point pt1, Point pt2, Color color) {
/* 129 */     Color oldColor = g.getColor();
/* 130 */     g.setColor(color);
/* 131 */     g.setXORMode(Color.BLACK);
/* 132 */     JLbsControlHelper.drawDottedLine(g, pt1.x, pt1.y, pt2.x, pt2.y);
/* 133 */     g.setColor(oldColor);
/*     */   }
/*     */ 
/*     */   
/*     */   static JDesktopPane getDesktopPane(JComponent frame) {
/* 138 */     JDesktopPane pane = null;
/* 139 */     Component c = frame.getParent();
/*     */ 
/*     */     
/* 142 */     while (pane == null) {
/*     */       
/* 144 */       if (c instanceof JDesktopPane) {
/*     */         
/* 146 */         pane = (JDesktopPane)c; continue;
/*     */       } 
/* 148 */       if (c == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 154 */       c = c.getParent();
/*     */     } 
/*     */ 
/*     */     
/* 158 */     return pane;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ControlPaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */