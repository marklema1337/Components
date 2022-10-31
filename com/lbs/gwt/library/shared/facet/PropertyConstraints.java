/*    */ package com.lbs.gwt.library.shared.facet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PropertyConstraints
/*    */   implements Serializable
/*    */ {
/*    */   private int m_MaxLength;
/*    */   private PropertyState m_State;
/*    */   
/*    */   public boolean isMandatory() {
/* 12 */     return (this.m_State != null && this.m_State == PropertyState.mandatory);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLength() {
/* 17 */     return this.m_MaxLength;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaxLength(int maxLength) {
/* 22 */     this.m_MaxLength = maxLength;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setState(PropertyState state) {
/* 27 */     this.m_State = state;
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyState getState() {
/* 32 */     return this.m_State;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\gwt\library\shared\facet\PropertyConstraints.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */