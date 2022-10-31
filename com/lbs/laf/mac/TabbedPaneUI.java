/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTabbedPane;
/*    */ import com.lbs.laf.common.DefaultSkinnableTheme;
/*    */ import com.lbs.laf.common.SkinImage;
/*    */ import com.lbs.laf.common.SkinnedTabbedPane;
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
/*    */ 
/*    */ 
/*    */ public class TabbedPaneUI
/*    */   extends SkinnedTabbedPane
/*    */ {
/*    */   private ILbsTabbedPane component;
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 30 */     TabbedPaneUI ui = new TabbedPaneUI();
/* 31 */     if (c instanceof ILbsTabbedPane)
/* 32 */       ui.component = (ILbsTabbedPane)c; 
/* 33 */     return (ComponentUI)ui;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkinTop() {
/* 38 */     return DefaultSkinnableTheme.getSkinImage(TabbedPaneUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 39 */         "tabpagetopheaderdesktop.png" : 
/* 40 */         "tabpagetopheader.png", 4, 6);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkinLeft() {
/* 45 */     return DefaultSkinnableTheme.getSkinImage(TabbedPaneUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 46 */         "tabpageleftheaderdesktop.png" : 
/* 47 */         "tabpageleftheader.png", 4, 6);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkinRight() {
/* 52 */     return DefaultSkinnableTheme.getSkinImage(TabbedPaneUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 53 */         "tabpagerightheaderdesktop.png" : 
/* 54 */         "tabpagerightheader.png", 4, 6);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkinBottom() {
/* 59 */     return DefaultSkinnableTheme.getSkinImage(TabbedPaneUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 60 */         "tabpagebotheaderdesktop.png" : 
/* 61 */         "tabpagebotheader.png", 4, 6);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkinBorderBottom() {
/* 66 */     return DefaultSkinnableTheme.getSkinImage(TabbedPaneUI.class, "tabpagebot.png", 1, 3, 0, 3, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkinImage getSkinBorderRight() {
/* 71 */     return DefaultSkinnableTheme.getSkinImage(TabbedPaneUI.class, "tabpageright.png", 1, 0, 3, 0, 3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
/* 77 */     if (this.component == null || !this.component.isWizardMode()) {
/* 78 */       return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
/*    */     }
/* 80 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\TabbedPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */