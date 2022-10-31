/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.controls.tablereport.TableReportPreferences;
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
/*     */ 
/*     */ public class TableLayoutData
/*     */   implements Serializable
/*     */ {
/*     */   public int m_Id;
/*     */   public String m_Name;
/*     */   public String m_Description;
/*     */   public String m_Layout;
/*     */   public String m_FormName;
/*     */   public boolean m_IsDefault;
/*     */   public boolean m_IsGeneral;
/*     */   public TableReportPreferences m_TablePrefs;
/*     */   
/*     */   public String getName() {
/*  31 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  38 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLayout() {
/*  45 */     return this.m_Layout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLayout(String layout) {
/*  52 */     this.m_Layout = layout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormName() {
/*  60 */     return this.m_FormName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormName(String formName) {
/*  67 */     this.m_FormName = formName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  74 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  81 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  88 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  95 */     this.m_Id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefault() {
/* 102 */     return this.m_IsDefault;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefault(boolean isDefault) {
/* 109 */     this.m_IsDefault = isDefault;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGeneral() {
/* 116 */     return this.m_IsGeneral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGeneral(boolean isGeneral) {
/* 123 */     this.m_IsGeneral = isGeneral;
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportPreferences getTablePrefs() {
/* 128 */     return this.m_TablePrefs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTablePrefs(TableReportPreferences tablePrefs) {
/* 133 */     this.m_TablePrefs = tablePrefs;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\TableLayoutData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */