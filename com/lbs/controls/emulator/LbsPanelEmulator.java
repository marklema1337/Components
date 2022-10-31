/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsPanelEmulator
/*    */   extends LbsComponentEmulator
/*    */   implements ILbsPanel
/*    */ {
/*    */   private String m_Header;
/*    */   private boolean m_Collapsed = false;
/*    */   
/*    */   public void setHeader(String header) {
/* 21 */     this.m_Header = header;
/*    */   }
/*    */   
/*    */   public boolean isCollapsed() {
/* 25 */     return this.m_Collapsed;
/*    */   }
/*    */   
/*    */   public void collapse() {
/* 29 */     this.m_Collapsed = true;
/*    */   }
/*    */   
/*    */   public void expand() {
/* 33 */     this.m_Collapsed = false;
/*    */   }
/*    */   
/*    */   public void setHeader(String header, ILbsPanel.IImageSupplier imageSupplier) {
/* 37 */     this.m_Header = header;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsPanelEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */