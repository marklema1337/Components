/*    */ package com.lbs.juel.tree;
/*    */ 
/*    */ import com.lbs.javax.el.ELException;
/*    */ import com.lbs.juel.misc.LocalMessages;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeBuilderException
/*    */   extends ELException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final String expression;
/*    */   private final int position;
/*    */   private final String encountered;
/*    */   private final String expected;
/*    */   
/*    */   public TreeBuilderException(String expression, int position, String encountered, String expected, String message) {
/* 37 */     super(LocalMessages.get("error.build", new Object[] { expression, message }));
/* 38 */     this.expression = expression;
/* 39 */     this.position = position;
/* 40 */     this.encountered = encountered;
/* 41 */     this.expected = expected;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getExpression() {
/* 49 */     return this.expression;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPosition() {
/* 57 */     return this.position;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEncountered() {
/* 65 */     return this.encountered;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getExpected() {
/* 73 */     return this.expected;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\TreeBuilderException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */