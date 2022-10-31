/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsButton;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsButtonEmulator
/*    */   extends LbsAbstractButtonEmulator
/*    */   implements ILbsButton
/*    */ {
/* 18 */   private int m_Mnemonic = 0;
/*    */   
/*    */   private boolean m_DefaultCapable = true;
/*    */   
/*    */   public int getMnemonic() {
/* 23 */     return this.m_Mnemonic;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDefaultButton() {
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDefaultCapable() {
/* 33 */     return this.m_DefaultCapable;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDefaultCapable(boolean defaultCapable) {
/* 38 */     this.m_DefaultCapable = defaultCapable;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMnemonic(char mnemonic) {
/* 43 */     this.m_Mnemonic = mnemonic;
/*    */   }
/*    */   
/*    */   public void setHighlightIcon(Icon icon) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsButtonEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */