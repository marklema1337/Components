/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.DefaultSkinnableTheme;
/*    */ import com.lbs.laf.common.SkinImage;
/*    */ import com.lbs.laf.common.SkinnedScrollBarUI;
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
/*    */ public class ScrollBarUI
/*    */   extends SkinnedScrollBarUI
/*    */ {
/*    */   public static ComponentUI createUI(JComponent c) {
/* 26 */     return (ComponentUI)new ScrollBarUI();
/*    */   }
/*    */ 
/*    */   
/*    */   protected SkinImage getSkinTrack(boolean bVertical) {
/* 31 */     return DefaultSkinnableTheme.getSkinImage(getClass(), bVertical ? (
/* 32 */         JLbsConstants.DESKTOP_MODE ? 
/* 33 */         "scrolltrackvert.png" : 
/* 34 */         "scrolltrackvert.png") : (
/* 35 */         JLbsConstants.DESKTOP_MODE ? 
/* 36 */         "scrolltrackhorz.png" : 
/* 37 */         "scrolltrackhorz.png"), 1, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SkinImage getSkinThumb(boolean bVertical) {
/* 42 */     return DefaultSkinnableTheme.getSkinImage(getClass(), bVertical ? (
/* 43 */         JLbsConstants.DESKTOP_MODE ? 
/* 44 */         "scrollthumbvertdesktop.png" : 
/* 45 */         "scrollthumbvert.png") : (
/* 46 */         JLbsConstants.DESKTOP_MODE ? 
/* 47 */         "scrollthumbhorzdesktop.png" : 
/* 48 */         "scrollthumbhorz.png"), 4, 6);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SkinImage getSkinGripper(boolean bVertical) {
/* 53 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\ScrollBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */