/*     */ package org.apache.logging.log4j.core.appender.mom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.ConnectionFactory;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.MapMessage;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageConsumer;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.Session;
/*     */ import javax.naming.NamingException;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.AbstractManager;
/*     */ import org.apache.logging.log4j.core.appender.AppenderLoggingException;
/*     */ import org.apache.logging.log4j.core.appender.ManagerFactory;
/*     */ import org.apache.logging.log4j.core.net.JndiManager;
/*     */ import org.apache.logging.log4j.core.util.Log4jThread;
/*     */ import org.apache.logging.log4j.message.MapMessage;
/*     */ import org.apache.logging.log4j.status.StatusLogger;
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
/*     */ public class JmsManager
/*     */   extends AbstractManager
/*     */ {
/*     */   public static class JmsManagerConfiguration
/*     */   {
/*     */     private final Properties jndiProperties;
/*     */     private final String connectionFactoryName;
/*     */     private final String destinationName;
/*     */     private final String userName;
/*     */     private final char[] password;
/*     */     private final boolean immediateFail;
/*     */     private final boolean retry;
/*     */     private final long reconnectIntervalMillis;
/*     */     
/*     */     JmsManagerConfiguration(Properties jndiProperties, String connectionFactoryName, String destinationName, String userName, char[] password, boolean immediateFail, long reconnectIntervalMillis) {
/*  67 */       this.jndiProperties = jndiProperties;
/*  68 */       this.connectionFactoryName = connectionFactoryName;
/*  69 */       this.destinationName = destinationName;
/*  70 */       this.userName = userName;
/*  71 */       this.password = password;
/*  72 */       this.immediateFail = immediateFail;
/*  73 */       this.reconnectIntervalMillis = reconnectIntervalMillis;
/*  74 */       this.retry = (reconnectIntervalMillis > 0L);
/*     */     }
/*     */     
/*     */     public String getConnectionFactoryName() {
/*  78 */       return this.connectionFactoryName;
/*     */     }
/*     */     
/*     */     public String getDestinationName() {
/*  82 */       return this.destinationName;
/*     */     }
/*     */     
/*     */     public JndiManager getJndiManager() {
/*  86 */       return JndiManager.getJndiManager(getJndiProperties());
/*     */     }
/*     */     
/*     */     public Properties getJndiProperties() {
/*  90 */       return this.jndiProperties;
/*     */     }
/*     */     
/*     */     public char[] getPassword() {
/*  94 */       return this.password;
/*     */     }
/*     */     
/*     */     public long getReconnectIntervalMillis() {
/*  98 */       return this.reconnectIntervalMillis;
/*     */     }
/*     */     
/*     */     public String getUserName() {
/* 102 */       return this.userName;
/*     */     }
/*     */     
/*     */     public boolean isImmediateFail() {
/* 106 */       return this.immediateFail;
/*     */     }
/*     */     
/*     */     public boolean isRetry() {
/* 110 */       return this.retry;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 115 */       return "JmsManagerConfiguration [jndiProperties=" + this.jndiProperties + ", connectionFactoryName=" + this.connectionFactoryName + ", destinationName=" + this.destinationName + ", userName=" + this.userName + ", immediateFail=" + this.immediateFail + ", retry=" + this.retry + ", reconnectIntervalMillis=" + this.reconnectIntervalMillis + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class JmsManagerFactory
/*     */     implements ManagerFactory<JmsManager, JmsManagerConfiguration>
/*     */   {
/*     */     private JmsManagerFactory() {}
/*     */ 
/*     */     
/*     */     public JmsManager createManager(String name, JmsManager.JmsManagerConfiguration data) {
/* 127 */       if (JndiManager.isJndiJmsEnabled()) {
/*     */         try {
/* 129 */           return new JmsManager(name, data);
/* 130 */         } catch (Exception e) {
/* 131 */           JmsManager.logger().error("Error creating JmsManager using JmsManagerConfiguration [{}]", data, e);
/* 132 */           return null;
/*     */         } 
/*     */       }
/* 135 */       JmsManager.logger().error("JNDI must be enabled by setting log4j2.enableJndiJms=true");
/* 136 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class Reconnector
/*     */     extends Log4jThread
/*     */   {
/* 145 */     private final CountDownLatch latch = new CountDownLatch(1);
/*     */     
/*     */     private volatile boolean shutdown;
/*     */     
/*     */     private final Object owner;
/*     */     
/*     */     private Reconnector(Object owner) {
/* 152 */       super("JmsManager-Reconnector");
/* 153 */       this.owner = owner;
/*     */     }
/*     */     
/*     */     public void latch() {
/*     */       try {
/* 158 */         this.latch.await();
/* 159 */       } catch (InterruptedException interruptedException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     void reconnect() throws NamingException, JMSException {
/* 165 */       JndiManager jndiManager2 = JmsManager.this.getJndiManager();
/* 166 */       Connection connection2 = JmsManager.this.createConnection(jndiManager2);
/* 167 */       Session session2 = JmsManager.this.createSession(connection2);
/* 168 */       Destination destination2 = JmsManager.this.createDestination(jndiManager2);
/* 169 */       MessageProducer messageProducer2 = JmsManager.this.createMessageProducer(session2, destination2);
/* 170 */       connection2.start();
/* 171 */       synchronized (this.owner) {
/* 172 */         JmsManager.this.jndiManager = jndiManager2;
/* 173 */         JmsManager.this.connection = connection2;
/* 174 */         JmsManager.this.session = session2;
/* 175 */         JmsManager.this.destination = destination2;
/* 176 */         JmsManager.this.messageProducer = messageProducer2;
/* 177 */         JmsManager.this.reconnector = null;
/* 178 */         this.shutdown = true;
/*     */       } 
/* 180 */       JmsManager.logger().debug("Connection reestablished to {}", JmsManager.this.configuration);
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 185 */       while (!this.shutdown) {
/*     */         try {
/* 187 */           sleep(JmsManager.this.configuration.getReconnectIntervalMillis());
/* 188 */           reconnect();
/* 189 */         } catch (InterruptedException|JMSException|NamingException e) {
/* 190 */           JmsManager.logger().debug("Cannot reestablish JMS connection to {}: {}", JmsManager.this.configuration, e.getLocalizedMessage(), e);
/*     */         } finally {
/*     */           
/* 193 */           this.latch.countDown();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 199 */       this.shutdown = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 204 */   static final JmsManagerFactory FACTORY = new JmsManagerFactory();
/*     */ 
/*     */ 
/*     */   
/*     */   private final JmsManagerConfiguration configuration;
/*     */ 
/*     */   
/*     */   private volatile Reconnector reconnector;
/*     */ 
/*     */   
/*     */   private volatile JndiManager jndiManager;
/*     */ 
/*     */   
/*     */   private volatile Connection connection;
/*     */ 
/*     */   
/*     */   private volatile Session session;
/*     */ 
/*     */   
/*     */   private volatile Destination destination;
/*     */ 
/*     */   
/*     */   private volatile MessageProducer messageProducer;
/*     */ 
/*     */ 
/*     */   
/*     */   public static JmsManager getJmsManager(String name, Properties jndiProperties, String connectionFactoryName, String destinationName, String userName, char[] password, boolean immediateFail, long reconnectIntervalMillis) {
/* 231 */     JmsManagerConfiguration configuration = new JmsManagerConfiguration(jndiProperties, connectionFactoryName, destinationName, userName, password, immediateFail, reconnectIntervalMillis);
/*     */     
/* 233 */     return (JmsManager)getManager(name, FACTORY, configuration);
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
/*     */   private JmsManager(String name, JmsManagerConfiguration configuration) {
/* 246 */     super(null, name);
/* 247 */     this.configuration = configuration;
/* 248 */     this.jndiManager = configuration.getJndiManager();
/*     */     try {
/* 250 */       this.connection = createConnection(this.jndiManager);
/* 251 */       this.session = createSession(this.connection);
/* 252 */       this.destination = createDestination(this.jndiManager);
/* 253 */       this.messageProducer = createMessageProducer(this.session, this.destination);
/* 254 */       this.connection.start();
/* 255 */     } catch (NamingException|JMSException e) {
/* 256 */       this.reconnector = createReconnector();
/* 257 */       this.reconnector.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean closeConnection() {
/* 262 */     if (this.connection == null) {
/* 263 */       return true;
/*     */     }
/* 265 */     Connection temp = this.connection;
/* 266 */     this.connection = null;
/*     */     try {
/* 268 */       temp.close();
/* 269 */       return true;
/* 270 */     } catch (JMSException e) {
/* 271 */       StatusLogger.getLogger().debug("Caught exception closing JMS Connection: {} ({}); continuing JMS manager shutdown", e
/*     */           
/* 273 */           .getLocalizedMessage(), temp, e);
/* 274 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean closeJndiManager() {
/* 279 */     if (this.jndiManager == null) {
/* 280 */       return true;
/*     */     }
/* 282 */     JndiManager tmp = this.jndiManager;
/* 283 */     this.jndiManager = null;
/* 284 */     tmp.close();
/* 285 */     return true;
/*     */   }
/*     */   
/*     */   private boolean closeMessageProducer() {
/* 289 */     if (this.messageProducer == null) {
/* 290 */       return true;
/*     */     }
/* 292 */     MessageProducer temp = this.messageProducer;
/* 293 */     this.messageProducer = null;
/*     */     try {
/* 295 */       temp.close();
/* 296 */       return true;
/* 297 */     } catch (JMSException e) {
/* 298 */       StatusLogger.getLogger().debug("Caught exception closing JMS MessageProducer: {} ({}); continuing JMS manager shutdown", e
/*     */           
/* 300 */           .getLocalizedMessage(), temp, e);
/* 301 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean closeSession() {
/* 306 */     if (this.session == null) {
/* 307 */       return true;
/*     */     }
/* 309 */     Session temp = this.session;
/* 310 */     this.session = null;
/*     */     try {
/* 312 */       temp.close();
/* 313 */       return true;
/* 314 */     } catch (JMSException e) {
/* 315 */       StatusLogger.getLogger().debug("Caught exception closing JMS Session: {} ({}); continuing JMS manager shutdown", e
/*     */           
/* 317 */           .getLocalizedMessage(), temp, e);
/* 318 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Connection createConnection(JndiManager jndiManager) throws NamingException, JMSException {
/* 323 */     ConnectionFactory connectionFactory = (ConnectionFactory)jndiManager.lookup(this.configuration.getConnectionFactoryName());
/* 324 */     if (this.configuration.getUserName() != null && this.configuration.getPassword() != null) {
/* 325 */       return connectionFactory.createConnection(this.configuration.getUserName(), 
/* 326 */           (this.configuration.getPassword() == null) ? null : String.valueOf(this.configuration.getPassword()));
/*     */     }
/* 328 */     return connectionFactory.createConnection();
/*     */   }
/*     */ 
/*     */   
/*     */   private Destination createDestination(JndiManager jndiManager) throws NamingException {
/* 333 */     return (Destination)jndiManager.lookup(this.configuration.getDestinationName());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Message createMessage(Serializable object) throws JMSException {
/* 358 */     if (object instanceof String)
/* 359 */       return (Message)this.session.createTextMessage((String)object); 
/* 360 */     if (object instanceof MapMessage) {
/* 361 */       return (Message)map((MapMessage<?, ?>)object, this.session.createMapMessage());
/*     */     }
/* 363 */     return (Message)this.session.createObjectMessage(object);
/*     */   }
/*     */   
/*     */   private void createMessageAndSend(LogEvent event, Serializable serializable) throws JMSException {
/* 367 */     Message message = createMessage(serializable);
/* 368 */     message.setJMSTimestamp(event.getTimeMillis());
/* 369 */     this.messageProducer.send(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageConsumer createMessageConsumer() throws JMSException {
/* 379 */     return this.session.createConsumer(this.destination);
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
/*     */   public MessageProducer createMessageProducer(Session session, Destination destination) throws JMSException {
/* 394 */     return session.createProducer(destination);
/*     */   }
/*     */   
/*     */   private Reconnector createReconnector() {
/* 398 */     Reconnector recon = new Reconnector(this);
/* 399 */     recon.setDaemon(true);
/* 400 */     recon.setPriority(1);
/* 401 */     return recon;
/*     */   }
/*     */   
/*     */   private Session createSession(Connection connection) throws JMSException {
/* 405 */     return connection.createSession(false, 1);
/*     */   }
/*     */   
/*     */   public JmsManagerConfiguration getJmsManagerConfiguration() {
/* 409 */     return this.configuration;
/*     */   }
/*     */   
/*     */   JndiManager getJndiManager() {
/* 413 */     return this.configuration.getJndiManager();
/*     */   }
/*     */   
/*     */   <T> T lookup(String destinationName) throws NamingException {
/* 417 */     return (T)this.jndiManager.lookup(destinationName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private MapMessage map(MapMessage<?, ?> log4jMapMessage, MapMessage jmsMapMessage) {
/* 423 */     log4jMapMessage.forEach((key, value) -> {
/*     */           try {
/*     */             jmsMapMessage.setObject(key, value);
/* 426 */           } catch (JMSException e) {
/*     */             throw new IllegalArgumentException(String.format("%s mapping key '%s' to value '%s': %s", new Object[] { e.getClass(), key, value, e.getLocalizedMessage() }), e);
/*     */           } 
/*     */         });
/*     */     
/* 431 */     return jmsMapMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean releaseSub(long timeout, TimeUnit timeUnit) {
/* 436 */     if (this.reconnector != null) {
/* 437 */       this.reconnector.shutdown();
/* 438 */       this.reconnector.interrupt();
/* 439 */       this.reconnector = null;
/*     */     } 
/* 441 */     boolean closed = false;
/* 442 */     closed &= closeJndiManager();
/* 443 */     closed &= closeMessageProducer();
/* 444 */     closed &= closeSession();
/* 445 */     closed &= closeConnection();
/* 446 */     return (closed && this.jndiManager.stop(timeout, timeUnit));
/*     */   }
/*     */   
/*     */   void send(LogEvent event, Serializable serializable) {
/* 450 */     if (this.messageProducer == null && 
/* 451 */       this.reconnector != null && !this.configuration.isImmediateFail()) {
/* 452 */       this.reconnector.latch();
/* 453 */       if (this.messageProducer == null) {
/* 454 */         throw new AppenderLoggingException("Error sending to JMS Manager '" + 
/* 455 */             getName() + "': JMS message producer not available");
/*     */       }
/*     */     } 
/*     */     
/* 459 */     synchronized (this) {
/*     */       try {
/* 461 */         createMessageAndSend(event, serializable);
/* 462 */       } catch (JMSException causeEx) {
/* 463 */         if (this.configuration.isRetry() && this.reconnector == null) {
/* 464 */           this.reconnector = createReconnector();
/*     */           try {
/* 466 */             closeJndiManager();
/* 467 */             this.reconnector.reconnect();
/* 468 */           } catch (NamingException|JMSException reconnEx) {
/* 469 */             logger().debug("Cannot reestablish JMS connection to {}: {}; starting reconnector thread {}", this.configuration, reconnEx
/* 470 */                 .getLocalizedMessage(), this.reconnector.getName(), reconnEx);
/* 471 */             this.reconnector.start();
/* 472 */             throw new AppenderLoggingException(
/* 473 */                 String.format("JMS exception sending to %s for %s", new Object[] { getName(), this.configuration }), causeEx);
/*     */           } 
/*     */           try {
/* 476 */             createMessageAndSend(event, serializable);
/* 477 */           } catch (JMSException e) {
/* 478 */             throw new AppenderLoggingException(
/* 479 */                 String.format("Error sending to %s after reestablishing JMS connection for %s", new Object[] {
/* 480 */                     getName(), this.configuration
/*     */                   }), causeEx);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\appender\mom\JmsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */