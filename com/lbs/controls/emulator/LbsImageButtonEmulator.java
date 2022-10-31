/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsImageButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsImageButtonEmulator
/*    */   extends LbsButtonEmulator
/*    */   implements ILbsImageButton
/*    */ {
/*    */   public LbsImageButtonEmulator() {
/* 18 */     reinitialize();
/*    */   }
/*    */ 
/*    */   
/*    */   public void reinitialize() {
/* 23 */     putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsImageButtonEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */