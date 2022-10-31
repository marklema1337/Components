/*     */ package com.lbs.controller;
/*     */ 
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public class ValidationMessage
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_Id;
/*  12 */   private MessageType m_Type = MessageType.error;
/*     */   
/*     */   private JLbsResourceId m_MessageResource;
/*     */   
/*     */   private String m_DefaultMessage;
/*     */   private String m_ComponentId;
/*     */   private String m_ChildComponentId;
/*  19 */   private int m_RowIndex = -1;
/*  20 */   private int m_ColumnIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidationMessage(MessageType type, JLbsResourceId messageResource, String defaultMessage, String componentId, String childComponentId, int rowIndex, int columnIndex) {
/*  26 */     this.m_Type = type;
/*  27 */     this.m_MessageResource = messageResource;
/*  28 */     this.m_DefaultMessage = defaultMessage;
/*  29 */     this.m_ComponentId = componentId;
/*  30 */     this.m_ChildComponentId = childComponentId;
/*  31 */     this.m_RowIndex = rowIndex;
/*  32 */     this.m_ColumnIndex = columnIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidationMessage(MessageType type, JLbsResourceId messageResource, String defaultMessage, String componentId, String childComponentId) {
/*  39 */     this.m_Type = type;
/*  40 */     this.m_MessageResource = messageResource;
/*  41 */     this.m_DefaultMessage = defaultMessage;
/*  42 */     this.m_ComponentId = componentId;
/*  43 */     this.m_ChildComponentId = childComponentId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidationMessage(MessageType type, String defaultMessage, String componentId, String childComponentId) {
/*  49 */     this.m_Type = type;
/*  50 */     this.m_DefaultMessage = defaultMessage;
/*  51 */     this.m_ComponentId = componentId;
/*  52 */     this.m_ChildComponentId = childComponentId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidationMessage(int id, MessageType type, String defaultMessage, int componentTag, int childComponentTag) {
/*  58 */     this.m_Id = id;
/*  59 */     this.m_Type = type;
/*  60 */     this.m_DefaultMessage = defaultMessage;
/*  61 */     this.m_ComponentId = String.valueOf(componentTag);
/*  62 */     this.m_ChildComponentId = String.valueOf(childComponentTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidationMessage(int id, MessageType type, String defaultMessage, int componentTag, int childComponentTag, int rowIdx, int columnIdx) {
/*  69 */     this.m_Id = id;
/*  70 */     this.m_Type = type;
/*  71 */     this.m_DefaultMessage = defaultMessage;
/*  72 */     this.m_ComponentId = String.valueOf(componentTag);
/*  73 */     this.m_ChildComponentId = String.valueOf(childComponentTag);
/*  74 */     this.m_RowIndex = rowIdx;
/*  75 */     this.m_ColumnIndex = columnIdx;
/*     */   }
/*     */ 
/*     */   
/*     */   public MessageType getType() {
/*  80 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(MessageType type) {
/*  85 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getMessageResource() {
/*  90 */     return this.m_MessageResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageResource(JLbsResourceId messageResource) {
/*  95 */     this.m_MessageResource = messageResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultMessage() {
/* 100 */     return this.m_DefaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultMessage(String defaultMessage) {
/* 105 */     this.m_DefaultMessage = defaultMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComponentId() {
/* 110 */     return this.m_ComponentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentId(String componentId) {
/* 115 */     this.m_ComponentId = componentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChildComponentId() {
/* 120 */     return this.m_ChildComponentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildComponentId(String childComponentId) {
/* 125 */     this.m_ChildComponentId = childComponentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRowIndex() {
/* 130 */     return this.m_RowIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRowIndex(int rowIndex) {
/* 135 */     this.m_RowIndex = rowIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnIndex() {
/* 140 */     return this.m_ColumnIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnIndex(int columnIndex) {
/* 145 */     this.m_ColumnIndex = columnIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int id) {
/* 150 */     this.m_Id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 155 */     return this.m_Id;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ValidationMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */