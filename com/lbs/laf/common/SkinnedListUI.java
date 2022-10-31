/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.ListModel;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicListUI;
/*     */ import javax.swing.plaf.basic.ComboPopup;
/*     */ 
/*     */ 
/*     */ public class SkinnedListUI
/*     */   extends BasicListUI
/*     */ {
/*     */   private static boolean FIX_NEEDED = false;
/*     */   public static final int SCROLLBAR_WIDTH = 20;
/*     */   protected int m_ScrollBarWidth;
/*     */   protected boolean m_LeftToRight;
/*     */   
/*     */   static {
/*  25 */     String version = System.getProperty("java.version");
/*     */     
/*  27 */     char minor = (version.length() > 2) ? 
/*  28 */       version.charAt(2) : Character
/*  29 */       .MIN_VALUE;
/*  30 */     char point = (version.length() > 4) ? 
/*  31 */       version.charAt(4) : Character
/*  32 */       .MIN_VALUE;
/*     */     
/*  34 */     FIX_NEEDED = !(point == '\001' && minor == '\004');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentUI createUI(JComponent list) {
/*  42 */     return new SkinnedListUI();
/*     */   }
/*     */ 
/*     */   
/*     */   protected JComponent getOwner(int[] scrollBarWidth) {
/*  47 */     scrollBarWidth[0] = 0;
/*  48 */     JComponent p = (JComponent)this.list.getParent();
/*  49 */     if (p instanceof javax.swing.JViewport) {
/*     */       
/*  51 */       p = (JComponent)p.getParent();
/*     */       
/*  53 */       if (p instanceof JScrollPane) {
/*     */         
/*  55 */         JScrollPane sp = (JScrollPane)p;
/*  56 */         if (sp.getVerticalScrollBar().isVisible()) {
/*     */           
/*  58 */           JScrollBar bar = sp.getVerticalScrollBar();
/*  59 */           scrollBarWidth[0] = (bar.getUI().getPreferredSize(bar)).width;
/*     */         } 
/*  61 */         p = (JComponent)p.getParent();
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     if (p instanceof ComboPopup) {
/*     */       
/*  67 */       ComboPopup cp = (ComboPopup)p;
/*  68 */       if (cp instanceof JComponent) {
/*  69 */         return (JComponent)cp;
/*     */       }
/*     */     } 
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  77 */     this.m_ScrollBarWidth = 0;
/*  78 */     this.m_LeftToRight = true;
/*     */     
/*  80 */     int[] barWidth = new int[1];
/*  81 */     JComponent cb = getOwner(barWidth);
/*     */     
/*  83 */     if (cb != null) {
/*     */       
/*  85 */       this.m_LeftToRight = cb.getComponentOrientation().isLeftToRight();
/*  86 */       this.m_ScrollBarWidth = barWidth[0];
/*     */     } 
/*     */     
/*  89 */     super.paint(g, c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer<Object> cellRenderer, ListModel<Object> dataModel, ListSelectionModel selModel, int leadIndex) {
/*  95 */     if (!FIX_NEEDED) {
/*     */       
/*  97 */       super.paintCell(g, row, rowBounds, cellRenderer, dataModel, selModel, leadIndex);
/*     */       
/*     */       return;
/*     */     } 
/* 101 */     Rectangle r = new Rectangle(rowBounds);
/*     */     
/* 103 */     if (!this.m_LeftToRight) {
/* 104 */       r.width -= this.m_ScrollBarWidth;
/*     */     }
/* 106 */     super.paintCell(g, row, r, cellRenderer, dataModel, selModel, leadIndex);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedListUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */