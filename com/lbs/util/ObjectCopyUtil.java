/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.lbs.data.objects.BusinessObjectIdentifier;
/*     */ import com.lbs.data.objects.CustomBusinessObject;
/*     */ import com.lbs.data.objects.CustomObjectIdentifier;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectCopyUtil
/*     */ {
/*     */   private static Object cloneField(ObjectCopyEnvironment env, Object srcOwner, Object dstOwner, Field field, Object fieldVal) throws CloneNotSupportedException, ObjectCopyException {
/*  30 */     if (fieldVal == null) {
/*  31 */       return null;
/*     */     }
/*  33 */     Object srcObject = fieldVal;
/*     */     
/*  35 */     if (env != null) {
/*     */       
/*  37 */       if (!env.isHandled(fieldVal.getClass())) {
/*  38 */         return fieldVal;
/*     */       }
/*  40 */       if (env.getListener() != null) {
/*     */         
/*  42 */         ObjectCopyEvent e = new ObjectCopyEvent(env, srcOwner, dstOwner, fieldVal, null, field.getName());
/*  43 */         Object newValue = env.getListener().createCopy(e);
/*     */         
/*  45 */         if (e.isConsumed()) {
/*     */           
/*  47 */           env.add(fieldVal, newValue);
/*  48 */           return newValue;
/*     */         } 
/*     */       } 
/*     */       
/*  52 */       if (env.contains(fieldVal)) {
/*  53 */         return env.get(fieldVal);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/*  58 */       Method m = field.getType().getMethod("clone", new Class[0]);
/*     */       
/*     */       try {
/*  61 */         Object newValue = m.invoke(fieldVal, new Object[0]);
/*  62 */         if (env != null)
/*  63 */           env.add(fieldVal, newValue); 
/*  64 */         fieldVal = newValue;
/*     */       }
/*  66 */       catch (InvocationTargetException e) {
/*     */         
/*  68 */         Throwable t = e.getTargetException();
/*     */         
/*  70 */         if (t instanceof Error)
/*     */         {
/*  72 */           throw (Error)t;
/*     */         }
/*  74 */         if (t instanceof RuntimeException)
/*     */         {
/*  76 */           throw (RuntimeException)t;
/*     */         }
/*  78 */         if (t instanceof CloneNotSupportedException)
/*     */         {
/*  80 */           fieldVal = createCopy(env, srcOwner, dstOwner, fieldVal, field.getName());
/*     */         }
/*     */         else
/*     */         {
/*  84 */           throw new RuntimeException(t.getMessage());
/*     */         }
/*     */       
/*     */       } 
/*  88 */     } catch (NoSuchMethodException noSuchMethodException) {
/*     */ 
/*     */     
/*  91 */     } catch (IllegalAccessException illegalAccessException) {}
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (env != null && env.getListener() != null) {
/*     */       
/*  97 */       ObjectCopyEvent e = new ObjectCopyEvent(env, srcOwner, dstOwner, srcObject, fieldVal, field.getName());
/*  98 */       env.getListener().doneCopy(e);
/*     */     } 
/*     */     
/* 101 */     return fieldVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void cloneFields(ObjectCopyEnvironment env, Object src, Object dest, Class objClass, boolean deep) throws CloneNotSupportedException, ObjectCopyException {
/* 107 */     Field[] fields = objClass.getDeclaredFields();
/* 108 */     if (env != null)
/*     */     {
/* 110 */       if (env.getListener() != null)
/*     */       {
/* 112 */         fields = env.getListener().sortObjectFields(src, fields);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 118 */     for (int i = 0; i < fields.length; i++) {
/*     */       
/* 120 */       Field field = fields[i];
/* 121 */       int fieldMods = field.getModifiers();
/*     */       
/* 123 */       if (!Modifier.isFinal(fieldMods)) {
/*     */ 
/*     */         
/* 126 */         field.setAccessible(true);
/*     */ 
/*     */         
/*     */         try {
/* 130 */           Object val = field.get(src);
/* 131 */           if (val != null && dest instanceof BusinessObject && val.getClass().equals(BusinessObjectIdentifier.class)) {
/*     */             
/* 133 */             val = new BusinessObjectIdentifier((BusinessObject)dest);
/*     */           }
/* 135 */           else if (val != null && dest instanceof CustomBusinessObject && val
/* 136 */             .getClass().equals(CustomObjectIdentifier.class)) {
/*     */             
/* 138 */             val = new CustomObjectIdentifier((CustomBusinessObject)dest);
/*     */ 
/*     */           
/*     */           }
/* 142 */           else if (deep && !ObjectUtil.isPrimitive(field) && !ObjectUtil.isImmutable(field)) {
/*     */             
/* 144 */             if (field.getType().isArray()) {
/* 145 */               val = createCopy(env, src, dest, val, field.getName());
/*     */             } else {
/* 147 */               val = cloneField(env, src, dest, field, val);
/*     */             } 
/*     */           } 
/* 150 */           field.set(dest, val);
/*     */         }
/* 152 */         catch (IllegalAccessException e) {
/*     */           
/* 154 */           LbsConsole.getLogger("Data.Client.ObjectCopyUtil").error("Can not access " + field.getName(), e);
/* 155 */           throw new RuntimeException(e.getMessage());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void internalCopy(ObjectCopyEnvironment env, Object src, Object dest, Class objClass, boolean deepCopy) throws CloneNotSupportedException, ObjectCopyException {
/* 163 */     Class superClass = objClass.getSuperclass();
/*     */     
/* 165 */     if (superClass != null && (superClass.equals(BusinessObject.class) || superClass.equals(BasicBusinessObject.class))) {
/*     */       
/* 167 */       cloneFields(env, src, dest, objClass, deepCopy);
/*     */       
/*     */       return;
/*     */     } 
/* 171 */     while (superClass != null) {
/*     */       
/* 173 */       cloneFields(env, src, dest, superClass, deepCopy);
/* 174 */       superClass = superClass.getSuperclass();
/*     */       
/* 176 */       if (superClass.equals(BusinessObject.class) || superClass.equals(BasicBusinessObject.class))
/*     */         break; 
/*     */     } 
/* 179 */     cloneFields(env, src, dest, objClass, deepCopy);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object createCopy(ObjectCopyEnvironment env, Object srcOwner, Object dstOwner, Object srcObject, String memberName) {
/* 184 */     Object dest = null;
/* 185 */     if (srcObject == null) {
/* 186 */       return null;
/*     */     }
/* 188 */     Class<?> objClass = srcObject.getClass();
/* 189 */     if (ObjectUtil.isPrimitive(objClass) || ObjectUtil.isImmutable(objClass)) {
/* 190 */       return srcObject;
/*     */     }
/*     */     
/*     */     try {
/* 194 */       if (env != null) {
/*     */         
/* 196 */         if (!env.isHandled(srcObject.getClass())) {
/* 197 */           return srcObject;
/*     */         }
/* 199 */         if (env.getListener() != null) {
/*     */           
/* 201 */           ObjectCopyEvent e = new ObjectCopyEvent(env, srcOwner, dstOwner, srcObject, null, memberName);
/* 202 */           dest = env.getListener().createCopy(e);
/*     */           
/* 204 */           if (e.isConsumed()) {
/*     */             
/* 206 */             env.add(srcObject, dest);
/* 207 */             return dest;
/*     */           } 
/*     */         } 
/*     */         
/* 211 */         if (env.contains(srcObject)) {
/* 212 */           return env.get(srcObject);
/*     */         }
/* 214 */         if (objClass.isArray()) {
/*     */           
/* 216 */           int length = Array.getLength(srcObject);
/* 217 */           dest = Array.newInstance(objClass.getComponentType(), length);
/*     */           
/* 219 */           env.add(srcObject, dest);
/*     */           
/* 221 */           Object o = null;
/* 222 */           for (int i = 0; i < length; i++)
/*     */           {
/* 224 */             o = Array.get(srcObject, i);
/* 225 */             o = createCopy(env, srcOwner, dstOwner, o, memberName);
/* 226 */             Array.set(dest, i, o);
/*     */           }
/*     */         
/* 229 */         } else if (objClass.equals(BigDecimal.class)) {
/*     */           
/* 231 */           dest = (new BigDecimal(0)).add((BigDecimal)srcObject);
/* 232 */           env.add(srcObject, dest);
/*     */         }
/* 234 */         else if (srcObject instanceof BusinessObject) {
/*     */           
/* 236 */           dest = ((BusinessObject)srcObject).cloneSingle();
/* 237 */           env.add(srcObject, dest);
/* 238 */           internalCopy(env, srcObject, dest, objClass, true);
/*     */         }
/*     */         else {
/*     */           
/* 242 */           dest = objClass.newInstance();
/* 243 */           env.add(srcObject, dest);
/* 244 */           internalCopy(env, srcObject, dest, objClass, true);
/*     */         }
/*     */       
/*     */       } 
/* 248 */     } catch (Exception e) {
/*     */       
/* 250 */       LbsConsole.getLogger("Data.Client.ObjectCopyUtil").error(null, e);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 255 */     if (env != null && env.getListener() != null) {
/*     */       
/* 257 */       ObjectCopyEvent e = new ObjectCopyEvent(env, srcOwner, dstOwner, srcObject, dest, memberName);
/* 258 */       env.getListener().doneCopy(e);
/*     */     } 
/*     */     
/* 261 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deepCopy(Object src, Object dest, Class objClass, IObjectCopyListener listener) throws CloneNotSupportedException, ObjectCopyException {
/* 267 */     ObjectCopyEnvironment env = new ObjectCopyEnvironment();
/* 268 */     deepCopy(src, dest, objClass, env, listener);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deepCopy(Object src, Object dest, Class objClass, ObjectCopyEnvironment env, IObjectCopyListener listener) throws CloneNotSupportedException, ObjectCopyException {
/* 274 */     if (listener != null) {
/* 275 */       env.setListener(listener);
/*     */     }
/* 277 */     internalCopy(env, src, dest, objClass, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ObjectCopyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */