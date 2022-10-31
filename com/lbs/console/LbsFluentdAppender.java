/*     */ package com.lbs.console;
/*     */ 
/*     */ import com.wywy.log4j.appender.FluencyConfig;
/*     */ import com.wywy.log4j.appender.Server;
/*     */ import com.wywy.log4j.appender.StaticField;
/*     */ import java.io.Serializable;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.ErrorHandler;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LifeCycle;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.filter.Filterable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsFluentdAppender
/*     */   implements ILbsAppender, Appender, LifeCycle, Filterable
/*     */ {
/*     */   private int m_DomainID;
/*     */   private FluencyAppender m_FluentdAppender;
/*     */   private String m_Servers;
/*     */   private String m_ApplicationName;
/*     */   private String m_HostName;
/*     */   
/*     */   LbsFluentdAppender(String name, String servers, String applicationName) {
/*  27 */     if (name == null || name.length() == 0)
/*  28 */       throw new IllegalArgumentException("A valid name should be provided!"); 
/*  29 */     if (servers == null || servers.length() == 0)
/*  30 */       throw new IllegalArgumentException("Servers should be provided!"); 
/*  31 */     init(name, servers, applicationName);
/*     */   }
/*     */ 
/*     */   
/*     */   private void init(String name, String servers, String applicationName) {
/*  36 */     this.m_Servers = servers;
/*  37 */     this.m_ApplicationName = applicationName;
/*  38 */     this.m_HostName = InetUtil.getHostname();
/*  39 */     this.m_FluentdAppender = FluencyAppender.createAppender(name, "LOGOLOG." + applicationName, 
/*  40 */         applicationName, "false", initStaticFields(this.m_HostName), parseServers(servers), (FluencyConfig)null, (Layout<? extends Serializable>)null, (Filter)null);
/*     */   }
/*     */ 
/*     */   
/*     */   private Server[] parseServers(String servers) {
/*  45 */     String[] serverList = servers.split(",");
/*  46 */     Server[] serverss = new Server[serverList.length];
/*  47 */     for (int i = 0; i < serverList.length; i++) {
/*     */       
/*  49 */       String serverAddress = serverList[i];
/*  50 */       String[] hostports = serverAddress.split(":");
/*  51 */       if (hostports.length != 2)
/*  52 */         throw new IllegalArgumentException("Servers should be provided in 'host1:port1,host2:port2' format!"); 
/*  53 */       serverss[i] = Server.createServer(hostports[0], Integer.parseInt(hostports[1]));
/*     */     } 
/*  55 */     return serverss;
/*     */   }
/*     */ 
/*     */   
/*     */   private StaticField[] initStaticFields(String hostName) {
/*  60 */     StaticField[] fields = new StaticField[1];
/*  61 */     fields[0] = StaticField.createStaticField("ClientHostName", hostName);
/*  62 */     return fields;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainId(int domainId) {
/*  67 */     this.m_DomainID = domainId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/*  73 */     if (!Log4JConfigurator.canAppend(this.m_DomainID))
/*     */       return; 
/*  75 */     this.m_FluentdAppender.append(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  80 */     StringBuilder buf = new StringBuilder();
/*  81 */     buf.append(getName());
/*  82 */     buf.append(" (FluentdAppender) ");
/*  83 */     buf.append("\nserver-addresses: \"");
/*  84 */     buf.append(getServers());
/*  85 */     buf.append("\"");
/*  86 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public AppenderProps constructProps() {
/*  91 */     return new FluentdAppenderProps(getName(), this.m_DomainID, getServers(), getApplicationName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  97 */     stop();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getServers() {
/* 102 */     return this.m_Servers;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getApplicationName() {
/* 107 */     return this.m_ApplicationName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 113 */     return this.m_FluentdAppender.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFilter(Filter filter) {
/* 119 */     this.m_FluentdAppender.addFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFilter(Filter filter) {
/* 125 */     this.m_FluentdAppender.removeFilter(filter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter() {
/* 131 */     return this.m_FluentdAppender.getFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFilter() {
/* 137 */     return this.m_FluentdAppender.hasFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFiltered(LogEvent event) {
/* 143 */     return this.m_FluentdAppender.isFiltered(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LifeCycle.State getState() {
/* 149 */     return this.m_FluentdAppender.getState();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 155 */     this.m_FluentdAppender.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 161 */     this.m_FluentdAppender.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 167 */     this.m_FluentdAppender.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStarted() {
/* 173 */     return this.m_FluentdAppender.isStarted();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStopped() {
/* 179 */     return this.m_FluentdAppender.isStopped();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Layout<? extends Serializable> getLayout() {
/* 185 */     return this.m_FluentdAppender.getLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreExceptions() {
/* 191 */     return this.m_FluentdAppender.ignoreExceptions();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getHandler() {
/* 197 */     return this.m_FluentdAppender.getHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandler(ErrorHandler handler) {
/* 203 */     this.m_FluentdAppender.setHandler(handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsFluentdAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */