/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.metal.MetalToolBarUI;
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
/*    */ public abstract class SkinnedToolbarUI
/*    */   extends MetalToolBarUI
/*    */ {
/*    */   public void installUI(JComponent c) {
/* 24 */     super.installUI(c);
/* 25 */     c.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 30 */     if (c.getClientProperty("Empty") == Boolean.TRUE) {
/*    */       
/* 32 */       g.setColor(c.getBackground());
/* 33 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*    */     } else {
/*    */       
/* 36 */       getSkin().draw(g, 0, c.getWidth(), c.getHeight());
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void setBorderToRollover(Component c) {
/* 41 */     if (c instanceof AbstractButton) {
/*    */       
/* 43 */       AbstractButton b = (AbstractButton)c;
/*    */       
/* 45 */       b.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setBorderToNormal(Component c) {
/* 51 */     if (c instanceof AbstractButton) {
/*    */       
/* 53 */       AbstractButton b = (AbstractButton)c;
/*    */       
/* 55 */       b.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract SkinImage getSkin();
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedToolbarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */