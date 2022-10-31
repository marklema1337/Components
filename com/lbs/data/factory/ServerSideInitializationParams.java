/*    */ package com.lbs.data.factory;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerSideInitializationParams
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 17 */   private int m_Mode = 0;
/*    */   
/*    */   private Object m_Source;
/*    */   private Object m_TypeInfo;
/*    */   
/*    */   public int getMode() {
/* 23 */     return this.m_Mode;
/*    */   }
/*    */   
/*    */   public void setMode(int mode) {
/* 27 */     this.m_Mode = mode;
/*    */   }
/*    */   
/*    */   public Object getSource() {
/* 31 */     return this.m_Source;
/*    */   }
/*    */   
/*    */   public void setSource(Object source) {
/* 35 */     this.m_Source = source;
/*    */   }
/*    */   
/*    */   public Object getTypeInfo() {
/* 39 */     return this.m_TypeInfo;
/*    */   }
/*    */   
/*    */   public void setTypeInfo(Object typeInfo) {
/* 43 */     this.m_TypeInfo = typeInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\ServerSideInitializationParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */