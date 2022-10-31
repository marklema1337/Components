/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.ErrorHandler;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LifeCycle;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.RollingFileAppender;
/*     */ import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
/*     */ import org.apache.logging.log4j.core.appender.rolling.RollingFileManager;
/*     */ import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
/*     */ import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
/*     */ import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.DefaultConfiguration;
/*     */ import org.apache.logging.log4j.core.filter.Filterable;
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
/*     */ public class LbsRollingFileAppender
/*     */   implements ILbsCloneableAppender, Appender, LifeCycle, Filterable
/*     */ {
/*     */   private String m_RelativeFilePath;
/*     */   private int m_DomainID;
/*     */   private RollingFileAppender m_RollingFileAppender;
/*     */   private int maxFileSize;
/*     */   private int maxNumOfFiles;
/*     */   
/*     */   public LbsRollingFileAppender(String name, Layout layout, String filename, int maxFileSize, int maxNumOfFiles) throws IOException {
/*  52 */     this(name, layout, filename, false, maxFileSize, maxNumOfFiles, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsRollingFileAppender(String name, Layout layout, String filename, boolean isRelative, int maxFileSize, int maxNumOfFiles, String append) throws IOException {
/*  57 */     this.m_RelativeFilePath = filename;
/*  58 */     this.maxFileSize = maxFileSize * 1024 * 1024;
/*  59 */     this.maxNumOfFiles = maxNumOfFiles;
/*  60 */     if (name == null || name.length() == 0)
/*  61 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  62 */     String filePath = isRelative ? constructFullFilePath(filename) : filename;
/*  63 */     String filePattern = String.valueOf(filePath) + ".%i";
/*  64 */     SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy = SizeBasedTriggeringPolicy.createPolicy(String.valueOf(this.maxFileSize));
/*  65 */     DefaultRolloverStrategy defaultRolloverStrategy = DefaultRolloverStrategy.createStrategy(String.valueOf(this.maxNumOfFiles), null, null, null, null, false, (Configuration)new DefaultConfiguration());
/*  66 */     this.m_RollingFileAppender = RollingFileAppender.createAppender(filePath, 
/*  67 */         filePattern, append, name, null, null, null, (TriggeringPolicy)sizeBasedTriggeringPolicy, (RolloverStrategy)defaultRolloverStrategy, layout, null, null, "false", null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRelativeFilePath() {
/*  72 */     return this.m_RelativeFilePath;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String constructFullFilePath(String filePath) {
/*  77 */     if (filePath != null && filePath.length() > 0) {
/*     */       
/*  79 */       filePath = appendPath(LbsConsoleHelper.getApplicationPath(), filePath);
/*  80 */       filePath = normalizeFilePath(filePath);
/*     */     } 
/*  82 */     return filePath;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  87 */     StringBuilder buf = new StringBuilder();
/*  88 */     buf.append(getName());
/*  89 */     buf.append(" (RollingFileAppender)\nlayout: \"");
/*  90 */     buf.append(this.m_RollingFileAppender.getLayout());
/*  91 */     buf.append("\"\noutput-location: \"");
/*  92 */     buf.append(this.m_RollingFileAppender.getFileName());
/*  93 */     buf.append("\"\n");
/*  94 */     buf.append("max-number-of-log-files/max-size-of-individual-log-file: \"");
/*  95 */     buf.append(getMaxBackupIndex());
/*  96 */     buf.append("/");
/*  97 */     buf.append(getMaximumFileSize() / 1024 / 1024);
/*  98 */     buf.append("MB\"");
/*  99 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMaximumFileSize() {
/* 104 */     return this.maxFileSize;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMaxBackupIndex() {
/* 109 */     return this.maxNumOfFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public AppenderProps constructProps() {
/* 114 */     return constructProps(this.m_DomainID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public RollingFileAppenderProps constructProps(int domainId, boolean forced) {
/* 119 */     LbsLayout layout = new LbsLayout(getLayout());
/* 120 */     LbsLayoutFactory.LogFormat format = LbsLayoutFactory.fromLbsLayout(layout);
/* 121 */     String filePath = getFilePathForDomain(domainId, forced);
/* 122 */     String name = getNameForDomain(domainId, forced);
/* 123 */     RollingFileAppenderProps props = new RollingFileAppenderProps(name, domainId, format, filePath, getMaxBackupIndex(), 
/* 124 */         getMaximumFileSize() / 1024 / 1024);
/* 125 */     props.setAbsoluteLogFilePath(constructFullFilePath(filePath));
/* 126 */     return props;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getNameForDomain(int domainId, boolean forced) {
/* 131 */     if (!forced && this.m_DomainID == domainId)
/* 132 */       return getName(); 
/* 133 */     return String.valueOf(getName()) + "_" + domainId;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getFilePathForDomain(int domainId, boolean forced) {
/* 138 */     String filePath = getRelativeFilePath();
/* 139 */     if (!forced && domainId == this.m_DomainID)
/* 140 */       return filePath; 
/* 141 */     String parent = null;
/* 142 */     String fileName = filePath;
/* 143 */     int idx = filePath.lastIndexOf('/');
/* 144 */     if (idx > 0) {
/*     */       
/* 146 */       parent = filePath.substring(0, idx);
/* 147 */       fileName = filePath.substring(idx + 1);
/*     */     }
/*     */     else {
/*     */       
/* 151 */       idx = filePath.lastIndexOf('\\');
/* 152 */       if (idx > 0) {
/*     */         
/* 154 */         parent = filePath.substring(0, idx);
/* 155 */         fileName = filePath.substring(idx + 1);
/*     */       } 
/*     */     } 
/* 158 */     return 
/*     */       
/* 160 */       String.valueOf((parent == null) ? "" : (String.valueOf(parent) + File.separator)) + domainId + File.separator + fileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainId(int domainId) {
/* 165 */     this.m_DomainID = domainId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ignoreExceptions() {
/* 170 */     return this.m_RollingFileAppender.ignoreExceptions();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String appendPath(String path, String fileName) {
/* 176 */     return String.valueOf(ensurePath(path)) + ensureFileName(fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String ensurePath(String path) {
/* 181 */     return ensurePath(path, File.separatorChar);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String ensurePath(String path, char separator) {
/* 186 */     String result = (path != null) ? 
/* 187 */       path : 
/* 188 */       "";
/* 189 */     if (result.lastIndexOf(separator) != result.length() - 1)
/* 190 */       result = String.valueOf(result) + separator; 
/* 191 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String ensureFileName(String path) {
/* 196 */     if (!isEmpty(path) && path.startsWith(File.separator))
/* 197 */       return path.substring(1); 
/* 198 */     return path;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String normalizeFilePath(String path) {
/* 203 */     if (path == null || File.separatorChar == '/')
/* 204 */       return path; 
/* 205 */     StringBuilder buffer = new StringBuilder(path);
/* 206 */     for (int i = 0; i < buffer.length(); i++) {
/* 207 */       if (buffer.charAt(i) == '/')
/* 208 */         buffer.setCharAt(i, File.separatorChar); 
/* 209 */     }  return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean isEmpty(String s) {
/* 214 */     return !(s != null && s.length() != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsAppender cloneAppenderForDomain(int domainId) throws Exception {
/* 219 */     RollingFileAppenderProps props = constructProps(domainId, true);
/* 220 */     return props.createAppender();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFilter(Filter filter) {
/* 226 */     this.m_RollingFileAppender.addFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFilter(Filter filter) {
/* 232 */     this.m_RollingFileAppender.removeFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter() {
/* 238 */     return this.m_RollingFileAppender.getFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFilter() {
/* 244 */     return this.m_RollingFileAppender.hasFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFiltered(LogEvent event) {
/* 250 */     return this.m_RollingFileAppender.isFiltered(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LifeCycle.State getState() {
/* 256 */     return this.m_RollingFileAppender.getState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 261 */     this.m_RollingFileAppender.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 267 */     this.m_RollingFileAppender.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 273 */     this.m_RollingFileAppender.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStarted() {
/* 279 */     return this.m_RollingFileAppender.isStarted();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStopped() {
/* 285 */     return this.m_RollingFileAppender.isStopped();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/* 291 */     if (!Log4JConfigurator.canAppend(this.m_DomainID))
/*     */       return; 
/* 293 */     this.m_RollingFileAppender.append(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Layout<? extends Serializable> getLayout() {
/* 299 */     return this.m_RollingFileAppender.getLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getHandler() {
/* 305 */     return this.m_RollingFileAppender.getHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandler(ErrorHandler handler) {
/* 311 */     this.m_RollingFileAppender.setHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 317 */     return this.m_RollingFileAppender.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 323 */     stop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileName() {
/* 331 */     return this.m_RollingFileAppender.getFileName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilePattern() {
/* 339 */     return this.m_RollingFileAppender.getFilePattern();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RollingFileManager getManager() {
/* 348 */     return (RollingFileManager)this.m_RollingFileAppender.getManager();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsRollingFileAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */