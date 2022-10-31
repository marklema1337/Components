/*     */ package com.lbs.client;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsMenuItem
/*     */ {
/*     */   private String m_Caption;
/*     */   private int m_Tag;
/*     */   private int m_Level;
/*     */   private boolean m_Leaf;
/*     */   private boolean m_ReportLeaf;
/*     */   private String m_MenuType;
/*     */   
/*     */   public LbsMenuItem(String caption, int tag, int level, String menuType, boolean leaf, boolean reportLeaf) {
/*  19 */     this.m_Caption = caption;
/*  20 */     this.m_Tag = tag;
/*  21 */     this.m_Level = level;
/*  22 */     this.m_MenuType = menuType;
/*  23 */     this.m_Leaf = leaf;
/*  24 */     this.m_ReportLeaf = reportLeaf;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsMenuItem(String caption, int tag, int level, String menuType, boolean leaf) {
/*  29 */     this.m_Caption = caption;
/*  30 */     this.m_Tag = tag;
/*  31 */     this.m_Level = level;
/*  32 */     this.m_MenuType = menuType;
/*  33 */     this.m_Leaf = leaf;
/*  34 */     this.m_ReportLeaf = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsMenuItem() {
/*  39 */     this("LbsMenuItem", 0, 0, "M", true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeaf() {
/*  44 */     return this.m_Leaf;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReportLeaf() {
/*  49 */     return this.m_ReportLeaf;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCaption() {
/*  54 */     return this.m_Caption;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/*  59 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  64 */     return this.m_Level;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMenuType() {
/*  69 */     return this.m_MenuType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeaf(boolean leaf) {
/*  74 */     this.m_Leaf = leaf;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReportLeaf(boolean reportLeaf) {
/*  79 */     this.m_ReportLeaf = reportLeaf;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaption(String caption) {
/*  84 */     this.m_Caption = caption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/*  89 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLevel(int level) {
/*  94 */     this.m_Level = level;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuType(String menuType) {
/*  99 */     this.m_MenuType = menuType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 106 */     sb.append("LbsMenuItem: ");
/*     */     
/* 108 */     sb.append(this.m_Caption);
/* 109 */     sb.append(" (");
/* 110 */     sb.append(this.m_Tag);
/* 111 */     sb.append(",");
/* 112 */     sb.append(this.m_Level);
/* 113 */     sb.append(",");
/* 114 */     sb.append(this.m_MenuType);
/* 115 */     sb.append(")");
/*     */     
/* 117 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\LbsMenuItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */