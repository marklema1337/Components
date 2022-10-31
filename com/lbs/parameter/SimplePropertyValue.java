/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Calendar;
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
/*    */ public class SimplePropertyValue
/*    */   extends PropertyValue
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Value;
/*    */   
/*    */   public SimplePropertyValue() {}
/*    */   
/*    */   public SimplePropertyValue(String value) {
/* 29 */     this.m_Value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 34 */     return this.m_Value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(String value) {
/* 39 */     this.m_Value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void prepareImports(ParameterProperty prop, ArrayList<String> imports) {
/* 45 */     if (prop.getType() != null) {
/* 46 */       switch (prop.getType()) {
/*    */         
/*    */         case type_BigDecimal:
/* 49 */           ParameterSchema.addImport(imports, BigDecimal.class.getName());
/*    */           break;
/*    */         case type_Calendar:
/* 52 */           ParameterSchema.addImport(imports, Calendar.class.getName());
/*    */           break;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getValueStatement(ParameterProperty prop) {
/* 60 */     if (prop.getType() != null)
/* 61 */       switch (prop.getType()) {
/*    */         
/*    */         case type_BigDecimal:
/* 64 */           return "new BigDecimal(" + this.m_Value + ")";
/*    */         case type_Double:
/* 66 */           return "new Double(" + this.m_Value + ")";
/*    */         case type_Float:
/* 68 */           return "new Float(" + this.m_Value + ")";
/*    */         case type_Long:
/* 70 */           return "Long.valueOf(" + this.m_Value + ")";
/*    */         case type_String:
/* 72 */           if (!this.m_Value.equals("null"))
/* 73 */             return "\"" + this.m_Value + "\""; 
/*    */           break;
/*    */       }  
/* 76 */     return this.m_Value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\SimplePropertyValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */