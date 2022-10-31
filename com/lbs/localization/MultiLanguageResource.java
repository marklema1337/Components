/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.globalization.JLbsCultureConstants;
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class MultiLanguageResource
/*     */   implements IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("Resource")
/*     */   private JLbsResourceId m_Resource;
/*     */   @XStreamAlias("LangDepValues")
/*  27 */   private Hashtable<String, String> m_LanguageDepValues = new Hashtable<>();
/*     */   @XStreamAlias("DefaultValues")
/*  29 */   private Hashtable<String, String> m_DefaultValues = new Hashtable<>();
/*     */   
/*  31 */   private static ArrayList<String> ms_LangNames = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public MultiLanguageResource() {
/*  35 */     this.m_Resource = new JLbsResourceId();
/*     */   }
/*     */ 
/*     */   
/*     */   public MultiLanguageResource(IApplicationContext context, int listId, int stringTag) {
/*  40 */     this(context, new JLbsResourceId(null, listId, stringTag));
/*     */   }
/*     */ 
/*     */   
/*     */   public MultiLanguageResource(IApplicationContext context, JLbsResourceId resource) {
/*  45 */     this(context, resource, (Hashtable<String, String>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public MultiLanguageResource(IApplicationContext context, JLbsResourceId resource, Hashtable<String, String> languageDepValues) {
/*  50 */     this.m_Resource = resource;
/*  51 */     if (this.m_Resource == null)
/*  52 */       this.m_Resource = new JLbsResourceId(); 
/*  53 */     this.m_LanguageDepValues = languageDepValues;
/*  54 */     if (this.m_LanguageDepValues == null)
/*  55 */       this.m_LanguageDepValues = new Hashtable<>(); 
/*  56 */     if (context != null) {
/*     */       
/*  58 */       ILocalizationServices service = context.getLocalizationService();
/*  59 */       initializeDefaultValues(service);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void initializeDefaultValues(ILocalizationServices service) {
/*  65 */     if (this.m_Resource != null && this.m_Resource.getListId() != 0) {
/*     */       
/*  67 */       initLangNames();
/*  68 */       for (String langName : ms_LangNames)
/*     */       {
/*  70 */         putDefaultValue(langName, this.m_Resource.getLocalizedValue(service, langName));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void initLangNames() {
/*  78 */     if (ms_LangNames == null || ms_LangNames.size() == 0) {
/*     */       
/*  80 */       ms_LangNames = new ArrayList<>();
/*  81 */       JLbsCultureInfoBase[] supportedCultures = JLbsCultureConstants.prepareCultureObjects(); byte b; int i; JLbsCultureInfoBase[] arrayOfJLbsCultureInfoBase1;
/*  82 */       for (i = (arrayOfJLbsCultureInfoBase1 = supportedCultures).length, b = 0; b < i; ) { JLbsCultureInfoBase culture = arrayOfJLbsCultureInfoBase1[b];
/*     */         
/*  84 */         ms_LangNames.add(culture.getLanguagePrefix()); b++; }
/*     */       
/*  86 */       Collections.sort(ms_LangNames);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void putDefaultValue(String langName, String value) {
/*  92 */     if (value != null && langName != null) {
/*  93 */       this.m_DefaultValues.put(langName, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void putLangDepValue(String langName, String value) {
/*  98 */     if (value != null && langName != null) {
/*  99 */       this.m_LanguageDepValues.put(langName, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public Hashtable<String, String> getLanguageDepValues() {
/* 104 */     return this.m_LanguageDepValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListId() {
/* 109 */     return this.m_Resource.getListId();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/* 114 */     return this.m_Resource.getStringTag();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListId(int listId) {
/* 119 */     this.m_Resource.setListId(listId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int stringTag) {
/* 124 */     this.m_Resource.setStringTag(stringTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue(String langName) {
/* 129 */     String value = this.m_LanguageDepValues.get(langName);
/* 130 */     if (!StringUtil.isEmpty(value))
/* 131 */       return value; 
/* 132 */     return this.m_DefaultValues.get(langName);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getLangNames() {
/* 137 */     initLangNames();
/* 138 */     return ms_LangNames;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 144 */     writer.startObject(getClass().getName(), "");
/* 145 */     describePropertiesXML(writer, localizationService);
/* 146 */     writer.endObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 152 */     writer.startObjectProperty("Resource", "JLbsResourceId", null, false);
/* 153 */     writer.writeInnerObject(this.m_Resource, JLbsResourceId.class, localizationService);
/* 154 */     writer.endObjectProperty();
/* 155 */     writer.startObjectProperty("LanguageDepValues", "Map<String, String>", null, false);
/* 156 */     writer.writeInnerMapObject(String.class, String.class, localizationService);
/* 157 */     writer.endObjectProperty();
/* 158 */     writer.startObjectProperty("DefaultValues", "Map<String, String>", null, false);
/* 159 */     writer.writeInnerMapObject(String.class, String.class, localizationService);
/* 160 */     writer.endObjectProperty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 171 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 177 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\MultiLanguageResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */