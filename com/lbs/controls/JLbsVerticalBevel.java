/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.SwingConstants;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsVerticalBevel
/*    */   extends JLbsPanel
/*    */   implements SwingConstants
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_VertAlignment;
/*    */   
/*    */   public JLbsVerticalBevel() {
/* 23 */     this.m_VertAlignment = 3;
/* 24 */     setBackground(Color.WHITE);
/* 25 */     setPreferredSize(new Dimension(100, 3));
/*    */   }
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/*    */     int yoffset;
/* 30 */     super.paintComponent(g);
/* 31 */     int width = getWidth();
/*    */     
/* 33 */     switch (this.m_VertAlignment) {
/*    */       case 1:
/* 35 */         yoffset = 3; break;
/* 36 */       case 0: yoffset = getHeight() - 2; break;
/* 37 */       default: yoffset = getHeight(); break;
/*    */     } 
/* 39 */     Color c = UIManager.getColor("control");
/* 40 */     if (c == null)
/* 41 */       c = Color.GRAY; 
/* 42 */     g.setColor(c);
/* 43 */     g.drawLine(0, yoffset - 2, width, yoffset - 2);
/* 44 */     g.setColor(c.brighter());
/* 45 */     g.drawLine(0, yoffset - 1, width, yoffset - 1);
/* 46 */     g.setColor(c.darker());
/* 47 */     g.drawLine(0, yoffset, width, yoffset);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getVertAlignment() {
/* 52 */     return this.m_VertAlignment;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVertAlignment(int i) {
/* 57 */     if (this.m_VertAlignment != i) {
/*    */       
/* 59 */       this.m_VertAlignment = i;
/* 60 */       repaint();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsVerticalBevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */