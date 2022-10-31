/*     */ package com.lbs.juel;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.ExpressionFactory;
/*     */ import com.lbs.javax.el.MethodExpression;
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.juel.misc.TypeConverter;
/*     */ import com.lbs.juel.tree.TreeBuilder;
/*     */ import com.lbs.juel.tree.TreeCache;
/*     */ import com.lbs.juel.tree.TreeStore;
/*     */ import com.lbs.juel.tree.impl.Builder;
/*     */ import com.lbs.juel.tree.impl.Cache;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.EnumSet;
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
/*     */ public class ExpressionFactoryImpl
/*     */   extends ExpressionFactory
/*     */ {
/*     */   public static final String PROP_METHOD_INVOCATIONS = "javax.el.methodInvocations";
/*     */   public static final String PROP_VAR_ARGS = "javax.el.varArgs";
/*     */   public static final String PROP_NULL_PROPERTIES = "javax.el.nullProperties";
/*     */   public static final String PROP_IGNORE_RETURN_TYPE = "javax.el.ignoreReturnType";
/*     */   public static final String PROP_CACHE_SIZE = "javax.el.cacheSize";
/*     */   private final TreeStore store;
/*     */   private final TypeConverter converter;
/*     */   
/*     */   public enum Profile
/*     */   {
/*  87 */     JEE5(
/*     */ 
/*     */       
/*  90 */       (String)EnumSet.noneOf(Builder.Feature.class)),
/*  91 */     JEE6(
/*     */ 
/*     */ 
/*     */       
/*  95 */       (String)EnumSet.of(Builder.Feature.METHOD_INVOCATIONS, Builder.Feature.VARARGS));
/*     */     
/*     */     private final EnumSet<Builder.Feature> features;
/*     */ 
/*     */     
/*     */     Profile(EnumSet<Builder.Feature> features) {
/* 101 */       this.features = features;
/*     */     }
/*     */ 
/*     */     
/*     */     Builder.Feature[] features() {
/* 106 */       return (Builder.Feature[])this.features.toArray((Object[])new Builder.Feature[this.features.size()]);
/*     */     }
/*     */ 
/*     */     
/*     */     boolean contains(Builder.Feature feature) {
/* 111 */       return this.features.contains(feature);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpressionFactoryImpl() {
/* 151 */     this(Profile.JEE6);
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
/*     */   public ExpressionFactoryImpl(Profile profile) {
/* 167 */     Properties properties = loadProperties("el.properties");
/* 168 */     this.store = createTreeStore(1000, profile, properties);
/* 169 */     this.converter = createTypeConverter(properties);
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
/*     */   public ExpressionFactoryImpl(Properties properties) {
/* 183 */     this(Profile.JEE6, properties);
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
/*     */   public ExpressionFactoryImpl(Profile profile, Properties properties) {
/* 200 */     this.store = createTreeStore(1000, profile, properties);
/* 201 */     this.converter = createTypeConverter(properties);
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
/*     */   public ExpressionFactoryImpl(Properties properties, TypeConverter converter) {
/* 217 */     this(Profile.JEE6, properties, converter);
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
/*     */   public ExpressionFactoryImpl(Profile profile, Properties properties, TypeConverter converter) {
/* 236 */     this.store = createTreeStore(1000, profile, properties);
/* 237 */     this.converter = converter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpressionFactoryImpl(TreeStore store) {
/* 248 */     this(store, TypeConverter.DEFAULT);
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
/*     */   public ExpressionFactoryImpl(TreeStore store, TypeConverter converter) {
/* 261 */     this.store = store;
/* 262 */     this.converter = converter;
/*     */   }
/*     */ 
/*     */   
/*     */   private Properties loadDefaultProperties() {
/* 267 */     String home = System.getProperty("java.home");
/* 268 */     String path = String.valueOf(home) + File.separator + "lib" + File.separator + "el.properties";
/* 269 */     File file = new File(path);
/* 270 */     if (file.exists()) {
/*     */       
/* 272 */       Properties properties = new Properties();
/* 273 */       InputStream input = null;
/*     */       
/*     */       try {
/* 276 */         properties.load(input = new FileInputStream(file));
/*     */       }
/* 278 */       catch (IOException e) {
/*     */         
/* 280 */         throw new ELException("Cannot read default EL properties", e);
/*     */       } finally {
/*     */ 
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */           
/* 288 */           if (input != null) {
/* 289 */             input.close();
/*     */           }
/* 291 */         } catch (IOException iOException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 296 */       if (getClass().getName().equals(properties.getProperty("javax.el.ExpressionFactory")))
/*     */       {
/* 298 */         return properties;
/*     */       }
/*     */     } 
/* 301 */     if (getClass().getName().equals(System.getProperty("javax.el.ExpressionFactory")))
/*     */     {
/* 303 */       return System.getProperties();
/*     */     }
/* 305 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Properties loadProperties(String path) {
/* 310 */     Properties properties = new Properties(loadDefaultProperties());
/*     */ 
/*     */     
/* 313 */     InputStream input = null;
/*     */     
/*     */     try {
/* 316 */       input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
/*     */     }
/* 318 */     catch (SecurityException e) {
/*     */       
/* 320 */       input = ClassLoader.getSystemResourceAsStream(path);
/*     */     } 
/* 322 */     if (input != null) {
/*     */       
/*     */       try {
/*     */         
/* 326 */         properties.load(input);
/*     */       }
/* 328 */       catch (IOException e) {
/*     */         
/* 330 */         throw new ELException("Cannot read EL properties", e);
/*     */       } finally {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 336 */           input.close();
/*     */         }
/* 338 */         catch (IOException iOException) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     return properties;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean getFeatureProperty(Profile profile, Properties properties, Builder.Feature feature, String property) {
/* 350 */     return Boolean.valueOf(properties.getProperty(property, String.valueOf(profile.contains(feature)))).booleanValue();
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
/*     */   protected TreeStore createTreeStore(int defaultCacheSize, Profile profile, Properties properties) {
/* 362 */     TreeBuilder builder = null;
/* 363 */     if (properties == null) {
/*     */       
/* 365 */       builder = createTreeBuilder(null, profile.features());
/*     */     }
/*     */     else {
/*     */       
/* 369 */       EnumSet<Builder.Feature> features = EnumSet.noneOf(Builder.Feature.class);
/* 370 */       if (getFeatureProperty(profile, properties, Builder.Feature.METHOD_INVOCATIONS, "javax.el.methodInvocations"))
/*     */       {
/* 372 */         features.add(Builder.Feature.METHOD_INVOCATIONS);
/*     */       }
/* 374 */       if (getFeatureProperty(profile, properties, Builder.Feature.VARARGS, "javax.el.varArgs"))
/*     */       {
/* 376 */         features.add(Builder.Feature.VARARGS);
/*     */       }
/* 378 */       if (getFeatureProperty(profile, properties, Builder.Feature.NULL_PROPERTIES, "javax.el.nullProperties"))
/*     */       {
/* 380 */         features.add(Builder.Feature.NULL_PROPERTIES);
/*     */       }
/* 382 */       if (getFeatureProperty(profile, properties, Builder.Feature.IGNORE_RETURN_TYPE, "javax.el.ignoreReturnType"))
/*     */       {
/* 384 */         features.add(Builder.Feature.IGNORE_RETURN_TYPE);
/*     */       }
/* 386 */       builder = createTreeBuilder(properties, (Builder.Feature[])features.toArray((Object[])new Builder.Feature[0]));
/*     */     } 
/*     */ 
/*     */     
/* 390 */     int cacheSize = defaultCacheSize;
/* 391 */     if (properties != null && properties.containsKey("javax.el.cacheSize")) {
/*     */       
/*     */       try {
/*     */         
/* 395 */         cacheSize = Integer.parseInt(properties.getProperty("javax.el.cacheSize"));
/*     */       }
/* 397 */       catch (NumberFormatException e) {
/*     */         
/* 399 */         throw new ELException("Cannot parse EL property javax.el.cacheSize", e);
/*     */       } 
/*     */     }
/* 402 */     Cache cache = (cacheSize > 0) ? 
/* 403 */       new Cache(cacheSize) : 
/* 404 */       null;
/*     */     
/* 406 */     return new TreeStore(builder, (TreeCache)cache);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TypeConverter createTypeConverter(Properties properties) {
/* 417 */     Class<?> clazz = load(TypeConverter.class, properties);
/* 418 */     if (clazz == null)
/*     */     {
/* 420 */       return TypeConverter.DEFAULT;
/*     */     }
/*     */     
/*     */     try {
/* 424 */       return TypeConverter.class.cast(clazz.newInstance());
/*     */     }
/* 426 */     catch (Exception e) {
/*     */       
/* 428 */       throw new ELException("TypeConverter " + clazz + " could not be instantiated", e);
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
/*     */   protected TreeBuilder createTreeBuilder(Properties properties, Builder.Feature... features) {
/* 443 */     Class<?> clazz = load(TreeBuilder.class, properties);
/* 444 */     if (clazz == null)
/*     */     {
/* 446 */       return (TreeBuilder)new Builder(features);
/*     */     }
/*     */     
/*     */     try {
/* 450 */       if (Builder.class.isAssignableFrom(clazz)) {
/*     */         
/* 452 */         Constructor<?> constructor = clazz.getConstructor(new Class[] { Builder.Feature[].class });
/* 453 */         if (constructor == null) {
/*     */           
/* 455 */           if (features == null || features.length == 0)
/*     */           {
/* 457 */             return TreeBuilder.class.cast(clazz.newInstance());
/*     */           }
/*     */ 
/*     */           
/* 461 */           throw new ELException("Builder " + clazz + " is missing constructor (can't pass features)");
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 466 */         return TreeBuilder.class.cast(constructor.newInstance(new Object[] { features }));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 471 */       return TreeBuilder.class.cast(clazz.newInstance());
/*     */     
/*     */     }
/* 474 */     catch (Exception e) {
/*     */       
/* 476 */       throw new ELException("TreeBuilder " + clazz + " could not be instantiated", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Class<?> load(Class<?> clazz, Properties properties) {
/* 482 */     if (properties != null) {
/*     */       
/* 484 */       String className = properties.getProperty(clazz.getName());
/* 485 */       if (className != null) {
/*     */         ClassLoader loader;
/*     */ 
/*     */         
/*     */         try {
/* 490 */           loader = Thread.currentThread().getContextClassLoader();
/*     */         }
/* 492 */         catch (Exception e) {
/*     */           
/* 494 */           throw new ELException("Could not get context class loader", e);
/*     */         } 
/*     */         
/*     */         try {
/* 498 */           return (loader == null) ? 
/* 499 */             Class.forName(className) : 
/* 500 */             loader.loadClass(className);
/*     */         }
/* 502 */         catch (ClassNotFoundException e) {
/*     */           
/* 504 */           throw new ELException("Class " + className + " not found", e);
/*     */         }
/* 506 */         catch (Exception e) {
/*     */           
/* 508 */           throw new ELException("Class " + className + " could not be instantiated", e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 512 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Object coerceToType(Object obj, Class<?> targetType) {
/* 518 */     return this.converter.convert(obj, targetType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final ObjectValueExpression createValueExpression(Object instance, Class<?> expectedType) {
/* 524 */     return new ObjectValueExpression(this.converter, instance, expectedType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final TreeValueExpression createValueExpression(ELContext context, String expression, Class<?> expectedType) {
/* 530 */     return new TreeValueExpression(this.store, context.getFunctionMapper(), context.getVariableMapper(), this.converter, expression, 
/* 531 */         expectedType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final TreeMethodExpression createMethodExpression(ELContext context, String expression, Class<?> expectedReturnType, Class[] expectedParamTypes) {
/* 538 */     return new TreeMethodExpression(this.store, context.getFunctionMapper(), context.getVariableMapper(), this.converter, expression, 
/* 539 */         expectedReturnType, expectedParamTypes);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\ExpressionFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */