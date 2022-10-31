/*     */ package com.lbs.mi.defs;
/*     */ 
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
/*     */ public class GlobalSearchDefinition
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_Id;
/*     */   private String m_Name;
/*     */   private int m_ListID;
/*     */   private int m_StringTag;
/*     */   private ArrayList<String> m_Columns;
/*     */   private ModuleAction m_Action;
/*     */   
/*     */   public GlobalSearchDefinition() {
/*  29 */     this.m_Columns = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public GlobalSearchDefinition(GlobalSearchDefinition def) {
/*  34 */     this.m_Id = def.getId();
/*  35 */     this.m_Name = def.getName();
/*  36 */     this.m_ListID = def.getListID();
/*  37 */     this.m_StringTag = def.getStringTag();
/*  38 */     this.m_Columns = def.getColumns();
/*  39 */     this.m_Action = def.getAction();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addColumn(String alias) {
/*  44 */     getColumns().add(alias);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  49 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  54 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListID(int listID) {
/*  59 */     this.m_ListID = listID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListID() {
/*  64 */     return this.m_ListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int stringTag) {
/*  69 */     this.m_StringTag = stringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/*  74 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getColumns() {
/*  79 */     return this.m_Columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAction(ModuleAction action) {
/*  84 */     this.m_Action = action;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleAction getAction() {
/*  89 */     return this.m_Action;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/*  95 */     this.m_Id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/* 101 */     return this.m_Id;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\GlobalSearchDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */