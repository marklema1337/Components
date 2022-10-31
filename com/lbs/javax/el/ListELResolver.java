/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import java.beans.FeatureDescriptor;
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
/*     */ public class ListELResolver
/*     */   extends ELResolver
/*     */ {
/*     */   private final boolean readOnly;
/*     */   
/*     */   public ListELResolver() {
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
/*     */   public ListELResolver(boolean readOnly) {
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
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/*  69 */     return isResolvable(base) ? 
/*  70 */       Integer.class : 
/*  71 */       null;
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
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/*  86 */     return null;
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
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 120 */     if (context == null)
/*     */     {
/* 122 */       throw new NullPointerException("context is null");
/*     */     }
/* 124 */     Class<?> result = null;
/* 125 */     if (isResolvable(base)) {
/*     */       
/* 127 */       toIndex((List)base, property);
/* 128 */       result = Object.class;
/* 129 */       context.setPropertyResolved(true);
/*     */     } 
/* 131 */     return result;
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
/* 165 */     if (context == null)
/*     */     {
/* 167 */       throw new NullPointerException("context is null");
/*     */     }
/* 169 */     Object result = null;
/* 170 */     if (isResolvable(base)) {
/*     */       
/* 172 */       int index = toIndex(null, property);
/* 173 */       List<?> list = (List)base;
/* 174 */       result = (index < 0 || index >= list.size()) ? 
/* 175 */         null : 
/* 176 */         list.get(index);
/* 177 */       context.setPropertyResolved(true);
/*     */     } 
/* 179 */     return result;
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
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 217 */     if (context == null)
/*     */     {
/* 219 */       throw new NullPointerException("context is null");
/*     */     }
/* 221 */     if (isResolvable(base)) {
/*     */       
/* 223 */       toIndex((List)base, property);
/* 224 */       context.setPropertyResolved(true);
/*     */     } 
/* 226 */     return this.readOnly;
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
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 272 */     if (context == null)
/*     */     {
/* 274 */       throw new NullPointerException("context is null");
/*     */     }
/* 276 */     if (isResolvable(base)) {
/*     */       
/* 278 */       if (this.readOnly)
/*     */       {
/* 280 */         throw new PropertyNotWritableException("resolver is read-only");
/*     */       }
/* 282 */       List<?> list = (List)base;
/* 283 */       int index = toIndex(list, property);
/*     */       
/*     */       try {
/* 286 */         list.set(index, value);
/*     */       }
/* 288 */       catch (UnsupportedOperationException e) {
/*     */         
/* 290 */         throw new PropertyNotWritableException(e);
/*     */       }
/* 292 */       catch (ArrayStoreException e) {
/*     */         
/* 294 */         throw new IllegalArgumentException(e);
/*     */       } 
/* 296 */       context.setPropertyResolved(true);
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
/*     */   private static final boolean isResolvable(Object base) {
/* 311 */     return base instanceof List;
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
/*     */   private static final int toIndex(List<?> base, Object property) {
/* 329 */     int index = 0;
/* 330 */     if (property instanceof Number) {
/*     */       
/* 332 */       index = ((Number)property).intValue();
/*     */     }
/* 334 */     else if (property instanceof String) {
/*     */       
/*     */       try
/*     */       {
/* 338 */         index = Integer.valueOf((String)property).intValue();
/*     */       }
/* 340 */       catch (NumberFormatException e)
/*     */       {
/* 342 */         throw new IllegalArgumentException("Cannot parse list index: " + property);
/*     */       }
/*     */     
/* 345 */     } else if (property instanceof Character) {
/*     */       
/* 347 */       index = ((Character)property).charValue();
/*     */     }
/* 349 */     else if (property instanceof Boolean) {
/*     */       
/* 351 */       index = ((Boolean)property).booleanValue() ? 
/* 352 */         1 : 
/* 353 */         0;
/*     */     }
/*     */     else {
/*     */       
/* 357 */       throw new IllegalArgumentException("Cannot coerce property to list index: " + property);
/*     */     } 
/* 359 */     if (base != null && (index < 0 || index >= base.size()))
/*     */     {
/* 361 */       throw new PropertyNotFoundException("List index out of bounds: " + index);
/*     */     }
/* 363 */     return index;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\ListELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */