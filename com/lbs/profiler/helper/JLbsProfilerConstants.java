/*     */ package com.lbs.profiler.helper;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public class JLbsProfilerConstants
/*     */ {
/*     */   public static final String BUSINESS_LOGIC_HANDLER = "BLH";
/*     */   public static final String SERVER_QUERY_FACTORY_First = "SQ.First";
/*     */   public static final String SERVER_QUERY_FACTORY_ExecuteRowset = "SQ.ExecuteRowset";
/*     */   public static final String SERVER_QUERY_FACTORY_PrepareCommand = "SQ.PrepareCommand";
/*     */   public static final String SERVER_QUERY_FACTORY_PrepareQuerySchema = "SQ.PrepareQuerySchema";
/*     */   public static final String SERVER_QUERY_FACTORY_Last = "SQ.Last";
/*     */   public static final String SERVER_QUERY_FACTORY_Search = "SQ.Search";
/*     */   public static final String SERVER_QUERY_FACTORY_GetQrySchema = "SQ.GetQuerySchema";
/*     */   public static final String SERVER_QUERY_FACTORY_GetQryGridSchema = "SQ.GetQueryGridSchema";
/*     */   public static final String SERVER_QUERY_FACTORY_ExecuteQuery = "SQ.ExecuteQuery";
/*     */   public static final String SERVER_QUERY_FACTORY_InternalExecServiceQry = "SQ.InternalExecuteServiceQuery";
/*     */   public static final String SERVER_QUERY_FACTORY_InternalExecServiceBatch = "SQ.InternalExecuteServiceBatch";
/*     */   public static final String SERVER_QUERY_FACTORY_AffectedAutoIncrementValues = "SQ.AffectedAutoIncrementValues";
/*     */   public static final String SERVER_QUERY_FACTORY_ExecuteSelectQuery = "SQ.ExecuteSelectQuery";
/*     */   public static final String SERVER_QUERY_FACTORY_Select = "SQ.Select";
/*     */   public static final String SERVER_QUERY_FACTORY_GetRowCount = "SQ.GetRowCount";
/*     */   public static final String SERVER_CURSORQ_FACTORY_First = "SCQ.First";
/*     */   public static final String SERVER_CURSORQ_FACTORY_NativeFirst = "SCQ.NativeFirst";
/*     */   public static final String DOCUARTINTG_GETFOLDERELEMENTS = "DC.DocuartInteg.GetFolderElements";
/*     */   public static final String DOCUARTINTG_GETDOCUMENT = "DC.DocuartInteg.GetDocument";
/*     */   public static final String SERVER_OBJECT_F_TransactionBegin = "SF.TransactionBegin";
/*     */   public static final String SERVER_OBJECT_F_TransactionRollback = "SF.TransactionRollback";
/*     */   public static final String SERVER_OBJECT_F_TransactionCommit = "SF.TransactionCommit";
/*     */   public static final String SERVER_OBJECT_F_BusinessObjectLocked = "SF.BusinessObjectLocked";
/*     */   public static final String SERVER_OBJECT_F_CheckIsLocked = "SF.CheckIsLocked";
/*     */   public static final String SERVER_OBJECT_F_CheckRequestLock = "SF.CheckRequestLock";
/*     */   public static final String SERVER_OBJECT_F_ReleaseLock = "SF.ReleaseLock";
/*     */   public static final String SERVER_OBJECT_F_LockAlreadyReleased = "SF.LockAlreadyReleased";
/*     */   public static final String SERVER_OBJECT_F_AcquireLock = "SF.AcquireLock";
/*     */   public static final String SERVER_OBJECT_F_TransactOpt = "SF.TransactOperation";
/*     */   public static final String DBCONNECTION_Test = "DBConn.Tests";
/*     */   public static final String DBCONNECTION_Transactions = "DBConn.Transactions";
/*     */   public static final String DBCONNECTION_ExecuteRowset = "DBConn.Execute.Rowset";
/*     */   public static final String DBCONNECTION_ExecuteScalar = "DBConn.Execute.Scalar";
/*     */   public static final String DBCONNECTION_ExecuteBatch = "DBConn.Execute.Batch";
/*     */   public static final String DBCONNECTION_ExecuteCommand = "DBConn.Execute.Command";
/*     */   public static final String REPORTING_ProcessQueryObject = "Reporting.ProcessQueryObject";
/*     */   public static final String REPORTING_RunDetailQueries = "Reporting.RunDetailQueries";
/*     */   public static final String REPORTING_ProcessDetailQueryObject = "Reporting.ProcessDetailQueryObject";
/*     */   public static final String ORA_CONNECTION_PrepareStatement = "ORADB.PrepareStatement";
/*     */   public static final String SERVLET_REMOTING_TOTAL = "Servlet.Remoting.Total";
/*     */   public static final String SERVLET_REMOTING_PROCESS = "Servlet.Remoting.Process";
/*     */   public static final String TRANSPORT_SENDDATA = "Transport.SendData";
/*     */   public static final String CONTEXT_EXECUTE = "ServerContext.Execute";
/*     */   public static final String CONTEXT_TransactionBegin = "ServerContext.TransactionBegin";
/*     */   public static final String CONTEXT_TransactionCommit = "ServerContext.TransactionCommit";
/*     */   public static final String CONTEXT_TransactionRollback = "ServerContext.TransactionRollback";
/*     */   public static final String CONTEXT_TransactionLifetime = "ServerContext.TransactionLifetime";
/*     */   public static final String BATCH_StartExecute = "Batch.StartExecute";
/*     */   public static final String BATCH_FinishExecute = "Batch.FinishExecute";
/*     */   
/*     */   public static String[] getList() {
/*     */     try {
/* 156 */       Field[] fields = JLbsProfilerConstants.class.getFields();
/* 157 */       ArrayList<Object> valueList = new ArrayList(fields.length + 1);
/*     */       
/* 159 */       for (int i = 0; i < fields.length; i++)
/*     */       {
/* 161 */         valueList.add(fields[i].get(null));
/*     */       }
/* 163 */       Collections.sort(valueList);
/* 164 */       return valueList.<String>toArray(new String[valueList.size()]);
/*     */     }
/* 166 */     catch (Exception t) {
/*     */       
/* 168 */       t.printStackTrace();
/*     */       
/* 170 */       return new String[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int size() {
/* 175 */     return (JLbsProfilerConstants.class.getFields()).length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean contains(String profilePointName) {
/* 185 */     return contains(profilePointName, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean contains(String profilePointName, String[] list) {
/* 196 */     if (list == null)
/* 197 */       list = getList(); 
/* 198 */     for (int i = 0; i < list.length; i++) {
/*     */       
/* 200 */       if (list[i].equals(profilePointName))
/* 201 */         return true; 
/*     */     } 
/* 203 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\JLbsProfilerConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */