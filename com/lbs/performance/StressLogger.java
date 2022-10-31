/*     */ package com.lbs.performance;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import java.io.IOException;
/*     */ import java.util.logging.FileHandler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StressLogger
/*     */   implements IPerformanceLogger
/*     */ {
/*     */   private static final boolean STRESS_LOGING = true;
/*  22 */   protected static Logger m_Logger = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StressLogger(String fileName) {
/*  29 */     synchronized (StressLogger.class) {
/*     */       
/*  31 */       if (m_Logger == null) {
/*  32 */         m_Logger = createLogger(fileName);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static Logger getLogger() {
/*  38 */     return m_Logger;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Logger createLogger(String fileName) {
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Logger createLogger(String loggerName, String fileName) {
/*  48 */     Logger logger = null;
/*     */ 
/*     */     
/*     */     try {
/*  52 */       if (logger == null)
/*     */       {
/*  54 */         logger = Logger.getLogger(loggerName);
/*  55 */         FileHandler fh = new FileHandler(fileName, true);
/*  56 */         LbsConsole.getLogger("Data.Client.StressLogger").info("Log Location : " + fileName);
/*     */         
/*  58 */         fh.setFormatter(new StressFormatter());
/*     */         
/*  60 */         logger.addHandler(fh);
/*  61 */         logger.setLevel(Level.ALL);
/*     */       }
/*     */     
/*  64 */     } catch (IOException e) {
/*     */       
/*  66 */       LbsConsole.getLogger("Data.Client.StressLogger").error(null, e);
/*     */     } 
/*     */ 
/*     */     
/*  70 */     return logger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void entering(String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/*  78 */     LogRecord lr = new LogRecord(Level.FINER, "ENTRY");
/*  79 */     lr.setSourceClassName(sourceClass);
/*  80 */     lr.setSourceMethodName(sourceMethod);
/*  81 */     lr.setParameters(new Object[] { sessionID, message, Long.valueOf(JLbsPreciseTime.getMicroSeconds()[1]), Integer.valueOf(level) });
/*  82 */     getLogger().log(lr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exiting(String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/*  91 */     LogRecord lr = new LogRecord(Level.FINER, "RETURN");
/*  92 */     lr.setSourceClassName(sourceClass);
/*  93 */     lr.setSourceMethodName(sourceMethod);
/*  94 */     lr.setParameters(new Object[] { sessionID, message, Long.valueOf(JLbsPreciseTime.getMicroSeconds()[1]), Integer.valueOf(level) });
/*  95 */     getLogger().log(lr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(String sourceClass, String sourceMethod, int level, String sessionID, String message) {
/* 101 */     LogRecord lr = new LogRecord(Level.FINER, "INFO");
/* 102 */     lr.setSourceClassName(sourceClass);
/* 103 */     lr.setSourceMethodName(sourceMethod);
/* 104 */     lr.setParameters(new Object[] { sessionID, message, Long.valueOf(JLbsPreciseTime.getMicroSeconds()[1]), Integer.valueOf(level) });
/* 105 */     getLogger().log(lr);
/*     */   }
/*     */   
/*     */   public void flush() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\performance\StressLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */