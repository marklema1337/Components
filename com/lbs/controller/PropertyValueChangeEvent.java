/*    */ package com.lbs.controller;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyValueChangeEvent<T extends Controller<?, ?>>
/*    */ {
/*    */   private T m_Source;
/*    */   private String m_PropertyName;
/*    */   private int m_UniqueUIId;
/*    */   private ArrayList<Integer> m_GroupIds;
/*    */   private Object m_OldValue;
/*    */   
/*    */   public PropertyValueChangeEvent(T source, String propertyName, int uniqueUIId, ArrayList<Integer> groupIds, Object propertyValue) {
/* 16 */     this.m_Source = source;
/* 17 */     this.m_PropertyName = propertyName;
/* 18 */     this.m_UniqueUIId = uniqueUIId;
/* 19 */     this.m_GroupIds = groupIds;
/* 20 */     this.m_OldValue = propertyValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getSource() {
/* 25 */     return this.m_Source;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 30 */     return this.m_PropertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getGroupIds() {
/* 35 */     return this.m_GroupIds;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getOldValue() {
/* 40 */     return this.m_OldValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUniqueUIId() {
/* 45 */     return this.m_UniqueUIId;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyValueChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */