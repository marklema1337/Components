/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsRowColorPrefObject
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_PropName;
/*    */   private String m_GeneratedExpression;
/*    */   private String m_IfExpression;
/*    */   private RowColorExpressionObject m_Object;
/*    */   
/*    */   public String getPropName() {
/* 17 */     return this.m_PropName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPropName(String propName) {
/* 22 */     this.m_PropName = propName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGeneratedExpression() {
/* 27 */     return this.m_GeneratedExpression;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGeneratedExpression(String generatedExpression) {
/* 32 */     this.m_GeneratedExpression = generatedExpression;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIfExpression() {
/* 37 */     return this.m_IfExpression;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIfExpression(String ifExpression) {
/* 42 */     this.m_IfExpression = ifExpression;
/*    */   }
/*    */ 
/*    */   
/*    */   public RowColorExpressionObject getObject() {
/* 47 */     return this.m_Object;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setObject(RowColorExpressionObject object) {
/* 52 */     this.m_Object = object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\JLbsRowColorPrefObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */