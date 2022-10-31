/*    */ package org.apache.logging.log4j.internal;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogManagerStatus
/*    */ {
/*    */   private static boolean initialized = false;
/*    */   
/*    */   public static void setInitialized(boolean managerStatus) {
/* 27 */     initialized = managerStatus;
/*    */   }
/*    */   
/*    */   public static boolean isInitialized() {
/* 31 */     return initialized;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\internal\LogManagerStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */