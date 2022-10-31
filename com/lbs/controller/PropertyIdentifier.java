/*    */ package com.lbs.controller;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyIdentifier
/*    */ {
/*    */   private String m_PropertyName;
/*    */   private int m_PropertyUIId;
/*    */   
/*    */   public PropertyIdentifier(String propertyName, int propertyUIId) {
/* 11 */     this.m_PropertyName = propertyName;
/* 12 */     this.m_PropertyUIId = propertyUIId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PropertyIdentifier(String propertyName) {
/* 18 */     this.m_PropertyName = propertyName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PropertyIdentifier() {}
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 27 */     return this.m_PropertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPropertyName(String propertyName) {
/* 32 */     this.m_PropertyName = propertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPropertyUIId() {
/* 37 */     return this.m_PropertyUIId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPropertyUIId(int propertyUIId) {
/* 42 */     this.m_PropertyUIId = propertyUIId;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */