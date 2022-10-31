/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BasicBusinessObjects;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.data.objects.BusinessObjectIdentifier;
/*     */ import com.lbs.data.objects.CustomBusinessObject;
/*     */ import com.lbs.data.objects.CustomObjectIdentifier;
/*     */ import com.lbs.data.objects.ILbsClonable;
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.invoke.MethodInvoker;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectUtil
/*     */ {
/*     */   private static Object cloneField(Field field, Object fieldVal) throws CloneNotSupportedException {
/*  39 */     if (fieldVal == null) {
/*  40 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  47 */       Method m = field.getType().getMethod("clone", new Class[0]);
/*     */       
/*     */       try {
/*  50 */         Object newValue = m.invoke(fieldVal, new Object[0]);
/*  51 */         return newValue;
/*     */       }
/*  53 */       catch (InvocationTargetException e) {
/*     */         
/*  55 */         Throwable t = e.getTargetException();
/*     */         
/*  57 */         if (t instanceof Error)
/*     */         {
/*  59 */           throw (Error)t;
/*     */         }
/*  61 */         if (t instanceof RuntimeException)
/*     */         {
/*  63 */           throw (RuntimeException)t;
/*     */         }
/*  65 */         if (t instanceof CloneNotSupportedException)
/*     */         {
/*     */           
/*  68 */           return createCopy(fieldVal);
/*     */         }
/*     */ 
/*     */         
/*  72 */         throw new RuntimeException(t.getMessage());
/*     */       }
/*     */     
/*     */     }
/*  76 */     catch (NoSuchMethodException noSuchMethodException) {
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  82 */     catch (IllegalAccessException illegalAccessException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     return fieldVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void cloneFields(Object src, Object dest, Class<?> objClass, boolean deep) throws CloneNotSupportedException {
/*  95 */     if (deep && JLbsConstants.OPTIMIZE_BO_CLONE && ILbsClonable.class.isAssignableFrom(objClass) && dest instanceof ILbsClonable) {
/*     */ 
/*     */       
/*  98 */       Object objClone = ((ILbsClonable)src).clone();
/*  99 */       ((ILbsClonable)dest).assignFrom(objClone);
/*     */       
/*     */       return;
/*     */     } 
/* 103 */     if (objClass.equals(Object.class)) {
/*     */       return;
/*     */     }
/* 106 */     Field[] fields = null;
/*     */     
/*     */     try {
/* 109 */       if (JLbsConstants.OPTIMIZE_BO_CLONE) {
/*     */         
/* 111 */         if ((ObjectUtilFieldHolder.getInstance()).ms_FieldsCahce.get(objClass) != null) {
/*     */           
/* 113 */           fields = (Field[])(ObjectUtilFieldHolder.getInstance()).ms_FieldsCahce.get(objClass);
/*     */         }
/*     */         else {
/*     */           
/* 117 */           fields = objClass.getDeclaredFields();
/* 118 */           if (ILbsRttiCachable.class.isAssignableFrom(objClass)) {
/* 119 */             (ObjectUtilFieldHolder.getInstance()).ms_FieldsCahce.put(objClass, fields);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 124 */         fields = objClass.getDeclaredFields();
/*     */       }
/*     */     
/* 127 */     } catch (Exception e) {
/*     */       
/* 129 */       LbsConsole.getLogger("Data.Client.ObjectUtil").error("Clone Exp at " + objClass.getName(), e);
/*     */     } 
/*     */ 
/*     */     
/* 133 */     if (fields == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 138 */     for (int i = 0; i < fields.length; i++) {
/*     */       
/* 140 */       Field field = fields[i];
/* 141 */       int fieldMods = field.getModifiers();
/*     */       
/* 143 */       if (!Modifier.isFinal(fieldMods)) {
/*     */ 
/*     */         
/* 146 */         field.setAccessible(true);
/*     */ 
/*     */         
/*     */         try {
/* 150 */           Object val = field.get(src);
/* 151 */           if (val != null && dest instanceof BusinessObject && val.getClass().equals(BusinessObjectIdentifier.class)) {
/*     */             
/* 153 */             val = new BusinessObjectIdentifier((BusinessObject)dest);
/*     */           }
/* 155 */           else if (val != null && dest instanceof CustomBusinessObject && val
/* 156 */             .getClass().equals(CustomObjectIdentifier.class)) {
/*     */             
/* 158 */             val = new CustomObjectIdentifier((CustomBusinessObject)dest);
/*     */ 
/*     */           
/*     */           }
/* 162 */           else if (deep && !isPrimitive(field) && !isImmutable(field)) {
/*     */             
/* 164 */             if (field.getType().isArray()) {
/* 165 */               val = createCopy(val);
/*     */             } else {
/* 167 */               val = cloneField(field, val);
/*     */             } 
/*     */           } 
/*     */           
/* 171 */           field.set(dest, val);
/*     */         }
/* 173 */         catch (IllegalAccessException e) {
/*     */ 
/*     */           
/* 176 */           LbsConsole.getLogger("Data.Client.ObjectUtil").error("Can not access " + field.getName(), e);
/*     */ 
/*     */ 
/*     */           
/* 180 */           throw new RuntimeException(e.getMessage());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static void internalCopy(Object src, Object dest, Class objClass, boolean deepCopy) throws CloneNotSupportedException {
/* 187 */     if (src instanceof Calendar) {
/*     */       
/* 189 */       Calendar srcCal = (Calendar)src;
/* 190 */       Calendar dstCal = (Calendar)dest;
/* 191 */       dstCal.setTimeZone(srcCal.getTimeZone());
/* 192 */       dstCal.setTimeInMillis(srcCal.getTimeInMillis());
/*     */       
/*     */       return;
/*     */     } 
/* 196 */     cloneFields(src, dest, objClass, deepCopy);
/* 197 */     Class superClass = objClass.getSuperclass();
/*     */     
/* 199 */     while (superClass != null) {
/*     */       
/* 201 */       cloneFields(src, dest, superClass, deepCopy);
/* 202 */       superClass = superClass.getSuperclass();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void shallowCopy(Object src, Object dest, Class objClass) throws CloneNotSupportedException {
/* 208 */     internalCopy(src, dest, objClass, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deepCopy(Object src, Object dest, Class objClass) throws CloneNotSupportedException {
/* 213 */     deepCopy(src, dest, objClass, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deepCopy(Object src, Object dest, Class objClass, boolean canClone) throws CloneNotSupportedException {
/* 218 */     boolean ok = false;
/* 219 */     Object result = null;
/* 220 */     if (canClone)
/*     */     {
/* 222 */       if (src instanceof BasicBusinessObject && dest != null) {
/*     */         
/* 224 */         ok = true;
/* 225 */         result = ((BasicBusinessObject)src).clone();
/* 226 */         ((BasicBusinessObject)dest).assign(result);
/*     */       }
/* 228 */       else if (src instanceof BasicBusinessObjects) {
/*     */         
/* 230 */         ok = true;
/* 231 */         dest = ((BasicBusinessObjects)src).clone();
/*     */       } 
/*     */     }
/* 234 */     if (!ok) {
/* 235 */       internalCopy(src, dest, objClass, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Object createCopy(Object src) {
/* 240 */     return createCopy(src, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object createCopy(Object src, boolean deepCopy) {
/* 245 */     Object dest = null;
/* 246 */     if (src == null) {
/* 247 */       return null;
/*     */     }
/* 249 */     Class<?> objClass = src.getClass();
/* 250 */     if (isPrimitive(objClass) || isImmutable(objClass)) {
/* 251 */       return src;
/*     */     }
/*     */     
/*     */     try {
/* 255 */       if (objClass.isArray()) {
/*     */         
/* 257 */         int length = Array.getLength(src);
/* 258 */         dest = Array.newInstance(objClass.getComponentType(), length);
/*     */         
/* 260 */         Object o = null;
/* 261 */         for (int i = 0; i < length; i++)
/*     */         {
/* 263 */           o = Array.get(src, i);
/* 264 */           o = createCopy(o);
/* 265 */           Array.set(dest, i, o);
/*     */         }
/*     */       
/* 268 */       } else if (objClass.equals(BigDecimal.class)) {
/*     */ 
/*     */         
/* 271 */         dest = src;
/*     */       }
/* 273 */       else if (objClass.equals(HashMap.class)) {
/*     */         
/* 275 */         dest = src;
/*     */       }
/* 277 */       else if (objClass.equals(LinkedHashMap.class)) {
/*     */         
/* 279 */         dest = src;
/*     */       }
/*     */       else {
/*     */         
/* 283 */         dest = objClass.newInstance();
/* 284 */         internalCopy(src, dest, objClass, deepCopy);
/*     */       }
/*     */     
/* 287 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 289 */       LbsConsole.getLogger("Data.Client.ObjectUtil").error("ObjectUtil.createCopy()", e);
/*     */ 
/*     */     
/*     */     }
/* 293 */     catch (IllegalAccessException e) {
/*     */       
/* 295 */       LbsConsole.getLogger("Data.Client.ObjectUtil").error("ObjectUtil.createCopy()", e);
/*     */ 
/*     */     
/*     */     }
/* 299 */     catch (InstantiationException e) {
/*     */       
/* 301 */       LbsConsole.getLogger("Data.Client.ObjectUtil").error("ObjectUtil.createCopy()", e);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 306 */     return dest;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPrimitive(Field f) {
/* 311 */     return f.getType().isPrimitive();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPrimitive(Class c) {
/* 316 */     return c.isPrimitive();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isImmutable(Field f) {
/* 321 */     return isImmutable(f.getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isImmutable(Class c) {
/* 326 */     return (c.equals(String.class) || c.equals(Integer.class) || c.equals(Long.class) || c.equals(Short.class) || c
/* 327 */       .equals(Byte.class) || c.equals(Character.class) || c.equals(Float.class) || c.equals(Double.class) || c
/* 328 */       .equals(Boolean.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasProperty(Class objClass, String propName, int index) {
/* 333 */     boolean found = hasProperty(objClass, propName, index, "get");
/*     */     
/* 335 */     if (!found && propName.charAt(0) == '_') {
/* 336 */       found = hasProperty(objClass, propName.substring(1), index, "_get");
/*     */     }
/* 338 */     if (!found) {
/* 339 */       found = hasProperty(objClass, propName, index, "is");
/*     */     }
/* 341 */     return found;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasProperty(Class objClass, String propName, int index, String methodPrefix) {
/* 346 */     Class[] paramTypes = null;
/* 347 */     if (index != -1) {
/* 348 */       paramTypes = new Class[] { int.class };
/*     */     }
/*     */     
/*     */     try {
/* 352 */       Method method = objClass.getMethod(methodPrefix + propName, paramTypes);
/*     */       
/* 354 */       return true;
/*     */     }
/* 356 */     catch (SecurityException securityException) {
/*     */ 
/*     */     
/*     */     }
/* 360 */     catch (NoSuchMethodException e) {
/*     */ 
/*     */       
/*     */       try {
/* 364 */         Field f = objClass.getField(propName);
/*     */         
/* 366 */         return true;
/*     */       }
/* 368 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 374 */     return false;
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
/*     */   public static void setProperty(Object target, String propName, Object value, int index) throws Exception {
/*     */     try {
/* 403 */       setProperty(target, propName, value, index, "set");
/*     */     }
/* 405 */     catch (NoSuchFieldException e) {
/*     */       
/* 407 */       if (propName.charAt(0) == '_') {
/* 408 */         setProperty(target, propName.substring(1), value, index, "_set");
/*     */       } else {
/* 410 */         throw e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setProperty(Object target, String propName, Object value, int index, String methodPrefix) throws Exception {
/* 416 */     Object[] parameters = null;
/*     */     
/* 418 */     if (index != -1) {
/* 419 */       parameters = new Object[] { value, Integer.valueOf(index) };
/*     */     } else {
/* 421 */       parameters = new Object[] { value };
/*     */     } 
/*     */     
/*     */     try {
/* 425 */       RttiUtil.runObjectInvokePath(target, propName, methodPrefix, parameters);
/*     */     }
/* 427 */     catch (NoSuchMethodException e) {
/*     */       
/* 429 */       Field f = target.getClass().getField(propName);
/* 430 */       f.set(target, value);
/*     */     }
/* 432 */     catch (Exception e) {
/*     */       
/* 434 */       LbsConsole.getLogger("Data.Client.ObjectUtil").error("Object=" + target + " prop=" + propName + "value=" + value, e);
/*     */ 
/*     */       
/* 437 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getProperty(Object target, String propName, int index) throws Exception {
/* 443 */     Object value = null;
/*     */     
/*     */     try {
/* 446 */       value = getProperty(target, propName, index, "get");
/*     */     }
/* 448 */     catch (NoSuchFieldException e) {
/*     */       
/* 450 */       if (propName.charAt(0) == '_') {
/* 451 */         value = getProperty(target, propName.substring(1), index, "_get");
/*     */       } else {
/* 453 */         value = getProperty(target, propName, index, "is");
/*     */       } 
/*     */     } 
/* 456 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PropertyValue getPropertyValue(Object target, String propName, int index) throws Exception {
/* 461 */     PropertyValue result = new PropertyValue();
/* 462 */     Object object = null;
/*     */     
/*     */     try {
/* 465 */       object = getProperty(target, propName, index, "get");
/* 466 */       result.hasProperty = true;
/* 467 */       result.propertyValue = object;
/*     */     }
/* 469 */     catch (NoSuchFieldException e) {
/*     */       
/* 471 */       if (propName.charAt(0) == '_') {
/*     */         
/* 473 */         object = getProperty(target, propName.substring(1), index, "_get");
/* 474 */         result.hasProperty = true;
/* 475 */         result.propertyValue = object;
/*     */       }
/*     */       else {
/*     */         
/* 479 */         object = getProperty(target, propName, index, "is");
/* 480 */         result.hasProperty = true;
/* 481 */         result.propertyValue = object;
/*     */       } 
/*     */     } 
/* 484 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Object getProperty(Object target, String propName, int index, String methodPrefix) throws Exception {
/* 489 */     Object[] parameters = null;
/*     */     
/* 491 */     if (index != -1)
/*     */     {
/* 493 */       parameters = new Object[] { Integer.valueOf(index) };
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 498 */       return RttiUtil.runObjectInvokePath(target, propName, methodPrefix, parameters);
/*     */     }
/* 500 */     catch (NoSuchMethodException e) {
/*     */       
/* 502 */       Field f = target.getClass().getField(propName);
/* 503 */       return f.get(target);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getPropertyValue(Object target, String propName, int index, String methodPrefix) throws Exception {
/* 509 */     Object[] parameters = null;
/*     */     
/* 511 */     if (index != -1)
/*     */     {
/* 513 */       parameters = new Object[] { Integer.valueOf(index) };
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 518 */       return RttiUtil.runObjectInvokePath(target, propName, methodPrefix, parameters);
/*     */     }
/* 520 */     catch (NoSuchMethodException e) {
/*     */       
/* 522 */       Field f = target.getClass().getField(propName);
/* 523 */       return f.get(target);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Class getMethodReturnClass(Class objClass, String methodName) {
/* 529 */     Method method = null;
/*     */     
/*     */     try {
/* 532 */       method = MethodInvoker.findMethod(objClass, methodName, null);
/*     */     }
/* 534 */     catch (NoSuchMethodException noSuchMethodException) {}
/*     */ 
/*     */ 
/*     */     
/* 538 */     if (method != null) {
/* 539 */       return method.getReturnType();
/*     */     }
/*     */     
/*     */     try {
/* 543 */       method = MethodInvoker.findMethod(objClass, methodName, new Object[] { Integer.valueOf(0) });
/*     */     }
/* 545 */     catch (NoSuchMethodException noSuchMethodException) {}
/*     */ 
/*     */ 
/*     */     
/* 549 */     if (method != null) {
/* 550 */       return method.getReturnType();
/*     */     }
/* 552 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Class getPropertyClass(Class objClass, String propName, int index) throws NoSuchFieldException {
/* 557 */     String methodName = "get" + propName;
/*     */     
/* 559 */     Class propClass = getMethodReturnClass(objClass, methodName);
/*     */     
/* 561 */     if (propClass != null) {
/* 562 */       return propClass;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 570 */     Field f = objClass.getField(propName);
/* 571 */     return f.getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSubClassOf(Class a, Class b) {
/* 576 */     return RttiUtil.isSubClassOf(a, b);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean areEqual(Object obj1, Object obj2) {
/* 625 */     boolean e1 = (obj1 == null);
/* 626 */     boolean e2 = (obj2 == null);
/*     */     
/* 628 */     if (e1 && e2) {
/* 629 */       return true;
/*     */     }
/* 631 */     if (e1 || e2) {
/* 632 */       return false;
/*     */     }
/* 634 */     return obj1.equals(obj2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setBOProperty(BasicBusinessObject bo, String property, Object value) {
/* 639 */     if (bo == null || property == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 643 */       RttiUtil.setProperty(bo, property, value);
/*     */     }
/* 645 */     catch (Exception e) {
/*     */       
/* 647 */       bo.getProperties().set(property, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBOPropertyInt(BasicBusinessObject bo, String property, int defVal) {
/* 653 */     Object value = null;
/* 654 */     if (bo != null && property != null) {
/*     */       
/*     */       try {
/* 657 */         value = RttiUtil.getProperty(bo, property);
/*     */       }
/* 659 */       catch (Exception e) {
/*     */ 
/*     */         
/*     */         try {
/* 663 */           value = bo.getProperties().get(property);
/*     */         }
/* 665 */         catch (Exception exception) {}
/*     */       } 
/*     */     }
/*     */     
/* 669 */     if (value instanceof Number)
/* 670 */       return ((Number)value).intValue(); 
/* 671 */     return defVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getBOPropertyString(BasicBusinessObject bo, String property) {
/* 676 */     Object value = null;
/* 677 */     if (bo != null && property != null) {
/*     */       
/*     */       try {
/* 680 */         value = RttiUtil.getProperty(bo, property);
/*     */       }
/* 682 */       catch (Exception e) {
/*     */ 
/*     */         
/*     */         try {
/* 686 */           value = bo.getProperties().get(property);
/*     */         }
/* 688 */         catch (Exception exception) {}
/*     */       } 
/*     */     }
/*     */     
/* 692 */     if (value instanceof String)
/* 693 */       return (String)value; 
/* 694 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String checkUUID(Object uuidVal) {
/* 699 */     if (uuidVal == null)
/* 700 */       return null; 
/* 701 */     String uuid = String.valueOf(uuidVal);
/* 702 */     if (uuid.length() == 32)
/*     */     {
/* 704 */       uuid = JLbsStringUtil.buildString(new String[] { uuid.substring(0, 8), "-", uuid.substring(8, 12), "-", uuid.substring(12, 16), "-", uuid
/* 705 */             .substring(16, 20), "-", uuid.substring(20) });
/*     */     }
/* 707 */     return uuid.toLowerCase(Locale.UK);
/*     */   }
/*     */   
/*     */   public static class ObjectUtilFieldHolder
/*     */   {
/* 712 */     private Map<Class, Field[]> ms_FieldsCahce = (Map)new HashMap<>();
/*     */ 
/*     */     
/*     */     private static ObjectUtilFieldHolder getInstance() {
/* 716 */       return LbsClassInstanceProvider.<ObjectUtilFieldHolder>getInstanceByClass(ObjectUtilFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ObjectUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */