/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.basic.BasicMenuBarUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SkinnedMenuBarUI
/*    */   extends BasicMenuBarUI
/*    */ {
/*    */   public void paint(Graphics g, JComponent c) {
/* 19 */     int width = c.getWidth();
/* 20 */     int height = c.getHeight();
/* 21 */     getSkin().draw(g, 2, width, height - 1);
/*    */   }
/*    */   
/*    */   public abstract SkinImage getSkin();
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedMenuBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */