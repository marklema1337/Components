/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.plaf.metal.MetalCheckBoxIcon;
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
/*    */ public class SkinnedCheckBoxIcon
/*    */   extends MetalCheckBoxIcon
/*    */ {
/* 22 */   private SkinnedButtonIndexModel m_IndexModel = new SkinnedButtonIndexModel();
/*    */   
/*    */   public SkinImage m_SkinImage;
/*    */   
/*    */   public SkinnedCheckBoxIcon(SkinImage skinImage) {
/* 27 */     this.m_SkinImage = skinImage;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getControlSize() {
/* 32 */     return this.m_SkinImage.getHsize();
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 37 */     AbstractButton button = (AbstractButton)c;
/* 38 */     this.m_IndexModel.setButton(button);
/* 39 */     int index = this.m_IndexModel.getIndexForState();
/* 40 */     this.m_SkinImage.draw(g, index, x, y, this.m_SkinImage.getHsize(), this.m_SkinImage.getVsize());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIconWidth() {
/* 45 */     return this.m_SkinImage.getHsize();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIconHeight() {
/* 50 */     return this.m_SkinImage.getVsize();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedCheckBoxIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */