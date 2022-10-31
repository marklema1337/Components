/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComboTextEdit;
/*    */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*    */ import com.lbs.controls.maskededit.JLbsTextEdit;
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
/*    */ public class JLbsComboTextEdit
/*    */   extends JLbsComboEdit
/*    */   implements ILbsComboTextEdit
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsComboTextEdit() {
/* 25 */     super((JLbsMaskedEdit)new JLbsTextEdit(), 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsComboTextEdit(int iMaxLength) {
/* 33 */     super((JLbsMaskedEdit)new JLbsTextEdit(iMaxLength), 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComboTextEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */