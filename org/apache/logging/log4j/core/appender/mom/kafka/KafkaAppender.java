/*     */ package org.apache.logging.log4j.core.appender.mom.kafka;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.AbstractLifeCycle;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.AbstractAppender;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.Property;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
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
/*     */ @Plugin(name = "Kafka", category = "Core", elementType = "appender", printObject = true)
/*     */ public final class KafkaAppender
/*     */   extends AbstractAppender
/*     */ {
/*     */   public static class Builder<B extends Builder<B>>
/*     */     extends AbstractAppender.Builder<B>
/*     */     implements org.apache.logging.log4j.core.util.Builder<KafkaAppender>
/*     */   {
/*     */     @PluginAttribute("retryCount")
/*     */     private String retryCount;
/*     */     @PluginAttribute("topic")
/*     */     private String topic;
/*     */     @PluginAttribute("key")
/*     */     private String key;
/*     */     @PluginAttribute(value = "syncSend", defaultBoolean = true)
/*     */     private boolean syncSend;
/*     */     
/*     */     public KafkaAppender build() {
/*  70 */       Layout<? extends Serializable> layout = getLayout();
/*  71 */       if (layout == null) {
/*  72 */         KafkaAppender.LOGGER.error("No layout provided for KafkaAppender");
/*  73 */         return null;
/*     */       } 
/*  75 */       KafkaManager kafkaManager = KafkaManager.getManager(getConfiguration().getLoggerContext(), getName(), this.topic, this.syncSend, 
/*  76 */           getPropertyArray(), this.key);
/*  77 */       return new KafkaAppender(getName(), layout, getFilter(), isIgnoreExceptions(), kafkaManager, 
/*  78 */           getPropertyArray(), getRetryCount());
/*     */     }
/*     */     
/*     */     public Integer getRetryCount() {
/*  82 */       Integer intRetryCount = null;
/*     */       try {
/*  84 */         intRetryCount = Integer.valueOf(this.retryCount);
/*  85 */       } catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */       
/*  88 */       return intRetryCount;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getTopic() {
/*  93 */       return this.topic;
/*     */     }
/*     */     
/*     */     public boolean isSyncSend() {
/*  97 */       return this.syncSend;
/*     */     }
/*     */     
/*     */     public B setKey(String key) {
/* 101 */       this.key = key;
/* 102 */       return (B)asBuilder();
/*     */     }
/*     */     
/*     */     public B setSyncSend(boolean syncSend) {
/* 106 */       this.syncSend = syncSend;
/* 107 */       return (B)asBuilder();
/*     */     }
/*     */     
/*     */     public B setTopic(String topic) {
/* 111 */       this.topic = topic;
/* 112 */       return (B)asBuilder();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 117 */   private static final String[] KAFKA_CLIENT_PACKAGES = new String[] { "org.apache.kafka.common", "org.apache.kafka.clients" };
/*     */   
/*     */   private final Integer retryCount;
/*     */   
/*     */   private final KafkaManager manager;
/*     */   
/*     */   @Deprecated
/*     */   public static KafkaAppender createAppender(Layout<? extends Serializable> layout, Filter filter, String name, boolean ignoreExceptions, String topic, Property[] properties, Configuration configuration, String key) {
/* 125 */     if (layout == null) {
/* 126 */       AbstractLifeCycle.LOGGER.error("No layout provided for KafkaAppender");
/* 127 */       return null;
/*     */     } 
/* 129 */     KafkaManager kafkaManager = KafkaManager.getManager(configuration.getLoggerContext(), name, topic, true, properties, key);
/*     */     
/* 131 */     return new KafkaAppender(name, layout, filter, ignoreExceptions, kafkaManager, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isRecursive(LogEvent event) {
/* 141 */     return Stream.<String>of(KAFKA_CLIENT_PACKAGES).anyMatch(prefix -> event.getLoggerName().startsWith(prefix));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PluginBuilderFactory
/*     */   public static <B extends Builder<B>> B newBuilder() {
/* 151 */     return (B)(new Builder<>()).asBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private KafkaAppender(String name, Layout<? extends Serializable> layout, Filter filter, boolean ignoreExceptions, KafkaManager manager, Property[] properties, Integer retryCount) {
/* 161 */     super(name, filter, layout, ignoreExceptions, properties);
/* 162 */     this.manager = Objects.<KafkaManager>requireNonNull(manager, "manager");
/* 163 */     this.retryCount = retryCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/* 168 */     if (event.getLoggerName() != null && isRecursive(event)) {
/* 169 */       LOGGER.warn("Recursive logging from [{}] for appender [{}].", event.getLoggerName(), getName());
/*     */     } else {
/*     */       try {
/* 172 */         tryAppend(event);
/* 173 */       } catch (Exception e) {
/*     */         
/* 175 */         if (this.retryCount != null) {
/* 176 */           int currentRetryAttempt = 0;
/* 177 */           while (currentRetryAttempt < this.retryCount.intValue()) {
/* 178 */             currentRetryAttempt++;
/*     */             try {
/* 180 */               tryAppend(event);
/*     */               break;
/* 182 */             } catch (Exception exception) {}
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 187 */         error("Unable to write to Kafka in appender [" + getName() + "]", event, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/* 194 */     super.start();
/* 195 */     this.manager.startup();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean stop(long timeout, TimeUnit timeUnit) {
/* 200 */     setStopping();
/* 201 */     boolean stopped = stop(timeout, timeUnit, false);
/* 202 */     stopped &= this.manager.stop(timeout, timeUnit);
/* 203 */     setStopped();
/* 204 */     return stopped;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 209 */     return "KafkaAppender{name=" + getName() + ", state=" + getState() + ", topic=" + this.manager.getTopic() + '}';
/*     */   }
/*     */   private void tryAppend(LogEvent event) throws ExecutionException, InterruptedException, TimeoutException {
/*     */     byte[] data;
/* 213 */     Layout<? extends Serializable> layout = getLayout();
/*     */     
/* 215 */     if (layout instanceof org.apache.logging.log4j.core.layout.SerializedLayout) {
/* 216 */       byte[] header = layout.getHeader();
/* 217 */       byte[] body = layout.toByteArray(event);
/* 218 */       data = new byte[header.length + body.length];
/* 219 */       System.arraycopy(header, 0, data, 0, header.length);
/* 220 */       System.arraycopy(body, 0, data, header.length, body.length);
/*     */     } else {
/* 222 */       data = layout.toByteArray(event);
/*     */     } 
/* 224 */     this.manager.send(data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\appender\mom\kafka\KafkaAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */