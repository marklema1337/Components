/*     */ package com.lbs.data.export.params;
/*     */ 
/*     */ import com.lbs.platform.interfaces.ILbsValidationError;
/*     */ import com.lbs.platform.interfaces.ILbsValidationResult;
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
/*     */ public class ImportError
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int ERROR_PERSIST = 0;
/*     */   public static final int ERROR_INITIALIZE = 1;
/*     */   public static final int ERROR_VALIDATE = 2;
/*     */   public static final int ERROR_READ = 3;
/*     */   public static final int ERROR_UPDATE = 4;
/*     */   public static final int ERROR_EXCEPTION = 5;
/*     */   public static final int ERROR_PREPARE = 6;
/*     */   public static final int ERROR_AUTHORIZATION = 7;
/*     */   public static final int OPERATION_INSERT = 0;
/*     */   public static final int OPERATION_UPDATE = 1;
/*     */   public static final int OPERATION_DELETE = 2;
/*     */   public static final int OPERATION_READ = 3;
/*     */   public static final int OPERATION_XML = 4;
/*     */   private int m_OperationType;
/*     */   private int m_ErrorType;
/*     */   private String m_ErrorStr;
/*     */   private Object m_ErrorObj;
/*     */   
/*     */   public ImportError() {}
/*     */   
/*     */   public ImportError(int operationType, int errorType, String errorStr) {
/*  47 */     this.m_OperationType = operationType;
/*  48 */     this.m_ErrorType = errorType;
/*  49 */     this.m_ErrorStr = errorStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImportError(int operationType, int errorType, Object errorData) {
/*  54 */     this.m_OperationType = operationType;
/*  55 */     this.m_ErrorType = errorType;
/*  56 */     this.m_ErrorStr = getErrorStr(errorData);
/*  57 */     this.m_ErrorObj = errorData;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getErrorStr(Object errorData) {
/*  62 */     if (errorData instanceof ILbsValidationResult) {
/*     */       
/*  64 */       StringBuilder sb = new StringBuilder();
/*  65 */       sb.append("Validation error: ");
/*  66 */       ILbsValidationResult result = (ILbsValidationResult)errorData;
/*  67 */       int size = result.size();
/*  68 */       if (size > 0)
/*     */       {
/*     */         
/*  71 */         for (int i = 0; i < size; i++) {
/*     */           
/*  73 */           ILbsValidationError err = (ILbsValidationError)result.get(i);
/*  74 */           sb.append("(" + err.getLocalizedMessage() + ")");
/*     */         } 
/*     */       }
/*  77 */       return sb.toString();
/*     */     } 
/*  79 */     return String.valueOf(errorData);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getErrorType() {
/*  84 */     return this.m_ErrorType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setErrorType(int errorType) {
/*  89 */     this.m_ErrorType = errorType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getErrorStr() {
/*  94 */     return this.m_ErrorStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setErrorStr(String errorStr) {
/*  99 */     this.m_ErrorStr = errorStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setErrorObj(Object errorObj) {
/* 104 */     this.m_ErrorObj = errorObj;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getErrorObj() {
/* 109 */     return this.m_ErrorObj;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOperationType() {
/* 114 */     return this.m_OperationType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperationType(int operationType) {
/* 119 */     this.m_OperationType = operationType;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\ImportError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */