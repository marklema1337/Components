/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsGroupBox;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsGroupBoxEmulator
/*    */   extends LbsPanelEmulator
/*    */   implements ILbsGroupBox
/*    */ {
/*    */   private String m_Text;
/*    */   
/*    */   public String getText() {
/* 20 */     return this.m_Text;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String title) {
/* 25 */     this.m_Text = title;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsGroupBoxEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */