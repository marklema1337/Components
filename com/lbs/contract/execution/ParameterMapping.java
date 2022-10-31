/*    */ package com.lbs.contract.execution;
/*    */ 
/*    */ import com.lbs.contract.ContractException;
/*    */ import com.lbs.data.Identifier;
/*    */ import com.lbs.util.StringUtil;
/*    */ import java.io.Serializable;
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
/*    */ public class ParameterMapping
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Identifier m_ParameterIdentifier;
/* 24 */   private ArrayList<PropertyMapping> m_PropertyMappings = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void setParameterIdentifier(Identifier parameterIdentifier) {
/* 28 */     this.m_ParameterIdentifier = parameterIdentifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public Identifier getParameterIdentifier() {
/* 33 */     return this.m_ParameterIdentifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getParameterId() {
/* 38 */     return this.m_ParameterIdentifier.getId();
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<PropertyMapping> getPropertyMappings() {
/* 43 */     return this.m_PropertyMappings;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addPropertyMapping(PropertyMapping mapping) {
/* 48 */     this.m_PropertyMappings.add(mapping);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyMapping getMapping(String propertyName) {
/* 53 */     for (PropertyMapping mapping : this.m_PropertyMappings) {
/*    */       
/* 55 */       if (StringUtil.equals(propertyName, mapping.getPropertyName()))
/* 56 */         return mapping; 
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     StringBuilder sb = new StringBuilder();
/* 65 */     sb.append(getClass().getName());
/* 66 */     sb.append("(" + this.m_ParameterIdentifier + ")");
/* 67 */     return sb.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize() throws ContractException {
/* 73 */     for (PropertyMapping propMapping : this.m_PropertyMappings)
/*    */     {
/* 75 */       propMapping.initialize();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\execution\ParameterMapping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */