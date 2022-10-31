/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.expression.QueryItem;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryColumnCase
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   protected List m_Values = new ArrayList();
/*  33 */   protected List m_Results = new ArrayList(); protected String m_Else;
/*  34 */   protected QueryItem m_ElseItem = null;
/*     */   
/*     */   protected int m_DBType;
/*     */   
/*     */   public void addCase(String value, String result) {
/*  39 */     this.m_Values.add(value);
/*  40 */     this.m_Results.add(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getElse() {
/*  48 */     return this.m_Else;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElse(String string) {
/*  56 */     this.m_Else = string;
/*  57 */     this.m_ElseItem = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDBType() {
/*  65 */     return this.m_DBType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDBType(int i) {
/*  73 */     this.m_DBType = i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected QueryItem getResultItem(int i) {
/*  78 */     Object obj = this.m_Results.get(i);
/*  79 */     if (obj instanceof QueryItem) {
/*     */       
/*  81 */       QueryItem res = (QueryItem)obj;
/*  82 */       return (QueryItem)res.clone();
/*     */     } 
/*  84 */     String result = (String)obj;
/*  85 */     return getResultItem(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected QueryItem getResultItem(String result) {
/*  92 */     switch (this.m_DBType)
/*     */     
/*     */     { case 11:
/*  95 */         resItem = new QueryItem(80, result);
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
/* 109 */         return resItem;case 8: case 10: resItem = new QueryItem(81, result); return resItem;case 16: resItem = null; return resItem; }  QueryItem resItem = new QueryItem(result); return resItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getCaseDefinitionItem(QueryColumn column) {
/* 114 */     QueryItem caseItem = new QueryItem(5, column.getQualifiedItem(), null);
/*     */ 
/*     */     
/* 117 */     for (int i = 0; i < this.m_Values.size(); i++) {
/*     */       
/* 119 */       String value = this.m_Values.get(i);
/*     */       
/* 121 */       caseItem.addSubItem(new QueryItem(6, new QueryItem(value), getResultItem(i)));
/*     */     } 
/*     */     
/* 124 */     if (!StringUtil.isEmpty(this.m_Else)) {
/* 125 */       caseItem.addSubItem(new QueryItem(7, getResultItem(this.m_Else), null));
/* 126 */     } else if (this.m_ElseItem != null) {
/* 127 */       caseItem.addSubItem(new QueryItem(7, (QueryItem)this.m_ElseItem.clone(), null));
/*     */     } 
/* 129 */     return caseItem;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getDefinitionItem(QueryColumn column) {
/* 135 */     return new QueryItem(8, new QueryItem(column.getAlias()), getCaseDefinitionItem(column));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getResults() {
/* 156 */     return this.m_Results;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getValues() {
/* 161 */     return this.m_Values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElseItem(QueryItem queryItem) {
/* 169 */     this.m_Else = null;
/* 170 */     this.m_ElseItem = queryItem;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryColumnCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */