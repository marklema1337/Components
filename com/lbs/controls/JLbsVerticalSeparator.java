/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GradientPaint;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Paint;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsVerticalSeparator
/*    */   extends JLbsPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_RightSpace;
/*    */   
/*    */   public JLbsVerticalSeparator() {
/* 25 */     setPreferredSize(new Dimension(100, 1));
/* 26 */     this.m_RightSpace = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintComponent(Graphics g) {
/* 31 */     Graphics2D g2 = (Graphics2D)g;
/* 32 */     Color clfill = UIManager.getColor("control");
/* 33 */     Paint oldPaint = g2.getPaint();
/* 34 */     int width = getWidth() - this.m_RightSpace;
/* 35 */     g2.setPaint(new GradientPaint(0.0F, 0.0F, clfill, width, 0.0F, clfill.darker(), false));
/* 36 */     g.fillRect(0, 0, width, getHeight());
/* 37 */     g2.setPaint(oldPaint);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRightSpace() {
/* 42 */     return this.m_RightSpace;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRightSpace(int i) {
/* 47 */     this.m_RightSpace = i;
/* 48 */     repaint();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsVerticalSeparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */