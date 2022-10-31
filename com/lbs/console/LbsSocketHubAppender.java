/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.logging.log4j.ThreadContext;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.ErrorHandler;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LifeCycle;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.filter.Filterable;
/*     */ import org.apache.logging.log4j.sockethub.Log4j2SocketHubAppender;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsSocketHubAppender
/*     */   implements ILbsAppender, Appender, LifeCycle, Filterable
/*     */ {
/*     */   private int m_DomainID;
/*     */   private Log4j2SocketHubAppender m_SocketHubAppender;
/*     */   private int m_Port;
/*     */   
/*     */   LbsSocketHubAppender(String name, int _port, String locationInfo) {
/*  33 */     if (name == null || name.length() == 0)
/*  34 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  35 */     this.m_Port = _port;
/*  36 */     this.m_SocketHubAppender = Log4j2SocketHubAppender.createAppender(String.valueOf(this.m_Port), locationInfo, name, null, null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainId(int domainId) {
/*  41 */     this.m_DomainID = domainId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/*  47 */     if (!Log4JConfigurator.canAppend(this.m_DomainID))
/*     */       return; 
/*  49 */     ThreadContext.put("ServerHttpPort", Log4JConfigurator.ServerHttpPort);
/*  50 */     this.m_SocketHubAppender.append(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  55 */     StringBuilder buf = new StringBuilder();
/*  56 */     buf.append(getName());
/*  57 */     buf.append(" (SocketHubAppender)\ninclude-line-info: ");
/*  58 */     buf.append(getLocationInfo());
/*  59 */     buf.append("\nlistening-port: \"");
/*  60 */     buf.append(getPort());
/*  61 */     buf.append("\"");
/*  62 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public AppenderProps constructProps() {
/*  67 */     return new SocketHubAppenderProps(getName(), this.m_DomainID, getPort().intValue(), getLocationInfo().booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  73 */     stop();
/*     */   }
/*     */ 
/*     */   
/*     */   private Integer getPort() {
/*  78 */     return Integer.valueOf(this.m_Port);
/*     */   }
/*     */ 
/*     */   
/*     */   private Boolean getLocationInfo() {
/*  83 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  89 */     return this.m_SocketHubAppender.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFilter(Filter filter) {
/*  95 */     this.m_SocketHubAppender.addFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFilter(Filter filter) {
/* 101 */     this.m_SocketHubAppender.removeFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter() {
/* 107 */     return this.m_SocketHubAppender.getFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFilter() {
/* 113 */     return this.m_SocketHubAppender.hasFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFiltered(LogEvent event) {
/* 119 */     return this.m_SocketHubAppender.isFiltered(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LifeCycle.State getState() {
/* 125 */     return this.m_SocketHubAppender.getState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 130 */     this.m_SocketHubAppender.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 136 */     this.m_SocketHubAppender.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 142 */     this.m_SocketHubAppender.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStarted() {
/* 148 */     return this.m_SocketHubAppender.isStarted();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStopped() {
/* 154 */     return this.m_SocketHubAppender.isStopped();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Layout<? extends Serializable> getLayout() {
/* 160 */     return this.m_SocketHubAppender.getLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreExceptions() {
/* 166 */     return this.m_SocketHubAppender.ignoreExceptions();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getHandler() {
/* 172 */     return this.m_SocketHubAppender.getHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandler(ErrorHandler handler) {
/* 178 */     this.m_SocketHubAppender.setHandler(handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsSocketHubAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */