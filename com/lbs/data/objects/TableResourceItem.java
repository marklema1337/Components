/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.mi.defs.ModuleMenuNode;
/*     */ import com.lbs.util.JLbsStringUtil;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableResourceItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2517458027847934325L;
/*     */   private String m_TableName;
/*     */   private int m_RecordLimit;
/*     */   private int m_DBModeID;
/*     */   private String m_DBModeCondition;
/*     */   private byte m_RestrictFirmSums;
/*     */   private byte m_RestrictDomainSums;
/*     */   private ModuleMenuNode m_MenuNode;
/*  38 */   private int m_ToleranceAmount = 5;
/*     */ 
/*     */   
/*     */   public TableResourceItem(String tableName, int limit, int dbModeID, String dbModeCondition) {
/*  42 */     this.m_TableName = tableName;
/*  43 */     this.m_RecordLimit = limit;
/*  44 */     this.m_DBModeID = dbModeID;
/*  45 */     this.m_DBModeCondition = dbModeCondition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToleranceAmount(int toleranceAmount) {
/*  50 */     this.m_ToleranceAmount = toleranceAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getToleranceAmount() {
/*  55 */     return this.m_ToleranceAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleMenuNode getMenuNode() {
/*  60 */     return this.m_MenuNode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuNode(ModuleMenuNode menuNode) {
/*  65 */     this.m_MenuNode = menuNode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/*  70 */     return this.m_TableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableName(String tableName) {
/*  75 */     this.m_TableName = tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRecordLimit() {
/*  80 */     return this.m_RecordLimit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecordLimit(int recordLimit) {
/*  85 */     this.m_RecordLimit = recordLimit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDBModeID() {
/*  90 */     return this.m_DBModeID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDBModeID(int modeID) {
/*  95 */     this.m_DBModeID = modeID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDBModeCondition() {
/* 100 */     return this.m_DBModeCondition;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDBModeCondition(String modeCondition) {
/* 105 */     this.m_DBModeCondition = modeCondition;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getVisibleModeID() {
/* 110 */     return (this.m_DBModeID == -1) ? "" : 
/*     */       
/* 112 */       String.valueOf(this.m_DBModeID);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 117 */     if (!(obj instanceof TableResourceItem)) {
/* 118 */       return false;
/*     */     }
/* 120 */     TableResourceItem item = (TableResourceItem)obj;
/*     */     
/* 122 */     return (JLbsStringUtil.areEqual(item.getTableName(), this.m_TableName) && item.getDBModeID() == this.m_DBModeID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 128 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getRestrictFirmSums() {
/* 133 */     return this.m_RestrictFirmSums;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestrictFirmSums(byte restrictFirmSums) {
/* 138 */     this.m_RestrictFirmSums = restrictFirmSums;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getRestrictDomainSums() {
/* 143 */     return this.m_RestrictDomainSums;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestrictDomainSums(byte restrictDomainSums) {
/* 148 */     this.m_RestrictDomainSums = restrictDomainSums;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\TableResourceItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */