/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Enumeration;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.logging.ConsoleHandler;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogManager;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.logging.SimpleFormatter;
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
/*     */ public class LbsConsole
/*     */   implements ILbsConsole
/*     */ {
/*     */   public static boolean SIMPLIFY_STACK_TRACES = true;
/*     */   protected static final String ROOT_LOGGER_NAME = "Console";
/*  41 */   public static final LbsLevel LEVEL_OFF = LbsLevel.OFF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static final LbsLevel LEVEL_FATAL = LbsLevel.FATAL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final LbsLevel LEVEL_ERROR = LbsLevel.ERROR;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final LbsLevel LEVEL_CLOUDINFO = LbsLevel.CLOUDINFO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public static final LbsLevel LEVEL_WARN = LbsLevel.WARN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final LbsLevel LEVEL_INFO = LbsLevel.INFO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static final LbsLevel LEVEL_DEBUG = LbsLevel.DEBUG;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static final LbsLevel LEVEL_TRACE = LbsLevel.TRACE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final LbsLevel LEVEL_ALL = LbsLevel.ALL;
/*     */   
/*  94 */   private static ILog4jConsole ms_log4jLogger = null;
/*     */   
/*     */   private static ILbsStatusLogger ms_StatusLogger;
/*     */   private static boolean ms_Initialized = false;
/*     */   private static boolean ms_RootLoggerInitialized = false;
/*  99 */   private static WeakHashMap<Class<?>, LbsConsole> ms_Loggers = new WeakHashMap<>();
/*     */   
/*     */   private Logger m_Logger;
/*     */   
/*     */   static {
/* 104 */     initializeLogger();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   LbsConsole() {}
/*     */ 
/*     */   
/*     */   LbsConsole(String name) {
/* 113 */     if (name == null || name.length() == 0) {
/* 114 */       this.m_Logger = Logger.getLogger("Console");
/*     */     } else {
/* 116 */       this.m_Logger = Logger.getLogger("Console." + name);
/* 117 */     }  internalInitialize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void overrideLevelConstants() {
/* 126 */     LbsLevel.OFF.setLevel(LevelExt.OFF, LevelExt.OFF.intValue());
/* 127 */     LbsLevel.FATAL.setLevel(LevelExt.FATAL, LevelExt.FATAL.intValue());
/* 128 */     LbsLevel.ERROR.setLevel(LevelExt.ERROR, LevelExt.ERROR.intValue());
/* 129 */     LbsLevel.CLOUDINFO.setLevel(LevelExt.CLOUDINFO, LevelExt.CLOUDINFO.intValue());
/* 130 */     LbsLevel.WARN.setLevel(LevelExt.WARN, LevelExt.WARN.intValue());
/* 131 */     LbsLevel.INFO.setLevel(LevelExt.INFO, LevelExt.INFO.intValue());
/* 132 */     LbsLevel.DEBUG.setLevel(LevelExt.DEBUG, LevelExt.DEBUG.intValue());
/* 133 */     LbsLevel.TRACE.setLevel(LevelExt.TRACE, LevelExt.TRACE.intValue());
/* 134 */     LbsLevel.ALL.setLevel(LevelExt.ALL, LevelExt.ALL.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   private void internalInitialize() {
/* 139 */     if (!ms_RootLoggerInitialized) {
/*     */       
/* 141 */       Handler handler = new ConsoleHandlerExt();
/* 142 */       Formatter formatter = new SimpleFormatterExt(null);
/* 143 */       handler.setFormatter(formatter);
/* 144 */       Logger logger = Logger.getLogger("Console");
/* 145 */       logger.setUseParentHandlers(false);
/* 146 */       Handler[] handlers = logger.getHandlers();
/* 147 */       for (int i = 0; i < handlers.length; i++)
/* 148 */         logger.removeHandler(handlers[i]); 
/* 149 */       logger.addHandler(handler);
/* 150 */       ms_RootLoggerInitialized = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initializeLogger() {
/* 159 */     if (!ms_Initialized) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 164 */         Class<?> clazz = Class.forName("com.lbs.console.Log4jLogger");
/* 165 */         ms_log4jLogger = (ILog4jConsole)clazz.newInstance();
/*     */         
/*     */         try {
/* 168 */           clazz = Class.forName("com.lbs.platform.server.LbsStatusLogger");
/* 169 */           ms_StatusLogger = (ILbsStatusLogger)clazz.newInstance();
/*     */         }
/* 171 */         catch (Throwable innerExc) {
/*     */           
/* 173 */           ms_StatusLogger = null;
/*     */         }
/*     */       
/* 176 */       } catch (Throwable e) {
/*     */         
/* 178 */         ms_Initialized = true;
/* 179 */         ms_log4jLogger = null;
/* 180 */         overrideLevelConstants();
/* 181 */         getLogger(LbsConsole.class).warn("Advanced logging will not be available! Using simple logger");
/*     */       }
/*     */       finally {
/*     */         
/* 185 */         ms_Initialized = true;
/* 186 */         getRootLogger().setLevel2(null);
/* 187 */         getLogger("net.sf.ehcache").setLevel2(LbsLevel.OFF);
/* 188 */         getRootLogger().trace("LbsConsole initialized");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void write2StatusLog(Object message) {
/* 195 */     if (ms_StatusLogger != null) {
/* 196 */       ms_StatusLogger.error(message);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void write2StatusLog(Object message, Throwable t) {
/* 201 */     if (ms_StatusLogger != null) {
/* 202 */       ms_StatusLogger.error(t, message);
/*     */     }
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
/*     */   public static LbsConsole getLogger(String name) {
/* 268 */     if (ms_log4jLogger != null) {
/* 269 */       return ms_log4jLogger.internalGetLogger(name);
/*     */     }
/* 271 */     return new LbsConsole(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LbsConsole getLogger(Class<?> clazz) {
/* 280 */     LbsConsole logger = ms_Loggers.get(clazz);
/* 281 */     if (logger != null) {
/* 282 */       return logger;
/*     */     }
/*     */     try {
/* 285 */       if (ms_log4jLogger != null) {
/* 286 */         logger = ms_log4jLogger.internalGetLogger(clazz.getName());
/*     */       } else {
/* 288 */         logger = new LbsConsole(clazz.getName());
/*     */       } 
/*     */     } finally {
/*     */       
/* 292 */       synchronized (ms_Loggers) {
/*     */         
/* 294 */         ms_Loggers.put(clazz, logger);
/*     */       } 
/*     */     } 
/* 297 */     return logger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LbsConsole getRootLogger() {
/* 305 */     if (ms_log4jLogger != null) {
/* 306 */       return ms_log4jLogger.internalGetRootLogger();
/*     */     }
/* 308 */     return new LbsConsole(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<String> getKnownLoggerNames() {
/*     */     Enumeration<?> knownLoggerNames;
/* 314 */     if (ms_log4jLogger != null) {
/* 315 */       knownLoggerNames = ms_log4jLogger.internalGetLoggerNames();
/*     */     } else {
/* 317 */       knownLoggerNames = LogManager.getLogManager().getLoggerNames();
/*     */     } 
/* 319 */     ArrayList<String> list = new ArrayList<>();
/* 320 */     while (knownLoggerNames.hasMoreElements()) {
/*     */       
/* 322 */       Object nextElement = knownLoggerNames.nextElement();
/* 323 */       String loggerName = (String)nextElement;
/* 324 */       if (loggerName.startsWith("Console") && !loggerName.equals("Console"))
/* 325 */         list.add(loggerName.replaceFirst("Console\\.", "")); 
/*     */     } 
/* 327 */     list.add("");
/*     */     
/* 329 */     Collections.sort(list, new Comparator<String>()
/*     */         {
/*     */           public int compare(String s1, String s2)
/*     */           {
/* 333 */             return s1.compareToIgnoreCase(s2);
/*     */           }
/*     */         });
/* 336 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Object message) {
/* 341 */     if (message == null)
/* 342 */       message = ""; 
/* 343 */     if (this.m_Logger == null)
/* 344 */       System.out.println("m_Logger null"); 
/* 345 */     if (LevelExt.TRACE == null)
/* 346 */       System.out.println("LevelExt.TRACE null"); 
/* 347 */     if (this.m_Logger != null && LevelExt.TRACE != null)
/*     */     {
/* 349 */       this.m_Logger.log(LevelExt.TRACE, message.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public void trace(Object message, Throwable t) {
/* 354 */     if (message == null)
/* 355 */       message = ""; 
/* 356 */     this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Throwable t) {
/* 361 */     if (t != null) {
/* 362 */       trace(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void debug(Object message) {
/* 367 */     if (message == null)
/* 368 */       message = ""; 
/* 369 */     this.m_Logger.log(LevelExt.DEBUG, message.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(LogRecord lr) {
/* 374 */     this.m_Logger.log(lr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(Level level, String sourceClass, String sourceMethod, String msg) {
/* 379 */     if (this.m_Logger != null) {
/* 380 */       this.m_Logger.logp(level, sourceClass, sourceMethod, msg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void debug(Object message, Throwable t) {
/* 385 */     if (message == null)
/* 386 */       message = ""; 
/* 387 */     if (t instanceof SilentException) {
/* 388 */       this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } else {
/* 390 */       this.m_Logger.log(LevelExt.DEBUG, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Throwable t) {
/* 396 */     if (t != null) {
/* 397 */       debug(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void info(Object message) {
/* 402 */     if (message == null)
/* 403 */       message = ""; 
/* 404 */     this.m_Logger.log(LevelExt.INFO, message.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Object message, Throwable t) {
/* 409 */     if (message == null)
/* 410 */       message = ""; 
/* 411 */     if (t instanceof SilentException) {
/* 412 */       this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } else {
/* 414 */       this.m_Logger.log(LevelExt.INFO, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void info(Throwable t) {
/* 419 */     if (t != null) {
/* 420 */       info(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void warn(Object message) {
/* 425 */     if (message == null)
/* 426 */       message = ""; 
/* 427 */     this.m_Logger.log(LevelExt.WARN, message.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Object message, Throwable t) {
/* 432 */     if (message == null)
/* 433 */       message = ""; 
/* 434 */     if (t instanceof SilentException) {
/* 435 */       this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } else {
/* 437 */       this.m_Logger.log(LevelExt.WARN, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void warn(Throwable t) {
/* 442 */     if (t != null) {
/* 443 */       warn(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void error(Object message) {
/* 448 */     if (message == null)
/* 449 */       message = ""; 
/* 450 */     this.m_Logger.log(LevelExt.ERROR, message.toString());
/* 451 */     write2StatusLog(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Object message, Throwable t) {
/* 457 */     if (message == null)
/* 458 */       message = ""; 
/* 459 */     if (t instanceof SilentException) {
/* 460 */       this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } else {
/* 462 */       this.m_Logger.log(LevelExt.ERROR, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/* 463 */     }  write2StatusLog(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Throwable t) {
/* 469 */     if (t != null) {
/* 470 */       error(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void cloudinfo(Object message) {
/* 475 */     if (message == null)
/* 476 */       message = ""; 
/* 477 */     this.m_Logger.log(LevelExt.CLOUDINFO, message.toString());
/* 478 */     write2StatusLog(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cloudinfo(Object message, Throwable t) {
/* 484 */     if (message == null)
/* 485 */       message = ""; 
/* 486 */     if (t instanceof SilentException) {
/* 487 */       this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } else {
/* 489 */       this.m_Logger.log(LevelExt.CLOUDINFO, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/* 490 */     }  write2StatusLog(message, t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cloudinfo(Throwable t) {
/* 496 */     if (t != null) {
/* 497 */       cloudinfo(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void fatal(Object message) {
/* 502 */     if (message == null)
/* 503 */       message = ""; 
/* 504 */     this.m_Logger.log(LevelExt.FATAL, message.toString());
/* 505 */     write2StatusLog(message.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Object message, Throwable t) {
/* 510 */     if (message == null)
/* 511 */       message = ""; 
/* 512 */     if (t instanceof SilentException) {
/* 513 */       this.m_Logger.log(LevelExt.TRACE, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */     } else {
/* 515 */       this.m_Logger.log(LevelExt.FATAL, String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/* 516 */     }  write2StatusLog(String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void fatal(Throwable t) {
/* 521 */     if (t != null) {
/* 522 */       fatal(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public void log(LbsLevel level, Object message) {
/* 527 */     if (level == null)
/*     */       return; 
/* 529 */     if (message == null)
/* 530 */       message = ""; 
/* 531 */     this.m_Logger.log((Level)level.getLevel(), message.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Object message, Throwable t) {
/* 536 */     if (level == null)
/*     */       return; 
/* 538 */     if (message == null)
/* 539 */       message = ""; 
/* 540 */     this.m_Logger.log((Level)level.getLevel(), String.valueOf(message.toString()) + "\n" + getStackTrace(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(LbsLevel level, Throwable t) {
/* 545 */     if (level != null && t != null) {
/* 546 */       log(level, t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   public LbsLevel getEffectiveLevel() {
/* 551 */     Logger parentLogger = this.m_Logger;
/* 552 */     Level level = null;
/* 553 */     while (level == null && parentLogger != null) {
/*     */       
/* 555 */       level = parentLogger.getLevel();
/* 556 */       parentLogger = this.m_Logger.getParent();
/*     */     } 
/* 558 */     if (level != null) {
/* 559 */       return new LbsLevel(level, level.intValue());
/*     */     }
/* 561 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLevel getLevel2() {
/* 566 */     Level level = this.m_Logger.getLevel();
/* 567 */     if (level != null) {
/* 568 */       return new LbsLevel(level, level.intValue());
/*     */     }
/* 570 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLevel2(LbsLevel level) {
/* 575 */     if (level == null) {
/* 576 */       this.m_Logger.setLevel(null);
/*     */     } else {
/* 578 */       this.m_Logger.setLevel((Level)level.getLevel());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEnabledFor2(LbsLevel level) {
/* 583 */     return this.m_Logger.isLoggable((Level)level.getLevel());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDebugEnabled() {
/* 588 */     return isEnabledFor2(LbsLevel.DEBUG);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInfoEnabled() {
/* 593 */     return isEnabledFor2(LbsLevel.INFO);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTraceEnabled() {
/* 598 */     return isEnabledFor2(LbsLevel.TRACE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWarnEnabled() {
/* 603 */     return isEnabledFor2(LbsLevel.WARN);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isErrorEnabled() {
/* 608 */     return isEnabledFor2(LbsLevel.ERROR);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCloudInfoEnabled() {
/* 613 */     return isEnabledFor2(LbsLevel.CLOUDINFO);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getStackTrace(Throwable t) {
/* 618 */     if (SIMPLIFY_STACK_TRACES) {
/* 619 */       LbsThrowableHandler.processThrowable(t);
/*     */     }
/* 621 */     StringBuilder buf = new StringBuilder();
/* 622 */     if (t.getMessage() != null)
/* 623 */       buf.append(String.valueOf(t.getMessage()) + "\n"); 
/* 624 */     StackTraceElement[] stackTrace = t.getStackTrace();
/* 625 */     for (int i = 0; i < stackTrace.length; i++) {
/*     */       
/* 627 */       buf.append(stackTrace[i].toString());
/* 628 */       if (i != stackTrace.length - 1)
/* 629 */         buf.append("\n"); 
/*     */     } 
/* 631 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LbsLevel getLevel() {
/* 640 */     return getRootLogger().getEffectiveLevel();
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
/*     */   public static void setLevel(LbsLevel level) {
/* 668 */     getRootLogger().setLevel2(level);
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
/*     */   public static boolean isEnabledFor(LbsLevel level) {
/* 680 */     return getRootLogger().isEnabledFor2(level);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEnabledFor(LbsLevel level, Class<?> clazz) {
/* 691 */     return getLogger(clazz).isEnabledFor2(level);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEnabledFor(LbsLevel level, String name) {
/* 700 */     return getLogger(name).isEnabledFor2(level);
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
/*     */   static class ConsoleHandlerExt
/*     */     extends ConsoleHandler
/*     */   {
/*     */     ConsoleHandler handlerErr;
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
/*     */     public ConsoleHandlerExt() {
/* 747 */       setLevel(LevelExt.ALL);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditive() {
/* 754 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdditivity(boolean additive) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAppender(ILbsAppender appender) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsAppender getAppender(String appenderName) {
/* 770 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsAppender[] getAppenders() {
/* 776 */     return new ILbsAppender[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllAppenders() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsAppender removeAppender(String appenderName) {
/* 787 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRootLogger() {
/* 792 */     return this.m_Logger.getName().equals("Console");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 797 */     String internalName = this.m_Logger.getName();
/* 798 */     return stripName(internalName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String stripName(String internalName) {
/* 803 */     int length = "Console".length();
/* 804 */     if (internalName.length() > length) {
/* 805 */       return internalName = internalName.substring(length + 1);
/*     */     }
/* 807 */     return internalName = internalName.substring(length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SimpleFormatterExt
/*     */     extends SimpleFormatter
/*     */   {
/*     */     private SimpleFormatterExt() {}
/*     */ 
/*     */     
/*     */     public synchronized String format(LogRecord record) {
/* 819 */       return String.valueOf(record.getMessage()) + "\n";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized void stopStatusLogger() {
/* 825 */     if (ms_StatusLogger != null) {
/*     */       
/* 827 */       ms_StatusLogger.stop();
/* 828 */       ms_StatusLogger = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void startStatusLogger() {
/* 835 */     if (ms_StatusLogger != null)
/* 836 */       ms_StatusLogger.start(); 
/*     */   }
/*     */   
/*     */   public synchronized void shutDown() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */