/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.ImageObserver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkinImage
/*     */ {
/*     */   private String m_Filename;
/*     */   private Image m_Image;
/*     */   private int m_ImageCount;
/*     */   private int m_HorzSize;
/*     */   private int m_VertSize;
/*     */   private int m_ULX;
/*     */   private int m_ULY;
/*     */   private int m_LRX;
/*     */   private int m_LRY;
/*     */   private boolean m_bNoBorder = false;
/*     */   
/*     */   public SkinImage(String filename) {
/*  43 */     this.m_Filename = filename;
/*  44 */     this.m_Image = SkinImageLoader.getImage(filename);
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinImage(String filename, int nrImages, int ulX, int ulY, int lrX, int lrY) {
/*  49 */     this(filename);
/*  50 */     this.m_ImageCount = nrImages;
/*  51 */     this.m_ULX = ulX;
/*  52 */     this.m_ULY = ulY;
/*  53 */     this.m_LRX = lrX;
/*  54 */     this.m_LRY = lrY;
/*  55 */     readSizes();
/*     */   }
/*     */   
/*     */   public SkinImage(String fileName, int nrImages, int roundedSize) {
/*  59 */     this(fileName, nrImages, roundedSize, roundedSize, roundedSize, roundedSize);
/*  60 */     if (roundedSize == 0) {
/*  61 */       this.m_bNoBorder = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getFilename() {
/*  66 */     return this.m_Filename;
/*     */   }
/*     */ 
/*     */   
/*     */   public Image getImage() {
/*  71 */     return this.m_Image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawImage(Graphics g, Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver ob) {
/*     */     try {
/*  79 */       if (img != null) {
/*  80 */         g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, ob);
/*     */       }
/*  82 */     } catch (Exception e) {
/*     */       
/*  84 */       System.out.println("Draw Image exception: " + e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(Graphics g, int index, int sizeX, int sizeY) {
/*  90 */     int offset = index * getHsize();
/*  91 */     Image img = this.m_Image;
/*  92 */     if (img == null)
/*     */       return; 
/*  94 */     if (!this.m_bNoBorder) {
/*     */ 
/*     */       
/*  97 */       drawImage(g, img, 0, 0, this.m_ULX, this.m_ULY, offset + 0, 0, offset + this.m_ULX, this.m_ULY, null);
/*     */       
/*  99 */       drawImage(g, img, this.m_ULX, 0, sizeX - this.m_LRX, this.m_ULY, offset + this.m_ULX, 0, offset + this.m_HorzSize - this.m_LRX, this.m_ULY, null);
/*     */       
/* 101 */       drawImage(g, img, sizeX - this.m_LRX, 0, sizeX, this.m_ULY, offset + this.m_HorzSize - this.m_LRX, 0, offset + this.m_HorzSize, this.m_ULY, null);
/*     */       
/* 103 */       drawImage(g, img, 0, this.m_ULY, this.m_ULX, sizeY - this.m_LRY, offset + 0, this.m_ULY, offset + this.m_ULX, this.m_VertSize - this.m_LRY, null);
/*     */       
/* 105 */       drawImage(g, img, sizeX - this.m_LRX, this.m_ULY, sizeX, sizeY - this.m_LRY, offset + this.m_HorzSize - this.m_LRX, this.m_ULY, offset + this.m_HorzSize, this.m_VertSize - this.m_LRY, null);
/*     */       
/* 107 */       drawImage(g, img, 0, sizeY - this.m_LRY, this.m_ULX, sizeY, offset + 0, this.m_VertSize - this.m_LRY, offset + this.m_ULX, this.m_VertSize, null);
/*     */       
/* 109 */       drawImage(g, img, this.m_ULX, sizeY - this.m_LRY, sizeX - this.m_LRX, sizeY, offset + this.m_ULX, this.m_VertSize - this.m_LRY, offset + this.m_HorzSize - this.m_LRX, this.m_VertSize, null);
/*     */       
/* 111 */       drawImage(g, img, sizeX - this.m_LRX, sizeY - this.m_LRY, sizeX, sizeY, offset + this.m_HorzSize - this.m_LRX, this.m_VertSize - this.m_LRY, offset + this.m_HorzSize, this.m_VertSize, null);
/*     */       
/* 113 */       drawImage(g, img, this.m_ULX, this.m_ULY, sizeX - this.m_LRX, sizeY - this.m_LRY, offset + this.m_ULX, this.m_ULY, offset + this.m_HorzSize - this.m_LRX, this.m_VertSize - this.m_LRY, null);
/*     */     } else {
/*     */       
/* 116 */       drawImage(g, img, 0, 0, sizeX, sizeY, offset, 0, offset + this.m_HorzSize, this.m_VertSize, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(Graphics g, int index, int x, int y, int sizeX, int sizeY) {
/* 122 */     int offset = index * getHsize();
/* 123 */     if (!this.m_bNoBorder) {
/*     */ 
/*     */       
/* 126 */       drawImage(g, this.m_Image, x + 0, y + 0, x + this.m_ULX, y + this.m_ULY, offset + 0, 0, offset + this.m_ULX, this.m_ULY, null);
/*     */ 
/*     */       
/* 129 */       drawImage(g, this.m_Image, x + this.m_ULX, y + 0, x + sizeX - this.m_LRX, y + this.m_ULY, offset + this.m_ULX, 0, offset + this.m_HorzSize - this.m_LRX, this.m_ULY, null);
/*     */ 
/*     */       
/* 132 */       drawImage(g, this.m_Image, x + sizeX - this.m_LRX, y + 0, x + sizeX, y + this.m_ULY, offset + this.m_HorzSize - this.m_LRX, 0, offset + this.m_HorzSize, this.m_ULY, null);
/*     */ 
/*     */       
/* 135 */       drawImage(g, this.m_Image, x + 0, y + this.m_ULY, x + this.m_ULX, y + sizeY - this.m_LRY, offset + 0, this.m_ULY, offset + this.m_ULX, this.m_VertSize - this.m_LRY, null);
/*     */ 
/*     */       
/* 138 */       drawImage(g, this.m_Image, x + sizeX - this.m_LRX, y + this.m_ULY, x + sizeX, y + sizeY - this.m_LRY, offset + this.m_HorzSize - this.m_LRX, this.m_ULY, offset + this.m_HorzSize, this.m_VertSize - this.m_LRY, null);
/*     */ 
/*     */       
/* 141 */       drawImage(g, this.m_Image, x + 0, y + sizeY - this.m_LRY, x + this.m_ULX, y + sizeY, offset + 0, this.m_VertSize - this.m_LRY, offset + this.m_ULX, this.m_VertSize, null);
/*     */ 
/*     */       
/* 144 */       drawImage(g, this.m_Image, x + this.m_ULX, y + sizeY - this.m_LRY, x + sizeX - this.m_LRX, y + sizeY, offset + this.m_ULX, this.m_VertSize - this.m_LRY, offset + this.m_HorzSize - this.m_LRX, this.m_VertSize, null);
/*     */ 
/*     */       
/* 147 */       drawImage(g, this.m_Image, x + sizeX - this.m_LRX, y + sizeY - this.m_LRY, x + sizeX, y + sizeY, offset + this.m_HorzSize - this.m_LRX, this.m_VertSize - this.m_LRY, offset + this.m_HorzSize, this.m_VertSize, null);
/* 148 */       drawImage(g, this.m_Image, x + this.m_ULX, y + this.m_ULY, x + sizeX - this.m_LRX, y + sizeY - this.m_LRY, offset + this.m_ULX, this.m_ULY, offset + this.m_HorzSize - this.m_LRX, this.m_VertSize - this.m_LRY, null);
/*     */     } else {
/*     */       
/* 151 */       drawImage(g, this.m_Image, x, y, x + sizeX, y + sizeY, offset, 0, offset + this.m_HorzSize, this.m_VertSize, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCentered(Graphics g, int index, int sizeX, int sizeY) {
/* 163 */     int offset = index * getHsize();
/*     */     
/* 165 */     int w = getHsize();
/* 166 */     int h = getVsize();
/*     */     
/* 168 */     int sx = (sizeX - w) / 2;
/* 169 */     int sy = (sizeY - h) / 2;
/*     */     
/* 171 */     drawImage(g, this.m_Image, sx, sy, sx + w, sy + h, offset, 0, offset + w, h, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCentered(Graphics g, int index, int x, int y, int sizeX, int sizeY) {
/* 187 */     int offset = index * getHsize();
/*     */     
/* 189 */     int w = getHsize();
/* 190 */     int h = getVsize();
/*     */     
/* 192 */     int sx = (sizeX - w) / 2;
/* 193 */     int sy = (sizeY - h) / 2;
/*     */     
/* 195 */     drawImage(g, this.m_Image, x + sx, y + sy, x + sx + w, y + sy + h, offset, 0, offset + w, h, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHsize() {
/* 205 */     return this.m_HorzSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVsize() {
/* 214 */     return this.m_VertSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 223 */     return new Dimension(this.m_HorzSize, this.m_VertSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readSizes() {
/* 231 */     Image img = this.m_Image;
/* 232 */     if (img != null) {
/*     */       
/* 234 */       this.m_HorzSize = this.m_Image.getWidth(null) / this.m_ImageCount;
/* 235 */       this.m_VertSize = this.m_Image.getHeight(null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */