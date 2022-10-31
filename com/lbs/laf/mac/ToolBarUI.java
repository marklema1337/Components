/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.DefaultSkinnableTheme;
/*    */ import com.lbs.laf.common.SkinImage;
/*    */ import com.lbs.laf.common.SkinnedToolbarUI;
/*    */ import javax.swing.JComponent;
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
/*    */ public class ToolBarUI
/*    */   extends SkinnedToolbarUI
/*    */ {
/* 20 */   private static ToolBarUI ms_ToolbarUI = new ToolBarUI();
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 24 */     return (ComponentUI)ms_ToolbarUI;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkin() {
/* 29 */     return DefaultSkinnableTheme.getSkinImage(getClass(), "toolbarback.png", 1, 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\ToolBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */