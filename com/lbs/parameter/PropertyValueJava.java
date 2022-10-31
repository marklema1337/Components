/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
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
/*    */ public class PropertyValueJava
/*    */   extends PropertyValue
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_ClassName;
/*    */   private String m_MemberName;
/*    */   
/*    */   public PropertyValueJava() {}
/*    */   
/*    */   public PropertyValueJava(String className, String memberName) {
/* 30 */     this.m_ClassName = className;
/* 31 */     this.m_MemberName = memberName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 36 */     return this.m_ClassName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClassName(String className) {
/* 41 */     this.m_ClassName = className;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMemberName() {
/* 46 */     return this.m_MemberName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMemberName(String memberName) {
/* 51 */     this.m_MemberName = memberName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getValueStatement(ParameterProperty prop) {
/* 57 */     return StringUtil.getNameFromQualified(this.m_ClassName) + "." + this.m_MemberName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void prepareImports(ParameterProperty prop, ArrayList<String> imports) {
/* 63 */     ParameterSchema.addImport(imports, this.m_ClassName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\PropertyValueJava.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */