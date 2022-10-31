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
/*    */ public final class AstString
/*    */   extends AstLiteral
/*    */ {
/*    */   private final String value;
/*    */   
/*    */   public AstString(String value) {
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
/* 39 */     return "\"" + this.value + "\"";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void appendStructure(StringBuilder b, Bindings bindings) {
/* 45 */     b.append("'");
/* 46 */     int length = this.value.length();
/* 47 */     for (int i = 0; i < length; i++) {
/*    */       
/* 49 */       char c = this.value.charAt(i);
/* 50 */       if (c == '\\' || c == '\'')
/*    */       {
/* 52 */         b.append('\\');
/*    */       }
/* 54 */       b.append(c);
/*    */     } 
/* 56 */     b.append("'");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */