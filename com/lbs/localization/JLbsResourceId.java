/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsResourceId
/*     */   implements Serializable, IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("ResourceGroup")
/*     */   private String m_ResourceGroup;
/*     */   @XStreamAlias("ListId")
/*     */   private int m_ListId;
/*     */   @XStreamAlias("StringTag")
/*     */   private int m_StringTag;
/*     */   
/*     */   public JLbsResourceId() {}
/*     */   
/*     */   public JLbsResourceId(String resourceGroup, int listId, int stringTag) {
/*  42 */     this.m_ResourceGroup = resourceGroup;
/*  43 */     this.m_ListId = listId;
/*  44 */     this.m_StringTag = stringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceGroup() {
/*  49 */     return this.m_ResourceGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResourceGroup(String resourceGroup) {
/*  54 */     this.m_ResourceGroup = resourceGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListId() {
/*  59 */     return this.m_ListId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListId(int listId) {
/*  64 */     this.m_ListId = listId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/*  69 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int stringTag) {
/*  74 */     this.m_StringTag = stringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedValue(ILocalizationServices localizationService) {
/*  79 */     if (localizationService != null) {
/*     */       
/*  81 */       String resGroup = this.m_ResourceGroup;
/*  82 */       if (JLbsStringUtil.isEmpty(resGroup))
/*  83 */         resGroup = "UN"; 
/*  84 */       return localizationService.getItem(this.m_ListId, this.m_StringTag, resGroup);
/*     */     } 
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedValue(ILocalizationServices localizationService, String language) {
/*  91 */     if (localizationService != null) {
/*     */       
/*  93 */       String resGroup = this.m_ResourceGroup;
/*  94 */       if (JLbsStringUtil.isEmpty(resGroup))
/*  95 */         resGroup = "UN"; 
/*  96 */       return localizationService.getItem(language, this.m_ListId, this.m_StringTag, resGroup);
/*     */     } 
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 103 */     writer.startObject(getClass().getName(), "");
/* 104 */     describePropertiesXML(writer, localizationService);
/* 105 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 110 */     writer.writeProperty("ResourceGroup", "String", "", false);
/* 111 */     writer.writeProperty("ListId", "int", "", true);
/* 112 */     writer.writeProperty("StringTag", "int", "", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {
/* 117 */     if (this.m_ListId == 0) {
/* 118 */       throw new ParameterException("'ListId' is mandatory for JLbsResourceId!");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 124 */     ArrayList<String> list = new ArrayList<>();
/* 125 */     if (this.m_ListId == 0)
/* 126 */       list.add("ListId"); 
/* 127 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 133 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 139 */     return "JLbsResourceId (" + this.m_ListId + ", " + this.m_StringTag + ", " + this.m_ResourceGroup + ")";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\JLbsResourceId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */