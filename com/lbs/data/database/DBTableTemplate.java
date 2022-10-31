/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.objects.BusinessSchema;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ public class DBTableTemplate
/*     */   extends BusinessSchema
/*     */   implements INamedEntity, Cloneable, Serializable, IDBTable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_Description;
/*     */   private boolean m_GenerateInterface;
/*     */   private String m_PackageName;
/*  27 */   private DBEntityCollection m_Fields = new DBEntityCollection(null);
/*  28 */   private DBEntityCollection m_Indexes = new DBEntityCollection(null);
/*  29 */   private List m_Constants = new ArrayList();
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  33 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  38 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  43 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntityCollection getFieldList() {
/*  48 */     return this.m_Fields;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntityCollection getIndexList() {
/*  53 */     return this.m_Indexes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypeName() {
/*  58 */     return "Table Template";
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntityCollection getFields() {
/*  63 */     return this.m_Fields;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFields(DBEntityCollection fields) {
/*  68 */     this.m_Fields = fields;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntityCollection getIndexes() {
/*  73 */     return this.m_Indexes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexes(DBEntityCollection indexes) {
/*  78 */     this.m_Indexes = indexes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTemplateTable() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPhysicalName() {
/*  89 */     return getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenerateInterface() {
/*  94 */     return this.m_GenerateInterface;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenerateInterface(boolean generateInterface) {
/*  99 */     this.m_GenerateInterface = generateInterface;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPackageName() {
/* 104 */     return this.m_PackageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPackageName(String packageName) {
/* 109 */     this.m_PackageName = packageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 114 */     if (!StringUtil.isEmpty(this.m_PackageName))
/* 115 */       return this.m_PackageName + "." + this.m_Name; 
/* 116 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getConstants() {
/* 121 */     return this.m_Constants;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDomainAware() {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBEntityCollection getPartitionFieldList() {
/* 133 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBTableTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */