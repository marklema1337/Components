/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class Log4JConfigurator
/*     */ {
/*  25 */   private Hashtable<String, ILbsAppender> m_AppenderList = new Hashtable<>();
/*  26 */   private List<ILbsCloneableAppender> m_ClonedAppenders = new ArrayList<>();
/*     */   
/*     */   private static ILbsConsoleDomainRegistry ms_DomainRegistry;
/*     */   private static boolean ms_SuspendFilter = false;
/*  30 */   public static String ServerHttpPort = null;
/*     */   
/*     */   private static String ms_ApplicationName;
/*     */ 
/*     */   
/*     */   public static void setDomainRegistry(ILbsConsoleDomainRegistry domainRegistry) {
/*  36 */     ms_DomainRegistry = domainRegistry;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsConsoleDomainRegistry getDomainRegistry() {
/*  41 */     return ms_DomainRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetToDefaults() {
/*  47 */     ArrayList<String> loggerNames = LbsConsole.getKnownLoggerNames();
/*  48 */     for (int i = 0; i < loggerNames.size(); i++) {
/*     */       
/*  50 */       LbsConsole logger = LbsConsole.getLogger(loggerNames.get(i));
/*  51 */       logger.setLevel2(null);
/*  52 */       if (!logger.isRootLogger()) {
/*  53 */         logger.setAdditivity(true);
/*     */       }
/*  55 */       if (logger instanceof Log4jLogger) {
/*     */         
/*  57 */         Log4jLogger log4jLogger = (Log4jLogger)logger;
/*  58 */         log4jLogger.removeAllAppenders();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void reconfigure() {
/*  65 */     if (ms_DomainRegistry != null) {
/*     */       
/*  67 */       int[] ids = ms_DomainRegistry.getAllDomainIds();
/*  68 */       for (ILbsCloneableAppender appender : this.m_ClonedAppenders) {
/*     */         
/*  70 */         ILbsConsole[] loggers = LbsConsoleHelper.getAttachedLoggers(appender.getName());
/*  71 */         if (loggers != null && loggers.length > 0)
/*     */         {
/*  73 */           for (int i = 0; i < ids.length; i++) {
/*     */ 
/*     */             
/*     */             try {
/*  77 */               ILbsAppender clonedAppender = appender.cloneAppenderForDomain(ids[i]);
/*  78 */               for (int j = 0; j < loggers.length; j++)
/*     */               {
/*  80 */                 loggers[j].addAppender(clonedAppender);
/*     */               }
/*     */             }
/*  83 */             catch (Exception e) {
/*     */               
/*  85 */               e.printStackTrace();
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized ArrayList<String> configure(Element consoleConfig) {
/*  96 */     ArrayList<String> errors = new ArrayList<>();
/*  97 */     if (consoleConfig == null || !consoleConfig.getNodeName().equals("console-config")) {
/*     */       
/*  99 */       errors.add("Given root element is not a console-config element! Cannot parse sorry.");
/* 100 */       return errors;
/*     */     } 
/* 102 */     resetToDefaults();
/*     */ 
/*     */     
/* 105 */     String clientLoggingLevel = consoleConfig.getAttribute("client-logging-level");
/* 106 */     LbsConsoleSettings.CLIENT_LOGGING_LEVEL = clientLoggingLevel;
/* 107 */     NodeList childNodes = consoleConfig.getChildNodes();
/*     */     int i;
/* 109 */     for (i = 0; i < childNodes.getLength(); i++) {
/*     */       
/* 111 */       Node item = childNodes.item(i);
/* 112 */       if (item instanceof Element && ((Element)item).getTagName().equals("appender-list"))
/*     */       {
/* 114 */         configureAppenderList(((Element)item).getChildNodes(), errors);
/*     */       }
/*     */     } 
/*     */     
/* 118 */     for (i = 0; i < childNodes.getLength(); i++) {
/*     */       
/* 120 */       Node item = childNodes.item(i);
/* 121 */       if (item instanceof Element && ((Element)item).getTagName().equals("root-category")) {
/*     */         
/* 123 */         configureRootCategory((Element)item, errors);
/*     */       }
/* 125 */       else if (item instanceof Element && ((Element)item).getTagName().equals("sub-categories")) {
/*     */         
/* 127 */         configureSubCategories((Element)item, errors);
/*     */       } 
/*     */     } 
/* 130 */     return errors;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void configureRootCategory(Element rootCategory, ArrayList<String> errors) {
/* 136 */     String logLevel = rootCategory.getAttribute("log-level");
/* 137 */     LbsConsole.getRootLogger().setLevel2(LbsLevel.getLevelByName(logLevel));
/*     */     
/* 139 */     NodeList childNodes = rootCategory.getChildNodes();
/* 140 */     for (int i = 0; i < childNodes.getLength(); i++) {
/*     */       
/* 142 */       Node item = childNodes.item(i);
/* 143 */       if (item instanceof Element && ((Element)item).getTagName().equals("appenders")) {
/*     */         
/* 145 */         NodeList appenders = ((Element)item).getChildNodes();
/* 146 */         configureAppendersOfCategory("", appenders, errors);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void configureSubCategories(Element item2, ArrayList<String> errors) {
/* 153 */     NodeList subCategories = item2.getChildNodes();
/* 154 */     for (int i = 0; i < subCategories.getLength(); i++) {
/*     */       
/* 156 */       Node subItem = subCategories.item(i);
/* 157 */       if (subItem instanceof Element && ((Element)subItem).getTagName().equals("sub-category")) {
/*     */         
/* 159 */         Element subElement = (Element)subItem;
/* 160 */         String categoryName = subElement.getAttribute("category-name");
/* 161 */         String logLevel = subElement.getAttribute("log-level");
/* 162 */         boolean additive = Boolean.valueOf(subElement.getAttribute("additive")).booleanValue();
/*     */         
/* 164 */         LbsConsole logger = LbsConsole.getLogger(categoryName);
/* 165 */         logger.setAdditivity(additive);
/* 166 */         logger.setLevel2(LbsLevel.getLevelByName(logLevel));
/*     */         
/* 168 */         NodeList childNodes = subElement.getChildNodes();
/* 169 */         for (int j = 0; j < childNodes.getLength(); j++) {
/*     */           
/* 171 */           Node item = childNodes.item(j);
/* 172 */           if (item instanceof Element && ((Element)item).getTagName().equals("appenders")) {
/*     */             
/* 174 */             NodeList appenders = ((Element)item).getChildNodes();
/* 175 */             configureAppendersOfCategory(categoryName, appenders, errors);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void configureAppenderList(NodeList appenderList, ArrayList<String> errors) {
/*     */     try {
/* 186 */       for (int i = 0; i < appenderList.getLength(); i++) {
/*     */         
/* 188 */         Node item = appenderList.item(i);
/*     */         
/* 190 */         if (item instanceof Element)
/*     */         {
/* 192 */           Element appender = (Element)item;
/*     */           
/* 194 */           if (appender.getTagName().equals("console-appender")) {
/*     */             LbsLayoutFactory.LogFormat format;
/* 196 */             String appenderID = appender.getAttribute("id");
/* 197 */             String outputLocation = appender.getAttribute("output-location");
/* 198 */             boolean systemErr = false;
/* 199 */             if (outputLocation.equals("System.err")) {
/* 200 */               systemErr = true;
/*     */             }
/*     */             
/* 203 */             String layoutPattern = appender.getAttribute("layout-pattern");
/* 204 */             if (layoutPattern != null && layoutPattern.length() > 0) {
/*     */               
/* 206 */               format = LbsLayoutFactory.LogFormat.PLAIN(layoutPattern);
/*     */             }
/*     */             else {
/*     */               
/* 210 */               boolean includeLineInfo = Boolean.getBoolean(appender.getAttribute("include-line-info"));
/* 211 */               format = LbsLayoutFactory.LogFormat.PLAIN(includeLineInfo);
/*     */             } 
/* 213 */             LbsLayout layout = LbsLayoutFactory.createLayout(format);
/* 214 */             ILbsAppender consoleAppender = LbsAppenderFactory.createConsoleAppender(appenderID, readDomainId(appender), 
/* 215 */                 layout, systemErr);
/* 216 */             this.m_AppenderList.put(consoleAppender.getName(), consoleAppender);
/*     */           
/*     */           }
/* 219 */           else if (appender.getTagName().equals("rolling-file-appender")) {
/*     */             LbsLayoutFactory.LogFormat format; int maxNumFiles, maxSizeOfAFile;
/* 221 */             String appenderID = appender.getAttribute("id");
/*     */ 
/*     */ 
/*     */             
/* 225 */             String logFormat = appender.getAttribute("log-format");
/* 226 */             if ("XML".equals(logFormat)) {
/*     */               
/* 228 */               boolean includeLineInfo = Boolean.getBoolean(appender.getAttribute("include-line-info"));
/* 229 */               format = LbsLayoutFactory.LogFormat.XML(includeLineInfo);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 234 */               String layoutPattern = appender.getAttribute("plain-log-layout-pattern");
/* 235 */               if (layoutPattern != null && layoutPattern.length() > 0) {
/*     */                 
/* 237 */                 format = LbsLayoutFactory.LogFormat.PLAIN(layoutPattern);
/*     */               }
/*     */               else {
/*     */                 
/* 241 */                 boolean includeLineInfo = Boolean.getBoolean(appender.getAttribute("include-line-info"));
/* 242 */                 format = LbsLayoutFactory.LogFormat.PLAIN(includeLineInfo);
/*     */               } 
/*     */             } 
/* 245 */             LbsLayout layout = LbsLayoutFactory.createLayout(format);
/*     */ 
/*     */             
/* 248 */             String filePath = appender.getAttribute("log-file-name");
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 254 */               String text = appender.getAttribute("max-number-of-log-files");
/* 255 */               maxNumFiles = Integer.parseInt(text);
/*     */             }
/* 257 */             catch (NumberFormatException e) {
/*     */               
/* 259 */               maxNumFiles = 10;
/* 260 */               errors.add("Rolling-file-appender id = " + appenderID + 
/* 261 */                   "Integer expected while parsing max-number-of-log-files. Using default (10)");
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 267 */               String text = appender.getAttribute("max-size-of-individual-log-file");
/* 268 */               maxSizeOfAFile = Integer.parseInt(text);
/*     */             }
/* 270 */             catch (NumberFormatException e) {
/*     */               
/* 272 */               maxSizeOfAFile = 10;
/* 273 */               errors.add("Rolling-file-appender id = " + appenderID + 
/* 274 */                   "Integer expected while parsing max-size-of-individual-log-file. Using default (10)");
/*     */             } 
/*     */             
/*     */             try {
/* 278 */               ILbsAppender rollingFileAppender = LbsAppenderFactory.createRollingFileAppender(appenderID, 
/* 279 */                   readDomainId(appender), layout, filePath, maxNumFiles, maxSizeOfAFile);
/* 280 */               this.m_AppenderList.put(rollingFileAppender.getName(), rollingFileAppender);
/* 281 */               String text = appender.getAttribute("duplicate-for-domains");
/* 282 */               boolean duplicateForDomains = true;
/* 283 */               if (text != null && text.length() > 0) {
/*     */                 
/*     */                 try {
/*     */                   
/* 287 */                   duplicateForDomains = Boolean.parseBoolean(text);
/*     */                 }
/* 289 */                 catch (Exception exception) {}
/*     */               }
/*     */ 
/*     */               
/* 293 */               if (duplicateForDomains && rollingFileAppender instanceof ILbsCloneableAppender) {
/* 294 */                 this.m_ClonedAppenders.add((ILbsCloneableAppender)rollingFileAppender);
/*     */               }
/* 296 */             } catch (IOException e) {
/*     */               
/* 298 */               errors.add("Could not add appender (id: " + appenderID + " ) Error message: " + e.getMessage());
/*     */             }
/*     */           
/*     */           }
/* 302 */           else if (appender.getTagName().equals("socket-hub-appender")) {
/*     */             int port;
/* 304 */             String appenderID = appender.getAttribute("id");
/*     */ 
/*     */             
/*     */             try {
/* 308 */               String text = appender.getAttribute("listening-port");
/* 309 */               port = Integer.parseInt(text);
/*     */             
/*     */             }
/* 312 */             catch (NumberFormatException e) {
/*     */               
/* 314 */               errors.add("Socket-hub-appender id = " + appenderID + 
/* 315 */                   "Integer expected while parsing port. Could not enable the appender");
/*     */             } 
/*     */ 
/*     */             
/* 319 */             boolean includeLineInfo = Boolean.getBoolean(appender.getAttribute("include-line-info"));
/* 320 */             ILbsAppender socketHubAppender = LbsAppenderFactory.createSocketHubAppender(appenderID, 
/* 321 */                 readDomainId(appender), port, includeLineInfo);
/* 322 */             this.m_AppenderList.put(socketHubAppender.getName(), socketHubAppender);
/*     */           
/*     */           }
/* 325 */           else if (appender.getTagName().equals("socket-appender")) {
/*     */             int remoteServerPort;
/* 327 */             String appenderID = appender.getAttribute("id");
/*     */             
/* 329 */             String remoteServerAddress = appender.getAttribute("remote-server-address");
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 334 */               String text = appender.getAttribute("remote-server-port");
/* 335 */               remoteServerPort = Integer.parseInt(text);
/*     */             
/*     */             }
/* 338 */             catch (NumberFormatException e) {
/*     */               
/* 340 */               errors.add("Socket-hub-appender id = " + appenderID + 
/* 341 */                   "Integer expected while parsing port. Could not enable the appender");
/*     */             } 
/*     */ 
/*     */             
/* 345 */             boolean includeLineInfo = Boolean.getBoolean(appender.getAttribute("include-line-info"));
/* 346 */             ILbsAppender socketAppender = LbsAppenderFactory.createSocketAppender(appenderID, readDomainId(appender), 
/* 347 */                 remoteServerAddress, remoteServerPort, includeLineInfo);
/* 348 */             this.m_AppenderList.put(socketAppender.getName(), socketAppender);
/*     */           
/*     */           }
/* 351 */           else if (appender.getTagName().equals("fluentd-appender")) {
/*     */             
/* 353 */             String appenderID = appender.getAttribute("id");
/* 354 */             String servers = appender.getAttribute("servers");
/*     */             
/* 356 */             ILbsAppender fluentdAppender = LbsAppenderFactory.createFluentdAppender(appenderID, readDomainId(appender), 
/* 357 */                 servers, ms_ApplicationName);
/*     */             
/* 359 */             this.m_AppenderList.put(fluentdAppender.getName(), fluentdAppender);
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 365 */     } catch (Exception e) {
/*     */       
/* 367 */       e.printStackTrace();
/* 368 */       LbsConsole.getLogger(getClass()).error(e, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int readDomainId(Element appender) {
/* 374 */     int domainId = -1;
/* 375 */     String text = appender.getAttribute("domain-id");
/*     */     
/*     */     try {
/* 378 */       if (text != null && text.length() > 0) {
/* 379 */         domainId = Integer.parseInt(text);
/*     */       }
/* 381 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 384 */     return domainId;
/*     */   }
/*     */ 
/*     */   
/*     */   private void configureAppendersOfCategory(String name, NodeList appenders, ArrayList<String> errors) {
/* 389 */     LbsConsole logger = LbsConsole.getLogger(name);
/* 390 */     if (logger instanceof Log4jLogger) {
/*     */       
/* 392 */       Log4jLogger log4jLogger = (Log4jLogger)logger;
/* 393 */       for (int i = 0; i < appenders.getLength(); i++) {
/*     */         
/* 395 */         Node item = appenders.item(i);
/* 396 */         if (item instanceof Element && ((Element)item).getTagName().equals("appender")) {
/*     */           
/* 398 */           String refid = ((Element)item).getAttribute("refid");
/* 399 */           ILbsAppender appender = this.m_AppenderList.get(refid);
/* 400 */           if (appender == null) {
/* 401 */             errors.add("Appender with name " + refid + " not found!");
/*     */           } else {
/* 403 */             log4jLogger.addAppender(appender);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean canAppend(int domainId) {
/* 411 */     if (ms_SuspendFilter)
/* 412 */       return true; 
/* 413 */     if (domainId < 0)
/* 414 */       return true; 
/* 415 */     if (ms_DomainRegistry != null) {
/*     */       
/* 417 */       int eventDomainId = ms_DomainRegistry.getDomainId();
/* 418 */       return (eventDomainId == domainId);
/*     */     } 
/* 420 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSuspendFilter(boolean suspendFilter) {
/* 425 */     ms_SuspendFilter = suspendFilter;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void configureHazelcastLog() {
/* 430 */     System.setProperty("hazelcast.logging.type", "log4j2");
/* 431 */     System.setProperty("hazelcast.logging.class", "com.lbs.data.util.LbsLog4j2Factory");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setApplicationName(String applicationName) {
/* 436 */     ms_ApplicationName = applicationName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\Log4JConfigurator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */