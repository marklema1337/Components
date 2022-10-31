/*    */ package com.lbs.controls.groupbox;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GradientPaint;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Paint;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.border.EmptyBorder;
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
/*    */ public class JLbsPaneBorder
/*    */   extends EmptyBorder
/*    */ {
/*    */   public JLbsPaneBorder() {
/* 26 */     super(3, 3, 0, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 31 */     Dimension d = new Dimension(0, 0);
/* 32 */     Graphics2D g2 = (Graphics2D)g;
/* 33 */     Paint oldPaint = g2.getPaint();
/* 34 */     Color clfill = UIManager.getColor("control");
/* 35 */     if (c.getComponentOrientation().isLeftToRight()) {
/*    */       
/* 37 */       g2.setPaint(new GradientPaint(0.0F, 0.0F, clfill.darker(), width, 0.0F, clfill, false));
/* 38 */       g.fillRect(x, y + d.height, width, 1);
/* 39 */       g2.setPaint(new GradientPaint(0.0F, (y + d.height), clfill.darker(), 0.0F, height, clfill, false));
/* 40 */       g.fillRect(x, y + d.height, x + 1, height);
/* 41 */       g2.setPaint(oldPaint);
/*    */     }
/*    */     else {
/*    */       
/* 45 */       g2.setPaint(new GradientPaint(width, 0.0F, clfill.darker(), 0.0F, 0.0F, clfill, false));
/* 46 */       g.fillRect(x, y + d.height, width, 1);
/* 47 */       g2.setPaint(new GradientPaint(0.0F, (y + d.height), clfill.darker(), 0.0F, height, clfill, false));
/* 48 */       g.fillRect(x + width - 1, y + d.height, x + 1, height);
/* 49 */       g2.setPaint(oldPaint);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsPaneBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */