/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.SkinImage;
/*    */ import com.lbs.laf.common.SkinnedButtonUI;
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
/*    */ public class ButtonDesktopUI
/*    */   extends SkinnedButtonUI
/*    */ {
/* 23 */   private static ButtonDesktopUI ms_Self = new ButtonDesktopUI();
/*    */ 
/*    */   
/*    */   private static SkinImage ms_Button;
/*    */   
/*    */   private static SkinImage ms_LookupButton;
/*    */   
/*    */   private static SkinImage ms_Toolbar;
/*    */   
/*    */   private static SkinImage ms_SaveButton;
/*    */   
/*    */   private static SkinImage ms_CloseButton;
/*    */   
/*    */   private static SkinImage ms_ApplyButton;
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 40 */     SkinnedButtonUI.createUI(c);
/* 41 */     return (ComponentUI)ms_Self;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized SkinImage getSkinToolbar() {
/* 46 */     if (ms_Toolbar == null)
/* 47 */       ms_Toolbar = new SkinImage(DefaultTheme.getImagePath(ButtonDesktopUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 48 */             "toolbardesktop.png" : 
/* 49 */             "toolbar.png"), 8, 4); 
/* 50 */     return ms_Toolbar;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized SkinImage getSkinButton() {
/* 55 */     if (ms_Button == null)
/* 56 */       ms_Button = new SkinImage(DefaultTheme.getImagePath(ButtonDesktopUI.class, "button.png"), 9, 0); 
/* 57 */     return ms_Button;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized SkinImage getSkinSaveButton() {
/* 62 */     if (ms_SaveButton == null)
/*    */     {
/* 64 */       ms_SaveButton = new SkinImage(DefaultTheme.getImagePath(ButtonDesktopUI.class, "buttonsave.png"), 9, 0);
/*    */     }
/* 66 */     return ms_SaveButton;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized SkinImage getSkinCloseButton() {
/* 71 */     if (ms_CloseButton == null) {
/* 72 */       ms_CloseButton = new SkinImage(DefaultTheme.getImagePath(ButtonDesktopUI.class, "buttoncancel.png"), 9, 0);
/*    */     }
/* 74 */     return ms_CloseButton;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized SkinImage getSkinApplyButton() {
/* 79 */     if (ms_ApplyButton == null) {
/* 80 */       ms_ApplyButton = new SkinImage(DefaultTheme.getImagePath(ButtonDesktopUI.class, "buttonapply.png"), 9, 0);
/*    */     }
/* 82 */     return ms_ApplyButton;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized SkinImage getLookupSkinButton() {
/* 88 */     if (ms_LookupButton == null)
/* 89 */       ms_LookupButton = new SkinImage(DefaultTheme.getImagePath(ButtonDesktopUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 90 */             "lookupbuttondesktop.png" : 
/* 91 */             "lookupbutton.png"), 9, 0); 
/* 92 */     return ms_LookupButton;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\ButtonDesktopUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */