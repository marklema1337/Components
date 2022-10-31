/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.DefaultSkinnableTheme;
/*    */ import com.lbs.laf.common.SkinImage;
/*    */ import com.lbs.laf.common.SkinnedMenuBarUI;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.border.EmptyBorder;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MenuBarUI
/*    */   extends SkinnedMenuBarUI
/*    */ {
/*    */   private SkinImage m_Skin;
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 23 */     c.setBorder(new EmptyBorder(0, 5, 0, 0));
/* 24 */     return (ComponentUI)new MenuBarUI();
/*    */   }
/*    */   
/*    */   public SkinImage getSkin() {
/* 28 */     if (this.m_Skin == null)
/* 29 */       this.m_Skin = DefaultSkinnableTheme.getSkinImage(getClass(), "menutop.png", 3, 0); 
/* 30 */     return this.m_Skin;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\MenuBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */