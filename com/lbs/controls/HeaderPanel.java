/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.JPanel;
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
/*     */ class HeaderPanel
/*     */   extends JLbsPanel
/*     */   implements MouseListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected JPanel m_Content;
/*     */   protected Font m_Font;
/*     */   protected String m_Text;
/*     */   protected boolean m_Selected;
/*     */   protected BufferedImage m_Expanded;
/*     */   protected BufferedImage m_Collapsed;
/*     */   protected Rectangle m_Target;
/* 131 */   private final int OFFSET = 30;
/* 132 */   private final int PAD = 5;
/*     */ 
/*     */   
/*     */   public HeaderPanel(String text, JPanel content) {
/* 136 */     this.m_Text = text;
/* 137 */     this.m_Font = UIManager.getFont("TitledBorder.font");
/* 138 */     this.m_Selected = false;
/* 139 */     addMouseListener(this);
/* 140 */     setPreferredSize(new Dimension(200, 20));
/* 141 */     createImages();
/* 142 */     setRequestFocusEnabled(true);
/* 143 */     this.m_Content = content;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggleSelection() {
/* 149 */     this.m_Selected = !this.m_Selected;
/* 150 */     this.m_Content.setVisible(!this.m_Content.isShowing());
/* 151 */     repaint();
/* 152 */     getParent().validate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 157 */     super.paintComponent(g);
/* 158 */     Graphics2D g2 = (Graphics2D)g;
/* 159 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 160 */     int h = getHeight();
/* 161 */     if (this.m_Selected) {
/* 162 */       g2.drawImage(this.m_Expanded, 5, 0, this);
/*     */     } else {
/* 164 */       g2.drawImage(this.m_Collapsed, 5, 0, this);
/* 165 */     }  g2.setFont(this.m_Font);
/* 166 */     FontRenderContext frc = g2.getFontRenderContext();
/* 167 */     LineMetrics lm = this.m_Font.getLineMetrics(this.m_Text, frc);
/* 168 */     float height = lm.getAscent() + lm.getDescent();
/* 169 */     float x = 30.0F;
/* 170 */     float y = (h + height) / 2.0F - lm.getDescent();
/* 171 */     g2.drawString(this.m_Text, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createImages() {
/* 176 */     int w = 20;
/* 177 */     int h = (getPreferredSize()).height;
/* 178 */     this.m_Target = new Rectangle(2, 0, 20, 18);
/* 179 */     this.m_Expanded = new BufferedImage(w, h, 1);
/* 180 */     Graphics2D g2 = this.m_Expanded.createGraphics();
/* 181 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 182 */     g2.setPaint(getBackground());
/* 183 */     g2.fillRect(0, 0, w, h);
/* 184 */     int[] x = { 2, w / 2, 18 };
/* 185 */     int[] y = { 4, 15, 4 };
/* 186 */     Polygon p = new Polygon(x, y, 3);
/* 187 */     g2.setPaint(Color.green.brighter());
/* 188 */     g2.fill(p);
/* 189 */     g2.setPaint(Color.blue.brighter());
/* 190 */     g2.draw(p);
/* 191 */     g2.dispose();
/* 192 */     this.m_Collapsed = new BufferedImage(w, h, 1);
/* 193 */     g2 = this.m_Collapsed.createGraphics();
/* 194 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 195 */     g2.setPaint(getBackground());
/* 196 */     g2.fillRect(0, 0, w, h);
/* 197 */     x = new int[] { 3, 13, 3 };
/* 198 */     y = new int[] { 4, h / 2, 16 };
/* 199 */     p = new Polygon(x, y, 3);
/* 200 */     g2.setPaint(Color.red);
/* 201 */     g2.fill(p);
/* 202 */     g2.setPaint(Color.blue.brighter());
/* 203 */     g2.draw(p);
/* 204 */     g2.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 209 */     if (e.getClickCount() >= 1) {
/* 210 */       toggleSelection();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 231 */     this.m_Text = text;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\HeaderPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */