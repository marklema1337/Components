/*    */ package com.lbs.dsignature.signer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DTBSWithSignedData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected byte[] m_SignedData;
/*    */   
/*    */   public byte[] getSignedData() {
/* 15 */     return this.m_SignedData;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSignedData(byte[] signedData) {
/* 20 */     this.m_SignedData = signedData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\DTBSWithSignedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */