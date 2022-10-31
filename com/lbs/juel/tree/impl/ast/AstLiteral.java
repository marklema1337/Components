/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
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
/*    */ public abstract class AstLiteral
/*    */   extends AstRightValue
/*    */ {
/*    */   public final int getCardinality() {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public final AstNode getChild(int i) {
/* 27 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstLiteral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */