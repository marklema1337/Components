/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
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
/*     */ public class QueryFilterTerms
/*     */   implements Serializable, Cloneable, Externalizable, ILbsXStreamListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("list")
/*     */   @XStreamImplicit(itemFieldName = "QueryFilterTerm")
/*     */   private ArrayList<QueryFilterTerm> m_List;
/*     */   
/*     */   public QueryFilterTerms() {
/*  35 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  40 */     if (this.m_List == null) {
/*  41 */       this.m_List = new ArrayList<>();
/*     */     }
/*     */   }
/*     */   
/*     */   public void add(QueryFilterTerm term) {
/*  46 */     this.m_List.add(term);
/*     */   }
/*     */   
/*     */   public void remove(int index) {
/*  50 */     this.m_List.remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  55 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryFilterTerm get(int index) {
/*  60 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  65 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  71 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/*  73 */       QueryFilterTerm term = get(i);
/*     */       
/*  75 */       if (term.getFilterType() != 1) {
/*     */         
/*  77 */         this.m_List.remove(i);
/*  78 */         i--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean clearLookupTerms() {
/*  85 */     boolean found = false;
/*     */     
/*  87 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/*  89 */       QueryFilterTerm term = get(i);
/*     */       
/*  91 */       if (term.getFilterType() == 3) {
/*     */         
/*  93 */         this.m_List.remove(i);
/*  94 */         i--;
/*  95 */         found = true;
/*     */       } 
/*     */     } 
/*  98 */     return found;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean clear(String columnName, int ownerType) {
/* 104 */     String tableName = null;
/*     */     
/* 106 */     int idx = columnName.indexOf('.');
/* 107 */     if (idx != -1) {
/*     */       
/* 109 */       tableName = columnName.substring(0, idx);
/* 110 */       columnName = columnName.substring(idx + 1);
/*     */     } 
/*     */     
/* 113 */     boolean found = false;
/* 114 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/* 116 */       QueryFilterTerm term = get(i);
/*     */       
/* 118 */       if (StringUtil.equals(columnName, term.getColumnName()) && StringUtil.equals(tableName, term.getTableAlias()) && term
/* 119 */         .canBeRemoved(ownerType)) {
/*     */         
/* 121 */         this.m_List.remove(i);
/* 122 */         i--;
/* 123 */         found = true;
/*     */       } 
/*     */     } 
/* 126 */     return found;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear(int ownerType) {
/* 132 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/* 134 */       QueryFilterTerm term = get(i);
/*     */       
/* 136 */       if (term.canBeRemoved(ownerType)) {
/*     */         
/* 138 */         this.m_List.remove(i);
/* 139 */         i--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 147 */     ExternalizationUtil.readExternal(this.m_List, in);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 152 */     ExternalizationUtil.writeExternal(this.m_List, out);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryFilterTerms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */