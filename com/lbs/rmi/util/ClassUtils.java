/*     */ package com.lbs.rmi.util;
/*     */ 
/*     */ import java.beans.Introspector;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ClassUtils
/*     */ {
/*     */   public static final String ARRAY_SUFFIX = "[]";
/*     */   private static final String INTERNAL_ARRAY_PREFIX = "[";
/*     */   private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
/*     */   private static final char PACKAGE_SEPARATOR = '.';
/*     */   private static final char INNER_CLASS_SEPARATOR = '$';
/*     */   public static final String CGLIB_CLASS_SEPARATOR = "$$";
/*     */   public static final String CLASS_FILE_SUFFIX = ".class";
/*  77 */   private static final Map primitiveWrapperTypeMap = new HashMap<>(8);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final Map primitiveTypeNameMap = new HashMap<>(16);
/*     */ 
/*     */   
/*     */   static {
/*  87 */     primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
/*  88 */     primitiveWrapperTypeMap.put(Byte.class, byte.class);
/*  89 */     primitiveWrapperTypeMap.put(Character.class, char.class);
/*  90 */     primitiveWrapperTypeMap.put(Double.class, double.class);
/*  91 */     primitiveWrapperTypeMap.put(Float.class, float.class);
/*  92 */     primitiveWrapperTypeMap.put(Integer.class, int.class);
/*  93 */     primitiveWrapperTypeMap.put(Long.class, long.class);
/*  94 */     primitiveWrapperTypeMap.put(Short.class, short.class);
/*     */     
/*  96 */     Set primitiveTypeNames = new HashSet(16);
/*  97 */     primitiveTypeNames.addAll(primitiveWrapperTypeMap.values());
/*  98 */     primitiveTypeNames.addAll(Arrays.asList((Class<?>[][])new Class[] {
/*  99 */             boolean[].class, byte[].class, char[].class, double[].class, 
/* 100 */             float[].class, int[].class, long[].class, short[].class }));
/* 101 */     for (Iterator<Class<?>> it = primitiveTypeNames.iterator(); it.hasNext(); ) {
/* 102 */       Class<?> primitiveClass = it.next();
/* 103 */       primitiveTypeNameMap.put(primitiveClass.getName(), primitiveClass);
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
/*     */   public static ClassLoader getDefaultClassLoader() {
/* 121 */     ClassLoader cl = null;
/*     */     try {
/* 123 */       cl = Thread.currentThread().getContextClassLoader();
/*     */     }
/* 125 */     catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 128 */     if (cl == null)
/*     */     {
/* 130 */       cl = ClassUtils.class.getClassLoader();
/*     */     }
/* 132 */     return cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ClassLoader overrideThreadContextClassLoader(ClassLoader classLoaderToUse) {
/* 143 */     Thread currentThread = Thread.currentThread();
/* 144 */     ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
/* 145 */     if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
/* 146 */       currentThread.setContextClassLoader(classLoaderToUse);
/* 147 */       return threadContextClassLoader;
/*     */     } 
/*     */     
/* 150 */     return null;
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
/*     */   public static Class forName(String name) throws ClassNotFoundException, LinkageError {
/* 167 */     return forName(name, getDefaultClassLoader());
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
/*     */   public static Class forName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
/* 182 */     Assert.notNull(name, "Name must not be null");
/*     */     
/* 184 */     Class clazz = resolvePrimitiveClassName(name);
/* 185 */     if (clazz != null) {
/* 186 */       return clazz;
/*     */     }
/*     */ 
/*     */     
/* 190 */     if (name.endsWith("[]")) {
/* 191 */       String elementClassName = name.substring(0, name.length() - "[]".length());
/* 192 */       Class<?> elementClass = forName(elementClassName, classLoader);
/* 193 */       return Array.newInstance(elementClass, 0).getClass();
/*     */     } 
/*     */ 
/*     */     
/* 197 */     if (name.startsWith("[L") && name.endsWith(";")) {
/* 198 */       String elementName = name.substring("[L".length(), name.length() - 1);
/* 199 */       Class<?> elementClass = forName(elementName, classLoader);
/* 200 */       return Array.newInstance(elementClass, 0).getClass();
/*     */     } 
/*     */ 
/*     */     
/* 204 */     if (name.startsWith("[")) {
/* 205 */       String elementName = name.substring("[".length());
/* 206 */       Class<?> elementClass = forName(elementName, classLoader);
/* 207 */       return Array.newInstance(elementClass, 0).getClass();
/*     */     } 
/*     */     
/* 210 */     ClassLoader classLoaderToUse = classLoader;
/* 211 */     if (classLoaderToUse == null) {
/* 212 */       classLoaderToUse = getDefaultClassLoader();
/*     */     }
/* 214 */     return classLoaderToUse.loadClass(name);
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
/*     */   public static Class resolveClassName(String className, ClassLoader classLoader) throws IllegalArgumentException {
/*     */     try {
/* 233 */       return forName(className, classLoader);
/*     */     }
/* 235 */     catch (ClassNotFoundException ex) {
/* 236 */       IllegalArgumentException iae = new IllegalArgumentException("Cannot find class [" + className + "]");
/* 237 */       iae.initCause(ex);
/* 238 */       throw iae;
/*     */     }
/* 240 */     catch (LinkageError ex) {
/* 241 */       IllegalArgumentException iae = new IllegalArgumentException(
/* 242 */           "Error loading class [" + className + "]: problem with class file or dependent class.");
/* 243 */       iae.initCause(ex);
/* 244 */       throw iae;
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
/*     */   public static Class resolvePrimitiveClassName(String name) {
/* 259 */     Class result = null;
/*     */ 
/*     */     
/* 262 */     if (name != null && name.length() <= 8)
/*     */     {
/* 264 */       result = (Class)primitiveTypeNameMap.get(name);
/*     */     }
/* 266 */     return result;
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
/*     */   public static boolean isPresent(String className) {
/* 278 */     return isPresent(className, getDefaultClassLoader());
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
/*     */   public static boolean isPresent(String className, ClassLoader classLoader) {
/*     */     try {
/* 292 */       forName(className, classLoader);
/* 293 */       return true;
/*     */     }
/* 295 */     catch (Throwable ex) {
/*     */       
/* 297 */       return false;
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
/*     */   public static Class getUserClass(Object instance) {
/* 309 */     Assert.notNull(instance, "Instance must not be null");
/* 310 */     return getUserClass(instance.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class getUserClass(Class clazz) {
/* 320 */     return (clazz != null && clazz.getName().indexOf("$$") != -1) ? 
/* 321 */       clazz.getSuperclass() : clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCacheSafe(Class clazz, ClassLoader classLoader) {
/* 331 */     Assert.notNull(clazz, "Class must not be null");
/* 332 */     ClassLoader target = clazz.getClassLoader();
/* 333 */     if (target == null) {
/* 334 */       return false;
/*     */     }
/* 336 */     ClassLoader cur = classLoader;
/* 337 */     if (cur == target) {
/* 338 */       return true;
/*     */     }
/* 340 */     while (cur != null) {
/* 341 */       cur = cur.getParent();
/* 342 */       if (cur == target) {
/* 343 */         return true;
/*     */       }
/*     */     } 
/* 346 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getShortName(String className) {
/* 357 */     Assert.hasLength(className, "Class name must not be empty");
/* 358 */     int lastDotIndex = className.lastIndexOf('.');
/* 359 */     int nameEndIndex = className.indexOf("$$");
/* 360 */     if (nameEndIndex == -1) {
/* 361 */       nameEndIndex = className.length();
/*     */     }
/* 363 */     String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
/* 364 */     shortName = shortName.replace('$', '.');
/* 365 */     return shortName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getShortName(Class clazz) {
/* 374 */     return getShortName(getQualifiedName(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getShortNameAsProperty(Class clazz) {
/* 385 */     String shortName = getShortName(clazz);
/* 386 */     int dotIndex = shortName.lastIndexOf('.');
/* 387 */     shortName = (dotIndex != -1) ? shortName.substring(dotIndex + 1) : shortName;
/* 388 */     return Introspector.decapitalize(shortName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getClassFileName(Class clazz) {
/* 398 */     Assert.notNull(clazz, "Class must not be null");
/* 399 */     String className = clazz.getName();
/* 400 */     int lastDotIndex = className.lastIndexOf('.');
/* 401 */     return String.valueOf(className.substring(lastDotIndex + 1)) + ".class";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPackageName(Class clazz) {
/* 412 */     Assert.notNull(clazz, "Class must not be null");
/* 413 */     String className = clazz.getName();
/* 414 */     int lastDotIndex = className.lastIndexOf('.');
/* 415 */     return (lastDotIndex != -1) ? className.substring(0, lastDotIndex) : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getQualifiedName(Class clazz) {
/* 425 */     Assert.notNull(clazz, "Class must not be null");
/* 426 */     if (clazz.isArray()) {
/* 427 */       return getQualifiedNameForArray(clazz);
/*     */     }
/*     */     
/* 430 */     return clazz.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getQualifiedNameForArray(Class<?> clazz) {
/* 441 */     StringBuilder buffer = new StringBuilder();
/* 442 */     while (clazz.isArray()) {
/* 443 */       clazz = clazz.getComponentType();
/* 444 */       buffer.append("[]");
/*     */     } 
/* 446 */     buffer.insert(0, clazz.getName());
/* 447 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getQualifiedMethodName(Method method) {
/* 457 */     Assert.notNull(method, "Method must not be null");
/* 458 */     return String.valueOf(method.getDeclaringClass().getName()) + "." + method.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDescriptiveType(Object value) {
/* 469 */     if (value == null) {
/* 470 */       return null;
/*     */     }
/* 472 */     Class<?> clazz = value.getClass();
/* 473 */     if (Proxy.isProxyClass(clazz)) {
/* 474 */       StringBuilder buf = new StringBuilder(clazz.getName());
/* 475 */       buf.append(" implementing ");
/* 476 */       Class[] ifcs = clazz.getInterfaces();
/* 477 */       for (int i = 0; i < ifcs.length; i++) {
/* 478 */         buf.append(ifcs[i].getName());
/* 479 */         if (i < ifcs.length - 1) {
/* 480 */           buf.append(',');
/*     */         }
/*     */       } 
/* 483 */       return buf.toString();
/*     */     } 
/* 485 */     if (clazz.isArray()) {
/* 486 */       return getQualifiedNameForArray(clazz);
/*     */     }
/*     */     
/* 489 */     return clazz.getName();
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
/*     */   public static boolean hasConstructor(Class clazz, Class[] paramTypes) {
/* 503 */     return (getConstructorIfAvailable(clazz, paramTypes) != null);
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
/*     */   public static Constructor getConstructorIfAvailable(Class clazz, Class[] paramTypes) {
/* 516 */     Assert.notNull(clazz, "Class must not be null");
/*     */     try {
/* 518 */       return clazz.getConstructor(paramTypes);
/*     */     }
/* 520 */     catch (NoSuchMethodException ex) {
/* 521 */       return null;
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
/*     */   public static boolean hasMethod(Class clazz, String methodName, Class[] paramTypes) {
/* 535 */     return (getMethodIfAvailable(clazz, methodName, paramTypes) != null);
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
/*     */   public static Method getMethodIfAvailable(Class clazz, String methodName, Class[] paramTypes) {
/* 549 */     Assert.notNull(clazz, "Class must not be null");
/* 550 */     Assert.notNull(methodName, "Method name must not be null");
/*     */     try {
/* 552 */       return clazz.getMethod(methodName, paramTypes);
/*     */     }
/* 554 */     catch (NoSuchMethodException ex) {
/* 555 */       return null;
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
/*     */   public static int getMethodCountForName(Class clazz, String methodName) {
/* 567 */     Assert.notNull(clazz, "Class must not be null");
/* 568 */     Assert.notNull(methodName, "Method name must not be null");
/* 569 */     int count = 0;
/* 570 */     Method[] declaredMethods = clazz.getDeclaredMethods();
/* 571 */     for (int i = 0; i < declaredMethods.length; i++) {
/* 572 */       Method method = declaredMethods[i];
/* 573 */       if (methodName.equals(method.getName())) {
/* 574 */         count++;
/*     */       }
/*     */     } 
/* 577 */     Class[] ifcs = clazz.getInterfaces();
/* 578 */     for (int j = 0; j < ifcs.length; j++) {
/* 579 */       count += getMethodCountForName(ifcs[j], methodName);
/*     */     }
/* 581 */     if (clazz.getSuperclass() != null) {
/* 582 */       count += getMethodCountForName(clazz.getSuperclass(), methodName);
/*     */     }
/* 584 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasAtLeastOneMethodWithName(Class clazz, String methodName) {
/* 595 */     Assert.notNull(clazz, "Class must not be null");
/* 596 */     Assert.notNull(methodName, "Method name must not be null");
/* 597 */     Method[] declaredMethods = clazz.getDeclaredMethods();
/* 598 */     for (int i = 0; i < declaredMethods.length; i++) {
/* 599 */       Method method = declaredMethods[i];
/* 600 */       if (method.getName().equals(methodName)) {
/* 601 */         return true;
/*     */       }
/*     */     } 
/* 604 */     Class[] ifcs = clazz.getInterfaces();
/* 605 */     for (int j = 0; j < ifcs.length; j++) {
/* 606 */       if (hasAtLeastOneMethodWithName(ifcs[j], methodName)) {
/* 607 */         return true;
/*     */       }
/*     */     } 
/* 610 */     return (clazz.getSuperclass() != null && hasAtLeastOneMethodWithName(clazz.getSuperclass(), methodName));
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
/*     */   public static Method getMostSpecificMethod(Method method, Class targetClass) {
/* 632 */     if (method != null && targetClass != null && !targetClass.equals(method.getDeclaringClass())) {
/*     */       try {
/* 634 */         method = targetClass.getMethod(method.getName(), method.getParameterTypes());
/*     */       }
/* 636 */       catch (NoSuchMethodException noSuchMethodException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 641 */     return method;
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
/*     */   public static Method getStaticMethod(Class clazz, String methodName, Class[] args) {
/* 653 */     Assert.notNull(clazz, "Class must not be null");
/* 654 */     Assert.notNull(methodName, "Method name must not be null");
/*     */     try {
/* 656 */       Method method = clazz.getDeclaredMethod(methodName, args);
/* 657 */       if ((method.getModifiers() & 0x8) != 0) {
/* 658 */         return method;
/*     */       }
/*     */     }
/* 661 */     catch (NoSuchMethodException noSuchMethodException) {}
/*     */     
/* 663 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPrimitiveWrapper(Class clazz) {
/* 674 */     Assert.notNull(clazz, "Class must not be null");
/* 675 */     return primitiveWrapperTypeMap.containsKey(clazz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPrimitiveOrWrapper(Class clazz) {
/* 686 */     Assert.notNull(clazz, "Class must not be null");
/* 687 */     return !(!clazz.isPrimitive() && !isPrimitiveWrapper(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPrimitiveArray(Class clazz) {
/* 697 */     Assert.notNull(clazz, "Class must not be null");
/* 698 */     return (clazz.isArray() && clazz.getComponentType().isPrimitive());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPrimitiveWrapperArray(Class clazz) {
/* 708 */     Assert.notNull(clazz, "Class must not be null");
/* 709 */     return (clazz.isArray() && isPrimitiveWrapper(clazz.getComponentType()));
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
/*     */   public static boolean isAssignable(Class lhsType, Class<?> rhsType) {
/* 722 */     Assert.notNull(lhsType, "Left-hand side type must not be null");
/* 723 */     Assert.notNull(rhsType, "Right-hand side type must not be null");
/* 724 */     return !(!lhsType.isAssignableFrom(rhsType) && 
/* 725 */       !lhsType.equals(primitiveWrapperTypeMap.get(rhsType)));
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
/*     */   public static boolean isAssignableValue(Class type, Object value) {
/* 737 */     Assert.notNull(type, "Type must not be null");
/* 738 */     return (value != null) ? isAssignable(type, value.getClass()) : (!type.isPrimitive());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String convertResourcePathToClassName(String resourcePath) {
/* 748 */     return resourcePath.replace('/', '.');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String convertClassNameToResourcePath(String className) {
/* 757 */     return className.replace('.', '/');
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
/*     */   public static String addResourcePathToPackagePath(Class clazz, String resourceName) {
/* 777 */     Assert.notNull(resourceName, "Resource name must not be null");
/* 778 */     if (!resourceName.startsWith("/")) {
/* 779 */       return String.valueOf(classPackageAsResourcePath(clazz)) + "/" + resourceName;
/*     */     }
/* 781 */     return String.valueOf(classPackageAsResourcePath(clazz)) + resourceName;
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
/*     */   public static String classPackageAsResourcePath(Class clazz) {
/* 799 */     if (clazz == null) {
/* 800 */       return "";
/*     */     }
/* 802 */     String className = clazz.getName();
/* 803 */     int packageEndIndex = className.lastIndexOf('.');
/* 804 */     if (packageEndIndex == -1) {
/* 805 */       return "";
/*     */     }
/* 807 */     String packageName = className.substring(0, packageEndIndex);
/* 808 */     return packageName.replace('.', '/');
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
/*     */   public static String classNamesToString(Class[] classes) {
/* 821 */     return classNamesToString(Arrays.asList((Class<?>[][])classes));
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
/*     */   public static String classNamesToString(Collection classes) {
/* 834 */     if (CollectionUtils.isEmpty(classes)) {
/* 835 */       return "[]";
/*     */     }
/* 837 */     StringBuilder sb = new StringBuilder("[");
/* 838 */     for (Iterator<Class<?>> it = classes.iterator(); it.hasNext(); ) {
/* 839 */       Class clazz = it.next();
/* 840 */       sb.append(clazz.getName());
/* 841 */       if (it.hasNext()) {
/* 842 */         sb.append(", ");
/*     */       }
/*     */     } 
/* 845 */     sb.append("]");
/* 846 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class[] getAllInterfaces(Object instance) {
/* 857 */     Assert.notNull(instance, "Instance must not be null");
/* 858 */     return getAllInterfacesForClass(instance.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class[] getAllInterfacesForClass(Class clazz) {
/* 869 */     return getAllInterfacesForClass(clazz, null);
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
/*     */   public static Class[] getAllInterfacesForClass(Class clazz, ClassLoader classLoader) {
/* 882 */     Assert.notNull(clazz, "Class must not be null");
/* 883 */     if (clazz.isInterface()) {
/* 884 */       return new Class[] { clazz };
/*     */     }
/* 886 */     List<Class<?>> interfaces = new ArrayList();
/* 887 */     while (clazz != null) {
/* 888 */       for (int i = 0; i < (clazz.getInterfaces()).length; i++) {
/* 889 */         Class<?> ifc = clazz.getInterfaces()[i];
/* 890 */         if (!interfaces.contains(ifc) && (
/* 891 */           classLoader == null || isVisible(ifc, classLoader))) {
/* 892 */           interfaces.add(ifc);
/*     */         }
/*     */       } 
/* 895 */       clazz = clazz.getSuperclass();
/*     */     } 
/* 897 */     return (Class[])interfaces.<Class<?>[]>toArray((Class<?>[][])new Class[interfaces.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set getAllInterfacesAsSet(Object instance) {
/* 907 */     Assert.notNull(instance, "Instance must not be null");
/* 908 */     return getAllInterfacesForClassAsSet(instance.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set getAllInterfacesForClassAsSet(Class clazz) {
/* 919 */     return getAllInterfacesForClassAsSet(clazz, null);
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
/*     */   public static Set getAllInterfacesForClassAsSet(Class<?> clazz, ClassLoader classLoader) {
/* 932 */     Assert.notNull(clazz, "Class must not be null");
/* 933 */     if (clazz.isInterface()) {
/* 934 */       return Collections.singleton(clazz);
/*     */     }
/* 936 */     Set<Class<?>> interfaces = new LinkedHashSet();
/* 937 */     while (clazz != null) {
/* 938 */       for (int i = 0; i < (clazz.getInterfaces()).length; i++) {
/* 939 */         Class<?> ifc = clazz.getInterfaces()[i];
/* 940 */         if (classLoader == null || isVisible(ifc, classLoader)) {
/* 941 */           interfaces.add(ifc);
/*     */         }
/*     */       } 
/* 944 */       clazz = clazz.getSuperclass();
/*     */     } 
/* 946 */     return interfaces;
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
/*     */   public static Class createCompositeInterface(Class[] interfaces, ClassLoader classLoader) {
/* 959 */     Assert.notEmpty((Object[])interfaces, "Interfaces must not be empty");
/* 960 */     Assert.notNull(classLoader, "ClassLoader must not be null");
/* 961 */     return Proxy.getProxyClass(classLoader, interfaces);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isVisible(Class<?> clazz, ClassLoader classLoader) {
/* 971 */     if (classLoader == null) {
/* 972 */       return true;
/*     */     }
/*     */     try {
/* 975 */       Class<?> actualClass = classLoader.loadClass(clazz.getName());
/* 976 */       return (clazz == actualClass);
/*     */     
/*     */     }
/* 979 */     catch (ClassNotFoundException ex) {
/*     */       
/* 981 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rm\\util\ClassUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */