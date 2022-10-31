/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.database.DBField;
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
/*     */ public class QueryColumn
/*     */   implements Serializable
/*     */ {
/*     */   public static final int MAX_ALIAS_LENGTH = 30;
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   protected QueryTable m_QueryTable = null;
/*     */   
/*     */   protected DBField m_Field;
/*     */   
/*     */   protected String m_NameVariable;
/*     */   
/*     */   protected boolean m_IsVariable;
/*     */   
/*     */   protected String m_Alias;
/*     */   
/*     */   protected boolean m_Enabled = true;
/*     */   protected boolean m_AlwaysEnabled = false;
/*     */   protected boolean m_Redefined = false;
/*     */   protected boolean m_Generated = false;
/*     */   protected boolean m_HasUniqueAlias = false;
/*     */   protected boolean m_Collatable = false;
/*  40 */   protected String m_QualifiedAlias = "";
/*     */   
/*  42 */   protected String m_UniqueAlias = "";
/*     */ 
/*     */   
/*     */   public QueryColumn(QueryTable table) {
/*  46 */     this.m_QueryTable = table;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn(QueryColumn copy) {
/*  51 */     this.m_QueryTable = copy.m_QueryTable;
/*  52 */     this.m_Field = copy.m_Field;
/*  53 */     this.m_NameVariable = copy.m_NameVariable;
/*  54 */     this.m_IsVariable = copy.m_IsVariable;
/*  55 */     this.m_Alias = copy.m_Alias;
/*  56 */     this.m_Enabled = copy.m_Enabled;
/*  57 */     this.m_AlwaysEnabled = copy.m_AlwaysEnabled;
/*  58 */     this.m_Redefined = copy.m_Redefined;
/*  59 */     this.m_Generated = copy.m_Generated;
/*  60 */     this.m_HasUniqueAlias = copy.m_HasUniqueAlias;
/*  61 */     this.m_Collatable = copy.m_Collatable;
/*  62 */     this.m_QualifiedAlias = copy.m_QualifiedAlias;
/*  63 */     this.m_UniqueAlias = copy.m_UniqueAlias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBField getField() {
/*  75 */     return this.m_Field;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(DBField field) {
/*  80 */     this.m_Field = field;
/*  81 */     if (field != null && StringUtil.isEmpty(this.m_Alias)) {
/*  82 */       this.m_Alias = field.getAlias();
/*     */     }
/*     */   }
/*     */   
/*     */   public String getAlias() {
/*  87 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFieldAlias() {
/*  92 */     if (this.m_Field != null)
/*  93 */       return this.m_Field.getAlias(); 
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlias(String string) {
/*  99 */     this.m_Alias = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 104 */     return this.m_Enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean b) {
/* 109 */     this.m_Enabled = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlwaysEnabled() {
/* 114 */     return this.m_AlwaysEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlwaysEnabled(boolean alwaysEnabled) {
/* 119 */     this.m_AlwaysEnabled = alwaysEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable getQueryTable() {
/* 124 */     return this.m_QueryTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getNameItem() {
/* 129 */     if (this.m_Field != null)
/*     */     {
/* 131 */       return this.m_Field.getNameItem();
/*     */     }
/*     */     
/* 134 */     if (this.m_IsVariable) {
/*     */       
/* 136 */       QueryItem queryItem = new QueryItem(12, new QueryItem(92, this.m_NameVariable));
/* 137 */       return queryItem;
/*     */     } 
/* 139 */     QueryItem result = new QueryItem(12, this.m_NameVariable);
/* 140 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getGroupItem() {
/* 150 */     QueryItem result = new QueryItem(1, this.m_QueryTable.getAccessItem(), getNameItem());
/* 151 */     result.setData(this);
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getQualifiedItem() {
/* 157 */     QueryItem result = new QueryItem(1, this.m_QueryTable.getAccessItem(), getNameItem());
/* 158 */     result.setData(this);
/* 159 */     if (this.m_Field != null && this.m_Field.getType() == 15)
/* 160 */       result = new QueryItem(19, result, null); 
/* 161 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVariable() {
/* 166 */     return this.m_IsVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVariable(boolean isVariable) {
/* 171 */     this.m_IsVariable = isVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getAccessItem() {
/* 176 */     return getQualifiedItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getDefinitionItem() {
/* 181 */     QueryItem item = getQualifiedItem();
/*     */     
/* 183 */     String alias = getAlias();
/* 184 */     if (!StringUtil.isEmpty(alias)) {
/*     */       
/* 186 */       if (!this.m_HasUniqueAlias)
/* 187 */         alias = getQueryTable().getAlias() + "_" + alias; 
/* 188 */       item = new QueryItem(4, item, new QueryItem(alias));
/*     */     } 
/* 190 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameVariable() {
/* 195 */     return this.m_NameVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNameVariable(String string) {
/* 200 */     this.m_NameVariable = string;
/* 201 */     this.m_IsVariable = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVariableBound() {
/* 206 */     return (!StringUtil.isEmpty(this.m_NameVariable) && this.m_IsVariable);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAggregate() {
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrimaryKey() {
/* 216 */     return (this.m_Field != null && this.m_Field.isPrimaryKey());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 221 */     return getQualifiedItem().toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllowsNullValues() {
/* 226 */     if (this.m_Field != null) {
/* 227 */       return this.m_Field.isAllowNullValues();
/*     */     }
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRedefined() {
/* 234 */     return this.m_Redefined;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRedefined(boolean redefined) {
/* 239 */     this.m_Redefined = redefined;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenerated() {
/* 244 */     return this.m_Generated;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenerated(boolean generated) {
/* 249 */     this.m_Generated = generated;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHasUniqueAlias() {
/* 254 */     return this.m_HasUniqueAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasUniqueAlias(boolean hasUniqueAlias) {
/* 259 */     this.m_HasUniqueAlias = hasUniqueAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQualifiedAlias() {
/* 264 */     if (this.m_QueryTable != null)
/* 265 */       return this.m_QueryTable.getAlias() + "." + this.m_Alias; 
/* 266 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUniqueAlias() {
/* 271 */     if (isHasUniqueAlias())
/* 272 */       return this.m_Alias; 
/* 273 */     return getQualifiedAlias();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCollatable(boolean collatable) {
/* 278 */     this.m_Collatable = collatable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollatable() {
/* 283 */     return this.m_Collatable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNativeAggregate() {
/* 288 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */