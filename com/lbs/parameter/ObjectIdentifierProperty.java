/*     */ package com.lbs.parameter;
/*     */ 
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectIdentifierProperty
/*     */   extends ParameterProperty
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  21 */   private ObjectIdentifierCase m_DefaultCase = new ObjectIdentifierCase();
/*  22 */   private ArrayList<ObjectIdentifierCase> m_Cases = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public ObjectIdentifierProperty() {
/*  26 */     this.m_Type = ParameterProperty.PropertyType.type_int;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareImports(ArrayList<String> imports) {
/*  32 */     super.prepareImports(imports);
/*  33 */     ParameterSchema.addImport(imports, "com.lbs.parameter.ObjectIdentifierOwner");
/*  34 */     ParameterSchema.addImport(imports, "com.lbs.par.gen.common.ObjectIdentifier");
/*  35 */     ParameterSchema.addImport(imports, "com.lbs.xui.contract.execution.ObjectIdentifierUtil");
/*  36 */     ParameterSchema.addImport(imports, ArrayList.class.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize(ParameterSchema parameter, boolean checkBusinessObjects) throws ParameterSchemaException {
/*  42 */     super.initialize(parameter, checkBusinessObjects);
/*  43 */     for (ObjectIdentifierCase item : this.m_Cases)
/*     */     {
/*  45 */       item.initialize(parameter);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/*  51 */     return this.m_DefaultCase.getTableName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableName(String tableName) {
/*  56 */     this.m_DefaultCase.setTableName(tableName);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSetValue() {
/*  61 */     return this.m_DefaultCase.getSetValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValue(String setValue) {
/*  66 */     this.m_DefaultCase.setSetValue(setValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ObjectIdentifierCase> getCases() {
/*  71 */     return this.m_Cases;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCase(ObjectIdentifierCase newCase) {
/*  76 */     this.m_Cases.add(newCase);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCases() {
/*  81 */     return (this.m_Cases.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasDefaultCase() {
/*  86 */     return !StringUtil.isEmpty(getTableName());
/*     */   }
/*     */   
/*     */   public static class ObjectIdentifierCase
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     private String m_TableName;
/*     */     private String m_SetValue;
/*  95 */     private ArrayList<ObjectIdentifierProperty.ObjectIdentifierCondition> m_Conditions = new ArrayList<>();
/*     */ 
/*     */     
/*     */     public String getTableName() {
/*  99 */       return this.m_TableName;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTableName(String tableName) {
/* 104 */       this.m_TableName = tableName;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getSetValue() {
/* 109 */       return this.m_SetValue;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSetValue(String setValue) {
/* 114 */       this.m_SetValue = setValue;
/*     */     }
/*     */ 
/*     */     
/*     */     public ArrayList<ObjectIdentifierProperty.ObjectIdentifierCondition> getConditions() {
/* 119 */       return this.m_Conditions;
/*     */     }
/*     */ 
/*     */     
/*     */     public void addCondition(ObjectIdentifierProperty.ObjectIdentifierCondition condition) {
/* 124 */       this.m_Conditions.add(condition);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasCondition() {
/* 129 */       return (this.m_Conditions.size() > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getConditionStatement() {
/* 134 */       StringBuilder sb = new StringBuilder();
/* 135 */       if (!hasCondition()) {
/*     */         
/* 137 */         sb.append("true");
/*     */       }
/*     */       else {
/*     */         
/* 141 */         boolean and = false;
/* 142 */         for (ObjectIdentifierProperty.ObjectIdentifierCondition condition : this.m_Conditions) {
/*     */           
/* 144 */           if (and)
/* 145 */             sb.append(" && "); 
/* 146 */           condition.appendCondition(sb);
/* 147 */           and = true;
/*     */         } 
/*     */       } 
/* 150 */       return sb.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public void initialize(ParameterSchema schema) throws ParameterSchemaException {
/* 155 */       for (ObjectIdentifierProperty.ObjectIdentifierCondition condition : this.m_Conditions)
/*     */       {
/* 157 */         condition.initialize(schema);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ObjectIdentifierCondition
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     private String m_PropertyName;
/*     */     private ParameterProperty m_Property;
/*     */     private String m_Value;
/* 170 */     private ObjectIdentifierProperty.CaseOperator m_Operator = ObjectIdentifierProperty.CaseOperator.equal;
/*     */ 
/*     */ 
/*     */     
/*     */     public ObjectIdentifierCondition() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public ObjectIdentifierCondition(String propertyName, String value, ObjectIdentifierProperty.CaseOperator operator) {
/* 179 */       this.m_PropertyName = propertyName;
/* 180 */       this.m_Value = value;
/* 181 */       this.m_Operator = operator;
/*     */     }
/*     */ 
/*     */     
/*     */     public void initialize(ParameterSchema schema) throws ParameterSchemaException {
/* 186 */       this.m_Property = schema.findProperty(this.m_PropertyName);
/* 187 */       if (this.m_Property == null) {
/* 188 */         throw new ParameterSchemaException("Property with name '" + this.m_PropertyName + "' does not exist in parameter schema '" + schema
/* 189 */             .getIdentifier() + "'");
/*     */       }
/*     */     }
/*     */     
/*     */     public String getPropertyName() {
/* 194 */       return this.m_PropertyName;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPropertyName(String propertyName) {
/* 199 */       this.m_PropertyName = propertyName;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 204 */       return this.m_Value;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setValue(String value) {
/* 209 */       this.m_Value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public ObjectIdentifierProperty.CaseOperator getOperator() {
/* 214 */       return this.m_Operator;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setOperator(ObjectIdentifierProperty.CaseOperator operator) {
/* 219 */       this.m_Operator = operator;
/*     */     }
/*     */     public void appendCondition(StringBuilder sb) {
/*     */       String[] parts;
/*     */       boolean or;
/* 224 */       String propName = this.m_Property.getMemberName();
/* 225 */       String opStr = " == ";
/* 226 */       switch (this.m_Operator) {
/*     */         
/*     */         case equal:
/* 229 */           opStr = " == ";
/*     */           break;
/*     */         case greater:
/* 232 */           opStr = " > ";
/*     */           break;
/*     */         case greaterequal:
/* 235 */           opStr = " >= ";
/*     */           break;
/*     */         case less:
/* 238 */           opStr = " < ";
/*     */           break;
/*     */         case lessequal:
/* 241 */           opStr = " <= ";
/*     */           break;
/*     */         case notequal:
/* 244 */           opStr = " != ";
/*     */           break;
/*     */         case in:
/* 247 */           parts = StringUtil.split(this.m_Value, ',');
/* 248 */           or = false;
/* 249 */           for (String part : parts) {
/*     */             
/* 251 */             if (or)
/* 252 */               sb.append(" || "); 
/* 253 */             sb.append(propName + opStr + part);
/* 254 */             or = true;
/*     */           } 
/*     */           return;
/*     */       } 
/* 258 */       sb.append(propName + opStr + this.m_Value);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum CaseOperator
/*     */   {
/* 264 */     equal, notequal, greater, less, greaterequal, lessequal, in;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ObjectIdentifierProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */