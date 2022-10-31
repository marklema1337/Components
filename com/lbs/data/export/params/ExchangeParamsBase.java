/*     */ package com.lbs.data.export.params;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.factory.ISubstitutionVariables;
/*     */ import com.lbs.data.objects.SimpleBusinessObject;
/*     */ import com.lbs.platform.interfaces.ILbsBOXMLSerializer;
/*     */ import com.lbs.platform.interfaces.ILbsExchangeCustomizationSerializer;
/*     */ import com.lbs.platform.interfaces.ILbsExchangeValidator;
/*     */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.SetUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
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
/*     */ public class ExchangeParamsBase
/*     */   implements IDataExchangeParams, Cloneable
/*     */ {
/*     */   public static boolean EXCHANGE_ON_SERVER = true;
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   protected int m_ExchangeType = 1;
/*     */   
/*     */   protected String m_BOClassName;
/*     */   protected String m_ObjectBOClassName;
/*     */   protected String m_CustGUID;
/*     */   protected String m_CustomObjectName;
/*     */   protected boolean m_CustomObject;
/*     */   protected ListDefinition m_ListDefinition;
/*  40 */   protected Hashtable<String, ILbsBOXMLSerializer> m_ExtensionSerializers = new Hashtable<>();
/*     */   
/*  42 */   protected int m_CalendarOption = 0;
/*  43 */   protected int m_Options = 144;
/*     */   
/*     */   protected String m_FileName;
/*     */   protected String m_XSLFileName;
/*  47 */   protected int m_DesiredObjectState = -1;
/*  48 */   protected int m_InsertOption = 0;
/*     */   
/*     */   protected byte[] m_XSLFileContent;
/*     */   
/*     */   protected int m_DomainNr;
/*     */   
/*     */   protected int m_ForbiddenOperations;
/*     */   
/*     */   protected ILbsExchangeValidator m_Validator;
/*     */   
/*     */   protected ISubstitutionVariables m_Variables;
/*     */   protected boolean m_Descriptive = true;
/*  60 */   protected IDataExchangeDescriptor m_Descriptor = null;
/*     */   
/*     */   protected ILbsExchangeCustomizationSerializer m_CustomSerializerFinder;
/*     */   
/*     */   public ExchangeParamsBase() {
/*  65 */     this.m_Options = 144;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExchangeParamsBase(ExchangeParamsBase copy) {
/*  70 */     this.m_ExchangeType = copy.m_ExchangeType;
/*  71 */     this.m_BOClassName = copy.m_BOClassName;
/*  72 */     this.m_CustGUID = copy.m_CustGUID;
/*  73 */     this.m_CustomObjectName = copy.m_CustomObjectName;
/*  74 */     this.m_ListDefinition = copy.m_ListDefinition;
/*  75 */     this.m_ExtensionSerializers = copy.m_ExtensionSerializers;
/*  76 */     this.m_CalendarOption = copy.m_CalendarOption;
/*  77 */     this.m_Options = copy.m_Options;
/*  78 */     this.m_FileName = copy.m_FileName;
/*  79 */     this.m_XSLFileName = copy.m_XSLFileName;
/*  80 */     this.m_DesiredObjectState = copy.m_DesiredObjectState;
/*  81 */     this.m_InsertOption = copy.m_InsertOption;
/*  82 */     this.m_XSLFileContent = copy.m_XSLFileContent;
/*  83 */     this.m_DomainNr = copy.m_DomainNr;
/*  84 */     this.m_ForbiddenOperations = copy.m_ForbiddenOperations;
/*  85 */     this.m_Validator = copy.m_Validator;
/*  86 */     this.m_Variables = copy.m_Variables;
/*  87 */     this.m_Descriptive = copy.m_Descriptive;
/*  88 */     this.m_Descriptor = copy.m_Descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IDataExchangeParams cloneParams() {
/*     */     try {
/*  95 */       Object clone = clone();
/*  96 */       if (clone instanceof ExchangeParamsBase) {
/*  97 */         return (ExchangeParamsBase)clone;
/*     */       }
/*  99 */     } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */ 
/*     */     
/* 102 */     return new ExchangeParamsBase(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsExchangeCustomizationSerializer getCustomSerializerFinder() {
/* 107 */     return this.m_CustomSerializerFinder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomSerializerFinder(ILbsExchangeCustomizationSerializer customSerializerFinder) {
/* 112 */     this.m_CustomSerializerFinder = customSerializerFinder;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCalendarExchangeOption() {
/* 117 */     return this.m_CalendarOption;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExchangeOptions() {
/* 122 */     return this.m_Options;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, ILbsBOXMLSerializer> getExtensionSerializers() {
/* 127 */     return this.m_ExtensionSerializers;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListDefinition getListDefinition() {
/* 132 */     return this.m_ListDefinition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListDefinition(ListDefinition listDefinition) {
/* 137 */     this.m_ListDefinition = listDefinition;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/* 142 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtensionSerializer(String extensionName, ILbsBOXMLSerializer serializer) {
/* 147 */     this.m_ExtensionSerializers.put(extensionName, serializer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBOClassName(String boClassName) {
/* 152 */     this.m_BOClassName = boClassName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBOClassName() {
/* 157 */     return this.m_BOClassName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectBOClassName(String boClassName) {
/* 162 */     this.m_ObjectBOClassName = boClassName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObjectBOClassName() {
/* 167 */     return this.m_ObjectBOClassName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarExchangeOption(int calendarOption) {
/* 172 */     this.m_CalendarOption = calendarOption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleOption(int option) {
/* 177 */     this.m_Options = SetUtil.toggleOption(this.m_Options, option, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOptionSet(int option) {
/* 182 */     return SetUtil.isOptionSet(this.m_Options, option);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOption(int option, boolean set) {
/* 187 */     if (set) {
/* 188 */       this.m_Options = SetUtil.setOption(this.m_Options, option);
/*     */     } else {
/* 190 */       this.m_Options = SetUtil.resetOption(this.m_Options, option);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getExchangeType() {
/* 195 */     return this.m_ExchangeType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExchangeType(int exchangeType) {
/* 200 */     this.m_ExchangeType = exchangeType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustGUID() {
/* 205 */     return this.m_CustGUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustGUID(String custGUID) {
/* 210 */     this.m_CustGUID = custGUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomObjectName() {
/* 215 */     return this.m_CustomObjectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomObjectName(String customObjectName) {
/* 220 */     this.m_CustomObjectName = customObjectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileName() {
/* 225 */     return this.m_FileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFileName(String fileName) {
/* 230 */     this.m_FileName = fileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDesiredObjectState() {
/* 235 */     return this.m_DesiredObjectState;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDesiredObjectState(int state) {
/* 240 */     this.m_DesiredObjectState = state;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInsertOption() {
/* 245 */     return this.m_InsertOption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInsertOption(int insertOption) {
/* 250 */     this.m_InsertOption = insertOption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXSLFileName(String xslFileName) {
/* 255 */     this.m_XSLFileName = xslFileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXSLFileName() {
/* 260 */     return this.m_XSLFileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXSLFileContent(byte[] xslFileContent) {
/* 265 */     this.m_XSLFileContent = xslFileContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getXSLFileContent() {
/* 270 */     return this.m_XSLFileContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readXSLFileContent() {
/* 275 */     if (!StringUtil.isEmpty(this.m_XSLFileName)) {
/*     */       
/*     */       try {
/*     */         
/* 279 */         setXSLFileContent(JLbsFileUtil.readFile(this.m_XSLFileName));
/*     */       }
/* 281 */       catch (IOException e) {
/*     */         
/* 283 */         LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDomainNr() {
/* 290 */     return this.m_DomainNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainNr(int domainNr) {
/* 295 */     this.m_DomainNr = domainNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getForbiddenOperations() {
/* 300 */     return this.m_ForbiddenOperations;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForbiddenOperations(int forbiddenOperations) {
/* 305 */     this.m_ForbiddenOperations = forbiddenOperations;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExchangeOptions(int options) {
/* 310 */     this.m_Options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValidator(ILbsExchangeValidator validator) {
/* 315 */     this.m_Validator = validator;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsValidationResult initialize(SimpleBusinessObject object) {
/* 320 */     if (this.m_Validator != null)
/* 321 */       return this.m_Validator.initialize(object); 
/* 322 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ISubstitutionVariables getVariables() {
/* 327 */     return this.m_Variables;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVariables(ISubstitutionVariables variables) {
/* 332 */     this.m_Variables = variables;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDescriptive() {
/* 337 */     return this.m_Descriptive;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptive(boolean descriptive) {
/* 342 */     this.m_Descriptive = descriptive;
/*     */   }
/*     */ 
/*     */   
/*     */   public IDataExchangeDescriptor getDescriptor() {
/* 347 */     return this.m_Descriptor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptor(IDataExchangeDescriptor descriptor) {
/* 352 */     this.m_Descriptor = descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExcelColumnMap(Hashtable<String, Integer> excelColumnMap) {}
/*     */ 
/*     */   
/*     */   public Hashtable<String, Integer> getExcelColumnMap() {
/* 361 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustomObject() {
/* 366 */     return this.m_CustomObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomObject(boolean customObject) {
/* 371 */     this.m_CustomObject = customObject;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ExchangeParamsBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */