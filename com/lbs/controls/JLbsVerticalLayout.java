/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.util.HashMap;
/*     */ import javax.swing.SwingConstants;
/*     */ 
/*     */ 
/*     */ public class JLbsVerticalLayout
/*     */   implements LayoutManager, SwingConstants
/*     */ {
/*     */   public static final int BOTH = 15;
/*     */   private int m_VGap;
/*     */   private int m_Alignment;
/*     */   private int m_Anchor;
/*     */   
/*     */   public JLbsVerticalLayout() {
/*  21 */     this(5, 15, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsVerticalLayout(int vgap) {
/*  26 */     this(vgap, 0, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsVerticalLayout(int vgap, int alignment) {
/*  31 */     this(vgap, alignment, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsVerticalLayout(int vgap, int alignment, int anchor) {
/*  36 */     this.m_VGap = vgap;
/*  37 */     this.m_Alignment = alignment;
/*  38 */     this.m_Anchor = anchor;
/*     */   }
/*     */ 
/*     */   
/*     */   private Dimension layoutSize(Container parent, boolean minimum) {
/*  43 */     Dimension dim = new Dimension(0, 0);
/*     */     
/*  45 */     synchronized (parent.getTreeLock()) {
/*     */       
/*  47 */       int n = parent.getComponentCount();
/*  48 */       for (int i = 0; i < n; i++) {
/*     */         
/*  50 */         Component c = parent.getComponent(i);
/*  51 */         if (c.isVisible()) {
/*     */           
/*  53 */           Dimension d = minimum ? c.getMinimumSize() : c.getPreferredSize();
/*  54 */           dim.width = Math.max(dim.width, d.width);
/*  55 */           dim.height += d.height;
/*  56 */           if (i > 0)
/*  57 */             dim.height += this.m_VGap; 
/*     */         } 
/*     */       } 
/*     */     } 
/*  61 */     Insets insets = parent.getInsets();
/*  62 */     dim.width += insets.left + insets.right + this.m_VGap * 2;
/*  63 */     dim.height += insets.top + insets.bottom + this.m_VGap + this.m_VGap;
/*  64 */     return dim;
/*     */   }
/*     */ 
/*     */   
/*     */   private Dimension getDimension(HashMap<Integer, Dimension> dimensions, int key) {
/*  69 */     return dimensions.get(Integer.valueOf(key));
/*     */   }
/*     */ 
/*     */   
/*     */   public void layoutContainer(Container parent) {
/*  74 */     Insets insets = parent.getInsets();
/*  75 */     synchronized (parent.getTreeLock()) {
/*     */       
/*  77 */       int n = parent.getComponentCount();
/*  78 */       Dimension pd = parent.getSize();
/*  79 */       int y = 0;
/*     */       
/*  81 */       HashMap<Integer, Dimension> dimensions = new HashMap<>(); int i;
/*  82 */       for (i = 0; i < n; i++) {
/*     */         
/*  84 */         Component c = parent.getComponent(i);
/*  85 */         Dimension d = c.getPreferredSize();
/*  86 */         dimensions.put(Integer.valueOf(i), d);
/*  87 */         y += d.height + this.m_VGap;
/*     */       } 
/*  89 */       y -= this.m_VGap;
/*     */       
/*  91 */       if (this.m_Anchor == 1) {
/*  92 */         y = insets.top + this.m_VGap;
/*     */       }
/*  94 */       else if (this.m_Anchor == 0) {
/*  95 */         y = (pd.height - y) / 2;
/*     */       } else {
/*  97 */         y = pd.height - y - insets.bottom;
/*  98 */       }  for (i = 0; i < n; i++) {
/*     */         
/* 100 */         Component c = parent.getComponent(i);
/* 101 */         Dimension d = getDimension(dimensions, i);
/* 102 */         int x = insets.left + this.m_VGap + 1;
/* 103 */         int wid = d.width;
/* 104 */         if (this.m_Alignment == 0) {
/* 105 */           x = (pd.width - d.width) / 2;
/*     */         }
/* 107 */         else if (this.m_Alignment == 4) {
/* 108 */           x = pd.width - d.width - insets.right;
/*     */         }
/* 110 */         else if (this.m_Alignment == 15) {
/* 111 */           wid = pd.width - insets.left - insets.right - 2 * this.m_VGap - 1;
/* 112 */         }  c.setBounds(x, y, wid, d.height);
/* 113 */         y += d.height + this.m_VGap;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension minimumLayoutSize(Container parent) {
/* 120 */     return layoutSize(parent, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension preferredLayoutSize(Container parent) {
/* 125 */     return layoutSize(parent, false);
/*     */   }
/*     */   
/*     */   public void addLayoutComponent(String name, Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(Component comp) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsVerticalLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */