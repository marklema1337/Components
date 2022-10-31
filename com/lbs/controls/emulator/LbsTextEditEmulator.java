/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTextEdit;
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
/*    */ public class LbsTextEditEmulator
/*    */   extends LbsMaskedEditEmulator
/*    */   implements ILbsTextEdit
/*    */ {
/*    */   private int m_EditStyle;
/*    */   
/*    */   public LbsTextEditEmulator() {}
/*    */   
/*    */   public LbsTextEditEmulator(int iMaxLength) {
/* 24 */     setTextLimit(iMaxLength);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEditStyle() {
/* 29 */     return this.m_EditStyle;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEditStyle(int style) {
/* 34 */     this.m_EditStyle = style;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsTextEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */