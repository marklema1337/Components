/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.MethodInfo;
/*     */ import com.lbs.javax.el.ValueReference;
/*     */ import com.lbs.juel.misc.LocalMessages;
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
/*     */ public final class AstText
/*     */   extends AstNode
/*     */ {
/*     */   private final String value;
/*     */   
/*     */   public AstText(String value) {
/*  31 */     this.value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/*  36 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeftValue() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMethodInvocation() {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getType(Bindings bindings, ELContext context) {
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(Bindings bindings, ELContext context) {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Bindings bindings, ELContext context, Object value) {
/*  61 */     throw new ELException(LocalMessages.get("error.value.set.rvalue", new Object[] { getStructuralId(bindings) }));
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueReference getValueReference(Bindings bindings, ELContext context) {
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/*  72 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes, Object[] paramValues) {
/*  82 */     return (returnType == null) ? 
/*  83 */       this.value : 
/*  84 */       bindings.convert(this.value, returnType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  90 */     return "\"" + this.value + "\"";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder b, Bindings bindings) {
/*  96 */     int end = this.value.length() - 1;
/*  97 */     for (int i = 0; i < end; i++) {
/*     */       
/*  99 */       char c = this.value.charAt(i);
/* 100 */       if ((c == '#' || c == '$') && this.value.charAt(i + 1) == '{')
/*     */       {
/* 102 */         b.append('\\');
/*     */       }
/* 104 */       b.append(c);
/*     */     } 
/* 106 */     if (end >= 0)
/*     */     {
/* 108 */       b.append(this.value.charAt(end));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 114 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 119 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */