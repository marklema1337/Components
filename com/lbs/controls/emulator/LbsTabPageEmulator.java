/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTabPage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsTabPageEmulator
/*    */   extends LbsPanelEmulator
/*    */   implements ILbsTabPage
/*    */ {
/* 16 */   private int m_PageIdx = 0;
/*    */   
/*    */   private String m_Caption;
/*    */   
/*    */   public String getCaption() {
/* 21 */     return this.m_Caption;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPageIndex() {
/* 26 */     return this.m_PageIdx;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCaption(String caption) {
/* 31 */     this.m_Caption = caption;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPageIndex(int value) {
/* 36 */     this.m_PageIdx = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsTabPageEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */