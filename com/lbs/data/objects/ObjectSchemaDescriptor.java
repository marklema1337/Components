/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectSchemaDescriptor
/*     */   implements Serializable, Comparable<ObjectSchemaDescriptor>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Identifier m_Identifier;
/*     */   private String m_PackageName;
/*     */   private JLbsResourceId m_DescriptionResource;
/*     */   private String m_Description;
/*  21 */   private Hashtable<String, String> m_LocalDescs = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectSchemaDescriptor(Identifier identifier, String packageName, JLbsResourceId descriptionResource, String description) {
/*  30 */     this.m_Identifier = identifier;
/*  31 */     this.m_PackageName = packageName;
/*  32 */     this.m_DescriptionResource = descriptionResource;
/*  33 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ObjectSchemaDescriptor o) {
/*  39 */     return this.m_Identifier.compareTo(o.m_Identifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getIdentifier() {
/*  44 */     return this.m_Identifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifier(Identifier identifier) {
/*  49 */     this.m_Identifier = identifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getDescriptionResource() {
/*  54 */     return this.m_DescriptionResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptionResource(JLbsResourceId descriptionResource) {
/*  59 */     this.m_DescriptionResource = descriptionResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  64 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  69 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalDescription(ILocalizationServices service) {
/*  74 */     String lang = null;
/*     */     
/*     */     try {
/*  77 */       lang = service.getCulture().getLanguagePrefix();
/*     */     }
/*  79 */     catch (Exception e) {
/*     */       
/*  81 */       lang = "";
/*     */     } 
/*  83 */     if (service != null) {
/*     */       
/*  85 */       String cached = this.m_LocalDescs.get(lang);
/*  86 */       if (cached != null)
/*  87 */         return cached; 
/*     */     } 
/*  89 */     String desc = getLocalDescImpl(service);
/*  90 */     this.m_LocalDescs.put(lang, desc);
/*  91 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getLocalDescImpl(ILocalizationServices service) {
/*  96 */     if (this.m_DescriptionResource != null) {
/*     */       
/*  98 */       String s = this.m_DescriptionResource.getLocalizedValue(service);
/*  99 */       if (StringUtil.isEmpty(s))
/* 100 */         return this.m_Description; 
/* 101 */       return s;
/*     */     } 
/* 103 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPackageName() {
/* 108 */     return this.m_PackageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPackageName(String packageName) {
/* 113 */     this.m_PackageName = packageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleBusinessObject createInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
/* 118 */     if (!StringUtil.isEmpty(this.m_Identifier.getGuid())) {
/*     */       
/* 120 */       CustomBusinessObject bo = new CustomBusinessObject(this.m_Identifier.getGuid(), this.m_Identifier.getId());
/* 121 */       return bo;
/*     */     } 
/* 123 */     String className = this.m_Identifier.getId();
/* 124 */     if (!StringUtil.isEmpty(this.m_PackageName))
/*     */     {
/* 126 */       className = this.m_PackageName + "." + className;
/*     */     }
/* 128 */     Class<?> c = Class.forName(className);
/* 129 */     return (SimpleBusinessObject)c.newInstance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 135 */     if (obj instanceof ObjectSchemaDescriptor) {
/*     */       
/* 137 */       ObjectSchemaDescriptor desc = (ObjectSchemaDescriptor)obj;
/* 138 */       return this.m_Identifier.equals(desc.getIdentifier());
/*     */     } 
/* 140 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 146 */     return (getClass().getName() + "_" + this.m_Identifier.hashCode()).hashCode();
/*     */   }
/*     */   
/*     */   public ObjectSchemaDescriptor() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectSchemaDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */