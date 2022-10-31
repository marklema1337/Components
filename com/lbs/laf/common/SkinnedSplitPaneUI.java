/*    */ package com.lbs.laf.common;
/*    */ 
/*    */ import javax.swing.plaf.basic.BasicSplitPaneDivider;
/*    */ import javax.swing.plaf.metal.MetalSplitPaneUI;
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
/*    */ public class SkinnedSplitPaneUI
/*    */   extends MetalSplitPaneUI
/*    */ {
/*    */   public BasicSplitPaneDivider createDefaultDivider() {
/* 19 */     return new SkinnedSplitPaneDivider(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedSplitPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */