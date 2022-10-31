/*    */ package com.lbs.dsignature.signer;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class ContentWithDigestData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private byte[] m_Content;
/*    */   private byte[] m_ContentHash;
/*    */   
/*    */   public byte[] getContent() {
/* 23 */     return this.m_Content;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContent(byte[] contentBuffer) {
/* 28 */     this.m_Content = contentBuffer;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getContentHash() {
/* 33 */     return this.m_ContentHash;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContentHash(byte[] contentHash) {
/* 38 */     this.m_ContentHash = contentHash;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\ContentWithDigestData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */