/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.MethodInfo;
/*     */ import com.lbs.javax.el.MethodNotFoundException;
/*     */ import com.lbs.javax.el.PropertyNotFoundException;
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.javax.el.ValueReference;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.IdentifierNode;
/*     */ import com.lbs.juel.tree.Node;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AstIdentifier
/*     */   extends AstNode
/*     */   implements IdentifierNode
/*     */ {
/*     */   private final String name;
/*     */   private final int index;
/*     */   private final boolean ignoreReturnType;
/*     */   
/*     */   public AstIdentifier(String name, int index) {
/*  41 */     this(name, index, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public AstIdentifier(String name, int index, boolean ignoreReturnType) {
/*  46 */     this.name = name;
/*  47 */     this.index = index;
/*  48 */     this.ignoreReturnType = ignoreReturnType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getType(Bindings bindings, ELContext context) {
/*  53 */     ValueExpression expression = bindings.getVariable(this.index);
/*  54 */     if (expression != null)
/*     */     {
/*  56 */       return expression.getType(context);
/*     */     }
/*  58 */     context.setPropertyResolved(false);
/*  59 */     Class<?> result = context.getELResolver().getType(context, null, this.name);
/*  60 */     if (!context.isPropertyResolved())
/*     */     {
/*  62 */       throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", new Object[] { this.name }));
/*     */     }
/*  64 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeftValue() {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMethodInvocation() {
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueReference getValueReference(Bindings bindings, ELContext context) {
/*  84 */     ValueExpression expression = bindings.getVariable(this.index);
/*  85 */     if (expression != null)
/*     */     {
/*  87 */       return expression.getValueReference(context);
/*     */     }
/*  89 */     return new ValueReference(null, this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/*  95 */     ValueExpression expression = bindings.getVariable(this.index);
/*  96 */     if (expression != null)
/*     */     {
/*  98 */       return expression.getValue(context);
/*     */     }
/* 100 */     context.setPropertyResolved(false);
/* 101 */     Object result = context.getELResolver().getValue(context, null, this.name);
/* 102 */     if (!context.isPropertyResolved())
/*     */     {
/* 104 */       throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", new Object[] { this.name }));
/*     */     }
/* 106 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Bindings bindings, ELContext context, Object value) {
/* 111 */     ValueExpression expression = bindings.getVariable(this.index);
/* 112 */     if (expression != null) {
/*     */       
/* 114 */       expression.setValue(context, value);
/*     */       return;
/*     */     } 
/* 117 */     context.setPropertyResolved(false);
/* 118 */     context.getELResolver().setValue(context, null, this.name, value);
/* 119 */     if (!context.isPropertyResolved())
/*     */     {
/* 121 */       throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", new Object[] { this.name }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(Bindings bindings, ELContext context) {
/* 127 */     ValueExpression expression = bindings.getVariable(this.index);
/* 128 */     if (expression != null)
/*     */     {
/* 130 */       return expression.isReadOnly(context);
/*     */     }
/* 132 */     context.setPropertyResolved(false);
/* 133 */     boolean result = context.getELResolver().isReadOnly(context, null, this.name);
/* 134 */     if (!context.isPropertyResolved())
/*     */     {
/* 136 */       throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", new Object[] { this.name }));
/*     */     }
/* 138 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Method getMethod(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/* 143 */     Object value = eval(bindings, context);
/* 144 */     if (value == null)
/*     */     {
/* 146 */       throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notfound", new Object[] { this.name }));
/*     */     }
/* 148 */     if (value instanceof Method) {
/*     */       
/* 150 */       Method method = findAccessibleMethod((Method)value);
/* 151 */       if (method == null)
/*     */       {
/* 153 */         throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notfound", new Object[] { this.name }));
/*     */       }
/* 155 */       if (!this.ignoreReturnType && returnType != null && !returnType.isAssignableFrom(method.getReturnType()))
/*     */       {
/* 157 */         throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.returntype", new Object[] { method.getReturnType(), 
/* 158 */                 this.name, returnType }));
/*     */       }
/* 160 */       if (!Arrays.equals((Object[])method.getParameterTypes(), (Object[])paramTypes))
/*     */       {
/* 162 */         throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notfound", new Object[] { this.name }));
/*     */       }
/* 164 */       return method;
/*     */     } 
/* 166 */     throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notamethod", new Object[] { this.name, value.getClass() }));
/*     */   }
/*     */ 
/*     */   
/*     */   public MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/* 171 */     Method method = getMethod(bindings, context, returnType, paramTypes);
/* 172 */     return new MethodInfo(method.getName(), method.getReturnType(), paramTypes);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes, Object[] params) {
/* 177 */     Method method = getMethod(bindings, context, returnType, paramTypes);
/*     */     
/*     */     try {
/* 180 */       return method.invoke(null, params);
/*     */     }
/* 182 */     catch (IllegalAccessException e) {
/*     */       
/* 184 */       throw new ELException(LocalMessages.get("error.identifier.method.access", new Object[] { this.name }));
/*     */     }
/* 186 */     catch (IllegalArgumentException e) {
/*     */       
/* 188 */       throw new ELException(LocalMessages.get("error.identifier.method.invocation", new Object[] { this.name, e }));
/*     */     }
/* 190 */     catch (InvocationTargetException e) {
/*     */       
/* 192 */       throw new ELException(LocalMessages.get("error.identifier.method.invocation", new Object[] { this.name, e.getCause() }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 199 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 205 */     b.append((bindings != null && bindings.isVariableBound(this.index)) ? 
/* 206 */         "<var>" : 
/* 207 */         this.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 212 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 217 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 222 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 227 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */