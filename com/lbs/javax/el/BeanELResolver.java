/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import com.lbs.util.ConversionDataLoss;
/*     */ import com.lbs.util.ConversionNotSupportedException;
/*     */ import com.lbs.util.ValueConverter;
/*     */ import java.beans.FeatureDescriptor;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BeanELResolver
/*     */   extends ELResolver
/*     */ {
/*     */   private final boolean readOnly;
/*     */   private final ConcurrentHashMap<Class<?>, BeanProperties> cache;
/*     */   private ExpressionFactory defaultFactory;
/*     */   
/*     */   protected static final class BeanProperties
/*     */   {
/*     */     private final Map<String, BeanELResolver.BeanProperty> map;
/*     */     
/*     */     public BeanProperties(Class<?> baseClass) {
/*     */       PropertyDescriptor[] descriptors;
/*  56 */       this.map = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  63 */         descriptors = Introspector.getBeanInfo(baseClass).getPropertyDescriptors();
/*     */       }
/*  65 */       catch (IntrospectionException e) {
/*     */         
/*  67 */         throw new ELException(e);
/*     */       }  byte b; int i; PropertyDescriptor[] arrayOfPropertyDescriptor1;
/*  69 */       for (i = (arrayOfPropertyDescriptor1 = descriptors).length, b = 0; b < i; ) { PropertyDescriptor descriptor = arrayOfPropertyDescriptor1[b];
/*     */         
/*  71 */         this.map.put(descriptor.getName(), new BeanELResolver.BeanProperty(descriptor));
/*  72 */         if (!Character.isUpperCase(descriptor.getName().charAt(0)))
/*  73 */           this.map.put(String.valueOf(descriptor.getName().substring(0, 1).toUpperCase(Locale.UK)) + descriptor.getName().substring(1), 
/*  74 */               new BeanELResolver.BeanProperty(descriptor)); 
/*     */         b++; }
/*     */     
/*     */     }
/*     */     
/*     */     public BeanELResolver.BeanProperty getBeanProperty(String property) {
/*  80 */       return this.map.get(property);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static final class BeanProperty
/*     */   {
/*     */     private final PropertyDescriptor descriptor;
/*     */     
/*     */     public BeanProperty(PropertyDescriptor descriptor) {
/*  90 */       this.descriptor = descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public Class<?> getPropertyType() {
/*  95 */       return this.descriptor.getPropertyType();
/*     */     }
/*     */ 
/*     */     
/*     */     public Method getReadMethod() {
/* 100 */       return BeanELResolver.findAccessibleMethod(this.descriptor.getReadMethod());
/*     */     }
/*     */ 
/*     */     
/*     */     public Method getWriteMethod() {
/* 105 */       return BeanELResolver.findAccessibleMethod(this.descriptor.getWriteMethod());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isReadOnly() {
/* 110 */       return (BeanELResolver.findAccessibleMethod(this.descriptor.getWriteMethod()) == null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static Method findAccessibleMethod(Method method) {
/* 116 */     if (method == null || method.isAccessible())
/*     */     {
/* 118 */       return method;
/*     */     }
/*     */     
/*     */     try {
/* 122 */       method.setAccessible(true);
/*     */     }
/* 124 */     catch (SecurityException e) {
/*     */       byte b; int i; Class[] arrayOfClass;
/* 126 */       for (i = (arrayOfClass = method.getDeclaringClass().getInterfaces()).length, b = 0; b < i; ) { Class<?> clazz = arrayOfClass[b];
/*     */         
/* 128 */         Method mth = null;
/*     */         
/*     */         try {
/* 131 */           mth = clazz.getMethod(method.getName(), method.getParameterTypes());
/* 132 */           mth = findAccessibleMethod(mth);
/* 133 */           if (mth != null)
/*     */           {
/* 135 */             return mth;
/*     */           }
/*     */         }
/* 138 */         catch (NoSuchMethodException noSuchMethodException) {}
/*     */         
/*     */         b++; }
/*     */ 
/*     */       
/* 143 */       Class<?> cls = method.getDeclaringClass().getSuperclass();
/* 144 */       if (cls != null) {
/*     */         
/* 146 */         Method mth = null;
/*     */         
/*     */         try {
/* 149 */           mth = cls.getMethod(method.getName(), method.getParameterTypes());
/* 150 */           mth = findAccessibleMethod(mth);
/* 151 */           if (mth != null)
/*     */           {
/* 153 */             return mth;
/*     */           }
/*     */         }
/* 156 */         catch (NoSuchMethodException noSuchMethodException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 161 */       return null;
/*     */     } 
/* 163 */     return method;
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
/*     */   public BeanELResolver() {
/* 176 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BeanELResolver(boolean readOnly) {
/* 184 */     this.readOnly = readOnly;
/* 185 */     this.cache = new ConcurrentHashMap<>();
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
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/* 203 */     return isResolvable(base) ? 
/* 204 */       Object.class : 
/* 205 */       null;
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
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/* 232 */     if (isResolvable(base)) {
/*     */       final PropertyDescriptor[] properties;
/*     */ 
/*     */       
/*     */       try {
/* 237 */         properties = Introspector.getBeanInfo(base.getClass()).getPropertyDescriptors();
/*     */       }
/* 239 */       catch (IntrospectionException e) {
/*     */         
/* 241 */         return Collections.<FeatureDescriptor>emptyList().iterator();
/*     */       } 
/* 243 */       return new Iterator<FeatureDescriptor>()
/*     */         {
/* 245 */           int next = 0;
/*     */ 
/*     */           
/*     */           public boolean hasNext() {
/* 249 */             return (properties != null && this.next < properties.length);
/*     */           }
/*     */ 
/*     */           
/*     */           public FeatureDescriptor next() {
/* 254 */             PropertyDescriptor property = properties[this.next++];
/* 255 */             FeatureDescriptor feature = new FeatureDescriptor();
/* 256 */             feature.setDisplayName(property.getDisplayName());
/* 257 */             feature.setName(property.getName());
/* 258 */             feature.setShortDescription(property.getShortDescription());
/* 259 */             feature.setExpert(property.isExpert());
/* 260 */             feature.setHidden(property.isHidden());
/* 261 */             feature.setPreferred(property.isPreferred());
/* 262 */             feature.setValue("type", property.getPropertyType());
/* 263 */             feature.setValue("resolvableAtDesignTime", Boolean.valueOf(true));
/* 264 */             return feature;
/*     */           }
/*     */ 
/*     */           
/*     */           public void remove() {
/* 269 */             throw new UnsupportedOperationException("cannot remove");
/*     */           }
/*     */         };
/*     */     } 
/* 273 */     return null;
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
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 305 */     if (context == null)
/*     */     {
/* 307 */       throw new NullPointerException();
/*     */     }
/* 309 */     Class<?> result = null;
/* 310 */     if (isResolvable(base)) {
/*     */       
/* 312 */       result = toBeanProperty(base, property).getPropertyType();
/* 313 */       context.setPropertyResolved(true);
/*     */     } 
/* 315 */     return result;
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
/*     */   public Object getValue(ELContext context, Object base, Object property) {
/* 348 */     if (context == null)
/*     */     {
/* 350 */       throw new NullPointerException();
/*     */     }
/* 352 */     Object result = null;
/* 353 */     if (isResolvable(base)) {
/*     */       
/* 355 */       Method method = toBeanProperty(base, property).getReadMethod();
/* 356 */       if (method == null)
/*     */       {
/* 358 */         throw new PropertyNotFoundException("Cannot read property " + property);
/*     */       }
/*     */       
/*     */       try {
/* 362 */         result = method.invoke(base, new Object[0]);
/*     */       }
/* 364 */       catch (InvocationTargetException e) {
/*     */         
/* 366 */         throw new ELException(e.getCause());
/*     */       }
/* 368 */       catch (Exception e) {
/*     */         
/* 370 */         throw new ELException(e);
/*     */       } 
/* 372 */       context.setPropertyResolved(true);
/*     */     } 
/* 374 */     return result;
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
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 405 */     if (context == null)
/*     */     {
/* 407 */       throw new NullPointerException();
/*     */     }
/* 409 */     boolean result = this.readOnly;
/* 410 */     if (isResolvable(base)) {
/*     */       
/* 412 */       result |= toBeanProperty(base, property).isReadOnly();
/* 413 */       context.setPropertyResolved(true);
/*     */     } 
/* 415 */     return result;
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
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 452 */     if (context == null)
/*     */     {
/* 454 */       throw new NullPointerException();
/*     */     }
/* 456 */     if (isResolvable(base)) {
/*     */       
/* 458 */       if (this.readOnly)
/*     */       {
/* 460 */         throw new PropertyNotWritableException("resolver is read-only");
/*     */       }
/* 462 */       Method method = toBeanProperty(base, property).getWriteMethod();
/* 463 */       if (method == null)
/*     */       {
/* 465 */         throw new PropertyNotWritableException("Cannot write property: " + property);
/*     */       }
/*     */       
/*     */       try {
/* 469 */         method.invoke(base, new Object[] { (value == null) ? null : ValueConverter.convert(value.getClass(), method.getParameterTypes()[0], value) });
/*     */       }
/* 471 */       catch (InvocationTargetException e) {
/*     */         
/* 473 */         throw new ELException("Cannot write property: " + property, e.getCause());
/*     */       }
/* 475 */       catch (IllegalAccessException e) {
/*     */         
/* 477 */         throw new PropertyNotWritableException("Cannot write property: " + property, e);
/*     */       }
/* 479 */       catch (ConversionNotSupportedException e) {
/*     */         
/* 481 */         throw new ELException("Cannot convert property type. Cannot write property: " + property, e.getCause());
/*     */       }
/* 483 */       catch (ConversionDataLoss e) {
/*     */         
/* 485 */         throw new ELException("Conversion data loss. Cannot write property: " + property, e.getCause());
/*     */       } 
/*     */       
/* 488 */       context.setPropertyResolved(true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invoke(ELContext context, Object base, Object method, Class[] paramTypes, Object[] params) {
/* 551 */     if (context == null)
/*     */     {
/* 553 */       throw new NullPointerException();
/*     */     }
/* 555 */     Object result = null;
/* 556 */     if (isResolvable(base)) {
/*     */       
/* 558 */       if (params == null)
/*     */       {
/* 560 */         params = new Object[0];
/*     */       }
/* 562 */       String name = method.toString();
/* 563 */       Method target = findMethod(base, name, paramTypes, params.length);
/* 564 */       if (target == null)
/*     */       {
/* 566 */         throw new MethodNotFoundException("Cannot find method " + name + " with " + params.length + " parameters in " + 
/* 567 */             base.getClass());
/*     */       }
/*     */       
/*     */       try {
/* 571 */         result = target.invoke(base, coerceParams(getExpressionFactory(context), target, params));
/*     */       }
/* 573 */       catch (InvocationTargetException e) {
/*     */         
/* 575 */         throw new ELException(e.getCause());
/*     */       }
/* 577 */       catch (IllegalAccessException e) {
/*     */         
/* 579 */         throw new ELException(e);
/*     */       } 
/* 581 */       context.setPropertyResolved(true);
/*     */     } 
/* 583 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private Method findMethod(Object base, String name, Class[] types, int paramCount) {
/* 588 */     if (types != null) {
/*     */       
/*     */       try {
/*     */         
/* 592 */         return findAccessibleMethod(base.getClass().getMethod(name, types));
/*     */       }
/* 594 */       catch (NoSuchMethodException e) {
/*     */         
/* 596 */         return null;
/*     */       } 
/*     */     }
/* 599 */     Method varArgsMethod = null; byte b; int i; Method[] arrayOfMethod;
/* 600 */     for (i = (arrayOfMethod = base.getClass().getMethods()).length, b = 0; b < i; ) { Method method = arrayOfMethod[b];
/*     */       
/* 602 */       if (method.getName().equals(name)) {
/*     */         
/* 604 */         int formalParamCount = (method.getParameterTypes()).length;
/* 605 */         if (method.isVarArgs() && paramCount >= formalParamCount - 1) {
/*     */           
/* 607 */           varArgsMethod = method;
/*     */         }
/* 609 */         else if (paramCount == formalParamCount) {
/*     */           
/* 611 */           return findAccessibleMethod(method);
/*     */         } 
/*     */       }  b++; }
/*     */     
/* 615 */     return (varArgsMethod == null) ? 
/* 616 */       null : 
/* 617 */       findAccessibleMethod(varArgsMethod);
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
/*     */   private ExpressionFactory getExpressionFactory(ELContext context) {
/* 631 */     Object obj = context.getContext(ExpressionFactory.class);
/* 632 */     if (obj instanceof ExpressionFactory)
/*     */     {
/* 634 */       return (ExpressionFactory)obj;
/*     */     }
/* 636 */     if (this.defaultFactory == null)
/*     */     {
/* 638 */       this.defaultFactory = ExpressionFactory.newInstance();
/*     */     }
/* 640 */     return this.defaultFactory;
/*     */   }
/*     */ 
/*     */   
/*     */   private Object[] coerceParams(ExpressionFactory factory, Method method, Object[] params) {
/* 645 */     Class[] types = method.getParameterTypes();
/* 646 */     Object[] args = new Object[types.length];
/* 647 */     if (method.isVarArgs()) {
/*     */       
/* 649 */       int varargIndex = types.length - 1;
/* 650 */       if (params.length < varargIndex)
/*     */       {
/* 652 */         throw new ELException("Bad argument count");
/*     */       }
/* 654 */       for (int i = 0; i < varargIndex; i++)
/*     */       {
/* 656 */         coerceValue(args, i, factory, params[i], types[i]);
/*     */       }
/* 658 */       Class<?> varargType = types[varargIndex].getComponentType();
/* 659 */       int length = params.length - varargIndex;
/* 660 */       Object array = null;
/* 661 */       if (length == 1) {
/*     */         
/* 663 */         Object source = params[varargIndex];
/* 664 */         if (source != null && source.getClass().isArray()) {
/*     */           
/* 666 */           if (types[varargIndex].isInstance(source))
/*     */           {
/* 668 */             array = source;
/*     */           }
/*     */           else
/*     */           {
/* 672 */             length = Array.getLength(source);
/* 673 */             array = Array.newInstance(varargType, length);
/* 674 */             for (int j = 0; j < length; j++)
/*     */             {
/* 676 */               coerceValue(array, j, factory, Array.get(source, j), varargType);
/*     */             }
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 682 */           array = Array.newInstance(varargType, 1);
/* 683 */           coerceValue(array, 0, factory, source, varargType);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 688 */         array = Array.newInstance(varargType, length);
/* 689 */         for (int j = 0; j < length; j++)
/*     */         {
/* 691 */           coerceValue(array, j, factory, params[varargIndex + j], varargType);
/*     */         }
/*     */       } 
/* 694 */       args[varargIndex] = array;
/*     */     }
/*     */     else {
/*     */       
/* 698 */       if (params.length != args.length)
/*     */       {
/* 700 */         throw new ELException("Bad argument count");
/*     */       }
/* 702 */       for (int i = 0; i < args.length; i++)
/*     */       {
/* 704 */         coerceValue(args, i, factory, params[i], types[i]);
/*     */       }
/*     */     } 
/* 707 */     return args;
/*     */   }
/*     */ 
/*     */   
/*     */   private void coerceValue(Object array, int index, ExpressionFactory factory, Object value, Class<?> type) {
/* 712 */     if (value != null || type.isPrimitive())
/*     */     {
/* 714 */       Array.set(array, index, factory.coerceToType(value, type));
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
/*     */   private final boolean isResolvable(Object base) {
/* 729 */     return (base != null);
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
/*     */   private final BeanProperty toBeanProperty(Object base, Object property) {
/* 745 */     BeanProperties beanProperties = this.cache.get(base.getClass());
/* 746 */     if (beanProperties == null) {
/*     */       
/* 748 */       BeanProperties newBeanProperties = new BeanProperties(base.getClass());
/* 749 */       beanProperties = this.cache.putIfAbsent(base.getClass(), newBeanProperties);
/* 750 */       if (beanProperties == null)
/*     */       {
/* 752 */         beanProperties = newBeanProperties;
/*     */       }
/*     */     } 
/* 755 */     BeanProperty beanProperty = (property == null) ? 
/* 756 */       null : 
/* 757 */       beanProperties.getBeanProperty(property.toString());
/* 758 */     if (beanProperty == null)
/*     */     {
/* 760 */       throw new PropertyNotFoundException("Could not find property " + property + " in " + base.getClass());
/*     */     }
/* 762 */     return beanProperty;
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
/*     */   private final void purgeBeanClasses(ClassLoader loader) {
/* 778 */     Iterator<Class<?>> classes = this.cache.keySet().iterator();
/* 779 */     while (classes.hasNext()) {
/*     */       
/* 781 */       if (loader == ((Class)classes.next()).getClassLoader())
/*     */       {
/* 783 */         classes.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\BeanELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */