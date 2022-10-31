/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.JComponent;
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
/*     */ public class InfiniteProgressPanel
/*     */   extends JComponent
/*     */   implements MouseListener
/*     */ {
/*  32 */   protected Area[] ticker = null;
/*  33 */   protected Thread animation = null;
/*     */   protected boolean started = false;
/*  35 */   protected int alphaLevel = 0;
/*  36 */   protected int rampDelay = 300;
/*  37 */   protected float shield = 0.7F;
/*  38 */   protected String text = "";
/*  39 */   protected int barsCount = 14;
/*  40 */   protected float fps = 15.0F;
/*     */   
/*  42 */   protected RenderingHints hints = null;
/*     */ 
/*     */   
/*     */   public InfiniteProgressPanel() {
/*  46 */     this("");
/*     */   }
/*     */ 
/*     */   
/*     */   public InfiniteProgressPanel(String text) {
/*  51 */     this(text, 14);
/*     */   }
/*     */ 
/*     */   
/*     */   public InfiniteProgressPanel(String text, int barsCount) {
/*  56 */     this(text, barsCount, 0.7F);
/*     */   }
/*     */ 
/*     */   
/*     */   public InfiniteProgressPanel(String text, int barsCount, float shield) {
/*  61 */     this(text, barsCount, shield, 15.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public InfiniteProgressPanel(String text, int barsCount, float shield, float fps) {
/*  66 */     this(text, barsCount, shield, fps, 300);
/*     */   }
/*     */ 
/*     */   
/*     */   public InfiniteProgressPanel(String text, int barsCount, float shield, float fps, int rampDelay) {
/*  71 */     setDoubleBuffered(true);
/*  72 */     this.text = text;
/*  73 */     this.rampDelay = (rampDelay >= 0) ? rampDelay : 0;
/*  74 */     this.shield = (shield >= 0.0F) ? shield : 0.0F;
/*  75 */     this.fps = (fps > 0.0F) ? fps : 15.0F;
/*  76 */     this.barsCount = (barsCount > 0) ? barsCount : 14;
/*     */     
/*  78 */     this.hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*  79 */     this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  80 */     this.hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/*  85 */     repaint();
/*  86 */     this.text = text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  91 */     return this.text;
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/*  96 */     addMouseListener(this);
/*  97 */     setVisible(true);
/*  98 */     this.ticker = buildTicker();
/*  99 */     this.animation = new Thread(new Animator(true));
/* 100 */     this.animation.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void stop() {
/* 105 */     if (this.animation != null) {
/* 106 */       this.animation.interrupt();
/* 107 */       this.animation = null;
/* 108 */       this.animation = new Thread(new Animator(false));
/* 109 */       this.animation.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void interrupt() {
/* 115 */     if (this.animation != null) {
/* 116 */       this.animation.interrupt();
/* 117 */       this.animation = null;
/*     */       
/* 119 */       removeMouseListener(this);
/* 120 */       setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 126 */     if (this.started) {
/*     */       
/* 128 */       int width = getWidth();
/* 129 */       int height = getHeight();
/*     */       
/* 131 */       double maxY = 0.0D;
/*     */       
/* 133 */       Graphics2D g2 = (Graphics2D)g;
/* 134 */       g2.setRenderingHints(this.hints);
/*     */       
/* 136 */       g2.setColor(new Color(255, 255, 255, (int)(this.alphaLevel * this.shield)));
/* 137 */       g2.fillRect(0, 0, getWidth(), getHeight());
/*     */       
/* 139 */       for (int i = 0; i < this.ticker.length; i++) {
/*     */         
/* 141 */         int channel = 224 - 128 / (i + 1);
/* 142 */         g2.setColor(new Color(channel, channel, channel, this.alphaLevel));
/* 143 */         g2.fill(this.ticker[i]);
/*     */         
/* 145 */         Rectangle2D bounds = this.ticker[i].getBounds2D();
/* 146 */         if (bounds.getMaxY() > maxY) {
/* 147 */           maxY = bounds.getMaxY();
/*     */         }
/*     */       } 
/* 150 */       if (this.text != null && this.text.length() > 0) {
/*     */         
/* 152 */         FontRenderContext context = g2.getFontRenderContext();
/* 153 */         TextLayout layout = new TextLayout(this.text, getFont(), context);
/* 154 */         Rectangle2D bounds = layout.getBounds();
/* 155 */         g2.setColor(getForeground());
/* 156 */         layout.draw(g2, (float)(width - bounds.getWidth()) / 2.0F, 
/* 157 */             (float)(maxY + layout.getLeading() + (2.0F * layout.getAscent())));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Area[] buildTicker() {
/* 164 */     Area[] ticker = new Area[this.barsCount];
/* 165 */     Point2D.Double center = new Point2D.Double(getWidth() / 2.0D, getHeight() / 2.0D);
/* 166 */     double fixedAngle = 6.283185307179586D / this.barsCount;
/*     */     
/* 168 */     for (double i = 0.0D; i < this.barsCount; i++) {
/*     */       
/* 170 */       Area primitive = buildPrimitive();
/*     */       
/* 172 */       AffineTransform toCenter = AffineTransform.getTranslateInstance(center.getX(), center.getY());
/* 173 */       AffineTransform toBorder = AffineTransform.getTranslateInstance(45.0D, -6.0D);
/* 174 */       AffineTransform toCircle = AffineTransform.getRotateInstance(-i * fixedAngle, center.getX(), center.getY());
/*     */       
/* 176 */       AffineTransform toWheel = new AffineTransform();
/* 177 */       toWheel.concatenate(toCenter);
/* 178 */       toWheel.concatenate(toBorder);
/*     */       
/* 180 */       primitive.transform(toWheel);
/* 181 */       primitive.transform(toCircle);
/*     */       
/* 183 */       ticker[(int)i] = primitive;
/*     */     } 
/*     */     
/* 186 */     return ticker;
/*     */   }
/*     */ 
/*     */   
/*     */   private Area buildPrimitive() {
/* 191 */     Rectangle2D.Double body = new Rectangle2D.Double(6.0D, 0.0D, 30.0D, 12.0D);
/* 192 */     Ellipse2D.Double head = new Ellipse2D.Double(0.0D, 0.0D, 12.0D, 12.0D);
/* 193 */     Ellipse2D.Double tail = new Ellipse2D.Double(30.0D, 0.0D, 12.0D, 12.0D);
/*     */     
/* 195 */     Area tick = new Area(body);
/* 196 */     tick.add(new Area(head));
/* 197 */     tick.add(new Area(tail));
/*     */     
/* 199 */     return tick;
/*     */   }
/*     */   
/*     */   protected class Animator
/*     */     implements Runnable
/*     */   {
/*     */     private boolean rampUp = true;
/*     */     
/*     */     protected Animator(boolean rampUp) {
/* 208 */       this.rampUp = rampUp;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 213 */       Point2D.Double center = new Point2D.Double(InfiniteProgressPanel.this.getWidth() / 2.0D, InfiniteProgressPanel.this.getHeight() / 2.0D);
/*     */       
/* 215 */       double fixedIncrement = 6.283185307179586D / InfiniteProgressPanel.this.barsCount;
/* 216 */       AffineTransform toCircle = AffineTransform.getRotateInstance(fixedIncrement, center.getX(), center.getY());
/*     */       
/* 218 */       long start = System.currentTimeMillis();
/* 219 */       if (InfiniteProgressPanel.this.rampDelay == 0) {
/* 220 */         InfiniteProgressPanel.this.alphaLevel = this.rampUp ? 255 : 0;
/*     */       }
/* 222 */       InfiniteProgressPanel.this.started = true;
/* 223 */       boolean inRamp = this.rampUp;
/*     */       
/* 225 */       while (!Thread.interrupted()) {
/*     */         
/* 227 */         if (!inRamp)
/*     */         {
/* 229 */           for (int i = 0; i < InfiniteProgressPanel.this.ticker.length; i++) {
/* 230 */             InfiniteProgressPanel.this.ticker[i].transform(toCircle);
/*     */           }
/*     */         }
/* 233 */         InfiniteProgressPanel.this.repaint();
/*     */         
/* 235 */         if (this.rampUp) {
/*     */           
/* 237 */           if (InfiniteProgressPanel.this.alphaLevel < 255) {
/*     */             
/* 239 */             InfiniteProgressPanel.this.alphaLevel = (int)(255L * (System.currentTimeMillis() - start) / InfiniteProgressPanel.this.rampDelay);
/* 240 */             if (InfiniteProgressPanel.this.alphaLevel >= 255) {
/*     */               
/* 242 */               InfiniteProgressPanel.this.alphaLevel = 255;
/* 243 */               inRamp = false;
/*     */             } 
/*     */           } 
/* 246 */         } else if (InfiniteProgressPanel.this.alphaLevel > 0) {
/* 247 */           InfiniteProgressPanel.this.alphaLevel = (int)(255L - 255L * (System.currentTimeMillis() - start) / InfiniteProgressPanel.this.rampDelay);
/* 248 */           if (InfiniteProgressPanel.this.alphaLevel <= 0) {
/*     */             
/* 250 */             InfiniteProgressPanel.this.alphaLevel = 0;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/*     */         try {
/* 257 */           Thread.sleep((inRamp ? 10L : (int)(1000.0F / InfiniteProgressPanel.this.fps)));
/* 258 */         } catch (InterruptedException ie) {
/*     */           break;
/*     */         } 
/* 261 */         Thread.yield();
/*     */       } 
/*     */       
/* 264 */       if (!this.rampUp) {
/*     */         
/* 266 */         InfiniteProgressPanel.this.started = false;
/* 267 */         InfiniteProgressPanel.this.repaint();
/*     */         
/* 269 */         InfiniteProgressPanel.this.setVisible(false);
/* 270 */         InfiniteProgressPanel.this.removeMouseListener(InfiniteProgressPanel.this);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\InfiniteProgressPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */