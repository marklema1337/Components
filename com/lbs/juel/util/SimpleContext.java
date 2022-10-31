/*     */ package com.lbs.juel.util;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELResolver;
/*     */ import com.lbs.javax.el.FunctionMapper;
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.javax.el.VariableMapper;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
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
/*     */ public class SimpleContext
/*     */   extends ELContext
/*     */ {
/*     */   private Functions functions;
/*     */   private Variables variables;
/*     */   private ELResolver resolver;
/*     */   
/*     */   static class Functions
/*     */     extends FunctionMapper
/*     */   {
/*  38 */     Map<String, Method> map = Collections.emptyMap();
/*     */ 
/*     */ 
/*     */     
/*     */     public Method resolveFunction(String prefix, String localName) {
/*  43 */       return this.map.get(String.valueOf(prefix) + ":" + localName);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setFunction(String prefix, String localName, Method method) {
/*  48 */       if (this.map.isEmpty())
/*     */       {
/*  50 */         this.map = new HashMap<>();
/*     */       }
/*  52 */       this.map.put(String.valueOf(prefix) + ":" + localName, method);
/*     */     }
/*     */   }
/*     */   
/*     */   static class Variables extends VariableMapper {
/*     */     Variables() {
/*  58 */       this.map = Collections.emptyMap();
/*     */     }
/*     */     Map<String, ValueExpression> map;
/*     */     
/*     */     public ValueExpression resolveVariable(String variable) {
/*  63 */       return this.map.get(variable);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ValueExpression setVariable(String variable, ValueExpression expression) {
/*  69 */       if (this.map.isEmpty())
/*     */       {
/*  71 */         this.map = new HashMap<>();
/*     */       }
/*  73 */       return this.map.put(variable, expression);
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
/*     */   public SimpleContext() {
/*  86 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleContext(ELResolver resolver) {
/*  94 */     this.resolver = resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunction(String prefix, String localName, Method method) {
/* 102 */     if (this.functions == null)
/*     */     {
/* 104 */       this.functions = new Functions();
/*     */     }
/* 106 */     this.functions.setFunction(prefix, localName, method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValueExpression setVariable(String name, ValueExpression expression) {
/* 114 */     if (this.variables == null)
/*     */     {
/* 116 */       this.variables = new Variables();
/*     */     }
/* 118 */     return this.variables.setVariable(name, expression);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FunctionMapper getFunctionMapper() {
/* 127 */     if (this.functions == null)
/*     */     {
/* 129 */       this.functions = new Functions();
/*     */     }
/* 131 */     return this.functions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableMapper getVariableMapper() {
/* 140 */     if (this.variables == null)
/*     */     {
/* 142 */       this.variables = new Variables();
/*     */     }
/* 144 */     return this.variables;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ELResolver getELResolver() {
/* 153 */     if (this.resolver == null)
/*     */     {
/* 155 */       this.resolver = new SimpleResolver();
/*     */     }
/* 157 */     return this.resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setELResolver(ELResolver resolver) {
/* 167 */     this.resolver = resolver;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\jue\\util\SimpleContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */