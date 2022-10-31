/*     */ package org.apache.logging.log4j.core.appender.db.jdbc;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.logging.Logger;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginFactory;
/*     */ import org.apache.logging.log4j.core.util.Loader;
/*     */ import org.apache.logging.log4j.status.StatusLogger;
/*     */ import org.apache.logging.log4j.util.Strings;
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
/*     */ @Plugin(name = "ConnectionFactory", category = "Core", elementType = "connectionSource", printObject = true)
/*     */ public final class FactoryMethodConnectionSource
/*     */   extends AbstractConnectionSource
/*     */ {
/*  40 */   private static final Logger LOGGER = (Logger)StatusLogger.getLogger();
/*     */   
/*     */   private final DataSource dataSource;
/*     */   
/*     */   private final String description;
/*     */   
/*     */   private FactoryMethodConnectionSource(DataSource dataSource, String className, String methodName, String returnType) {
/*  47 */     this.dataSource = dataSource;
/*  48 */     this.description = "factory{ public static " + returnType + ' ' + className + '.' + methodName + "() }";
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection getConnection() throws SQLException {
/*  53 */     return this.dataSource.getConnection();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  58 */     return this.description;
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
/*     */   @PluginFactory
/*     */   public static FactoryMethodConnectionSource createConnectionSource(@PluginAttribute("class") String className, @PluginAttribute("method") String methodName) {
/*     */     final Method method;
/*     */     DataSource dataSource;
/*  75 */     if (Strings.isEmpty(className) || Strings.isEmpty(methodName)) {
/*  76 */       LOGGER.error("No class name or method name specified for the connection factory method.");
/*  77 */       return null;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  82 */       Class<?> factoryClass = Loader.loadClass(className);
/*  83 */       method = factoryClass.getMethod(methodName, new Class[0]);
/*  84 */     } catch (Exception e) {
/*  85 */       LOGGER.error(e.toString(), e);
/*  86 */       return null;
/*     */     } 
/*     */     
/*  89 */     Class<?> returnType = method.getReturnType();
/*  90 */     String returnTypeString = returnType.getName();
/*     */     
/*  92 */     if (returnType == DataSource.class) {
/*     */       try {
/*  94 */         dataSource = (DataSource)method.invoke(null, new Object[0]);
/*  95 */         returnTypeString = returnTypeString + "[" + dataSource + ']';
/*  96 */       } catch (Exception e) {
/*  97 */         LOGGER.error(e.toString(), e);
/*  98 */         return null;
/*     */       } 
/* 100 */     } else if (returnType == Connection.class) {
/* 101 */       dataSource = new DataSource()
/*     */         {
/*     */           public Connection getConnection() throws SQLException {
/*     */             try {
/* 105 */               return (Connection)method.invoke(null, new Object[0]);
/* 106 */             } catch (Exception e) {
/* 107 */               throw new SQLException("Failed to obtain connection from factory method.", e);
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           public Connection getConnection(String username, String password) throws SQLException {
/* 113 */             throw new UnsupportedOperationException();
/*     */           }
/*     */ 
/*     */           
/*     */           public int getLoginTimeout() throws SQLException {
/* 118 */             throw new UnsupportedOperationException();
/*     */           }
/*     */ 
/*     */           
/*     */           public PrintWriter getLogWriter() throws SQLException {
/* 123 */             throw new UnsupportedOperationException();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public Logger getParentLogger() {
/* 129 */             throw new UnsupportedOperationException();
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 134 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public void setLoginTimeout(int seconds) throws SQLException {
/* 139 */             throw new UnsupportedOperationException();
/*     */           }
/*     */ 
/*     */           
/*     */           public void setLogWriter(PrintWriter out) throws SQLException {
/* 144 */             throw new UnsupportedOperationException();
/*     */           }
/*     */ 
/*     */           
/*     */           public <T> T unwrap(Class<T> iface) throws SQLException {
/* 149 */             return null;
/*     */           }
/*     */         };
/*     */     } else {
/* 153 */       LOGGER.error("Method [{}.{}()] returns unsupported type [{}].", className, methodName, returnType
/* 154 */           .getName());
/* 155 */       return null;
/*     */     } 
/*     */     
/* 158 */     return new FactoryMethodConnectionSource(dataSource, className, methodName, returnTypeString);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\appender\db\jdbc\FactoryMethodConnectionSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */