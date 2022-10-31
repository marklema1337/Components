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
/*     */ public class AstMethod
/*     */   extends AstNode
/*     */ {
/*     */   private final AstProperty property;
/*     */   private final AstParameters params;
/*     */   
/*     */   public AstMethod(AstProperty property, AstParameters params) {
/*  35 */     this.property = property;
/*  36 */     this.params = params;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getType(Bindings bindings, ELContext context) {
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(Bindings bindings, ELContext context) {
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Bindings bindings, ELContext context, Object value) {
/*  56 */     throw new ELException(LocalMessages.get("error.value.set.rvalue", new Object[] { getStructuralId(bindings) }));
/*     */   }
/*     */ 
/*     */   
/*     */   public MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeftValue() {
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMethodInvocation() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public final ValueReference getValueReference(Bindings bindings, ELContext context) {
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder builder, Bindings bindings) {
/*  82 */     this.property.appendStructure(builder, bindings);
/*  83 */     this.params.appendStructure(builder, bindings);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object eval(Bindings bindings, ELContext context, boolean answerNullIfBaseIsNull) {
/*  88 */     Object base = this.property.getPrefix().eval(bindings, context);
/*  89 */     if (base == null) {
/*     */       
/*  91 */       if (answerNullIfBaseIsNull)
/*     */       {
/*  93 */         return null;
/*     */       }
/*  95 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", new Object[] { this.property.getPrefix() }));
/*     */     } 
/*  97 */     Object method = this.property.getProperty(bindings, context);
/*  98 */     if (method == null)
/*     */     {
/* 100 */       throw new PropertyNotFoundException(LocalMessages.get("error.property.method.notfound", new Object[] { "null", base }));
/*     */     }
/* 102 */     String name = (String)bindings.convert(method, String.class);
/*     */     
/* 104 */     context.setPropertyResolved(false);
/* 105 */     Object result = context.getELResolver().invoke(context, base, name, null, this.params.eval(bindings, context));
/* 106 */     if (!context.isPropertyResolved())
/*     */     {
/* 108 */       throw new MethodNotFoundException(LocalMessages.get("error.property.method.notfound", new Object[] { name, base.getClass() }));
/*     */     }
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/* 116 */     return eval(bindings, context, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes, Object[] paramValues) {
/* 121 */     return eval(bindings, context, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 126 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Node getChild(int i) {
/* 131 */     return (i == 0) ? 
/* 132 */       (Node)this.property : (
/* 133 */       (i == 1) ? 
/* 134 */       (Node)this.params : 
/* 135 */       null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 141 */     return "<method>";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */