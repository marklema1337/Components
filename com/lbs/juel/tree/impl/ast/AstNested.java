/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
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
/*    */ public final class AstNested
/*    */   extends AstRightValue
/*    */ {
/*    */   private final AstNode child;
/*    */   
/*    */   public AstNested(AstNode child) {
/* 27 */     this.child = child;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object eval(Bindings bindings, ELContext context) {
/* 33 */     return this.child.eval(bindings, context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     return "(...)";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 45 */     b.append("(");
/* 46 */     this.child.appendStructure(b, bindings);
/* 47 */     b.append(")");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCardinality() {
/* 52 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AstNode getChild(int i) {
/* 57 */     return (i == 0) ? 
/* 58 */       this.child : 
/* 59 */       null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstNested.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */