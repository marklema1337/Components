/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Properties;
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
/*     */ public abstract class ExpressionFactory
/*     */ {
/*     */   public static ExpressionFactory newInstance() {
/*  91 */     return newInstance(null);
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
/*     */   public static ExpressionFactory newInstance(Properties properties) {
/*     */     ClassLoader classLoader;
/*     */     try {
/* 116 */       classLoader = Thread.currentThread().getContextClassLoader();
/* 117 */       if (classLoader == null) {
/* 118 */         classLoader = ExpressionFactory.class.getClassLoader();
/*     */       }
/* 120 */     } catch (SecurityException e) {
/*     */       
/* 122 */       classLoader = ExpressionFactory.class.getClassLoader();
/*     */     } 
/*     */     
/* 125 */     String className = null;
/*     */     
/* 127 */     String serviceId = "META-INF/services/" + ExpressionFactory.class.getName();
/* 128 */     InputStream input = classLoader.getResourceAsStream(serviceId);
/*     */     
/*     */     try {
/* 131 */       if (input != null)
/*     */       {
/* 133 */         BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
/* 134 */         className = reader.readLine();
/* 135 */         reader.close();
/*     */       }
/*     */     
/* 138 */     } catch (IOException iOException) {
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 144 */       if (input != null) {
/*     */         
/*     */         try {
/*     */           
/* 148 */           input.close();
/*     */         }
/* 150 */         catch (Exception exception) {
/*     */ 
/*     */         
/*     */         }
/*     */         finally {
/*     */           
/* 156 */           input = null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 161 */     if (className == null || className.trim().length() == 0) {
/*     */       
/*     */       try {
/*     */         
/* 165 */         String home = System.getProperty("java.home");
/* 166 */         if (home != null) {
/*     */           
/* 168 */           String path = String.valueOf(home) + File.separator + "lib" + File.separator + "el.properties";
/* 169 */           File file = new File(path);
/* 170 */           if (file.exists())
/*     */           {
/* 172 */             input = new FileInputStream(file);
/* 173 */             Properties props = new Properties();
/* 174 */             props.load(input);
/* 175 */             className = props.getProperty(ExpressionFactory.class.getName());
/*     */           }
/*     */         
/*     */         } 
/* 179 */       } catch (IOException iOException) {
/*     */ 
/*     */       
/*     */       }
/* 183 */       catch (SecurityException securityException) {
/*     */ 
/*     */       
/*     */       }
/*     */       finally {
/*     */         
/* 189 */         if (input != null) {
/*     */           
/*     */           try {
/*     */             
/* 193 */             input.close();
/*     */           }
/* 195 */           catch (IOException iOException) {
/*     */ 
/*     */           
/*     */           }
/*     */           finally {
/*     */             
/* 201 */             input = null;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 207 */     if (className == null || className.trim().length() == 0) {
/*     */       
/*     */       try {
/*     */         
/* 211 */         className = System.getProperty(ExpressionFactory.class.getName());
/*     */       }
/* 213 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (className == null || className.trim().length() == 0)
/*     */     {
/* 221 */       className = "com.lbs.juel.ExpressionFactoryImpl";
/*     */     }
/*     */     
/* 224 */     return newInstance(properties, className, classLoader);
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
/*     */   private static ExpressionFactory newInstance(Properties properties, String className, ClassLoader classLoader) {
/* 244 */     Class<?> clazz = null;
/*     */     
/*     */     try {
/* 247 */       clazz = classLoader.loadClass(className.trim());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 253 */     catch (ClassNotFoundException e) {
/*     */       
/* 255 */       throw new ELException("Could not find expression factory class", e);
/*     */     } 
/*     */     
/*     */     try {
/* 259 */       if (properties != null) {
/*     */         
/* 261 */         Constructor<?> constructor = null;
/*     */         
/*     */         try {
/* 264 */           constructor = clazz.getConstructor(new Class[] { Properties.class });
/*     */         }
/* 266 */         catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */         
/* 270 */         if (constructor != null)
/*     */         {
/* 272 */           return (ExpressionFactory)constructor.newInstance(new Object[] { properties });
/*     */         }
/*     */       } 
/* 275 */       return (ExpressionFactory)clazz.newInstance();
/*     */     }
/* 277 */     catch (Exception e) {
/*     */       
/* 279 */       throw new ELException("Could not create expression factory instance", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract Object coerceToType(Object paramObject, Class<?> paramClass);
/*     */   
/*     */   public abstract MethodExpression createMethodExpression(ELContext paramELContext, String paramString, Class<?> paramClass, Class<?>[] paramArrayOfClass);
/*     */   
/*     */   public abstract ValueExpression createValueExpression(ELContext paramELContext, String paramString, Class<?> paramClass);
/*     */   
/*     */   public abstract ValueExpression createValueExpression(Object paramObject, Class<?> paramClass);
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\ExpressionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */