/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkinnedMenuItem
/*     */   extends SkinImage
/*     */ {
/*     */   private int m_LeftOffset;
/*     */   private int m_LeftRolloverOffset;
/*     */   private int m_RightOffset;
/*     */   private int m_RightRolloverOffset;
/*     */   private int m_HSize;
/*     */   private int m_VSize;
/*     */   private int m_RoundedSize;
/*     */   private boolean m_DoneAllCalculations = false;
/*     */   
/*     */   public SkinnedMenuItem(String fileName, int leftOffset, int leftRolloverOffset, int rightOffset, int rightRolloverOffset, int roundedSize) {
/*  27 */     super(fileName);
/*  28 */     this.m_LeftOffset = leftOffset;
/*  29 */     this.m_LeftRolloverOffset = leftRolloverOffset;
/*  30 */     this.m_RightOffset = rightOffset;
/*  31 */     this.m_RightRolloverOffset = rightRolloverOffset;
/*  32 */     this.m_RoundedSize = roundedSize;
/*  33 */     calculateSizes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Graphics g, boolean isEnabled, boolean isSelected, boolean isPushed, boolean isRollover, int pSizeX, int leftSize, int sizeY, boolean isLeftToRight) {
/*  42 */     Image image = getImage();
/*  43 */     if (image == null) {
/*     */       return;
/*     */     }
/*  46 */     int offsetL = 0, offsetR = 0;
/*     */     
/*  48 */     if (isSelected) {
/*     */       
/*  50 */       offsetL = this.m_LeftRolloverOffset;
/*  51 */       offsetR = this.m_RightRolloverOffset;
/*     */     }
/*     */     else {
/*     */       
/*  55 */       offsetL = this.m_LeftOffset;
/*  56 */       offsetR = this.m_RightOffset;
/*     */     } 
/*     */     
/*  59 */     offsetL = this.m_HSize * offsetL;
/*  60 */     offsetR = this.m_HSize * offsetR;
/*     */     
/*  62 */     int sizeL = leftSize;
/*  63 */     int sizeR = pSizeX - leftSize;
/*     */ 
/*     */ 
/*     */     
/*  67 */     int offset = offsetL;
/*  68 */     int dOffset = (isLeftToRight || isSelected) ? 
/*  69 */       0 : (
/*  70 */       pSizeX - leftSize);
/*  71 */     g.translate(dOffset, 0);
/*     */     
/*  73 */     if (this.m_RoundedSize > 0) {
/*     */ 
/*     */       
/*  76 */       g.drawImage(image, 0, 0, this.m_RoundedSize, this.m_RoundedSize, offset + 0, 0, offset + this.m_RoundedSize, this.m_RoundedSize, null);
/*     */ 
/*     */       
/*  79 */       g.drawImage(image, this.m_RoundedSize, 0, sizeL - this.m_RoundedSize, this.m_RoundedSize, offset + this.m_RoundedSize, 0, offset + 
/*  80 */           this.m_HSize - this.m_RoundedSize, this.m_RoundedSize, null);
/*     */ 
/*     */       
/*  83 */       g.drawImage(image, sizeL - this.m_RoundedSize, 0, sizeL, this.m_RoundedSize, offset + this.m_HSize - this.m_RoundedSize, 0, offset + 
/*  84 */           this.m_HSize, this.m_RoundedSize, null);
/*     */ 
/*     */       
/*  87 */       g.drawImage(image, 0, this.m_RoundedSize, this.m_RoundedSize, sizeY - this.m_RoundedSize, offset + 0, this.m_RoundedSize, offset + 
/*  88 */           this.m_RoundedSize, this.m_VSize - this.m_RoundedSize, null);
/*     */ 
/*     */       
/*  91 */       g.drawImage(image, sizeL - this.m_RoundedSize, this.m_RoundedSize, sizeL, sizeY - this.m_RoundedSize, offset + this.m_HSize - 
/*  92 */           this.m_RoundedSize, this.m_RoundedSize, offset + this.m_HSize, this.m_VSize - this.m_RoundedSize, null);
/*     */ 
/*     */       
/*  95 */       g.drawImage(image, 0, sizeY - this.m_RoundedSize, this.m_RoundedSize, sizeY, offset + 0, this.m_VSize - this.m_RoundedSize, offset + 
/*  96 */           this.m_RoundedSize, this.m_VSize, null);
/*     */ 
/*     */       
/*  99 */       g.drawImage(image, this.m_RoundedSize, sizeY - this.m_RoundedSize, sizeL - this.m_RoundedSize, sizeY, offset + this.m_RoundedSize, 
/* 100 */           this.m_VSize - this.m_RoundedSize, offset + this.m_HSize - this.m_RoundedSize, this.m_VSize, null);
/*     */ 
/*     */       
/* 103 */       g.drawImage(image, sizeL - this.m_RoundedSize, sizeY - this.m_RoundedSize, sizeL, sizeY, offset + this.m_HSize - this.m_RoundedSize, 
/* 104 */           this.m_VSize - this.m_RoundedSize, offset + this.m_HSize, this.m_VSize, null);
/*     */     } 
/*     */     
/* 107 */     g.drawImage(image, this.m_RoundedSize, this.m_RoundedSize, sizeL - this.m_RoundedSize, sizeY - this.m_RoundedSize, offset + this.m_RoundedSize, 
/* 108 */         this.m_RoundedSize, offset + this.m_HSize - this.m_RoundedSize, this.m_VSize - this.m_RoundedSize, null);
/*     */     
/* 110 */     g.translate(-dOffset, 0);
/*     */ 
/*     */ 
/*     */     
/* 114 */     offset = offsetR;
/*     */     
/* 116 */     dOffset = (isLeftToRight || isSelected) ? leftSize : 0;
/* 117 */     g.translate(dOffset, 0);
/* 118 */     if (this.m_RoundedSize > 0) {
/*     */ 
/*     */       
/* 121 */       g.drawImage(image, 0, 0, this.m_RoundedSize, this.m_RoundedSize, offset + 0, 0, offset + this.m_RoundedSize, this.m_RoundedSize, null);
/*     */ 
/*     */       
/* 124 */       g.drawImage(image, this.m_RoundedSize, 0, sizeR - this.m_RoundedSize, this.m_RoundedSize, offset + this.m_RoundedSize, 0, offset + 
/* 125 */           this.m_HSize - this.m_RoundedSize, this.m_RoundedSize, null);
/*     */ 
/*     */       
/* 128 */       g.drawImage(image, sizeR - this.m_RoundedSize, 0, sizeR, this.m_RoundedSize, offset + this.m_HSize - this.m_RoundedSize, 0, offset + 
/* 129 */           this.m_HSize, this.m_RoundedSize, null);
/*     */ 
/*     */       
/* 132 */       g.drawImage(image, 0, this.m_RoundedSize, this.m_RoundedSize, sizeY - this.m_RoundedSize, offset + 0, this.m_RoundedSize, offset + 
/* 133 */           this.m_RoundedSize, this.m_VSize - this.m_RoundedSize, null);
/*     */ 
/*     */       
/* 136 */       g.drawImage(image, sizeR - this.m_RoundedSize, this.m_RoundedSize, sizeR, sizeY - this.m_RoundedSize, offset + this.m_HSize - 
/* 137 */           this.m_RoundedSize, this.m_RoundedSize, offset + this.m_HSize, this.m_VSize - this.m_RoundedSize, null);
/*     */ 
/*     */       
/* 140 */       g.drawImage(image, 0, sizeY - this.m_RoundedSize, this.m_RoundedSize, sizeY, offset + 0, this.m_VSize - this.m_RoundedSize, offset + 
/* 141 */           this.m_RoundedSize, this.m_VSize, null);
/*     */ 
/*     */       
/* 144 */       g.drawImage(image, this.m_RoundedSize, sizeY - this.m_RoundedSize, sizeR - this.m_RoundedSize, sizeY, offset + this.m_RoundedSize, 
/* 145 */           this.m_VSize - this.m_RoundedSize, offset + this.m_HSize - this.m_RoundedSize, this.m_VSize, null);
/*     */ 
/*     */       
/* 148 */       g.drawImage(image, sizeR - this.m_RoundedSize, sizeY - this.m_RoundedSize, sizeR, sizeY, offset + this.m_HSize - this.m_RoundedSize, 
/* 149 */           this.m_VSize - this.m_RoundedSize, offset + this.m_HSize, this.m_VSize, null);
/*     */     } 
/*     */     
/* 152 */     g.drawImage(image, this.m_RoundedSize, this.m_RoundedSize, sizeR - this.m_RoundedSize, sizeY - this.m_RoundedSize, offset + this.m_RoundedSize, 
/* 153 */         this.m_RoundedSize, offset + this.m_HSize - this.m_RoundedSize, this.m_VSize - this.m_RoundedSize, null);
/*     */     
/* 155 */     g.translate(-dOffset, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHsize() {
/* 164 */     if (!this.m_DoneAllCalculations) {
/*     */       
/* 166 */       calculateSizes();
/* 167 */       this.m_DoneAllCalculations = true;
/*     */     } 
/* 169 */     return this.m_HSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVsize() {
/* 174 */     if (!this.m_DoneAllCalculations) {
/*     */       
/* 176 */       calculateSizes();
/* 177 */       this.m_DoneAllCalculations = true;
/*     */     } 
/* 179 */     return this.m_VSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calculateSizes() {
/* 184 */     int maxOffset = 0;
/* 185 */     if (this.m_LeftOffset > maxOffset)
/* 186 */       maxOffset = this.m_LeftOffset; 
/* 187 */     if (this.m_LeftRolloverOffset > maxOffset)
/* 188 */       maxOffset = this.m_LeftRolloverOffset; 
/* 189 */     if (this.m_RightOffset > maxOffset)
/* 190 */       maxOffset = this.m_RightOffset; 
/* 191 */     if (this.m_RightRolloverOffset > maxOffset) {
/* 192 */       maxOffset = this.m_RightRolloverOffset;
/*     */     }
/* 194 */     Image image = getImage();
/* 195 */     this.m_HSize = (image == null) ? 
/* 196 */       0 : (
/* 197 */       image.getWidth(null) / (maxOffset + 1));
/*     */     
/* 199 */     this.m_VSize = (image == null) ? 
/* 200 */       0 : 
/* 201 */       image.getHeight(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedMenuItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */