/*    */ package com.lbs.controller;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChildAddEvent
/*    */ {
/*    */   private boolean m_Consumed = false;
/*    */   private ChildController<?, ?, ?> m_ChildToAdd;
/*    */   
/*    */   public ChildAddEvent(ChildController<?, ?, ?> childToAdd) {
/* 11 */     this.m_ChildToAdd = childToAdd;
/*    */   }
/*    */ 
/*    */   
/*    */   public void consume() {
/* 16 */     this.m_Consumed = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsumed() {
/* 21 */     return this.m_Consumed;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChildController<?, ?, ?> getChildToAdd() {
/* 26 */     return this.m_ChildToAdd;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ChildAddEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */