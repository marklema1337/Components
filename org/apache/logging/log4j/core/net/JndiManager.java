/*     */ package org.apache.logging.log4j.core.net;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.appender.AbstractManager;
/*     */ import org.apache.logging.log4j.core.appender.ManagerFactory;
/*     */ import org.apache.logging.log4j.core.util.JndiCloser;
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
/*     */ public class JndiManager
/*     */   extends AbstractManager
/*     */ {
/*  41 */   private static final JndiManagerFactory FACTORY = new JndiManagerFactory();
/*     */   
/*     */   private static final String PREFIX = "log4j2.enableJndi";
/*     */   private static final String JAVA_SCHEME = "java";
/*  45 */   private static final boolean JNDI_CONTEXT_SELECTOR_ENABLED = isJndiEnabled("ContextSelector");
/*  46 */   private static final boolean JNDI_JDBC_ENABLED = isJndiEnabled("Jdbc");
/*  47 */   private static final boolean JNDI_JMS_ENABLED = isJndiEnabled("Jms");
/*  48 */   private static final boolean JNDI_LOOKUP_ENABLED = isJndiEnabled("Lookup");
/*     */   
/*     */   private final InitialContext context;
/*     */   
/*     */   private static boolean isJndiEnabled(String subKey) {
/*  53 */     return PropertiesUtil.getProperties().getBooleanProperty("log4j2.enableJndi" + subKey, false);
/*     */   }
/*     */   
/*     */   public static boolean isJndiEnabled() {
/*  57 */     return (isJndiContextSelectorEnabled() || isJndiJdbcEnabled() || isJndiJmsEnabled() || isJndiLookupEnabled());
/*     */   }
/*     */   
/*     */   public static boolean isJndiContextSelectorEnabled() {
/*  61 */     return JNDI_CONTEXT_SELECTOR_ENABLED;
/*     */   }
/*     */   
/*     */   public static boolean isJndiJdbcEnabled() {
/*  65 */     return JNDI_JDBC_ENABLED;
/*     */   }
/*     */   
/*     */   public static boolean isJndiJmsEnabled() {
/*  69 */     return JNDI_JMS_ENABLED;
/*     */   }
/*     */   
/*     */   public static boolean isJndiLookupEnabled() {
/*  73 */     return JNDI_LOOKUP_ENABLED;
/*     */   }
/*     */   
/*     */   private JndiManager(String name, InitialContext context) {
/*  77 */     super(null, name);
/*  78 */     this.context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JndiManager getDefaultManager() {
/*  87 */     return (JndiManager)getManager(JndiManager.class.getName(), FACTORY, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JndiManager getDefaultManager(String name) {
/*  97 */     return (JndiManager)getManager(name, FACTORY, null);
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
/*     */   public static JndiManager getJndiManager(String initialContextFactoryName, String providerURL, String urlPkgPrefixes, String securityPrincipal, String securityCredentials, Properties additionalProperties) {
/* 119 */     Properties properties = createProperties(initialContextFactoryName, providerURL, urlPkgPrefixes, securityPrincipal, securityCredentials, additionalProperties);
/*     */     
/* 121 */     return (JndiManager)getManager(createManagerName(), FACTORY, properties);
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
/*     */   public static JndiManager getJndiManager(Properties properties) {
/* 133 */     return (JndiManager)getManager(createManagerName(), FACTORY, properties);
/*     */   }
/*     */   
/*     */   private static String createManagerName() {
/* 137 */     return JndiManager.class.getName() + '@' + JndiManager.class.hashCode();
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
/*     */   public static Properties createProperties(String initialContextFactoryName, String providerURL, String urlPkgPrefixes, String securityPrincipal, String securityCredentials, Properties additionalProperties) {
/* 162 */     if (initialContextFactoryName == null) {
/* 163 */       return null;
/*     */     }
/* 165 */     Properties properties = new Properties();
/* 166 */     properties.setProperty("java.naming.factory.initial", initialContextFactoryName);
/* 167 */     if (providerURL != null) {
/* 168 */       properties.setProperty("java.naming.provider.url", providerURL);
/*     */     } else {
/* 170 */       LOGGER.warn("The JNDI InitialContextFactory class name [{}] was provided, but there was no associated provider URL. This is likely to cause problems.", initialContextFactoryName);
/*     */     } 
/*     */     
/* 173 */     if (urlPkgPrefixes != null) {
/* 174 */       properties.setProperty("java.naming.factory.url.pkgs", urlPkgPrefixes);
/*     */     }
/* 176 */     if (securityPrincipal != null) {
/* 177 */       properties.setProperty("java.naming.security.principal", securityPrincipal);
/* 178 */       if (securityCredentials != null) {
/* 179 */         properties.setProperty("java.naming.security.credentials", securityCredentials);
/*     */       } else {
/* 181 */         LOGGER.warn("A security principal [{}] was provided, but with no corresponding security credentials.", securityPrincipal);
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     if (additionalProperties != null) {
/* 186 */       properties.putAll(additionalProperties);
/*     */     }
/* 188 */     return properties;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean releaseSub(long timeout, TimeUnit timeUnit) {
/* 193 */     return JndiCloser.closeSilently(this.context);
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
/*     */   public <T> T lookup(String name) throws NamingException {
/* 206 */     if (this.context == null) {
/* 207 */       return null;
/*     */     }
/*     */     try {
/* 210 */       URI uri = new URI(name);
/* 211 */       if (uri.getScheme() == null || uri.getScheme().equals("java")) {
/* 212 */         return (T)this.context.lookup(name);
/*     */       }
/* 214 */       LOGGER.warn("Unsupported JNDI URI - {}", name);
/* 215 */     } catch (URISyntaxException ex) {
/* 216 */       LOGGER.warn("Invalid JNDI URI - {}", name);
/*     */     } 
/* 218 */     return null;
/*     */   }
/*     */   
/*     */   private static class JndiManagerFactory implements ManagerFactory<JndiManager, Properties> {
/*     */     private JndiManagerFactory() {}
/*     */     
/*     */     public JndiManager createManager(String name, Properties data) {
/* 225 */       if (!JndiManager.isJndiEnabled()) {
/* 226 */         throw new IllegalStateException(String.format("JNDI must be enabled by setting one of the %s* properties to true", new Object[] { "log4j2.enableJndi" }));
/*     */       }
/*     */       try {
/* 229 */         return new JndiManager(name, new InitialContext(data));
/* 230 */       } catch (NamingException e) {
/* 231 */         JndiManager.LOGGER.error("Error creating JNDI InitialContext for '{}'.", name, e);
/* 232 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 239 */     return "JndiManager [context=" + this.context + ", count=" + this.count + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\net\JndiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */