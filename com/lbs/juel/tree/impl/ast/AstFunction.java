/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.FunctionNode;
/*     */ import com.lbs.juel.tree.Node;
/*     */ import java.lang.reflect.Array;
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
/*     */ public class AstFunction
/*     */   extends AstRightValue
/*     */   implements FunctionNode
/*     */ {
/*     */   private final int index;
/*     */   private final String name;
/*     */   private final AstParameters params;
/*     */   private final boolean varargs;
/*     */   
/*     */   public AstFunction(String name, int index, AstParameters params) {
/*  37 */     this(name, index, params, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public AstFunction(String name, int index, AstParameters params, boolean varargs) {
/*  42 */     this.name = name;
/*  43 */     this.index = index;
/*  44 */     this.params = params;
/*  45 */     this.varargs = varargs;
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
/*     */   protected Object invoke(Bindings bindings, ELContext context, Object base, Method method) throws InvocationTargetException, IllegalAccessException {
/*  61 */     Class[] types = method.getParameterTypes();
/*  62 */     Object[] params = null;
/*  63 */     if (types.length > 0) {
/*     */       
/*  65 */       params = new Object[types.length];
/*  66 */       if (this.varargs && method.isVarArgs()) {
/*     */         
/*  68 */         for (int i = 0; i < params.length - 1; i++) {
/*     */           
/*  70 */           Object param = getParam(i).eval(bindings, context);
/*  71 */           if (param != null || types[i].isPrimitive())
/*     */           {
/*  73 */             params[i] = bindings.convert(param, types[i]);
/*     */           }
/*     */         } 
/*  76 */         int varargIndex = types.length - 1;
/*  77 */         Class<?> varargType = types[varargIndex].getComponentType();
/*  78 */         int length = getParamCount() - varargIndex;
/*  79 */         Object array = null;
/*  80 */         if (length == 1) {
/*     */           
/*  82 */           Object param = getParam(varargIndex).eval(bindings, context);
/*  83 */           if (param != null && param.getClass().isArray()) {
/*     */             
/*  85 */             if (types[varargIndex].isInstance(param)) {
/*     */               
/*  87 */               array = param;
/*     */             }
/*     */             else {
/*     */               
/*  91 */               length = Array.getLength(param);
/*  92 */               array = Array.newInstance(varargType, length);
/*  93 */               for (int j = 0; j < length; j++)
/*     */               {
/*  95 */                 Object elem = Array.get(param, j);
/*  96 */                 if (elem != null || varargType.isPrimitive())
/*     */                 {
/*  98 */                   Array.set(array, j, bindings.convert(elem, varargType));
/*     */                 }
/*     */               }
/*     */             
/*     */             } 
/*     */           } else {
/*     */             
/* 105 */             array = Array.newInstance(varargType, 1);
/* 106 */             if (param != null || varargType.isPrimitive())
/*     */             {
/* 108 */               Array.set(array, 0, bindings.convert(param, varargType));
/*     */             }
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 114 */           array = Array.newInstance(varargType, length);
/* 115 */           for (int j = 0; j < length; j++) {
/*     */             
/* 117 */             Object param = getParam(varargIndex + j).eval(bindings, context);
/* 118 */             if (param != null || varargType.isPrimitive())
/*     */             {
/* 120 */               Array.set(array, j, bindings.convert(param, varargType));
/*     */             }
/*     */           } 
/*     */         } 
/* 124 */         params[varargIndex] = array;
/*     */       }
/*     */       else {
/*     */         
/* 128 */         for (int i = 0; i < params.length; i++) {
/*     */           
/* 130 */           Object param = getParam(i).eval(bindings, context);
/* 131 */           if (param != null || types[i].isPrimitive())
/*     */           {
/* 133 */             params[i] = bindings.convert(param, types[i]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 138 */     return method.invoke(base, params);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/* 144 */     Method method = bindings.getFunction(this.index);
/*     */     
/*     */     try {
/* 147 */       return invoke(bindings, context, null, method);
/*     */     }
/* 149 */     catch (IllegalAccessException e) {
/*     */       
/* 151 */       throw new ELException(LocalMessages.get("error.function.access", new Object[] { this.name }), e);
/*     */     }
/* 153 */     catch (InvocationTargetException e) {
/*     */       
/* 155 */       throw new ELException(LocalMessages.get("error.function.invocation", new Object[] { this.name }), e.getCause());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 162 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 168 */     b.append((bindings != null && bindings.isFunctionBound(this.index)) ? 
/* 169 */         "<fn>" : 
/* 170 */         this.name);
/* 171 */     this.params.appendStructure(b, bindings);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 176 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 181 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVarArgs() {
/* 186 */     return this.varargs;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getParamCount() {
/* 191 */     return this.params.getCardinality();
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstNode getParam(int i) {
/* 196 */     return this.params.getChild(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 201 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 206 */     return (i == 0) ? 
/* 207 */       this.params : 
/* 208 */       null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */