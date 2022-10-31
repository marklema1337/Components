/*     */ package com.lbs.juel.tree;
/*     */ 
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.FunctionMapper;
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.javax.el.VariableMapper;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.misc.TypeConverter;
/*     */ import java.lang.reflect.Method;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tree
/*     */ {
/*     */   private final ExpressionNode root;
/*     */   private final List<FunctionNode> functions;
/*     */   private final List<IdentifierNode> identifiers;
/*     */   private final boolean deferred;
/*     */   
/*     */   public Tree(ExpressionNode root, List<FunctionNode> functions, List<IdentifierNode> identifiers, boolean deferred) {
/*  55 */     this.root = root;
/*  56 */     this.functions = functions;
/*  57 */     this.identifiers = identifiers;
/*  58 */     this.deferred = deferred;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<FunctionNode> getFunctionNodes() {
/*  66 */     return this.functions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<IdentifierNode> getIdentifierNodes() {
/*  74 */     return this.identifiers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpressionNode getRoot() {
/*  82 */     return this.root;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDeferred() {
/*  87 */     return this.deferred;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  93 */     return getRoot().getStructuralId(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bindings bind(FunctionMapper fnMapper, VariableMapper varMapper) {
/* 104 */     return bind(fnMapper, varMapper, null);
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
/*     */   public Bindings bind(FunctionMapper fnMapper, VariableMapper varMapper, TypeConverter converter) {
/* 116 */     Method[] methods = null;
/* 117 */     if (!this.functions.isEmpty()) {
/*     */       
/* 119 */       if (fnMapper == null)
/*     */       {
/* 121 */         throw new ELException(LocalMessages.get("error.function.nomapper", new Object[0]));
/*     */       }
/* 123 */       methods = new Method[this.functions.size()];
/* 124 */       for (int i = 0; i < this.functions.size(); i++) {
/*     */         
/* 126 */         FunctionNode node = this.functions.get(i);
/* 127 */         String image = node.getName();
/* 128 */         Method method = null;
/* 129 */         int colon = image.indexOf(':');
/* 130 */         if (colon < 0) {
/*     */           
/* 132 */           method = fnMapper.resolveFunction("", image);
/*     */         }
/*     */         else {
/*     */           
/* 136 */           method = fnMapper.resolveFunction(image.substring(0, colon), image.substring(colon + 1));
/*     */         } 
/* 138 */         if (method == null)
/*     */         {
/* 140 */           throw new ELException(LocalMessages.get("error.function.notfound", new Object[] { image }));
/*     */         }
/* 142 */         if (node.isVarArgs() && method.isVarArgs()) {
/*     */           
/* 144 */           if ((method.getParameterTypes()).length > node.getParamCount() + 1)
/*     */           {
/* 146 */             throw new ELException(LocalMessages.get("error.function.params", new Object[] { image }));
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 151 */         else if ((method.getParameterTypes()).length != node.getParamCount()) {
/*     */           
/* 153 */           throw new ELException(LocalMessages.get("error.function.params", new Object[] { image }));
/*     */         } 
/*     */         
/* 156 */         methods[node.getIndex()] = method;
/*     */       } 
/*     */     } 
/* 159 */     ValueExpression[] expressions = null;
/* 160 */     if (this.identifiers.size() > 0) {
/*     */       
/* 162 */       expressions = new ValueExpression[this.identifiers.size()];
/* 163 */       for (int i = 0; i < this.identifiers.size(); i++) {
/*     */         
/* 165 */         IdentifierNode node = this.identifiers.get(i);
/* 166 */         ValueExpression expression = null;
/* 167 */         if (varMapper != null)
/*     */         {
/* 169 */           expression = varMapper.resolveVariable(node.getName());
/*     */         }
/* 171 */         expressions[node.getIndex()] = expression;
/*     */       } 
/*     */     } 
/* 174 */     return new Bindings(methods, expressions, converter);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\Tree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */