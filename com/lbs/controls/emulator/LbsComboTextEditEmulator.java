/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComboTextEdit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsComboTextEditEmulator
/*    */   extends LbsComboEditEmulator
/*    */   implements ILbsComboTextEdit
/*    */ {
/*    */   public LbsComboTextEditEmulator() {
/* 18 */     super(new LbsTextEditEmulator());
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsComboTextEditEmulator(int iMaxLength) {
/* 23 */     super(new LbsTextEditEmulator(iMaxLength));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsComboTextEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */