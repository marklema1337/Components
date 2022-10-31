/*    */ package com.lbs.remoteclient;
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
/*    */ public class URLRedirectionException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_TargetRootURI;
/*    */   private Object m_TargetLoginParameters;
/*    */   
/*    */   public URLRedirectionException(String targetRootURI, Object targetLoginParameters) {
/* 22 */     this.m_TargetRootURI = targetRootURI;
/* 23 */     this.m_TargetLoginParameters = targetLoginParameters;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTargetRootURI() {
/* 28 */     return this.m_TargetRootURI;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getTargetLoginParameters() {
/* 33 */     return this.m_TargetLoginParameters;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     StringBuilder sb = new StringBuilder();
/* 39 */     sb.append(String.valueOf(getClass().getName()) + ", ");
/* 40 */     sb.append("URL = " + this.m_TargetRootURI);
/* 41 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\URLRedirectionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */