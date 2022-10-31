/*    */ package com.lbs.controls.controllers;
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
/*    */ 
/*    */ public class NameIdentifier
/*    */ {
/*    */   protected int m_ControlType;
/*    */   protected int m_ControlTag;
/*    */   protected int m_NameType;
/*    */   
/*    */   public NameIdentifier(int controlType, int controlTag, int nameType) {
/* 22 */     this.m_ControlType = controlType;
/* 23 */     this.m_ControlTag = controlTag;
/* 24 */     this.m_NameType = nameType;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 29 */     if (obj instanceof NameIdentifier) {
/*    */       
/* 31 */       NameIdentifier id = (NameIdentifier)obj;
/* 32 */       return (id.m_ControlTag == this.m_ControlTag && id.m_NameType == this.m_NameType && id.m_ControlType == this.m_ControlType);
/*    */     } 
/* 34 */     return super.equals(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getHashCodeString() {
/* 39 */     return String.valueOf(this.m_ControlTag) + "_" + this.m_NameType + "_" + this.m_ControlType;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 44 */     return getHashCodeString().hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String generateConstructor() {
/* 49 */     return "new NameIdentifier(" + this.m_ControlType + ", " + this.m_ControlTag + ", " + this.m_NameType + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return "NameIdentifier(" + this.m_ControlType + ", " + this.m_ControlTag + ", " + this.m_NameType + ")";
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\controllers\NameIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */