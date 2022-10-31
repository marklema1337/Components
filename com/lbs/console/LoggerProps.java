/*     */ package com.lbs.console;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
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
/*     */ public class LoggerProps
/*     */   implements Serializable, Comparable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_LoggerName;
/*     */   private boolean m_Additive;
/*     */   private String m_LogLevel;
/*     */   private AppenderProps[] m_Appenders;
/*     */   
/*     */   public LoggerProps(String loggerName, LoggerType type, boolean additive, LbsLevel logLevel) {
/*  37 */     boolean isRootLogger = !(loggerName != null && loggerName.length() != 0);
/*  38 */     String fullLoggerName = isRootLogger ? 
/*  39 */       type.getPrefix() : (
/*  40 */       String.valueOf(type.getPrefix()) + "." + loggerName);
/*     */     
/*  42 */     construct(fullLoggerName, additive, logLevel);
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
/*     */   public LoggerProps(String fullLoggerName, boolean additive, LbsLevel logLevel) {
/*  54 */     construct(fullLoggerName, additive, logLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   private void construct(String fullLoggerName, boolean additive, LbsLevel logLevel) {
/*  59 */     if (fullLoggerName == null) {
/*  60 */       throw new IllegalArgumentException("Logger name cannot be null!");
/*     */     }
/*  62 */     this.m_LoggerName = fullLoggerName;
/*  63 */     LoggerType type = getType();
/*  64 */     if (type == null) {
/*     */       
/*  66 */       this.m_LoggerName = null;
/*  67 */       throw new IllegalArgumentException(String.valueOf(fullLoggerName) + " is not a valid logger name!");
/*     */     } 
/*     */     
/*  70 */     boolean isRootLogger = fullLoggerName.equals(type.getPrefix());
/*  71 */     if (isRootLogger && type == LoggerType.CONSOLE && additive) {
/*  72 */       throw new IllegalArgumentException("Root logger cannot be additive!");
/*     */     }
/*  74 */     this.m_Additive = additive;
/*  75 */     this.m_Appenders = null;
/*     */     
/*  77 */     this.m_LogLevel = (logLevel == null) ? 
/*  78 */       null : 
/*  79 */       logLevel.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAppender(AppenderProps appender) {
/*  84 */     if (appender == null) {
/*     */       return;
/*     */     }
/*  87 */     if (this.m_Appenders == null) {
/*     */       
/*  89 */       this.m_Appenders = new AppenderProps[1];
/*  90 */       this.m_Appenders[0] = appender;
/*     */     }
/*     */     else {
/*     */       
/*  94 */       int replacementIndex = -1;
/*  95 */       for (int i = 0; i < this.m_Appenders.length; i++) {
/*     */         
/*  97 */         if (this.m_Appenders[i].getName().equals(appender.getName())) {
/*     */           
/*  99 */           replacementIndex = i;
/*     */           break;
/*     */         } 
/*     */       } 
/* 103 */       if (replacementIndex < 0) {
/*     */         
/* 105 */         AppenderProps[] newAppenders = new AppenderProps[this.m_Appenders.length + 1];
/* 106 */         System.arraycopy(this.m_Appenders, 0, newAppenders, 0, this.m_Appenders.length);
/* 107 */         newAppenders[newAppenders.length - 1] = appender;
/* 108 */         this.m_Appenders = newAppenders;
/*     */       } else {
/*     */         
/* 111 */         this.m_Appenders[replacementIndex] = appender;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAppender(String appenderName) {
/* 117 */     if (appenderName == null || appenderName.length() == 0)
/*     */       return; 
/* 119 */     if (this.m_Appenders == null || this.m_Appenders.length == 0) {
/*     */       return;
/*     */     }
/* 122 */     for (int i = 0; i < this.m_Appenders.length; i++) {
/*     */       
/* 124 */       if (this.m_Appenders[i].getName().equals(appenderName)) {
/*     */         
/* 126 */         this.m_Appenders[i] = null;
/* 127 */         AppenderProps[] newAppenders = new AppenderProps[this.m_Appenders.length - 1];
/* 128 */         System.arraycopy(this.m_Appenders, 0, newAppenders, 0, i);
/* 129 */         System.arraycopy(this.m_Appenders, i + 1, newAppenders, i, this.m_Appenders.length - i - 1);
/* 130 */         this.m_Appenders = newAppenders;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAppenders() {
/* 138 */     this.m_Appenders = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdditive() {
/* 143 */     return this.m_Additive;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdditive(boolean additive) {
/* 148 */     this.m_Additive = additive;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsLevel getLogLevel() {
/* 153 */     return LbsLevel.getLevelByName(this.m_LogLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogLevel(LbsLevel logLevel) {
/* 158 */     if (logLevel == null) {
/* 159 */       this.m_LogLevel = null;
/*     */     } else {
/* 161 */       this.m_LogLevel = logLevel.getName();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AppenderProps[] getAppenders() {
/* 170 */     if (this.m_Appenders == null) {
/* 171 */       return new AppenderProps[0];
/*     */     }
/* 173 */     AppenderProps[] copy = new AppenderProps[this.m_Appenders.length];
/* 174 */     System.arraycopy(this.m_Appenders, 0, copy, 0, this.m_Appenders.length);
/* 175 */     return copy;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoggerType getType() {
/* 180 */     return LoggerType.getTypeByName(getFullName());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 185 */     if (this.m_LoggerName == null || this.m_LoggerName.length() == 0)
/* 186 */       this.m_LoggerName = LoggerType.CONSOLE.getPrefix(); 
/* 187 */     return this.m_LoggerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 192 */     String prefix = getType().getPrefix();
/* 193 */     String name = getFullName().substring(prefix.length());
/* 194 */     if (name.startsWith("."))
/* 195 */       name = name.substring(1); 
/* 196 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 201 */     return getFullName().compareTo(((LoggerProps)o).getFullName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 208 */     if (!(obj instanceof LoggerProps))
/* 209 */       return false; 
/* 210 */     return (compareTo(obj) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 217 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 222 */     LoggerProps clone = new LoggerProps(getFullName(), this.m_Additive, getLogLevel());
/* 223 */     if (this.m_Appenders != null)
/*     */     {
/* 225 */       for (int i = 0; i < this.m_Appenders.length; i++)
/* 226 */         clone.addAppender((AppenderProps)this.m_Appenders[i].clone()); 
/*     */     }
/* 228 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void apply() {
/* 233 */     LbsConsole logger = LbsConsole.getLogger(getFullName());
/* 234 */     logger.setAdditivity(isAdditive());
/* 235 */     logger.setLevel2(getLogLevel());
/*     */     
/* 237 */     if (this.m_Appenders != null)
/*     */     {
/* 239 */       for (int i = 0; i < this.m_Appenders.length; i++) {
/*     */         
/* 241 */         ILbsAppender appender = null;
/*     */         
/*     */         try {
/* 244 */           appender = this.m_Appenders[i].createAppender();
/*     */         }
/* 246 */         catch (IOException e) {
/*     */           
/* 248 */           e.printStackTrace();
/* 249 */           appender = null;
/*     */         } 
/* 251 */         if (appender != null)
/* 252 */           logger.addAppender(appender); 
/*     */       } 
/*     */     }
/* 255 */     Gson gson = (new GsonBuilder()).registerTypeAdapter(AppenderProps.class, new InterfaceAdapter()).create();
/* 256 */     logger.log(getLogLevel(), "LogLevelChangedFromAdminClient:" + gson.toJson(this));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LoggerProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */