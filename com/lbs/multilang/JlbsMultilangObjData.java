/*    */ package com.lbs.multilang;
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
/*    */ public class JlbsMultilangObjData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Object businessObj;
/*    */   private String dataField;
/*    */   private Object editor;
/*    */   
/*    */   public Object getEditor() {
/* 25 */     return this.editor;
/*    */   }
/*    */   
/*    */   public void setEditor(Object editor) {
/* 29 */     this.editor = editor;
/*    */   }
/*    */   
/*    */   public Object getBusinessObj() {
/* 33 */     return this.businessObj;
/*    */   }
/*    */   
/*    */   public void setBusinessObj(Object businessObj) {
/* 37 */     this.businessObj = businessObj;
/*    */   }
/*    */   
/*    */   public String getDataField() {
/* 41 */     return this.dataField;
/*    */   }
/*    */   
/*    */   public void setDataField(String dataField) {
/* 45 */     this.dataField = dataField;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\multilang\JlbsMultilangObjData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */