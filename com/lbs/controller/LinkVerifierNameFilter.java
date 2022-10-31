/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
/*    */ 
/*    */ 
/*    */ public class LinkVerifierNameFilter
/*    */   implements LinkVerifierFilter
/*    */ {
/*    */   private String m_LinkVerifierName;
/*    */   
/*    */   public LinkVerifierNameFilter(String linkVerifierName) {
/* 12 */     this.m_LinkVerifierName = linkVerifierName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldValidate(String linkVerifierName, String propertyToValidate) {
/* 18 */     return StringUtil.equals(linkVerifierName, this.m_LinkVerifierName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\LinkVerifierNameFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */