/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsEditorPane;
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
/*    */ public class LbsEditorPaneEmulator
/*    */   extends LbsTextComponentEmulator
/*    */   implements ILbsEditorPane
/*    */ {
/*    */   private String m_ContentType;
/*    */   private int m_TextLimit;
/*    */   
/*    */   public String getContentType() {
/* 22 */     return this.m_ContentType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scrollToReference(String reference) {}
/*    */ 
/*    */   
/*    */   public void setContentType(String type) {
/* 31 */     this.m_ContentType = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTextLimit(int limit) {
/* 36 */     this.m_TextLimit = limit;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTextLimit() {
/* 41 */     return this.m_TextLimit;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsEditorPaneEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */