/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.javax.el.ELException;
/*    */ import com.lbs.juel.tree.Bindings;
/*    */ import com.lbs.juel.tree.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AstBracket
/*    */   extends AstProperty
/*    */ {
/*    */   protected final AstNode property;
/*    */   
/*    */   public AstBracket(AstNode base, AstNode property, boolean lvalue, boolean strict) {
/* 28 */     this(base, property, lvalue, strict, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public AstBracket(AstNode base, AstNode property, boolean lvalue, boolean strict, boolean ignoreReturnType) {
/* 33 */     super(base, lvalue, strict, ignoreReturnType);
/* 34 */     this.property = property;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Object getProperty(Bindings bindings, ELContext context) throws ELException {
/* 40 */     return this.property.eval(bindings, context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "[...]";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 52 */     getChild(0).appendStructure(b, bindings);
/* 53 */     b.append("[");
/* 54 */     getChild(1).appendStructure(b, bindings);
/* 55 */     b.append("]");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCardinality() {
/* 60 */     return 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AstNode getChild(int i) {
/* 66 */     return (i == 1) ? 
/* 67 */       this.property : 
/* 68 */       super.getChild(i);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstBracket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */