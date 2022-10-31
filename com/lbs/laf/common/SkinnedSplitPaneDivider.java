/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.Color;
/*    */ import java.awt.GradientPaint;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.BufferedImage;
/*    */ import javax.swing.plaf.basic.BasicSplitPaneDivider;
/*    */ import javax.swing.plaf.basic.BasicSplitPaneUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkinnedSplitPaneDivider
/*    */   extends BasicSplitPaneDivider
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final int DRAG_BUMP_DIAMETER = 2;
/*    */   
/*    */   public SkinnedSplitPaneDivider(BasicSplitPaneUI ui) {
/* 23 */     super(ui);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g) {
/* 28 */     if (this.splitPane.getOrientation() == 0) {
/*    */       
/* 30 */       int bumpWidth = Math.max(20, getWidth() / 10);
/* 31 */       BufferedImage bumps = getDragImage(false, bumpWidth, 6, false);
/* 32 */       g.drawImage(bumps, (getWidth() - bumps.getWidth()) / 2, (getHeight() - bumps.getHeight()) / 2, null);
/*    */     }
/*    */     else {
/*    */       
/* 36 */       int bumpHeight = Math.max(20, getHeight() / 10);
/* 37 */       BufferedImage bumps = getDragImage(false, 6, bumpHeight, false);
/* 38 */       g.drawImage(bumps, (getWidth() - bumps.getWidth()) / 2 - 1, (getHeight() - bumps.getHeight()) / 2, null);
/*    */     } 
/*    */     
/* 41 */     super.paint(g);
/*    */   }
/*    */ 
/*    */   
/*    */   public static BufferedImage getDragImage(boolean alwaysUseActive, int width, int height, boolean isCrowded) {
/* 46 */     BufferedImage result = getBlankImage(width, height);
/* 47 */     Graphics2D graphics = (Graphics2D)result.getGraphics();
/*    */     
/* 49 */     graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*    */     
/* 51 */     Color back1 = new Color(176, 185, 194);
/* 52 */     Color back2 = new Color(120, 125, 130);
/* 53 */     Color fore = new Color(250, 252, 255);
/*    */     
/* 55 */     int bumpCellSize = 4;
/* 56 */     if (isCrowded)
/* 57 */       bumpCellSize--; 
/* 58 */     int bumpRows = height / bumpCellSize;
/* 59 */     int bumpColumns = (width - 2) / bumpCellSize;
/*    */     
/* 61 */     int bumpRowOffset = (height - bumpCellSize * bumpRows) / 2;
/* 62 */     int bumpColOffset = 1 + (width - bumpCellSize * bumpColumns) / 2;
/*    */     
/* 64 */     for (int col = 0; col < bumpColumns; col++) {
/*    */       
/* 66 */       int cx = bumpColOffset + col * bumpCellSize;
/* 67 */       boolean isEvenCol = (col % 2 == 0);
/* 68 */       int offsetY = isEvenCol ? 
/* 69 */         0 : 
/* 70 */         2;
/* 71 */       for (int row = 0; row < bumpRows; row++) {
/*    */         
/* 73 */         int cy = offsetY + bumpRowOffset + row * bumpCellSize;
/* 74 */         graphics.setColor(fore);
/* 75 */         graphics.fillOval(cx + 1, cy + 1, 2, 2);
/*    */         
/* 77 */         graphics
/* 78 */           .setPaint(new GradientPaint(cx, cy, back1, (cx + 2 - 1), (cy + 2 - 1), back2));
/* 79 */         graphics.fillOval(cx, cy, 2, 2);
/*    */       } 
/*    */     } 
/* 82 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public static BufferedImage getBlankImage(int width, int height) {
/* 87 */     BufferedImage image = new BufferedImage(width, height, 2);
/*    */ 
/*    */     
/* 90 */     Graphics2D graphics = (Graphics2D)image.getGraphics().create();
/* 91 */     graphics.setColor(new Color(0, 0, 0, 0));
/* 92 */     graphics.setComposite(AlphaComposite.Src);
/* 93 */     graphics.fillRect(0, 0, width, height);
/* 94 */     graphics.dispose();
/*    */     
/* 96 */     return image;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedSplitPaneDivider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */