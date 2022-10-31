/*     */ package com.lbs.parameter;
/*     */ 
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.platform.interfaces.IDataWriter;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.IParameterSerializer;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParameterSchema
/*     */   implements Comparable<ParameterSchema>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Identifier m_Identifier;
/*     */   private Identifier m_BaseParamIdentifier;
/*     */   private ParameterSchema m_BaseParameter;
/*     */   private boolean m_Generate;
/*     */   private boolean m_Wrapper;
/*     */   private String m_ModuleName;
/*     */   private String m_PackageName;
/*     */   private JLbsResourceId m_DescResource;
/*     */   private String m_Description;
/*  50 */   private ArrayList<ParameterProperty> m_Properties = new ArrayList<>();
/*     */   
/*  52 */   private ArrayList<ParameterConstructor> m_Constructors = new ArrayList<>();
/*     */   
/*  54 */   private Hashtable<String, ArrayList<ParameterFilterGroup>> m_FilterProps = new Hashtable<>();
/*     */ 
/*     */   
/*     */   public void initialize(boolean checkWrappers) throws ParameterSchemaException {
/*  58 */     if (this.m_BaseParameter == null && this.m_BaseParamIdentifier != null) {
/*     */       
/*  60 */       this.m_BaseParameter = ParameterSchemaRegistry.findParameter(this.m_BaseParamIdentifier);
/*  61 */       if (this.m_BaseParameter == null)
/*  62 */         throw new ParameterSchemaException("Cannot find base parameter with name : " + this.m_BaseParamIdentifier); 
/*  63 */       if (!this.m_Wrapper && this.m_BaseParameter.isWrapper()) {
/*  64 */         throw new ParameterSchemaException("Cannot extend a wrapper parameter (" + this.m_BaseParamIdentifier + ") in " + this.m_Identifier + "! Only a wrapper parameter can extend another wrapper parameter!");
/*     */       }
/*     */     } 
/*  67 */     if (checkWrappers && this.m_Wrapper) {
/*     */       
/*     */       try {
/*     */         
/*  71 */         Class<?> c = Class.forName(getQualifiedName());
/*  72 */         if (!IParameter.class.isAssignableFrom(c)) {
/*  73 */           throw new ParameterSchemaException("Wrapper parameter class '" + c.getName() + "' is not implementing '" + IParameter.class
/*  74 */               .getName() + "'! Only classes that implement IParameter interface can be defined as wrapper parameters.");
/*     */         }
/*     */       }
/*  77 */       catch (ClassNotFoundException e) {
/*     */         
/*  79 */         throw new ParameterSchemaException(e);
/*     */       } 
/*     */     }
/*  82 */     for (ParameterProperty prop : this.m_Properties)
/*     */     {
/*  84 */       prop.initialize(this, checkWrappers);
/*     */     }
/*  86 */     if (hasFilterProps())
/*     */     {
/*  88 */       for (String propName : getFilterPropNames()) {
/*     */         
/*  90 */         ParameterFilterlistProperty filterProp = findFilterListProp(propName, false);
/*  91 */         if (filterProp == null) {
/*  92 */           throw new ParameterSchemaException("Could not find filter list property named '" + propName + "' in parameter named '" + this.m_Identifier + "'!");
/*     */         }
/*  94 */         if (findFilterListProp(propName, true) == null && 
/*  95 */           !filterProp.hasSetter() && (filterProp
/*  96 */           .getModifier() == ParameterProperty.Modifier.mod_private || filterProp.getModifier() == ParameterProperty.Modifier.mod_package)) {
/*  97 */           throw new ParameterSchemaException("There is no way to access filter list property named '" + propName + "' in parameter named '" + this.m_Identifier + "'! Please either change modifier of this property to at least protected, or set hasSetter to true!");
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParameterFilterlistProperty findFilterListProp(String propName, boolean onlyMyProps) {
/* 109 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 111 */       if (StringUtil.equals(propName, prop.getName()))
/*     */       {
/* 113 */         if (prop instanceof ParameterFilterlistProperty)
/* 114 */           return (ParameterFilterlistProperty)prop; 
/*     */       }
/*     */     } 
/* 117 */     if (!onlyMyProps && 
/* 118 */       this.m_BaseParameter != null) {
/* 119 */       return this.m_BaseParameter.findFilterListProp(propName, false);
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasProperty(String propName) {
/* 126 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 128 */       if (prop.getName().equals(propName))
/* 129 */         return true; 
/*     */     } 
/* 131 */     if (this.m_BaseParameter != null)
/* 132 */       return this.m_BaseParameter.hasProperty(propName); 
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addImport(ArrayList<String> imports, String s) {
/* 138 */     if (!imports.contains(s) && s.indexOf('.') > 0) {
/* 139 */       imports.add(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<String> prepareImports() {
/* 144 */     ArrayList<String> imports = new ArrayList<>();
/* 145 */     if (this.m_BaseParameter != null) {
/* 146 */       addImport(imports, this.m_BaseParameter.getQualifiedName());
/*     */     } else {
/* 148 */       addImport(imports, Parameter.class.getName());
/* 149 */     }  addImport(imports, ILocalizationServices.class.getName());
/* 150 */     addImport(imports, XMLDescribeBuffer.class.getName());
/* 151 */     addImport(imports, XMLSerializerBase.class.getName());
/* 152 */     addImport(imports, Element.class.getName());
/* 153 */     addImport(imports, IDataExchangeParams.class.getName());
/* 154 */     addImport(imports, ILbsDataExchangeWriter.class.getName());
/* 155 */     addImport(imports, IDataWriter.class.getName());
/* 156 */     addImport(imports, IParameterSerializer.class.getName());
/*     */     
/* 158 */     for (ParameterProperty prop : this.m_Properties)
/*     */     {
/* 160 */       prop.prepareImports(imports);
/*     */     }
/* 162 */     if (this.m_BaseParameter != null)
/*     */     {
/* 164 */       for (ParameterProperty prop : this.m_BaseParameter.getProperties())
/*     */       {
/* 166 */         prop.prepareImports(imports);
/*     */       }
/*     */     }
/*     */     
/* 170 */     if (hasFilterProps()) {
/*     */       
/* 172 */       addImport(imports, "com.lbs.filter.JLbsFilterList");
/* 173 */       for (String filterPropName : getFilterPropNames()) {
/*     */         
/* 175 */         for (ParameterFilterGroup filterGrp : getFilterGroupsForProp(filterPropName))
/*     */         {
/* 177 */           filterGrp.prepareImports(imports);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     return imports;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMandatoryProp() {
/* 187 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 189 */       if (prop.isMandatory())
/* 190 */         return true; 
/*     */     } 
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasObjectIdentifier() {
/* 197 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 199 */       if (prop instanceof ObjectIdentifierProperty)
/* 200 */         return true; 
/*     */     } 
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean superHasObjectIdentifier() {
/* 207 */     if (this.m_BaseParameter != null)
/* 208 */       return this.m_BaseParameter.hasObjectIdentifier(); 
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ObjectIdentifierProperty> getObjectIdentifierProps() {
/* 214 */     ArrayList<ObjectIdentifierProperty> props = new ArrayList<>();
/* 215 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 217 */       if (prop instanceof ObjectIdentifierProperty)
/* 218 */         props.add((ObjectIdentifierProperty)prop); 
/*     */     } 
/* 220 */     return props;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasIdentifier() {
/* 225 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 227 */       if (prop.isPartOfIdentifier())
/* 228 */         return true; 
/*     */     } 
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ParameterProperty> getMandatoryProps() {
/* 235 */     ArrayList<ParameterProperty> props = new ArrayList<>();
/* 236 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 238 */       if (prop.isMandatory())
/* 239 */         props.add(prop); 
/*     */     } 
/* 241 */     return props;
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getIdentifier() {
/* 246 */     return this.m_Identifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifier(Identifier name) {
/* 251 */     this.m_Identifier = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQualifiedName() {
/* 256 */     return getPackageName() + "." + this.m_Identifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQualifiedId() {
/* 261 */     return getPackageName() + "." + this.m_Identifier.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBaseParameter() {
/* 266 */     return (this.m_BaseParameter != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ParameterSchema getBaseParameter() {
/* 271 */     return this.m_BaseParameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseParameter(ParameterSchema baseParameter) {
/* 276 */     this.m_BaseParameter = baseParameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseParamIdentifier(Identifier baseParamName) {
/* 281 */     this.m_BaseParamIdentifier = baseParamName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getBaseParamIdentifier() {
/* 286 */     return this.m_BaseParamIdentifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenerate() {
/* 291 */     return this.m_Generate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenerate(boolean generate) {
/* 296 */     this.m_Generate = generate;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModuleName() {
/* 301 */     return this.m_ModuleName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModuleName(String moduleName) {
/* 306 */     this.m_ModuleName = moduleName;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ParameterProperty> getProperties() {
/* 311 */     return this.m_Properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProperties(ArrayList<ParameterProperty> properties) {
/* 316 */     this.m_Properties = properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ParameterConstructor> getConstructors() {
/* 321 */     return this.m_Constructors;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConstructors(ArrayList<ParameterConstructor> constructors) {
/* 326 */     this.m_Constructors = constructors;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPackageName() {
/* 331 */     if (!StringUtil.isEmpty(this.m_PackageName))
/* 332 */       return this.m_PackageName; 
/* 333 */     return "com.lbs.par.gen." + this.m_ModuleName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPackageName(String packageName) {
/* 338 */     this.m_PackageName = packageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescResource(JLbsResourceId descResource) {
/* 343 */     this.m_DescResource = descResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getDescResource() {
/* 348 */     return this.m_DescResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 353 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 358 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWrapper(boolean wrapper) {
/* 363 */     this.m_Wrapper = wrapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWrapper() {
/* 368 */     return this.m_Wrapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedDescription(ILocalizationServices localService) {
/* 373 */     return formatDescription(calculateDescription(localService));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatDescription(String description) {
/* 379 */     return JLbsStringUtil.escapeJava(description);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatForJavaClass(String description) {
/* 384 */     if (description == null)
/* 385 */       return null; 
/* 386 */     int length = description.length();
/* 387 */     int cnt = 30;
/* 388 */     if (length > cnt) {
/*     */       
/* 390 */       StringBuilder sb = new StringBuilder();
/* 391 */       int idx = 0;
/* 392 */       while (idx < length) {
/*     */         
/* 394 */         int endIdx = findEndIdx(idx, length, description, cnt);
/* 395 */         sb.append(description.substring(idx, endIdx));
/* 396 */         if (endIdx == length)
/*     */           break; 
/* 398 */         sb.append("\"\n + \"");
/* 399 */         idx = endIdx;
/*     */       } 
/* 401 */       return sb.toString();
/*     */     } 
/* 403 */     return description;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int findEndIdx(int idx, int length, String description, int cnt) {
/* 408 */     int endIdx = idx + cnt;
/* 409 */     if (endIdx > length)
/* 410 */       return length; 
/* 411 */     while (Character.isWhitespace(description.charAt(endIdx)))
/* 412 */       endIdx--; 
/* 413 */     return endIdx;
/*     */   }
/*     */ 
/*     */   
/*     */   private String calculateDescription(ILocalizationServices localService) {
/* 418 */     if (this.m_DescResource != null && localService != null) {
/*     */       
/* 420 */       String localDesc = localService.getItem(this.m_DescResource.getListId(), this.m_DescResource.getStringTag(), this.m_DescResource
/* 421 */           .getResourceGroup());
/* 422 */       if (!StringUtil.isEmpty(localDesc))
/* 423 */         return localDesc; 
/*     */     } 
/* 425 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescResourceGroup() {
/* 430 */     if (this.m_DescResource != null)
/* 431 */       return this.m_DescResource.getResourceGroup(); 
/* 432 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDescListId() {
/* 437 */     if (this.m_DescResource != null)
/* 438 */       return this.m_DescResource.getListId(); 
/* 439 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDescStrTag() {
/* 444 */     if (this.m_DescResource != null)
/* 445 */       return this.m_DescResource.getStringTag(); 
/* 446 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFilterGroups(String filterPropName, ArrayList<ParameterFilterGroup> filters) {
/* 451 */     this.m_FilterProps.put(filterPropName, filters);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFilterProps() {
/* 456 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 458 */       if (prop instanceof ParameterFilterlistProperty)
/* 459 */         return true; 
/*     */     } 
/* 461 */     return (this.m_FilterProps.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClassIdentifier(String typeName) {
/* 466 */     String id = getIdentifier().getId();
/* 467 */     if (hasAbstractListProp())
/*     */     {
/* 469 */       if (StringUtil.isEmpty(typeName)) {
/* 470 */         id = id + "<T extends " + getAbstractListTypeName() + ">";
/*     */       } else {
/* 472 */         id = id + "<" + typeName + ">";
/*     */       }  } 
/* 474 */     return id;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAbstractListProp() {
/* 479 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 481 */       if (prop.isAbstractList())
/* 482 */         return true; 
/*     */     } 
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAbstractListTypeName() {
/* 489 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 491 */       if (prop.isAbstractList())
/* 492 */         return prop.getTypeString(prop.getType(), prop.getTypeName()); 
/*     */     } 
/* 494 */     return "Object";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBrowserListTypeName() {
/* 499 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 501 */       if (prop instanceof ParameterBrowserListProperty && !prop.isAbstractList())
/* 502 */         return prop.getTypeString(prop.getType(), prop.getTypeName()); 
/*     */     } 
/* 504 */     return "Object";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getFilterPropNames() {
/* 509 */     return Collections.list(this.m_FilterProps.keys());
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ParameterFilterGroup> getFilterGroupsForProp(String propName) {
/* 514 */     ArrayList<ParameterFilterGroup> filters = this.m_FilterProps.get(propName);
/* 515 */     if (filters == null)
/* 516 */       return new ArrayList<>(); 
/* 517 */     return filters;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAccessFilterProp(String propName) {
/* 522 */     ParameterFilterlistProperty prop = findFilterListProp(propName, false);
/* 523 */     if (findFilterListProp(propName, true) == null)
/*     */     {
/* 525 */       return (prop.getModifier() == ParameterProperty.Modifier.mod_protected || prop.getModifier() == ParameterProperty.Modifier.mod_public);
/*     */     }
/* 527 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 533 */     return this.m_Identifier.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ParameterSchema o) {
/* 538 */     return this.m_Identifier.toSingleString().compareTo(o.getIdentifier().toSingleString());
/*     */   }
/*     */ 
/*     */   
/*     */   public ParameterProperty findProperty(String propertyName) {
/* 543 */     for (ParameterProperty prop : this.m_Properties) {
/*     */       
/* 545 */       if (prop.getName().equals(propertyName))
/* 546 */         return prop; 
/*     */     } 
/* 548 */     if (this.m_BaseParameter != null)
/* 549 */       return this.m_BaseParameter.findProperty(propertyName); 
/* 550 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 557 */     if (obj instanceof ParameterSchema)
/* 558 */       return (compareTo((ParameterSchema)obj) == 0); 
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 565 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */