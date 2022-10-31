/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.factory.INamedVariables;
/*     */ import com.lbs.data.factory.ISubstitutionListener;
/*     */ import com.lbs.data.objects.BusinessSchema;
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
/*     */ 
/*     */ 
/*     */ public class DBEntity
/*     */   extends BusinessSchema
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int NONE = 0;
/*     */   public static final int FIELD = 1;
/*     */   public static final int INDEX = 2;
/*     */   public static final int INDEX_SEGMENT = 3;
/*     */   public static final int TABLE = 4;
/*     */   public static final int STORED_PROC = 5;
/*     */   public static final int VIEW = 6;
/*     */   public static final int TRIGGER = 7;
/*     */   public static final int SUMMARY_TABLE = 8;
/*     */   public static final int FUNCTION = 9;
/*     */   protected int m_ID;
/*     */   protected String m_PhysicalName;
/*     */   protected int m_EntityType;
/*  37 */   protected int m_ListID = -1;
/*     */   
/*     */   protected int m_StringTag;
/*     */   protected String m_Description;
/*     */   
/*     */   public DBEntity(DBEntity src) {
/*  43 */     this.m_EntityType = src.m_EntityType;
/*  44 */     this.m_ID = src.m_ID;
/*  45 */     this.m_PhysicalName = src.m_PhysicalName;
/*  46 */     this.m_ListID = src.m_ListID;
/*  47 */     this.m_StringTag = src.m_StringTag;
/*  48 */     this.m_Description = src.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/*  53 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int i) {
/*  58 */     this.m_StringTag = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBEntity(int type) {
/*  63 */     this.m_EntityType = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getID() {
/*  68 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityType() {
/*  73 */     return this.m_EntityType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setID(int iD) {
/*  78 */     this.m_ID = iD;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  83 */     super.setName(name);
/*     */     
/*  85 */     if (StringUtil.isEmpty(this.m_PhysicalName)) {
/*  86 */       this.m_PhysicalName = this.m_Name;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  91 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void changeName(INameProducer namer, INamedVariables variables, ISubstitutionListener listener, Object dbConnection) {
/*  97 */     this.m_PhysicalName = namer.getPhysicalName(this, variables, listener, dbConnection);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPhysicalName() {
/* 102 */     return this.m_PhysicalName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhysicalName(String string) {
/* 107 */     this.m_PhysicalName = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 112 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String string) {
/* 117 */     this.m_Description = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListID() {
/* 122 */     return this.m_ListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListID(int listID) {
/* 127 */     this.m_ListID = listID;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */