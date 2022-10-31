/*     */ package com.lbs.data.database;
/*     */ 
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
/*     */ public class DBFields
/*     */   extends DBEntityCollection
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public DBFields(DBTable table) {
/*  21 */     super(table);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(int index, DBField field) {
/*  26 */     if (field.isPrimaryKey()) {
/*     */       
/*  28 */       DBTable table = (DBTable)getOwner();
/*  29 */       table.setKeyField(field);
/*     */     } 
/*     */     
/*  32 */     if (field.isAutoIncrement()) {
/*     */       
/*  34 */       DBTable table = (DBTable)getOwner();
/*  35 */       table.setAutoIncrementField(field);
/*     */     } 
/*     */     
/*  38 */     if (index == -1) {
/*  39 */       return add(field);
/*     */     }
/*  41 */     return add(index, field);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(DBField field) {
/*  46 */     return add(-1, field);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField item(int index) {
/*  51 */     return (DBField)entity(index);
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
/*     */   public DBField get(String fieldName) {
/*  70 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  72 */       DBField field = item(i);
/*  73 */       if (StringUtil.equals(fieldName, field.getName())) {
/*  74 */         return field;
/*     */       }
/*     */     } 
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField getByPhysicalName(String name) {
/*  82 */     int idx = findByPhysicalName(name);
/*     */     
/*  84 */     if (idx == -1) {
/*  85 */       return null;
/*     */     }
/*  87 */     return item(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField getByAlias(String alias) {
/*  92 */     int idx = findByAlias(alias);
/*     */     
/*  94 */     if (idx == -1) {
/*  95 */       return null;
/*     */     }
/*  97 */     return item(idx);
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
/*     */   public int find(String fieldName) {
/* 117 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 119 */       DBField field = item(i);
/*     */       
/* 121 */       if (fieldName.equals(field.getName())) {
/* 122 */         return i;
/*     */       }
/*     */     } 
/* 125 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int findIgnoreCase(String fieldName) {
/* 131 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 133 */       DBField field = item(i);
/*     */       
/* 135 */       if (fieldName.equalsIgnoreCase(field.getName())) {
/* 136 */         return i;
/*     */       }
/*     */     } 
/* 139 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int findByAlias(String alias) {
/* 145 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 147 */       DBField field = item(i);
/*     */       
/* 149 */       if (alias.equals(field.getAlias())) {
/* 150 */         return i;
/*     */       }
/*     */     } 
/* 153 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBFields.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */