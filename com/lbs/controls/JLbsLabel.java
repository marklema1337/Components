/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsLabel;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.UIManager;
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
/*     */ public class JLbsLabel
/*     */   extends JLabel
/*     */   implements ILbsLabel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int TEXT_PAD = 4;
/*     */   private static final int TEXT_PADx2 = 8;
/*     */   protected static final int DEF_FONTSTYLE = 0;
/*  46 */   protected int m_NumLines = 0;
/*  47 */   protected List m_SubStrings = new ArrayList();
/*  48 */   protected int m_MaxWidthLine = -1;
/*  49 */   protected int m_MaxLineWidth = 0;
/*     */   
/*     */   protected boolean isLPTImage = false;
/*  52 */   private Dimension m_PreferredSize = null;
/*  53 */   public static Map<String, FontMetrics> ms_Font_Metrics = new HashMap<>();
/*  54 */   public static int ms_Font_Metrics_Size = 40;
/*     */ 
/*     */   
/*     */   public JLbsLabel() {
/*  58 */     setHorizontalAlignment(2);
/*  59 */     setFont(new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsLabel(String text) {
/*  64 */     this();
/*  65 */     setText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public FontMetrics getFontMetrics(Font font) {
/*  70 */     FontMetrics fontMetrics = null;
/*  71 */     String key = String.valueOf(font.getName()) + "_" + font.getStyle() + "_" + font.getSize();
/*  72 */     fontMetrics = ms_Font_Metrics.get(key);
/*  73 */     if (fontMetrics != null)
/*  74 */       return fontMetrics; 
/*  75 */     synchronized (ms_Font_Metrics) {
/*     */       
/*  77 */       if (ms_Font_Metrics.keySet().size() >= ms_Font_Metrics_Size)
/*     */       {
/*  79 */         ms_Font_Metrics = new HashMap<>();
/*     */       }
/*  81 */       if (fontMetrics == null) {
/*     */         
/*  83 */         fontMetrics = super.getFontMetrics(font);
/*  84 */         ms_Font_Metrics.put(key, fontMetrics);
/*     */       } 
/*     */     } 
/*  87 */     return fontMetrics;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/*  93 */     setName(text);
/*  94 */     super.setText(text);
/*  95 */     this.m_NumLines = 1;
/*  96 */     int start = 0;
/*  97 */     int newlineAt = 0;
/*  98 */     int newLineDoubleAt = 0;
/*  99 */     int lastNewLineAt = 0;
/*     */     
/* 101 */     if (this.m_SubStrings != null) {
/* 102 */       this.m_SubStrings.clear();
/*     */     }
/*     */ 
/*     */     
/* 106 */     if (!JLbsStringUtil.isEmpty(text))
/*     */     {
/* 108 */       while ((newlineAt = text.indexOf("\n", start)) > 0 || (newLineDoubleAt = text.indexOf("\\n", start)) > 0) {
/*     */         
/* 110 */         boolean foundDouble = false;
/* 111 */         if (newlineAt < 0) {
/*     */           
/* 113 */           newlineAt = newLineDoubleAt;
/* 114 */           foundDouble = true;
/*     */         } 
/* 116 */         lastNewLineAt = newlineAt;
/* 117 */         String sub = (new String(text.substring(start, newlineAt))).trim();
/* 118 */         this.m_SubStrings.add(sub);
/* 119 */         this.m_NumLines++;
/* 120 */         start = newlineAt + (foundDouble ? 
/* 121 */           2 : 
/* 122 */           1);
/*     */       } 
/*     */     }
/*     */     
/* 126 */     if (lastNewLineAt > 0 && lastNewLineAt < text.length()) {
/* 127 */       String str = (new String(text.substring(lastNewLineAt, text.length()))).trim();
/*     */     }
/*     */ 
/*     */     
/* 131 */     if (this.m_NumLines > 1) {
/*     */       
/* 133 */       String sub = (new String(text.substring(start))).trim();
/* 134 */       this.m_SubStrings.add(sub);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumLines() {
/* 162 */     return this.m_NumLines;
/*     */   }
/*     */ 
/*     */   
/*     */   private Dimension internalCalcSize() {
/* 167 */     Font f = getFont();
/*     */     
/* 169 */     if (f == null) {
/* 170 */       f = UIManager.getFont("Label.font");
/*     */     }
/* 172 */     if (f == null) {
/*     */       
/* 174 */       System.err.println("Couldn't get Font for " + getName() + "  Setting to Dialog-9");
/* 175 */       f = new Font("Dialog", 0, 9);
/* 176 */       setFont(f);
/*     */     } 
/*     */     
/* 179 */     FontMetrics fm = getFontMetrics(f);
/*     */     
/* 181 */     if (this.m_NumLines > 1) {
/*     */       
/* 183 */       for (int i = 0; i < this.m_NumLines; i++)
/*     */       {
/* 185 */         int lineWidth = fm.stringWidth(this.m_SubStrings.get(i));
/* 186 */         if (lineWidth > this.m_MaxLineWidth)
/*     */         {
/* 188 */           this.m_MaxWidthLine = i;
/* 189 */           this.m_MaxLineWidth = lineWidth;
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 196 */       this.m_MaxWidthLine = 0;
/* 197 */       String txt = getText();
/* 198 */       this.m_MaxLineWidth = (txt != null) ? 
/* 199 */         getInternalWidth(fm, txt, f) : 
/* 200 */         0;
/*     */     } 
/*     */     
/* 203 */     int leading = fm.getLeading();
/*     */ 
/*     */ 
/*     */     
/* 207 */     int height = this.m_NumLines * fm.getHeight() + leading;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     int width = this.m_MaxLineWidth + 2 * leading;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     Insets insets = getInsets();
/*     */     
/* 220 */     Dimension result = new Dimension(width + 8 + insets.left + insets.right, height + 8 + insets.top + 
/* 221 */         insets.bottom);
/* 222 */     Dimension d = super.getPreferredSize();
/* 223 */     if (d != null && this.m_NumLines == 1) {
/*     */       
/* 225 */       result.width = Math.max(d.width, result.width);
/* 226 */       result.height = Math.max(d.height, result.height);
/*     */     } 
/* 228 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getInternalWidth(FontMetrics fm, String txt, Font f) {
/* 233 */     if (getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT) {
/*     */       
/* 235 */       if (!JLbsStringUtil.isEmpty(txt)) {
/*     */         
/* 237 */         FontRenderContext frc = new FontRenderContext(null, false, false);
/* 238 */         GlyphVector gv = f.createGlyphVector(frc, txt);
/* 239 */         return (int)gv.getVisualBounds().getWidth();
/*     */       } 
/*     */ 
/*     */       
/* 243 */       return 0;
/*     */     } 
/*     */     
/* 246 */     return fm.stringWidth(txt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHorizontalAlignment() {
/* 252 */     if (getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT)
/*     */     {
/* 254 */       switch (super.getHorizontalAlignment()) {
/*     */         
/*     */         case 4:
/* 257 */           return 2;
/*     */         case 2:
/* 259 */           return 4;
/*     */       } 
/*     */     }
/* 262 */     return super.getHorizontalAlignment();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 270 */     Dimension dimension = internalCalcSize();
/* 271 */     if (!this.isLPTImage) {
/*     */       
/* 273 */       setPreferredSize(dimension);
/* 274 */       return dimension;
/*     */     } 
/* 276 */     return super.getPreferredSize();
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
/*     */   public Dimension getMinimumSize() {
/* 290 */     if (this.m_PreferredSize == null)
/* 291 */       return super.getMinimumSize(); 
/* 292 */     return getPreferredSize();
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
/*     */   public Dimension getMaximumSize() {
/* 305 */     if (this.m_PreferredSize == null)
/* 306 */       return super.getMaximumSize(); 
/* 307 */     return getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension preferredSize) {
/* 312 */     this.m_PreferredSize = preferredSize;
/* 313 */     super.setPreferredSize(preferredSize);
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
/*     */   public void paintComponent(Graphics g) {
/* 326 */     Dimension dim = getSize();
/* 327 */     Font f = getFont();
/* 328 */     FontMetrics fm = getFontMetrics(f);
/* 329 */     Insets insets = getInsets();
/* 330 */     g.setFont(f);
/*     */ 
/*     */ 
/*     */     
/* 334 */     if (isOpaque()) {
/*     */       
/* 336 */       g.setColor(getBackground());
/* 337 */       g.fillRect(insets.left, insets.top, dim.width - insets.right - 1, dim.height - insets.bottom - 1);
/*     */     } 
/*     */     
/* 340 */     String fullText = getText();
/* 341 */     if (fullText == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 346 */     g.setColor(getForeground());
/*     */ 
/*     */ 
/*     */     
/* 350 */     int fontHeight = fm.getHeight();
/*     */     
/* 352 */     int labelHeight = fontHeight * this.m_NumLines + fm.getLeading();
/* 353 */     int labelWidth = dim.width - insets.left - insets.right;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     int extra = Math.max(0, dim.height - insets.top - insets.bottom - labelHeight);
/*     */ 
/*     */ 
/*     */     
/* 362 */     int ypos = insets.top + fontHeight - fm.getDescent() + extra / 2;
/* 363 */     int xpos = insets.left + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 368 */     if (!JLbsStringUtil.isEmpty(getText())) {
/* 369 */       int swidth; switch (getHorizontalAlignment()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 0:
/* 376 */           if (this.m_NumLines > 1) {
/*     */             
/* 378 */             for (int i = 0; i < this.m_NumLines; i++) {
/*     */               
/* 380 */               String subString = this.m_SubStrings.get(i);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 385 */               int lineWidth = fm.stringWidth(subString);
/* 386 */               xpos = insets.left + (labelWidth - lineWidth) / 2;
/*     */               
/* 388 */               g.drawString(subString, xpos, ypos);
/* 389 */               ypos += fontHeight;
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 395 */           swidth = fm.stringWidth(fullText);
/* 396 */           xpos = insets.left + (labelWidth - swidth) / 2;
/* 397 */           g.drawString(fullText, xpos, ypos);
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 405 */           if (this.m_NumLines > 1) {
/*     */             
/* 407 */             for (int i = 0; i < this.m_NumLines; i++) {
/*     */               
/* 409 */               String subString = this.m_SubStrings.get(i);
/* 410 */               g.drawString(subString, xpos, ypos);
/* 411 */               ypos += fontHeight;
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/* 416 */           g.drawString(fullText, xpos, ypos);
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 426 */           if (this.m_NumLines > 1) {
/*     */             
/* 428 */             for (int i = 0; i < this.m_NumLines; i++) {
/*     */               
/* 430 */               String subString = this.m_SubStrings.get(i);
/*     */ 
/*     */ 
/*     */               
/* 434 */               int lineWidth = fm.stringWidth(subString);
/* 435 */               xpos = labelWidth - insets.right - lineWidth;
/*     */               
/* 437 */               g.drawString(subString, xpos, ypos);
/* 438 */               ypos += fontHeight;
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/* 443 */           xpos = labelWidth - fm.stringWidth(fullText);
/* 444 */           g.drawString(fullText, xpos, ypos);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 450 */     if (getIcon() != null) {
/*     */       
/* 452 */       super.paintComponent(g);
/* 453 */       Icon icon = getIcon();
/* 454 */       if (icon instanceof ImageIcon) {
/*     */         
/* 456 */         Image image = ((ImageIcon)icon).getImage();
/* 457 */         if (image != null) {
/*     */           
/* 459 */           Rectangle rect = getClientRectInsideBorder(this);
/* 460 */           g.setColor(getBackground());
/* 461 */           g.fillRect(rect.x, rect.y, rect.x + rect.width, rect.x + rect.height);
/* 462 */           int width = rect.width;
/* 463 */           int height = rect.height;
/* 464 */           int imgWidth = image.getWidth(null);
/* 465 */           int imgHeight = image.getHeight(null);
/*     */           
/* 467 */           if (width > imgWidth) {
/*     */             
/* 469 */             rect.x += (width - imgWidth) / 2;
/* 470 */             width = imgWidth;
/*     */           } 
/* 472 */           if (height > imgHeight) {
/*     */             
/* 474 */             rect.y = (height - imgHeight) / 2;
/* 475 */             height = imgHeight;
/*     */           } 
/*     */           
/* 478 */           g.drawImage(image, rect.x, rect.y, rect.x + width, rect.y + height, 0, 0, imgWidth, imgHeight, getBackground(), 
/* 479 */               null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Rectangle getClientRectInsideBorder(JComponent c) {
/* 488 */     if (c == null)
/* 489 */       return new Rectangle(); 
/* 490 */     Dimension size = c.getSize();
/* 491 */     Insets inset = c.getInsets();
/* 492 */     if (inset != null)
/* 493 */       return new Rectangle(inset.left, inset.top, size.width - inset.left - inset.right, size.height - inset.top - 
/* 494 */           inset.bottom); 
/* 495 */     return new Rectangle(0, 0, size.width, size.height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void autosize() {
/* 500 */     setSize(getPreferredSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font f) {
/* 505 */     super.setFont(f);
/* 506 */     getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 511 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 516 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 521 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 526 */     return getParent();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLPTImage() {
/* 531 */     return this.isLPTImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLPTImage(boolean isLPTImage) {
/* 536 */     this.isLPTImage = isLPTImage;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */