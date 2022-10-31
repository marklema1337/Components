/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.SkinImage;
/*    */ import com.lbs.laf.common.SkinnedMenuItem;
/*    */ import com.lbs.laf.common.SkinnedMenuUI;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MenuUI
/*    */   extends SkinnedMenuUI
/*    */ {
/* 23 */   static SkinnedMenuItem ms_Skin = new SkinnedMenuItem(DefaultTheme.getImagePath(MenuItemUI.class, "menu.png"), 0, 1, 2, 3, 2);
/* 24 */   static SkinImage ms_TopSkin = new SkinImage(DefaultTheme.getImagePath(MenuItemUI.class, "menutop.png"), 3, 1, 1, 6, 2);
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 28 */     return (ComponentUI)new MenuUI();
/*    */   }
/*    */ 
/*    */   
/*    */   protected SkinnedMenuItem getSkin() {
/* 33 */     return ms_Skin;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SkinImage getTopSkin() {
/* 38 */     return ms_TopSkin;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
/* 43 */     super.paintBackground(g, menuItem, bgColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\MenuUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */