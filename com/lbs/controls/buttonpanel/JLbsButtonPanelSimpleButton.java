/*     */ package com.lbs.controls.buttonpanel;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
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
/*     */ public class JLbsButtonPanelSimpleButton
/*     */   implements ILbsButtonPanelChild
/*     */ {
/*     */   private static JButton ms_Button;
/*     */   private static JButton ms_LookupButton;
/*     */   private static JButton ms_LangWithFlagButton;
/*     */   public static final int COMBO = 0;
/*     */   public static final int ELLIPSIS = 1;
/*     */   public static final int DATE = 2;
/*     */   public static final int LANG_FLAG = 3;
/*  46 */   protected int m_iWidth = 0;
/*  47 */   protected int m_iGlyphType = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsButtonPanelSimpleButton(int iWidth) {
/*  55 */     this.m_iWidth = iWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/*  60 */     ms_Button = null;
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
/*     */   public JLbsButtonPanelSimpleButton(int iWidth, int iGlyphType) {
/*  72 */     this.m_iWidth = iWidth;
/*  73 */     this.m_iGlyphType = iGlyphType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void PaintButton(Component c, Graphics g, Rectangle rect) {
/*  78 */     InternalPaintButton(c, g, rect, false, false, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean PaintButtonHover(Component c, Graphics g, Rectangle rect) {
/*  83 */     InternalPaintButton(c, g, rect, false, true, 0);
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean PaintButtonPressed(Component c, Graphics g, Rectangle rect) {
/*  89 */     InternalPaintButton(c, g, rect, true, false, 1);
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/*  95 */     return this.m_iWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void InternalPaintButton(Component c, Graphics g, Rectangle rect, boolean bPressed, boolean bHover, int offset) {
/* 101 */     rect.width--;
/*     */     
/* 103 */     JButton button = getButton((this.m_iGlyphType <= 3));
/* 104 */     ButtonModel model = button.getModel();
/* 105 */     model.setPressed(bPressed);
/* 106 */     model.setSelected(bPressed);
/* 107 */     model.setArmed(bPressed);
/* 108 */     if (bHover) {
/* 109 */       model.setRollover(true);
/*     */     } else {
/* 111 */       model.setRollover(false);
/* 112 */     }  g.setColor(UIManager.getColor("control"));
/* 113 */     g.fillRect(rect.x, rect.y, rect.width, rect.height);
/*     */ 
/*     */     
/* 116 */     ComponentUI ui = UIManager.getUI(button);
/* 117 */     if (ui != null) {
/*     */       
/* 119 */       button.setLocation(0, 0);
/* 120 */       button.setSize(rect.getSize());
/* 121 */       g.translate(rect.x, rect.y);
/* 122 */       ui.paint(g, button);
/* 123 */       g.translate(-rect.x, -rect.y);
/*     */     } 
/*     */     
/* 126 */     SwingUtilities.paintComponent(g, button, (Container)c, rect);
/* 127 */     Rectangle rectDraw = new Rectangle(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 5);
/* 128 */     paintGlyph(g, rectDraw, offset, offset);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintGlyph(Graphics g, Rectangle rect, int offsX, int offsY) {
/* 133 */     int centerX = rect.x + rect.width / 2 + offsX;
/* 134 */     int centerY = rect.y + rect.height / 2 + offsY;
/* 135 */     g.setColor(Color.black);
/* 136 */     switch (this.m_iGlyphType) {
/*     */       
/*     */       case 1:
/* 139 */         g.setColor(UIManager.getColor("Button.roll"));
/* 140 */         centerY += centerY / 4;
/* 141 */         g.fillOval(centerX - 5, centerY, 3, 3);
/* 142 */         g.fillOval(centerX - 1, centerY, 3, 3);
/* 143 */         g.fillOval(centerX + 3, centerY, 3, 3);
/*     */       
/*     */       case 3:
/*     */         return;
/*     */       case 2:
/* 148 */         if (JLbsConstants.DESKTOP_MODE) {
/*     */           
/* 150 */           ImageIcon icon = (ImageIcon)UIManager.get("DateEdit.dateIcon");
/* 151 */           if (icon != null) {
/* 152 */             g.drawImage(icon.getImage(), centerX - 8, centerY - 8, null);
/*     */           }
/*     */         } else {
/*     */           
/* 156 */           g.drawLine(centerX + 7, centerY - 5, centerX + 7, centerY + 6);
/* 157 */           g.drawLine(centerX + 6, centerY + 6, centerX - 6, centerY + 6);
/* 158 */           g.setColor(Color.gray);
/* 159 */           g.drawLine(centerX - 7, centerY - 5, centerX - 7, centerY + 6);
/* 160 */           g.drawLine(centerX - 6, centerY - 5, centerX + 6, centerY - 5);
/* 161 */           g.setColor(new Color(0, 0, 128));
/* 162 */           g.drawLine(centerX - 6, centerY - 4, centerX + 6, centerY - 4);
/* 163 */           g.drawLine(centerX - 6, centerY - 3, centerX + 6, centerY - 3);
/* 164 */           g.drawLine(centerX - 6, centerY - 2, centerX + 6, centerY - 2);
/* 165 */           g.setColor(Color.gray);
/* 166 */           g.drawLine(centerX - 4, centerY - 3, centerX + 4, centerY - 3);
/* 167 */           g.setColor(new Color(240, 240, 240));
/* 168 */           g.drawLine(centerX - 6, centerY - 1, centerX + 6, centerY - 1);
/* 169 */           g.drawLine(centerX - 6, centerY + 1, centerX + 6, centerY + 1);
/* 170 */           g.drawLine(centerX - 6, centerY + 3, centerX + 6, centerY + 3);
/* 171 */           g.drawLine(centerX - 6, centerY + 5, centerX + 6, centerY + 5);
/* 172 */           g.drawLine(centerX - 4, centerY - 1, centerX - 4, centerY + 5);
/* 173 */           g.drawLine(centerX - 1, centerY - 1, centerX - 1, centerY + 5);
/* 174 */           g.drawLine(centerX + 2, centerY - 1, centerX + 2, centerY + 5);
/* 175 */           g.setColor(new Color(240, 10, 10));
/* 176 */           g.drawLine(centerX + 4, centerY + 3, centerX + 3, centerY + 3);
/*     */         } 
/*     */     } 
/*     */ 
/*     */     
/* 181 */     int iStartY = centerY - 2;
/* 182 */     g.drawLine(centerX - 4, iStartY + 0, centerX + 4, iStartY + 0);
/* 183 */     g.drawLine(centerX - 3, iStartY + 1, centerX + 3, iStartY + 1);
/* 184 */     g.drawLine(centerX - 2, iStartY + 2, centerX + 2, iStartY + 2);
/* 185 */     g.drawLine(centerX - 1, iStartY + 3, centerX + 1, iStartY + 3);
/* 186 */     g.drawLine(centerX - 0, iStartY + 4, centerX + 0, iStartY + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JButton getButton(boolean isLookup) {
/* 194 */     if (isLookup) {
/*     */       
/* 196 */       if (ms_LookupButton == null) {
/*     */         
/* 198 */         ms_LookupButton = new JButton();
/* 199 */         ms_LookupButton.putClientProperty("JButton.isLookupButton", Boolean.TRUE);
/*     */       } 
/* 201 */       return ms_LookupButton;
/*     */     } 
/* 203 */     if (ms_Button == null)
/* 204 */       ms_Button = new JButton(); 
/* 205 */     return ms_Button;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JButton getButton() {
/* 211 */     return getButton(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JButton getFlagButton() {
/* 216 */     if (ms_LangWithFlagButton == null) {
/*     */       
/* 218 */       ms_LangWithFlagButton = new JButton();
/* 219 */       ms_LangWithFlagButton.putClientProperty("JButton.isMultilangButton", Boolean.TRUE);
/*     */     } 
/* 221 */     return ms_LangWithFlagButton;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\buttonpanel\JLbsButtonPanelSimpleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */