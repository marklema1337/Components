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
/*    */ public class LoggerType
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private static final char LOGGER_TYPE_CONSOLE = 'C';
/*    */   private static final char LOGGER_TYPE_PROFILER = 'P';
/* 21 */   public static final LoggerType CONSOLE = new LoggerType('C');
/* 22 */   public static final LoggerType PROFILER = new LoggerType('P');
/*    */   
/*    */   private char m_Type;
/*    */ 
/*    */   
/*    */   private LoggerType(char type) {
/* 28 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 33 */     if (!(obj instanceof LoggerType)) {
/* 34 */       return false;
/*    */     }
/* 36 */     return (((LoggerType)obj).m_Type == this.m_Type);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 41 */     return super.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPrefix() {
/* 46 */     switch (this.m_Type) {
/*    */       
/*    */       case 'C':
/* 49 */         return "";
/*    */       case 'P':
/* 51 */         return "Profiler";
/*    */     } 
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static LoggerType getTypeByName(String fullLoggerName) {
/* 59 */     if (fullLoggerName.startsWith("Profiler"))
/* 60 */       return PROFILER; 
/* 61 */     if (fullLoggerName.startsWith("")) {
/* 62 */       return CONSOLE;
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     return getPrefix();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LoggerType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */