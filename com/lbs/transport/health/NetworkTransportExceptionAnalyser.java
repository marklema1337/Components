/*    */ package com.lbs.transport.health;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NetworkTransportExceptionAnalyser
/*    */   implements TransportExceptionAnalyser
/*    */ {
/*    */   public boolean isTransportBlocker(Throwable t) {
/* 11 */     return !(!(t instanceof java.net.ConnectException) && !(t instanceof java.rmi.ConnectException));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\health\NetworkTransportExceptionAnalyser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */