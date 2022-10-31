/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.controls.FontMetricHandler;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsGroupBorder
/*     */   extends TitledBorder
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   public static Map<String, FontMetrics> ms_Font_Metrics = new HashMap<>();
/*  35 */   public static int ms_Font_Metrics_Size = 40;
/*     */ 
/*     */   
/*     */   public JLbsGroupBorder() {
/*  39 */     this((String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsGroupBorder(String title) {
/*  44 */     super(null, title);
/*  45 */     setTitleColor(JLbsConstants.DESKTOP_MODE ? 
/*  46 */         Color.WHITE : 
/*  47 */         Color.BLACK);
/*  48 */     setTitleFont(new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/*  53 */     String title = getTitle();
/*  54 */     if (title != null && title.length() == 0)
/*  55 */       title = null; 
/*  56 */     Dimension d = new Dimension(100, (title == null) ? 
/*  57 */         2 : 
/*  58 */         30);
/*  59 */     Font titleFont = getTitleFont();
/*  60 */     if (titleFont != null && title != null) {
/*     */       
/*  62 */       g.setFont(titleFont);
/*  63 */       d = getFontSize(c, g, title);
/*     */     } 
/*     */     
/*  66 */     int corner = d.height / 4;
/*  67 */     Graphics2D g2 = (Graphics2D)g;
/*  68 */     Paint oldPaint = g2.getPaint();
/*  69 */     if (c.getComponentOrientation().isLeftToRight()) {
/*  70 */       paintForNormalOrientation(g, x, y, width, height, title, d, titleFont, corner, g2, oldPaint);
/*     */     } else {
/*  72 */       paintForRightToLeftOrientation(g, x, y, width, height, title, d, titleFont, corner, g2, oldPaint);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintForNormalOrientation(Graphics g, int x, int y, int width, int height, String title, Dimension d, Font titleFont, int corner, Graphics2D g2, Paint oldPaint) {
/*  92 */     int[] x2 = { 0, 0, corner, d.width - 1, d.width - 1 };
/*  93 */     int[] y2 = { d.height, corner, d.height, d.height };
/*  94 */     Color clfill = JLbsConstants.DESKTOP_MODE ? 
/*  95 */       new Color(204, 121, 151) : 
/*  96 */       UIManager.getColor("control");
/*     */ 
/*     */     
/*  99 */     if (title != null)
/*     */     {
/* 101 */       if (JLbsConstants.DESKTOP_MODE) {
/*     */         
/* 103 */         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 104 */         g.setColor(clfill);
/* 105 */         g.fillRoundRect(0, 0, d.width - 1, d.height - 1, 5, 5);
/*     */       }
/*     */       else {
/*     */         
/* 109 */         GradientPaint gp = new GradientPaint(0.0F, 0.0F, clfill.brighter(), 0.0F, (d.height - 1), clfill, false);
/* 110 */         g2.setPaint(gp);
/* 111 */         g.fillPolygon(x2, y2, 5);
/*     */       } 
/*     */     }
/* 114 */     if (!JLbsConstants.DESKTOP_MODE) {
/*     */       
/* 116 */       g2.setPaint(new GradientPaint(0.0F, 0.0F, clfill.darker(), width, 0.0F, clfill, false));
/* 117 */       g.fillRect(x, y + d.height, width, 1);
/* 118 */       g2.setPaint(new GradientPaint(0.0F, (y + d.height), clfill.darker(), 0.0F, height, clfill, false));
/* 119 */       g.fillRect(x, y + d.height, x + 1, height);
/*     */     } 
/* 121 */     g2.setPaint(oldPaint);
/* 122 */     if (title != null)
/*     */     {
/* 124 */       if (!JLbsConstants.DESKTOP_MODE) {
/*     */         
/* 126 */         g.setColor(UIManager.getColor("control").darker());
/* 127 */         g.drawPolyline(x2, y2, 5);
/*     */         
/* 129 */         int[] x3 = { 1, 1, corner, d.width - 2 };
/* 130 */         int[] y3 = { d.height - 2, corner, 1, 1 };
/*     */         
/* 132 */         g.setColor(Color.WHITE);
/* 133 */         g.drawPolyline(x3, y3, 4);
/*     */       } 
/*     */     }
/* 136 */     Color titleColor = getTitleColor();
/* 137 */     if (titleColor == null)
/* 138 */       titleColor = UIManager.getColor("TitledBorder.titleColor"); 
/* 139 */     g.setColor(titleColor);
/* 140 */     g.setFont(titleFont);
/* 141 */     if (title != null) {
/* 142 */       g.drawString(title, corner + 2, d.height - corner - 1);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintForRightToLeftOrientation(Graphics g, int x, int y, int width, int height, String title, Dimension d, Font titleFont, int corner, Graphics2D g2, Paint oldPaint) {
/* 161 */     int[] x2 = { width, width, width - corner, width - d.width, width - d.width, width };
/* 162 */     int[] y2 = { d.height, corner, d.height, d.height };
/* 163 */     Color clfill = JLbsConstants.DESKTOP_MODE ? 
/* 164 */       new Color(204, 121, 151) : 
/* 165 */       UIManager.getColor("control");
/*     */ 
/*     */     
/* 168 */     if (title != null)
/*     */     {
/* 170 */       if (JLbsConstants.DESKTOP_MODE) {
/*     */         
/* 172 */         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 173 */         g.setColor(clfill);
/* 174 */         g.fillRoundRect(width - d.width - 1, 0, width, d.height - 1, 5, 5);
/*     */       }
/*     */       else {
/*     */         
/* 178 */         GradientPaint gp = new GradientPaint(0.0F, 0.0F, clfill, 0.0F, (d.height - 1), clfill.brighter(), false);
/* 179 */         g2.setPaint(gp);
/* 180 */         g.fillPolygon(x2, y2, 5);
/*     */       } 
/*     */     }
/* 183 */     if (!JLbsConstants.DESKTOP_MODE) {
/*     */       
/* 185 */       g2.setPaint(new GradientPaint(0.0F, 0.0F, clfill, width, 0.0F, clfill.darker(), false));
/* 186 */       g.fillRect(x, y + d.height, width, 1);
/* 187 */       g2.setPaint(new GradientPaint(width, (y + d.height), clfill.darker(), width, height, clfill, false));
/* 188 */       g.fillRect(width - 1, y + d.height, width, height);
/*     */     } 
/*     */     
/* 191 */     g2.setPaint(oldPaint);
/* 192 */     if (title != null)
/*     */     {
/* 194 */       if (!JLbsConstants.DESKTOP_MODE) {
/*     */         
/* 196 */         g.setColor(UIManager.getColor("control").darker());
/* 197 */         g.drawPolyline(x2, y2, 5);
/*     */         
/* 199 */         int[] x3 = { width - 1, width - 1, width - corner, width - d.width + 1 };
/* 200 */         int[] y3 = { d.height - 2, corner };
/*     */         
/* 202 */         g.setColor(UIManager.getColor("control").darker());
/* 203 */         g.drawPolyline(x3, y3, 4);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 208 */     Color titleColor = getTitleColor();
/* 209 */     if (titleColor == null)
/* 210 */       titleColor = UIManager.getColor("TitledBorder.titleColor"); 
/* 211 */     g.setColor(titleColor);
/* 212 */     g.setFont(titleFont);
/* 213 */     if (title != null) {
/* 214 */       g.drawString(title, width - d.width + 2 + corner, d.height - corner - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public Insets getBorderInsets(Component c) {
/* 219 */     if (JLbsStringUtil.isEmpty(getTitle()))
/* 220 */       return new Insets(3, 3, 0, 0); 
/* 221 */     return new Insets(22, 3, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(Component c, Graphics g) {
/* 226 */     Dimension result = null;
/* 227 */     String title = getTitle();
/* 228 */     if (title != null && title.length() > 0) {
/*     */       
/* 230 */       result = getFontSize(c, g, title);
/* 231 */       result.width += 4;
/* 232 */       result.height += 4;
/*     */     } else {
/*     */       
/* 235 */       result = new Dimension(0, 0);
/* 236 */     }  return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getFontSize(Component c, Graphics g, String s) {
/*     */     FontMetrics fm;
/* 242 */     if (g == null) {
/*     */       
/* 244 */       fm = FontMetricHandler.getFontMetricsFromStyleContext(getTitleFont());
/*     */     } else {
/*     */       
/* 247 */       fm = getFontMetrics(getTitleFont(), g);
/* 248 */     }  Insets insets = getBorderInsets(c);
/* 249 */     return new Dimension(2 * insets.left + fm.stringWidth(s) + 8, fm.getMaxDescent() + fm.getMaxAscent() + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontMetrics getFontMetrics(Font font, Graphics g) {
/* 255 */     FontMetrics fontMetrics = null;
/* 256 */     String key = String.valueOf(font.getName()) + "_" + font.getStyle() + "_" + font.getSize();
/* 257 */     fontMetrics = ms_Font_Metrics.get(key);
/* 258 */     if (fontMetrics != null)
/* 259 */       return fontMetrics; 
/* 260 */     synchronized (ms_Font_Metrics) {
/*     */       
/* 262 */       if (ms_Font_Metrics.keySet().size() >= ms_Font_Metrics_Size)
/*     */       {
/* 264 */         ms_Font_Metrics = new HashMap<>();
/*     */       }
/* 266 */       if (fontMetrics == null) {
/*     */         
/* 268 */         fontMetrics = g.getFontMetrics();
/* 269 */         ms_Font_Metrics.put(key, fontMetrics);
/*     */       } 
/*     */     } 
/* 272 */     return fontMetrics;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsGroupBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */