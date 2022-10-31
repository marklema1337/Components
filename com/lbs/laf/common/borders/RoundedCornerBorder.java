/*    */ package com.lbs.laf.common.borders;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Insets;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Area;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.RoundRectangle2D;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ 
/*    */ public class RoundedCornerBorder
/*    */   extends AbstractBorder
/*    */ {
/* 17 */   private int r = 8;
/* 18 */   private int top = 2;
/* 19 */   private int left = 5;
/* 20 */   private int bottom = 2;
/* 21 */   private int right = 5;
/*    */ 
/*    */ 
/*    */   
/*    */   public RoundedCornerBorder() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public RoundedCornerBorder(int top, int left, int bottom, int right) {
/* 30 */     this.top = top;
/* 31 */     this.left = left;
/* 32 */     this.bottom = bottom;
/* 33 */     this.right = right;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 39 */     Graphics2D g2 = (Graphics2D)g.create();
/* 40 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 41 */     RoundRectangle2D round = new RoundRectangle2D.Float(x, y, width, height, this.r, this.r);
/* 42 */     Container parent = c.getParent();
/* 43 */     if (parent != null) {
/*    */       
/* 45 */       g2.setColor(parent.getBackground());
/* 46 */       Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
/* 47 */       corner.subtract(new Area(round));
/* 48 */       g2.fill(corner);
/*    */     } 
/* 50 */     g2.dispose();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c) {
/* 56 */     return new Insets(this.top, this.left, this.bottom, this.right);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 62 */     insets.left = this.left;
/* 63 */     insets.right = this.right;
/* 64 */     insets.top = this.top;
/* 65 */     insets.bottom = this.bottom;
/* 66 */     return insets;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\borders\RoundedCornerBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */