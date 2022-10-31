/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.database.DBField;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
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
/*     */ public class QueryColumns
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   protected transient IQueryColumnProvider m_Provider = null;
/*     */   
/*  26 */   protected List<QueryColumn> m_List = new ArrayList<>();
/*     */   
/*     */   protected boolean m_DefaultEnabled = true;
/*     */   
/*     */   public void setProvider(IQueryColumnProvider provider) {
/*  31 */     this.m_Provider = provider;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn item(int index) {
/*  36 */     if (this.m_Provider != null) {
/*  37 */       return this.m_Provider.item(index);
/*     */     }
/*  39 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn find(String source, String name) {
/*  44 */     if (this.m_Provider != null) {
/*  45 */       return this.m_Provider.find(source, name);
/*     */     }
/*     */ 
/*     */     
/*  49 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  51 */       QueryColumn col = item(i);
/*  52 */       DBField field = col.getField();
/*     */       
/*  54 */       if (field != null)
/*     */       {
/*     */         
/*  57 */         if ((source == null || field.getTableName().equals(source)) && (field.getName().equals(name) || JLbsStringUtil.equals(field.getCustFieldName(), name)))
/*  58 */           return col;  } 
/*     */     } 
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn findByAlias(String alias) {
/*  65 */     if (this.m_Provider != null) {
/*  66 */       return this.m_Provider.findByAlias(alias);
/*     */     }
/*     */ 
/*     */     
/*  70 */     int dotIdx = (alias == null) ? -1 : alias.indexOf('.');
/*  71 */     if (dotIdx > 0)
/*     */     {
/*  73 */       alias = alias.substring(dotIdx + 1);
/*     */     }
/*     */ 
/*     */     
/*  77 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  79 */       QueryColumn col = item(i);
/*     */       
/*  81 */       if (StringUtil.equals(col.getAlias(), alias)) {
/*  82 */         return col;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn findByFieldAlias(String alias) {
/*  94 */     if (this.m_Provider != null) {
/*  95 */       return this.m_Provider.findByFieldAlias(alias);
/*     */     }
/*     */ 
/*     */     
/*  99 */     int dotIdx = (alias == null) ? -1 : alias.indexOf('.');
/* 100 */     if (dotIdx > 0)
/*     */     {
/* 102 */       alias = alias.substring(dotIdx + 1);
/*     */     }
/*     */ 
/*     */     
/* 106 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 108 */       QueryColumn col = item(i);
/*     */       
/* 110 */       DBField field = col.getField();
/* 111 */       if (field != null && StringUtil.equals(field.getAlias(), alias)) {
/* 112 */         return col;
/*     */       }
/*     */     } 
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn find(DBField field) {
/* 120 */     if (this.m_Provider != null) {
/* 121 */       return this.m_Provider.find(field);
/*     */     }
/* 123 */     return find(field.getTableName(), field.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn findByAlias(String alias, DBField field) {
/* 128 */     if (this.m_Provider != null) {
/* 129 */       return this.m_Provider.findByAlias(alias, field);
/*     */     }
/*     */ 
/*     */     
/* 133 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 135 */       QueryColumn col = item(i);
/* 136 */       DBField colField = col.getField();
/*     */       
/* 138 */       if (colField == field && col.getAlias().equals(alias)) {
/* 139 */         return col;
/*     */       }
/*     */     } 
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(QueryColumn col) {
/* 147 */     this.m_List.add(col);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addColumns(QueryColumns cols) {
/* 152 */     for (int i = 0; i < cols.size(); i++)
/*     */     {
/* 154 */       this.m_List.add(cols.item(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(QueryColumn col) {
/* 161 */     return this.m_List.remove(col);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(QueryColumn col) {
/* 166 */     if (this.m_Provider != null) {
/* 167 */       return this.m_Provider.contains(col);
/*     */     }
/* 169 */     return this.m_List.contains(col);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int size() {
/* 174 */     if (this.m_Provider != null) {
/* 175 */       return this.m_Provider.size();
/*     */     }
/* 177 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefaultEnabled() {
/* 185 */     return this.m_DefaultEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultEnabled(boolean b) {
/* 193 */     this.m_DefaultEnabled = b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 199 */     if (this.m_Provider != null)
/*     */     {
/* 201 */       for (int i = 0; i < this.m_Provider.size(); i++)
/*     */       {
/* 203 */         this.m_List.add(this.m_Provider.item(i));
/*     */       }
/*     */     }
/* 206 */     out.defaultWriteObject();
/*     */     
/* 208 */     if (this.m_Provider != null) {
/* 209 */       this.m_List.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 214 */     in.defaultReadObject();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryColumns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */