/*    */ package org.apache.logging.log4j.core.net.ssl;
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
/*    */ public class StoreConfigurationException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public StoreConfigurationException(Exception e) {
/* 26 */     super(e);
/*    */   }
/*    */   
/*    */   public StoreConfigurationException(String message) {
/* 30 */     super(message);
/*    */   }
/*    */   
/*    */   public StoreConfigurationException(String message, Exception e) {
/* 34 */     super(message, e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\net\ssl\StoreConfigurationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */