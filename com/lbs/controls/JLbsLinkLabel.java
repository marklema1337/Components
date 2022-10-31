/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsLinkLabel;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ public class JLbsLinkLabel
/*     */   extends JLbsLabel
/*     */   implements MouseListener, ILbsLinkLabel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   private static final Color DOWN = Color.RED;
/*  23 */   private static final Color HOVER = Color.BLUE;
/*     */   
/*     */   private static final int ST_NONE = 0;
/*     */   
/*     */   private static final int ST_HOVER = 1;
/*     */   private static final int ST_CLICKED = 2;
/*     */   private int m_State;
/*     */   private MouseListener m_ClickListener;
/*     */   
/*     */   public JLbsLinkLabel() {
/*  33 */     setForeground(Color.BLACK);
/*  34 */     setCursor(new Cursor(12));
/*  35 */     setOpaque(false);
/*  36 */     addMouseListener(this);
/*  37 */     setBorder(new EmptyBorder(0, 4, 0, 4));
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/*  42 */     if (!isEnabled())
/*     */       return; 
/*  44 */     if (this.m_ClickListener != null) {
/*  45 */       this.m_ClickListener.mouseClicked(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updatePreferredSize() {
/*  50 */     Dimension dim = getPreferredSize();
/*  51 */     if (dim != null) {
/*     */       
/*  53 */       dim.width += 8;
/*  54 */       setPreferredSize(dim);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateSize() {
/*  60 */     Dimension dim = getSize();
/*     */ 
/*     */ 
/*     */     
/*  64 */     if (dim.height == 0) {
/*  65 */       dim.height = 20;
/*     */     }
/*  67 */     int textLength = 0;
/*  68 */     if (!JLbsStringUtil.isEmpty(getText())) {
/*  69 */       textLength = getText().length() * 6;
/*     */     }
/*  71 */     dim.width = textLength;
/*     */     
/*  73 */     setSize(dim);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/*  78 */     if (!isEnabled())
/*     */       return; 
/*  80 */     Component c = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
/*  81 */     if (c instanceof javax.swing.JRootPane)
/*     */       return; 
/*  83 */     this.m_State = 1;
/*  84 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/*  89 */     if (!isEnabled())
/*     */       return; 
/*  91 */     this.m_State = 0;
/*  92 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  97 */     if (!isEnabled())
/*     */       return; 
/*  99 */     this.m_State = 2;
/* 100 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 105 */     if (!isEnabled())
/*     */       return; 
/* 107 */     if (this.m_State == 2)
/* 108 */       this.m_State = 1; 
/* 109 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 114 */     Rectangle rect = JLbsControlHelper.getClientRectInsideBorder(this);
/* 115 */     if (isOpaque()) {
/*     */       
/* 117 */       g.setColor(getBackground());
/* 118 */       g.fillRect(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
/*     */     } 
/* 120 */     if (getIcon() != null)
/*     */     {
/* 122 */       getIcon().paintIcon(this, g, rect.x, (rect.height - getIcon().getIconHeight()) / 2);
/*     */     }
/* 124 */     String text = getText();
/* 125 */     if (text != null) {
/*     */       Rectangle txtRect;
/* 127 */       switch (this.m_State) {
/*     */         
/*     */         case 1:
/* 130 */           g.setColor(HOVER);
/*     */           break;
/*     */         case 2:
/* 133 */           g.setColor(DOWN);
/*     */           break;
/*     */         default:
/* 136 */           g.setColor(getForeground());
/*     */           break;
/*     */       } 
/*     */       
/* 140 */       if (getIcon() != null) {
/* 141 */         txtRect = new Rectangle(rect.x + getIcon().getIconWidth() + getIconTextGap(), rect.y, rect.width, rect.height);
/*     */       } else {
/* 143 */         txtRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
/* 144 */       }  Rectangle result = JLbsControlHelper.drawStringVCentered(this, g, txtRect, text, getHorizontalAlignment());
/* 145 */       int bottom = result.y - 1;
/* 146 */       g.drawLine(result.x, bottom, result.x + result.width, bottom);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener getClickListener() {
/* 152 */     return this.m_ClickListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClickListener(MouseListener clickListener) {
/* 157 */     this.m_ClickListener = clickListener;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsLinkLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */