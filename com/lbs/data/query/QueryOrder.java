/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.database.DBField;
/*     */ import com.lbs.data.database.DBIndex;
/*     */ import com.lbs.data.database.DBIndexSegment;
/*     */ import com.lbs.data.database.DBIndexSegments;
/*     */ import com.lbs.data.expression.QueryItem;
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
/*     */ public class QueryOrder
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   public static final int[] COMPARISON_OPS = new int[] { 41, 42, 40, 44, 43 };
/*     */   
/*     */   protected String m_Name;
/*     */   
/*     */   protected boolean m_IndexAscending = true;
/*     */   protected QueryOrderColumns m_Columns;
/*  30 */   protected DBIndex m_Index = null;
/*  31 */   protected DBIndex m_QueryIndex = null;
/*     */   
/*  33 */   protected transient IQueryRestrictionCommand[] m_SearchClauses = null;
/*  34 */   protected transient QueryItem[] m_OrderClauses = null;
/*     */ 
/*     */   
/*     */   public QueryOrder() {
/*  38 */     this.m_Columns = new QueryOrderColumns();
/*  39 */     this.m_SearchClauses = new IQueryRestrictionCommand[5];
/*  40 */     this.m_OrderClauses = new QueryItem[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryOrder(QueryTable table, DBIndex index) {
/*  45 */     this();
/*     */     
/*  47 */     this.m_Index = index;
/*  48 */     this.m_QueryIndex = index;
/*  49 */     DBIndexSegments segments = index.getSegments();
/*     */ 
/*     */     
/*  52 */     QueryColumns columns = table.getColumns();
/*     */ 
/*     */     
/*  55 */     for (int i = 0; i < segments.size(); i++) {
/*     */       
/*  57 */       DBIndexSegment segment = segments.item(i);
/*  58 */       DBField segField = segment.getField();
/*     */       
/*  60 */       QueryColumn column = columns.find(segField);
/*     */       
/*  62 */       if (column != null)
/*     */       {
/*     */         
/*  65 */         this.m_Columns.add(new QueryOrderColumn(column));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public QueryOrderColumns getOrderColumns() {
/*  71 */     return this.m_Columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  76 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String string) {
/*  81 */     this.m_Name = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIndexAscending() {
/*  86 */     return this.m_IndexAscending;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexAscending(boolean idxAscending) {
/*  91 */     this.m_IndexAscending = idxAscending;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex getIndex() {
/*  96 */     return this.m_Index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void append(QueryOrder order) {
/* 101 */     for (int i = 0; i < order.m_Columns.size(); i++) {
/* 102 */       this.m_Columns.add(order.m_Columns.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   public QueryItem[] getOrderClauses() {
/* 107 */     if (this.m_OrderClauses == null) {
/* 108 */       this.m_OrderClauses = new QueryItem[2];
/*     */     }
/* 110 */     return this.m_OrderClauses;
/*     */   }
/*     */ 
/*     */   
/*     */   public IQueryRestrictionCommand[] getSearchClauses() {
/* 115 */     if (this.m_SearchClauses == null) {
/* 116 */       this.m_SearchClauses = new IQueryRestrictionCommand[5];
/*     */     }
/* 118 */     return this.m_SearchClauses;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex getQueryIndex() {
/* 123 */     return this.m_QueryIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryIndex(DBIndex queryIndex) {
/* 128 */     this.m_QueryIndex = queryIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addOrderColumn(QueryColumn column) {
/* 133 */     this.m_Columns.add(new QueryOrderColumn(column));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addOrderColumn(QueryColumn column, boolean ascending) {
/* 138 */     this.m_Columns.add(new QueryOrderColumn(column, ascending));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */