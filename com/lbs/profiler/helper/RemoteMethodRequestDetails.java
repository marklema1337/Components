/*    */ package com.lbs.profiler.helper;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
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
/*    */ 
/*    */ public class RemoteMethodRequestDetails
/*    */ {
/* 21 */   private static transient ThreadLocal ms_MethodCallDetails = new ThreadLocal();
/*    */   
/*    */   public String sessionCode;
/*    */   
/*    */   public String targetServlet;
/*    */   
/*    */   private WeakReference[] parameters;
/*    */   
/*    */   public String methodName;
/*    */   
/*    */   public boolean[] returnParams;
/*    */   
/*    */   public String stackInfo;
/*    */   
/*    */   public String openWindowNames;
/*    */   
/* 37 */   public int[] userGroups = new int[0];
/*    */   
/* 39 */   public String userName = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean newInstance;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static RemoteMethodRequestDetails getMethodCallDetails() {
/* 55 */     return ms_MethodCallDetails.get();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setMethodCallDetails(RemoteMethodRequestDetails methodCallDetails) {
/* 67 */     ms_MethodCallDetails.set(methodCallDetails);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParameters(Object[] parameters) {
/* 72 */     if (parameters == null) {
/*    */       return;
/*    */     }
/* 75 */     this.parameters = new WeakReference[parameters.length];
/* 76 */     for (int i = 0; i < parameters.length; i++) {
/* 77 */       this.parameters[i] = new WeakReference(parameters[i]);
/*    */     }
/*    */   }
/*    */   
/*    */   public Object[] getParameters() {
/* 82 */     if (this.parameters == null) {
/* 83 */       return new Object[0];
/*    */     }
/*    */ 
/*    */     
/* 87 */     Object[] retval = new Object[this.parameters.length];
/* 88 */     for (int i = 0; i < retval.length; i++)
/* 89 */       retval[i] = this.parameters[i].get(); 
/* 90 */     return retval;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\profiler\helper\RemoteMethodRequestDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */