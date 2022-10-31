/*     */ package com.lbs.juel.util;
/*     */ 
/*     */ import com.lbs.javax.el.ArrayELResolver;
/*     */ import com.lbs.javax.el.BeanELResolver;
/*     */ import com.lbs.javax.el.CompositeELResolver;
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELResolver;
/*     */ import com.lbs.javax.el.ListELResolver;
/*     */ import com.lbs.javax.el.MapELResolver;
/*     */ import com.lbs.javax.el.ResourceBundleELResolver;
/*     */ import java.beans.FeatureDescriptor;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleResolver
/*     */   extends ELResolver
/*     */ {
/*  39 */   private static final ELResolver DEFAULT_RESOLVER_READ_ONLY = (ELResolver)new CompositeELResolver()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static final ELResolver DEFAULT_RESOLVER_READ_WRITE = (ELResolver)new CompositeELResolver()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final RootPropertyResolver root;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final CompositeELResolver delegate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleResolver(ELResolver resolver, boolean readOnly) {
/*  69 */     this.delegate = new CompositeELResolver();
/*  70 */     this.delegate.add(this.root = new RootPropertyResolver(readOnly));
/*  71 */     this.delegate.add(resolver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleResolver(ELResolver resolver) {
/*  80 */     this(resolver, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleResolver(boolean readOnly) {
/*  91 */     this(readOnly ? DEFAULT_RESOLVER_READ_ONLY : DEFAULT_RESOLVER_READ_WRITE, readOnly);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleResolver() {
/* 100 */     this(DEFAULT_RESOLVER_READ_WRITE, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RootPropertyResolver getRootPropertyResolver() {
/* 110 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/* 116 */     return this.delegate.getCommonPropertyType(context, base);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/* 122 */     return this.delegate.getFeatureDescriptors(context, base);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 128 */     return this.delegate.getType(context, base, property);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(ELContext context, Object base, Object property) {
/* 134 */     return this.delegate.getValue(context, base, property);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 140 */     return this.delegate.isReadOnly(context, base, property);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 146 */     this.delegate.setValue(context, base, property, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invoke(ELContext context, Object base, Object method, Class[] paramTypes, Object[] params) {
/* 152 */     return this.delegate.invoke(context, base, method, paramTypes, params);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\jue\\util\SimpleResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */