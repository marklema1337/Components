/*    */ package com.lbs.controller;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FilteredLinkVerifier
/*    */   implements BasicLinkVerifier
/*    */ {
/*    */   protected LinkVerifierFilter m_Filter;
/*    */   
/*    */   public FilteredLinkVerifier(LinkVerifierFilter filter) {
/* 11 */     this.m_Filter = filter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OperationResult validate(String linkVerifierName, String propertyToValidate, Object valueToValidate) throws InvalidDataException {
/* 18 */     if (this.m_Filter.shouldValidate(linkVerifierName, propertyToValidate))
/* 19 */       return validateImpl(linkVerifierName, propertyToValidate, valueToValidate); 
/* 20 */     return null;
/*    */   }
/*    */   
/*    */   protected abstract OperationResult validateImpl(String paramString1, String paramString2, Object paramObject) throws InvalidDataException;
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\FilteredLinkVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */