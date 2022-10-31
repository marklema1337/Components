/*    */ package com.lbs.rmi.test;
/*    */ 
/*    */ import com.lbs.data.query.QueryBusinessObjects;
/*    */ import com.lbs.data.query.QueryParams;
/*    */ import com.lbs.remoteclient.IClientContext;
/*    */ import com.lbs.rmi.JLbsClientHttpInvoker;
/*    */ import com.lbs.transport.RemoteMethodResponse;
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
/*    */ public class JLbsHttpInvokerTester
/*    */ {
/*    */   public static void test(IClientContext context) {
/* 22 */     testQueryFactoryOK(context);
/* 23 */     testQueryFactoryException(context);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void testQueryFactoryOK(IClientContext context) {
/* 28 */     JLbsClientHttpInvoker invoker = new JLbsClientHttpInvoker(context.getRootUri(), "Invoker", context.getSession(), null);
/*    */     
/*    */     try {
/* 31 */       QueryBusinessObjects list = new QueryBusinessObjects();
/* 32 */       QueryParams params = new QueryParams();
/* 33 */       Object[] parameters = { "MMQOItemBrowser", params, list, Integer.valueOf(30), new Boolean(false) };
/* 34 */       boolean[] returnParams = { false, false, true };
/* 35 */       RemoteMethodResponse response = invoker.invoke("SQ", "first", parameters, returnParams, null, false);
/* 36 */       System.out.println(response);
/*    */     }
/* 38 */     catch (Exception e) {
/*    */       
/* 40 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void testQueryFactoryException(IClientContext context) {
/* 46 */     JLbsClientHttpInvoker invoker = new JLbsClientHttpInvoker(context.getRootUri(), "Invoker", context.getSession(), null);
/*    */     
/*    */     try {
/* 49 */       QueryBusinessObjects list = new QueryBusinessObjects();
/* 50 */       QueryParams params = new QueryParams();
/* 51 */       Object[] parameters = { "MMQOItemBrowserXX01", params, list, Integer.valueOf(30), new Boolean(false) };
/* 52 */       boolean[] returnParams = { false, false, true };
/* 53 */       RemoteMethodResponse response = invoker.invoke("SQ", "first", parameters, returnParams, null, false);
/* 54 */       System.out.println(response);
/*    */     }
/* 56 */     catch (Exception e) {
/*    */       
/* 58 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\test\JLbsHttpInvokerTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */