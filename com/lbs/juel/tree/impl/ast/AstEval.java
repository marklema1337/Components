/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.MethodInfo;
/*     */ import com.lbs.javax.el.ValueReference;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AstEval
/*     */   extends AstNode
/*     */ {
/*     */   private final AstNode child;
/*     */   private final boolean deferred;
/*     */   
/*     */   public AstEval(AstNode child, boolean deferred) {
/*  30 */     this.child = child;
/*  31 */     this.deferred = deferred;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDeferred() {
/*  36 */     return this.deferred;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeftValue() {
/*  41 */     return getChild(0).isLeftValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMethodInvocation() {
/*  46 */     return getChild(0).isMethodInvocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueReference getValueReference(Bindings bindings, ELContext context) {
/*  51 */     return this.child.getValueReference(bindings, context);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/*  57 */     return this.child.eval(bindings, context);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  63 */     return 
/*     */       
/*  65 */       String.valueOf(this.deferred ? "#" : "$") + "{...}";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder b, Bindings bindings) {
/*  71 */     b.append(this.deferred ? 
/*  72 */         "#{" : 
/*  73 */         "${");
/*  74 */     this.child.appendStructure(b, bindings);
/*  75 */     b.append("}");
/*     */   }
/*     */ 
/*     */   
/*     */   public MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/*  80 */     return this.child.getMethodInfo(bindings, context, returnType, paramTypes);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes, Object[] paramValues) {
/*  85 */     return this.child.invoke(bindings, context, returnType, paramTypes, paramValues);
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getType(Bindings bindings, ELContext context) {
/*  90 */     return this.child.getType(bindings, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/*  95 */     return this.child.isLiteralText();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(Bindings bindings, ELContext context) {
/* 100 */     return this.child.isReadOnly(bindings, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Bindings bindings, ELContext context, Object value) {
/* 105 */     this.child.setValue(bindings, context, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 110 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 115 */     return (i == 0) ? 
/* 116 */       this.child : 
/* 117 */       null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstEval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */