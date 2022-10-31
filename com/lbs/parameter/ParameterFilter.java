/*     */ package com.lbs.parameter;
/*     */ 
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
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
/*     */ public class ParameterFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_XUIDoc;
/*  29 */   private FilterType m_Type = FilterType.type_STRING;
/*     */   
/*     */   private int m_Tag;
/*     */   
/*     */   private String m_Title;
/*     */   
/*     */   private int m_ApplicationID;
/*     */   private JLbsResourceId m_ResourceId;
/*     */   private String m_DataField;
/*     */   private String m_QueryTableLink;
/*     */   private String m_QueryParameter;
/*     */   private String m_QueryParameterLB;
/*     */   private String m_QueryParameterUB;
/*     */   private String m_QueryRestrictiveTerm;
/*     */   private String m_QueryRangeTerm;
/*     */   private int m_ListID;
/*     */   private String m_ResourceGroup;
/*  46 */   private Hashtable<String, String> m_XUIProperties = new Hashtable<>();
/*     */   
/*     */   private String m_ModeAttributes;
/*     */   
/*     */   private boolean forGwt;
/*     */ 
/*     */   
/*     */   public void printXUIDefString(StringBuilder sb) {
/*  54 */     sb.append("<Filter Tag=\"" + this.m_Tag + "\" Type=\"" + this.m_Type.getTypeName() + "\" Title=\"" + this.m_Title + "\" XUIDoc=\"" + this.m_XUIDoc + "\" ApplicationID=\"" + this.m_ApplicationID + "\">\n");
/*     */     
/*  56 */     printXUIProperty(sb, this.m_DataField, "DataField");
/*  57 */     printXUIProperty(sb, this.m_QueryParameter, "QueryParameter");
/*  58 */     printXUIProperty(sb, this.m_QueryParameterUB, "QueryParameterUB");
/*  59 */     printXUIProperty(sb, this.m_QueryRangeTerm, "QueryRangeTerm");
/*  60 */     printXUIProperty(sb, this.m_QueryRestrictiveTerm, "QueryRestrictiveTerm");
/*  61 */     printXUIProperty(sb, this.m_QueryTableLink, "QueryTableLink");
/*  62 */     printXUIProperty(sb, this.m_QueryParameterLB, "QueryParameterLB");
/*  63 */     if (this.m_ListID != 0)
/*     */     {
/*  65 */       printXUIProperty(sb, this.m_ListID + "", "ListID");
/*     */     }
/*  67 */     if (this.m_ResourceId != null && this.m_ResourceId.getListId() != 0)
/*     */     {
/*  69 */       sb.append("<Resource Property=\"Title\" ListID=\"" + this.m_ResourceId.getListId() + "\" StringTag=\"" + this.m_ResourceId
/*  70 */           .getStringTag() + "\" />\n");
/*     */     }
/*  72 */     Enumeration<String> propNames = this.m_XUIProperties.keys();
/*  73 */     while (propNames.hasMoreElements()) {
/*     */       
/*  75 */       String propName = propNames.nextElement();
/*  76 */       String value = this.m_XUIProperties.get(propName);
/*  77 */       printXUIProperty(sb, value, propName);
/*     */     } 
/*  79 */     if (this.m_ModeAttributes != null && 
/*  80 */       !StringUtil.isEmpty(this.m_ModeAttributes)) {
/*  81 */       sb.append("<ModeAttributes>\n" + this.m_ModeAttributes + "</ModeAttributes>\n");
/*     */     }
/*  83 */     sb.append("</Filter>\n");
/*     */   }
/*     */ 
/*     */   
/*     */   private void printXUIProperty(StringBuilder sb, String value, String propName) {
/*  88 */     if (!StringUtil.isEmpty(value))
/*     */     {
/*  90 */       sb.append("<Property " + propName + "=\"" + value + "\" />\n");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/*  96 */     switch (this.m_Type) {
/*     */       
/*     */       case type_DATE:
/*  99 */         return "com.lbs.filter.JLbsFilterDate";
/*     */       case type_DATERANGE:
/* 101 */         return "com.lbs.filter.JLbsFilterDateRange";
/*     */       case type_GROUP:
/* 103 */         return "com.lbs.filter.JLbsFilterGroupSelection";
/*     */       case type_LOOKUPFILTER:
/* 105 */         return "com.lbs.filter.JLbsFilterLookup";
/*     */       case type_NUMERIC:
/* 107 */         return "com.lbs.filter.JLbsFilterNumeric";
/*     */       case type_NUMERICRANGE:
/* 109 */         return "com.lbs.filter.JLbsFilterNumericRange";
/*     */       case type_SELECTION:
/* 111 */         return "com.lbs.filter.JLbsFilterSelection";
/*     */       case type_STRING:
/* 113 */         return "com.lbs.filter.JLbsFilterString";
/*     */       case type_STRINGRANGE:
/* 115 */         return "com.lbs.filter.JLbsFilterStringRange";
/*     */       case type_TIME:
/* 117 */         return "com.lbs.filter.JLbsFilterTime";
/*     */       case type_TIMERANGE:
/* 119 */         return "com.lbs.filter.JLbsFilterTimeRange";
/*     */     } 
/* 121 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypeStringOnly() {
/* 126 */     return StringUtil.getNameFromQualified(getTypeString());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasResourceId() {
/* 131 */     return (this.m_ResourceId != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdString() {
/* 136 */     String resGroup = this.m_ResourceId.getResourceGroup();
/* 137 */     if (StringUtil.isEmpty(resGroup))
/* 138 */       resGroup = "UN"; 
/* 139 */     return "new JLbsResourceId(\"" + resGroup + "\", " + this.m_ResourceId.getListId() + ", " + this.m_ResourceId.getStringTag() + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void prepareImports(ArrayList<String> imports) {
/* 144 */     ParameterSchema.addImport(imports, getTypeString());
/* 145 */     ParameterSchema.addImport(imports, "com.lbs.filter.JLbsFilterQueryParams");
/* 146 */     if (hasResourceId()) {
/* 147 */       ParameterSchema.addImport(imports, JLbsResourceId.class.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   private String safeString(String s) {
/* 152 */     if (s == null)
/* 153 */       return ""; 
/* 154 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXUIDoc() {
/* 159 */     return safeString(this.m_XUIDoc);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXUIDoc(String xUIDoc) {
/* 164 */     this.m_XUIDoc = xUIDoc;
/*     */   }
/*     */ 
/*     */   
/*     */   public FilterType getType() {
/* 169 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(FilterType type) {
/* 174 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 179 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/* 184 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 189 */     return safeString(this.m_Title);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 194 */     this.m_Title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getApplicationID() {
/* 199 */     return this.m_ApplicationID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setApplicationID(int applicationID) {
/* 204 */     this.m_ApplicationID = applicationID;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getResourceId() {
/* 209 */     return this.m_ResourceId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResourceId(JLbsResourceId resourceId) {
/* 214 */     this.m_ResourceId = resourceId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDataField() {
/* 219 */     return safeString(this.m_DataField);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDataField(String dataField) {
/* 224 */     this.m_DataField = dataField;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryTableLink() {
/* 229 */     return safeString(this.m_QueryTableLink);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryTableLink(String queryTableLink) {
/* 234 */     this.m_QueryTableLink = queryTableLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryParameter() {
/* 239 */     return safeString(this.m_QueryParameter);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParameter(String queryParameter) {
/* 244 */     this.m_QueryParameter = queryParameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryParameterLB() {
/* 249 */     return safeString(this.m_QueryParameterLB);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParameterLB(String queryParameterLB) {
/* 254 */     this.m_QueryParameterLB = queryParameterLB;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryParameterUB() {
/* 259 */     return safeString(this.m_QueryParameterUB);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParameterUB(String queryParameterUB) {
/* 264 */     this.m_QueryParameterUB = queryParameterUB;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryRestrictiveTerm() {
/* 269 */     return safeString(this.m_QueryRestrictiveTerm);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryRestrictiveTerm(String queryRestrictiveTerm) {
/* 274 */     this.m_QueryRestrictiveTerm = queryRestrictiveTerm;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryRangeTerm() {
/* 279 */     return safeString(this.m_QueryRangeTerm);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryRangeTerm(String queryRangeTerm) {
/* 284 */     this.m_QueryRangeTerm = queryRangeTerm;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListID() {
/* 289 */     return this.m_ListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListID(int listID) {
/* 294 */     this.m_ListID = listID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasList() {
/* 299 */     return (this.m_Type == FilterType.type_GROUP || this.m_Type == FilterType.type_SELECTION);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResourceGroup(String resourceGroup) {
/* 304 */     this.m_ResourceGroup = resourceGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceGroup() {
/* 309 */     if (StringUtil.isEmpty(this.m_ResourceGroup))
/* 310 */       return "UN"; 
/* 311 */     return this.m_ResourceGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, String> getXUIProperties() {
/* 316 */     return this.m_XUIProperties;
/*     */   }
/*     */   
/*     */   public enum FilterType {
/* 320 */     type_STRING("STRING"), type_STRINGRANGE("STRINGRANGE"), type_NUMERIC("NUMERIC"), type_NUMERICRANGE("NUMERICRANGE"), type_DATE("DATE"),
/* 321 */     type_DATERANGE("DATERANGE"), type_TIME("TIME"), type_TIMERANGE("TIMERANGE"), type_GROUP("GROUP"), type_SELECTION("SELECTION"),
/* 322 */     type_LOOKUPFILTER("LOOKUPFILTER"), type_AUTOCOMPLETE("AUTOCOMPLETE");
/*     */ 
/*     */ 
/*     */     
/*     */     private String m_TypeName;
/*     */ 
/*     */ 
/*     */     
/*     */     FilterType(String typeName) {
/*     */       this.m_TypeName = typeName;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 336 */     private static final Map<String, FilterType> ms_LookupTable = new HashMap<>();
/*     */ 
/*     */     
/*     */     static {
/* 340 */       for (FilterType s : EnumSet.<FilterType>allOf(FilterType.class)) {
/* 341 */         ms_LookupTable.put(s.getTypeName(), s);
/*     */       }
/*     */     }
/*     */     
/*     */     public static FilterType get(String typeName) {
/* 346 */       return ms_LookupTable.get(typeName);
/*     */     }
/*     */     public String getTypeName() {
/*     */       return this.m_TypeName;
/*     */     } }
/*     */   public boolean isForGwt() {
/* 352 */     return this.forGwt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForGwt(boolean forGwt) {
/* 357 */     this.forGwt = forGwt;
/*     */   }
/*     */   
/*     */   public String getModeAttributes() {
/* 361 */     return this.m_ModeAttributes;
/*     */   }
/*     */   
/*     */   public void setModeAttributes(String m_ModeAttributes) {
/* 365 */     this.m_ModeAttributes = m_ModeAttributes;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */