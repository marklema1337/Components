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
/*    */ public class AstComposite
/*    */   extends AstRightValue
/*    */ {
/*    */   private final List<AstNode> nodes;
/*    */   
/*    */   public AstComposite(List<AstNode> nodes) {
/* 29 */     this.nodes = nodes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object eval(Bindings bindings, ELContext context) {
/* 35 */     StringBuilder b = new StringBuilder(16);
/* 36 */     for (int i = 0; i < getCardinality(); i++)
/*    */     {
/* 38 */       b.append((String)bindings.convert(((AstNode)this.nodes.get(i)).eval(bindings, context), String.class));
/*    */     }
/* 40 */     return b.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "composite";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 52 */     for (int i = 0; i < getCardinality(); i++)
/*    */     {
/* 54 */       ((AstNode)this.nodes.get(i)).appendStructure(b, bindings);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCardinality() {
/* 60 */     return this.nodes.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public AstNode getChild(int i) {
/* 65 */     return this.nodes.get(i);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstComposite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */