/*     */ package com.lbs.invoke;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.math.BigDecimal;
/*     */ import java.security.InvalidParameterException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RttiUtil
/*     */ {
/*     */   public static final String GETMETHODPREFIX = "get";
/*     */   public static final String GETMETHODPREFIX_BOOL = "is";
/*     */   public static final String SETMETHODPREFIX = "set";
/*     */   
/*     */   public static Field findField(Class<?> c, String fieldName) {
/*  30 */     Field field = null;
/*     */     
/*     */     try {
/*  33 */       field = c.getDeclaredField(fieldName);
/*     */     }
/*  35 */     catch (Exception exception) {}
/*     */ 
/*     */     
/*  38 */     if (field == null) {
/*     */       
/*     */       try {
/*  41 */         field = c.getField(fieldName);
/*     */       }
/*  43 */       catch (Exception exception) {}
/*     */     }
/*     */     
/*  46 */     if (field == null) {
/*     */       
/*  48 */       Class<?> superclass = c.getSuperclass();
/*  49 */       if (superclass != null && !superclass.equals(Object.class))
/*  50 */         field = findField(superclass, fieldName); 
/*     */     } 
/*  52 */     return field;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void generateDefaultMethodBody(Method method, StringBuffer sb, String argumentNamePrefix, ArrayList importList, boolean superExists) {
/*  58 */     int mod = method.getModifiers();
/*  59 */     if (Modifier.isPublic(mod))
/*  60 */       sb.append("public "); 
/*  61 */     if (Modifier.isProtected(mod))
/*  62 */       sb.append("protected "); 
/*  63 */     if (Modifier.isPrivate(mod))
/*  64 */       sb.append("private "); 
/*  65 */     if (Modifier.isStatic(mod))
/*  66 */       sb.append("static "); 
/*  67 */     if (Modifier.isFinal(mod))
/*  68 */       sb.append("final "); 
/*  69 */     if (Modifier.isSynchronized(mod)) {
/*  70 */       sb.append("synchronized ");
/*     */     }
/*  72 */     Class<?> returnType = method.getReturnType();
/*  73 */     if (returnType == null) {
/*  74 */       sb.append("void ");
/*     */     } else {
/*  76 */       sb.append(String.valueOf(addClass(returnType, importList)) + " ");
/*     */     } 
/*  78 */     sb.append(method.getName());
/*     */     
/*  80 */     sb.append("(");
/*  81 */     int paramCnt = 0;
/*  82 */     Class[] params = method.getParameterTypes();
/*  83 */     if (params != null) {
/*     */       
/*  85 */       paramCnt = params.length;
/*  86 */       for (int i = 0; i < params.length; i++) {
/*     */         
/*  88 */         if (i > 0)
/*  89 */           sb.append(", "); 
/*  90 */         sb.append(String.valueOf(addClass(params[i], importList)) + " " + argumentNamePrefix + i);
/*     */       } 
/*     */     } 
/*  93 */     sb.append(")");
/*  94 */     Class[] excs = method.getExceptionTypes();
/*  95 */     if (excs != null && excs.length > 0) {
/*     */       
/*  97 */       sb.append(" throws ");
/*  98 */       for (int i = 0; i < excs.length; i++) {
/*     */         
/* 100 */         if (i > 0)
/* 101 */           sb.append(", "); 
/* 102 */         sb.append(addClass(excs[i], importList));
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     sb.append("\n{\n");
/* 107 */     sb.append("\t/* TODO implement method body */\n");
/* 108 */     if (superExists) {
/*     */       
/* 110 */       if (returnType != null && !returnType.equals(void.class))
/* 111 */         sb.append("return "); 
/* 112 */       sb.append("\tsuper." + method.getName() + "(");
/* 113 */       if (paramCnt > 0)
/*     */       {
/* 115 */         for (int i = 0; i < paramCnt; i++) {
/*     */           
/* 117 */           if (i > 0)
/* 118 */             sb.append(", "); 
/* 119 */           sb.append(String.valueOf(argumentNamePrefix) + i);
/*     */         } 
/*     */       }
/* 122 */       sb.append(");\n");
/*     */     }
/* 124 */     else if (returnType != null) {
/* 125 */       if (returnType.isPrimitive())
/*     */       
/*     */       { try {
/* 128 */           String value = "";
/* 129 */           if (returnType.equals(int.class)) {
/* 130 */             value = "0";
/* 131 */           } else if (returnType.equals(float.class)) {
/* 132 */             value = "0.0";
/* 133 */           } else if (returnType.equals(byte.class)) {
/* 134 */             value = "(byte) 0";
/* 135 */           } else if (returnType.equals(double.class)) {
/* 136 */             value = "0.0";
/* 137 */           } else if (returnType.equals(boolean.class)) {
/* 138 */             value = "false";
/* 139 */           } else if (returnType.equals(char.class)) {
/* 140 */             value = "' '";
/* 141 */           } else if (returnType.equals(short.class)) {
/* 142 */             value = "0";
/* 143 */           } else if (returnType.equals(long.class)) {
/* 144 */             value = "0l";
/* 145 */           }  sb.append("\treturn " + value + ";");
/*     */         }
/* 147 */         catch (Exception exception) {} }
/*     */       
/*     */       else
/*     */       
/* 151 */       { sb.append("\treturn null;"); } 
/* 152 */     }  sb.append("\n}\n");
/*     */   }
/*     */ 
/*     */   
/*     */   private static String addClass(Class clazz, ArrayList<String> importList) {
/* 157 */     if (importList == null) {
/* 158 */       return clazz.getName();
/*     */     }
/*     */     
/* 161 */     if (clazz.isPrimitive())
/* 162 */       return clazz.getName(); 
/* 163 */     String name = clazz.getName();
/* 164 */     importList.add(name);
/* 165 */     int idx = name.lastIndexOf('.');
/* 166 */     if (idx > 0)
/* 167 */       name = name.substring(idx + 1); 
/* 168 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCallCompatibleDepth(Class a, Class b) {
/* 174 */     if (a != null && b != null) {
/*     */       
/* 176 */       if (isValueClass(b) && valueClassToWrapper(b) == a)
/* 177 */         return 10; 
/* 178 */       if (a == b)
/* 179 */         return 10; 
/* 180 */       if (isSubClassOf(a, b))
/* 181 */         return 5; 
/*     */     } 
/* 183 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCallCompatible(Class a, Class b) {
/* 188 */     if (a != null && b != null) {
/*     */       
/* 190 */       if (isValueClass(b) && valueClassToWrapper(b) == a)
/* 191 */         return true; 
/* 192 */       return isSubClassOf(a, b);
/*     */     } 
/* 194 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void copyProperties(Object source, Object target) {
/* 199 */     if (source == null || target == null)
/*     */       return; 
/* 201 */     Method[] srcProps = getMethods(source.getClass(), "get");
/* 202 */     if (srcProps == null)
/*     */       return; 
/* 204 */     for (int i = 0; i < srcProps.length; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 208 */         Method mtd = srcProps[i];
/* 209 */         Object val = mtd.invoke(source, null);
/* 210 */         MethodInvoker.invokeMethodSilent("set" + mtd.getName().substring(3), target, new Object[] { val });
/*     */       }
/* 212 */       catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method[] getMethods(Class c, String prefix) {
/* 220 */     if (c == null || prefix == null)
/* 221 */       return null; 
/* 222 */     Method[] methods = c.getMethods();
/*     */ 
/*     */     
/* 225 */     ArrayList<Method> list = new ArrayList();
/* 226 */     for (int i = 0; i < methods.length; i++) {
/*     */       
/* 228 */       Method mtd = methods[i];
/* 229 */       if (mtd.getName().startsWith(prefix))
/* 230 */         list.add(mtd); 
/*     */     } 
/* 232 */     if (list.size() > 0)
/* 233 */       return list.<Method>toArray(new Method[list.size()]); 
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSubClassOf(Class subCls, Class cls) {
/* 239 */     if (subCls != null && cls != null) {
/*     */       do
/*     */       {
/*     */         
/* 243 */         if (subCls == cls)
/* 244 */           return true; 
/* 245 */         subCls = subCls.getSuperclass();
/*     */       }
/* 247 */       while (subCls != null);
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSubClassOf(Class subCls, String clsName) {
/* 254 */     if (subCls != null && clsName != null) {
/*     */       do
/*     */       {
/*     */         
/* 258 */         if (subCls.getName().compareTo(clsName) == 0)
/* 259 */           return true; 
/* 260 */         subCls = subCls.getSuperclass();
/*     */       }
/* 262 */       while (subCls != null);
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValueClass(Class<int> a) {
/* 270 */     return !(a != int.class && a != char.class && a != byte.class && a != long.class && a != short.class && a != boolean.class && 
/* 271 */       a != float.class && a != double.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isValueWrapperClass(Class<Integer> a) {
/* 276 */     return !(a != Integer.class && a != Character.class && a != Byte.class && a != Long.class && a != Short.class && 
/* 277 */       a != Boolean.class && a != Float.class && a != Double.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isNumericClass(Class<Integer> a) {
/* 282 */     return !(a != Integer.class && a != Byte.class && a != Long.class && a != Short.class && a != Float.class && 
/* 283 */       a != Double.class && a != int.class && a != byte.class && a != long.class && a != short.class && 
/* 284 */       a != float.class && a != double.class && !isSubClassOf(a, Number.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getNumericValue(Object o) {
/* 289 */     if (o != null) {
/*     */       
/*     */       try {
/* 292 */         Class<?> a = o.getClass();
/* 293 */         if (a == Integer.class)
/* 294 */           return ((Integer)o).doubleValue(); 
/* 295 */         if (a == Byte.class)
/* 296 */           return ((Byte)o).doubleValue(); 
/* 297 */         if (a == Long.class)
/* 298 */           return ((Long)o).doubleValue(); 
/* 299 */         if (a == Short.class)
/* 300 */           return ((Short)o).doubleValue(); 
/* 301 */         if (a == Float.class)
/* 302 */           return ((Float)o).doubleValue(); 
/* 303 */         if (a == Double.class)
/* 304 */           return ((Double)o).doubleValue(); 
/* 305 */         if (a == BigDecimal.class) {
/* 306 */           return ((BigDecimal)o).doubleValue();
/*     */         }
/* 308 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 311 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFirstClassValueOrWrapper(Class<String> a) {
/* 316 */     return !(a != String.class && a != Object.class && !isValueClass(a) && !isValueWrapperClass(a) && !isSubClassOf(
/* 317 */         a, Number.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSimpleClassValueOrWrapper(Class<String> a) {
/* 322 */     return !(a != String.class && !isValueClass(a) && !isValueWrapperClass(a) && !isSubClassOf(a, Number.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Class valueClassToWrapper(Class<boolean> a) {
/* 327 */     if (a == boolean.class)
/* 328 */       return Boolean.class; 
/* 329 */     if (a == int.class)
/* 330 */       return Integer.class; 
/* 331 */     if (a == long.class)
/* 332 */       return Long.class; 
/* 333 */     if (a == char.class)
/* 334 */       return Character.class; 
/* 335 */     if (a == byte.class)
/* 336 */       return Byte.class; 
/* 337 */     if (a == short.class)
/* 338 */       return Short.class; 
/* 339 */     if (a == float.class)
/* 340 */       return Float.class; 
/* 341 */     if (a == double.class)
/* 342 */       return Double.class; 
/* 343 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean implementsInterface(Class cls, Class intf) {
/* 348 */     if (cls != null) {
/*     */       
/* 350 */       Class[] interfaces = cls.getInterfaces();
/* 351 */       if (interfaces != null && interfaces.length > 0)
/* 352 */         for (int i = 0; i < interfaces.length; i++) {
/* 353 */           if (interfaces[i] == intf)
/* 354 */             return true; 
/* 355 */         }   return implementsInterface(cls.getSuperclass(), intf);
/*     */     } 
/* 357 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean implementsInterface(Class cls, String intfName) {
/* 362 */     if (cls != null) {
/*     */       
/* 364 */       Class[] interfaces = cls.getInterfaces();
/* 365 */       if (interfaces != null && interfaces.length > 0)
/* 366 */         for (int i = 0; i < interfaces.length; i++) {
/*     */           
/* 368 */           String name = interfaces[i].getName();
/*     */           
/* 370 */           if (name.compareToIgnoreCase(intfName) == 0)
/* 371 */             return true; 
/*     */         }  
/* 373 */       return implementsInterface(cls.getSuperclass(), intfName);
/*     */     } 
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object createInstance(Class cls) {
/* 380 */     if (cls != null) {
/*     */       
/*     */       try {
/* 383 */         return cls.newInstance();
/*     */       }
/* 385 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 388 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getProperty(Object target, String propPath, Object[] parameters) throws NoSuchMethodException, InvalidObjectException, InvalidParameterException, Exception {
/* 394 */     return runObjectInvokePath(target, propPath, "get", parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getProperty(Object target, String propPath) throws NoSuchMethodException, InvalidObjectException, InvalidParameterException, Exception {
/* 400 */     return runObjectInvokePath(target, propPath, "get", null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object setProperty(Object target, String propPath, Object value, Object[] parameters) throws NoSuchMethodException, InvalidObjectException, InvalidParameterException, Exception {
/* 406 */     if (parameters == null || parameters.length == 0)
/* 407 */       return setProperty(target, propPath, value); 
/* 408 */     Object[] newParameters = new Object[parameters.length + 1];
/* 409 */     for (int i = 0; i < parameters.length; i++)
/* 410 */       newParameters[i] = parameters[i]; 
/* 411 */     newParameters[parameters.length] = value;
/* 412 */     return runObjectInvokePath(target, propPath, "set", newParameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object setProperty(Object target, String propPath, Object value) throws NoSuchMethodException, InvalidObjectException, InvalidParameterException, Exception {
/* 418 */     Object[] parameters = { value };
/* 419 */     return runObjectInvokePath(target, propPath, "set", parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object runObjectInvokePath(Object target, String propPath, String prefix, Object[] parameters) throws NoSuchMethodException, InvalidObjectException, InvalidParameterException, Exception {
/* 425 */     if (propPath == null || propPath.length() == 0)
/* 426 */       return target; 
/* 427 */     if (target == null)
/* 428 */       throw new InvalidObjectException(null); 
/* 429 */     int iDotIndex = propPath.indexOf(".");
/* 430 */     if (iDotIndex == 0)
/* 431 */       throw new InvalidParameterException(null); 
/* 432 */     if (iDotIndex < 0) {
/*     */       
/* 434 */       String str = (prefix != null) ? (
/* 435 */         String.valueOf(prefix) + propPath) : 
/* 436 */         propPath;
/* 437 */       return MethodInvoker.invokeMethod(str, target, parameters);
/*     */     } 
/*     */ 
/*     */     
/* 441 */     String propName = propPath.substring(0, iDotIndex);
/* 442 */     Object propObject = MethodInvoker.invokeMethod("get" + propName, target, (Object[])null);
/* 443 */     if (iDotIndex == propPath.length())
/* 444 */       return propObject; 
/* 445 */     return runObjectInvokePath(propObject, propPath.substring(iDotIndex + 1), prefix, parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object runObjectInvokePathSafe(Object target, String propPath, String prefix, Object[] parameters) {
/*     */     try {
/* 453 */       return runObjectInvokePath(target, propPath, prefix, parameters);
/*     */     }
/* 455 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 458 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Class getClass(String clsName) {
/*     */     try {
/* 465 */       return Class.forName(clsName);
/*     */     }
/* 467 */     catch (Throwable throwable) {
/*     */ 
/*     */       
/* 470 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static byte[] copyByteArray(byte[] arr, int start, int length) {
/* 475 */     if (arr != null) {
/*     */       
/* 477 */       start = Math.max(0, start);
/* 478 */       length = Math.min(length, arr.length - start);
/* 479 */       if (length > 0) {
/*     */         
/* 481 */         byte[] result = new byte[length];
/* 482 */         for (int i = 0; i < length; i++)
/* 483 */           result[i] = arr[start++]; 
/* 484 */         return result;
/*     */       } 
/*     */     } 
/* 487 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean colorsEqual(Color c1, Color c2) {
/* 492 */     if (c1 == null || c2 == null)
/* 493 */       return (c1 == c2); 
/* 494 */     return c1.equals(c2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean fontsEqual(Font f1, Font f2) {
/* 499 */     if (f1 == null || f2 == null)
/* 500 */       return (f1 == f2); 
/* 501 */     return f1.equals(f2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areIntArraysEqual(int[] a1, int[] a2) {
/* 506 */     if (a1 == a2)
/* 507 */       return true; 
/* 508 */     if (a1 == null || a2 == null || a1.length != a2.length)
/* 509 */       return false; 
/* 510 */     for (int i = 0; i < a1.length; i++) {
/* 511 */       if (a1[i] != a2[i])
/* 512 */         return false; 
/* 513 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areByteArraysEqual(byte[] a1, byte[] a2) {
/* 518 */     if (a1 == a2)
/* 519 */       return true; 
/* 520 */     if (a1 == null || a2 == null || a1.length != a2.length)
/* 521 */       return false; 
/* 522 */     for (int i = 0; i < a1.length; i++) {
/* 523 */       if (a1[i] != a2[i])
/* 524 */         return false; 
/* 525 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTypeName(Class type) {
/* 530 */     if (type == null)
/* 531 */       return null; 
/* 532 */     String t = type.getName();
/* 533 */     int i = t.lastIndexOf('.');
/* 534 */     if (i > 0 && i < t.length())
/* 535 */       return t.substring(i + 1); 
/* 536 */     return t;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTypeName(String t) {
/* 541 */     if (t == null)
/* 542 */       return null; 
/* 543 */     int i = t.lastIndexOf('.');
/* 544 */     if (i > 0 && i < t.length())
/* 545 */       return t.substring(i + 1); 
/* 546 */     return t;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isClassAvailable(String classname) {
/*     */     try {
/* 553 */       Class.forName(classname);
/* 554 */       return true;
/*     */     }
/* 556 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 559 */       return false;
/*     */     } 
/*     */   }
/*     */   public static Object getSimpleProperty(Object bObj, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 563 */     return bObj.getClass().getMethod("get" + name, new Class[0]).invoke(bObj, new Object[0]);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\RttiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */