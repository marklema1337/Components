/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
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
/*     */ public class DBTableLink
/*     */   implements Serializable, Cloneable, INamedEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int RELATION_REFERENCE = 0;
/*     */   public static final int RELATION_OWNER = 1;
/*     */   private DBTable m_OwnerTable;
/*     */   private String m_Alias;
/*     */   private String m_LinkTable;
/*     */   private String m_LinkTableGUID;
/*     */   private String m_Condition;
/*     */   private int m_Cardinality;
/*     */   private int m_Relation;
/*     */   private boolean m_RefCount;
/*     */   protected boolean m_Authorize = false;
/*     */   protected boolean m_ForceAuthorize = true;
/*  35 */   protected String m_AuthMode = "";
/*     */   
/*     */   private String m_ExtraCondition;
/*     */   
/*     */   private String m_ExtraConditionJoinType;
/*     */   
/*     */   private transient DBTable m_LinkDBTable;
/*     */   
/*     */   private transient QueryItem m_LinkExpression;
/*     */   
/*     */   private boolean m_HasRefColumn = false;
/*     */   private boolean m_DomainAwareLink = false;
/*     */   private boolean m_ExcludeForResolver = false;
/*     */   
/*     */   public String getName() {
/*  50 */     return getAlias();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAlias() {
/*  55 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlias(String alias) {
/*  60 */     this.m_Alias = alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/*  65 */     return this.m_Cardinality;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCardinality(int cardinality) {
/*  70 */     this.m_Cardinality = cardinality;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCondition() {
/*  75 */     return this.m_Condition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCondition(String condition) {
/*  80 */     this.m_Condition = condition;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLinkTable() {
/*  85 */     return this.m_LinkTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinkTable(String linkTable) {
/*  90 */     this.m_LinkTable = linkTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRefCount() {
/*  95 */     return this.m_RefCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRefCount(boolean refCount) {
/* 100 */     this.m_RefCount = refCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRelation() {
/* 105 */     return this.m_Relation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRelation(int relation) {
/* 110 */     this.m_Relation = relation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasRefColumn(boolean hasRefColumn) {
/* 115 */     this.m_HasRefColumn = hasRefColumn;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHasRefColumn() {
/* 120 */     return this.m_HasRefColumn;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 125 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCardinality(String cardinality) {
/* 130 */     if (StringUtil.equals(cardinality, "1-1"))
/* 131 */       return 0; 
/* 132 */     if (StringUtil.equals(cardinality, "1-N"))
/* 133 */       return 1; 
/* 134 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCardinality(int cardinality) {
/* 139 */     switch (cardinality) {
/*     */       
/*     */       case 0:
/* 142 */         return "1-1";
/*     */       case 1:
/* 144 */         return "1-N";
/*     */     } 
/* 146 */     return "1-1";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRelation(String relation) {
/* 152 */     if (StringUtil.equals(relation, "reference"))
/* 153 */       return 0; 
/* 154 */     if (StringUtil.equals(relation, "owner")) {
/* 155 */       return 1;
/*     */     }
/* 157 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getRelation(int relation) {
/* 162 */     switch (relation) {
/*     */       
/*     */       case 1:
/* 165 */         return "owner";
/*     */       case 0:
/* 167 */         return "reference";
/*     */     } 
/* 169 */     return "reference";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLinkTableGUID() {
/* 175 */     return this.m_LinkTableGUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinkTableGUID(String linkTableGUID) {
/* 180 */     this.m_LinkTableGUID = linkTableGUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTable getLinkDBTable() {
/* 185 */     return this.m_LinkDBTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinkDBTable(DBTable linkDBTable) {
/* 190 */     this.m_LinkDBTable = linkDBTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getLinkExpression() {
/* 195 */     return this.m_LinkExpression;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinkExpression(QueryItem linkExpression) {
/* 200 */     this.m_LinkExpression = linkExpression;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTable getOwnerTable() {
/* 205 */     return this.m_OwnerTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwnerTable(DBTable ownerTable) {
/* 210 */     this.m_OwnerTable = ownerTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAuthorize() {
/* 215 */     return this.m_Authorize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthorize(boolean authorize) {
/* 223 */     this.m_Authorize = authorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canForceAuthorize() {
/* 228 */     return this.m_ForceAuthorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceAuthorize(boolean forceAuthorize) {
/* 233 */     this.m_ForceAuthorize = forceAuthorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthMode(String authMode) {
/* 238 */     this.m_AuthMode = authMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAuthMode() {
/* 243 */     return this.m_AuthMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 248 */     return "(" + getAlias() + ": " + this.m_OwnerTable.getName() + " -> " + this.m_LinkDBTable.getName() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraCondition(String extraCondition) {
/* 256 */     this.m_ExtraCondition = extraCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtraCondition() {
/* 264 */     return this.m_ExtraCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraConditionJoinType(String extraConditionJoinType) {
/* 272 */     this.m_ExtraConditionJoinType = extraConditionJoinType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtraConditionJoinType() {
/* 280 */     return this.m_ExtraConditionJoinType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainAwareLink(boolean hasDomainRelatedColumn) {
/* 285 */     this.m_DomainAwareLink = hasDomainRelatedColumn;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDomainAwareLink() {
/* 290 */     return this.m_DomainAwareLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExcludeForResolver(boolean excludeForResolver) {
/* 295 */     this.m_ExcludeForResolver = excludeForResolver;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExcludeForResolver() {
/* 300 */     return this.m_ExcludeForResolver;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBTableLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */