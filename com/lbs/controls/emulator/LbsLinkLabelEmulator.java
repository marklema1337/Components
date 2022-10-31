/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsLinkLabel;
/*    */ import java.awt.event.MouseListener;
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
/*    */ public class LbsLinkLabelEmulator
/*    */   extends LbsLabelEmulator
/*    */   implements ILbsLinkLabel
/*    */ {
/*    */   private MouseListener m_Listener;
/*    */   
/*    */   public MouseListener getClickListener() {
/* 22 */     return this.m_Listener;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClickListener(MouseListener clickListener) {
/* 27 */     this.m_Listener = clickListener;
/*    */   }
/*    */   
/*    */   public void updatePreferredSize() {}
/*    */   
/*    */   public void updateSize() {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsLinkLabelEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */