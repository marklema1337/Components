/*     */ package com.lbs.juel.tree.impl.ast;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
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
/*     */ public class AstBinary
/*     */   extends AstRightValue
/*     */ {
/*     */   public static abstract class SimpleOperator
/*     */     implements Operator
/*     */   {
/*     */     public Object eval(Bindings bindings, ELContext context, AstNode left, AstNode right) {
/*  35 */       return apply((TypeConverter)bindings, left.eval(bindings, context), right.eval(bindings, context));
/*     */     }
/*     */     
/*     */     protected abstract Object apply(TypeConverter param1TypeConverter, Object param1Object1, Object param1Object2);
/*     */   }
/*     */   
/*  41 */   public static final Operator ADD = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/*  46 */         return NumberOperations.add(converter, o1, o2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  52 */         return "+";
/*     */       }
/*     */     };
/*  55 */   public static final Operator AND = new Operator()
/*     */     {
/*     */       public Object eval(Bindings bindings, ELContext context, AstNode left, AstNode right)
/*     */       {
/*  59 */         Boolean l = (Boolean)bindings.convert(left.eval(bindings, context), Boolean.class);
/*  60 */         return Boolean.TRUE.equals(l) ? 
/*  61 */           bindings.convert(right.eval(bindings, context), Boolean.class) : 
/*  62 */           Boolean.FALSE;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  68 */         return "&&";
/*     */       }
/*     */     };
/*  71 */   public static final Operator DIV = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/*  76 */         return NumberOperations.div(converter, o1, o2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  82 */         return "/";
/*     */       }
/*     */     };
/*  85 */   public static final Operator EQ = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/*  90 */         return Boolean.valueOf(BooleanOperations.eq(converter, o1, o2));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/*  96 */         return "==";
/*     */       }
/*     */     };
/*  99 */   public static final Operator GE = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 104 */         return Boolean.valueOf(BooleanOperations.ge(converter, o1, o2));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 110 */         return ">=";
/*     */       }
/*     */     };
/* 113 */   public static final Operator GT = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 118 */         return Boolean.valueOf(BooleanOperations.gt(converter, o1, o2));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 124 */         return ">";
/*     */       }
/*     */     };
/* 127 */   public static final Operator LE = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 132 */         return Boolean.valueOf(BooleanOperations.le(converter, o1, o2));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 138 */         return "<=";
/*     */       }
/*     */     };
/* 141 */   public static final Operator LT = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 146 */         return Boolean.valueOf(BooleanOperations.lt(converter, o1, o2));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 152 */         return "<";
/*     */       }
/*     */     };
/* 155 */   public static final Operator MOD = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 160 */         return NumberOperations.mod(converter, o1, o2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 166 */         return "%";
/*     */       }
/*     */     };
/* 169 */   public static final Operator MUL = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 174 */         return NumberOperations.mul(converter, o1, o2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 180 */         return "*";
/*     */       }
/*     */     };
/* 183 */   public static final Operator NE = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 188 */         return Boolean.valueOf(BooleanOperations.ne(converter, o1, o2));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 194 */         return "!=";
/*     */       }
/*     */     };
/* 197 */   public static final Operator OR = new Operator()
/*     */     {
/*     */       public Object eval(Bindings bindings, ELContext context, AstNode left, AstNode right)
/*     */       {
/* 201 */         Boolean l = (Boolean)bindings.convert(left.eval(bindings, context), Boolean.class);
/* 202 */         return Boolean.TRUE.equals(l) ? 
/* 203 */           Boolean.TRUE : 
/* 204 */           bindings.convert(right.eval(bindings, context), Boolean.class);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 210 */         return "||";
/*     */       }
/*     */     };
/* 213 */   public static final Operator SUB = new SimpleOperator()
/*     */     {
/*     */       
/*     */       public Object apply(TypeConverter converter, Object o1, Object o2)
/*     */       {
/* 218 */         return NumberOperations.sub(converter, o1, o2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String toString() {
/* 224 */         return "-";
/*     */       }
/*     */     };
/*     */   
/*     */   private final Operator operator;
/*     */   private final AstNode left;
/*     */   private final AstNode right;
/*     */   
/*     */   public AstBinary(AstNode left, AstNode right, Operator operator) {
/* 233 */     this.left = left;
/* 234 */     this.right = right;
/* 235 */     this.operator = operator;
/*     */   }
/*     */ 
/*     */   
/*     */   public Operator getOperator() {
/* 240 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eval(Bindings bindings, ELContext context) {
/* 246 */     return this.operator.eval(bindings, context, this.left, this.right);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 252 */     return "'" + this.operator.toString() + "'";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 258 */     this.left.appendStructure(b, bindings);
/* 259 */     b.append(' ');
/* 260 */     b.append(this.operator);
/* 261 */     b.append(' ');
/* 262 */     this.right.appendStructure(b, bindings);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardinality() {
/* 267 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public AstNode getChild(int i) {
/* 272 */     return (i == 0) ? 
/* 273 */       this.left : (
/* 274 */       (i == 1) ? 
/* 275 */       this.right : 
/* 276 */       null);
/*     */   }
/*     */   
/*     */   public static interface Operator {
/*     */     Object eval(Bindings param1Bindings, ELContext param1ELContext, AstNode param1AstNode1, AstNode param1AstNode2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstBinary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */