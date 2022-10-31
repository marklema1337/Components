/*     */ package com.lbs.rmi.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public abstract class CollectionUtils
/*     */ {
/*     */   public static boolean isEmpty(Collection collection) {
/*  44 */     return !(collection != null && !collection.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Map map) {
/*  54 */     return !(map != null && !map.isEmpty());
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
/*     */   public static List arrayToList(Object source) {
/*  67 */     return Arrays.asList(ObjectUtils.toObjectArray(source));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void mergeArrayIntoCollection(Object array, Collection<Object> collection) {
/*  76 */     if (collection == null) {
/*  77 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  79 */     Object[] arr = ObjectUtils.toObjectArray(array);
/*  80 */     for (int i = 0; i < arr.length; i++) {
/*  81 */       collection.add(arr[i]);
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
/*     */   public static void mergePropertiesIntoMap(Properties props, Map<String, String> map) {
/*  94 */     if (map == null) {
/*  95 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  97 */     if (props != null) {
/*  98 */       for (Enumeration<?> en = props.propertyNames(); en.hasMoreElements(); ) {
/*  99 */         String key = (String)en.nextElement();
/* 100 */         map.put(key, props.getProperty(key));
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
/*     */   public static boolean contains(Iterator iterator, Object element) {
/* 113 */     if (iterator != null) {
/* 114 */       while (iterator.hasNext()) {
/* 115 */         Object candidate = iterator.next();
/* 116 */         if (ObjectUtils.nullSafeEquals(candidate, element)) {
/* 117 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean contains(Enumeration enumeration, Object element) {
/* 131 */     if (enumeration != null) {
/* 132 */       while (enumeration.hasMoreElements()) {
/* 133 */         Object candidate = enumeration.nextElement();
/* 134 */         if (ObjectUtils.nullSafeEquals(candidate, element)) {
/* 135 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 139 */     return false;
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
/*     */   public static boolean containsInstance(Collection collection, Object element) {
/* 151 */     if (collection != null) {
/* 152 */       for (Iterator it = collection.iterator(); it.hasNext(); ) {
/* 153 */         Object candidate = it.next();
/* 154 */         if (candidate == element) {
/* 155 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsAny(Collection source, Collection candidates) {
/* 170 */     if (isEmpty(source) || isEmpty(candidates)) {
/* 171 */       return false;
/*     */     }
/* 173 */     for (Iterator it = candidates.iterator(); it.hasNext();) {
/* 174 */       if (source.contains(it.next())) {
/* 175 */         return true;
/*     */       }
/*     */     } 
/* 178 */     return false;
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
/*     */   public static Object findFirstMatch(Collection source, Collection candidates) {
/* 191 */     if (isEmpty(source) || isEmpty(candidates)) {
/* 192 */       return null;
/*     */     }
/* 194 */     for (Iterator it = candidates.iterator(); it.hasNext(); ) {
/* 195 */       Object candidate = it.next();
/* 196 */       if (source.contains(candidate)) {
/* 197 */         return candidate;
/*     */       }
/*     */     } 
/* 200 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object findValueOfType(Collection collection, Class type) {
/* 211 */     if (isEmpty(collection)) {
/* 212 */       return null;
/*     */     }
/* 214 */     Object value = null;
/* 215 */     for (Iterator it = collection.iterator(); it.hasNext(); ) {
/* 216 */       Object obj = it.next();
/* 217 */       if (type == null || type.isInstance(obj)) {
/* 218 */         if (value != null)
/*     */         {
/* 220 */           return null;
/*     */         }
/* 222 */         value = obj;
/*     */       } 
/*     */     } 
/* 225 */     return value;
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
/*     */   public static Object findValueOfType(Collection collection, Class[] types) {
/* 238 */     if (isEmpty(collection) || ObjectUtils.isEmpty((Object[])types)) {
/* 239 */       return null;
/*     */     }
/* 241 */     for (int i = 0; i < types.length; i++) {
/* 242 */       Object value = findValueOfType(collection, types[i]);
/* 243 */       if (value != null) {
/* 244 */         return value;
/*     */       }
/*     */     } 
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasUniqueObject(Collection collection) {
/* 257 */     if (isEmpty(collection)) {
/* 258 */       return false;
/*     */     }
/* 260 */     boolean hasCandidate = false;
/* 261 */     Object candidate = null;
/* 262 */     for (Iterator it = collection.iterator(); it.hasNext(); ) {
/* 263 */       Object elem = it.next();
/* 264 */       if (!hasCandidate) {
/* 265 */         hasCandidate = true;
/* 266 */         candidate = elem; continue;
/*     */       } 
/* 268 */       if (candidate != elem) {
/* 269 */         return false;
/*     */       }
/*     */     } 
/* 272 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rm\\util\CollectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */