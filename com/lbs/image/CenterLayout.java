/*    */ package com.lbs.image;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Insets;
/*    */ import java.awt.LayoutManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CenterLayout
/*    */   implements LayoutManager
/*    */ {
/*    */   public void addLayoutComponent(String name, Component comp) {}
/*    */   
/*    */   public void removeLayoutComponent(Component comp) {}
/*    */   
/*    */   public Dimension preferredLayoutSize(Container container) {
/* 21 */     Component c = container.getComponent(0);
/* 22 */     if (c != null) {
/*    */       
/* 24 */       Dimension size = c.getPreferredSize();
/* 25 */       Insets insets = container.getInsets();
/* 26 */       size.width += insets.left + insets.right;
/* 27 */       size.height += insets.top + insets.bottom;
/* 28 */       return size;
/*    */     } 
/*    */ 
/*    */     
/* 32 */     return new Dimension(0, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension minimumLayoutSize(Container cont) {
/* 38 */     return preferredLayoutSize(cont);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void layoutContainer(Container container) {
/*    */     try {
/* 45 */       Component c = container.getComponent(0);
/*    */       
/* 47 */       c.setSize(c.getPreferredSize());
/* 48 */       Dimension size = c.getSize();
/* 49 */       Dimension containerSize = container.getSize();
/* 50 */       Insets containerInsets = container.getInsets();
/* 51 */       containerSize.width -= containerInsets.left + containerInsets.right;
/* 52 */       containerSize.height -= containerInsets.top + containerInsets.bottom;
/* 53 */       int componentLeft = containerSize.width / 2 - size.width / 2;
/* 54 */       int componentTop = containerSize.height / 2 - size.height / 2;
/* 55 */       componentLeft += containerInsets.left;
/* 56 */       componentTop += containerInsets.top;
/*    */       
/* 58 */       c.setBounds(componentLeft, componentTop, size.width, size.height);
/*    */     }
/* 60 */     catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\image\CenterLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */