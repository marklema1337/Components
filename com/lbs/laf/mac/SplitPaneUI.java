/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.SkinnedSplitPaneUI;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ 
/*    */ 
/*    */ public class SplitPaneUI
/*    */   extends SkinnedSplitPaneUI
/*    */ {
/*    */   public static ComponentUI createUI(JComponent c) {
/* 12 */     return (ComponentUI)new SplitPaneUI();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\SplitPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */