/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
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
/*    */ public class PropertyValueNewInstance
/*    */   extends PropertyValue
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected String getValueStatement(ParameterProperty prop) {
/* 25 */     String typeName = prop.getTypeString();
/* 26 */     if (prop.isList())
/* 27 */       typeName = typeName.replaceFirst("List", "ArrayList"); 
/* 28 */     if (prop.isMap())
/* 29 */       typeName = typeName.replaceFirst("Map", "HashMap"); 
/* 30 */     if (prop.getType() == ParameterProperty.PropertyType.type_Calendar)
/* 31 */       return "Calendar.getInstance()"; 
/* 32 */     return "new " + typeName + "()";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void prepareImports(ParameterProperty prop, ArrayList<String> imports) {
/* 38 */     if (prop.isList())
/* 39 */       ParameterSchema.addImport(imports, ArrayList.class.getName()); 
/* 40 */     if (prop.isMap())
/* 41 */       ParameterSchema.addImport(imports, HashMap.class.getName()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\PropertyValueNewInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */