/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import java.beans.FeatureDescriptor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeELResolver
/*     */   extends ELResolver
/*     */ {
/*     */   private final List<ELResolver> resolvers;
/*     */   
/*     */   public CompositeELResolver() {
/*  45 */     this.resolvers = new ArrayList<>();
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
/*     */   public void add(ELResolver elResolver) {
/*  58 */     if (elResolver == null)
/*     */     {
/*  60 */       throw new NullPointerException("resolver must not be null");
/*     */     }
/*  62 */     this.resolvers.add(elResolver);
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
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/*  84 */     Class<?> result = null;
/*  85 */     for (int i = 0, l = this.resolvers.size(); i < l; i++) {
/*     */       
/*  87 */       Class<?> type = ((ELResolver)this.resolvers.get(i)).getCommonPropertyType(context, base);
/*  88 */       if (type != null)
/*     */       {
/*  90 */         if (result == null || type.isAssignableFrom(result)) {
/*     */           
/*  92 */           result = type;
/*     */         }
/*  94 */         else if (!result.isAssignableFrom(type)) {
/*     */           
/*  96 */           result = Object.class;
/*     */         } 
/*     */       }
/*     */     } 
/* 100 */     return result;
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
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(final ELContext context, final Object base) {
/* 124 */     return new Iterator<FeatureDescriptor>()
/*     */       {
/* 126 */         Iterator<FeatureDescriptor> empty = Collections.<FeatureDescriptor>emptyList().iterator();
/* 127 */         Iterator<ELResolver> resolvers = CompositeELResolver.this.resolvers.iterator();
/* 128 */         Iterator<FeatureDescriptor> features = this.empty;
/*     */ 
/*     */         
/*     */         Iterator<FeatureDescriptor> features() {
/* 132 */           while (!this.features.hasNext() && this.resolvers.hasNext()) {
/*     */             
/* 134 */             this.features = ((ELResolver)this.resolvers.next()).getFeatureDescriptors(context, base);
/* 135 */             if (this.features == null)
/*     */             {
/* 137 */               this.features = this.empty;
/*     */             }
/*     */           } 
/* 140 */           return this.features;
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean hasNext() {
/* 145 */           return features().hasNext();
/*     */         }
/*     */ 
/*     */         
/*     */         public FeatureDescriptor next() {
/* 150 */           return features().next();
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 155 */           features().remove();
/*     */         }
/*     */       };
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
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 200 */     context.setPropertyResolved(false);
/* 201 */     for (int i = 0, l = this.resolvers.size(); i < l; i++) {
/*     */       
/* 203 */       Class<?> type = ((ELResolver)this.resolvers.get(i)).getType(context, base, property);
/* 204 */       if (context.isPropertyResolved())
/*     */       {
/* 206 */         return type;
/*     */       }
/*     */     } 
/* 209 */     return null;
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
/*     */   public Object getValue(ELContext context, Object base, Object property) {
/* 250 */     context.setPropertyResolved(false);
/* 251 */     for (int i = 0, l = this.resolvers.size(); i < l; i++) {
/*     */       
/* 253 */       Object value = ((ELResolver)this.resolvers.get(i)).getValue(context, base, property);
/* 254 */       if (context.isPropertyResolved())
/*     */       {
/* 256 */         return value;
/*     */       }
/*     */     } 
/* 259 */     return null;
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
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 301 */     context.setPropertyResolved(false);
/* 302 */     for (int i = 0, l = this.resolvers.size(); i < l; i++) {
/*     */       
/* 304 */       boolean readOnly = ((ELResolver)this.resolvers.get(i)).isReadOnly(context, base, property);
/* 305 */       if (context.isPropertyResolved())
/*     */       {
/* 307 */         return readOnly;
/*     */       }
/*     */     } 
/* 310 */     return false;
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
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 354 */     context.setPropertyResolved(false);
/* 355 */     for (int i = 0, l = this.resolvers.size(); i < l; i++) {
/*     */       
/* 357 */       ((ELResolver)this.resolvers.get(i)).setValue(context, base, property, value);
/* 358 */       if (context.isPropertyResolved()) {
/*     */         return;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 425 */     context.setPropertyResolved(false);
/* 426 */     for (int i = 0, l = this.resolvers.size(); i < l; i++) {
/*     */       
/* 428 */       Object result = ((ELResolver)this.resolvers.get(i)).invoke(context, base, method, paramTypes, params);
/* 429 */       if (context.isPropertyResolved())
/*     */       {
/* 431 */         return result;
/*     */       }
/*     */     } 
/* 434 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\CompositeELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */