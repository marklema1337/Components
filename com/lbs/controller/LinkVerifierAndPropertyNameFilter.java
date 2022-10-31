/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
/*    */ 
/*    */ public class LinkVerifierAndPropertyNameFilter
/*    */   extends LinkVerifierNameFilter
/*    */ {
/*    */   private String m_PropertyName;
/*    */   
/*    */   public LinkVerifierAndPropertyNameFilter(String linkVerifierName, String propertyName) {
/* 11 */     super(linkVerifierName);
/* 12 */     this.m_PropertyName = propertyName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldValidate(String linkVerifierName, String propertyToValidate) {
/* 18 */     return (super.shouldValidate(linkVerifierName, propertyToValidate) && StringUtil.equals(propertyToValidate, this.m_PropertyName));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\LinkVerifierAndPropertyNameFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */