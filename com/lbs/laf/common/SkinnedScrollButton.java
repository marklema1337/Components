/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.plaf.metal.MetalScrollButton;
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
/*    */ public class SkinnedScrollButton
/*    */   extends MetalScrollButton
/*    */ {
/*    */   private SkinImage m_Skin;
/* 23 */   private SkinnedButtonIndexModel m_IndexModel = new SkinnedButtonIndexModel();
/*    */ 
/*    */   
/*    */   public SkinnedScrollButton(SkinImage skin, int orientation, int width, boolean freeStanding) {
/* 27 */     super(orientation, width, freeStanding);
/* 28 */     setBorder(null);
/* 29 */     setRolloverEnabled(true);
/* 30 */     setMargin(new Insets(0, 0, 0, 0));
/* 31 */     setBorder(null);
/* 32 */     this.m_IndexModel.setButton(this);
/* 33 */     this.m_Skin = skin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g) {
/* 38 */     if (this.m_Skin != null) {
/* 39 */       this.m_Skin.draw(g, this.m_IndexModel.getIndexForState(), getWidth(), getHeight());
/*    */     } else {
/* 41 */       super.paint(g);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Dimension getPreferredSize() {
/* 46 */     if (this.m_Skin != null)
/* 47 */       return new Dimension(this.m_Skin.getHsize(), this.m_Skin.getVsize()); 
/* 48 */     return super.getPreferredSize();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedScrollButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */