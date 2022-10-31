/*    */ package com.lbs.control.interfaces;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RowAddEvent
/*    */ {
/*    */   private Object m_RowObjectToAdd;
/*    */   private boolean m_Consumed = false;
/*    */   
/*    */   public RowAddEvent(Object rowObjectToAdd) {
/* 11 */     this.m_RowObjectToAdd = rowObjectToAdd;
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
/*    */   public Object getRowObjectToAdd() {
/* 26 */     return this.m_RowObjectToAdd;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\RowAddEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */