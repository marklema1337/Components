/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.GradientPaint;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Paint;
/*    */ import java.awt.Point;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsProgressBar
/*    */   extends JPanel
/*    */ {
/*    */   protected double m_Percent;
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 25 */     Rectangle drawRect = new Rectangle(0, 0, getWidth(), getHeight());
/* 26 */     g.setColor(isOpaque() ? getBackground() : Color.WHITE);
/* 27 */     g.fillRect(drawRect.x, drawRect.y, drawRect.width, drawRect.height);
/*    */     
/* 29 */     Color darkGray = new Color(64, 64, 64);
/* 30 */     Color lightGray = new Color(192, 192, 192);
/* 31 */     g.setColor(lightGray);
/*    */     
/* 33 */     int right = drawRect.x + drawRect.width - 1;
/* 34 */     int bottom = drawRect.y + drawRect.height - 1;
/* 35 */     g.drawLine(drawRect.x, drawRect.y, drawRect.x, bottom);
/* 36 */     g.drawLine(drawRect.x, drawRect.y, right, drawRect.y);
/* 37 */     g.setColor(Color.WHITE);
/* 38 */     g.drawLine(right, drawRect.y, right, bottom);
/* 39 */     g.drawLine(drawRect.x, bottom, right, bottom);
/* 40 */     g.setColor(darkGray);
/* 41 */     drawRect.grow(-1, -1);
/* 42 */     g.drawRect(drawRect.x, drawRect.y, drawRect.width - 1, drawRect.height - 1);
/* 43 */     drawRect.grow(-1, -1);
/* 44 */     g.setColor(lightGray);
/* 45 */     right -= 2;
/* 46 */     bottom -= 2;
/* 47 */     g.drawLine(drawRect.x, drawRect.y, drawRect.x, bottom);
/* 48 */     g.drawLine(drawRect.x, drawRect.y, right, drawRect.y);
/*    */     
/* 50 */     Point barPos = getBarPosition(drawRect.width);
/* 51 */     Rectangle fillRect = new Rectangle(drawRect.x + 1 + barPos.x, drawRect.y, barPos.y - barPos.x, drawRect.height);
/* 52 */     g.setColor(darkGray);
/* 53 */     right = fillRect.x + fillRect.width - 1;
/* 54 */     g.drawLine(fillRect.x, fillRect.y, fillRect.x, fillRect.y + fillRect.height);
/* 55 */     g.drawLine(right, fillRect.y, right, fillRect.y + fillRect.height);
/* 56 */     fillRect.grow(-1, 0);
/* 57 */     g.setColor(Color.WHITE);
/* 58 */     right = fillRect.x + fillRect.width - 1;
/* 59 */     bottom = fillRect.y + fillRect.height - 1;
/* 60 */     g.drawLine(fillRect.x, fillRect.y, fillRect.x, bottom);
/* 61 */     g.drawLine(fillRect.x, fillRect.y, right, fillRect.y);
/* 62 */     g.setColor(Color.LIGHT_GRAY);
/* 63 */     g.drawLine(right, fillRect.y, right, bottom);
/* 64 */     g.drawLine(fillRect.x, bottom, right, bottom);
/* 65 */     fillRect.grow(-1, -1);
/* 66 */     Paint p = new GradientPaint(fillRect.x, fillRect.y, Color.LIGHT_GRAY, fillRect.x, (fillRect.y + fillRect.height), Color.WHITE);
/* 67 */     Graphics2D g2 = (Graphics2D)g;
/* 68 */     Paint oldP = g2.getPaint();
/* 69 */     g2.setPaint(p);
/* 70 */     g2.fillRect(fillRect.x, fillRect.y, fillRect.width - 1, fillRect.height - 1);
/* 71 */     g2.setPaint(oldP);
/*    */   }
/*    */ 
/*    */   
/*    */   public void reshape(int x, int y, int w, int h) {
/* 76 */     super.reshape(x, y, w, h);
/* 77 */     repaint();
/*    */   }
/*    */ 
/*    */   
/*    */   protected Point getBarPosition(int width) {
/* 82 */     return new Point(0, (int)(width * this.m_Percent / 100.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   protected double getPercent() {
/* 87 */     return this.m_Percent;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setPercent(double value) {
/* 92 */     value = Math.min(100.0D, Math.max(0.0D, value));
/* 93 */     if (Math.abs(value - this.m_Percent) >= 1.0E-7D) {
/*    */       
/* 95 */       this.m_Percent = value;
/* 96 */       invalidate();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsProgressBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */