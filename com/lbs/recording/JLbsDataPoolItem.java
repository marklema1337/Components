/*     */ package com.lbs.recording;
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
/*     */ public class JLbsDataPoolItem
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_Tag;
/*     */   private int m_ParentTag;
/*     */   private int m_Type;
/*     */   private String m_Title;
/*     */   private Object m_Value;
/*     */   private String m_PossibleValues;
/*     */   
/*     */   public JLbsDataPoolItem(int tag, int parentTag, int type, Object value, String title, String possibleValues) {
/*  27 */     setTag(tag);
/*  28 */     setParentTag(parentTag);
/*  29 */     setType(type);
/*  30 */     setValue(value);
/*  31 */     setTitle(title);
/*  32 */     setPossibleValues(possibleValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParentTag() {
/*  40 */     return this.m_ParentTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParentTag(int parentTag) {
/*  48 */     this.m_ParentTag = parentTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTag() {
/*  56 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/*  64 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  72 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/*  80 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  88 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/*  96 */     this.m_Value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     StringBuilder sb = new StringBuilder();
/* 102 */     sb.append("[");
/* 103 */     sb.append("Tag=" + getTag());
/* 104 */     sb.append(", Title=" + getTitle());
/* 105 */     sb.append(", Parent Tag=" + getParentTag());
/* 106 */     sb.append(", Type=" + getType());
/* 107 */     sb.append(", Value=" + getValue());
/* 108 */     sb.append("]");
/*     */     
/* 110 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 115 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 123 */     return this.m_Title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 131 */     this.m_Title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPossibleValues() {
/* 136 */     return this.m_PossibleValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPossibleValues(String possibleValues) {
/* 141 */     this.m_PossibleValues = possibleValues;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\JLbsDataPoolItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */