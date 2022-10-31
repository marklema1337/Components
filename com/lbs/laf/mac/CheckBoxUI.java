/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.SkinnedCheckBoxIcon;
/*    */ import com.lbs.laf.common.SkinnedCheckBoxUI;
/*    */ import com.lbs.util.JLbsConstants;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckBoxUI
/*    */   extends SkinnedCheckBoxUI
/*    */ {
/* 23 */   private final SkinnedCheckBoxIcon ms_SkinnedIcon = new SkinnedCheckBoxIcon(DefaultTheme.getSkinImage(CheckBoxUI.class, 
/* 24 */         JLbsConstants.DESKTOP_MODE ? 
/* 25 */         "checkboxdesktop.png" : 
/* 26 */         "checkbox.png", 8, 0));
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 30 */     return (ComponentUI)new CheckBoxUI();
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinnedCheckBoxIcon getIcon() {
/* 35 */     return this.ms_SkinnedIcon;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\CheckBoxUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */