/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import java.beans.FeatureDescriptor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapELResolver
/*     */   extends ELResolver
/*     */ {
/*     */   private final boolean readOnly;
/*     */   
/*     */   public MapELResolver() {
/*  41 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapELResolver(boolean readOnly) {
/*  49 */     this.readOnly = readOnly;
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
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/*  66 */     return isResolvable(base) ? 
/*  67 */       Object.class : 
/*  68 */       null;
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
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/* 103 */     if (isResolvable(base)) {
/*     */       
/* 105 */       Map<?, ?> map = (Map<?, ?>)base;
/* 106 */       final Iterator<?> keys = map.keySet().iterator();
/* 107 */       return new Iterator<FeatureDescriptor>()
/*     */         {
/*     */           public boolean hasNext()
/*     */           {
/* 111 */             return keys.hasNext();
/*     */           }
/*     */ 
/*     */           
/*     */           public FeatureDescriptor next() {
/* 116 */             Object key = keys.next();
/* 117 */             FeatureDescriptor feature = new FeatureDescriptor();
/* 118 */             feature.setDisplayName((key == null) ? 
/* 119 */                 "null" : 
/* 120 */                 key.toString());
/* 121 */             feature.setName(feature.getDisplayName());
/* 122 */             feature.setShortDescription("");
/* 123 */             feature.setExpert(true);
/* 124 */             feature.setHidden(false);
/* 125 */             feature.setPreferred(true);
/*     */ 
/*     */             
/* 128 */             if (key != null)
/* 129 */               feature.setValue("type", key.getClass()); 
/* 130 */             feature.setValue("resolvableAtDesignTime", Boolean.valueOf(true));
/* 131 */             return feature;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void remove() {
/* 137 */             throw new UnsupportedOperationException("cannot remove");
/*     */           }
/*     */         };
/*     */     } 
/* 141 */     return null;
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
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 170 */     if (context == null)
/*     */     {
/* 172 */       throw new NullPointerException("context is null");
/*     */     }
/* 174 */     Class<?> result = null;
/* 175 */     if (isResolvable(base)) {
/*     */       
/* 177 */       result = Object.class;
/* 178 */       context.setPropertyResolved(true);
/*     */     } 
/* 180 */     return result;
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
/*     */   public Object getValue(ELContext context, Object base, Object property) {
/* 214 */     if (context == null)
/*     */     {
/* 216 */       throw new NullPointerException("context is null");
/*     */     }
/* 218 */     Object result = null;
/* 219 */     if (isResolvable(base)) {
/*     */       
/* 221 */       result = ((Map)base).get(property);
/* 222 */       context.setPropertyResolved(true);
/*     */     } 
/* 224 */     return result;
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
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 257 */     if (context == null)
/*     */     {
/* 259 */       throw new NullPointerException("context is null");
/*     */     }
/* 261 */     if (isResolvable(base))
/*     */     {
/* 263 */       context.setPropertyResolved(true);
/*     */     }
/* 265 */     return this.readOnly;
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
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 308 */     if (context == null)
/*     */     {
/* 310 */       throw new NullPointerException("context is null");
/*     */     }
/* 312 */     if (isResolvable(base)) {
/*     */       
/* 314 */       if (this.readOnly)
/*     */       {
/* 316 */         throw new PropertyNotWritableException("resolver is read-only");
/*     */       }
/* 318 */       ((Map<Object, Object>)base).put(property, value);
/* 319 */       context.setPropertyResolved(true);
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
/* 334 */     return base instanceof Map;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\MapELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */