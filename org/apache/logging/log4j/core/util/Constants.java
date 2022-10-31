/*     */ package org.apache.logging.log4j.core.util;
/*     */ 
/*     */ import org.apache.logging.log4j.util.PropertiesUtil;
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
/*     */ 
/*     */ public final class Constants
/*     */ {
/*     */   public static final String LOG4J_LOG_EVENT_FACTORY = "Log4jLogEventFactory";
/*     */   public static final String LOG4J_CONTEXT_SELECTOR = "Log4jContextSelector";
/*     */   public static final String LOG4J_DEFAULT_STATUS_LEVEL = "Log4jDefaultStatusLevel";
/*     */   public static final String JNDI_CONTEXT_NAME = "java:comp/env/log4j/context-name";
/*     */   public static final int MILLIS_IN_SECONDS = 1000;
/*  54 */   public static final boolean FORMAT_MESSAGES_IN_BACKGROUND = PropertiesUtil.getProperties().getBooleanProperty("log4j.format.msg.async", false);
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
/*     */   @Deprecated
/*  67 */   public static final boolean FORMAT_MESSAGES_PATTERN_DISABLE_LOOKUPS = PropertiesUtil.getProperties().getBooleanProperty("log4j2.formatMsgNoLookups", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static final boolean IS_WEB_APP = org.apache.logging.log4j.util.Constants.IS_WEB_APP;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public static final boolean ENABLE_THREADLOCALS = org.apache.logging.log4j.util.Constants.ENABLE_THREADLOCALS;
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
/*  98 */   public static final boolean ENABLE_DIRECT_ENCODERS = PropertiesUtil.getProperties().getBooleanProperty("log4j2.enable.direct.encoders", true);
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
/* 110 */   public static final int INITIAL_REUSABLE_MESSAGE_SIZE = size("log4j.initialReusableMsgSize", 128);
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
/* 121 */   public static final int MAX_REUSABLE_MESSAGE_SIZE = size("log4j.maxReusableMsgSize", 518);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public static final int ENCODER_CHAR_BUFFER_SIZE = size("log4j.encoder.charBufferSize", 2048);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public static final int ENCODER_BYTE_BUFFER_SIZE = size("log4j.encoder.byteBufferSize", 8192);
/*     */ 
/*     */   
/*     */   private static int size(String property, int defaultValue) {
/* 144 */     return PropertiesUtil.getProperties().getIntegerProperty(property, defaultValue);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\cor\\util\Constants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */