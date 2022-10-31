/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsGroupBox;
/*     */ import com.lbs.controls.JLbsPanel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.net.URL;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.EtchedBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import javax.swing.plaf.PanelUI;
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
/*     */ public class JLbsGroupBox
/*     */   extends JLbsPanel
/*     */   implements ILbsGroupBox
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  35 */   protected String m_Text = "Group Box";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsGroupBox() {
/*  42 */     setBorder(new EtchedBorder(1));
/*  43 */     setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/*  48 */     super.updateUI();
/*  49 */     Border border = getBorder();
/*  50 */     if (border != null && !(border instanceof EmptyBorder) && !(border instanceof JLbsGroupBorder)) {
/*     */       
/*  52 */       Border newBorder = UIManager.getBorder("Panel.roundFrame");
/*  53 */       if (newBorder != null) {
/*     */         
/*     */         try {
/*  56 */           setBorder((Border)newBorder.getClass().newInstance());
/*     */         }
/*  58 */         catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean bEnable) {
/*  69 */     super.setEnabled(bEnable);
/*  70 */     int iCompCount = getComponentCount();
/*  71 */     for (int i = 0; i < iCompCount; i++) {
/*  72 */       getComponent(i).setEnabled(bEnable);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUI(PanelUI ui) {
/*  82 */     super.setUI(ui);
/*  83 */     Border prevBorder = getBorder();
/*  84 */     if (ui != null && prevBorder != null && !(prevBorder instanceof EmptyBorder) && !(prevBorder instanceof JLbsGroupBorder)) {
/*     */       
/*  86 */       Border border = UIManager.getBorder("Panel.roundFrame");
/*  87 */       if (border != null) {
/*     */         
/*  89 */         super.setBorder(border);
/*  90 */         if (border instanceof TitledBorder && this.m_Text != null) {
/*  91 */           ((TitledBorder)border).setTitle(this.m_Text);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void paint(Graphics g) {
/*  98 */     super.paint(g);
/*  99 */     if (this.m_Text != null) {
/*     */       
/* 101 */       FontMetrics fm = g.getFontMetrics();
/* 102 */       int iTextTop = fm.getHeight();
/* 103 */       Border border = getBorder();
/* 104 */       if (border == null || border instanceof TitledBorder)
/*     */         return; 
/* 106 */       g.setColor(getBackground());
/* 107 */       g.fillRect(8, 0, fm.stringWidth(this.m_Text) + 6, iTextTop);
/* 108 */       iTextTop -= (border.getBorderInsets((Component)this)).top / 2 + 1;
/* 109 */       Color color = UIManager.getColor(isEnabled() ? 
/* 110 */           "Panel.CaptionColor" : 
/* 111 */           "Panel.CaptionColorDisabled");
/* 112 */       if (color == null) {
/*     */         
/* 114 */         color = getForeground();
/* 115 */         if (!isEnabled())
/* 116 */           color.brighter().brighter(); 
/*     */       } 
/* 118 */       g.setColor(color);
/* 119 */       g.drawString(this.m_Text, 11, iTextTop);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 128 */     return this.m_Text;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 133 */     Border border = getBorder();
/* 134 */     if (border.getClass().equals(TitledBorder.class)) {
/*     */       
/* 136 */       Dimension size = super.getPreferredSize();
/*     */       
/*     */       try {
/* 139 */         Dimension borderSize = ((TitledBorder)border).getMinimumSize((Component)this);
/* 140 */         size.width = Math.max(borderSize.width, size.width);
/* 141 */         size.height = Math.max(borderSize.height, size.height);
/* 142 */         return size;
/*     */       }
/* 144 */       catch (Exception e) {
/*     */         
/* 146 */         return size;
/*     */       } 
/*     */     } 
/* 149 */     if (border instanceof JLbsGroupBorder) {
/*     */       
/* 151 */       Dimension size = super.getPreferredSize();
/*     */       
/* 153 */       Dimension borderSize = ((JLbsGroupBorder)border).getPreferredSize((Component)this, null);
/* 154 */       size.width = Math.max(borderSize.width, size.width);
/* 155 */       size.height = Math.max(borderSize.height, size.height);
/* 156 */       return size;
/*     */     } 
/* 158 */     return super.getPreferredSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 166 */     this.m_Text = text;
/* 167 */     Border border = getBorder();
/* 168 */     if (border instanceof TitledBorder) {
/*     */       
/* 170 */       TitledBorder titleBorder = (TitledBorder)border;
/* 171 */       titleBorder.setTitle(this.m_Text);
/*     */     } 
/* 173 */     invalidate();
/* 174 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {
/* 179 */     if (border == null)
/* 180 */       border = new EmptyBorder(0, 0, 0, 0); 
/* 181 */     if (border instanceof TitledBorder) {
/*     */       
/* 183 */       super.setBorder(border);
/* 184 */       ((TitledBorder)border).setTitle(this.m_Text);
/*     */     } else {
/*     */       
/* 187 */       super.setBorder(new TitledBorder(border, this.m_Text));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static Image getResourceBitmap(String resName) {
/* 192 */     URL url = JLbsGroupBox.class.getResource(resName);
/* 193 */     if (url == null) {
/* 194 */       return null;
/*     */     }
/* 196 */     Image image = Toolkit.getDefaultToolkit().createImage(url);
/*     */ 
/*     */     
/* 199 */     image.getWidth(null);
/* 200 */     image.getHeight(null);
/*     */     
/* 202 */     return image;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsGroupBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */