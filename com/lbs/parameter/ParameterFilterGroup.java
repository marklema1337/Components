/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
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
/*    */ public class ParameterFilterGroup
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 21 */   private Hashtable<String, String> m_XUIProperties = new Hashtable<>();
/* 22 */   private ArrayList<ParameterFilter> m_Filters = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public Hashtable<String, String> getXUIProperties() {
/* 26 */     return this.m_XUIProperties;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<ParameterFilter> getFilters() {
/* 31 */     return this.m_Filters;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void prepareImports(ArrayList<String> imports) {
/* 36 */     for (ParameterFilter filter : this.m_Filters)
/*    */     {
/* 38 */       filter.prepareImports(imports);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void printXUIDefString(StringBuilder sb) {
/* 44 */     if (this.m_XUIProperties.size() > 0) {
/*    */       
/* 46 */       sb.append("<FilterGroup ");
/* 47 */       Enumeration<String> propNames = this.m_XUIProperties.keys();
/* 48 */       while (propNames.hasMoreElements()) {
/*    */         
/* 50 */         String propName = propNames.nextElement();
/* 51 */         String propValue = this.m_XUIProperties.get(propName);
/* 52 */         sb.append(propName + "=\"" + propValue + "\" ");
/*    */       } 
/* 54 */       sb.append(">\n");
/*    */     } 
/* 56 */     for (ParameterFilter filter : this.m_Filters)
/*    */     {
/* 58 */       filter.printXUIDefString(sb);
/*    */     }
/* 60 */     if (this.m_XUIProperties.size() > 0)
/*    */     {
/* 62 */       sb.append("</FilterGroup>\n");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterFilterGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */