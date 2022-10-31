/*    */ package com.lbs.controls.misc;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.border.LineBorder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsEtchedBorder
/*    */   extends LineBorder
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsEtchedBorder() {
/* 22 */     super(Color.GRAY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 27 */     Color oldColor = g.getColor();
/*    */     
/* 29 */     Color cltrColor = UIManager.getColor("control");
/* 30 */     g.setColor(cltrColor.darker()); int i;
/* 31 */     for (i = 0; i < this.thickness; i++) {
/*    */       
/* 33 */       g.drawLine(x + i, y + i, x + i + width - 1, y + i);
/* 34 */       g.drawLine(x + i, y + i, x + i, y + i + height - 1);
/*    */     } 
/* 36 */     g.setColor(cltrColor.brighter());
/* 37 */     for (i = 0; i < this.thickness; i++) {
/*    */       
/* 39 */       g.drawLine(x + i, y + i + height - 1, x + i + width - 1, y + i + height - 1);
/* 40 */       g.drawLine(x + i + width - 1, y + i, x + i + width - 1, y + i + height - 1);
/*    */     } 
/* 42 */     g.setColor(oldColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\misc\JLbsEtchedBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */