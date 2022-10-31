/*    */ package com.lbs.crypto;
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
/*    */ public class JLbsDESKey
/*    */ {
/* 15 */   protected byte[] m_KeyData = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsDESKey() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JLbsDESKey(byte[] keyData) {
/* 35 */     internalInitialize(keyData, 0, keyData.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInitialized() {
/* 44 */     return (this.m_KeyData != null && this.m_KeyData.length == 8);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getKey() {
/* 54 */     return this.m_KeyData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void internalInitialize(byte[] keyData, int index, int length) {
/* 60 */     if (keyData.length > 0 && index >= 0 && length > 0 && index + length <= keyData.length) {
/*    */       
/* 62 */       this.m_KeyData = new byte[8];
/* 63 */       int iLen = Math.min(length, 8);
/* 64 */       System.arraycopy(keyData, index, this.m_KeyData, 0, iLen);
/* 65 */       if (iLen < 8)
/* 66 */         for (int i = iLen; i < 8; i++)
/* 67 */           this.m_KeyData[i] = 0;  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsDESKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */