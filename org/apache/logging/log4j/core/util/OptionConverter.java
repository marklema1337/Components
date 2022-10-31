/*     */ package org.apache.logging.log4j.core.util;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.lookup.StrSubstitutor;
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
/*     */ public final class OptionConverter
/*     */ {
/*  33 */   private static final Logger LOGGER = (Logger)StatusLogger.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int ONE_K = 1024;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] concatenateArrays(String[] l, String[] r) {
/*  44 */     int len = l.length + r.length;
/*  45 */     String[] a = new String[len];
/*     */     
/*  47 */     System.arraycopy(l, 0, a, 0, l.length);
/*  48 */     System.arraycopy(r, 0, a, l.length, r.length);
/*     */     
/*  50 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String convertSpecialChars(String s) {
/*  55 */     int len = s.length();
/*  56 */     StringBuilder sbuf = new StringBuilder(len);
/*     */     
/*  58 */     int i = 0;
/*  59 */     while (i < len) {
/*  60 */       char c = s.charAt(i++);
/*  61 */       if (c == '\\') {
/*  62 */         c = s.charAt(i++);
/*  63 */         switch (c) {
/*     */           case 'n':
/*  65 */             c = '\n';
/*     */             break;
/*     */           case 'r':
/*  68 */             c = '\r';
/*     */             break;
/*     */           case 't':
/*  71 */             c = '\t';
/*     */             break;
/*     */           case 'f':
/*  74 */             c = '\f';
/*     */             break;
/*     */           case 'b':
/*  77 */             c = '\b';
/*     */             break;
/*     */           case '"':
/*  80 */             c = '"';
/*     */             break;
/*     */           case '\'':
/*  83 */             c = '\'';
/*     */             break;
/*     */           case '\\':
/*  86 */             c = '\\';
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*  92 */       sbuf.append(c);
/*     */     } 
/*  94 */     return sbuf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object instantiateByKey(Properties props, String key, Class<?> superClass, Object defaultValue) {
/* 101 */     String className = findAndSubst(key, props);
/* 102 */     if (className == null) {
/* 103 */       LOGGER.error("Could not find value for key {}", key);
/* 104 */       return defaultValue;
/*     */     } 
/*     */     
/* 107 */     return instantiateByClassName(className.trim(), superClass, defaultValue);
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
/*     */   public static boolean toBoolean(String value, boolean defaultValue) {
/* 123 */     if (value == null) {
/* 124 */       return defaultValue;
/*     */     }
/* 126 */     String trimmedVal = value.trim();
/* 127 */     if ("true".equalsIgnoreCase(trimmedVal)) {
/* 128 */       return true;
/*     */     }
/* 130 */     if ("false".equalsIgnoreCase(trimmedVal)) {
/* 131 */       return false;
/*     */     }
/* 133 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int toInt(String value, int defaultValue) {
/* 143 */     if (value != null) {
/* 144 */       String s = value.trim();
/*     */       try {
/* 146 */         return Integer.parseInt(s);
/* 147 */       } catch (NumberFormatException e) {
/* 148 */         LOGGER.error("[{}] is not in proper int form.", s, e);
/*     */       } 
/*     */     } 
/* 151 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public static Level toLevel(String value, Level defaultValue) {
/* 155 */     if (value == null) {
/* 156 */       return defaultValue;
/*     */     }
/*     */     
/* 159 */     value = value.trim();
/*     */     
/* 161 */     int hashIndex = value.indexOf('#');
/* 162 */     if (hashIndex == -1) {
/* 163 */       if ("NULL".equalsIgnoreCase(value)) {
/* 164 */         return null;
/*     */       }
/*     */       
/* 167 */       return Level.toLevel(value, defaultValue);
/*     */     } 
/*     */     
/* 170 */     Level result = defaultValue;
/*     */     
/* 172 */     String clazz = value.substring(hashIndex + 1);
/* 173 */     String levelName = value.substring(0, hashIndex);
/*     */ 
/*     */     
/* 176 */     if ("NULL".equalsIgnoreCase(levelName)) {
/* 177 */       return null;
/*     */     }
/*     */     
/* 180 */     LOGGER.debug("toLevel:class=[" + clazz + "]:pri=[" + levelName + "]");
/*     */ 
/*     */     
/*     */     try {
/* 184 */       Class<?> customLevel = Loader.loadClass(clazz);
/*     */ 
/*     */ 
/*     */       
/* 188 */       Class<?>[] paramTypes = new Class[] { String.class, Level.class };
/*     */       
/* 190 */       Method toLevelMethod = customLevel.getMethod("toLevel", paramTypes);
/*     */ 
/*     */       
/* 193 */       Object[] params = { levelName, defaultValue };
/* 194 */       Object o = toLevelMethod.invoke(null, params);
/*     */       
/* 196 */       result = (Level)o;
/* 197 */     } catch (ClassNotFoundException e) {
/* 198 */       LOGGER.warn("custom level class [" + clazz + "] not found.");
/* 199 */     } catch (NoSuchMethodException e) {
/* 200 */       LOGGER.warn("custom level class [" + clazz + "] does not have a class function toLevel(String, Level)", e);
/*     */     }
/* 202 */     catch (InvocationTargetException e) {
/* 203 */       if (e.getTargetException() instanceof InterruptedException || e
/* 204 */         .getTargetException() instanceof java.io.InterruptedIOException) {
/* 205 */         Thread.currentThread().interrupt();
/*     */       }
/* 207 */       LOGGER.warn("custom level class [" + clazz + "] could not be instantiated", e);
/* 208 */     } catch (ClassCastException e) {
/* 209 */       LOGGER.warn("class [" + clazz + "] is not a subclass of org.apache.log4j.Level", e);
/* 210 */     } catch (IllegalAccessException e) {
/* 211 */       LOGGER.warn("class [" + clazz + "] cannot be instantiated due to access restrictions", e);
/* 212 */     } catch (RuntimeException e) {
/* 213 */       LOGGER.warn("class [" + clazz + "], level [" + levelName + "] conversion failed.", e);
/*     */     } 
/* 215 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long toFileSize(String value, long defaultValue) {
/* 225 */     if (value == null) {
/* 226 */       return defaultValue;
/*     */     }
/*     */     
/* 229 */     String str = value.trim().toUpperCase(Locale.ENGLISH);
/* 230 */     long multiplier = 1L;
/*     */     
/*     */     int index;
/* 233 */     if ((index = str.indexOf("KB")) != -1) {
/* 234 */       multiplier = 1024L;
/* 235 */       str = str.substring(0, index);
/* 236 */     } else if ((index = str.indexOf("MB")) != -1) {
/* 237 */       multiplier = 1048576L;
/* 238 */       str = str.substring(0, index);
/* 239 */     } else if ((index = str.indexOf("GB")) != -1) {
/* 240 */       multiplier = 1073741824L;
/* 241 */       str = str.substring(0, index);
/*     */     } 
/*     */     try {
/* 244 */       return Long.parseLong(str) * multiplier;
/* 245 */     } catch (NumberFormatException e) {
/* 246 */       LOGGER.error("[{}] is not in proper int form.", str);
/* 247 */       LOGGER.error("[{}] not in expected format.", value, e);
/*     */       
/* 249 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String findAndSubst(String key, Properties props) {
/* 261 */     String value = props.getProperty(key);
/* 262 */     if (value == null) {
/* 263 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 267 */       return substVars(value, props);
/* 268 */     } catch (IllegalArgumentException e) {
/* 269 */       LOGGER.error("Bad option value [{}].", value, e);
/* 270 */       return value;
/*     */     } 
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
/*     */   public static Object instantiateByClassName(String className, Class<?> superClass, Object defaultValue) {
/* 287 */     if (className != null) {
/*     */       try {
/* 289 */         Class<?> classObj = Loader.loadClass(className);
/* 290 */         if (!superClass.isAssignableFrom(classObj)) {
/* 291 */           LOGGER.error("A \"{}\" object is not assignable to a \"{}\" variable.", className, superClass
/* 292 */               .getName());
/* 293 */           LOGGER.error("The class \"{}\" was loaded by [{}] whereas object of type [{}] was loaded by [{}].", superClass
/* 294 */               .getName(), superClass.getClassLoader(), classObj.getTypeName(), classObj.getName());
/* 295 */           return defaultValue;
/*     */         } 
/* 297 */         return classObj.newInstance();
/* 298 */       } catch (Exception e) {
/* 299 */         LOGGER.error("Could not instantiate class [{}].", className, e);
/*     */       } 
/*     */     }
/* 302 */     return defaultValue;
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
/*     */   public static String substVars(String val, Properties props) throws IllegalArgumentException {
/* 344 */     return StrSubstitutor.replace(val, props);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\cor\\util\OptionConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */