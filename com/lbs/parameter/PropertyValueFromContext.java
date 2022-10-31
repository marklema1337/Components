/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyValueFromContext
/*    */   extends PropertyValue
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_VarName;
/*    */   
/*    */   public PropertyValueFromContext() {}
/*    */   
/*    */   public PropertyValueFromContext(String varName) {
/* 28 */     this.m_VarName = varName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getVarName() {
/* 33 */     return this.m_VarName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVarName(String varName) {
/* 38 */     this.m_VarName = varName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getValueStatement(ParameterProperty prop) {
/* 44 */     return "context.getVariable(\"" + this.m_VarName + "\")";
/*    */   }
/*    */   
/*    */   protected void prepareImports(ParameterProperty prop, ArrayList<String> imports) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\PropertyValueFromContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */