/*    */ package com.lbs.laf.common.borders;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.border.AbstractBorder;
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
/*    */ public class RoundBorder
/*    */   extends AbstractBorder
/*    */ {
/*    */   Color m_Color;
/*    */   int m_RoundX;
/*    */   int m_RoundY;
/*    */   
/*    */   public RoundBorder(Color c, int x, int y) {
/* 28 */     this.m_Color = c;
/* 29 */     this.m_RoundX = x;
/* 30 */     this.m_RoundY = y;
/*    */   }
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 34 */     Color oldColor = g.getColor();
/* 35 */     g.setColor(this.m_Color);
/* 36 */     g.drawRoundRect(x, y, width - 1, height - 1, this.m_RoundX, this.m_RoundY);
/* 37 */     g.setColor(oldColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\borders\RoundBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */