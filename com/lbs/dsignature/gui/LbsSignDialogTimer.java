/*    */ package com.lbs.dsignature.gui;
/*    */ 
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ public class LbsSignDialogTimer
/*    */   extends TimerTask {
/*    */   Timer m_Timer;
/*  9 */   int m_Count = 0;
/*    */   
/*    */   LbsSignDialog m_Dlg;
/*    */ 
/*    */   
/*    */   public LbsSignDialogTimer() {}
/*    */ 
/*    */   
/*    */   public LbsSignDialogTimer(Timer timer, LbsSignDialog dlg) {
/* 18 */     this.m_Timer = timer;
/* 19 */     this.m_Dlg = dlg;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void toDo() {}
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 28 */     return this.m_Count;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 34 */     toDo();
/* 35 */     if (this.m_Count > 60) {
/*    */       
/* 37 */       this.m_Dlg.m_CertTreePinPanel.clearFields();
/* 38 */       this.m_Count = 0;
/* 39 */       toDo();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\gui\LbsSignDialogTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */