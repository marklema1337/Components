/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.ErrorHandler;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LifeCycle;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.ConsoleAppender;
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
/*     */ public class LbsConsoleAppender
/*     */   implements ILbsAppender, Appender, LifeCycle, Filterable
/*     */ {
/*     */   private int m_DomainID;
/*     */   private ConsoleAppender m_ConsoleAppender;
/*     */   
/*     */   LbsConsoleAppender(String name) {
/*  33 */     if (name == null || name.length() == 0)
/*  34 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  35 */     this.m_ConsoleAppender = ((ConsoleAppender.Builder)ConsoleAppender.newBuilder().withName(name)).build();
/*     */   }
/*     */ 
/*     */   
/*     */   LbsConsoleAppender(String name, Layout layout, ConsoleAppender.Target target) {
/*  40 */     if (name == null || name.length() == 0)
/*  41 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  42 */     this.m_ConsoleAppender = ((ConsoleAppender.Builder)((ConsoleAppender.Builder)ConsoleAppender.newBuilder().withName(name)).withLayout(layout)).setTarget(target).build();
/*     */   }
/*     */ 
/*     */   
/*     */   LbsConsoleAppender(String name, Layout layout) {
/*  47 */     if (name == null || name.length() == 0)
/*  48 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  49 */     this.m_ConsoleAppender = ((ConsoleAppender.Builder)((ConsoleAppender.Builder)ConsoleAppender.newBuilder().withName(name)).withLayout(layout)).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainId(int domainId) {
/*  54 */     this.m_DomainID = domainId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ignoreExceptions() {
/*  59 */     return this.m_ConsoleAppender.ignoreExceptions();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  64 */     StringBuilder buf = new StringBuilder();
/*  65 */     buf.append(getName());
/*  66 */     buf.append(" (ConsoleAppender)\nlayout: \"");
/*  67 */     buf.append(this.m_ConsoleAppender.getLayout());
/*  68 */     buf.append("\"\noutput-location: \"");
/*  69 */     buf.append(this.m_ConsoleAppender.getManager().getName());
/*  70 */     buf.append("\"");
/*  71 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public AppenderProps constructProps() {
/*  76 */     LbsLayout layout = new LbsLayout(this.m_ConsoleAppender.getLayout());
/*  77 */     LbsLayoutFactory.LogFormat format = LbsLayoutFactory.fromLbsLayout(layout);
/*  78 */     ConsoleAppenderProps props = new ConsoleAppenderProps(getName(), this.m_DomainID, format, this.m_ConsoleAppender.getManager().getName().equals("SYSTEM_ERR"));
/*  79 */     return props;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  86 */     return this.m_ConsoleAppender.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  92 */     stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFilter(Filter filter) {
/*  98 */     this.m_ConsoleAppender.addFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFilter(Filter filter) {
/* 104 */     this.m_ConsoleAppender.removeFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter() {
/* 110 */     return this.m_ConsoleAppender.getFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFilter() {
/* 116 */     return this.m_ConsoleAppender.hasFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFiltered(LogEvent event) {
/* 122 */     return this.m_ConsoleAppender.isFiltered(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LifeCycle.State getState() {
/* 128 */     return this.m_ConsoleAppender.getState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 133 */     this.m_ConsoleAppender.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 139 */     this.m_ConsoleAppender.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 145 */     this.m_ConsoleAppender.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStarted() {
/* 151 */     return this.m_ConsoleAppender.isStarted();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStopped() {
/* 157 */     return this.m_ConsoleAppender.isStopped();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/* 163 */     if (!Log4JConfigurator.canAppend(this.m_DomainID))
/*     */       return; 
/* 165 */     this.m_ConsoleAppender.append(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Layout<? extends Serializable> getLayout() {
/* 171 */     return this.m_ConsoleAppender.getLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getHandler() {
/* 177 */     return this.m_ConsoleAppender.getHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandler(ErrorHandler handler) {
/* 183 */     this.m_ConsoleAppender.setHandler(handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsConsoleAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */