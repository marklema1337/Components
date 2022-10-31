/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.Timer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsProgressAnim
/*     */   extends JLbsProgressBar
/*     */   implements ActionListener
/*     */ {
/*     */   private boolean m_Animate = false;
/*     */   private boolean m_Forward = true;
/*  19 */   private int m_BlockSize = 40;
/*     */   
/*     */   private Timer m_Timer;
/*     */   
/*     */   public boolean isAnimate() {
/*  24 */     return this.m_Animate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnimate(boolean b) {
/*  29 */     if (this.m_Animate == b)
/*  30 */       return;  if (b) {
/*     */       
/*  32 */       if (this.m_Timer == null) {
/*     */         
/*  34 */         this.m_Timer = new Timer(250, this);
/*  35 */         this.m_Timer.setRepeats(true);
/*     */       } 
/*  37 */       this.m_Timer.start();
/*     */ 
/*     */     
/*     */     }
/*  41 */     else if (this.m_Timer != null) {
/*  42 */       this.m_Timer.stop();
/*     */     } 
/*  44 */     this.m_Animate = b;
/*  45 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  50 */     if (e.getSource() == this.m_Timer) {
/*  51 */       doAnimate();
/*     */     }
/*     */   }
/*     */   
/*     */   public void doAnimate() {
/*  56 */     if (this.m_Timer != null)
/*  57 */       this.m_Timer.stop(); 
/*  58 */     if (this.m_Forward) {
/*     */       
/*  60 */       this.m_Percent++;
/*  61 */       if (this.m_Percent > 100.0D)
/*     */       {
/*  63 */         this.m_Forward = false;
/*  64 */         this.m_Percent = 99.0D;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  69 */       this.m_Percent--;
/*  70 */       if (this.m_Percent < 0.0D) {
/*     */         
/*  72 */         this.m_Forward = true;
/*  73 */         this.m_Percent = 1.0D;
/*     */       } 
/*     */     } 
/*  76 */     repaint();
/*  77 */     if (this.m_Timer != null) {
/*  78 */       this.m_Timer.start();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getBlockSize() {
/*  83 */     return this.m_BlockSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockSize(int i) {
/*  88 */     i = Math.max(4, i);
/*  89 */     if (this.m_BlockSize != i) {
/*     */       
/*  91 */       this.m_BlockSize = i;
/*  92 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Point getBarPosition(int width) {
/*  98 */     int barSize = Math.min(this.m_BlockSize, width / 2);
/*  99 */     int restSize = width - barSize;
/* 100 */     int pos = (int)(restSize * this.m_Percent / 100.0D);
/* 101 */     return new Point(pos, pos + barSize);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsProgressAnim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */