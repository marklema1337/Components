/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.expression.QueryItem;
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
/*     */ public class QueryTerm
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_Name;
/*     */   protected String m_Value;
/*     */   protected boolean m_Enabled = true;
/*     */   protected QueryItem m_Expression;
/*     */   protected boolean m_Generated = false;
/*     */   protected String m_AssociatedLink;
/*     */   protected ArrayList<String> m_TableAliases;
/*     */   
/*     */   public String getName() {
/*  34 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String string) {
/*  42 */     this.m_Name = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(String string) {
/*  50 */     this.m_Value = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  58 */     return this.m_Enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean b) {
/*  66 */     this.m_Enabled = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getExpression() {
/*  74 */     return this.m_Expression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(QueryItem item) {
/*  82 */     this.m_Expression = item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  90 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTerm negate() {
/*  95 */     QueryTerm term = new QueryTerm();
/*     */     
/*  97 */     term.m_Enabled = this.m_Enabled;
/*  98 */     term.m_Name = this.m_Name;
/*  99 */     term.m_Value = this.m_Value;
/* 100 */     term.m_Expression = this.m_Expression.negate();
/*     */     
/* 102 */     return term;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenerated() {
/* 107 */     return this.m_Generated;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenerated(boolean generated) {
/* 112 */     this.m_Generated = generated;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAssociatedLink() {
/* 117 */     return this.m_AssociatedLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAssociatedLink(String associatedLink) {
/* 122 */     this.m_AssociatedLink = associatedLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableAliases(ArrayList<String> tableAliases) {
/* 127 */     this.m_TableAliases = tableAliases;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getTableAliases() {
/* 132 */     return this.m_TableAliases;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryTerm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */