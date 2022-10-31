/*     */ package com.lbs.controls.misc;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingConstants;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsStatusBar
/*     */   extends JPanel
/*     */   implements SwingConstants
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  33 */   protected static final Border ms_Border = JLbsConstants.DESKTOP_MODE ? 
/*  34 */     new EmptyBorder(0, 0, 0, 0) : 
/*  35 */     new JLbsEtchedBorder();
/*     */   
/*     */   protected static final int ms_GapWidth = 2;
/*     */   
/*     */   private ArrayList m_Panels;
/*     */   private int m_SpringPanelCount;
/*     */   private int m_SpringWidth;
/*     */   
/*     */   public JLbsStatusBar() {
/*  44 */     this.m_Panels = new ArrayList();
/*  45 */     setFont(new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/*  46 */     setLayout((LayoutManager)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel() {
/*  51 */     return addPanel(-1);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width) {
/*  56 */     return addPanel(width, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width, int alignment) {
/*  61 */     JLbsStatusBarPanel panel = new JLbsStatusBarPanel(width, null, alignment);
/*  62 */     this.m_Panels.add(panel);
/*  63 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(String s) {
/*  68 */     return addPanel(-1, s);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width, String s) {
/*  73 */     return addPanel(width, s, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width, String s, int alignment) {
/*  78 */     return addPanel(width, s, alignment, 0, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width, String s, int alignment, int options) {
/*  83 */     return addPanel(width, s, alignment, options, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width, String s, int alignment, int options, String name) {
/*  88 */     JLbsStatusBarPanel panel = new JLbsStatusBarPanel(width, s, alignment);
/*  89 */     panel.setOptions(options);
/*  90 */     panel.setName(name);
/*     */     
/*  92 */     this.m_Panels.add(panel);
/*  93 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel addPanel(int width, Component c, int alignment, int options, String name) {
/*  98 */     JLbsStatusBarPanel panel = new JLbsStatusBarPanel(width, c, alignment);
/*  99 */     panel.setOptions(options);
/* 100 */     panel.setName(name);
/*     */     
/* 102 */     add(c);
/* 103 */     c.addComponentListener(new ComponentAdapter()
/*     */         {
/*     */           public void componentHidden(ComponentEvent e)
/*     */           {
/* 107 */             super.componentHidden(e);
/* 108 */             JLbsStatusBar.this.repaint();
/*     */           }
/*     */ 
/*     */           
/*     */           public void componentShown(ComponentEvent e) {
/* 113 */             super.componentShown(e);
/* 114 */             JLbsStatusBar.this.repaint();
/*     */           }
/*     */         });
/*     */     
/* 118 */     this.m_Panels.add(panel);
/* 119 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 124 */     this.m_Panels.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(String name) {
/* 130 */     for (int i = 0; i < this.m_Panels.size(); i++) {
/*     */       
/* 132 */       JLbsStatusBarPanel panel = getPanel(i);
/*     */       
/* 134 */       if (panel != null && JLbsStringUtil.areEqual(panel.getName(), name))
/* 135 */         return i; 
/*     */     } 
/* 137 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaption(String name, String msg) {
/* 142 */     int idx = indexOf(name);
/* 143 */     if (idx != -1) {
/* 144 */       setCaption(idx, msg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setCaption(int index, String msg) {
/* 149 */     JLbsStatusBarPanel panel = getPanel(index);
/* 150 */     if (panel != null) {
/*     */       
/* 152 */       panel.setCaption(msg);
/* 153 */       invalidateSafe();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextColor(int index, Color c) {
/* 159 */     JLbsStatusBarPanel panel = getPanel(index);
/* 160 */     if (panel != null) {
/*     */       
/* 162 */       panel.setTextColor(c);
/* 163 */       invalidateSafe();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 169 */     int width = 0;
/* 170 */     int height = 20;
/* 171 */     this.m_SpringPanelCount = 0;
/* 172 */     for (int i = 0; i < this.m_Panels.size(); i++) {
/*     */       
/* 174 */       JLbsStatusBarPanel panel = this.m_Panels.get(i);
/* 175 */       if (panel.getWidth() < 0)
/* 176 */         this.m_SpringPanelCount++; 
/* 177 */       width += panel.getPreferredWidth();
/*     */     } 
/* 179 */     if (this.m_Panels.size() > 1)
/* 180 */       width += (this.m_Panels.size() - 1) * 2; 
/* 181 */     return new Dimension(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStatusBarPanel getPanel(int index) {
/* 186 */     if (index >= 0 && index < this.m_Panels.size())
/* 187 */       return this.m_Panels.get(index); 
/* 188 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void printChildren(Graphics g) {
/* 193 */     super.printChildren(g);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 198 */     layoutPanels();
/*     */     
/* 200 */     g.setColor(getBackground());
/* 201 */     int width = getWidth();
/* 202 */     int height = getHeight();
/* 203 */     g.fillRect(0, 0, width, height);
/*     */     
/* 205 */     super.paintComponent(g);
/*     */     
/* 207 */     for (int i = 0; i < this.m_Panels.size(); i++) {
/*     */       
/* 209 */       JLbsStatusBarPanel panel = this.m_Panels.get(i);
/* 210 */       panel.paint(g, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidateSafe() {
/* 216 */     Runnable callRevalidate = new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 220 */           JLbsStatusBar.this.invalidate();
/* 221 */           JLbsStatusBar.this.repaint();
/*     */         }
/*     */       };
/* 224 */     SwingUtilities.invokeLater(callRevalidate);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doLayout() {
/* 229 */     super.doLayout();
/*     */     
/* 231 */     layoutPanels();
/*     */   }
/*     */ 
/*     */   
/*     */   private void layoutPanels() {
/* 236 */     int width = getWidth();
/*     */ 
/*     */     
/* 239 */     int numGrabs = 0;
/* 240 */     int paneWidth = 0;
/* 241 */     int numSpring = 0;
/*     */ 
/*     */     
/* 244 */     int numVisible = 0;
/* 245 */     for (int i = 0; i < this.m_Panels.size(); i++) {
/*     */       
/* 247 */       JLbsStatusBarPanel panel = this.m_Panels.get(i);
/* 248 */       int panelWidth = panel.getWidth();
/*     */       
/* 250 */       if (panel.hasOption(4) && panel.isEmpty()) {
/*     */         
/* 252 */         panelWidth = 0;
/* 253 */         panel.m_DrawWidth = panelWidth;
/* 254 */         numVisible--;
/*     */       }
/* 256 */       else if (panel.hasOption(2)) {
/*     */         
/* 258 */         Dimension size = panel.getSize(getFont());
/* 259 */         int sizeWidth = size.width;
/*     */         
/* 261 */         if (size.width <= 0 && panel.hasOption(8)) {
/* 262 */           sizeWidth = panel.getPreferredWidth();
/*     */         }
/* 264 */         panelWidth = sizeWidth + 4 + 5;
/* 265 */         panel.m_DrawWidth = panelWidth;
/*     */       }
/* 267 */       else if (panel.hasOption(1)) {
/*     */         
/* 269 */         numGrabs++;
/*     */       }
/* 271 */       else if (panelWidth < 0) {
/*     */         
/* 273 */         numSpring++;
/*     */       } 
/*     */       
/* 276 */       numVisible++;
/* 277 */       paneWidth += panelWidth;
/*     */     } 
/*     */     
/* 280 */     if (numVisible > 1) {
/* 281 */       paneWidth += (numVisible - 1) * 2;
/*     */     }
/* 283 */     int diff = width - paneWidth;
/* 284 */     this.m_SpringPanelCount = numGrabs + numSpring;
/* 285 */     this.m_SpringWidth = (this.m_SpringPanelCount > 0) ? (
/* 286 */       diff / this.m_SpringPanelCount) : 
/* 287 */       0;
/*     */     
/* 289 */     int xOffset = 0;
/* 290 */     for (int j = 0; j < this.m_Panels.size(); j++) {
/*     */       
/* 292 */       JLbsStatusBarPanel panel = this.m_Panels.get(j);
/* 293 */       int panelWidth = (panel.getWidth() < 0) ? 
/* 294 */         this.m_SpringWidth : 
/* 295 */         panel.getWidth();
/*     */       
/* 297 */       if (panel.hasOption(4) && panel.isEmpty()) {
/* 298 */         panelWidth = 0;
/*     */       }
/* 300 */       if (panel.hasOption(2)) {
/* 301 */         panelWidth = panel.m_DrawWidth;
/*     */       }
/* 303 */       if (panel.hasOption(1)) {
/* 304 */         panelWidth += this.m_SpringWidth;
/*     */       }
/* 306 */       panel.m_DrawOffset = xOffset;
/* 307 */       panel.m_DrawWidth = panelWidth;
/*     */       
/* 309 */       if (!getComponentOrientation().isLeftToRight()) {
/* 310 */         panel.m_DrawOffset = width - xOffset - panelWidth;
/*     */       }
/* 312 */       if (panelWidth > 0) {
/* 313 */         xOffset += panelWidth + 2;
/*     */       }
/* 315 */       if (panelWidth > 0) {
/* 316 */         panel.doLayout(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/* 322 */     ComponentOrientation oldOrientation = getComponentOrientation();
/* 323 */     super.setComponentOrientation(o);
/*     */     
/* 325 */     oldOrientation.isLeftToRight(); o.isLeftToRight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 333 */     super.setFont(font);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPanelCount() {
/* 339 */     return this.m_Panels.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\misc\JLbsStatusBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */