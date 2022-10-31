/*     */ package com.lbs.juel.util;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELResolver;
/*     */ import com.lbs.javax.el.PropertyNotFoundException;
/*     */ import com.lbs.javax.el.PropertyNotWritableException;
/*     */ import java.beans.FeatureDescriptor;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public class RootPropertyResolver
/*     */   extends ELResolver
/*     */ {
/*  40 */   private final Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
/*     */ 
/*     */   
/*     */   private final boolean readOnly;
/*     */ 
/*     */ 
/*     */   
/*     */   public RootPropertyResolver() {
/*  48 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RootPropertyResolver(boolean readOnly) {
/*  58 */     this.readOnly = readOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isResolvable(Object base) {
/*  63 */     return (base == null);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean resolve(ELContext context, Object base, Object property) {
/*  68 */     context.setPropertyResolved((isResolvable(base) && property instanceof String));
/*  69 */     return context.isPropertyResolved();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/*  75 */     return isResolvable(context) ? 
/*  76 */       String.class : 
/*  77 */       null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/*  89 */     return resolve(context, base, property) ? 
/*  90 */       Object.class : 
/*  91 */       null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(ELContext context, Object base, Object property) {
/*  97 */     if (resolve(context, base, property)) {
/*     */       
/*  99 */       if (!isProperty((String)property))
/*     */       {
/* 101 */         throw new PropertyNotFoundException("Cannot find property " + property);
/*     */       }
/* 103 */       return getProperty((String)property);
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 111 */     return resolve(context, base, property) ? 
/* 112 */       this.readOnly : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) throws PropertyNotWritableException {
/* 119 */     if (resolve(context, base, property)) {
/*     */       
/* 121 */       if (this.readOnly)
/*     */       {
/* 123 */         throw new PropertyNotWritableException("Resolver is read only!");
/*     */       }
/* 125 */       setProperty((String)property, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invoke(ELContext context, Object base, Object method, Class[] paramTypes, Object[] params) {
/* 132 */     if (resolve(context, base, method))
/*     */     {
/* 134 */       throw new NullPointerException("Cannot invoke method " + method + " on null");
/*     */     }
/* 136 */     return null;
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
/*     */   public Object getProperty(String property) {
/* 148 */     return this.map.get(property);
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
/*     */   public void setProperty(String property, Object value) {
/* 161 */     this.map.put(property, value);
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
/*     */   public boolean isProperty(String property) {
/* 173 */     return this.map.containsKey(property);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<String> properties() {
/* 183 */     return this.map.keySet();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\jue\\util\RootPropertyResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */