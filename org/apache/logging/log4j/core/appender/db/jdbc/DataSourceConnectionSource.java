/*    */ package org.apache.logging.log4j.core.appender.db.jdbc;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Objects;
/*    */ import javax.naming.NamingException;
/*    */ import javax.sql.DataSource;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*    */ import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
/*    */ import org.apache.logging.log4j.core.config.plugins.PluginFactory;
/*    */ import org.apache.logging.log4j.core.net.JndiManager;
/*    */ import org.apache.logging.log4j.status.StatusLogger;
/*    */ import org.apache.logging.log4j.util.Strings;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Plugin(name = "DataSource", category = "Core", elementType = "connectionSource", printObject = true)
/*    */ public final class DataSourceConnectionSource
/*    */   extends AbstractConnectionSource
/*    */ {
/* 40 */   private static final Logger LOGGER = (Logger)StatusLogger.getLogger();
/*    */   
/*    */   private final DataSource dataSource;
/*    */   private final String description;
/*    */   
/*    */   private DataSourceConnectionSource(String dataSourceName, DataSource dataSource) {
/* 46 */     this.dataSource = Objects.<DataSource>requireNonNull(dataSource, "dataSource");
/* 47 */     this.description = "dataSource{ name=" + dataSourceName + ", value=" + dataSource + " }";
/*    */   }
/*    */ 
/*    */   
/*    */   public Connection getConnection() throws SQLException {
/* 52 */     return this.dataSource.getConnection();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PluginFactory
/*    */   public static DataSourceConnectionSource createConnectionSource(@PluginAttribute("jndiName") String jndiName) {
/* 68 */     if (!JndiManager.isJndiJdbcEnabled()) {
/* 69 */       LOGGER.error("JNDI must be enabled by setting log4j2.enableJndiJdbc=true");
/* 70 */       return null;
/*    */     } 
/* 72 */     if (Strings.isEmpty(jndiName)) {
/* 73 */       LOGGER.error("No JNDI name provided.");
/* 74 */       return null;
/*    */     } 
/*    */     
/*    */     try {
/* 78 */       DataSource dataSource = (DataSource)JndiManager.getDefaultManager(DataSourceConnectionSource.class.getCanonicalName()).lookup(jndiName);
/* 79 */       if (dataSource == null) {
/* 80 */         LOGGER.error("No DataSource found with JNDI name [" + jndiName + "].");
/* 81 */         return null;
/*    */       } 
/* 83 */       return new DataSourceConnectionSource(jndiName, dataSource);
/* 84 */     } catch (NamingException e) {
/* 85 */       LOGGER.error(e.getMessage(), e);
/* 86 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\appender\db\jdbc\DataSourceConnectionSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */