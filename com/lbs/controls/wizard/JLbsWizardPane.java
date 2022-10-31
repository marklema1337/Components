/*    */ package com.lbs.controls.wizard;
/*    */ 
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class JLbsWizardPane
/*    */   extends JPanel
/*    */   implements ILbsWizardPane
/*    */ {
/*    */   public boolean canGoCancel() {
/* 15 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canGoNext() {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canGoPrevious() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFinalPage() {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize() {}
/*    */ 
/*    */   
/*    */   public boolean finalize(boolean cancelled) {
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\wizard\JLbsWizardPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */