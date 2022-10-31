/*    */ package org.apache.logging.log4j.core.config;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.apache.logging.log4j.Level;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ import org.apache.logging.log4j.message.Message;
/*    */ import org.apache.logging.log4j.util.Supplier;
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
/*    */ public class DefaultReliabilityStrategy
/*    */   implements ReliabilityStrategy, LocationAwareReliabilityStrategy
/*    */ {
/*    */   private final LoggerConfig loggerConfig;
/*    */   
/*    */   public DefaultReliabilityStrategy(LoggerConfig loggerConfig) {
/* 36 */     this.loggerConfig = Objects.<LoggerConfig>requireNonNull(loggerConfig, "loggerConfig is null");
/*    */   }
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
/*    */   public void log(Supplier<LoggerConfig> reconfigured, String loggerName, String fqcn, Marker marker, Level level, Message data, Throwable t) {
/* 49 */     this.loggerConfig.log(loggerName, fqcn, marker, level, data, t);
/*    */   }
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
/*    */   public void log(Supplier<LoggerConfig> reconfigured, String loggerName, String fqcn, StackTraceElement location, Marker marker, Level level, Message data, Throwable t) {
/* 63 */     this.loggerConfig.log(loggerName, fqcn, location, marker, level, data, t);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void log(Supplier<LoggerConfig> reconfigured, LogEvent event) {
/* 74 */     this.loggerConfig.log(event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoggerConfig getActiveLoggerConfig(Supplier<LoggerConfig> next) {
/* 86 */     return this.loggerConfig;
/*    */   }
/*    */   
/*    */   public void afterLogEvent() {}
/*    */   
/*    */   public void beforeStopAppenders() {}
/*    */   
/*    */   public void beforeStopConfiguration(Configuration configuration) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\config\DefaultReliabilityStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */