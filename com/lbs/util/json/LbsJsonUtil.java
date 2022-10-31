/*     */ package com.lbs.util.json;
/*     */ 
/*     */ import com.google.gson.ExclusionStrategy;
/*     */ import com.google.gson.FieldAttributes;
/*     */ import com.google.gson.FieldNamingStrategy;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Type;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsJsonUtil
/*     */ {
/*  36 */   private static Hashtable<Class, Hashtable<String, String>> classSameFieldCache = new Hashtable<>();
/*     */   
/*     */   private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:z";
/*     */   
/*     */   public static String toJson(Object cand, boolean prettyPrint, boolean storeClassName) {
/*  41 */     Object obj = storeClassName ? 
/*  42 */       new JsonSerializedObject(cand) : 
/*  43 */       cand;
/*  44 */     GsonBuilder gsonBuilder = new GsonBuilder();
/*  45 */     if (obj.getClass().getAnnotation(LbsJsonCustomizer.class) != null) {
/*     */       
/*  47 */       final Hashtable<String, String> sameFields = getSameFieldMap(classSameFieldCache, obj.getClass());
/*  48 */       gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy()
/*     */           {
/*     */             public boolean shouldSkipField(FieldAttributes f)
/*     */             {
/*  52 */               return sameFields.containsKey(f.getName());
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean shouldSkipClass(Class<?> clazz) {
/*  57 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*  61 */     if (prettyPrint)
/*  62 */       gsonBuilder.setPrettyPrinting(); 
/*  63 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/*  64 */     Gson gson = gsonBuilder.create();
/*  65 */     System.out.println((new Gson()).toJsonTree(obj));
/*  66 */     return gson.toJson(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toJson(Object cand, boolean prettyPrint, boolean storeClassName, boolean serializeNulls) {
/*  72 */     final Object obj = storeClassName ? 
/*  73 */       new JsonSerializedObject(cand) : 
/*  74 */       cand;
/*  75 */     final ArrayList<Object> lines = new ArrayList();
/*  76 */     GsonBuilder gsonBuilder = new GsonBuilder();
/*  77 */     if (prettyPrint)
/*  78 */       gsonBuilder.setPrettyPrinting(); 
/*  79 */     if (serializeNulls) {
/*  80 */       gsonBuilder.serializeNulls();
/*     */     } else {
/*     */       
/*  83 */       gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy()
/*     */           {
/*     */             
/*     */             public boolean shouldSkipField(FieldAttributes f)
/*     */             {
/*  88 */               String fieldName = f.getName();
/*  89 */               if (fieldName.substring(0, 1).equals("i"))
/*  90 */                 fieldName = String.valueOf(fieldName.substring(0, 1).replace("i", "ı")) + fieldName.substring(1); 
/*  91 */               fieldName = String.valueOf(fieldName.substring(0, 1).toUpperCase()) + fieldName.substring(1);
/*     */ 
/*     */               
/*     */               try {
/*  95 */                 Method method = f.getDeclaringClass().getMethod("get" + fieldName, null);
/*  96 */                 Object value = method.invoke(obj, null);
/*  97 */                 if (value != null)
/*     */                 {
/*  99 */                   if (value instanceof String && value.equals(""))
/* 100 */                     return true; 
/* 101 */                   if (value instanceof Integer && ((Integer)value).intValue() == 0)
/* 102 */                     return true; 
/* 103 */                   if (value instanceof Double && ((Double)value).doubleValue() == 0.0D)
/* 104 */                     return true; 
/* 105 */                   if (value instanceof BigDecimal && ((BigDecimal)value).compareTo(BigDecimal.ZERO) == 0) {
/* 106 */                     return true;
/*     */                   }
/* 108 */                   if (value instanceof ArrayList) {
/*     */                     
/* 110 */                     lines.add((ArrayList)value);
/*     */                   } else {
/*     */                     
/* 113 */                     return false;
/*     */                   }
/*     */                 
/*     */                 }
/*     */               
/* 118 */               } catch (NoSuchMethodException|SecurityException noSuchMethodException) {
/*     */ 
/*     */               
/* 121 */               } catch (IllegalAccessException illegalAccessException) {
/*     */ 
/*     */               
/* 124 */               } catch (IllegalArgumentException e) {
/*     */                 
/* 126 */                 if (lines instanceof ArrayList)
/*     */                 {
/* 128 */                   for (int i = 0; i < lines.size(); i++)
/*     */                   {
/*     */                     
/*     */                     try {
/* 132 */                       Method method = f.getDeclaringClass().getMethod("get" + fieldName, null);
/* 133 */                       boolean emptyValue = false;
/* 134 */                       for (int j = 0; j < ((ArrayList)lines.get(i)).size(); j++) {
/*     */                         
/* 136 */                         Object value = method.invoke(((ArrayList)lines.get(i)).get(j), null);
/* 137 */                         if (value != null)
/*     */                         {
/* 139 */                           if (value instanceof String && value.equals("")) {
/* 140 */                             emptyValue = true;
/* 141 */                           } else if (value instanceof Integer && ((Integer)value).intValue() == 0) {
/* 142 */                             emptyValue = true;
/* 143 */                           } else if (value instanceof Double && ((Double)value).doubleValue() == 0.0D) {
/* 144 */                             emptyValue = true;
/* 145 */                           } else if (value instanceof BigDecimal && ((BigDecimal)value).compareTo(BigDecimal.ZERO) == 0) {
/* 146 */                             emptyValue = true;
/*     */                           } else {
/*     */                             
/* 149 */                             emptyValue = false;
/*     */                             
/*     */                             break;
/*     */                           } 
/*     */                         }
/*     */                       } 
/*     */                       
/* 156 */                       return emptyValue;
/*     */                     
/*     */                     }
/* 159 */                     catch (Exception exception) {}
/*     */                   
/*     */                   }
/*     */ 
/*     */                 
/*     */                 }
/*     */               }
/* 166 */               catch (InvocationTargetException invocationTargetException) {}
/*     */ 
/*     */ 
/*     */               
/* 170 */               return false;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean shouldSkipClass(Class<?> clazz) {
/* 175 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 180 */     gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy()
/*     */         {
/*     */ 
/*     */           
/*     */           public String translateName(Field f)
/*     */           {
/* 186 */             int indexOf = f.getName().indexOf("_");
/* 187 */             if (indexOf > 0)
/* 188 */               return f.getName().substring(indexOf + 1); 
/* 189 */             return f.getName();
/*     */           }
/*     */         });
/*     */     
/* 193 */     if (obj.getClass().getAnnotation(LbsJsonCustomizer.class) != null) {
/*     */       
/* 195 */       final Hashtable<String, String> sameFields = getSameFieldMap(classSameFieldCache, obj.getClass());
/* 196 */       gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy()
/*     */           {
/*     */             public boolean shouldSkipField(FieldAttributes f)
/*     */             {
/* 200 */               return sameFields.containsKey(f.getName());
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean shouldSkipClass(Class<?> clazz) {
/* 205 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 209 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/* 210 */     Gson gson = gsonBuilder.create();
/*     */     
/* 212 */     return gson.toJson(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toJsonMandatory(Object cand, boolean prettyPrint, boolean storeClassName, boolean serializeNulls, final HashMap<String, String[]> mandatoryFields) {
/* 218 */     Object obj = storeClassName ? 
/* 219 */       new JsonSerializedObject(cand) : 
/* 220 */       cand;
/* 221 */     GsonBuilder gsonBuilder = new GsonBuilder();
/* 222 */     gsonBuilder.setPrettyPrinting().serializeNulls();
/* 223 */     gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy()
/*     */         {
/*     */ 
/*     */           
/*     */           public String translateName(Field f)
/*     */           {
/* 229 */             int indexOf = f.getName().indexOf("_");
/* 230 */             if (indexOf > 0)
/* 231 */               return f.getName().substring(indexOf + 1); 
/* 232 */             return f.getName();
/*     */           }
/*     */         });
/*     */     
/* 236 */     gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy()
/*     */         {
/*     */           public boolean shouldSkipField(FieldAttributes f)
/*     */           {
/* 240 */             String classFullName = f.getDeclaringClass().getName();
/* 241 */             if (!JLbsStringUtil.isEmpty(classFullName)) {
/*     */               
/* 243 */               String className = "";
/* 244 */               int lastIndex = classFullName.lastIndexOf(".");
/* 245 */               if (lastIndex > -1) {
/* 246 */                 className = classFullName.substring(lastIndex + 1);
/*     */               }
/* 248 */               if (!JLbsStringUtil.isEmpty(className)) {
/*     */                 
/* 250 */                 String[] mandatoryFieldArray = (String[])mandatoryFields.get(className);
/* 251 */                 if (mandatoryFieldArray != null && mandatoryFieldArray.length > 0)
/*     */                 {
/* 253 */                   for (int i = 0; i < mandatoryFieldArray.length; i++) {
/*     */                     
/* 255 */                     String mandatoryField = mandatoryFieldArray[i];
/* 256 */                     if (f.getName().equalsIgnoreCase(mandatoryField)) {
/* 257 */                       return false;
/*     */                     }
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/* 263 */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean shouldSkipClass(Class<?> clazz) {
/* 268 */             return false;
/*     */           }
/*     */         });
/*     */     
/* 272 */     if (prettyPrint)
/* 273 */       gsonBuilder.setPrettyPrinting(); 
/* 274 */     if (serializeNulls)
/* 275 */       gsonBuilder.serializeNulls(); 
/* 276 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/* 277 */     Gson gson = gsonBuilder.create();
/*     */     
/* 279 */     return gson.toJson(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> Object fromJson(String jsonStr, Class<T> clazz) throws Exception {
/* 284 */     GsonBuilder gsonBuilder = new GsonBuilder();
/* 285 */     Hashtable<String, String> lbsSame = null;
/* 286 */     if (jsonStr.startsWith("@")) {
/*     */       
/* 288 */       jsonStr = jsonStr.substring(jsonStr.indexOf("\":\"") + 3);
/* 289 */       int ind = jsonStr.indexOf("\",\"");
/* 290 */       String className = jsonStr.substring(0, ind);
/* 291 */       String ia = jsonStr.substring(ind + 3);
/* 292 */       if (ia.startsWith("instanceArray")) {
/* 293 */         jsonStr = ia.substring(16, ia.length() - 2);
/* 294 */       } else if (ia.startsWith("instance")) {
/* 295 */         jsonStr = ia.substring(10, ia.length() - 1);
/* 296 */       }  clazz = (Class)Class.forName(className);
/*     */     } 
/* 298 */     if (clazz.getAnnotation(LbsJsonCustomizer.class) != null) {
/*     */       
/* 300 */       final Hashtable<String, String> sameFields = getSameFieldMap(classSameFieldCache, clazz);
/* 301 */       lbsSame = sameFields;
/* 302 */       gsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy()
/*     */           {
/*     */             public boolean shouldSkipField(FieldAttributes f)
/*     */             {
/* 306 */               return sameFields.containsKey(f.getName());
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean shouldSkipClass(Class<?> clazz) {
/* 311 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 315 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/* 316 */     Gson gson = gsonBuilder.create();
/* 317 */     T obj = (T)gson.fromJson(jsonStr, clazz);
/* 318 */     if (lbsSame == null) {
/* 319 */       return obj;
/*     */     }
/* 321 */     for (Map.Entry<String, String> fn : lbsSame.entrySet())
/*     */     {
/* 323 */       PropertyUtils.setProperty(obj, fn.getKey(), PropertyUtils.getProperty(obj, fn.getValue()));
/*     */     }
/* 325 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> Object fromJson(String jsonStr, Class<T> clazz, boolean modifyFields) throws Exception {
/* 330 */     GsonBuilder gsonBuilder = new GsonBuilder();
/* 331 */     if (modifyFields)
/* 332 */       gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy()
/*     */           {
/*     */ 
/*     */             
/*     */             public String translateName(Field f)
/*     */             {
/* 338 */               int indexOf = f.getName().indexOf("_");
/* 339 */               if (indexOf > 0)
/* 340 */                 return f.getName().substring(indexOf + 1); 
/* 341 */               return f.getName();
/*     */             }
/*     */           }); 
/* 344 */     Hashtable<String, String> lbsSame = null;
/* 345 */     if (jsonStr.startsWith("@")) {
/*     */       
/* 347 */       jsonStr = jsonStr.substring(jsonStr.indexOf("\":\"") + 3);
/* 348 */       int ind = jsonStr.indexOf("\",\"");
/* 349 */       String className = jsonStr.substring(0, ind);
/* 350 */       String ia = jsonStr.substring(ind + 3);
/* 351 */       if (ia.startsWith("instanceArray")) {
/* 352 */         jsonStr = ia.substring(16, ia.length() - 2);
/* 353 */       } else if (ia.startsWith("instance")) {
/* 354 */         jsonStr = ia.substring(10, ia.length() - 1);
/* 355 */       }  clazz = (Class)Class.forName(className);
/*     */     } 
/* 357 */     if (clazz.getAnnotation(LbsJsonCustomizer.class) != null) {
/*     */       
/* 359 */       final Hashtable<String, String> sameFields = getSameFieldMap(classSameFieldCache, clazz);
/* 360 */       lbsSame = sameFields;
/* 361 */       gsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy()
/*     */           {
/*     */             public boolean shouldSkipField(FieldAttributes f)
/*     */             {
/* 365 */               return sameFields.containsKey(f.getName());
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean shouldSkipClass(Class<?> clazz) {
/* 370 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/* 374 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/* 375 */     Gson gson = gsonBuilder.create();
/* 376 */     T obj = (T)gson.fromJson(jsonStr, clazz);
/* 377 */     if (lbsSame == null) {
/* 378 */       return obj;
/*     */     }
/* 380 */     for (Map.Entry<String, String> fn : lbsSame.entrySet())
/*     */     {
/* 382 */       PropertyUtils.setProperty(obj, fn.getKey(), PropertyUtils.getProperty(obj, fn.getValue()));
/*     */     }
/* 384 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Hashtable<String, String> getSameFieldMap(Hashtable<Class<?>, Hashtable<String, String>> classSameFieldCache, Class<?> clazz) {
/* 390 */     Hashtable<String, String> sameFields = classSameFieldCache.get(clazz);
/* 391 */     if (sameFields == null) {
/*     */       
/* 393 */       sameFields = new Hashtable<>();
/* 394 */       classSameFieldCache.put(clazz, sameFields);
/* 395 */       LbsJsonCustomizer anno = clazz.<LbsJsonCustomizer>getAnnotation(LbsJsonCustomizer.class); byte b; int i; String[] arrayOfString;
/* 396 */       for (i = (arrayOfString = anno.sameFields()).length, b = 0; b < i; ) { String tupleStr = arrayOfString[b];
/*     */         
/* 398 */         String[] tuple = tupleStr.split("=");
/* 399 */         sameFields.put(tuple[0], tuple[1]); b++; }
/*     */     
/*     */     } 
/* 402 */     return sameFields;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, String> fromJson(String json, Type type, boolean modifyFields) {
/* 407 */     GsonBuilder gsonBuilder = new GsonBuilder();
/* 408 */     if (modifyFields) {
/* 409 */       gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy()
/*     */           {
/*     */ 
/*     */             
/*     */             public String translateName(Field f)
/*     */             {
/* 415 */               int indexOf = f.getName().indexOf("_");
/* 416 */               if (indexOf > 0)
/* 417 */                 return f.getName().substring(indexOf + 1); 
/* 418 */               return f.getName();
/*     */             }
/*     */           });
/*     */     }
/* 422 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/* 423 */     Gson gson = gsonBuilder.create();
/* 424 */     Map<String, String> obj = (Map<String, String>)gson.fromJson(json, type);
/* 425 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object fromJson(JsonElement jsonValue, Class<?> s, boolean modifyFields) {
/* 430 */     GsonBuilder gsonBuilder = new GsonBuilder();
/* 431 */     if (modifyFields) {
/* 432 */       gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy()
/*     */           {
/*     */ 
/*     */             
/*     */             public String translateName(Field f)
/*     */             {
/* 438 */               int indexOf = f.getName().indexOf("_");
/* 439 */               if (indexOf > 0)
/* 440 */                 return f.getName().substring(indexOf + 1); 
/* 441 */               return f.getName();
/*     */             }
/*     */           });
/*     */     }
/* 445 */     gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss:z");
/* 446 */     Gson gson = gsonBuilder.create();
/* 447 */     Object obj = gson.fromJson(jsonValue, s);
/* 448 */     return obj;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\json\LbsJsonUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */