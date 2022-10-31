/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.event.FocusEvent;
/*    */ import java.awt.event.FocusListener;
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
/*    */ public class JLbsUpdateFocusAdapter
/*    */   implements FocusListener
/*    */ {
/*    */   public void focusLost(FocusEvent feEvt) {
/* 22 */     InternalUpdate(feEvt.getSource());
/*    */   }
/*    */   
/*    */   public void focusGained(FocusEvent feEvt) {
/* 26 */     InternalUpdate(feEvt.getSource());
/*    */   }
/*    */ 
/*    */   
/*    */   private void InternalUpdate(Object obj) {
/* 31 */     if (obj instanceof Component)
/* 32 */       ((Component)obj).repaint(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsUpdateFocusAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */