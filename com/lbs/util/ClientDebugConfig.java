/*    */ package com.lbs.util;
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
/*    */ public class ClientDebugConfig
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static transient boolean DEBUG = false;
/*    */   public static transient boolean DEBUG_DATA_CLIENT = false;
/* 22 */   public static transient int LOGLEVEL = DEBUG ? 
/* 23 */     7 : 
/* 24 */     0;
/*    */   
/*    */   private boolean m_Debug = false;
/*    */   private boolean m_DebugDataClient = false;
/* 28 */   private int m_LogLevel = this.m_Debug ? 
/* 29 */     7 : 
/* 30 */     0;
/*    */ 
/*    */ 
/*    */   
/*    */   private ClientDebugConfig(boolean debug, boolean debugDataClient, int logLevel) {
/* 35 */     this.m_Debug = debug;
/* 36 */     this.m_DebugDataClient = debugDataClient;
/* 37 */     this.m_LogLevel = logLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ClientDebugConfig getInstance() {
/* 42 */     return new ClientDebugConfig(DEBUG, DEBUG_DATA_CLIENT, LOGLEVEL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyDebugInfo() {
/* 47 */     JLbsConstants.DEBUG = this.m_Debug;
/* 48 */     JLbsConstants.DEBUG_DATA_CLIENT = this.m_DebugDataClient;
/* 49 */     JLbsConstants.LOGLEVEL = this.m_LogLevel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ClientDebugConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */