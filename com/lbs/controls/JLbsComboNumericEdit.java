/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComboNumericEdit;
/*    */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*    */ import com.lbs.controls.numericedit.JLbsNumericEditWithCalculator;
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
/*    */ public class JLbsComboNumericEdit
/*    */   extends JLbsComboEdit
/*    */   implements ILbsComboNumericEdit
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsComboNumericEdit() {
/* 25 */     super((JLbsMaskedEdit)new JLbsNumericEditWithCalculator(), 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsComboNumericEdit(boolean bReal) {
/* 36 */     super((JLbsMaskedEdit)new JLbsNumericEditWithCalculator(bReal), 1);
/*    */   }
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
/*    */   public JLbsComboNumericEdit(boolean bReal, Number nNumber) {
/* 50 */     super((JLbsMaskedEdit)new JLbsNumericEditWithCalculator(bReal, nNumber), 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComboNumericEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */