/*    */ package com.lbs.data.debug;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StackInfo
/*    */ {
/* 15 */   private final transient LbsConsole m_Logger = LbsConsole.getLogger("Data.Client.StackInfo");
/*    */   
/*    */   public StackInfo() {
/* 18 */     StackTraceElement[] stackTrace = (new Exception("")).getStackTrace();
/*    */     
/* 20 */     for (int i = 0; i < stackTrace.length; i++) {
/*    */       
/* 22 */       StackTraceElement stackElement = stackTrace[i];
/*    */ 
/*    */       
/* 25 */       this.m_Logger.debug(stackElement);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\debug\StackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */