/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.DataConstants;
/*     */ import com.lbs.data.database.DBTable;
/*     */ import com.lbs.data.database.DBTableLink;
/*     */ import com.lbs.data.expression.QueryItem;
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
/*     */ public class QueryTableLink
/*     */   extends QueryTable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int LINK_NATURAL = 0;
/*     */   public static final int LINK_LEFT_INNER = 1;
/*     */   public static final int LINK_LEFT_OUTER = 2;
/*     */   public static final int LINK_RIGHT_OUTER = 3;
/*     */   public static final int CARDINALITY_ONE_TO_ONE = 0;
/*     */   public static final int CARDINALITY_ONE_TO_MANY = 1;
/*     */   public static final int OPTIMIZATION_NONE = 0;
/*     */   public static final int OPTIMIZATION_INDEXED = 1;
/*     */   protected String m_LinkCondition;
/*  33 */   protected int m_LinkType = 0;
/*  34 */   protected int m_LinkCardinality = 0;
/*     */   protected boolean m_Authorize = false;
/*     */   protected boolean m_ForceAuthorize = true;
/*  37 */   protected String m_AuthMode = "";
/*     */   
/*     */   protected String m_ExtraCondition;
/*     */   protected String m_ExtraConditionJoinType;
/*  41 */   protected int m_LinkOptimization = 1;
/*     */   
/*     */   protected QueryItem m_LinkExpression;
/*     */   
/*     */   protected String m_ParentAlias;
/*     */   
/*     */   protected boolean m_QuerySpecific = false;
/*     */   
/*     */   protected QueryTableLinks m_RelatedLinks;
/*     */   protected boolean m_Generated = false;
/*  51 */   protected DBTableLink m_TableLink = null;
/*     */   
/*  53 */   protected String m_ExtraConditionForNonAuth = "";
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryTableLink() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryTableLink(QueryTableLink tableLink) {
/*  62 */     super(tableLink);
/*     */     
/*  64 */     this.m_LinkType = tableLink.m_LinkType;
/*  65 */     this.m_LinkCondition = tableLink.m_LinkCondition;
/*  66 */     this.m_LinkExpression = tableLink.m_LinkExpression;
/*  67 */     this.m_LinkCardinality = tableLink.m_LinkCardinality;
/*  68 */     this.m_LinkOptimization = tableLink.m_LinkOptimization;
/*  69 */     this.m_ParentAlias = tableLink.m_ParentAlias;
/*  70 */     this.m_QuerySpecific = tableLink.m_QuerySpecific;
/*  71 */     this.m_RelatedLinks = tableLink.m_RelatedLinks;
/*  72 */     this.m_Authorize = tableLink.m_Authorize;
/*  73 */     this.m_ForceAuthorize = tableLink.m_ForceAuthorize;
/*  74 */     this.m_AuthMode = tableLink.m_AuthMode;
/*  75 */     this.m_ExtraCondition = tableLink.m_ExtraCondition;
/*  76 */     this.m_ExtraConditionJoinType = tableLink.m_ExtraConditionJoinType;
/*  77 */     this.queryParamName = tableLink.queryParamName;
/*  78 */     this.queryParamValue = tableLink.queryParamValue;
/*  79 */     this.m_ExtraConditionForNonAuth = tableLink.m_ExtraConditionForNonAuth;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableLink(DBTable table, String tableAlias, DBTableLink link) {
/*  84 */     setTable(table);
/*     */     
/*  86 */     this.m_TableLink = link;
/*     */     
/*  88 */     setLinkCondition(link.getCondition());
/*  89 */     setLinkCardinality(link.getCardinality());
/*  90 */     setAuthorize(link.canAuthorize());
/*  91 */     setForceAuthorize(link.canForceAuthorize());
/*  92 */     setAuthMode(link.getAuthMode());
/*  93 */     setParentAlias(tableAlias);
/*  94 */     setExtraCondition(link.getExtraCondition());
/*  95 */     setExtraConditionJoinType(link.getExtraConditionJoinType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLinkType() {
/* 103 */     return this.m_LinkType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinkType(int linkType) {
/* 111 */     this.m_LinkType = linkType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getLinkExpression() {
/* 119 */     return this.m_LinkExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLinkCondition() {
/* 127 */     return this.m_LinkCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinkCondition(String string) {
/* 135 */     this.m_LinkCondition = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinkExpression(QueryItem item) {
/* 143 */     this.m_LinkExpression = item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getLinkDefinitionItem() {
/* 150 */     QueryItem expr, tableLinkItem = isJoinQuery() ? getDefinitionItem() : new QueryItem(27, getDefinitionItem(), null);
/*     */     
/* 152 */     switch (this.m_LinkType) {
/*     */       
/*     */       case 1:
/* 155 */         expr = (QueryItem)this.m_LinkExpression.clone();
/* 156 */         return new QueryItem(22, tableLinkItem, expr);
/*     */       
/*     */       case 2:
/* 159 */         expr = (QueryItem)this.m_LinkExpression.clone();
/* 160 */         return new QueryItem(23, tableLinkItem, expr);
/*     */       
/*     */       case 3:
/* 163 */         expr = (QueryItem)this.m_LinkExpression.clone();
/* 164 */         return new QueryItem(25, tableLinkItem, expr);
/*     */       
/*     */       case 0:
/* 167 */         if (DataConstants.NATURAL_JOINS) {
/*     */           
/* 169 */           expr = (QueryItem)this.m_LinkExpression.clone();
/* 170 */           return new QueryItem(21, tableLinkItem, expr);
/*     */         } 
/* 172 */         return new QueryItem(21, tableLinkItem, null);
/*     */     } 
/*     */     
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLinkCardinality() {
/* 180 */     return this.m_LinkCardinality;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinkCardinality(int cardinality) {
/* 185 */     this.m_LinkCardinality = cardinality;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLinkOptimization() {
/* 190 */     return this.m_LinkOptimization;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinkOptimization(int linkOptimization) {
/* 195 */     this.m_LinkOptimization = linkOptimization;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void main(QueryTable table) {
/* 201 */     QueryItem left = table.getKeyColumn().getQualifiedItem();
/*     */     
/* 203 */     QueryItem right = new QueryItem(1, new QueryItem(2, table.getAlias()), new QueryItem(12, "PARENTREF"));
/*     */ 
/*     */     
/* 206 */     QueryItem queryItem1 = new QueryItem(40, left, right);
/*     */     
/* 208 */     System.out.println(queryItem1.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParentAlias() {
/* 213 */     return this.m_ParentAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentAlias(String parentAlias) {
/* 218 */     this.m_ParentAlias = parentAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuerySpecific() {
/* 223 */     return this.m_QuerySpecific;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQuerySpecific(boolean querySpecific) {
/* 228 */     this.m_QuerySpecific = querySpecific;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableLinks getRelatedLinks() {
/* 233 */     return this.m_RelatedLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRelatedLinks(QueryTableLinks relatedLinks) {
/* 238 */     this.m_RelatedLinks = relatedLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenerated() {
/* 243 */     return this.m_Generated;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenerated(boolean generated) {
/* 248 */     this.m_Generated = generated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAuthorize() {
/* 256 */     return this.m_Authorize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthorize(boolean authorize) {
/* 264 */     this.m_Authorize = authorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canForceAuthorize() {
/* 269 */     return this.m_ForceAuthorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceAuthorize(boolean forceAuthorize) {
/* 274 */     this.m_ForceAuthorize = forceAuthorize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthMode(String authMode) {
/* 279 */     this.m_AuthMode = authMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAuthMode() {
/* 284 */     return this.m_AuthMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTableLink getTableLink() {
/* 289 */     return this.m_TableLink;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraCondition(String extraCondition) {
/* 297 */     this.m_ExtraCondition = extraCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtraCondition() {
/* 305 */     return this.m_ExtraCondition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraConditionJoinType(String extraConditionJoinType) {
/* 313 */     this.m_ExtraConditionJoinType = extraConditionJoinType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtraConditionJoinType() {
/* 321 */     return this.m_ExtraConditionJoinType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraConditionForNonAuth(String extraConditionForNonAuth) {
/* 326 */     this.m_ExtraConditionForNonAuth = extraConditionForNonAuth;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getExtraConditionForNonAuth() {
/* 331 */     return this.m_ExtraConditionForNonAuth;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTableLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */