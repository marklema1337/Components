/*     */ package com.lbs.invoke;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MethodInvoker
/*     */ {
/*     */   public static Method[] getMethod(Class type, String methodName) throws NoSuchMethodException {
/*  23 */     if (type == null || methodName == null)
/*  24 */       return null; 
/*  25 */     boolean canCache = !(type.getClassLoader() instanceof com.lbs.interfaces.ILbsDisposeableClassLoader);
/*     */     
/*  27 */     String path = String.valueOf(type.getName()) + "." + methodName;
/*  28 */     synchronized ((MethodInvokerFieldHolder.getInstance()).ms_MethodCache) {
/*     */       
/*  30 */       result = (Method[])(MethodInvokerFieldHolder.getInstance()).ms_MethodCache.get(path);
/*     */     } 
/*  32 */     if (result != null)
/*  33 */       return result; 
/*  34 */     Method[] result = getMethodInternal(type, methodName);
/*  35 */     if (result != null && canCache)
/*  36 */       synchronized ((MethodInvokerFieldHolder.getInstance()).ms_MethodCache) {
/*     */         
/*  38 */         (MethodInvokerFieldHolder.getInstance()).ms_MethodCache.put(path, result);
/*     */       }  
/*  40 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearMethod(Class type, String methodName) {
/*  45 */     if (type == null || methodName == null)
/*     */       return; 
/*  47 */     String path = String.valueOf(type.getName()) + "." + methodName;
/*  48 */     synchronized ((MethodInvokerFieldHolder.getInstance()).ms_MethodCache) {
/*     */       
/*  50 */       (MethodInvokerFieldHolder.getInstance()).ms_MethodCache.remove(path);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Method[] getMethodInternal(Class type, String methodName) throws NoSuchMethodException {
/*  56 */     if (methodName != null && type != null) {
/*     */       
/*  58 */       Method[] methods = type.getMethods();
/*  59 */       if (methods != null && methods.length > 0) {
/*     */         
/*  61 */         ArrayList<Method> list = new ArrayList();
/*  62 */         for (int i = 0; i < methods.length; i++) {
/*     */           
/*  64 */           Method method = methods[i];
/*  65 */           if (method == null) {
/*     */             
/*  67 */             if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL > 8) {
/*  68 */               System.out.println("Method is null: not possible");
/*     */             }
/*     */           } else {
/*  71 */             String foundMethodName = method.getName();
/*  72 */             if (foundMethodName == null)
/*     */             
/*  74 */             { if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL > 8) {
/*  75 */                 System.out.println("Method name is null: not possible");
/*     */               } }
/*     */             
/*  78 */             else if (methodName.compareTo(foundMethodName) == 0)
/*  79 */             { list.add(method); } 
/*     */           } 
/*  81 */         }  if (list.size() > 0) {
/*     */           
/*  83 */           Method[] result = new Method[list.size()];
/*  84 */           for (int j = 0; j < result.length; j++)
/*  85 */             result[j] = list.get(j); 
/*  86 */           return result;
/*     */         } 
/*     */       } 
/*     */     } 
/*  90 */     throw new NoSuchMethodException(("The method " + methodName + " could not be found in the class " + type != null) ? type.toString() : null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Method findMethod(Class type, String methodName, Object[] parameters) throws NoSuchMethodException {
/*  95 */     Method[] methods = getMethod(type, methodName);
/*  96 */     if (methods.length == 1)
/*  97 */       return methods[0]; 
/*  98 */     return getBestMatchMethod(methods, parameters);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Method findMethod(Class type, String methodName, Class[] parameters) throws NoSuchMethodException {
/* 103 */     Method[] methods = getMethod(type, methodName);
/* 104 */     if (methods.length == 1)
/* 105 */       return methods[0]; 
/* 106 */     return getBestMatchMethod(methods, parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method findMethodSafe(Class type, String methodName, Class[] parameters) {
/*     */     try {
/* 113 */       return findMethod(type, methodName, parameters);
/*     */     }
/* 115 */     catch (Exception e) {
/*     */       
/* 117 */       if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL > 8) {
/* 118 */         System.out.println("findMethodSafe: " + e);
/*     */       }
/* 120 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Method getBestMatchMethod(Method[] methods, Object[] parameters) {
/* 125 */     return getBestMatchMethod(methods, getParameterClassInfos(parameters));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Method getBestMatchMethod(Method[] methods, Class[] parameters) {
/* 130 */     int iParamCount = (parameters == null) ? 
/* 131 */       0 : 
/* 132 */       parameters.length;
/* 133 */     ArrayList<Method> list = new ArrayList();
/* 134 */     for (int i = 0; i < methods.length; i++) {
/*     */       
/* 136 */       Method method = methods[i];
/* 137 */       Class[] paramTypes = method.getParameterTypes();
/* 138 */       int iMethodParamCount = paramTypes.length;
/*     */       
/* 140 */       if (iParamCount == iMethodParamCount) {
/*     */         
/* 142 */         int match = 0;
/* 143 */         for (int k = 0; k < iParamCount; k++) {
/*     */           
/* 145 */           if (paramTypes[k] == parameters[k])
/* 146 */             match++; 
/*     */         } 
/* 148 */         if (match == iParamCount)
/* 149 */           return method; 
/* 150 */         list.add(method);
/*     */       } 
/*     */     } 
/* 153 */     switch (list.size()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 162 */         return null;
/*     */       case 1:
/*     */         return list.get(0);
/*     */     } 
/*     */     System.err.println("Ambigious method call " + ((Method)list.get(0)).getName());
/*     */   } public static Object invokeMethod(Method method, Object target, Object[] parameters) throws InvocationTargetException, IllegalAccessException {
/* 168 */     if (!method.isAccessible())
/* 169 */       method.setAccessible(true); 
/* 170 */     return method.invoke(target, parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object invokeMethod(String method, Object target, Object[] parameters) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
/* 176 */     return invokeMethod(findMethod(target.getClass(), method, parameters), target, parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object invokeMethodSafe(String method, Object target, Object[] parameters) {
/*     */     try {
/* 183 */       return invokeMethod(findMethod(target.getClass(), method, parameters), target, parameters);
/*     */     }
/* 185 */     catch (Exception e) {
/*     */       
/* 187 */       if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL > 8) {
/* 188 */         System.out.println("SafeInvoke: " + e);
/*     */       }
/* 190 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object invokeMethodSilent(String method, Object target, Object[] parameters) {
/*     */     try {
/* 197 */       return invokeMethod(findMethod(target.getClass(), method, parameters), target, parameters);
/*     */     }
/* 199 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 202 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static Class[] getParameterClassInfos(Object[] parameters) {
/* 207 */     if (parameters == null || parameters.length == 0)
/* 208 */       return new Class[0]; 
/* 209 */     Class[] result = new Class[parameters.length];
/* 210 */     for (int i = 0; i < parameters.length; i++) {
/*     */       
/* 212 */       if (parameters[i] == null) {
/* 213 */         result[i] = Object.class;
/*     */       } else {
/* 215 */         result[i] = parameters[i].getClass();
/*     */       } 
/* 217 */     }  return result;
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
/*     */   public static class MethodInvokerFieldHolder
/*     */   {
/* 257 */     private Hashtable ms_MethodCache = new Hashtable<>();
/*     */ 
/*     */     
/*     */     public static MethodInvokerFieldHolder getInstance() {
/* 261 */       return (MethodInvokerFieldHolder)LbsClassInstanceProvider.getInstanceByClass(MethodInvokerFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\MethodInvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */