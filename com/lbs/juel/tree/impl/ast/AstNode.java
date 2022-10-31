/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.ExpressionNode;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AstNode
/*     */   implements ExpressionNode
/*     */ {
/*     */   public final Object getValue(Bindings bindings, ELContext context, Class<?> type) {
/*  31 */     Object value = eval(bindings, context);
/*  32 */     if (type != null)
/*     */     {
/*  34 */       value = bindings.convert(value, type);
/*     */     }
/*  36 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void appendStructure(StringBuilder paramStringBuilder, Bindings paramBindings);
/*     */   
/*     */   public abstract Object eval(Bindings paramBindings, ELContext paramELContext);
/*     */   
/*     */   public final String getStructuralId(Bindings bindings) {
/*  45 */     StringBuilder builder = new StringBuilder();
/*  46 */     appendStructure(builder, bindings);
/*  47 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Method findAccessibleMethod(Method method) {
/*  58 */     if (method == null || method.isAccessible())
/*     */     {
/*  60 */       return method;
/*     */     }
/*     */     
/*     */     try {
/*  64 */       method.setAccessible(true);
/*     */     }
/*  66 */     catch (SecurityException e) {
/*     */       byte b; int i; Class[] arrayOfClass;
/*  68 */       for (i = (arrayOfClass = method.getDeclaringClass().getInterfaces()).length, b = 0; b < i; ) { Class<?> clazz = arrayOfClass[b];
/*     */         
/*  70 */         Method mth = null;
/*     */         
/*     */         try {
/*  73 */           mth = clazz.getMethod(method.getName(), method.getParameterTypes());
/*  74 */           mth = findAccessibleMethod(mth);
/*  75 */           if (mth != null)
/*     */           {
/*  77 */             return mth;
/*     */           }
/*     */         }
/*  80 */         catch (NoSuchMethodException noSuchMethodException) {}
/*     */         
/*     */         b++; }
/*     */ 
/*     */       
/*  85 */       Class<?> cls = method.getDeclaringClass().getSuperclass();
/*  86 */       if (cls != null) {
/*     */         
/*  88 */         Method mth = null;
/*     */         
/*     */         try {
/*  91 */           mth = cls.getMethod(method.getName(), method.getParameterTypes());
/*  92 */           mth = findAccessibleMethod(mth);
/*  93 */           if (mth != null)
/*     */           {
/*  95 */             return mth;
/*     */           }
/*     */         }
/*  98 */         catch (NoSuchMethodException noSuchMethodException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 103 */       return null;
/*     */     } 
/* 105 */     return method;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */