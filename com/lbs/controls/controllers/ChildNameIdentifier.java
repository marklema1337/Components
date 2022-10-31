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
/*    */ public class ChildNameIdentifier
/*    */   extends NameIdentifier
/*    */ {
/*    */   private int m_ChildControlType;
/*    */   private int m_ChildControlTag;
/*    */   
/*    */   public ChildNameIdentifier(int controlType, int controlTag, int nameType, int childControlType, int childControlTag) {
/* 19 */     super(controlType, controlTag, nameType);
/* 20 */     this.m_ChildControlType = childControlType;
/* 21 */     this.m_ChildControlTag = childControlTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 26 */     if (obj instanceof ChildNameIdentifier) {
/*    */       
/* 28 */       ChildNameIdentifier id = (ChildNameIdentifier)obj;
/* 29 */       return (id.m_ChildControlTag == this.m_ChildControlTag && id.m_ChildControlType == this.m_ChildControlType && super.equals(obj));
/*    */     } 
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 37 */     return super.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getHashCodeString() {
/* 42 */     return String.valueOf(super.getHashCodeString()) + "__" + this.m_ChildControlType + "_" + this.m_ChildControlTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public String generateConstructor() {
/* 47 */     return "new ChildNameIdentifier(" + this.m_ControlType + ", " + this.m_ControlTag + ", " + this.m_NameType + ", " + this.m_ChildControlType + 
/* 48 */       ", " + this.m_ChildControlTag + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "ChildNameIdentifier(" + this.m_ControlType + ", " + this.m_ControlTag + ", " + this.m_NameType + ", " + this.m_ChildControlType + ", " + 
/* 55 */       this.m_ChildControlTag + ")";
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\controllers\ChildNameIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */