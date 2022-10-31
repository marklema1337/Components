/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class ParameterListProperty
/*    */   extends ParameterProperty
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public String getItemTypeString() {
/* 21 */     return getTypeString(this.m_Type, this.m_TypeName);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTypeString() {
/* 27 */     String s = super.getTypeString();
/* 28 */     if (s == null) {
/* 29 */       return "List";
/*    */     }
/* 31 */     return getTypeStringForList(s);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getTypeStringForList(String s) {
/* 36 */     return "List<" + s + ">";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void prepareImports(ArrayList<String> imports) {
/* 42 */     super.prepareImports(imports);
/* 43 */     if (declare()) {
/* 44 */       ParameterSchema.addImport(imports, List.class.getName());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean needsXmlChildren() {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isList() {
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterListProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */