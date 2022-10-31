/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
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
/*    */ public class ParameterConstructor
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 19 */   private ArrayList<ParameterProperty> m_Properties = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<ParameterProperty> getProperties() {
/* 27 */     return this.m_Properties;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProperties(ArrayList<ParameterProperty> properties) {
/* 32 */     this.m_Properties = properties;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterConstructor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */