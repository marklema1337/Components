/*      */ package com.lbs.data.objects;
/*      */ 
/*      */ import com.ibm.icu.text.DecimalFormatSymbols;
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
/*      */ import com.lbs.data.export.JExportMapItem;
/*      */ import com.lbs.data.query.QueryBusinessObject;
/*      */ import com.lbs.globalization.JLbsDefaultCultureInfo;
/*      */ import com.lbs.invoke.RttiUtil;
/*      */ import com.lbs.localization.IPropertyProvider;
/*      */ import com.lbs.localization.LbsMessage;
/*      */ import com.lbs.platform.interfaces.IDataProvider;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import com.lbs.util.ObjectUtil;
/*      */ import com.lbs.util.PropertyValue;
/*      */ import com.lbs.util.StringUtil;
/*      */ import com.lbs.util.ValueConverter;
/*      */ import java.io.InvalidObjectException;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.math.BigDecimal;
/*      */ import java.security.InvalidParameterException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ObjectValueManager
/*      */   implements IPropertyProvider
/*      */ {
/*      */   static {
/*   50 */     LbsMessage.setPropertyProvider(new ObjectValueManager());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void touch() {}
/*      */ 
/*      */   
/*      */   public static boolean hasMember(Object obj, String memberName) {
/*   59 */     Class<?> objClass = obj.getClass();
/*      */     
/*   61 */     Field field = null;
/*      */     
/*      */     try {
/*   64 */       field = objClass.getDeclaredField(memberName);
/*      */     }
/*   66 */     catch (NoSuchFieldException noSuchFieldException) {}
/*      */ 
/*      */     
/*   69 */     return (field != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void internalSetMemberValue(Object obj, String memberName, Object memberValue, boolean convertValue) throws Exception {
/*   76 */     Object value = null;
/*      */     
/*   78 */     int index = -1;
/*   79 */     int idx = memberName.indexOf('[');
/*   80 */     if (idx != -1) {
/*      */       
/*   82 */       String propIdxStr = memberName.substring(idx + 1, memberName.length() - 1);
/*   83 */       index = Integer.valueOf(propIdxStr).intValue();
/*      */       
/*   85 */       memberName = memberName.substring(0, idx);
/*      */     } 
/*      */     
/*   88 */     if (memberName.startsWith("Extensions") && obj instanceof IDataProvider) {
/*   89 */       value = ((IDataProvider)obj).getBO();
/*      */     } else {
/*   91 */       value = obj;
/*      */     } 
/*      */     
/*      */     try {
/*   95 */       if (value instanceof Map) {
/*      */         
/*   97 */         ((Map<String, Object>)value).put(memberName, memberValue);
/*      */         return;
/*      */       } 
/*  100 */       if (ObjectUtil.hasProperty(value.getClass(), memberName, index)) {
/*      */         
/*  102 */         if (convertValue) {
/*      */           
/*  104 */           Class propClass = getObjectMemberClass(value, memberName);
/*  105 */           if (memberValue != null) {
/*  106 */             memberValue = ValueConverter.convert(propClass, memberValue);
/*      */           } else {
/*  108 */             memberValue = ValueConverter.convertNull(propClass, null);
/*      */           } 
/*      */         } 
/*  111 */         ObjectUtil.setProperty(value, memberName, memberValue, index);
/*      */       }
/*  113 */       else if (value instanceof BasicBusinessObject) {
/*      */         
/*  115 */         BasicBusinessObject basicObj = (BasicBusinessObject)value;
/*  116 */         basicObj.getProperties().setValue(memberName, memberValue, false);
/*      */       }
/*  118 */       else if (value instanceof ObjectPropertyList) {
/*      */         
/*  120 */         ObjectPropertyList propList = (ObjectPropertyList)value;
/*  121 */         propList.set(memberName, memberValue);
/*      */       }
/*  123 */       else if (value instanceof ObjectExtensions) {
/*      */         
/*  125 */         if (memberValue instanceof CustomBusinessObject)
/*      */         {
/*  127 */           ObjectExtensions extList = (ObjectExtensions)value;
/*  128 */           extList.add((CustomBusinessObject)memberValue);
/*      */         }
/*      */       
/*      */       } 
/*  132 */     } catch (Exception e) {
/*      */       
/*  134 */       LbsConsole.getLogger("Data.Client.ObjectValueManager").debug("ObjectValueManager.setMemberValue()", e);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  142 */       throw e;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setMemberValue(Object obj, String memberName, Object memberValue) throws Exception {
/*  148 */     if (obj instanceof QueryBusinessObject) {
/*      */       
/*  150 */       internalSetMemberValue(obj, memberName, memberValue, false);
/*      */       
/*      */       return;
/*      */     } 
/*  154 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  156 */     if (parts == null) {
/*  157 */       throw new NoSuchFieldException("Property name is null or invalid!");
/*      */     }
/*  159 */     if (parts.length == 1) {
/*  160 */       internalSetMemberValue(obj, memberName, memberValue, false);
/*      */     } else {
/*      */       
/*  163 */       for (int i = 0; i < parts.length - 1;) {
/*      */         
/*  165 */         if (obj != null) {
/*  166 */           obj = internalGetMemberValue(obj, parts[i], false, false);
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*  171 */       if (obj == null) {
/*  172 */         throw new Exception("Can not set a member value of a 'null' object!");
/*      */       }
/*  174 */       internalSetMemberValue(obj, parts[parts.length - 1], memberValue, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setGridMemberValue(Object obj, String memberName, Object memberValue, boolean convert) throws Exception {
/*  180 */     setMemberValue(obj, memberName, memberValue, convert);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setMemberValue(Object<Object, Object> obj, String memberName, Object memberValue, boolean convert) throws Exception {
/*  185 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  187 */     Object<Object, Object> propObj = null;
/*      */     
/*  189 */     if (parts.length == 1) {
/*  190 */       internalSetMemberValue(obj, memberName, memberValue, convert);
/*      */     } else {
/*      */       
/*  193 */       for (int i = 0; i < parts.length - 1; i++) {
/*      */         
/*  195 */         if (obj != null) {
/*  196 */           propObj = (Object<Object, Object>)internalGetMemberValue(obj, parts[i], convert, false);
/*      */         }
/*  198 */         if (propObj == null)
/*      */         {
/*  200 */           if (obj instanceof CustomBusinessObject) {
/*      */             
/*  202 */             CustomBusinessObject cObj = (CustomBusinessObject)obj;
/*  203 */             propObj = (Object<Object, Object>)new CustomBusinessObject(null);
/*      */             
/*  205 */             cObj.set(parts[i], propObj, false);
/*      */           }
/*  207 */           else if (obj instanceof com.lbs.interfaces.IParameter) {
/*      */             
/*  209 */             Class<? extends Object> c = (Class)obj.getClass();
/*      */             
/*      */             try {
/*  212 */               Method getter = c.getMethod("get" + parts[i], new Class[0]);
/*  213 */               Class<?> innerObjectType = getter.getReturnType();
/*  214 */               if (innerObjectType.equals(List.class)) {
/*      */                 
/*  216 */                 propObj = (Object<Object, Object>)new ArrayList();
/*      */               }
/*  218 */               else if (innerObjectType.equals(Map.class)) {
/*      */                 
/*  220 */                 propObj = (Object<Object, Object>)new HashMap<>();
/*      */               }
/*      */               else {
/*      */                 
/*  224 */                 propObj = (Object<Object, Object>)innerObjectType.newInstance();
/*      */               } 
/*  226 */               if (propObj != null) {
/*  227 */                 setMemberValue(obj, parts[i], propObj);
/*      */               }
/*  229 */             } catch (Exception e) {
/*      */               
/*  231 */               obj = null;
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } else {
/*  237 */             obj = null;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  249 */         obj = propObj;
/*      */       } 
/*      */       
/*  252 */       if (obj == null) {
/*  253 */         throw new Exception("Can not set a member value of a 'null' object!");
/*      */       }
/*  255 */       internalSetMemberValue(obj, parts[parts.length - 1], memberValue, convert);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setMemberValueWithLines(Object<Object, Object> obj, String memberName, Object memberValue, boolean convert) throws Exception {
/*  261 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  263 */     Object<Object, Object> propObj = null;
/*      */     
/*  265 */     if (parts.length == 1) {
/*  266 */       internalSetMemberValue(obj, memberName, memberValue, convert);
/*      */     } else {
/*      */       
/*  269 */       for (int i = 0; i < parts.length - 1; i++) {
/*      */         
/*  271 */         if (obj != null) {
/*  272 */           propObj = (Object<Object, Object>)internalGetMemberValueWithLines(obj, parts[i], convert, false, false);
/*      */         }
/*  274 */         if (propObj == null)
/*      */         {
/*  276 */           if (obj instanceof CustomBusinessObject) {
/*      */             
/*  278 */             CustomBusinessObject cObj = (CustomBusinessObject)obj;
/*  279 */             propObj = (Object<Object, Object>)new CustomBusinessObject(null);
/*      */             
/*  281 */             cObj.set(parts[i], propObj, false);
/*      */           }
/*  283 */           else if (obj instanceof com.lbs.interfaces.IParameter) {
/*      */             
/*  285 */             Class<? extends Object> c = (Class)obj.getClass();
/*      */             
/*      */             try {
/*  288 */               Method getter = c.getMethod("get" + parts[i], new Class[0]);
/*  289 */               Class<?> innerObjectType = getter.getReturnType();
/*  290 */               if (innerObjectType.equals(List.class)) {
/*      */                 
/*  292 */                 propObj = (Object<Object, Object>)new ArrayList();
/*      */               }
/*  294 */               else if (innerObjectType.equals(Map.class)) {
/*      */                 
/*  296 */                 propObj = (Object<Object, Object>)new HashMap<>();
/*      */               }
/*      */               else {
/*      */                 
/*  300 */                 propObj = (Object<Object, Object>)innerObjectType.newInstance();
/*      */               } 
/*  302 */               if (propObj != null) {
/*  303 */                 setMemberValue(obj, parts[i], propObj);
/*      */               }
/*  305 */             } catch (Exception e) {
/*      */               
/*  307 */               obj = null;
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } else {
/*  313 */             obj = null;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  325 */         obj = propObj;
/*      */       } 
/*      */       
/*  328 */       if (obj == null) {
/*  329 */         throw new Exception("Can not set a member value of a 'null' object!");
/*      */       }
/*  331 */       internalSetMemberValue(obj, parts[parts.length - 1], memberValue, convert);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Class getMemberClass(Class objClass, String memberName) throws NoSuchFieldException {
/*  356 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  358 */     if (parts == null) {
/*  359 */       throw new NoSuchFieldException("Property name is null or invalid!");
/*      */     }
/*  361 */     if (parts.length == 1) {
/*  362 */       return ObjectUtil.getPropertyClass(objClass, memberName, -1);
/*      */     }
/*  364 */     for (int i = 0; i < parts.length - 1;) {
/*      */       
/*  366 */       if (objClass != null) {
/*  367 */         objClass = internalGetMemberClass(objClass, parts[i]);
/*      */         
/*      */         i++;
/*      */       } 
/*      */     } 
/*  372 */     if (objClass != null) {
/*  373 */       return ObjectUtil.getPropertyClass(objClass, parts[parts.length - 1], -1);
/*      */     }
/*  375 */     throw new NoSuchFieldException("Specified property is not accessible or can not be found!");
/*      */   }
/*      */ 
/*      */   
/*      */   public static Class getMemberClassWithList(Class<BusinessObjects> objClass, String memberName, Object object) throws InvalidObjectException, InvalidParameterException, NoSuchMethodException, Exception {
/*  380 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  382 */     if (parts == null) {
/*  383 */       throw new NoSuchFieldException("Property name is null or invalid!");
/*      */     }
/*  385 */     if (parts.length == 1) {
/*  386 */       return ObjectUtil.getPropertyClass(objClass, memberName, -1);
/*      */     }
/*  388 */     String lastPart = "";
/*  389 */     Class<BusinessObjects> prevObjClass = null;
/*  390 */     for (int i = 0; i < parts.length - 1;) {
/*      */       
/*  392 */       if (objClass != null) {
/*      */ 
/*      */         
/*  395 */         if (objClass == BusinessObjects.class && lastPart != null && lastPart.length() > 0) {
/*      */           
/*  397 */           BusinessObjects temp = (BusinessObjects)RttiUtil.getProperty(object, lastPart);
/*  398 */           if (!temp.isEmpty()) {
/*  399 */             objClass = (Class)temp.get(0).getClass();
/*      */           } else {
/*      */             
/*  402 */             Object o = getListItem(prevObjClass, lastPart);
/*  403 */             if (o != null)
/*  404 */               objClass = (Class)o.getClass(); 
/*      */           } 
/*      */         } 
/*  407 */         prevObjClass = objClass;
/*  408 */         objClass = internalGetMemberClass(objClass, parts[i]);
/*  409 */         lastPart = parts[i];
/*      */         
/*      */         i++;
/*      */       } 
/*      */     } 
/*      */     
/*  415 */     if (objClass == BusinessObjects.class && lastPart != null && lastPart.length() > 0) {
/*      */       
/*  417 */       BusinessObjects temp = (BusinessObjects)RttiUtil.getProperty(object, lastPart);
/*  418 */       if (!temp.isEmpty()) {
/*  419 */         objClass = (Class)temp.get(0).getClass();
/*      */       } else {
/*      */         
/*  422 */         Object o = getListItem(prevObjClass, lastPart);
/*  423 */         if (o != null) {
/*  424 */           objClass = (Class)o.getClass();
/*      */         }
/*      */       } 
/*      */     } 
/*  428 */     if (objClass != null) {
/*  429 */       return ObjectUtil.getPropertyClass(objClass, parts[parts.length - 1], -1);
/*      */     }
/*  431 */     throw new NoSuchFieldException("Specified property is not accessible or can not be found!");
/*      */   }
/*      */ 
/*      */   
/*      */   public static Class getObjectMemberClass(Object obj, String memberName) throws Exception {
/*  436 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  438 */     if (parts == null) {
/*  439 */       throw new NoSuchFieldException("Property name is null or invalid!");
/*      */     }
/*  441 */     Class<?> objClass = obj.getClass();
/*  442 */     if (parts.length == 1) {
/*  443 */       return ObjectUtil.getPropertyClass(objClass, memberName, -1);
/*      */     }
/*  445 */     for (int i = 0; i < parts.length - 1; i++) {
/*      */       
/*  447 */       if (obj != null) {
/*      */         
/*  449 */         obj = internalGetMemberValue(obj, parts[i], false, false);
/*      */         
/*  451 */         if (obj != null) {
/*  452 */           objClass = obj.getClass();
/*      */         } else {
/*  454 */           objClass = internalGetMemberClass(objClass, parts[i]);
/*      */         }
/*      */       
/*      */       }
/*  458 */       else if (objClass != null) {
/*  459 */         objClass = internalGetMemberClass(objClass, parts[i]);
/*      */       } 
/*      */     } 
/*      */     
/*  463 */     if (objClass != null) {
/*  464 */       return ObjectUtil.getPropertyClass(objClass, parts[parts.length - 1], -1);
/*      */     }
/*  466 */     throw new NoSuchFieldException("Specified property is not accessible or can not be found!");
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object convertMemberValue(Object value, Class srcType, Class dstType) {
/*  471 */     return convertMemberValue(value, srcType, dstType, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object convertMemberValueForTest(Object value, Class srcType, Class dstType) {
/*  476 */     Object resValue = value;
/*      */     
/*      */     try {
/*  479 */       JLbsDefaultCultureInfo culture = new JLbsDefaultCultureInfo();
/*  480 */       JLbsRealNumberFormatter numberFormatter = new JLbsRealNumberFormatter(culture.getDefaultNumberFormatIndex());
/*  481 */       numberFormatter.getFormat().setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
/*  482 */       numberFormatter.getFormat().setGroupingUsed(false);
/*      */       
/*  484 */       resValue = ValueConverter.convert(srcType, dstType, value, null, numberFormatter);
/*      */     }
/*  486 */     catch (Exception e) {
/*      */       
/*  488 */       LbsConsole.getLogger("Data.Client.ObjectValueManager").error("ObjectValueManager.convertMemberValueForTest", e);
/*      */     } 
/*      */     
/*  491 */     return resValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> T convertValue(Object value, Class<T> dstType, boolean isSilent) {
/*      */     try {
/*  499 */       if (value == null && dstType.isPrimitive())
/*  500 */         dstType = RttiUtil.valueClassToWrapper(dstType); 
/*  501 */       return (T)ValueConverter.convert(null, dstType, value);
/*      */     }
/*  503 */     catch (Exception e) {
/*      */       
/*  505 */       LbsConsole.getLogger("Data.Client.ObjectValueManager").error("ObjectValueManager.convertValue", e);
/*      */       
/*  507 */       if (!isSilent)
/*      */       {
/*  509 */         throw new RuntimeException(e);
/*      */       }
/*      */       
/*  512 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Object convertMemberValue(Object value, Class srcType, Class dstType, boolean isSilent) {
/*  517 */     Object resValue = value;
/*      */ 
/*      */     
/*      */     try {
/*  521 */       resValue = ValueConverter.convert(srcType, dstType, value);
/*      */     }
/*  523 */     catch (Exception e) {
/*      */       
/*  525 */       LbsConsole.getLogger("Data.Client.ObjectValueManager").error("ObjectValueManager.convertMemberValue", e);
/*      */       
/*  527 */       if (!isSilent)
/*      */       {
/*  529 */         throw new RuntimeException();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  539 */     return resValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean compareMemberValues(Object value1, Object value2) {
/*  544 */     if (value1 != null) {
/*      */       
/*  546 */       if (value2 == null) {
/*  547 */         return false;
/*      */       }
/*  549 */       if (value1.getClass().equals(BigDecimal.class) && value2.getClass().equals(BigDecimal.class)) {
/*  550 */         return (((BigDecimal)value1).compareTo((BigDecimal)value2) == 0);
/*      */       }
/*  552 */       return value1.equals(value2);
/*      */     } 
/*      */     
/*  555 */     return (value2 == null);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getIntValue(Object obj, String memberName) {
/*  560 */     return ((Integer)getMemberValue(obj, memberName, (Class)int.class)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getStringValue(Object obj, String memberName) {
/*  565 */     return getMemberValue(obj, memberName, String.class);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean getBooleanValue(Object obj, String memberName) {
/*  570 */     return ((Boolean)getMemberValue(obj, memberName, (Class)boolean.class)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public static BigDecimal getBigDecimalValue(Object obj, String memberName) {
/*  575 */     return getMemberValue(obj, memberName, BigDecimal.class);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> T getMemberValue(Object obj, String memberName, Class<T> expectedType) {
/*      */     try {
/*  582 */       return convertValue(getMemberValue(obj, memberName, true), expectedType, true);
/*      */     }
/*  584 */     catch (Exception e) {
/*      */       
/*  586 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getMemberValue(Object obj, String memberName) throws Exception {
/*  592 */     return getMemberValue(obj, (Class)null, memberName, (Class)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getMemberValue(Object obj, String memberName, boolean silent) throws Exception {
/*  597 */     return getMemberValueEx(obj, null, memberName, null, false, silent);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getMemberValue(Object obj, String memberName, boolean silent, boolean hasFirstLevelBO) throws Exception {
/*  602 */     return getMemberValueEx(obj, null, memberName, null, false, silent, hasFirstLevelBO);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getMemberValue(Object obj, Class objType, String memberName, Class memberType) throws Exception {
/*  607 */     return getMemberValueEx(obj, objType, memberName, memberType, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getGridMemberValue(Object obj, Class objType, String memberName, Class memberType) throws Exception {
/*  612 */     return getMemberValueEx(obj, objType, memberName, memberType, true, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getMemberValue(Object obj, Class objType, String memberName, Class memberType, boolean create) throws Exception {
/*  618 */     return getMemberValueEx(obj, objType, memberName, memberType, create, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getMemberValueEx(Object obj, Class objType, String memberName, Class memberType, boolean create, boolean silent) throws Exception {
/*  624 */     return getMemberValueEx(obj, objType, memberName, memberType, create, silent, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getMemberValueEx(Object obj, Class<?> objType, String memberName, Class memberType, boolean create, boolean silent, boolean firstLevelBO) throws Exception {
/*  630 */     if (obj != null && objType == null) {
/*  631 */       objType = obj.getClass();
/*      */     }
/*  633 */     if (obj == null && objType != null) {
/*      */       
/*      */       try {
/*      */         
/*  637 */         obj = objType.newInstance();
/*      */       }
/*  639 */       catch (Exception e) {
/*      */         
/*  641 */         obj = null;
/*      */       } 
/*      */     }
/*      */     
/*  645 */     if (obj == null && objType == null) {
/*  646 */       return null;
/*      */     }
/*  648 */     String[] parts = StringUtil.split(memberName, '.');
/*      */     
/*  650 */     Object value = obj;
/*  651 */     for (int i = 0; i < parts.length; i++) {
/*      */       
/*  653 */       if (value != null) {
/*  654 */         value = internalGetMemberValue(value, parts[i], create, silent, firstLevelBO);
/*      */       }
/*      */     } 
/*  657 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Object internalGetMemberValue(Object obj, String memberName, boolean create, boolean silent) throws Exception {
/*  663 */     return internalGetMemberValue(obj, memberName, create, silent, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Object internalGetMemberValue(Object obj, String memberName, boolean create, boolean silent, boolean firstLevelBO) throws Exception {
/*  668 */     Object value = null;
/*      */     
/*  670 */     int index = -1;
/*  671 */     int idx = memberName.indexOf('[');
/*  672 */     if (idx != -1) {
/*      */       
/*  674 */       String propIdxStr = memberName.substring(idx + 1, memberName.length() - 1);
/*  675 */       index = Integer.valueOf(propIdxStr).intValue();
/*      */       
/*  677 */       memberName = memberName.substring(0, idx);
/*      */     } 
/*      */     
/*  680 */     if (memberName.startsWith("Extensions") && obj instanceof IDataProvider) {
/*  681 */       value = ((IDataProvider)obj).getBO();
/*      */     } else {
/*  683 */       value = obj;
/*      */     } 
/*      */     
/*      */     try {
/*  687 */       if (value instanceof BasicBusinessObject) {
/*      */         
/*  689 */         BasicBusinessObject basicObj = (BasicBusinessObject)value;
/*      */         
/*  691 */         if (memberName.endsWith("_SELECTEDLANG")) {
/*      */           
/*  693 */           if (basicObj.getProperties().containsProperty(memberName)) {
/*  694 */             return basicObj.getProperties().get(memberName);
/*      */           }
/*  696 */           return null;
/*      */         } 
/*      */         
/*  699 */         if (JLbsConstants.XUI_MULTILANG) {
/*      */           
/*  701 */           if (basicObj.getProperties().containsProperty(memberName + "_SELECTEDLANG")) {
/*      */             
/*  703 */             Object multiVal = basicObj.getProperties().get(memberName + "_SELECTEDLANG");
/*  704 */             if (multiVal instanceof String && !JLbsStringUtil.isEmpty((String)multiVal)) {
/*  705 */               return multiVal;
/*      */             }
/*      */           } 
/*  708 */           if (obj instanceof QueryBusinessObject) {
/*      */             
/*  710 */             String lang = (String)((BasicBusinessObject)obj).getProperties().get("!_éLOGITEM_BO_LANGUAGEé_!");
/*  711 */             if (basicObj.getProperties().containsProperty(memberName.toUpperCase(Locale.ENGLISH) + "_" + lang)) {
/*      */               
/*  713 */               Object multiVal = basicObj.getProperties().get(memberName.toUpperCase(Locale.ENGLISH) + "_" + lang);
/*  714 */               if (multiVal instanceof String && !JLbsStringUtil.isEmpty((String)multiVal))
/*      */               {
/*  716 */                 return multiVal;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  723 */         if (basicObj.getProperties().containsProperty(memberName))
/*      */         {
/*  725 */           return basicObj.getProperties().get(memberName);
/*      */         }
/*  727 */         if (basicObj instanceof CustomBusinessObject && 
/*  728 */           JLbsStringUtil.equals(memberName, "PARENTREF") && basicObj
/*  729 */           .getProperties().containsProperty("ParentReference"))
/*      */         {
/*  731 */           return basicObj.getProperties().get("ParentReference");
/*      */         }
/*  733 */         if (firstLevelBO)
/*      */         {
/*  735 */           return null;
/*      */         }
/*  737 */         if (basicObj instanceof CustomBusinessObject)
/*  738 */           return null; 
/*      */       } 
/*  740 */       if (value instanceof ObjectPropertyList) {
/*      */         
/*  742 */         ObjectPropertyList propList = (ObjectPropertyList)value;
/*      */         
/*  744 */         if (propList.containsProperty(memberName))
/*  745 */           return propList.get(memberName); 
/*      */       } 
/*  747 */       if (value instanceof IObjectPropertyGetter)
/*      */       {
/*  749 */         return ((IObjectPropertyGetter)value).getProperty(memberName);
/*      */       }
/*      */       
/*  752 */       if (value instanceof BasicBusinessObjects)
/*      */       {
/*  754 */         if (StringUtil.equals(memberName, "item") && index != -1) {
/*  755 */           return ((BasicBusinessObjects)value).itemAt(index);
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*  760 */       if (value instanceof ObjectExtensions) {
/*      */         
/*  762 */         ObjectExtensions extList = (ObjectExtensions)value;
/*      */         
/*  764 */         if (extList.contains(memberName)) {
/*  765 */           return extList.get(memberName);
/*      */         }
/*  767 */         if (create) {
/*      */           
/*  769 */           CustomBusinessObject cObj = new CustomBusinessObject(memberName);
/*  770 */           extList.add(cObj);
/*  771 */           return cObj;
/*      */         } 
/*      */       } 
/*  774 */       PropertyValue property = ObjectUtil.getPropertyValue(value, memberName, index);
/*  775 */       if (property.isHasProperty())
/*  776 */         return property.getPropertyValue(); 
/*  777 */       throw new NoSuchFieldException("Property not found: " + memberName);
/*      */     
/*      */     }
/*  780 */     catch (Exception e) {
/*      */       
/*  782 */       if (!silent) {
/*      */         
/*  784 */         if (JLbsConstants.checkAppCloud() && !JLbsConstants.checkSAAS()) {
/*  785 */           LbsConsole.getLogger("Data.Client.ObjectValueManager").debug("ObjectValueManager.getMemberValue()", e);
/*      */         } else {
/*  787 */           LbsConsole.getLogger("Data.Client.ObjectValueManager").error("ObjectValueManager.getMemberValue()", e);
/*  788 */         }  throw e;
/*      */       } 
/*      */       
/*  791 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Object internalGetMemberValueWithLines(Object obj, String memberName, boolean create, boolean silent, boolean firstLevelBO) throws Exception {
/*  800 */     Object value = null;
/*      */     
/*  802 */     int index = -1;
/*  803 */     int idx = memberName.indexOf('[');
/*  804 */     if (idx != -1) {
/*      */       
/*  806 */       String propIdxStr = memberName.substring(idx + 1, memberName.length() - 1);
/*  807 */       index = Integer.valueOf(propIdxStr).intValue();
/*      */       
/*  809 */       memberName = memberName.substring(0, idx);
/*      */     } 
/*      */     
/*  812 */     if (memberName.startsWith("Extensions") && obj instanceof IDataProvider) {
/*  813 */       value = ((IDataProvider)obj).getBO();
/*      */     } else {
/*  815 */       value = obj;
/*      */     } 
/*      */     
/*      */     try {
/*  819 */       if (value instanceof BasicBusinessObject) {
/*      */         
/*  821 */         BasicBusinessObject basicObj = (BasicBusinessObject)value;
/*      */         
/*  823 */         if (memberName.endsWith("_SELECTEDLANG")) {
/*      */           
/*  825 */           if (basicObj.getProperties().containsProperty(memberName)) {
/*  826 */             return basicObj.getProperties().get(memberName);
/*      */           }
/*  828 */           return null;
/*      */         } 
/*      */         
/*  831 */         if (basicObj.getProperties().containsProperty(memberName))
/*      */         {
/*  833 */           return basicObj.getProperties().get(memberName);
/*      */         }
/*  835 */         if (basicObj instanceof CustomBusinessObject && 
/*  836 */           JLbsStringUtil.equals(memberName, "PARENTREF") && basicObj
/*  837 */           .getProperties().containsProperty("ParentReference"))
/*      */         {
/*  839 */           return basicObj.getProperties().get("ParentReference");
/*      */         }
/*  841 */         if (firstLevelBO)
/*      */         {
/*  843 */           return null;
/*      */         }
/*  845 */         if (basicObj instanceof CustomBusinessObject)
/*  846 */           return null; 
/*      */       } 
/*  848 */       if (value instanceof ObjectPropertyList) {
/*      */         
/*  850 */         ObjectPropertyList propList = (ObjectPropertyList)value;
/*      */         
/*  852 */         if (propList.containsProperty(memberName))
/*  853 */           return propList.get(memberName); 
/*      */       } 
/*  855 */       if (value instanceof IObjectPropertyGetter)
/*      */       {
/*  857 */         return ((IObjectPropertyGetter)value).getProperty(memberName);
/*      */       }
/*      */       
/*  860 */       if (value instanceof BasicBusinessObjects) {
/*      */         
/*  862 */         BasicBusinessObjects b = (BasicBusinessObjects)value;
/*  863 */         if (!b.isEmpty()) {
/*      */           
/*  865 */           BasicBusinessObject itemAt = ((BasicBusinessObjects<BasicBusinessObject>)value).itemAt(0);
/*  866 */           return internalGetMemberValue(itemAt, memberName, true, false);
/*      */         } 
/*      */ 
/*      */         
/*  870 */         Object newInstance = getListItem(obj.getClass(), memberName);
/*  871 */         ((BasicBusinessObjects<Object>)value).add(newInstance);
/*  872 */         return newInstance;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  878 */       if (value instanceof ObjectExtensions) {
/*      */         
/*  880 */         ObjectExtensions extList = (ObjectExtensions)value;
/*      */         
/*  882 */         if (extList.contains(memberName)) {
/*  883 */           return extList.get(memberName);
/*      */         }
/*  885 */         if (create) {
/*      */           
/*  887 */           CustomBusinessObject cObj = new CustomBusinessObject(memberName);
/*  888 */           extList.add(cObj);
/*  889 */           return cObj;
/*      */         } 
/*      */       } 
/*  892 */       PropertyValue property = ObjectUtil.getPropertyValue(value, memberName, index);
/*  893 */       if (property.isHasProperty()) {
/*      */         
/*  895 */         Object res = property.getPropertyValue();
/*  896 */         if (res instanceof BasicBusinessObjects) {
/*      */           
/*  898 */           BasicBusinessObjects<Object> temp = (BasicBusinessObjects)res;
/*  899 */           if (!temp.isEmpty()) {
/*      */             
/*  901 */             res = temp.get(0);
/*  902 */             if (res instanceof BusinessObject) {
/*  903 */               ((BusinessObject)res).createLinkedObjects(2);
/*      */             }
/*      */           } else {
/*      */             
/*  907 */             res = getListItem(value.getClass(), memberName);
/*  908 */             if (res != null) {
/*  909 */               temp.add(res);
/*      */             }
/*      */           } 
/*      */         } 
/*  913 */         return res;
/*      */       } 
/*  915 */       throw new NoSuchFieldException("Property not found: " + memberName);
/*      */     
/*      */     }
/*  918 */     catch (Exception e) {
/*      */       
/*  920 */       if (!silent) {
/*      */         
/*  922 */         if (JLbsConstants.checkAppCloud() && !JLbsConstants.checkSAAS()) {
/*  923 */           LbsConsole.getLogger("Data.Client.ObjectValueManager").debug("ObjectValueManager.getMemberValue()", e);
/*      */         } else {
/*  925 */           LbsConsole.getLogger("Data.Client.ObjectValueManager").error("ObjectValueManager.getMemberValue()", e);
/*  926 */         }  throw e;
/*      */       } 
/*      */       
/*  929 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object getListItem(Class value, String memberName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
/*  939 */     Object result = null;
/*  940 */     if (memberName == null || memberName.length() == 0)
/*  941 */       return result; 
/*  942 */     Object newInstance = value.newInstance();
/*  943 */     List proList = ((SimpleBusinessObject)newInstance).getSerializer().getProperties("");
/*      */ 
/*      */     
/*  946 */     Optional<JExportMapItem> findFirst = proList.stream().filter(prop -> memberName.equals(((JExportMapItem)prop).getPropertyName())).findFirst();
/*  947 */     if (findFirst.isPresent() && findFirst.get() != null) {
/*      */       
/*  949 */       String objectName = ((JExportMapItem)findFirst.get()).getObjectName();
/*  950 */       Object bo = Class.forName(objectName).newInstance();
/*  951 */       if (bo instanceof SimpleBusinessObject) {
/*      */         
/*  953 */         SimpleBusinessObject exchangeObject = ((SimpleBusinessObject)bo).getExchangeObject();
/*  954 */         if (exchangeObject instanceof BusinessObject)
/*  955 */           ((BusinessObject)exchangeObject).createLinkedObjects(2); 
/*  956 */         return exchangeObject;
/*      */       } 
/*      */     } 
/*      */     
/*  960 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Class internalGetMemberClass(Class objClass, String memberName) throws NoSuchFieldException {
/*  965 */     int index = -1;
/*  966 */     int idx = memberName.indexOf('[');
/*  967 */     if (idx != -1) {
/*      */       
/*  969 */       String propIdxStr = memberName.substring(idx + 1, memberName.length() - 1);
/*  970 */       index = Integer.valueOf(propIdxStr).intValue();
/*      */       
/*  972 */       memberName = memberName.substring(0, idx);
/*      */     } 
/*      */     
/*  975 */     return ObjectUtil.getPropertyClass(objClass, memberName, index);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getClassString(Class c) {
/*  980 */     if (c == null) {
/*  981 */       return "Object";
/*      */     }
/*  983 */     if (c.isArray()) {
/*  984 */       return c.getComponentType().getName() + "[]";
/*      */     }
/*  986 */     String name = c.getName();
/*  987 */     if (name.startsWith("java.lang")) {
/*  988 */       name = name.substring(10);
/*      */     }
/*  990 */     return name;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getDefautClassValue(Class dstClass) {
/*  995 */     if (dstClass == null) {
/*  996 */       return null;
/*      */     }
/*  998 */     if (dstClass.isPrimitive()) {
/*  999 */       dstClass = RttiUtil.valueClassToWrapper(dstClass);
/*      */     }
/* 1001 */     if (dstClass.equals(String.class)) {
/* 1002 */       return "";
/*      */     }
/* 1004 */     if (dstClass.equals(Boolean.class)) {
/* 1005 */       return new Boolean(false);
/*      */     }
/* 1007 */     if (RttiUtil.isSubClassOf(dstClass, Number.class)) {
/*      */       
/*      */       try {
/*      */         
/* 1011 */         return ValueConverter.convert(dstClass, Integer.valueOf(0));
/*      */       }
/* 1013 */       catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1018 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void fill(BusinessObject dstObject, QueryBusinessObject qryObject) {
/* 1023 */     ObjectPropertyList qryProps = qryObject.m_Properties;
/*      */ 
/*      */ 
/*      */     
/* 1027 */     Enumeration<String> propEnum = qryProps.properties();
/*      */     
/* 1029 */     while (propEnum.hasMoreElements()) {
/*      */       
/* 1031 */       String propName = propEnum.nextElement();
/* 1032 */       Object propValue = qryProps.getValue(propName);
/*      */ 
/*      */       
/*      */       try {
/* 1036 */         Class propClass = getObjectMemberClass(dstObject, propName);
/*      */         
/* 1038 */         if (propValue != null) {
/* 1039 */           propValue = ValueConverter.convert(propClass, propValue);
/*      */         }
/* 1041 */         setMemberValue(dstObject, propName, propValue);
/*      */       }
/* 1043 */       catch (NoSuchFieldException noSuchFieldException) {
/*      */ 
/*      */       
/* 1046 */       } catch (Exception e) {
/*      */         
/* 1048 */         LbsConsole.getLogger("Data.Client.ObjectValueManager").error("ObjectValueManager.convertMemberValue", e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void truncateDateTime(Calendar c, int subType) {
/* 1057 */     switch (subType) {
/*      */       
/*      */       case 8:
/* 1060 */         c.set(11, 0);
/* 1061 */         c.set(12, 0);
/* 1062 */         c.set(13, 0);
/* 1063 */         c.set(14, 0);
/*      */         break;
/*      */       
/*      */       case 9:
/* 1067 */         c.set(1, BasicBusinessObject.MIN_DATE.get(1));
/* 1068 */         c.set(2, BasicBusinessObject.MIN_DATE.get(2));
/* 1069 */         c.set(5, BasicBusinessObject.MIN_DATE.get(5));
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getValue(Object source, String propertyName) {
/*      */     try {
/* 1078 */       Object value = getMemberValue(source, propertyName);
/* 1079 */       return String.valueOf(value);
/*      */     }
/* 1081 */     catch (Exception e) {
/*      */       
/* 1083 */       return "Unknown";
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectValueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */