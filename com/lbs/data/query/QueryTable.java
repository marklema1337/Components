/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.database.DBField;
/*     */ import com.lbs.data.database.DBIndex;
/*     */ import com.lbs.data.database.DBIndexSegment;
/*     */ import com.lbs.data.database.DBIndexes;
/*     */ import com.lbs.data.database.DBTable;
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.data.objects.BusinessSchemaException;
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
/*     */ 
/*     */ 
/*     */ public class QueryTable
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected DBTable m_Table;
/*     */   protected String m_Alias;
/*     */   protected String m_NameVariable;
/*     */   protected QueryColumns m_Columns;
/*     */   protected QueryOrders m_Orders;
/*     */   protected boolean m_Enabled = true;
/*     */   protected transient QuerySchema m_InnerSchema;
/*     */   protected transient QueryItem m_InnerQueryItem;
/*  39 */   protected String queryParamName = null;
/*  40 */   protected String queryParamValue = null;
/*     */ 
/*     */   
/*     */   public QueryTable() {
/*  44 */     this.m_Columns = new QueryColumns();
/*  45 */     this.m_Orders = new QueryOrders();
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable(QueryTable table) {
/*  50 */     this();
/*     */     
/*  52 */     init(table);
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(QueryTable table) {
/*  57 */     this.m_Table = table.m_Table;
/*  58 */     this.m_Alias = table.m_Alias;
/*  59 */     this.m_NameVariable = table.m_NameVariable;
/*     */     
/*  61 */     this.m_InnerSchema = table.m_InnerSchema;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAlias() {
/*  66 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTablePhysicalName() {
/*  71 */     if (this.m_Table == null) {
/*  72 */       return null;
/*     */     }
/*  74 */     return this.m_Table.getPhysicalName();
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTable getTable() {
/*  79 */     return this.m_Table;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlias(String alias) {
/*  84 */     this.m_Alias = alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTable(DBTable table) {
/*  89 */     this.m_Table = table;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumns getColumns() {
/*  94 */     return this.m_Columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumns(QueryColumns columns) {
/*  99 */     this.m_Columns = columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryOrders getOrders() {
/* 104 */     return this.m_Orders;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkColumns(QuerySchema qrySchema) throws BusinessSchemaException {}
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 113 */     return this.m_Enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 118 */     this.m_Enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getDefinitionItem() {
/* 124 */     String tableName = (this.m_Table != null) ? this.m_Table.getPhysicalName() : this.m_NameVariable;
/*     */     
/* 126 */     QueryItem item = getNameItem();
/*     */     
/* 128 */     if (!this.m_Alias.equalsIgnoreCase(tableName)) {
/*     */       
/* 130 */       QueryItem aliasItem = new QueryItem(2, item, getAccessItem());
/* 131 */       item = aliasItem;
/*     */     } 
/*     */     
/* 134 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isJoinQuery() {
/* 139 */     return (this.m_InnerQueryItem != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getNameItem() {
/* 144 */     if (isJoinQuery()) {
/* 145 */       return new QueryItem(26, this.m_InnerQueryItem);
/*     */     }
/* 147 */     if (this.m_Table != null)
/*     */     {
/* 149 */       return new QueryItem(101, this.m_Table);
/*     */     }
/*     */ 
/*     */     
/* 153 */     return new QueryItem(10, new QueryItem(92, this.m_NameVariable));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getAccessItem() {
/* 159 */     return new QueryItem(17, this.m_Alias);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAggregates() {
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOnlyNativeAggregate() {
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getQualifiedColumnItem(String colName) {
/* 174 */     return new QueryItem(1, getAccessItem(), new QueryItem(12, colName));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameVariable() {
/* 179 */     return this.m_NameVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNameVariable(String string) {
/* 184 */     this.m_NameVariable = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVariableBound() {
/* 189 */     return !StringUtil.isEmpty(this.m_NameVariable);
/*     */   }
/*     */ 
/*     */   
/*     */   private int findIndex(QueryOrder order, int startIdx) {
/* 194 */     DBIndexes indexes = this.m_Table.getIndexes();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     for (int i = startIdx; i < indexes.size(); i++) {
/*     */       
/* 201 */       DBIndex index = indexes.item(i);
/* 202 */       boolean match = true;
/*     */       
/* 204 */       for (int j = 0; j < index.getSegments().size(); j++) {
/*     */         
/* 206 */         DBIndexSegment segment = index.getSegments().item(j);
/* 207 */         DBField field = segment.getField();
/*     */         
/* 209 */         if (field == null || order.m_Columns.find(field) == null)
/*     */         {
/* 211 */           if (field == null || !"TE_SUBCOMPANY".equals(field.getName())) {
/*     */ 
/*     */ 
/*     */             
/* 215 */             match = false;
/*     */             break;
/*     */           } 
/*     */         }
/*     */       } 
/* 220 */       if (match) {
/* 221 */         return i;
/*     */       }
/*     */     } 
/* 224 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOrderUnique(QueryOrder order) {
/* 229 */     int idx = 0;
/* 230 */     while (idx != -1) {
/*     */       
/* 232 */       idx = findIndex(order, idx + 1);
/*     */       
/* 234 */       if (idx != -1) {
/*     */         
/* 236 */         DBIndex index = this.m_Table.getIndexes().item(idx);
/* 237 */         if (index.isUnique()) {
/* 238 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryColumn getKeyColumn() {
/* 248 */     QueryColumn candidate = null;
/* 249 */     for (int i = 0; i < this.m_Columns.size(); i++) {
/*     */       
/* 251 */       QueryColumn column = this.m_Columns.item(i);
/*     */       
/* 253 */       if (column.isPrimaryKey()) {
/*     */         
/* 255 */         if (column.isEnabled())
/* 256 */           return column; 
/* 257 */         candidate = column;
/*     */       } 
/*     */     } 
/*     */     
/* 261 */     return candidate;
/*     */   }
/*     */ 
/*     */   
/*     */   public QuerySchema getInnerSchema() {
/* 266 */     return this.m_InnerSchema;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInnerSchema(QuerySchema innerSchema) {
/* 271 */     this.m_InnerSchema = innerSchema;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getInnerQueryItem() {
/* 276 */     return this.m_InnerQueryItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInnerQueryItem(QueryItem innerQueryItem) {
/* 281 */     this.m_InnerQueryItem = innerQueryItem;
/*     */   }
/*     */   
/*     */   public String getQueryParamName() {
/* 285 */     return this.queryParamName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParamName(String queryParamName) {
/* 290 */     this.queryParamName = queryParamName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryParamValue() {
/* 295 */     return this.queryParamValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParamValue(String queryParamValue) {
/* 300 */     this.queryParamValue = queryParamValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */