/*    */ package com.lbs.controls.emulator;
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
/*    */ public class LbsToggleButtonEmulator
/*    */   extends LbsAbstractButtonEmulator
/*    */ {
/*    */   protected void setPressed(boolean b) {
/* 17 */     if (isPressed() == b || !isEnabled()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 22 */     if (!b)
/*    */     {
/* 24 */       setSelected(!isSelected());
/*    */     }
/* 26 */     super.setPressed(b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsToggleButtonEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */