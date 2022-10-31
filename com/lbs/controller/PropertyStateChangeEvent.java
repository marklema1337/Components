/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.gwt.library.shared.facet.PropertyState;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyStateChangeEvent<T extends Controller<?, ?>>
/*    */ {
/*    */   private T m_Source;
/*    */   private String m_PropertyName;
/*    */   private Integer m_UniqueUIId;
/*    */   private ArrayList<Integer> m_GroupIds;
/*    */   private PropertyState m_OldState;
/*    */   private PropertyState m_NewState;
/*    */   
/*    */   public PropertyStateChangeEvent(T source, String propertyName, Integer uniqueUIId, ArrayList<Integer> groupIds, PropertyState newState, PropertyState oldState) {
/* 21 */     this.m_Source = source;
/* 22 */     this.m_PropertyName = propertyName;
/* 23 */     this.m_UniqueUIId = uniqueUIId;
/* 24 */     this.m_GroupIds = groupIds;
/* 25 */     this.m_NewState = newState;
/* 26 */     this.m_OldState = oldState;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getSource() {
/* 31 */     return this.m_Source;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 36 */     return this.m_PropertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getGroupIds() {
/* 41 */     return this.m_GroupIds;
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyState getNewState() {
/* 46 */     return this.m_NewState;
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyState getOldState() {
/* 51 */     return this.m_OldState;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getUniqueUIId() {
/* 56 */     return this.m_UniqueUIId;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyStateChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */