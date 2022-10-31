/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.gwt.library.shared.facet.PropertyState;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupStateChangeEvent<T extends Controller<?, ?>>
/*    */ {
/*    */   private T m_Source;
/*    */   private Integer m_GroupId;
/*    */   private PropertyState m_NewState;
/*    */   
/*    */   public GroupStateChangeEvent(T source, Integer groupId, PropertyState newState) {
/* 17 */     this.m_Source = source;
/* 18 */     this.m_GroupId = groupId;
/* 19 */     this.m_NewState = newState;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getSource() {
/* 24 */     return this.m_Source;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getGroupId() {
/* 29 */     return this.m_GroupId;
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyState getNewState() {
/* 34 */     return this.m_NewState;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\GroupStateChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */