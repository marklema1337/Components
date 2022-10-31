/*     */ package com.lbs.rmi.util;
/*     */ 
/*     */ import java.util.Collection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Assert
/*     */ {
/*     */   public static void isTrue(boolean expression, String message) {
/*  64 */     if (!expression) {
/*  65 */       throw new IllegalArgumentException(message);
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
/*     */   public static void isTrue(boolean expression) {
/*  77 */     isTrue(expression, "[Assertion failed] - this expression must be true");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void isNull(Object object, String message) {
/*  88 */     if (object != null) {
/*  89 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void isNull(Object object) {
/* 100 */     isNull(object, "[Assertion failed] - the object argument must be null");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notNull(Object object, String message) {
/* 111 */     if (object == null) {
/* 112 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notNull(Object object) {
/* 123 */     notNull(object, "[Assertion failed] - this argument is required; it must not be null");
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
/*     */   public static void hasLength(String text, String message) {
/* 135 */     if (!StringUtils.hasLength(text)) {
/* 136 */       throw new IllegalArgumentException(message);
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
/*     */   public static void hasLength(String text) {
/* 148 */     hasLength(text, 
/* 149 */         "[Assertion failed] - this String argument must have length; it must not be null or empty");
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
/*     */   public static void hasText(String text, String message) {
/* 161 */     if (!StringUtils.hasText(text)) {
/* 162 */       throw new IllegalArgumentException(message);
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
/*     */   public static void hasText(String text) {
/* 174 */     hasText(text, 
/* 175 */         "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void doesNotContain(String textToSearch, String substring, String message) {
/* 186 */     if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && 
/* 187 */       textToSearch.indexOf(substring) != -1) {
/* 188 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void doesNotContain(String textToSearch, String substring) {
/* 199 */     doesNotContain(textToSearch, substring, 
/* 200 */         "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
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
/*     */   public static void notEmpty(Object[] array, String message) {
/* 213 */     if (ObjectUtils.isEmpty(array)) {
/* 214 */       throw new IllegalArgumentException(message);
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
/*     */   public static void notEmpty(Object[] array) {
/* 226 */     notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
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
/*     */   public static void noNullElements(Object[] array, String message) {
/* 238 */     if (array != null) {
/* 239 */       for (int i = 0; i < array.length; i++) {
/* 240 */         if (array[i] == null) {
/* 241 */           throw new IllegalArgumentException(message);
/*     */         }
/*     */       } 
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
/*     */   public static void noNullElements(Object[] array) {
/* 255 */     noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
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
/*     */   public static void notEmpty(Collection collection, String message) {
/* 267 */     if (CollectionUtils.isEmpty(collection)) {
/* 268 */       throw new IllegalArgumentException(message);
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
/*     */   public static void notEmpty(Collection collection) {
/* 280 */     notEmpty(collection, 
/* 281 */         "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
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
/*     */   public static void notEmpty(Map map, String message) {
/* 293 */     if (CollectionUtils.isEmpty(map)) {
/* 294 */       throw new IllegalArgumentException(message);
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
/*     */   public static void notEmpty(Map map) {
/* 306 */     notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
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
/*     */   public static void isInstanceOf(Class clazz, Object obj) {
/* 319 */     isInstanceOf(clazz, obj, "");
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
/*     */   public static void isInstanceOf(Class type, Object obj, String message) {
/* 335 */     notNull(type, "Type to check against must not be null");
/* 336 */     if (!type.isInstance(obj)) {
/* 337 */       throw new IllegalArgumentException(String.valueOf(message) + 
/* 338 */           "Object of class [" + ((obj != null) ? obj.getClass().getName() : "null") + 
/* 339 */           "] must be an instance of " + type);
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
/*     */   public static void isAssignable(Class superType, Class subType) {
/* 351 */     isAssignable(superType, subType, "");
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
/*     */   public static void isAssignable(Class superType, Class<?> subType, String message) {
/* 366 */     notNull(superType, "Type to check against must not be null");
/* 367 */     if (subType == null || !superType.isAssignableFrom(subType)) {
/* 368 */       throw new IllegalArgumentException(String.valueOf(message) + subType + " is not assignable to " + superType);
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
/*     */   public static void state(boolean expression, String message) {
/* 383 */     if (!expression) {
/* 384 */       throw new IllegalStateException(message);
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
/*     */   public static void state(boolean expression) {
/* 398 */     state(expression, "[Assertion failed] - this state invariant must be true");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rm\\util\Assert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */