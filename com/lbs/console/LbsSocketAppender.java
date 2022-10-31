/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.net.InetAddress;
/*     */ import org.apache.logging.log4j.ThreadContext;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.ErrorHandler;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LifeCycle;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.SocketAppender;
/*     */ import org.apache.logging.log4j.core.filter.Filterable;
/*     */ import org.apache.logging.log4j.core.layout.PatternLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsSocketAppender
/*     */   implements ILbsAppender, Appender, LifeCycle, Filterable
/*     */ {
/*     */   private int m_DomainID;
/*     */   private SocketAppender m_SocketAppender;
/*     */   private String m_Host;
/*     */   private int m_Port;
/*     */   
/*     */   LbsSocketAppender(String name, InetAddress address, int port) {
/*  35 */     if (name == null || name.length() == 0)
/*  36 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  37 */     init(name, port, address.getHostAddress());
/*     */   }
/*     */ 
/*     */   
/*     */   LbsSocketAppender(String name, String host, int port) {
/*  42 */     if (name == null || name.length() == 0)
/*  43 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  44 */     init(name, port, host);
/*     */   }
/*     */ 
/*     */   
/*     */   private void init(String name, int port, String hostAddress) {
/*  49 */     this.m_Host = hostAddress;
/*  50 */     this.m_Port = port;
/*  51 */     this.m_SocketAppender = SocketAppender.createAppender(this.m_Host, String.valueOf(this.m_Port), null, null, 0, null, null, name, "true", null, (Layout)PatternLayout.createDefaultLayout(), null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainId(int domainId) {
/*  56 */     this.m_DomainID = domainId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/*  62 */     if (!Log4JConfigurator.canAppend(this.m_DomainID))
/*     */       return; 
/*  64 */     ThreadContext.put("ServerHttpPort", Log4JConfigurator.ServerHttpPort);
/*  65 */     this.m_SocketAppender.append(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  70 */     StringBuilder buf = new StringBuilder();
/*  71 */     buf.append(getName());
/*  72 */     buf.append(" (SocketAppender)\ninclude-line-info: ");
/*  73 */     buf.append(getLocationInfo());
/*  74 */     buf.append("\nremote-server-address: \"");
/*  75 */     buf.append(getRemoteHost());
/*  76 */     buf.append(":");
/*  77 */     buf.append(getPort());
/*  78 */     buf.append("\"");
/*  79 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AppenderProps constructProps() {
/*  85 */     return new SocketAppenderProps(getName(), this.m_DomainID, getRemoteHost(), getPort().intValue(), getLocationInfo().booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  91 */     stop();
/*     */   }
/*     */ 
/*     */   
/*     */   private Integer getPort() {
/*  96 */     return Integer.valueOf(this.m_Port);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getRemoteHost() {
/* 101 */     return this.m_Host;
/*     */   }
/*     */ 
/*     */   
/*     */   private Boolean getLocationInfo() {
/* 106 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 112 */     return this.m_SocketAppender.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFilter(Filter filter) {
/* 118 */     this.m_SocketAppender.addFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFilter(Filter filter) {
/* 124 */     this.m_SocketAppender.removeFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter() {
/* 130 */     return this.m_SocketAppender.getFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFilter() {
/* 136 */     return this.m_SocketAppender.hasFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFiltered(LogEvent event) {
/* 142 */     return this.m_SocketAppender.isFiltered(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LifeCycle.State getState() {
/* 148 */     return this.m_SocketAppender.getState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 153 */     this.m_SocketAppender.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 159 */     this.m_SocketAppender.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 165 */     this.m_SocketAppender.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStarted() {
/* 171 */     return this.m_SocketAppender.isStarted();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStopped() {
/* 177 */     return this.m_SocketAppender.isStopped();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Layout<? extends Serializable> getLayout() {
/* 183 */     return this.m_SocketAppender.getLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreExceptions() {
/* 189 */     return this.m_SocketAppender.ignoreExceptions();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getHandler() {
/* 195 */     return this.m_SocketAppender.getHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandler(ErrorHandler handler) {
/* 201 */     this.m_SocketAppender.setHandler(handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsSocketAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */