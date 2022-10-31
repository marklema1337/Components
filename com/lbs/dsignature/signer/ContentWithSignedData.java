/*    */ package com.lbs.dsignature.signer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ContentWithSignedData
/*    */   extends DTBSWithSignedData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Tckn;
/*    */   private String m_SessionCode;
/*    */   
/*    */   public String getTckn() {
/* 14 */     return this.m_Tckn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTckn(String tckn) {
/* 19 */     this.m_Tckn = tckn;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSessionCode() {
/* 24 */     return this.m_SessionCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSessionCode(String sessionCode) {
/* 29 */     this.m_SessionCode = sessionCode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\ContentWithSignedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */