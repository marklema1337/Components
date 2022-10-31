/*     */ package com.lbs.data.export;
/*     */ 
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
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
/*     */ public class JExportMapItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int PROP_TYPE_NORMAL = 0;
/*     */   public static final int PROP_TYPE_COLLECTION = 1;
/*     */   public static final int PROP_TYPE_OBJECT = 2;
/*     */   private String m_ParentPropertyName;
/*     */   private String m_PropertyName;
/*     */   private String m_MapName;
/*     */   private String m_MapDescription;
/*     */   private int m_Include;
/*     */   private int m_BackwardLink;
/*     */   private int m_UserForKeyResolve;
/*     */   private int m_PropertyType;
/*     */   private boolean m_ReadOnly = false;
/*     */   private boolean m_ChildrenProcessed = true;
/*  38 */   private int m_ExcelColumnIdx = -1;
/*  39 */   private int m_ListId = 0;
/*  40 */   private int m_Tag = 0;
/*     */   
/*     */   private int m_PropertyDbType;
/*  43 */   private Hashtable<String, String> m_LanguageDescriptions = new Hashtable<>();
/*     */   
/*     */   private String m_ObjectName;
/*  46 */   private transient List m_ChildPropertiesList = null;
/*     */   private IExportPropertyProvider m_PropertyProvider;
/*     */   
/*     */   public String getMapDescription() {
/*  50 */     return this.m_MapDescription;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMapDescription(String mapDescription) {
/*  55 */     this.m_MapDescription = mapDescription;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListId() {
/*  60 */     return this.m_ListId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListId(int listId) {
/*  65 */     this.m_ListId = listId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/*  70 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/*  75 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInclude() {
/*  82 */     return this.m_Include;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInclude(int include) {
/*  87 */     if (this.m_ReadOnly && JLbsStringUtil.isEmpty(getParentPropertyName()))
/*     */       return; 
/*  89 */     this.m_Include = include;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMapName() {
/*  94 */     return this.m_MapName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMapName(String mapName) {
/*  99 */     this.m_MapName = mapName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 104 */     return this.m_PropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropertyName(String propertyName) {
/* 109 */     this.m_PropertyName = propertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParentPropertyName() {
/* 114 */     return this.m_ParentPropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentPropertyName(String parentPropertyName) {
/* 119 */     this.m_ParentPropertyName = parentPropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQualifiedPropertyName() {
/* 124 */     return (JLbsStringUtil.isEmpty(this.m_ParentPropertyName) ? "" : (this.m_ParentPropertyName + ".")) + this.m_PropertyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBackwardLink() {
/* 131 */     return this.m_BackwardLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackwardLink(int backwardLink) {
/* 136 */     this.m_BackwardLink = backwardLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUserForKeyResolve() {
/* 141 */     return this.m_UserForKeyResolve;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserForKeyResolve(int userForKeyResolve) {
/* 146 */     this.m_UserForKeyResolve = userForKeyResolve;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/* 151 */     return this.m_PropertyType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropertyType(int propertyType) {
/* 156 */     this.m_PropertyType = propertyType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReadOnly(boolean readOnly) {
/* 161 */     this.m_ReadOnly = readOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChildren() {
/* 166 */     return (this.m_PropertyType == 1 || this.m_PropertyType == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollection() {
/* 171 */     return (this.m_PropertyType == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropertyProvider(IExportPropertyProvider propertyProvider) {
/* 176 */     this.m_PropertyProvider = propertyProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   public IExportPropertyProvider getPropertyProvider() {
/* 181 */     return this.m_PropertyProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildProperties(List childPropertiesList) {
/* 186 */     this.m_ChildPropertiesList = childPropertiesList;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getChildProperties() {
/* 191 */     if (this.m_ChildPropertiesList != null) {
/* 192 */       return this.m_ChildPropertiesList;
/*     */     }
/* 194 */     if (this.m_PropertyProvider != null)
/*     */     {
/* 196 */       this.m_ChildPropertiesList = this.m_PropertyProvider.getProperties(getQualifiedPropertyName());
/*     */     }
/* 198 */     return this.m_ChildPropertiesList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 203 */     return this.m_ReadOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChildrenProcessed() {
/* 208 */     return this.m_ChildrenProcessed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildrenProcessed(boolean childrenProcessed) {
/* 213 */     this.m_ChildrenProcessed = childrenProcessed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 219 */     if (obj instanceof JExportMapItem) {
/*     */       
/* 221 */       JExportMapItem item = (JExportMapItem)obj;
/* 222 */       return (StringUtil.equals(this.m_MapName, item.m_MapName) && 
/* 223 */         StringUtil.equals(this.m_ParentPropertyName, item.m_ParentPropertyName) && 
/* 224 */         StringUtil.equals(this.m_PropertyName, item.m_PropertyName) && this.m_PropertyType == item.m_PropertyType);
/*     */     } 
/* 226 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 232 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExcelColumnIdx(int excelColumnIdx) {
/* 240 */     this.m_ExcelColumnIdx = excelColumnIdx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExcelColumnIdx() {
/* 248 */     return this.m_ExcelColumnIdx;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropertyDbType(int propertyDbType) {
/* 253 */     this.m_PropertyDbType = propertyDbType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPropertyDbType() {
/* 258 */     return this.m_PropertyDbType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putLanguageDescription(String langName, String description) {
/* 263 */     this.m_LanguageDescriptions.put(langName, description);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageDescription(String langName) {
/* 268 */     return this.m_LanguageDescriptions.get(langName);
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, String> getLanguageDescriptions() {
/* 273 */     return this.m_LanguageDescriptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLangDependantDescription(ILocalizationServices localService, String langName) {
/* 278 */     if (this.m_LanguageDescriptions != null) {
/*     */       
/* 280 */       String description = this.m_LanguageDescriptions.get(langName);
/* 281 */       if (!StringUtil.isEmpty(description))
/* 282 */         return description; 
/*     */     } 
/* 284 */     if (localService != null)
/*     */     {
/* 286 */       if (this.m_ListId > 0) {
/*     */         
/* 288 */         String description = localService.getItem(this.m_ListId, this.m_Tag);
/* 289 */         if (!StringUtil.isEmpty(description))
/* 290 */           return description; 
/*     */       } 
/*     */     }
/* 293 */     return this.m_MapName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLanguageDescriptions(Hashtable<String, String> languageDescriptions) {
/* 298 */     this.m_LanguageDescriptions = languageDescriptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 303 */     this.m_ObjectName = objectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/* 308 */     return this.m_ObjectName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExactInclude(int include) {
/* 313 */     this.m_Include = include;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\JExportMapItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */