/*     */ package com.lbs.console;
/*     */ 
/*     */ import com.lbs.base.config.service.context.LbsCorrelationIdProvider;
/*     */ import com.lbs.base.config.service.context.LbsSpanIdProvider;
/*     */ import com.wywy.log4j.appender.FluencyConfig;
/*     */ import com.wywy.log4j.appender.Server;
/*     */ import com.wywy.log4j.appender.StaticField;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.AbstractAppender;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginElement;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginFactory;
/*     */ import org.apache.logging.log4j.core.layout.PatternLayout;
/*     */ import org.apache.logging.log4j.core.pattern.NameAbbreviator;
/*     */ import org.apache.logging.log4j.core.util.Booleans;
/*     */ import org.apache.logging.log4j.status.StatusLogger;
/*     */ import org.komamitsu.fluency.Fluency;
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
/*     */ @Plugin(name = "Fluency", category = "Core", elementType = "appender", printObject = true)
/*     */ public final class FluencyAppender
/*     */   extends AbstractAppender
/*     */ {
/*  51 */   private static final StatusLogger LOG = StatusLogger.getLogger();
/*  52 */   private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
/*  53 */   private final NameAbbreviator abbr = NameAbbreviator.getAbbreviator("1.");
/*     */   
/*     */   private boolean usePre26Abbreviate = false;
/*     */   
/*     */   private Method abbreviateMethod;
/*     */   
/*     */   private Fluency fluency;
/*     */   
/*     */   private Map<String, Object> parameters;
/*     */   
/*     */   private Map<String, String> staticFields;
/*     */   
/*     */   private FluencyAppender(String name, Map<String, Object> parameters, Map<String, String> staticFields, Server[] servers, FluencyConfig fluencyConfig, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
/*  66 */     super(name, filter, layout, ignoreExceptions);
/*     */     
/*  68 */     this.parameters = parameters;
/*  69 */     this.staticFields = staticFields;
/*     */ 
/*     */     
/*     */     try {
/*  73 */       this.fluency = makeFluency(servers, fluencyConfig);
/*  74 */       LOG.info("FluencyAppender initialized");
/*     */     }
/*  76 */     catch (IOException e) {
/*     */       
/*  78 */       LOG.error(e.getMessage());
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  83 */       this.abbreviateMethod = NameAbbreviator.class.getMethod("abbreviate", new Class[] { String.class });
/*  84 */       this.usePre26Abbreviate = true;
/*     */     }
/*  86 */     catch (NoSuchMethodException e) {
/*     */       
/*     */       try
/*     */       {
/*  90 */         this.abbreviateMethod = NameAbbreviator.class.getMethod("abbreviate", new Class[] { String.class, StringBuilder.class });
/*     */       }
/*  92 */       catch (NoSuchMethodException e1)
/*     */       {
/*  94 */         LOG.error(e.getMessage(), e);
/*     */       }
/*  96 */       catch (SecurityException e1)
/*     */       {
/*  98 */         LOG.error(e.getMessage(), e);
/*     */       }
/*     */     
/* 101 */     } catch (SecurityException e) {
/*     */       
/* 103 */       LOG.error(e.getMessage(), e);
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
/*     */   @PluginFactory
/*     */   public static FluencyAppender createAppender(@PluginAttribute("name") String name, @PluginAttribute("tag") String tag, @PluginAttribute("application") String application, @PluginAttribute("ignoreExceptions") String ignore, @PluginElement("StaticField") StaticField[] staticFields, @PluginElement("Server") Server[] servers, @PluginElement("FluencyConfig") FluencyConfig fluencyConfig, @PluginElement("Layout") Layout<? extends Serializable> layout, @PluginElement("Filter") Filter filter) {
/*     */     PatternLayout patternLayout;
/* 119 */     boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
/*     */     
/* 121 */     Map<String, Object> parameters = new HashMap<>();
/* 122 */     Map<String, String> fields = new HashMap<>();
/*     */ 
/*     */ 
/*     */     
/* 126 */     if (tag != null) {
/*     */       
/* 128 */       parameters.put("tag", tag);
/*     */     }
/*     */     else {
/*     */       
/* 132 */       throw new IllegalArgumentException("tag is required");
/*     */     } 
/*     */ 
/*     */     
/* 136 */     if (application != null)
/*     */     {
/* 138 */       fields.put("LogSourceName", application); }  byte b;
/*     */     int i;
/*     */     StaticField[] arrayOfStaticField;
/* 141 */     for (i = (arrayOfStaticField = staticFields).length, b = 0; b < i; ) { StaticField field = arrayOfStaticField[b];
/*     */       
/* 143 */       if (field.getName().trim().equals("")) {
/*     */         
/* 145 */         LOG.warn("Skipping empty field");
/*     */       
/*     */       }
/* 148 */       else if (field.getValue().trim().equals("")) {
/*     */         
/* 150 */         LOG.warn("Skipping field {} due to empty value", field.getName());
/*     */       } else {
/*     */         
/* 153 */         fields.put(field.getName(), field.getValue());
/*     */       }  b++; }
/*     */     
/* 156 */     if (layout == null)
/*     */     {
/* 158 */       patternLayout = PatternLayout.createDefaultLayout();
/*     */     }
/*     */     
/* 161 */     return new FluencyAppender(name, parameters, fields, servers, fluencyConfig, filter, (Layout<? extends Serializable>)patternLayout, ignoreExceptions);
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
/*     */   static Fluency makeFluency(Server[] servers, FluencyConfig config) throws IOException {
/* 178 */     if (servers.length == 0 && config == null)
/*     */     {
/* 180 */       return Fluency.defaultFluency();
/*     */     }
/* 182 */     if (servers.length == 0)
/*     */     {
/* 184 */       return Fluency.defaultFluency(config.configure());
/*     */     }
/* 186 */     List<InetSocketAddress> addresses = new ArrayList<>(servers.length); byte b; int i; Server[] arrayOfServer;
/* 187 */     for (i = (arrayOfServer = servers).length, b = 0; b < i; ) { Server s = arrayOfServer[b];
/*     */       
/* 189 */       addresses.add(s.configure()); b++; }
/*     */     
/* 191 */     if (config == null)
/*     */     {
/* 193 */       return Fluency.defaultFluency(addresses);
/*     */     }
/* 195 */     return Fluency.defaultFluency(addresses, config.configure());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LogEvent logEvent) {
/* 201 */     Map<String, Object> m = new HashMap<>();
/* 202 */     String level = logEvent.getLevel().name();
/* 203 */     String loggerName = logEvent.getLoggerName();
/* 204 */     if (logEvent.getThrown() != null) {
/*     */       
/* 206 */       m.put("Data", logEvent.getThrown().getMessage());
/* 207 */       String stackTrace = new String(getLayout().toByteArray(logEvent));
/* 208 */       m.put("StackTrace", stackTrace);
/*     */     }
/*     */     else {
/*     */       
/* 212 */       m.put("Data", logEvent.getMessage().getFormattedMessage());
/*     */     } 
/* 214 */     String message = new String(getLayout().toByteArray(logEvent));
/* 215 */     Date eventTime = new Date(logEvent.getTimeMillis());
/*     */     
/* 217 */     m.put("LogLevel", level);
/* 218 */     m.put("Logger", loggerName);
/* 219 */     m.put("Thread", logEvent.getThreadName());
/* 220 */     m.putAll(this.staticFields);
/*     */ 
/*     */ 
/*     */     
/* 224 */     m.put("@timestamp", this.format.format(eventTime));
/* 225 */     String correlationId = LbsCorrelationIdProvider.getCurrentCorrelationId();
/* 226 */     if (correlationId != null && !correlationId.equals(""))
/*     */     {
/* 228 */       m.put("CorrelationId", correlationId);
/*     */     }
/* 230 */     String spanId = LbsSpanIdProvider.getCurrentSpanId();
/* 231 */     if (spanId != null && !spanId.equals(""))
/*     */     {
/* 233 */       m.put("SpanId", spanId);
/*     */     }
/* 235 */     String timeStamp = getTimeStamp();
/* 236 */     if (timeStamp != null && !timeStamp.equals(""))
/*     */     {
/* 238 */       m.put("Timestamp", timeStamp);
/*     */     }
/*     */     
/* 241 */     if (this.fluency != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 247 */         this.fluency.emit((String)this.parameters.get("tag"), m);
/*     */       }
/* 249 */       catch (IOException e) {
/*     */         
/* 251 */         LOG.error(e.getMessage());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private String getTimeStamp() {
/* 258 */     Calendar cal = Calendar.getInstance();
/* 259 */     cal.setTimeZone(TimeZone.getDefault());
/* 260 */     Date date = cal.getTime();
/* 261 */     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
/* 262 */     return dateFormat.format(date);
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
/*     */   private String abbreviatePre26(String stringToAbbreviate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/* 277 */     return (String)this.abbreviateMethod.invoke(this.abbr, new Object[] { stringToAbbreviate });
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
/*     */   private String abbreviate(String stringToAbbreviate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/* 292 */     StringBuilder logger = new StringBuilder();
/* 293 */     this.abbreviateMethod.invoke(this.abbr, new Object[] { stringToAbbreviate, logger });
/* 294 */     return logger.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\FluencyAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */