/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.javax.el.ELException;
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
/*    */ public class AstDot
/*    */   extends AstProperty
/*    */ {
/*    */   protected final String property;
/*    */   
/*    */   public AstDot(AstNode base, String property, boolean lvalue) {
/* 28 */     this(base, property, lvalue, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public AstDot(AstNode base, String property, boolean lvalue, boolean ignoreReturnType) {
/* 33 */     super(base, lvalue, true, ignoreReturnType);
/* 34 */     this.property = property;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getProperty(Bindings bindings, ELContext context) throws ELException {
/* 40 */     return this.property;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return ". " + this.property;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 52 */     getChild(0).appendStructure(b, bindings);
/* 53 */     b.append(".");
/* 54 */     b.append(this.property);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCardinality() {
/* 59 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstDot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */