/*     */ package com.lbs.parameter;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.objects.SimpleBusinessObject;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class ParameterMapProperty
/*     */   extends ParameterProperty
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ParameterProperty.PropertyType m_KeyType;
/*     */   private String m_KeyTypeName;
/*     */   
/*     */   public void initialize(ParameterSchema parameter, boolean checkBusinessObjects) throws ParameterSchemaException {
/*  29 */     super.initialize(parameter, checkBusinessObjects);
/*  30 */     if (this.m_KeyType == ParameterProperty.PropertyType.type_parameter) {
/*     */       
/*  32 */       if (!this.m_KeyTypeName.startsWith("{") && this.m_KeyTypeName.indexOf('.') >= 0) {
/*     */         return;
/*     */       }
/*  35 */       int idx = this.m_KeyTypeName.indexOf('.');
/*  36 */       String guid = null;
/*  37 */       String typeName = this.m_KeyTypeName;
/*  38 */       if (idx > 0) {
/*     */         
/*  40 */         guid = this.m_KeyTypeName.substring(0, idx);
/*  41 */         typeName = this.m_KeyTypeName.substring(idx + 1);
/*     */       } 
/*  43 */       ParameterSchema ref = ParameterSchemaRegistry.getContainer(guid).findParameter(typeName);
/*  44 */       if (ref == null)
/*  45 */         throw new ParameterSchemaException("Cannot find referred parameter with name : " + this.m_KeyTypeName); 
/*  46 */       this.m_KeyTypeName = ref.getQualifiedName();
/*     */     } 
/*  48 */     if (this.m_KeyType == ParameterProperty.PropertyType.type_business_object) {
/*     */       
/*     */       try {
/*     */         
/*  52 */         Class<?> c = Class.forName(this.m_KeyTypeName);
/*  53 */         if (!SimpleBusinessObject.class.isAssignableFrom(c))
/*     */         {
/*  55 */           String message = "Class '" + this.m_KeyTypeName + "' is not an instance of SimpleBusinessObject!";
/*  56 */           LbsConsole.getLogger(getClass()).error(message);
/*     */         }
/*     */       
/*     */       }
/*  60 */       catch (ClassNotFoundException e) {
/*     */         
/*  62 */         String message = "Business object class '" + this.m_KeyTypeName + "' cannot be loaded!";
/*  63 */         LbsConsole.getLogger(getClass()).error(message);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemTypeString() {
/*  71 */     return getTypeString(this.m_Type, this.m_TypeName);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyTypeString() {
/*  76 */     return getTypeString(this.m_KeyType, this.m_KeyTypeName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/*  82 */     String s = super.getTypeString();
/*  83 */     String keyTypeString = getTypeString(this.m_KeyType, this.m_KeyTypeName);
/*  84 */     if (s == null || keyTypeString == null) {
/*  85 */       return "Map";
/*     */     }
/*  87 */     return "Map<" + keyTypeString + ", " + s + ">";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareImports(ArrayList<String> imports) {
/*  93 */     super.prepareImports(imports);
/*  94 */     if (!StringUtil.isEmpty(this.m_KeyTypeName))
/*  95 */       ParameterSchema.addImport(imports, this.m_KeyTypeName); 
/*  96 */     ParameterSchema.addImport(imports, Map.class.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyType(ParameterProperty.PropertyType keyType) {
/* 101 */     this.m_KeyType = keyType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyTypeName(String keyTypeName) {
/* 106 */     this.m_KeyTypeName = keyTypeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParameterProperty.PropertyType getKeyType() {
/* 111 */     return this.m_KeyType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyTypeName() {
/* 116 */     return this.m_KeyTypeName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean needsXmlChildren() {
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMap() {
/* 128 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterMapProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */