/*    */ package com.lbs.util;
/*    */ 
/*    */ 
/*    */ public class ClientContextHolder
/*    */ {
/*    */   private Object clientContext;
/*    */   
/*    */   public static ClientContextHolder getInstance() {
/*  9 */     return LbsClassInstanceProvider.<ClientContextHolder>getInstanceByClass(ClientContextHolder.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getClientContext() {
/* 14 */     return this.clientContext;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClientContext(Object clientContext) {
/* 19 */     this.clientContext = clientContext;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ClientContextHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */