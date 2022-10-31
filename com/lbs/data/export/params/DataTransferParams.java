/*    */ package com.lbs.data.export.params;
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
/*    */ public class DataTransferParams
/*    */   implements IDataTransferParams
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private IDataImportParams m_ImportParams;
/*    */   private IDataExportParams m_ExportParams;
/*    */   private Object m_TargetLoginParameters;
/*    */   private boolean m_ExecuteInBatch;
/*    */   private int[] m_KeysToExport;
/*    */   
/*    */   public void setImportParams(IDataImportParams importParams) {
/* 24 */     this.m_ImportParams = importParams;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDataImportParams getImportParams() {
/* 29 */     return this.m_ImportParams;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExportParams(IDataExportParams exportParams) {
/* 34 */     this.m_ExportParams = exportParams;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDataExportParams getExportParams() {
/* 39 */     return this.m_ExportParams;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getTargetLoginParameters() {
/* 44 */     return this.m_TargetLoginParameters;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTargetLoginParameters(Object targetLoginParameters) {
/* 49 */     this.m_TargetLoginParameters = targetLoginParameters;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isExecuteInBatch() {
/* 54 */     return this.m_ExecuteInBatch;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExecuteInBatch(boolean executeInBatch) {
/* 59 */     this.m_ExecuteInBatch = executeInBatch;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getPrimaryKeysToExport() {
/* 64 */     return this.m_KeysToExport;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPrimaryKeysToExport(int[] keys) {
/* 69 */     this.m_KeysToExport = keys;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\DataTransferParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */