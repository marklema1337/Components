/*    */ package com.lbs.controls.groupbox;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsButtonStateChangeEvent
/*    */ {
/*    */   private Object m_GroupBox;
/*    */   private Object m_Button;
/*    */   private int m_OldMask;
/*    */   private int m_NewMask;
/*    */   private int m_ButtonTag;
/*    */   
/*    */   public JLbsButtonStateChangeEvent(Object groupBox, Object btn, int oldMask, int newMask, int btnTag) {
/* 20 */     this.m_GroupBox = groupBox;
/* 21 */     this.m_Button = btn;
/* 22 */     this.m_OldMask = oldMask;
/* 23 */     this.m_NewMask = newMask;
/* 24 */     this.m_ButtonTag = btnTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getButton() {
/* 29 */     return this.m_Button;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getButtonTag() {
/* 34 */     return this.m_ButtonTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getGroupBox() {
/* 39 */     return this.m_GroupBox;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNewMask() {
/* 44 */     return this.m_NewMask;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOldMask() {
/* 49 */     return this.m_OldMask;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsButtonStateChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */