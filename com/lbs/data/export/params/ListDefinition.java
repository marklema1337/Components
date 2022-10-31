/*     */ package com.lbs.data.export.params;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ public class ListDefinition
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private boolean m_ExcludeAll = false;
/*     */   private List m_IncludeList;
/*     */   private List m_ExcludeList;
/*     */   private List m_ForwardList;
/*     */   private List m_BackwardList;
/*     */   private List m_ParameterList;
/*     */   private List m_MapList;
/*     */   
/*     */   public ListDefinition() {
/*  29 */     this.m_ExcludeAll = false;
/*  30 */     this.m_IncludeList = new ArrayList();
/*  31 */     this.m_ExcludeList = new ArrayList();
/*  32 */     this.m_ForwardList = new ArrayList();
/*  33 */     this.m_BackwardList = new ArrayList();
/*  34 */     this.m_ParameterList = new ArrayList();
/*  35 */     this.m_MapList = new ArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public List getIncludeList() {
/*  40 */     return this.m_IncludeList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearIncludeList() {
/*  45 */     this.m_IncludeList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludeList(List includeList) {
/*  50 */     if (includeList == null) {
/*  51 */       this.m_IncludeList.clear();
/*     */     } else {
/*  53 */       this.m_IncludeList = includeList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addIncludeListItem(Object item) {
/*  58 */     if (this.m_IncludeList.contains(item)) {
/*     */       return;
/*     */     }
/*  61 */     this.m_IncludeList.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeIncludeListItem(Object item) {
/*  66 */     if (!this.m_IncludeList.contains(item)) {
/*     */       return;
/*     */     }
/*  69 */     this.m_IncludeList.remove(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getExcludeList() {
/*  74 */     return this.m_ExcludeList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearExcludeList() {
/*  79 */     this.m_ExcludeList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExcludeList(List excludeList) {
/*  84 */     if (excludeList == null) {
/*  85 */       this.m_ExcludeList.clear();
/*     */     } else {
/*  87 */       this.m_ExcludeList = excludeList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addExcludeListItem(Object item) {
/*  92 */     if (this.m_ExcludeList.contains(item)) {
/*     */       return;
/*     */     }
/*  95 */     this.m_ExcludeList.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeExcludeListItem(Object item) {
/* 100 */     if (!this.m_ExcludeList.contains(item)) {
/*     */       return;
/*     */     }
/* 103 */     this.m_ExcludeList.remove(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getForwardList() {
/* 108 */     return this.m_ForwardList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearForwardList() {
/* 113 */     this.m_ForwardList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForwardList(List forwardList) {
/* 118 */     if (forwardList == null) {
/* 119 */       this.m_ForwardList.clear();
/*     */     } else {
/* 121 */       this.m_ForwardList = forwardList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addForwardListItem(Object item) {
/* 126 */     if (this.m_ForwardList.contains(item)) {
/*     */       return;
/*     */     }
/* 129 */     this.m_ForwardList.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeForwardListItem(Object item) {
/* 134 */     if (!this.m_ForwardList.contains(item)) {
/*     */       return;
/*     */     }
/* 137 */     this.m_ForwardList.remove(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBackwardList() {
/* 142 */     return this.m_BackwardList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearBackwardList() {
/* 147 */     this.m_BackwardList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackwardList(List backwardList) {
/* 152 */     if (backwardList == null) {
/* 153 */       this.m_BackwardList.clear();
/*     */     } else {
/* 155 */       this.m_BackwardList = backwardList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addBackwardListItem(Object item) {
/* 160 */     if (this.m_BackwardList.contains(item)) {
/*     */       return;
/*     */     }
/* 163 */     this.m_BackwardList.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeBackwardListItem(Object item) {
/* 168 */     if (!this.m_BackwardList.contains(item))
/*     */       return; 
/* 170 */     this.m_BackwardList.remove(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getMapList() {
/* 175 */     return this.m_MapList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearMapList() {
/* 180 */     this.m_MapList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMapList(List mapList) {
/* 185 */     if (mapList == null) {
/* 186 */       this.m_MapList.clear();
/*     */     } else {
/* 188 */       this.m_MapList = mapList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addMapListItem(Object item) {
/* 193 */     if (this.m_MapList.contains(item)) {
/*     */       return;
/*     */     }
/* 196 */     this.m_MapList.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMapListItem(Object item) {
/* 201 */     if (!this.m_MapList.contains(item))
/*     */       return; 
/* 203 */     this.m_MapList.remove(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getParameterList() {
/* 208 */     return this.m_ParameterList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearParameterList() {
/* 213 */     this.m_ParameterList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParameterList(List parameterList) {
/* 218 */     if (parameterList == null) {
/* 219 */       this.m_ParameterList.clear();
/*     */     } else {
/* 221 */       this.m_ParameterList = parameterList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addParameter(Object item) {
/* 226 */     if (this.m_ParameterList.contains(item)) {
/*     */       return;
/*     */     }
/* 229 */     this.m_ParameterList.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeParameter(Object item) {
/* 234 */     if (!this.m_ParameterList.contains(item)) {
/*     */       return;
/*     */     }
/* 237 */     this.m_ParameterList.remove(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExcludeAll() {
/* 242 */     return this.m_ExcludeAll;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExcludeAll(boolean includeAll) {
/* 247 */     this.m_ExcludeAll = includeAll;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ListDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */