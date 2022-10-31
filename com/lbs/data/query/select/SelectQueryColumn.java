/*     */ package com.lbs.data.query.select;
/*     */ 
/*     */ import com.lbs.data.database.DBField;
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.data.expression.QueryItemList;
/*     */ import com.lbs.data.query.QueryColumn;
/*     */ import com.lbs.data.query.QueryColumnCase;
/*     */ import com.lbs.data.query.QueryTable;
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
/*     */ public class SelectQueryColumn
/*     */   extends QueryColumn
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int FUNC_NONE = 0;
/*     */   public static final int FUNC_AVG = 1;
/*     */   public static final int FUNC_SUM = 2;
/*     */   public static final int FUNC_MIN = 3;
/*     */   public static final int FUNC_MAX = 4;
/*     */   public static final int FUNC_COUNT = 5;
/*     */   public static final int FUNC_COUNT_ROWS = 6;
/*     */   public static final int FUNC_COUNT_DISTINCT = 7;
/*     */   public static final int FUNC_NATIVE_AGGREGATE = 8;
/*     */   public static final int FUNC_COUNT_BIG = 9;
/*  38 */   protected int m_AggregateFunction = 0;
/*  39 */   protected int m_AccessType = 0;
/*     */   
/*     */   protected String m_ExpressionValue;
/*     */   protected QueryItem m_Expression;
/*  43 */   protected QueryColumnCase m_Case = null;
/*     */   
/*     */   protected boolean m_PartitionOver = false;
/*  46 */   protected String[] m_PartitionCols = null;
/*  47 */   protected SelectQueryColumn[] m_PartitionColumns = null;
/*     */   
/*  49 */   protected int m_QueryColumnIndex = -1;
/*     */ 
/*     */   
/*     */   public SelectQueryColumn(QueryTable table) {
/*  53 */     super(table);
/*     */   }
/*     */ 
/*     */   
/*     */   public SelectQueryColumn(SelectQueryColumn copy) {
/*  58 */     super(copy);
/*  59 */     this.m_AggregateFunction = copy.m_AggregateFunction;
/*  60 */     this.m_AccessType = copy.m_AccessType;
/*  61 */     this.m_ExpressionValue = copy.m_ExpressionValue;
/*  62 */     this.m_Expression = copy.m_Expression;
/*  63 */     this.m_Case = copy.m_Case;
/*  64 */     this.m_PartitionOver = copy.m_PartitionOver;
/*  65 */     this.m_PartitionCols = copy.m_PartitionCols;
/*  66 */     this.m_PartitionColumns = copy.m_PartitionColumns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getDefinitionItem() {
/*  76 */     QueryItem item = null;
/*  77 */     if (this.m_Case != null) {
/*  78 */       item = this.m_Case.getCaseDefinitionItem(this);
/*  79 */     } else if (this.m_Expression != null) {
/*  80 */       item = (QueryItem)this.m_Expression.clone();
/*     */     } else {
/*     */       
/*  83 */       item = getQualifiedItem();
/*  84 */       item.setCollatable(this.m_Collatable);
/*     */     } 
/*     */     
/*  87 */     switch (this.m_AggregateFunction) {
/*     */       
/*     */       case 1:
/*  90 */         item = new QueryItem(50, item, null);
/*     */         break;
/*     */       
/*     */       case 2:
/*  94 */         item = new QueryItem(51, item, null);
/*     */         break;
/*     */       
/*     */       case 4:
/*  98 */         item = new QueryItem(52, item, null);
/*     */         break;
/*     */       
/*     */       case 3:
/* 102 */         item = new QueryItem(53, item, null);
/*     */         break;
/*     */       
/*     */       case 5:
/* 106 */         item = new QueryItem(54, item, null);
/*     */         break;
/*     */       
/*     */       case 9:
/* 110 */         item = new QueryItem(59, item, null);
/*     */         break;
/*     */       
/*     */       case 6:
/* 114 */         item = new QueryItem(55, null, null);
/*     */         break;
/*     */       
/*     */       case 7:
/* 118 */         item = new QueryItem(56, item, null);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 8:
/* 128 */         item = new QueryItem(57, item, null);
/*     */         break;
/*     */       
/*     */       case 0:
/* 132 */         if (this.m_Case != null) {
/* 133 */           return new QueryItem(8, new QueryItem(getAlias()), item);
/*     */         }
/*     */         break;
/*     */     } 
/* 137 */     if (this.m_PartitionOver) {
/*     */       
/* 139 */       item = new QueryItem(58, item, null);
/* 140 */       if (this.m_PartitionColumns != null)
/*     */       {
/* 142 */         for (int i = 0; i < this.m_PartitionColumns.length; i++) {
/*     */           
/* 144 */           SelectQueryColumn partitionCol = this.m_PartitionColumns[i];
/* 145 */           item.addSubItem(partitionCol.getQualifiedItem(), false);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 150 */     if (!StringUtil.isEmpty(getAlias())) {
/*     */       
/* 152 */       String alias = getQueryAlias();
/* 153 */       item = new QueryItem(4, item, new QueryItem(alias));
/* 154 */       item.setCollatable(this.m_Collatable);
/*     */     } 
/* 156 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryAlias() {
/* 161 */     String alias = getAlias();
/*     */ 
/*     */ 
/*     */     
/* 165 */     if (!this.m_HasUniqueAlias && getQueryTable() != null)
/* 166 */       alias = generateAlias(alias); 
/* 167 */     return alias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String generateAlias(String alias) {
/* 176 */     alias = getQueryTable().getAlias() + "_" + alias;
/*     */     
/* 178 */     int diff = alias.length() - 30;
/* 179 */     if (diff > 0) {
/*     */ 
/*     */       
/* 182 */       int hashCode = alias.hashCode();
/* 183 */       if (hashCode == Integer.MIN_VALUE)
/* 184 */         hashCode++; 
/* 185 */       alias = "A_" + Math.abs(hashCode);
/* 186 */       if (alias.length() > 30) {
/* 187 */         return alias.substring(0, 30);
/*     */       }
/* 189 */       return alias;
/*     */     } 
/* 191 */     return alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAggregateFunction() {
/* 196 */     return this.m_AggregateFunction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAggregateFunction(int i) {
/* 201 */     this.m_AggregateFunction = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAggregate() {
/* 206 */     return (this.m_AggregateFunction != 0 || hasAggregateExpression());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasAggregateExpression() {
/* 211 */     if (this.m_Expression != null)
/*     */     {
/* 213 */       return hasAggregate(this.m_Expression);
/*     */     }
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasAggregate(QueryItem expression) {
/* 220 */     switch (expression.getType()) {
/*     */       
/*     */       case 50:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 54:
/*     */       case 55:
/*     */       case 56:
/*     */       case 57:
/*     */       case 58:
/*     */       case 59:
/* 232 */         return true;
/*     */     } 
/* 234 */     QueryItemList subItems = expression.getSubItems();
/* 235 */     if (subItems != null)
/*     */     {
/* 237 */       for (int i = 0; i < subItems.size(); i++) {
/*     */         
/* 239 */         if (hasAggregate(subItems.get(i)))
/* 240 */           return true; 
/*     */       } 
/*     */     }
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNativeAggregate() {
/* 248 */     return (this.m_AggregateFunction == 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAccessType() {
/* 253 */     if (this.m_Case != null) {
/* 254 */       return this.m_Case.getDBType();
/*     */     }
/* 256 */     if (this.m_AccessType != 0) {
/* 257 */       return this.m_AccessType;
/*     */     }
/* 259 */     if (this.m_Field != null) {
/* 260 */       return this.m_Field.getType();
/*     */     }
/* 262 */     return this.m_AccessType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAccessType(int i) {
/* 267 */     this.m_AccessType = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefinitionAccessType() {
/* 272 */     return this.m_AccessType;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumnCase getCase() {
/* 277 */     return this.m_Case;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCase(QueryColumnCase case1) {
/* 282 */     this.m_Case = case1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(DBField field) {
/* 287 */     super.setField(field);
/*     */     
/* 289 */     if (field.isPrimaryKey()) {
/* 290 */       setAlwaysEnabled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getExpressionValue() {
/* 295 */     return this.m_ExpressionValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getExpression() {
/* 300 */     return this.m_Expression;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpressionValue(String string) {
/* 305 */     this.m_ExpressionValue = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpression(QueryItem item) {
/* 310 */     this.m_Expression = item;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartitionOver() {
/* 315 */     return this.m_PartitionOver;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartitionOver(boolean partitionOver) {
/* 320 */     this.m_PartitionOver = partitionOver;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getPartitionCols() {
/* 325 */     return this.m_PartitionCols;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartitionCols(String[] partitionCols) {
/* 330 */     this.m_PartitionCols = partitionCols;
/*     */   }
/*     */ 
/*     */   
/*     */   public SelectQueryColumn[] getPartitionColumns() {
/* 335 */     return this.m_PartitionColumns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartitionColumns(SelectQueryColumn[] partitionColumns) {
/* 340 */     this.m_PartitionColumns = partitionColumns;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getQueryColumnIndex() {
/* 345 */     return this.m_QueryColumnIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryColumnIndex(int queryColumnIndex) {
/* 350 */     this.m_QueryColumnIndex = queryColumnIndex;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\select\SelectQueryColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */