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
/*    */ public class ParameterBrowserListProperty
/*    */   extends ParameterListProperty
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected boolean m_Abstract = false;
/*    */   
/*    */   public ParameterBrowserListProperty() {
/* 24 */     setType(ParameterProperty.PropertyType.type_parameter);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAbstractList(boolean abstractList) {
/* 29 */     this.m_Abstract = abstractList;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize(ParameterSchema parameter, boolean checkBusinessObjects) throws ParameterSchemaException {
/* 35 */     if (StringUtil.isEmpty(this.m_Name)) {
/*    */       
/* 37 */       ParameterSchema baseParameter = parameter.getBaseParameter();
/* 38 */       if (baseParameter == null)
/* 39 */         throw new ParameterSchemaException("Cannot find base parameter for browser filter content property!!"); 
/* 40 */       ArrayList<ParameterProperty> baseProperties = baseParameter.getProperties();
/* 41 */       for (ParameterProperty baseProperty : baseProperties) {
/*    */         
/* 43 */         if (baseProperty instanceof ParameterBrowserListProperty && baseProperty.isAbstractList()) {
/*    */           
/* 45 */           setName(baseProperty.getName());
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 50 */     super.initialize(parameter, checkBusinessObjects);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getTypeStringForList(String s) {
/* 56 */     return "List<" + (isAbstractList() ? "T" : s) + ">";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAbstractList() {
/* 64 */     return this.m_Abstract;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasGetter() {
/* 70 */     return declare();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasSetter() {
/* 76 */     return declare();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean declare() {
/* 82 */     if (!isAbstractList())
/* 83 */       return false; 
/* 84 */     return super.declare();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterBrowserListProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */