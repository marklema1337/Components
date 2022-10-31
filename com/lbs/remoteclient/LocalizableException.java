/*    */ package com.lbs.remoteclient;
/*    */ 
/*    */ import com.lbs.resource.JLbsLocalizer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocalizableException
/*    */   extends Exception
/*    */ {
/*    */   protected int m_iResStr;
/*    */   
/*    */   public int getiResStr() {
/* 18 */     return this.m_iResStr;
/*    */   }
/*    */ 
/*    */   
/*    */   public LocalizableException(String msg, int iResStr) {
/* 23 */     super(msg);
/* 24 */     this.m_iResStr = iResStr;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/*    */     try {
/* 32 */       String s = JLbsLocalizer.getCultureResource(this.m_iResStr);
/*    */       
/* 34 */       return (s != null && s.length() > 0) ? 
/* 35 */         s : 
/* 36 */         super.getMessage();
/*    */     }
/* 38 */     catch (Exception e) {
/*    */       
/* 40 */       e.printStackTrace();
/* 41 */       return super.getMessage();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\LocalizableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */