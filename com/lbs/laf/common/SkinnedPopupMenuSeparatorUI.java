/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.metal.MetalSeparatorUI;
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
/*    */ 
/*    */ 
/*    */ public abstract class SkinnedPopupMenuSeparatorUI
/*    */   extends MetalSeparatorUI
/*    */ {
/*    */   public void paint(Graphics g, JComponent c) {
/* 27 */     Dimension s = c.getSize();
/* 28 */     JComponent p = (JComponent)c.getParent();
/* 29 */     Integer maxValueInt = (Integer)p.getClientProperty("maxIconWidth");
/* 30 */     int maxValue = (maxValueInt == null) ? 
/* 31 */       16 : 
/* 32 */       maxValueInt.intValue();
/*    */     
/* 34 */     g.setColor(Color.white);
/* 35 */     g.fillRect(0, 0, s.width, s.height);
/*    */     
/* 37 */     Rectangle rect = new Rectangle(0, 0, maxValue + 8, s.height);
/* 38 */     g.setColor(new Color(12632256));
/*    */     
/* 40 */     if (p.getComponentOrientation().isLeftToRight()) {
/*    */       
/* 42 */       g.fillRect(rect.x, rect.y, rect.width, rect.height);
/* 43 */       g.drawLine(rect.width + 15, 1, s.width, 1);
/*    */     }
/*    */     else {
/*    */       
/* 47 */       rect.x = s.width - rect.width;
/* 48 */       g.fillRect(rect.x, rect.y, rect.width, rect.height);
/* 49 */       g.drawLine(0, 1, s.width - rect.width + 15, 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension getPreferredSize(JComponent c) {
/* 56 */     return new Dimension(0, 4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedPopupMenuSeparatorUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */