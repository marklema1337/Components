/*     */ package com.lbs.parameter;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.objects.SimpleBusinessObject;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.interfaces.Identifiable;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class ParameterProperty
/*     */   implements Comparable<ParameterProperty>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_Name;
/*     */   protected PropertyType m_Type;
/*     */   protected String m_TypeName;
/*  46 */   protected Modifier m_Modifier = Modifier.mod_private;
/*     */   protected boolean m_Mandatory;
/*     */   protected boolean m_Constant = false;
/*     */   protected boolean m_HasGetter = true;
/*     */   protected boolean m_HasSetter = true;
/*     */   protected boolean m_Array;
/*     */   protected PropertyValue m_InitialValue;
/*  53 */   protected String m_EmptyValue = null;
/*     */   
/*     */   protected boolean m_Serializable = true;
/*     */   
/*     */   protected boolean m_PartOfIdentifier = false;
/*     */   protected JLbsResourceId m_DescResource;
/*     */   protected String m_Description;
/*     */   
/*     */   public void initialize(ParameterSchema parameter, boolean checkBusinessObjects) throws ParameterSchemaException {
/*  62 */     if (this.m_Type == PropertyType.type_parameter) {
/*     */       
/*  64 */       if (!this.m_TypeName.startsWith("{") && this.m_TypeName.indexOf('.') >= 0) {
/*     */         return;
/*     */       }
/*  67 */       int idx = this.m_TypeName.indexOf('.');
/*  68 */       String guid = null;
/*  69 */       String typeName = this.m_TypeName;
/*  70 */       if (idx > 0) {
/*     */         
/*  72 */         guid = this.m_TypeName.substring(0, idx);
/*  73 */         typeName = this.m_TypeName.substring(idx + 1);
/*     */       } 
/*  75 */       ParameterSchema ref = ParameterSchemaRegistry.getContainer(guid).findParameter(typeName);
/*  76 */       if (ref == null)
/*  77 */         throw new ParameterSchemaException("Cannot find referred parameter with name : " + this.m_TypeName); 
/*  78 */       this.m_TypeName = ref.getQualifiedName();
/*     */     } 
/*  80 */     if (checkBusinessObjects && this.m_Type == PropertyType.type_business_object) {
/*     */       
/*     */       try {
/*     */         
/*  84 */         Class<?> c = Class.forName(this.m_TypeName);
/*  85 */         if (!SimpleBusinessObject.class.isAssignableFrom(c))
/*     */         {
/*  87 */           String message = "Class '" + this.m_TypeName + "' is not an instance of SimpleBusinessObject!";
/*  88 */           LbsConsole.getLogger(getClass()).error(message);
/*     */         }
/*     */       
/*     */       }
/*  92 */       catch (ClassNotFoundException e) {
/*     */         
/*  94 */         String message = "Business object class '" + this.m_TypeName + "' cannot be loaded!";
/*  95 */         LbsConsole.getLogger(getClass()).error(message);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHasGetter(boolean hasGetter) {
/* 103 */     this.m_HasGetter = hasGetter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasSetter(boolean hasSetter) {
/* 108 */     this.m_HasSetter = hasSetter;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasGetter() {
/* 113 */     return this.m_HasGetter;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSetter() {
/* 118 */     return this.m_HasSetter;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasInitialValue() {
/* 123 */     return (this.m_InitialValue != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInitialValueStatement() {
/* 128 */     return this.m_InitialValue.getValueStatement(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameCamel() {
/* 133 */     return getNameCamel(this.m_Name);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getNameCamel(String name) {
/* 138 */     if (name == null)
/* 139 */       return ""; 
/* 140 */     return name.substring(0, 1).toUpperCase(Locale.UK) + name.substring(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMemberName() {
/* 145 */     String camelName = getNameCamel();
/* 146 */     if (this.m_Constant)
/*     */     {
/* 148 */       return camelName;
/*     */     }
/*     */     
/* 151 */     camelName = "m_" + camelName;
/* 152 */     return camelName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 157 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 162 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public PropertyType getType() {
/* 167 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(PropertyType type) {
/* 172 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypeName() {
/* 177 */     return this.m_TypeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeName(String typeName) {
/* 182 */     this.m_TypeName = typeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Modifier getModifier() {
/* 187 */     return this.m_Modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModifier(Modifier modifier) {
/* 192 */     this.m_Modifier = modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMandatory() {
/* 197 */     return this.m_Mandatory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMandatory(boolean mandatory) {
/* 202 */     this.m_Mandatory = mandatory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConstant(boolean constant) {
/* 207 */     this.m_Constant = constant;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConstant() {
/* 212 */     return this.m_Constant;
/*     */   }
/*     */ 
/*     */   
/*     */   public PropertyValue getInitialValue() {
/* 217 */     return this.m_InitialValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitialValue(PropertyValue initialValue) {
/* 222 */     this.m_InitialValue = initialValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTypeAsDBType() {
/* 227 */     switch (this.m_Type) {
/*     */       
/*     */       case mod_package:
/* 230 */         return 7;
/*     */       case null:
/*     */       case null:
/*     */       case null:
/*     */       case null:
/* 235 */         return 1;
/*     */       case null:
/* 237 */         return 10;
/*     */       case null:
/* 239 */         return 16;
/*     */       case null:
/*     */       case null:
/* 242 */         return 6;
/*     */       case null:
/*     */       case null:
/* 245 */         return 5;
/*     */       case null:
/*     */       case null:
/* 248 */         return 3;
/*     */       case null:
/*     */       case null:
/* 251 */         return 4;
/*     */     } 
/*     */     
/* 254 */     return 11;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeStringOnly() {
/* 260 */     String s = getTypeString();
/* 261 */     if (s != null) {
/*     */       
/* 263 */       int idx = s.indexOf('<');
/* 264 */       if (idx > 0)
/* 265 */         return s.substring(0, idx); 
/*     */     } 
/* 267 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/* 272 */     String s = null;
/* 273 */     s = getTypeString(this.m_Type, this.m_TypeName);
/* 274 */     if (this.m_Array)
/* 275 */       return s + "[]"; 
/* 276 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDeclarationTypeString() {
/* 281 */     String s = getTypeString();
/* 282 */     if (s.equals("boolean"))
/* 283 */       s = "Boolean"; 
/* 284 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTypeString(PropertyType type, String typeName) {
/* 289 */     String s = null;
/* 290 */     if (type != null)
/*     */     
/* 292 */     { switch (type)
/*     */       
/*     */       { case null:
/*     */         case null:
/* 296 */           s = StringUtil.getNameFromQualified(typeName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 304 */           return s; }  s = type.getTypeName(); }  return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBOType() {
/* 309 */     return (this.m_Type == PropertyType.type_business_object);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParameterType() {
/* 314 */     return (this.m_Type == PropertyType.type_parameter);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean needsXmlChildren() {
/* 319 */     return (isBOType() || isParameterType());
/*     */   }
/*     */ 
/*     */   
/*     */   public String castString(String str) {
/* 324 */     String s = getTypeString();
/* 325 */     return castGetClass(str, s);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String castGetClass(String str, String c) {
/* 330 */     if (c.equals("byte")) {
/* 331 */       return "((Byte) " + str + ").byteValue()";
/*     */     }
/* 333 */     if (c.equals("char")) {
/* 334 */       return "((Character) " + str + ").charValue()";
/*     */     }
/* 336 */     if (c.equals("int")) {
/* 337 */       return "((Integer) " + str + ").intValue()";
/*     */     }
/* 339 */     if (c.equals("long")) {
/* 340 */       return "((Long) " + str + ").longValue()";
/*     */     }
/* 342 */     if (c.equals("short")) {
/* 343 */       return "((Short) " + str + ").shortValue()";
/*     */     }
/* 345 */     if (c.equals("float")) {
/* 346 */       return "((Float) " + str + ").floatValue()";
/*     */     }
/* 348 */     if (c.equals("double")) {
/* 349 */       return "((Double) " + str + ").doubleValue()";
/*     */     }
/* 351 */     if (c.equals("boolean")) {
/* 352 */       return "((Boolean) " + str + ").booleanValue()";
/*     */     }
/*     */     
/* 355 */     return "(" + c + ") " + str;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModifierString() {
/* 360 */     switch (this.m_Modifier) {
/*     */       
/*     */       case mod_package:
/* 363 */         return "";
/*     */     } 
/*     */     
/* 366 */     return this.m_Modifier.getTypeName();
/*     */   }
/*     */   
/*     */   public enum Modifier
/*     */   {
/* 371 */     mod_public("public"), mod_protected("protected"), mod_private("private"), mod_package("package");
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
/* 385 */     private static final Map<String, Modifier> ms_LookupTable = new HashMap<>();
/*     */     private String m_Name;
/*     */     
/*     */     static {
/* 389 */       for (Modifier s : EnumSet.<Modifier>allOf(Modifier.class))
/* 390 */         ms_LookupTable.put(s.getTypeName(), s);  } Modifier(String name) {
/*     */       this.m_Name = name;
/*     */     } public String getTypeName() {
/*     */       return this.m_Name;
/*     */     } public static Modifier get(String name) {
/* 395 */       return ms_LookupTable.get(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum PropertyType {
/* 400 */     type_boolean("boolean"), type_Boolean("Boolean"), type_byte("byte"), type_Byte("Byte"), type_int("int"), type_Integer("Integer"),
/* 401 */     type_float("float"), type_Float("Float"), type_double("double"), type_Double("Double"), type_long("long"),
/* 402 */     type_Long("Long"), type_BigDecimal("BigDecimal"), type_String("String"), type_Calendar("Calendar"), type_parameter("parameter"),
/* 403 */     type_business_object("business-object"), type_Timestamp("Timestamp");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String m_TypeName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 417 */     private static final Map<String, PropertyType> ms_LookupTable = new HashMap<>(); PropertyType(String typeName) { this.m_TypeName = typeName; } public String getTypeName() {
/*     */       return this.m_TypeName;
/*     */     }
/*     */     static {
/* 421 */       for (PropertyType s : EnumSet.<PropertyType>allOf(PropertyType.class)) {
/* 422 */         ms_LookupTable.put(s.getTypeName(), s);
/*     */       }
/*     */     }
/*     */     
/*     */     public static PropertyType get(String typeName) {
/* 427 */       return ms_LookupTable.get(typeName);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void prepareImports(ArrayList<String> imports) {
/* 433 */     if (this.m_Mandatory)
/*     */     {
/* 435 */       ParameterSchema.addImport(imports, Parameter.Mandatory.class.getSimpleName());
/*     */     }
/* 437 */     ParameterSchema.addImport(imports, ArrayList.class.getName());
/* 438 */     ParameterSchema.addImport(imports, ParameterException.class.getName());
/* 439 */     ParameterSchema.addImport(imports, IParameterMandatoryListener.class.getName());
/* 440 */     ParameterSchema.addImport(imports, XMLSerializerBase.class.getName());
/* 441 */     ParameterSchema.addImport(imports, Element.class.getName());
/* 442 */     ParameterSchema.addImport(imports, IDataExchangeParams.class.getName());
/* 443 */     ParameterSchema.addImport(imports, ILbsDataExchangeWriter.class.getName());
/* 444 */     if (!StringUtil.isEmpty(this.m_TypeName))
/* 445 */       ParameterSchema.addImport(imports, this.m_TypeName); 
/* 446 */     if (this.m_Type == PropertyType.type_BigDecimal)
/* 447 */       ParameterSchema.addImport(imports, BigDecimal.class.getName()); 
/* 448 */     if (this.m_Type == PropertyType.type_Calendar)
/* 449 */       ParameterSchema.addImport(imports, Calendar.class.getName()); 
/* 450 */     if (this.m_Type == PropertyType.type_Timestamp)
/* 451 */       ParameterSchema.addImport(imports, Timestamp.class.getName()); 
/* 452 */     if (isList())
/* 453 */       ParameterSchema.addImport(imports, List.class.getName()); 
/* 454 */     ParameterSchema.addImport(imports, XMLDescribeBuffer.class.getName());
/* 455 */     if (declare())
/*     */     {
/* 457 */       if (this.m_Serializable) {
/* 458 */         ParameterSchema.addImport(imports, "com.thoughtworks.xstream.annotations.XStreamAlias");
/*     */       } else {
/* 460 */         ParameterSchema.addImport(imports, "com.thoughtworks.xstream.annotations.XStreamOmitField");
/*     */       }  } 
/* 462 */     if (this.m_InitialValue != null)
/* 463 */       this.m_InitialValue.prepareImports(this, imports); 
/* 464 */     if (this.m_PartOfIdentifier)
/*     */     {
/* 466 */       ParameterSchema.addImport(imports, Identifiable.class.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArray(boolean array) {
/* 473 */     this.m_Array = array;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArray() {
/* 478 */     return this.m_Array;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyValue(String emptyValue) {
/* 483 */     this.m_EmptyValue = emptyValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEmptyValue() {
/* 488 */     return this.m_EmptyValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEmptyValue() {
/* 493 */     return !StringUtil.isEmpty(this.m_EmptyValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getDescResource() {
/* 498 */     return this.m_DescResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescResource(JLbsResourceId descResource) {
/* 503 */     this.m_DescResource = descResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 508 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 513 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedDescription(ILocalizationServices localService) {
/* 518 */     return ParameterSchema.formatDescription(calculateLocalizedDescription(localService));
/*     */   }
/*     */ 
/*     */   
/*     */   private String calculateLocalizedDescription(ILocalizationServices localService) {
/* 523 */     if (this.m_DescResource != null && localService != null) {
/*     */       
/* 525 */       String localDesc = localService.getItem(this.m_DescResource.getListId(), this.m_DescResource.getStringTag(), this.m_DescResource
/* 526 */           .getResourceGroup());
/* 527 */       if (!StringUtil.isEmpty(localDesc))
/* 528 */         return localDesc; 
/*     */     } 
/* 530 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescResourceGroup() {
/* 535 */     if (this.m_DescResource != null)
/* 536 */       return this.m_DescResource.getResourceGroup(); 
/* 537 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDescListId() {
/* 542 */     if (this.m_DescResource != null)
/* 543 */       return this.m_DescResource.getListId(); 
/* 544 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDescStrTag() {
/* 549 */     if (this.m_DescResource != null)
/* 550 */       return this.m_DescResource.getStringTag(); 
/* 551 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSerializable(boolean xmlSerializable) {
/* 556 */     this.m_Serializable = xmlSerializable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSerializable() {
/* 561 */     return this.m_Serializable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isList() {
/* 566 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbstractList() {
/* 571 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMap() {
/* 576 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFilters() {
/* 581 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean declare() {
/* 586 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartOfIdentifier(boolean partOfIdentifier) {
/* 591 */     this.m_PartOfIdentifier = partOfIdentifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartOfIdentifier() {
/* 596 */     return this.m_PartOfIdentifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ParameterProperty o) {
/* 601 */     return this.m_Name.compareTo(o.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 608 */     if (obj instanceof ParameterProperty)
/* 609 */       return (compareTo((ParameterProperty)obj) == 0); 
/* 610 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 616 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */