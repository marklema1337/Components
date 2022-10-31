/*    */ package com.lbs.console;
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
/*    */ public class LbsConsoleSettings
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2L;
/* 48 */   public static transient String CLIENT_LOGGING_LEVEL = "WARN";
/*    */   
/* 50 */   private String m_ClientLoggingLevel = CLIENT_LOGGING_LEVEL;
/*    */ 
/*    */   
/*    */   public LbsConsoleSettings(String logLevel) {
/* 54 */     this.m_ClientLoggingLevel = logLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LbsConsoleSettings getInstance() {
/* 59 */     return new LbsConsoleSettings(CLIENT_LOGGING_LEVEL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyClientSettings() {
/* 64 */     CLIENT_LOGGING_LEVEL = this.m_ClientLoggingLevel;
/* 65 */     LbsConsole.getRootLogger().setLevel2(LbsLevel.getLevelByName(CLIENT_LOGGING_LEVEL));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsConsoleSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */