/*     */ package com.lbs.console;
/*     */ 
/*     */ import com.lbs.profiler.helper.RemoteMethodRequestDetails;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsProfilerLogger
/*     */ {
/*     */   public static final String LOGGER_NAME = "Profiler";
/*  19 */   private static transient Log4jLogger ms_Logger = null;
/*  20 */   private static final String CLASS_NAME = JLbsProfilerLogger.class.getName();
/*     */   
/*     */   public static boolean SESSION_LOGGER_ENABLED = true;
/*     */   private static Hashtable<String, Log4jLogger> ms_CachedLoggers;
/*     */   
/*     */   static {
/*  26 */     LbsConsole logger = LbsConsole.getLogger("Profiler");
/*  27 */     if (logger instanceof Log4jLogger) {
/*     */       
/*  29 */       ms_Logger = (Log4jLogger)logger;
/*  30 */       ms_Logger.setAdditivity(false);
/*  31 */       ms_CachedLoggers = new Hashtable<>();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static void log(LbsLevel level, Object message, Throwable t) {
/*  37 */     if (ms_Logger != null && SESSION_LOGGER_ENABLED) {
/*     */       
/*  39 */       RemoteMethodRequestDetails methodCallDetails = RemoteMethodRequestDetails.getMethodCallDetails();
/*     */       String userName;
/*  41 */       if (methodCallDetails == null || (userName = methodCallDetails.userName) == null || userName.length() == 0) {
/*  42 */         ms_Logger.internalLog(CLASS_NAME, level, message, t);
/*     */       } else {
/*     */         Log4jLogger logger;
/*     */         
/*  46 */         synchronized (ms_CachedLoggers) {
/*     */           
/*  48 */           userName = processUserName(userName);
/*  49 */           logger = ms_CachedLoggers.get(userName);
/*  50 */           if (logger == null) {
/*     */             
/*  52 */             int defaultBufLength = "Profiler".length() + userName.length() + 1;
/*  53 */             StringBuilder buf = getLoggerName(userName, defaultBufLength);
/*  54 */             logger = (Log4jLogger)ms_Logger.internalGetLogger(buf.toString());
/*  55 */             ms_CachedLoggers.put(userName, logger);
/*     */           } 
/*     */         } 
/*  58 */         processLoggerForDomain(logger);
/*  59 */         logger.internalLog(CLASS_NAME, level, message, t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void processLoggerForDomain(Log4jLogger logger) {
/*  66 */     ILbsConsoleDomainRegistry ms_DomainRegistry = Log4JConfigurator.getDomainRegistry();
/*  67 */     int domainId = (ms_DomainRegistry == null) ? 
/*  68 */       0 : 
/*  69 */       ms_DomainRegistry.getDomainId();
/*  70 */     ILbsAppender[] appenders = logger.getAppenders();
/*  71 */     for (int i = 0; i < appenders.length; i++) {
/*     */       
/*  73 */       ILbsAppender appender = appenders[i];
/*  74 */       appender.setDomainId(domainId);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String processUserName(String userName) {
/*  80 */     if (Log4JConfigurator.getDomainRegistry() != null)
/*  81 */       userName = String.valueOf(Log4JConfigurator.getDomainRegistry().getDomainId()) + "." + userName; 
/*  82 */     return userName;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean isEnabledFor(LbsLevel level) {
/*  87 */     if (ms_Logger != null && SESSION_LOGGER_ENABLED) {
/*     */       Log4jLogger logger;
/*  89 */       RemoteMethodRequestDetails methodCallDetails = RemoteMethodRequestDetails.getMethodCallDetails();
/*     */       String userName;
/*  91 */       if (methodCallDetails == null || (userName = methodCallDetails.userName) == null || userName.length() == 0) {
/*  92 */         return ms_Logger.internalIsEnabledFor(level);
/*     */       }
/*     */ 
/*     */       
/*  96 */       synchronized (ms_CachedLoggers) {
/*     */         
/*  98 */         userName = processUserName(userName);
/*  99 */         logger = ms_CachedLoggers.get(userName);
/* 100 */         if (logger == null) {
/*     */           
/* 102 */           int defaultBufLength = "Profiler".length() + userName.length() + 1;
/*     */           
/* 104 */           StringBuilder buf = getLoggerName(userName, defaultBufLength);
/* 105 */           logger = (Log4jLogger)ms_Logger.internalGetLogger(buf.toString());
/* 106 */           ms_CachedLoggers.put(userName, logger);
/*     */         } 
/*     */       } 
/* 109 */       return logger.internalIsEnabledFor(level);
/*     */     } 
/*     */     
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static StringBuilder getLoggerName(String userName, int defaultBufLength) {
/* 117 */     StringBuilder buf = new StringBuilder(defaultBufLength);
/* 118 */     buf.append("Profiler");
/* 119 */     buf.append('.');
/* 120 */     buf.append(userName);
/* 121 */     return buf;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\JLbsProfilerLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */