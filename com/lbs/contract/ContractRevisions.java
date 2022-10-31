/*    */ package com.lbs.contract;
/*    */ 
/*    */ import java.util.Hashtable;
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
/*    */ public class ContractRevisions
/*    */ {
/* 16 */   private static Hashtable<String, Hashtable<String, Boolean>> ms_ContractRevisions = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public static void putContractRevision(String contractID, Hashtable<String, Boolean> revisionData) {
/* 20 */     ms_ContractRevisions.put(contractID, revisionData);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Hashtable<String, Boolean> getContractRevision(String contractID) {
/* 25 */     return ms_ContractRevisions.get(contractID);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Hashtable<String, Hashtable<String, Boolean>> getContractRevisions() {
/* 30 */     return ms_ContractRevisions;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setContractRevisions(Hashtable<String, Hashtable<String, Boolean>> contractRevisions) {
/* 35 */     ms_ContractRevisions = contractRevisions;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractRevisions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */