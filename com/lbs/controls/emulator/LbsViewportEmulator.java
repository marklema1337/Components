/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsViewport;
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
/*    */ 
/*    */ public class LbsViewportEmulator
/*    */   extends LbsComponentEmulator
/*    */   implements ILbsViewport
/*    */ {
/*    */   public boolean isHorizontallyFixed() {
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVerticallyFixed() {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   public void setHorizontallyFixed(boolean fixed) {}
/*    */   
/*    */   public void setVerticallyFixed(boolean fixed) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsViewportEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */