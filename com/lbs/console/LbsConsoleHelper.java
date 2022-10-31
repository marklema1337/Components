/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
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
/*     */ public class LbsConsoleHelper
/*     */ {
/*  21 */   private static String ms_ApplicationPath = System.getProperty("user.dir");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsConsole[] getAttachedLoggers(String appenderName) {
/*  29 */     ArrayList<LbsConsole> loggers = new ArrayList();
/*  30 */     ArrayList<String> knownLoggerNames = LbsConsole.getKnownLoggerNames();
/*  31 */     for (int i = 0; i < knownLoggerNames.size(); i++) {
/*     */       
/*  33 */       String loggerName = knownLoggerNames.get(i);
/*  34 */       LbsConsole logger = LbsConsole.getLogger(loggerName);
/*  35 */       ILbsAppender appender = logger.getAppender(appenderName);
/*  36 */       if (appender != null)
/*  37 */         loggers.add(logger); 
/*     */     } 
/*  39 */     return loggers.<ILbsConsole>toArray(new ILbsConsole[loggers.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender getAppenderByName(String appenderName) {
/*  44 */     ArrayList<String> knownLoggerNames = LbsConsole.getKnownLoggerNames();
/*  45 */     for (int i = 0; i < knownLoggerNames.size(); i++) {
/*     */       
/*  47 */       String loggerName = knownLoggerNames.get(i);
/*  48 */       LbsConsole logger = LbsConsole.getLogger(loggerName);
/*  49 */       ILbsAppender appender = logger.getAppender(appenderName);
/*  50 */       if (appender != null)
/*  51 */         return appender; 
/*     */     } 
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LoggerProps[] constructLoggerProps() {
/*  58 */     ArrayList<String> knownLoggerNames = LbsConsole.getKnownLoggerNames();
/*  59 */     LoggerProps[] loggerProps = new LoggerProps[knownLoggerNames.size()];
/*  60 */     Hashtable<Object, Object> appenderCache = new Hashtable<>();
/*  61 */     for (int i = 0; i < knownLoggerNames.size(); i++) {
/*     */       
/*  63 */       String loggerName = knownLoggerNames.get(i);
/*  64 */       loggerProps[i] = getLoggerProps(loggerName, appenderCache);
/*     */     } 
/*  66 */     return loggerProps;
/*     */   }
/*     */ 
/*     */   
/*     */   private static LoggerProps getLoggerProps(String loggerName, Hashtable<String, AppenderProps> appenderCache) {
/*  71 */     LbsConsole logger = LbsConsole.getLogger(loggerName);
/*  72 */     LoggerProps props = new LoggerProps(logger.getName(), logger.isAdditive(), logger.getLevel2());
/*  73 */     ILbsAppender[] appenders = logger.getAppenders();
/*  74 */     for (int j = 0; j < appenders.length; j++) {
/*     */       
/*  76 */       AppenderProps appenderProps = null;
/*  77 */       if (appenderCache != null)
/*  78 */         appenderProps = (AppenderProps)appenderCache.get(appenders[j].getName()); 
/*  79 */       if (appenderProps == null) {
/*     */         
/*  81 */         appenderProps = appenders[j].constructProps();
/*  82 */         if (appenderCache != null)
/*  83 */           appenderCache.put(appenders[j].getName(), appenderProps); 
/*     */       } 
/*  85 */       props.addAppender(appenderProps);
/*     */     } 
/*  87 */     return props;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LoggerProps getLoggerProps(String loggerName) {
/*  92 */     return getLoggerProps(loggerName, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static AppenderProps[] constructKnownAppenderProps() {
/*  97 */     ArrayList<String> knownLoggerNames = LbsConsole.getKnownLoggerNames();
/*  98 */     Hashtable<Object, Object> appenderCache = new Hashtable<>();
/*  99 */     for (int i = 0; i < knownLoggerNames.size(); i++) {
/*     */       
/* 101 */       String loggerName = knownLoggerNames.get(i);
/* 102 */       LbsConsole logger = LbsConsole.getLogger(loggerName);
/*     */       
/* 104 */       ILbsAppender[] appenders = logger.getAppenders();
/* 105 */       for (int j = 0; j < appenders.length; j++) {
/*     */         
/* 107 */         AppenderProps props = (AppenderProps)appenderCache.get(appenders[j].getName());
/* 108 */         if (props == null) {
/*     */           
/* 110 */           props = appenders[j].constructProps();
/* 111 */           appenderCache.put(appenders[j].getName(), props);
/*     */         } 
/*     */       } 
/*     */     } 
/* 115 */     List<?> valueList = Arrays.asList(appenderCache.values().toArray(new Object[appenderCache.values().size()]));
/* 116 */     Collections.sort(valueList, new Comparator()
/*     */         {
/*     */           public int compare(Object o1, Object o2)
/*     */           {
/* 120 */             AppenderProps a1 = (AppenderProps)o1;
/* 121 */             AppenderProps a2 = (AppenderProps)o2;
/* 122 */             return a1.getName().compareTo(a2.getName());
/*     */           }
/*     */         });
/* 125 */     return valueList.<AppenderProps>toArray(new AppenderProps[valueList.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setAppPath(String appPath) {
/* 130 */     ms_ApplicationPath = appPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getApplicationPath() {
/* 135 */     if (ms_ApplicationPath == null)
/* 136 */       throw new RuntimeException("Application path is not initialized, please call " + LbsConsoleHelper.class.getName() + 
/* 137 */           ".setApplicationPath() first"); 
/* 138 */     return ms_ApplicationPath;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsConsoleHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */