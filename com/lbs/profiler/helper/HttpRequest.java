/*    */ package com.lbs.profiler.helper;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class HttpRequest
/*    */ {
/*    */   private String m_RequestURI;
/*    */   private String m_ContextPath;
/*    */   private Map m_ParameterMap;
/*    */   
/*    */   public String getContextPath() {
/* 22 */     return this.m_ContextPath;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContextPath(String contextPath) {
/* 27 */     this.m_ContextPath = contextPath;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map getParameterMap() {
/* 32 */     return this.m_ParameterMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParameterMap(Map parameterMap) {
/* 37 */     this.m_ParameterMap = parameterMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRequestURI() {
/* 42 */     return this.m_RequestURI;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRequestURI(String requestURI) {
/* 47 */     this.m_RequestURI = requestURI;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\HttpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */