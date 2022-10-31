/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.UIManager;
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
/*    */ public class SkinnedBorder
/*    */   extends AbstractBorder
/*    */ {
/*    */   private Insets m_Insets;
/*    */   private SkinImage m_SkinImage;
/*    */   
/*    */   public SkinnedBorder(Insets insets, SkinImage skinImage) {
/* 29 */     this.m_Insets = insets;
/* 30 */     this.m_SkinImage = skinImage;
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c) {
/* 35 */     return this.m_Insets;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/* 44 */     int index = (c == null || c.isEnabled()) ? 0 : 1;
/* 45 */     if (c instanceof JComponent) {
/*    */       
/* 47 */       JComponent comp = (JComponent)c;
/* 48 */       Object property = comp.getClientProperty("mandatory");
/* 49 */       if (property instanceof Boolean)
/*    */       {
/* 51 */         if (comp.isEnabled() && ((Boolean)property).booleanValue())
/* 52 */           index = 2; 
/*    */       }
/*    */     } 
/* 55 */     g.setColor(UIManager.getColor("control"));
/* 56 */     g.drawRect(x, y, w, h);
/* 57 */     this.m_SkinImage.draw(g, index, w, h);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */