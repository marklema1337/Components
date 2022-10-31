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
/*    */ 
/*    */ public final class AstNull
/*    */   extends AstLiteral
/*    */ {
/*    */   public Object eval(Bindings bindings, ELContext context) {
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     return "null";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 38 */     b.append("null");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstNull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */