/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsSubMenu;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsSubMenuEmulator
/*    */   extends LbsComponentEmulator
/*    */   implements ILbsSubMenu
/*    */ {
/*    */   public void addItem(Object item) {
/* 15 */     add((LbsComponentEmulator)item, -1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsSubMenuEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */