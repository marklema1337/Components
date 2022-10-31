/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PivotExtraColumn
/*    */   implements Serializable
/*    */ {
/*    */   private String fieldName;
/*    */   private String fieldDescription;
/*    */   private String fieldTable;
/*    */   private String fieldDefinition;
/*    */   private String queryName;
/*    */   
/*    */   public String getFieldName() {
/* 26 */     return this.fieldName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFieldName(String fieldName) {
/* 33 */     this.fieldName = fieldName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFieldDescription() {
/* 40 */     return this.fieldDescription;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFieldDescription(String fieldDescription) {
/* 47 */     this.fieldDescription = fieldDescription;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFieldTable() {
/* 54 */     return this.fieldTable;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFieldTable(String fieldTable) {
/* 61 */     this.fieldTable = fieldTable;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getQueryName() {
/* 68 */     return this.queryName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setQueryName(String queryName) {
/* 75 */     this.queryName = queryName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFieldDefinition() {
/* 82 */     return this.fieldDefinition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFieldDefinition(String fieldDefinition) {
/* 89 */     this.fieldDefinition = fieldDefinition;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\PivotExtraColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */