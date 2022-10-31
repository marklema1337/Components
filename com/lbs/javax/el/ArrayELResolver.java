/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import java.beans.FeatureDescriptor;
/*     */ import java.lang.reflect.Array;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayELResolver
/*     */   extends ELResolver
/*     */ {
/*     */   private final boolean readOnly;
/*     */   
/*     */   public ArrayELResolver() {
/*  41 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayELResolver(boolean readOnly) {
/*  52 */     this.readOnly = readOnly;
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
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/*  71 */     return isResolvable(base) ? 
/*  72 */       Integer.class : 
/*  73 */       null;
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
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/*  91 */     return null;
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
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 124 */     if (context == null)
/*     */     {
/* 126 */       throw new NullPointerException("context is null");
/*     */     }
/* 128 */     Class<?> result = null;
/* 129 */     if (isResolvable(base)) {
/*     */       
/* 131 */       toIndex(base, property);
/* 132 */       result = base.getClass().getComponentType();
/* 133 */       context.setPropertyResolved(true);
/*     */     } 
/* 135 */     return result;
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
/* 168 */     if (context == null)
/*     */     {
/* 170 */       throw new NullPointerException("context is null");
/*     */     }
/* 172 */     Object result = null;
/* 173 */     if (isResolvable(base)) {
/*     */       
/* 175 */       int index = toIndex(null, property);
/* 176 */       result = (index < 0 || index >= Array.getLength(base)) ? 
/* 177 */         null : 
/* 178 */         Array.get(base, index);
/* 179 */       context.setPropertyResolved(true);
/*     */     } 
/* 181 */     return result;
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
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 215 */     if (context == null)
/*     */     {
/* 217 */       throw new NullPointerException("context is null");
/*     */     }
/* 219 */     if (isResolvable(base)) {
/*     */       
/* 221 */       toIndex(base, property);
/* 222 */       context.setPropertyResolved(true);
/*     */     } 
/* 224 */     return this.readOnly;
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
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 266 */     if (context == null)
/*     */     {
/* 268 */       throw new NullPointerException("context is null");
/*     */     }
/* 270 */     if (isResolvable(base)) {
/*     */       
/* 272 */       if (this.readOnly)
/*     */       {
/* 274 */         throw new PropertyNotWritableException("resolver is read-only");
/*     */       }
/* 276 */       Array.set(base, toIndex(base, property), value);
/* 277 */       context.setPropertyResolved(true);
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
/* 292 */     return (base != null && base.getClass().isArray());
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
/*     */   private final int toIndex(Object base, Object property) {
/* 310 */     int index = 0;
/* 311 */     if (property instanceof Number) {
/*     */       
/* 313 */       index = ((Number)property).intValue();
/*     */     }
/* 315 */     else if (property instanceof String) {
/*     */       
/*     */       try
/*     */       {
/* 319 */         index = Integer.valueOf((String)property).intValue();
/*     */       }
/* 321 */       catch (NumberFormatException e)
/*     */       {
/* 323 */         throw new IllegalArgumentException("Cannot parse array index: " + property);
/*     */       }
/*     */     
/* 326 */     } else if (property instanceof Character) {
/*     */       
/* 328 */       index = ((Character)property).charValue();
/*     */     }
/* 330 */     else if (property instanceof Boolean) {
/*     */       
/* 332 */       index = ((Boolean)property).booleanValue() ? 
/* 333 */         1 : 
/* 334 */         0;
/*     */     }
/*     */     else {
/*     */       
/* 338 */       throw new IllegalArgumentException("Cannot coerce property to array index: " + property);
/*     */     } 
/* 340 */     if (base != null && (index < 0 || index >= Array.getLength(base)))
/*     */     {
/* 342 */       throw new PropertyNotFoundException("Array index out of bounds: " + index);
/*     */     }
/* 344 */     return index;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\ArrayELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */