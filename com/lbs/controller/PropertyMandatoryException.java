/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyMandatoryException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private static final int LIST_ID = 80300;
/*    */   private static final int STRING_TAG = 501;
/* 18 */   HashSet<String> m_MandatoryProperties = new HashSet<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public PropertyMandatoryException() {}
/*    */ 
/*    */   
/*    */   public PropertyMandatoryException(String mandatoryProperty) {
/* 26 */     super(80300, 501, "The following property is mandatory:");
/* 27 */     this.m_MandatoryProperties.add(mandatoryProperty);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyMandatoryException(HashSet<String> mandatoryProperties) {
/* 32 */     super(80300, 501, "The following properties are mandatory: ");
/* 33 */     this.m_MandatoryProperties = mandatoryProperties;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String buildMandatoryExceptionPropertyString(HashSet<String> mandatoryProperties) {
/* 38 */     StringBuilder sb = new StringBuilder(101);
/* 39 */     boolean comma = false;
/* 40 */     Iterator<String> iter = mandatoryProperties.iterator();
/* 41 */     while (iter.hasNext()) {
/*    */       
/* 43 */       if (comma)
/*    */       {
/* 45 */         sb.append(", ");
/*    */       }
/* 47 */       sb.append("\"").append(iter.next()).append("\"");
/* 48 */       comma = true;
/*    */     } 
/* 50 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<String> getMandatoryProperties() {
/* 55 */     return this.m_MandatoryProperties;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedMessage() {
/* 61 */     String localizedMessage = super.getLocalizedMessage();
/*    */     
/* 63 */     if (this.m_MandatoryProperties != null) {
/* 64 */       return String.valueOf(localizedMessage) + buildMandatoryExceptionPropertyString(this.m_MandatoryProperties);
/*    */     }
/* 66 */     return localizedMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getErrorMessage() {
/* 71 */     return super.getLocalizedMessage();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\PropertyMandatoryException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */