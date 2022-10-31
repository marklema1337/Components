/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsGridLayout
/*     */   extends GridLayout
/*     */ {
/*     */   public JLbsGridLayout() {
/*  19 */     this(1, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsGridLayout(int rows, int cols) {
/*  24 */     this(rows, cols, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsGridLayout(int rows, int cols, int hgap, int vgap) {
/*  29 */     super(rows, cols, hgap, vgap);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension preferredLayoutSize(Container parent) {
/*  34 */     synchronized (parent.getTreeLock()) {
/*     */       
/*  36 */       Insets insets = parent.getInsets();
/*  37 */       int ncomponents = parent.getComponentCount();
/*  38 */       int nrows = getRows();
/*  39 */       int ncols = getColumns();
/*  40 */       if (nrows > 0) {
/*     */         
/*  42 */         ncols = (ncomponents + nrows - 1) / nrows;
/*     */       }
/*     */       else {
/*     */         
/*  46 */         nrows = (ncomponents + ncols - 1) / ncols;
/*     */       } 
/*  48 */       int[] w = new int[ncols];
/*  49 */       int[] h = new int[nrows];
/*  50 */       for (int i = 0; i < ncomponents; i++) {
/*     */         
/*  52 */         int r = i / ncols;
/*  53 */         int c = i % ncols;
/*  54 */         Component comp = parent.getComponent(i);
/*  55 */         Dimension d = comp.getPreferredSize();
/*  56 */         if (w[c] < d.width)
/*     */         {
/*  58 */           w[c] = d.width;
/*     */         }
/*  60 */         if (h[r] < d.height)
/*     */         {
/*  62 */           h[r] = d.height;
/*     */         }
/*     */       } 
/*  65 */       int nw = 0;
/*  66 */       for (int j = 0; j < ncols; j++)
/*     */       {
/*  68 */         nw += w[j];
/*     */       }
/*  70 */       int nh = 0;
/*  71 */       for (int k = 0; k < nrows; k++)
/*     */       {
/*  73 */         nh += h[k];
/*     */       }
/*  75 */       return new Dimension(insets.left + insets.right + nw + (ncols - 1) * getHgap(), insets.top + insets.bottom + nh + (nrows - 1) * getVgap());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension minimumLayoutSize(Container parent) {
/*  81 */     synchronized (parent.getTreeLock()) {
/*     */       
/*  83 */       Insets insets = parent.getInsets();
/*  84 */       int ncomponents = parent.getComponentCount();
/*  85 */       int nrows = getRows();
/*  86 */       int ncols = getColumns();
/*  87 */       if (nrows > 0) {
/*     */         
/*  89 */         ncols = (ncomponents + nrows - 1) / nrows;
/*     */       }
/*     */       else {
/*     */         
/*  93 */         nrows = (ncomponents + ncols - 1) / ncols;
/*     */       } 
/*  95 */       int[] w = new int[ncols];
/*  96 */       int[] h = new int[nrows];
/*  97 */       for (int i = 0; i < ncomponents; i++) {
/*     */         
/*  99 */         int r = i / ncols;
/* 100 */         int c = i % ncols;
/* 101 */         Component comp = parent.getComponent(i);
/* 102 */         Dimension d = comp.getMinimumSize();
/* 103 */         if (w[c] < d.width)
/*     */         {
/* 105 */           w[c] = d.width;
/*     */         }
/* 107 */         if (h[r] < d.height)
/*     */         {
/* 109 */           h[r] = d.height;
/*     */         }
/*     */       } 
/* 112 */       int nw = 0;
/* 113 */       for (int j = 0; j < ncols; j++)
/*     */       {
/* 115 */         nw += w[j];
/*     */       }
/* 117 */       int nh = 0;
/* 118 */       for (int k = 0; k < nrows; k++)
/*     */       {
/* 120 */         nh += h[k];
/*     */       }
/* 122 */       return new Dimension(insets.left + insets.right + nw + (ncols - 1) * getHgap(), insets.top + insets.bottom + nh + (nrows - 1) * getVgap());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void layoutContainer(Container parent) {
/* 128 */     synchronized (parent.getTreeLock()) {
/*     */       
/* 130 */       Insets insets = parent.getInsets();
/* 131 */       int ncomponents = parent.getComponentCount();
/* 132 */       int nrows = getRows();
/* 133 */       int ncols = getColumns();
/* 134 */       if (ncomponents == 0) {
/*     */         return;
/*     */       }
/*     */       
/* 138 */       if (nrows > 0) {
/*     */         
/* 140 */         ncols = (ncomponents + nrows - 1) / nrows;
/*     */       }
/*     */       else {
/*     */         
/* 144 */         nrows = (ncomponents + ncols - 1) / ncols;
/*     */       } 
/* 146 */       int hgap = getHgap();
/* 147 */       int vgap = getVgap();
/*     */       
/* 149 */       Dimension pd = preferredLayoutSize(parent);
/* 150 */       double sw = 1.0D * parent.getWidth() / pd.width;
/* 151 */       double sh = 1.0D * parent.getHeight() / pd.height;
/*     */       
/* 153 */       int[] w = new int[ncols];
/* 154 */       int[] h = new int[nrows];
/* 155 */       for (int i = 0; i < ncomponents; i++) {
/*     */         
/* 157 */         int r = i / ncols;
/* 158 */         int j = i % ncols;
/* 159 */         Component comp = parent.getComponent(i);
/* 160 */         Dimension d = comp.getPreferredSize();
/* 161 */         d.width = (int)(sw * d.width);
/* 162 */         d.height = (int)(sh * d.height);
/* 163 */         if (w[j] < d.width)
/*     */         {
/* 165 */           w[j] = d.width;
/*     */         }
/* 167 */         if (h[r] < d.height)
/*     */         {
/* 169 */           h[r] = d.height;
/*     */         }
/*     */       } 
/* 172 */       for (int c = 0, x = insets.left; c < ncols; c++) {
/*     */         
/* 174 */         for (int r = 0, y = insets.top; r < nrows; r++) {
/*     */           
/* 176 */           int j = r * ncols + c;
/* 177 */           if (j < ncomponents)
/*     */           {
/* 179 */             parent.getComponent(j).setBounds(x, y, w[c], h[r]);
/*     */           }
/* 181 */           y += h[r] + vgap;
/*     */         } 
/* 183 */         x += w[c] + hgap;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsGridLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */