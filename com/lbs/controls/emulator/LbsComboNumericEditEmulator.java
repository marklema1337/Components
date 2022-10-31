/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComboNumericEdit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsComboNumericEditEmulator
/*    */   extends LbsComboEditEmulator
/*    */   implements ILbsComboNumericEdit
/*    */ {
/*    */   public LbsComboNumericEditEmulator() {
/* 18 */     super(new LbsNumericEditEmulator());
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsComboNumericEditEmulator(boolean bReal) {
/* 23 */     super(new LbsNumericEditEmulator(bReal));
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsComboNumericEditEmulator(boolean bReal, Number nNumber) {
/* 28 */     super(new LbsNumericEditEmulator(bReal, nNumber));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsComboNumericEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */