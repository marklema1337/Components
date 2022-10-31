/*    */ package com.lbs.controller.dto;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LinkVerifierDescription
/*    */ {
/*    */   private String m_LinkVerifierName;
/*    */   private String m_PropertyName;
/*    */   
/*    */   public LinkVerifierDescription(String linkVerifierName, String propertyName) {
/* 11 */     this.m_LinkVerifierName = linkVerifierName;
/* 12 */     this.m_PropertyName = propertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLinkVerifierName() {
/* 17 */     return this.m_LinkVerifierName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLinkVerifierName(String linkVerifierName) {
/* 22 */     this.m_LinkVerifierName = linkVerifierName;
/*    */   }
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
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\dto\LinkVerifierDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */