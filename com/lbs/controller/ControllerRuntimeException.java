/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.localization.LbsLocalizableException;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControllerRuntimeException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   private ArrayList<Exception> m_Exceptions = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public ControllerRuntimeException(ArrayList<Exception> exceptions) {
/* 18 */     this.m_Exceptions = exceptions;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<Exception> getExceptions() {
/* 23 */     return this.m_Exceptions;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedMessage() {
/* 29 */     StringBuilder sb = new StringBuilder();
/* 30 */     String superMsg = super.getLocalizedMessage();
/* 31 */     if (superMsg != null)
/* 32 */       sb.append(superMsg); 
/* 33 */     sb.append("Details: ").append("\n");
/* 34 */     for (Exception e : this.m_Exceptions)
/*    */     {
/* 36 */       sb.append(e.getLocalizedMessage()).append("\n");
/*    */     }
/* 38 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void localizeMessage(ILocalizationServices lclService) {
/* 43 */     for (Exception e : this.m_Exceptions) {
/*    */       
/* 45 */       if (e instanceof LbsLocalizableException)
/*    */       {
/* 47 */         ((LbsLocalizableException)e).localizeMessage(lclService);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ControllerRuntimeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */