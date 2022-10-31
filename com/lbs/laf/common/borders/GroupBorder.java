/*    */ package com.lbs.laf.common.borders;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ import javax.swing.border.TitledBorder;
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
/*    */ public class GroupBorder
/*    */   extends TitledBorder
/*    */ {
/*    */   public GroupBorder(AbstractBorder border) {
/* 26 */     super(border);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 31 */     Color color = UIManager.getColor(c.isEnabled() ? "Panel.CaptionColor" : "Panel.CaptionColorDisabled");
/* 32 */     if (getTitleColor() != color)
/* 33 */       setTitleColor(color); 
/* 34 */     super.paintBorder(c, g, x, y, width, height);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\borders\GroupBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */