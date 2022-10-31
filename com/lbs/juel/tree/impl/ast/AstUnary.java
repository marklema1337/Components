/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.juel.misc.BooleanOperations;
/*     */ import com.lbs.juel.misc.NumberOperations;
/*     */ import com.lbs.juel.misc.TypeConverter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AstUnary
/*     */   extends AstRightValue
/*     */ {
/*     */   public static abstract class SimpleOperator
/*     */     implements Operator
/*     */   {
/*     */     public Object eval(Bindings bindings, ELContext context, AstNode node) {
/*  36 */       return apply((TypeConverter)bindings, node.eval(bindings, context));
/*     */     }
/*     */     
/*     */     protected abstract Object apply(TypeConverter param1TypeConverter, Object param1Object);
/*     */   }
/*     */   
/*  42 */   public static final Operator EMPTY = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o)
/*     */       {
/*  47 */         return Boolean.valueOf(BooleanOperations.empty(converter, o));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  53 */         return "empty";
/*     */       }
/*     */     };
/*  56 */   public static final Operator NEG = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o)
/*     */       {
/*  61 */         return NumberOperations.neg(converter, o);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  67 */         return "-";
/*     */       }
/*     */     };
/*  70 */   public static final Operator NOT = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o)
/*     */       {
/*  75 */         return Boolean.valueOf(!((Boolean)converter.convert(o, Boolean.class)).booleanValue());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  81 */         return "!";
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private final Operator operator;
/*     */   private final AstNode child;
/*     */   
/*     */   public AstUnary(AstNode child, Operator operator) {
/*  90 */     this.child = child;
/*  91 */     this.operator = operator;
/*     */   }
/*     */ 
/*     */   
/*     */   public Operator getOperator() {
/*  96 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) throws ELException {
/* 102 */     return this.operator.eval(bindings, context, this.child);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     return "'" + this.operator.toString() + "'";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 114 */     b.append(this.operator);
/* 115 */     b.append(' ');
/* 116 */     this.child.appendStructure(b, bindings);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 121 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 126 */     return (i == 0) ? 
/* 127 */       this.child : 
/* 128 */       null;
/*     */   }
/*     */   
/*     */   public static interface Operator {
/*     */     Object eval(Bindings param1Bindings, ELContext param1ELContext, AstNode param1AstNode);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstUnary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */