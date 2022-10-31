/*     */ package com.lbs.start;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ConvolveOp;
/*     */ import java.awt.image.Kernel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JPanel;
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
/*     */ public class JLbsAnimationPanel
/*     */   extends JPanel
/*     */ {
/*     */   private float m_GradientFactor;
/*     */   private String m_Message;
/*     */   private Thread m_AnimatorThread;
/*     */   private BufferedImage m_ModifiedImage;
/*     */   private BufferedImage m_OriginalImage;
/*     */   private Font m_Font;
/*     */   
/*     */   public JLbsAnimationPanel(String message, ImageIcon icon) {
/*  41 */     this.m_Message = message;
/*  42 */     this.m_Font = getFont().deriveFont(16.0F);
/*     */     
/*  44 */     Image image = icon.getImage();
/*  45 */     this.m_OriginalImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), 2);
/*  46 */     this.m_ModifiedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), 2);
/*  47 */     Graphics g = this.m_OriginalImage.createGraphics();
/*  48 */     g.drawImage(image, 0, 0, this);
/*  49 */     g.dispose();
/*     */     
/*  51 */     setBrightness(1.0F);
/*  52 */     setOpaque(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/*  57 */     super.paintComponent(g);
/*     */     
/*  59 */     if (this.m_ModifiedImage != null) {
/*     */       
/*  61 */       int width = getWidth();
/*  62 */       int height = getHeight();
/*     */       
/*  64 */       synchronized (this.m_ModifiedImage) {
/*     */         
/*  66 */         Graphics2D g2 = (Graphics2D)g;
/*  67 */         g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*  68 */         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  69 */         g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
/*     */         
/*  71 */         FontRenderContext context = g2.getFontRenderContext();
/*  72 */         TextLayout layout = new TextLayout(this.m_Message, this.m_Font, context);
/*  73 */         Rectangle2D bounds = layout.getBounds();
/*     */         
/*  75 */         int x = (width - this.m_ModifiedImage.getWidth(null)) / 2;
/*  76 */         int y = (int)(height - this.m_ModifiedImage.getHeight(null) + bounds.getHeight() + layout.getAscent()) / 2;
/*     */         
/*  78 */         g2.drawImage(this.m_ModifiedImage, x, y, this);
/*  79 */         g2.setColor(new Color(0, 0, 0, (int)(this.m_GradientFactor * 255.0F)));
/*  80 */         layout.draw(g2, (float)(width - bounds.getWidth()) / 2.0F, 
/*  81 */             (float)((y + this.m_ModifiedImage.getHeight(null)) + bounds.getHeight() + layout.getAscent()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBrightness(float multiple) {
/*  90 */     float[] brightKernel = { multiple };
/*  91 */     RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*  92 */     BufferedImageOp bright = new ConvolveOp(new Kernel(1, 1, brightKernel), 1, hints);
/*  93 */     bright.filter(this.m_OriginalImage, this.m_ModifiedImage);
/*  94 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  99 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessage(String message) {
/* 104 */     this.m_Message = message;
/* 105 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setGradientFactor(float gradientFactor) {
/* 110 */     this.m_GradientFactor = gradientFactor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/* 115 */     this.m_AnimatorThread = new Thread(new HighlightHandler(), "HighlightHandler");
/* 116 */     this.m_AnimatorThread.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void stop() {
/* 121 */     if (this.m_AnimatorThread != null)
/* 122 */       this.m_AnimatorThread.interrupt(); 
/* 123 */     this.m_AnimatorThread = null;
/*     */   }
/*     */   
/*     */   class HighlightHandler
/*     */     implements Runnable {
/* 128 */     private int m_Direction = 1;
/* 129 */     private final int MIN_VALUE = 30;
/* 130 */     private final int MAX_VALUE = 55;
/* 131 */     private int m_Value = 30;
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 136 */       while (!Thread.currentThread().isInterrupted()) {
/*     */ 
/*     */         
/*     */         try {
/* 140 */           Thread.sleep(80L);
/*     */         }
/* 142 */         catch (InterruptedException e) {
/*     */           return;
/*     */         } 
/*     */ 
/*     */         
/* 147 */         this.m_Value += this.m_Direction;
/* 148 */         if (this.m_Value > 55) {
/*     */           
/* 150 */           this.m_Value = 55;
/* 151 */           this.m_Direction = -1;
/*     */         }
/* 153 */         else if (this.m_Value < 30) {
/*     */           
/* 155 */           this.m_Value = 30;
/* 156 */           this.m_Direction = 1;
/*     */         } 
/*     */         
/* 159 */         synchronized (JLbsAnimationPanel.this.m_ModifiedImage) {
/*     */           
/* 161 */           JLbsAnimationPanel.this.setBrightness(this.m_Value / 40.0F);
/* 162 */           JLbsAnimationPanel.this.setGradientFactor(this.m_Value / 55.0F);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\JLbsAnimationPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */