/*     */ package info.clearthought.layout;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingleFiledLayout
/*     */   implements LayoutManager, Serializable
/*     */ {
/*     */   public static final int COLUMN = 0;
/*     */   public static final int ROW = 1;
/*     */   public static final int LEFT = 0;
/*     */   public static final int TOP = 0;
/*     */   public static final int CENTER = 1;
/*     */   public static final int FULL = 2;
/*     */   public static final int BOTTOM = 3;
/*     */   public static final int RIGHT = 4;
/*  87 */   public static int DEFAULT_GAP = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int orientation;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int justification;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int gap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SingleFiledLayout() {
/* 110 */     this(0, 0, DEFAULT_GAP);
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
/*     */ 
/*     */   
/*     */   public SingleFiledLayout(int orientation) {
/* 124 */     this(orientation, 0, DEFAULT_GAP);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SingleFiledLayout(int orientation, int justification, int gap) {
/* 142 */     if (orientation != 1) {
/* 143 */       orientation = 0;
/*     */     }
/* 145 */     if (justification != 1 && justification != 2 && 
/* 146 */       justification != 4) {
/* 147 */       justification = 0;
/*     */     }
/* 149 */     if (gap < 0) {
/* 150 */       gap = 0;
/*     */     }
/*     */     
/* 153 */     this.orientation = orientation;
/* 154 */     this.justification = justification;
/* 155 */     this.gap = gap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void layoutContainer(Container container) {
/* 178 */     Dimension size = container.getSize();
/*     */ 
/*     */     
/* 181 */     Insets inset = container.getInsets();
/*     */ 
/*     */     
/* 184 */     int x = inset.left;
/* 185 */     int y = inset.top;
/*     */ 
/*     */     
/* 188 */     Component[] component = container.getComponents();
/*     */ 
/*     */     
/* 191 */     if (this.orientation == 0) {
/*     */       
/* 193 */       for (int counter = 0; counter < component.length; counter++)
/*     */       {
/*     */         
/* 196 */         Dimension d = component[counter].getPreferredSize();
/*     */ 
/*     */         
/* 199 */         switch (this.justification) {
/*     */           
/*     */           case 0:
/* 202 */             x = inset.left;
/*     */             break;
/*     */           
/*     */           case 1:
/* 206 */             x = (size.width - d.width >> 1) + 
/* 207 */               inset.left - inset.right;
/*     */             break;
/*     */           
/*     */           case 2:
/* 211 */             x = inset.left;
/* 212 */             d.width = size.width - inset.left - inset.right;
/*     */             break;
/*     */           
/*     */           case 4:
/* 216 */             x = size.width - d.width - inset.right;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 221 */         component[counter].setBounds(x, y, d.width, d.height);
/*     */ 
/*     */         
/* 224 */         y += d.height + this.gap;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 229 */       for (int counter = 0; counter < component.length; counter++) {
/*     */ 
/*     */         
/* 232 */         Dimension d = component[counter].getPreferredSize();
/*     */ 
/*     */         
/* 235 */         switch (this.justification) {
/*     */           
/*     */           case 0:
/* 238 */             y = inset.top;
/*     */             break;
/*     */           
/*     */           case 1:
/* 242 */             y = (size.height - d.height >> 1) + 
/* 243 */               inset.top - inset.bottom;
/*     */             break;
/*     */           
/*     */           case 2:
/* 247 */             y = inset.top;
/* 248 */             d.height = size.height - inset.top - inset.bottom;
/*     */             break;
/*     */           
/*     */           case 3:
/* 252 */             y = size.height - d.height - inset.bottom;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 257 */         component[counter].setBounds(x, y, d.width, d.height);
/*     */ 
/*     */         
/* 260 */         x += d.width + this.gap;
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension preferredLayoutSize(Container container) {
/* 279 */     int totalWidth = 0;
/* 280 */     int totalHeight = 0;
/*     */ 
/*     */     
/* 283 */     Component[] component = container.getComponents();
/*     */ 
/*     */     
/* 286 */     if (this.orientation == 0) {
/*     */ 
/*     */       
/* 289 */       for (int counter = 0; counter < component.length; counter++) {
/*     */         
/* 291 */         Dimension d = component[counter].getPreferredSize();
/*     */         
/* 293 */         if (totalWidth < d.width) {
/* 294 */           totalWidth = d.width;
/*     */         }
/* 296 */         totalHeight += d.height + this.gap;
/*     */       } 
/*     */ 
/*     */       
/* 300 */       totalHeight -= this.gap;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 306 */       for (int counter = 0; counter < component.length; counter++) {
/*     */         
/* 308 */         Dimension d = component[counter].getPreferredSize();
/*     */         
/* 310 */         totalWidth += d.width + this.gap;
/*     */         
/* 312 */         if (totalHeight < d.height) {
/* 313 */           totalHeight = d.height;
/*     */         }
/*     */       } 
/*     */       
/* 317 */       totalWidth -= this.gap;
/*     */     } 
/*     */ 
/*     */     
/* 321 */     Insets inset = container.getInsets();
/* 322 */     totalWidth += inset.left + inset.right;
/* 323 */     totalHeight += inset.top + inset.bottom;
/*     */     
/* 325 */     return new Dimension(totalWidth, totalHeight);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension minimumLayoutSize(Container container) {
/* 343 */     int totalWidth = 0;
/* 344 */     int totalHeight = 0;
/*     */ 
/*     */     
/* 347 */     Component[] component = container.getComponents();
/*     */ 
/*     */     
/* 350 */     if (this.orientation == 0) {
/*     */ 
/*     */       
/* 353 */       for (int counter = 0; counter < component.length; counter++) {
/*     */         
/* 355 */         Dimension d = component[counter].getMinimumSize();
/*     */         
/* 357 */         if (totalWidth < d.width) {
/* 358 */           totalWidth = d.width;
/*     */         }
/* 360 */         totalHeight += d.height + this.gap;
/*     */       } 
/*     */ 
/*     */       
/* 364 */       totalHeight -= this.gap;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 370 */       for (int counter = 0; counter < component.length; counter++) {
/*     */         
/* 372 */         Dimension d = component[counter].getMinimumSize();
/*     */         
/* 374 */         totalWidth += d.width + this.gap;
/*     */         
/* 376 */         if (totalHeight < d.height) {
/* 377 */           totalHeight = d.height;
/*     */         }
/*     */       } 
/*     */       
/* 381 */       totalWidth = -this.gap;
/*     */     } 
/*     */ 
/*     */     
/* 385 */     Insets inset = container.getInsets();
/* 386 */     totalWidth += inset.left + inset.right;
/* 387 */     totalHeight += inset.top + inset.bottom;
/*     */     
/* 389 */     return new Dimension(totalWidth, totalHeight);
/*     */   }
/*     */   
/*     */   public void addLayoutComponent(String name, Component component) {}
/*     */   
/*     */   public void removeLayoutComponent(Component component) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\info\clearthought\layout\SingleFiledLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */