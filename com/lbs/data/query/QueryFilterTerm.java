/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class QueryFilterTerm
/*     */   extends QueryFilterTermBase
/*     */   implements Serializable, Cloneable, Externalizable, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String VAR_KEY_COL = "$V(key)";
/*     */   protected String m_ColumnName;
/*     */   protected String m_TableAlias;
/*  32 */   protected Pattern searchPattern = null;
/*     */   
/*     */   protected boolean patternChecked = false;
/*     */   
/*     */   protected boolean m_Negated = false;
/*  37 */   protected int m_InnerIdx = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryFilterTerm() {}
/*     */ 
/*     */   
/*     */   public QueryFilterTerm(ILbsCultureInfo culture, String keyText, String fieldName) {
/*  45 */     boolean[] restrictive = new boolean[1];
/*  46 */     this.m_SearchValue = getSearchText(culture, keyText, restrictive);
/*  47 */     this.m_Operation = restrictive[0] ? 3 : 1;
/*     */ 
/*     */ 
/*     */     
/*  51 */     setColumnName(fieldName);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pattern getSearchPattern() {
/*  56 */     if (!this.patternChecked) {
/*  57 */       this.searchPattern = makeSearchValuePattern(this.m_SearchValue);
/*     */     }
/*  59 */     return this.searchPattern;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryFilterTerm(String columnName, Object value, int op, int whereConnector, int filterType) {
/*  64 */     this();
/*  65 */     this.m_SearchValue = value;
/*  66 */     setColumnName(columnName);
/*  67 */     setOperation(op);
/*  68 */     setSearchValue(value);
/*  69 */     setWhereConnector(whereConnector);
/*  70 */     setFilterType(filterType);
/*     */     
/*  72 */     if (filterType == 3 && this.m_HasInlinePercent && op != 3) {
/*  73 */       this.m_Operation = op;
/*     */     }
/*     */   }
/*     */   
/*     */   public QueryFilterTerm(String columnName, Object value, int op) {
/*  78 */     this();
/*  79 */     this.m_SearchValue = value;
/*  80 */     setColumnName(columnName);
/*  81 */     setOperation(op);
/*  82 */     setSearchValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryFilterTerm(String columnName, Object value) {
/*  87 */     this(columnName, value, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pattern makeSearchValuePattern(Object searchValue) {
/*  92 */     Pattern p = null;
/*  93 */     if (searchValue instanceof QueryFilterTree) {
/*     */       
/*  95 */       QueryFilterTree searchTree = (QueryFilterTree)searchValue;
/*  96 */       if (searchTree != null && searchTree.getSearchValue() != null && !searchTree.getSearchValue().equals(""))
/*     */       {
/*  98 */         if (searchTree.getSearchValue() != null) {
/*     */           
/* 100 */           String value = String.valueOf(searchTree.getSearchValue());
/* 101 */           if (value != null && !value.trim().equals("")) {
/*     */             
/* 103 */             if (!value.startsWith("^"))
/* 104 */               value = "^" + value; 
/* 105 */             String modifiedForRegEx = value.replaceAll("%", ".*");
/* 106 */             modifiedForRegEx = modifiedForRegEx.replaceAll("//*", "");
/* 107 */             modifiedForRegEx = "(" + modifiedForRegEx + ")+";
/* 108 */             p = Pattern.compile(modifiedForRegEx, 66);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 113 */     this.patternChecked = true;
/* 114 */     return p;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 119 */     Object obj = super.clone();
/*     */     
/* 121 */     ObjectUtil.deepCopy(this, obj, QueryFilterTerm.class);
/*     */     
/* 123 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVariableKeyColumn() {
/* 128 */     return StringUtil.equals(this.m_ColumnName, "$V(key)");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnName() {
/* 133 */     return this.m_ColumnName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnName(String columnName) {
/* 138 */     this.m_ColumnName = columnName;
/* 139 */     if (this.m_ColumnName != null) {
/*     */       
/* 141 */       int idx = this.m_ColumnName.indexOf('.');
/* 142 */       if (idx != -1) {
/*     */         
/* 144 */         this.m_TableAlias = this.m_ColumnName.substring(0, idx);
/* 145 */         if (this.m_TableAlias.equals("inner")) {
/* 146 */           this.m_TableAlias = null;
/*     */         } else {
/* 148 */           this.m_ColumnName = this.m_ColumnName.substring(idx + 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getTableAlias() {
/* 155 */     return this.m_TableAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNegated() {
/* 160 */     return this.m_Negated;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNegated(boolean negated) {
/* 165 */     this.m_Negated = negated;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInnerIdx() {
/* 170 */     return this.m_InnerIdx;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInnerIdx(int innerIdx) {
/* 175 */     this.m_InnerIdx = innerIdx;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 180 */     super.readExternal(in);
/*     */     
/* 182 */     this.m_ColumnName = (String)in.readObject();
/* 183 */     this.m_TableAlias = (String)in.readObject();
/*     */     
/* 185 */     this.m_Negated = in.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 190 */     super.writeExternal(out);
/*     */     
/* 192 */     out.writeObject(this.m_ColumnName);
/* 193 */     out.writeObject(this.m_TableAlias);
/*     */     
/* 195 */     out.writeBoolean(this.m_Negated);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryFilterTerm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */