/*    */ package com.lbs.data.export.params;
/*    */ 
/*    */ import com.lbs.data.objects.SimpleBusinessObject;
/*    */ import com.lbs.data.objects.SimpleBusinessObjects;
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
/*    */ 
/*    */ 
/*    */ public class ImportFailures
/*    */ {
/*    */   private SimpleBusinessObjects m_Failed;
/*    */   private ImportError[] m_Errors;
/*    */   
/*    */   public ImportFailures() {}
/*    */   
/*    */   public ImportFailures(SimpleBusinessObjects failed, ImportError[] errors) {
/* 27 */     this.m_Failed = failed;
/* 28 */     this.m_Errors = errors;
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleBusinessObjects getFailed() {
/* 33 */     return this.m_Failed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFailed(SimpleBusinessObjects failed) {
/* 38 */     this.m_Failed = failed;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImportError[] getErrors() {
/* 43 */     return this.m_Errors;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setErrors(ImportError[] errors) {
/* 48 */     this.m_Errors = errors;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImportError getErrorAt(int idx) {
/* 53 */     if (idx >= 0 && this.m_Errors != null && this.m_Errors.length > idx)
/* 54 */       return this.m_Errors[idx]; 
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleBusinessObject getItemAt(int idx) {
/* 60 */     return (SimpleBusinessObject)this.m_Failed.itemAt(idx);
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 65 */     return this.m_Failed.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ImportFailures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */