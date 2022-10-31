/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RowColorExpressionObject
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_Column;
/*     */   private int m_Operator;
/*     */   private String m_Value;
/*     */   private String m_ColorString;
/*     */   private Integer m_ColumnTag;
/*     */   private String m_ColumnPropName;
/*     */   private String m_ColumnHeader;
/*     */   private boolean m_IsAdvanced;
/*     */   private int m_FB;
/*     */   private int m_Type;
/*     */   private ArrayList<JLbsStringListItem> m_listId;
/*     */   
/*     */   public Integer getColumnTag() {
/*  26 */     return this.m_ColumnTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnTag(Integer columnTag) {
/*  31 */     this.m_ColumnTag = columnTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnPropName() {
/*  36 */     return this.m_ColumnPropName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnPropName(String columnPropName) {
/*  41 */     this.m_ColumnPropName = columnPropName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnHeader() {
/*  46 */     return this.m_ColumnHeader;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnHeader(String columnHeader) {
/*  51 */     this.m_ColumnHeader = columnHeader;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumn() {
/*  56 */     return this.m_Column;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumn(int column) {
/*  61 */     this.m_Column = column;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOperator() {
/*  66 */     return this.m_Operator;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperator(int operator) {
/*  71 */     this.m_Operator = operator;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  76 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String value) {
/*  81 */     this.m_Value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColorString() {
/*  86 */     return this.m_ColorString;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColorString(String colorString) {
/*  91 */     this.m_ColorString = colorString;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdvanced() {
/*  96 */     return this.m_IsAdvanced;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdvanced(boolean isAdvanced) {
/* 101 */     this.m_IsAdvanced = isAdvanced;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFB() {
/* 106 */     return this.m_FB;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFB(int m_fb) {
/* 111 */     this.m_FB = m_fb;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 116 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 121 */     this.m_Type = type;
/*     */   }
/*     */   
/*     */   public ArrayList<JLbsStringListItem> getListId() {
/* 125 */     return this.m_listId;
/*     */   }
/*     */   
/*     */   public void setListId(ArrayList<JLbsStringListItem> listId) {
/* 129 */     this.m_listId = listId;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\RowColorExpressionObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */