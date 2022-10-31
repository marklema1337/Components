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
/*    */ public class AstChoice
/*    */   extends AstRightValue
/*    */ {
/*    */   private final AstNode question;
/*    */   private final AstNode yes;
/*    */   private final AstNode no;
/*    */   
/*    */   public AstChoice(AstNode question, AstNode yes, AstNode no) {
/* 28 */     this.question = question;
/* 29 */     this.yes = yes;
/* 30 */     this.no = no;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object eval(Bindings bindings, ELContext context) throws ELException {
/* 36 */     Boolean value = (Boolean)bindings.convert(this.question.eval(bindings, context), Boolean.class);
/* 37 */     return value.booleanValue() ? 
/* 38 */       this.yes.eval(bindings, context) : 
/* 39 */       this.no.eval(bindings, context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     return "?";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 51 */     this.question.appendStructure(b, bindings);
/* 52 */     b.append(" ? ");
/* 53 */     this.yes.appendStructure(b, bindings);
/* 54 */     b.append(" : ");
/* 55 */     this.no.appendStructure(b, bindings);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCardinality() {
/* 60 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AstNode getChild(int i) {
/* 65 */     return (i == 0) ? 
/* 66 */       this.question : (
/* 67 */       (i == 1) ? 
/* 68 */       this.yes : (
/* 69 */       (i == 2) ? 
/* 70 */       this.no : 
/* 71 */       null));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */