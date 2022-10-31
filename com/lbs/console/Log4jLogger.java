/*     */ package com.lbs.console;
/*     */ 
/*     */ import com.lbs.profiler.helper.RemoteMethodRequestDetails;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.Logger;
/*     */ import org.apache.logging.log4j.core.LoggerContext;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.LoggerConfig;
/*     */ import org.apache.logging.log4j.status.StatusLogger;
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
/*     */ class Log4jLogger
/*     */   extends LbsConsole
/*     */   implements ILog4jConsole
/*     */ {
/*     */   private Logger m_Log4jLogger;
/*     */   private static boolean ms_RootLoggerInitialized = false;
/*  31 */   private static final String CLASS_NAME = Log4jLogger.class.getName();
/*     */ 
/*     */   
/*     */   Log4jLogger() {
/*  35 */     this.m_Log4jLogger = (Logger)LogManager.getLogger("Console");
/*  36 */     if (!ms_RootLoggerInitialized) {
/*     */       
/*  38 */       this.m_Log4jLogger.setAdditive(false);
/*  39 */       StatusLogger.getLogger().setLevel(Level.OFF);
/*     */ 
/*     */ 
/*     */       
/*  43 */       ms_RootLoggerInitialized = true;
/*  44 */       overrideLevelConstants();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Log4jLogger(String name) {
/*  50 */     if (name == null || name.length() == 0) {
/*  51 */       this.m_Log4jLogger = (Logger)LogManager.getLogger("Console");
/*     */     } else {
/*  53 */       this.m_Log4jLogger = (Logger)LogManager.getLogger("Console." + name);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void overrideLevelConstants() {
/*  58 */     LbsLevel.OFF.setLevel(Level.OFF, Level.OFF.intLevel());
/*  59 */     LbsLevel.FATAL.setLevel(Level.FATAL, Level.FATAL.intLevel());
/*  60 */     LbsLevel.ERROR.setLevel(Level.ERROR, Level.ERROR.intLevel());
/*  61 */     LbsLevel.CLOUDINFO.setLevel(Level.forName("CLOUDINFO", 250), 250);
/*  62 */     LbsLevel.WARN.setLevel(Level.WARN, Level.WARN.intLevel());
/*  63 */     LbsLevel.INFO.setLevel(Level.INFO, Level.INFO.intLevel());
/*  64 */     LbsLevel.DEBUG.setLevel(Level.DEBUG, Level.DEBUG.intLevel());
/*     */     
/*     */     try {
/*  67 */       LbsLevel.TRACE.setLevel(Level.TRACE, Level.TRACE.intLevel());
/*     */     }
/*  69 */     catch (Exception t) {
/*     */ 
/*     */ 
/*     */       
/*  73 */       LbsLevel.TRACE.setLevel(Level.ALL, Level.ALL.intLevel());
/*     */     } 
/*  75 */     LbsLevel.ALL.setLevel(Level.ALL, Level.ALL.intLevel());
/*     */   }
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
/*     */ 
/*     */   
/*     */   public void addAppender(ILbsAppender appender) {
/* 113 */     synchronized (this.m_Log4jLogger) {
/*     */       
/* 115 */       if (this.m_Log4jLogger.getAppenders().containsKey(appender.getName())) {
/* 116 */         this.m_Log4jLogger.removeAppender((Appender)appender);
/*     */       }
/* 118 */       this.m_Log4jLogger.addAppender((Appender)appender);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsAppender removeAppender(String appenderName) {
/* 125 */     synchronized (this.m_Log4jLogger) {
/*     */       
/* 127 */       ILbsAppender appender = (ILbsAppender)this.m_Log4jLogger.getAppenders().get(appenderName);
/* 128 */       if (appender != null) {
/*     */         
/* 130 */         this.m_Log4jLogger.removeAppender((Appender)appender);
/* 131 */         return appender;
/*     */       } 
/* 133 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllAppenders() {
/* 139 */     for (String appName : this.m_Log4jLogger.getAppenders().keySet()) {
/* 140 */       this.m_Log4jLogger.removeAppender((Appender)this.m_Log4jLogger.getAppenders().get(appName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsAppender getAppender(String appenderName) {
/* 148 */     return (ILbsAppender)this.m_Log4jLogger.getAppenders().get(appenderName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsAppender[] getAppenders() {
/* 157 */     ILbsAppender[] a = new ILbsAppender[this.m_Log4jLogger.getAppenders().size()];
/* 158 */     return (ILbsAppender[])this.m_Log4jLogger.getAppenders().values().toArray((Object[])a);
/*     */   }
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
/*     */ 
/*     */   
/*     */   public LbsConsole internalGetLogger(String name) {
/* 251 */     return new Log4jLogger(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsConsole internalGetRootLogger() {
/* 256 */     return new Log4jLogger();
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Object message) {
/* 261 */     trace(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Object message, Throwable t) {
/* 266 */     log(LbsLevel.TRACE, message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(Object message) {
/* 272 */     debug(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Object message, Throwable t) {
/* 277 */     log(LbsLevel.DEBUG, message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(Object message) {
/* 283 */     info(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Object message, Throwable t) {
/* 288 */     log(LbsLevel.INFO, message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(Object message) {
/* 294 */     warn(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Object message, Throwable t) {
/* 299 */     log(LbsLevel.WARN, message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Object message) {
/* 305 */     error(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Object message, Throwable t) {
/* 310 */     log(LbsLevel.ERROR, message, t);
/* 311 */     write2StatusLog(message, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cloudinfo(Object message) {
/* 316 */     cloudinfo(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cloudinfo(Object message, Throwable t) {
/* 321 */     log(LbsLevel.CLOUDINFO, message, t);
/* 322 */     write2StatusLog(message, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Object message) {
/* 327 */     fatal(message, (Throwable)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Object message, Throwable t) {
/* 332 */     log(LbsLevel.FATAL, message, t);
/* 333 */     write2StatusLog(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Object message) {
/* 341 */     log(level, message, (Throwable)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Object message, Throwable t) {
/* 349 */     if (level == null) {
/*     */       return;
/*     */     }
/* 352 */     if (SIMPLIFY_STACK_TRACES) {
/* 353 */       LbsThrowableHandler.processThrowable(t);
/*     */     }
/* 355 */     if (t instanceof SilentException)
/*     */     {
/* 357 */       level = LbsLevel.TRACE;
/*     */     }
/*     */     
/* 360 */     RemoteMethodRequestDetails methodCallDetails = RemoteMethodRequestDetails.getMethodCallDetails();
/*     */     String userName;
/* 362 */     if (methodCallDetails == null || (userName = methodCallDetails.userName) == null || userName.length() == 0) {
/* 363 */       this.m_Log4jLogger.log((Level)level.getLevel(), message, t);
/*     */     } else {
/* 365 */       this.m_Log4jLogger.log((Level)level.getLevel(), "Username : " + userName + " - " + message, t);
/*     */     } 
/*     */     
/* 368 */     JLbsProfilerLogger.log(level, message, t);
/*     */   }
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
/*     */   protected void internalLog(String callerFQCN, LbsLevel level, Object message, Throwable t) {
/* 382 */     if (level == null) {
/*     */       return;
/*     */     }
/* 385 */     if (SIMPLIFY_STACK_TRACES) {
/* 386 */       LbsThrowableHandler.processThrowable(t);
/*     */     }
/* 388 */     if (t instanceof SilentException)
/*     */     {
/* 390 */       level = LbsLevel.TRACE;
/*     */     }
/* 392 */     this.m_Log4jLogger.log((Level)level.getLevel(), MarkerManager.getMarker(callerFQCN), message, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLevel getEffectiveLevel() {
/* 397 */     Level level = this.m_Log4jLogger.getLevel();
/* 398 */     if (level != null) {
/* 399 */       return new LbsLevel(level, level.intLevel());
/*     */     }
/* 401 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLevel getLevel2() {
/* 406 */     return getEffectiveLevel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel2(LbsLevel level) {
/* 415 */     Configuration configuration = this.m_Log4jLogger.getContext().getConfiguration();
/* 416 */     Level level2 = (level == null) ? null : (Level)level.getLevel();
/* 417 */     if (!configuration.getLoggers().containsKey(this.m_Log4jLogger.getName())) {
/* 418 */       LoggerConfig loggerConfig = new LoggerConfig(this.m_Log4jLogger.getName(), level2, this.m_Log4jLogger.isAdditive());
/* 419 */       configuration.addLogger(this.m_Log4jLogger.getName(), loggerConfig);
/*     */     } 
/* 421 */     configuration.getLoggerConfig(this.m_Log4jLogger.getName()).setLevel(level2);
/* 422 */     this.m_Log4jLogger.getContext().updateLoggers();
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration internalGetLoggerNames() {
/*     */     class EnumerationWrapper
/*     */       implements Enumeration
/*     */     {
/*     */       private Enumeration m_InternalEnum;
/*     */       
/*     */       public EnumerationWrapper(Enumeration enumeration) {
/* 433 */         this.m_InternalEnum = enumeration;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean hasMoreElements() {
/* 438 */         return this.m_InternalEnum.hasMoreElements();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Object nextElement() {
/* 444 */         return ((Logger)this.m_InternalEnum.nextElement()).getName();
/*     */       }
/*     */     };
/*     */     
/* 448 */     return new EnumerationWrapper(Collections.enumeration(((LoggerContext)LogManager.getContext(false)).getLoggers()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabledFor2(LbsLevel level) {
/* 453 */     return !(!internalIsEnabledFor(level) && !JLbsProfilerLogger.isEnabledFor(level));
/*     */   }
/*     */ 
/*     */   
/*     */   boolean internalIsEnabledFor(LbsLevel level) {
/* 458 */     return this.m_Log4jLogger.isEnabled((Level)level.getLevel());
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdditivity(boolean additive) {
/* 475 */     if (isRootLogger() && additive)
/* 476 */       throw new RuntimeException("Sorry, additivity of root logger cannot be changed!"); 
/* 477 */     this.m_Log4jLogger.setAdditive(additive);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditive() {
/* 485 */     return this.m_Log4jLogger.isAdditive();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRootLogger() {
/* 490 */     return this.m_Log4jLogger.getName().equals("Console");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 495 */     String internalName = this.m_Log4jLogger.getName();
/* 496 */     return stripName(internalName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 502 */     StringBuilder message = new StringBuilder(String.valueOf(getName()) + " [" + getEffectiveLevel() + "] Additive: " + isAdditive() + " \nAppenders:\n");
/* 503 */     ILbsAppender[] attachedAppenders = getAppenders();
/* 504 */     for (int i = 0; i < attachedAppenders.length; i++) {
/* 505 */       message.append(String.valueOf(attachedAppenders[i].toString()) + "\n\n");
/*     */     }
/* 507 */     return message.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\Log4jLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */