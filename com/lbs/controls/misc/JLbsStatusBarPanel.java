/*     */ package com.lbs.controls.misc;
/*     */ 
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsStatusBarPanel
/*     */ {
/*     */   public static final int NONE = 0;
/*     */   public static final int GRAB_EXCESS_SPACE = 1;
/*     */   public static final int AUTO_SIZE = 2;
/*     */   public static final int HIDE_EMPTY = 4;
/*     */   public static final int MINIMUM_WIDTH = 8;
/*     */   private int m_Width;
/*     */   private int m_Alignment;
/*     */   private String m_Caption;
/*     */   private Color m_TextColor;
/*     */   private String m_Name;
/*     */   private int m_Options;
/*     */   protected int m_DrawOffset;
/*     */   protected int m_DrawWidth;
/*     */   protected Component m_Component;
/*     */   
/*     */   public JLbsStatusBarPanel(int width, String s, int alignment) {
/*  41 */     this.m_Width = width;
/*  42 */     this.m_Caption = s;
/*  43 */     this.m_Alignment = alignment;
/*  44 */     this.m_Options = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel(int width, Component c, int alignment) {
/*  49 */     this.m_Width = width;
/*  50 */     this.m_Component = c;
/*  51 */     this.m_Alignment = alignment;
/*  52 */     this.m_Options = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel(String s) {
/*  57 */     this.m_Caption = s;
/*  58 */     this.m_Options = 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  63 */     if (this.m_Component != null) {
/*  64 */       return !this.m_Component.isVisible();
/*     */     }
/*  66 */     return JLbsStringUtil.isEmpty(this.m_Caption);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paint(Graphics g, JLbsStatusBar panel) {
/*  74 */     int height = panel.getHeight();
/*     */     
/*  76 */     int panelWidth = this.m_DrawWidth;
/*  77 */     int xOffset = this.m_DrawOffset;
/*  78 */     if (panelWidth > 0) {
/*     */       
/*  80 */       JLbsStatusBar.ms_Border.paintBorder(panel, g, xOffset, 0, panelWidth, height);
/*     */       
/*  82 */       if (this.m_Component != null) {
/*     */         return;
/*     */       }
/*  85 */       Insets insets = JLbsStatusBar.ms_Border.getBorderInsets(panel);
/*  86 */       Rectangle rect = new Rectangle(xOffset + insets.left, insets.top, panelWidth - insets.left - insets.right, height - 
/*  87 */           insets.top - insets.bottom);
/*     */       
/*  89 */       g.setColor((this.m_TextColor != null) ? 
/*  90 */           getTextColor() : 
/*  91 */           panel.getForeground());
/*  92 */       String caption = getCaption();
/*  93 */       g.setFont(panel.getFont());
/*  94 */       if (!JLbsStringUtil.isEmpty(caption)) {
/*     */         
/*  96 */         rect.x += 2;
/*  97 */         rect.width -= 4;
/*     */         
/*  99 */         int alignment = getAlignment();
/*     */         
/* 101 */         Rectangle iconR = new Rectangle();
/* 102 */         Rectangle textR = new Rectangle();
/*     */         
/* 104 */         FontMetrics fm = g.getFontMetrics();
/* 105 */         caption = SwingUtilities.layoutCompoundLabel(fm, caption, null, 0, alignment, 
/* 106 */             10, 10, rect, iconR, textR, 0);
/* 107 */         int textX = textR.x;
/* 108 */         int textY = textR.y + fm.getAscent();
/*     */         
/* 110 */         g.drawString(caption, textX, textY);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAlignment() {
/* 122 */     return this.m_Alignment;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 127 */     return this.m_Width;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCaption() {
/* 132 */     return this.m_Caption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaption(String string) {
/* 137 */     this.m_Caption = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 142 */     if (this.m_Component instanceof JComponent)
/* 143 */       return ((JComponent)this.m_Component).getToolTipText(); 
/* 144 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String string) {
/* 149 */     if (this.m_Component instanceof JComponent) {
/* 150 */       ((JComponent)this.m_Component).setToolTipText(string);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTextColor(Color c) {
/* 155 */     this.m_TextColor = c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredWidth() {
/* 160 */     return (this.m_Width < 0) ? 
/* 161 */       0 : 
/* 162 */       this.m_Width;
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
/*     */   public Color getTextColor() {
/* 179 */     return this.m_TextColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 184 */     return this.m_Options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 189 */     this.m_Options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOption(int option) {
/* 194 */     return ((this.m_Options & option) == option);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 199 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 204 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize(Font font) {
/* 209 */     if (this.m_Component != null) {
/* 210 */       return this.m_Component.getSize();
/*     */     }
/* 212 */     Dimension size = JLbsControlHelper.measureString(this.m_Caption, null, font);
/*     */     
/* 214 */     if (size == null) {
/* 215 */       return new Dimension(0, 0);
/*     */     }
/* 217 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doLayout(JLbsStatusBar statusBar) {
/* 222 */     if (this.m_Component != null) {
/* 223 */       this.m_Component.setLocation(this.m_DrawOffset + 5, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 231 */     return this.m_Component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponent(Component component) {
/* 236 */     this.m_Component = component;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\misc\JLbsStatusBarPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */