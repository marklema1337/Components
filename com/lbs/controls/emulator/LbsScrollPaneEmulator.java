/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComponent;
/*    */ import com.lbs.control.interfaces.ILbsScrollPane;
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
/*    */ public class LbsScrollPaneEmulator
/*    */   extends LbsComponentEmulator
/*    */   implements ILbsScrollPane
/*    */ {
/*    */   public LbsScrollPaneEmulator() {}
/*    */   
/*    */   public LbsScrollPaneEmulator(LbsComponentEmulator child) {
/* 25 */     add(child, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsComponent getInnerComponent() {
/* 30 */     if (getComponentCount() > 0)
/* 31 */       return (ILbsComponent)getChildAt(0); 
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHorizontalScrollBarPolicy() {
/* 37 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getVerticalScrollBarPolicy() {
/* 42 */     return 22;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWheelScrollingEnabled() {
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public void setHorizontalScrollBarPolicy(int policy) {}
/*    */   
/*    */   public void setVerticalScrollBarPolicy(int policy) {}
/*    */   
/*    */   public void setWheelScrollingEnabled(boolean handleWheel) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsScrollPaneEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */