/*     */ package com.lbs.javax.el;
/*     */ 
/*     */ import java.beans.FeatureDescriptor;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceBundleELResolver
/*     */   extends ELResolver
/*     */ {
/*     */   public Class<?> getCommonPropertyType(ELContext context, Object base) {
/*  49 */     return isResolvable(base) ? 
/*  50 */       String.class : 
/*  51 */       null;
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
/*     */   public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
/*  85 */     if (isResolvable(base)) {
/*     */       
/*  87 */       final Enumeration<String> keys = ((ResourceBundle)base).getKeys();
/*  88 */       return new Iterator<FeatureDescriptor>()
/*     */         {
/*     */           public boolean hasNext()
/*     */           {
/*  92 */             return keys.hasMoreElements();
/*     */           }
/*     */ 
/*     */           
/*     */           public FeatureDescriptor next() {
/*  97 */             FeatureDescriptor feature = new FeatureDescriptor();
/*  98 */             feature.setDisplayName(keys.nextElement());
/*  99 */             feature.setName(feature.getDisplayName());
/* 100 */             feature.setShortDescription("");
/* 101 */             feature.setExpert(true);
/* 102 */             feature.setHidden(false);
/* 103 */             feature.setPreferred(true);
/* 104 */             feature.setValue("type", String.class);
/* 105 */             feature.setValue("resolvableAtDesignTime", Boolean.valueOf(true));
/* 106 */             return feature;
/*     */           }
/*     */ 
/*     */           
/*     */           public void remove() {
/* 111 */             throw new UnsupportedOperationException("Cannot remove");
/*     */           }
/*     */         };
/*     */     } 
/*     */     
/* 116 */     return null;
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
/*     */   public Class<?> getType(ELContext context, Object base, Object property) {
/* 140 */     if (context == null)
/*     */     {
/* 142 */       throw new NullPointerException("context is null");
/*     */     }
/* 144 */     if (isResolvable(base))
/*     */     {
/* 146 */       context.setPropertyResolved(true);
/*     */     }
/* 148 */     return null;
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
/*     */   public Object getValue(ELContext context, Object base, Object property) {
/* 179 */     if (context == null)
/*     */     {
/* 181 */       throw new NullPointerException("context is null");
/*     */     }
/* 183 */     Object result = null;
/* 184 */     if (isResolvable(base)) {
/*     */       
/* 186 */       if (property != null) {
/*     */         
/*     */         try {
/*     */           
/* 190 */           result = ((ResourceBundle)base).getObject(property.toString());
/*     */         }
/* 192 */         catch (MissingResourceException e) {
/*     */           
/* 194 */           result = "???" + property + "???";
/*     */         } 
/*     */       }
/* 197 */       context.setPropertyResolved(true);
/*     */     } 
/* 199 */     return result;
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
/*     */   public boolean isReadOnly(ELContext context, Object base, Object property) {
/* 213 */     if (context == null)
/*     */     {
/* 215 */       throw new NullPointerException("context is null");
/*     */     }
/* 217 */     if (isResolvable(base))
/*     */     {
/* 219 */       context.setPropertyResolved(true);
/*     */     }
/* 221 */     return true;
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
/*     */   public void setValue(ELContext context, Object base, Object property, Object value) {
/* 244 */     if (context == null)
/*     */     {
/* 246 */       throw new NullPointerException("context is null");
/*     */     }
/* 248 */     if (isResolvable(base))
/*     */     {
/* 250 */       throw new PropertyNotWritableException("resolver is read-only");
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
/* 265 */     return base instanceof ResourceBundle;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\ResourceBundleELResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */