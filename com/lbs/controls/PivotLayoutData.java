/*     */ package com.lbs.controls;
/*     */ 
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
/*     */ public class PivotLayoutData
/*     */   implements Serializable
/*     */ {
/*     */   public int id;
/*     */   public String name;
/*     */   public String description;
/*     */   public String layout;
/*     */   public String queryName;
/*     */   public boolean isDefault;
/*     */   public boolean isGeneral;
/*     */   public PivotExtraColumn[] extraColumns;
/*     */   
/*     */   public String getName() {
/*  29 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  36 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLayout() {
/*  43 */     return this.layout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLayout(String layout) {
/*  50 */     this.layout = layout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQueryName() {
/*  58 */     return this.queryName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQueryName(String queryName) {
/*  65 */     this.queryName = queryName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  72 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  79 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  93 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefault() {
/* 100 */     return this.isDefault;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefault(boolean isDefault) {
/* 107 */     this.isDefault = isDefault;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGeneral() {
/* 114 */     return this.isGeneral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGeneral(boolean isGeneral) {
/* 121 */     this.isGeneral = isGeneral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PivotExtraColumn[] getExtraColumns() {
/* 128 */     return this.extraColumns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraColumns(PivotExtraColumn[] extraColumns) {
/* 135 */     this.extraColumns = extraColumns;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\PivotLayoutData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */