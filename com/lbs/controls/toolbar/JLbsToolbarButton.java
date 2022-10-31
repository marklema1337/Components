/*     */ package com.lbs.controls.toolbar;
/*     */ 
/*     */ import com.lbs.util.ILbsCaptionTag;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.border.BevelBorder;
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
/*     */ class JLbsToolbarButton
/*     */   extends JButton
/*     */   implements ILbsCaptionTag, MouseListener, FocusListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/* 251 */   public static BevelBorder m_normal = new BevelBorder(0);
/* 252 */   public static BevelBorder m_pressed = new BevelBorder(1);
/*     */ 
/*     */   
/*     */   private int m_Tag;
/*     */ 
/*     */   
/*     */   public JLbsToolbarButton() {
/* 259 */     setContentAreaFilled(false);
/* 260 */     setBorder(m_normal);
/* 261 */     setBorderPainted(false);
/* 262 */     reinitialize();
/* 263 */     addMouseListener(this);
/* 264 */     addFocusListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 269 */     setToolTipText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 274 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/* 279 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reinitialize() {
/* 284 */     putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 295 */     setBorder(m_pressed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/* 306 */     setBorderPainted(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 312 */     if (getModel().isPressed()) {
/* 313 */       setBorderPainted(true);
/*     */     } else {
/* 315 */       setBorder(m_normal);
/* 316 */       setBorderPainted(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 328 */     setBorder(m_normal);
/* 329 */     setBorderPainted(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\toolbar\JLbsToolbarButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */