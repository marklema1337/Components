/*    */ package com.lbs.contract;
/*    */ 
/*    */ import com.lbs.localization.LbsLocalizableException;
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
/*    */ public class ContractException
/*    */   extends LbsLocalizableException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private static final String CONTRACT_EXCEPTION_DEF_MSG = "An exception occured during contract definiton!";
/*    */   
/*    */   public ContractException() {
/* 21 */     this("An exception occured during contract definiton!");
/*    */   }
/*    */ 
/*    */   
/*    */   public ContractException(String message) {
/* 26 */     super(-1003, 72, message);
/*    */   }
/*    */ 
/*    */   
/*    */   public ContractException(Throwable cause) {
/* 31 */     this("An exception occured during contract definiton!", cause);
/*    */   }
/*    */ 
/*    */   
/*    */   public ContractException(String message, Throwable cause) {
/* 36 */     super(cause, -1003, 72, message);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */