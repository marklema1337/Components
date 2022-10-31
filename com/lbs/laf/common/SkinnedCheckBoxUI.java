/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.plaf.metal.MetalCheckBoxUI;
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
/*    */ public abstract class SkinnedCheckBoxUI
/*    */   extends MetalCheckBoxUI
/*    */ {
/*    */   public void installDefaults(AbstractButton button) {
/* 27 */     super.installDefaults(button);
/* 28 */     this.icon = getIcon();
/* 29 */     button.setRolloverEnabled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintFocus(Graphics g, Rectangle t, Dimension arg2) {
/* 34 */     Graphics2D g2d = (Graphics2D)g;
/* 35 */     g2d.setColor(Color.black);
/* 36 */     g2d.setStroke(SkinnedButtonUI.ms_FocusStroke);
/* 37 */     g2d.drawLine(t.x - 1, t.y - 1, t.x - 1 + t.width + 1, t.y - 1);
/* 38 */     g2d.drawLine(t.x - 1, t.y - 1 + t.height + 1, t.x - 1 + t.width + 1, t.y - 1 + t.height + 1);
/* 39 */     g2d.drawLine(t.x - 1, t.y - 1, t.x - 1, t.y - 1 + t.height + 1);
/* 40 */     g2d.drawLine(t.x - 1 + t.width + 1, t.y - 1, t.x - 1 + t.width + 1, t.y - 1 + t.height + 1);
/*    */   }
/*    */   
/*    */   public abstract SkinnedCheckBoxIcon getIcon();
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedCheckBoxUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */