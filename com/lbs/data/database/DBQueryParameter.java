/*     */ package com.lbs.data.database;
/*     */ 
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
/*     */ public class DBQueryParameter
/*     */ {
/*  16 */   public static String NEW_VALUE_PREFIX = "new_";
/*  17 */   public static String OLD_VALUE_PREFIX = "old_";
/*     */   
/*     */   public static final int PARAMTYPE_INPUT = 1;
/*     */   
/*     */   public static final int PARAMTYPE_OUTPUT = 2;
/*     */   
/*     */   public static final int PARAMTYPE_RETURN = 4;
/*     */   
/*     */   public static final int PARAMVALUE_INITIAL = 8;
/*     */   
/*     */   public static final int PARAMVALUE_CURRENT = 16;
/*     */   
/*     */   public static final int PARAMVALUE_MEMBER = 32;
/*     */   protected String m_Name;
/*     */   protected DBField m_Field;
/*     */   protected int m_ParamSize;
/*     */   protected int m_ParamType;
/*     */   protected int m_ParamOptions;
/*     */   protected String m_MemberName;
/*     */   
/*     */   public DBQueryParameter(DBQueryParameter param) {
/*  38 */     this.m_Name = param.m_Name;
/*     */ 
/*     */     
/*  41 */     this.m_Field = param.m_Field;
/*  42 */     this.m_ParamSize = param.m_ParamSize;
/*  43 */     this.m_ParamType = param.m_ParamType;
/*  44 */     this.m_ParamOptions = param.m_ParamOptions;
/*  45 */     this.m_MemberName = param.m_MemberName;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBQueryParameter() {
/*  50 */     this.m_ParamOptions = 17;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBQueryParameter(DBField field) {
/*  55 */     if (field == null) {
/*     */       return;
/*     */     }
/*  58 */     this.m_Name = field.getName();
/*  59 */     this.m_Field = field;
/*     */ 
/*     */     
/*  62 */     this.m_ParamSize = field.getSize();
/*  63 */     this.m_ParamType = field.getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOption(int paramOption) {
/*  68 */     return ((this.m_ParamOptions & paramOption) == paramOption);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParamOptions() {
/*  77 */     return this.m_ParamOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParamSize() {
/*  86 */     return this.m_ParamSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParamType() {
/*  95 */     return this.m_ParamType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParamOptions(int paramOptions) {
/* 104 */     this.m_ParamOptions = paramOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParamSize(int paramSize) {
/* 113 */     this.m_ParamSize = paramSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParamType(int paramType) {
/* 122 */     this.m_ParamType = paramType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 130 */     if (obj instanceof DBQueryParameter) {
/*     */       
/* 132 */       DBQueryParameter param = (DBQueryParameter)obj;
/*     */       
/* 134 */       return (param.getName().equals(getName()) && param.m_Field.equals(this.m_Field));
/*     */     } 
/* 136 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 142 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMemberName() {
/* 150 */     return this.m_MemberName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMemberName(String memberName) {
/* 159 */     this.m_MemberName = memberName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 168 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 177 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 185 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 187 */     sb.append("Name=" + this.m_Name);
/*     */     
/* 189 */     if (this.m_Field != null) {
/*     */       
/* 191 */       sb.append(",ColumnName=" + this.m_Field.getName());
/* 192 */       sb.append(",ColumnSource=" + this.m_Field.getTableName());
/*     */     } 
/*     */     
/* 195 */     sb.append(",MemberName=" + this.m_MemberName);
/* 196 */     sb.append(",ParamSize=" + this.m_ParamSize);
/* 197 */     sb.append(",ParamType=" + this.m_ParamType);
/*     */     
/* 199 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField getField() {
/* 204 */     return this.m_Field;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(DBField field) {
/* 209 */     this.m_Field = field;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getAccessItem() {
/* 214 */     return new QueryItem(90, this.m_Name);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBQueryParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */