/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ public class JLbsInfoLabel
/*     */   extends JLabel
/*     */   implements MouseListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  18 */   private static final Color BORDER = new Color(172, 168, 153);
/*  19 */   private static final Color NORMAL = new Color(144, 153, 174);
/*  20 */   private static final Color HOVER = new Color(255, 238, 194);
/*     */   
/*     */   private static final int ST_NONE = 0;
/*     */   
/*     */   private static final int ST_HOVER = 1;
/*     */   private static final int ST_CLICKED = 2;
/*     */   private int m_State;
/*     */   private MouseListener m_ClickListener;
/*     */   
/*     */   public JLbsInfoLabel() {
/*  30 */     setPreferredSize(new Dimension(20, 20));
/*  31 */     addMouseListener(this);
/*  32 */     setForeground(Color.WHITE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePreferredSize() {
/*  37 */     Dimension dim = getPreferredSize();
/*  38 */     if (dim != null) {
/*     */       
/*  40 */       dim.width += 8;
/*  41 */       dim.height += 8;
/*  42 */       setPreferredSize(dim);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/*  48 */     if (!isEnabled())
/*  49 */       return;  this.m_State = 2;
/*  50 */     repaint();
/*  51 */     if (this.m_ClickListener != null) {
/*  52 */       this.m_ClickListener.mouseClicked(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/*  57 */     if (!isEnabled())
/*  58 */       return;  Component c = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
/*  59 */     if (c instanceof javax.swing.JRootPane)
/*  60 */       return;  this.m_State = 1;
/*  61 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/*  66 */     if (!isEnabled())
/*  67 */       return;  this.m_State = 0;
/*  68 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  81 */     Rectangle rect = JLbsControlHelper.getClientRectInsideBorder(this);
/*  82 */     g.setColor(BORDER);
/*  83 */     g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
/*  84 */     g.setColor((this.m_State != 0) ? HOVER : NORMAL);
/*  85 */     g.fillRect(rect.x + 1, rect.y + 1, rect.width - 2, rect.height - 2);
/*  86 */     String text = getText();
/*  87 */     if (text != null) {
/*     */       
/*  89 */       g.setColor((this.m_State != 0) ? Color.BLACK : Color.WHITE);
/*  90 */       g.setFont(getFont());
/*  91 */       rect = JLbsControlHelper.getClientRect(this);
/*  92 */       JLbsControlHelper.drawStringVCentered(this, g, new Rectangle(rect.x + 6, rect.y + 2, rect.width - 4, rect.height - 8), text, getHorizontalAlignment());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener getClickListener() {
/*  98 */     return this.m_ClickListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClickListener(MouseListener clickListener) {
/* 103 */     this.m_ClickListener = clickListener;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsInfoLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */