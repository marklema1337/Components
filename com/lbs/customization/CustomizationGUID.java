/*    */ package com.lbs.customization;
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
/*    */ 
/*    */ public class CustomizationGUID
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   String m_Identifier;
/*    */   
/*    */   public CustomizationGUID(String identifier) {
/* 20 */     this.m_Identifier = identifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIdentifier() {
/* 25 */     return this.m_Identifier;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\customization\CustomizationGUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */