/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.juel.tree.Bindings;
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
/*    */ 
/*    */ public final class AstNumber
/*    */   extends AstLiteral
/*    */ {
/*    */   private final Number value;
/*    */   
/*    */   public AstNumber(Number value) {
/* 27 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object eval(Bindings bindings, ELContext context) {
/* 33 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 45 */     b.append(this.value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */