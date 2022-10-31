/*     */ package com.lbs.data;
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
/*     */ public class UserShortCut
/*     */   implements Serializable, IShortCuts
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  18 */   private int m_Type = 0;
/*  19 */   private int m_ID = 0;
/*     */   
/*     */   private int m_Tag;
/*     */   
/*     */   private String m_EntityName;
/*     */   
/*     */   private int m_ShortCut;
/*     */   private int m_Modifier1;
/*     */   private int m_Modifier2;
/*     */   
/*     */   public UserShortCut() {}
/*     */   
/*     */   public UserShortCut(String entityName, int actionID) {
/*  32 */     this.m_EntityName = entityName;
/*  33 */     this.m_ID = actionID;
/*     */   }
/*     */ 
/*     */   
/*     */   public UserShortCut(String entityName, int actionID, int shortCut) {
/*  38 */     this.m_EntityName = entityName;
/*  39 */     this.m_ID = actionID;
/*  40 */     this.m_ShortCut = shortCut;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/*  45 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/*  50 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getID() {
/*  55 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setID(int id) {
/*  60 */     this.m_ID = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEntityName() {
/*  65 */     return this.m_EntityName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityName(String entityName) {
/*  70 */     this.m_EntityName = entityName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getShortCut() {
/*  75 */     return this.m_ShortCut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShortCut(int shortCut) {
/*  80 */     this.m_ShortCut = shortCut;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModifier1(int modifier1) {
/*  85 */     this.m_Modifier1 = modifier1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModifier1() {
/*  90 */     return this.m_Modifier1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModifier2(int modifier2) {
/*  95 */     this.m_Modifier2 = modifier2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModifier2() {
/* 100 */     return this.m_Modifier2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/* 105 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 111 */     return this.m_Tag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\UserShortCut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */