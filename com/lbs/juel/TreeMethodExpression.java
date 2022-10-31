/*     */ package com.lbs.juel;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.FunctionMapper;
/*     */ import com.lbs.javax.el.MethodExpression;
/*     */ import com.lbs.javax.el.MethodInfo;
/*     */ import com.lbs.javax.el.VariableMapper;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.misc.TypeConverter;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.ExpressionNode;
/*     */ import com.lbs.juel.tree.Node;
/*     */ import com.lbs.juel.tree.NodePrinter;
/*     */ import com.lbs.juel.tree.Tree;
/*     */ import com.lbs.juel.tree.TreeBuilder;
/*     */ import com.lbs.juel.tree.TreeStore;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.PrintWriter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TreeMethodExpression
/*     */   extends MethodExpression
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final TreeBuilder builder;
/*     */   private final Bindings bindings;
/*     */   private final String expr;
/*     */   private final Class<?> type;
/*     */   private final Class<?>[] types;
/*     */   private final boolean deferred;
/*     */   private transient ExpressionNode node;
/*     */   private String structure;
/*     */   
/*     */   public TreeMethodExpression(TreeStore store, FunctionMapper functions, VariableMapper variables, TypeConverter converter, String expr, Class<?> returnType, Class[] paramTypes) {
/*  79 */     Tree tree = store.get(expr);
/*     */     
/*  81 */     this.builder = store.getBuilder();
/*  82 */     this.bindings = tree.bind(functions, variables, converter);
/*  83 */     this.expr = expr;
/*  84 */     this.type = returnType;
/*  85 */     this.types = paramTypes;
/*  86 */     this.node = tree.getRoot();
/*  87 */     this.deferred = tree.isDeferred();
/*     */     
/*  89 */     if (this.node.isLiteralText()) {
/*     */       
/*  91 */       if (returnType == void.class || returnType == Void.class)
/*     */       {
/*  93 */         throw new ELException(LocalMessages.get("error.method.literal.void", new Object[] { expr }));
/*     */       }
/*     */     }
/*  96 */     else if (!this.node.isMethodInvocation()) {
/*     */       
/*  98 */       if (!this.node.isLeftValue())
/*     */       {
/* 100 */         throw new ELException(LocalMessages.get("error.method.invalid", new Object[] { expr }));
/*     */       }
/* 102 */       if (paramTypes == null)
/*     */       {
/* 104 */         throw new NullPointerException(LocalMessages.get("error.method.notypes", new Object[0]));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getStructuralId() {
/* 111 */     if (this.structure == null)
/*     */     {
/* 113 */       this.structure = this.node.getStructuralId(this.bindings);
/*     */     }
/* 115 */     return this.structure;
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
/*     */   public MethodInfo getMethodInfo(ELContext context) throws ELException {
/* 127 */     return this.node.getMethodInfo(this.bindings, context, this.type, this.types);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExpressionString() {
/* 133 */     return this.expr;
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
/*     */   public Object invoke(ELContext context, Object[] paramValues) throws ELException {
/* 146 */     return this.node.invoke(this.bindings, context, this.type, this.types, paramValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/* 155 */     return this.node.isLiteralText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isParmetersProvided() {
/* 164 */     return this.node.isMethodInvocation();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeferred() {
/* 172 */     return this.deferred;
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
/*     */   public boolean equals(Object obj) {
/* 191 */     if (obj != null && obj.getClass() == getClass()) {
/*     */       
/* 193 */       TreeMethodExpression other = (TreeMethodExpression)obj;
/* 194 */       if (!this.builder.equals(other.builder))
/*     */       {
/* 196 */         return false;
/*     */       }
/* 198 */       if (this.type != other.type)
/*     */       {
/* 200 */         return false;
/*     */       }
/* 202 */       if (!Arrays.equals((Object[])this.types, (Object[])other.types))
/*     */       {
/* 204 */         return false;
/*     */       }
/* 206 */       return (getStructuralId().equals(other.getStructuralId()) && this.bindings.equals(other.bindings));
/*     */     } 
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 214 */     return getStructuralId().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 220 */     return "TreeMethodExpression(" + this.expr + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter writer) {
/* 229 */     NodePrinter.dump(writer, (Node)this.node);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 234 */     in.defaultReadObject();
/*     */     
/*     */     try {
/* 237 */       this.node = this.builder.build(this.expr).getRoot();
/*     */     }
/* 239 */     catch (ELException e) {
/*     */       
/* 241 */       throw new IOException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\TreeMethodExpression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */