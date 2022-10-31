/*    */ package com.lbs.laf.common.borders;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Insets;
/*    */ import java.awt.RenderingHints;
/*    */ import javax.swing.JInternalFrame;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ 
/*    */ public class FrameRoundedCornerBorder
/*    */   extends AbstractBorder
/*    */ {
/* 15 */   private int r = 16;
/* 16 */   private int top = 2;
/* 17 */   private int left = 5;
/* 18 */   private int bottom = 2;
/* 19 */   private int right = 5;
/*    */ 
/*    */   
/*    */   private Color color;
/*    */ 
/*    */   
/*    */   public FrameRoundedCornerBorder() {}
/*    */ 
/*    */   
/*    */   public FrameRoundedCornerBorder(int top, int left, int bottom, int right, Color color) {
/* 29 */     this.top = top;
/* 30 */     this.left = left;
/* 31 */     this.bottom = bottom;
/* 32 */     this.right = right;
/* 33 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   public FrameRoundedCornerBorder(int top, int left, int bottom, int right, int r, Color color) {
/* 38 */     this.top = top;
/* 39 */     this.left = left;
/* 40 */     this.bottom = bottom;
/* 41 */     this.right = right;
/* 42 */     this.r = r;
/* 43 */     this.color = color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 49 */     Graphics2D g2 = (Graphics2D)g.create();
/* 50 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 51 */     if (this.color == null && c instanceof JInternalFrame) {
/*    */       
/* 53 */       JInternalFrame frame = (JInternalFrame)c;
/* 54 */       if (frame.isSelected()) {
/* 55 */         g2.setColor(new Color(200, 200, 200));
/*    */       } else {
/* 57 */         g2.setColor(new Color(230, 230, 230));
/*    */       } 
/* 59 */     }  if (this.color != null)
/* 60 */       g2.setColor(this.color); 
/* 61 */     g2.fillRoundRect(x, y, width, height, this.r, this.r);
/* 62 */     g2.dispose();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c) {
/* 68 */     return new Insets(this.top, this.left, this.bottom, this.right);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 74 */     insets.left = this.left;
/* 75 */     insets.right = this.right;
/* 76 */     insets.top = this.top;
/* 77 */     insets.bottom = this.bottom;
/* 78 */     return insets;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\borders\FrameRoundedCornerBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */