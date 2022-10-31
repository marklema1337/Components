/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.logging.log4j.core.appender.ConsoleAppender;
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
/*     */ public class LbsAppenderFactory
/*     */ {
/*     */   private static final String SYSTEM_OUT = "System.out";
/*     */   private static final String SYSTEM_ERR = "System.err";
/*     */   
/*     */   public static ILbsAppender createConsoleAppender(String appenderID, LbsLayout layout, boolean systemErr) {
/*  23 */     return createConsoleAppender(appenderID, -1, layout, systemErr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsAppender createConsoleAppender(String appenderID, int domainId, LbsLayout layout, boolean systemErr) {
/*  31 */     if (appenderID == null) {
/*  32 */       throw new IllegalArgumentException("appenderID cannot be null!");
/*     */     }
/*  34 */     LbsConsoleAppender appender = new LbsConsoleAppender(appenderID, layout.getLayout(), systemErr ? 
/*  35 */         ConsoleAppender.Target.SYSTEM_ERR : 
/*  36 */         ConsoleAppender.Target.SYSTEM_OUT);
/*  37 */     appender.setDomainId(domainId);
/*  38 */     appender.start();
/*  39 */     return appender;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createAppender(ConsoleAppenderProps props) {
/*  44 */     LbsLayout layout = LbsLayoutFactory.createLayout(props.getFormat());
/*  45 */     return createConsoleAppender(props.getName(), props.getDomainId(), layout, props.isSystemErr());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsAppender createRollingFileAppender(String appenderID, LbsLayout layout, String filePath, int maxNumFiles, int maxSizeOfAFile) throws IOException {
/*  51 */     return createRollingFileAppender(appenderID, -1, layout, filePath, maxNumFiles, maxSizeOfAFile);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsAppender createRollingFileAppender(String appenderID, int domainId, LbsLayout layout, String filePath, int maxNumFiles, int maxSizeOfAFile) throws IOException {
/*  57 */     return createRollingFileAppender(appenderID, domainId, layout, filePath, maxNumFiles, maxSizeOfAFile, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsAppender createRollingFileAppender(String appenderID, int domainId, LbsLayout layout, String filePath, int maxNumFiles, int maxSizeOfAFile, boolean isRelative, String append) throws IOException {
/*  63 */     if (appenderID == null) {
/*  64 */       throw new IllegalArgumentException("appenderID cannot be null!");
/*     */     }
/*  66 */     if (filePath == null || filePath.length() == 0) {
/*  67 */       throw new IllegalArgumentException("filePath cannot be empty!");
/*     */     }
/*  69 */     if (layout == null) {
/*  70 */       throw new IllegalArgumentException("layout cannot be null, create a layout using #createLayout method");
/*     */     }
/*  72 */     LbsRollingFileAppender fileAppender = new LbsRollingFileAppender(appenderID, layout.getLayout(), filePath, isRelative, 
/*  73 */         maxSizeOfAFile, maxNumFiles, append);
/*  74 */     fileAppender.setDomainId(domainId);
/*  75 */     fileAppender.start();
/*  76 */     return fileAppender;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createAppender(RollingFileAppenderProps props) throws IOException {
/*  81 */     LbsLayout layout = LbsLayoutFactory.createLayout(props.getFormat());
/*  82 */     return createRollingFileAppender(props.getName(), props.getDomainId(), layout, props.getLogFilePath(), 
/*  83 */         props.getMaxNumFiles(), props.getMaxSizeOfAFile());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsAppender createSocketAppender(String appenderID, String remoteServerAddress, int remoteServerPort, boolean includeLineInfo) {
/*  89 */     return createSocketAppender(appenderID, -1, remoteServerAddress, remoteServerPort, includeLineInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ILbsAppender createSocketAppender(String appenderID, int domainId, String remoteServerAddress, int remoteServerPort, boolean includeLineInfo) {
/*  95 */     if (appenderID == null) {
/*  96 */       throw new IllegalArgumentException("appenderID cannot be null!");
/*     */     }
/*  98 */     if (remoteServerAddress == null || remoteServerAddress.length() == 0) {
/*  99 */       throw new IllegalArgumentException("remoteServerAddress cannot be empty!");
/*     */     }
/* 101 */     LbsSocketAppender socketAppender = new LbsSocketAppender(appenderID, remoteServerAddress, remoteServerPort);
/* 102 */     socketAppender.setDomainId(domainId);
/* 103 */     socketAppender.start();
/* 104 */     return socketAppender;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createAppender(SocketAppenderProps props) {
/* 109 */     return createSocketAppender(props.getName(), props.getDomainId(), props.getRemoteServerAddress(), 
/* 110 */         props.getRemoteServerPort(), props.isIncludeLineInfo());
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createSocketHubAppender(String appenderID, int domainId, int port, boolean includeLineInfo) {
/* 115 */     if (appenderID == null) {
/* 116 */       throw new IllegalArgumentException("appenderID cannot be null!");
/*     */     }
/* 118 */     LbsSocketHubAppender socketHubAppender = new LbsSocketHubAppender(appenderID, port, String.valueOf(includeLineInfo));
/* 119 */     socketHubAppender.setDomainId(domainId);
/* 120 */     socketHubAppender.start();
/* 121 */     return socketHubAppender;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createAppender(SocketHubAppenderProps props) {
/* 126 */     return createSocketHubAppender(props.getName(), props.getDomainId(), props.getPort(), props.isIncludeLineInfo());
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized void destroyAppender(ILbsAppender appender) {
/* 131 */     if (appender == null)
/*     */       return; 
/* 133 */     ILbsConsole[] loggers = LbsConsoleHelper.getAttachedLoggers(appender.getName());
/* 134 */     for (int i = 0; i < loggers.length; i++) {
/* 135 */       loggers[i].removeAppender(appender.getName());
/*     */     }
/* 137 */     appender.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createFluentdAppender(String appenderID, String servers, String applicationName) {
/* 142 */     return createFluentdAppender(appenderID, -1, servers, applicationName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createFluentdAppender(String appenderID, int domainId, String servers, String applicationName) {
/* 147 */     if (appenderID == null) {
/* 148 */       throw new IllegalArgumentException("appenderID cannot be null!");
/*     */     }
/* 150 */     if (servers == null || servers.length() == 0) {
/* 151 */       throw new IllegalArgumentException("ServerAddress cannot be empty!");
/*     */     }
/* 153 */     LbsFluentdAppender fluentdAppender = new LbsFluentdAppender(appenderID, servers, applicationName);
/* 154 */     fluentdAppender.setDomainId(domainId);
/* 155 */     fluentdAppender.start();
/* 156 */     return fluentdAppender;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsAppender createAppender(FluentdAppenderProps props) {
/* 161 */     return createFluentdAppender(props.getName(), props.getDomainId(), props.getServers(), props.getApplicationName());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsAppenderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */