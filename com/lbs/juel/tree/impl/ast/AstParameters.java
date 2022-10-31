/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.juel.tree.Bindings;
/*    */ import com.lbs.juel.tree.Node;
/*    */ import java.util.List;
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
/*    */ public class AstParameters
/*    */   extends AstRightValue
/*    */ {
/*    */   private final List<AstNode> nodes;
/*    */   
/*    */   public AstParameters(List<AstNode> nodes) {
/* 29 */     this.nodes = nodes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object[] eval(Bindings bindings, ELContext context) {
/* 35 */     Object[] result = new Object[this.nodes.size()];
/* 36 */     for (int i = 0; i < this.nodes.size(); i++)
/*    */     {
/* 38 */       result[i] = ((AstNode)this.nodes.get(i)).eval(bindings, context);
/*    */     }
/* 40 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "(...)";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder builder, Bindings bindings) {
/* 52 */     builder.append("(");
/* 53 */     for (int i = 0; i < this.nodes.size(); i++) {
/*    */       
/* 55 */       if (i > 0)
/*    */       {
/* 57 */         builder.append(", ");
/*    */       }
/* 59 */       ((AstNode)this.nodes.get(i)).appendStructure(builder, bindings);
/*    */     } 
/* 61 */     builder.append(")");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCardinality() {
/* 66 */     return this.nodes.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public AstNode getChild(int i) {
/* 71 */     return this.nodes.get(i);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */