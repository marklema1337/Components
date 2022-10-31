/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsImageEmulator
/*    */   extends LbsLabelEmulator
/*    */   implements ILbsImage
/*    */ {
/*    */   boolean m_Restricted;
/*    */   
/*    */   public boolean isRestricted() {
/* 20 */     return this.m_Restricted;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRestricted(boolean restricted) {
/* 25 */     this.m_Restricted = restricted;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsImageEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */