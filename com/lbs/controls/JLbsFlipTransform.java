/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsFlipTransform
/*     */   extends AffineTransform
/*     */   implements SwingConstants, PropertyChangeListener, ComponentListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int BASELINE_CENTER = 20;
/*     */   public static final int BASELINE_TOP = 21;
/*     */   public static final int BASELINE_BOTTOM = 22;
/*     */   private ILbsFlipComponent m_Component;
/*     */   
/*     */   public JLbsFlipTransform(ILbsFlipComponent component) {
/*  39 */     this.m_Component = component;
/*  40 */     adjust();
/*  41 */     this.m_Component.addPropertyChangeListener("componentOrientation", this);
/*  42 */     this.m_Component.addComponentListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent evt) {
/*  47 */     adjust();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlipped() {
/*  52 */     return !this.m_Component.getComponentOrientation().isLeftToRight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void adjust() {
/*  57 */     if (isFlipped()) {
/*     */       
/*  59 */       setToTranslation((this.m_Component.getWidth() - 1), 0.0D);
/*  60 */       AffineTransform x = AffineTransform.getScaleInstance(-1.0D, 1.0D);
/*  61 */       concatenate(x);
/*     */     }
/*     */     else {
/*     */       
/*  65 */       setToIdentity();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFlipTransform(AffineTransform transform) {
/*  71 */     return (transform.getScaleX() == -1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int transposeX(int x) {
/*  76 */     if (isFlipped()) {
/*  77 */       return this.m_Component.getWidth() - x;
/*     */     }
/*  79 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public Point transposePoint(Point p) {
/*  84 */     Point pt = new Point(p);
/*  85 */     if (isFlipped())
/*  86 */       pt.x = this.m_Component.getWidth() - pt.x; 
/*  87 */     return pt;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle transposeRect(Rectangle r) {
/*  92 */     Rectangle rect = new Rectangle(r);
/*  93 */     if (isFlipped())
/*  94 */       rect.x = this.m_Component.getWidth() - rect.x - rect.width; 
/*  95 */     return rect;
/*     */   }
/*     */   
/*     */   public static Dimension measureStringSize(JComponent owner, Graphics2D g, String s, Font font, boolean bFlipped) {
/*     */     FontMetrics fm;
/* 100 */     Dimension result = new Dimension(0, 0);
/* 101 */     Graphics canvas = (g == null) ? (
/* 102 */       (owner == null) ? 
/* 103 */       null : 
/* 104 */       owner.getGraphics()) : 
/* 105 */       g;
/*     */ 
/*     */     
/* 108 */     if (canvas == null) {
/*     */       
/* 110 */       fm = FontMetricHandler.getFontMetricsFromStyleContext(font);
/*     */     }
/*     */     else {
/*     */       
/* 114 */       fm = FontMetricHandler.getFontMetricsFromStyleContext(canvas.getFont());
/*     */     } 
/*     */     
/* 117 */     if (s != null) {
/*     */       
/* 119 */       Rectangle2D strRect = fm.getStringBounds(s, canvas);
/* 120 */       if (bFlipped) {
/*     */         
/* 122 */         result.width = Math.max(1, Math.abs((int)strRect.getWidth()));
/* 123 */         result.height = Math.abs((int)strRect.getHeight());
/*     */       }
/*     */       else {
/*     */         
/* 127 */         result.width = Math.max(1, (int)strRect.getWidth());
/* 128 */         result.height = (int)strRect.getHeight();
/*     */       } 
/* 130 */       result.height = fm.getAscent() + fm.getDescent();
/*     */     } 
/* 132 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Shape getInnerClip(Shape currentClip, Rectangle newClip) {
/* 137 */     if (currentClip == null) {
/* 138 */       return newClip;
/*     */     }
/* 140 */     return currentClip.getBounds().createIntersection(newClip);
/*     */   }
/*     */   
/* 143 */   public static int VERTICAL_SHIFT = -3;
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
/*     */   public static void drawString(Graphics2D g, String text, Color color, Font font, Rectangle textRect, int horizontalAlign, int verticalAlign, boolean clip) {
/* 157 */     if (text == null || text.equals("")) {
/*     */       return;
/*     */     }
/* 160 */     AffineTransform oldTrans = g.getTransform();
/*     */     
/* 162 */     boolean flip = isFlipTransform(oldTrans);
/*     */     
/* 164 */     g.setFont(font);
/*     */     
/* 166 */     FontRenderContext fontCtx = g.getFontRenderContext();
/* 167 */     TextLayout tl = new TextLayout(text, font, fontCtx);
/*     */ 
/*     */     
/* 170 */     Dimension size = measureStringSize((JComponent)null, g, text, font, flip);
/* 171 */     Shape clipRect = g.getClip();
/*     */     
/* 173 */     int y = textRect.y;
/* 174 */     switch (verticalAlign) {
/*     */       
/*     */       case 20:
/* 177 */         y = (int)(textRect.y - tl.getDescent() + (textRect.height + tl.getAscent()) / 2.0F);
/*     */         break;
/*     */       case 21:
/* 180 */         y = (int)(textRect.y + tl.getAscent());
/*     */         break;
/*     */       case 22:
/* 183 */         y = (int)((textRect.y + textRect.height) - tl.getDescent());
/*     */         break;
/*     */       
/*     */       case 1:
/* 187 */         y = textRect.y + size.height + 1;
/*     */         break;
/*     */       case 3:
/* 190 */         y = textRect.y + textRect.height - 1;
/*     */         break;
/*     */       
/*     */       default:
/* 194 */         y = textRect.y + (textRect.height + size.height) / 2 + VERTICAL_SHIFT;
/*     */         break;
/*     */     } 
/*     */     
/* 198 */     int x = textRect.x;
/*     */     
/* 200 */     switch (horizontalAlign) {
/*     */       
/*     */       case 0:
/* 203 */         x = textRect.x + (int)((textRect.width - tl.getAdvance()) / 2.0F);
/*     */         break;
/*     */       case 2:
/*     */       case 10:
/* 207 */         x = textRect.x;
/*     */         break;
/*     */       
/*     */       case 4:
/*     */       case 11:
/* 212 */         x = textRect.x + (int)(textRect.width - tl.getAdvance());
/*     */         break;
/*     */     } 
/*     */     
/* 216 */     if (flip) {
/* 217 */       x = 2 * textRect.x + textRect.width - x;
/*     */     }
/* 219 */     clip = false;
/* 220 */     if (clip) {
/* 221 */       g.setClip(getInnerClip(clipRect, textRect));
/*     */     }
/* 223 */     g.setPaint(color);
/*     */     
/* 225 */     if (flip) {
/*     */       
/* 227 */       AffineTransform aft = AffineTransform.getScaleInstance(-1.0D, 1.0D);
/* 228 */       Font flipFont = font.deriveFont(aft);
/* 229 */       g.setFont(flipFont);
/*     */     } 
/*     */     
/* 232 */     g.drawString(text, x, y);
/*     */ 
/*     */     
/* 235 */     g.setTransform(oldTrans);
/* 236 */     if (clip) {
/* 237 */       g.setClip(clipRect);
/*     */     }
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 242 */     if (this.m_Component.isOpaque()) {
/*     */       
/* 244 */       g.setColor(this.m_Component.getBackground());
/* 245 */       g.fillRect(0, 0, this.m_Component.getWidth(), this.m_Component.getHeight());
/*     */     } 
/*     */     
/* 248 */     Graphics2D g2d = (Graphics2D)g;
/* 249 */     AffineTransform oldTransform = g2d.getTransform();
/*     */ 
/*     */     
/*     */     try {
/* 253 */       if (!this.m_Component.getComponentOrientation().isLeftToRight()) {
/* 254 */         g2d.transform(this);
/*     */       }
/* 256 */       this.m_Component.paintFlipComponent(g);
/*     */     }
/* 258 */     catch (Exception e) {
/*     */       
/* 260 */       if (JLbsConstants.DEBUG) {
/* 261 */         e.printStackTrace();
/*     */       }
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/* 267 */         g2d.transform(createInverse());
/*     */       }
/* 269 */       catch (NoninvertibleTransformException e) {
/*     */         
/* 271 */         g2d.setTransform(oldTransform);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 278 */     adjust();
/*     */   }
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsFlipTransform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */