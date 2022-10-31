/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.MethodInfo;
/*     */ import com.lbs.javax.el.MethodNotFoundException;
/*     */ import com.lbs.javax.el.PropertyNotFoundException;
/*     */ import com.lbs.javax.el.ValueReference;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.Node;
/*     */ import java.lang.reflect.InvocationTargetException;
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
/*     */ public abstract class AstProperty
/*     */   extends AstNode
/*     */ {
/*     */   protected final AstNode prefix;
/*     */   protected final boolean lvalue;
/*     */   protected final boolean strict;
/*     */   protected final boolean ignoreReturnType;
/*     */   
/*     */   public AstProperty(AstNode prefix, boolean lvalue, boolean strict) {
/*  39 */     this(prefix, lvalue, strict, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public AstProperty(AstNode prefix, boolean lvalue, boolean strict, boolean ignoreReturnType) {
/*  44 */     this.prefix = prefix;
/*  45 */     this.lvalue = lvalue;
/*  46 */     this.strict = strict;
/*  47 */     this.ignoreReturnType = ignoreReturnType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode getPrefix() {
/*  54 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueReference getValueReference(Bindings bindings, ELContext context) {
/*  59 */     Object base = this.prefix.eval(bindings, context);
/*  60 */     if (base == null)
/*     */     {
/*  62 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.prefix }));
/*     */     }
/*  64 */     Object property = getProperty(bindings, context);
/*  65 */     if (property == null && this.strict)
/*     */     {
/*  67 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { "null", base }));
/*     */     }
/*  69 */     return new ValueReference(base, property);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/*  75 */     Object base = this.prefix.eval(bindings, context);
/*  76 */     if (base == null)
/*     */     {
/*  78 */       return null;
/*     */     }
/*  80 */     Object property = getProperty(bindings, context);
/*  81 */     if (property == null && this.strict)
/*     */     {
/*  83 */       return null;
/*     */     }
/*  85 */     context.setPropertyResolved(false);
/*  86 */     Object result = context.getELResolver().getValue(context, base, property);
/*  87 */     if (!context.isPropertyResolved())
/*     */     {
/*  89 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { property, base }));
/*     */     }
/*  91 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isLiteralText() {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isLeftValue() {
/* 101 */     return this.lvalue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMethodInvocation() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getType(Bindings bindings, ELContext context) {
/* 111 */     if (!this.lvalue)
/*     */     {
/* 113 */       return null;
/*     */     }
/* 115 */     Object base = this.prefix.eval(bindings, context);
/* 116 */     if (base == null)
/*     */     {
/* 118 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.prefix }));
/*     */     }
/* 120 */     Object property = getProperty(bindings, context);
/* 121 */     if (property == null && this.strict)
/*     */     {
/* 123 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { "null", base }));
/*     */     }
/* 125 */     context.setPropertyResolved(false);
/* 126 */     Class<?> result = context.getELResolver().getType(context, base, property);
/* 127 */     if (!context.isPropertyResolved())
/*     */     {
/* 129 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { property, base }));
/*     */     }
/* 131 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(Bindings bindings, ELContext context) throws ELException {
/* 136 */     if (!this.lvalue)
/*     */     {
/* 138 */       return true;
/*     */     }
/* 140 */     Object base = this.prefix.eval(bindings, context);
/* 141 */     if (base == null)
/*     */     {
/* 143 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.prefix }));
/*     */     }
/* 145 */     Object property = getProperty(bindings, context);
/* 146 */     if (property == null && this.strict)
/*     */     {
/* 148 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { "null", base }));
/*     */     }
/* 150 */     context.setPropertyResolved(false);
/* 151 */     boolean result = context.getELResolver().isReadOnly(context, base, property);
/* 152 */     if (!context.isPropertyResolved())
/*     */     {
/* 154 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { property, base }));
/*     */     }
/* 156 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Bindings bindings, ELContext context, Object value) throws ELException {
/* 161 */     if (!this.lvalue)
/*     */     {
/* 163 */       throw new ELException(LocalMessages.get("error.value.set.rvalue", new Object[] { getStructuralId(bindings) }));
/*     */     }
/* 165 */     Object base = this.prefix.eval(bindings, context);
/* 166 */     if (base == null)
/*     */     {
/* 168 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.prefix }));
/*     */     }
/* 170 */     Object property = getProperty(bindings, context);
/* 171 */     if (property == null && this.strict)
/*     */     {
/* 173 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { "null", base }));
/*     */     }
/* 175 */     context.setPropertyResolved(false);
/* 176 */     context.getELResolver().setValue(context, base, property, value);
/* 177 */     if (!context.isPropertyResolved())
/*     */     {
/* 179 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", new Object[] { property, base }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Method findMethod(String name, Class<?> clazz, Class<?> returnType, Class[] paramTypes) {
/* 185 */     Method method = null;
/*     */     
/*     */     try {
/* 188 */       method = clazz.getMethod(name, paramTypes);
/*     */     }
/* 190 */     catch (NoSuchMethodException e) {
/*     */       
/* 192 */       throw new MethodNotFoundException(LocalMessages.get("error.property.method.notfound", new Object[] { name, clazz }));
/*     */     } 
/* 194 */     method = findAccessibleMethod(method);
/* 195 */     if (method == null)
/*     */     {
/* 197 */       throw new MethodNotFoundException(LocalMessages.get("error.property.method.notfound", new Object[] { name, clazz }));
/*     */     }
/* 199 */     if (!this.ignoreReturnType && returnType != null && !returnType.isAssignableFrom(method.getReturnType()))
/*     */     {
/* 201 */       throw new MethodNotFoundException(LocalMessages.get("error.property.method.returntype", new Object[] { method.getReturnType(), name, 
/* 202 */               clazz, returnType }));
/*     */     }
/* 204 */     return method;
/*     */   }
/*     */ 
/*     */   
/*     */   public MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/* 209 */     Object base = this.prefix.eval(bindings, context);
/* 210 */     if (base == null)
/*     */     {
/* 212 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.prefix }));
/*     */     }
/* 214 */     Object property = getProperty(bindings, context);
/* 215 */     if (property == null && this.strict)
/*     */     {
/* 217 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.method.notfound", new Object[] { "null", base }));
/*     */     }
/* 219 */     String name = (String)bindings.convert(property, String.class);
/* 220 */     Method method = findMethod(name, base.getClass(), returnType, paramTypes);
/* 221 */     return new MethodInfo(method.getName(), method.getReturnType(), paramTypes);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes, Object[] paramValues) {
/* 226 */     Object base = this.prefix.eval(bindings, context);
/* 227 */     if (base == null)
/*     */     {
/* 229 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.prefix }));
/*     */     }
/* 231 */     Object property = getProperty(bindings, context);
/* 232 */     if (property == null && this.strict)
/*     */     {
/* 234 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.method.notfound", new Object[] { "null", base }));
/*     */     }
/* 236 */     String name = (String)bindings.convert(property, String.class);
/* 237 */     Method method = findMethod(name, base.getClass(), returnType, paramTypes);
/*     */     
/*     */     try {
/* 240 */       return method.invoke(base, paramValues);
/*     */     }
/* 242 */     catch (IllegalAccessException e) {
/*     */       
/* 244 */       throw new ELException(LocalMessages.get("error.property.method.access", new Object[] { name, base.getClass() }));
/*     */     }
/* 246 */     catch (IllegalArgumentException e) {
/*     */       
/* 248 */       throw new ELException(LocalMessages.get("error.property.method.invocation", new Object[] { name, base.getClass() }), e);
/*     */     }
/* 250 */     catch (InvocationTargetException e) {
/*     */       
/* 252 */       throw new ELException(LocalMessages.get("error.property.method.invocation", new Object[] { name, base.getClass() }), e.getCause());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 258 */     return (i == 0) ? 
/* 259 */       this.prefix : 
/* 260 */       null;
/*     */   }
/*     */   
/*     */   protected abstract Object getProperty(Bindings paramBindings, ELContext paramELContext) throws ELException;
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */